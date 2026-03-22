package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResult;
import com.google.firebase.firestore.pipeline.evaluation.EvaluationContext;
import com.google.firebase.firestore.pipeline.evaluation.UtilsKt;
import com.google.firestore.v1.Function;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

/* JADX INFO: compiled from: expressions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0096\u0001\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012i\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0005j\u0002`\r\u0012\u000e\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012B)\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0013B|\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012i\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0005j\u0002`\r¢\u0006\u0004\b\u0011\u0010\u0014B\u0084\u0001\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012i\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0005j\u0002`\r\u0012\u0006\u0010\u0015\u001a\u00020\u0001¢\u0006\u0004\b\u0011\u0010\u0016B\u0098\u0001\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012i\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0005j\u0002`\r\u0012\u0006\u0010\u0015\u001a\u00020\u0001\u0012\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u000e\"\u00020\u0017¢\u0006\u0004\b\u0011\u0010\u0018B\u008c\u0001\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012i\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0005j\u0002`\r\u0012\u0006\u0010\u0019\u001a\u00020\u0001\u0012\u0006\u0010\u001a\u001a\u00020\u0001¢\u0006\u0004\b\u0011\u0010\u001bB \u0001\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012i\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0005j\u0002`\r\u0012\u0006\u0010\u0019\u001a\u00020\u0001\u0012\u0006\u0010\u001a\u001a\u00020\u0001\u0012\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u000e\"\u00020\u0017¢\u0006\u0004\b\u0011\u0010\u001cB\u0084\u0001\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012i\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0005j\u0002`\r\u0012\u0006\u0010\u001d\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u001eB\u0098\u0001\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012i\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0005j\u0002`\r\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u000e\"\u00020\u0017¢\u0006\u0004\b\u0011\u0010\u001fJ\u0015\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0010¢\u0006\u0002\b)J4\u0010*\u001a!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b2\u0006\u0010+\u001a\u00020,H\u0000¢\u0006\u0002\b-J\r\u0010.\u001a\u00020\u0003H\u0010¢\u0006\u0002\b/J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0017H\u0096\u0002J\b\u00103\u001a\u000204H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!Rq\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\u0002\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0005j\u0002`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000eX\u0080\u0004¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/google/firebase/firestore/pipeline/FunctionExpression;", "Lcom/google/firebase/firestore/pipeline/Expression;", "name", "", "function", "Lkotlin/Function1;", "", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lkotlin/ParameterName;", "input", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateDocument;", "params", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateFunction;", "", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;[Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "(Ljava/lang/String;Ljava/util/List;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "param", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lcom/google/firebase/firestore/pipeline/Expression;)V", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lcom/google/firebase/firestore/pipeline/Expression;[Ljava/lang/Object;)V", "param1", "param2", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/Expression;)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/Expression;[Ljava/lang/Object;)V", "fieldName", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;[Ljava/lang/Object;)V", "getName$com_google_firebase_firebase_firestore", "()Ljava/lang/String;", "getParams$com_google_firebase_firebase_firestore", "()[Lcom/google/firebase/firestore/pipeline/Expression;", "[Lcom/google/firebase/firestore/pipeline/Expression;", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "toProto$com_google_firebase_firebase_firestore", "evaluateFunction", "context", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "evaluateFunction$com_google_firebase_firebase_firestore", "canonicalId", "canonicalId$com_google_firebase_firebase_firestore", "equals", "", "other", "hashCode", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public class FunctionExpression extends Expression {
    private final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> function;
    private final String name;
    private final InternalOptions options;
    private final Expression[] params;

    /* JADX INFO: renamed from: getName$com_google_firebase_firebase_firestore, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: getParams$com_google_firebase_firebase_firestore, reason: from getter */
    public final Expression[] getParams() {
        return this.params;
    }

    public /* synthetic */ FunctionExpression(String str, Function1 function1, Expression[] expressionArr, InternalOptions internalOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) function1, expressionArr, (i & 8) != 0 ? InternalOptions.EMPTY : internalOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, Expression[] params, InternalOptions options) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(options, "options");
        this.name = name;
        this.function = function;
        this.params = params;
        this.options = options;
    }

    public /* synthetic */ FunctionExpression(String str, List list, InternalOptions internalOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (List<? extends Expression>) list, (i & 4) != 0 ? InternalOptions.EMPTY : internalOptions);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FunctionExpression(String name, List<? extends Expression> params, InternalOptions options) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(options, "options");
        Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> function1 = FunctionRegistry.INSTANCE.getFunctions().get(name);
        this(name, function1 == null ? UtilsKt.getNotImplemented() : function1, (Expression[]) params.toArray(new Expression[0]), options);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function) {
        this(name, function, new Expression[0], null, 8, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, Expression param) {
        this(name, function, new Expression[]{param}, null, 8, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(param, "param");
    }

    public FunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, Expression param, Object... params) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(params, "params");
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(param);
        spreadBuilder.addSpread(Expression.INSTANCE.toArrayOfExprOrConstant$com_google_firebase_firebase_firestore(params));
        this(name, function, (Expression[]) spreadBuilder.toArray(new Expression[spreadBuilder.size()]), null, 8, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, Expression param1, Expression param2) {
        this(name, function, new Expression[]{param1, param2}, null, 8, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(param1, "param1");
        Intrinsics.checkNotNullParameter(param2, "param2");
    }

    public FunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, Expression param1, Expression param2, Object... params) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(param1, "param1");
        Intrinsics.checkNotNullParameter(param2, "param2");
        Intrinsics.checkNotNullParameter(params, "params");
        SpreadBuilder spreadBuilder = new SpreadBuilder(3);
        spreadBuilder.add(param1);
        spreadBuilder.add(param2);
        spreadBuilder.addSpread(Expression.INSTANCE.toArrayOfExprOrConstant$com_google_firebase_firebase_firestore(params));
        this(name, function, (Expression[]) spreadBuilder.toArray(new Expression[spreadBuilder.size()]), null, 8, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, String fieldName) {
        this(name, function, new Field[]{Expression.INSTANCE.field(fieldName)}, null, 8, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
    }

    public FunctionExpression(String name, Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>> function, String fieldName, Object... params) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        Intrinsics.checkNotNullParameter(params, "params");
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(Expression.INSTANCE.field(fieldName));
        spreadBuilder.addSpread(Expression.INSTANCE.toArrayOfExprOrConstant$com_google_firebase_firebase_firestore(params));
        this(name, function, (Expression[]) spreadBuilder.toArray(new Expression[spreadBuilder.size()]), null, 8, null);
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public Value toProto$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        Function.Builder builderNewBuilder = Function.newBuilder();
        builderNewBuilder.setName(this.name);
        for (Expression expression : this.params) {
            builderNewBuilder.addArgs(expression.toProto$com_google_firebase_firebase_firestore(userDataReader));
        }
        this.options.forEach$com_google_firebase_firebase_firestore(new FunctionExpression$toProto$1(builderNewBuilder));
        Value valueBuild = Value.newBuilder().setFunctionValue(builderNewBuilder).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public final Function1<MutableDocument, EvaluateResult> evaluateFunction$com_google_firebase_firebase_firestore(EvaluationContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> function1 = this.function;
        Expression[] expressionArr = this.params;
        ArrayList arrayList = new ArrayList(expressionArr.length);
        for (Expression expression : expressionArr) {
            arrayList.add(expression.evaluateFunction$com_google_firebase_firebase_firestore(context));
        }
        return function1.invoke(arrayList);
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public String canonicalId$com_google_firebase_firebase_firestore() {
        Expression[] expressionArr = this.params;
        ArrayList arrayList = new ArrayList(expressionArr.length);
        for (Expression expression : expressionArr) {
            arrayList.add(expression.canonicalId$com_google_firebase_firebase_firestore());
        }
        return "fn(" + this.name + '[' + CollectionsKt.joinToString$default(arrayList, ",", null, null, 0, null, null, 62, null) + "])";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FunctionExpression)) {
            return false;
        }
        FunctionExpression functionExpression = (FunctionExpression) other;
        return Intrinsics.areEqual(this.name, functionExpression.name) && Arrays.equals(this.params, functionExpression.params) && Intrinsics.areEqual(this.options, functionExpression.options);
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + Arrays.hashCode(this.params)) * 31) + this.options.hashCode();
    }
}
