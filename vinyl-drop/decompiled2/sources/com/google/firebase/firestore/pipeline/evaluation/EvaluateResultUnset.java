package com.google.firebase.firestore.pipeline.evaluation;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.Value;
import kotlin.Metadata;

/* JADX INFO: compiled from: EvaluateResult.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u0014\u0010\u000b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResultUnset;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "<init>", "()V", Values.VECTOR_MAP_VECTORS_KEY, "Lcom/google/firestore/v1/Value;", "getValue", "()Lcom/google/firestore/v1/Value;", "isSuccess", "", "()Z", "isError", "isUnset", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class EvaluateResultUnset extends EvaluateResult {
    private static final boolean isError = false;
    private static final Value value = null;
    public static final EvaluateResultUnset INSTANCE = new EvaluateResultUnset();
    private static final boolean isSuccess = true;
    private static final boolean isUnset = true;

    private EvaluateResultUnset() {
        super(null);
    }

    @Override // com.google.firebase.firestore.pipeline.evaluation.EvaluateResult
    public Value getValue() {
        return value;
    }

    @Override // com.google.firebase.firestore.pipeline.evaluation.EvaluateResult
    public boolean isSuccess() {
        return isSuccess;
    }

    @Override // com.google.firebase.firestore.pipeline.evaluation.EvaluateResult
    public boolean isError() {
        return isError;
    }

    @Override // com.google.firebase.firestore.pipeline.evaluation.EvaluateResult
    public boolean isUnset() {
        return isUnset;
    }
}
