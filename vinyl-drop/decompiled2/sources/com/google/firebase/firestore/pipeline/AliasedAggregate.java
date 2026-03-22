package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: aggregates.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/google/firebase/firestore/pipeline/AliasedAggregate;", "", "alias", "", "expr", "Lcom/google/firebase/firestore/pipeline/AggregateFunction;", "<init>", "(Ljava/lang/String;Lcom/google/firebase/firestore/pipeline/AggregateFunction;)V", "getAlias$com_google_firebase_firebase_firestore", "()Ljava/lang/String;", "getExpr$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/pipeline/AggregateFunction;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class AliasedAggregate {
    private final String alias;
    private final AggregateFunction expr;

    public AliasedAggregate(String alias, AggregateFunction expr) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        Intrinsics.checkNotNullParameter(expr, "expr");
        this.alias = alias;
        this.expr = expr;
    }

    /* JADX INFO: renamed from: getAlias$com_google_firebase_firebase_firestore, reason: from getter */
    public final String getAlias() {
        return this.alias;
    }

    /* JADX INFO: renamed from: getExpr$com_google_firebase_firebase_firestore, reason: from getter */
    public final AggregateFunction getExpr() {
        return this.expr;
    }
}
