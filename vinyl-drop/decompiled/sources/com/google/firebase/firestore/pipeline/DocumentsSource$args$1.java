package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.Value;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* synthetic */ class DocumentsSource$args$1 extends FunctionReferenceImpl implements Function1<ResourcePath, Value> {
    DocumentsSource$args$1(Object obj) {
        super(1, obj, Values.class, "encodeValue", "encodeValue(Lcom/google/firebase/firestore/model/ResourcePath;)Lcom/google/firestore/v1/Value;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Value invoke(ResourcePath p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Values.encodeValue(p0);
    }
}
