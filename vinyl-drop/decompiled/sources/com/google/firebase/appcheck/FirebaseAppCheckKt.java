package com.google.firebase.appcheck;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FirebaseAppCheck.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\r\u0010\u0007\u001a\u00020\b*\u00020\tH\u0086\u0002\u001a\r\u0010\n\u001a\u00020\u000b*\u00020\tH\u0086\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\f"}, d2 = {"appCheck", "Lcom/google/firebase/appcheck/FirebaseAppCheck;", "Lcom/google/firebase/Firebase;", "getAppCheck", "(Lcom/google/firebase/Firebase;)Lcom/google/firebase/appcheck/FirebaseAppCheck;", "app", "Lcom/google/firebase/FirebaseApp;", "component1", "", "Lcom/google/firebase/appcheck/AppCheckToken;", "component2", "", "com.google.firebase-appcheck-firebase-appcheck"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FirebaseAppCheckKt {
    public static final FirebaseAppCheck getAppCheck(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAppCheck, "getInstance(...)");
        return firebaseAppCheck;
    }

    public static final FirebaseAppCheck appCheck(Firebase firebase2, FirebaseApp app) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(app, "app");
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance(app);
        Intrinsics.checkNotNullExpressionValue(firebaseAppCheck, "getInstance(...)");
        return firebaseAppCheck;
    }

    public static final String component1(AppCheckToken appCheckToken) {
        Intrinsics.checkNotNullParameter(appCheckToken, "<this>");
        String token = appCheckToken.getToken();
        Intrinsics.checkNotNullExpressionValue(token, "getToken(...)");
        return token;
    }

    public static final long component2(AppCheckToken appCheckToken) {
        Intrinsics.checkNotNullParameter(appCheckToken, "<this>");
        return appCheckToken.get$expireAt();
    }
}
