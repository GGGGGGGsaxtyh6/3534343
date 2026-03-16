# Reconocimiento LiberaTelecom - HTB Challenge

## Infraestructura Descubierta

### Servidores
| IP | Servicios | Función |
|---|---|---|
| 213.158.84.58 | 80/443 (Nginx + WordPress) | Web principal liberatelecom.es |
| 213.165.93.213 | 80/443 (Apache 2.4.37 + Tomcat 10.1.24) | Portal clientes CRM Gossan |
| 88.20.254.32 | 80/443/8006/10000/20000 | Nextcloud, Proxmox, Webmin, Usermin, ONLYOFFICE |

### Subdominios
- `liberatelecom.es` → 213.158.84.58 (WordPress principal)
- `clientes.liberatelecom.es` → 213.165.93.213 (Portal CRM Gossan)
- `nc.liberatelecom.es` → 88.20.254.32 (Nextcloud 32.0.5)
- `office.liberatelecom.es` → 88.20.254.32 (ONLYOFFICE Docs)
- `t40.liberatelecom.es` → 88.20.254.32 (Proxmox VE)
- `vmin.liberatelecom.es` → 88.20.254.32 (Webmin)
- `maps.liberatelecom.es` → 88.20.254.32 (Welcome page)
- `ip-security.es` / `www.ip-security.es` → redirige a liberatelecom.es/ip-security/

### Usuarios WordPress
| ID | Username | Email | Gravatar Hash |
|---|---|---|---|
| 3 | liberaadmin | info@liberatelecom.es | 1fe1eeb4a451adaba4c4068583a8c3d2 |
| 5 | m3admin | desconocido | 14b64f25a1cd25d73637f502173fefd5 |
| 6 | socialmedia (Cristina) | desconocido | - |

## Software y Versiones

### WordPress Principal
- **WordPress**: versión actual (Yoast SEO 27.1.1)
- **Plugins**: Elementor 3.35.6, Elementor Pro 3.35.1, Essential Addons 6.5.13, WPBakery, Smart Slider 3, WP Rocket 3.15.9, GDPR Cookie Compliance, Creame WhatsApp Me
- **Plugins CF7**: Contact Form 7, **CFDB7 1.3.5**, **Flamingo 2.6.1**, **Contact Form Entries 1.4.8**
- **Tema**: consto / consto-raanet

### WordPress /test/
- WordPress antiguo (jQuery 3.5.1)
- Yoast SEO 16.5
- WPBakery, Autoptimize, mc4wp, Redirection

### Portal Clientes (Gossan CRM)
- **Apache**: 2.4.37 (AlmaLinux) OpenSSL/1.1.1k
- **Tomcat**: 10.1.24
- **Backend**: es.gossan.gosbilling.gosresources.ClientesRESTServlet
- **Filtro Auth**: es.gossan.gosbilling.gosresources.ClientesRESTFilter
- **DB Connection**: jdbc.1

### Nextcloud
- **Versión**: 32.0.5

## API Endpoints Gossan CRM

Todos requieren `token_id` header excepto `/services/recuperarPasswd/`:
- `POST /services/login/{schema}` - Login (user, passwd, lang)
- `POST /services/recuperarPasswd/{schema}` - **SIN AUTH** (DNI, MAIL, CCC, _LANG, IMPORTE)
- `GET /services/clientes` - Datos del cliente
- `GET /services/facturas` - Facturas
- `GET /services/telefonos` - Teléfonos
- `GET /services/periodos/{telef}` - Periodos
- `GET /services/llamadas/{telef}/periodo/{per}` - Llamadas
- `GET /services/descarga-factura/{id}/{tokenId}/{filename}.PDF` - Descarga factura
- `GET /services/pago_factura/{id}` - Pago factura
- `POST /services/cambiarPasswd` - Cambiar contraseña
- `POST /services/solicitar_suspension` - Solicitar suspensión
- `GET /services/tarjeta/vincular` - Vincular tarjeta
- `DELETE /services/tarjeta/desvincular` - Desvincular tarjeta

## Formularios CF7 (Leads)

| ID | Nombre | Campos |
|---|---|---|
| 6 | Página de Contacto | nombre, correo, area-texto |
| 696 | Te Llamamos Nosotros | nombre, telefono |
| 1222 | Newsletter | nombre, correo |
| 1424 | Trabaja con Nosotros | nombre, apellido, correo, telefono, direccion, provincia, curriculum |
| 2610 | Landing Promociones | nombre, apellidos, telefono, correo, codigo-postal |
| 3437 | Form Blogs | nombre, telefono |

