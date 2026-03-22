package com.google.firebase.firestore.pipeline.evaluation;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.Value;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EvaluateResult.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u0014\u0010\u000b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResultValue;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", Values.VECTOR_MAP_VECTORS_KEY, "Lcom/google/firestore/v1/Value;", "<init>", "(Lcom/google/firestore/v1/Value;)V", "getValue", "()Lcom/google/firestore/v1/Value;", "isSuccess", "", "()Z", "isError", "isUnset", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class EvaluateResultValue extends EvaluateResult {
    private final boolean isError;
    private final boolean isSuccess;
    private final boolean isUnset;
    private final Value value;

    public static /* synthetic */ EvaluateResultValue copy$default(EvaluateResultValue evaluateResultValue, Value value, int i, Object obj) {
        if ((i & 1) != 0) {
            value = evaluateResultValue.value;
        }
        return evaluateResultValue.copy(value);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Value getValue() {
        return this.value;
    }

    public final EvaluateResultValue copy(Value value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new EvaluateResultValue(value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof EvaluateResultValue) && Intrinsics.areEqual(this.value, ((EvaluateResultValue) other).value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return "EvaluateResultValue(value=" + this.value + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EvaluateResultValue(Value value) {
        super(null);
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        this.isSuccess = true;
    }

    @Override // com.google.firebase.firestore.pipeline.evaluation.EvaluateResult
    public Value getValue() {
        return this.value;
    }

    @Override // com.google.firebase.firestore.pipeline.evaluation.EvaluateResult
    /* JADX INFO: renamed from: isSuccess, reason: from getter */
    public boolean getIsSuccess() {
        return this.isSuccess;
    }

    @Override // com.google.firebase.firestore.pipeline.evaluation.EvaluateResult
    /* JADX INFO: renamed from: isError, reason: from getter */
    public boolean getIsError() {
        return this.isError;
    }

    @Override // com.google.firebase.firestore.pipeline.evaluation.EvaluateResult
    /* JADX INFO: renamed from: isUnset, reason: from getter */
    public boolean getIsUnset() {
        return this.isUnset;
    }
}
