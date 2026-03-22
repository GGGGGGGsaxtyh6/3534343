package com.google.firebase.functions;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.functions.HttpsCallableOptions;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Functions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006\u001a+\u0010\t\u001a\u00020\n*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00062\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010\u001a+\u0010\u0011\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0014"}, d2 = {"functions", "Lcom/google/firebase/functions/FirebaseFunctions;", "Lcom/google/firebase/Firebase;", "getFunctions", "(Lcom/google/firebase/Firebase;)Lcom/google/firebase/functions/FirebaseFunctions;", "regionOrCustomDomain", "", "app", "Lcom/google/firebase/FirebaseApp;", "getHttpsCallable", "Lcom/google/firebase/functions/HttpsCallableReference;", "name", "init", "Lkotlin/Function1;", "Lcom/google/firebase/functions/HttpsCallableOptions$Builder;", "", "Lkotlin/ExtensionFunctionType;", "getHttpsCallableFromUrl", ImagesContract.URL, "Ljava/net/URL;", "com.google.firebase-firebase-functions"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FunctionsKt {
    public static final FirebaseFunctions getFunctions(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        return FirebaseFunctions.INSTANCE.getInstance();
    }

    public static final FirebaseFunctions functions(Firebase firebase2, String regionOrCustomDomain) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(regionOrCustomDomain, "regionOrCustomDomain");
        return FirebaseFunctions.INSTANCE.getInstance(regionOrCustomDomain);
    }

    public static final FirebaseFunctions functions(Firebase firebase2, FirebaseApp app) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(app, "app");
        return FirebaseFunctions.INSTANCE.getInstance(app);
    }

    public static final FirebaseFunctions functions(Firebase firebase2, FirebaseApp app, String regionOrCustomDomain) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(regionOrCustomDomain, "regionOrCustomDomain");
        return FirebaseFunctions.INSTANCE.getInstance(app, regionOrCustomDomain);
    }

    public static final HttpsCallableReference getHttpsCallable(FirebaseFunctions firebaseFunctions, String name, Function1<? super HttpsCallableOptions.Builder, Unit> init) {
        Intrinsics.checkNotNullParameter(firebaseFunctions, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(init, "init");
        HttpsCallableOptions.Builder builder = new HttpsCallableOptions.Builder();
        init.invoke(builder);
        return firebaseFunctions.getHttpsCallable(name, builder.build());
    }

    public static final HttpsCallableReference getHttpsCallableFromUrl(FirebaseFunctions firebaseFunctions, URL url, Function1<? super HttpsCallableOptions.Builder, Unit> init) {
        Intrinsics.checkNotNullParameter(firebaseFunctions, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(init, "init");
        HttpsCallableOptions.Builder builder = new HttpsCallableOptions.Builder();
        init.invoke(builder);
        return firebaseFunctions.getHttpsCallableFromUrl(url, builder.build());
    }
}
