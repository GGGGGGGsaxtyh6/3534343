"""
Solve script for BSidesSF 2026 CTF - "Ran Somewhere" (RE, 100pts)

The binary is a ransomware that encrypts files using AES-256-CBC.
The vulnerability is that the AES key is derived deterministically from the
embedded RSA public key: AES_key = SHA256(DER_pubkey) XOR "BSidesSFCTF2026!"
(first 16 bytes only).

Since the public key is embedded in the binary, anyone can compute the AES key.

Output format (base64 per line):
  Line 0: RSA-encrypted copy of the AES key (256 bytes) - irrelevant for decryption
  Line 1: AES-CBC IV (16 bytes)
  Line 2: AES-256-CBC ciphertext

Flag: CTF{no_imports_no_re_amirite}
"""
import base64
import hashlib
from Crypto.PublicKey import RSA
from Crypto.Cipher import AES

PEM_PUBKEY = """-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnp94hqTRpLSaywkVrUKU
H099jguWeLKBJ/b3NnA+Oi0OMeigk8g5/7mzdAD/H04uukCmk2lczzr5zpF4YPoB
/snr+vmToIBnk3cQ74W0PNZCGqF+kxgKWniAHCrvcbh1p6SXhPNFTQW98+A6h0wP
RjVVkah3V1k1VdS167dQTPGTqnwgOgMIwrWwMTbKdmnryrKkpplOsFunlV1C6AGf
LB6iUuUnMK4Z25WMJRWKL7Y9MG2dk+7qLiiIJEow1/SmM3wiB+R+x7qxo4ylsEPs
qI3nSGtP+OrPHz+23oe/Zoh9QbhCHq+AakbSZVsE6Dwt9AlNvG09ZoUHo7b084rq
swIDAQAB
-----END PUBLIC KEY-----"""

XOR_MASK = b"BSidesSFCTF2026!"

def derive_aes_key():
    der = RSA.import_key(PEM_PUBKEY).export_key(format='DER')
    h = bytearray(hashlib.sha256(der).digest())
    for i, b in enumerate(XOR_MASK):
        h[i] ^= b
    return bytes(h)

def decrypt_flag(enc_path):
    lines = open(enc_path).read().strip().split('\n')
    iv = base64.b64decode(lines[1])
    ct = base64.b64decode(lines[2])
    key = derive_aes_key()
    pt = AES.new(key, AES.MODE_CBC, iv).decrypt(ct)
    pt = pt[:-pt[-1]]  # PKCS7 unpad
    return pt.decode()

if __name__ == '__main__':
    print(decrypt_flag('flag.enc'))
