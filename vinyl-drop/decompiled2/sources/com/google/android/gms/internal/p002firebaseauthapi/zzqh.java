package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@24.0.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzqh {
    private final Map<zzqj, zznx<?, ?>> zza;
    private final Map<zzqk, zznt<?>> zzb;
    private final Map<zzqj, zzph<?, ?>> zzc;
    private final Map<zzqk, zzpd<?>> zzd;

    public final <SerializationT extends zzqf> zzqh zza(zznt<SerializationT> zzntVar) throws GeneralSecurityException {
        zzqk zzqkVar = new zzqk(zzntVar.zzb(), zzntVar.zza());
        boolean zContainsKey = this.zzb.containsKey(zzqkVar);
        Map<zzqk, zznt<?>> map = this.zzb;
        if (!zContainsKey) {
            map.put(zzqkVar, zzntVar);
            return this;
        }
        zznt<?> zzntVar2 = map.get(zzqkVar);
        if (zzntVar2.equals(zzntVar) && zzntVar.equals(zzntVar2)) {
            return this;
        }
        throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + String.valueOf(zzqkVar));
    }

    public final <KeyT extends zzbi, SerializationT extends zzqf> zzqh zza(zznx<KeyT, SerializationT> zznxVar) throws GeneralSecurityException {
        zzqj zzqjVar = new zzqj(zznxVar.zza(), zznxVar.zzb());
        boolean zContainsKey = this.zza.containsKey(zzqjVar);
        Map<zzqj, zznx<?, ?>> map = this.zza;
        if (zContainsKey) {
            zznx<?, ?> zznxVar2 = map.get(zzqjVar);
            if (zznxVar2.equals(zznxVar) && zznxVar.equals(zznxVar2)) {
                return this;
            }
            throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + String.valueOf(zzqjVar));
        }
        map.put(zzqjVar, zznxVar);
        return this;
    }

    public final <SerializationT extends zzqf> zzqh zza(zzpd<SerializationT> zzpdVar) throws GeneralSecurityException {
        zzqk zzqkVar = new zzqk(zzpdVar.zzb(), zzpdVar.zza());
        boolean zContainsKey = this.zzd.containsKey(zzqkVar);
        Map<zzqk, zzpd<?>> map = this.zzd;
        if (zContainsKey) {
            zzpd<?> zzpdVar2 = map.get(zzqkVar);
            if (zzpdVar2.equals(zzpdVar) && zzpdVar.equals(zzpdVar2)) {
                return this;
            }
            throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + String.valueOf(zzqkVar));
        }
        map.put(zzqkVar, zzpdVar);
        return this;
    }

    public final <ParametersT extends zzcb, SerializationT extends zzqf> zzqh zza(zzph<ParametersT, SerializationT> zzphVar) throws GeneralSecurityException {
        zzqj zzqjVar = new zzqj(zzphVar.zza(), zzphVar.zzb());
        boolean zContainsKey = this.zzc.containsKey(zzqjVar);
        Map<zzqj, zzph<?, ?>> map = this.zzc;
        if (zContainsKey) {
            zzph<?, ?> zzphVar2 = map.get(zzqjVar);
            if (zzphVar2.equals(zzphVar) && zzphVar.equals(zzphVar2)) {
                return this;
            }
            throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + String.valueOf(zzqjVar));
        }
        map.put(zzqjVar, zzphVar);
        return this;
    }

    public final zzqi zza() {
        return new zzqi(this);
    }

    public zzqh() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new HashMap();
        this.zzd = new HashMap();
    }

    public zzqh(zzqi zzqiVar) {
        this.zza = new HashMap(zzqiVar.zza);
        this.zzb = new HashMap(zzqiVar.zzb);
        this.zzc = new HashMap(zzqiVar.zzc);
        this.zzd = new HashMap(zzqiVar.zzd);
    }
}
