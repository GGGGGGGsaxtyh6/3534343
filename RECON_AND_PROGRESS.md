# Flash Telecom CTF Challenge - Reconnaissance & Progress

## Target Infrastructure

### Main Sites
| Subdomain | IP | Stack | Status |
|---|---|---|---|
| flashtelecom.es | Cloudflare | WordPress 6.9.4, PHP 8.1, Plesk, WPBakery 8.7.2, Yoast 27.2 | Accessible |
| panel.flashtelecom.es | Cloudflare → backend | Laravel, PHP 7.4.33, AdminLTE | Login required |
| clientes.flashtelecom.es | Cloudflare → backend | Laravel, PHP 7.4.33 | Login required |
| api.flashtelecom.es | 161.22.43.99 | PHP 7.4.33, Apache 2.4.62 | API key required |
| vuapi.flashtelecom.es | 65.20.103.44 | PHP 7.4.33, Apache 2.4.62, MySQL 3306 exposed | API key required |

### Backup/Secondary
| Subdomain | IP | Stack | Status |
|---|---|---|---|
| backupapi.flashtelecom.es | ? | Laravel, PHP 7.4.33 | Login required |
| vupanel.flashtelecom.es | 65.20.103.44 | Laravel, PHP 7.4.33 | Login required |
| wss.flashtelecom.es | ? | Laravel, PHP 7.4.33 | Login required (untrusted cert) |
| tls.flashtelecom.es | ? | Laravel, PHP 7.4.33 | Login required |
| tlsbackup.flashtelecom.es | ? | Laravel, PHP 7.4.33 | Login required |
| pjsip.flashtelecom.es | ? | Laravel, PHP 7.4.33 | Login required (HTTP only) |
| files.flashtelecom.es | ? | Laravel, PHP 7.4.33 | Login required |

### Infrastructure Services
| Service | URL/IP | Version | Status |
|---|---|---|---|
| n8n | 207.180.231.26:5678 (also :2096) | n8n 2.4.6 (DEVELOPMENT) | Login required, VULNERABLE |
| Mattermost | deploy.flashtelecom.es:443 | 10.11.1 | Invite required for registration |
| osTicket | soporte.flashtelecom.es | < 1.18.3 | VULNERABLE to CVE-2026-22200 |
| Asterisk ARI | 65.20.103.44:8088 | FlashTelecom-A1 | Basic auth required |
| Wazuh | 207.180.231.26:55000 | Wazuh | Auth required |
| Plesk | 2023.flashtelecom.es | Obsidian 18.0.75 | 526 SSL error through CF |
| Prometheus | 65.20.103.44:9100 | Node Exporter | Metrics exposed (no secrets) |
| CloudPanel | dopanel.flashtelecom.es:8443 | 167.172.56.55 | Connection refused |
| dboxfiles | 217.160.102.3 | Laravel, PHP 8.4.18 | Login required |

### Related Domains
- fmeuropa.com → redirects to flashtelecom.es
- panel.telecomgrup.com → redirects to panel.flashtelecom.es
- panelv4.fmeuropa.com → redirects to panel.flashtelecom.es
- api.fmeuropa.com → panel login (different from api.flashtelecom.es)
- betaapi.fmeuropa.com → panel login

## Confirmed Vulnerabilities

### 1. n8n 2.4.6 - CVE-2026-25049 (CVSS 9.4)
- **Type**: Expression Injection → RCE
- **Affected**: n8n >= 2.0.0, < 2.5.2
- **Requirement**: Active webhook/form endpoint OR authenticated access
- **Blocker**: No active webhooks found via fuzzing

### 2. osTicket - CVE-2026-22200 (CVSS 7.5)
- **Type**: Arbitrary File Read via PHP filter chains in PDF export
- **Confirmed**: `check.py` confirms VULNERABLE and EXPLOITABLE by anonymous
- **Progress**: Exploit ticket CREATED successfully
- **Blocker**: Cannot access ticket (need ticket number or staff credentials)

### 3. n8n 2.4.6 - CVE-2026-21858 (Ni8mare, CVSS 10.0)
- **Type**: Unauthenticated File Read → Token Forge → RCE
- **Note**: Primarily affects 1.x branch; 2.x may have separate patch
- **Blocker**: Requires active form/webhook trigger endpoint

## API Documentation (from flashtelecom.es/integracion-api/)
- API key passed as `?apikey=xxxxx` query parameter
- Endpoints:
  - `/v1/get-flatrates-number/<number>?apikey=xxx`
  - `/v1/get-flatrates-user?apikey=xxx`
  - `/numbers/<number>/calls?apikey=xxx`
  - `/numbers/<number>?apikey=xxx`
  - `/user/<client_id>?apikey=xxx`
  - `/numbers/<number>/blacklist?apikey=xxx`
  - `/numbers/<number>/whitelist?apikey=xxx`
  - `/numbers/<number>/recordings?apikey=xxx`

## Key Observations
- Server hostname: "VU-asterisk-y-panel" (from Prometheus)
- AlmaLinux 9.7 (kernel 5.14.0-611.27.1.el9_7)
- Panel `/alta` pages accessible without auth (product selection only)
- Mattermost has `EnableUserCreation: true` and `EnableSignUpWithEmail: true` but `EnableOpenServer: false` (needs invite)
- osTicket account registration enabled but requires email verification
- All API endpoints return "Invalid or missing API key" regardless of path

## Attempted Attacks (Failed)
- SQL injection on panel login (Cloudflare blocks + Laravel ORM)
- SQL injection on API apikey parameter
- PHP type juggling on API
- MySQL brute force (connection timeout/firewall)
- n8n login brute force (140+ combinations)
- Asterisk ARI credential brute force
- Wazuh default credentials
- WordPress user enumeration (blocked by Cloudflare)
- WordPress xmlrpc.php (403)
- osTicket staff panel brute force
- osTicket ticket number brute force (always returns same message)

## BREAKTHROUGH: osTicket CVE-2026-22200 Exploited Successfully

### Attack Chain
1. Registered on osTicket with a temporary email (guerrillamail)
2. Received and clicked confirmation link from `ricardo@grupogestion.com`
3. Created ticket with PHP filter chain payload (CVE-2026-22200)
4. Exported ticket as PDF via `tickets.php?id=10&a=print`
5. Extracted BMP images from PDF containing server files
6. Successfully read `include/ost-config.php`

### Leaked Credentials
- **DB User**: `osticket_5`
- **DB Pass**: `92ZEQ31Awi4QCrrf+`
- **DB Name**: `osticket_e`
- **SECRET_SALT**: `DAkk93HVe9BlBwVkkqvE_84DmKIfQ=sm`
- **Admin Email**: `ricardo@grupogestion.com`

### Password Reuse Success
- `ricardo@grupogestion.com:92ZEQ31Awi4QCrrf+` works on:
  - osTicket Staff Panel (FULL ADMIN ACCESS)
  - panel.flashtelecom.es (customer role - /alta only)
  - backupapi.flashtelecom.es (customer role - /alta only)
  - dboxfiles 217.160.102.3 (customer role - /alta only)

### osTicket Staff Panel Access
- 10 tickets visible (IDs 1-10)
- 11 users registered
- Ticket #6: CyberSOC CERT Deloitte report about phone scam [910032568]
- Admin panel at /scp/admin.php accessible

## Next Steps
1. Read more files via osTicket exploit (Laravel .env from other servers if co-hosted)
2. Use osTicket SQL access to dump data
3. Find API key for api.flashtelecom.es
4. Escalate panel access from customer to admin role
5. Check Plesk admin config for credentials to other services
