package com.google.re2j;

import com.google.re2j.MatcherInput;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class Matcher {
    private int anchorFlag;
    private int appendPos;
    private final int groupCount;
    private final int[] groups;
    private boolean hasGroups;
    private boolean hasMatch;
    private int inputLength;
    private MatcherInput matcherInput;
    private final Map<String, Integer> namedGroups;
    private final Pattern pattern;

    private Matcher(Pattern pattern) {
        if (pattern == null) {
            throw new NullPointerException("pattern is null");
        }
        this.pattern = pattern;
        RE2 re2 = pattern.re2();
        int iNumberOfCapturingGroups = re2.numberOfCapturingGroups();
        this.groupCount = iNumberOfCapturingGroups;
        this.groups = new int[(iNumberOfCapturingGroups * 2) + 2];
        this.namedGroups = re2.namedGroups;
    }

    Matcher(Pattern pattern, CharSequence charSequence) {
        this(pattern);
        reset(charSequence);
    }

    Matcher(Pattern pattern, MatcherInput matcherInput) {
        this(pattern);
        reset(matcherInput);
    }

    public Pattern pattern() {
        return this.pattern;
    }

    public Matcher reset() {
        this.inputLength = this.matcherInput.length();
        this.appendPos = 0;
        this.hasMatch = false;
        this.hasGroups = false;
        return this;
    }

    public Matcher reset(CharSequence charSequence) {
        return reset(MatcherInput.utf16(charSequence));
    }

    public Matcher reset(byte[] bArr) {
        return reset(MatcherInput.utf8(bArr));
    }

    private Matcher reset(MatcherInput matcherInput) {
        if (matcherInput == null) {
            throw new NullPointerException("input is null");
        }
        this.matcherInput = matcherInput;
        reset();
        return this;
    }

    public int start() {
        return start(0);
    }

    public int end() {
        return end(0);
    }

    public int start(int i) {
        loadGroup(i);
        return this.groups[i * 2];
    }

    public int start(String str) {
        Integer num = this.namedGroups.get(str);
        if (num == null) {
            throw new IllegalArgumentException("group '" + str + "' not found");
        }
        return start(num.intValue());
    }

    public int end(int i) {
        loadGroup(i);
        return this.groups[(i * 2) + 1];
    }

    public int end(String str) {
        Integer num = this.namedGroups.get(str);
        if (num == null) {
            throw new IllegalArgumentException("group '" + str + "' not found");
        }
        return end(num.intValue());
    }

    public String group() {
        return group(0);
    }

    public String group(int i) {
        int iStart = start(i);
        int iEnd = end(i);
        if (iStart >= 0 || iEnd >= 0) {
            return substring(iStart, iEnd);
        }
        return null;
    }

    public String group(String str) {
        Integer num = this.namedGroups.get(str);
        if (num == null) {
            throw new IllegalArgumentException("group '" + str + "' not found");
        }
        return group(num.intValue());
    }

    public int groupCount() {
        return this.groupCount;
    }

    private void loadGroup(int i) {
        if (i < 0 || i > this.groupCount) {
            throw new IndexOutOfBoundsException("Group index out of bounds: " + i);
        }
        if (!this.hasMatch) {
            throw new IllegalStateException("perhaps no match attempted");
        }
        if (i == 0 || this.hasGroups) {
            return;
        }
        int i2 = this.groups[1] + 1;
        int i3 = this.inputLength;
        int i4 = i2 > i3 ? i3 : i2;
        RE2 re2 = this.pattern.re2();
        MatcherInput matcherInput = this.matcherInput;
        int[] iArr = this.groups;
        if (!re2.match(matcherInput, iArr[0], i4, this.anchorFlag, iArr, this.groupCount + 1)) {
            throw new IllegalStateException("inconsistency in matching group data");
        }
        this.hasGroups = true;
    }

    public boolean matches() {
        return genMatch(0, 2);
    }

    public boolean lookingAt() {
        return genMatch(0, 1);
    }

    public boolean find() {
        int i;
        if (this.hasMatch) {
            int[] iArr = this.groups;
            i = iArr[1];
            if (iArr[0] == i) {
                i++;
            }
        } else {
            i = 0;
        }
        return genMatch(i, 0);
    }

    public boolean find(int i) {
        if (i < 0 || i > this.inputLength) {
            throw new IndexOutOfBoundsException("start index out of bounds: " + i);
        }
        reset();
        return genMatch(i, 0);
    }

    private boolean genMatch(int i, int i2) {
        if (!this.pattern.re2().match(this.matcherInput, i, this.inputLength, i2, this.groups, 1)) {
            return false;
        }
        this.hasMatch = true;
        this.hasGroups = false;
        this.anchorFlag = i2;
        return true;
    }

    String substring(int i, int i2) {
        MatcherInput.Encoding encoding = this.matcherInput.getEncoding();
        MatcherInput.Encoding encoding2 = MatcherInput.Encoding.UTF_8;
        MatcherInput matcherInput = this.matcherInput;
        if (encoding == encoding2) {
            return new String(matcherInput.asBytes(), i, i2 - i);
        }
        return matcherInput.asCharSequence().subSequence(i, i2).toString();
    }

    int inputLength() {
        return this.inputLength;
    }

    public static String quoteReplacement(String str) {
        if (str.indexOf(92) < 0 && str.indexOf(36) < 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\' || cCharAt == '$') {
                sb.append('\\');
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public Matcher appendReplacement(StringBuffer stringBuffer, String str) {
        StringBuilder sb = new StringBuilder();
        appendReplacement(sb, str);
        stringBuffer.append((CharSequence) sb);
        return this;
    }

    public Matcher appendReplacement(StringBuilder sb, String str) {
        int iStart = start();
        int iEnd = end();
        int i = this.appendPos;
        if (i < iStart) {
            sb.append(substring(i, iStart));
        }
        this.appendPos = iEnd;
        appendReplacementInternal(sb, str);
        return this;
    }

    private void appendReplacementInternal(StringBuilder sb, String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length - 1) {
            if (str.charAt(i2) == '\\') {
                if (i3 < i2) {
                    sb.append(str.substring(i3, i2));
                }
                i2++;
                i3 = i2;
            } else if (str.charAt(i2) != '$') {
                continue;
            } else {
                int i4 = i2 + 1;
                char cCharAt = str.charAt(i4);
                if ('0' <= cCharAt && cCharAt <= '9') {
                    int i5 = cCharAt - '0';
                    if (i3 < i2) {
                        sb.append(str.substring(i3, i2));
                    }
                    int i6 = i2 + 2;
                    while (i6 < length) {
                        char cCharAt2 = str.charAt(i6);
                        if (cCharAt2 < '0' || cCharAt2 > '9' || ((i5 * 10) + cCharAt2) - 48 > this.groupCount) {
                            break;
                        }
                        i6++;
                        i5 = i;
                    }
                    if (i5 > this.groupCount) {
                        throw new IndexOutOfBoundsException("n > number of groups: " + i5);
                    }
                    String strGroup = group(i5);
                    if (strGroup != null) {
                        sb.append(strGroup);
                    }
                    i3 = i6;
                    i2 = i6 - 1;
                } else if (cCharAt != '{') {
                    continue;
                } else {
                    if (i3 < i2) {
                        sb.append(str.substring(i3, i2));
                    }
                    int i7 = i2 + 2;
                    int i8 = i7;
                    while (i8 < str.length() && str.charAt(i8) != '}' && str.charAt(i8) != ' ') {
                        i8++;
                    }
                    if (i8 == str.length() || str.charAt(i8) != '}') {
                        throw new IllegalArgumentException("named capture group is missing trailing '}'");
                    }
                    sb.append(group(str.substring(i7, i8)));
                    i3 = i8 + 1;
                    i2 = i4;
                }
            }
            i2++;
        }
        if (i3 < length) {
            sb.append((CharSequence) str, i3, length);
        }
    }

    public StringBuffer appendTail(StringBuffer stringBuffer) {
        stringBuffer.append(substring(this.appendPos, this.inputLength));
        return stringBuffer;
    }

    public StringBuilder appendTail(StringBuilder sb) {
        sb.append(substring(this.appendPos, this.inputLength));
        return sb;
    }

    public String replaceAll(String str) {
        return replace(str, true);
    }

    public String replaceFirst(String str) {
        return replace(str, false);
    }

    private String replace(String str, boolean z) {
        reset();
        StringBuffer stringBuffer = new StringBuffer();
        while (find()) {
            appendReplacement(stringBuffer, str);
            if (!z) {
                break;
            }
        }
        appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
