#!/usr/bin/env python3
from PIL import Image
from pyzbar.pyzbar import decode

img = Image.open('/workspace/vinyl-drop/decompiled/resources/res/drawable/vinyl_drop_receipt.png')
print(f"Image size: {img.size}, mode: {img.mode}")

results = decode(img)
if results:
    for r in results:
        print(f"Type: {r.type}, Data: {r.data.decode('utf-8', errors='replace')}")
else:
    print("No QR code found with pyzbar, trying zxingcpp...")
    import zxingcpp
    results = zxingcpp.read_barcodes(img)
    if results:
        for r in results:
            print(f"Type: {r.format}, Text: {r.text}")
    else:
        print("No barcode found with either library")
