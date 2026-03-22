package com.google.re2j;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.re2j.Regexp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
class Parser {
    private static final int[][] ANY_TABLE = {new int[]{0, 1114111, 1}};
    private static final String ERR_DUPLICATE_NAMED_CAPTURE = "duplicate capture group name";
    private static final String ERR_INTERNAL_ERROR = "regexp/syntax: internal error";
    private static final String ERR_INVALID_CHAR_CLASS = "invalid character class";
    private static final String ERR_INVALID_CHAR_RANGE = "invalid character class range";
    private static final String ERR_INVALID_ESCAPE = "invalid escape sequence";
    private static final String ERR_INVALID_NAMED_CAPTURE = "invalid named capture";
    private static final String ERR_INVALID_PERL_OP = "invalid or unsupported Perl syntax";
    private static final String ERR_INVALID_REPEAT_OP = "invalid nested repetition operator";
    private static final String ERR_INVALID_REPEAT_SIZE = "invalid repeat count";
    private static final String ERR_MISSING_BRACKET = "missing closing ]";
    private static final String ERR_MISSING_PAREN = "missing closing )";
    private static final String ERR_MISSING_REPEAT_ARGUMENT = "missing argument to repetition operator";
    private static final String ERR_TRAILING_BACKSLASH = "trailing backslash at end of expression";
    private int flags;
    private Regexp free;
    private final String wholeRegexp;
    private final Stack stack = new Stack(null);
    private int numCap = 0;
    private final Map<String, Integer> namedGroups = new HashMap();

    private static class Stack extends ArrayList<Regexp> {
        private Stack() {
        }

        /* synthetic */ Stack(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.util.ArrayList, java.util.AbstractList
        public void removeRange(int i, int i2) {
            super.removeRange(i, i2);
        }
    }

    Parser(String str, int i) {
        this.wholeRegexp = str;
        this.flags = i;
    }

    private Regexp newRegexp(Regexp.Op op) {
        Regexp regexp = this.free;
        if (regexp != null && regexp.subs != null && regexp.subs.length > 0) {
            this.free = regexp.subs[0];
            regexp.reinit();
            regexp.op = op;
            return regexp;
        }
        return new Regexp(op);
    }

    private void reuse(Regexp regexp) {
        if (regexp.subs != null && regexp.subs.length > 0) {
            regexp.subs[0] = this.free;
        }
        this.free = regexp;
    }

    private Regexp pop() {
        return this.stack.remove(r0.size() - 1);
    }

    private Regexp[] popToPseudo() {
        int size = this.stack.size();
        int i = size;
        while (i > 0 && !this.stack.get(i - 1).op.isPseudo()) {
            i--;
        }
        Regexp[] regexpArr = (Regexp[]) this.stack.subList(i, size).toArray(new Regexp[size - i]);
        this.stack.removeRange(i, size);
        return regexpArr;
    }

    private Regexp push(Regexp regexp) {
        if (regexp.op == Regexp.Op.CHAR_CLASS && regexp.runes.length == 2 && regexp.runes[0] == regexp.runes[1]) {
            if (maybeConcat(regexp.runes[0], this.flags & (-2))) {
                return null;
            }
            regexp.op = Regexp.Op.LITERAL;
            regexp.runes = new int[]{regexp.runes[0]};
            regexp.flags = this.flags & (-2);
        } else if ((regexp.op == Regexp.Op.CHAR_CLASS && regexp.runes.length == 4 && regexp.runes[0] == regexp.runes[1] && regexp.runes[2] == regexp.runes[3] && Unicode.simpleFold(regexp.runes[0]) == regexp.runes[2] && Unicode.simpleFold(regexp.runes[2]) == regexp.runes[0]) || (regexp.op == Regexp.Op.CHAR_CLASS && regexp.runes.length == 2 && regexp.runes[0] + 1 == regexp.runes[1] && Unicode.simpleFold(regexp.runes[0]) == regexp.runes[1] && Unicode.simpleFold(regexp.runes[1]) == regexp.runes[0])) {
            if (maybeConcat(regexp.runes[0], this.flags | 1)) {
                return null;
            }
            regexp.op = Regexp.Op.LITERAL;
            regexp.runes = new int[]{regexp.runes[0]};
            regexp.flags = this.flags | 1;
        } else {
            maybeConcat(-1, 0);
        }
        this.stack.add(regexp);
        return regexp;
    }

    private boolean maybeConcat(int i, int i2) {
        int size = this.stack.size();
        if (size < 2) {
            return false;
        }
        Regexp regexp = this.stack.get(size - 1);
        Regexp regexp2 = this.stack.get(size - 2);
        if (regexp.op == Regexp.Op.LITERAL && regexp2.op == Regexp.Op.LITERAL && (regexp.flags & 1) == (regexp2.flags & 1)) {
            regexp2.runes = concatRunes(regexp2.runes, regexp.runes);
            if (i >= 0) {
                regexp.runes = new int[]{i};
                regexp.flags = i2;
                return true;
            }
            pop();
            reuse(regexp);
        }
        return false;
    }

    private Regexp newLiteral(int i, int i2) {
        Regexp regexpNewRegexp = newRegexp(Regexp.Op.LITERAL);
        regexpNewRegexp.flags = i2;
        if ((i2 & 1) != 0) {
            i = minFoldRune(i);
        }
        regexpNewRegexp.runes = new int[]{i};
        return regexpNewRegexp;
    }

    private static int minFoldRune(int i) {
        if (i < 65 || i > 66639) {
            return i;
        }
        int i2 = i;
        for (int iSimpleFold = Unicode.simpleFold(i); iSimpleFold != i; iSimpleFold = Unicode.simpleFold(iSimpleFold)) {
            if (i2 > iSimpleFold) {
                i2 = iSimpleFold;
            }
        }
        return i2;
    }

