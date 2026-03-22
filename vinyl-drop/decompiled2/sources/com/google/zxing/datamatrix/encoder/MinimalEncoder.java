package com.google.zxing.datamatrix.encoder;

import androidx.core.view.InputDeviceCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.api.ClientProto;
import com.google.common.base.Ascii;
import com.google.mlkit.common.MlKitException;
import com.google.zxing.common.MinimalECIInput;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes3.dex */
public final class MinimalEncoder {
    static final char[] C40_SHIFT2_CHARS = {'!', Typography.quote, '#', Typography.dollar, '%', Typography.amp, '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', Typography.less, '=', Typography.greater, '?', '@', '[', '\\', ']', '^', '_'};

    enum Mode {
        ASCII,
        C40,
        TEXT,
        X12,
        EDF,
        B256
    }

    static boolean isExtendedASCII(char c, int i) {
        return c != i && c >= 128 && c <= 255;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isInC40Shift1Set(char c) {
        return c <= 31;
    }

    private MinimalEncoder() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isInC40Shift2Set(char c, int i) {
        for (char c2 : C40_SHIFT2_CHARS) {
            if (c2 == c) {
                return true;
            }
        }
        return c == i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isInTextShift1Set(char c) {
        return isInC40Shift1Set(c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isInTextShift2Set(char c, int i) {
        return isInC40Shift2Set(c, i);
    }

    public static String encodeHighLevel(String str) {
        return encodeHighLevel(str, null, -1, SymbolShapeHint.FORCE_NONE);
    }

    public static String encodeHighLevel(String str, Charset charset, int i, SymbolShapeHint symbolShapeHint) {
        int i2;
        if (str.startsWith("[)>\u001e05\u001d") && str.endsWith("\u001e\u0004")) {
            str = str.substring("[)>\u001e05\u001d".length(), str.length() - 2);
            i2 = 5;
        } else if (str.startsWith("[)>\u001e06\u001d") && str.endsWith("\u001e\u0004")) {
            str = str.substring("[)>\u001e06\u001d".length(), str.length() - 2);
            i2 = 6;
        } else {
            i2 = 0;
        }
        return new String(encode(str, charset, i, symbolShapeHint, i2), StandardCharsets.ISO_8859_1);
    }

    static byte[] encode(String str, Charset charset, int i, SymbolShapeHint symbolShapeHint, int i2) {
        return encodeMinimally(new Input(str, charset, i, symbolShapeHint, i2, null)).getBytes();
    }

    static void addEdge(Edge[][] edgeArr, Edge edge) {
        int i = edge.fromPosition + edge.characterLength;
        if (edgeArr[i][edge.getEndMode().ordinal()] == null || edgeArr[i][edge.getEndMode().ordinal()].cachedTotalSize > edge.cachedTotalSize) {
            edgeArr[i][edge.getEndMode().ordinal()] = edge;
        }
    }

    static int getNumberOfC40Words(Input input, int i, boolean z, int[] iArr) {
        int i2 = 0;
        for (int i3 = i; i3 < input.length(); i3++) {
            if (input.isECI(i3)) {
                iArr[0] = 0;
                return 0;
            }
            char cCharAt = input.charAt(i3);
            if ((z && HighLevelEncoder.isNativeC40(cCharAt)) || (!z && HighLevelEncoder.isNativeText(cCharAt))) {
                i2++;
            } else if (isExtendedASCII(cCharAt, input.getFNC1Character())) {
                int i4 = cCharAt & 255;
                i2 = (i4 < 128 || (!(z && HighLevelEncoder.isNativeC40((char) (i4 + (-128)))) && (z || !HighLevelEncoder.isNativeText((char) (i4 + (-128)))))) ? i2 + 4 : i2 + 3;
            } else {
                i2 += 2;
            }
            if (i2 % 3 == 0 || ((i2 - 2) % 3 == 0 && i3 + 1 == input.length())) {
                iArr[0] = (i3 - i) + 1;
                return (int) Math.ceil(((double) i2) / 3.0d);
            }
        }
        iArr[0] = 0;
        return 0;
    }

    static void addEdges(Input input, Edge[][] edgeArr, int i, Edge edge) {
        if (input.isECI(i)) {
            addEdge(edgeArr, new Edge(input, Mode.ASCII, i, 1, edge, null));
            return;
        }
        char cCharAt = input.charAt(i);
        int i2 = 0;
        if (edge == null || edge.getEndMode() != Mode.EDF) {
            if (HighLevelEncoder.isDigit(cCharAt) && input.haveNCharacters(i, 2) && HighLevelEncoder.isDigit(input.charAt(i + 1))) {
                addEdge(edgeArr, new Edge(input, Mode.ASCII, i, 2, edge, null));
            } else {
                addEdge(edgeArr, new Edge(input, Mode.ASCII, i, 1, edge, null));
            }
            Mode[] modeArr = {Mode.C40, Mode.TEXT};
            for (int i3 = 0; i3 < 2; i3++) {
                Mode mode = modeArr[i3];
                int[] iArr = new int[1];
                if (getNumberOfC40Words(input, i, mode == Mode.C40, iArr) > 0) {
                    addEdge(edgeArr, new Edge(input, mode, i, iArr[0], edge, null));
                }
            }
            if (input.haveNCharacters(i, 3) && HighLevelEncoder.isNativeX12(input.charAt(i)) && HighLevelEncoder.isNativeX12(input.charAt(i + 1)) && HighLevelEncoder.isNativeX12(input.charAt(i + 2))) {
                addEdge(edgeArr, new Edge(input, Mode.X12, i, 3, edge, null));
            }
            addEdge(edgeArr, new Edge(input, Mode.B256, i, 1, edge, null));
        }
        while (i2 < 3) {
            int i4 = i + i2;
            if (!input.haveNCharacters(i4, 1) || !HighLevelEncoder.isNativeEDIFACT(input.charAt(i4))) {
                break;
            }
            int i5 = i2 + 1;
            addEdge(edgeArr, new Edge(input, Mode.EDF, i, i5, edge, null));
            i2 = i5;
        }
        if (i2 == 3 && input.haveNCharacters(i, 4) && HighLevelEncoder.isNativeEDIFACT(input.charAt(i + 3))) {
            addEdge(edgeArr, new Edge(input, Mode.EDF, i, 4, edge, null));
        }
    }

    static Result encodeMinimally(Input input) {
        int length = input.length();
        int i = 0;
        Edge[][] edgeArr = (Edge[][]) Array.newInstance((Class<?>) Edge.class, length + 1, 6);
        addEdges(input, edgeArr, 0, null);
        for (int i2 = 1; i2 <= length; i2++) {
            for (int i3 = 0; i3 < 6; i3++) {
                Edge edge = edgeArr[i2][i3];
                if (edge != null && i2 < length) {
                    addEdges(input, edgeArr, i2, edge);
                }
            }
            for (int i4 = 0; i4 < 6; i4++) {
                edgeArr[i2 - 1][i4] = null;
            }
        }
        int i5 = -1;
        int i6 = Integer.MAX_VALUE;
        while (i < 6) {
            Edge edge2 = edgeArr[length][i];
            if (edge2 != null) {
                int i7 = (i < 1 || i > 3) ? edge2.cachedTotalSize : edge2.cachedTotalSize + 1;
                if (i7 < i6) {
                    i5 = i;
                    i6 = i7;
                }
            }
            i++;
        }
        if (i5 < 0) {
            throw new IllegalStateException("Failed to encode \"" + input + "\"");
        }
        return new Result(edgeArr[length][i5]);
    }

    private static final class Edge {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final int cachedTotalSize;
        private final int characterLength;
        private final int fromPosition;
        private final Input input;
        private final Mode mode;
        private final Edge previous;
        private static final int[] allCodewordCapacities = {3, 5, 8, 10, 12, 16, 18, 22, 30, 32, 36, 44, 49, 62, 86, 114, 144, 174, MlKitException.CODE_SCANNER_TASK_IN_PROGRESS, 280, 368, 456, 576, 696, 816, ClientProto.OAUTH_SCOPES_FIELD_NUMBER, 1304, 1558};
        private static final int[] squareCodewordCapacities = {3, 5, 8, 12, 18, 22, 30, 36, 44, 62, 86, 114, 144, 174, MlKitException.CODE_SCANNER_TASK_IN_PROGRESS, 280, 368, 456, 576, 696, 816, ClientProto.OAUTH_SCOPES_FIELD_NUMBER, 1304, 1558};
        private static final int[] rectangularCodewordCapacities = {5, 10, 16, 33, 32, 49};

        private static int getC40Value(boolean z, int i, char c, int i2) {
            if (c == i2) {
                return 27;
            }
            if (z) {
                if (c <= 31) {
                    return c;
                }
                if (c == ' ') {
                    return 3;
                }
                return c <= '/' ? c - '!' : c <= '9' ? c - ',' : c <= '@' ? c - '+' : c <= 'Z' ? c - '3' : c <= '_' ? c - 'E' : c <= 127 ? c - '`' : c;
            }
            if (c == 0) {
                return 0;
            }
            if (i == 0 && c <= 3) {
                return c - 1;
            }
            if (i == 1 && c <= 31) {
                return c;
            }
            if (c == ' ') {
                return 3;
            }
            if (c >= '!' && c <= '/') {
                return c - '!';
            }
            if (c >= '0' && c <= '9') {
                return c - ',';
            }
            if (c >= ':' && c <= '@') {
                return c - '+';
            }
            if (c >= 'A' && c <= 'Z') {
                return c - '@';
            }
            if (c >= '[' && c <= '_') {
                return c - 'E';
            }
            if (c == '`') {
                return 0;
            }
            return (c < 'a' || c > 'z') ? (c < '{' || c > 127) ? c : c - '`' : c - 'S';
        }

        private static int getX12Value(char c) {
            if (c == '\r') {
                return 0;
            }
            if (c == '*') {
                return 1;
            }
            if (c == '>') {
                return 2;
            }
            if (c == ' ') {
                return 3;
            }
            return (c < '0' || c > '9') ? (c < 'A' || c > 'Z') ? c : c - '3' : c - ',';
        }

        /* synthetic */ Edge(Input input, Mode mode, int i, int i2, Edge edge, AnonymousClass1 anonymousClass1) {
            this(input, mode, i, i2, edge);
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x0054 A[PHI: r10
          0x0054: PHI (r10v12 int) = (r10v10 int), (r10v10 int), (r10v10 int), (r10v18 int), (r10v18 int), (r10v18 int) binds: [B:64:0x0099, B:66:0x009d, B:68:0x00a1, B:29:0x004a, B:31:0x004e, B:33:0x0052] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:83:0x00cb A[PHI: r10
          0x00cb: PHI (r10v7 int) = (r10v4 int), (r10v4 int), (r10v4 int), (r10v10 int), (r10v10 int), (r10v18 int) binds: [B:78:0x00c1, B:80:0x00c5, B:82:0x00c9, B:58:0x008e, B:60:0x0092, B:26:0x0044] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private Edge(Input input, Mode mode, int i, int i2, Edge edge) {
            this.input = input;
            this.mode = mode;
            this.fromPosition = i;
            this.characterLength = i2;
            this.previous = edge;
            int numberOfC40Words = edge != null ? edge.cachedTotalSize : 0;
            Mode previousMode = getPreviousMode();
            int iOrdinal = mode.ordinal();
            if (iOrdinal == 0) {
                numberOfC40Words = (input.isECI(i) || MinimalEncoder.isExtendedASCII(input.charAt(i), input.getFNC1Character())) ? numberOfC40Words + 2 : numberOfC40Words + 1;
                if (previousMode == Mode.C40 || previousMode == Mode.TEXT || previousMode == Mode.X12) {
                }
            } else if (iOrdinal == 1 || iOrdinal == 2 || iOrdinal == 3) {
                if (mode == Mode.X12) {
                    numberOfC40Words += 2;
                } else {
                    numberOfC40Words += MinimalEncoder.getNumberOfC40Words(input, i, mode == Mode.C40, new int[1]) * 2;
                }
                if (previousMode == Mode.ASCII || previousMode == Mode.B256) {
                    numberOfC40Words++;
                } else if (previousMode != mode && (previousMode == Mode.C40 || previousMode == Mode.TEXT || previousMode == Mode.X12)) {
                    numberOfC40Words += 2;
                }
            } else if (iOrdinal == 4) {
                int i3 = numberOfC40Words + 3;
                if (previousMode == Mode.ASCII || previousMode == Mode.B256) {
                    numberOfC40Words += 4;
                } else {
                    numberOfC40Words = (previousMode == Mode.C40 || previousMode == Mode.TEXT || previousMode == Mode.X12) ? numberOfC40Words + 5 : i3;
                }
            } else if (iOrdinal == 5) {
                numberOfC40Words = (previousMode == Mode.B256 && getB256Size() != 250) ? numberOfC40Words + 1 : numberOfC40Words + 2;
                if (previousMode != Mode.ASCII) {
                    if (previousMode == Mode.C40 || previousMode == Mode.TEXT || previousMode == Mode.X12) {
                    }
                }
            }
            this.cachedTotalSize = numberOfC40Words;
        }

        int getB256Size() {
            int i = 0;
            for (Edge edge = this; edge != null && edge.mode == Mode.B256 && i <= 250; edge = edge.previous) {
                i++;
            }
            return i;
        }

        Mode getPreviousStartMode() {
            Edge edge = this.previous;
            return edge == null ? Mode.ASCII : edge.mode;
        }

        Mode getPreviousMode() {
            Edge edge = this.previous;
            return edge == null ? Mode.ASCII : edge.getEndMode();
        }

        Mode getEndMode() {
            if (this.mode == Mode.EDF) {
                if (this.characterLength < 4) {
                    return Mode.ASCII;
                }
                int lastASCII = getLastASCII();
                if (lastASCII > 0 && getCodewordsRemaining(this.cachedTotalSize + lastASCII) <= 2 - lastASCII) {
                    return Mode.ASCII;
                }
            }
            if (this.mode == Mode.C40 || this.mode == Mode.TEXT || this.mode == Mode.X12) {
                if (this.fromPosition + this.characterLength >= this.input.length() && getCodewordsRemaining(this.cachedTotalSize) == 0) {
                    return Mode.ASCII;
                }
                if (getLastASCII() == 1 && getCodewordsRemaining(this.cachedTotalSize + 1) == 0) {
                    return Mode.ASCII;
                }
            }
            return this.mode;
        }

        Mode getMode() {
            return this.mode;
        }

        int getLastASCII() {
            int length = this.input.length();
            int i = this.fromPosition + this.characterLength;
            int i2 = length - i;
            if (i2 <= 4 && i < length) {
                if (i2 == 1) {
                    return MinimalEncoder.isExtendedASCII(this.input.charAt(i), this.input.getFNC1Character()) ? 0 : 1;
                }
                if (i2 == 2) {
                    if (!MinimalEncoder.isExtendedASCII(this.input.charAt(i), this.input.getFNC1Character())) {
                        int i3 = i + 1;
                        if (!MinimalEncoder.isExtendedASCII(this.input.charAt(i3), this.input.getFNC1Character())) {
                            return (HighLevelEncoder.isDigit(this.input.charAt(i)) && HighLevelEncoder.isDigit(this.input.charAt(i3))) ? 1 : 2;
                        }
                    }
                    return 0;
                }
                Input input = this.input;
                if (i2 == 3) {
                    if (HighLevelEncoder.isDigit(input.charAt(i)) && HighLevelEncoder.isDigit(this.input.charAt(i + 1)) && !MinimalEncoder.isExtendedASCII(this.input.charAt(i + 2), this.input.getFNC1Character())) {
                        return 2;
                    }
                    return (HighLevelEncoder.isDigit(this.input.charAt(i + 1)) && HighLevelEncoder.isDigit(this.input.charAt(i + 2)) && !MinimalEncoder.isExtendedASCII(this.input.charAt(i), this.input.getFNC1Character())) ? 2 : 0;
                }
                if (HighLevelEncoder.isDigit(input.charAt(i)) && HighLevelEncoder.isDigit(this.input.charAt(i + 1)) && HighLevelEncoder.isDigit(this.input.charAt(i + 2)) && HighLevelEncoder.isDigit(this.input.charAt(i + 3))) {
                    return 2;
                }
            }
            return 0;
        }

        int getMinSymbolSize(int i) {
            int i2 = AnonymousClass1.$SwitchMap$com$google$zxing$datamatrix$encoder$SymbolShapeHint[this.input.getShapeHint().ordinal()];
            if (i2 == 1) {
                for (int i3 : squareCodewordCapacities) {
                    if (i3 >= i) {
                        return i3;
                    }
                }
            } else if (i2 == 2) {
                for (int i4 : rectangularCodewordCapacities) {
                    if (i4 >= i) {
                        return i4;
                    }
                }
            }
            for (int i5 : allCodewordCapacities) {
                if (i5 >= i) {
                    return i5;
                }
            }
            int[] iArr = allCodewordCapacities;
            return iArr[iArr.length - 1];
        }

        int getCodewordsRemaining(int i) {
            return getMinSymbolSize(i) - i;
        }

        static byte[] getBytes(int i) {
            return new byte[]{(byte) i};
        }

        static byte[] getBytes(int i, int i2) {
            return new byte[]{(byte) i, (byte) i2};
        }

        static void setC40Word(byte[] bArr, int i, int i2, int i3, int i4) {
            int i5 = ((i2 & 255) * 1600) + ((i3 & 255) * 40) + (i4 & 255) + 1;
            bArr[i] = (byte) (i5 / 256);
            bArr[i + 1] = (byte) (i5 % 256);
        }

        byte[] getX12Words() {
            int i = (this.characterLength / 3) * 2;
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < i; i2 += 2) {
                int i3 = (i2 / 2) * 3;
                setC40Word(bArr, i2, getX12Value(this.input.charAt(this.fromPosition + i3)), getX12Value(this.input.charAt(this.fromPosition + i3 + 1)), getX12Value(this.input.charAt(this.fromPosition + i3 + 2)));
            }
            return bArr;
        }

        static int getShiftValue(char c, boolean z, int i) {
            if (z && MinimalEncoder.isInC40Shift1Set(c)) {
                return 0;
            }
            if (!z && MinimalEncoder.isInTextShift1Set(c)) {
                return 0;
            }
            if (z && MinimalEncoder.isInC40Shift2Set(c, i)) {
                return 1;
            }
            return (z || !MinimalEncoder.isInTextShift2Set(c, i)) ? 2 : 1;
        }

        byte[] getC40Words(boolean z, int i) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.characterLength; i2++) {
                char cCharAt = this.input.charAt(this.fromPosition + i2);
                if ((z && HighLevelEncoder.isNativeC40(cCharAt)) || (!z && HighLevelEncoder.isNativeText(cCharAt))) {
                    arrayList.add(Byte.valueOf((byte) getC40Value(z, 0, cCharAt, i)));
                } else if (!MinimalEncoder.isExtendedASCII(cCharAt, i)) {
                    int shiftValue = getShiftValue(cCharAt, z, i);
                    arrayList.add(Byte.valueOf((byte) shiftValue));
                    arrayList.add(Byte.valueOf((byte) getC40Value(z, shiftValue, cCharAt, i)));
                } else {
                    char c = (char) ((cCharAt & 255) - 128);
                    if ((z && HighLevelEncoder.isNativeC40(c)) || (!z && HighLevelEncoder.isNativeText(c))) {
                        arrayList.add((byte) 1);
                        arrayList.add(Byte.valueOf(Ascii.RS));
                        arrayList.add(Byte.valueOf((byte) getC40Value(z, 0, c, i)));
                    } else {
                        arrayList.add((byte) 1);
                        arrayList.add(Byte.valueOf(Ascii.RS));
                        int shiftValue2 = getShiftValue(c, z, i);
                        arrayList.add(Byte.valueOf((byte) shiftValue2));
                        arrayList.add(Byte.valueOf((byte) getC40Value(z, shiftValue2, c, i)));
                    }
                }
            }
            if (arrayList.size() % 3 != 0) {
                arrayList.add((byte) 0);
            }
            byte[] bArr = new byte[(arrayList.size() / 3) * 2];
            int i3 = 0;
            for (int i4 = 0; i4 < arrayList.size(); i4 += 3) {
                setC40Word(bArr, i3, ((Byte) arrayList.get(i4)).byteValue() & 255, ((Byte) arrayList.get(i4 + 1)).byteValue() & 255, ((Byte) arrayList.get(i4 + 2)).byteValue() & 255);
                i3 += 2;
            }
            return bArr;
        }

        byte[] getEDFBytes() {
            int iCeil = (int) Math.ceil(((double) this.characterLength) / 4.0d);
            byte[] bArr = new byte[iCeil * 3];
            int i = this.fromPosition;
            int iMin = Math.min((this.characterLength + i) - 1, this.input.length() - 1);
            for (int i2 = 0; i2 < iCeil; i2 += 3) {
                int[] iArr = new int[4];
                for (int i3 = 0; i3 < 4; i3++) {
                    if (i <= iMin) {
                        iArr[i3] = this.input.charAt(i) & '?';
                        i++;
                    } else {
                        iArr[i3] = i == iMin + 1 ? 31 : 0;
                    }
                }
                int i4 = (iArr[0] << 18) | (iArr[1] << 12) | (iArr[2] << 6) | iArr[3];
                bArr[i2] = (byte) ((i4 >> 16) & 255);
                bArr[i2 + 1] = (byte) ((i4 >> 8) & 255);
                bArr[i2 + 2] = (byte) (i4 & 255);
            }
            return bArr;
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x005f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        byte[] getLatchBytes() {
            int iOrdinal = getPreviousMode().ordinal();
            if (iOrdinal == 0) {
                int iOrdinal2 = this.mode.ordinal();
                if (iOrdinal2 == 1) {
                    return getBytes(230);
                }
                if (iOrdinal2 == 2) {
                    return getBytes(239);
                }
                if (iOrdinal2 == 3) {
                    return getBytes(238);
                }
                if (iOrdinal2 == 4) {
                    return getBytes(240);
                }
                if (iOrdinal2 == 5) {
                    return getBytes(231);
                }
            } else if (iOrdinal == 1 || iOrdinal == 2 || iOrdinal == 3) {
                if (this.mode != getPreviousMode()) {
                    int iOrdinal3 = this.mode.ordinal();
                    if (iOrdinal3 == 0) {
                        return getBytes(254);
                    }
                    if (iOrdinal3 == 1) {
                        return getBytes(254, 230);
                    }
                    if (iOrdinal3 == 2) {
                        return getBytes(254, 239);
                    }
                    if (iOrdinal3 == 3) {
                        return getBytes(254, 238);
                    }
                    if (iOrdinal3 == 4) {
                        return getBytes(254, 240);
                    }
                    if (iOrdinal3 == 5) {
                        return getBytes(254, 231);
                    }
                }
            } else if (iOrdinal != 4 && iOrdinal == 5) {
            }
            return new byte[0];
        }

        byte[] getDataBytes() {
            int iOrdinal = this.mode.ordinal();
            if (iOrdinal != 0) {
                if (iOrdinal == 1) {
                    return getC40Words(true, this.input.getFNC1Character());
                }
                if (iOrdinal == 2) {
                    return getC40Words(false, this.input.getFNC1Character());
                }
                if (iOrdinal == 3) {
                    return getX12Words();
                }
                if (iOrdinal == 4) {
                    return getEDFBytes();
                }
                if (iOrdinal == 5) {
                    return getBytes(this.input.charAt(this.fromPosition));
                }
                return new byte[0];
            }
            boolean zIsECI = this.input.isECI(this.fromPosition);
            Input input = this.input;
            if (zIsECI) {
                return getBytes(241, input.getECIValue(this.fromPosition) + 1);
            }
            if (MinimalEncoder.isExtendedASCII(input.charAt(this.fromPosition), this.input.getFNC1Character())) {
                return getBytes(235, this.input.charAt(this.fromPosition) - 127);
            }
            int i = this.characterLength;
            Input input2 = this.input;
            if (i == 2) {
                return getBytes(((input2.charAt(this.fromPosition) - '0') * 10) + this.input.charAt(this.fromPosition + 1) + 82);
            }
            if (input2.isFNC1(this.fromPosition)) {
                return getBytes(232);
            }
            return getBytes(this.input.charAt(this.fromPosition) + 1);
        }
    }

    /* JADX INFO: renamed from: com.google.zxing.datamatrix.encoder.MinimalEncoder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$datamatrix$encoder$SymbolShapeHint;

        static {
            int[] iArr = new int[SymbolShapeHint.values().length];
            $SwitchMap$com$google$zxing$datamatrix$encoder$SymbolShapeHint = iArr;
            try {
                iArr[SymbolShapeHint.FORCE_SQUARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$datamatrix$encoder$SymbolShapeHint[SymbolShapeHint.FORCE_RECTANGLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static final class Result {
        private final byte[] bytes;

        Result(Edge edge) {
            int i;
            Input input = edge.input;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            int i2 = 0;
            int iPrepend = ((edge.mode == Mode.C40 || edge.mode == Mode.TEXT || edge.mode == Mode.X12) && edge.getEndMode() != Mode.ASCII) ? prepend(Edge.getBytes(254), arrayList) : 0;
            for (Edge edge2 = edge; edge2 != null; edge2 = edge2.previous) {
                iPrepend += prepend(edge2.getDataBytes(), arrayList);
                if (edge2.previous == null || edge2.getPreviousStartMode() != edge2.getMode()) {
                    if (edge2.getMode() == Mode.B256) {
                        if (iPrepend <= 249) {
                            arrayList.add(0, Byte.valueOf((byte) iPrepend));
                            i = iPrepend + 1;
                        } else {
                            arrayList.add(0, Byte.valueOf((byte) (iPrepend % ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION)));
                            arrayList.add(0, Byte.valueOf((byte) ((iPrepend / ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + 249)));
                            i = iPrepend + 2;
                        }
                        arrayList2.add(Integer.valueOf(arrayList.size()));
                        arrayList3.add(Integer.valueOf(i));
                    }
                    prepend(edge2.getLatchBytes(), arrayList);
                    iPrepend = 0;
                }
            }
            if (input.getMacroId() == 5) {
                prepend(Edge.getBytes(236), arrayList);
            } else if (input.getMacroId() == 6) {
                prepend(Edge.getBytes(237), arrayList);
            }
            if (input.getFNC1Character() > 0) {
                prepend(Edge.getBytes(232), arrayList);
            }
            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                applyRandomPattern(arrayList, arrayList.size() - ((Integer) arrayList2.get(i3)).intValue(), ((Integer) arrayList3.get(i3)).intValue());
            }
            int minSymbolSize = edge.getMinSymbolSize(arrayList.size());
            if (arrayList.size() < minSymbolSize) {
                arrayList.add((byte) -127);
            }
            while (arrayList.size() < minSymbolSize) {
                arrayList.add(Byte.valueOf((byte) randomize253State(arrayList.size() + 1)));
            }
            this.bytes = new byte[arrayList.size()];
            while (true) {
                byte[] bArr = this.bytes;
                if (i2 >= bArr.length) {
                    return;
                }
                bArr[i2] = ((Byte) arrayList.get(i2)).byteValue();
                i2++;
            }
        }

        static int prepend(byte[] bArr, List<Byte> list) {
            for (int length = bArr.length - 1; length >= 0; length--) {
                list.add(0, Byte.valueOf(bArr[length]));
            }
            return bArr.length;
        }

        private static int randomize253State(int i) {
            int i2 = (i * 149) % 253;
            int i3 = i2 + 130;
            return i3 <= 254 ? i3 : i2 - 124;
        }

        static void applyRandomPattern(List<Byte> list, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = i + i3;
                int iByteValue = (list.get(i4).byteValue() & 255) + (((i4 + 1) * 149) % 255) + 1;
                if (iByteValue > 255) {
                    iByteValue += InputDeviceCompat.SOURCE_ANY;
                }
                list.set(i4, Byte.valueOf((byte) iByteValue));
            }
        }

        public byte[] getBytes() {
            return this.bytes;
        }
    }

    private static final class Input extends MinimalECIInput {
        private final int macroId;
        private final SymbolShapeHint shape;

        /* synthetic */ Input(String str, Charset charset, int i, SymbolShapeHint symbolShapeHint, int i2, AnonymousClass1 anonymousClass1) {
            this(str, charset, i, symbolShapeHint, i2);
        }

        private Input(String str, Charset charset, int i, SymbolShapeHint symbolShapeHint, int i2) {
            super(str, charset, i);
            this.shape = symbolShapeHint;
            this.macroId = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getMacroId() {
            return this.macroId;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public SymbolShapeHint getShapeHint() {
            return this.shape;
        }
    }
}
