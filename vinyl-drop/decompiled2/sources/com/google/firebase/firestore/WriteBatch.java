package com.google.firebase.firestore;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class WriteBatch {
    private final FirebaseFirestore firestore;
    private final ArrayList<Mutation> mutations = new ArrayList<>();
    private boolean committed = false;

    public interface Function {
        void apply(WriteBatch writeBatch);
    }

    WriteBatch(FirebaseFirestore firebaseFirestore) {
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firebaseFirestore);
    }

    public WriteBatch set(DocumentReference documentReference, Object obj) {
        return set(documentReference, obj, SetOptions.OVERWRITE);
    }

    public WriteBatch set(DocumentReference documentReference, Object obj, SetOptions setOptions) {
        UserData.ParsedSetData setData;
        this.firestore.validateReference(documentReference);
        Preconditions.checkNotNull(obj, "Provided data must not be null.");
        Preconditions.checkNotNull(setOptions, "Provided options must not be null.");
        verifyNotCommitted();
        boolean zIsMerge = setOptions.isMerge();
        FirebaseFirestore firebaseFirestore = this.firestore;
        if (zIsMerge) {
            setData = firebaseFirestore.getUserDataReader().parseMergeData(obj, setOptions.getFieldMask());
        } else {
            setData = firebaseFirestore.getUserDataReader().parseSetData(obj);
        }
        this.mutations.add(setData.toMutation(documentReference.getKey(), Precondition.NONE));
        return this;
    }

    public WriteBatch update(DocumentReference documentReference, Map<String, Object> map) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(map));
    }

    public WriteBatch update(DocumentReference documentReference, String str, Object obj, Object... objArr) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, str, obj, objArr)));
    }

    public WriteBatch update(DocumentReference documentReference, FieldPath fieldPath, Object obj, Object... objArr) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, obj, objArr)));
    }

    private WriteBatch update(DocumentReference documentReference, UserData.ParsedUpdateData parsedUpdateData) {
        this.firestore.validateReference(documentReference);
        verifyNotCommitted();
        this.mutations.add(parsedUpdateData.toMutation(documentReference.getKey(), Precondition.exists(true)));
        return this;
    }

    public WriteBatch delete(DocumentReference documentReference) {
        this.firestore.validateReference(documentReference);
        verifyNotCommitted();
        this.mutations.add(new DeleteMutation(documentReference.getKey(), Precondition.NONE));
        return this;
    }

    public Task<Void> commit() {
        verifyNotCommitted();
        this.committed = true;
        if (!this.mutations.isEmpty()) {
            return (Task) this.firestore.callClient(new com.google.firebase.firestore.util.Function() { // from class: com.google.firebase.firestore.WriteBatch$$ExternalSyntheticLambda0
                @Override // com.google.firebase.firestore.util.Function
                public final Object apply(Object obj) {
                    return this.f$0.m618lambda$commit$0$comgooglefirebasefirestoreWriteBatch((FirestoreClient) obj);
                }
            });
        }
        return Tasks.forResult(null);
    }

    /* JADX INFO: renamed from: lambda$commit$0$com-google-firebase-firestore-WriteBatch, reason: not valid java name */
    /* synthetic */ Task m618lambda$commit$0$comgooglefirebasefirestoreWriteBatch(FirestoreClient firestoreClient) {
        return firestoreClient.write(this.mutations);
    }

    private void verifyNotCommitted() {
        if (this.committed) {
            throw new IllegalStateException("A write batch can no longer be used after commit() has been called.");
        }
    }
}
