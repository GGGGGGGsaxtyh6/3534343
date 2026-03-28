import os
import io
import json
import zipfile
import hashlib
import secrets
import sqlite3
import base64
from datetime import datetime, timedelta
from functools import wraps

from flask import (
    Flask, request, jsonify, render_template, redirect,
    url_for, session, send_file, make_response, g
)
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import padding as sym_padding
from cryptography.hazmat.backends import default_backend

app = Flask(__name__)
app.secret_key = os.environ.get("SECRET_KEY", secrets.token_hex(32))

DATABASE = "/data/nginx_ui.db"
BACKUP_AES_KEY = os.environ.get("BACKUP_KEY", secrets.token_hex(16))
FLAG = os.environ.get("FLAG", "flag{CVE-2026-27944_unauth_backup_l3ak_n1nxUI_cr1t1c4l}")

# ---------------------------------------------------------------------------
# Database helpers
# ---------------------------------------------------------------------------

def get_db():
    db = getattr(g, "_database", None)
    if db is None:
        db = g._database = sqlite3.connect(DATABASE)
        db.row_factory = sqlite3.Row
    return db


@app.teardown_appcontext
def close_db(exception):
    db = getattr(g, "_database", None)
    if db is not None:
        db.close()


def init_db():
    os.makedirs(os.path.dirname(DATABASE), exist_ok=True)
    db = sqlite3.connect(DATABASE)
    db.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username TEXT UNIQUE, password_hash TEXT, role TEXT, api_token TEXT)")
    db.execute("CREATE TABLE IF NOT EXISTS sessions (id INTEGER PRIMARY KEY, user_id INTEGER, token TEXT, created_at TEXT)")
    db.execute("CREATE TABLE IF NOT EXISTS ssl_certificates (id INTEGER PRIMARY KEY, domain TEXT, private_key TEXT, certificate TEXT)")
    db.execute("CREATE TABLE IF NOT EXISTS nginx_configs (id INTEGER PRIMARY KEY, name TEXT, content TEXT)")
    db.execute("CREATE TABLE IF NOT EXISTS audit_log (id INTEGER PRIMARY KEY, action TEXT, user TEXT, timestamp TEXT, details TEXT)")

    admin_hash = hashlib.sha256("Sup3r$ecur3P@ss!2026".encode()).hexdigest()
    admin_token = secrets.token_hex(32)

    try:
        db.execute(
            "INSERT INTO users (username, password_hash, role, api_token) VALUES (?, ?, ?, ?)",
            ("admin", admin_hash, "superadmin", admin_token),
        )
    except sqlite3.IntegrityError:
        pass

    operator_hash = hashlib.sha256("0p3r@t0r#Nginx".encode()).hexdigest()
    operator_token = secrets.token_hex(32)
    try:
        db.execute(
            "INSERT INTO users (username, password_hash, role, api_token) VALUES (?, ?, ?, ?)",
            ("operator", operator_hash, "operator", operator_token),
        )
    except sqlite3.IntegrityError:
        pass

    try:
        db.execute(
            "INSERT INTO ssl_certificates (domain, private_key, certificate) VALUES (?, ?, ?)",
            (
                "nginx-ui.internal",
                "-----BEGIN RSA PRIVATE KEY-----\nMIIEpAIBAAKCAQEA7v8F...[REDACTED]...k9Q==\n-----END RSA PRIVATE KEY-----",
                "-----BEGIN CERTIFICATE-----\nMIIDdzCCAl+gAwIBAgIEU...[REDACTED]...w==\n-----END CERTIFICATE-----",
            ),
        )
    except sqlite3.IntegrityError:
        pass

    try:
        db.execute(
            "INSERT INTO ssl_certificates (domain, private_key, certificate) VALUES (?, ?, ?)",
            (
                "flag.internal",
                f"-----BEGIN FLAG PRIVATE KEY-----\n{FLAG}\n-----END FLAG PRIVATE KEY-----",
                "-----BEGIN CERTIFICATE-----\nMIIDflag...cert...data\n-----END CERTIFICATE-----",
            ),
        )
    except sqlite3.IntegrityError:
        pass

    nginx_conf = """server {
    listen 80;
    server_name nginx-ui.internal;
    location / {
        proxy_pass http://127.0.0.1:9000;
        proxy_set_header Host $host;
    }
    location /admin {
        auth_basic "Restricted";
        proxy_pass http://127.0.0.1:9000/admin;
    }
}"""
    try:
        db.execute("INSERT INTO nginx_configs (name, content) VALUES (?, ?)", ("default.conf", nginx_conf))
    except sqlite3.IntegrityError:
        pass

    db.execute(
        "INSERT INTO audit_log (action, user, timestamp, details) VALUES (?, ?, ?, ?)",
        ("system_init", "system", datetime.utcnow().isoformat(), "Nginx UI initialized successfully"),
    )

    db.commit()
    db.close()


