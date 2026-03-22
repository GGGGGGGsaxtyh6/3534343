package com.google.re2j;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes3.dex */
abstract class MatcherInput {

    enum Encoding {
        UTF_16,
        UTF_8
    }

    abstract byte[] asBytes();

    abstract CharSequence asCharSequence();

    abstract Encoding getEncoding();

    abstract int length();

    MatcherInput() {
    }

    static MatcherInput utf16(CharSequence charSequence) {
        return new Utf16MatcherInput(charSequence);
    }

    static MatcherInput utf8(byte[] bArr) {
        return new Utf8MatcherInput(bArr);
    }

    static MatcherInput utf8(String str) {
        return new Utf8MatcherInput(str.getBytes(Charset.forName("UTF-8")));
    }

    static class Utf8MatcherInput extends MatcherInput {
        byte[] bytes;

        public Utf8MatcherInput(byte[] bArr) {
            this.bytes = bArr;
        }

        @Override // com.google.re2j.MatcherInput
        public Encoding getEncoding() {
            return Encoding.UTF_8;
        }

        @Override // com.google.re2j.MatcherInput
        public CharSequence asCharSequence() {
            return new String(this.bytes, Charset.forName("UTF-8"));
        }

        @Override // com.google.re2j.MatcherInput
        public byte[] asBytes() {
            return this.bytes;
        }

        @Override // com.google.re2j.MatcherInput
        public int length() {
            return this.bytes.length;
        }
    }

    static class Utf16MatcherInput extends MatcherInput {
        CharSequence charSequence;

        public Utf16MatcherInput(CharSequence charSequence) {
            this.charSequence = charSequence;
        }

        @Override // com.google.re2j.MatcherInput
        public Encoding getEncoding() {
            return Encoding.UTF_16;
        }

        @Override // com.google.re2j.MatcherInput
        public CharSequence asCharSequence() {
            return this.charSequence;
        }

        @Override // com.google.re2j.MatcherInput
        public byte[] asBytes() {
            return this.charSequence.toString().getBytes(Charset.forName("UTF-16"));
        }

        @Override // com.google.re2j.MatcherInput
        public int length() {
            return this.charSequence.length();
        }
    }
}
