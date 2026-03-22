package com.google.re2j;

import java.util.Arrays;
import java.util.Map;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes3.dex */
class Regexp {
    static final Regexp[] EMPTY_SUBS = new Regexp[0];
    int cap;
    int flags;
    int max;
    int min;
    String name;
    Map<String, Integer> namedGroups;
    Op op;
    int[] runes;
    Regexp[] subs;

    enum Op {
        NO_MATCH,
        EMPTY_MATCH,
        LITERAL,
        CHAR_CLASS,
        ANY_CHAR_NOT_NL,
        ANY_CHAR,
        BEGIN_LINE,
        END_LINE,
        BEGIN_TEXT,
        END_TEXT,
        WORD_BOUNDARY,
        NO_WORD_BOUNDARY,
        CAPTURE,
        STAR,
        PLUS,
        QUEST,
        REPEAT,
        CONCAT,
        ALTERNATE,
        LEFT_PAREN,
        VERTICAL_BAR;

        boolean isPseudo() {
            return ordinal() >= LEFT_PAREN.ordinal();
        }
    }

    Regexp(Op op) {
        this.op = op;
    }

    Regexp(Regexp regexp) {
        this.op = regexp.op;
        this.flags = regexp.flags;
        this.subs = regexp.subs;
        this.runes = regexp.runes;
        this.min = regexp.min;
        this.max = regexp.max;
        this.cap = regexp.cap;
        this.name = regexp.name;
        this.namedGroups = regexp.namedGroups;
    }

    void reinit() {
        this.flags = 0;
        this.subs = EMPTY_SUBS;
        this.runes = null;
        this.max = 0;
        this.min = 0;
        this.cap = 0;
        this.name = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendTo(sb);
        return sb.toString();
    }

    private static void quoteIfHyphen(StringBuilder sb, int i) {
        if (i == 45) {
            sb.append('\\');
        }
    }

