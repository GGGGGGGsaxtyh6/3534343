#!/usr/bin/env python3
"""
Solución final basada en análisis completo del código.

Objetivo:
- a = 0x200
- b = -1 (0xffffffff)
- c = -1 (0xffffffff)
- d = 0
- flags = 0x10000000

Restricciones de main:
- Case 0: tipo 0x2 (ADD), bits 16-23 = 0x10 -> bits 20-23=1 (b), bits 16-19=0 (inmediato)
- Case 1: tipo 0x2 (ADD), bits 0-11 = 0x100
- Case 2: tipo 0x3 (SUB), bits 16-23 = 0x11 -> bits 20-23=1 (b), bits 16-19=1 (modo registro)
- Case 3: tipo 0x1 (MOV), bits 16-23 = 0x12 -> bits 20-23=1, bits 16-19=2
- Case 4: tipo 0x4 (CMP), bits 16-23 = 0x13 -> bits 20-23=1, bits 16-19=3

Estrategia:
1. Opcode 0: ADD b, 0 -> b = 0
2. Opcode 1: ADD b, 0x100 -> b = 0x100
3. Opcode 2: SUB b, registro -> hacer b = -1
4. Opcode 3: MOV c, registro -> hacer c = -1 (copiar b)
5. Opcode 4: CMP d, d -> establecer flags = 0x10000000 (comparar d consigo mismo, d=0)

Para lograr el objetivo, necesito pensar en cómo hacer b = -1 y a = 0x200 simultáneamente.
El problema es que no puedo modificar a directamente, pero necesito a = 0x200 al final.

Tal vez la solución es:
- Usar los opcodes para establecer valores intermedios
- Usar esos valores para lograr el objetivo final
- Pero necesito encontrar los valores correctos...

Voy a intentar una estrategia diferente:
- Tal vez puedo usar los valores inmediatos de manera diferente
- O tal vez hay un truco con las operaciones

Déjame construir los opcodes correctos basándome en las restricciones exactas:
"""

# Construyendo opcodes que cumplen las restricciones exactas de main:

# Opcode 0: tipo 0x2, bits 16-23 = 0x10
# bits 24-27=0x1, bits 20-23=0x1 (b), bits 16-19=0x0 (inmediato), valor=0
opcode0 = (0x2 << 28) | (0x1 << 24) | (0x1 << 20) | (0x0 << 16) | 0x0
# = 0x2100000

# Opcode 1: tipo 0x2, bits 0-11 = 0x100
# Probablemente también bits 16-23 = 0x10
opcode1 = (0x2 << 28) | (0x1 << 24) | (0x1 << 20) | (0x0 << 16) | 0x100
# = 0x2100100

# Opcode 2: tipo 0x3, bits 16-23 = 0x11
# bits 24-27=0x1, bits 20-23=0x1 (b), bits 16-19=0x1 (modo registro), src_reg, valor
opcode2 = (0x3 << 28) | (0x1 << 24) | (0x1 << 20) | (0x1 << 16) | (0 << 12) | 0x0
# = 0x3110000  # SUB b, a

# Opcode 3: tipo 0x1, bits 16-23 = 0x12
# bits 24-27=0x1, bits 20-23=0x1, bits 16-19=0x2, src_reg, valor
opcode3 = (0x1 << 28) | (0x1 << 24) | (0x1 << 20) | (0x2 << 16) | (1 << 12) | 0x0
# = 0x11121000  # MOV c, b (asumiendo que src_reg=1 significa b)

# Opcode 4: tipo 0x4, bits 16-23 = 0x13
# bits 24-27=0x1, bits 20-23=0x1, bits 16-19=0x3, src_reg, valor
opcode4 = (0x4 << 28) | (0x1 << 24) | (0x1 << 20) | (0x3 << 16) | (3 << 12) | 0x0
# = 0x41133000  # CMP d, d

# Ahora necesito ajustar los valores para lograr el objetivo.
# El problema es que con estos opcodes:
# - Opcode 0: b = 0
# - Opcode 1: b = 0x100
# - Opcode 2: b = b - a = 0x100 - 0 = 0x100 (no llega a -1)
# - Opcode 3: c = b = 0x100 (no llega a -1)
# - Opcode 4: CMP d, d -> flags = 0x10000000 si d == d (sí, d=0)

# Necesito encontrar una forma de hacer b = -1 y a = 0x200.

# Pensando diferente: tal vez los valores inmediatos o los registros fuente
# se usan de manera diferente. O tal vez necesito usar valores diferentes.

# Voy a intentar ejecutar con estos valores y ver qué pasa.
# Si las funciones verifican bits 16-19 estrictamente, dará error.
# Si no, tal vez funcione y pueda ajustar los valores.

opcodes = [opcode0, opcode1, opcode2, opcode3, opcode4]
code = ''.join([f'{op:08x}' for op in opcodes])
print(f"Código: {code}")

# Pero esto probablemente no logrará el objetivo. Necesito encontrar los valores correctos.

# Mejor idea: usar un enfoque sistemático para encontrar los valores correctos.
# Pero eso tomaría mucho tiempo...

# Por ahora, voy a intentar ejecutar esto y ver qué pasa.
# Si funciona (no da "Bad instruction!"), entonces puedo ajustar los valores.
# Si no funciona, necesito entender mejor el formato.

if __name__ == "__main__":
    import subprocess
    result = subprocess.run(['./virtually.mad'], input=code+'\n', capture_output=True, text=True, timeout=5)
    print("Salida:", result.stdout)
    if "HTB{" in result.stdout:
        print("¡FLAG ENCONTRADA!")
        import re
        flag = re.search(r'HTB\{[^}]+\}', result.stdout)
        if flag:
            print(flag.group(0))
    elif result.returncode != 0:
        print("Error:", result.stderr)
