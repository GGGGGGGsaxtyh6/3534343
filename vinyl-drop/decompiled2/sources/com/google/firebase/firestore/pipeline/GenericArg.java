package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \b2\u00020\u0001:\u0006\b\t\n\u000b\f\rB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u0082\u0001\u0005\u000e\u000f\u0010\u0011\u0012¨\u0006\u0013"}, d2 = {"Lcom/google/firebase/firestore/pipeline/GenericArg;", "", "<init>", "()V", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "Companion", "AggregateArg", "ExprArg", "OrderingArg", "MapArg", "ListArg", "Lcom/google/firebase/firestore/pipeline/GenericArg$AggregateArg;", "Lcom/google/firebase/firestore/pipeline/GenericArg$ExprArg;", "Lcom/google/firebase/firestore/pipeline/GenericArg$ListArg;", "Lcom/google/firebase/firestore/pipeline/GenericArg$MapArg;", "Lcom/google/firebase/firestore/pipeline/GenericArg$OrderingArg;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class GenericArg {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ GenericArg(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Value toProto(UserDataReader userDataReader);

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001¨\u0006\u0007"}, d2 = {"Lcom/google/firebase/firestore/pipeline/GenericArg$Companion;", "", "<init>", "()V", TypedValues.TransitionType.S_FROM, "Lcom/google/firebase/firestore/pipeline/GenericArg;", "arg", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GenericArg from(Object arg) {
            if (arg instanceof AggregateFunction) {
                return new AggregateArg((AggregateFunction) arg);
            }
            if (arg instanceof Ordering) {
                return new OrderingArg((Ordering) arg);
            }
            if (arg instanceof Map) {
                Set<Map.Entry> setEntrySet = ((Map) arg).entrySet();
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(setEntrySet, 10)), 16));
                for (Map.Entry entry : setEntrySet) {
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    Intrinsics.checkNotNull(key, "null cannot be cast to non-null type kotlin.String");
                    Pair pair = TuplesKt.to((String) key, GenericArg.INSTANCE.from(value));
                    linkedHashMap.put(pair.getFirst(), pair.getSecond());
                }
                return new MapArg(linkedHashMap);
            }
            if (!(arg instanceof List)) {
                return new ExprArg(Expression.INSTANCE.toExprOrConstant$com_google_firebase_firebase_firestore(arg));
            }
            Iterable iterable = (Iterable) arg;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(from(it.next()));
            }
            return new ListArg(arrayList);
        }
    }

    private GenericArg() {
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/google/firebase/firestore/pipeline/GenericArg$AggregateArg;", "Lcom/google/firebase/firestore/pipeline/GenericArg;", "aggregate", "Lcom/google/firebase/firestore/pipeline/AggregateFunction;", "<init>", "(Lcom/google/firebase/firestore/pipeline/AggregateFunction;)V", "getAggregate", "()Lcom/google/firebase/firestore/pipeline/AggregateFunction;", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class AggregateArg extends GenericArg {
        private final AggregateFunction aggregate;

        public static /* synthetic */ AggregateArg copy$default(AggregateArg aggregateArg, AggregateFunction aggregateFunction, int i, Object obj) {
            if ((i & 1) != 0) {
                aggregateFunction = aggregateArg.aggregate;
            }
            return aggregateArg.copy(aggregateFunction);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AggregateFunction getAggregate() {
            return this.aggregate;
        }

        public final AggregateArg copy(AggregateFunction aggregate) {
            Intrinsics.checkNotNullParameter(aggregate, "aggregate");
            return new AggregateArg(aggregate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AggregateArg) && Intrinsics.areEqual(this.aggregate, ((AggregateArg) other).aggregate);
        }

        public int hashCode() {
            return this.aggregate.hashCode();
        }

        public String toString() {
            return "AggregateArg(aggregate=" + this.aggregate + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AggregateArg(AggregateFunction aggregate) {
            super(null);
            Intrinsics.checkNotNullParameter(aggregate, "aggregate");
            this.aggregate = aggregate;
        }

        public final AggregateFunction getAggregate() {
            return this.aggregate;
        }

        @Override // com.google.firebase.firestore.pipeline.GenericArg
        public Value toProto(UserDataReader userDataReader) {
            Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
            return this.aggregate.toProto$com_google_firebase_firebase_firestore(userDataReader);
        }
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/google/firebase/firestore/pipeline/GenericArg$ExprArg;", "Lcom/google/firebase/firestore/pipeline/GenericArg;", "expr", "Lcom/google/firebase/firestore/pipeline/Expression;", "<init>", "(Lcom/google/firebase/firestore/pipeline/Expression;)V", "getExpr", "()Lcom/google/firebase/firestore/pipeline/Expression;", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class ExprArg extends GenericArg {
        private final Expression expr;

        public static /* synthetic */ ExprArg copy$default(ExprArg exprArg, Expression expression, int i, Object obj) {
            if ((i & 1) != 0) {
                expression = exprArg.expr;
            }
            return exprArg.copy(expression);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Expression getExpr() {
            return this.expr;
        }

        public final ExprArg copy(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new ExprArg(expr);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ExprArg) && Intrinsics.areEqual(this.expr, ((ExprArg) other).expr);
        }

        public int hashCode() {
            return this.expr.hashCode();
        }

        public String toString() {
            return "ExprArg(expr=" + this.expr + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ExprArg(Expression expr) {
            super(null);
            Intrinsics.checkNotNullParameter(expr, "expr");
            this.expr = expr;
        }

        public final Expression getExpr() {
            return this.expr;
        }

        @Override // com.google.firebase.firestore.pipeline.GenericArg
        public Value toProto(UserDataReader userDataReader) {
            Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
            return this.expr.toProto$com_google_firebase_firebase_firestore(userDataReader);
        }
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/google/firebase/firestore/pipeline/GenericArg$OrderingArg;", "Lcom/google/firebase/firestore/pipeline/GenericArg;", "ordering", "Lcom/google/firebase/firestore/pipeline/Ordering;", "<init>", "(Lcom/google/firebase/firestore/pipeline/Ordering;)V", "getOrdering", "()Lcom/google/firebase/firestore/pipeline/Ordering;", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class OrderingArg extends GenericArg {
        private final Ordering ordering;

        public static /* synthetic */ OrderingArg copy$default(OrderingArg orderingArg, Ordering ordering, int i, Object obj) {
            if ((i & 1) != 0) {
                ordering = orderingArg.ordering;
            }
            return orderingArg.copy(ordering);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Ordering getOrdering() {
            return this.ordering;
        }

        public final OrderingArg copy(Ordering ordering) {
            Intrinsics.checkNotNullParameter(ordering, "ordering");
            return new OrderingArg(ordering);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof OrderingArg) && Intrinsics.areEqual(this.ordering, ((OrderingArg) other).ordering);
        }

        public int hashCode() {
            return this.ordering.hashCode();
        }

        public String toString() {
            return "OrderingArg(ordering=" + this.ordering + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OrderingArg(Ordering ordering) {
            super(null);
            Intrinsics.checkNotNullParameter(ordering, "ordering");
            this.ordering = ordering;
        }

        public final Ordering getOrdering() {
            return this.ordering;
        }

        @Override // com.google.firebase.firestore.pipeline.GenericArg
        public Value toProto(UserDataReader userDataReader) {
            Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
            return this.ordering.toProto$com_google_firebase_firebase_firestore(userDataReader);
        }
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0015\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/google/firebase/firestore/pipeline/GenericArg$MapArg;", "Lcom/google/firebase/firestore/pipeline/GenericArg;", "args", "", "", "<init>", "(Ljava/util/Map;)V", "getArgs", "()Ljava/util/Map;", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class MapArg extends GenericArg {
        private final Map<String, GenericArg> args;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MapArg copy$default(MapArg mapArg, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                map = mapArg.args;
            }
            return mapArg.copy(map);
        }

        public final Map<String, GenericArg> component1() {
            return this.args;
        }

        public final MapArg copy(Map<String, ? extends GenericArg> args) {
            Intrinsics.checkNotNullParameter(args, "args");
            return new MapArg(args);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MapArg) && Intrinsics.areEqual(this.args, ((MapArg) other).args);
        }

        public int hashCode() {
            return this.args.hashCode();
        }

        public String toString() {
            return "MapArg(args=" + this.args + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public MapArg(Map<String, ? extends GenericArg> args) {
            super(null);
            Intrinsics.checkNotNullParameter(args, "args");
            this.args = args;
        }

        public final Map<String, GenericArg> getArgs() {
            return this.args;
        }

        @Override // com.google.firebase.firestore.pipeline.GenericArg
        public Value toProto(UserDataReader userDataReader) {
            Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
            Map<String, GenericArg> map = this.args;
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
            Iterator<T> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                linkedHashMap.put(entry.getKey(), ((GenericArg) entry.getValue()).toProto(userDataReader));
            }
            return Values.encodeValue(linkedHashMap);
        }
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u0019\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/google/firebase/firestore/pipeline/GenericArg$ListArg;", "Lcom/google/firebase/firestore/pipeline/GenericArg;", "args", "", "<init>", "(Ljava/util/List;)V", "getArgs", "()Ljava/util/List;", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class ListArg extends GenericArg {
        private final List<GenericArg> args;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ListArg copy$default(ListArg listArg, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = listArg.args;
            }
            return listArg.copy(list);
        }

        public final List<GenericArg> component1() {
            return this.args;
        }

        public final ListArg copy(List<? extends GenericArg> args) {
            Intrinsics.checkNotNullParameter(args, "args");
            return new ListArg(args);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ListArg) && Intrinsics.areEqual(this.args, ((ListArg) other).args);
        }

        public int hashCode() {
            return this.args.hashCode();
        }

        public String toString() {
            return "ListArg(args=" + this.args + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public ListArg(List<? extends GenericArg> args) {
            super(null);
            Intrinsics.checkNotNullParameter(args, "args");
            this.args = args;
        }

        public final List<GenericArg> getArgs() {
            return this.args;
        }

        @Override // com.google.firebase.firestore.pipeline.GenericArg
        public Value toProto(UserDataReader userDataReader) {
            Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
            List<GenericArg> list = this.args;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((GenericArg) it.next()).toProto(userDataReader));
            }
            return Values.encodeValue(arrayList);
        }
    }
}
