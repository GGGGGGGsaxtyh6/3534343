package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.Selectable;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001dB!\b\u0002\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\f\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006H\u0010¢\u0006\u0002\b\rJ\r\u0010\u000e\u001a\u00020\u000fH\u0010¢\u0006\u0002\b\u0010J\u001b\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0010¢\u0006\u0002\b\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u001e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u001e"}, d2 = {"Lcom/google/firebase/firestore/pipeline/SelectStage;", "Lcom/google/firebase/firestore/pipeline/Stage;", "fields", "", "Lcom/google/firebase/firestore/pipeline/Selectable;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "([Lcom/google/firebase/firestore/pipeline/Selectable;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "getFields$com_google_firebase_firebase_firestore", "()[Lcom/google/firebase/firestore/pipeline/Selectable;", "[Lcom/google/firebase/firestore/pipeline/Selectable;", "self", "self$com_google_firebase_firebase_firestore", "canonicalId", "", "canonicalId$com_google_firebase_firebase_firestore", "args", "Lkotlin/sequences/Sequence;", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "args$com_google_firebase_firebase_firestore", "equals", "", "other", "", "hashCode", "", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SelectStage extends Stage<SelectStage> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Selectable[] fields;

    public /* synthetic */ SelectStage(Selectable[] selectableArr, InternalOptions internalOptions, DefaultConstructorMarker defaultConstructorMarker) {
        this(selectableArr, internalOptions);
    }

    @JvmStatic
    public static final SelectStage of(Selectable selectable, Object... objArr) {
        return INSTANCE.of(selectable, objArr);
    }

    @JvmStatic
    public static final SelectStage of(String str, Object... objArr) {
        return INSTANCE.of(str, objArr);
    }

    /* JADX INFO: renamed from: getFields$com_google_firebase_firebase_firestore, reason: from getter */
    public final Selectable[] getFields() {
        return this.fields;
    }

    /* JADX INFO: compiled from: stage.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t\"\u00020\u0001H\u0007¢\u0006\u0002\u0010\nJ)\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t\"\u00020\u0001H\u0007¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/google/firebase/firestore/pipeline/SelectStage$Companion;", "", "<init>", "()V", "of", "Lcom/google/firebase/firestore/pipeline/SelectStage;", "selection", "Lcom/google/firebase/firestore/pipeline/Selectable;", "additionalSelections", "", "(Lcom/google/firebase/firestore/pipeline/Selectable;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/SelectStage;", "fieldName", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/SelectStage;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SelectStage of(Selectable selection, Object... additionalSelections) {
            Intrinsics.checkNotNullParameter(selection, "selection");
            Intrinsics.checkNotNullParameter(additionalSelections, "additionalSelections");
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(selection);
            Selectable.Companion companion = Selectable.INSTANCE;
            ArrayList arrayList = new ArrayList(additionalSelections.length);
            for (Object obj : additionalSelections) {
                arrayList.add(companion.toSelectable(obj));
            }
            spreadBuilder.addSpread(arrayList.toArray(new Selectable[0]));
            return new SelectStage((Selectable[]) spreadBuilder.toArray(new Selectable[spreadBuilder.size()]), InternalOptions.EMPTY, null);
        }

        @JvmStatic
        public final SelectStage of(String fieldName, Object... additionalSelections) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(additionalSelections, "additionalSelections");
            return of(Expression.INSTANCE.field(fieldName), Arrays.copyOf(additionalSelections, additionalSelections.length));
        }
    }

    private SelectStage(Selectable[] selectableArr, InternalOptions internalOptions) {
        super("select", internalOptions, null);
        this.fields = selectableArr;
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public SelectStage self$com_google_firebase_firebase_firestore(InternalOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new SelectStage(this.fields, options);
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public String canonicalId$com_google_firebase_firebase_firestore() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public Sequence<Value> args$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        return SequencesKt.sequenceOf((Object[]) new Value[]{Values.encodeValue((Map<String, Value>) StageKt.associateWithoutDuplications(this.fields, userDataReader))});
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectStage)) {
            return false;
        }
        SelectStage selectStage = (SelectStage) other;
        return Arrays.equals(this.fields, selectStage.fields) && Intrinsics.areEqual(getOptions(), selectStage.getOptions());
    }

    public int hashCode() {
        return (Arrays.hashCode(this.fields) * 31) + getOptions().hashCode();
    }
}
