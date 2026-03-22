package com.google.firebase.functions;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.functions.FunctionsComponent;
import com.google.firebase.functions.dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class FunctionsComponent_MainModule_Companion_BindProjectIdFactory implements Factory<String> {
    private final Provider<FirebaseOptions> optionsProvider;

    public FunctionsComponent_MainModule_Companion_BindProjectIdFactory(Provider<FirebaseOptions> provider) {
        this.optionsProvider = provider;
    }

    @Override // javax.inject.Provider
    public String get() {
        return bindProjectId(this.optionsProvider.get());
    }

    public static FunctionsComponent_MainModule_Companion_BindProjectIdFactory create(Provider<FirebaseOptions> provider) {
        return new FunctionsComponent_MainModule_Companion_BindProjectIdFactory(provider);
    }

    public static String bindProjectId(FirebaseOptions firebaseOptions) {
        return FunctionsComponent.MainModule.INSTANCE.bindProjectId(firebaseOptions);
    }
}
