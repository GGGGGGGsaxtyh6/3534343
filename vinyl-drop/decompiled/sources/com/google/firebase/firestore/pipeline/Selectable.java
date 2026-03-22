package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.FieldPath;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: expressions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b'\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X \u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u0001X \u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/google/firebase/firestore/pipeline/Selectable;", "Lcom/google/firebase/firestore/pipeline/Expression;", "<init>", "()V", "alias", "", "getAlias$com_google_firebase_firebase_firestore", "()Ljava/lang/String;", "expr", "getExpr$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/pipeline/Expression;", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class Selectable extends Expression {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: getAlias$com_google_firebase_firebase_firestore */
    public abstract String getAlias();

    /* JADX INFO: renamed from: getExpr$com_google_firebase_firebase_firestore */
    public abstract Expression getExpr();

    /* JADX INFO: compiled from: expressions.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001¨\u0006\u0007"}, d2 = {"Lcom/google/firebase/firestore/pipeline/Selectable$Companion;", "", "<init>", "()V", "toSelectable", "Lcom/google/firebase/firestore/pipeline/Selectable;", "o", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Selectable toSelectable(Object o) {
            Intrinsics.checkNotNullParameter(o, "o");
            if (o instanceof Selectable) {
                return (Selectable) o;
            }
            if (o instanceof String) {
                return Expression.INSTANCE.field((String) o);
            }
            if (o instanceof FieldPath) {
                return Expression.INSTANCE.field((FieldPath) o);
            }
            throw new IllegalArgumentException("Unknown Selectable type: " + o);
        }
    }
}
