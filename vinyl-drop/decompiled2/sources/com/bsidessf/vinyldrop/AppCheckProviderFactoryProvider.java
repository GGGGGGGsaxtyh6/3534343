package com.bsidessf.vinyldrop;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.appcheck.AppCheckProviderFactory;
import kotlin.Metadata;

/* JADX INFO: compiled from: AppCheckProviderFactoryProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/bsidessf/vinyldrop/AppCheckProviderFactoryProvider;", "", "<init>", "()V", "provide", "Lcom/google/firebase/appcheck/AppCheckProviderFactory;", "app"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class AppCheckProviderFactoryProvider {
    public static final AppCheckProviderFactoryProvider INSTANCE = new AppCheckProviderFactoryProvider();

    private AppCheckProviderFactoryProvider() {
    }

    public final AppCheckProviderFactory provide() {
        return CustomAppCheckProviderFactory.INSTANCE;
    }
}
