package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResult;
import com.google.firebase.firestore.pipeline.evaluation.EvaluationContext;
import com.google.firestore.v1.Value;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

/* JADX INFO: compiled from: expressions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u008c\u0001\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012i\u0010\b\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\n¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\tj\u0002`\u0011\u0012\u000e\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0012¢\u0006\u0004\b\u0004\u0010\u0013B\u0084\u0001\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012i\u0010\b\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\n¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\tj\u0002`\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0015B\u008c\u0001\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012i\u0010\b\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\n¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\tj\u0002`\u0011\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b\u0004\u0010\u0019B\u0098\u0001\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012i\u0010\b\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\n¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\tj\u0002`\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u0012\"\u00020\u0018¢\u0006\u0004\b\u0004\u0010\u001aB\u008c\u0001\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012i\u0010\b\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\n¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\tj\u0002`\u0011\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u001bB\u0084\u0001\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012i\u0010\b\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\n¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\tj\u0002`\u0011\u0012\u0006\u0010\u001c\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\u001dB\u0098\u0001\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012i\u0010\b\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\n¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\tj\u0002`\u0011\u0012\u0006\u0010\u001c\u001a\u00020\u0007\u0012\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u0012\"\u00020\u0018¢\u0006\u0004\b\u0004\u0010\u001eJ\u0015\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0010¢\u0006\u0002\b%J4\u0010&\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f2\u0006\u0010'\u001a\u00020(H\u0010¢\u0006\u0002\b)J\r\u0010*\u001a\u00020\u0007H\u0010¢\u0006\u0002\b+J\b\u0010,\u001a\u00020\u0007H\u0016J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u00100\u001a\u000201H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00062"}, d2 = {"Lcom/google/firebase/firestore/pipeline/BooleanFunctionExpression;", "Lcom/google/firebase/firestore/pipeline/BooleanExpression;", "expr", "Lcom/google/firebase/firestore/pipeline/Expression;", "<init>", "(Lcom/google/firebase/firestore/pipeline/Expression;)V", "name", "", "function", "Lkotlin/Function1;", "", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lkotlin/ParameterName;", "input", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateDocument;", "params", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateFunction;", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;[Lcom/google/firebase/firestore/pipeline/Expression;)V", "param", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lcom/google/firebase/firestore/pipeline/Expression;)V", "param1", "param2", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lcom/google/firebase/firestore/pipeline/Expression;Ljava/lang/Object;)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lcom/google/firebase/firestore/pipeline/Expression;[Ljava/lang/Object;)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/Expression;)V", "fieldName", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;[Ljava/lang/Object;)V", "getExpr", "()Lcom/google/firebase/firestore/pipeline/Expression;", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "toProto$com_google_firebase_firebase_firestore", "evaluateFunction", "context", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "evaluateFunction$com_google_firebase_firebase_firestore", "canonicalId", "canonicalId$com_google_firebase_firebase_firestore", "toString", "equals", "", "other", "hashCode", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class BooleanFunctionExpression extends BooleanExpression {
    private final Expression expr;

    public final Expression getExpr() {
        return this.expr;
    }

    public BooleanFunctionExpression(Expression expr) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        this.expr = expr;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BooleanFunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, Expression[] params) {
        this(new FunctionExpression(name, function, params, null, 8, null));
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BooleanFunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, Expression param) {
        this(name, function, new Expression[]{param});
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(param, "param");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BooleanFunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, Expression param1, Object param2) {
        this(name, function, new Expression[]{param1, Expression.INSTANCE.toExprOrConstant$com_google_firebase_firebase_firestore(param2)});
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(param1, "param1");
        Intrinsics.checkNotNullParameter(param2, "param2");
    }

    public BooleanFunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, Expression param, Object... params) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(params, "params");
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(param);
        spreadBuilder.addSpread(Expression.INSTANCE.toArrayOfExprOrConstant$com_google_firebase_firebase_firestore(params));
        this(name, function, (Expression[]) spreadBuilder.toArray(new Expression[spreadBuilder.size()]));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BooleanFunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, Expression param1, Expression param2) {
        this(name, function, new Expression[]{param1, param2});
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(param1, "param1");
        Intrinsics.checkNotNullParameter(param2, "param2");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BooleanFunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, String fieldName) {
        this(name, function, new Field[]{Expression.INSTANCE.field(fieldName)});
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
    }

    public BooleanFunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, String fieldName, Object... params) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        Intrinsics.checkNotNullParameter(params, "params");
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(Expression.INSTANCE.field(fieldName));
        spreadBuilder.addSpread(Expression.INSTANCE.toArrayOfExprOrConstant$com_google_firebase_firebase_firestore(params));
        this(name, function, (Expression[]) spreadBuilder.toArray(new Expression[spreadBuilder.size()]));
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public Value toProto$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        return this.expr.toProto$com_google_firebase_firebase_firestore(userDataReader);
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public Function1<MutableDocument, EvaluateResult> evaluateFunction$com_google_firebase_firebase_firestore(EvaluationContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return this.expr.evaluateFunction$com_google_firebase_firebase_firestore(context);
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public String canonicalId$com_google_firebase_firebase_firestore() {
        return this.expr.canonicalId$com_google_firebase_firebase_firestore();
    }

    public String toString() {
        return this.expr.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof BooleanFunctionExpression) {
            return Intrinsics.areEqual(this.expr, ((BooleanFunctionExpression) other).expr);
        }
        if (other instanceof FunctionExpression) {
            return Intrinsics.areEqual(this.expr, other);
        }
        return false;
    }

    public int hashCode() {
        return this.expr.hashCode();
    }
}
