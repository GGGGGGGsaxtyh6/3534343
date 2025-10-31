import random
random.seed(493)
vals = [random.randint(32,125) for _ in range(300)]
text = "".join(chr(v) for v in vals)
print(text)
