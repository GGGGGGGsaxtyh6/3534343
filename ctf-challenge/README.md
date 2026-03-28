# Nginx UI — Phantom Backup

> **Categoría:** Web Exploitation  
> **Dificultad:** Hard  
> **Puntos:** 500  
> **Autor:** CTF Challenge Generator  

---

## Descripción

Tu equipo de seguridad ha detectado actividad sospechosa en el panel de administración **Nginx UI** de un servidor de producción. Un atacante parece haber obtenido credenciales de administrador sin haber realizado fuerza bruta ni phishing.

El equipo de infraestructura asegura que el panel está protegido con autenticación en todos los endpoints, pero algo no cuadra...

Tu misión es auditar la aplicación **Nginx UI v2.3.2** y encontrar cómo el atacante pudo obtener información sensible sin credenciales.

**Encuentra la flag.**

---

## Despliegue

```bash
# Opción 1: Docker Compose
docker compose up --build -d

# Opción 2: Docker directo
docker build -t nginx-ui-ctf .
docker run -d -p 9000:9000 --name nginx-ui-ctf nginx-ui-ctf
```

La aplicación estará disponible en `http://localhost:9000`

---

## Pistas

<details>
<summary>Pista 1 (fácil)</summary>
¿Todos los endpoints de la API requieren autenticación? Prueba a enumerar rutas.
</details>

<details>
<summary>Pista 2 (media)</summary>
Investiga CVEs recientes relacionados con Nginx UI y backups. ¿Qué versión está corriendo?
</details>

<details>
<summary>Pista 3 (difícil)</summary>
Los headers HTTP de la respuesta pueden contener más información de la que crees. ¿Has mirado TODOS los headers?
</details>

---

## Formato de flag

```
flag{...}
```

---

## Notas para organizadores

- La solución completa está en `solution/solve.py`
- El reto requiere:
  1. Reconocimiento de versión vulnerable
  2. Descubrimiento de endpoint sin autenticación
  3. Análisis de headers HTTP (Base64)
  4. Descifrado AES-256-CBC
  5. Extracción de ZIP y búsqueda de la flag en certificados
- Tiempo estimado: 30–60 minutos para jugadores experimentados
