package com.google.firebase.functions;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: HttpsCallableContext.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lcom/google/firebase/functions/HttpsCallableContext;", "", "authToken", "", "instanceIdToken", "appCheckToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAuthToken", "()Ljava/lang/String;", "getInstanceIdToken", "getAppCheckToken", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class HttpsCallableContext {
    private final String appCheckToken;
    private final String authToken;
    private final String instanceIdToken;

    public HttpsCallableContext(String str, String str2, String str3) {
        this.authToken = str;
        this.instanceIdToken = str2;
        this.appCheckToken = str3;
    }

    public final String getAuthToken() {
        return this.authToken;
    }

    public final String getInstanceIdToken() {
        return this.instanceIdToken;
    }

    public final String getAppCheckToken() {
        return this.appCheckToken;
    }
}
