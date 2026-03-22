package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.Stage;
import com.google.firebase.firestore.pipeline.evaluation.EvaluationContext;
import com.google.firestore.v1.Pipeline;
import com.google.firestore.v1.Value;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002B\u0019\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011J\r\u0010\u0012\u001a\u00020\u0004H ¢\u0006\u0002\b\u0013J\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u000f\u001a\u00020\u0010H ¢\u0006\u0002\b\u0017J\u0017\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0006H ¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0016H\u0004¢\u0006\u0002\u0010\u001eJ\u001b\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004¢\u0006\u0002\u0010\u001fJ\u001b\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020 ¢\u0006\u0002\u0010!J\u001b\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\"¢\u0006\u0002\u0010#J\u001b\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020$¢\u0006\u0002\u0010%J\u001b\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020&¢\u0006\u0002\u0010'J)\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020*0)H\u0010¢\u0006\u0002\b.R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u0082\u0001\u0013/0123456789:;<=>?@A¨\u0006B"}, d2 = {"Lcom/google/firebase/firestore/pipeline/Stage;", ExifInterface.GPS_DIRECTION_TRUE, "", "name", "", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "(Ljava/lang/String;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "getName$com_google_firebase_firebase_firestore", "()Ljava/lang/String;", "getOptions$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/pipeline/InternalOptions;", "toProtoStage", "Lcom/google/firestore/v1/Pipeline$Stage;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "toProtoStage$com_google_firebase_firebase_firestore", "canonicalId", "canonicalId$com_google_firebase_firebase_firestore", "args", "Lkotlin/sequences/Sequence;", "Lcom/google/firestore/v1/Value;", "args$com_google_firebase_firebase_firestore", "self", "self$com_google_firebase_firebase_firestore", "(Lcom/google/firebase/firestore/pipeline/InternalOptions;)Lcom/google/firebase/firestore/pipeline/Stage;", "withOption", "key", Values.VECTOR_MAP_VECTORS_KEY, "(Ljava/lang/String;Lcom/google/firestore/v1/Value;)Lcom/google/firebase/firestore/pipeline/Stage;", "(Ljava/lang/String;Ljava/lang/String;)Lcom/google/firebase/firestore/pipeline/Stage;", "", "(Ljava/lang/String;Z)Lcom/google/firebase/firestore/pipeline/Stage;", "", "(Ljava/lang/String;J)Lcom/google/firebase/firestore/pipeline/Stage;", "", "(Ljava/lang/String;D)Lcom/google/firebase/firestore/pipeline/Stage;", "Lcom/google/firebase/firestore/pipeline/Field;", "(Ljava/lang/String;Lcom/google/firebase/firestore/pipeline/Field;)Lcom/google/firebase/firestore/pipeline/Stage;", "evaluate", "", "Lcom/google/firebase/firestore/model/MutableDocument;", "context", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "inputs", "evaluate$com_google_firebase_firebase_firestore", "Lcom/google/firebase/firestore/pipeline/AddFieldsStage;", "Lcom/google/firebase/firestore/pipeline/AggregateStage;", "Lcom/google/firebase/firestore/pipeline/CollectionGroupSource;", "Lcom/google/firebase/firestore/pipeline/CollectionSource;", "Lcom/google/firebase/firestore/pipeline/DatabaseSource;", "Lcom/google/firebase/firestore/pipeline/DistinctStage;", "Lcom/google/firebase/firestore/pipeline/DocumentsSource;", "Lcom/google/firebase/firestore/pipeline/FindNearestStage;", "Lcom/google/firebase/firestore/pipeline/LimitStage;", "Lcom/google/firebase/firestore/pipeline/OffsetStage;", "Lcom/google/firebase/firestore/pipeline/RawStage;", "Lcom/google/firebase/firestore/pipeline/RemoveFieldsStage;", "Lcom/google/firebase/firestore/pipeline/ReplaceStage;", "Lcom/google/firebase/firestore/pipeline/SampleStage;", "Lcom/google/firebase/firestore/pipeline/SelectStage;", "Lcom/google/firebase/firestore/pipeline/SortStage;", "Lcom/google/firebase/firestore/pipeline/UnionStage;", "Lcom/google/firebase/firestore/pipeline/UnnestStage;", "Lcom/google/firebase/firestore/pipeline/WhereStage;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class Stage<T extends Stage<T>> {
    private final String name;
    private final InternalOptions options;

    public /* synthetic */ Stage(String str, InternalOptions internalOptions, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, internalOptions);
    }

    public abstract Sequence<Value> args$com_google_firebase_firebase_firestore(UserDataReader userDataReader);

    public abstract String canonicalId$com_google_firebase_firebase_firestore();

    public abstract T self$com_google_firebase_firebase_firestore(InternalOptions options);

    private Stage(String str, InternalOptions internalOptions) {
        this.name = str;
        this.options = internalOptions;
    }

    /* JADX INFO: renamed from: getName$com_google_firebase_firebase_firestore, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: getOptions$com_google_firebase_firebase_firestore, reason: from getter */
    public final InternalOptions getOptions() {
        return this.options;
    }

    public final Pipeline.Stage toProtoStage$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        Pipeline.Stage.Builder builderNewBuilder = Pipeline.Stage.newBuilder();
        builderNewBuilder.setName(this.name);
        Iterator<Value> it = args$com_google_firebase_firebase_firestore(userDataReader).iterator();
        while (it.hasNext()) {
            builderNewBuilder.addArgs(it.next());
        }
        this.options.forEach$com_google_firebase_firebase_firestore(new Stage$toProtoStage$2(builderNewBuilder));
        Pipeline.Stage stageBuild = builderNewBuilder.build();
        Intrinsics.checkNotNullExpressionValue(stageBuild, "build(...)");
        return stageBuild;
    }

    protected final T withOption(String key, Value value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return (T) self$com_google_firebase_firebase_firestore(this.options.with$com_google_firebase_firebase_firestore(key, value));
    }

    public final T withOption(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return (T) withOption(key, Values.encodeValue(value));
    }

    public final T withOption(String key, boolean value) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (T) withOption(key, Values.encodeValue(value));
    }

    public final T withOption(String key, long value) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (T) withOption(key, Values.encodeValue(value));
    }

    public final T withOption(String key, double value) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (T) withOption(key, Values.encodeValue(value));
    }

    public final T withOption(String key, Field value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return (T) withOption(key, value.toProto$com_google_firebase_firebase_firestore());
    }

    public List<MutableDocument> evaluate$com_google_firebase_firebase_firestore(EvaluationContext context, List<MutableDocument> inputs) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(inputs, "inputs");
        throw new NotImplementedError("Stage " + this.name + " does not support offline evaluation");
    }
}
