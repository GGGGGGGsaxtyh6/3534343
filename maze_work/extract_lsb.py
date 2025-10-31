from PIL import Image
import numpy as np
img = Image.open("rev_maze/maze.png")
arr = np.array(img)
channels = {0:"R",1:"G",2:"B",3:"A"}
for ch,name in channels.items():
    channel_data = arr[:,:,ch]
    for bit in range(8):
        bits = (channel_data >> bit) & 1
        flat = bits.flatten()
        n = len(flat)//8
        flat = flat[:n*8].reshape(-1,8)
        values = flat.dot(1 << np.arange(8))
        text = "".join(chr(v) for v in values)
        printable = "".join(c if 32 <= ord(c) < 127 else "." for c in text[:200])
        print(f"Channel {name} bit {bit}: {printable}")
