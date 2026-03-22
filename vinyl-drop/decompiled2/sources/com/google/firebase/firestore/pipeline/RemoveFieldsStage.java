package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firestore.v1.Value;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B#\b\u0000\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006H\u0010¢\u0006\u0002\b\u000bJ\r\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0002\b\u000eJ\u001b\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0010¢\u0006\u0002\b\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u0018\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u001b"}, d2 = {"Lcom/google/firebase/firestore/pipeline/RemoveFieldsStage;", "Lcom/google/firebase/firestore/pipeline/Stage;", "fields", "", "Lcom/google/firebase/firestore/pipeline/Field;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "([Lcom/google/firebase/firestore/pipeline/Field;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "[Lcom/google/firebase/firestore/pipeline/Field;", "self", "self$com_google_firebase_firebase_firestore", "canonicalId", "", "canonicalId$com_google_firebase_firebase_firestore", "args", "Lkotlin/sequences/Sequence;", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "args$com_google_firebase_firebase_firestore", "equals", "", "other", "", "hashCode", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class RemoveFieldsStage extends Stage<RemoveFieldsStage> {
    private final Field[] fields;

    public /* synthetic */ RemoveFieldsStage(Field[] fieldArr, InternalOptions internalOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fieldArr, (i & 2) != 0 ? InternalOptions.EMPTY : internalOptions);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoveFieldsStage(Field[] fields, InternalOptions options) {
        super("remove_fields", options, null);
        Intrinsics.checkNotNullParameter(fields, "fields");
        Intrinsics.checkNotNullParameter(options, "options");
        this.fields = fields;
        for (Field field : fields) {
            String alias = field.getAlias();
            if (Intrinsics.areEqual(alias, Field.DOCUMENT_ID.getAlias())) {
                throw new IllegalArgumentException(("Alias " + Field.DOCUMENT_ID.getAlias() + " is required").toString());
            }
            if (Intrinsics.areEqual(alias, Field.CREATE_TIME.getAlias())) {
                throw new IllegalArgumentException(("Alias " + Field.CREATE_TIME.getAlias() + " is required").toString());
            }
            if (Intrinsics.areEqual(alias, Field.UPDATE_TIME.getAlias())) {
                throw new IllegalArgumentException(("Alias " + Field.UPDATE_TIME.getAlias() + " is required").toString());
            }
        }
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public RemoveFieldsStage self$com_google_firebase_firebase_firestore(InternalOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new RemoveFieldsStage(this.fields, options);
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public String canonicalId$com_google_firebase_firebase_firestore() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public Sequence<Value> args$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        return SequencesKt.map(ArraysKt.asSequence(this.fields), RemoveFieldsStage$args$1.INSTANCE);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RemoveFieldsStage)) {
            return false;
        }
        RemoveFieldsStage removeFieldsStage = (RemoveFieldsStage) other;
        return Arrays.equals(this.fields, removeFieldsStage.fields) && Intrinsics.areEqual(getOptions(), removeFieldsStage.getOptions());
    }

    public int hashCode() {
        return (Arrays.hashCode(this.fields) * 31) + getOptions().hashCode();
    }
}
