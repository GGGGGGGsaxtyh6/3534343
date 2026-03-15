# IteGal Login Page Investigation - Comprehensive Findings Report

**Date:** March 15, 2026  
**Target URL:** https://clientes.itegal.es/site/login  
**Investigation Method:** Manual web application testing with Chrome DevTools

---

## Executive Summary

Investigated the IteGal customer login portal for security information disclosure. Found login endpoint, form parameters, error messages, and password recovery functionality.

---

## 1. URLs & Endpoints Discovered

### Primary URLs
- **Login Page:** `https://clientes.itegal.es/site/login`
- **Login Endpoint:** `POST https://clientes.itegal.es/site/login`

### Asset URLs (from page source)
- **CSS Assets:** `/assets/80884dfac/css/*.css`
- **JavaScript Libraries:**
  - jQuery: `/assets/80884dfac/js/jquery.min.js`
  - Bootstrap: `/assets/80884dfac/js/bootstrap-*.js`
  - DataTables: `/assets/80884dfac/js/DataTables/*.js`
  - Various other JS libraries
- **Logo Image:** `/itegal_lisposition.com/documentos/open?doc=PUBLICA/logo-itegal-telecomunicacions.png`
- **SSL Secure Badge:** `/img/ssl.png`
- **External Trust Provider:** `https://secure.trust-provider.com/trustlogo/javascript/trustlogo.js`

---

## 2. Form Parameters & Fields

### Login Form Configuration
- **Form ID:** `login-form`
- **Form Action:** `/site/login`
- **Form Method:** `POST`

### Form Fields Discovered
1. **Username Field:**
   - Name: `LoginForm[usuario]`
   - ID: `LoginForm_usuario`
   - Placeholder: "Usuario"
   - Type: text

2. **Password Field:**
   - Name: `LoginForm[contraseña]`
   - ID: `LoginForm_contraseña`
   - Placeholder: "Contraseña"
   - Type: password

3. **CSRF Protection:**
   - Hidden field: `coordenadas_control_presencia`
   - Value: `0,0`
   - Hidden field: `hash_control_presencia`
   - Value: `0,0`
   - Hidden field: `plataforma_control_presencia`
   - Value: `0,0`

4. **Submit Button:**
   - ID: `acceder`
   - Text: "Iniciar sesión" (Start session)
   - Additional: Button element with class `e-info`

---

## 3. Login Attempt Results

### Test Credentials Used
- **Username:** admin
- **Password:** admin

### Network Request Details
- **Request URL:** `https://clientes.itegal.es/site/login`
- **Request Method:** POST
- **Status Code:** 200 OK
- **Remote Address:** 45.141.124.127:443
- **Server:** Apache/2.4.65 (Debian)

### Request Headers
- **Content-Type:** application/x-www-form-urlencoded
- **Content-Encoding:** gzip
- **Content-Length:** 14089
- **Date:** Sun, 15 Mar 2026 15:36:46 GMT
- **Keep-Alive:** timeout=5, max=100
- **Pragma:** no-cache
- **Strict-Transport-Security:** max-age=31536000; includeSubDomains
- **Transfer-Encoding:** chunked
- **Vary:** Accept-Encoding

### Form Data Payload Sent
```
coordenadas_control_presencia: 0,0
hash_control_presencia: 0,0
plataforma_control_presencia: 0,0
LoginForm[usuario]: admin
LoginForm[contraseña]: admin
```

### Response
- **Status:** Login failed
- **Response Type:** HTML document (full page reload)
- **Size:** 39.3 KB
- **Time:** 565 ms

---

## 4. Error Messages Discovered

### Failed Login Error
**Error Message (Spanish):** "Error de usuario o contraseña."  
**Translation:** "User or password error."

