package com.google.re2j;

import com.google.common.base.Ascii;
import okio.Utf8;

/* JADX INFO: loaded from: classes3.dex */
abstract class MachineInput {
    static final int EOF = -8;

    abstract boolean canCheckPrefix();

    abstract int context(int i);

    abstract int endPos();

    abstract int index(RE2 re2, int i);

    abstract int step(int i);

    MachineInput() {
    }

    static MachineInput fromUTF8(byte[] bArr) {
        return new UTF8Input(bArr);
    }

    static MachineInput fromUTF8(byte[] bArr, int i, int i2) {
        return new UTF8Input(bArr, i, i2);
    }

    static MachineInput fromUTF16(CharSequence charSequence) {
        return new UTF16Input(charSequence, 0, charSequence.length());
    }

    static MachineInput fromUTF16(CharSequence charSequence, int i, int i2) {
        return new UTF16Input(charSequence, i, i2);
    }

    private static class UTF8Input extends MachineInput {
        final byte[] b;
        final int end;
        final int start;

        @Override // com.google.re2j.MachineInput
        boolean canCheckPrefix() {
            return true;
        }

        UTF8Input(byte[] bArr) {
            this.b = bArr;
            this.start = 0;
            this.end = bArr.length;
        }

        UTF8Input(byte[] bArr, int i, int i2) {
            if (i2 > bArr.length) {
                throw new ArrayIndexOutOfBoundsException("end is greater than length: " + i2 + " > " + bArr.length);
            }
            this.b = bArr;
            this.start = i;
            this.end = i2;
        }

        @Override // com.google.re2j.MachineInput
        int step(int i) {
            int i2 = i + this.start;
            int i3 = this.end;
            if (i2 >= i3) {
                return -8;
            }
            byte[] bArr = this.b;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            int i5 = b & 255;
            if ((b & 128) == 0) {
                return (i5 << 3) | 1;
            }
            if ((b & 224) == 192) {
                int i6 = b & Ascii.US;
                if (i4 >= i3) {
                    return -8;
                }
                return (((i6 << 6) | (bArr[i4] & Utf8.REPLACEMENT_BYTE)) << 3) | 2;
            }
            if ((b & 240) == 224) {
                int i7 = b & Ascii.SI;
                int i8 = i2 + 2;
                if (i8 >= i3) {
                    return -8;
                }
                return (((bArr[i8] & Utf8.REPLACEMENT_BYTE) | (((i7 << 6) | (bArr[i4] & Utf8.REPLACEMENT_BYTE)) << 6)) << 3) | 3;
            }
            int i9 = b & 7;
            if (i2 + 3 >= i3) {
                return -8;
            }
            return (((bArr[i2 + 3] & Utf8.REPLACEMENT_BYTE) | (((((i9 << 6) | (bArr[i4] & Utf8.REPLACEMENT_BYTE)) << 6) | (bArr[i2 + 2] & Utf8.REPLACEMENT_BYTE)) << 6)) << 3) | 4;
        }

        @Override // com.google.re2j.MachineInput
        int index(RE2 re2, int i) {
            int i2 = i + this.start;
            int iIndexOf = Utils.indexOf(this.b, re2.prefixUTF8, i2);
            return iIndexOf < 0 ? iIndexOf : iIndexOf - i2;
        }

        @Override // com.google.re2j.MachineInput
        int context(int i) {
            int iStep;
            int i2 = this.start;
            int i3 = i + i2;
            if (i3 <= i2 || i3 > this.end) {
                iStep = -1;
            } else {
                int i4 = i3 - 2;
                iStep = this.b[i3 - 1];
                if (iStep >= 128) {
                    int i5 = i3 - 4;
                    if (i5 >= i2) {
                        i2 = i5;
                    }
                    while (i4 >= i2 && (this.b[i4] & 192) == 128) {
                        i4--;
                    }
                    int i6 = this.start;
                    if (i4 < i6) {
                        i4 = i6;
                    }
                    iStep = step(i4) >> 3;
                }
            }
            return Utils.emptyOpContext(iStep, i3 < this.end ? step(i3) >> 3 : -1);
        }

        @Override // com.google.re2j.MachineInput
        int endPos() {
            return this.end;
        }
    }

    private static class UTF16Input extends MachineInput {
        final int end;
        final int start;
        final CharSequence str;

        @Override // com.google.re2j.MachineInput
        boolean canCheckPrefix() {
            return true;
        }

        public UTF16Input(CharSequence charSequence, int i, int i2) {
            this.str = charSequence;
            this.start = i;
            this.end = i2;
        }

        @Override // com.google.re2j.MachineInput
        int step(int i) {
            int i2 = i + this.start;
            if (i2 >= this.end) {
                return -8;
            }
            int iCodePointAt = Character.codePointAt(this.str, i2);
            return Character.charCount(iCodePointAt) | (iCodePointAt << 3);
        }

        @Override // com.google.re2j.MachineInput
        int index(RE2 re2, int i) {
            int i2 = i + this.start;
            int iIndexOf = indexOf(this.str, re2.prefix, i2);
            return iIndexOf < 0 ? iIndexOf : iIndexOf - i2;
        }

        @Override // com.google.re2j.MachineInput
        int context(int i) {
            int i2 = i + this.start;
            return Utils.emptyOpContext((i2 <= 0 || i2 > this.str.length()) ? -1 : Character.codePointBefore(this.str, i2), i2 < this.str.length() ? Character.codePointAt(this.str, i2) : -1);
        }

        @Override // com.google.re2j.MachineInput
        int endPos() {
            return this.end;
        }

        private int indexOf(CharSequence charSequence, String str, int i) {
            if (charSequence instanceof String) {
                return ((String) charSequence).indexOf(str, i);
            }
            if (charSequence instanceof StringBuilder) {
                return ((StringBuilder) charSequence).indexOf(str, i);
            }
            return indexOfFallback(charSequence, str, i);
        }

        private int indexOfFallback(CharSequence charSequence, String str, int i) {
            if (i >= charSequence.length()) {
                return str.isEmpty() ? 0 : -1;
            }
            if (i < 0) {
                i = 0;
            }
            if (str.isEmpty()) {
                return i;
            }
            char cCharAt = str.charAt(0);
            int length = charSequence.length() - str.length();
            while (i <= length) {
                if (charSequence.charAt(i) != cCharAt) {
                    do {
                        i++;
                        if (i > length) {
                            break;
                        }
                    } while (charSequence.charAt(i) != cCharAt);
                }
                if (i <= length) {
                    int i2 = i + 1;
                    int length2 = (str.length() + i2) - 1;
                    for (int i3 = 1; i2 < length2 && charSequence.charAt(i2) == str.charAt(i3); i3++) {
                        i2++;
                    }
                    if (i2 == length2) {
                        return i;
                    }
                }
                i++;
            }
            return -1;
        }
    }
}
