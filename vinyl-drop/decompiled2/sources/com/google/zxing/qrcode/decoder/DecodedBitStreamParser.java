package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f4 A[LOOP:0: B:64:0x001e->B:61:0x00f4, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bf A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v19 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static DecoderResult decode(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel, Map<DecodeHintType, ?> map) throws FormatException {
        Mode modeForBits;
        int i;
        Mode mode;
        boolean z;
        boolean z2;
        ?? r12;
        StringBuilder sb;
        boolean z3;
        boolean z4;
        ?? r122;
        byte[] bArr2 = bArr;
        BitSource bitSource = new BitSource(bArr2);
        StringBuilder sb2 = new StringBuilder(50);
        int i2 = 1;
        ArrayList arrayList = new ArrayList(1);
        int bits = -1;
        int bits2 = -1;
        boolean z5 = 0;
        boolean z6 = false;
        boolean z7 = false;
        CharacterSetECI characterSetECIByValue = null;
        while (true) {
            try {
                if (bitSource.available() < 4) {
                    modeForBits = Mode.TERMINATOR;
                } else {
                    modeForBits = Mode.forBits(bitSource.readBits(4));
                }
                int i3 = 3;
                ?? r123 = z5;
                boolean z8 = z6;
                boolean z9 = z7;
                switch (modeForBits) {
                    case TERMINATOR:
                        i = 4;
                        r122 = r123;
                        z4 = z8;
                        z3 = z9;
                        mode = modeForBits;
                        r12 = r122;
                        z2 = z4;
                        z = z3;
                        if (mode == Mode.TERMINATOR) {
                            if (characterSetECIByValue != null) {
                                if (z2) {
                                    sb = sb2;
                                    i3 = i;
                                } else if (z) {
                                    i = 6;
                                    sb = sb2;
                                    i3 = i;
                                } else {
                                    sb = sb2;
                                    i3 = 2;
                                }
                            } else if (z2) {
                                sb = sb2;
                            } else if (z) {
                                i = 5;
                                sb = sb2;
                                i3 = i;
                            } else {
                                sb = sb2;
                                i3 = 1;
                            }
                            String string = sb.toString();
                            if (arrayList.isEmpty()) {
                                arrayList = null;
                            }
                            return new DecoderResult(bArr2, string, arrayList, errorCorrectionLevel == null ? null : errorCorrectionLevel.toString(), bits, bits2, i3);
                        }
                        i2 = 1;
                        bArr2 = bArr;
                        z5 = r12;
                        z6 = z2;
                        z7 = z;
                        break;
                    case FNC1_FIRST_POSITION:
                        int i4 = i2;
                        z8 = i4 == true ? 1 : 0;
                        r123 = i4;
                        z9 = z7;
                        i = 4;
                        r122 = r123;
                        z4 = z8;
                        z3 = z9;
                        mode = modeForBits;
                        r12 = r122;
                        z2 = z4;
                        z = z3;
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                    case FNC1_SECOND_POSITION:
                        int i5 = i2;
                        z9 = i5 == true ? 1 : 0;
                        r123 = i5;
                        z8 = z6;
                        i = 4;
                        r122 = r123;
                        z4 = z8;
                        z3 = z9;
                        mode = modeForBits;
                        r12 = r122;
                        z2 = z4;
                        z = z3;
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                    case STRUCTURED_APPEND:
                        if (bitSource.available() < 16) {
                            throw FormatException.getFormatInstance();
                        }
                        bits = bitSource.readBits(8);
                        bits2 = bitSource.readBits(8);
                        mode = modeForBits;
                        i = 4;
                        r12 = z5;
                        z2 = z6;
                        z = z7;
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                    case ECI:
                        characterSetECIByValue = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(bitSource));
                        if (characterSetECIByValue == null) {
                            throw FormatException.getFormatInstance();
                        }
                        mode = modeForBits;
                        i = 4;
                        r12 = z5;
                        z2 = z6;
                        z = z7;
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                    case HANZI:
                        int bits3 = bitSource.readBits(4);
                        int bits4 = bitSource.readBits(modeForBits.getCharacterCountBits(version));
                        if (bits3 == i2) {
                            decodeHanziSegment(bitSource, sb2, bits4);
                        }
                        mode = modeForBits;
                        i = 4;
                        r12 = z5;
                        z2 = z6;
                        z = z7;
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                    default:
                        int bits5 = bitSource.readBits(modeForBits.getCharacterCountBits(version));
                        int i6 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[modeForBits.ordinal()];
                        if (i6 == i2) {
                            mode = modeForBits;
                            i = 4;
                            decodeNumericSegment(bitSource, sb2, bits5);
                            r12 = z5;
                            z2 = z6;
                            z = z7;
                        } else if (i6 == 2) {
                            mode = modeForBits;
                            i = 4;
                            decodeAlphanumericSegment(bitSource, sb2, bits5, z5);
                            r12 = z5;
                            z2 = z6;
                            z = z7;
                        } else if (i6 != 3) {
                            i = 4;
                            if (i6 == 4) {
                                decodeKanjiSegment(bitSource, sb2, bits5);
                                r122 = z5;
                                z4 = z6;
                                z3 = z7;
                                mode = modeForBits;
                                r12 = r122;
                                z2 = z4;
                                z = z3;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                        } else {
                            mode = modeForBits;
                            i = 4;
                            decodeByteSegment(bitSource, sb2, bits5, characterSetECIByValue, arrayList, map);
                            r12 = z5;
                            z2 = z6;
                            z = z7;
                        }
                        if (mode == Mode.TERMINATOR) {
                        }
                        break;
                }
            } catch (IllegalArgumentException unused) {
                throw FormatException.getFormatInstance();
            }
        }
    }

    private static void decodeHanziSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (StringUtils.GB2312_CHARSET == null) {
            throw FormatException.getFormatInstance();
        }
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int bits = bitSource.readBits(13);
            int i3 = (bits % 96) | ((bits / 96) << 8);
            int i4 = i3 + (i3 < 2560 ? 41377 : 42657);
            bArr[i2] = (byte) ((i4 >> 8) & 255);
            bArr[i2 + 1] = (byte) (i4 & 255);
            i2 += 2;
            i--;
        }
        sb.append(new String(bArr, StringUtils.GB2312_CHARSET));
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (StringUtils.SHIFT_JIS_CHARSET == null) {
            throw FormatException.getFormatInstance();
        }
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int bits = bitSource.readBits(13);
            int i3 = (bits % 192) | ((bits / 192) << 8);
            int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        sb.append(new String(bArr, StringUtils.SHIFT_JIS_CHARSET));
    }

    private static void decodeByteSegment(BitSource bitSource, StringBuilder sb, int i, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        Charset charset;
        if (i * 8 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) bitSource.readBits(8);
        }
        if (characterSetECI == null) {
            charset = StringUtils.guessCharset(bArr, map);
        } else {
            charset = characterSetECI.getCharset();
        }
        sb.append(new String(bArr, charset));
        collection.add(bArr);
    }

    private static char toAlphaNumericChar(int i) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i >= cArr.length) {
            throw FormatException.getFormatInstance();
        }
        return cArr[i];
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuilder sb, int i, boolean z) throws FormatException {
        while (i > 1) {
            if (bitSource.available() < 11) {
                throw FormatException.getFormatInstance();
            }
            int bits = bitSource.readBits(11);
            sb.append(toAlphaNumericChar(bits / 45));
            sb.append(toAlphaNumericChar(bits % 45));
            i -= 2;
        }
        if (i == 1) {
            if (bitSource.available() < 6) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bitSource.readBits(6)));
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i2 = length + 1;
                        if (sb.charAt(i2) == '%') {
                            sb.deleteCharAt(i2);
                        } else {
                            sb.setCharAt(length, (char) 29);
                        }
                    }
                }
            }
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        while (i >= 3) {
            if (bitSource.available() < 10) {
                throw FormatException.getFormatInstance();
            }
            int bits = bitSource.readBits(10);
            if (bits >= 1000) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits / 100));
            sb.append(toAlphaNumericChar((bits / 10) % 10));
            sb.append(toAlphaNumericChar(bits % 10));
            i -= 3;
        }
        if (i == 2) {
            if (bitSource.available() < 7) {
                throw FormatException.getFormatInstance();
            }
            int bits2 = bitSource.readBits(7);
            if (bits2 >= 100) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits2 / 10));
            sb.append(toAlphaNumericChar(bits2 % 10));
            return;
        }
        if (i == 1) {
            if (bitSource.available() < 4) {
                throw FormatException.getFormatInstance();
            }
            int bits3 = bitSource.readBits(4);
            if (bits3 >= 10) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits3));
        }
    }

    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int bits = bitSource.readBits(8);
        if ((bits & 128) == 0) {
            return bits & 127;
        }
        if ((bits & 192) == 128) {
            return bitSource.readBits(8) | ((bits & 63) << 8);
        }
        if ((bits & 224) == 192) {
            return bitSource.readBits(16) | ((bits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }
}
