package com.google.firebase.appcheck;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FirebaseAppCheck implements InteropAppCheckTokenProvider {

    public interface AppCheckListener {
        void onAppCheckTokenChanged(AppCheckToken appCheckToken);
    }

    public abstract void addAppCheckListener(AppCheckListener appCheckListener);

    public abstract Task<AppCheckToken> getAppCheckToken(boolean z);

    public abstract Task<AppCheckToken> getLimitedUseAppCheckToken();

    public abstract void installAppCheckProviderFactory(AppCheckProviderFactory appCheckProviderFactory);

    public abstract void installAppCheckProviderFactory(AppCheckProviderFactory appCheckProviderFactory, boolean z);

    public abstract void removeAppCheckListener(AppCheckListener appCheckListener);

    public abstract void setTokenAutoRefreshEnabled(boolean z);

    public static FirebaseAppCheck getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static FirebaseAppCheck getInstance(FirebaseApp firebaseApp) {
        return (FirebaseAppCheck) firebaseApp.get(FirebaseAppCheck.class);
    }
}
