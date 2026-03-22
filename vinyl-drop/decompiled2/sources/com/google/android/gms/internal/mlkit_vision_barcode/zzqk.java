package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Objects;

/* JADX INFO: compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzqk {
    private final zzqi zza;
    private final Integer zzb;
    private final Integer zzc = null;
    private final Boolean zzd = null;

    /* synthetic */ zzqk(zzqh zzqhVar, zzqj zzqjVar) {
        this.zza = zzqhVar.zza;
        this.zzb = zzqhVar.zzb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzqk)) {
            return false;
        }
        zzqk zzqkVar = (zzqk) obj;
        if (Objects.equal(this.zza, zzqkVar.zza) && Objects.equal(this.zzb, zzqkVar.zzb)) {
            Integer num = zzqkVar.zzc;
            if (Objects.equal(null, null)) {
                Boolean bool = zzqkVar.zzd;
                if (Objects.equal(null, null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, null, null);
    }

    public final zzqi zza() {
        return this.zza;
    }

    public final Integer zzb() {
        return this.zzb;
    }
}
