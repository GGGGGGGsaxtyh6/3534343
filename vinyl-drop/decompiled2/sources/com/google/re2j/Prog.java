package com.google.re2j;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
final class Prog {
    Inst[] inst = new Inst[10];
    int instSize = 0;
    int numCap = 2;
    int start;

    Prog() {
    }

    Inst getInst(int i) {
        return this.inst[i];
    }

    int numInst() {
        return this.instSize;
    }

    void addInst(int i) {
        int i2 = this.instSize;
        Inst[] instArr = this.inst;
        if (i2 >= instArr.length) {
            this.inst = (Inst[]) Arrays.copyOf(instArr, instArr.length * 2);
        }
        this.inst[this.instSize] = new Inst(i);
        this.instSize++;
    }

    Inst skipNop(int i) {
        Inst inst = this.inst[i];
        while (true) {
            if (inst.op != 7 && inst.op != 3) {
                return inst;
            }
            inst = this.inst[i];
            i = inst.out;
        }
    }

    boolean prefix(StringBuilder sb) {
        Inst instSkipNop = skipNop(this.start);
        if (!Inst.isRuneOp(instSkipNop.op) || instSkipNop.runes.length != 1) {
            return instSkipNop.op == 6;
        }
        while (Inst.isRuneOp(instSkipNop.op) && instSkipNop.runes.length == 1 && (instSkipNop.arg & 1) == 0) {
            sb.appendCodePoint(instSkipNop.runes[0]);
            instSkipNop = skipNop(instSkipNop.out);
        }
        return instSkipNop.op == 6;
    }

    int startCond() {
        int i = this.start;
        int i2 = 0;
        while (true) {
            Inst inst = this.inst[i];
            int i3 = inst.op;
            if (i3 != 3) {
                if (i3 == 4) {
                    i2 |= inst.arg;
                } else {
                    if (i3 == 5) {
                        return -1;
                    }
                    if (i3 != 7) {
                        return i2;
                    }
                }
            }
            i = inst.out;
        }
    }

    int next(int i) {
        Inst inst = this.inst[i >> 1];
        if ((i & 1) == 0) {
            return inst.out;
        }
        return inst.arg;
    }

    void patch(int i, int i2) {
        while (i != 0) {
            Inst inst = this.inst[i >> 1];
            if ((i & 1) == 0) {
                i = inst.out;
                inst.out = i2;
            } else {
                i = inst.arg;
                inst.arg = i2;
            }
        }
    }

    int append(int i, int i2) {
        if (i == 0) {
            return i2;
        }
        if (i2 == 0) {
            return i;
        }
        int i3 = i;
        while (true) {
            int next = next(i3);
            if (next == 0) {
                break;
            }
            i3 = next;
        }
        Inst inst = this.inst[i3 >> 1];
        if ((i3 & 1) == 0) {
            inst.out = i2;
            return i;
        }
        inst.arg = i2;
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.instSize; i++) {
            int length = sb.length();
            sb.append(i);
            if (i == this.start) {
                sb.append('*');
            }
            sb.append("        ".substring(sb.length() - length)).append(this.inst[i]).append('\n');
        }
        return sb.toString();
    }
}
