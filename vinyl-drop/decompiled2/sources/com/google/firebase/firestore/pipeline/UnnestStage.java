package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
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
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001bB\u001b\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\b\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005H\u0010¢\u0006\u0002\b\tJ\r\u0010\n\u001a\u00020\u000bH\u0010¢\u0006\u0002\b\fJ\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/google/firebase/firestore/pipeline/UnnestStage;", "Lcom/google/firebase/firestore/pipeline/Stage;", "selectable", "Lcom/google/firebase/firestore/pipeline/Selectable;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "(Lcom/google/firebase/firestore/pipeline/Selectable;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "self", "self$com_google_firebase_firebase_firestore", "canonicalId", "", "canonicalId$com_google_firebase_firebase_firestore", "args", "Lkotlin/sequences/Sequence;", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "args$com_google_firebase_firebase_firestore", "equals", "", "other", "", "hashCode", "", "withIndexField", "indexField", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class UnnestStage extends Stage<UnnestStage> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Selectable selectable;

    @JvmStatic
    public static final UnnestStage withField(Selectable selectable) {
        return INSTANCE.withField(selectable);
    }

    @JvmStatic
    public static final UnnestStage withField(String str, String str2) {
        return INSTANCE.withField(str, str2);
    }

    public /* synthetic */ UnnestStage(Selectable selectable, InternalOptions internalOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(selectable, (i & 2) != 0 ? InternalOptions.EMPTY : internalOptions);
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007¨\u0006\u000b"}, d2 = {"Lcom/google/firebase/firestore/pipeline/UnnestStage$Companion;", "", "<init>", "()V", "withField", "Lcom/google/firebase/firestore/pipeline/UnnestStage;", "arrayWithAlias", "Lcom/google/firebase/firestore/pipeline/Selectable;", "arrayField", "", "alias", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final UnnestStage withField(Selectable arrayWithAlias) {
            Intrinsics.checkNotNullParameter(arrayWithAlias, "arrayWithAlias");
            return new UnnestStage(arrayWithAlias, null, 2, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final UnnestStage withField(String arrayField, String alias) {
            Intrinsics.checkNotNullParameter(arrayField, "arrayField");
            Intrinsics.checkNotNullParameter(alias, "alias");
            return new UnnestStage(Expression.INSTANCE.field(arrayField).alias(alias), null, 2, 0 == true ? 1 : 0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnnestStage(Selectable selectable, InternalOptions options) {
        super("unnest", options, null);
        Intrinsics.checkNotNullParameter(selectable, "selectable");
        Intrinsics.checkNotNullParameter(options, "options");
        this.selectable = selectable;
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public UnnestStage self$com_google_firebase_firebase_firestore(InternalOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new UnnestStage(this.selectable, options);
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public String canonicalId$com_google_firebase_firebase_firestore() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public Sequence<Value> args$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        return SequencesKt.sequenceOf((Object[]) new Value[]{this.selectable.toProto$com_google_firebase_firebase_firestore(userDataReader), Expression.INSTANCE.field(this.selectable.getAlias()).toProto$com_google_firebase_firebase_firestore()});
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UnnestStage)) {
            return false;
        }
        UnnestStage unnestStage = (UnnestStage) other;
        return Intrinsics.areEqual(this.selectable, unnestStage.selectable) && Intrinsics.areEqual(getOptions(), unnestStage.getOptions());
    }

    public int hashCode() {
        return (this.selectable.hashCode() * 31) + getOptions().hashCode();
    }

    public final UnnestStage withIndexField(String indexField) {
        Intrinsics.checkNotNullParameter(indexField, "indexField");
        return withOption("index_field", indexField);
    }
}
