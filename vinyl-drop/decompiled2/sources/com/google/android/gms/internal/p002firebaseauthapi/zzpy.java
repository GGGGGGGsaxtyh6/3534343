package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@24.0.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzpy {
    private final Map<zzpx, zzps<?, ?>> zza;
    private final Map<Class<?>, zzpz<?, ?>> zzb;

    public final <KeyT extends zzbi, PrimitiveT> zzpy zza(zzps<KeyT, PrimitiveT> zzpsVar) throws GeneralSecurityException {
        if (zzpsVar == null) {
            throw new NullPointerException("primitive constructor must be non-null");
        }
        zzpx zzpxVar = new zzpx(zzpsVar.zza(), zzpsVar.zzb());
        boolean zContainsKey = this.zza.containsKey(zzpxVar);
        Map<zzpx, zzps<?, ?>> map = this.zza;
        if (!zContainsKey) {
            map.put(zzpxVar, zzpsVar);
            return this;
        }
        zzps<?, ?> zzpsVar2 = map.get(zzpxVar);
        if (zzpsVar2.equals(zzpsVar) && zzpsVar.equals(zzpsVar2)) {
            return this;
        }
        throw new GeneralSecurityException("Attempt to register non-equal PrimitiveConstructor object for already existing object of type: " + String.valueOf(zzpxVar));
    }

    public final <InputPrimitiveT, WrapperPrimitiveT> zzpy zza(zzpz<InputPrimitiveT, WrapperPrimitiveT> zzpzVar) throws GeneralSecurityException {
        if (zzpzVar == null) {
            throw new NullPointerException("wrapper must be non-null");
        }
        Class<WrapperPrimitiveT> clsZzb = zzpzVar.zzb();
        boolean zContainsKey = this.zzb.containsKey(clsZzb);
        Map<Class<?>, zzpz<?, ?>> map = this.zzb;
        if (zContainsKey) {
            zzpz<?, ?> zzpzVar2 = map.get(clsZzb);
            if (zzpzVar2.equals(zzpzVar) && zzpzVar.equals(zzpzVar2)) {
                return this;
            }
            throw new GeneralSecurityException("Attempt to register non-equal PrimitiveWrapper object or input class object for already existing object of type" + String.valueOf(clsZzb));
        }
        map.put(clsZzb, zzpzVar);
        return this;
    }

    public final zzpw zza() {
        return new zzpw(this);
    }

    private zzpy() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
    }

    private zzpy(zzpw zzpwVar) {
        this.zza = new HashMap(zzpwVar.zza);
        this.zzb = new HashMap(zzpwVar.zzb);
    }
}
