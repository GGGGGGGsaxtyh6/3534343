package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.pipeline.evaluation.EvaluationContext;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB!\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\n¢\u0006\u0004\b\b\u0010\u000bJ\r\u0010\u0010\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0015\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007H\u0010¢\u0006\u0002\b\u001aJ\u001b\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0010¢\u0006\u0002\b J)\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0010¢\u0006\u0002\b'R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006("}, d2 = {"Lcom/google/firebase/firestore/pipeline/CollectionSource;", "Lcom/google/firebase/firestore/pipeline/Stage;", "path", "Lcom/google/firebase/firestore/model/ResourcePath;", "serializer", "Lcom/google/firebase/firestore/remote/RemoteSerializer;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "(Lcom/google/firebase/firestore/model/ResourcePath;Lcom/google/firebase/firestore/remote/RemoteSerializer;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "Lcom/google/firebase/firestore/pipeline/CollectionSourceOptions;", "(Lcom/google/firebase/firestore/model/ResourcePath;Lcom/google/firebase/firestore/remote/RemoteSerializer;Lcom/google/firebase/firestore/pipeline/CollectionSourceOptions;)V", "getPath$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/model/ResourcePath;", "getSerializer$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/remote/RemoteSerializer;", "canonicalId", "", "canonicalId$com_google_firebase_firebase_firestore", "equals", "", "other", "", "hashCode", "", "self", "self$com_google_firebase_firebase_firestore", "args", "Lkotlin/sequences/Sequence;", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "args$com_google_firebase_firebase_firestore", "evaluate", "", "Lcom/google/firebase/firestore/model/MutableDocument;", "context", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "inputs", "evaluate$com_google_firebase_firebase_firestore", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class CollectionSource extends Stage<CollectionSource> {
    private final ResourcePath path;
    private final RemoteSerializer serializer;

    /* JADX INFO: renamed from: getPath$com_google_firebase_firebase_firestore, reason: from getter */
    public final ResourcePath getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: getSerializer$com_google_firebase_firebase_firestore, reason: from getter */
    public final RemoteSerializer getSerializer() {
        return this.serializer;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionSource(ResourcePath path, RemoteSerializer serializer, InternalOptions options) {
        super("collection", options, null);
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(options, "options");
        this.path = path;
        this.serializer = serializer;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CollectionSource(ResourcePath path, RemoteSerializer serializer, CollectionSourceOptions options) {
        this(path, serializer, options.getOptions());
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(options, "options");
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public String canonicalId$com_google_firebase_firebase_firestore() {
        return getName() + '(' + this.path.canonicalString() + ')';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CollectionSource)) {
            return false;
        }
        CollectionSource collectionSource = (CollectionSource) other;
        return Intrinsics.areEqual(this.path, collectionSource.path) && Intrinsics.areEqual(this.serializer.databaseId(), collectionSource.serializer.databaseId()) && Intrinsics.areEqual(getOptions(), collectionSource.getOptions());
    }

    public int hashCode() {
        return (((this.path.hashCode() * 31) + this.serializer.databaseId().hashCode()) * 31) + getOptions().hashCode();
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public CollectionSource self$com_google_firebase_firebase_firestore(InternalOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new CollectionSource(this.path, this.serializer, options);
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public Sequence<Value> args$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        return SequencesKt.sequenceOf((Object[]) new Value[]{Value.newBuilder().setReferenceValue(DomExceptionUtils.SEPARATOR + this.path.canonicalString()).build()});
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public List<MutableDocument> evaluate$com_google_firebase_firebase_firestore(EvaluationContext context, List<MutableDocument> inputs) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(inputs, "inputs");
        ArrayList arrayList = new ArrayList();
        for (Object obj : inputs) {
            MutableDocument mutableDocument = (MutableDocument) obj;
            if (mutableDocument.isFoundDocument() && Intrinsics.areEqual(mutableDocument.getKey().getCollectionPath(), this.path)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
