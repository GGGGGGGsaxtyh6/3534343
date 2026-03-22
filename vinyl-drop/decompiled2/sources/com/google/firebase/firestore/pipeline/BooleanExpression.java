package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.pipeline.evaluation.UtilsKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: expressions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0007\b'\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0016\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001J\u0016\u0010\u0006\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\u0000J\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/google/firebase/firestore/pipeline/BooleanExpression;", "Lcom/google/firebase/firestore/pipeline/Expression;", "<init>", "()V", "countIf", "Lcom/google/firebase/firestore/pipeline/AggregateFunction;", "conditional", "thenExpr", "elseExpr", "thenValue", "", "elseValue", "not", "ifError", "catchExpr", "ifError$com_google_firebase_firebase_firestore", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class BooleanExpression extends Expression {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final BooleanExpression rawFunction(String str, Expression... expressionArr) {
        return INSTANCE.rawFunction(str, expressionArr);
    }

    public final AggregateFunction countIf() {
        return AggregateFunction.INSTANCE.countIf(this);
    }

    public final Expression conditional(Expression thenExpr, Expression elseExpr) {
        Intrinsics.checkNotNullParameter(thenExpr, "thenExpr");
        Intrinsics.checkNotNullParameter(elseExpr, "elseExpr");
        return Expression.INSTANCE.conditional(this, thenExpr, elseExpr);
    }

    public final Expression conditional(Object thenValue, Object elseValue) {
        Intrinsics.checkNotNullParameter(thenValue, "thenValue");
        Intrinsics.checkNotNullParameter(elseValue, "elseValue");
        return Expression.INSTANCE.conditional(this, thenValue, elseValue);
    }

    public final BooleanExpression not() {
        return Expression.INSTANCE.not(this);
    }

    public final BooleanExpression ifError$com_google_firebase_firebase_firestore(BooleanExpression catchExpr) {
        Intrinsics.checkNotNullParameter(catchExpr, "catchExpr");
        return Expression.INSTANCE.ifError(this, catchExpr);
    }

    /* JADX INFO: compiled from: expressions.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\nH\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/google/firebase/firestore/pipeline/BooleanExpression$Companion;", "", "<init>", "()V", "rawFunction", "Lcom/google/firebase/firestore/pipeline/BooleanExpression;", "name", "", "expr", "", "Lcom/google/firebase/firestore/pipeline/Expression;", "(Ljava/lang/String;[Lcom/google/firebase/firestore/pipeline/Expression;)Lcom/google/firebase/firestore/pipeline/BooleanExpression;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BooleanExpression rawFunction(String name, Expression... expr) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new BooleanFunctionExpression(name, UtilsKt.getNotImplemented(), expr);
        }
    }
}
