package com.google.android.gms.common.api.internal;

import java.util.Objects;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zabi implements Runnable {
    final /* synthetic */ zabj zaa;

    zabi(zabj zabjVar) {
        Objects.requireNonNull(zabjVar);
        this.zaa = zabjVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zabk zabkVar = this.zaa.zaa;
        String name = zabkVar.zaz().getClass().getName();
        String.valueOf(name);
        zabkVar.zaz().disconnect(String.valueOf(name).concat(" disconnecting because it was signed out."));
    }
}
