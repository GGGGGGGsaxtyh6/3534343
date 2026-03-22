package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.evaluation.EvaluationContext;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: stage.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B!\b\u0001\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0007\u0010\u000bJ)\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0010¢\u0006\u0002\b\u001cJ\r\u0010\u001d\u001a\u00020\nH\u0010¢\u0006\u0002\b\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0096\u0002J\b\u0010#\u001a\u00020$H\u0016J\u0015\u0010%\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006H\u0010¢\u0006\u0002\b&J\u001b\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010*\u001a\u00020+H\u0010¢\u0006\u0002\b,R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR+\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0010j\b\u0012\u0004\u0012\u00020\n`\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013¨\u0006-"}, d2 = {"Lcom/google/firebase/firestore/pipeline/DocumentsSource;", "Lcom/google/firebase/firestore/pipeline/Stage;", "documents", "", "Lcom/google/firebase/firestore/model/ResourcePath;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "([Lcom/google/firebase/firestore/model/ResourcePath;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "document", "", "(Ljava/lang/String;)V", "getDocuments", "()[Lcom/google/firebase/firestore/model/ResourcePath;", "[Lcom/google/firebase/firestore/model/ResourcePath;", "docKeySet", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "getDocKeySet", "()Ljava/util/HashSet;", "docKeySet$delegate", "Lkotlin/Lazy;", "evaluate", "", "Lcom/google/firebase/firestore/model/MutableDocument;", "context", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "inputs", "evaluate$com_google_firebase_firebase_firestore", "canonicalId", "canonicalId$com_google_firebase_firebase_firestore", "equals", "", "other", "", "hashCode", "", "self", "self$com_google_firebase_firebase_firestore", "args", "Lkotlin/sequences/Sequence;", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "args$com_google_firebase_firebase_firestore", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class DocumentsSource extends Stage<DocumentsSource> {

    /* JADX INFO: renamed from: docKeySet$delegate, reason: from kotlin metadata */
    private final Lazy docKeySet;
    private final ResourcePath[] documents;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DocumentsSource(ResourcePath[] documents) {
        this(documents, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(documents, "documents");
    }

    public final ResourcePath[] getDocuments() {
        return this.documents;
    }

    public /* synthetic */ DocumentsSource(ResourcePath[] resourcePathArr, InternalOptions internalOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(resourcePathArr, (i & 2) != 0 ? InternalOptions.EMPTY : internalOptions);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DocumentsSource(ResourcePath[] documents, InternalOptions options) {
        super("documents", options, null);
        Intrinsics.checkNotNullParameter(documents, "documents");
        Intrinsics.checkNotNullParameter(options, "options");
        this.documents = documents;
        this.docKeySet = LazyKt.lazy(new Function0() { // from class: com.google.firebase.firestore.pipeline.DocumentsSource$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DocumentsSource.docKeySet_delegate$lambda$1(this.f$0);
            }
        });
    }

    private final HashSet<String> getDocKeySet() {
        return (HashSet) this.docKeySet.getValue();
    }

    static final HashSet docKeySet_delegate$lambda$1(DocumentsSource documentsSource) {
        ResourcePath[] resourcePathArr = documentsSource.documents;
        ArrayList arrayList = new ArrayList(resourcePathArr.length);
        for (ResourcePath resourcePath : resourcePathArr) {
            arrayList.add(resourcePath.canonicalString());
        }
        return CollectionsKt.toHashSet(arrayList);
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public List<MutableDocument> evaluate$com_google_firebase_firebase_firestore(EvaluationContext context, List<MutableDocument> inputs) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(inputs, "inputs");
        ArrayList arrayList = new ArrayList();
        for (Object obj : inputs) {
            MutableDocument mutableDocument = (MutableDocument) obj;
            if (mutableDocument.isFoundDocument() && getDocKeySet().contains(mutableDocument.getKey().getPath().canonicalString())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public String canonicalId$com_google_firebase_firebase_firestore() {
        return getName() + '(' + CollectionsKt.joinToString$default(ArraysKt.sorted(this.documents), ",", null, null, 0, null, null, 62, null) + ')';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentsSource)) {
            return false;
        }
        DocumentsSource documentsSource = (DocumentsSource) other;
        return Arrays.equals(this.documents, documentsSource.documents) && Intrinsics.areEqual(getOptions(), documentsSource.getOptions());
    }

    public int hashCode() {
        return (Arrays.hashCode(this.documents) * 31) + getOptions().hashCode();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DocumentsSource(String document) {
        this(new ResourcePath[]{ResourcePath.fromString(document)}, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(document, "document");
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public DocumentsSource self$com_google_firebase_firebase_firestore(InternalOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new DocumentsSource(this.documents, options);
    }

    @Override // com.google.firebase.firestore.pipeline.Stage
    public Sequence<Value> args$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        return SequencesKt.map(ArraysKt.asSequence(this.documents), new DocumentsSource$args$1(Values.INSTANCE));
    }
}
