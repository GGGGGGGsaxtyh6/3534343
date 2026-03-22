package com.google.firebase.functions;

import android.content.Context;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.functions.FunctionsComponent;
import com.google.firebase.functions.FunctionsMultiResourceComponent;
import com.google.firebase.functions.dagger.internal.DoubleCheck;
import com.google.firebase.functions.dagger.internal.Factory;
import com.google.firebase.functions.dagger.internal.InstanceFactory;
import com.google.firebase.functions.dagger.internal.Preconditions;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class DaggerFunctionsComponent {
    private DaggerFunctionsComponent() {
    }

    public static FunctionsComponent.Builder builder() {
        return new Builder();
    }

    private static final class Builder implements FunctionsComponent.Builder {
        private Deferred<InteropAppCheckTokenProvider> setAppCheck;
        private Context setApplicationContext;
        private Provider<InternalAuthProvider> setAuth;
        private FirebaseOptions setFirebaseOptions;
        private Provider<FirebaseInstanceIdInternal> setIid;
        private Executor setLiteExecutor;
        private Executor setUiExecutor;

        private Builder() {
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public /* bridge */ /* synthetic */ FunctionsComponent.Builder setAppCheck(Deferred deferred) {
            return setAppCheck((Deferred<InteropAppCheckTokenProvider>) deferred);
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public /* bridge */ /* synthetic */ FunctionsComponent.Builder setAuth(Provider provider) {
            return setAuth((Provider<InternalAuthProvider>) provider);
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public /* bridge */ /* synthetic */ FunctionsComponent.Builder setIid(Provider provider) {
            return setIid((Provider<FirebaseInstanceIdInternal>) provider);
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setApplicationContext(Context context) {
            this.setApplicationContext = (Context) Preconditions.checkNotNull(context);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setFirebaseOptions(FirebaseOptions firebaseOptions) {
            this.setFirebaseOptions = (FirebaseOptions) Preconditions.checkNotNull(firebaseOptions);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setLiteExecutor(Executor executor) {
            this.setLiteExecutor = (Executor) Preconditions.checkNotNull(executor);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setUiExecutor(Executor executor) {
            this.setUiExecutor = (Executor) Preconditions.checkNotNull(executor);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setAuth(Provider<InternalAuthProvider> provider) {
            this.setAuth = (Provider) Preconditions.checkNotNull(provider);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setIid(Provider<FirebaseInstanceIdInternal> provider) {
            this.setIid = (Provider) Preconditions.checkNotNull(provider);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setAppCheck(Deferred<InteropAppCheckTokenProvider> deferred) {
            this.setAppCheck = (Deferred) Preconditions.checkNotNull(deferred);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public FunctionsComponent build() {
            Preconditions.checkBuilderRequirement(this.setApplicationContext, Context.class);
            Preconditions.checkBuilderRequirement(this.setFirebaseOptions, FirebaseOptions.class);
            Preconditions.checkBuilderRequirement(this.setLiteExecutor, Executor.class);
            Preconditions.checkBuilderRequirement(this.setUiExecutor, Executor.class);
            Preconditions.checkBuilderRequirement(this.setAuth, Provider.class);
            Preconditions.checkBuilderRequirement(this.setIid, Provider.class);
            Preconditions.checkBuilderRequirement(this.setAppCheck, Deferred.class);
            return new FunctionsComponentImpl(this.setApplicationContext, this.setFirebaseOptions, this.setLiteExecutor, this.setUiExecutor, this.setAuth, this.setIid, this.setAppCheck);
        }
    }

    private static final class FunctionsComponentImpl implements FunctionsComponent {
        private com.google.firebase.functions.dagger.internal.Provider<String> bindProjectIdProvider;
        private com.google.firebase.functions.dagger.internal.Provider<FirebaseContextProvider> firebaseContextProvider;
        private com.google.firebase.functions.dagger.internal.Provider<FunctionsMultiResourceComponent.FirebaseFunctionsFactory> firebaseFunctionsFactoryProvider;
        private FirebaseFunctions_Factory firebaseFunctionsProvider;
        private final FunctionsComponentImpl functionsComponentImpl;
        private com.google.firebase.functions.dagger.internal.Provider<FunctionsMultiResourceComponent> functionsMultiResourceComponentProvider;
        private com.google.firebase.functions.dagger.internal.Provider<Deferred<InteropAppCheckTokenProvider>> setAppCheckProvider;
        private com.google.firebase.functions.dagger.internal.Provider<Context> setApplicationContextProvider;
        private com.google.firebase.functions.dagger.internal.Provider<Provider<InternalAuthProvider>> setAuthProvider;
        private com.google.firebase.functions.dagger.internal.Provider<FirebaseOptions> setFirebaseOptionsProvider;
        private com.google.firebase.functions.dagger.internal.Provider<Provider<FirebaseInstanceIdInternal>> setIidProvider;
        private com.google.firebase.functions.dagger.internal.Provider<Executor> setLiteExecutorProvider;
        private com.google.firebase.functions.dagger.internal.Provider<Executor> setUiExecutorProvider;

        private FunctionsComponentImpl(Context context, FirebaseOptions firebaseOptions, Executor executor, Executor executor2, Provider<InternalAuthProvider> provider, Provider<FirebaseInstanceIdInternal> provider2, Deferred<InteropAppCheckTokenProvider> deferred) {
            this.functionsComponentImpl = this;
            initialize(context, firebaseOptions, executor, executor2, provider, provider2, deferred);
        }

        private void initialize(Context context, FirebaseOptions firebaseOptions, Executor executor, Executor executor2, Provider<InternalAuthProvider> provider, Provider<FirebaseInstanceIdInternal> provider2, Deferred<InteropAppCheckTokenProvider> deferred) {
            this.setApplicationContextProvider = InstanceFactory.create(context);
            Factory factoryCreate = InstanceFactory.create(firebaseOptions);
            this.setFirebaseOptionsProvider = factoryCreate;
            this.bindProjectIdProvider = FunctionsComponent_MainModule_Companion_BindProjectIdFactory.create(factoryCreate);
            this.setAuthProvider = InstanceFactory.create(provider);
            this.setIidProvider = InstanceFactory.create(provider2);
            this.setAppCheckProvider = InstanceFactory.create(deferred);
            Factory factoryCreate2 = InstanceFactory.create(executor);
            this.setLiteExecutorProvider = factoryCreate2;
            this.firebaseContextProvider = DoubleCheck.provider(FirebaseContextProvider_Factory.create(this.setAuthProvider, this.setIidProvider, this.setAppCheckProvider, factoryCreate2));
            Factory factoryCreate3 = InstanceFactory.create(executor2);
            this.setUiExecutorProvider = factoryCreate3;
            FirebaseFunctions_Factory firebaseFunctions_FactoryCreate = FirebaseFunctions_Factory.create(this.setApplicationContextProvider, this.bindProjectIdProvider, this.firebaseContextProvider, this.setLiteExecutorProvider, factoryCreate3);
            this.firebaseFunctionsProvider = firebaseFunctions_FactoryCreate;
            com.google.firebase.functions.dagger.internal.Provider<FunctionsMultiResourceComponent.FirebaseFunctionsFactory> providerCreateFactoryProvider = FunctionsMultiResourceComponent_FirebaseFunctionsFactory_Impl.createFactoryProvider(firebaseFunctions_FactoryCreate);
            this.firebaseFunctionsFactoryProvider = providerCreateFactoryProvider;
            this.functionsMultiResourceComponentProvider = DoubleCheck.provider(FunctionsMultiResourceComponent_Factory.create(providerCreateFactoryProvider));
        }

        @Override // com.google.firebase.functions.FunctionsComponent
        public FunctionsMultiResourceComponent getMultiResourceComponent() {
            return this.functionsMultiResourceComponentProvider.get();
        }
    }
}
