FROM python:3.12-slim

RUN useradd -m -s /bin/bash ctfuser

WORKDIR /opt/nginx-ui

COPY app/requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY app/ .

RUN mkdir -p /data && chown ctfuser:ctfuser /data

ENV PYTHONUNBUFFERED=1
ENV FLAG="flag{CVE-2026-27944_unauth_backup_l3ak_n1nxUI_cr1t1c4l}"
ENV BACKUP_KEY="4e6f7420736f2073656372657421210a"
ENV SECRET_KEY="a1b2c3d4e5f6a7b8c9d0e1f2a3b4c5d6e7f8a9b0c1d2e3f4a5b6c7d8e9f0a1b2"

EXPOSE 9000

USER ctfuser

CMD ["python", "app.py"]
