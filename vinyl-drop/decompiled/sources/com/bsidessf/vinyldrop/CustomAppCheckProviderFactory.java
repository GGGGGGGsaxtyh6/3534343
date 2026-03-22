package com.bsidessf.vinyldrop;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckProvider;
import com.google.firebase.appcheck.AppCheckProviderFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomAppCheckProviderFactory.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\u0006"}, d2 = {"Lcom/bsidessf/vinyldrop/CustomAppCheckProviderFactory;", "Lcom/google/firebase/appcheck/AppCheckProviderFactory;", "<init>", "()V", "create", "Lcom/google/firebase/appcheck/AppCheckProvider;", "app", "Lcom/google/firebase/FirebaseApp;"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class CustomAppCheckProviderFactory implements AppCheckProviderFactory {
    public static final CustomAppCheckProviderFactory INSTANCE = new CustomAppCheckProviderFactory();

    private CustomAppCheckProviderFactory() {
    }

    @Override // com.google.firebase.appcheck.AppCheckProviderFactory
    public AppCheckProvider create(FirebaseApp app) {
        Intrinsics.checkNotNullParameter(app, "app");
        return new CustomAppCheckProvider(app);
    }
}
