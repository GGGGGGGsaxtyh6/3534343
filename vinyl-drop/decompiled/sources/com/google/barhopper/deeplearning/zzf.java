package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;

/* JADX INFO: compiled from: com.google.mlkit:barcode-scanning@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzf extends zzeh implements zzfn {
    private static final zzf zzb;
    private zzeo zzd = zzP();

    static {
        zzf zzfVar = new zzf();
        zzb = zzfVar;
        zzeh.zzV(zzf.class, zzfVar);
    }

    private zzf() {
    }

    public static zze zza() {
        return (zze) zzb.zzG();
    }

    static /* synthetic */ void zzc(zzf zzfVar, zzc zzcVar) {
        zzcVar.getClass();
        zzeo zzeoVar = zzfVar.zzd;
        if (!zzeoVar.zzc()) {
            zzfVar.zzd = zzeh.zzQ(zzeoVar);
        }
        zzfVar.zzd.add(zzcVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh
    protected final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzS(zzb, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzc.class});
        }
        if (i2 == 3) {
            return new zzf();
        }
        zzd zzdVar = null;
        if (i2 == 4) {
            return new zze(zzdVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