    private void literal(int i) {
        push(newLiteral(i, this.flags));
    }

    private Regexp op(Regexp.Op op) {
        Regexp regexpNewRegexp = newRegexp(op);
        regexpNewRegexp.flags = this.flags;
        return push(regexpNewRegexp);
    }

    private void repeat(Regexp.Op op, int i, int i2, int i3, StringIterator stringIterator, int i4) throws PatternSyntaxException {
        int i5 = this.flags;
        if ((i5 & 64) != 0) {
            if (stringIterator.more() && stringIterator.lookingAt('?')) {
                stringIterator.skip(1);
                i5 ^= 32;
            }
            if (i4 != -1) {
                throw new PatternSyntaxException(ERR_INVALID_REPEAT_OP, stringIterator.from(i4));
            }
        }
        int size = this.stack.size();
        if (size == 0) {
            throw new PatternSyntaxException(ERR_MISSING_REPEAT_ARGUMENT, stringIterator.from(i3));
        }
        int i6 = size - 1;
        Regexp regexp = this.stack.get(i6);
        if (regexp.op.isPseudo()) {
            throw new PatternSyntaxException(ERR_MISSING_REPEAT_ARGUMENT, stringIterator.from(i3));
        }
        Regexp regexpNewRegexp = newRegexp(op);
        regexpNewRegexp.min = i;
        regexpNewRegexp.max = i2;
        regexpNewRegexp.flags = i5;
        regexpNewRegexp.subs = new Regexp[]{regexp};
        this.stack.set(i6, regexpNewRegexp);
    }

    private Regexp concat() {
        maybeConcat(-1, 0);
        Regexp[] regexpArrPopToPseudo = popToPseudo();
        if (regexpArrPopToPseudo.length == 0) {
            return push(newRegexp(Regexp.Op.EMPTY_MATCH));
        }
        return push(collapse(regexpArrPopToPseudo, Regexp.Op.CONCAT));
    }

    private Regexp alternate() {
        Regexp[] regexpArrPopToPseudo = popToPseudo();
        if (regexpArrPopToPseudo.length > 0) {
            cleanAlt(regexpArrPopToPseudo[regexpArrPopToPseudo.length - 1]);
        }
        if (regexpArrPopToPseudo.length == 0) {
            return push(newRegexp(Regexp.Op.NO_MATCH));
        }
        return push(collapse(regexpArrPopToPseudo, Regexp.Op.ALTERNATE));
    }

    private void cleanAlt(Regexp regexp) {
        if (regexp.op == Regexp.Op.CHAR_CLASS) {
            regexp.runes = new CharClass(regexp.runes).cleanClass().toArray();
            if (regexp.runes.length == 2 && regexp.runes[0] == 0 && regexp.runes[1] == 1114111) {
                regexp.runes = null;
                regexp.op = Regexp.Op.ANY_CHAR;
            } else if (regexp.runes.length == 4 && regexp.runes[0] == 0 && regexp.runes[1] == 9 && regexp.runes[2] == 11 && regexp.runes[3] == 1114111) {
                regexp.runes = null;
                regexp.op = Regexp.Op.ANY_CHAR_NOT_NL;
            }
        }
    }

    private Regexp collapse(Regexp[] regexpArr, Regexp.Op op) {
        if (regexpArr.length == 1) {
            return regexpArr[0];
        }
        int length = 0;
        for (Regexp regexp : regexpArr) {
            length += regexp.op == op ? regexp.subs.length : 1;
        }
        Regexp[] regexpArr2 = new Regexp[length];
        int length2 = 0;
        for (Regexp regexp2 : regexpArr) {
            if (regexp2.op == op) {
                System.arraycopy(regexp2.subs, 0, regexpArr2, length2, regexp2.subs.length);
                length2 += regexp2.subs.length;
                reuse(regexp2);
            } else {
                regexpArr2[length2] = regexp2;
                length2++;
            }
        }
        Regexp regexpNewRegexp = newRegexp(op);
        regexpNewRegexp.subs = regexpArr2;
        if (op == Regexp.Op.ALTERNATE) {
            regexpNewRegexp.subs = factor(regexpNewRegexp.subs, regexpNewRegexp.flags);
            if (regexpNewRegexp.subs.length == 1) {
                Regexp regexp3 = regexpNewRegexp.subs[0];
                reuse(regexpNewRegexp);
                return regexp3;
            }
        }
        return regexpNewRegexp;
    }

