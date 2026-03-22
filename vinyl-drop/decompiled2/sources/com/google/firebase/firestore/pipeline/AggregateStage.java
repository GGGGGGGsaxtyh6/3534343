package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.Selectable;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 (2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001(B;\b\u0002\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bB\u001d\b\u0012\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\n\u0010\fJ\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0010¢\u0006\u0002\b\u000eJ'\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00042\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u0012\"\u00020\u0013¢\u0006\u0002\u0010\u0014J'\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u0012\"\u00020\u0013¢\u0006\u0002\u0010\u0017J\u0015\u0010\u0018\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001aJ\r\u0010\u001b\u001a\u00020\u0004H\u0010¢\u0006\u0002\b\u001cJ\u001b\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010 \u001a\u00020!H\u0010¢\u0006\u0002\b\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010&\u001a\u00020'H\u0016R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/google/firebase/firestore/pipeline/AggregateStage;", "Lcom/google/firebase/firestore/pipeline/Stage;", "accumulators", "", "", "Lcom/google/firebase/firestore/pipeline/AggregateFunction;", "groups", "Lcom/google/firebase/firestore/pipeline/Expression;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "(Ljava/util/Map;Ljava/util/Map;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "(Ljava/util/Map;)V", "self", "self$com_google_firebase_firebase_firestore", "withGroups", "groupField", "additionalGroups", "", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/AggregateStage;", "group", "Lcom/google/firebase/firestore/pipeline/Selectable;", "(Lcom/google/firebase/firestore/pipeline/Selectable;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/AggregateStage;", "withOptions", "Lcom/google/firebase/firestore/pipeline/AggregateOptions;", "withOptions$com_google_firebase_firebase_firestore", "canonicalId", "canonicalId$com_google_firebase_firebase_firestore", "args", "Lkotlin/sequences/Sequence;", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "args$com_google_firebase_firebase_firestore", "equals", "", "other", "hashCode", "", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class AggregateStage extends Stage<AggregateStage> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<String, AggregateFunction> accumulators;
    private final Map<String, Expression> groups;

    public /* synthetic */ AggregateStage(Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(map);
    }

    @JvmStatic
    public static final AggregateStage withAccumulators(AliasedAggregate aliasedAggregate, AliasedAggregate... aliasedAggregateArr) {
        return INSTANCE.withAccumulators(aliasedAggregate, aliasedAggregateArr);
    }

    /* synthetic */ AggregateStage(Map map, Map map2, InternalOptions internalOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, map2, (i & 4) != 0 ? InternalOptions.EMPTY : internalOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AggregateStage(Map<String, AggregateFunction> map, Map<String, ? extends Expression> map2, InternalOptions internalOptions) {
        super("aggregate", internalOptions, null);
        this.accumulators = map;
        this.groups = map2;
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\t\"\u00020\u0007H\u0007¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/google/firebase/firestore/pipeline/AggregateStage$Companion;", "", "<init>", "()V", "withAccumulators", "Lcom/google/firebase/firestore/pipeline/AggregateStage;", "accumulator", "Lcom/google/firebase/firestore/pipeline/AliasedAggregate;", "additionalAccumulators", "", "(Lcom/google/firebase/firestore/pipeline/AliasedAggregate;[Lcom/google/firebase/firestore/pipeline/AliasedAggregate;)Lcom/google/firebase/firestore/pipeline/AggregateStage;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AggregateStage withAccumulators(AliasedAggregate accumulator, AliasedAggregate... additionalAccumulators) {
            Intrinsics.checkNotNullParameter(accumulator, "accumulator");
            Intrinsics.checkNotNullParameter(additionalAccumulators, "additionalAccumulators");
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to(accumulator.getAlias(), accumulator.getExpr()));
            for (AliasedAggregate aliasedAggregate : additionalAccumulators) {
                if (mapMapOf.containsKey(aliasedAggregate.getAlias())) {
                    throw new IllegalArgumentException("Duplicate alias: '" + aliasedAggregate.getAlias() + '\'');
                }
                mapMapOf = MapsKt.plus(mapMapOf, TuplesKt.to(aliasedAggregate.getAlias(), aliasedAggregate.getExpr()));
            }
            return new AggregateStage(mapMapOf, null);
        }
    }

    private AggregateStage(Map<String, AggregateFunction> map) {
        this(map, MapsKt.emptyMap(), null, 4, null);
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public AggregateStage self$com_google_firebase_firebase_firestore(InternalOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new AggregateStage(this.accumulators, this.groups, options);
    }

    public final AggregateStage withGroups(String groupField, Object... additionalGroups) {
        Intrinsics.checkNotNullParameter(groupField, "groupField");
        Intrinsics.checkNotNullParameter(additionalGroups, "additionalGroups");
        return withGroups(Expression.INSTANCE.field(groupField), Arrays.copyOf(additionalGroups, additionalGroups.length));
    }

    public final AggregateStage withGroups(Selectable group, Object... additionalGroups) {
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(additionalGroups, "additionalGroups");
        Selectable.Companion companion = Selectable.INSTANCE;
        ArrayList<Selectable> arrayList = new ArrayList(additionalGroups.length);
        for (Object obj : additionalGroups) {
            arrayList.add(companion.toSelectable(obj));
        }
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to(group.getAlias(), group.getExpr()));
        for (Selectable selectable : arrayList) {
            if (mapMapOf.containsKey(selectable.getAlias())) {
                throw new IllegalArgumentException("Duplicate alias: '" + selectable.getAlias() + '\'');
            }
            mapMapOf = MapsKt.plus(mapMapOf, TuplesKt.to(selectable.getAlias(), selectable.getExpr()));
        }
        return new AggregateStage(this.accumulators, mapMapOf, getOptions());
    }

    public final AggregateStage withOptions$com_google_firebase_firebase_firestore(AggregateOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new AggregateStage(this.accumulators, this.groups, options.getOptions());
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public String canonicalId$com_google_firebase_firebase_firestore() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public Sequence<Value> args$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        Value[] valueArr = new Value[2];
        Map<String, AggregateFunction> map = this.accumulators;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), ((AggregateFunction) entry.getValue()).toProto$com_google_firebase_firebase_firestore(userDataReader));
        }
        valueArr[0] = Values.encodeValue(linkedHashMap);
        Map<String, Expression> map2 = this.groups;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(map2.size()));
        Iterator<T> it2 = map2.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it2.next();
            linkedHashMap2.put(entry2.getKey(), ((Expression) entry2.getValue()).toProto$com_google_firebase_firebase_firestore(userDataReader));
        }
        valueArr[1] = Values.encodeValue(linkedHashMap2);
        return SequencesKt.sequenceOf((Object[]) valueArr);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AggregateStage)) {
            return false;
        }
        AggregateStage aggregateStage = (AggregateStage) other;
        return Intrinsics.areEqual(this.accumulators, aggregateStage.accumulators) && Intrinsics.areEqual(this.groups, aggregateStage.groups) && Intrinsics.areEqual(getOptions(), aggregateStage.getOptions());
    }

    public int hashCode() {
        return (((this.accumulators.hashCode() * 31) + this.groups.hashCode()) * 31) + getOptions().hashCode();
    }
}
