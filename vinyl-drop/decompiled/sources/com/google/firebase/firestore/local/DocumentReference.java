package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
class DocumentReference {
    static final Comparator<DocumentReference> BY_KEY = new Comparator() { // from class: com.google.firebase.firestore.local.DocumentReference$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return DocumentReference.lambda$static$0((DocumentReference) obj, (DocumentReference) obj2);
        }
    };
    static final Comparator<DocumentReference> BY_TARGET = new Comparator() { // from class: com.google.firebase.firestore.local.DocumentReference$$ExternalSyntheticLambda1
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return DocumentReference.lambda$static$1((DocumentReference) obj, (DocumentReference) obj2);
        }
    };
    private final DocumentKey key;
    private final int targetOrBatchId;

    public DocumentReference(DocumentKey documentKey, int i) {
        this.key = documentKey;
        this.targetOrBatchId = i;
    }

    DocumentKey getKey() {
        return this.key;
    }

    int getId() {
        return this.targetOrBatchId;
    }

    static /* synthetic */ int lambda$static$0(DocumentReference documentReference, DocumentReference documentReference2) {
        int iCompareTo = documentReference.key.compareTo(documentReference2.key);
        return iCompareTo != 0 ? iCompareTo : Integer.compare(documentReference.targetOrBatchId, documentReference2.targetOrBatchId);
    }

    static /* synthetic */ int lambda$static$1(DocumentReference documentReference, DocumentReference documentReference2) {
        int iCompare = Integer.compare(documentReference.targetOrBatchId, documentReference2.targetOrBatchId);
        return iCompare != 0 ? iCompare : documentReference.key.compareTo(documentReference2.key);
    }
}