    private Regexp[] factor(Regexp[] regexpArr, int i) {
        int i2;
        Regexp regexpLeadingRegexp;
        char c;
        int i3;
        int length;
        int[] iArr;
        int i4;
        if (regexpArr.length < 2) {
            return regexpArr;
        }
        int length2 = regexpArr.length;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int[] iArr2 = null;
        while (true) {
            char c2 = 1;
            if (i5 > length2) {
                break;
            }
            if (i5 < length2) {
                Regexp regexp = regexpArr[i5];
                if (regexp.op == Regexp.Op.CONCAT && regexp.subs.length > 0) {
                    regexp = regexp.subs[0];
                }
                if (regexp.op == Regexp.Op.LITERAL) {
                    iArr = regexp.runes;
                    length = regexp.runes.length;
                    i3 = regexp.flags & 1;
                } else {
                    i3 = 0;
                    length = 0;
                    iArr = null;
                }
                if (i3 == i7) {
                    int i10 = 0;
                    while (i10 < i8 && i10 < length) {
                        c = c2;
                        if (iArr2[i10] != iArr[i10]) {
                            break;
                        }
                        i10++;
                        c2 = c;
                    }
                    c = c2;
                    if (i10 > 0) {
                        i8 = i10;
                    }
                    i5++;
                } else {
                    c = 1;
                }
            } else {
                c = 1;
                i3 = 0;
                length = 0;
                iArr = null;
            }
            if (i5 != i9) {
                if (i5 == i9 + 1) {
                    i4 = i6 + 1;
                    regexpArr[i6] = regexpArr[i9];
                } else {
                    Regexp regexpNewRegexp = newRegexp(Regexp.Op.LITERAL);
                    regexpNewRegexp.flags = i7;
                    regexpNewRegexp.runes = Utils.subarray(iArr2, 0, i8);
                    for (int i11 = i9; i11 < i5; i11++) {
                        regexpArr[i11] = removeLeadingString(regexpArr[i11], i8);
                    }
                    Regexp regexpCollapse = collapse(subarray(regexpArr, i9, i5), Regexp.Op.ALTERNATE);
                    Regexp regexpNewRegexp2 = newRegexp(Regexp.Op.CONCAT);
                    Regexp[] regexpArr2 = new Regexp[2];
                    regexpArr2[0] = regexpNewRegexp;
                    regexpArr2[c] = regexpCollapse;
                    regexpNewRegexp2.subs = regexpArr2;
                    i4 = i6 + 1;
                    regexpArr[i6] = regexpNewRegexp2;
                }
                i6 = i4;
            }
            i9 = i5;
            i7 = i3;
            iArr2 = iArr;
            i8 = length;
            i5++;
        }
        int i12 = 0;
        int i13 = 0;
        Regexp regexp2 = null;
        for (int i14 = 0; i14 <= i6; i14++) {
            if (i14 < i6) {
                regexpLeadingRegexp = leadingRegexp(regexpArr[i14]);
                if (regexp2 == null || !regexp2.equals(regexpLeadingRegexp) || (!isCharClass(regexp2) && (regexp2.op != Regexp.Op.REPEAT || regexp2.min != regexp2.max || !isCharClass(regexp2.subs[0])))) {
                }
            } else {
                regexpLeadingRegexp = null;
            }
            if (i14 == i13) {
                i13 = i14;
                regexp2 = regexpLeadingRegexp;
            } else if (i14 == i13 + 1) {
                regexpArr[i12] = regexpArr[i13];
                i12++;
                i13 = i14;
                regexp2 = regexpLeadingRegexp;
            } else {
                int i15 = i13;
                while (i15 < i14) {
                    regexpArr[i15] = removeLeadingRegexp(regexpArr[i15], i15 != i13);
                    i15++;
                }
                Regexp regexpCollapse2 = collapse(subarray(regexpArr, i13, i14), Regexp.Op.ALTERNATE);
                Regexp regexpNewRegexp3 = newRegexp(Regexp.Op.CONCAT);
                regexpNewRegexp3.subs = new Regexp[]{regexp2, regexpCollapse2};
                regexpArr[i12] = regexpNewRegexp3;
                i12++;
                i13 = i14;
                regexp2 = regexpLeadingRegexp;
            }
        }
        int i16 = 0;
        int i17 = 0;
        for (int i18 = 0; i18 <= i12; i18++) {
            if (i18 >= i12 || !isCharClass(regexpArr[i18])) {
                if (i18 != i17) {
                    int i19 = i17 + 1;
                    if (i18 == i19) {
                        i2 = i16 + 1;
                        regexpArr[i16] = regexpArr[i17];
                    } else {
                        int i20 = i17;
                        for (int i21 = i19; i21 < i18; i21++) {
                            Regexp regexp3 = regexpArr[i20];
                            Regexp regexp4 = regexpArr[i21];
                            if (regexp3.op.ordinal() < regexp4.op.ordinal() || (regexp3.op == regexp4.op && regexp3.runes.length < regexp4.runes.length)) {
                                i20 = i21;
                            }
                        }
                        Regexp regexp5 = regexpArr[i17];
                        regexpArr[i17] = regexpArr[i20];
                        regexpArr[i20] = regexp5;
                        while (i19 < i18) {
                            mergeCharClass(regexpArr[i17], regexpArr[i19]);
                            reuse(regexpArr[i19]);
                            i19++;
                        }
                        cleanAlt(regexpArr[i17]);
                        i2 = i16 + 1;
                        regexpArr[i16] = regexpArr[i17];
                    }
                    i16 = i2;
                }
                if (i18 < i12) {
                    regexpArr[i16] = regexpArr[i18];
                    i16++;
                }
                i17 = i18 + 1;
            }
        }
        int i22 = 0;
        int i23 = 0;
        while (i22 < i16) {
            int i24 = i22 + 1;
            if (i24 >= i16 || regexpArr[i22].op != Regexp.Op.EMPTY_MATCH || regexpArr[i22 + 1].op != Regexp.Op.EMPTY_MATCH) {
                regexpArr[i23] = regexpArr[i22];
                i23++;
            }
            i22 = i24;
        }
        return subarray(regexpArr, 0, i23);
    }

    private Regexp removeLeadingString(Regexp regexp, int i) {
        if (regexp.op == Regexp.Op.CONCAT && regexp.subs.length > 0) {
            Regexp regexpRemoveLeadingString = removeLeadingString(regexp.subs[0], i);
            regexp.subs[0] = regexpRemoveLeadingString;
            if (regexpRemoveLeadingString.op == Regexp.Op.EMPTY_MATCH) {
                reuse(regexpRemoveLeadingString);
                int length = regexp.subs.length;
                if (length == 0 || length == 1) {
                    regexp.op = Regexp.Op.EMPTY_MATCH;
                    regexp.subs = null;
                    return regexp;
                }
                if (length == 2) {
                    Regexp regexp2 = regexp.subs[1];
                    reuse(regexp);
                    return regexp2;
                }
                regexp.subs = subarray(regexp.subs, 1, regexp.subs.length);
                return regexp;
            }
        } else if (regexp.op == Regexp.Op.LITERAL) {
            regexp.runes = Utils.subarray(regexp.runes, i, regexp.runes.length);
            if (regexp.runes.length == 0) {
                regexp.op = Regexp.Op.EMPTY_MATCH;
            }
        }
        return regexp;
    }

