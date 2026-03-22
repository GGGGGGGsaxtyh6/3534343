package com.google.android.gms.common.api.internal;

import java.util.Objects;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zaah implements Runnable {
    final /* synthetic */ zaar zaa;

    zaah(zaar zaarVar) {
        Objects.requireNonNull(zaarVar);
        this.zaa = zaarVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zaar zaarVar = this.zaa;
        zaarVar.zau().cancelAvailabilityErrorNotifications(zaarVar.zat());
    }
}
