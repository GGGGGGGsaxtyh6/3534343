package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.pipeline.Expression;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResult;
import com.google.firebase.firestore.pipeline.evaluation.EvaluationContext;
import com.google.firestore.v1.Value;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: expressions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0010¢\u0006\u0002\b\fJ4\u0010\r\u001a!\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\u000ej\u0002`\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0010¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\u0019H\u0010¢\u0006\u0002\b\u001aJ\b\u0010\u001b\u001a\u00020\u0019H\u0016J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\b\u0010 \u001a\u00020!H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\""}, d2 = {"Lcom/google/firebase/firestore/pipeline/BooleanConstant;", "Lcom/google/firebase/firestore/pipeline/BooleanExpression;", "constant", "Lcom/google/firebase/firestore/pipeline/Expression$Constant;", "<init>", "(Lcom/google/firebase/firestore/pipeline/Expression$Constant;)V", "getConstant", "()Lcom/google/firebase/firestore/pipeline/Expression$Constant;", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "toProto$com_google_firebase_firebase_firestore", "evaluateFunction", "Lkotlin/Function1;", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lkotlin/ParameterName;", "name", "input", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateDocument;", "context", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "evaluateFunction$com_google_firebase_firebase_firestore", "canonicalId", "", "canonicalId$com_google_firebase_firebase_firestore", "toString", "equals", "", "other", "", "hashCode", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class BooleanConstant extends BooleanExpression {
    private final Expression.Constant constant;

    public BooleanConstant(Expression.Constant constant) {
        Intrinsics.checkNotNullParameter(constant, "constant");
        this.constant = constant;
    }

    public final Expression.Constant getConstant() {
        return this.constant;
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public Value toProto$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        return this.constant.getValue();
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public Function1<MutableDocument, EvaluateResult> evaluateFunction$com_google_firebase_firebase_firestore(EvaluationContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return this.constant.evaluateFunction$com_google_firebase_firebase_firestore(context);
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public String canonicalId$com_google_firebase_firebase_firestore() {
        return this.constant.canonicalId$com_google_firebase_firebase_firestore();
    }

    public String toString() {
        return this.constant.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof BooleanConstant) {
            return Intrinsics.areEqual(this.constant, ((BooleanConstant) other).constant);
        }
        if (other instanceof Expression.Constant) {
            return Intrinsics.areEqual(this.constant, other);
        }
        return false;
    }

    public int hashCode() {
        return this.constant.hashCode();
    }
}
