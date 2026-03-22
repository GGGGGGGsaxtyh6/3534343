package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H\u0010¢\u0006\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/google/firebase/firestore/pipeline/CollectionSourceOptions;", "Lcom/google/firebase/firestore/pipeline/AbstractOptions;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "(Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "()V", "withHints", "hints", "Lcom/google/firebase/firestore/pipeline/CollectionHints;", "self", "self$com_google_firebase_firebase_firestore", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class CollectionSourceOptions extends AbstractOptions<CollectionSourceOptions> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionSourceOptions(InternalOptions options) {
        super(options);
        Intrinsics.checkNotNullParameter(options, "options");
    }

    public CollectionSourceOptions() {
        this(InternalOptions.EMPTY);
    }

    public final CollectionSourceOptions withHints(CollectionHints hints) {
        Intrinsics.checkNotNullParameter(hints, "hints");
        return adding(hints);
    }

    @Override // com.google.firebase.firestore.pipeline.AbstractOptions
    public CollectionSourceOptions self$com_google_firebase_firebase_firestore(InternalOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new CollectionSourceOptions(options);
    }
}