    private void appendTo(StringBuilder sb) {
        int i = 0;
        switch (AnonymousClass1.$SwitchMap$com$google$re2j$Regexp$Op[this.op.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                Regexp regexp = this.subs[0];
                if (regexp.op.ordinal() > Op.CAPTURE.ordinal() || (regexp.op == Op.LITERAL && regexp.runes.length > 1)) {
                    sb.append("(?:");
                    regexp.appendTo(sb);
                    sb.append(')');
                } else {
                    regexp.appendTo(sb);
                }
                int i2 = AnonymousClass1.$SwitchMap$com$google$re2j$Regexp$Op[this.op.ordinal()];
                if (i2 == 1) {
                    sb.append('*');
                } else if (i2 == 2) {
                    sb.append('+');
                } else if (i2 == 3) {
                    sb.append('?');
                } else if (i2 == 4) {
                    sb.append('{').append(this.min);
                    if (this.min != this.max) {
                        sb.append(',');
                        int i3 = this.max;
                        if (i3 >= 0) {
                            sb.append(i3);
                        }
                    }
                    sb.append('}');
                }
                if ((this.flags & 32) != 0) {
                    sb.append('?');
                }
                break;
            case 5:
                sb.append("[^\\x00-\\x{10FFFF}]");
                break;
            case 6:
                sb.append("(?:)");
                break;
            case 7:
                Regexp[] regexpArr = this.subs;
                int length = regexpArr.length;
                while (i < length) {
                    Regexp regexp2 = regexpArr[i];
                    if (regexp2.op == Op.ALTERNATE) {
                        sb.append("(?:");
                        regexp2.appendTo(sb);
                        sb.append(')');
                    } else {
                        regexp2.appendTo(sb);
                    }
                    i++;
                }
                break;
            case 8:
                Regexp[] regexpArr2 = this.subs;
                int length2 = regexpArr2.length;
                String str = "";
                while (i < length2) {
                    Regexp regexp3 = regexpArr2[i];
                    sb.append(str);
                    regexp3.appendTo(sb);
                    i++;
                    str = "|";
                }
                break;
            case 9:
                if ((this.flags & 1) != 0) {
                    sb.append("(?i:");
                }
                int[] iArr = this.runes;
                int length3 = iArr.length;
                while (i < length3) {
                    Utils.escapeRune(sb, iArr[i]);
                    i++;
                }
                if ((this.flags & 1) != 0) {
                    sb.append(')');
                }
                break;
            case 10:
                sb.append("(?-s:.)");
                break;
            case 11:
                sb.append("(?s:.)");
                break;
            case 12:
                String str2 = this.name;
                if (str2 == null || str2.isEmpty()) {
                    sb.append('(');
                } else {
                    sb.append("(?P<");
                    sb.append(this.name);
                    sb.append(">");
                }
                if (this.subs[0].op != Op.EMPTY_MATCH) {
                    this.subs[0].appendTo(sb);
                }
                sb.append(')');
                break;
            case 13:
                sb.append("\\A");
                break;
            case 14:
                if ((this.flags & 256) != 0) {
                    sb.append("(?-m:$)");
                } else {
                    sb.append("\\z");
                }
                break;
            case 15:
                sb.append('^');
                break;
            case 16:
                sb.append(Typography.dollar);
                break;
            case 17:
                sb.append("\\b");
                break;
            case 18:
                sb.append("\\B");
                break;
            case 19:
                if (this.runes.length % 2 != 0) {
                    sb.append("[invalid char class]");
                } else {
                    sb.append('[');
                    int[] iArr2 = this.runes;
                    if (iArr2.length == 0) {
                        sb.append("^\\x00-\\x{10FFFF}");
                    } else if (iArr2[0] == 0 && iArr2[iArr2.length - 1] == 1114111) {
                        sb.append('^');
                        int i4 = 1;
                        while (true) {
                            int[] iArr3 = this.runes;
                            if (i4 < iArr3.length - 1) {
                                int i5 = iArr3[i4] + 1;
                                int i6 = iArr3[i4 + 1] - 1;
                                quoteIfHyphen(sb, i5);
                                Utils.escapeRune(sb, i5);
                                if (i5 != i6) {
                                    sb.append('-');
                                    quoteIfHyphen(sb, i6);
                                    Utils.escapeRune(sb, i6);
                                }
                                i4 += 2;
                            }
                        }
                    } else {
                        while (true) {
                            int[] iArr4 = this.runes;
                            if (i < iArr4.length) {
                                int i7 = iArr4[i];
                                int i8 = iArr4[i + 1];
                                quoteIfHyphen(sb, i7);
                                Utils.escapeRune(sb, i7);
                                if (i7 != i8) {
                                    sb.append('-');
                                    quoteIfHyphen(sb, i8);
                                    Utils.escapeRune(sb, i8);
                                }
                                i += 2;
                            }
                        }
                    }
                    sb.append(']');
                }
                break;
            default:
                sb.append(this.op);
                break;
        }
    }

    /* JADX INFO: renamed from: com.google.re2j.Regexp$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$re2j$Regexp$Op;

        static {
            int[] iArr = new int[Op.values().length];
            $SwitchMap$com$google$re2j$Regexp$Op = iArr;
            try {
                iArr[Op.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.PLUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.QUEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.REPEAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.NO_MATCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.EMPTY_MATCH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.CONCAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.ALTERNATE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.LITERAL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.ANY_CHAR_NOT_NL.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.ANY_CHAR.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.CAPTURE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.BEGIN_TEXT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.END_TEXT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.BEGIN_LINE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.END_LINE.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.WORD_BOUNDARY.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.NO_WORD_BOUNDARY.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Op.CHAR_CLASS.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    int maxCap() {
        int i = this.op == Op.CAPTURE ? this.cap : 0;
        Regexp[] regexpArr = this.subs;
        if (regexpArr != null) {
            for (Regexp regexp : regexpArr) {
                int iMaxCap = regexp.maxCap();
                if (i < iMaxCap) {
                    i = iMaxCap;
                }
            }
        }
        return i;
    }

    public int hashCode() {
        int iHashCode;
        int iHashCode2;
        int i;
        int iDeepHashCode;
        int iHashCode3 = this.op.hashCode();
        int i2 = AnonymousClass1.$SwitchMap$com$google$re2j$Regexp$Op[this.op.ordinal()];
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            iHashCode = (this.flags & 32) * 31;
            iHashCode2 = this.subs[0].hashCode();
        } else {
            if (i2 != 4) {
                if (i2 != 7 && i2 != 8) {
                    if (i2 == 9) {
                        iDeepHashCode = Arrays.hashCode(this.runes);
                    } else if (i2 == 12) {
                        int i3 = this.cap * 31;
                        String str = this.name;
                        iHashCode = i3 + ((str != null ? str.hashCode() : 0) * 31);
                        iHashCode2 = this.subs[0].hashCode();
                    } else if (i2 == 14) {
                        iDeepHashCode = this.flags & 256;
                    } else {
                        if (i2 != 19) {
                            return iHashCode3;
                        }
                        iDeepHashCode = Arrays.hashCode(this.runes);
                    }
                    return iHashCode3 + i;
                }
                iDeepHashCode = Arrays.deepHashCode(this.subs);
                i = iDeepHashCode * 31;
                return iHashCode3 + i;
            }
            iHashCode = (this.min * 31) + (this.max * 31);
            iHashCode2 = this.subs[0].hashCode();
        }
        i = iHashCode + (iHashCode2 * 31);
        return iHashCode3 + i;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r6) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.re2j.Regexp.equals(java.lang.Object):boolean");
    }
}
