package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResult;
import com.google.firebase.firestore.pipeline.evaluation.EvaluationContext;
import com.google.firestore.v1.Value;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: expressions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0010¢\u0006\u0002\b\fJ4\u0010\r\u001a!\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\u000ej\u0002`\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0010¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\u0019H\u0010¢\u0006\u0002\b\u001aJ\b\u0010\u001b\u001a\u00020\u0019H\u0016J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\b\u0010 \u001a\u00020!H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\""}, d2 = {"Lcom/google/firebase/firestore/pipeline/BooleanField;", "Lcom/google/firebase/firestore/pipeline/BooleanExpression;", "field", "Lcom/google/firebase/firestore/pipeline/Field;", "<init>", "(Lcom/google/firebase/firestore/pipeline/Field;)V", "getField", "()Lcom/google/firebase/firestore/pipeline/Field;", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "toProto$com_google_firebase_firebase_firestore", "evaluateFunction", "Lkotlin/Function1;", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lkotlin/ParameterName;", "name", "input", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateDocument;", "context", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "evaluateFunction$com_google_firebase_firebase_firestore", "canonicalId", "", "canonicalId$com_google_firebase_firebase_firestore", "toString", "equals", "", "other", "", "hashCode", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class BooleanField extends BooleanExpression {
    private final Field field;

    public BooleanField(Field field) {
        Intrinsics.checkNotNullParameter(field, "field");
        this.field = field;
    }

    public final Field getField() {
        return this.field;
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public Value toProto$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        return this.field.toProto$com_google_firebase_firebase_firestore(userDataReader);
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public Function1<MutableDocument, EvaluateResult> evaluateFunction$com_google_firebase_firebase_firestore(EvaluationContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return this.field.evaluateFunction$com_google_firebase_firebase_firestore(context);
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public String canonicalId$com_google_firebase_firebase_firestore() {
        return this.field.canonicalId$com_google_firebase_firebase_firestore();
    }

    public String toString() {
        return this.field.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof BooleanField) {
            return Intrinsics.areEqual(this.field, ((BooleanField) other).field);
        }
        if (other instanceof Field) {
            return Intrinsics.areEqual(this.field, other);
        }
        return false;
    }

    public int hashCode() {
        return this.field.hashCode();
    }
}
