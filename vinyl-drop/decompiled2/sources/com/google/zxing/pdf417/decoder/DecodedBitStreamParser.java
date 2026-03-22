package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.ECIStringBuilder;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.math.BigInteger;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_ADDRESSEE = 4;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_CHECKSUM = 6;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_NAME = 0;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_SIZE = 5;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SEGMENT_COUNT = 1;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SENDER = 3;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_TIME_STAMP = 2;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger bigIntegerValueOf = BigInteger.valueOf(900L);
        bigIntegerArr[1] = bigIntegerValueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(bigIntegerValueOf);
            i++;
        }
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(int[] iArr, String str) throws FormatException {
        ECIStringBuilder eCIStringBuilder = new ECIStringBuilder(iArr.length * 2);
        int iTextCompaction = textCompaction(iArr, 1, eCIStringBuilder);
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        while (iTextCompaction < iArr[0]) {
            int i = iTextCompaction + 1;
            int i2 = iArr[iTextCompaction];
            if (i2 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                switch (i2) {
                    case 900:
                        iTextCompaction = textCompaction(iArr, i, eCIStringBuilder);
                        continue;
                    case 901:
                        break;
                    case 902:
                        iTextCompaction = numericCompaction(iArr, i, eCIStringBuilder);
                        continue;
                    default:
                        switch (i2) {
                            case MACRO_PDF417_TERMINATOR /* 922 */:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                                throw FormatException.getFormatInstance();
                            case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                                break;
                            case ECI_USER_DEFINED /* 925 */:
                                iTextCompaction += 2;
                                continue;
                            case ECI_GENERAL_PURPOSE /* 926 */:
                                iTextCompaction += 3;
                                continue;
                            case ECI_CHARSET /* 927 */:
                                iTextCompaction += 2;
                                eCIStringBuilder.appendECI(iArr[i]);
                                continue;
                            case 928:
                                iTextCompaction = decodeMacroBlock(iArr, i, pDF417ResultMetadata);
                                continue;
                            default:
                                iTextCompaction = textCompaction(iArr, iTextCompaction, eCIStringBuilder);
                                continue;
                        }
                        break;
                }
                iTextCompaction = byteCompaction(i2, iArr, i, eCIStringBuilder);
            } else {
                iTextCompaction += 2;
                eCIStringBuilder.append((char) iArr[i]);
            }
        }
        if (eCIStringBuilder.isEmpty() && pDF417ResultMetadata.getFileId() == null) {
            throw FormatException.getFormatInstance();
        }
        DecoderResult decoderResult = new DecoderResult(null, eCIStringBuilder.toString(), null, str);
        decoderResult.setOther(pDF417ResultMetadata);
        return decoderResult;
    }

    static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        int i2;
        if (i + 2 > iArr[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] iArr2 = new int[2];
        int i3 = 0;
        while (i3 < 2) {
            iArr2[i3] = iArr[i];
            i3++;
            i++;
        }
        String strDecodeBase900toBase10 = decodeBase900toBase10(iArr2, 2);
        if (strDecodeBase900toBase10.isEmpty()) {
            pDF417ResultMetadata.setSegmentIndex(0);
        } else {
            try {
                pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(strDecodeBase900toBase10));
            } catch (NumberFormatException unused) {
                throw FormatException.getFormatInstance();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (i < iArr[0] && i < iArr.length && (i2 = iArr[i]) != MACRO_PDF417_TERMINATOR && i2 != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
            sb.append(String.format("%03d", Integer.valueOf(i2)));
            i++;
        }
        if (sb.length() == 0) {
            throw FormatException.getFormatInstance();
        }
        pDF417ResultMetadata.setFileId(sb.toString());
        int i4 = iArr[i] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD ? i + 1 : -1;
        while (i < iArr[0]) {
            int i5 = iArr[i];
            if (i5 == MACRO_PDF417_TERMINATOR) {
                i++;
                pDF417ResultMetadata.setLastSegment(true);
            } else if (i5 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                switch (iArr[i + 1]) {
                    case 0:
                        ECIStringBuilder eCIStringBuilder = new ECIStringBuilder();
                        i = textCompaction(iArr, i + 2, eCIStringBuilder);
                        pDF417ResultMetadata.setFileName(eCIStringBuilder.toString());
                        break;
                    case 1:
                        ECIStringBuilder eCIStringBuilder2 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i + 2, eCIStringBuilder2);
                        try {
                            pDF417ResultMetadata.setSegmentCount(Integer.parseInt(eCIStringBuilder2.toString()));
                        } catch (NumberFormatException unused2) {
                            throw FormatException.getFormatInstance();
                        }
                        break;
                    case 2:
                        ECIStringBuilder eCIStringBuilder3 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i + 2, eCIStringBuilder3);
                        try {
                            pDF417ResultMetadata.setTimestamp(Long.parseLong(eCIStringBuilder3.toString()));
                        } catch (NumberFormatException unused3) {
                            throw FormatException.getFormatInstance();
                        }
                        break;
                    case 3:
                        ECIStringBuilder eCIStringBuilder4 = new ECIStringBuilder();
                        i = textCompaction(iArr, i + 2, eCIStringBuilder4);
                        pDF417ResultMetadata.setSender(eCIStringBuilder4.toString());
                        break;
                    case 4:
                        ECIStringBuilder eCIStringBuilder5 = new ECIStringBuilder();
                        i = textCompaction(iArr, i + 2, eCIStringBuilder5);
                        pDF417ResultMetadata.setAddressee(eCIStringBuilder5.toString());
                        break;
                    case 5:
                        ECIStringBuilder eCIStringBuilder6 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i + 2, eCIStringBuilder6);
                        try {
                            pDF417ResultMetadata.setFileSize(Long.parseLong(eCIStringBuilder6.toString()));
                        } catch (NumberFormatException unused4) {
                            throw FormatException.getFormatInstance();
                        }
                        break;
                    case 6:
                        ECIStringBuilder eCIStringBuilder7 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i + 2, eCIStringBuilder7);
                        try {
                            pDF417ResultMetadata.setChecksum(Integer.parseInt(eCIStringBuilder7.toString()));
                        } catch (NumberFormatException unused5) {
                            throw FormatException.getFormatInstance();
                        }
                        break;
                    default:
                        throw FormatException.getFormatInstance();
                }
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i4 != -1) {
            int i6 = i - i4;
            if (pDF417ResultMetadata.isLastSegment()) {
                i6--;
            }
            if (i6 > 0) {
                pDF417ResultMetadata.setOptionalData(Arrays.copyOfRange(iArr, i4, i6 + i4));
            }
        }
        return i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x0039. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x003c. Please report as an issue. */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1092)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1117)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1117)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1117)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1117)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    private static int textCompaction(int[] r10, int r11, com.google.zxing.common.ECIStringBuilder r12) throws com.google.zxing.FormatException {
        /*
            r0 = 0
            r1 = r10[r0]
            int r2 = r1 - r11
            int r2 = r2 * 2
            int[] r2 = new int[r2]
            int r1 = r1 - r11
            int r1 = r1 * 2
            int[] r1 = new int[r1]
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r3 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r4 = r0
            r5 = r4
        L12:
            r6 = r10[r0]
            if (r11 >= r6) goto L79
            if (r4 != 0) goto L79
            int r6 = r11 + 1
            r7 = r10[r11]
            r8 = 900(0x384, float:1.261E-42)
            if (r7 >= r8) goto L2d
            int r11 = r7 / 30
            r2[r5] = r11
            int r11 = r5 + 1
            int r7 = r7 % 30
            r2[r11] = r7
            int r5 = r5 + 2
            goto L3f
        L2d:
            r9 = 913(0x391, float:1.28E-42)
            if (r7 == r9) goto L6e
            r9 = 927(0x39f, float:1.299E-42)
            if (r7 == r9) goto L49
            r9 = 928(0x3a0, float:1.3E-42)
            if (r7 == r9) goto L47
            switch(r7) {
                case 900: goto L41;
                case 901: goto L47;
                case 902: goto L47;
                default: goto L3c;
            }
        L3c:
            switch(r7) {
                case 922: goto L47;
                case 923: goto L47;
                case 924: goto L47;
                default: goto L3f;
            }
        L3f:
            r11 = r6
            goto L12
        L41:
            int r11 = r5 + 1
            r2[r5] = r8
            r5 = r11
            goto L3f
        L47:
            r4 = 1
            goto L12
        L49:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = decodeTextCompaction(r2, r1, r5, r12, r3)
            int r11 = r11 + 2
            r2 = r10[r6]
            r12.appendECI(r2)
            r2 = r10[r0]
            if (r11 > r2) goto L69
            int r3 = r2 - r11
            int r3 = r3 * 2
            int[] r3 = new int[r3]
            int r2 = r2 - r11
            int r2 = r2 * 2
            int[] r2 = new int[r2]
            r5 = r3
            r3 = r1
            r1 = r2
            r2 = r5
            r5 = r0
            goto L12
        L69:
            com.google.zxing.FormatException r10 = com.google.zxing.FormatException.getFormatInstance()
            throw r10
        L6e:
            r2[r5] = r9
            int r11 = r11 + 2
            r6 = r10[r6]
            r1[r5] = r6
            int r5 = r5 + 1
            goto L12
        L79:
            decodeTextCompaction(r2, r1, r5, r12, r3)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.textCompaction(int[], int, com.google.zxing.common.ECIStringBuilder):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ee A[FALL_THROUGH, PHI: r3 r4
      0x00ee: PHI (r3v7 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode) = 
      (r3v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r3v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r3v2 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r3v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r3v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r3v10 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r3v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r3v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r3v11 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r3v16 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
     binds: [B:78:0x00e8, B:72:0x00d6, B:74:0x00da, B:64:0x00be, B:58:0x00ae, B:63:0x00ba, B:52:0x009a, B:47:0x0086, B:49:0x008b, B:33:0x005b] A[DONT_GENERATE, DONT_INLINE]
      0x00ee: PHI (r4v7 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode) = 
      (r4v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r4v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r4v3 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r4v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r4v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r4v12 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r4v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r4v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r4v15 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
      (r4v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
     binds: [B:78:0x00e8, B:72:0x00d6, B:74:0x00da, B:64:0x00be, B:58:0x00ae, B:63:0x00ba, B:52:0x009a, B:47:0x0086, B:49:0x008b, B:33:0x005b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Mode decodeTextCompaction(int[] iArr, int[] iArr2, int i, ECIStringBuilder eCIStringBuilder, Mode mode) {
        int i2;
        char c;
        char c2;
        Mode mode2;
        Mode mode3;
        Mode mode4 = mode;
        Mode mode5 = mode4;
        Mode mode6 = mode5;
        int i3 = 0;
        while (i3 < i) {
            int i4 = iArr[i3];
            int iOrdinal = mode5.ordinal();
            char c3 = ' ';
            if (iOrdinal != 0) {
                if (iOrdinal != 1) {
                    if (iOrdinal != 2) {
                        if (iOrdinal != 3) {
                            if (iOrdinal != 4) {
                                if (iOrdinal == 5) {
                                    if (i4 < 29) {
                                        c2 = PUNCT_CHARS[i4];
                                    } else if (i4 == 29 || i4 == 900) {
                                        mode5 = Mode.ALPHA;
                                    } else {
                                        if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                            eCIStringBuilder.append((char) iArr2[i3]);
                                        }
                                        c2 = 0;
                                    }
                                }
                            } else if (i4 < 26) {
                                c2 = (char) (i4 + 65);
                            } else {
                                if (i4 != 26) {
                                    if (i4 != 900) {
                                        c3 = 0;
                                    } else {
                                        mode5 = Mode.ALPHA;
                                        c3 = 0;
                                        mode2 = mode6;
                                        mode6 = mode5;
                                        c2 = c3;
                                    }
                                }
                                mode5 = mode6;
                                mode2 = mode6;
                                mode6 = mode5;
                                c2 = c3;
                            }
                            mode2 = mode6;
                        } else if (i4 < 29) {
                            c = PUNCT_CHARS[i4];
                            Mode mode7 = mode6;
                            mode6 = mode5;
                            c2 = c;
                            mode2 = mode7;
                        } else if (i4 == 29 || i4 == 900) {
                            mode4 = Mode.ALPHA;
                            mode5 = mode4;
                        } else if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            eCIStringBuilder.append((char) iArr2[i3]);
                        }
                        mode2 = mode6;
                        mode6 = mode5;
                        c2 = 0;
                    } else if (i4 < 25) {
                        c = MIXED_CHARS[i4];
                        Mode mode72 = mode6;
                        mode6 = mode5;
                        c2 = c;
                        mode2 = mode72;
                    } else if (i4 == 900) {
                        mode4 = Mode.ALPHA;
                        c3 = 0;
                        mode5 = mode4;
                        mode2 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    } else {
                        if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            switch (i4) {
                                case 25:
                                    mode4 = Mode.PUNCT;
                                    c3 = 0;
                                    mode5 = mode4;
                                    break;
                                case 26:
                                    break;
                                case 27:
                                    mode4 = Mode.LOWER;
                                    c3 = 0;
                                    mode5 = mode4;
                                    break;
                                case 28:
                                    break;
                                case 29:
                                    mode6 = mode5;
                                    mode5 = Mode.PUNCT_SHIFT;
                                default:
                                    c3 = 0;
                                    break;
                            }
                            mode2 = mode6;
                            mode6 = mode5;
                            c2 = c3;
                        } else {
                            eCIStringBuilder.append((char) iArr2[i3]);
                        }
                        c3 = 0;
                        mode2 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    }
                } else if (i4 < 26) {
                    i2 = i4 + 97;
                    c = (char) i2;
                    Mode mode722 = mode6;
                    mode6 = mode5;
                    c2 = c;
                    mode2 = mode722;
                } else {
                    if (i4 != 900) {
                        if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            switch (i4) {
                                case 27:
                                    mode3 = Mode.ALPHA_SHIFT;
                                    Mode mode8 = mode3;
                                    mode6 = mode5;
                                    mode5 = mode8;
                                    break;
                                case 28:
                                    mode4 = Mode.MIXED;
                                    break;
                                case 29:
                                    mode3 = Mode.PUNCT_SHIFT;
                                    Mode mode82 = mode3;
                                    mode6 = mode5;
                                    mode5 = mode82;
                                    break;
                            }
                            mode2 = mode6;
                            mode6 = mode5;
                            c2 = c3;
                        } else {
                            eCIStringBuilder.append((char) iArr2[i3]);
                        }
                        c3 = 0;
                        mode2 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    } else {
                        mode4 = Mode.ALPHA;
                    }
                    c3 = 0;
                    mode5 = mode4;
                    mode2 = mode6;
                    mode6 = mode5;
                    c2 = c3;
                }
            } else if (i4 < 26) {
                i2 = i4 + 65;
                c = (char) i2;
                Mode mode7222 = mode6;
                mode6 = mode5;
                c2 = c;
                mode2 = mode7222;
            } else {
                if (i4 != 900) {
                    if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                        switch (i4) {
                            case 27:
                                mode4 = Mode.LOWER;
                                break;
                            case 28:
                                mode4 = Mode.MIXED;
                                break;
                            case 29:
                                mode6 = mode5;
                                mode5 = Mode.PUNCT_SHIFT;
                        }
                        mode2 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    } else {
                        eCIStringBuilder.append((char) iArr2[i3]);
                    }
                    c3 = 0;
                    mode2 = mode6;
                    mode6 = mode5;
                    c2 = c3;
                } else {
                    mode4 = Mode.ALPHA;
                }
                c3 = 0;
                mode5 = mode4;
                mode2 = mode6;
                mode6 = mode5;
                c2 = c3;
            }
            if (c2 != 0) {
                eCIStringBuilder.append(c2);
            }
            i3++;
            mode5 = mode6;
            mode6 = mode2;
        }
        return mode4;
    }

    private static int byteCompaction(int i, int[] iArr, int i2, ECIStringBuilder eCIStringBuilder) throws FormatException {
        int i3;
        int i4;
        boolean z = false;
        while (i2 < iArr[0] && !z) {
            while (true) {
                i3 = iArr[0];
                if (i2 >= i3 || iArr[i2] != ECI_CHARSET) {
                    break;
                }
                eCIStringBuilder.appendECI(iArr[i2 + 1]);
                i2 += 2;
            }
            if (i2 >= i3 || iArr[i2] >= 900) {
                z = true;
            } else {
                long j = 0;
                int i5 = 0;
                while (true) {
                    i4 = i2 + 1;
                    j = (j * 900) + ((long) iArr[i2]);
                    i5++;
                    if (i5 >= 5 || i4 >= iArr[0] || iArr[i4] >= 900) {
                        break;
                    }
                    i2 = i4;
                }
                if (i5 != 5 || (i != BYTE_COMPACTION_MODE_LATCH_6 && (i4 >= iArr[0] || iArr[i4] >= 900))) {
                    i4 -= i5;
                    while (i4 < iArr[0] && !z) {
                        int i6 = i4 + 1;
                        int i7 = iArr[i4];
                        if (i7 < 900) {
                            eCIStringBuilder.append((byte) i7);
                            i4 = i6;
                        } else if (i7 == ECI_CHARSET) {
                            i4 += 2;
                            eCIStringBuilder.appendECI(iArr[i6]);
                        } else {
                            z = true;
                        }
                    }
                } else {
                    for (int i8 = 0; i8 < 6; i8++) {
                        eCIStringBuilder.append((byte) (j >> ((5 - i8) * 8)));
                    }
                }
                i2 = i4;
            }
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x003e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0007 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int numericCompaction(int[] iArr, int i, ECIStringBuilder eCIStringBuilder) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (true) {
            int i3 = iArr[0];
            if (i < i3 && !z) {
                int i4 = i + 1;
                int i5 = iArr[i];
                if (i4 == i3) {
                    z = true;
                }
                if (i5 < 900) {
                    iArr2[i2] = i5;
                    i2++;
                } else {
                    if (i5 != 900 && i5 != 901 && i5 != ECI_CHARSET && i5 != 928) {
                        switch (i5) {
                            case MACRO_PDF417_TERMINATOR /* 922 */:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                            case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                                z = true;
                                break;
                        }
                    } else {
                        z = true;
                    }
                    if (i2 % 15 != 0 || i5 == 902 || z) {
                        if (i2 <= 0) {
                            eCIStringBuilder.append(decodeBase900toBase10(iArr2, i2));
                            i2 = 0;
                        }
                    }
                }
                i = i4;
                if (i2 % 15 != 0) {
                }
                if (i2 <= 0) {
                }
            }
        }
        return i;
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigIntegerAdd = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigIntegerAdd = bigIntegerAdd.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String string = bigIntegerAdd.toString();
        if (string.charAt(0) != '1') {
            throw FormatException.getFormatInstance();
        }
        return string.substring(1);
    }
}
