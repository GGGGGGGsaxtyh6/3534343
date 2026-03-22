package com.google.android.gms.internal.mlkit_vision_barcode;

/* JADX INFO: compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzqh {
    private zzqi zza;
    private Integer zzb;

    public final zzqh zza(zzqi zzqiVar) {
        this.zza = zzqiVar;
        return this;
    }

    public final zzqh zzb(Integer num) {
        this.zzb = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzqk zzd() {
        return new zzqk(this, null);
    }
}
