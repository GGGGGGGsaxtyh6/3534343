package com.bsidessf.vinyldrop;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VinylDropApp.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/bsidessf/vinyldrop/VinylDropApp;", "Landroid/app/Application;", "<init>", "()V", "onCreate", "", "app"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class VinylDropApp extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAppCheck, "getInstance(...)");
        firebaseAppCheck.installAppCheckProviderFactory(AppCheckProviderFactoryProvider.INSTANCE.provide());
        firebaseAppCheck.setTokenAutoRefreshEnabled(true);
    }
}