    private static Regexp leadingRegexp(Regexp regexp) {
        if (regexp.op == Regexp.Op.EMPTY_MATCH) {
            return null;
        }
        if (regexp.op == Regexp.Op.CONCAT && regexp.subs.length > 0) {
            regexp = regexp.subs[0];
            if (regexp.op == Regexp.Op.EMPTY_MATCH) {
                return null;
            }
        }
        return regexp;
    }

    private Regexp removeLeadingRegexp(Regexp regexp, boolean z) {
        if (regexp.op == Regexp.Op.CONCAT && regexp.subs.length > 0) {
            if (z) {
                reuse(regexp.subs[0]);
            }
            regexp.subs = subarray(regexp.subs, 1, regexp.subs.length);
            int length = regexp.subs.length;
            if (length == 0) {
                regexp.op = Regexp.Op.EMPTY_MATCH;
                regexp.subs = Regexp.EMPTY_SUBS;
                return regexp;
            }
            if (length != 1) {
                return regexp;
            }
            Regexp regexp2 = regexp.subs[0];
            reuse(regexp);
            return regexp2;
        }
        if (z) {
            reuse(regexp);
        }
        return newRegexp(Regexp.Op.EMPTY_MATCH);
    }

    private static Regexp literalRegexp(String str, int i) {
        Regexp regexp = new Regexp(Regexp.Op.LITERAL);
        regexp.flags = i;
        regexp.runes = Utils.stringToRunes(str);
        return regexp;
    }

    private static class StringIterator {
        private int pos = 0;
        private final String str;

        StringIterator(String str) {
            this.str = str;
        }

        int pos() {
            return this.pos;
        }

        void rewindTo(int i) {
            this.pos = i;
        }

        boolean more() {
            return this.pos < this.str.length();
        }

        int peek() {
            return this.str.codePointAt(this.pos);
        }

        void skip(int i) {
            this.pos += i;
        }

        void skipString(String str) {
            this.pos += str.length();
        }

        int pop() {
            int iCodePointAt = this.str.codePointAt(this.pos);
            this.pos += Character.charCount(iCodePointAt);
            return iCodePointAt;
        }

        boolean lookingAt(char c) {
            return this.str.charAt(this.pos) == c;
        }

        boolean lookingAt(String str) {
            return rest().startsWith(str);
        }

        String rest() {
            return this.str.substring(this.pos);
        }

        String from(int i) {
            return this.str.substring(i, this.pos);
        }

        public String toString() {
            return rest();
        }
    }