# ---------------------------------------------------------------------------
# Auth middleware
# ---------------------------------------------------------------------------

def login_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        if "user_id" not in session:
            token = request.headers.get("X-Api-Token")
            if not token:
                return jsonify({"error": "Authentication required"}), 401
            db = get_db()
            user = db.execute("SELECT * FROM users WHERE api_token = ?", (token,)).fetchone()
            if not user:
                return jsonify({"error": "Invalid API token"}), 403
        return f(*args, **kwargs)
    return decorated


def admin_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        if "user_id" not in session:
            return jsonify({"error": "Authentication required"}), 401
        db = get_db()
        user = db.execute("SELECT * FROM users WHERE id = ?", (session["user_id"],)).fetchone()
        if not user or user["role"] != "superadmin":
            return jsonify({"error": "Insufficient privileges"}), 403
        return f(*args, **kwargs)
    return decorated


# ---------------------------------------------------------------------------
# Encryption helpers
# ---------------------------------------------------------------------------

def encrypt_backup(data: bytes, key_hex: str) -> tuple[bytes, str]:
    key = bytes.fromhex(key_hex)
    iv = os.urandom(16)
    padder = sym_padding.PKCS7(128).padder()
    padded = padder.update(data) + padder.finalize()
    cipher = Cipher(algorithms.AES(key), modes.CBC(iv), backend=default_backend())
    enc = cipher.encryptor()
    ct = enc.update(padded) + enc.finalize()
    return iv + ct, base64.b64encode(iv).decode()


# ---------------------------------------------------------------------------
# Public routes
# ---------------------------------------------------------------------------

@app.route("/")
def index():
    if "user_id" in session:
        return redirect(url_for("dashboard"))
    return render_template("login.html")


@app.route("/login", methods=["POST"])
def login():
    username = request.form.get("username", "")
    password = request.form.get("password", "")
    pw_hash = hashlib.sha256(password.encode()).hexdigest()
    db = get_db()
    user = db.execute(
        "SELECT * FROM users WHERE username = ? AND password_hash = ?",
        (username, pw_hash),
    ).fetchone()
    if user:
        session["user_id"] = user["id"]
        session["username"] = user["username"]
        session["role"] = user["role"]
        db.execute(
            "INSERT INTO audit_log (action, user, timestamp, details) VALUES (?, ?, ?, ?)",
            ("login", username, datetime.utcnow().isoformat(), f"Login from {request.remote_addr}"),
        )
        db.commit()
        return redirect(url_for("dashboard"))
    return render_template("login.html", error="Invalid credentials")


@app.route("/logout")
def logout():
    session.clear()
    return redirect(url_for("index"))


@app.route("/dashboard")
@login_required
def dashboard():
    return render_template("dashboard.html", username=session.get("username", ""))


# ---------------------------------------------------------------------------
# API endpoints — protected
# ---------------------------------------------------------------------------

@app.route("/api/configs", methods=["GET"])
@login_required
def api_configs():
    db = get_db()
    rows = db.execute("SELECT id, name FROM nginx_configs").fetchall()
    return jsonify([dict(r) for r in rows])


@app.route("/api/configs/<int:cid>", methods=["GET"])
@login_required
def api_config_detail(cid):
    db = get_db()
    row = db.execute("SELECT * FROM nginx_configs WHERE id = ?", (cid,)).fetchone()
    if not row:
        return jsonify({"error": "Not found"}), 404
    return jsonify(dict(row))


@app.route("/api/certificates", methods=["GET"])
@login_required
def api_certificates():
    db = get_db()
    rows = db.execute("SELECT id, domain FROM ssl_certificates").fetchall()
    return jsonify([dict(r) for r in rows])


@app.route("/api/users", methods=["GET"])
@admin_required
def api_users():
    db = get_db()
    rows = db.execute("SELECT id, username, role FROM users").fetchall()
    return jsonify([dict(r) for r in rows])


