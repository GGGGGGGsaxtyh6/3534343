package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.LimboDocumentChange;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class View {
    private boolean current;
    private DocumentSet documentSet;
    private final QueryOrPipeline query;
    private ImmutableSortedSet<DocumentKey> syncedDocuments;
    private ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
    private ImmutableSortedSet<DocumentKey> limboDocuments = DocumentKey.emptyKeySet();
    private ImmutableSortedSet<DocumentKey> mutatedKeys = DocumentKey.emptyKeySet();

    public static class DocumentChanges {
        final DocumentViewChangeSet changeSet;
        final DocumentSet documentSet;
        final ImmutableSortedSet<DocumentKey> mutatedKeys;
        private final boolean needsRefill;

        /* synthetic */ DocumentChanges(DocumentSet documentSet, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet immutableSortedSet, boolean z, AnonymousClass1 anonymousClass1) {
            this(documentSet, documentViewChangeSet, immutableSortedSet, z);
        }

        private DocumentChanges(DocumentSet documentSet, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet<DocumentKey> immutableSortedSet, boolean z) {
            this.documentSet = documentSet;
            this.changeSet = documentViewChangeSet;
            this.mutatedKeys = immutableSortedSet;
            this.needsRefill = z;
        }

        public boolean needsRefill() {
            return this.needsRefill;
        }
    }

    private static class LimitEdges {
        final Document first;
        final Document second;

        LimitEdges(Document document, Document document2) {
            this.first = document;
            this.second = document2;
        }
    }

    public View(QueryOrPipeline queryOrPipeline, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        this.query = queryOrPipeline;
        this.documentSet = DocumentSet.emptySet(queryOrPipeline.comparator());
        this.syncedDocuments = immutableSortedSet;
    }

    public ViewSnapshot.SyncState getSyncState() {
        return this.syncState;
    }

    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        return computeDocChanges(immutableSortedMap, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00ee  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap, DocumentChanges documentChanges) {
        ImmutableSortedSet<DocumentKey> immutableSortedSet;
        DocumentSet documentSet;
        Document firstDocument;
        boolean z;
        ImmutableSortedSet<DocumentKey> immutableSortedSetRemove;
        DocumentViewChangeSet documentViewChangeSet = documentChanges != null ? documentChanges.changeSet : new DocumentViewChangeSet();
        DocumentSet documentSet2 = documentChanges != null ? documentChanges.documentSet : this.documentSet;
        ImmutableSortedSet<DocumentKey> immutableSortedSetRemove2 = documentChanges != null ? documentChanges.mutatedKeys : this.mutatedKeys;
        LimitEdges limitEdges = getLimitEdges(this.query, documentSet2);
        Document document = limitEdges.first;
        Document document2 = limitEdges.second;
        DocumentSet documentSetRemove = documentSet2;
        boolean z2 = false;
        for (Map.Entry<DocumentKey, Document> entry : immutableSortedMap) {
            DocumentKey key = entry.getKey();
            Document document3 = documentSet2.getDocument(key);
            Document value = this.query.matches(entry.getValue()) ? entry.getValue() : null;
            boolean z3 = document3 != null && this.mutatedKeys.contains(document3.getKey());
            boolean z4 = value != null && (value.hasLocalMutations() || (this.mutatedKeys.contains(value.getKey()) && value.hasCommittedMutations()));
            if (document3 == null || value == null) {
                if (document3 != null || value == null) {
                    if (document3 != null && value == null) {
                        documentViewChangeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.REMOVED, document3));
                        if (document != null || document2 != null) {
                            z2 = true;
                        }
                    }
                    z = false;
                } else {
                    documentViewChangeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.ADDED, value));
                }
                z = true;
            } else if (document3.getData().equals(value.getData())) {
                if (z3 != z4) {
                    documentViewChangeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.METADATA, value));
                    z = true;
                }
                z = false;
            } else {
                if (!shouldWaitForSyncedDocument(document3, value)) {
                    documentViewChangeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.MODIFIED, value));
                    Comparator<Document> comparator = this.query.comparator();
                    if ((document != null && comparator.compare(value, document) > 0) || (document2 != null && comparator.compare(value, document2) < 0)) {
                    }
                    z = true;
                }
                z = false;
            }
            if (z) {
                if (value != null) {
                    documentSetRemove = documentSetRemove.add(value);
                    if (value.hasLocalMutations()) {
                        immutableSortedSetRemove = immutableSortedSetRemove2.insert(value.getKey());
                    } else {
                        immutableSortedSetRemove = immutableSortedSetRemove2.remove(value.getKey());
                    }
                } else {
                    documentSetRemove = documentSetRemove.remove(key);
                    immutableSortedSetRemove = immutableSortedSetRemove2.remove(key);
                }
                immutableSortedSetRemove2 = immutableSortedSetRemove;
            }
        }
        Long limit = getLimit(this.query);
        if (limit == null) {
            immutableSortedSet = immutableSortedSetRemove2;
            documentSet = documentSetRemove;
        } else if (this.query.isPipeline()) {
            ArrayList arrayList = new ArrayList();
            Iterator<Document> it = documentSetRemove.iterator();
            while (it.hasNext()) {
                arrayList.add((MutableDocument) it.next());
            }
            List<MutableDocument> listEvaluate$com_google_firebase_firebase_firestore = this.query.pipeline$com_google_firebase_firebase_firestore().evaluate$com_google_firebase_firebase_firestore(arrayList);
            DocumentSet documentSetEmptySet = DocumentSet.emptySet(this.query.comparator());
            Iterator<MutableDocument> it2 = listEvaluate$com_google_firebase_firebase_firestore.iterator();
            while (it2.hasNext()) {
                documentSetEmptySet = documentSetEmptySet.add(it2.next());
            }
            for (Document document4 : documentSetRemove) {
                if (!documentSetEmptySet.contains(document4.getKey())) {
                    immutableSortedSetRemove2 = immutableSortedSetRemove2.remove(document4.getKey());
                    documentViewChangeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.REMOVED, document4));
                }
            }
            documentSet = documentSetEmptySet;
            immutableSortedSet = immutableSortedSetRemove2;
        } else {
            long jAbs = Math.abs(limit.longValue());
            Query.LimitType limitType = getLimitType(this.query);
            long size = documentSetRemove.size();
            while (true) {
                size -= jAbs;
                if (size <= 0) {
                    break;
                }
                if (limitType == Query.LimitType.LIMIT_TO_FIRST) {
                    firstDocument = documentSetRemove.getLastDocument();
                } else {
                    firstDocument = documentSetRemove.getFirstDocument();
                }
                documentSetRemove = documentSetRemove.remove(firstDocument.getKey());
                immutableSortedSetRemove2 = immutableSortedSetRemove2.remove(firstDocument.getKey());
                documentViewChangeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.REMOVED, firstDocument));
                jAbs = 1;
            }
            immutableSortedSet = immutableSortedSetRemove2;
            documentSet = documentSetRemove;
        }
        Assert.hardAssert(!z2 || documentChanges == null, "View was refilled using docs that themselves needed refilling.", new Object[0]);
        return new DocumentChanges(documentSet, documentViewChangeSet, immutableSortedSet, z2, null);
    }

    private boolean shouldWaitForSyncedDocument(Document document, Document document2) {
        return document.hasLocalMutations() && document2.hasCommittedMutations() && !document2.hasLocalMutations();
    }

    public ViewChange applyChanges(DocumentChanges documentChanges) {
        return applyChanges(documentChanges, null);
    }

    public ViewChange applyChanges(DocumentChanges documentChanges, TargetChange targetChange) {
        return applyChanges(documentChanges, targetChange, false);
    }

    public ViewChange applyChanges(DocumentChanges documentChanges, TargetChange targetChange, boolean z) {
        ViewSnapshot viewSnapshot;
        Assert.hardAssert(!documentChanges.needsRefill, "Cannot apply changes that need a refill", new Object[0]);
        DocumentSet documentSet = this.documentSet;
        this.documentSet = documentChanges.documentSet;
        this.mutatedKeys = documentChanges.mutatedKeys;
        List<DocumentViewChange> changes = documentChanges.changeSet.getChanges();
        final Comparator<Document> comparator = this.query.comparator();
        Collections.sort(changes, new Comparator() { // from class: com.google.firebase.firestore.core.View$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return View.lambda$applyChanges$0(comparator, (DocumentViewChange) obj, (DocumentViewChange) obj2);
            }
        });
        applyTargetChange(targetChange);
        List<LimboDocumentChange> listEmptyList = z ? Collections.emptyList() : updateLimboDocuments();
        ViewSnapshot.SyncState syncState = (this.limboDocuments.size() == 0 && this.current && !z) ? ViewSnapshot.SyncState.SYNCED : ViewSnapshot.SyncState.LOCAL;
        boolean z2 = syncState != this.syncState;
        this.syncState = syncState;
        if (changes.size() != 0 || z2) {
            viewSnapshot = new ViewSnapshot(this.query, documentChanges.documentSet, documentSet, changes, syncState == ViewSnapshot.SyncState.LOCAL, documentChanges.mutatedKeys, z2, false, (targetChange == null || targetChange.getResumeToken().isEmpty()) ? false : true);
        } else {
            viewSnapshot = null;
        }
        return new ViewChange(viewSnapshot, listEmptyList);
    }

    static /* synthetic */ int lambda$applyChanges$0(Comparator comparator, DocumentViewChange documentViewChange, DocumentViewChange documentViewChange2) {
        int iCompare = Integer.compare(changeTypeOrder(documentViewChange), changeTypeOrder(documentViewChange2));
        return iCompare != 0 ? iCompare : comparator.compare(documentViewChange.getDocument(), documentViewChange2.getDocument());
    }

    public ViewChange applyOnlineStateChange(OnlineState onlineState) {
        if (this.current && onlineState == OnlineState.OFFLINE) {
            this.current = false;
            return applyChanges(new DocumentChanges(this.documentSet, new DocumentViewChangeSet(), this.mutatedKeys, false, null));
        }
        return new ViewChange(null, Collections.emptyList());
    }

    private void applyTargetChange(TargetChange targetChange) {
        if (targetChange != null) {
            Iterator<DocumentKey> it = targetChange.getAddedDocuments().iterator();
            while (it.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.insert(it.next());
            }
            for (DocumentKey documentKey : targetChange.getModifiedDocuments()) {
                Assert.hardAssert(this.syncedDocuments.contains(documentKey), "Modified document %s not found in view.", documentKey);
            }
            Iterator<DocumentKey> it2 = targetChange.getRemovedDocuments().iterator();
            while (it2.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.remove(it2.next());
            }
            this.current = targetChange.isCurrent();
        }
    }

    private List<LimboDocumentChange> updateLimboDocuments() {
        if (!this.current) {
            return Collections.emptyList();
        }
        ImmutableSortedSet<DocumentKey> immutableSortedSet = this.limboDocuments;
        this.limboDocuments = DocumentKey.emptyKeySet();
        for (Document document : this.documentSet) {
            if (shouldBeLimboDoc(document.getKey())) {
                this.limboDocuments = this.limboDocuments.insert(document.getKey());
            }
        }
        ArrayList arrayList = new ArrayList(immutableSortedSet.size() + this.limboDocuments.size());
        for (DocumentKey documentKey : immutableSortedSet) {
            if (!this.limboDocuments.contains(documentKey)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.REMOVED, documentKey));
            }
        }
        for (DocumentKey documentKey2 : this.limboDocuments) {
            if (!immutableSortedSet.contains(documentKey2)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.ADDED, documentKey2));
            }
        }
        return arrayList;
    }

    private boolean shouldBeLimboDoc(DocumentKey documentKey) {
        Document document;
        return (this.syncedDocuments.contains(documentKey) || (document = this.documentSet.getDocument(documentKey)) == null || document.hasLocalMutations()) ? false : true;
    }

    ImmutableSortedSet<DocumentKey> getLimboDocuments() {
        return this.limboDocuments;
    }

    ImmutableSortedSet<DocumentKey> getSyncedDocuments() {
        return this.syncedDocuments;
    }

    /* JADX INFO: renamed from: com.google.firebase.firestore.core.View$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type;

        static {
            int[] iArr = new int[DocumentViewChange.Type.values().length];
            $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type = iArr;
            try {
                iArr[DocumentViewChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.METADATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.REMOVED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static int changeTypeOrder(DocumentViewChange documentViewChange) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[documentViewChange.getType().ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2 && i != 3) {
                if (i == 4) {
                    return 0;
                }
                throw new IllegalArgumentException("Unknown change type: " + documentViewChange.getType());
            }
        }
        return i2;
    }

    private static Long getLimit(QueryOrPipeline queryOrPipeline) {
        if (queryOrPipeline.isPipeline()) {
            if (PipelineUtilKt.getLastEffectiveLimit(queryOrPipeline.pipeline$com_google_firebase_firebase_firestore()) == null) {
                return null;
            }
            return Long.valueOf(r2.intValue());
        }
        Query query = queryOrPipeline.query();
        if (query.hasLimit()) {
            return Long.valueOf(query.getLimit());
        }
        return null;
    }

    private static Query.LimitType getLimitType(QueryOrPipeline queryOrPipeline) {
        if (queryOrPipeline.isPipeline()) {
            Long limit = getLimit(queryOrPipeline);
            return (limit == null || limit.longValue() <= 0) ? Query.LimitType.LIMIT_TO_LAST : Query.LimitType.LIMIT_TO_FIRST;
        }
        return queryOrPipeline.query().getLimitType();
    }

    private static LimitEdges getLimitEdges(QueryOrPipeline queryOrPipeline, DocumentSet documentSet) {
        Long limit = getLimit(queryOrPipeline);
        if (limit == null) {
            return new LimitEdges(null, null);
        }
        if (!queryOrPipeline.isPipeline()) {
            Query query = queryOrPipeline.query();
            if (query.getLimitType() == Query.LimitType.LIMIT_TO_FIRST && documentSet.size() == query.getLimit()) {
                return new LimitEdges(documentSet.getLastDocument(), null);
            }
            if (query.getLimitType() == Query.LimitType.LIMIT_TO_LAST && documentSet.size() == query.getLimit()) {
                return new LimitEdges(null, documentSet.getFirstDocument());
            }
        } else {
            if (limit.longValue() > 0 && documentSet.size() == limit.longValue()) {
                return new LimitEdges(documentSet.getLastDocument(), null);
            }
            if (limit.longValue() < 0 && documentSet.size() == (-limit.longValue())) {
                return new LimitEdges(null, documentSet.getFirstDocument());
            }
        }
        return new LimitEdges(null, null);
    }
}
