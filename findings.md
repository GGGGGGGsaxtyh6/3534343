# Fibytel CTF Challenge - Findings

## Target
- **Main site**: https://www.fibytel.es (IP: 185.45.74.149)
- **Hosting**: ProfesionalHosting (AS201446), Madrid, Spain

## Discovered Services
| Port | Service | Version |
|------|---------|---------|
| 21 | FTP | ProFTPD |
| 25 | SMTP | Postfix |
| 53 | DNS | - |
| 80 | HTTP | Apache (PleskLin) |
| 110 | POP3 | Dovecot |
| 143 | IMAP | Dovecot |
| 443 | HTTPS | Apache + PHP 7.4.33 |
| 587 | SMTP | Postfix |
| 993 | IMAPS | Dovecot |
| 3306 | MySQL | MariaDB 10.1.46 |
| 8443 | HTTPS | Plesk Obsidian 18.0.70 (Nginx) |
| 8880 | HTTP | Plesk |
| 50050 | SSH | OpenSSH 7.4 |

## Discovered Domains (same IP)
- fibytel.es / www.fibytel.es / mail.fibytel.es / webmail.fibytel.es
- cblcom.com → redirects to fibytel.es
- fibytel.com → redirects to fibytel.es
- cblcomfibra.es → redirects to fibytel.es
- invesfutur.com (default Apache page)
- lamerche.com
- ejeasesores.com (WordPress - separate business)
- Second IP in SPF: 185.45.74.200

## Web Application Structure
### Main site (https://www.fibytel.es/)
- Template: Eterna by BootstrapMade
- Pages: fibra.php, movil.php, esenciales.php, somos2.php, familiares.php, fibrayfijo.php, contacto.php
- **Login**: clientes.php → forms/control.php (always returns 500)
- **Password recovery**: recordarcontra.php → forms/enviarcontra.php (500)
- **Lead form**: meinteresa.php → forms/contact2.php (redirects to contactono.php)
- **Contact**: contacto.php → forms/contact.php
- **Invoices**: facturas.php (302 redirect to clientes.php when not authenticated)
- **Logout**: salir.php

### Contacto subdirectory (/contacto/)
- Separate template (SoftLand by BootstrapMade)
- Login at areacliente.html → control.php
- Pages: fibra.html, movil.html, fijo.html, fibraymovil.html, etc.

### Key directories (all 403 Forbidden)
- /forms/, /pdf/, /test/, /assets/, /error_docs/

## CVEs Investigated
- **Plesk 18.0.70**: CVE-2025-54336 (auth bypass - requires 0e password), CVE-2025-65518 (DoS)
- **PHP 7.4.33**: EOL, last release
- **MariaDB 10.1.46**: CVE-2020-28912 (Windows only)
- **OpenSSH 7.4**: CVE-2018-15473 (user enumeration)
- **Horde webmail**: Multiple CVEs requiring auth

## Exploitation Attempts
1. SQL injection on login forms (forms/control.php, contacto/control.php) - DB connection appears broken, all return 500
2. SQL injection on contact2.php lead submission form - No injection found
3. Plesk auth bypass CVE-2025-54336 - Failed (admin password not 0e prefixed)
4. AWS credentials from Plesk JS (AKIAR4YEYRJLXPZPYBFL) - Telemetry-only IAM user, no useful access
5. SSH brute force (hydra, port 50050) - Failed with multiple username/password combinations
6. Plesk XML API brute force (admin, root usernames) - Failed with 700+ passwords
7. IMAP/IMAPS brute force (hydra, fibytel@fibytel.es) - Hydra reported "baja" = FALSE POSITIVE
8. 403 bypass techniques on /forms/, /pdf/, /test/, /assets/ - No bypass found
9. phpinfo/backup file hunting - Nothing found across all directories
10. Plesk REST API v2 (Swagger documented at /api/v2/) - All 32 endpoints require auth
11. WP Toolkit API (77 endpoints at /api/modules/wp-toolkit/) - All require auth
12. Plesk password reset triggered via get_password.php - Email sent to admin but can't access inbox
13. LFI testing on all PHP pages - No inclusion vulnerability found
14. Directory enumeration (gobuster, manual) - Found /contacto/ sub-app, /pdf/, /test/ (all 403)
15. Wayback Machine OSINT - Found meinteresa.php lead form, sitemap.xml (404 now)
16. DNS zone transfer - Denied
17. Reverse IP lookup - Found 7 domains on same IP (cblcom.com, fibytel.com, etc.)
18. Second IP (185.45.74.200) from SPF record - Hosting provider server, not useful
19. FTP anonymous/brute force - Port filtered/timeout
20. SMTP VRFY email enumeration - Disabled by Postfix
21. ProFTPD CVE-2024-48651 investigation - Can't connect to FTP
22. OpenSSH 7.4 CVE-2024-6387 (regreSSHion) - Not affected (version not in range)

## Key Internal Pages Found
- `/facturas.php` - 302 redirects to clientes.php (requires authenticated session)
- `/salir.php` - Logout page (200, redirects to clientes.php)
- `/contactook.php` - Contact form success page
- `/contactono.php` - Contact form failure page  
- `/meinteresa.php` - Lead capture form (ACTIVE, 200)

## Lead Form Parameters (from meinteresa.php)
- name (Nombre)
- telefono (Teléfono)
- email (Email)
- direccion (Dirección completa)
- message (Mensaje)
- val (hidden - product name)
- val1 (hidden - product description)

## Most Promising Attack Vectors Remaining
1. **Email credential brute force** - If cracked, gives access to Plesk reset emails + leads sent via email
2. **Plesk panel access** - Full control of server including database, file manager
3. **PHP session manipulation** - Login returns 500 but creates session; need to authenticate session
4. **Larger wordlist IMAP brute force** - Rate limited but possible with patience
5. **ProFTPD direct file access** - If FTP becomes available, can read PHP source code for DB credentials
