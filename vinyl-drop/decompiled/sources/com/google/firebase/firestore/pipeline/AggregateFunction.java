package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firestore.v1.Function;
import com.google.firestore.v1.Value;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: aggregates.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B+\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\u000bB\u0019\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\rB\u0019\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\u000fJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0003J\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/google/firebase/firestore/pipeline/AggregateFunction;", "", "name", "", "params", "", "Lcom/google/firebase/firestore/pipeline/Expression;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "(Ljava/lang/String;[Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "(Ljava/lang/String;)V", "expr", "(Ljava/lang/String;Lcom/google/firebase/firestore/pipeline/Expression;)V", "fieldName", "(Ljava/lang/String;Ljava/lang/String;)V", "[Lcom/google/firebase/firestore/pipeline/Expression;", "alias", "Lcom/google/firebase/firestore/pipeline/AliasedAggregate;", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "toProto$com_google_firebase_firebase_firestore", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class AggregateFunction {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String name;
    private final InternalOptions options;
    private final Expression[] params;

    public /* synthetic */ AggregateFunction(String str, Expression expression, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, expression);
    }

    public /* synthetic */ AggregateFunction(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }

    public /* synthetic */ AggregateFunction(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @JvmStatic
    public static final AggregateFunction average(Expression expression) {
        return INSTANCE.average(expression);
    }

    @JvmStatic
    public static final AggregateFunction average(String str) {
        return INSTANCE.average(str);
    }

    @JvmStatic
    public static final AggregateFunction count(Expression expression) {
        return INSTANCE.count(expression);
    }

    @JvmStatic
    public static final AggregateFunction count(String str) {
        return INSTANCE.count(str);
    }

    @JvmStatic
    public static final AggregateFunction countAll() {
        return INSTANCE.countAll();
    }

    @JvmStatic
    public static final AggregateFunction countDistinct(Expression expression) {
        return INSTANCE.countDistinct(expression);
    }

    @JvmStatic
    public static final AggregateFunction countDistinct(String str) {
        return INSTANCE.countDistinct(str);
    }

    @JvmStatic
    public static final AggregateFunction countIf(BooleanExpression booleanExpression) {
        return INSTANCE.countIf(booleanExpression);
    }

    @JvmStatic
    public static final AggregateFunction maximum(Expression expression) {
        return INSTANCE.maximum(expression);
    }

    @JvmStatic
    public static final AggregateFunction maximum(String str) {
        return INSTANCE.maximum(str);
    }

    @JvmStatic
    public static final AggregateFunction minimum(Expression expression) {
        return INSTANCE.minimum(expression);
    }

    @JvmStatic
    public static final AggregateFunction minimum(String str) {
        return INSTANCE.minimum(str);
    }

    @JvmStatic
    public static final AggregateFunction rawAggregate(String str, Expression... expressionArr) {
        return INSTANCE.rawAggregate(str, expressionArr);
    }

    @JvmStatic
    public static final AggregateFunction sum(Expression expression) {
        return INSTANCE.sum(expression);
    }

    @JvmStatic
    public static final AggregateFunction sum(String str) {
        return INSTANCE.sum(str);
    }

    private AggregateFunction(String str, Expression[] expressionArr, InternalOptions internalOptions) {
        this.name = str;
        this.params = expressionArr;
        this.options = internalOptions;
    }

    /* synthetic */ AggregateFunction(String str, Expression[] expressionArr, InternalOptions internalOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, expressionArr, (i & 4) != 0 ? InternalOptions.EMPTY : internalOptions);
    }

    private AggregateFunction(String str) {
        this(str, new Expression[0], null, 4, null);
    }

    private AggregateFunction(String str, Expression expression) {
        this(str, new Expression[]{expression}, null, 4, null);
    }

    private AggregateFunction(String str, String str2) {
        this(str, Expression.INSTANCE.field(str2));
    }

    /* JADX INFO: compiled from: aggregates.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\nH\u0007¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\u0005H\u0007J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0007H\u0007J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\nH\u0007J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0007H\u0007J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\nH\u0007J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0007H\u0007J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\nH\u0007J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0007H\u0007J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\nH\u0007J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0007H\u0007J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\nH\u0007J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0007H\u0007J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\nH\u0007¨\u0006\u0018"}, d2 = {"Lcom/google/firebase/firestore/pipeline/AggregateFunction$Companion;", "", "<init>", "()V", "rawAggregate", "Lcom/google/firebase/firestore/pipeline/AggregateFunction;", "name", "", "expr", "", "Lcom/google/firebase/firestore/pipeline/Expression;", "(Ljava/lang/String;[Lcom/google/firebase/firestore/pipeline/Expression;)Lcom/google/firebase/firestore/pipeline/AggregateFunction;", "countAll", "count", "fieldName", "expression", "countIf", "condition", "Lcom/google/firebase/firestore/pipeline/BooleanExpression;", "sum", "average", "minimum", "maximum", "countDistinct", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AggregateFunction rawAggregate(String name, Expression... expr) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new AggregateFunction(name, expr, null, 4, null);
        }

        @JvmStatic
        public final AggregateFunction countAll() {
            return new AggregateFunction("count", (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction count(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new AggregateFunction("count", fieldName, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction count(Expression expression) {
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new AggregateFunction("count", expression, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction countIf(BooleanExpression condition) {
            Intrinsics.checkNotNullParameter(condition, "condition");
            return new AggregateFunction("count_if", condition, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction sum(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new AggregateFunction("sum", fieldName, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction sum(Expression expression) {
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new AggregateFunction("sum", expression, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction average(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new AggregateFunction("average", fieldName, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction average(Expression expression) {
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new AggregateFunction("average", expression, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction minimum(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new AggregateFunction("minimum", fieldName, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction minimum(Expression expression) {
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new AggregateFunction("minimum", expression, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction maximum(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new AggregateFunction("maximum", fieldName, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction maximum(Expression expression) {
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new AggregateFunction("maximum", expression, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction countDistinct(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new AggregateFunction("count_distinct", fieldName, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final AggregateFunction countDistinct(Expression expression) {
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new AggregateFunction("count_distinct", expression, (DefaultConstructorMarker) null);
        }
    }

    public final AliasedAggregate alias(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        return new AliasedAggregate(alias, this);
    }

    public final Value toProto$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        Function.Builder builderNewBuilder = Function.newBuilder();
        builderNewBuilder.setName(this.name);
        for (Expression expression : this.params) {
            builderNewBuilder.addArgs(expression.toProto$com_google_firebase_firebase_firestore(userDataReader));
        }
        this.options.forEach$com_google_firebase_firebase_firestore(new AggregateFunction$toProto$1(builderNewBuilder));
        Value valueBuild = Value.newBuilder().setFunctionValue(builderNewBuilder).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }
}
