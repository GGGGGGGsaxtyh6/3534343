package com.google.re2j;

import kotlin.text.Typography;

/* JADX INFO: loaded from: classes3.dex */
final class Inst {
    public static final int ALT = 1;
    public static final int ALT_MATCH = 2;
    public static final int CAPTURE = 3;
    public static final int EMPTY_WIDTH = 4;
    public static final int FAIL = 5;
    public static final int MATCH = 6;
    public static final int NOP = 7;
    public static final int RUNE = 8;
    public static final int RUNE1 = 9;
    public static final int RUNE_ANY = 10;
    public static final int RUNE_ANY_NOT_NL = 11;
    int arg;
    int op;
    int out;
    int[] runes;

    static boolean isRuneOp(int i) {
        return 8 <= i && i <= 11;
    }

    Inst(int i) {
        this.op = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0039, code lost:
    
        r0 = r1.length / 2;
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x003d, code lost:
    
        if (r1 >= r0) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x003f, code lost:
    
        r4 = ((r0 - r1) / 2) + r1;
        r5 = r8.runes;
        r6 = r4 * 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004a, code lost:
    
        if (r5[r6] > r9) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0050, code lost:
    
        if (r9 > r5[r6 + 1]) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0052, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0053, code lost:
    
        r1 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0056, code lost:
    
        r0 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0058, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    boolean matchRune(int i) {
        int[] iArr = this.runes;
        if (iArr.length == 1) {
            int i2 = iArr[0];
            if (i == i2) {
                return true;
            }
            if ((this.arg & 1) != 0) {
                for (int iSimpleFold = Unicode.simpleFold(i2); iSimpleFold != i2; iSimpleFold = Unicode.simpleFold(iSimpleFold)) {
                    if (i == iSimpleFold) {
                        return true;
                    }
                }
            }
            return false;
        }
        int i3 = 0;
        while (true) {
            int[] iArr2 = this.runes;
            if (i3 >= iArr2.length || i3 > 8) {
                break;
            }
            if (i < iArr2[i3]) {
                return false;
            }
            if (i <= iArr2[i3 + 1]) {
                return true;
            }
            i3 += 2;
        }
    }

    public String toString() {
        switch (this.op) {
            case 1:
                return "alt -> " + this.out + ", " + this.arg;
            case 2:
                return "altmatch -> " + this.out + ", " + this.arg;
            case 3:
                return "cap " + this.arg + " -> " + this.out;
            case 4:
                return "empty " + this.arg + " -> " + this.out;
            case 5:
                return "fail";
            case 6:
                return "match";
            case 7:
                return "nop -> " + this.out;
            case 8:
                if (this.runes == null) {
                    return "rune <null>";
                }
                return "rune " + escapeRunes(this.runes) + ((this.arg & 1) != 0 ? "/i" : "") + " -> " + this.out;
            case 9:
                return "rune1 " + escapeRunes(this.runes) + " -> " + this.out;
            case 10:
                return "any -> " + this.out;
            case 11:
                return "anynotnl -> " + this.out;
            default:
                throw new IllegalStateException("unhandled case in Inst.toString");
        }
    }

    private static String escapeRunes(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.quote);
        for (int i : iArr) {
            Utils.escapeRune(sb, i);
        }
        sb.append(Typography.quote);
        return sb.toString();
    }
}
