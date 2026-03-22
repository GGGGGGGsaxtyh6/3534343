package com.google.firebase.functions;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.functions.FirebaseFunctionsException;
import com.google.firebase.functions.dagger.assisted.Assisted;
import com.google.firebase.functions.dagger.assisted.AssistedInject;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Executor;
import javax.inject.Named;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Publisher;

/* JADX INFO: compiled from: FirebaseFunctions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 42\u00020\u0001:\u00014BG\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0001\u0010\t\u001a\u00020\n\u0012\b\b\u0001\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005J\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0015\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u0005H\u0001¢\u0006\u0002\b!J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0005H\u0007J\u0016\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020(J-\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010\u0019\u001a\u00020\u00052\b\u0010,\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001d\u001a\u00020-H\u0000¢\u0006\u0002\b.J-\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010,\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001d\u001a\u00020-H\u0000¢\u0006\u0002\b.J4\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0*2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010,\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010/2\u0006\u0010\u001d\u001a\u00020-H\u0002J-\u00100\u001a\b\u0012\u0004\u0012\u000202012\u0006\u0010\u0019\u001a\u00020\u00052\b\u0010,\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001d\u001a\u00020-H\u0000¢\u0006\u0002\b3J-\u00100\u001a\b\u0012\u0004\u0012\u000202012\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010,\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001d\u001a\u00020-H\u0000¢\u0006\u0002\b3R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/google/firebase/functions/FirebaseFunctions;", "", "context", "Landroid/content/Context;", "projectId", "", "regionOrCustomDomain", "contextProvider", "Lcom/google/firebase/functions/ContextProvider;", "executor", "Ljava/util/concurrent/Executor;", "uiExecutor", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/google/firebase/functions/ContextProvider;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;)V", "client", "Lokhttp3/OkHttpClient;", "serializer", "Lcom/google/firebase/functions/Serializer;", "region", "customDomain", "urlFormat", "emulatorSettings", "Lcom/google/firebase/emulators/EmulatedServiceSettings;", "getHttpsCallable", "Lcom/google/firebase/functions/HttpsCallableReference;", "name", "getHttpsCallableFromUrl", ImagesContract.URL, "Ljava/net/URL;", "options", "Lcom/google/firebase/functions/HttpsCallableOptions;", "getURL", "function", "getURL$com_google_firebase_firebase_functions", "useFunctionsEmulator", "", "origin", "useEmulator", "host", "port", "", NotificationCompat.CATEGORY_CALL, "Lcom/google/android/gms/tasks/Task;", "Lcom/google/firebase/functions/HttpsCallableResult;", "data", "Lcom/google/firebase/functions/HttpsCallOptions;", "call$com_google_firebase_firebase_functions", "Lcom/google/firebase/functions/HttpsCallableContext;", "stream", "Lorg/reactivestreams/Publisher;", "Lcom/google/firebase/functions/StreamResponse;", "stream$com_google_firebase_firebase_functions", "Companion", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FirebaseFunctions {
    private static boolean providerInstallStarted;
    private final OkHttpClient client;
    private final ContextProvider contextProvider;
    private String customDomain;
    private EmulatedServiceSettings emulatorSettings;
    private final Executor executor;
    private final String projectId;
    private String region;
    private final Serializer serializer;
    private String urlFormat;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final TaskCompletionSource<Void> providerInstalled = new TaskCompletionSource<>();

    @JvmStatic
    public static final FirebaseFunctions getInstance() {
        return INSTANCE.getInstance();
    }

    @JvmStatic
    public static final FirebaseFunctions getInstance(FirebaseApp firebaseApp) {
        return INSTANCE.getInstance(firebaseApp);
    }

    @JvmStatic
    public static final FirebaseFunctions getInstance(FirebaseApp firebaseApp, String str) {
        return INSTANCE.getInstance(firebaseApp, str);
    }

    @JvmStatic
    public static final FirebaseFunctions getInstance(String str) {
        return INSTANCE.getInstance(str);
    }

    @AssistedInject
    public FirebaseFunctions(Context context, @Named("projectId") String str, @Assisted String str2, ContextProvider contextProvider, @Lightweight Executor executor, @UiThread Executor uiExecutor) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(uiExecutor, "uiExecutor");
        this.executor = executor;
        this.client = new OkHttpClient();
        this.serializer = new Serializer();
        Object objCheckNotNull = Preconditions.checkNotNull(contextProvider);
        Intrinsics.checkNotNullExpressionValue(objCheckNotNull, "checkNotNull(...)");
        this.contextProvider = (ContextProvider) objCheckNotNull;
        Object objCheckNotNull2 = Preconditions.checkNotNull(str);
        Intrinsics.checkNotNullExpressionValue(objCheckNotNull2, "checkNotNull(...)");
        this.projectId = (String) objCheckNotNull2;
        this.urlFormat = "https://%1$s-%2$s.cloudfunctions.net/%3$s";
        try {
            new URL(str2);
            this.region = "us-central1";
            this.customDomain = str2;
        } catch (MalformedURLException unused) {
            this.region = str2;
            this.customDomain = null;
        }
        INSTANCE.maybeInstallProviders(context, uiExecutor);
    }

    public final HttpsCallableReference getHttpsCallable(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new HttpsCallableReference(this, name, new HttpsCallOptions());
    }

    public final HttpsCallableReference getHttpsCallableFromUrl(URL url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new HttpsCallableReference(this, url, new HttpsCallOptions());
    }

    public final HttpsCallableReference getHttpsCallable(String name, HttpsCallableOptions options) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        return new HttpsCallableReference(this, name, new HttpsCallOptions(options));
    }

    public final HttpsCallableReference getHttpsCallableFromUrl(URL url, HttpsCallableOptions options) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(options, "options");
        return new HttpsCallableReference(this, url, new HttpsCallOptions(options));
    }

    public final URL getURL$com_google_firebase_firebase_functions(String function) {
        Intrinsics.checkNotNullParameter(function, "function");
        EmulatedServiceSettings emulatedServiceSettings = this.emulatorSettings;
        if (emulatedServiceSettings != null) {
            this.urlFormat = "http://" + emulatedServiceSettings.getHost() + ':' + emulatedServiceSettings.getPort() + "/%2$s/%1$s/%3$s";
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(this.urlFormat, Arrays.copyOf(new Object[]{this.region, this.projectId, function}, 3));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        if (this.customDomain != null && emulatedServiceSettings == null) {
            str = this.customDomain + '/' + function;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Deprecated(message = "Use useEmulator to connect to the emulator.")
    public final void useFunctionsEmulator(String origin) {
        Intrinsics.checkNotNullParameter(origin, "origin");
        Preconditions.checkNotNull(origin, "origin cannot be null");
        this.urlFormat = origin + "/%2$s/%1$s/%3$s";
    }

    public final void useEmulator(String host, int port) {
        Intrinsics.checkNotNullParameter(host, "host");
        this.emulatorSettings = new EmulatedServiceSettings(host, port);
    }

    public final Task<HttpsCallableResult> call$com_google_firebase_firebase_functions(final String name, final Object data, final HttpsCallOptions options) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        Task<HttpsCallableResult> taskContinueWithTask = providerInstalled.getTask().continueWithTask(this.executor, new Continuation() { // from class: com.google.firebase.functions.FirebaseFunctions$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseFunctions.call$lambda$0(this.f$0, options, task);
            }
        }).continueWithTask(this.executor, new Continuation() { // from class: com.google.firebase.functions.FirebaseFunctions$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseFunctions.call$lambda$1(this.f$0, name, data, options, task);
            }
        });
        Intrinsics.checkNotNullExpressionValue(taskContinueWithTask, "continueWithTask(...)");
        return taskContinueWithTask;
    }

    static final Task call$lambda$0(FirebaseFunctions firebaseFunctions, HttpsCallOptions httpsCallOptions, Task it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return firebaseFunctions.contextProvider.getContext(httpsCallOptions.limitedUseAppCheckTokens);
    }

    static final Task call$lambda$1(FirebaseFunctions firebaseFunctions, String str, Object obj, HttpsCallOptions httpsCallOptions, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            Intrinsics.checkNotNull(exception);
            return Tasks.forException(exception);
        }
        return firebaseFunctions.call(firebaseFunctions.getURL$com_google_firebase_firebase_functions(str), obj, (HttpsCallableContext) task.getResult(), httpsCallOptions);
    }

    public final Task<HttpsCallableResult> call$com_google_firebase_firebase_functions(final URL url, final Object data, final HttpsCallOptions options) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(options, "options");
        Task<HttpsCallableResult> taskContinueWithTask = providerInstalled.getTask().continueWithTask(this.executor, new Continuation() { // from class: com.google.firebase.functions.FirebaseFunctions$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseFunctions.call$lambda$2(this.f$0, options, task);
            }
        }).continueWithTask(this.executor, new Continuation() { // from class: com.google.firebase.functions.FirebaseFunctions$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseFunctions.call$lambda$3(this.f$0, url, data, options, task);
            }
        });
        Intrinsics.checkNotNullExpressionValue(taskContinueWithTask, "continueWithTask(...)");
        return taskContinueWithTask;
    }

    static final Task call$lambda$2(FirebaseFunctions firebaseFunctions, HttpsCallOptions httpsCallOptions, Task it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return firebaseFunctions.contextProvider.getContext(httpsCallOptions.limitedUseAppCheckTokens);
    }

    static final Task call$lambda$3(FirebaseFunctions firebaseFunctions, URL url, Object obj, HttpsCallOptions httpsCallOptions, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            Intrinsics.checkNotNull(exception);
            return Tasks.forException(exception);
        }
        return firebaseFunctions.call(url, obj, (HttpsCallableContext) task.getResult(), httpsCallOptions);
    }

    private final Task<HttpsCallableResult> call(URL url, Object data, HttpsCallableContext context, HttpsCallOptions options) {
        Preconditions.checkNotNull(url, "url cannot be null");
        HashMap map = new HashMap();
        map.put("data", this.serializer.encode(data));
        JSONObject jSONObject = new JSONObject(map);
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        RequestBody.Companion companion = RequestBody.INSTANCE;
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        Request.Builder builderPost = new Request.Builder().url(url).post(companion.create(string, mediaType));
        Intrinsics.checkNotNull(context);
        if (context.getAuthToken() != null) {
            builderPost = builderPost.header(HttpHeaders.AUTHORIZATION, "Bearer " + context.getAuthToken());
        }
        if (context.getInstanceIdToken() != null) {
            builderPost = builderPost.header("Firebase-Instance-ID-Token", context.getInstanceIdToken());
        }
        if (context.getAppCheckToken() != null) {
            builderPost = builderPost.header("X-Firebase-AppCheck", context.getAppCheckToken());
        }
        Call callNewCall = options.apply$com_google_firebase_firebase_functions(this.client).newCall(builderPost.build());
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        callNewCall.enqueue(new Callback() { // from class: com.google.firebase.functions.FirebaseFunctions.call.5
            @Override // okhttp3.Callback
            public void onFailure(Call ignored, IOException e) {
                Intrinsics.checkNotNullParameter(ignored, "ignored");
                Intrinsics.checkNotNullParameter(e, "e");
                if (e instanceof InterruptedIOException) {
                    taskCompletionSource.setException(new FirebaseFunctionsException("DEADLINE_EXCEEDED", FirebaseFunctionsException.Code.DEADLINE_EXCEEDED, null, e));
                } else {
                    taskCompletionSource.setException(new FirebaseFunctionsException("INTERNAL", FirebaseFunctionsException.Code.INTERNAL, null, e));
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call ignored, Response response) throws IOException {
                Intrinsics.checkNotNullParameter(ignored, "ignored");
                Intrinsics.checkNotNullParameter(response, "response");
                FirebaseFunctionsException.Code codeFromHttpStatus = FirebaseFunctionsException.Code.INSTANCE.fromHttpStatus(response.code());
                ResponseBody responseBodyBody = response.body();
                Intrinsics.checkNotNull(responseBodyBody);
                String strString = responseBodyBody.string();
                FirebaseFunctionsException firebaseFunctionsExceptionFromResponse$com_google_firebase_firebase_functions = FirebaseFunctionsException.INSTANCE.fromResponse$com_google_firebase_firebase_functions(codeFromHttpStatus, strString, this.serializer);
                if (firebaseFunctionsExceptionFromResponse$com_google_firebase_firebase_functions != null) {
                    taskCompletionSource.setException(firebaseFunctionsExceptionFromResponse$com_google_firebase_firebase_functions);
                    return;
                }
                try {
                    JSONObject jSONObject2 = new JSONObject(strString);
                    Object objOpt = jSONObject2.opt("data");
                    if (objOpt == null) {
                        objOpt = jSONObject2.opt("result");
                    }
                    if (objOpt == null) {
                        taskCompletionSource.setException(new FirebaseFunctionsException("Response is missing data field.", FirebaseFunctionsException.Code.INTERNAL, null));
                    } else {
                        taskCompletionSource.setResult(new HttpsCallableResult(this.serializer.decode(objOpt)));
                    }
                } catch (JSONException e) {
                    taskCompletionSource.setException(new FirebaseFunctionsException("Response is not valid JSON object.", FirebaseFunctionsException.Code.INTERNAL, null, e));
                }
            }
        });
        Task<HttpsCallableResult> task = taskCompletionSource.getTask();
        Intrinsics.checkNotNullExpressionValue(task, "getTask(...)");
        return task;
    }

    public final Publisher<StreamResponse> stream$com_google_firebase_firebase_functions(String name, Object data, HttpsCallOptions options) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        return stream$com_google_firebase_firebase_functions(getURL$com_google_firebase_firebase_functions(name), data, options);
    }

    public final Publisher<StreamResponse> stream$com_google_firebase_firebase_functions(URL url, Object data, final HttpsCallOptions options) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(options, "options");
        Task<TContinuationResult> taskContinueWithTask = providerInstalled.getTask().continueWithTask(this.executor, new Continuation() { // from class: com.google.firebase.functions.FirebaseFunctions$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseFunctions.stream$lambda$4(this.f$0, options, task);
            }
        });
        Intrinsics.checkNotNullExpressionValue(taskContinueWithTask, "continueWithTask(...)");
        return new PublisherStream(url, data, options, this.client, this.serializer, taskContinueWithTask, this.executor);
    }

    static final Task stream$lambda$4(FirebaseFunctions firebaseFunctions, HttpsCallOptions httpsCallOptions, Task it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return firebaseFunctions.contextProvider.getContext(httpsCallOptions.limitedUseAppCheckTokens);
    }

    /* JADX INFO: compiled from: FirebaseFunctions.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0007R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/google/firebase/functions/FirebaseFunctions$Companion;", "", "<init>", "()V", "providerInstalled", "Lcom/google/android/gms/tasks/TaskCompletionSource;", "Ljava/lang/Void;", "providerInstallStarted", "", "maybeInstallProviders", "", "context", "Landroid/content/Context;", "uiExecutor", "Ljava/util/concurrent/Executor;", "getInstance", "Lcom/google/firebase/functions/FirebaseFunctions;", "app", "Lcom/google/firebase/FirebaseApp;", "regionOrCustomDomain", "", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void maybeInstallProviders(final Context context, Executor uiExecutor) {
            synchronized (FirebaseFunctions.providerInstalled) {
                if (FirebaseFunctions.providerInstallStarted) {
                    return;
                }
                Companion companion = FirebaseFunctions.INSTANCE;
                FirebaseFunctions.providerInstallStarted = true;
                Unit unit = Unit.INSTANCE;
                uiExecutor.execute(new Runnable() { // from class: com.google.firebase.functions.FirebaseFunctions$Companion$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ProviderInstaller.installIfNeededAsync(context, new ProviderInstaller.ProviderInstallListener() { // from class: com.google.firebase.functions.FirebaseFunctions$Companion$maybeInstallProviders$2$1
                            @Override // com.google.android.gms.security.ProviderInstaller.ProviderInstallListener
                            public void onProviderInstalled() {
                                FirebaseFunctions.providerInstalled.setResult(null);
                            }

                            @Override // com.google.android.gms.security.ProviderInstaller.ProviderInstallListener
                            public void onProviderInstallFailed(int i, Intent intent) {
                                Log.d("FirebaseFunctions", "Failed to update ssl context");
                                FirebaseFunctions.providerInstalled.setResult(null);
                            }
                        });
                    }
                });
            }
        }

        @JvmStatic
        public final FirebaseFunctions getInstance(FirebaseApp app, String regionOrCustomDomain) {
            Intrinsics.checkNotNullParameter(app, "app");
            Intrinsics.checkNotNullParameter(regionOrCustomDomain, "regionOrCustomDomain");
            Preconditions.checkNotNull(app, "You must call FirebaseApp.initializeApp first.");
            Preconditions.checkNotNull(regionOrCustomDomain);
            FunctionsMultiResourceComponent functionsMultiResourceComponent = (FunctionsMultiResourceComponent) app.get(FunctionsMultiResourceComponent.class);
            Preconditions.checkNotNull(functionsMultiResourceComponent, "Functions component does not exist.");
            FirebaseFunctions firebaseFunctions = functionsMultiResourceComponent.get(regionOrCustomDomain);
            Intrinsics.checkNotNull(firebaseFunctions);
            return firebaseFunctions;
        }

        @JvmStatic
        public final FirebaseFunctions getInstance(FirebaseApp app) {
            Intrinsics.checkNotNullParameter(app, "app");
            return getInstance(app, "us-central1");
        }

        @JvmStatic
        public final FirebaseFunctions getInstance(String regionOrCustomDomain) {
            Intrinsics.checkNotNullParameter(regionOrCustomDomain, "regionOrCustomDomain");
            FirebaseApp firebaseApp = FirebaseApp.getInstance();
            Intrinsics.checkNotNullExpressionValue(firebaseApp, "getInstance(...)");
            return getInstance(firebaseApp, regionOrCustomDomain);
        }

        @JvmStatic
        public final FirebaseFunctions getInstance() {
            FirebaseApp firebaseApp = FirebaseApp.getInstance();
            Intrinsics.checkNotNullExpressionValue(firebaseApp, "getInstance(...)");
            return getInstance(firebaseApp, "us-central1");
        }
    }
}
