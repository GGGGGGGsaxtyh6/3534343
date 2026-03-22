package com.google.firebase.firestore.pipeline.evaluation;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.model.Values;
import kotlin.Metadata;

/* JADX INFO: compiled from: Arithmetic.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/google/firebase/firestore/pipeline/evaluation/LongValue;", "Lcom/google/firebase/firestore/pipeline/evaluation/FirestoreNumber;", Values.VECTOR_MAP_VECTORS_KEY, "", "<init>", "(J)V", "getValue", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class LongValue implements FirestoreNumber {
    private final long value;

    public static /* synthetic */ LongValue copy$default(LongValue longValue, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = longValue.value;
        }
        return longValue.copy(j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getValue() {
        return this.value;
    }

    public final LongValue copy(long value) {
        return new LongValue(value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LongValue) && this.value == ((LongValue) other).value;
    }

    public int hashCode() {
        return Long.hashCode(this.value);
    }

    public String toString() {
        return "LongValue(value=" + this.value + ')';
    }

    public LongValue(long j) {
        this.value = j;
    }

    public final long getValue() {
        return this.value;
    }
}