    static Regexp parse(String str, int i) throws PatternSyntaxException {
        return new Parser(str, i).parseInternal();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:70:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0152  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Regexp parseInternal() throws PatternSyntaxException {
        int iPos;
        Regexp.Op op;
        CharClass charClass;
        int i = this.flags;
        if ((i & 2) != 0) {
            return literalRegexp(this.wholeRegexp, i);
        }
        StringIterator stringIterator = new StringIterator(this.wholeRegexp);
        int i2 = -1;
        short s = -1;
        int i3 = -1;
        while (true) {
            if (stringIterator.more()) {
                int iPeek = stringIterator.peek();
                if (iPeek == 36) {
                    if ((this.flags & 16) != 0) {
                        op(Regexp.Op.END_TEXT).flags |= 256;
                    } else {
                        op(Regexp.Op.END_LINE);
                    }
                    stringIterator.skip(1);
                } else if (iPeek == 46) {
                    if ((this.flags & 8) != 0) {
                        op(Regexp.Op.ANY_CHAR);
                    } else {
                        op(Regexp.Op.ANY_CHAR_NOT_NL);
                    }
                    stringIterator.skip(1);
                } else {
                    if (iPeek != 63) {
                        if (iPeek == 94) {
                            if ((this.flags & 16) != 0) {
                                op(Regexp.Op.BEGIN_TEXT);
                            } else {
                                op(Regexp.Op.BEGIN_LINE);
                            }
                            stringIterator.skip(1);
                        } else if (iPeek == 91) {
                            parseClass(stringIterator);
                        } else if (iPeek == 92) {
                            int iPos2 = stringIterator.pos();
                            stringIterator.skip(1);
                            if ((this.flags & 64) != 0 && stringIterator.more()) {
                                int iPop = stringIterator.pop();
                                if (iPop == 81) {
                                    String strRest = stringIterator.rest();
                                    int iIndexOf = strRest.indexOf("\\E");
                                    if (iIndexOf >= 0) {
                                        strRest = strRest.substring(0, iIndexOf);
                                    }
                                    stringIterator.skipString(strRest);
                                    stringIterator.skipString("\\E");
                                    for (int i4 = 0; i4 < strRest.length(); i4++) {
                                        literal(strRest.charAt(i4));
                                    }
                                } else if (iPop == 98) {
                                    op(Regexp.Op.WORD_BOUNDARY);
                                } else if (iPop != 122) {
                                    switch (iPop) {
                                        case 65:
                                            op(Regexp.Op.BEGIN_TEXT);
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                                            op(Regexp.Op.NO_WORD_BOUNDARY);
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                                            throw new PatternSyntaxException(ERR_INVALID_ESCAPE, "\\C");
                                        default:
                                            stringIterator.rewindTo(iPos2);
                                            Regexp regexpNewRegexp = newRegexp(Regexp.Op.CHAR_CLASS);
                                            regexpNewRegexp.flags = this.flags;
                                            if (!stringIterator.lookingAt("\\p")) {
                                                charClass = new CharClass();
                                                if (!parseUnicodeClass(stringIterator, charClass)) {
                                                }
                                            }
                                            break;
                                    }
                                } else {
                                    op(Regexp.Op.END_TEXT);
                                }
                            } else {
                                Regexp regexpNewRegexp2 = newRegexp(Regexp.Op.CHAR_CLASS);
                                regexpNewRegexp2.flags = this.flags;
                                if (!stringIterator.lookingAt("\\p") || stringIterator.lookingAt("\\P")) {
                                    charClass = new CharClass();
                                    if (!parseUnicodeClass(stringIterator, charClass)) {
                                        regexpNewRegexp2.runes = charClass.toArray();
                                        push(regexpNewRegexp2);
                                    } else {
                                        CharClass charClass2 = new CharClass();
                                        if (parsePerlClassEscape(stringIterator, charClass2)) {
                                            regexpNewRegexp2.runes = charClass2.toArray();
                                            push(regexpNewRegexp2);
                                        } else {
                                            stringIterator.rewindTo(iPos2);
                                            reuse(regexpNewRegexp2);
                                            literal(parseEscape(stringIterator));
                                        }
                                    }
                                }
                            }
                        } else if (iPeek == 123) {
                            iPos = stringIterator.pos();
                            int repeat = parseRepeat(stringIterator);
                            if (repeat < 0) {
                                stringIterator.rewindTo(iPos);
                                literal(stringIterator.pop());
                            } else {
                                i2 = repeat >> 16;
                                s = (short) (repeat & 65535);
                                repeat(Regexp.Op.REPEAT, i2, s, iPos, stringIterator, i3);
                            }
                            i3 = iPos;
                        } else if (iPeek != 124) {
                            switch (iPeek) {
                                case 40:
                                    if ((this.flags & 64) != 0 && stringIterator.lookingAt("(?")) {
                                        parsePerlFlags(stringIterator);
                                    } else {
                                        Regexp regexpOp = op(Regexp.Op.LEFT_PAREN);
                                        int i5 = this.numCap + 1;
                                        this.numCap = i5;
                                        regexpOp.cap = i5;
                                        stringIterator.skip(1);
                                    }
                                    break;
                                case 41:
                                    parseRightParen();
                                    stringIterator.skip(1);
                                    break;
                                case 42:
                                case 43:
                                    break;
                                default:
                                    literal(stringIterator.pop());
                                    break;
                            }
                        } else {
                            parseVerticalBar();
                            stringIterator.skip(1);
                        }
                    }
                    iPos = stringIterator.pos();
                    int iPop2 = stringIterator.pop();
                    if (iPop2 == 42) {
                        op = Regexp.Op.STAR;
                    } else if (iPop2 == 43) {
                        op = Regexp.Op.PLUS;
                    } else {
                        op = iPop2 != 63 ? null : Regexp.Op.QUEST;
                    }
                    repeat(op, i2, s, iPos, stringIterator, i3);
                    i3 = iPos;
                }
                i3 = -1;
            } else {
                concat();
                if (swapVerticalBar()) {
                    pop();
                }
                alternate();
                if (this.stack.size() != 1) {
                    throw new PatternSyntaxException(ERR_MISSING_PAREN, this.wholeRegexp);
                }
                this.stack.get(0).namedGroups = this.namedGroups;
                return this.stack.get(0);
            }
        }
    }

    private static int parseRepeat(StringIterator stringIterator) throws PatternSyntaxException {
        int i;
        int iPos = stringIterator.pos();
        if (stringIterator.more() && stringIterator.lookingAt('{')) {
            stringIterator.skip(1);
            int i2 = parseInt(stringIterator);
            if (i2 == -1 || !stringIterator.more()) {
                return -1;
            }
            if (stringIterator.lookingAt(',')) {
                stringIterator.skip(1);
                if (!stringIterator.more()) {
                    return -1;
                }
                if (stringIterator.lookingAt('}')) {
                    i = -1;
                } else {
                    i = parseInt(stringIterator);
                    if (i == -1) {
                        return -1;
                    }
                }
            } else {
                i = i2;
            }
            if (stringIterator.more() && stringIterator.lookingAt('}')) {
                stringIterator.skip(1);
                if (i2 < 0 || i2 > 1000 || i == -2 || i > 1000 || (i >= 0 && i2 > i)) {
                    throw new PatternSyntaxException(ERR_INVALID_REPEAT_SIZE, stringIterator.from(iPos));
                }
                return (i2 << 16) | (65535 & i);
            }
        }
        return -1;
    }

    private void parsePerlFlags(StringIterator stringIterator) throws PatternSyntaxException {
        int iPos = stringIterator.pos();
        String strRest = stringIterator.rest();
        if (strRest.startsWith("(?P<")) {
            int iIndexOf = strRest.indexOf(62);
            if (iIndexOf < 0) {
                throw new PatternSyntaxException(ERR_INVALID_NAMED_CAPTURE, strRest);
            }
            String strSubstring = strRest.substring(4, iIndexOf);
            stringIterator.skipString(strSubstring);
            stringIterator.skip(5);
            if (!isValidCaptureName(strSubstring)) {
                throw new PatternSyntaxException(ERR_INVALID_NAMED_CAPTURE, strRest.substring(0, iIndexOf));
            }
            Regexp regexpOp = op(Regexp.Op.LEFT_PAREN);
            int i = this.numCap + 1;
            this.numCap = i;
            regexpOp.cap = i;
            if (this.namedGroups.put(strSubstring, Integer.valueOf(this.numCap)) != null) {
                throw new PatternSyntaxException(ERR_DUPLICATE_NAMED_CAPTURE, strSubstring);
            }
            regexpOp.name = strSubstring;
            return;
        }
        stringIterator.skip(2);
        int i2 = this.flags;
        boolean z = false;
        byte b = 1;
        while (stringIterator.more()) {
            int iPop = stringIterator.pop();
            if (iPop != 41) {
                if (iPop == 45) {
                    if (b < 0) {
                        break;
                    }
                    i2 = ~i2;
                    b = -1;
                    z = false;
                } else if (iPop != 58) {
                    if (iPop == 85) {
                        i2 |= 32;
                    } else if (iPop == 105) {
                        i2 |= 1;
                    } else if (iPop == 109) {
                        i2 &= -17;
                    } else if (iPop != 115) {
                        break;
                    } else {
                        i2 |= 8;
                    }
                    z = true;
                }
            }
            if (b < 0) {
                if (z) {
                    i2 = ~i2;
                }
            }
            if (iPop == 58) {
                op(Regexp.Op.LEFT_PAREN);
            }
            this.flags = i2;
            return;
        }
        throw new PatternSyntaxException(ERR_INVALID_PERL_OP, stringIterator.from(iPos));
    }

    private static boolean isValidCaptureName(String str) {
        if (str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt != '_' && !Utils.isalnum(cCharAt)) {
                return false;
            }
        }
        return true;
    }

