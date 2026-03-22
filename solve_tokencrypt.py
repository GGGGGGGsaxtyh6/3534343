#!/usr/bin/env python3
"""
Solve BSidesSF 2026 CTF - tokencrypt (Terminal Crypto, 100pts)

Attack: brute-force 16-bit Feistel key s, recover affine layer via GF(2) linear algebra.
"""
import re, subprocess, sys, os

os.chdir('/workspace')
exec(open('tokencrypt.py').read())


def interact(session_cmds):
    script = "sleep 3\n"
    for cmd, delay in session_cmds:
        script += f'echo "{cmd}"\nsleep {delay}\n'
    script += 'echo "exit"\n'
    proc = subprocess.run(
        ['bash', '-c', f'{{\n{script}\n}} | timeout 40 socat - TCP:tokencrypt-3aad1fd8.challenges.bsidessf.net:1616,connect-timeout=10'],
        capture_output=True, text=True, timeout=60
    )
    clean = re.sub(r'\x1b\[[^a-zA-Z]*[a-zA-Z]', '', proc.stdout)
    return re.sub(r'\x1b[^a-zA-Z\[]*', '', clean)


def parse_tcai(text):
    return [(int(m.group(1)), [int(x.strip()) for x in m.group(2).split(',')])
            for m in re.finditer(r'tc\.ai\((\d+),\s*\[([^\]]+)\]\)', text)]


def solve_gf2(A_rows, b_bits, n):
    nrows = len(A_rows)
    aug = []
    for i in range(nrows):
        row = A_rows[i] & ((1 << n) - 1)
        if (b_bits >> i) & 1:
            row |= (1 << n)
        aug.append(row)
    pivot_row = 0
    for col in range(n):
        found = None
        for r in range(pivot_row, nrows):
            if (aug[r] >> col) & 1:
                found = r
                break
        if found is None:
            continue
        aug[pivot_row], aug[found] = aug[found], aug[pivot_row]
        for r in range(nrows):
            if r != pivot_row and (aug[r] >> col) & 1:
                aug[r] ^= aug[pivot_row]
        pivot_row += 1
    for r in range(pivot_row, nrows):
        if (aug[r] >> n) & 1:
            return None
    x = 0
    for r in range(pivot_row):
        for c in range(n):
            if (aug[r] >> c) & 1:
                if (aug[r] >> n) & 1:
                    x |= (1 << c)
                break
    return x


def gather_data():
    plaintexts = list(range(100001, 100051))
    pt_str = "[" + ", ".join(str(x) for x in plaintexts) + "]"
    output = interact([
        ("getflag", 2),
        ("setsecurity", 1),
        ("Fastest", 2),
        ("encrypt", 1),
        (pt_str, 3),
    ])
    parsed = parse_tcai(output)
    flag_enc = known_cts = None
    for rounds, tokens in parsed:
        if rounds == 1024:
            flag_enc = tokens
        elif rounds == 16:
            known_cts = tokens
    if not flag_enc:
        raise RuntimeError("No encrypted flag found")
    pairs = list(zip(plaintexts[:len(known_cts)], known_cts))
    print(f"Flag ({len(flag_enc)} tokens): {flag_enc}")
    print(f"Known pairs: {len(pairs)}")
    return flag_enc, pairs


def recover_key(pairs):
    pts = [p for p, _ in pairs]
    cts = [c for _, c in pairs]
    y0 = cts[0]
    ediffs = [ct ^ y0 for ct in cts[1:]]
    ediff_bits = [sum(((ediffs[i] >> j) & 1) << i for i in range(len(ediffs))) for j in range(24)]

    print(f"Brute-forcing s (0..65535)...")
    for s_cand in range(65536):
        if s_cand % 10000 == 0:
            print(f"  s={s_cand}...")

        fouts = [_c_encrypt(pt, s_cand, rounds=16) for pt in pts]
        d0 = fouts[0]
        diffs = [fo ^ d0 for fo in fouts[1:]]

        m_rows = []
        valid = True
        for j in range(24):
            row = solve_gf2(diffs, ediff_bits[j], 24)
            if row is None:
                valid = False
                break
            m_rows.append(row)
        if not valid:
            continue

        b24 = (y0 ^ _mat_mul_rows(m_rows, d0)) & MASK24
        if all((_mat_mul_rows(m_rows, _c_encrypt(pt, s_cand, 16)) ^ b24) & MASK24 == ct
               for pt, ct in pairs[:5]):
            print(f"  FOUND s={s_cand}")
            return s_cand, m_rows, b24

    raise RuntimeError("Key recovery failed")


def decrypt_flag(flag_enc, s, m_rows, b24):
    minv = _mat_inv_rows(m_rows)
    chunks = 1024 // 16

    def chunk_dec(y):
        z = _mat_mul_rows(minv, (y ^ b24) & MASK24) & MASK24
        return _c_decrypt(z, s, rounds=16) & MASK24

    result = []
    for ct in flag_enc:
        y = ct & MASK24
        for c in reversed(range(chunks)):
            y = chunk_dec(y) ^ c
        result.append(y)
    return result


def tokens_to_flag(tokens):
    bs = b''
    for t in tokens:
        bs += t.to_bytes(3, 'big')
    return bs.rstrip(b'\x00').decode('utf-8', errors='replace')


if __name__ == '__main__':
    print("=== Gathering data ===")
    flag_enc, pairs = gather_data()
    print("\n=== Recovering key ===")
    s, m_rows, b24 = recover_key(pairs)
    print("\n=== Decrypting flag ===")
    tokens = decrypt_flag(flag_enc, s, m_rows, b24)
    print(f"Tokens: {tokens}")
    flag = tokens_to_flag(tokens)
    print(f"Flag: {flag}")
