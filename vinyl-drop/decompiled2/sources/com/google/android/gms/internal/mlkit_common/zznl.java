package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* JADX INFO: compiled from: com.google.mlkit:common@@18.11.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznl {
    private final zznh zza;
    private final zznj zzb = null;
    private final zznj zzc = null;
    private final Boolean zzd = null;

    /* synthetic */ zznl(zzni zzniVar, zznk zznkVar) {
        this.zza = zzniVar.zza;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zznl)) {
            return false;
        }
        zznl zznlVar = (zznl) obj;
        if (Objects.equal(this.zza, zznlVar.zza)) {
            zznj zznjVar = zznlVar.zzb;
            if (Objects.equal(null, null)) {
                zznj zznjVar2 = zznlVar.zzc;
                if (Objects.equal(null, null)) {
                    Boolean bool = zznlVar.zzd;
                    if (Objects.equal(null, null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, null, null);
    }

    public final zznh zza() {
        return this.zza;
    }
}