## CVEs Potencialmente Explotables

### CVE-2026-0825 - Contact Form Entries < 1.4.6
- **Tipo**: Missing Authorization en CSV export
- **Impacto**: Exfiltración de datos de formularios sin autenticación
- **Estado**: Versión instalada 1.4.8 (parcheada) pero podría ser vulnerable
- **Mecanismo**: Export key en código fuente + `admin-post.php?vx_crm_form_action=download_csv&vx_crm_key=KEY`
- **admin-post.php**: ACCESIBLE (no bloqueado por 423)

### Apache 2.4.37 CVEs
- CVE-2024-38474 (mod_rewrite bypass, CVSS 9.8)
- CVE-2025-58098 (SSI command injection)
- CVE-2023-38709 (HTTP response splitting)

### Tomcat 10.1.24 CVEs
- CVE-2024-38286 (DoS via TLS)
- 9 CVEs conocidas

## Hallazgos Clave

1. **admin-post.php accesible** - No bloqueado por el WAF 423
2. **wp-admin bloqueado** con 423 Locked
3. **wp-login.php** devuelve 502 Bad Gateway
4. **xmlrpc.php** abierto con system.multicall
5. **Recuperación de contraseña Gossan** funciona sin token
6. **Directory listing** en clientes.liberatelecom.es (/assets/, /WEB-INF/, /public_html/)
7. **WP Rocket cache accesible** en wp-content/cache/
8. **wp-config.php.bak** existe (403) en ambos sitios WP
9. **cfdb7_uploads** y **wpcf7_uploads** existen (403)
10. **Stack trace Java** expuesto al enviar _LANG como integer

## Vectores de Ataque Probados

### Intentados (sin éxito)
- Brute force xmlrpc WP (rate limited / connection drops)
- SQL injection en login Gossan (prepared statements)
- SQL injection en recuperación de contraseña (todos los campos)
- Bypass 403 en wp-config.php.bak (path manipulation, headers, encoding)
- Registro de usuario WP via REST API
- Acceso CF7 entries sin auth (REST API, admin-ajax.php)
- Brute force Nextcloud (multiple users/passwords)
- Virtual host fuzzing (ffuf en las 3 IPs)
- Directory fuzzing (dirsearch/ffuf en ambos sitios)
- IDOR en facturas/clientes Gossan
- Type juggling en export CSV

### Vectores Prometedores Pendientes
1. **CVE-2026-0825** (Contact Form Entries export CSV): `admin-post.php` es ACCESIBLE y ejecuta el código del plugin. Falta encontrar la export key que debería estar en alguna página con shortcode `[vx-entries export=true]`
2. **Brute force WP** con lotes más pequeños (1-5 passwords por request) para evitar rate limiting
3. **wp-config.php.bak** existe (403) - necesita bypass avanzado de Apache
4. **Stack trace Java** expuesto - puede revelar más endpoints del servlet
5. **Nextcloud shared links** - buscar tokens de compartición en el contenido del sitio
6. **Apache mod_rewrite CVE-2024-38474** - bypass de restricciones en Apache 2.4.37

## Información Técnica Detallada

### Java Stack Trace (al enviar _LANG como integer)
```
es.gossan.gosbilling.gosresources.ClientesRESTServlet.recuperarParametrosRequest(ClientesRESTServlet.java:206)
es.gossan.gosbilling.gosresources.ClientesRESTServlet.doPost(ClientesRESTServlet.java:131)
jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
es.gossan.gosbilling.gosresources.ClientesRESTFilter.doFilter(ClientesRESTFilter.java:26)
```

### Comportamiento del WAF (423 Locked)
- Bloquea: POST a admin-ajax.php con ciertos params, arrays en URL, params vacíos
- Permite: GET a admin-post.php con params simples (key=1, key=test, key=true)
- No bloquea: xmlrpc.php, REST API, /services/ endpoint

### Emails Confirmados
- liberaadmin → info@liberatelecom.es (gravatar MD5 match)
- contacto empresa: hola@liberatelecom.es, info@liberatelecom.es
- teléfono: 868289754, WhatsApp: 698969091
