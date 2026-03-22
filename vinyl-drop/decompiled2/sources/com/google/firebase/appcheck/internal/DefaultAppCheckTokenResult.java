package com.google.firebase.appcheck.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseException;
import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.appcheck.AppCheckTokenResult;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultAppCheckTokenResult extends AppCheckTokenResult {
    static final String DUMMY_TOKEN = "eyJlcnJvciI6IlVOS05PV05fRVJST1IifQ==";
    private final FirebaseException error;
    private final String token;

    public static DefaultAppCheckTokenResult constructFromAppCheckToken(AppCheckToken appCheckToken) {
        Preconditions.checkNotNull(appCheckToken);
        return new DefaultAppCheckTokenResult(appCheckToken.getToken(), null);
    }

    public static DefaultAppCheckTokenResult constructFromError(FirebaseException firebaseException) {
        return new DefaultAppCheckTokenResult(DUMMY_TOKEN, (FirebaseException) Preconditions.checkNotNull(firebaseException));
    }

    private DefaultAppCheckTokenResult(String str, FirebaseException firebaseException) {
        Preconditions.checkNotEmpty(str);
        this.token = str;
        this.error = firebaseException;
    }

    @Override // com.google.firebase.appcheck.AppCheckTokenResult
    public String getToken() {
        return this.token;
    }

    @Override // com.google.firebase.appcheck.AppCheckTokenResult
    public Exception getError() {
        return this.error;
    }
}
