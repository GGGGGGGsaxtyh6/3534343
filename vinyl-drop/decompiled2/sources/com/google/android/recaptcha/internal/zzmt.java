package com.google.android.recaptcha.internal;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzmt {
    private static final zzmt zzb = new zzmt(true);
    final zzpe zza = new zzoz();
    private boolean zzc;
    private boolean zzd;

    private zzmt() {
    }

    public static int zza(zzms zzmsVar, Object obj) {
        int iZzd;
        int iZzA;
        zzpw zzpwVarZzd = zzmsVar.zzd();
        int iZza = zzmsVar.zza();
        zzmsVar.zzg();
        int iZzA2 = zzln.zzA(iZza << 3);
        if (zzpwVarZzd == zzpw.GROUP) {
            zzoi zzoiVar = (zzoi) obj;
            byte[] bArr = zznl.zzb;
            if (zzoiVar instanceof zzkp) {
                throw null;
            }
            iZzA2 += iZzA2;
        }
        zzpx zzpxVar = zzpx.INT;
        int iZzB = 4;
        switch (zzpwVarZzd) {
            case DOUBLE:
                ((Double) obj).doubleValue();
                iZzB = 8;
                return iZzA2 + iZzB;
            case FLOAT:
                ((Float) obj).floatValue();
                return iZzA2 + iZzB;
            case INT64:
                iZzB = zzln.zzB(((Long) obj).longValue());
                return iZzA2 + iZzB;
            case UINT64:
                iZzB = zzln.zzB(((Long) obj).longValue());
                return iZzA2 + iZzB;
            case INT32:
                iZzB = zzln.zzB(((Integer) obj).intValue());
                return iZzA2 + iZzB;
            case FIXED64:
                ((Long) obj).longValue();
                iZzB = 8;
                return iZzA2 + iZzB;
            case FIXED32:
                ((Integer) obj).intValue();
                return iZzA2 + iZzB;
            case BOOL:
                ((Boolean) obj).booleanValue();
                iZzB = 1;
                return iZzA2 + iZzB;
            case STRING:
                if (!(obj instanceof zzle)) {
                    iZzB = zzln.zzz((String) obj);
                    return iZzA2 + iZzB;
                }
                iZzd = ((zzle) obj).zzd();
                iZzA = zzln.zzA(iZzd);
                iZzB = iZzA + iZzd;
                return iZzA2 + iZzB;
            case GROUP:
                iZzB = ((zzoi) obj).zzo();
                return iZzA2 + iZzB;
            case MESSAGE:
                if (!(obj instanceof zzns)) {
                    iZzB = zzln.zzx((zzoi) obj);
                    return iZzA2 + iZzB;
                }
                iZzd = ((zzns) obj).zza();
                iZzA = zzln.zzA(iZzd);
                iZzB = iZzA + iZzd;
                return iZzA2 + iZzB;
            case BYTES:
                if (obj instanceof zzle) {
                    iZzd = ((zzle) obj).zzd();
                    iZzA = zzln.zzA(iZzd);
                } else {
                    iZzd = ((byte[]) obj).length;
                    iZzA = zzln.zzA(iZzd);
                }
                iZzB = iZzA + iZzd;
                return iZzA2 + iZzB;
            case UINT32:
                iZzB = zzln.zzA(((Integer) obj).intValue());
                return iZzA2 + iZzB;
            case ENUM:
                iZzB = obj instanceof zznf ? zzln.zzB(((zznf) obj).zza()) : zzln.zzB(((Integer) obj).intValue());
                return iZzA2 + iZzB;
            case SFIXED32:
                ((Integer) obj).intValue();
                return iZzA2 + iZzB;
            case SFIXED64:
                ((Long) obj).longValue();
                iZzB = 8;
                return iZzA2 + iZzB;
            case SINT32:
                int iIntValue = ((Integer) obj).intValue();
                iZzB = zzln.zzA((iIntValue >> 31) ^ (iIntValue + iIntValue));
                return iZzA2 + iZzB;
            case SINT64:
                long jLongValue = ((Long) obj).longValue();
                iZzB = zzln.zzB((jLongValue >> 63) ^ (jLongValue + jLongValue));
                return iZzA2 + iZzB;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static zzmt zzd() {
        return zzb;
    }

    private static Object zzl(Object obj) {
        if (obj instanceof zzon) {
            return ((zzon) obj).zzd();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private final void zzm(Map.Entry entry) {
        zzms zzmsVar = (zzms) entry.getKey();
        Object value = entry.getValue();
        boolean z = value instanceof zzns;
        zzmsVar.zzg();
        if (zzmsVar.zze() != zzpx.MESSAGE) {
            if (z) {
                throw new IllegalStateException("Lazy fields must be message-valued");
            }
            this.zza.put(zzmsVar, zzl(value));
            return;
        }
        Object objZze = zze(zzmsVar);
        if (objZze == null) {
            this.zza.put(zzmsVar, zzl(value));
            if (z) {
                this.zzd = true;
                return;
            }
            return;
        }
        if (z) {
            throw null;
        }
        this.zza.put(zzmsVar, objZze instanceof zzon ? zzmsVar.zzc((zzon) objZze, (zzon) value) : zzmsVar.zzb(((zzoi) objZze).zzae(), (zzoi) value).zzk());
    }

    private static boolean zzn(Map.Entry entry) {
        zzms zzmsVar = (zzms) entry.getKey();
        if (zzmsVar.zze() != zzpx.MESSAGE) {
            return true;
        }
        zzmsVar.zzg();
        Object value = entry.getValue();
        if (value instanceof zzoj) {
            return ((zzoj) value).zzp();
        }
        if (value instanceof zzns) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzo(Map.Entry entry) {
        int i;
        int iZzA;
        int iZzA2;
        zzms zzmsVar = (zzms) entry.getKey();
        Object value = entry.getValue();
        if (zzmsVar.zze() != zzpx.MESSAGE) {
            return zza(zzmsVar, value);
        }
        zzmsVar.zzg();
        zzmsVar.zzf();
        if (value instanceof zzns) {
            int iZza = ((zzms) entry.getKey()).zza();
            int iZzA3 = zzln.zzA(8);
            i = iZzA3 + iZzA3;
            iZzA = zzln.zzA(16) + zzln.zzA(iZza);
            int iZzA4 = zzln.zzA(24);
            int iZza2 = ((zzns) value).zza();
            iZzA2 = iZzA4 + zzln.zzA(iZza2) + iZza2;
        } else {
            int iZza3 = ((zzms) entry.getKey()).zza();
            int iZzA5 = zzln.zzA(8);
            i = iZzA5 + iZzA5;
            iZzA = zzln.zzA(16) + zzln.zzA(iZza3);
            iZzA2 = zzln.zzA(24) + zzln.zzx((zzoi) value);
        }
        return i + iZzA + iZzA2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzmt) {
            return this.zza.equals(((zzmt) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzb() {
        zzpe zzpeVar;
        int iZzc = this.zza.zzc();
        int i = 0;
        int iZzo = 0;
        while (true) {
            zzpeVar = this.zza;
            if (i >= iZzc) {
                break;
            }
            iZzo += zzo(zzpeVar.zzg(i));
            i++;
        }
        Iterator it = zzpeVar.zzd().iterator();
        while (it.hasNext()) {
            iZzo += zzo((Map.Entry) it.next());
        }
        return iZzo;
    }

    /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final zzmt clone() {
        zzpe zzpeVar;
        zzmt zzmtVar = new zzmt();
        int iZzc = this.zza.zzc();
        int i = 0;
        while (true) {
            zzpeVar = this.zza;
            if (i >= iZzc) {
                break;
            }
            Map.Entry entryZzg = zzpeVar.zzg(i);
            zzmtVar.zzi((zzms) ((zzpa) entryZzg).zza(), entryZzg.getValue());
            i++;
        }
        for (Map.Entry entry : zzpeVar.zzd()) {
            zzmtVar.zzi((zzms) entry.getKey(), entry.getValue());
        }
        zzmtVar.zzd = this.zzd;
        return zzmtVar;
    }

    public final Object zze(zzms zzmsVar) {
        Object obj = this.zza.get(zzmsVar);
        if (!(obj instanceof zzns)) {
            return obj;
        }
        throw null;
    }

    public final Iterator zzf() {
        if (this.zza.isEmpty()) {
            return Collections.emptyIterator();
        }
        boolean z = this.zzd;
        zzpe zzpeVar = this.zza;
        return z ? new zznq(zzpeVar.entrySet().iterator()) : zzpeVar.entrySet().iterator();
    }

    public final void zzg() {
        zzpe zzpeVar;
        if (this.zzc) {
            return;
        }
        int iZzc = this.zza.zzc();
        int i = 0;
        while (true) {
            zzpeVar = this.zza;
            if (i >= iZzc) {
                break;
            }
            Object value = zzpeVar.zzg(i).getValue();
            if (value instanceof zznd) {
                ((zznd) value).zzG();
            }
            i++;
        }
        Iterator it = zzpeVar.zzd().iterator();
        while (it.hasNext()) {
            Object value2 = ((Map.Entry) it.next()).getValue();
            if (value2 instanceof zznd) {
                ((zznd) value2).zzG();
            }
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzh(zzmt zzmtVar) {
        int iZzc = zzmtVar.zza.zzc();
        for (int i = 0; i < iZzc; i++) {
            zzm(zzmtVar.zza.zzg(i));
        }
        Iterator it = zzmtVar.zza.zzd().iterator();
        while (it.hasNext()) {
            zzm((Map.Entry) it.next());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
    
        if ((r4 instanceof com.google.android.recaptcha.internal.zznf) == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0034, code lost:
    
        if ((r4 instanceof byte[]) == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0048, code lost:
    
        if (r0 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0022, code lost:
    
        if ((r4 instanceof com.google.android.recaptcha.internal.zzns) == false) goto L32;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzi(zzms zzmsVar, Object obj) {
        boolean z;
        zzmsVar.zzg();
        zzmsVar.zzd();
        byte[] bArr = zznl.zzb;
        obj.getClass();
        zzpw zzpwVar = zzpw.DOUBLE;
        zzpx zzpxVar = zzpx.INT;
        switch (r0.zza()) {
            case INT:
                z = obj instanceof Integer;
                break;
            case LONG:
                z = obj instanceof Long;
                break;
            case FLOAT:
                z = obj instanceof Float;
                break;
            case DOUBLE:
                z = obj instanceof Double;
                break;
            case BOOLEAN:
                z = obj instanceof Boolean;
                break;
            case STRING:
                z = obj instanceof String;
                break;
            case BYTE_STRING:
                if (!(obj instanceof zzle)) {
                    break;
                }
                if (obj instanceof zzns) {
                    this.zzd = true;
                }
                this.zza.put(zzmsVar, obj);
                return;
            case ENUM:
                if (!(obj instanceof Integer)) {
                    break;
                }
                if (obj instanceof zzns) {
                }
                this.zza.put(zzmsVar, obj);
                return;
            case MESSAGE:
                if (!(obj instanceof zzoi)) {
                    break;
                }
                if (obj instanceof zzns) {
                }
                this.zza.put(zzmsVar, obj);
                return;
            default:
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzmsVar.zza()), zzmsVar.zzd().zza(), obj.getClass().getName()));
        }
    }

    public final boolean zzj() {
        return this.zzc;
    }

    public final boolean zzk() {
        int iZzc = this.zza.zzc();
        int i = 0;
        while (true) {
            zzpe zzpeVar = this.zza;
            if (i >= iZzc) {
                Iterator it = zzpeVar.zzd().iterator();
                while (it.hasNext()) {
                    if (!zzn((Map.Entry) it.next())) {
                        return false;
                    }
                }
                return true;
            }
            if (!zzn(zzpeVar.zzg(i))) {
                return false;
            }
            i++;
        }
    }

    private zzmt(boolean z) {
        zzg();
        zzg();
    }
}
