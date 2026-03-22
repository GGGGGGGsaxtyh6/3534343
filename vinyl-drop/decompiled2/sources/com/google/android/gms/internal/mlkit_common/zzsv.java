package com.google.android.gms.internal.mlkit_common;

/* JADX INFO: compiled from: com.google.mlkit:common@@18.11.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzsv {
    private static zzsv zza;

    private zzsv() {
    }

    public static synchronized zzsv zza() {
        if (zza == null) {
            zza = new zzsv();
        }
        return zza;
    }

    public static void zzb() {
        zzsu.zza();
    }
}
