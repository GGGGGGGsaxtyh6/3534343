package com.google.firebase.firestore;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class AggregateQuery {
    private final List<AggregateField> aggregateFieldList;
    private final Query query;

    AggregateQuery(Query query, List<AggregateField> list) {
        this.query = query;
        this.aggregateFieldList = list;
    }

    public Query getQuery() {
        return this.query;
    }

    public List<AggregateField> getAggregateFields() {
        return this.aggregateFieldList;
    }

    public Task<AggregateQuerySnapshot> get(AggregateSource aggregateSource) {
        Preconditions.checkNotNull(aggregateSource, "AggregateSource must not be null");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        ((Task) this.query.firestore.callClient(new Function() { // from class: com.google.firebase.firestore.AggregateQuery$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f$0.m600lambda$get$0$comgooglefirebasefirestoreAggregateQuery((FirestoreClient) obj);
            }
        })).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.AggregateQuery$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m601lambda$get$1$comgooglefirebasefirestoreAggregateQuery(taskCompletionSource, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: renamed from: lambda$get$0$com-google-firebase-firestore-AggregateQuery, reason: not valid java name */
    /* synthetic */ Task m600lambda$get$0$comgooglefirebasefirestoreAggregateQuery(FirestoreClient firestoreClient) {
        return firestoreClient.runAggregateQuery(this.query.query, this.aggregateFieldList);
    }

    /* JADX INFO: renamed from: lambda$get$1$com-google-firebase-firestore-AggregateQuery, reason: not valid java name */
    /* synthetic */ Object m601lambda$get$1$comgooglefirebasefirestoreAggregateQuery(TaskCompletionSource taskCompletionSource, Task task) throws Exception {
        if (task.isSuccessful()) {
            taskCompletionSource.setResult(new AggregateQuerySnapshot(this, (Map) task.getResult()));
            return null;
        }
        taskCompletionSource.setException(task.getException());
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AggregateQuery)) {
            return false;
        }
        AggregateQuery aggregateQuery = (AggregateQuery) obj;
        return this.query.equals(aggregateQuery.query) && this.aggregateFieldList.equals(aggregateQuery.aggregateFieldList);
    }

    public int hashCode() {
        return Objects.hash(this.query, this.aggregateFieldList);
    }
}
