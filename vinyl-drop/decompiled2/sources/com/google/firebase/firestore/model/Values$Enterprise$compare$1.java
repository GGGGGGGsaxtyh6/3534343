package com.google.firebase.firestore.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firestore.v1.Value;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Values.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* synthetic */ class Values$Enterprise$compare$1 extends FunctionReferenceImpl implements Function2<Value, Value, Integer> {
    Values$Enterprise$compare$1(Object obj) {
        super(2, obj, Values.class, "compare", "compare(Lcom/google/firestore/v1/Value;Lcom/google/firestore/v1/Value;)I", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Integer invoke(Value p0, Value p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return Integer.valueOf(Values.compare(p0, p1));
    }
}
