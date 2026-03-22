package com.google.zxing.common;

import com.google.zxing.FormatException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes3.dex */
public final class ECIStringBuilder {
    private StringBuilder currentBytes;
    private Charset currentCharset;
    private StringBuilder result;

    public ECIStringBuilder() {
        this.currentCharset = StandardCharsets.ISO_8859_1;
        this.currentBytes = new StringBuilder();
    }

    public ECIStringBuilder(int i) {
        this.currentCharset = StandardCharsets.ISO_8859_1;
        this.currentBytes = new StringBuilder(i);
    }

    public void append(char c) {
        this.currentBytes.append((char) (c & 255));
    }

    public void append(byte b) {
        this.currentBytes.append((char) (b & 255));
    }

    public void append(String str) {
        this.currentBytes.append(str);
    }

    public void append(int i) {
        append(String.valueOf(i));
    }

    public void appendECI(int i) throws FormatException {
        encodeCurrentBytesIfAny();
        CharacterSetECI characterSetECIByValue = CharacterSetECI.getCharacterSetECIByValue(i);
        if (characterSetECIByValue == null) {
            throw FormatException.getFormatInstance();
        }
        this.currentCharset = characterSetECIByValue.getCharset();
    }

    private void encodeCurrentBytesIfAny() {
        boolean zEquals = this.currentCharset.equals(StandardCharsets.ISO_8859_1);
        StringBuilder sb = this.currentBytes;
        if (zEquals) {
            if (sb.length() > 0) {
                StringBuilder sb2 = this.result;
                StringBuilder sb3 = this.currentBytes;
                if (sb2 == null) {
                    this.result = sb3;
                    this.currentBytes = new StringBuilder();
                    return;
                } else {
                    sb2.append((CharSequence) sb3);
                    this.currentBytes = new StringBuilder();
                    return;
                }
            }
            return;
        }
        if (sb.length() > 0) {
            byte[] bytes = this.currentBytes.toString().getBytes(StandardCharsets.ISO_8859_1);
            this.currentBytes = new StringBuilder();
            StringBuilder sb4 = this.result;
            Charset charset = this.currentCharset;
            if (sb4 == null) {
                this.result = new StringBuilder(new String(bytes, charset));
            } else {
                sb4.append(new String(bytes, charset));
            }
        }
    }

    public void appendCharacters(StringBuilder sb) {
        encodeCurrentBytesIfAny();
        this.result.append((CharSequence) sb);
    }

    public int length() {
        return toString().length();
    }

    public boolean isEmpty() {
        if (this.currentBytes.length() != 0) {
            return false;
        }
        StringBuilder sb = this.result;
        return sb == null || sb.length() == 0;
    }

    public String toString() {
        encodeCurrentBytesIfAny();
        StringBuilder sb = this.result;
        return sb == null ? "" : sb.toString();
    }
}
