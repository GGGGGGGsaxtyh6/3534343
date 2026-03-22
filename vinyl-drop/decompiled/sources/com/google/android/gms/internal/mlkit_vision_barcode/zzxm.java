package com.google.android.gms.internal.mlkit_vision_barcode;

/* JADX INFO: compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzxm {
    public static final zzxm zza;

    static {
        zzm().zzm();
        zzxl zzxlVarZzm = zzm();
        zzxlVarZzm.zzi(false);
        zza = zzxlVarZzm.zzm();
    }

    public static zzxl zzm() {
        zzxd zzxdVar = new zzxd();
        zzxdVar.zzg(10);
        zzxdVar.zze(5);
        zzxdVar.zzf(0.25f);
        zzxdVar.zzd(0.8f);
        zzxdVar.zzi(true);
        zzxdVar.zzc(0.5f);
        zzxdVar.zzb(0.8f);
        zzxdVar.zzk(1500L);
        zzxdVar.zzh(3000L);
        zzxdVar.zza(true);
        zzxdVar.zzj(0.1f);
        zzxdVar.zzl(0.05f);
        return zzxdVar;
    }

    abstract float zza();

    abstract float zzb();

    abstract float zzc();

    abstract float zzd();

    abstract float zze();

    abstract float zzf();

    abstract int zzg();

    abstract int zzh();

    abstract long zzi();

    abstract long zzj();

    abstract boolean zzk();

    abstract boolean zzl();
}
