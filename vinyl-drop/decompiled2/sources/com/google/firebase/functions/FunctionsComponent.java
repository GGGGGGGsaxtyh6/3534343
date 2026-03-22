package com.google.firebase.functions;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.functions.dagger.Binds;
import com.google.firebase.functions.dagger.BindsInstance;
import com.google.firebase.functions.dagger.Component;
import com.google.firebase.functions.dagger.Module;
import com.google.firebase.functions.dagger.Provides;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;
import javax.inject.Named;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FunctionsComponent.kt */
/* JADX INFO: loaded from: classes3.dex */
@Component(modules = {MainModule.class})
@Singleton
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\ba\u0018\u00002\u00020\u0001:\u0002\u0006\u0007R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Lcom/google/firebase/functions/FunctionsComponent;", "", "multiResourceComponent", "Lcom/google/firebase/functions/FunctionsMultiResourceComponent;", "getMultiResourceComponent", "()Lcom/google/firebase/functions/FunctionsMultiResourceComponent;", "Builder", "MainModule", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public interface FunctionsComponent {

    /* JADX INFO: compiled from: FunctionsComponent.kt */
    @Component.Builder
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004H'J\u0010\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007H'J\u0012\u0010\b\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\nH'J\u0012\u0010\u000b\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\nH'J\u0016\u0010\f\u001a\u00020\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH'J\u0016\u0010\u0010\u001a\u00020\u00002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000eH'J\u0016\u0010\u0013\u001a\u00020\u00002\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H'J\b\u0010\u0017\u001a\u00020\u0018H&¨\u0006\u0019"}, d2 = {"Lcom/google/firebase/functions/FunctionsComponent$Builder;", "", "setApplicationContext", "applicationContext", "Landroid/content/Context;", "setFirebaseOptions", "options", "Lcom/google/firebase/FirebaseOptions;", "setLiteExecutor", "executor", "Ljava/util/concurrent/Executor;", "setUiExecutor", "setAuth", "auth", "Lcom/google/firebase/inject/Provider;", "Lcom/google/firebase/auth/internal/InternalAuthProvider;", "setIid", "iid", "Lcom/google/firebase/iid/internal/FirebaseInstanceIdInternal;", "setAppCheck", "appCheck", "Lcom/google/firebase/inject/Deferred;", "Lcom/google/firebase/appcheck/interop/InteropAppCheckTokenProvider;", "build", "Lcom/google/firebase/functions/FunctionsComponent;", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface Builder {
        FunctionsComponent build();

        @BindsInstance
        Builder setAppCheck(Deferred<InteropAppCheckTokenProvider> appCheck);

        @BindsInstance
        Builder setApplicationContext(Context applicationContext);

        @BindsInstance
        Builder setAuth(Provider<InternalAuthProvider> auth);

        @BindsInstance
        Builder setFirebaseOptions(FirebaseOptions options);

        @BindsInstance
        Builder setIid(Provider<FirebaseInstanceIdInternal> iid);

        @BindsInstance
        Builder setLiteExecutor(@Lightweight Executor executor);

        @BindsInstance
        Builder setUiExecutor(@UiThread Executor executor);
    }

    FunctionsMultiResourceComponent getMultiResourceComponent();

    /* JADX INFO: compiled from: FunctionsComponent.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0007"}, d2 = {"Lcom/google/firebase/functions/FunctionsComponent$MainModule;", "", "contextProvider", "Lcom/google/firebase/functions/ContextProvider;", "provider", "Lcom/google/firebase/functions/FirebaseContextProvider;", "Companion", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @Module
    public interface MainModule {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @Binds
        ContextProvider contextProvider(FirebaseContextProvider provider);

        /* JADX INFO: compiled from: FunctionsComponent.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/google/firebase/functions/FunctionsComponent$MainModule$Companion;", "", "<init>", "()V", "bindProjectId", "", "options", "Lcom/google/firebase/FirebaseOptions;", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
            }

            @Provides
            @Named("projectId")
            public final String bindProjectId(FirebaseOptions options) {
                Intrinsics.checkNotNullParameter(options, "options");
                return options.getProjectId();
            }
        }
    }
}
