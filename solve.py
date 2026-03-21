#!/usr/bin/env python3
"""
Solver for BSidesSF CTF challenge: three-questions-1
Ask three yes/no questions to identify a musical character.

Strategy:
  Ask Q0 (Human?), Q3 (Supernatural?), Q5 (Real world?) — these three
  questions uniquely identify every character in the pool.

Characters and their full profiles:
  Dorothy Gale          : Human=T, Alive=T, Hero=T, Magic=F, Protag=T, RealWorld=F
  Matilda Wormwood      : Human=T, Alive=T, Hero=T, Magic=T, Protag=T, RealWorld=T
  Miss Trunchbull       : Human=T, Alive=T, Hero=F, Magic=F, Protag=F, RealWorld=T
  Scarecrow             : Human=F, Alive=T, Hero=T, Magic=F, Protag=F, RealWorld=F
  Wicked Witch of West  : Human=T, Alive=F, Hero=F, Magic=T, Protag=F, RealWorld=F

Flag: CTF{gu3ss3sthr33f0rth33}
"""

import subprocess, json, re, sys

BASE = "https://three-questions-1-789ec8f2.challenges.bsidessf.net"

LOOKUP = {
    (True, False, False): "Dorothy Gale",
    (True, True, True): "Matilda Wormwood",
    (True, False, True): "Miss Trunchbull",
    (False, False, False): "Scarecrow",
    (True, True, False): "Wicked Witch of the West",
}


def curl(path, cookies="/tmp/tq_cookies.txt"):
    r = subprocess.run(
        ["curl", "-s", "-b", cookies, "-c", cookies, f"{BASE}{path}"],
        capture_output=True, text=True, timeout=30,
    )
    return r.stdout


def register_and_login():
    import time
    user = f"solver_{int(time.time())}"
    pw = "Solve1234"
    curl(f"/register", cookies="/dev/null")
    raw = subprocess.run(
        ["curl", "-s", "-c", "/tmp/tq_cookies.txt",
         "-X", "POST",
         "-d", f"username={user}&password={pw}&confirm={pw}",
         f"{BASE}/register"],
        capture_output=True, text=True, timeout=30,
    )
    raw = subprocess.run(
        ["curl", "-s", "-c", "/tmp/tq_cookies.txt", "-b", "/tmp/tq_cookies.txt",
         "-X", "POST",
         "-d", f"username={user}&password={pw}",
         f"{BASE}/login"],
        capture_output=True, text=True, timeout=30,
    )
    print(f"[+] Registered & logged in as {user}")


def solve():
    register_and_login()

    curl("/new-game")

    a0 = json.loads(curl("/ask?question=0"))["answer"]
    a3 = json.loads(curl("/ask?question=3"))["answer"]
    a5 = json.loads(curl("/ask?question=5"))["answer"]

    key = (a0, a3, a5)
    guess = LOOKUP.get(key)
    if guess is None:
        print(f"[-] Unknown combination: {key}")
        sys.exit(1)

    print(f"[+] Answers: Human={a0}, Magic={a3}, RealWorld={a5}")
    print(f"[+] Guessing: {guess}")

    resp = json.loads(curl(f"/guess?guess={guess}"))
    home = curl("/home")

    flag = re.search(r"CTF\{[^}]+\}", home)
    if flag:
        print(f"[+] FLAG: {flag.group(0)}")
    elif resp.get("game") == "won":
        print("[+] Correct guess, but no flag found in page.")
    else:
        print(f"[-] Wrong guess. Response: {resp}")


if __name__ == "__main__":
    solve()
