package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firestore.v1.Pipeline;
import com.google.firestore.v1.Value;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* synthetic */ class Stage$toProtoStage$2 extends AdaptedFunctionReference implements Function2<String, Value, Unit> {
    Stage$toProtoStage$2(Object obj) {
        super(2, obj, Pipeline.Stage.Builder.class, "putOptions", "putOptions(Ljava/lang/String;Lcom/google/firestore/v1/Value;)Lcom/google/firestore/v1/Pipeline$Stage$Builder;", 8);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(String str, Value value) {
        invoke2(str, value);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String str, Value value) {
        ((Pipeline.Stage.Builder) this.receiver).putOptions(str, value);
    }
}
