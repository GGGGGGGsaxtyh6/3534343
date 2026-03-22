package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: expressions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0002\u0019\u001aB\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/google/firebase/firestore/pipeline/Ordering;", "", "expr", "Lcom/google/firebase/firestore/pipeline/Expression;", "dir", "Lcom/google/firebase/firestore/pipeline/Ordering$Direction;", "<init>", "(Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/Ordering$Direction;)V", "getExpr", "()Lcom/google/firebase/firestore/pipeline/Expression;", "getDir", "()Lcom/google/firebase/firestore/pipeline/Ordering$Direction;", "canonicalId", "", "canonicalId$com_google_firebase_firebase_firestore", "equals", "", "other", "hashCode", "", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "toProto$com_google_firebase_firebase_firestore", "Companion", "Direction", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class Ordering {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Direction dir;
    private final Expression expr;

    @JvmStatic
    public static final Ordering ascending(Expression expression) {
        return INSTANCE.ascending(expression);
    }

    @JvmStatic
    public static final Ordering ascending(String str) {
        return INSTANCE.ascending(str);
    }

    @JvmStatic
    public static final Ordering descending(Expression expression) {
        return INSTANCE.descending(expression);
    }

    @JvmStatic
    public static final Ordering descending(String str) {
        return INSTANCE.descending(str);
    }

    public Ordering(Expression expr, Direction dir) {
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(dir, "dir");
        this.expr = expr;
        this.dir = dir;
    }

    public final Direction getDir() {
        return this.dir;
    }

    public final Expression getExpr() {
        return this.expr;
    }

    public final String canonicalId$com_google_firebase_firebase_firestore() {
        return this.expr.canonicalId$com_google_firebase_firebase_firestore() + (this.dir == Direction.ASCENDING ? "asc" : "desc");
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Ordering)) {
            return false;
        }
        Ordering ordering = (Ordering) other;
        return Intrinsics.areEqual(this.expr, ordering.expr) && this.dir == ordering.dir;
    }

    public int hashCode() {
        return (this.expr.hashCode() * 31) + this.dir.hashCode();
    }

    /* JADX INFO: compiled from: expressions.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\u000b"}, d2 = {"Lcom/google/firebase/firestore/pipeline/Ordering$Companion;", "", "<init>", "()V", "ascending", "Lcom/google/firebase/firestore/pipeline/Ordering;", "expr", "Lcom/google/firebase/firestore/pipeline/Expression;", "fieldName", "", "descending", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Ordering ascending(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new Ordering(expr, Direction.ASCENDING);
        }

        @JvmStatic
        public final Ordering ascending(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new Ordering(Expression.INSTANCE.field(fieldName), Direction.ASCENDING);
        }

        @JvmStatic
        public final Ordering descending(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new Ordering(expr, Direction.DESCENDING);
        }

        @JvmStatic
        public final Ordering descending(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new Ordering(Expression.INSTANCE.field(fieldName), Direction.DESCENDING);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: expressions.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/google/firebase/firestore/pipeline/Ordering$Direction;", "", "proto", "Lcom/google/firestore/v1/Value;", "<init>", "(Ljava/lang/String;ILcom/google/firestore/v1/Value;)V", "getProto", "()Lcom/google/firestore/v1/Value;", "ASCENDING", "DESCENDING", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Direction {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Direction[] $VALUES;
        public static final Direction ASCENDING = new Direction("ASCENDING", 0, Values.encodeValue("ascending"));
        public static final Direction DESCENDING = new Direction("DESCENDING", 1, Values.encodeValue("descending"));
        private final Value proto;

        private static final /* synthetic */ Direction[] $values() {
            return new Direction[]{ASCENDING, DESCENDING};
        }

        public static EnumEntries<Direction> getEntries() {
            return $ENTRIES;
        }

        private Direction(String str, int i, Value value) {
            this.proto = value;
        }

        public final Value getProto() {
            return this.proto;
        }

        static {
            Direction[] directionArr$values = $values();
            $VALUES = directionArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(directionArr$values);
        }

        public static Direction valueOf(String str) {
            return (Direction) Enum.valueOf(Direction.class, str);
        }

        public static Direction[] values() {
            return (Direction[]) $VALUES.clone();
        }
    }

    public final Value toProto$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        Value valueBuild = Value.newBuilder().setMapValue(MapValue.newBuilder().putFields("direction", this.dir.getProto()).putFields("expression", this.expr.toProto$com_google_firebase_firebase_firestore(userDataReader))).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }
}
