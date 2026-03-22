package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.model.Values;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u001f\u0010\u000b\u001a\u00020\u00002\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\r\"\u00020\n¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/google/firebase/firestore/pipeline/CollectionHints;", "Lcom/google/firebase/firestore/pipeline/AbstractOptions;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "(Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "()V", "self", "withForceIndex", Values.VECTOR_MAP_VECTORS_KEY, "", "withIgnoreIndexFields", "values", "", "([Ljava/lang/String;)Lcom/google/firebase/firestore/pipeline/CollectionHints;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class CollectionHints extends AbstractOptions<CollectionHints> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionHints(InternalOptions options) {
        super(options);
        Intrinsics.checkNotNullParameter(options, "options");
    }

    public CollectionHints() {
        this(InternalOptions.EMPTY);
    }

    @Override // com.google.firebase.firestore.pipeline.AbstractOptions
    /* JADX INFO: renamed from: self, reason: merged with bridge method [inline-methods] */
    public CollectionHints self$com_google_firebase_firebase_firestore(InternalOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new CollectionHints(options);
    }

    public final CollectionHints withForceIndex(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return with("force_index", value);
    }

    public final CollectionHints withIgnoreIndexFields(String... values) {
        Intrinsics.checkNotNullParameter(values, "values");
        return with("ignore_index_fields", (String[]) Arrays.copyOf(values, values.length));
    }
}
