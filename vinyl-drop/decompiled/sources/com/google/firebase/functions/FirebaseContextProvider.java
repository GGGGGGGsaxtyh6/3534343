package com.google.firebase.functions;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FirebaseContextProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B=\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0012\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0018\u00010\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0013H\u0002J\u0018\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/google/firebase/functions/FirebaseContextProvider;", "Lcom/google/firebase/functions/ContextProvider;", "tokenProvider", "Lcom/google/firebase/inject/Provider;", "Lcom/google/firebase/auth/internal/InternalAuthProvider;", "instanceId", "Lcom/google/firebase/iid/internal/FirebaseInstanceIdInternal;", "appCheckDeferred", "Lcom/google/firebase/inject/Deferred;", "Lcom/google/firebase/appcheck/interop/InteropAppCheckTokenProvider;", "executor", "Ljava/util/concurrent/Executor;", "<init>", "(Lcom/google/firebase/inject/Provider;Lcom/google/firebase/inject/Provider;Lcom/google/firebase/inject/Deferred;Ljava/util/concurrent/Executor;)V", "TAG", "", "appCheckRef", "Ljava/util/concurrent/atomic/AtomicReference;", "getContext", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/firebase/functions/HttpsCallableContext;", "getLimitedUseAppCheckToken", "", "getAuthToken", "getAppCheckToken", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FirebaseContextProvider implements ContextProvider {
    private final String TAG;
    private final AtomicReference<InteropAppCheckTokenProvider> appCheckRef;
    private final Executor executor;
    private final Provider<FirebaseInstanceIdInternal> instanceId;
    private final Provider<InternalAuthProvider> tokenProvider;

    @Inject
    public FirebaseContextProvider(Provider<InternalAuthProvider> tokenProvider, Provider<FirebaseInstanceIdInternal> instanceId, Deferred<InteropAppCheckTokenProvider> appCheckDeferred, @Lightweight Executor executor) {
        Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(appCheckDeferred, "appCheckDeferred");
        Intrinsics.checkNotNullParameter(executor, "executor");
        this.tokenProvider = tokenProvider;
        this.instanceId = instanceId;
        this.executor = executor;
        this.TAG = "FirebaseContextProvider";
        this.appCheckRef = new AtomicReference<>();
        appCheckDeferred.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.functions.FirebaseContextProvider$$ExternalSyntheticLambda1
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                FirebaseContextProvider._init_$lambda$1(this.f$0, provider);
            }
        });
    }

    static final void _init_$lambda$1(FirebaseContextProvider firebaseContextProvider, Provider p) {
        Intrinsics.checkNotNullParameter(p, "p");
        InteropAppCheckTokenProvider interopAppCheckTokenProvider = (InteropAppCheckTokenProvider) p.get();
        firebaseContextProvider.appCheckRef.set(interopAppCheckTokenProvider);
        interopAppCheckTokenProvider.addAppCheckTokenListener(new AppCheckTokenListener() { // from class: com.google.firebase.functions.FirebaseContextProvider$$ExternalSyntheticLambda3
            @Override // com.google.firebase.appcheck.interop.AppCheckTokenListener
            public final void onAppCheckTokenChanged(AppCheckTokenResult appCheckTokenResult) {
                Intrinsics.checkNotNullParameter(appCheckTokenResult, "it");
            }
        });
    }

    @Override // com.google.firebase.functions.ContextProvider
    public Task<HttpsCallableContext> getContext(boolean getLimitedUseAppCheckToken) {
        final Task<String> authToken = getAuthToken();
        final Task<String> appCheckToken = getAppCheckToken(getLimitedUseAppCheckToken);
        return Tasks.whenAll((Task<?>[]) new Task[]{authToken, appCheckToken}).onSuccessTask(this.executor, new SuccessContinuation() { // from class: com.google.firebase.functions.FirebaseContextProvider$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return Tasks.forResult(new HttpsCallableContext((String) authToken.getResult(), this.instanceId.get().getToken(), (String) appCheckToken.getResult()));
            }
        });
    }

    private final Task<String> getAuthToken() {
        InternalAuthProvider internalAuthProvider = this.tokenProvider.get();
        if (internalAuthProvider == null) {
            Task<String> taskForResult = Tasks.forResult(null);
            Intrinsics.checkNotNullExpressionValue(taskForResult, "forResult(...)");
            return taskForResult;
        }
        Task taskContinueWith = internalAuthProvider.getAccessToken(false).continueWith(this.executor, new Continuation() { // from class: com.google.firebase.functions.FirebaseContextProvider$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseContextProvider.getAuthToken$lambda$3(task);
            }
        });
        Intrinsics.checkNotNullExpressionValue(taskContinueWith, "continueWith(...)");
        return taskContinueWith;
    }

    static final String getAuthToken$lambda$3(Task task) throws Exception {
        Intrinsics.checkNotNullParameter(task, "task");
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            if (exception instanceof FirebaseNoSignedInUserException) {
                return null;
            }
            Intrinsics.checkNotNull(exception);
            throw exception;
        }
        return ((GetTokenResult) task.getResult()).getToken();
    }

    private final Task<String> getAppCheckToken(boolean getLimitedUseAppCheckToken) {
        InteropAppCheckTokenProvider interopAppCheckTokenProvider = this.appCheckRef.get();
        if (interopAppCheckTokenProvider == null) {
            Task<String> taskForResult = Tasks.forResult(null);
            Intrinsics.checkNotNullExpressionValue(taskForResult, "forResult(...)");
            return taskForResult;
        }
        Task<AppCheckTokenResult> limitedUseToken = getLimitedUseAppCheckToken ? interopAppCheckTokenProvider.getLimitedUseToken() : interopAppCheckTokenProvider.getToken(false);
        Intrinsics.checkNotNull(limitedUseToken);
        Task taskOnSuccessTask = limitedUseToken.onSuccessTask(this.executor, new SuccessContinuation() { // from class: com.google.firebase.functions.FirebaseContextProvider$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return FirebaseContextProvider.getAppCheckToken$lambda$4(this.f$0, (AppCheckTokenResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(taskOnSuccessTask, "onSuccessTask(...)");
        return taskOnSuccessTask;
    }

    static final Task getAppCheckToken$lambda$4(FirebaseContextProvider firebaseContextProvider, AppCheckTokenResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getError() != null) {
            Log.w(firebaseContextProvider.TAG, "Error getting App Check token. Error: " + result.getError());
        }
        return Tasks.forResult(result.getToken());
    }
}
