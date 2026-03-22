package com.google.firebase.firestore.pipeline.evaluation;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.RealtimePipeline;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "", "pipeline", "Lcom/google/firebase/firestore/RealtimePipeline;", "<init>", "(Lcom/google/firebase/firestore/RealtimePipeline;)V", "getPipeline", "()Lcom/google/firebase/firestore/RealtimePipeline;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class EvaluationContext {
    private final RealtimePipeline pipeline;

    public EvaluationContext(RealtimePipeline pipeline) {
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        this.pipeline = pipeline;
    }

    public final RealtimePipeline getPipeline() {
        return this.pipeline;
    }
}
