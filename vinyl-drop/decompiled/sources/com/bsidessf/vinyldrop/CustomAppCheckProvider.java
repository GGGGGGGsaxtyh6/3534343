package com.bsidessf.vinyldrop;

import android.net.Uri;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckProvider;
import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CustomAppCheckProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0002"}, d2 = {"Lcom/bsidessf/vinyldrop/CustomAppCheckProvider;", "Lcom/google/firebase/appcheck/AppCheckProvider;", "app", "Lcom/google/firebase/FirebaseApp;", "<init>", "(Lcom/google/firebase/FirebaseApp;)V", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "getToken", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/firebase/appcheck/AppCheckToken;", "mintToken", "idToken", "", "buildMintUrl"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class CustomAppCheckProvider implements AppCheckProvider {
    private final FirebaseApp app;
    private final ExecutorService executor;

    public CustomAppCheckProvider(FirebaseApp app) {
        Intrinsics.checkNotNullParameter(app, "app");
        this.app = app;
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override // com.google.firebase.appcheck.AppCheckProvider
    public Task<AppCheckToken> getToken() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(this.app);
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            taskCompletionSource.setException(new IllegalStateException("User must be signed in to mint App Check token."));
            Task<AppCheckToken> task = taskCompletionSource.getTask();
            Intrinsics.checkNotNullExpressionValue(task, "getTask(...)");
            return task;
        }
        Task<GetTokenResult> idToken = currentUser.getIdToken(false);
        final Function1 function1 = new Function1() { // from class: com.bsidessf.vinyldrop.CustomAppCheckProvider$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CustomAppCheckProvider.getToken$lambda$1(taskCompletionSource, this, (GetTokenResult) obj);
            }
        };
        idToken.addOnSuccessListener(new OnSuccessListener() { // from class: com.bsidessf.vinyldrop.CustomAppCheckProvider$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                function1.invoke(obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.bsidessf.vinyldrop.CustomAppCheckProvider$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                CustomAppCheckProvider.getToken$lambda$3(taskCompletionSource, exc);
            }
        });
        Task<AppCheckToken> task2 = taskCompletionSource.getTask();
        Intrinsics.checkNotNullExpressionValue(task2, "getTask(...)");
        return task2;
    }

    static final Unit getToken$lambda$1(final TaskCompletionSource taskCompletionSource, final CustomAppCheckProvider customAppCheckProvider, GetTokenResult getTokenResult) {
        final String token = getTokenResult.getToken();
        String str = token;
        if (str == null || StringsKt.isBlank(str)) {
            taskCompletionSource.setException(new IllegalStateException("Failed to fetch ID token."));
            return Unit.INSTANCE;
        }
        customAppCheckProvider.executor.execute(new Runnable() { // from class: com.bsidessf.vinyldrop.CustomAppCheckProvider$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CustomAppCheckProvider.getToken$lambda$1$lambda$0(this.f$0, token, taskCompletionSource);
            }
        });
        return Unit.INSTANCE;
    }

    static final void getToken$lambda$1$lambda$0(CustomAppCheckProvider customAppCheckProvider, String str, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(customAppCheckProvider.mintToken(str));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    static final void getToken$lambda$3(TaskCompletionSource taskCompletionSource, Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        taskCompletionSource.setException(e);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.google.firebase.appcheck.AppCheckToken mintToken(java.lang.String r7) throws org.json.JSONException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bsidessf.vinyldrop.CustomAppCheckProvider.mintToken(java.lang.String):com.google.firebase.appcheck.AppCheckToken");
    }

    private final String buildMintUrl() {
        String projectId = this.app.getOptions().getProjectId();
        if (projectId == null) {
            projectId = "vinyldrop-corgi";
        }
        String string = new Uri.Builder().scheme("https").authority("us-central1-" + projectId + ".cloudfunctions.net").appendPath("mintAppCheckToken").build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
