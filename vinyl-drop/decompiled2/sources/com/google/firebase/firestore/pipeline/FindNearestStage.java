package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.VectorValue;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.Value;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u001e\u001fB+\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB)\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u000b¢\u0006\u0004\b\t\u0010\fJ\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0010¢\u0006\u0002\b\u000eJ\r\u0010\u000f\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u0011J\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0010¢\u0006\u0002\b\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/google/firebase/firestore/pipeline/FindNearestStage;", "Lcom/google/firebase/firestore/pipeline/Stage;", "property", "Lcom/google/firebase/firestore/pipeline/Expression;", "vector", "distanceMeasure", "Lcom/google/firebase/firestore/pipeline/FindNearestStage$DistanceMeasure;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "(Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/FindNearestStage$DistanceMeasure;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "Lcom/google/firebase/firestore/pipeline/FindNearestOptions;", "(Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/FindNearestStage$DistanceMeasure;Lcom/google/firebase/firestore/pipeline/FindNearestOptions;)V", "self", "self$com_google_firebase_firebase_firestore", "canonicalId", "", "canonicalId$com_google_firebase_firebase_firestore", "args", "Lkotlin/sequences/Sequence;", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "args$com_google_firebase_firebase_firestore", "equals", "", "other", "", "hashCode", "", "Companion", "DistanceMeasure", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FindNearestStage extends Stage<FindNearestStage> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final DistanceMeasure distanceMeasure;
    private final Expression property;
    private final Expression vector;

    public /* synthetic */ FindNearestStage(Expression expression, Expression expression2, DistanceMeasure distanceMeasure, FindNearestOptions findNearestOptions, DefaultConstructorMarker defaultConstructorMarker) {
        this(expression, expression2, distanceMeasure, findNearestOptions);
    }

    public /* synthetic */ FindNearestStage(Expression expression, Expression expression2, DistanceMeasure distanceMeasure, InternalOptions internalOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(expression, expression2, distanceMeasure, (i & 8) != 0 ? InternalOptions.EMPTY : internalOptions);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FindNearestStage(Expression property, Expression vector, DistanceMeasure distanceMeasure, InternalOptions options) {
        super("find_nearest", options, null);
        Intrinsics.checkNotNullParameter(property, "property");
        Intrinsics.checkNotNullParameter(vector, "vector");
        Intrinsics.checkNotNullParameter(distanceMeasure, "distanceMeasure");
        Intrinsics.checkNotNullParameter(options, "options");
        this.property = property;
        this.vector = vector;
        this.distanceMeasure = distanceMeasure;
    }

    private FindNearestStage(Expression expression, Expression expression2, DistanceMeasure distanceMeasure, FindNearestOptions findNearestOptions) {
        this(expression, expression2, distanceMeasure, findNearestOptions.getOptions());
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0013\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0001¢\u0006\u0002\b\u000eJ/\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0001¢\u0006\u0002\b\u000eJ/\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0001¢\u0006\u0002\b\u000eJ/\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0001¢\u0006\u0002\b\u000eJ/\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000e¨\u0006\u0012"}, d2 = {"Lcom/google/firebase/firestore/pipeline/FindNearestStage$Companion;", "", "<init>", "()V", "of", "Lcom/google/firebase/firestore/pipeline/FindNearestStage;", "vectorField", "Lcom/google/firebase/firestore/pipeline/Field;", "vectorValue", "Lcom/google/firebase/firestore/VectorValue;", "distanceMeasure", "Lcom/google/firebase/firestore/pipeline/FindNearestStage$DistanceMeasure;", "options", "Lcom/google/firebase/firestore/pipeline/FindNearestOptions;", "of$com_google_firebase_firebase_firestore", "", "", "Lcom/google/firebase/firestore/pipeline/Expression;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ FindNearestStage of$com_google_firebase_firebase_firestore$default(Companion companion, Field field, VectorValue vectorValue, DistanceMeasure distanceMeasure, FindNearestOptions findNearestOptions, int i, Object obj) {
            if ((i & 8) != 0) {
                findNearestOptions = new FindNearestOptions();
            }
            return companion.of$com_google_firebase_firebase_firestore(field, vectorValue, distanceMeasure, findNearestOptions);
        }

        @JvmStatic
        public final FindNearestStage of$com_google_firebase_firebase_firestore(Field vectorField, VectorValue vectorValue, DistanceMeasure distanceMeasure, FindNearestOptions options) {
            Intrinsics.checkNotNullParameter(vectorField, "vectorField");
            Intrinsics.checkNotNullParameter(vectorValue, "vectorValue");
            Intrinsics.checkNotNullParameter(distanceMeasure, "distanceMeasure");
            Intrinsics.checkNotNullParameter(options, "options");
            return new FindNearestStage(vectorField, Expression.INSTANCE.constant(vectorValue), distanceMeasure, options, null);
        }

        public static /* synthetic */ FindNearestStage of$com_google_firebase_firebase_firestore$default(Companion companion, Field field, double[] dArr, DistanceMeasure distanceMeasure, FindNearestOptions findNearestOptions, int i, Object obj) {
            if ((i & 8) != 0) {
                findNearestOptions = new FindNearestOptions();
            }
            return companion.of$com_google_firebase_firebase_firestore(field, dArr, distanceMeasure, findNearestOptions);
        }

        @JvmStatic
        public final FindNearestStage of$com_google_firebase_firebase_firestore(Field vectorField, double[] vectorValue, DistanceMeasure distanceMeasure, FindNearestOptions options) {
            Intrinsics.checkNotNullParameter(vectorField, "vectorField");
            Intrinsics.checkNotNullParameter(vectorValue, "vectorValue");
            Intrinsics.checkNotNullParameter(distanceMeasure, "distanceMeasure");
            Intrinsics.checkNotNullParameter(options, "options");
            return new FindNearestStage(vectorField, Expression.INSTANCE.vector(vectorValue), distanceMeasure, options, null);
        }

        public static /* synthetic */ FindNearestStage of$com_google_firebase_firebase_firestore$default(Companion companion, String str, VectorValue vectorValue, DistanceMeasure distanceMeasure, FindNearestOptions findNearestOptions, int i, Object obj) {
            if ((i & 8) != 0) {
                findNearestOptions = new FindNearestOptions();
            }
            return companion.of$com_google_firebase_firebase_firestore(str, vectorValue, distanceMeasure, findNearestOptions);
        }

        @JvmStatic
        public final FindNearestStage of$com_google_firebase_firebase_firestore(String vectorField, VectorValue vectorValue, DistanceMeasure distanceMeasure, FindNearestOptions options) {
            Intrinsics.checkNotNullParameter(vectorField, "vectorField");
            Intrinsics.checkNotNullParameter(vectorValue, "vectorValue");
            Intrinsics.checkNotNullParameter(distanceMeasure, "distanceMeasure");
            Intrinsics.checkNotNullParameter(options, "options");
            return new FindNearestStage(Expression.INSTANCE.field(vectorField), Expression.INSTANCE.constant(vectorValue), distanceMeasure, options, null);
        }

        public static /* synthetic */ FindNearestStage of$com_google_firebase_firebase_firestore$default(Companion companion, String str, double[] dArr, DistanceMeasure distanceMeasure, FindNearestOptions findNearestOptions, int i, Object obj) {
            if ((i & 8) != 0) {
                findNearestOptions = new FindNearestOptions();
            }
            return companion.of$com_google_firebase_firebase_firestore(str, dArr, distanceMeasure, findNearestOptions);
        }

        @JvmStatic
        public final FindNearestStage of$com_google_firebase_firebase_firestore(String vectorField, double[] vectorValue, DistanceMeasure distanceMeasure, FindNearestOptions options) {
            Intrinsics.checkNotNullParameter(vectorField, "vectorField");
            Intrinsics.checkNotNullParameter(vectorValue, "vectorValue");
            Intrinsics.checkNotNullParameter(distanceMeasure, "distanceMeasure");
            Intrinsics.checkNotNullParameter(options, "options");
            return new FindNearestStage(Expression.INSTANCE.field(vectorField), Expression.INSTANCE.vector(vectorValue), distanceMeasure, options, null);
        }

        public static /* synthetic */ FindNearestStage of$com_google_firebase_firebase_firestore$default(Companion companion, String str, Expression expression, DistanceMeasure distanceMeasure, FindNearestOptions findNearestOptions, int i, Object obj) {
            if ((i & 8) != 0) {
                findNearestOptions = new FindNearestOptions();
            }
            return companion.of$com_google_firebase_firebase_firestore(str, expression, distanceMeasure, findNearestOptions);
        }

        public final FindNearestStage of$com_google_firebase_firebase_firestore(String vectorField, Expression vectorValue, DistanceMeasure distanceMeasure, FindNearestOptions options) {
            Intrinsics.checkNotNullParameter(vectorField, "vectorField");
            Intrinsics.checkNotNullParameter(vectorValue, "vectorValue");
            Intrinsics.checkNotNullParameter(distanceMeasure, "distanceMeasure");
            Intrinsics.checkNotNullParameter(options, "options");
            return new FindNearestStage(Expression.INSTANCE.field(vectorField), vectorValue, distanceMeasure, options, null);
        }
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0012\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/google/firebase/firestore/pipeline/FindNearestStage$DistanceMeasure;", "", "proto", "Lcom/google/firestore/v1/Value;", "<init>", "(Lcom/google/firestore/v1/Value;)V", "protoString", "", "(Ljava/lang/String;)V", "getProto$com_google_firebase_firebase_firestore", "()Lcom/google/firestore/v1/Value;", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DistanceMeasure {
        private final Value proto;
        public static final DistanceMeasure EUCLIDEAN = new DistanceMeasure("euclidean");
        public static final DistanceMeasure COSINE = new DistanceMeasure("cosine");
        public static final DistanceMeasure DOT_PRODUCT = new DistanceMeasure("dot_product");

        private DistanceMeasure(Value value) {
            this.proto = value;
        }

        /* JADX INFO: renamed from: getProto$com_google_firebase_firebase_firestore, reason: from getter */
        public final Value getProto() {
            return this.proto;
        }

        private DistanceMeasure(String str) {
            this(Values.encodeValue(str));
        }
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public FindNearestStage self$com_google_firebase_firebase_firestore(InternalOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new FindNearestStage(this.property, this.vector, this.distanceMeasure, options);
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public String canonicalId$com_google_firebase_firebase_firestore() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public Sequence<Value> args$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        return SequencesKt.sequenceOf((Object[]) new Value[]{this.property.toProto$com_google_firebase_firebase_firestore(userDataReader), this.vector.toProto$com_google_firebase_firebase_firestore(userDataReader), this.distanceMeasure.getProto()});
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FindNearestStage)) {
            return false;
        }
        FindNearestStage findNearestStage = (FindNearestStage) other;
        return Intrinsics.areEqual(this.property, findNearestStage.property) && Intrinsics.areEqual(this.vector, findNearestStage.vector) && Intrinsics.areEqual(this.distanceMeasure, findNearestStage.distanceMeasure) && Intrinsics.areEqual(getOptions(), findNearestStage.getOptions());
    }

    public int hashCode() {
        return (((((this.property.hashCode() * 31) + this.vector.hashCode()) * 31) + this.distanceMeasure.hashCode()) * 31) + getOptions().hashCode();
    }
}
