package com.google.firebase.appcheck.internal;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class DefaultTokenRefresher$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DefaultTokenRefresher f$0;

    public /* synthetic */ DefaultTokenRefresher$$ExternalSyntheticLambda1(DefaultTokenRefresher defaultTokenRefresher) {
        this.f$0 = defaultTokenRefresher;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.onRefresh();
    }
}
