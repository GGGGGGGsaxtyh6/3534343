package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@24.0.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzox {
    private static final zzox zza = new zzox();
    private final Map<String, zzcb> zzb = new HashMap();

    public static zzox zza() {
        return zza;
    }

    zzox() {
    }

    private final synchronized void zza(String str, zzcb zzcbVar) throws GeneralSecurityException {
        boolean zContainsKey = this.zzb.containsKey(str);
        Map<String, zzcb> map = this.zzb;
        if (zContainsKey) {
            if (map.get(str).equals(zzcbVar)) {
                return;
            }
            throw new GeneralSecurityException("Parameters object with name " + str + " already exists (" + String.valueOf(this.zzb.get(str)) + "), cannot insert " + String.valueOf(zzcbVar));
        }
        map.put(str, zzcbVar);
    }

    public final synchronized void zza(Map<String, zzcb> map) throws GeneralSecurityException {
        for (Map.Entry<String, zzcb> entry : map.entrySet()) {
            zza(entry.getKey(), entry.getValue());
        }
    }
}
