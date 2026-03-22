package com.google.firebase.firestore;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.pipeline.CollectionGroupOptions;
import com.google.firebase.firestore.pipeline.CollectionGroupSource;
import com.google.firebase.firestore.pipeline.CollectionSource;
import com.google.firebase.firestore.pipeline.CollectionSourceOptions;
import com.google.firebase.firestore.remote.RemoteSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RealtimePipeline.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u0015\u0010\n\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011J\u000e\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\fJ\u0015\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/google/firebase/firestore/RealtimePipelineSource;", "", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "<init>", "(Lcom/google/firebase/firestore/FirebaseFirestore;)V", "convertFrom", "Lcom/google/firebase/firestore/RealtimePipeline;", SearchIntents.EXTRA_QUERY, "Lcom/google/firebase/firestore/Query;", "collection", "path", "", "ref", "Lcom/google/firebase/firestore/CollectionReference;", "stage", "Lcom/google/firebase/firestore/pipeline/CollectionSource;", "collection$com_google_firebase_firebase_firestore", "collectionGroup", "collectionId", "Lcom/google/firebase/firestore/pipeline/CollectionGroupSource;", "collectionGroup$com_google_firebase_firebase_firestore", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class RealtimePipelineSource {
    private final FirebaseFirestore firestore;

    public RealtimePipelineSource(FirebaseFirestore firestore) {
        Intrinsics.checkNotNullParameter(firestore, "firestore");
        this.firestore = firestore;
    }

    public final RealtimePipeline convertFrom(Query query) {
        Intrinsics.checkNotNullParameter(query, "query");
        if (!Intrinsics.areEqual(query.firestore.getDatabaseId(), this.firestore.getDatabaseId())) {
            throw new IllegalArgumentException("Provided query is from a different Firestore instance.");
        }
        com.google.firebase.firestore.core.Query query2 = query.query;
        FirebaseFirestore firebaseFirestore = this.firestore;
        RealtimePipeline realtimePipeline = query2.toRealtimePipeline(firebaseFirestore, firebaseFirestore.getUserDataReader());
        Intrinsics.checkNotNullExpressionValue(realtimePipeline, "toRealtimePipeline(...)");
        return realtimePipeline;
    }

    public final RealtimePipeline collection(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        CollectionReference collectionReferenceCollection = this.firestore.collection(path);
        Intrinsics.checkNotNullExpressionValue(collectionReferenceCollection, "collection(...)");
        return collection(collectionReferenceCollection);
    }

    public final RealtimePipeline collection(CollectionReference ref) {
        Intrinsics.checkNotNullParameter(ref, "ref");
        ResourcePath resourcePathFromString = ResourcePath.fromString(ref.getPath());
        Intrinsics.checkNotNullExpressionValue(resourcePathFromString, "fromString(...)");
        return collection$com_google_firebase_firebase_firestore(new CollectionSource(resourcePathFromString, new RemoteSerializer(this.firestore.getDatabaseId()), new CollectionSourceOptions()));
    }

    public final RealtimePipeline collection$com_google_firebase_firebase_firestore(CollectionSource stage) {
        Intrinsics.checkNotNullParameter(stage, "stage");
        if (!Intrinsics.areEqual(stage.getSerializer().databaseId(), this.firestore.getDatabaseId())) {
            throw new IllegalArgumentException("Provided collection is from a different Firestore instance.");
        }
        FirebaseFirestore firebaseFirestore = this.firestore;
        RemoteSerializer remoteSerializer = new RemoteSerializer(this.firestore.getDatabaseId());
        UserDataReader userDataReader = this.firestore.getUserDataReader();
        Intrinsics.checkNotNullExpressionValue(userDataReader, "getUserDataReader(...)");
        return new RealtimePipeline(firebaseFirestore, remoteSerializer, userDataReader, stage);
    }

    public final RealtimePipeline collectionGroup(String collectionId) {
        Intrinsics.checkNotNullParameter(collectionId, "collectionId");
        return collectionGroup$com_google_firebase_firebase_firestore(new CollectionGroupSource(collectionId, new CollectionGroupOptions()));
    }

    public final RealtimePipeline collectionGroup$com_google_firebase_firebase_firestore(CollectionGroupSource stage) {
        Intrinsics.checkNotNullParameter(stage, "stage");
        FirebaseFirestore firebaseFirestore = this.firestore;
        RemoteSerializer remoteSerializer = new RemoteSerializer(this.firestore.getDatabaseId());
        UserDataReader userDataReader = this.firestore.getUserDataReader();
        Intrinsics.checkNotNullExpressionValue(userDataReader, "getUserDataReader(...)");
        return new RealtimePipeline(firebaseFirestore, remoteSerializer, userDataReader, stage);
    }
}
