package com.google.re2j;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class Pattern implements Serializable {
    public static final int CASE_INSENSITIVE = 1;
    public static final int DISABLE_UNICODE_GROUPS = 8;
    public static final int DOTALL = 2;
    public static final int LONGEST_MATCH = 16;
    public static final int MULTILINE = 4;
    private static final long serialVersionUID = 0;
    private final int flags;
    private final String pattern;
    private final transient RE2 re2;

    Pattern(String str, int i, RE2 re2) {
        if (str == null) {
            throw new NullPointerException("pattern is null");
        }
        if (re2 == null) {
            throw new NullPointerException("re2 is null");
        }
        this.pattern = str;
        this.flags = i;
        this.re2 = re2;
    }

    public void reset() {
        this.re2.reset();
    }

    public int flags() {
        return this.flags;
    }

    public String pattern() {
        return this.pattern;
    }

    RE2 re2() {
        return this.re2;
    }

    public static Pattern compile(String str) {
        return compile(str, str, 0);
    }

    public static Pattern compile(String str, int i) {
        String str2 = (i & 1) != 0 ? "(?i)" + str : str;
        if ((i & 2) != 0) {
            str2 = "(?s)" + str2;
        }
        if ((i & 4) != 0) {
            str2 = "(?m)" + str2;
        }
        if ((i & (-32)) != 0) {
            throw new IllegalArgumentException("Flags should only be a combination of MULTILINE, DOTALL, CASE_INSENSITIVE, DISABLE_UNICODE_GROUPS, LONGEST_MATCH");
        }
        return compile(str2, str, i);
    }

    private static Pattern compile(String str, String str2, int i) {
        return new Pattern(str2, i, RE2.compileImpl(str, (i & 8) != 0 ? 84 : 212, (i & 16) != 0));
    }

    public static boolean matches(String str, CharSequence charSequence) {
        return compile(str).matcher(charSequence).matches();
    }

    public static boolean matches(String str, byte[] bArr) {
        return compile(str).matcher(bArr).matches();
    }

    public boolean matches(String str) {
        return matcher(str).matches();
    }

    public boolean matches(byte[] bArr) {
        return matcher(bArr).matches();
    }

    public Matcher matcher(CharSequence charSequence) {
        return new Matcher(this, charSequence);
    }

    public Matcher matcher(byte[] bArr) {
        return new Matcher(this, MatcherInput.utf8(bArr));
    }

    Matcher matcher(MatcherInput matcherInput) {
        return new Matcher(this, matcherInput);
    }

    public String[] split(String str) {
        return split(str, 0);
    }

    public String[] split(String str, int i) {
        return split(new Matcher(this, str), i);
    }

    private String[] split(Matcher matcher, int i) {
        int i2 = 0;
        int iEnd = 0;
        int i3 = 0;
        int i4 = 0;
        while (matcher.find()) {
            i3++;
            if (i != 0 || iEnd < matcher.start()) {
                i4 = i3;
            }
            iEnd = matcher.end();
        }
        int i5 = 1;
        if (iEnd < matcher.inputLength() || i != 0) {
            i4 = i3 + 1;
        }
        if (i <= 0 || i4 <= i) {
            i5 = 0;
            i = i4;
        }
        String[] strArr = new String[i];
        matcher.reset();
        int iEnd2 = 0;
        while (matcher.find() && i2 < i - i5) {
            strArr[i2] = matcher.substring(iEnd2, matcher.start());
            iEnd2 = matcher.end();
            i2++;
        }
        if (i2 < i) {
            strArr[i2] = matcher.substring(iEnd2, matcher.inputLength());
        }
        return strArr;
    }

    public static String quote(String str) {
        return RE2.quoteMeta(str);
    }

    public String toString() {
        return this.pattern;
    }

    public int groupCount() {
        return this.re2.numberOfCapturingGroups();
    }

    public Map<String, Integer> namedGroups() {
        return Collections.unmodifiableMap(this.re2.namedGroups);
    }

    Object readResolve() {
        return compile(this.pattern, this.flags);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Pattern pattern = (Pattern) obj;
            if (this.flags == pattern.flags && this.pattern.equals(pattern.pattern)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.pattern.hashCode() * 31) + this.flags;
    }
}
