from PIL import Image
import numpy as np
img = Image.open("rev_maze/maze.png")
arr = np.array(img)
bits = (arr[:,:,0] & 1).flatten()
n = len(bits)//8
bits = bits[:n*8].reshape(-1,8)
values = bits.dot(1 << np.arange(8))
text = "".join(chr(v) for v in values)
print(text[:200])
