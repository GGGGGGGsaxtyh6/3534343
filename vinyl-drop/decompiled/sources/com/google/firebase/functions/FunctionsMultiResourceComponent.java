package com.google.firebase.functions;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.functions.dagger.assisted.Assisted;
import com.google.firebase.functions.dagger.assisted.AssistedFactory;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FunctionsMultiResourceComponent.kt */
/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001:\u0001\fB\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\bH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/google/firebase/functions/FunctionsMultiResourceComponent;", "", "functionsFactory", "Lcom/google/firebase/functions/FunctionsMultiResourceComponent$FirebaseFunctionsFactory;", "<init>", "(Lcom/google/firebase/functions/FunctionsMultiResourceComponent$FirebaseFunctionsFactory;)V", "instances", "", "", "Lcom/google/firebase/functions/FirebaseFunctions;", "get", "regionOrCustomDomain", "FirebaseFunctionsFactory", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FunctionsMultiResourceComponent {
    private final FirebaseFunctionsFactory functionsFactory;
    private final Map<String, FirebaseFunctions> instances;

    /* JADX INFO: compiled from: FunctionsMultiResourceComponent.kt */
    @AssistedFactory
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\ba\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/google/firebase/functions/FunctionsMultiResourceComponent$FirebaseFunctionsFactory;", "", "create", "Lcom/google/firebase/functions/FirebaseFunctions;", "regionOrCustomDomain", "", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface FirebaseFunctionsFactory {
        FirebaseFunctions create(@Assisted String regionOrCustomDomain);
    }

    @Inject
    public FunctionsMultiResourceComponent(FirebaseFunctionsFactory functionsFactory) {
        Intrinsics.checkNotNullParameter(functionsFactory, "functionsFactory");
        this.functionsFactory = functionsFactory;
        this.instances = new HashMap();
    }

    public final synchronized FirebaseFunctions get(String regionOrCustomDomain) {
        FirebaseFunctions firebaseFunctionsCreate;
        Intrinsics.checkNotNullParameter(regionOrCustomDomain, "regionOrCustomDomain");
        firebaseFunctionsCreate = this.instances.get(regionOrCustomDomain);
        if (firebaseFunctionsCreate == null) {
            firebaseFunctionsCreate = this.functionsFactory.create(regionOrCustomDomain);
            this.instances.put(regionOrCustomDomain, firebaseFunctionsCreate);
        }
        return firebaseFunctionsCreate;
    }
}