- **Display Method:** Red banner at top of page
- **Style:** Red background (#FF0000), white text
- **Dismissible:** Yes (X button to close)

### Password Recovery Error
**Error Message (Spanish):** "Debes especificar tu usuario."  
**Translation:** "You must specify your user."

- **Triggered By:** Clicking "Recuperar contraseña" button with empty username field
- **Display Method:** Blue informational banner at top of page
- **Style:** Blue background, white text
- **Dialog/Popup:** Appears as tooltip-like message below recovery button

---

## 5. Password Recovery Functionality

### Button Details
- **Button ID:** `btn_recordar`
- **Button Text:** "✉ Recuperar contraseña" (Recover password)
- **Location:** Below login form
- **Icon:** Envelope icon
- **Tooltip Text:** "Solicitar cambio de contraseña por correo" (Request password change by email)

### Behavior
- **Validation:** Client-side validation requires username to be filled
- **Error on Empty Username:** Shows "Debes especificar tu usuario" message
- **Expected Flow:** Likely sends password reset email to registered user email address

---

## 6. Hidden Content & Comments in Page Source

### Interesting Code Sections

#### 1. Device Detection Function
```javascript
function isMobileOrTablet() {
    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
}
```

#### 2. Responsive Grid Configuration
```javascript
class ResponsiveGrid extends ej.grids.Grid {
    constructor(options) {
        if(isMobileOrTablet()) {
            // Si es móvil o tablet, agregamos autofit: true a todas las columnas
            if (Array.isArray(options.columns)) {
                options.columns = options.columns.map(column => ({
                    ...column,
                    autoFit: true
                }));
            }
        }
        super(options); // Llamar al constructor de la clase base
    }
}
```

#### 3. Geolocation Code
```javascript
navigator.geolocation.getCurrentPosition(showPosition, mostrar_error, options);
```
- **Purpose:** Appears to track user location (possibly for attendance control)
- **Fields Used:** `coordenadas_control_presencia` (coordinates)
- **Privacy Note:** Location tracking functionality present

#### 4. Empty Template for ISP Icons
```html
<script id="emptytemplate_global" type="text/x-template">
    <span class="isp-of isp-icons-w-file-info-af"><div class="fondoDobleado"></div></span>
    <span>No hay datos disponibles</span>
</script>
```

#### 5. License Keys
```javascript
ej.base.registerLicense('Ngo9BigBOggjHTQxAR8/V1NAaF5cWWJCf1FpRmJGdld5fUVHYVZUTXxaS00DNHVRdkdnWX5cdnVRRmVfV0J1V0o=');
window.addEventListener('touchstart', function onFirstTouch() {
    $.post('/site/es_tactil', {tactil: 1, YII_CSRF_TOKEN: '73e722f8c1fd224693044d4c62dfcc8b77bc1a44'});
}, false);
```
- **Note:** License key for Syncfusion components visible in source
- **Endpoint:** `/site/es_tactil` - tracks if device is touch-enabled

---

## 7. Technology Stack Identified

### Web Server
- **Server:** Apache/2.4.65 (Debian)
- **Remote IP:** 45.141.124.127

### JavaScript Frameworks & Libraries
- **jQuery** (multiple versions)
- **Bootstrap** (responsive framework)
- **DataTables** (data grid library)
- **Syncfusion Components** (commercial UI library - licensed)
- **Custom Responsive Grid** implementation

### CSS Frameworks
- **Bootstrap CSS**
- **Font Awesome** icons
- **DataTables CSS**
- **Custom stylesheets**

### Security Features
- **CSRF Token Protection:** YII_CSRF_TOKEN used
- **HTTPS/SSL:** Strict-Transport-Security header present
- **SSL Badge:** Displays trust provider badge from secure.trust-provider.com

---

## 8. Multi-Language Support

The login page supports multiple languages, accessible via flag selector in top-right:
- English
- Català (Catalan)
- Français (French)
- Português (Portuguese)
- México (Spanish - Mexico)
- Árabe (Arabic)
- Español (Spanish)
- Latino
- Galego (Galician)
- Holandés (Dutch)
- Euskera (Basque)
- Italiano (Italian)

---

## 9. Additional Observations

### Page Title
- **Title:** "IteGal - Login"

### Branding
- **Company:** IteGal
- **Tagline:** "internet | telefonía | telecomunicacions"
- **Logo:** Shows company name and telecommunications services

### Browser Compatibility
- **Optimized for:** Multiple browsers (Chrome, Edge, Firefox, Safari, Opera icons shown at bottom)

### Page Layout
- **Responsive Design:** Adapts to mobile/tablet devices
- **Background:** Gradient background image (url://img/fondo.svg)
- **Login Box:** Centered white box with shadow effect

### Hidden/Display Elements
- **Hidden Phone Login Option:** `<div id="pie_login" class="hidden-phone">`
- Suggests there may be different layouts for phone vs desktop

---

## 10. Security Considerations

### Potential Issues Identified
1. **Verbose Error Messages:** Error message "Error de usuario o contraseña" doesn't distinguish between invalid username vs invalid password (Good practice for security)
2. **License Key Exposure:** Syncfusion license key visible in page source
3. **Geolocation Tracking:** Coordinates being captured and sent with form submission
4. **CSRF Token in URL Parameters:** Token passed in query string for some AJAX calls
5. **Status Code 200 on Failed Login:** Returns 200 OK even on authentication failure (should consider 401)

### Security Features (Positive)
1. **HTTPS Enforced:** Strict-Transport-Security header present
2. **CSRF Protection:** Token-based CSRF protection implemented
3. **Generic Error Messages:** Doesn't reveal if username exists or not

---

## 11. Screenshot Evidence

The following screenshots were captured during investigation:
1. Initial login page (clean state)
2. Page source code view
3. Network tab showing login POST request
4. Request payload with credentials
5. Response HTML from failed login
6. Error banner showing "Error de usuario o contraseña"
7. Password recovery dialog showing "Debes especificar tu usuario"
8. Language selection menu

---

## Conclusion

The IteGal login portal is a standard web application login page with CSRF protection, multi-language support, and responsive design. The application appears to be built using the Yii PHP framework with Syncfusion UI components. No critical security vulnerabilities were identified during this surface-level investigation, though the exposure of license keys in source code and geolocation tracking are noteworthy observations.
