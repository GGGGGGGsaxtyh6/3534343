package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.mlkit:barcode-scanning@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzfp<T> implements zzge<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzgz.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzfm zzg;
    private final boolean zzh;
    private final int[] zzi;
    private final int zzj;
    private final int zzk;
    private final zzgs zzl;
    private final zzdt zzm;

    private zzfp(int[] iArr, Object[] objArr, int i, int i2, zzfm zzfmVar, boolean z, int[] iArr2, int i3, int i4, zzfs zzfsVar, zzez zzezVar, zzgs zzgsVar, zzdt zzdtVar, zzfh zzfhVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        boolean z2 = false;
        if (zzdtVar != null && (zzfmVar instanceof zzed)) {
            z2 = true;
        }
        this.zzh = z2;
        this.zzi = iArr2;
        this.zzj = i3;
        this.zzk = i4;
        this.zzl = zzgsVar;
        this.zzm = zzdtVar;
        this.zzg = zzfmVar;
    }

    private static void zzA(Object obj) {
        if (!zzL(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzB(Object obj, Object obj2, int i) {
        if (zzI(obj2, i)) {
            int iZzs = zzs(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = iZzs;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzge zzgeVarZzv = zzv(i);
            if (!zzI(obj, i)) {
                if (zzL(object)) {
                    Object objZze = zzgeVarZzv.zze();
                    zzgeVarZzv.zzg(objZze, object);
                    unsafe.putObject(obj, j, objZze);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzD(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzL(object2)) {
                Object objZze2 = zzgeVarZzv.zze();
                zzgeVarZzv.zzg(objZze2, object2);
                unsafe.putObject(obj, j, objZze2);
                object2 = objZze2;
            }
            zzgeVarZzv.zzg(object2, object);
        }
    }

    private final void zzC(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzM(obj2, i2, i)) {
            int iZzs = zzs(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = iZzs;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzge zzgeVarZzv = zzv(i);
            if (!zzM(obj, i2, i)) {
                if (zzL(object)) {
                    Object objZze = zzgeVarZzv.zze();
                    zzgeVarZzv.zzg(objZze, object);
                    unsafe.putObject(obj, j, objZze);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzE(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzL(object2)) {
                Object objZze2 = zzgeVarZzv.zze();
                zzgeVarZzv.zzg(objZze2, object2);
                unsafe.putObject(obj, j, objZze2);
                object2 = objZze2;
            }
            zzgeVarZzv.zzg(object2, object);
        }
    }

    private final void zzD(Object obj, int i) {
        int iZzp = zzp(i);
        long j = 1048575 & iZzp;
        if (j == 1048575) {
            return;
        }
        zzgz.zzq(obj, j, (1 << (iZzp >>> 20)) | zzgz.zzc(obj, j));
    }

    private final void zzE(Object obj, int i, int i2) {
        zzgz.zzq(obj, zzp(i2) & 1048575, i);
    }

    private final void zzF(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzs(i) & 1048575, obj2);
        zzD(obj, i);
    }

    private final void zzG(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzs(i2) & 1048575, obj2);
        zzE(obj, i, i2);
    }

    private final boolean zzH(Object obj, Object obj2, int i) {
        return zzI(obj, i) == zzI(obj2, i);
    }

    private final boolean zzI(Object obj, int i) {
        int iZzp = zzp(i);
        long j = iZzp & 1048575;
        if (j != 1048575) {
            return (zzgz.zzc(obj, j) & (1 << (iZzp >>> 20))) != 0;
        }
        int iZzs = zzs(i);
        long j2 = iZzs & 1048575;
        switch (zzr(iZzs)) {
            case 0:
                return Double.doubleToRawLongBits(zzgz.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzgz.zzb(obj, j2)) != 0;
            case 2:
                return zzgz.zzd(obj, j2) != 0;
            case 3:
                return zzgz.zzd(obj, j2) != 0;
            case 4:
                return zzgz.zzc(obj, j2) != 0;
            case 5:
                return zzgz.zzd(obj, j2) != 0;
            case 6:
                return zzgz.zzc(obj, j2) != 0;
            case 7:
                return zzgz.zzw(obj, j2);
            case 8:
                Object objZzf = zzgz.zzf(obj, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zzdf) {
                    return !zzdf.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzgz.zzf(obj, j2) != null;
            case 10:
                return !zzdf.zzb.equals(zzgz.zzf(obj, j2));
            case 11:
                return zzgz.zzc(obj, j2) != 0;
            case 12:
                return zzgz.zzc(obj, j2) != 0;
            case 13:
                return zzgz.zzc(obj, j2) != 0;
            case 14:
                return zzgz.zzd(obj, j2) != 0;
            case 15:
                return zzgz.zzc(obj, j2) != 0;
            case 16:
                return zzgz.zzd(obj, j2) != 0;
            case 17:
                return zzgz.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzJ(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzI(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzK(Object obj, int i, zzge zzgeVar) {
        return zzgeVar.zzk(zzgz.zzf(obj, i & 1048575));
    }

    private static boolean zzL(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzeh) {
            return ((zzeh) obj).zzY();
        }
        return true;
    }

    private final boolean zzM(Object obj, int i, int i2) {
        return zzgz.zzc(obj, (long) (zzp(i2) & 1048575)) == i;
    }

    private static boolean zzN(Object obj, long j) {
        return ((Boolean) zzgz.zzf(obj, j)).booleanValue();
    }

    private static final void zzO(int i, Object obj, zzhh zzhhVar) throws IOException {
        if (obj instanceof String) {
            zzhhVar.zzG(i, (String) obj);
        } else {
            zzhhVar.zzd(i, (zzdf) obj);
        }
    }

    static zzgt zzd(Object obj) {
        zzeh zzehVar = (zzeh) obj;
        zzgt zzgtVar = zzehVar.zzc;
        if (zzgtVar != zzgt.zzc()) {
            return zzgtVar;
        }
        zzgt zzgtVarZzf = zzgt.zzf();
        zzehVar.zzc = zzgtVarZzf;
        return zzgtVarZzf;
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x03a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static zzfp zzl(Class cls, zzfj zzfjVar, zzfs zzfsVar, zzez zzezVar, zzgs zzgsVar, zzdt zzdtVar, zzfh zzfhVar) {
        int i;
        int iCharAt;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int[] iArr;
        int i7;
        int i8;
        char cCharAt;
        int i9;
        char cCharAt2;
        int i10;
        char cCharAt3;
        int i11;
        char cCharAt4;
        int i12;
        char cCharAt5;
        int i13;
        char cCharAt6;
        int i14;
        char cCharAt7;
        int i15;
        char cCharAt8;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        String str;
        int iObjectFieldOffset;
        char c;
        int iObjectFieldOffset2;
        int i21;
        int i22;
        int i23;
        Field fieldZzz;
        char cCharAt9;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        Object obj;
        Field fieldZzz2;
        Object obj2;
        Field fieldZzz3;
        int i29;
        char cCharAt10;
        int i30;
        char cCharAt11;
        int i31;
        char cCharAt12;
        int i32;
        char cCharAt13;
        if (!(zzfjVar instanceof zzfw)) {
            throw null;
        }
        zzfw zzfwVar = (zzfw) zzfjVar;
        String strZzd = zzfwVar.zzd();
        int length = strZzd.length();
        char c2 = 55296;
        if (strZzd.charAt(0) >= 55296) {
            int i33 = 1;
            while (true) {
                i = i33 + 1;
                if (strZzd.charAt(i33) < 55296) {
                    break;
                }
                i33 = i;
            }
        } else {
            i = 1;
        }
        int i34 = i + 1;
        int iCharAt2 = strZzd.charAt(i);
        if (iCharAt2 >= 55296) {
            int i35 = iCharAt2 & 8191;
            int i36 = 13;
            while (true) {
                i32 = i34 + 1;
                cCharAt13 = strZzd.charAt(i34);
                if (cCharAt13 < 55296) {
                    break;
                }
                i35 |= (cCharAt13 & 8191) << i36;
                i36 += 13;
                i34 = i32;
            }
            iCharAt2 = i35 | (cCharAt13 << i36);
            i34 = i32;
        }
        if (iCharAt2 == 0) {
            i3 = 0;
            i6 = 0;
            iCharAt = 0;
            i2 = 0;
            i4 = 0;
            i5 = 0;
            iArr = zza;
            i7 = 0;
        } else {
            int i37 = i34 + 1;
            int iCharAt3 = strZzd.charAt(i34);
            if (iCharAt3 >= 55296) {
                int i38 = iCharAt3 & 8191;
                int i39 = 13;
                while (true) {
                    i15 = i37 + 1;
                    cCharAt8 = strZzd.charAt(i37);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i38 |= (cCharAt8 & 8191) << i39;
                    i39 += 13;
                    i37 = i15;
                }
                iCharAt3 = i38 | (cCharAt8 << i39);
                i37 = i15;
            }
            int i40 = i37 + 1;
            int iCharAt4 = strZzd.charAt(i37);
            if (iCharAt4 >= 55296) {
                int i41 = iCharAt4 & 8191;
                int i42 = 13;
                while (true) {
                    i14 = i40 + 1;
                    cCharAt7 = strZzd.charAt(i40);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i41 |= (cCharAt7 & 8191) << i42;
                    i42 += 13;
                    i40 = i14;
                }
                iCharAt4 = i41 | (cCharAt7 << i42);
                i40 = i14;
            }
            int i43 = i40 + 1;
            int iCharAt5 = strZzd.charAt(i40);
            if (iCharAt5 >= 55296) {
                int i44 = iCharAt5 & 8191;
                int i45 = 13;
                while (true) {
                    i13 = i43 + 1;
                    cCharAt6 = strZzd.charAt(i43);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i44 |= (cCharAt6 & 8191) << i45;
                    i45 += 13;
                    i43 = i13;
                }
                iCharAt5 = i44 | (cCharAt6 << i45);
                i43 = i13;
            }
            int i46 = i43 + 1;
            int iCharAt6 = strZzd.charAt(i43);
            if (iCharAt6 >= 55296) {
                int i47 = iCharAt6 & 8191;
                int i48 = 13;
                while (true) {
                    i12 = i46 + 1;
                    cCharAt5 = strZzd.charAt(i46);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i47 |= (cCharAt5 & 8191) << i48;
                    i48 += 13;
                    i46 = i12;
                }
                iCharAt6 = i47 | (cCharAt5 << i48);
                i46 = i12;
            }
            int i49 = i46 + 1;
            iCharAt = strZzd.charAt(i46);
            if (iCharAt >= 55296) {
                int i50 = iCharAt & 8191;
                int i51 = 13;
                while (true) {
                    i11 = i49 + 1;
                    cCharAt4 = strZzd.charAt(i49);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i50 |= (cCharAt4 & 8191) << i51;
                    i51 += 13;
                    i49 = i11;
                }
                iCharAt = i50 | (cCharAt4 << i51);
                i49 = i11;
            }
            int i52 = i49 + 1;
            int iCharAt7 = strZzd.charAt(i49);
            if (iCharAt7 >= 55296) {
                int i53 = iCharAt7 & 8191;
                int i54 = 13;
                while (true) {
                    i10 = i52 + 1;
                    cCharAt3 = strZzd.charAt(i52);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i53 |= (cCharAt3 & 8191) << i54;
                    i54 += 13;
                    i52 = i10;
                }
                iCharAt7 = i53 | (cCharAt3 << i54);
                i52 = i10;
            }
            int i55 = i52 + 1;
            int iCharAt8 = strZzd.charAt(i52);
            if (iCharAt8 >= 55296) {
                int i56 = iCharAt8 & 8191;
                int i57 = 13;
                while (true) {
                    i9 = i55 + 1;
                    cCharAt2 = strZzd.charAt(i55);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i56 |= (cCharAt2 & 8191) << i57;
                    i57 += 13;
                    i55 = i9;
                }
                iCharAt8 = i56 | (cCharAt2 << i57);
                i55 = i9;
            }
            int i58 = i55 + 1;
            int iCharAt9 = strZzd.charAt(i55);
            if (iCharAt9 >= 55296) {
                int i59 = iCharAt9 & 8191;
                int i60 = 13;
                while (true) {
                    i8 = i58 + 1;
                    cCharAt = strZzd.charAt(i58);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i59 |= (cCharAt & 8191) << i60;
                    i60 += 13;
                    i58 = i8;
                }
                iCharAt9 = i59 | (cCharAt << i60);
                i58 = i8;
            }
            int i61 = iCharAt3 + iCharAt3 + iCharAt4;
            int[] iArr2 = new int[iCharAt9 + iCharAt7 + iCharAt8];
            int i62 = iCharAt7;
            i2 = iCharAt5;
            i3 = i62;
            i4 = iCharAt6;
            i5 = iCharAt9;
            i6 = i61;
            iArr = iArr2;
            i7 = iCharAt3;
            i34 = i58;
        }
        Unsafe unsafe = zzb;
        Object[] objArrZze = zzfwVar.zze();
        Class<?> cls2 = zzfwVar.zza().getClass();
        int i63 = i5 + i3;
        int i64 = iCharAt + iCharAt;
        int[] iArr3 = new int[iCharAt * 3];
        Object[] objArr = new Object[i64];
        int i65 = i5;
        int i66 = i63;
        int i67 = 0;
        int i68 = 0;
        while (i34 < length) {
            int i69 = i34 + 1;
            int iCharAt10 = strZzd.charAt(i34);
            if (iCharAt10 >= c2) {
                int i70 = iCharAt10 & 8191;
                int i71 = i69;
                int i72 = 13;
                while (true) {
                    i31 = i71 + 1;
                    cCharAt12 = strZzd.charAt(i71);
                    if (cCharAt12 < c2) {
                        break;
                    }
                    i70 |= (cCharAt12 & 8191) << i72;
                    i72 += 13;
                    i71 = i31;
                }
                iCharAt10 = i70 | (cCharAt12 << i72);
                i16 = i31;
            } else {
                i16 = i69;
            }
            int i73 = i16 + 1;
            int iCharAt11 = strZzd.charAt(i16);
            if (iCharAt11 >= c2) {
                int i74 = iCharAt11 & 8191;
                int i75 = i73;
                int i76 = 13;
                while (true) {
                    i30 = i75 + 1;
                    cCharAt11 = strZzd.charAt(i75);
                    if (cCharAt11 < c2) {
                        break;
                    }
                    i74 |= (cCharAt11 & 8191) << i76;
                    i76 += 13;
                    i75 = i30;
                }
                iCharAt11 = i74 | (cCharAt11 << i76);
                i17 = i30;
            } else {
                i17 = i73;
            }
            if ((iCharAt11 & 1024) != 0) {
                iArr[i67] = i68;
                i67++;
            }
            int i77 = iCharAt11 & 255;
            zzfw zzfwVar2 = zzfwVar;
            int i78 = iCharAt11 & 2048;
            if (i77 >= 51) {
                int i79 = i17 + 1;
                int iCharAt12 = strZzd.charAt(i17);
                char c3 = 55296;
                if (iCharAt12 >= 55296) {
                    int i80 = iCharAt12 & 8191;
                    int i81 = i79;
                    int i82 = 13;
                    while (true) {
                        i29 = i81 + 1;
                        cCharAt10 = strZzd.charAt(i81);
                        if (cCharAt10 < c3) {
                            break;
                        }
                        i80 |= (cCharAt10 & 8191) << i82;
                        i82 += 13;
                        i81 = i29;
                        c3 = 55296;
                    }
                    iCharAt12 = i80 | (cCharAt10 << i82);
                    i26 = i29;
                } else {
                    i26 = i79;
                }
                int i83 = i26;
                int i84 = i77 - 51;
                i18 = length;
                if (i84 == 9 || i84 == 17) {
                    i27 = i6 + 1;
                    int i85 = i68 / 3;
                    objArr[i85 + i85 + 1] = objArrZze[i6];
                } else {
                    if (i84 != 12) {
                        i28 = i78;
                    } else if (zzfwVar2.zzc() == 1 || i78 != 0) {
                        i27 = i6 + 1;
                        int i86 = i68 / 3;
                        objArr[i86 + i86 + 1] = objArrZze[i6];
                    } else {
                        i28 = 0;
                    }
                    int i87 = iCharAt12 + iCharAt12;
                    obj = objArrZze[i87];
                    int i88 = i28;
                    if (obj instanceof Field) {
                        fieldZzz2 = zzz(cls2, (String) obj);
                        objArrZze[i87] = fieldZzz2;
                    } else {
                        fieldZzz2 = (Field) obj;
                    }
                    int i89 = i7;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzz2);
                    int i90 = i87 + 1;
                    obj2 = objArrZze[i90];
                    i19 = i89;
                    if (obj2 instanceof Field) {
                        fieldZzz3 = zzz(cls2, (String) obj2);
                        objArrZze[i90] = fieldZzz3;
                    } else {
                        fieldZzz3 = (Field) obj2;
                    }
                    iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzz3);
                    str = strZzd;
                    i22 = i88;
                    i17 = i83;
                    i21 = 0;
                    c = 55296;
                }
                i6 = i27;
                i28 = i78;
                int i872 = iCharAt12 + iCharAt12;
                obj = objArrZze[i872];
                int i882 = i28;
                if (obj instanceof Field) {
                }
                int i892 = i7;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzz2);
                int i902 = i872 + 1;
                obj2 = objArrZze[i902];
                i19 = i892;
                if (obj2 instanceof Field) {
                }
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZzz3);
                str = strZzd;
                i22 = i882;
                i17 = i83;
                i21 = 0;
                c = 55296;
            } else {
                i18 = length;
                i19 = i7;
                int i91 = i6 + 1;
                Field fieldZzz4 = zzz(cls2, (String) objArrZze[i6]);
                if (i77 == 9 || i77 == 17) {
                    i20 = i91;
                    int i92 = i68 / 3;
                    objArr[i92 + i92 + 1] = fieldZzz4.getType();
                } else {
                    if (i77 == 27) {
                        i24 = i91;
                        i25 = 1;
                        i6 += 2;
                    } else if (i77 == 49) {
                        i6 += 2;
                        i24 = i91;
                        i25 = 1;
                    } else if (i77 == 12 || i77 == 30 || i77 == 44) {
                        if (zzfwVar2.zzc() == 1 || i78 != 0) {
                            i6 += 2;
                            int i93 = i68 / 3;
                            objArr[i93 + i93 + 1] = objArrZze[i91];
                            str = strZzd;
                            iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzz4);
                            if ((iCharAt11 & 4096) != 0 || i77 > 17) {
                                c = 55296;
                                iObjectFieldOffset2 = 1048575;
                                i21 = 0;
                            } else {
                                int i94 = i17 + 1;
                                int iCharAt13 = str.charAt(i17);
                                if (iCharAt13 >= 55296) {
                                    int i95 = iCharAt13 & 8191;
                                    int i96 = 13;
                                    while (true) {
                                        i23 = i94 + 1;
                                        cCharAt9 = str.charAt(i94);
                                        if (cCharAt9 < 55296) {
                                            break;
                                        }
                                        i95 |= (cCharAt9 & 8191) << i96;
                                        i96 += 13;
                                        i94 = i23;
                                    }
                                    iCharAt13 = i95 | (cCharAt9 << i96);
                                } else {
                                    i23 = i94;
                                }
                                int i97 = i19 + i19 + (iCharAt13 / 32);
                                Object obj3 = objArrZze[i97];
                                if (obj3 instanceof Field) {
                                    fieldZzz = (Field) obj3;
                                } else {
                                    fieldZzz = zzz(cls2, (String) obj3);
                                    objArrZze[i97] = fieldZzz;
                                }
                                int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZzz);
                                i21 = iCharAt13 % 32;
                                i17 = i23;
                                c = 55296;
                                iObjectFieldOffset2 = iObjectFieldOffset3;
                            }
                            if (i77 >= 18 && i77 <= 49) {
                                iArr[i66] = iObjectFieldOffset;
                                i66++;
                            }
                            i22 = i78;
                        } else {
                            str = strZzd;
                            i6 = i91;
                            i78 = 0;
                            iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzz4);
                            if ((iCharAt11 & 4096) != 0) {
                                c = 55296;
                                iObjectFieldOffset2 = 1048575;
                                i21 = 0;
                                if (i77 >= 18) {
                                    iArr[i66] = iObjectFieldOffset;
                                    i66++;
                                }
                                i22 = i78;
                            }
                        }
                    } else if (i77 == 50) {
                        int i98 = i6 + 2;
                        int i99 = i65 + 1;
                        iArr[i65] = i68;
                        int i100 = i68 / 3;
                        int i101 = i100 + i100;
                        objArr[i101] = objArrZze[i91];
                        if (i78 != 0) {
                            objArr[i101 + 1] = objArrZze[i98];
                            i6 += 3;
                            str = strZzd;
                            i65 = i99;
                            iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzz4);
                            if ((iCharAt11 & 4096) != 0) {
                            }
                        } else {
                            i6 = i98;
                            i65 = i99;
                            i78 = 0;
                            str = strZzd;
                            iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzz4);
                            if ((iCharAt11 & 4096) != 0) {
                            }
                        }
                    } else {
                        i20 = i91;
                    }
                    int i102 = i68 / 3;
                    objArr[i102 + i102 + i25] = objArrZze[i24];
                    str = strZzd;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzz4);
                    if ((iCharAt11 & 4096) != 0) {
                    }
                }
                str = strZzd;
                i6 = i20;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZzz4);
                if ((iCharAt11 & 4096) != 0) {
                }
            }
            int i103 = i68 + 1;
            iArr3[i68] = iCharAt10;
            int i104 = i68 + 2;
            iArr3[i103] = ((iCharAt11 & 512) != 0 ? 536870912 : 0) | ((iCharAt11 & 256) != 0 ? 268435456 : 0) | (i22 != 0 ? Integer.MIN_VALUE : 0) | (i77 << 20) | iObjectFieldOffset;
            i68 += 3;
            iArr3[i104] = (i21 << 20) | iObjectFieldOffset2;
            i34 = i17;
            strZzd = str;
            c2 = c;
            zzfwVar = zzfwVar2;
            length = i18;
            i7 = i19;
        }
        return new zzfp(iArr3, objArr, i2, i4, zzfwVar.zza(), false, iArr, i5, i63, zzfsVar, zzezVar, zzgsVar, zzdtVar, zzfhVar);
    }

    private static double zzm(Object obj, long j) {
        return ((Double) zzgz.zzf(obj, j)).doubleValue();
    }

    private static float zzn(Object obj, long j) {
        return ((Float) zzgz.zzf(obj, j)).floatValue();
    }

    private static int zzo(Object obj, long j) {
        return ((Integer) zzgz.zzf(obj, j)).intValue();
    }

    private final int zzp(int i) {
        return this.zzc[i + 2];
    }

    private final int zzq(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzr(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzs(int i) {
        return this.zzc[i + 1];
    }

    private static long zzt(Object obj, long j) {
        return ((Long) zzgz.zzf(obj, j)).longValue();
    }

    private final zzel zzu(int i) {
        int i2 = i / 3;
        return (zzel) this.zzd[i2 + i2 + 1];
    }

    private final zzge zzv(int i) {
        Object[] objArr = this.zzd;
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzge zzgeVar = (zzge) objArr[i3];
        if (zzgeVar != null) {
            return zzgeVar;
        }
        zzge zzgeVarZzb = zzfu.zza().zzb((Class) objArr[i3 + 1]);
        this.zzd[i3] = zzgeVarZzb;
        return zzgeVarZzb;
    }

    private final Object zzw(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzx(Object obj, int i) {
        zzge zzgeVarZzv = zzv(i);
        int iZzs = zzs(i) & 1048575;
        if (!zzI(obj, i)) {
            return zzgeVarZzv.zze();
        }
        Object object = zzb.getObject(obj, iZzs);
        if (zzL(object)) {
            return object;
        }
        Object objZze = zzgeVarZzv.zze();
        if (object != null) {
            zzgeVarZzv.zzg(objZze, object);
        }
        return objZze;
    }

    private final Object zzy(Object obj, int i, int i2) {
        zzge zzgeVarZzv = zzv(i2);
        if (!zzM(obj, i, i2)) {
            return zzgeVarZzv.zze();
        }
        Object object = zzb.getObject(obj, zzs(i2) & 1048575);
        if (zzL(object)) {
            return object;
        }
        Object objZze = zzgeVarZzv.zze();
        if (object != null) {
            zzgeVarZzv.zzg(objZze, object);
        }
        return objZze;
    }

    private static Field zzz(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:211:0x054c  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0710 A[PHI: r0
      0x0710: PHI (r0v2 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>) = 
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v39 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
      (r0v1 com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp<T>)
     binds: [B:18:0x004f, B:278:0x0703, B:243:0x0639, B:276:0x06fb, B:218:0x057f, B:135:0x036a, B:132:0x0353, B:129:0x033c, B:126:0x0325, B:123:0x030e, B:120:0x02f6, B:117:0x02de, B:114:0x02c6, B:111:0x02ac, B:108:0x0294, B:105:0x027c, B:102:0x0264, B:99:0x024c, B:96:0x0234, B:82:0x01e0, B:84:0x01ee, B:78:0x01c4, B:74:0x01b4, B:70:0x019d, B:67:0x0188, B:64:0x0172, B:61:0x0165, B:58:0x0158, B:55:0x0149, B:49:0x011f, B:46:0x010b, B:42:0x00ed, B:39:0x00d7, B:36:0x00c0, B:33:0x00b2, B:30:0x00a4, B:27:0x0089, B:24:0x006e, B:21:0x0058] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int zza(Object obj) {
        int i;
        int iZzA;
        int iZzA2;
        int iZzB;
        int iZzA3;
        int iZzA4;
        int iZzA5;
        int iZzd;
        int iZzA6;
        int iZzh;
        int size;
        int iZzl;
        int iZzA7;
        int iZzg;
        int iZzA8;
        int iZzA9;
        int iZzB2;
        int iZze;
        int iZzA10;
        int iZzA11;
        int iZzw;
        int iZzA12;
        int iZzA13;
        int iZzA14;
        int iZzd2;
        int iZzA15;
        zzfp<T> zzfpVar = this;
        Object obj2 = obj;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 0;
        int i4 = 0;
        int iZzA16 = 0;
        int i5 = 1048575;
        while (i3 < zzfpVar.zzc.length) {
            int iZzs = zzfpVar.zzs(i3);
            int iZzr = zzr(iZzs);
            int[] iArr = zzfpVar.zzc;
            int i6 = iArr[i3];
            int i7 = iArr[i3 + 2];
            int i8 = i7 & i2;
            if (iZzr <= 17) {
                if (i8 != i5) {
                    i4 = i8 == i2 ? 0 : unsafe.getInt(obj2, i8);
                    i5 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            int i9 = iZzs & i2;
            if (iZzr >= zzdy.DOUBLE_LIST_PACKED.zza()) {
                zzdy.SINT64_LIST_PACKED.zza();
            }
            int i10 = iZzA16;
            long j = i9;
            switch (iZzr) {
                case 0:
                    iZzA16 = zzfpVar.zzJ(obj2, i3, i5, i4, i) ? i10 + zzdn.zzA(i6 << 3) + 8 : i10;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 1:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        iZzA = zzdn.zzA(i6 << 3);
                        iZzA4 = iZzA + 4;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 2:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        long j2 = unsafe.getLong(obj2, j);
                        iZzA2 = zzdn.zzA(i6 << 3);
                        iZzB = zzdn.zzB(j2);
                        iZzA4 = iZzA2 + iZzB;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 3:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        long j3 = unsafe.getLong(obj2, j);
                        iZzA2 = zzdn.zzA(i6 << 3);
                        iZzB = zzdn.zzB(j3);
                        iZzA4 = iZzA2 + iZzB;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 4:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        long j4 = unsafe.getInt(obj2, j);
                        iZzA2 = zzdn.zzA(i6 << 3);
                        iZzB = zzdn.zzB(j4);
                        iZzA4 = iZzA2 + iZzB;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 5:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        iZzA3 = zzdn.zzA(i6 << 3);
                        iZzA4 = iZzA3 + 8;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 6:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        iZzA = zzdn.zzA(i6 << 3);
                        iZzA4 = iZzA + 4;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 7:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        iZzA4 = zzdn.zzA(i6 << 3) + 1;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 8:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        int i11 = i6 << 3;
                        Object object = unsafe.getObject(obj2, j);
                        if (object instanceof zzdf) {
                            iZzA5 = zzdn.zzA(i11);
                            iZzd = ((zzdf) object).zzd();
                            iZzA6 = zzdn.zzA(iZzd);
                            iZzA4 = iZzA5 + iZzA6 + iZzd;
                            iZzA16 = i10 + iZzA4;
                            zzfpVar = this;
                            i3 += 3;
                            obj2 = obj;
                            i2 = 1048575;
                        } else {
                            iZzA2 = zzdn.zzA(i11);
                            iZzB = zzdn.zzz((String) object);
                            iZzA4 = iZzA2 + iZzB;
                            iZzA16 = i10 + iZzA4;
                            zzfpVar = this;
                            i3 += 3;
                            obj2 = obj;
                            i2 = 1048575;
                        }
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 9:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        iZzh = zzgg.zzh(i6, unsafe.getObject(obj2, j), zzfpVar.zzv(i3));
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 10:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        zzdf zzdfVar = (zzdf) unsafe.getObject(obj2, j);
                        iZzA5 = zzdn.zzA(i6 << 3);
                        iZzd = zzdfVar.zzd();
                        iZzA6 = zzdn.zzA(iZzd);
                        iZzA4 = iZzA5 + iZzA6 + iZzd;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 11:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        int i12 = unsafe.getInt(obj2, j);
                        iZzA2 = zzdn.zzA(i6 << 3);
                        iZzB = zzdn.zzA(i12);
                        iZzA4 = iZzA2 + iZzB;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 12:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        long j5 = unsafe.getInt(obj2, j);
                        iZzA2 = zzdn.zzA(i6 << 3);
                        iZzB = zzdn.zzB(j5);
                        iZzA4 = iZzA2 + iZzB;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 13:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        iZzA = zzdn.zzA(i6 << 3);
                        iZzA4 = iZzA + 4;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 14:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        iZzA3 = zzdn.zzA(i6 << 3);
                        iZzA4 = iZzA3 + 8;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 15:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        int i13 = unsafe.getInt(obj2, j);
                        iZzA2 = zzdn.zzA(i6 << 3);
                        iZzB = zzdn.zzA((i13 >> 31) ^ (i13 + i13));
                        iZzA4 = iZzA2 + iZzB;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 16:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        long j6 = unsafe.getLong(obj2, j);
                        iZzA2 = zzdn.zzA(i6 << 3);
                        iZzB = zzdn.zzB((j6 >> 63) ^ (j6 + j6));
                        iZzA4 = iZzA2 + iZzB;
                        iZzA16 = i10 + iZzA4;
                        zzfpVar = this;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    zzfpVar = this;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 17:
                    if (zzfpVar.zzJ(obj2, i3, i5, i4, i)) {
                        iZzh = zzdn.zzw(i6, (zzfm) unsafe.getObject(obj2, j), zzfpVar.zzv(i3));
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 18:
                    iZzh = zzgg.zzd(i6, (List) unsafe.getObject(obj2, j), false);
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 19:
                    iZzh = zzgg.zzb(i6, (List) unsafe.getObject(obj2, j), false);
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 20:
                    List list = (List) unsafe.getObject(obj2, j);
                    int i14 = zzgg.zza;
                    iZzg = list.size() == 0 ? 0 : zzgg.zzg(list) + (list.size() * zzdn.zzA(i6 << 3));
                    iZzA16 = iZzg + i10;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 21:
                    List list2 = (List) unsafe.getObject(obj2, j);
                    int i15 = zzgg.zza;
                    size = list2.size();
                    if (size != 0) {
                        iZzl = zzgg.zzl(list2);
                        iZzA7 = zzdn.zzA(i6 << 3);
                        iZzB2 = size * iZzA7;
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 22:
                    List list3 = (List) unsafe.getObject(obj2, j);
                    int i16 = zzgg.zza;
                    size = list3.size();
                    if (size != 0) {
                        iZzl = zzgg.zzf(list3);
                        iZzA7 = zzdn.zzA(i6 << 3);
                        iZzB2 = size * iZzA7;
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 23:
                    iZzh = zzgg.zzd(i6, (List) unsafe.getObject(obj2, j), false);
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 24:
                    iZzh = zzgg.zzb(i6, (List) unsafe.getObject(obj2, j), false);
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 25:
                    List list4 = (List) unsafe.getObject(obj2, j);
                    int i17 = zzgg.zza;
                    int size2 = list4.size();
                    iZzh = size2 == 0 ? 0 : size2 * (zzdn.zzA(i6 << 3) + 1);
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 26:
                    List list5 = (List) unsafe.getObject(obj2, j);
                    int i18 = zzgg.zza;
                    int size3 = list5.size();
                    if (size3 != 0) {
                        iZzg = zzdn.zzA(i6 << 3) * size3;
                        if (list5 instanceof zzey) {
                            zzey zzeyVar = (zzey) list5;
                            for (int i19 = 0; i19 < size3; i19++) {
                                Object objZza = zzeyVar.zza();
                                if (objZza instanceof zzdf) {
                                    int iZzd3 = ((zzdf) objZza).zzd();
                                    iZzg += zzdn.zzA(iZzd3) + iZzd3;
                                } else {
                                    iZzg += zzdn.zzz((String) objZza);
                                }
                            }
                        } else {
                            for (int i20 = 0; i20 < size3; i20++) {
                                Object obj3 = list5.get(i20);
                                if (obj3 instanceof zzdf) {
                                    int iZzd4 = ((zzdf) obj3).zzd();
                                    iZzg += zzdn.zzA(iZzd4) + iZzd4;
                                } else {
                                    iZzg += zzdn.zzz((String) obj3);
                                }
                            }
                        }
                    }
                    iZzA16 = iZzg + i10;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 27:
                    List list6 = (List) unsafe.getObject(obj2, j);
                    zzge zzgeVarZzv = zzfpVar.zzv(i3);
                    int i21 = zzgg.zza;
                    int size4 = list6.size();
                    if (size4 == 0) {
                        iZzA8 = 0;
                    } else {
                        iZzA8 = zzdn.zzA(i6 << 3) * size4;
                        for (int i22 = 0; i22 < size4; i22++) {
                            Object obj4 = list6.get(i22);
                            if (obj4 instanceof zzex) {
                                int iZza = ((zzex) obj4).zza();
                                iZzA8 += zzdn.zzA(iZza) + iZza;
                            } else {
                                iZzA8 += zzdn.zzy((zzfm) obj4, zzgeVarZzv);
                            }
                        }
                    }
                    iZzA16 = i10 + iZzA8;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 28:
                    List list7 = (List) unsafe.getObject(obj2, j);
                    int i23 = zzgg.zza;
                    int size5 = list7.size();
                    if (size5 == 0) {
                        iZzA9 = 0;
                    } else {
                        iZzA9 = size5 * zzdn.zzA(i6 << 3);
                        for (int i24 = 0; i24 < list7.size(); i24++) {
                            int iZzd5 = ((zzdf) list7.get(i24)).zzd();
                            iZzA9 += zzdn.zzA(iZzd5) + iZzd5;
                        }
                    }
                    iZzA16 = i10 + iZzA9;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 29:
                    List list8 = (List) unsafe.getObject(obj2, j);
                    int i25 = zzgg.zza;
                    size = list8.size();
                    if (size != 0) {
                        iZzl = zzgg.zzk(list8);
                        iZzA7 = zzdn.zzA(i6 << 3);
                        iZzB2 = size * iZzA7;
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 30:
                    List list9 = (List) unsafe.getObject(obj2, j);
                    int i26 = zzgg.zza;
                    size = list9.size();
                    if (size != 0) {
                        iZzl = zzgg.zza(list9);
                        iZzA7 = zzdn.zzA(i6 << 3);
                        iZzB2 = size * iZzA7;
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 31:
                    iZzh = zzgg.zzb(i6, (List) unsafe.getObject(obj2, j), false);
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 32:
                    iZzh = zzgg.zzd(i6, (List) unsafe.getObject(obj2, j), false);
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 33:
                    List list10 = (List) unsafe.getObject(obj2, j);
                    int i27 = zzgg.zza;
                    size = list10.size();
                    if (size != 0) {
                        iZzl = zzgg.zzi(list10);
                        iZzA7 = zzdn.zzA(i6 << 3);
                        iZzB2 = size * iZzA7;
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 34:
                    List list11 = (List) unsafe.getObject(obj2, j);
                    int i28 = zzgg.zza;
                    size = list11.size();
                    if (size != 0) {
                        iZzl = zzgg.zzj(list11);
                        iZzA7 = zzdn.zzA(i6 << 3);
                        iZzB2 = size * iZzA7;
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    iZzA16 = i10 + iZzh;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 35:
                    iZze = zzgg.zze((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 36:
                    iZze = zzgg.zzc((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 37:
                    iZze = zzgg.zzg((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 38:
                    iZze = zzgg.zzl((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 39:
                    iZze = zzgg.zzf((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 40:
                    iZze = zzgg.zze((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 41:
                    iZze = zzgg.zzc((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 42:
                    List list12 = (List) unsafe.getObject(obj2, j);
                    int i29 = zzgg.zza;
                    iZze = list12.size();
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 43:
                    iZze = zzgg.zzk((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 44:
                    iZze = zzgg.zza((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 45:
                    iZze = zzgg.zzc((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 46:
                    iZze = zzgg.zze((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 47:
                    iZze = zzgg.zzi((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    iZze = zzgg.zzj((List) unsafe.getObject(obj2, j));
                    if (iZze > 0) {
                        iZzA10 = zzdn.zzA(i6 << 3);
                        iZzA11 = zzdn.zzA(iZze);
                        iZzA9 = iZzA10 + iZzA11 + iZze;
                        iZzA16 = i10 + iZzA9;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 49:
                    List list13 = (List) unsafe.getObject(obj2, j);
                    zzge zzgeVarZzv2 = zzfpVar.zzv(i3);
                    int i30 = zzgg.zza;
                    int size6 = list13.size();
                    if (size6 == 0) {
                        iZzw = 0;
                    } else {
                        iZzw = 0;
                        for (int i31 = 0; i31 < size6; i31++) {
                            iZzw += zzdn.zzw(i6, (zzfm) list13.get(i31), zzgeVarZzv2);
                        }
                    }
                    iZzA16 = i10 + iZzw;
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 50:
                    zzfg zzfgVar = (zzfg) unsafe.getObject(obj2, j);
                    if (!zzfgVar.isEmpty()) {
                        Iterator it = zzfgVar.entrySet().iterator();
                        if (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            entry.getKey();
                            entry.getValue();
                            throw null;
                        }
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        iZzA12 = zzdn.zzA(i6 << 3);
                        iZzh = iZzA12 + 8;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        iZzA13 = zzdn.zzA(i6 << 3);
                        iZzh = iZzA13 + 4;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 53:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        long jZzt = zzt(obj2, j);
                        iZzl = zzdn.zzA(i6 << 3);
                        iZzB2 = zzdn.zzB(jZzt);
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        long jZzt2 = zzt(obj2, j);
                        iZzl = zzdn.zzA(i6 << 3);
                        iZzB2 = zzdn.zzB(jZzt2);
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 55:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        long jZzo = zzo(obj2, j);
                        iZzl = zzdn.zzA(i6 << 3);
                        iZzB2 = zzdn.zzB(jZzo);
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 56:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        iZzA12 = zzdn.zzA(i6 << 3);
                        iZzh = iZzA12 + 8;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 57:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        iZzA13 = zzdn.zzA(i6 << 3);
                        iZzh = iZzA13 + 4;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 58:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        iZzh = zzdn.zzA(i6 << 3) + 1;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 59:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        int i32 = i6 << 3;
                        Object object2 = unsafe.getObject(obj2, j);
                        if (object2 instanceof zzdf) {
                            iZzA14 = zzdn.zzA(i32);
                            iZzd2 = ((zzdf) object2).zzd();
                            iZzA15 = zzdn.zzA(iZzd2);
                            iZzh = iZzA14 + iZzA15 + iZzd2;
                            iZzA16 = i10 + iZzh;
                            i3 += 3;
                            obj2 = obj;
                            i2 = 1048575;
                        } else {
                            iZzl = zzdn.zzA(i32);
                            iZzB2 = zzdn.zzz((String) object2);
                            iZzh = iZzl + iZzB2;
                            iZzA16 = i10 + iZzh;
                            i3 += 3;
                            obj2 = obj;
                            i2 = 1048575;
                        }
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 60:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        iZzh = zzgg.zzh(i6, unsafe.getObject(obj2, j), zzfpVar.zzv(i3));
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        zzdf zzdfVar2 = (zzdf) unsafe.getObject(obj2, j);
                        iZzA14 = zzdn.zzA(i6 << 3);
                        iZzd2 = zzdfVar2.zzd();
                        iZzA15 = zzdn.zzA(iZzd2);
                        iZzh = iZzA14 + iZzA15 + iZzd2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 62:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        int iZzo = zzo(obj2, j);
                        iZzl = zzdn.zzA(i6 << 3);
                        iZzB2 = zzdn.zzA(iZzo);
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        long jZzo2 = zzo(obj2, j);
                        iZzl = zzdn.zzA(i6 << 3);
                        iZzB2 = zzdn.zzB(jZzo2);
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 64:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        iZzA13 = zzdn.zzA(i6 << 3);
                        iZzh = iZzA13 + 4;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 65:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        iZzA12 = zzdn.zzA(i6 << 3);
                        iZzh = iZzA12 + 8;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        int iZzo2 = zzo(obj2, j);
                        iZzl = zzdn.zzA(i6 << 3);
                        iZzB2 = zzdn.zzA((iZzo2 >> 31) ^ (iZzo2 + iZzo2));
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        long jZzt3 = zzt(obj2, j);
                        iZzl = zzdn.zzA(i6 << 3);
                        iZzB2 = zzdn.zzB((jZzt3 >> 63) ^ (jZzt3 + jZzt3));
                        iZzh = iZzl + iZzB2;
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
                case 68:
                    if (zzfpVar.zzM(obj2, i6, i3)) {
                        iZzh = zzdn.zzw(i6, (zzfm) unsafe.getObject(obj2, j), zzfpVar.zzv(i3));
                        iZzA16 = i10 + iZzh;
                        i3 += 3;
                        obj2 = obj;
                        i2 = 1048575;
                    }
                    i3 += 3;
                    obj2 = obj;
                    i2 = 1048575;
                    break;
            }
        }
        int iZza2 = iZzA16 + ((zzeh) obj).zzc.zza();
        if (!zzfpVar.zzh) {
            return iZza2;
        }
        zzdx zzdxVar = ((zzed) obj).zzb;
        int iZzc = zzdxVar.zza.zzc();
        int iZza3 = 0;
        for (int i33 = 0; i33 < iZzc; i33++) {
            Map.Entry entryZzg = zzdxVar.zza.zzg(i33);
            iZza3 += zzdx.zza((zzdw) ((zzgi) entryZzg).zza(), entryZzg.getValue());
        }
        for (Map.Entry entry2 : zzdxVar.zza.zzd()) {
            iZza3 += zzdx.zza((zzdw) entry2.getKey(), entry2.getValue());
        }
        return iZza2 + iZza3;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final int zzb(Object obj) {
        int i;
        long jDoubleToLongBits;
        int iFloatToIntBits;
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < this.zzc.length; i4 += 3) {
            int iZzs = zzs(i4);
            int[] iArr = this.zzc;
            int i5 = 1048575 & iZzs;
            int iZzr = zzr(iZzs);
            int i6 = iArr[i4];
            long j = i5;
            int iHashCode = 37;
            switch (iZzr) {
                case 0:
                    i = i3 * 53;
                    jDoubleToLongBits = Double.doubleToLongBits(zzgz.zza(obj, j));
                    byte[] bArr = zzep.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 1:
                    i = i3 * 53;
                    iFloatToIntBits = Float.floatToIntBits(zzgz.zzb(obj, j));
                    i3 = i + iFloatToIntBits;
                    break;
                case 2:
                    i = i3 * 53;
                    jDoubleToLongBits = zzgz.zzd(obj, j);
                    byte[] bArr2 = zzep.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 3:
                    i = i3 * 53;
                    jDoubleToLongBits = zzgz.zzd(obj, j);
                    byte[] bArr3 = zzep.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 4:
                    i = i3 * 53;
                    iFloatToIntBits = zzgz.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 5:
                    i = i3 * 53;
                    jDoubleToLongBits = zzgz.zzd(obj, j);
                    byte[] bArr4 = zzep.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 6:
                    i = i3 * 53;
                    iFloatToIntBits = zzgz.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 7:
                    i = i3 * 53;
                    iFloatToIntBits = zzep.zza(zzgz.zzw(obj, j));
                    i3 = i + iFloatToIntBits;
                    break;
                case 8:
                    i = i3 * 53;
                    iFloatToIntBits = ((String) zzgz.zzf(obj, j)).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case 9:
                    i2 = i3 * 53;
                    Object objZzf = zzgz.zzf(obj, j);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i3 = i2 + iHashCode;
                    break;
                case 10:
                    i = i3 * 53;
                    iFloatToIntBits = zzgz.zzf(obj, j).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case 11:
                    i = i3 * 53;
                    iFloatToIntBits = zzgz.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 12:
                    i = i3 * 53;
                    iFloatToIntBits = zzgz.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 13:
                    i = i3 * 53;
                    iFloatToIntBits = zzgz.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 14:
                    i = i3 * 53;
                    jDoubleToLongBits = zzgz.zzd(obj, j);
                    byte[] bArr5 = zzep.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 15:
                    i = i3 * 53;
                    iFloatToIntBits = zzgz.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 16:
                    i = i3 * 53;
                    jDoubleToLongBits = zzgz.zzd(obj, j);
                    byte[] bArr6 = zzep.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 17:
                    i2 = i3 * 53;
                    Object objZzf2 = zzgz.zzf(obj, j);
                    if (objZzf2 != null) {
                        iHashCode = objZzf2.hashCode();
                    }
                    i3 = i2 + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case 49:
                    i = i3 * 53;
                    iFloatToIntBits = zzgz.zzf(obj, j).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case 50:
                    i = i3 * 53;
                    iFloatToIntBits = zzgz.zzf(obj, j).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = Double.doubleToLongBits(zzm(obj, j));
                        byte[] bArr7 = zzep.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = Float.floatToIntBits(zzn(obj, j));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 53:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr8 = zzep.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr9 = zzep.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 55:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 56:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr10 = zzep.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 57:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 58:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzep.zza(zzN(obj, j));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 59:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = ((String) zzgz.zzf(obj, j)).hashCode();
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 60:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzgz.zzf(obj, j).hashCode();
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzgz.zzf(obj, j).hashCode();
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 62:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 64:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 65:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr11 = zzep.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr12 = zzep.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                    }
                    break;
                case 68:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzgz.zzf(obj, j).hashCode();
                        i3 = i + iFloatToIntBits;
                    }
                    break;
            }
        }
        int iHashCode2 = (i3 * 53) + ((zzeh) obj).zzc.hashCode();
        return this.zzh ? (iHashCode2 * 53) + ((zzed) obj).zzb.zza.hashCode() : iHashCode2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0d08  */
    /* JADX WARN: Removed duplicated region for block: B:551:0x0d42  */
    /* JADX WARN: Removed duplicated region for block: B:616:0x0a70 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:619:0x0cdf A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:621:0x0055 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:638:0x0a82 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:659:0x0cf3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0238  */
    /* JADX WARN: Type update failed for variable: r38v22 ??, new type: byte[]
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 36701. Try increasing type updates limit count.
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:37)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:224)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:86)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnSame(TypeUpdate.java:72)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setImmutableType(TypeInferenceVisitor.java:111)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$1(TypeInferenceVisitor.java:102)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:102)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryToFixIncompatiblePrimitives(FixTypesVisitor.java:820)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX WARN: Type update failed for variable: r38v22 ??, new type: byte[]
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 36701. Try increasing type updates limit count.
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:37)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:224)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:86)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnSame(TypeUpdate.java:72)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setImmutableType(TypeInferenceVisitor.java:111)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$1(TypeInferenceVisitor.java:102)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:102)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Type update failed for variable: r38v22 byte[], new type: byte[]
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 36701. Try increasing type updates limit count.
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:37)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:224)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:86)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnSame(TypeUpdate.java:72)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setImmutableType(TypeInferenceVisitor.java:111)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$1(TypeInferenceVisitor.java:102)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:102)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryToForceImmutableTypes(FixTypesVisitor.java:946)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX WARN: Type update failed for variable: r7v1 ??, new type: byte[]
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 36701. Try increasing type updates limit count.
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:37)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:224)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:86)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnSame(TypeUpdate.java:72)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setImmutableType(TypeInferenceVisitor.java:111)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$1(TypeInferenceVisitor.java:102)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:102)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryToFixIncompatiblePrimitives(FixTypesVisitor.java:820)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX WARN: Type update failed for variable: r7v1 ??, new type: byte[]
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 36701. Try increasing type updates limit count.
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:37)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:224)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:86)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnSame(TypeUpdate.java:72)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setImmutableType(TypeInferenceVisitor.java:111)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$1(TypeInferenceVisitor.java:102)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:102)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final int zzc(Object obj, byte[] bArr, int i, int i2, int i3, zzcu zzcuVar) throws IOException {
        int i4;
        zzfp<T> zzfpVar;
        Unsafe unsafe;
        Object obj2;
        int i5;
        int iZzq;
        int i6;
        int i7;
        int i8;
        int i9;
        zzcu zzcuVar2;
        int i10;
        byte[] bArr2;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        Object obj3;
        Unsafe unsafe2;
        byte[] bArr3;
        zzcu zzcuVar3;
        int i16;
        int i17;
        int i18;
        byte[] bArr4;
        Unsafe unsafe3;
        int i19;
        Object obj4;
        int i20;
        int i21;
        zzcu zzcuVar4;
        int i22;
        int i23;
        int i24;
        int iZzg;
        int iZzj;
        int iZzm;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        int iZzj2;
        int i31;
        int i32;
        int i33;
        int iZzm2;
        byte[] bArr5;
        int iZza;
        int i34;
        int iZzj3;
        zzfp<T> zzfpVar2 = this;
        Object obj5 = obj;
        byte[] bArr6 = bArr;
        int i35 = i2;
        zzcu zzcuVar5 = zzcuVar;
        zzA(obj5);
        Unsafe unsafe4 = zzb;
        int i36 = 0;
        int iZzi = i;
        int i37 = 0;
        int i38 = 0;
        int i39 = 0;
        int i40 = -1;
        int i41 = 1048575;
        while (true) {
            if (iZzi < i35) {
                int iZzk = iZzi + 1;
                int i42 = bArr6[iZzi];
                if (i42 < 0) {
                    iZzk = zzcv.zzk(i42, bArr6, iZzk, zzcuVar5);
                    i42 = zzcuVar5.zza;
                }
                int i43 = iZzk;
                int i44 = i42;
                iZzi = i43;
                int i45 = (i44 == true ? 1 : 0) >>> 3;
                if (i45 > i40) {
                    iZzq = (i45 < zzfpVar2.zze || i45 > zzfpVar2.zzf) ? -1 : zzfpVar2.zzq(i45, i37 / 3);
                    if (iZzq != -1) {
                        zzfpVar = zzfpVar2;
                        unsafe = unsafe4;
                        i6 = i36;
                        i7 = i6;
                        i8 = i45;
                        i9 = i39;
                        i4 = i3;
                        obj2 = obj5;
                        zzcuVar2 = zzcuVar5;
                        i10 = i44 == true ? 1 : 0;
                        bArr2 = bArr6;
                    } else {
                        boolean z = (i44 == true ? 1 : 0) & 7;
                        int[] iArr = zzfpVar2.zzc;
                        int i46 = iArr[iZzq + 1];
                        int i47 = iZzq;
                        int iZzr = zzr(i46);
                        long j = i46 & 1048575;
                        if (iZzr <= 17) {
                            int i48 = iArr[i47 + 2];
                            int i49 = 1 << (i48 >>> 20);
                            int i50 = i48 & 1048575;
                            if (i50 != i41) {
                                int i51 = 1048575;
                                if (i41 != 1048575) {
                                    unsafe4.putInt(obj5, i41, i39);
                                    i51 = 1048575;
                                }
                                i39 = i50 == i51 ? 0 : unsafe4.getInt(obj5, i50);
                                i41 = i50;
                            }
                            switch (iZzr) {
                                case 0:
                                    zzcu zzcuVar6 = zzcuVar5;
                                    bArr3 = bArr6;
                                    zzcuVar3 = zzcuVar6;
                                    i13 = i41;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    i36 = 0;
                                    obj3 = obj5;
                                    i15 = i39;
                                    unsafe2 = unsafe4;
                                    if (z == 1) {
                                        i16 = iZzi + 8;
                                        i17 = i15 | i49;
                                        zzgz.zzo(obj3, j, Double.longBitsToDouble(zzcv.zzq(bArr3, iZzi)));
                                        byte[] bArr7 = bArr3;
                                        zzcuVar5 = zzcuVar3;
                                        bArr6 = bArr7;
                                        i35 = i2;
                                        iZzi = i16;
                                        i38 = i14;
                                        obj5 = obj3;
                                        unsafe4 = unsafe2;
                                        i40 = i45;
                                        i41 = i13;
                                        i39 = i17;
                                        i37 = i6;
                                    } else {
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 1:
                                    zzcu zzcuVar7 = zzcuVar5;
                                    bArr3 = bArr6;
                                    zzcuVar3 = zzcuVar7;
                                    i13 = i41;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    i36 = 0;
                                    obj3 = obj5;
                                    i15 = i39;
                                    unsafe2 = unsafe4;
                                    if (z == 5) {
                                        i16 = iZzi + 4;
                                        i17 = i15 | i49;
                                        zzgz.zzp(obj3, j, Float.intBitsToFloat(zzcv.zzc(bArr3, iZzi)));
                                        byte[] bArr72 = bArr3;
                                        zzcuVar5 = zzcuVar3;
                                        bArr6 = bArr72;
                                        i35 = i2;
                                        iZzi = i16;
                                        i38 = i14;
                                        obj5 = obj3;
                                        unsafe4 = unsafe2;
                                        i40 = i45;
                                        i41 = i13;
                                        i39 = i17;
                                        i37 = i6;
                                    } else {
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 2:
                                case 3:
                                    zzcu zzcuVar8 = zzcuVar5;
                                    byte[] bArr8 = bArr6;
                                    i13 = i41;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    i36 = 0;
                                    i15 = i39;
                                    if (z == 0) {
                                        int iZzm3 = zzcv.zzm(bArr8, iZzi, zzcuVar8);
                                        unsafe4.putLong(obj5, j, zzcuVar8.zzb);
                                        zzcuVar5 = zzcuVar8;
                                        bArr6 = bArr8;
                                        i41 = i13;
                                        i39 = i15 | i49;
                                        iZzi = iZzm3;
                                        i37 = i6;
                                        i38 = i14;
                                        i40 = i45;
                                        i35 = i2;
                                    } else {
                                        unsafe2 = unsafe4;
                                        obj3 = obj5;
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 4:
                                case 11:
                                    zzcu zzcuVar9 = zzcuVar5;
                                    byte[] bArr9 = bArr6;
                                    i13 = i41;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    i36 = 0;
                                    i15 = i39;
                                    if (z == 0) {
                                        i18 = i15 | i49;
                                        iZzi = zzcv.zzj(bArr9, iZzi, zzcuVar9);
                                        unsafe4.putInt(obj5, j, zzcuVar9.zza);
                                        bArr4 = bArr9;
                                        zzcuVar5 = zzcuVar9;
                                        bArr6 = bArr4;
                                        i41 = i13;
                                        i35 = i2;
                                        i39 = i18;
                                        i37 = i6;
                                        i38 = i14;
                                        i40 = i45;
                                    } else {
                                        unsafe2 = unsafe4;
                                        obj3 = obj5;
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 5:
                                case 14:
                                    byte[] bArr10 = bArr6;
                                    Unsafe unsafe5 = unsafe4;
                                    zzcu zzcuVar10 = zzcuVar5;
                                    i13 = i41;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    i36 = 0;
                                    i15 = i39;
                                    if (z == 1) {
                                        unsafe4 = unsafe5;
                                        unsafe4.putLong(obj5, j, zzcv.zzq(bArr10, iZzi));
                                        zzcuVar5 = zzcuVar10;
                                        bArr6 = bArr10;
                                        i41 = i13;
                                        iZzi += 8;
                                        i39 = i15 | i49;
                                        i37 = i6;
                                        i38 = i14;
                                        i40 = i45;
                                        i35 = i2;
                                    } else {
                                        unsafe4 = unsafe5;
                                        unsafe2 = unsafe4;
                                        obj3 = obj5;
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 6:
                                case 13:
                                    byte[] bArr11 = bArr6;
                                    unsafe3 = unsafe4;
                                    zzcu zzcuVar11 = zzcuVar5;
                                    i13 = i41;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    i36 = 0;
                                    i15 = i39;
                                    if (z == 5) {
                                        unsafe3.putInt(obj5, j, zzcv.zzc(bArr11, iZzi));
                                        zzcuVar5 = zzcuVar11;
                                        unsafe4 = unsafe3;
                                        bArr6 = bArr11;
                                        i41 = i13;
                                        i35 = i2;
                                        iZzi += 4;
                                        i39 = i15 | i49;
                                        i37 = i6;
                                        i38 = i14;
                                        i40 = i45;
                                    } else {
                                        obj3 = obj5;
                                        unsafe2 = unsafe3;
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 7:
                                    byte[] bArr12 = bArr6;
                                    unsafe3 = unsafe4;
                                    zzcu zzcuVar12 = zzcuVar5;
                                    i13 = i41;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    i36 = 0;
                                    i15 = i39;
                                    if (z == 0) {
                                        i18 = i15 | i49;
                                        iZzi = zzcv.zzm(bArr12, iZzi, zzcuVar12);
                                        zzgz.zzm(obj5, j, zzcuVar12.zzb != 0);
                                        bArr4 = bArr12;
                                        zzcuVar5 = zzcuVar12;
                                        unsafe4 = unsafe3;
                                        bArr6 = bArr4;
                                        i41 = i13;
                                        i35 = i2;
                                        i39 = i18;
                                        i37 = i6;
                                        i38 = i14;
                                        i40 = i45;
                                    } else {
                                        obj3 = obj5;
                                        unsafe2 = unsafe3;
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 8:
                                    byte[] bArr13 = bArr6;
                                    unsafe3 = unsafe4;
                                    zzcu zzcuVar13 = zzcuVar5;
                                    i13 = i41;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    i15 = i39;
                                    if (z == 2) {
                                        if ((i46 & 536870912) != 0) {
                                            iZzi = zzcv.zzj(bArr13, iZzi, zzcuVar13);
                                            int i52 = zzcuVar13.zza;
                                            if (i52 < 0) {
                                                throw new zzer("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                            }
                                            int i53 = i15 | i49;
                                            if (i52 == 0) {
                                                zzcuVar13.zzc = "";
                                                i19 = i53;
                                                i36 = 0;
                                            } else {
                                                int length = bArr13.length;
                                                int i54 = zzhe.zza;
                                                if ((iZzi | i52 | ((length - iZzi) - i52)) < 0) {
                                                    throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(iZzi), Integer.valueOf(i52)));
                                                }
                                                int i55 = iZzi + i52;
                                                char[] cArr = new char[i52];
                                                int i56 = 0;
                                                while (iZzi < i55) {
                                                    byte b = bArr13[iZzi];
                                                    if (zzha.zzd(b)) {
                                                        iZzi++;
                                                        cArr[i56] = (char) b;
                                                        i56++;
                                                    } else {
                                                        while (iZzi < i55) {
                                                            int i57 = iZzi + 1;
                                                            int i58 = iZzi;
                                                            byte b2 = bArr13[i58];
                                                            if (zzha.zzd(b2)) {
                                                                int i59 = i56 + 1;
                                                                cArr[i56] = (char) b2;
                                                                iZzi = i57;
                                                                while (true) {
                                                                    i56 = i59;
                                                                    if (iZzi < i55) {
                                                                        byte b3 = bArr13[iZzi];
                                                                        if (zzha.zzd(b3)) {
                                                                            iZzi++;
                                                                            i59 = i56 + 1;
                                                                            cArr[i56] = (char) b3;
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                int i60 = i53;
                                                                if (b2 < -32) {
                                                                    if (i57 >= i55) {
                                                                        throw new zzer("Protocol message had invalid UTF-8.");
                                                                    }
                                                                    zzha.zzc(b2, bArr13[i57], cArr, i56);
                                                                    i56++;
                                                                    iZzi = i58 + 2;
                                                                } else if (b2 < -16) {
                                                                    if (i57 >= i55 - 1) {
                                                                        throw new zzer("Protocol message had invalid UTF-8.");
                                                                    }
                                                                    zzha.zzb(b2, bArr13[i57], bArr13[i58 + 2], cArr, i56);
                                                                    iZzi = i58 + 3;
                                                                    i53 = i60;
                                                                    i56++;
                                                                } else {
                                                                    if (i57 >= i55 - 2) {
                                                                        throw new zzer("Protocol message had invalid UTF-8.");
                                                                    }
                                                                    char[] cArr2 = cArr;
                                                                    zzha.zza(b2, bArr13[i57], bArr13[i58 + 2], bArr13[i58 + 3], cArr2, i56);
                                                                    i56 += 2;
                                                                    cArr = cArr2;
                                                                    iZzi = i58 + 4;
                                                                }
                                                                i53 = i60;
                                                            }
                                                        }
                                                        i19 = i53;
                                                        i36 = 0;
                                                        zzcuVar13.zzc = new String(cArr, 0, i56);
                                                        iZzi = i55;
                                                    }
                                                }
                                                while (iZzi < i55) {
                                                }
                                                i19 = i53;
                                                i36 = 0;
                                                zzcuVar13.zzc = new String(cArr, 0, i56);
                                                iZzi = i55;
                                            }
                                        } else {
                                            i36 = 0;
                                            iZzi = zzcv.zzh(bArr13, iZzi, zzcuVar13);
                                            i19 = i15 | i49;
                                        }
                                        unsafe3.putObject(obj5, j, zzcuVar13.zzc);
                                        zzcuVar5 = zzcuVar13;
                                        unsafe4 = unsafe3;
                                        bArr6 = bArr13;
                                        i41 = i13;
                                        i35 = i2;
                                        i37 = i6;
                                        i38 = i14 == true ? 1 : 0;
                                        i39 = i19;
                                        i40 = i45;
                                    } else {
                                        i36 = 0;
                                        obj3 = obj5;
                                        unsafe2 = unsafe3;
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 9:
                                    obj4 = obj5;
                                    i13 = i41;
                                    int i61 = i39;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    if (z == 2) {
                                        i39 = i61 | i49;
                                        Unsafe unsafe6 = unsafe4;
                                        Object objZzx = zzfpVar2.zzx(obj4, i6);
                                        byte[] bArr14 = bArr6;
                                        zzcu zzcuVar14 = zzcuVar5;
                                        int iZzo = zzcv.zzo(objZzx, zzfpVar2.zzv(i6), bArr14, iZzi, i2, zzcuVar14);
                                        zzfpVar2.zzF(obj4, i6, objZzx);
                                        zzcuVar5 = zzcuVar14;
                                        unsafe4 = unsafe6;
                                        bArr6 = bArr14;
                                        i41 = i13;
                                        i35 = i2;
                                        iZzi = iZzo;
                                        obj5 = obj4;
                                        i37 = i6;
                                        i38 = i14;
                                        i40 = i45;
                                        i36 = 0;
                                    } else {
                                        i15 = i61;
                                        unsafe2 = unsafe4;
                                        obj3 = obj4;
                                        i36 = 0;
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 10:
                                    obj4 = obj5;
                                    i13 = i41;
                                    i20 = i39;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    if (z == 2) {
                                        i20 |= i49;
                                        iZzi = zzcv.zza(bArr6, iZzi, zzcuVar5);
                                        unsafe4.putObject(obj4, j, zzcuVar5.zzc);
                                        i41 = i13;
                                        i35 = i2;
                                        i39 = i20;
                                        obj5 = obj4;
                                        i37 = i6;
                                        i38 = i14;
                                        i40 = i45;
                                        i36 = 0;
                                    } else {
                                        unsafe2 = unsafe4;
                                        i15 = i20;
                                        obj3 = obj4;
                                        i36 = 0;
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 12:
                                    obj4 = obj5;
                                    i13 = i41;
                                    i20 = i39;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    if (z == 0) {
                                        iZzi = zzcv.zzj(bArr6, iZzi, zzcuVar5);
                                        int i62 = zzcuVar5.zza;
                                        zzel zzelVarZzu = zzfpVar2.zzu(i6);
                                        if ((Integer.MIN_VALUE & i46) == 0 || zzelVarZzu == null || zzelVarZzu.zza(i62)) {
                                            i20 |= i49;
                                            unsafe4.putInt(obj4, j, i62);
                                        } else {
                                            zzd(obj4).zzj(i14 == true ? 1 : 0, Long.valueOf(i62));
                                        }
                                        i41 = i13;
                                        i35 = i2;
                                        i39 = i20;
                                        obj5 = obj4;
                                        i37 = i6;
                                        i38 = i14;
                                        i40 = i45;
                                        i36 = 0;
                                    } else {
                                        unsafe2 = unsafe4;
                                        i15 = i20;
                                        obj3 = obj4;
                                        i36 = 0;
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 15:
                                    obj4 = obj5;
                                    i13 = i41;
                                    i20 = i39;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    if (z == 0) {
                                        i20 |= i49;
                                        iZzi = zzcv.zzj(bArr6, iZzi, zzcuVar5);
                                        unsafe4.putInt(obj4, j, zzdj.zzb(zzcuVar5.zza));
                                        i41 = i13;
                                        i35 = i2;
                                        i39 = i20;
                                        obj5 = obj4;
                                        i37 = i6;
                                        i38 = i14;
                                        i40 = i45;
                                        i36 = 0;
                                    } else {
                                        unsafe2 = unsafe4;
                                        i15 = i20;
                                        obj3 = obj4;
                                        i36 = 0;
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                case 16:
                                    if (z == 0) {
                                        i39 |= i49;
                                        int iZzm4 = zzcv.zzm(bArr6, iZzi, zzcuVar5);
                                        unsafe4.putLong(obj5, j, zzdj.zzc(zzcuVar5.zzb));
                                        iZzi = iZzm4;
                                        i37 = i47;
                                        i38 = i44 == true ? 1 : 0;
                                        i40 = i45;
                                        i36 = 0;
                                        i35 = i2;
                                    } else {
                                        i6 = i47;
                                        i14 = i44 == true ? 1 : 0;
                                        i13 = i41;
                                        i15 = i39;
                                        i36 = 0;
                                        unsafe2 = unsafe4;
                                        obj3 = obj5;
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                                default:
                                    zzcu zzcuVar15 = zzcuVar5;
                                    byte[] bArr15 = bArr6;
                                    i13 = i41;
                                    i6 = i47;
                                    i14 = i44 == true ? 1 : 0;
                                    i36 = 0;
                                    obj3 = obj5;
                                    i15 = i39;
                                    unsafe2 = unsafe4;
                                    if (z == 3) {
                                        Object objZzx2 = zzfpVar2.zzx(obj3, i6);
                                        int iZzn = zzcv.zzn(objZzx2, zzfpVar2.zzv(i6), bArr15, iZzi, i2, (i45 << 3) | 4, zzcuVar15);
                                        zzfpVar2.zzF(obj3, i6, objZzx2);
                                        bArr6 = bArr;
                                        zzcuVar5 = zzcuVar;
                                        iZzi = iZzn;
                                        i37 = i6;
                                        i38 = i14 == true ? 1 : 0;
                                        obj5 = obj3;
                                        unsafe4 = unsafe2;
                                        i40 = i45;
                                        i41 = i13;
                                        i39 = i15 | i49;
                                        i35 = i2;
                                    } else {
                                        bArr2 = bArr;
                                        zzfpVar = zzfpVar2;
                                        i7 = i36;
                                        i10 = i14;
                                        obj2 = obj3;
                                        unsafe = unsafe2;
                                        i9 = i15;
                                        i8 = i45;
                                        i41 = i13;
                                        i4 = i3;
                                        zzcuVar2 = zzcuVar;
                                    }
                                    break;
                            }
                        } else {
                            Unsafe unsafe7 = unsafe4;
                            Object obj6 = obj5;
                            int i63 = i45;
                            i6 = i47;
                            i7 = 0;
                            if (iZzr != 27) {
                                unsafe = unsafe7;
                                i9 = i39;
                                i21 = iZzi;
                                zzcuVar4 = zzcuVar;
                                i22 = i41;
                                if (iZzr <= 49) {
                                    long j2 = i46;
                                    Unsafe unsafe8 = zzb;
                                    zzeo zzeoVarZzd = (zzeo) unsafe8.getObject(obj6, j);
                                    if (!zzeoVarZzd.zzc()) {
                                        int size = zzeoVarZzd.size();
                                        zzeoVarZzd = zzeoVarZzd.zzd(size != 0 ? size + size : 10);
                                        unsafe8.putObject(obj6, j, zzeoVarZzd);
                                    }
                                    switch (iZzr) {
                                        case 18:
                                        case 35:
                                            zzfpVar = zzfpVar2;
                                            obj2 = obj6;
                                            i8 = i63;
                                            zzeo zzeoVar = zzeoVarZzd;
                                            i23 = i44 == true ? 1 : 0;
                                            i24 = i21;
                                            if (z == 2) {
                                                int i64 = zzcv.zza;
                                                zzdp zzdpVar = (zzdp) zzeoVar;
                                                iZzj = zzcv.zzj(bArr, i24, zzcuVar4);
                                                int i65 = zzcuVar4.zza + iZzj;
                                                while (iZzj < i65) {
                                                    zzdpVar.zzf(Double.longBitsToDouble(zzcv.zzq(bArr, iZzj)));
                                                    iZzj += 8;
                                                }
                                                if (iZzj != i65) {
                                                    throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                                }
                                                i21 = i24;
                                                iZzi = iZzj;
                                                if (iZzi != i21) {
                                                    bArr6 = bArr;
                                                    i40 = i8;
                                                    zzcuVar5 = zzcuVar4;
                                                    i38 = i23;
                                                    zzfpVar2 = zzfpVar;
                                                    obj5 = obj2;
                                                    i37 = i6;
                                                    i41 = i22;
                                                    i36 = 0;
                                                    i39 = i9;
                                                    unsafe4 = unsafe;
                                                    i35 = i2;
                                                } else {
                                                    i4 = i3;
                                                    zzcuVar2 = zzcuVar4;
                                                    i10 = i23;
                                                    i41 = i22;
                                                }
                                                break;
                                            } else {
                                                if (z == 1) {
                                                    iZzg = i24 + 8;
                                                    int i66 = zzcv.zza;
                                                    zzdp zzdpVar2 = (zzdp) zzeoVar;
                                                    zzdpVar2.zzf(Double.longBitsToDouble(zzcv.zzq(bArr, i24)));
                                                    while (iZzg < i2) {
                                                        int iZzj4 = zzcv.zzj(bArr, iZzg, zzcuVar4);
                                                        if (i23 == zzcuVar4.zza) {
                                                            zzdpVar2.zzf(Double.longBitsToDouble(zzcv.zzq(bArr, iZzj4)));
                                                            iZzg = iZzj4 + 8;
                                                        } else {
                                                            i21 = i24;
                                                            iZzi = iZzg;
                                                            if (iZzi != i21) {
                                                            }
                                                        }
                                                    }
                                                    i21 = i24;
                                                    iZzi = iZzg;
                                                    if (iZzi != i21) {
                                                    }
                                                }
                                                i21 = i24;
                                                iZzi = i21;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            break;
                                        case 19:
                                        case 36:
                                            zzfpVar = zzfpVar2;
                                            obj2 = obj6;
                                            i8 = i63;
                                            zzeo zzeoVar2 = zzeoVarZzd;
                                            i23 = i44 == true ? 1 : 0;
                                            i24 = i21;
                                            if (z == 2) {
                                                int i67 = zzcv.zza;
                                                zzdz zzdzVar = (zzdz) zzeoVar2;
                                                iZzj = zzcv.zzj(bArr, i24, zzcuVar4);
                                                int i68 = zzcuVar4.zza + iZzj;
                                                while (iZzj < i68) {
                                                    zzdzVar.zzh(Float.intBitsToFloat(zzcv.zzc(bArr, iZzj)));
                                                    iZzj += 4;
                                                }
                                                if (iZzj != i68) {
                                                    throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                                }
                                                i21 = i24;
                                                iZzi = iZzj;
                                                if (iZzi != i21) {
                                                }
                                            } else {
                                                if (z == 5) {
                                                    iZzg = i24 + 4;
                                                    int i69 = zzcv.zza;
                                                    zzdz zzdzVar2 = (zzdz) zzeoVar2;
                                                    zzdzVar2.zzh(Float.intBitsToFloat(zzcv.zzc(bArr, i24)));
                                                    while (iZzg < i2) {
                                                        int iZzj5 = zzcv.zzj(bArr, iZzg, zzcuVar4);
                                                        if (i23 == zzcuVar4.zza) {
                                                            zzdzVar2.zzh(Float.intBitsToFloat(zzcv.zzc(bArr, iZzj5)));
                                                            iZzg = iZzj5 + 4;
                                                        } else {
                                                            i21 = i24;
                                                            iZzi = iZzg;
                                                            if (iZzi != i21) {
                                                            }
                                                        }
                                                    }
                                                    i21 = i24;
                                                    iZzi = iZzg;
                                                    if (iZzi != i21) {
                                                    }
                                                }
                                                i21 = i24;
                                                iZzi = i21;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            break;
                                        case 20:
                                        case 21:
                                        case 37:
                                        case 38:
                                            zzfpVar = zzfpVar2;
                                            obj2 = obj6;
                                            i8 = i63;
                                            zzeo zzeoVar3 = zzeoVarZzd;
                                            i23 = i44 == true ? 1 : 0;
                                            i24 = i21;
                                            if (z == 2) {
                                                int i70 = zzcv.zza;
                                                zzfb zzfbVar = (zzfb) zzeoVar3;
                                                iZzj = zzcv.zzj(bArr, i24, zzcuVar4);
                                                int i71 = zzcuVar4.zza + iZzj;
                                                while (iZzj < i71) {
                                                    iZzj = zzcv.zzm(bArr, iZzj, zzcuVar4);
                                                    zzfbVar.zzf(zzcuVar4.zzb);
                                                }
                                                if (iZzj != i71) {
                                                    throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                                }
                                                i21 = i24;
                                                iZzi = iZzj;
                                                if (iZzi != i21) {
                                                }
                                            } else {
                                                if (z == 0) {
                                                    int i72 = zzcv.zza;
                                                    zzfb zzfbVar2 = (zzfb) zzeoVar3;
                                                    iZzm = zzcv.zzm(bArr, i24, zzcuVar4);
                                                    zzfbVar2.zzf(zzcuVar4.zzb);
                                                    while (iZzm < i2) {
                                                        int iZzj6 = zzcv.zzj(bArr, iZzm, zzcuVar4);
                                                        if (i23 == zzcuVar4.zza) {
                                                            iZzm = zzcv.zzm(bArr, iZzj6, zzcuVar4);
                                                            zzfbVar2.zzf(zzcuVar4.zzb);
                                                        } else {
                                                            i21 = i24;
                                                            iZzi = iZzm;
                                                            if (iZzi != i21) {
                                                            }
                                                        }
                                                    }
                                                    i21 = i24;
                                                    iZzi = iZzm;
                                                    if (iZzi != i21) {
                                                    }
                                                }
                                                i21 = i24;
                                                iZzi = i21;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            break;
                                        case 22:
                                        case 29:
                                        case 39:
                                        case 43:
                                            zzfpVar = zzfpVar2;
                                            obj2 = obj6;
                                            i8 = i63;
                                            if (z == 2) {
                                                iZzg = zzcv.zzg(bArr, i21, zzeoVarZzd, zzcuVar4);
                                                i23 = i44 == true ? 1 : 0;
                                                iZzi = iZzg;
                                                if (iZzi != i21) {
                                                }
                                            } else {
                                                if (z == 0) {
                                                    iZzm = zzcv.zzl(i44 == true ? 1 : 0, bArr, i21, i2, zzeoVarZzd, zzcuVar4);
                                                    i23 = i44 == true ? 1 : 0;
                                                    iZzi = iZzm;
                                                    if (iZzi != i21) {
                                                    }
                                                }
                                                i23 = i44 == true ? 1 : 0;
                                                iZzi = i21;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            break;
                                        case 23:
                                        case 32:
                                        case 40:
                                        case 46:
                                            zzfpVar = zzfpVar2;
                                            obj2 = obj6;
                                            i8 = i63;
                                            if (z == 2) {
                                                int i73 = zzcv.zza;
                                                zzfb zzfbVar3 = (zzfb) zzeoVarZzd;
                                                iZzm = zzcv.zzj(bArr, i21, zzcuVar4);
                                                int i74 = zzcuVar4.zza + iZzm;
                                                while (iZzm < i74) {
                                                    zzfbVar3.zzf(zzcv.zzq(bArr, iZzm));
                                                    iZzm += 8;
                                                }
                                                if (iZzm != i74) {
                                                    throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                                }
                                                i23 = i44 == true ? 1 : 0;
                                                iZzi = iZzm;
                                                if (iZzi != i21) {
                                                }
                                            } else {
                                                if (z == 1) {
                                                    iZzg = i21 + 8;
                                                    int i75 = zzcv.zza;
                                                    zzfb zzfbVar4 = (zzfb) zzeoVarZzd;
                                                    zzfbVar4.zzf(zzcv.zzq(bArr, i21));
                                                    while (iZzg < i2) {
                                                        int iZzj7 = zzcv.zzj(bArr, iZzg, zzcuVar4);
                                                        if (i44 == zzcuVar4.zza) {
                                                            zzfbVar4.zzf(zzcv.zzq(bArr, iZzj7));
                                                            iZzg = iZzj7 + 8;
                                                        } else {
                                                            i23 = i44 == true ? 1 : 0;
                                                            iZzi = iZzg;
                                                            if (iZzi != i21) {
                                                            }
                                                        }
                                                    }
                                                    i23 = i44 == true ? 1 : 0;
                                                    iZzi = iZzg;
                                                    if (iZzi != i21) {
                                                    }
                                                }
                                                i23 = i44 == true ? 1 : 0;
                                                iZzi = i21;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            break;
                                        case 24:
                                        case 31:
                                        case 41:
                                        case 45:
                                            zzfpVar = zzfpVar2;
                                            obj2 = obj6;
                                            i8 = i63;
                                            if (z == 2) {
                                                int i76 = zzcv.zza;
                                                zzei zzeiVar = (zzei) zzeoVarZzd;
                                                iZzm = zzcv.zzj(bArr, i21, zzcuVar4);
                                                int i77 = zzcuVar4.zza + iZzm;
                                                while (iZzm < i77) {
                                                    zzeiVar.zzg(zzcv.zzc(bArr, iZzm));
                                                    iZzm += 4;
                                                }
                                                if (iZzm != i77) {
                                                    throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                                }
                                                i23 = i44 == true ? 1 : 0;
                                                iZzi = iZzm;
                                                if (iZzi != i21) {
                                                }
                                            } else {
                                                if (z == 5) {
                                                    iZzg = i21 + 4;
                                                    int i78 = zzcv.zza;
                                                    zzei zzeiVar2 = (zzei) zzeoVarZzd;
                                                    zzeiVar2.zzg(zzcv.zzc(bArr, i21));
                                                    while (iZzg < i2) {
                                                        int iZzj8 = zzcv.zzj(bArr, iZzg, zzcuVar4);
                                                        if (i44 == zzcuVar4.zza) {
                                                            zzeiVar2.zzg(zzcv.zzc(bArr, iZzj8));
                                                            iZzg = iZzj8 + 4;
                                                        } else {
                                                            i23 = i44 == true ? 1 : 0;
                                                            iZzi = iZzg;
                                                            if (iZzi != i21) {
                                                            }
                                                        }
                                                    }
                                                    i23 = i44 == true ? 1 : 0;
                                                    iZzi = iZzg;
                                                    if (iZzi != i21) {
                                                    }
                                                }
                                                i23 = i44 == true ? 1 : 0;
                                                iZzi = i21;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            break;
                                        case 25:
                                        case 42:
                                            zzfpVar = zzfpVar2;
                                            obj2 = obj6;
                                            i8 = i63;
                                            if (z == 2) {
                                                int i79 = zzcv.zza;
                                                zzcw zzcwVar = (zzcw) zzeoVarZzd;
                                                iZzm = zzcv.zzj(bArr, i21, zzcuVar4);
                                                int i80 = zzcuVar4.zza + iZzm;
                                                while (iZzm < i80) {
                                                    iZzm = zzcv.zzm(bArr, iZzm, zzcuVar4);
                                                    zzcwVar.zze(zzcuVar4.zzb != 0);
                                                }
                                                if (iZzm != i80) {
                                                    throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                                }
                                                i23 = i44 == true ? 1 : 0;
                                                iZzi = iZzm;
                                                if (iZzi != i21) {
                                                }
                                            } else {
                                                if (z == 0) {
                                                    int i81 = zzcv.zza;
                                                    zzcw zzcwVar2 = (zzcw) zzeoVarZzd;
                                                    iZzg = zzcv.zzm(bArr, i21, zzcuVar4);
                                                    zzcwVar2.zze(zzcuVar4.zzb != 0);
                                                    while (iZzg < i2) {
                                                        int iZzj9 = zzcv.zzj(bArr, iZzg, zzcuVar4);
                                                        if (i44 == zzcuVar4.zza) {
                                                            iZzg = zzcv.zzm(bArr, iZzj9, zzcuVar4);
                                                            zzcwVar2.zze(zzcuVar4.zzb != 0);
                                                        } else {
                                                            i23 = i44 == true ? 1 : 0;
                                                            iZzi = iZzg;
                                                            if (iZzi != i21) {
                                                            }
                                                        }
                                                    }
                                                    i23 = i44 == true ? 1 : 0;
                                                    iZzi = iZzg;
                                                    if (iZzi != i21) {
                                                    }
                                                }
                                                i23 = i44 == true ? 1 : 0;
                                                iZzi = i21;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            break;
                                        case 26:
                                            zzfpVar = zzfpVar2;
                                            obj2 = obj6;
                                            i8 = i63;
                                            if (z == 2) {
                                                if ((j2 & 536870912) == 0) {
                                                    iZzm = zzcv.zzj(bArr, i21, zzcuVar4);
                                                    int i82 = zzcuVar4.zza;
                                                    if (i82 < 0) {
                                                        throw new zzer("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                                    }
                                                    if (i82 == 0) {
                                                        zzeoVarZzd.add("");
                                                    } else {
                                                        zzeoVarZzd.add(new String(bArr, iZzm, i82, zzep.zza));
                                                        iZzm += i82;
                                                    }
                                                    while (iZzm < i2) {
                                                        int iZzj10 = zzcv.zzj(bArr, iZzm, zzcuVar4);
                                                        if (i44 == zzcuVar4.zza) {
                                                            iZzm = zzcv.zzj(bArr, iZzj10, zzcuVar4);
                                                            int i83 = zzcuVar4.zza;
                                                            if (i83 < 0) {
                                                                throw new zzer("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                                            }
                                                            if (i83 == 0) {
                                                                zzeoVarZzd.add("");
                                                            } else {
                                                                zzeoVarZzd.add(new String(bArr, iZzm, i83, zzep.zza));
                                                                iZzm += i83;
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    iZzm = zzcv.zzj(bArr, i21, zzcuVar4);
                                                    int i84 = zzcuVar4.zza;
                                                    if (i84 < 0) {
                                                        throw new zzer("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                                    }
                                                    if (i84 == 0) {
                                                        zzeoVarZzd.add("");
                                                    } else {
                                                        int i85 = iZzm + i84;
                                                        if (!zzhe.zzg(bArr, iZzm, i85)) {
                                                            throw new zzer("Protocol message had invalid UTF-8.");
                                                        }
                                                        zzeoVarZzd.add(new String(bArr, iZzm, i84, zzep.zza));
                                                        iZzm = i85;
                                                    }
                                                    while (iZzm < i2) {
                                                        int iZzj11 = zzcv.zzj(bArr, iZzm, zzcuVar4);
                                                        if (i44 == zzcuVar4.zza) {
                                                            iZzm = zzcv.zzj(bArr, iZzj11, zzcuVar4);
                                                            int i86 = zzcuVar4.zza;
                                                            if (i86 < 0) {
                                                                throw new zzer("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                                            }
                                                            if (i86 == 0) {
                                                                zzeoVarZzd.add("");
                                                            } else {
                                                                int i87 = iZzm + i86;
                                                                if (!zzhe.zzg(bArr, iZzm, i87)) {
                                                                    throw new zzer("Protocol message had invalid UTF-8.");
                                                                }
                                                                zzeoVarZzd.add(new String(bArr, iZzm, i86, zzep.zza));
                                                                iZzm = i87;
                                                            }
                                                        }
                                                    }
                                                }
                                                i23 = i44 == true ? 1 : 0;
                                                iZzi = iZzm;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            i23 = i44 == true ? 1 : 0;
                                            iZzi = i21;
                                            if (iZzi != i21) {
                                            }
                                            break;
                                        case 27:
                                            i25 = i44 == true ? 1 : 0;
                                            obj2 = obj6;
                                            i26 = i21;
                                            i8 = i63;
                                            if (z == 2) {
                                                zzfpVar = this;
                                                iZzg = zzcv.zzf(zzfpVar.zzv(i6), i25 == true ? 1 : 0, bArr, i26, i2, zzeoVarZzd, zzcuVar4);
                                                i21 = i26;
                                                i23 = i25 == true ? 1 : 0;
                                                zzcuVar4 = zzcuVar4;
                                                iZzi = iZzg;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            zzfpVar = this;
                                            i21 = i26;
                                            i23 = i25;
                                            iZzi = i21;
                                            if (iZzi != i21) {
                                            }
                                            break;
                                        case 28:
                                            i25 = i44 == true ? 1 : 0;
                                            obj2 = obj6;
                                            i26 = i21;
                                            i8 = i63;
                                            if (z == 2) {
                                                int iZzj12 = zzcv.zzj(bArr, i26, zzcuVar4);
                                                int i88 = zzcuVar4.zza;
                                                if (i88 < 0) {
                                                    throw new zzer("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                                }
                                                if (i88 > bArr.length - iZzj12) {
                                                    throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                                }
                                                if (i88 == 0) {
                                                    zzeoVarZzd.add(zzdf.zzb);
                                                } else {
                                                    zzeoVarZzd.add(zzdf.zzr(bArr, iZzj12, i88));
                                                    iZzj12 += i88;
                                                }
                                                while (iZzj12 < i2) {
                                                    int iZzj13 = zzcv.zzj(bArr, iZzj12, zzcuVar4);
                                                    if (i25 == zzcuVar4.zza) {
                                                        iZzj12 = zzcv.zzj(bArr, iZzj13, zzcuVar4);
                                                        int i89 = zzcuVar4.zza;
                                                        if (i89 < 0) {
                                                            throw new zzer("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                                                        }
                                                        if (i89 > bArr.length - iZzj12) {
                                                            throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                                        }
                                                        if (i89 == 0) {
                                                            zzeoVarZzd.add(zzdf.zzb);
                                                        } else {
                                                            zzeoVarZzd.add(zzdf.zzr(bArr, iZzj12, i89));
                                                            iZzj12 += i89;
                                                        }
                                                    } else {
                                                        zzfpVar = this;
                                                        i21 = i26;
                                                        iZzi = iZzj12;
                                                        i23 = i25 == true ? 1 : 0;
                                                        if (iZzi != i21) {
                                                        }
                                                    }
                                                }
                                                zzfpVar = this;
                                                i21 = i26;
                                                iZzi = iZzj12;
                                                i23 = i25 == true ? 1 : 0;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            zzfpVar = this;
                                            i21 = i26;
                                            i23 = i25;
                                            iZzi = i21;
                                            if (iZzi != i21) {
                                            }
                                            break;
                                        case 30:
                                        case 44:
                                            if (z == 2) {
                                                iZzg = zzcv.zzg(bArr, i21, zzeoVarZzd, zzcuVar4);
                                                i28 = i44 == true ? 1 : 0;
                                                i27 = i21;
                                            } else if (z == 0) {
                                                i27 = i21;
                                                int iZzl = zzcv.zzl(i44 == true ? 1 : 0, bArr, i27, i2, zzeoVarZzd, zzcuVar4);
                                                i28 = i44 == true ? 1 : 0;
                                                iZzg = iZzl;
                                            } else {
                                                obj2 = obj;
                                                i25 = i44 == true ? 1 : 0;
                                                i8 = i63;
                                                zzfpVar = this;
                                                i23 = i25;
                                                iZzi = i21;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            zzel zzelVarZzu2 = zzfpVar2.zzu(i6);
                                            zzgs zzgsVar = zzfpVar2.zzl;
                                            int i90 = zzgg.zza;
                                            if (zzelVarZzu2 == null) {
                                                obj2 = obj;
                                                i8 = i63;
                                            } else if (zzeoVarZzd instanceof RandomAccess) {
                                                int size2 = zzeoVarZzd.size();
                                                Object objZzn = null;
                                                int i91 = 0;
                                                int i92 = 0;
                                                while (i92 < size2) {
                                                    int iIntValue = ((Integer) zzeoVarZzd.get(i92)).intValue();
                                                    if (zzelVarZzu2.zza(iIntValue)) {
                                                        if (i92 != i91) {
                                                            zzeoVarZzd.set(i91, Integer.valueOf(iIntValue));
                                                        }
                                                        i91++;
                                                        i29 = i92;
                                                        i30 = i63;
                                                    } else {
                                                        i29 = i92;
                                                        i30 = i63;
                                                        objZzn = zzgg.zzn(obj, i30, iIntValue, objZzn, zzgsVar);
                                                    }
                                                    i63 = i30;
                                                    i92 = i29 + 1;
                                                }
                                                obj2 = obj;
                                                i8 = i63;
                                                if (i91 != size2) {
                                                    zzeoVarZzd.subList(i91, size2).clear();
                                                }
                                            } else {
                                                obj2 = obj;
                                                i8 = i63;
                                                Iterator it = zzeoVarZzd.iterator();
                                                Object objZzn2 = null;
                                                while (it.hasNext()) {
                                                    int iIntValue2 = ((Integer) it.next()).intValue();
                                                    if (!zzelVarZzu2.zza(iIntValue2)) {
                                                        objZzn2 = zzgg.zzn(obj2, i8, iIntValue2, objZzn2, zzgsVar);
                                                        it.remove();
                                                    }
                                                }
                                            }
                                            zzfpVar = this;
                                            i21 = i27;
                                            i23 = i28;
                                            iZzi = iZzg;
                                            if (iZzi != i21) {
                                            }
                                            break;
                                        case 33:
                                        case 47:
                                            if (z == 2) {
                                                int i93 = zzcv.zza;
                                                zzei zzeiVar3 = (zzei) zzeoVarZzd;
                                                iZzj2 = zzcv.zzj(bArr, i21, zzcuVar4);
                                                int i94 = zzcuVar4.zza + iZzj2;
                                                while (iZzj2 < i94) {
                                                    iZzj2 = zzcv.zzj(bArr, iZzj2, zzcuVar4);
                                                    zzeiVar3.zzg(zzdj.zzb(zzcuVar4.zza));
                                                }
                                                if (iZzj2 != i94) {
                                                    throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                                }
                                                obj2 = obj;
                                                zzfpVar = zzfpVar2;
                                                i23 = i44 == true ? 1 : 0;
                                                i8 = i63;
                                                iZzi = iZzj2;
                                                if (iZzi != i21) {
                                                }
                                            } else {
                                                if (z == 0) {
                                                    int i95 = zzcv.zza;
                                                    zzei zzeiVar4 = (zzei) zzeoVarZzd;
                                                    iZzg = zzcv.zzj(bArr, i21, zzcuVar4);
                                                    zzeiVar4.zzg(zzdj.zzb(zzcuVar4.zza));
                                                    while (iZzg < i2) {
                                                        int iZzj14 = zzcv.zzj(bArr, iZzg, zzcuVar4);
                                                        if (i44 == zzcuVar4.zza) {
                                                            iZzg = zzcv.zzj(bArr, iZzj14, zzcuVar4);
                                                            zzeiVar4.zzg(zzdj.zzb(zzcuVar4.zza));
                                                        } else {
                                                            obj2 = obj;
                                                            zzfpVar = zzfpVar2;
                                                            i23 = i44 == true ? 1 : 0;
                                                            i8 = i63;
                                                            iZzi = iZzg;
                                                            if (iZzi != i21) {
                                                            }
                                                        }
                                                    }
                                                    obj2 = obj;
                                                    zzfpVar = zzfpVar2;
                                                    i23 = i44 == true ? 1 : 0;
                                                    i8 = i63;
                                                    iZzi = iZzg;
                                                    if (iZzi != i21) {
                                                    }
                                                }
                                                obj2 = obj;
                                                zzfpVar = zzfpVar2;
                                                i23 = i44 == true ? 1 : 0;
                                                i8 = i63;
                                                iZzi = i21;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            break;
                                        case 34:
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                                            if (z == 2) {
                                                int i96 = zzcv.zza;
                                                zzfb zzfbVar5 = (zzfb) zzeoVarZzd;
                                                iZzj2 = zzcv.zzj(bArr, i21, zzcuVar4);
                                                int i97 = zzcuVar4.zza + iZzj2;
                                                while (iZzj2 < i97) {
                                                    iZzj2 = zzcv.zzm(bArr, iZzj2, zzcuVar4);
                                                    zzfbVar5.zzf(zzdj.zzc(zzcuVar4.zzb));
                                                }
                                                if (iZzj2 != i97) {
                                                    throw new zzer("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                                                }
                                                obj2 = obj;
                                                zzfpVar = zzfpVar2;
                                                i23 = i44 == true ? 1 : 0;
                                                i8 = i63;
                                                iZzi = iZzj2;
                                                if (iZzi != i21) {
                                                }
                                            } else {
                                                if (z == 0) {
                                                    int i98 = zzcv.zza;
                                                    zzfb zzfbVar6 = (zzfb) zzeoVarZzd;
                                                    iZzg = zzcv.zzm(bArr, i21, zzcuVar4);
                                                    zzfbVar6.zzf(zzdj.zzc(zzcuVar4.zzb));
                                                    while (iZzg < i2) {
                                                        int iZzj15 = zzcv.zzj(bArr, iZzg, zzcuVar4);
                                                        if (i44 == zzcuVar4.zza) {
                                                            iZzg = zzcv.zzm(bArr, iZzj15, zzcuVar4);
                                                            zzfbVar6.zzf(zzdj.zzc(zzcuVar4.zzb));
                                                        } else {
                                                            obj2 = obj;
                                                            zzfpVar = zzfpVar2;
                                                            i23 = i44 == true ? 1 : 0;
                                                            i8 = i63;
                                                            iZzi = iZzg;
                                                            if (iZzi != i21) {
                                                            }
                                                        }
                                                    }
                                                    obj2 = obj;
                                                    zzfpVar = zzfpVar2;
                                                    i23 = i44 == true ? 1 : 0;
                                                    i8 = i63;
                                                    iZzi = iZzg;
                                                    if (iZzi != i21) {
                                                    }
                                                }
                                                obj2 = obj;
                                                zzfpVar = zzfpVar2;
                                                i23 = i44 == true ? 1 : 0;
                                                i8 = i63;
                                                iZzi = i21;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            break;
                                        default:
                                            zzfpVar = zzfpVar2;
                                            byte[] bArr16 = bArr;
                                            obj2 = obj6;
                                            int i99 = i2;
                                            i8 = i63;
                                            zzeo zzeoVar4 = zzeoVarZzd;
                                            i23 = i44 == true ? 1 : 0;
                                            i24 = i21;
                                            if (z == 3) {
                                                int i100 = ((i23 == true ? 1 : 0) & (-8)) | 4;
                                                zzge zzgeVarZzv = zzfpVar.zzv(i6);
                                                iZzj = zzcv.zzd(zzgeVarZzv, bArr16, i24, i99, i100, zzcuVar4);
                                                i21 = i24;
                                                zzeoVar4.add(zzcuVar4.zzc);
                                                while (iZzj < i99) {
                                                    int iZzj16 = zzcv.zzj(bArr16, iZzj, zzcuVar4);
                                                    if (i23 == zzcuVar4.zza) {
                                                        iZzj = zzcv.zzd(zzgeVarZzv, bArr16, iZzj16, i99, i100, zzcuVar4);
                                                        zzeoVar4.add(zzcuVar4.zzc);
                                                        bArr16 = bArr;
                                                        i99 = i2;
                                                    } else {
                                                        iZzi = iZzj;
                                                        if (iZzi != i21) {
                                                        }
                                                    }
                                                }
                                                iZzi = iZzj;
                                                if (iZzi != i21) {
                                                }
                                            }
                                            i21 = i24;
                                            iZzi = i21;
                                            if (iZzi != i21) {
                                            }
                                            break;
                                    }
                                } else {
                                    zzfpVar = zzfpVar2;
                                    i8 = i63;
                                    i31 = i44 == true ? 1 : 0;
                                    obj2 = obj6;
                                    if (iZzr != 50) {
                                        Unsafe unsafe9 = zzb;
                                        long j3 = iArr[i6 + 2] & 1048575;
                                        switch (iZzr) {
                                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                                                i10 = i31 == true ? 1 : 0;
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr;
                                                if (z == 1) {
                                                    iZzm2 = i33 + 8;
                                                    unsafe9.putObject(obj2, j, Double.valueOf(Double.longBitsToDouble(zzcv.zzq(bArr2, i33))));
                                                    unsafe9.putInt(obj2, j3, i8);
                                                    iZzi = iZzm2;
                                                    if (iZzi == i33) {
                                                        i35 = i2;
                                                        i40 = i8;
                                                        bArr6 = bArr2;
                                                        zzfpVar2 = zzfpVar;
                                                        i38 = i10 == true ? 1 : 0;
                                                        obj5 = obj2;
                                                        zzcuVar5 = zzcuVar2;
                                                        i41 = i22;
                                                        i36 = 0;
                                                        i37 = i32;
                                                        i39 = i9;
                                                        unsafe4 = unsafe;
                                                    } else {
                                                        i4 = i3;
                                                        i41 = i22;
                                                        i6 = i32;
                                                    }
                                                }
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                                                i10 = i31 == true ? 1 : 0;
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr;
                                                if (z == 5) {
                                                    iZzm2 = i33 + 4;
                                                    unsafe9.putObject(obj2, j, Float.valueOf(Float.intBitsToFloat(zzcv.zzc(bArr2, i33))));
                                                    unsafe9.putInt(obj2, j3, i8);
                                                    iZzi = iZzm2;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                            case 53:
                                            case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                                                i10 = i31 == true ? 1 : 0;
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr;
                                                if (z == 0) {
                                                    iZzm2 = zzcv.zzm(bArr2, i33, zzcuVar2);
                                                    unsafe9.putObject(obj2, j, Long.valueOf(zzcuVar2.zzb));
                                                    unsafe9.putInt(obj2, j3, i8);
                                                    iZzi = iZzm2;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                            case 55:
                                            case 62:
                                                i10 = i31 == true ? 1 : 0;
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr;
                                                if (z == 0) {
                                                    iZzm2 = zzcv.zzj(bArr2, i33, zzcuVar2);
                                                    unsafe9.putObject(obj2, j, Integer.valueOf(zzcuVar2.zza));
                                                    unsafe9.putInt(obj2, j3, i8);
                                                    iZzi = iZzm2;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                            case 56:
                                            case 65:
                                                i10 = i31 == true ? 1 : 0;
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr;
                                                if (z == 1) {
                                                    iZzm2 = i33 + 8;
                                                    unsafe9.putObject(obj2, j, Long.valueOf(zzcv.zzq(bArr2, i33)));
                                                    unsafe9.putInt(obj2, j3, i8);
                                                    iZzi = iZzm2;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                            case 57:
                                            case 64:
                                                i10 = i31 == true ? 1 : 0;
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr;
                                                if (z == 5) {
                                                    iZzm2 = i33 + 4;
                                                    unsafe9.putObject(obj2, j, Integer.valueOf(zzcv.zzc(bArr2, i33)));
                                                    unsafe9.putInt(obj2, j3, i8);
                                                    iZzi = iZzm2;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                            case 58:
                                                i10 = i31 == true ? 1 : 0;
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr;
                                                if (z == 0) {
                                                    iZzm2 = zzcv.zzm(bArr2, i33, zzcuVar2);
                                                    unsafe9.putObject(obj2, j, Boolean.valueOf(zzcuVar2.zzb != 0));
                                                    unsafe9.putInt(obj2, j3, i8);
                                                    iZzi = iZzm2;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                            case 59:
                                                i10 = i31 == true ? 1 : 0;
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr;
                                                if (z == 2) {
                                                    iZzm2 = zzcv.zzj(bArr2, i33, zzcuVar2);
                                                    int i101 = zzcuVar2.zza;
                                                    if (i101 == 0) {
                                                        unsafe9.putObject(obj2, j, "");
                                                    } else {
                                                        int i102 = iZzm2 + i101;
                                                        if ((i46 & 536870912) != 0 && !zzhe.zzg(bArr2, iZzm2, i102)) {
                                                            throw new zzer("Protocol message had invalid UTF-8.");
                                                        }
                                                        unsafe9.putObject(obj2, j, new String(bArr2, iZzm2, i101, zzep.zza));
                                                        iZzm2 = i102;
                                                    }
                                                    unsafe9.putInt(obj2, j3, i8);
                                                    iZzi = iZzm2;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                            case 60:
                                                i10 = i31 == true ? 1 : 0;
                                                if (z == 2) {
                                                    Object objZzy = zzfpVar.zzy(obj2, i8, i6);
                                                    int iZzo2 = zzcv.zzo(objZzy, zzfpVar.zzv(i6), bArr, i21, i2, zzcuVar4);
                                                    zzcuVar2 = zzcuVar4;
                                                    bArr2 = bArr;
                                                    zzfpVar.zzG(obj2, i8, i6, objZzy);
                                                    i32 = i6;
                                                    i33 = i21;
                                                    iZzi = iZzo2;
                                                    if (iZzi == i33) {
                                                    }
                                                } else {
                                                    zzcuVar2 = zzcuVar4;
                                                    bArr2 = bArr;
                                                    i32 = i6;
                                                    i33 = i21;
                                                    iZzi = i33;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                break;
                                            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                                                bArr5 = bArr;
                                                i10 = i31 == true ? 1 : 0;
                                                if (z == 2) {
                                                    iZza = zzcv.zza(bArr5, i21, zzcuVar4);
                                                    unsafe9.putObject(obj2, j, zzcuVar4.zzc);
                                                    unsafe9.putInt(obj2, j3, i8);
                                                    iZzi = iZza;
                                                    i32 = i6;
                                                    i33 = i21;
                                                    zzcuVar2 = zzcuVar4;
                                                    bArr2 = bArr5;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr5;
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                            case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                                                bArr5 = bArr;
                                                i34 = i31 == true ? 1 : 0;
                                                if (z == 0) {
                                                    iZza = zzcv.zzj(bArr5, i21, zzcuVar4);
                                                    int i103 = zzcuVar4.zza;
                                                    zzfpVar = this;
                                                    zzel zzelVarZzu3 = zzfpVar.zzu(i6);
                                                    if (zzelVarZzu3 == null || zzelVarZzu3.zza(i103)) {
                                                        i10 = i34 == true ? 1 : 0;
                                                        unsafe9.putObject(obj2, j, Integer.valueOf(i103));
                                                        unsafe9.putInt(obj2, j3, i8);
                                                    } else {
                                                        zzgt zzgtVarZzd = zzd(obj2);
                                                        Long lValueOf = Long.valueOf(i103);
                                                        i10 = i34 == true ? 1 : 0;
                                                        zzgtVarZzd.zzj(i10 == true ? 1 : 0, lValueOf);
                                                    }
                                                    iZzi = iZza;
                                                    i32 = i6;
                                                    i33 = i21;
                                                    zzcuVar2 = zzcuVar4;
                                                    bArr2 = bArr5;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                zzfpVar = this;
                                                i10 = i34;
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr5;
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                                                bArr5 = bArr;
                                                i34 = i31 == true ? 1 : 0;
                                                if (z == 0) {
                                                    iZzj3 = zzcv.zzj(bArr5, i21, zzcuVar4);
                                                    unsafe9.putObject(obj2, j, Integer.valueOf(zzdj.zzb(zzcuVar4.zza)));
                                                    unsafe9.putInt(obj2, j3, i8);
                                                    zzfpVar = this;
                                                    iZzi = iZzj3;
                                                    i10 = i34;
                                                    i32 = i6;
                                                    i33 = i21;
                                                    zzcuVar2 = zzcuVar4;
                                                    bArr2 = bArr5;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                zzfpVar = this;
                                                i10 = i34;
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr5;
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                            case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                                                bArr5 = bArr;
                                                if (z == 0) {
                                                    iZzj3 = zzcv.zzm(bArr5, i21, zzcuVar4);
                                                    i34 = i31 == true ? 1 : 0;
                                                    unsafe9.putObject(obj2, j, Long.valueOf(zzdj.zzc(zzcuVar4.zzb)));
                                                    unsafe9.putInt(obj2, j3, i8);
                                                    zzfpVar = this;
                                                    iZzi = iZzj3;
                                                    i10 = i34;
                                                    i32 = i6;
                                                    i33 = i21;
                                                    zzcuVar2 = zzcuVar4;
                                                    bArr2 = bArr5;
                                                    if (iZzi == i33) {
                                                    }
                                                } else {
                                                    zzfpVar = this;
                                                    i10 = i31 == true ? 1 : 0;
                                                    i32 = i6;
                                                    i33 = i21;
                                                    zzcuVar2 = zzcuVar4;
                                                    bArr2 = bArr5;
                                                    iZzi = i33;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                                break;
                                            case 68:
                                                if (z == 3) {
                                                    int i104 = ((i31 == true ? 1 : 0) & (-8)) | 4;
                                                    Object objZzy2 = zzfpVar.zzy(obj2, i8, i6);
                                                    int iZzn2 = zzcv.zzn(objZzy2, zzfpVar.zzv(i6), bArr, i21, i2, i104, zzcuVar);
                                                    zzcuVar4 = zzcuVar;
                                                    bArr5 = bArr;
                                                    zzfpVar.zzG(obj2, i8, i6, objZzy2);
                                                    iZzi = iZzn2;
                                                    i10 = i31 == true ? 1 : 0;
                                                    i32 = i6;
                                                    i33 = i21;
                                                    zzcuVar2 = zzcuVar4;
                                                    bArr2 = bArr5;
                                                    if (iZzi == i33) {
                                                    }
                                                }
                                            default:
                                                i10 = i31 == true ? 1 : 0;
                                                i32 = i6;
                                                i33 = i21;
                                                zzcuVar2 = zzcuVar4;
                                                bArr2 = bArr;
                                                iZzi = i33;
                                                if (iZzi == i33) {
                                                }
                                                break;
                                        }
                                    } else {
                                        if (z == 2) {
                                            Unsafe unsafe10 = zzb;
                                            Object objZzw = zzfpVar.zzw(i6);
                                            Object object = unsafe10.getObject(obj2, j);
                                            if (!((zzfg) object).zze()) {
                                                zzfg zzfgVarZzb = zzfg.zza().zzb();
                                                zzfh.zza(zzfgVarZzb, object);
                                                unsafe10.putObject(obj2, j, zzfgVarZzb);
                                            }
                                            throw null;
                                        }
                                        i4 = i3;
                                        i10 = i31;
                                        iZzi = i21;
                                        i41 = i22;
                                        zzcuVar2 = zzcuVar4;
                                    }
                                }
                            } else if (z == 2) {
                                zzeo zzeoVarZzd2 = (zzeo) unsafe7.getObject(obj6, j);
                                if (!zzeoVarZzd2.zzc()) {
                                    int size3 = zzeoVarZzd2.size();
                                    zzeoVarZzd2 = zzeoVarZzd2.zzd(size3 != 0 ? size3 + size3 : 10);
                                    unsafe7.putObject(obj6, j, zzeoVarZzd2);
                                }
                                int iZzf = zzcv.zzf(zzfpVar2.zzv(i6), i44 == true ? 1 : 0, bArr, iZzi, i2, zzeoVarZzd2, zzcuVar);
                                bArr6 = bArr;
                                i35 = i2;
                                i38 = i44 == true ? 1 : 0;
                                obj5 = obj6;
                                i37 = i6;
                                i36 = 0;
                                i40 = i63;
                                zzcuVar5 = zzcuVar;
                                iZzi = iZzf;
                                unsafe4 = unsafe7;
                            } else {
                                zzcuVar4 = zzcuVar;
                                zzfpVar = zzfpVar2;
                                i31 = i44 == true ? 1 : 0;
                                obj2 = obj6;
                                unsafe = unsafe7;
                                i9 = i39;
                                i21 = iZzi;
                                i8 = i63;
                                i22 = i41;
                                i4 = i3;
                                i10 = i31;
                                iZzi = i21;
                                i41 = i22;
                                zzcuVar2 = zzcuVar4;
                            }
                            bArr2 = bArr;
                        }
                    }
                    if (i10 != i4 && i4 != 0) {
                        i5 = i2;
                        i38 = i10;
                        i39 = i9;
                    } else if (!zzfpVar.zzh) {
                        zzds zzdsVar = zzcuVar2.zzd;
                        int i105 = zzds.zzb;
                        int i106 = zzfu.zza;
                        if (zzdsVar != zzds.zza) {
                            zzfm zzfmVar = zzfpVar.zzg;
                            zzgs zzgsVar2 = zzfpVar.zzl;
                            zzds zzdsVar2 = zzcuVar2.zzd;
                            int i107 = zzcv.zza;
                            zzef zzefVarZzb = zzdsVar2.zzb(zzfmVar, i8);
                            if (zzefVarZzb == null) {
                                i12 = i2;
                                i11 = i10;
                                iZzi = zzcv.zzi(i11 == true ? 1 : 0, bArr2, iZzi, i12, zzd(obj2), zzcuVar2);
                            } else {
                                zzed zzedVar = (zzed) obj2;
                                zzedVar.zzc();
                                i12 = i2;
                                i11 = i10 == true ? 1 : 0;
                                iZzi = zzcv.zzb(i11 == true ? 1 : 0, bArr, iZzi, i12, zzedVar, zzefVarZzb, zzgsVar2, zzcuVar);
                            }
                        } else {
                            i11 = i10;
                            i12 = i2;
                            iZzi = zzcv.zzi(i11 == true ? 1 : 0, bArr, iZzi, i12, zzd(obj2), zzcuVar);
                        }
                        bArr6 = bArr;
                        zzcuVar5 = zzcuVar;
                        i38 = i11;
                        i35 = i12;
                        obj5 = obj2;
                        i37 = i6;
                        i36 = i7;
                        i39 = i9;
                        unsafe4 = unsafe;
                        i40 = i8;
                        zzfpVar2 = zzfpVar;
                    }
                } else {
                    if (i45 >= zzfpVar2.zze && i45 <= zzfpVar2.zzf) {
                        iZzq = zzfpVar2.zzq(i45, i36);
                    }
                    if (iZzq != -1) {
                    }
                    if (i10 != i4) {
                    }
                    if (!zzfpVar.zzh) {
                    }
                }
            } else {
                i4 = i3;
                zzfpVar = zzfpVar2;
                unsafe = unsafe4;
                obj2 = obj5;
                i5 = i35;
            }
        }
        if (i41 != 1048575) {
            unsafe.putInt(obj2, i41, i39);
        }
        for (int i108 = zzfpVar.zzj; i108 < zzfpVar.zzk; i108++) {
            int[] iArr2 = zzfpVar.zzi;
            int[] iArr3 = zzfpVar.zzc;
            int i109 = iArr2[i108];
            int i110 = iArr3[i109];
            Object objZzf = zzgz.zzf(obj2, zzfpVar.zzs(i109) & 1048575);
            if (objZzf != null && zzfpVar.zzu(i109) != null) {
                throw null;
            }
        }
        if (i4 == 0) {
            if (iZzi != i5) {
                throw new zzer("Failed to parse the message.");
            }
        } else if (iZzi > i5 || i38 != i4) {
            throw new zzer("Failed to parse the message.");
        }
        return iZzi;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final Object zze() {
        return ((zzeh) this.zzg).zzK();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0071  */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzf(Object obj) {
        if (zzL(obj)) {
            if (obj instanceof zzeh) {
                zzeh zzehVar = (zzeh) obj;
                zzehVar.zzW(Integer.MAX_VALUE);
                zzehVar.zza = 0;
                zzehVar.zzU();
            }
            int[] iArr = this.zzc;
            for (int i = 0; i < iArr.length; i += 3) {
                int iZzs = zzs(i);
                int i2 = 1048575 & iZzs;
                int iZzr = zzr(iZzs);
                long j = i2;
                if (iZzr != 9) {
                    if (iZzr != 60 && iZzr != 68) {
                        switch (iZzr) {
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                            case 49:
                                ((zzeo) zzgz.zzf(obj, j)).zzb();
                                break;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzfg) object).zzc();
                                    unsafe.putObject(obj, j, object);
                                }
                                break;
                        }
                    } else if (zzM(obj, this.zzc[i], i)) {
                        zzv(i).zzf(zzb.getObject(obj, j));
                    }
                } else if (zzI(obj, i)) {
                    zzv(i).zzf(zzb.getObject(obj, j));
                }
            }
            this.zzl.zza(obj);
            if (this.zzh) {
                this.zzm.zza(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final void zzg(Object obj, Object obj2) {
        zzA(obj);
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzs = zzs(i);
            int i2 = 1048575 & iZzs;
            int[] iArr = this.zzc;
            int iZzr = zzr(iZzs);
            int i3 = iArr[i];
            long j = i2;
            switch (iZzr) {
                case 0:
                    if (zzI(obj2, i)) {
                        zzgz.zzo(obj, j, zzgz.zza(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 1:
                    if (zzI(obj2, i)) {
                        zzgz.zzp(obj, j, zzgz.zzb(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 2:
                    if (zzI(obj2, i)) {
                        zzgz.zzr(obj, j, zzgz.zzd(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 3:
                    if (zzI(obj2, i)) {
                        zzgz.zzr(obj, j, zzgz.zzd(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 4:
                    if (zzI(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 5:
                    if (zzI(obj2, i)) {
                        zzgz.zzr(obj, j, zzgz.zzd(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 6:
                    if (zzI(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 7:
                    if (zzI(obj2, i)) {
                        zzgz.zzm(obj, j, zzgz.zzw(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 8:
                    if (zzI(obj2, i)) {
                        zzgz.zzs(obj, j, zzgz.zzf(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 9:
                    zzB(obj, obj2, i);
                    break;
                case 10:
                    if (zzI(obj2, i)) {
                        zzgz.zzs(obj, j, zzgz.zzf(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 11:
                    if (zzI(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 12:
                    if (zzI(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 13:
                    if (zzI(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 14:
                    if (zzI(obj2, i)) {
                        zzgz.zzr(obj, j, zzgz.zzd(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 15:
                    if (zzI(obj2, i)) {
                        zzgz.zzq(obj, j, zzgz.zzc(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 16:
                    if (zzI(obj2, i)) {
                        zzgz.zzr(obj, j, zzgz.zzd(obj2, j));
                        zzD(obj, i);
                    }
                    break;
                case 17:
                    zzB(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case 49:
                    zzeo zzeoVarZzd = (zzeo) zzgz.zzf(obj, j);
                    zzeo zzeoVar = (zzeo) zzgz.zzf(obj2, j);
                    int size = zzeoVarZzd.size();
                    int size2 = zzeoVar.size();
                    if (size > 0 && size2 > 0) {
                        if (!zzeoVarZzd.zzc()) {
                            zzeoVarZzd = zzeoVarZzd.zzd(size2 + size);
                        }
                        zzeoVarZzd.addAll(zzeoVar);
                    }
                    if (size > 0) {
                        zzeoVar = zzeoVarZzd;
                    }
                    zzgz.zzs(obj, j, zzeoVar);
                    break;
                case 50:
                    int i4 = zzgg.zza;
                    zzgz.zzs(obj, j, zzfh.zza(zzgz.zzf(obj, j), zzgz.zzf(obj2, j)));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                case 53:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzM(obj2, i3, i)) {
                        zzgz.zzs(obj, j, zzgz.zzf(obj2, j));
                        zzE(obj, i3, i);
                    }
                    break;
                case 60:
                    zzC(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzM(obj2, i3, i)) {
                        zzgz.zzs(obj, j, zzgz.zzf(obj2, j));
                        zzE(obj, i3, i);
                    }
                    break;
                case 68:
                    zzC(obj, obj2, i);
                    break;
            }
        }
        zzgg.zzp(this.zzl, obj, obj2);
        if (this.zzh) {
            zzgg.zzo(this.zzm, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzcu zzcuVar) throws IOException {
        zzc(obj, bArr, i, i2, 0, zzcuVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0023  */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzi(Object obj, zzhh zzhhVar) throws IOException {
        Map.Entry entry;
        Iterator it;
        int i;
        Map.Entry entry2;
        int i2;
        int i3;
        int i4;
        zzfp<T> zzfpVar = this;
        if (zzfpVar.zzh) {
            zzdx zzdxVar = ((zzed) obj).zzb;
            if (zzdxVar.zza.isEmpty()) {
                entry = null;
                it = null;
            } else {
                Iterator itZzf = zzdxVar.zzf();
                entry = (Map.Entry) itZzf.next();
                it = itZzf;
            }
        }
        int[] iArr = zzfpVar.zzc;
        Unsafe unsafe = zzb;
        int i5 = 0;
        int i6 = 1048575;
        int i7 = 0;
        while (i5 < iArr.length) {
            int iZzs = zzfpVar.zzs(i5);
            int[] iArr2 = zzfpVar.zzc;
            int iZzr = zzr(iZzs);
            int i8 = iArr2[i5];
            if (iZzr <= 17) {
                int i9 = iArr2[i5 + 2];
                int i10 = i9 & 1048575;
                if (i10 != i6) {
                    i = 1;
                    i7 = i10 == 1048575 ? 0 : unsafe.getInt(obj, i10);
                    i6 = i10;
                } else {
                    i = 1;
                }
                entry2 = entry;
                i2 = i6;
                i3 = i7;
                i4 = i << (i9 >>> 20);
            } else {
                i = 1;
                entry2 = entry;
                i2 = i6;
                i3 = i7;
                i4 = 0;
            }
            while (entry2 != null && ((zzee) entry2.getKey()).zza <= i8) {
                zzfpVar.zzm.zzb(zzhhVar, entry2);
                entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            long j = iZzs & 1048575;
            switch (iZzr) {
                case 0:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzf(i8, zzgz.zza(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 1:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzo(i8, zzgz.zzb(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 2:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzt(i8, unsafe.getLong(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 3:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzK(i8, unsafe.getLong(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 4:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzr(i8, unsafe.getInt(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 5:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzm(i8, unsafe.getLong(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 6:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzk(i8, unsafe.getInt(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 7:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzb(i8, zzgz.zzw(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 8:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzO(i8, unsafe.getObject(obj, j), zzhhVar);
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 9:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzv(i8, unsafe.getObject(obj, j), zzfpVar.zzv(i5));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 10:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzd(i8, (zzdf) unsafe.getObject(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 11:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzI(i8, unsafe.getInt(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 12:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzi(i8, unsafe.getInt(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 13:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzx(i8, unsafe.getInt(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 14:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzz(i8, unsafe.getLong(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 15:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzB(i8, unsafe.getInt(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 16:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzD(i8, unsafe.getLong(obj, j));
                    }
                    zzfpVar = this;
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 17:
                    if (zzfpVar.zzJ(obj, i5, i2, i3, i4)) {
                        zzhhVar.zzq(i8, unsafe.getObject(obj, j), zzfpVar.zzv(i5));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 18:
                    zzgg.zzr(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 19:
                    zzgg.zzv(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 20:
                    zzgg.zzx(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 21:
                    zzgg.zzD(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 22:
                    zzgg.zzw(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 23:
                    zzgg.zzu(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 24:
                    zzgg.zzt(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 25:
                    zzgg.zzq(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 26:
                    int i11 = zzfpVar.zzc[i5];
                    List list = (List) unsafe.getObject(obj, j);
                    int i12 = zzgg.zza;
                    if (list != null && !list.isEmpty()) {
                        zzhhVar.zzH(i11, list);
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 27:
                    int i13 = zzfpVar.zzc[i5];
                    List list2 = (List) unsafe.getObject(obj, j);
                    zzge zzgeVarZzv = zzfpVar.zzv(i5);
                    int i14 = zzgg.zza;
                    if (list2 != null && !list2.isEmpty()) {
                        for (int i15 = 0; i15 < list2.size(); i15++) {
                            ((zzdo) zzhhVar).zzv(i13, list2.get(i15), zzgeVarZzv);
                        }
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 28:
                    int i16 = zzfpVar.zzc[i5];
                    List list3 = (List) unsafe.getObject(obj, j);
                    int i17 = zzgg.zza;
                    if (list3 != null && !list3.isEmpty()) {
                        zzhhVar.zze(i16, list3);
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 29:
                    zzgg.zzC(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 30:
                    zzgg.zzs(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 31:
                    zzgg.zzy(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 32:
                    zzgg.zzz(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 33:
                    zzgg.zzA(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 34:
                    zzgg.zzB(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, false);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 35:
                    zzgg.zzr(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 36:
                    zzgg.zzv(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 37:
                    zzgg.zzx(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 38:
                    zzgg.zzD(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 39:
                    zzgg.zzw(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 40:
                    zzgg.zzu(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 41:
                    zzgg.zzt(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 42:
                    zzgg.zzq(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 43:
                    zzgg.zzC(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 44:
                    zzgg.zzs(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 45:
                    zzgg.zzy(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 46:
                    zzgg.zzz(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 47:
                    zzgg.zzA(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    zzgg.zzB(zzfpVar.zzc[i5], (List) unsafe.getObject(obj, j), zzhhVar, i);
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 49:
                    int i18 = zzfpVar.zzc[i5];
                    List list4 = (List) unsafe.getObject(obj, j);
                    zzge zzgeVarZzv2 = zzfpVar.zzv(i5);
                    int i19 = zzgg.zza;
                    if (list4 != null && !list4.isEmpty()) {
                        for (int i20 = 0; i20 < list4.size(); i20++) {
                            ((zzdo) zzhhVar).zzq(i18, list4.get(i20), zzgeVarZzv2);
                        }
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 50:
                    if (unsafe.getObject(obj, j) != null) {
                        throw null;
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzf(i8, zzm(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzo(i8, zzn(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 53:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzt(i8, zzt(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzK(i8, zzt(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 55:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzr(i8, zzo(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 56:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzm(i8, zzt(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 57:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzk(i8, zzo(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 58:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzb(i8, zzN(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 59:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzO(i8, unsafe.getObject(obj, j), zzhhVar);
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 60:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzv(i8, unsafe.getObject(obj, j), zzfpVar.zzv(i5));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzd(i8, (zzdf) unsafe.getObject(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 62:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzI(i8, zzo(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzi(i8, zzo(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 64:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzx(i8, zzo(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 65:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzz(i8, zzt(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzB(i8, zzo(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzD(i8, zzt(obj, j));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                case 68:
                    if (zzfpVar.zzM(obj, i8, i5)) {
                        zzhhVar.zzq(i8, unsafe.getObject(obj, j), zzfpVar.zzv(i5));
                    }
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
                default:
                    i5 += 3;
                    i7 = i3;
                    i6 = i2;
                    entry = entry2;
                    break;
            }
        }
        while (entry != null) {
            zzfpVar.zzm.zzb(zzhhVar, entry);
            entry = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        ((zzeh) obj).zzc.zzl(zzhhVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final boolean zzj(Object obj, Object obj2) {
        boolean zZzE;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzs = zzs(i);
            long j = iZzs & 1048575;
            switch (zzr(iZzs)) {
                case 0:
                    if (!zzH(obj, obj2, i) || Double.doubleToLongBits(zzgz.zza(obj, j)) != Double.doubleToLongBits(zzgz.zza(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 1:
                    if (!zzH(obj, obj2, i) || Float.floatToIntBits(zzgz.zzb(obj, j)) != Float.floatToIntBits(zzgz.zzb(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 2:
                    if (!zzH(obj, obj2, i) || zzgz.zzd(obj, j) != zzgz.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 3:
                    if (!zzH(obj, obj2, i) || zzgz.zzd(obj, j) != zzgz.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 4:
                    if (!zzH(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 5:
                    if (!zzH(obj, obj2, i) || zzgz.zzd(obj, j) != zzgz.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 6:
                    if (!zzH(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 7:
                    if (!zzH(obj, obj2, i) || zzgz.zzw(obj, j) != zzgz.zzw(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 8:
                    if (!zzH(obj, obj2, i) || !zzgg.zzE(zzgz.zzf(obj, j), zzgz.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 9:
                    if (!zzH(obj, obj2, i) || !zzgg.zzE(zzgz.zzf(obj, j), zzgz.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 10:
                    if (!zzH(obj, obj2, i) || !zzgg.zzE(zzgz.zzf(obj, j), zzgz.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 11:
                    if (!zzH(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 12:
                    if (!zzH(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 13:
                    if (!zzH(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 14:
                    if (!zzH(obj, obj2, i) || zzgz.zzd(obj, j) != zzgz.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 15:
                    if (!zzH(obj, obj2, i) || zzgz.zzc(obj, j) != zzgz.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 16:
                    if (!zzH(obj, obj2, i) || zzgz.zzd(obj, j) != zzgz.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 17:
                    if (!zzH(obj, obj2, i) || !zzgg.zzE(zzgz.zzf(obj, j), zzgz.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                case 49:
                    zZzE = zzgg.zzE(zzgz.zzf(obj, j), zzgz.zzf(obj2, j));
                    break;
                case 50:
                    zZzE = zzgg.zzE(zzgz.zzf(obj, j), zzgz.zzf(obj2, j));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                case 53:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE /* 54 */:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                case 68:
                    long jZzp = zzp(i) & 1048575;
                    if (zzgz.zzc(obj, jZzp) != zzgz.zzc(obj2, jZzp) || !zzgg.zzE(zzgz.zzf(obj, j), zzgz.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                    break;
                default:
                    break;
            }
            if (!zZzE) {
                return false;
            }
        }
        if (!((zzeh) obj).zzc.equals(((zzeh) obj2).zzc)) {
            return false;
        }
        if (this.zzh) {
            return ((zzed) obj).zzb.equals(((zzed) obj2).zzb);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x008d  */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zzk(Object obj) {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i3 < this.zzj) {
            int[] iArr = this.zzi;
            int[] iArr2 = this.zzc;
            int i6 = iArr[i3];
            int i7 = iArr2[i6];
            int iZzs = zzs(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i5) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(obj, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i5;
                i2 = i4;
            }
            Object obj2 = obj;
            if ((268435456 & iZzs) != 0 && !zzJ(obj2, i6, i, i2, i10)) {
                return false;
            }
            int iZzr = zzr(iZzs);
            if (iZzr == 9 || iZzr == 17) {
                if (zzJ(obj2, i6, i, i2, i10) && !zzK(obj2, iZzs, zzv(i6))) {
                    return false;
                }
            } else if (iZzr == 27) {
                List list = (List) zzgz.zzf(obj2, iZzs & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzge zzgeVarZzv = zzv(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzgeVarZzv.zzk(list.get(i11))) {
                            return false;
                        }
                    }
                }
            } else if (iZzr == 60 || iZzr == 68) {
                if (zzM(obj2, i7, i6) && !zzK(obj2, iZzs, zzv(i6))) {
                    return false;
                }
            } else if (iZzr != 49) {
                if (iZzr == 50 && !((zzfg) zzgz.zzf(obj2, iZzs & 1048575)).isEmpty()) {
                    throw null;
                }
            }
            i3++;
            obj = obj2;
            i5 = i;
            i4 = i2;
        }
        return !this.zzh || ((zzed) obj).zzb.zzk();
    }
}
