package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: options.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001d\b\u0000\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u000bJ#\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rH\u0000¢\u0006\u0002\b\u000bJ\u001d\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u000bJ!\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00042\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0000¢\u0006\u0002\b\u000bJ\u0015\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u0011J'\u0010\u0012\u001a\u00020\u00132\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u0015H\u0000¢\u0006\u0002\b\u0016J\b\u0010\u0017\u001a\u00020\u0005H\u0002R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/google/firebase/firestore/pipeline/InternalOptions;", "", "options", "Lcom/google/common/collect/ImmutableMap;", "", "Lcom/google/firestore/v1/Value;", "<init>", "(Lcom/google/common/collect/ImmutableMap;)V", "with", "key", Values.VECTOR_MAP_VECTORS_KEY, "with$com_google_firebase_firebase_firestore", "values", "", "Lcom/google/firebase/firestore/pipeline/AbstractOptions;", "adding", "newOptions", "adding$com_google_firebase_firebase_firestore", "forEach", "", "f", "Lkotlin/Function2;", "forEach$com_google_firebase_firebase_firestore", "toValue", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class InternalOptions {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final InternalOptions EMPTY;
    private final ImmutableMap<String, Value> options;

    public InternalOptions(ImmutableMap<String, Value> options) {
        Intrinsics.checkNotNullParameter(options, "options");
        this.options = options;
    }

    public final InternalOptions with$com_google_firebase_firebase_firestore(String key, Value value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        ImmutableMap.Builder builderBuilderWithExpectedSize = ImmutableMap.builderWithExpectedSize(this.options.size() + 1);
        builderBuilderWithExpectedSize.putAll(this.options);
        builderBuilderWithExpectedSize.put(key, value);
        ImmutableMap immutableMapBuildKeepingLast = builderBuilderWithExpectedSize.buildKeepingLast();
        Intrinsics.checkNotNullExpressionValue(immutableMapBuildKeepingLast, "buildKeepingLast(...)");
        return new InternalOptions(immutableMapBuildKeepingLast);
    }

    public final InternalOptions with$com_google_firebase_firebase_firestore(String key, Iterable<Value> values) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(values, "values");
        Value valueBuild = Value.newBuilder().setArrayValue(ArrayValue.newBuilder().addAllValues(values).build()).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return with$com_google_firebase_firebase_firestore(key, valueBuild);
    }

    public final InternalOptions with$com_google_firebase_firebase_firestore(String key, InternalOptions value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return with$com_google_firebase_firebase_firestore(key, value.toValue());
    }

    public final InternalOptions with$com_google_firebase_firebase_firestore(String key, AbstractOptions<?> value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return with$com_google_firebase_firebase_firestore(key, value.getOptions());
    }

    public final InternalOptions adding$com_google_firebase_firebase_firestore(InternalOptions newOptions) {
        Intrinsics.checkNotNullParameter(newOptions, "newOptions");
        ImmutableMap.Builder builderBuilderWithExpectedSize = ImmutableMap.builderWithExpectedSize(this.options.size() + newOptions.options.size());
        builderBuilderWithExpectedSize.putAll(this.options);
        builderBuilderWithExpectedSize.putAll(newOptions.options);
        ImmutableMap immutableMapBuild = builderBuilderWithExpectedSize.build();
        Intrinsics.checkNotNullExpressionValue(immutableMapBuild, "build(...)");
        return new InternalOptions(immutableMapBuild);
    }

    public final void forEach$com_google_firebase_firebase_firestore(Function2<? super String, ? super Value, Unit> f) {
        Intrinsics.checkNotNullParameter(f, "f");
        UnmodifiableIterator it = ((ImmutableSet) this.options.entrySet()).iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Intrinsics.checkNotNullExpressionValue(key, "<get-key>(...)");
            Object value = entry.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-value>(...)");
            f.invoke(key, value);
        }
    }

    private final Value toValue() {
        Value valueBuild = Value.newBuilder().setMapValue(MapValue.newBuilder().putAllFields(this.options).build()).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    /* JADX INFO: compiled from: options.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/google/firebase/firestore/pipeline/InternalOptions$Companion;", "", "<init>", "()V", "EMPTY", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "of", "key", "", Values.VECTOR_MAP_VECTORS_KEY, "Lcom/google/firestore/v1/Value;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final InternalOptions of(String key, Value value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            ImmutableMap immutableMapOf = ImmutableMap.of(key, value);
            Intrinsics.checkNotNullExpressionValue(immutableMapOf, "of(...)");
            return new InternalOptions(immutableMapOf);
        }
    }

    static {
        ImmutableMap immutableMapOf = ImmutableMap.of();
        Intrinsics.checkNotNullExpressionValue(immutableMapOf, "of(...)");
        EMPTY = new InternalOptions(immutableMapOf);
    }
}
