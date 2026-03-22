package com.google.android.gms.common.server.response;

import android.util.Log;
import androidx.camera.video.AudioStats;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import kotlin.text.Typography;
import okio.internal.Buffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaf = {'u', 'l', 'l'};
    private static final char[] zag = {'r', 'u', 'e'};
    private static final char[] zah = {'r', 'u', 'e', Typography.quote};
    private static final char[] zai = {'a', 'l', 's', 'e'};
    private static final char[] zaj = {'a', 'l', 's', 'e', Typography.quote};
    private static final char[] zak = {'\n'};
    private static final zai zam = new zaa();
    private static final zai zan = new zab();
    private static final zai zao = new zac();
    private static final zai zap = new zad();
    private static final zai zaq = new zae();
    private static final zai zar = new zaf();
    private static final zai zas = new zag();
    private static final zai zat = new zah();
    private final char[] zaa = new char[1];
    private final char[] zab = new char[32];
    private final char[] zac = new char[1024];
    private final StringBuilder zad = new StringBuilder(32);
    private final StringBuilder zae = new StringBuilder(1024);
    private final Stack zal = new Stack();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.9.0 */
    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002a, code lost:
    
        throw new com.google.android.gms.common.server.response.FastParser.ParseException("Unexpected control character while reading string");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.lang.String zaA(java.io.BufferedReader r8, char[] r9, java.lang.StringBuilder r10, char[] r11) throws com.google.android.gms.common.server.response.FastParser.ParseException, java.io.IOException {
        /*
            r0 = 0
            r10.setLength(r0)
            int r1 = r9.length
            r8.mark(r1)
            r1 = r0
            r2 = r1
        La:
            int r3 = r8.read(r9)
            r4 = -1
            if (r3 == r4) goto L60
            r4 = r0
        L12:
            if (r4 >= r3) goto L58
            char r5 = r9[r4]
            boolean r6 = java.lang.Character.isISOControl(r5)
            if (r6 == 0) goto L2b
            if (r11 == 0) goto L23
            char r6 = r11[r0]
            if (r6 != r5) goto L23
            goto L2b
        L23:
            com.google.android.gms.common.server.response.FastParser$ParseException r8 = new com.google.android.gms.common.server.response.FastParser$ParseException
            java.lang.String r9 = "Unexpected control character while reading string"
            r8.<init>(r9)
            throw r8
        L2b:
            int r6 = r4 + 1
            r7 = 34
            if (r5 != r7) goto L4d
            if (r1 != 0) goto L55
            r10.append(r9, r0, r4)
            r8.reset()
            long r0 = (long) r6
            r8.skip(r0)
            if (r2 == 0) goto L48
            java.lang.String r8 = r10.toString()
            java.lang.String r8 = com.google.android.gms.common.util.JsonUtils.unescapeString(r8)
            return r8
        L48:
            java.lang.String r8 = r10.toString()
            return r8
        L4d:
            r4 = 92
            if (r5 != r4) goto L55
            r1 = r1 ^ 1
            r2 = 1
            goto L56
        L55:
            r1 = r0
        L56:
            r4 = r6
            goto L12
        L58:
            r10.append(r9, r0, r3)
            int r3 = r9.length
            r8.mark(r3)
            goto La
        L60:
            com.google.android.gms.common.server.response.FastParser$ParseException r8 = new com.google.android.gms.common.server.response.FastParser$ParseException
            java.lang.String r9 = "Unexpected EOF while parsing string"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.zaA(java.io.BufferedReader, char[], java.lang.StringBuilder, char[]):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean zai(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        HashMap map;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String strZaj = zaj(bufferedReader);
        boolean z = true;
        if (strZaj == null) {
            zaz(1);
            return false;
        }
        while (strZaj != null) {
            FastJsonResponse.Field<?, ?> field = fieldMappings.get(strZaj);
            if (field == null) {
                strZaj = zak(bufferedReader);
            } else {
                Stack stack = this.zal;
                stack.push(4);
                char c = '}';
                switch (field.zaa) {
                    case 0:
                        if (!field.zab) {
                            fastJsonResponse.zaa(field, zab(bufferedReader));
                        } else {
                            fastJsonResponse.zab(field, zam(bufferedReader, zam));
                        }
                        break;
                    case 1:
                        if (!field.zab) {
                            fastJsonResponse.zac(field, zad(bufferedReader));
                        } else {
                            fastJsonResponse.zad(field, zam(bufferedReader, zas));
                        }
                        break;
                    case 2:
                        if (!field.zab) {
                            fastJsonResponse.zae(field, zac(bufferedReader));
                        } else {
                            fastJsonResponse.zaf(field, zam(bufferedReader, zan));
                        }
                        break;
                    case 3:
                        if (!field.zab) {
                            fastJsonResponse.zag(field, zaf(bufferedReader));
                        } else {
                            fastJsonResponse.zah(field, zam(bufferedReader, zao));
                        }
                        break;
                    case 4:
                        if (!field.zab) {
                            fastJsonResponse.zai(field, zag(bufferedReader));
                        } else {
                            fastJsonResponse.zaj(field, zam(bufferedReader, zap));
                        }
                        break;
                    case 5:
                        if (!field.zab) {
                            fastJsonResponse.zak(field, zah(bufferedReader));
                        } else {
                            fastJsonResponse.zal(field, zam(bufferedReader, zat));
                        }
                        break;
                    case 6:
                        if (!field.zab) {
                            fastJsonResponse.zam(field, zar(bufferedReader, false));
                        } else {
                            fastJsonResponse.zan(field, zam(bufferedReader, zaq));
                        }
                        break;
                    case 7:
                        if (!field.zab) {
                            fastJsonResponse.zao(field, zaa(bufferedReader));
                        } else {
                            fastJsonResponse.zap(field, zam(bufferedReader, zar));
                        }
                        break;
                    case 8:
                        fastJsonResponse.zaq(field, Base64Utils.decode(zan(bufferedReader, this.zac, this.zae, zak)));
                        break;
                    case 9:
                        fastJsonResponse.zaq(field, Base64Utils.decodeUrlSafe(zan(bufferedReader, this.zac, this.zae, zak)));
                        break;
                    case 10:
                        char cZaw = zaw(bufferedReader);
                        if (cZaw == 'n') {
                            zay(bufferedReader, zaf);
                            map = null;
                        } else {
                            if (cZaw != '{') {
                                throw new ParseException("Expected start of a map object");
                            }
                            stack.push(1);
                            map = new HashMap();
                            while (true) {
                                char cZaw2 = zaw(bufferedReader);
                                if (cZaw2 == 0) {
                                    throw new ParseException("Unexpected EOF");
                                }
                                if (cZaw2 == '\"') {
                                    char[] cArr = this.zab;
                                    StringBuilder sb = this.zad;
                                    String strZaA = zaA(bufferedReader, cArr, sb, null);
                                    if (zaw(bufferedReader) != ':') {
                                        String.valueOf(strZaA);
                                        throw new ParseException("No map value found for key ".concat(String.valueOf(strZaA)));
                                    }
                                    if (zaw(bufferedReader) != '\"') {
                                        String.valueOf(strZaA);
                                        throw new ParseException("Expected String value for key ".concat(String.valueOf(strZaA)));
                                    }
                                    map.put(strZaA, zaA(bufferedReader, cArr, sb, null));
                                    char cZaw3 = zaw(bufferedReader);
                                    if (cZaw3 == ',') {
                                        z = true;
                                        c = '}';
                                    } else {
                                        if (cZaw3 != '}') {
                                            StringBuilder sb2 = new StringBuilder(String.valueOf(cZaw3).length() + 47);
                                            sb2.append("Unexpected character while parsing string map: ");
                                            sb2.append(cZaw3);
                                            throw new ParseException(sb2.toString());
                                        }
                                        zaz(1);
                                    }
                                } else if (cZaw2 == c) {
                                    zaz(z ? 1 : 0);
                                }
                            }
                        }
                        fastJsonResponse.zar(field, map);
                        break;
                    case 11:
                        if (field.zab) {
                            char cZaw4 = zaw(bufferedReader);
                            if (cZaw4 == 'n') {
                                zay(bufferedReader, zaf);
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, null);
                            } else {
                                stack.push(5);
                                if (cZaw4 != '[') {
                                    throw new ParseException("Expected array start");
                                }
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, zav(bufferedReader, field));
                            }
                        } else {
                            char cZaw5 = zaw(bufferedReader);
                            if (cZaw5 == 'n') {
                                zay(bufferedReader, zaf);
                                fastJsonResponse.addConcreteTypeInternal(field, field.zae, null);
                            } else {
                                stack.push(1);
                                if (cZaw5 != '{') {
                                    throw new ParseException("Expected start of object");
                                }
                                try {
                                    FastJsonResponse fastJsonResponseZaf = field.zaf();
                                    zai(bufferedReader, fastJsonResponseZaf);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.zae, fastJsonResponseZaf);
                                } catch (IllegalAccessException e) {
                                    throw new ParseException("Error instantiating inner object", e);
                                } catch (InstantiationException e2) {
                                    throw new ParseException("Error instantiating inner object", e2);
                                }
                            }
                        }
                        break;
                    default:
                        int i = field.zaa;
                        StringBuilder sb3 = new StringBuilder(String.valueOf(i).length() + 19);
                        sb3.append("Invalid field type ");
                        sb3.append(i);
                        throw new ParseException(sb3.toString());
                }
                zaz(4);
                zaz(2);
                char cZaw6 = zaw(bufferedReader);
                if (cZaw6 == ',') {
                    strZaj = zaj(bufferedReader);
                } else {
                    if (cZaw6 != '}') {
                        StringBuilder sb4 = new StringBuilder(String.valueOf(cZaw6).length() + 54);
                        sb4.append("Expected end of object or field separator, but found: ");
                        sb4.append(cZaw6);
                        throw new ParseException(sb4.toString());
                    }
                    strZaj = null;
                }
                z = true;
            }
        }
        zaz(z ? 1 : 0);
        return z;
    }

    private final String zaj(BufferedReader bufferedReader) throws ParseException, IOException {
        Stack stack = this.zal;
        stack.push(2);
        char cZaw = zaw(bufferedReader);
        if (cZaw == '\"') {
            stack.push(3);
            String strZaA = zaA(bufferedReader, this.zab, this.zad, null);
            zaz(3);
            if (zaw(bufferedReader) == ':') {
                return strZaA;
            }
            throw new ParseException("Expected key/value separator");
        }
        if (cZaw == ']') {
            zaz(2);
            zaz(1);
            zaz(5);
            return null;
        }
        if (cZaw == '}') {
            zaz(2);
            return null;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(cZaw).length() + 18);
        sb.append("Unexpected token: ");
        sb.append(cZaw);
        throw new ParseException(sb.toString());
    }

    private final String zak(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        char cZaw = zaw(bufferedReader);
        int i = 1;
        if (cZaw == '\"') {
            char[] cArr = this.zaa;
            if (bufferedReader.read(cArr) == -1) {
                throw new ParseException("Unexpected EOF while parsing string");
            }
            char c = cArr[0];
            boolean z = false;
            do {
                if (c == '\"') {
                    if (z) {
                        z = true;
                        c = '\"';
                    }
                }
                z = c == '\\' ? !z : false;
                if (bufferedReader.read(cArr) == -1) {
                    throw new ParseException("Unexpected EOF while parsing string");
                }
                c = cArr[0];
            } while (!Character.isISOControl(c));
            throw new ParseException("Unexpected control character while reading string");
        }
        if (cZaw == ',') {
            throw new ParseException("Missing value");
        }
        if (cZaw == '[') {
            this.zal.push(5);
            bufferedReader.mark(32);
            if (zaw(bufferedReader) == ']') {
                zaz(5);
            } else {
                bufferedReader.reset();
                boolean z2 = false;
                boolean z3 = false;
                while (i > 0) {
                    char cZaw2 = zaw(bufferedReader);
                    if (cZaw2 == 0) {
                        throw new ParseException("Unexpected EOF while parsing array");
                    }
                    if (Character.isISOControl(cZaw2)) {
                        throw new ParseException("Unexpected control character while reading array");
                    }
                    if (cZaw2 == '\"') {
                        if (!z3) {
                            z2 = !z2;
                        }
                        cZaw2 = '\"';
                    }
                    if (cZaw2 == '[') {
                        if (!z2) {
                            i++;
                        }
                        cZaw2 = '[';
                    }
                    if (cZaw2 == ']' && !z2) {
                        i--;
                    }
                    z3 = (cZaw2 == '\\' && z2) ? !z3 : false;
                }
                zaz(5);
            }
        } else if (cZaw != '{') {
            bufferedReader.reset();
            zax(bufferedReader, this.zac);
        } else {
            this.zal.push(1);
            bufferedReader.mark(32);
            char cZaw3 = zaw(bufferedReader);
            if (cZaw3 == '}') {
                zaz(1);
            } else {
                if (cZaw3 != '\"') {
                    StringBuilder sb = new StringBuilder(String.valueOf(cZaw3).length() + 17);
                    sb.append("Unexpected token ");
                    sb.append(cZaw3);
                    throw new ParseException(sb.toString());
                }
                bufferedReader.reset();
                zaj(bufferedReader);
                while (zak(bufferedReader) != null) {
                }
                zaz(1);
            }
        }
        char cZaw4 = zaw(bufferedReader);
        if (cZaw4 == ',') {
            zaz(2);
            return zaj(bufferedReader);
        }
        if (cZaw4 == '}') {
            zaz(2);
            return null;
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(cZaw4).length() + 17);
        sb2.append("Unexpected token ");
        sb2.append(cZaw4);
        throw new ParseException(sb2.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zal, reason: merged with bridge method [inline-methods] */
    public final String zaa(BufferedReader bufferedReader) throws ParseException, IOException {
        return zan(bufferedReader, this.zab, this.zad, null);
    }

    private final ArrayList zam(BufferedReader bufferedReader, zai zaiVar) throws ParseException, IOException {
        char cZaw = zaw(bufferedReader);
        if (cZaw == 'n') {
            zay(bufferedReader, zaf);
            return null;
        }
        if (cZaw != '[') {
            throw new ParseException("Expected start of array");
        }
        this.zal.push(5);
        ArrayList arrayList = new ArrayList();
        while (true) {
            bufferedReader.mark(1024);
            char cZaw2 = zaw(bufferedReader);
            if (cZaw2 == 0) {
                throw new ParseException("Unexpected EOF");
            }
            if (cZaw2 != ',') {
                if (cZaw2 == ']') {
                    zaz(5);
                    return arrayList;
                }
                bufferedReader.reset();
                arrayList.add(zaiVar.zaa(this, bufferedReader));
            }
        }
    }

    private final String zan(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        char cZaw = zaw(bufferedReader);
        if (cZaw == '\"') {
            return zaA(bufferedReader, cArr, sb, cArr2);
        }
        if (cZaw != 'n') {
            throw new ParseException("Expected string");
        }
        zay(bufferedReader, zaf);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zao, reason: merged with bridge method [inline-methods] */
    public final int zab(BufferedReader bufferedReader) throws ParseException, IOException {
        int i;
        int i2;
        char[] cArr = this.zac;
        int iZax = zax(bufferedReader, cArr);
        if (iZax == 0) {
            return 0;
        }
        if (iZax <= 0) {
            throw new ParseException("No number to parse");
        }
        char c = cArr[0];
        int i3 = c == '-' ? Integer.MIN_VALUE : -2147483647;
        int i4 = c == '-' ? 1 : 0;
        if (i4 < iZax) {
            i2 = i4 + 1;
            int iDigit = Character.digit(cArr[i4], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            i = -iDigit;
        } else {
            i = 0;
            i2 = i4;
        }
        while (i2 < iZax) {
            int i5 = i2 + 1;
            int iDigit2 = Character.digit(cArr[i2], 10);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (i < -214748364) {
                throw new ParseException("Number too large");
            }
            int i6 = i * 10;
            if (i6 < i3 + iDigit2) {
                throw new ParseException("Number too large");
            }
            i = i6 - iDigit2;
            i2 = i5;
        }
        if (i4 == 0) {
            return -i;
        }
        if (i2 > 1) {
            return i;
        }
        throw new ParseException("No digits to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zap, reason: merged with bridge method [inline-methods] */
    public final long zac(BufferedReader bufferedReader) throws ParseException, IOException {
        long j;
        int i;
        char[] cArr = this.zac;
        int iZax = zax(bufferedReader, cArr);
        if (iZax == 0) {
            return 0L;
        }
        if (iZax <= 0) {
            throw new ParseException("No number to parse");
        }
        char c = cArr[0];
        long j2 = c == '-' ? Long.MIN_VALUE : -9223372036854775807L;
        int i2 = c == '-' ? 1 : 0;
        int i3 = 10;
        if (i2 < iZax) {
            i = i2 + 1;
            int iDigit = Character.digit(cArr[i2], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            j = -iDigit;
        } else {
            j = 0;
            i = i2;
        }
        while (i < iZax) {
            int i4 = i + 1;
            int iDigit2 = Character.digit(cArr[i], i3);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (j < Buffer.OVERFLOW_ZONE) {
                throw new ParseException("Number too large");
            }
            long j3 = j * 10;
            long j4 = j2;
            long j5 = iDigit2;
            if (j3 < j4 + j5) {
                throw new ParseException("Number too large");
            }
            j = j3 - j5;
            i = i4;
            j2 = j4;
            i3 = 10;
        }
        if (i2 == 0) {
            return -j;
        }
        if (i > 1) {
            return j;
        }
        throw new ParseException("No digits to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zaq, reason: merged with bridge method [inline-methods] */
    public final BigInteger zad(BufferedReader bufferedReader) throws ParseException, IOException {
        char[] cArr = this.zac;
        int iZax = zax(bufferedReader, cArr);
        if (iZax == 0) {
            return null;
        }
        return new BigInteger(new String(cArr, 0, iZax));
    }

    private final boolean zar(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        char cZaw = zaw(bufferedReader);
        if (cZaw == '\"') {
            if (z) {
                throw new ParseException("No boolean value found in string");
            }
            return zar(bufferedReader, true);
        }
        if (cZaw == 'f') {
            zay(bufferedReader, z ? zaj : zai);
            return false;
        }
        if (cZaw == 'n') {
            zay(bufferedReader, zaf);
            return false;
        }
        if (cZaw == 't') {
            zay(bufferedReader, z ? zah : zag);
            return true;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(cZaw).length() + 18);
        sb.append("Unexpected token: ");
        sb.append(cZaw);
        throw new ParseException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zas, reason: merged with bridge method [inline-methods] */
    public final float zaf(BufferedReader bufferedReader) throws ParseException, IOException {
        char[] cArr = this.zac;
        int iZax = zax(bufferedReader, cArr);
        if (iZax == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(cArr, 0, iZax));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zat, reason: merged with bridge method [inline-methods] */
    public final double zag(BufferedReader bufferedReader) throws ParseException, IOException {
        char[] cArr = this.zac;
        int iZax = zax(bufferedReader, cArr);
        return iZax == 0 ? AudioStats.AUDIO_AMPLITUDE_NONE : Double.parseDouble(new String(cArr, 0, iZax));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zau, reason: merged with bridge method [inline-methods] */
    public final BigDecimal zah(BufferedReader bufferedReader) throws ParseException, IOException {
        char[] cArr = this.zac;
        int iZax = zax(bufferedReader, cArr);
        if (iZax == 0) {
            return null;
        }
        return new BigDecimal(new String(cArr, 0, iZax));
    }

    private final ArrayList zav(BufferedReader bufferedReader, FastJsonResponse.Field field) throws ParseException, IOException {
        ArrayList arrayList = new ArrayList();
        char cZaw = zaw(bufferedReader);
        if (cZaw == ']') {
            zaz(5);
            return arrayList;
        }
        if (cZaw == 'n') {
            zay(bufferedReader, zaf);
            zaz(5);
            return null;
        }
        if (cZaw != '{') {
            StringBuilder sb = new StringBuilder(String.valueOf(cZaw).length() + 18);
            sb.append("Unexpected token: ");
            sb.append(cZaw);
            throw new ParseException(sb.toString());
        }
        Stack stack = this.zal;
        stack.push(1);
        while (true) {
            try {
                FastJsonResponse fastJsonResponseZaf = field.zaf();
                if (!zai(bufferedReader, fastJsonResponseZaf)) {
                    return arrayList;
                }
                arrayList.add(fastJsonResponseZaf);
                char cZaw2 = zaw(bufferedReader);
                if (cZaw2 != ',') {
                    if (cZaw2 == ']') {
                        zaz(5);
                        return arrayList;
                    }
                    StringBuilder sb2 = new StringBuilder(String.valueOf(cZaw2).length() + 18);
                    sb2.append("Unexpected token: ");
                    sb2.append(cZaw2);
                    throw new ParseException(sb2.toString());
                }
                if (zaw(bufferedReader) != '{') {
                    throw new ParseException("Expected start of next object in array");
                }
                stack.push(1);
            } catch (IllegalAccessException e) {
                throw new ParseException("Error instantiating inner object", e);
            } catch (InstantiationException e2) {
                throw new ParseException("Error instantiating inner object", e2);
            }
        }
    }

    private final char zaw(BufferedReader bufferedReader) throws ParseException, IOException {
        char[] cArr = this.zaa;
        if (bufferedReader.read(cArr) != -1) {
            while (Character.isWhitespace(cArr[0])) {
                if (bufferedReader.read(cArr) == -1) {
                }
            }
            return cArr[0];
        }
        return (char) 0;
    }

    private final int zax(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i;
        char cZaw = zaw(bufferedReader);
        if (cZaw == 0) {
            throw new ParseException("Unexpected EOF");
        }
        if (cZaw == ',') {
            throw new ParseException("Missing value");
        }
        if (cZaw == 'n') {
            zay(bufferedReader, zaf);
            return 0;
        }
        bufferedReader.mark(1024);
        if (cZaw == '\"') {
            i = 0;
            boolean z = false;
            while (i < 1024 && bufferedReader.read(cArr, i, 1) != -1) {
                char c = cArr[i];
                if (Character.isISOControl(c)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
                int i2 = i + 1;
                if (c != '\"') {
                    if (c == '\\') {
                        z = !z;
                    }
                    i = i2;
                } else if (!z) {
                    bufferedReader.reset();
                    bufferedReader.skip(i2);
                    return i;
                }
                z = false;
                i = i2;
            }
        } else {
            cArr[0] = cZaw;
            i = 1;
            while (i < 1024 && bufferedReader.read(cArr, i, 1) != -1) {
                char c2 = cArr[i];
                if (c2 == '}' || c2 == ',' || Character.isWhitespace(c2) || cArr[i] == ']') {
                    bufferedReader.reset();
                    bufferedReader.skip(i - 1);
                    cArr[i] = 0;
                    return i;
                }
                i++;
            }
        }
        if (i == 1024) {
            throw new ParseException("Absurdly long value");
        }
        throw new ParseException("Unexpected EOF");
    }

    private final void zay(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (true) {
            int length = cArr.length;
            if (i >= length) {
                return;
            }
            char[] cArr2 = this.zab;
            int i2 = bufferedReader.read(cArr2, 0, length - i);
            if (i2 == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i3 = 0; i3 < i2; i3++) {
                if (cArr[i3 + i] != cArr2[i3]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i += i2;
        }
    }

    private final void zaz(int i) throws ParseException {
        Stack stack = this.zal;
        if (stack.isEmpty()) {
            StringBuilder sb = new StringBuilder(String.valueOf(i).length() + 35);
            sb.append("Expected state ");
            sb.append(i);
            sb.append(" but had empty stack");
            throw new ParseException(sb.toString());
        }
        int iIntValue = ((Integer) stack.pop()).intValue();
        if (iIntValue == i) {
            return;
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(i).length() + 24 + String.valueOf(iIntValue).length());
        sb2.append("Expected state ");
        sb2.append(i);
        sb2.append(" but had ");
        sb2.append(iIntValue);
        throw new ParseException(sb2.toString());
    }

    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            try {
                Stack stack = this.zal;
                stack.push(0);
                char cZaw = zaw(bufferedReader);
                if (cZaw == 0) {
                    throw new ParseException("No data to parse");
                }
                if (cZaw == '[') {
                    stack.push(5);
                    Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                    if (fieldMappings.size() != 1) {
                        throw new ParseException("Object array response class must have a single Field");
                    }
                    FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                    t.addConcreteTypeArrayInternal(value, value.zae, zav(bufferedReader, value));
                } else {
                    if (cZaw != '{') {
                        StringBuilder sb = new StringBuilder(String.valueOf(cZaw).length() + 18);
                        sb.append("Unexpected token: ");
                        sb.append(cZaw);
                        throw new ParseException(sb.toString());
                    }
                    stack.push(1);
                    zai(bufferedReader, t);
                }
                zaz(0);
            } catch (IOException e) {
                throw new ParseException(e);
            }
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException unused) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
        }
    }

    final /* synthetic */ boolean zae(BufferedReader bufferedReader, boolean z) {
        return zar(bufferedReader, false);
    }
}
