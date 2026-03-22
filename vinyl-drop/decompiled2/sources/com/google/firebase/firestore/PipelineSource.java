package com.google.firebase.firestore;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.pipeline.AliasedAggregate;
import com.google.firebase.firestore.pipeline.CollectionGroupOptions;
import com.google.firebase.firestore.pipeline.CollectionGroupSource;
import com.google.firebase.firestore.pipeline.CollectionSource;
import com.google.firebase.firestore.pipeline.CollectionSourceOptions;
import com.google.firebase.firestore.pipeline.DatabaseSource;
import com.google.firebase.firestore.pipeline.DocumentsSource;
import com.google.firebase.firestore.remote.RemoteSerializer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Pipeline.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u000eJ\u0016\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0007J\u001f\u0010\u0017\u001a\u00020\u00072\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\u0018\"\u00020\u000e¢\u0006\u0002\u0010\u0019J\u001f\u0010\u0017\u001a\u00020\u00072\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001a0\u0018\"\u00020\u001a¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/google/firebase/firestore/PipelineSource;", "", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "<init>", "(Lcom/google/firebase/firestore/FirebaseFirestore;)V", "createFrom", "Lcom/google/firebase/firestore/Pipeline;", SearchIntents.EXTRA_QUERY, "Lcom/google/firebase/firestore/Query;", "aggregateQuery", "Lcom/google/firebase/firestore/AggregateQuery;", "collection", "path", "", "ref", "Lcom/google/firebase/firestore/CollectionReference;", "options", "Lcom/google/firebase/firestore/pipeline/CollectionSourceOptions;", "collectionGroup", "collectionId", "Lcom/google/firebase/firestore/pipeline/CollectionGroupOptions;", "database", "documents", "", "([Ljava/lang/String;)Lcom/google/firebase/firestore/Pipeline;", "Lcom/google/firebase/firestore/DocumentReference;", "([Lcom/google/firebase/firestore/DocumentReference;)Lcom/google/firebase/firestore/Pipeline;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PipelineSource {
    private final FirebaseFirestore firestore;

    public PipelineSource(FirebaseFirestore firestore) {
        Intrinsics.checkNotNullParameter(firestore, "firestore");
        this.firestore = firestore;
    }

    public final Pipeline createFrom(Query query) {
        Intrinsics.checkNotNullParameter(query, "query");
        if (!Intrinsics.areEqual(query.firestore.getDatabaseId(), this.firestore.getDatabaseId())) {
            throw new IllegalArgumentException("Provided query is from a different Firestore instance.");
        }
        com.google.firebase.firestore.core.Query query2 = query.query;
        FirebaseFirestore firebaseFirestore = this.firestore;
        Pipeline pipeline = query2.toPipeline(firebaseFirestore, firebaseFirestore.getUserDataReader());
        Intrinsics.checkNotNullExpressionValue(pipeline, "toPipeline(...)");
        return pipeline;
    }

    public final Pipeline createFrom(AggregateQuery aggregateQuery) {
        Intrinsics.checkNotNullParameter(aggregateQuery, "aggregateQuery");
        List<AggregateField> aggregateFields = aggregateQuery.getAggregateFields();
        Intrinsics.checkNotNullExpressionValue(aggregateFields, "getAggregateFields(...)");
        Query query = aggregateQuery.getQuery();
        Intrinsics.checkNotNullExpressionValue(query, "getQuery(...)");
        Pipeline pipelineCreateFrom = createFrom(query);
        AliasedAggregate pipeline = ((AggregateField) CollectionsKt.first((List) aggregateFields)).toPipeline();
        Intrinsics.checkNotNullExpressionValue(pipeline, "toPipeline(...)");
        List listDrop = CollectionsKt.drop(aggregateFields, 1);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDrop, 10));
        Iterator it = listDrop.iterator();
        while (it.hasNext()) {
            arrayList.add(((AggregateField) it.next()).toPipeline());
        }
        AliasedAggregate[] aliasedAggregateArr = (AliasedAggregate[]) arrayList.toArray(new AliasedAggregate[0]);
        return pipelineCreateFrom.aggregate(pipeline, (AliasedAggregate[]) Arrays.copyOf(aliasedAggregateArr, aliasedAggregateArr.length));
    }

    public final Pipeline collection(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        CollectionReference collectionReferenceCollection = this.firestore.collection(path);
        Intrinsics.checkNotNullExpressionValue(collectionReferenceCollection, "collection(...)");
        return collection(collectionReferenceCollection);
    }

    public final Pipeline collection(CollectionReference ref) {
        Intrinsics.checkNotNullParameter(ref, "ref");
        return collection(ref, new CollectionSourceOptions());
    }

    public final Pipeline collection(CollectionReference ref, CollectionSourceOptions options) {
        FirebaseOptions options2;
        FirebaseOptions options3;
        Intrinsics.checkNotNullParameter(ref, "ref");
        Intrinsics.checkNotNullParameter(options, "options");
        if (Intrinsics.areEqual(ref.firestore.getDatabaseId(), this.firestore.getDatabaseId())) {
            FirebaseApp app = ref.firestore.getApp();
            String projectId = null;
            String projectId2 = (app == null || (options3 = app.getOptions()) == null) ? null : options3.getProjectId();
            FirebaseApp app2 = this.firestore.getApp();
            if (app2 != null && (options2 = app2.getOptions()) != null) {
                projectId = options2.getProjectId();
            }
            if (Intrinsics.areEqual(projectId2, projectId)) {
                FirebaseFirestore firebaseFirestore = this.firestore;
                UserDataReader userDataReader = firebaseFirestore.getUserDataReader();
                Intrinsics.checkNotNullExpressionValue(userDataReader, "getUserDataReader(...)");
                ResourcePath resourcePathFromString = ResourcePath.fromString(ref.getPath());
                Intrinsics.checkNotNullExpressionValue(resourcePathFromString, "fromString(...)");
                return new Pipeline(firebaseFirestore, userDataReader, new CollectionSource(resourcePathFromString, new RemoteSerializer(this.firestore.getDatabaseId()), options));
            }
        }
        throw new IllegalArgumentException("Invalid CollectionReference. The Firestore instance of the CollectionReference must match the Firestore instance of the PipelineSource.");
    }

    public final Pipeline collectionGroup(String collectionId) {
        Intrinsics.checkNotNullParameter(collectionId, "collectionId");
        return collectionGroup(collectionId, new CollectionGroupOptions());
    }

    public final Pipeline collectionGroup(String collectionId, CollectionGroupOptions options) {
        Intrinsics.checkNotNullParameter(collectionId, "collectionId");
        Intrinsics.checkNotNullParameter(options, "options");
        FirebaseFirestore firebaseFirestore = this.firestore;
        UserDataReader userDataReader = firebaseFirestore.getUserDataReader();
        Intrinsics.checkNotNullExpressionValue(userDataReader, "getUserDataReader(...)");
        return new Pipeline(firebaseFirestore, userDataReader, new CollectionGroupSource(collectionId, options));
    }

    public final Pipeline database() {
        FirebaseFirestore firebaseFirestore = this.firestore;
        UserDataReader userDataReader = firebaseFirestore.getUserDataReader();
        Intrinsics.checkNotNullExpressionValue(userDataReader, "getUserDataReader(...)");
        return new Pipeline(firebaseFirestore, userDataReader, new DatabaseSource(null, 1, null));
    }

    public final Pipeline documents(String... documents) {
        Intrinsics.checkNotNullParameter(documents, "documents");
        FirebaseFirestore firebaseFirestore = this.firestore;
        ArrayList arrayList = new ArrayList(documents.length);
        for (String str : documents) {
            arrayList.add(firebaseFirestore.document(str));
        }
        DocumentReference[] documentReferenceArr = (DocumentReference[]) arrayList.toArray(new DocumentReference[0]);
        return documents((DocumentReference[]) Arrays.copyOf(documentReferenceArr, documentReferenceArr.length));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Pipeline documents(DocumentReference... documents) {
        Intrinsics.checkNotNullParameter(documents, "documents");
        DatabaseId databaseId = this.firestore.getDatabaseId();
        Intrinsics.checkNotNullExpressionValue(databaseId, "getDatabaseId(...)");
        for (DocumentReference documentReference : documents) {
            if (!Intrinsics.areEqual(documentReference.getFirestore().getDatabaseId(), databaseId)) {
                throw new IllegalArgumentException("Provided document reference is from a different Firestore instance.");
            }
        }
        FirebaseFirestore firebaseFirestore = this.firestore;
        UserDataReader userDataReader = firebaseFirestore.getUserDataReader();
        Intrinsics.checkNotNullExpressionValue(userDataReader, "getUserDataReader(...)");
        ArrayList arrayList = new ArrayList(documents.length);
        for (DocumentReference documentReference2 : documents) {
            arrayList.add(ResourcePath.fromString(documentReference2.getPath()));
        }
        return new Pipeline(firebaseFirestore, userDataReader, new DocumentsSource((ResourcePath[]) arrayList.toArray(new ResourcePath[0]), null, 2, 0 == true ? 1 : 0));
    }
}
