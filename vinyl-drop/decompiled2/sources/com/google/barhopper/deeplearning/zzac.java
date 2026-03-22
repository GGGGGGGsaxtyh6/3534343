package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

/* JADX INFO: compiled from: com.google.mlkit:barcode-scanning@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzac extends zzeh implements zzfn {
    private static final zzac zzb;
    private int zzd;
    private String zze = "";
    private zzdf zzf = zzdf.zzb;
    private String zzg = "";
    private zzdf zzh = zzdf.zzb;
    private float zzi = 0.25f;
    private float zzj = 0.25f;
    private float zzk = 0.5f;
    private float zzl = 0.85f;
    private int zzm = 1;

    static {
        zzac zzacVar = new zzac();
        zzb = zzacVar;
        zzeh.zzV(zzac.class, zzacVar);
    }

    private zzac() {
    }

    public static zzab zza() {
        return (zzab) zzb.zzG();
    }

    static /* synthetic */ void zzc(zzac zzacVar, zzdf zzdfVar) {
        zzdfVar.getClass();
        zzacVar.zzd |= 2;
        zzacVar.zzf = zzdfVar;
    }

    static /* synthetic */ void zzd(zzac zzacVar, zzdf zzdfVar) {
        zzdfVar.getClass();
        zzacVar.zzd |= 8;
        zzacVar.zzh = zzdfVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzS(zzb, "\u0004\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဈ\u0002\u0004ည\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007ခ\u0006\bခ\u0007\tင\b", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
        }
        if (i2 == 3) {
            return new zzac();
        }
        zzaa zzaaVar = null;
        if (i2 == 4) {
            return new zzab(zzaaVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
