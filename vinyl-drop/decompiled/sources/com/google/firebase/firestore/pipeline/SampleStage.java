package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
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
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u001b\u001cB#\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007H\u0010¢\u0006\u0002\b\u000bJ\r\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0002\b\u000eJ\u001b\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0010¢\u0006\u0002\b\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/google/firebase/firestore/pipeline/SampleStage;", "Lcom/google/firebase/firestore/pipeline/Stage;", "size", "", "mode", "Lcom/google/firebase/firestore/pipeline/SampleStage$Mode;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "(Ljava/lang/Number;Lcom/google/firebase/firestore/pipeline/SampleStage$Mode;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "self", "self$com_google_firebase_firebase_firestore", "canonicalId", "", "canonicalId$com_google_firebase_firebase_firestore", "args", "Lkotlin/sequences/Sequence;", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "args$com_google_firebase_firebase_firestore", "equals", "", "other", "", "hashCode", "", "Mode", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SampleStage extends Stage<SampleStage> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Mode mode;
    private final Number size;

    @JvmStatic
    public static final SampleStage withDocLimit(int i) {
        return INSTANCE.withDocLimit(i);
    }

    @JvmStatic
    public static final SampleStage withPercentage(double d) {
        return INSTANCE.withPercentage(d);
    }

    /* synthetic */ SampleStage(Number number, Mode mode, InternalOptions internalOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(number, mode, (i & 4) != 0 ? InternalOptions.EMPTY : internalOptions);
    }

    private SampleStage(Number number, Mode mode, InternalOptions internalOptions) {
        super("sample", internalOptions, null);
        this.size = number;
        this.mode = mode;
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public SampleStage self$com_google_firebase_firebase_firestore(InternalOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new SampleStage(this.size, this.mode, options);
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public String canonicalId$com_google_firebase_firebase_firestore() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0012\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/google/firebase/firestore/pipeline/SampleStage$Mode;", "", "proto", "Lcom/google/firestore/v1/Value;", "<init>", "(Lcom/google/firestore/v1/Value;)V", "protoString", "", "(Ljava/lang/String;)V", "getProto$com_google_firebase_firebase_firestore", "()Lcom/google/firestore/v1/Value;", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Mode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Mode DOCUMENTS = new Mode("documents");
        private static final Mode PERCENT = new Mode("percent");
        private final Value proto;

        private Mode(Value value) {
            this.proto = value;
        }

        /* JADX INFO: renamed from: getProto$com_google_firebase_firebase_firestore, reason: from getter */
        public final Value getProto() {
            return this.proto;
        }

        /* JADX INFO: compiled from: stage.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/google/firebase/firestore/pipeline/SampleStage$Mode$Companion;", "", "<init>", "()V", "DOCUMENTS", "Lcom/google/firebase/firestore/pipeline/SampleStage$Mode;", "getDOCUMENTS", "()Lcom/google/firebase/firestore/pipeline/SampleStage$Mode;", "PERCENT", "getPERCENT", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Mode getDOCUMENTS() {
                return Mode.DOCUMENTS;
            }

            public final Mode getPERCENT() {
                return Mode.PERCENT;
            }
        }

        private Mode(String str) {
            this(Values.encodeValue(str));
        }
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/google/firebase/firestore/pipeline/SampleStage$Companion;", "", "<init>", "()V", "withPercentage", "Lcom/google/firebase/firestore/pipeline/SampleStage;", "percentage", "", "withDocLimit", "results", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SampleStage withPercentage(double percentage) {
            return new SampleStage(Double.valueOf(percentage), Mode.INSTANCE.getPERCENT(), null, 4, null);
        }

        @JvmStatic
        public final SampleStage withDocLimit(int results) {
            return new SampleStage(Integer.valueOf(results), Mode.INSTANCE.getDOCUMENTS(), null, 4, null);
        }
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public Sequence<Value> args$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        return SequencesKt.sequenceOf((Object[]) new Value[]{Values.encodeValue(this.size), this.mode.getProto()});
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SampleStage)) {
            return false;
        }
        SampleStage sampleStage = (SampleStage) other;
        return Intrinsics.areEqual(this.size, sampleStage.size) && Intrinsics.areEqual(this.mode, sampleStage.mode) && Intrinsics.areEqual(getOptions(), sampleStage.getOptions());
    }

    public int hashCode() {
        return (((this.size.hashCode() * 31) + this.mode.hashCode()) * 31) + getOptions().hashCode();
    }
}