@app.route("/api/audit", methods=["GET"])
@admin_required
def api_audit():
    db = get_db()
    rows = db.execute("SELECT * FROM audit_log ORDER BY id DESC LIMIT 50").fetchall()
    return jsonify([dict(r) for r in rows])


# ---------------------------------------------------------------------------
# VULNERABLE ENDPOINT — simulates CVE-2026-27944
# ---------------------------------------------------------------------------

@app.route("/api/backup", methods=["GET"])
def api_backup():
    """
    BUG: This endpoint lacks authentication middleware.
    Additionally, the AES key + IV are leaked via response headers,
    allowing immediate decryption of the backup archive.
    """
    db_conn = sqlite3.connect(DATABASE)
    db_conn.row_factory = sqlite3.Row

    users = [dict(r) for r in db_conn.execute("SELECT * FROM users").fetchall()]
    certs = [dict(r) for r in db_conn.execute("SELECT * FROM ssl_certificates").fetchall()]
    configs = [dict(r) for r in db_conn.execute("SELECT * FROM nginx_configs").fetchall()]
    audit = [dict(r) for r in db_conn.execute("SELECT * FROM audit_log ORDER BY id DESC LIMIT 100").fetchall()]
    db_conn.close()

    backup_manifest = {
        "version": "2.3.2",
        "generated_at": datetime.utcnow().isoformat(),
        "hostname": "nginx-ui-prod-01",
        "components": ["users", "certificates", "configs", "audit_log"],
    }

    buf = io.BytesIO()
    with zipfile.ZipFile(buf, "w", zipfile.ZIP_DEFLATED) as zf:
        zf.writestr("manifest.json", json.dumps(backup_manifest, indent=2))
        zf.writestr("users.json", json.dumps(users, indent=2))
        zf.writestr("certificates.json", json.dumps(certs, indent=2))
        zf.writestr("nginx_configs.json", json.dumps(configs, indent=2))
        zf.writestr("audit_log.json", json.dumps(audit, indent=2))

    raw_zip = buf.getvalue()

    encrypted_data, iv_b64 = encrypt_backup(raw_zip, BACKUP_AES_KEY)

    response = make_response(encrypted_data)
    response.headers["Content-Type"] = "application/octet-stream"
    response.headers["Content-Disposition"] = "attachment; filename=nginx-ui-backup.enc"

    security_blob = base64.b64encode(
        json.dumps({
            "algorithm": "AES-256-CBC",
            "key": BACKUP_AES_KEY,
            "iv": iv_b64,
            "integrity": hashlib.sha256(raw_zip).hexdigest(),
        }).encode()
    ).decode()
    response.headers["X-Backup-Security"] = security_blob

    response.headers["X-Backup-Version"] = "2.3.2"
    response.headers["X-Powered-By"] = "Nginx UI/2.3.2"

    return response


# ---------------------------------------------------------------------------
# Red-herring endpoints to increase difficulty
# ---------------------------------------------------------------------------

@app.route("/api/system/health", methods=["GET"])
def health():
    return jsonify({"status": "ok", "version": "2.3.2", "uptime": "14d 3h 22m"})


@app.route("/api/system/info", methods=["GET"])
@login_required
def system_info():
    return jsonify({
        "os": "Ubuntu 22.04 LTS",
        "nginx_version": "1.24.0",
        "ui_version": "2.3.2",
        "cpu_cores": 4,
        "memory_mb": 8192,
    })


@app.route("/api/system/logs", methods=["GET"])
@login_required
def system_logs():
    return jsonify({"logs": [
        {"ts": "2026-03-25T10:00:00", "level": "INFO", "msg": "Service started"},
        {"ts": "2026-03-25T10:01:12", "level": "WARN", "msg": "High memory usage detected"},
        {"ts": "2026-03-25T12:30:45", "level": "ERROR", "msg": "Failed to reload nginx config"},
    ]})


@app.route("/api/backup/schedule", methods=["GET"])
@login_required
def backup_schedule():
    return jsonify({
        "enabled": True,
        "frequency": "daily",
        "retention_days": 30,
        "last_backup": "2026-03-26T03:00:00Z",
        "next_backup": "2026-03-27T03:00:00Z",
        "encryption": "AES-256-CBC",
    })


# ---------------------------------------------------------------------------
# Startup
# ---------------------------------------------------------------------------

if __name__ == "__main__":
    init_db()
    app.run(host="0.0.0.0", port=9000, debug=False)
