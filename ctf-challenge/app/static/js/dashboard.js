document.addEventListener("DOMContentLoaded", function () {
    fetch("/api/audit")
        .then(r => r.json())
        .then(data => {
            const tbody = document.getElementById("audit-log");
            if (!data.length) {
                tbody.innerHTML = "<tr><td colspan='4'>No entries</td></tr>";
                return;
            }
            tbody.innerHTML = data.slice(0, 10).map(e =>
                `<tr>
                    <td>${e.timestamp || "-"}</td>
                    <td>${e.action || "-"}</td>
                    <td>${e.user || "-"}</td>
                    <td>${e.details || "-"}</td>
                </tr>`
            ).join("");
        })
        .catch(() => {
            document.getElementById("audit-log").innerHTML =
                "<tr><td colspan='4'>Failed to load</td></tr>";
        });

    fetch("/api/configs")
        .then(r => r.json())
        .then(data => {
            const el = document.getElementById("configs-list");
            if (!data.length) { el.textContent = "No configs found"; return; }
            el.innerHTML = data.map(c =>
                `<div style="padding:8px 0;border-bottom:1px solid var(--border)">
                    <strong>${c.name}</strong> <span style="color:var(--text-muted)">(ID: ${c.id})</span>
                </div>`
            ).join("");
        })
        .catch(() => {
            document.getElementById("configs-list").textContent = "Failed to load configs";
        });
});