    private static int parseInt(StringIterator stringIterator) {
        int iPeek;
        int iPos = stringIterator.pos();
        while (stringIterator.more() && (iPeek = stringIterator.peek()) >= 48 && iPeek <= 57) {
            stringIterator.skip(1);
        }
        String strFrom = stringIterator.from(iPos);
        if (strFrom.isEmpty()) {
            return -1;
        }
        if (strFrom.length() > 1 && strFrom.charAt(0) == '0') {
            return -1;
        }
        if (strFrom.length() > 8) {
            return -2;
        }
        return Integer.valueOf(strFrom, 10).intValue();
    }

    private static boolean isCharClass(Regexp regexp) {
        return (regexp.op == Regexp.Op.LITERAL && regexp.runes.length == 1) || regexp.op == Regexp.Op.CHAR_CLASS || regexp.op == Regexp.Op.ANY_CHAR_NOT_NL || regexp.op == Regexp.Op.ANY_CHAR;
    }

    /* JADX INFO: renamed from: com.google.re2j.Parser$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$re2j$Regexp$Op;

        static {
            int[] iArr = new int[Regexp.Op.values().length];
            $SwitchMap$com$google$re2j$Regexp$Op = iArr;
            try {
                iArr[Regexp.Op.LITERAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Regexp.Op.CHAR_CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Regexp.Op.ANY_CHAR_NOT_NL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$re2j$Regexp$Op[Regexp.Op.ANY_CHAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static boolean matchRune(Regexp regexp, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$google$re2j$Regexp$Op[regexp.op.ordinal()];
        if (i2 == 1) {
            return regexp.runes.length == 1 && regexp.runes[0] == i;
        }
        if (i2 != 2) {
            return i2 != 3 ? i2 == 4 : i != 10;
        }
        for (int i3 = 0; i3 < regexp.runes.length; i3 += 2) {
            if (regexp.runes[i3] <= i && i <= regexp.runes[i3 + 1]) {
                return true;
            }
        }
        return false;
    }

    private void parseVerticalBar() {
        concat();
        if (swapVerticalBar()) {
            return;
        }
        op(Regexp.Op.VERTICAL_BAR);
    }

    private static void mergeCharClass(Regexp regexp, Regexp regexp2) {
        int i = AnonymousClass1.$SwitchMap$com$google$re2j$Regexp$Op[regexp.op.ordinal()];
        if (i == 1) {
            if (regexp2.runes[0] == regexp.runes[0] && regexp2.flags == regexp.flags) {
                return;
            }
            regexp.op = Regexp.Op.CHAR_CLASS;
            regexp.runes = new CharClass().appendLiteral(regexp.runes[0], regexp.flags).appendLiteral(regexp2.runes[0], regexp2.flags).toArray();
            return;
        }
        if (i != 2) {
            if (i == 3 && matchRune(regexp2, 10)) {
                regexp.op = Regexp.Op.ANY_CHAR;
                return;
            }
            return;
        }
        if (regexp2.op == Regexp.Op.LITERAL) {
            regexp.runes = new CharClass(regexp.runes).appendLiteral(regexp2.runes[0], regexp2.flags).toArray();
        } else {
            regexp.runes = new CharClass(regexp.runes).appendClass(regexp2.runes).toArray();
        }
    }

    private boolean swapVerticalBar() {
        int size = this.stack.size();
        if (size >= 3 && this.stack.get(size - 2).op == Regexp.Op.VERTICAL_BAR) {
            int i = size - 1;
            if (isCharClass(this.stack.get(i))) {
                int i2 = size - 3;
                if (isCharClass(this.stack.get(i2))) {
                    Regexp regexp = this.stack.get(i);
                    Regexp regexp2 = this.stack.get(i2);
                    if (regexp.op.ordinal() > regexp2.op.ordinal()) {
                        this.stack.set(i2, regexp);
                    } else {
                        regexp2 = regexp;
                        regexp = regexp2;
                    }
                    mergeCharClass(regexp, regexp2);
                    reuse(regexp2);
                    pop();
                    return true;
                }
            }
        }
        if (size < 2) {
            return false;
        }
        int i3 = size - 1;
        Regexp regexp3 = this.stack.get(i3);
        int i4 = size - 2;
        Regexp regexp4 = this.stack.get(i4);
        if (regexp4.op != Regexp.Op.VERTICAL_BAR) {
            return false;
        }
        if (size >= 3) {
            cleanAlt(this.stack.get(size - 3));
        }
        this.stack.set(i4, regexp3);
        this.stack.set(i3, regexp4);
        return true;
    }

    private void parseRightParen() throws PatternSyntaxException {
        concat();
        if (swapVerticalBar()) {
            pop();
        }
        alternate();
        if (this.stack.size() < 2) {
            throw new PatternSyntaxException(ERR_INTERNAL_ERROR, "stack underflow");
        }
        Regexp regexpPop = pop();
        Regexp regexpPop2 = pop();
        if (regexpPop2.op != Regexp.Op.LEFT_PAREN) {
            throw new PatternSyntaxException(ERR_MISSING_PAREN, this.wholeRegexp);
        }
        this.flags = regexpPop2.flags;
        if (regexpPop2.cap == 0) {
            push(regexpPop);
            return;
        }
        regexpPop2.op = Regexp.Op.CAPTURE;
        regexpPop2.subs = new Regexp[]{regexpPop};
        push(regexpPop2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x004c, code lost:
    
        if (r6.peek() <= 55) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int parseEscape(StringIterator stringIterator) throws PatternSyntaxException {
        int iPos = stringIterator.pos();
        stringIterator.skip(1);
        if (!stringIterator.more()) {
            throw new PatternSyntaxException(ERR_TRAILING_BACKSLASH);
        }
        int iPop = stringIterator.pop();
        if (iPop == 97) {
            return 7;
        }
        if (iPop == 102) {
            return 12;
        }
        if (iPop == 110) {
            return 10;
        }
        if (iPop == 114) {
            return 13;
        }
        if (iPop == 116) {
            return 9;
        }
        if (iPop == 118) {
            return 11;
        }
        if (iPop != 120) {
            switch (iPop) {
                case 49:
                case 50:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                case 53:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                case 55:
                    if (stringIterator.more()) {
                        if (stringIterator.peek() >= 48) {
                        }
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    int iPeek = iPop - 48;
                    for (int i = 1; i < 3 && stringIterator.more() && stringIterator.peek() >= 48 && stringIterator.peek() <= 55; i++) {
                        iPeek = ((iPeek * 8) + stringIterator.peek()) - 48;
                        stringIterator.skip(1);
                    }
                    return iPeek;
                default:
                    if (!Utils.isalnum(iPop)) {
                        return iPop;
                    }
                    break;
            }
        } else if (stringIterator.more()) {
            int iPop2 = stringIterator.pop();
            if (iPop2 == 123) {
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    if (!stringIterator.more()) {
                        break;
                    }
                    int iPop3 = stringIterator.pop();
                    if (iPop3 != 125) {
                        int iUnhex = Utils.unhex(iPop3);
                        if (iUnhex < 0 || (i2 = (i2 * 16) + iUnhex) > 1114111) {
                            break;
                        }
                        i3++;
                    } else if (i3 != 0) {
                        return i2;
                    }
                }
            } else {
                int iUnhex2 = Utils.unhex(iPop2);
                if (stringIterator.more()) {
                    int iUnhex3 = Utils.unhex(stringIterator.pop());
                    if (iUnhex2 >= 0 && iUnhex3 >= 0) {
                        return (iUnhex2 * 16) + iUnhex3;
                    }
                }
            }
        }
        throw new PatternSyntaxException(ERR_INVALID_ESCAPE, stringIterator.from(iPos));
    }

    private static int parseClassChar(StringIterator stringIterator, int i) throws PatternSyntaxException {
        if (!stringIterator.more()) {
            throw new PatternSyntaxException(ERR_MISSING_BRACKET, stringIterator.from(i));
        }
        if (stringIterator.lookingAt('\\')) {
            return parseEscape(stringIterator);
        }
        return stringIterator.pop();
    }

    private boolean parsePerlClassEscape(StringIterator stringIterator, CharClass charClass) {
        int iPos = stringIterator.pos();
        if ((this.flags & 64) == 0 || !stringIterator.more() || stringIterator.pop() != 92 || !stringIterator.more()) {
            return false;
        }
        stringIterator.pop();
        CharGroup charGroup = CharGroup.PERL_GROUPS.get(stringIterator.from(iPos));
        if (charGroup == null) {
            return false;
        }
        charClass.appendGroup(charGroup, (this.flags & 1) != 0);
        return true;
    }

    private boolean parseNamedClass(StringIterator stringIterator, CharClass charClass) throws PatternSyntaxException {
        String strRest = stringIterator.rest();
        int iIndexOf = strRest.indexOf(":]");
        if (iIndexOf < 0) {
            return false;
        }
        String strSubstring = strRest.substring(0, iIndexOf + 2);
        stringIterator.skipString(strSubstring);
        CharGroup charGroup = CharGroup.POSIX_GROUPS.get(strSubstring);
        if (charGroup == null) {
            throw new PatternSyntaxException(ERR_INVALID_CHAR_RANGE, strSubstring);
        }
        charClass.appendGroup(charGroup, (this.flags & 1) != 0);
        return true;
    }

    private static Pair<int[][], int[][]> unicodeTable(String str) {
        if (str.equals("Any")) {
            int[][] iArr = ANY_TABLE;
            return Pair.of(iArr, iArr);
        }
        int[][] iArr2 = UnicodeTables.CATEGORIES.get(str);
        if (iArr2 != null) {
            return Pair.of(iArr2, UnicodeTables.FOLD_CATEGORIES.get(str));
        }
        int[][] iArr3 = UnicodeTables.SCRIPTS.get(str);
        if (iArr3 != null) {
            return Pair.of(iArr3, UnicodeTables.FOLD_SCRIPT.get(str));
        }
        return null;
    }

    private boolean parseUnicodeClass(StringIterator stringIterator, CharClass charClass) throws PatternSyntaxException {
        String strSubstring;
        int iPos = stringIterator.pos();
        if ((this.flags & 128) == 0 || !(stringIterator.lookingAt("\\p") || stringIterator.lookingAt("\\P"))) {
            return false;
        }
        stringIterator.skip(1);
        int i = stringIterator.pop() == 80 ? -1 : 1;
        if (!stringIterator.more()) {
            stringIterator.rewindTo(iPos);
            throw new PatternSyntaxException(ERR_INVALID_CHAR_RANGE, stringIterator.rest());
        }
        int iPop = stringIterator.pop();
        if (iPop != 123) {
            strSubstring = Utils.runeToString(iPop);
        } else {
            String strRest = stringIterator.rest();
            int iIndexOf = strRest.indexOf(125);
            if (iIndexOf < 0) {
                stringIterator.rewindTo(iPos);
                throw new PatternSyntaxException(ERR_INVALID_CHAR_RANGE, stringIterator.rest());
            }
            strSubstring = strRest.substring(0, iIndexOf);
            stringIterator.skipString(strSubstring);
            stringIterator.skip(1);
        }
        if (!strSubstring.isEmpty() && strSubstring.charAt(0) == '^') {
            i = -i;
            strSubstring = strSubstring.substring(1);
        }
        Pair<int[][], int[][]> pairUnicodeTable = unicodeTable(strSubstring);
        if (pairUnicodeTable == null) {
            throw new PatternSyntaxException(ERR_INVALID_CHAR_RANGE, stringIterator.from(iPos));
        }
        int[][] iArr = pairUnicodeTable.first;
        int[][] iArr2 = pairUnicodeTable.second;
        if ((this.flags & 1) == 0 || iArr2 == null) {
            charClass.appendTableWithSign(iArr, i);
        } else {
            charClass.appendClassWithSign(new CharClass().appendTable(iArr).appendTable(iArr2).cleanClass().toArray(), i);
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x008b, code lost:
    
        r13.rewindTo(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0097, code lost:
    
        throw new com.google.re2j.PatternSyntaxException(com.google.re2j.Parser.ERR_INVALID_CHAR_RANGE, r13.rest());
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseClass(StringIterator stringIterator) throws PatternSyntaxException {
        byte b;
        int classChar;
        int classChar2;
        int iPos = stringIterator.pos();
        stringIterator.skip(1);
        Regexp regexpNewRegexp = newRegexp(Regexp.Op.CHAR_CLASS);
        regexpNewRegexp.flags = this.flags;
        CharClass charClass = new CharClass();
        if (stringIterator.more() && stringIterator.lookingAt('^')) {
            stringIterator.skip(1);
            if ((this.flags & 4) == 0) {
                charClass.appendRange(10, 10);
            }
            b = -1;
        } else {
            b = 1;
        }
        boolean z = true;
        while (true) {
            if (!stringIterator.more() || stringIterator.peek() != 93 || z) {
                if (stringIterator.more() && stringIterator.lookingAt('-') && (this.flags & 64) == 0 && !z) {
                    String strRest = stringIterator.rest();
                    if (strRest.equals("-") || !strRest.startsWith("-]")) {
                        break;
                    }
                }
                int iPos2 = stringIterator.pos();
                if (stringIterator.lookingAt("[:")) {
                    if (parseNamedClass(stringIterator, charClass)) {
                        continue;
                    } else {
                        stringIterator.rewindTo(iPos2);
                        if (parseUnicodeClass(stringIterator, charClass)) {
                            continue;
                        } else {
                            stringIterator.rewindTo(iPos2);
                            classChar = parseClassChar(stringIterator, iPos);
                            if (!stringIterator.more()) {
                                classChar2 = classChar;
                                if ((this.flags & 1) != 0) {
                                }
                            }
                        }
                    }
                } else if (parseUnicodeClass(stringIterator, charClass) && !parsePerlClassEscape(stringIterator, charClass)) {
                    stringIterator.rewindTo(iPos2);
                    classChar = parseClassChar(stringIterator, iPos);
                    if (!stringIterator.more() && stringIterator.lookingAt('-')) {
                        stringIterator.skip(1);
                        if (stringIterator.more() && stringIterator.lookingAt(']')) {
                            stringIterator.skip(-1);
                            classChar2 = classChar;
                            if ((this.flags & 1) != 0) {
                            }
                        } else {
                            classChar2 = parseClassChar(stringIterator, iPos);
                            if (classChar2 < classChar) {
                                throw new PatternSyntaxException(ERR_INVALID_CHAR_RANGE, stringIterator.from(iPos2));
                            }
                            if ((this.flags & 1) != 0) {
                            }
                        }
                    } else {
                        classChar2 = classChar;
                        if ((this.flags & 1) != 0) {
                            charClass.appendRange(classChar, classChar2);
                        } else {
                            charClass.appendFoldedRange(classChar, classChar2);
                        }
                    }
                }
                z = false;
            } else {
                stringIterator.skip(1);
                charClass.cleanClass();
                if (b < 0) {
                    charClass.negateClass();
                }
                regexpNewRegexp.runes = charClass.toArray();
                push(regexpNewRegexp);
                return;
            }
        }
    }

    static Regexp[] subarray(Regexp[] regexpArr, int i, int i2) {
        Regexp[] regexpArr2 = new Regexp[i2 - i];
        for (int i3 = i; i3 < i2; i3++) {
            regexpArr2[i3 - i] = regexpArr[i3];
        }
        return regexpArr2;
    }

    private static class Pair<F, S> {
        final F first;
        final S second;

        Pair(F f, S s) {
            this.first = f;
            this.second = s;
        }

        static <F, S> Pair<F, S> of(F f, S s) {
            return new Pair<>(f, s);
        }
    }

    private static int[] concatRunes(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[iArr.length + iArr2.length];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
        return iArr3;
    }
}
