package com.google.firebase.functions;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.tasks.Task;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.reactivestreams.Publisher;

/* JADX INFO: compiled from: HttpsCallableReference.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B#\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB#\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\fJ\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012J\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0007J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010 \u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Lcom/google/firebase/functions/HttpsCallableReference;", "", "functionsClient", "Lcom/google/firebase/functions/FirebaseFunctions;", "name", "", "options", "Lcom/google/firebase/functions/HttpsCallOptions;", "<init>", "(Lcom/google/firebase/functions/FirebaseFunctions;Ljava/lang/String;Lcom/google/firebase/functions/HttpsCallOptions;)V", ImagesContract.URL, "Ljava/net/URL;", "(Lcom/google/firebase/functions/FirebaseFunctions;Ljava/net/URL;Lcom/google/firebase/functions/HttpsCallOptions;)V", "getOptions$com_google_firebase_firebase_functions$annotations", "()V", "getOptions$com_google_firebase_firebase_functions", "()Lcom/google/firebase/functions/HttpsCallOptions;", NotificationCompat.CATEGORY_CALL, "Lcom/google/android/gms/tasks/Task;", "Lcom/google/firebase/functions/HttpsCallableResult;", "data", "stream", "Lorg/reactivestreams/Publisher;", "Lcom/google/firebase/functions/StreamResponse;", "setTimeout", "", "timeout", "", "units", "Ljava/util/concurrent/TimeUnit;", "getTimeout", "()J", "withTimeout", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class HttpsCallableReference {
    private final FirebaseFunctions functionsClient;
    private final String name;
    private final HttpsCallOptions options;
    private final URL url;

    public static /* synthetic */ void getOptions$com_google_firebase_firebase_functions$annotations() {
    }

    public final Publisher<StreamResponse> stream() {
        return stream$default(this, null, 1, null);
    }

    /* JADX INFO: renamed from: getOptions$com_google_firebase_firebase_functions, reason: from getter */
    public final HttpsCallOptions getOptions() {
        return this.options;
    }

    public HttpsCallableReference(FirebaseFunctions functionsClient, String str, HttpsCallOptions options) {
        Intrinsics.checkNotNullParameter(functionsClient, "functionsClient");
        Intrinsics.checkNotNullParameter(options, "options");
        this.functionsClient = functionsClient;
        this.name = str;
        this.url = null;
        this.options = options;
    }

    public HttpsCallableReference(FirebaseFunctions functionsClient, URL url, HttpsCallOptions options) {
        Intrinsics.checkNotNullParameter(functionsClient, "functionsClient");
        Intrinsics.checkNotNullParameter(options, "options");
        this.functionsClient = functionsClient;
        this.name = null;
        this.url = url;
        this.options = options;
    }

    public final Task<HttpsCallableResult> call(Object data) {
        String str = this.name;
        FirebaseFunctions firebaseFunctions = this.functionsClient;
        if (str != null) {
            return firebaseFunctions.call$com_google_firebase_firebase_functions(str, data, this.options);
        }
        URL url = this.url;
        Intrinsics.checkNotNull(url);
        return firebaseFunctions.call$com_google_firebase_firebase_functions(url, data, this.options);
    }

    public final Task<HttpsCallableResult> call() {
        String str = this.name;
        FirebaseFunctions firebaseFunctions = this.functionsClient;
        if (str != null) {
            return firebaseFunctions.call$com_google_firebase_firebase_functions(str, (Object) null, this.options);
        }
        URL url = this.url;
        Intrinsics.checkNotNull(url);
        return firebaseFunctions.call$com_google_firebase_firebase_functions(url, (Object) null, this.options);
    }

    public static /* synthetic */ Publisher stream$default(HttpsCallableReference httpsCallableReference, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = null;
        }
        return httpsCallableReference.stream(obj);
    }

    public final Publisher<StreamResponse> stream(Object data) {
        String str = this.name;
        FirebaseFunctions firebaseFunctions = this.functionsClient;
        if (str != null) {
            return firebaseFunctions.stream$com_google_firebase_firebase_functions(str, data, this.options);
        }
        URL url = this.url;
        if (url != null) {
            return firebaseFunctions.stream$com_google_firebase_firebase_functions(url, data, this.options);
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    public final void setTimeout(long timeout, TimeUnit units) {
        Intrinsics.checkNotNullParameter(units, "units");
        this.options.setTimeout$com_google_firebase_firebase_functions(timeout, units);
    }

    public final long getTimeout() {
        return this.options.getTimeout$com_google_firebase_firebase_functions();
    }

    public final HttpsCallableReference withTimeout(long timeout, TimeUnit units) {
        Intrinsics.checkNotNullParameter(units, "units");
        HttpsCallableReference httpsCallableReference = new HttpsCallableReference(this.functionsClient, this.name, this.options);
        httpsCallableReference.setTimeout(timeout, units);
        return httpsCallableReference;
    }
}
