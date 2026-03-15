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
1. SQL injection on login forms - No injection found (DB appears down)
2. Plesk auth bypass CVE-2025-54336 - Failed (password not 0e prefixed)
3. AWS credentials from Plesk JS - Telemetry only, no useful access
4. SSH brute force - Failed
5. Plesk XML API brute force - Failed
6. IMAP brute force - Hydra reported "baja" as password but appears to be false positive
7. 403 bypass techniques - No bypass found
8. phpinfo/backup file hunting - Nothing found
9. Plesk REST API v2 (Swagger documented) - All endpoints require auth
10. Plesk password reset triggered - Email sent but can't access inbox

## Lead Form Parameters (from meinteresa.php)
- name (Nombre)
- telefono (Teléfono)
- email (Email)
- direccion (Dirección completa)
- message (Mensaje)
- val (hidden - product name)
- val1 (hidden - product description)
