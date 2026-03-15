# Reconocimiento del Challenge - itegal.es

## Infraestructura descubierta

### Web Properties
| Subdominio | IP | Puerto | Tecnología | Notas |
|---|---|---|---|---|
| clientes.itegal.es | 45.141.124.127 | 443 | ISPGestión (Yii 1.x, PHP, Apache 2.4.65) | Login protegido, API en /api/leads (401) |
| itegal.ispgestion.com | 45.141.124.127 | 443 | ISPGestión (mismo servidor) | Tenant de ISPGestión SaaS |
| alta.itegal.es | 151.80.237.40 | 443 | Apache estático | Formulario de cobertura |
| cobertura.itegal.es | 151.80.237.40 | 443 | Apache estático | Mismo contenido que alta |
| ubi.itegal.es | 151.80.237.40 | 443 | Apache, PHP | Geolocalización + teléfono |
| itegal.es | 82.98.171.199 | 443 | WordPress (Yoast SEO) | Web corporativa |

### Endpoints encontrados en clientes.itegal.es
- `/api` -> 200 "Error: 6"
- `/app` -> 200 "Error: 7"
- `/api/leads` -> 401
- `/api/v1/leads` -> 401
- `/api/particulares` -> 401
- `/gii` -> 403 (Yii code generator)
- `/server-status` -> 403
- `/index.html` -> 200 "holaaa"
- `/isp_assets/` -> Directory listing habilitado
- `/site/recordar_passwd` -> Password reset (enumera usuarios)
- `/documentos/open?doc=PUBLICA/...` -> Acceso a documentos públicos

### Subdominios de itegal.es (crt.sh)
alta, castrocaldelaswifi4eu, clientes, cobertura, cpanel, cpcalendars, cpcontacts, fora, fora2, formacion, mail, sat, tarifas, ubi, vpn, webdisk, webmail

### Versiones detectadas
- Apache 2.4.65 (Debian)
- Yii Framework 1.x
- PHP con PHPSESSID
- Syncfusion EJ2 (con licencia)
- ISPGestión (versión no determinada)

### Subdominios adicionales (gobuster DNS)
- speedtest.itegal.es -> 147.78.24.250 (no responde)
- cliente.itegal.es -> 45.141.124.127 (redirige a clientesitegal.ispgestion.com - misma instancia)
- acs1.itegal.es -> 147.78.24.231 (ACS/CWMP server, no responde HTTP)
- acs2.itegal.es -> 147.78.24.232 (ACS/CWMP server, no responde HTTP)

### ISPGestión SaaS Tenants (crt.sh)
- Patrón: app.clientes{empresa}.ispgestion.com
- clientesitegal.ispgestion.com -> 45.141.124.127 (misma instancia que clientes.itegal.es)
- itegal.ispgestion.com -> 45.141.124.127 (misma instancia)
- app.clientesdemo.ispgestion.com -> 37.153.91.155
- app.clientesdesarrollo.ispgestion.com -> 37.153.91.155

### CVEs investigadas
- Apache 2.4.65: CVE-2025-58098, CVE-2025-66200, CVE-2025-65082
- Yii 1.x: CVE-2023-47130 (deserialization RCE), CVE-2022-41922
- Yii 2: CVE-2024-58136 (Improper Protection of Alternate Path - CRITICAL 9.8)
- ISPGestión: No CVEs conocidas

### Intentos realizados sin éxito
- SQL injection en login (/site/login) - sqlmap level 3 risk 2
- SQL injection en password reset (/site/recordar_passwd)
- Path traversal en /documentos/open
- Header bypass (X-Forwarded-For, X-Original-URL, etc.)
- Brute force de 500+ usernames via password reset
- Fuzz de 200+ parámetros en /api
- Basic/Bearer auth con credenciales comunes
- Default credentials (admin/admin, etc.)

### Notas clave
- /api devuelve "Error: 6" (200 OK sin auth) - significado del error desconocido
- /app devuelve "Error: 7" (200 OK sin auth) - endpoint de app móvil
- /app/login devuelve 401 (endpoint de login de la app)
- CORS completamente abierto (Access-Control-Allow-Origin: *)
- API acepta header Authorization
- Recuperación de contraseña confirma si usuario existe o no
