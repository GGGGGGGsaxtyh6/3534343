package com.google.firebase.functions;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: HttpsCallOptions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0011\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0010¢\u0006\u0004\b\u0004\u0010\u0006J\r\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0002\b\u000eJ\u001d\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\nH\u0000¢\u0006\u0002\b\u0012J\r\u0010\u0013\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0014J\u0015\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0018R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/google/firebase/functions/HttpsCallOptions;", "", "publicCallableOptions", "Lcom/google/firebase/functions/HttpsCallableOptions;", "<init>", "(Lcom/google/firebase/functions/HttpsCallableOptions;)V", "()V", "timeout", "", "timeoutUnits", "Ljava/util/concurrent/TimeUnit;", "limitedUseAppCheckTokens", "", "getLimitedUseAppCheckTokens", "getLimitedUseAppCheckTokens$com_google_firebase_firebase_functions", "setTimeout", "", "units", "setTimeout$com_google_firebase_firebase_functions", "getTimeout", "getTimeout$com_google_firebase_firebase_functions", "apply", "Lokhttp3/OkHttpClient;", "client", "apply$com_google_firebase_firebase_functions", "Companion", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class HttpsCallOptions {
    private static final long DEFAULT_TIMEOUT = 70;
    public final boolean limitedUseAppCheckTokens;
    private long timeout;
    private TimeUnit timeoutUnits;
    private static final Companion Companion = new Companion(null);
    private static final TimeUnit DEFAULT_TIMEOUT_UNITS = TimeUnit.SECONDS;

    public HttpsCallOptions(HttpsCallableOptions publicCallableOptions) {
        Intrinsics.checkNotNullParameter(publicCallableOptions, "publicCallableOptions");
        this.timeout = DEFAULT_TIMEOUT;
        this.timeoutUnits = DEFAULT_TIMEOUT_UNITS;
        this.limitedUseAppCheckTokens = publicCallableOptions.limitedUseAppCheckTokens;
    }

    public HttpsCallOptions() {
        this.timeout = DEFAULT_TIMEOUT;
        this.timeoutUnits = DEFAULT_TIMEOUT_UNITS;
        this.limitedUseAppCheckTokens = false;
    }

    /* JADX INFO: renamed from: getLimitedUseAppCheckTokens$com_google_firebase_firebase_functions, reason: from getter */
    public final boolean getLimitedUseAppCheckTokens() {
        return this.limitedUseAppCheckTokens;
    }

    public final void setTimeout$com_google_firebase_firebase_functions(long timeout, TimeUnit units) {
        Intrinsics.checkNotNullParameter(units, "units");
        this.timeout = timeout;
        this.timeoutUnits = units;
    }

    public final long getTimeout$com_google_firebase_firebase_functions() {
        return this.timeoutUnits.toMillis(this.timeout);
    }

    public final OkHttpClient apply$com_google_firebase_firebase_functions(OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        return client.newBuilder().callTimeout(this.timeout, this.timeoutUnits).readTimeout(this.timeout, this.timeoutUnits).build();
    }

    /* JADX INFO: compiled from: HttpsCallOptions.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/google/firebase/functions/HttpsCallOptions$Companion;", "", "<init>", "()V", "DEFAULT_TIMEOUT", "", "DEFAULT_TIMEOUT_UNITS", "Ljava/util/concurrent/TimeUnit;", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
