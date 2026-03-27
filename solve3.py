#!/usr/bin/env python3
from pwn import *
import re

HOST = "154.57.164.76"
PORT = 30231

def solve():
    r = remote(HOST, PORT)

    r.recvuntil(b"> ")
    r.sendline(b"1")

    r.recvuntil(b"Go!\n")
    
    for rnd in range(100):
        players = {}

        data = r.recvuntil(b"> ").decode()
        
        for m in re.finditer(r'Player (\d+): ([\d ]+)', data):
            player_num = int(m.group(1))
            dice_values = list(map(int, m.group(2).strip().split()))
            total = sum(dice_values)
            players[player_num] = total

        max_score = max(players.values())
        winners = [p for p, s in players.items() if s == max_score]
        winner = winners[-1]
        
        r.sendline(str(winner).encode())

        confirm = r.recvline().decode().strip()
        
        if "correct" not in confirm.lower():
            log.error(f"Round {rnd+1} failed: {confirm}")
            rest = r.recvall(timeout=3).decode()
            print(rest)
            r.close()
            return
        
        log.info(f"Round {rnd+1}: Player {winner} wins (score {max_score}) - {confirm}")

    result = r.recvall(timeout=5).decode()
    print(result)
    r.close()

if __name__ == "__main__":
    solve()
