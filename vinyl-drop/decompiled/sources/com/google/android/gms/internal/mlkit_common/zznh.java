package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* JADX INFO: compiled from: com.google.mlkit:common@@18.11.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznh {
    private final String zza;
    private final zznf zzc;
    private final String zze;
    private final zzne zzf;
    private final String zzb = null;
    private final String zzd = null;
    private final Long zzg = null;
    private final Boolean zzh = null;
    private final Boolean zzi = null;

    /* synthetic */ zznh(zznd zzndVar, zzng zzngVar) {
        this.zza = zzndVar.zza;
        this.zzc = zzndVar.zzb;
        this.zze = zzndVar.zzc;
        this.zzf = zzndVar.zzd;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zznh)) {
            return false;
        }
        zznh zznhVar = (zznh) obj;
        if (Objects.equal(this.zza, zznhVar.zza)) {
            String str = zznhVar.zzb;
            if (Objects.equal(null, null) && Objects.equal(this.zzc, zznhVar.zzc)) {
                String str2 = zznhVar.zzd;
                if (Objects.equal(null, null) && Objects.equal(this.zze, zznhVar.zze) && Objects.equal(this.zzf, zznhVar.zzf)) {
                    Long l = zznhVar.zzg;
                    if (Objects.equal(null, null)) {
                        Boolean bool = zznhVar.zzh;
                        if (Objects.equal(null, null)) {
                            Boolean bool2 = zznhVar.zzi;
                            if (Objects.equal(null, null)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze, this.zzf, null, null, null);
    }

    public final zzne zza() {
        return this.zzf;
    }

    public final zznf zzb() {
        return this.zzc;
    }

    public final String zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zza;
    }
}
