package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzbj implements Iterator {
    final Iterator zza;

    @CheckForNull
    Object zzb = null;

    @CheckForNull
    Collection zzc = null;
    Iterator zzd = zzcw.INSTANCE;
    final /* synthetic */ zzbr zze;

    zzbj(zzbr zzbrVar) {
        this.zze = zzbrVar;
        this.zza = zzbrVar.zza.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext() || this.zzd.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.zzd.hasNext()) {
            Map.Entry entry = (Map.Entry) this.zza.next();
            this.zzb = entry.getKey();
            Collection collection = (Collection) entry.getValue();
            this.zzc = collection;
            this.zzd = collection.iterator();
        }
        return zza(this.zzb, this.zzd.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.zzd.remove();
        if (((Collection) Objects.requireNonNull(this.zzc)).isEmpty()) {
            this.zza.remove();
        }
        zzbr zzbrVar = this.zze;
        zzbrVar.zzb--;
    }

    abstract Object zza(Object obj, Object obj2);
}
