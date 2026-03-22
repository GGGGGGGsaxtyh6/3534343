package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.RealtimePipeline;
import com.google.firebase.firestore.core.PipelineSourceType;
import com.google.firebase.firestore.core.PipelineUtilKt;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.QueryOrPipeline;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Function;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes3.dex */
class LocalDocumentsView {
    private final DocumentOverlayCache documentOverlayCache;
    private final IndexManager indexManager;
    private final MutationQueue mutationQueue;
    private final RemoteDocumentCache remoteDocumentCache;

    LocalDocumentsView(RemoteDocumentCache remoteDocumentCache, MutationQueue mutationQueue, DocumentOverlayCache documentOverlayCache, IndexManager indexManager) {
        this.remoteDocumentCache = remoteDocumentCache;
        this.mutationQueue = mutationQueue;
        this.documentOverlayCache = documentOverlayCache;
        this.indexManager = indexManager;
    }

    RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    MutationQueue getMutationQueue() {
        return this.mutationQueue;
    }

    DocumentOverlayCache getDocumentOverlayCache() {
        return this.documentOverlayCache;
    }

    Document getDocument(DocumentKey documentKey) {
        Overlay overlay = this.documentOverlayCache.getOverlay(documentKey);
        MutableDocument baseDocument = getBaseDocument(documentKey, overlay);
        if (overlay != null) {
            overlay.getMutation().applyToLocalView(baseDocument, FieldMask.EMPTY, Timestamp.now());
        }
        return baseDocument;
    }

    ImmutableSortedMap<DocumentKey, Document> getDocuments(Iterable<DocumentKey> iterable) {
        return getLocalViewOfDocuments(this.remoteDocumentCache.getAll(iterable), new HashSet());
    }

    ImmutableSortedMap<DocumentKey, Document> getLocalViewOfDocuments(Map<DocumentKey, MutableDocument> map, Set<DocumentKey> set) {
        HashMap map2 = new HashMap();
        populateOverlays(map2, map.keySet());
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapEmptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (Map.Entry<DocumentKey, OverlayedDocument> entry : computeViews(map, map2, set).entrySet()) {
            immutableSortedMapEmptyDocumentMap = immutableSortedMapEmptyDocumentMap.insert(entry.getKey(), entry.getValue().getDocument());
        }
        return immutableSortedMapEmptyDocumentMap;
    }

    Map<DocumentKey, OverlayedDocument> getOverlayedDocuments(Map<DocumentKey, MutableDocument> map) {
        HashMap map2 = new HashMap();
        populateOverlays(map2, map.keySet());
        return computeViews(map, map2, new HashSet());
    }

    private Map<DocumentKey, OverlayedDocument> computeViews(Map<DocumentKey, MutableDocument> map, Map<DocumentKey, Overlay> map2, Set<DocumentKey> set) {
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        for (MutableDocument mutableDocument : map.values()) {
            Overlay overlay = map2.get(mutableDocument.getKey());
            if (set.contains(mutableDocument.getKey()) && (overlay == null || (overlay.getMutation() instanceof PatchMutation))) {
                map3.put(mutableDocument.getKey(), mutableDocument);
            } else if (overlay != null) {
                map4.put(mutableDocument.getKey(), overlay.getMutation().getFieldMask());
                overlay.getMutation().applyToLocalView(mutableDocument, overlay.getMutation().getFieldMask(), Timestamp.now());
            } else {
                map4.put(mutableDocument.getKey(), FieldMask.EMPTY);
            }
        }
        map4.putAll(recalculateAndSaveOverlays(map3));
        HashMap map5 = new HashMap();
        for (Map.Entry<DocumentKey, MutableDocument> entry : map.entrySet()) {
            map5.put(entry.getKey(), new OverlayedDocument(entry.getValue(), (FieldMask) map4.get(entry.getKey())));
        }
        return map5;
    }

    private Map<DocumentKey, FieldMask> recalculateAndSaveOverlays(Map<DocumentKey, MutableDocument> map) {
        List<MutationBatch> allMutationBatchesAffectingDocumentKeys = this.mutationQueue.getAllMutationBatchesAffectingDocumentKeys(map.keySet());
        HashMap map2 = new HashMap();
        TreeMap treeMap = new TreeMap();
        for (MutationBatch mutationBatch : allMutationBatchesAffectingDocumentKeys) {
            for (DocumentKey documentKey : mutationBatch.getKeys()) {
                MutableDocument mutableDocument = map.get(documentKey);
                if (mutableDocument != null) {
                    map2.put(documentKey, mutationBatch.applyToLocalView(mutableDocument, map2.containsKey(documentKey) ? (FieldMask) map2.get(documentKey) : FieldMask.EMPTY));
                    int batchId = mutationBatch.getBatchId();
                    if (!treeMap.containsKey(Integer.valueOf(batchId))) {
                        treeMap.put(Integer.valueOf(batchId), new HashSet());
                    }
                    ((Set) treeMap.get(Integer.valueOf(batchId))).add(documentKey);
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (Map.Entry entry : treeMap.descendingMap().entrySet()) {
            HashMap map3 = new HashMap();
            for (DocumentKey documentKey2 : (Set) entry.getValue()) {
                if (!hashSet.contains(documentKey2)) {
                    Mutation mutationCalculateOverlayMutation = Mutation.calculateOverlayMutation(map.get(documentKey2), (FieldMask) map2.get(documentKey2));
                    if (mutationCalculateOverlayMutation != null) {
                        map3.put(documentKey2, mutationCalculateOverlayMutation);
                    }
                    hashSet.add(documentKey2);
                }
            }
            this.documentOverlayCache.saveOverlays(((Integer) entry.getKey()).intValue(), map3);
        }
        return map2;
    }

    void recalculateAndSaveOverlays(Set<DocumentKey> set) {
        recalculateAndSaveOverlays(this.remoteDocumentCache.getAll(set));
    }

    ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(QueryOrPipeline queryOrPipeline, FieldIndex.IndexOffset indexOffset, QueryContext queryContext) {
        if (queryOrPipeline.isQuery()) {
            if (queryOrPipeline.query().isDocumentQuery()) {
                return getDocumentsMatchingDocumentQuery(queryOrPipeline.query().getPath());
            }
            if (queryOrPipeline.query().isCollectionGroupQuery()) {
                return getDocumentsMatchingCollectionGroupQuery(queryOrPipeline.query(), indexOffset, queryContext);
            }
            return getDocumentsMatchingCollectionQuery(queryOrPipeline.query(), indexOffset, queryContext);
        }
        return getDocumentsMatchingPipeline(queryOrPipeline, indexOffset, queryContext);
    }

    ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(QueryOrPipeline queryOrPipeline, FieldIndex.IndexOffset indexOffset) {
        return getDocumentsMatchingQuery(queryOrPipeline, indexOffset, null);
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingDocumentQuery(ResourcePath resourcePath) {
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapEmptyDocumentMap = DocumentCollections.emptyDocumentMap();
        Document document = getDocument(DocumentKey.fromPath(resourcePath));
        return document.isFoundDocument() ? immutableSortedMapEmptyDocumentMap.insert(document.getKey(), document) : immutableSortedMapEmptyDocumentMap;
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionGroupQuery(Query query, FieldIndex.IndexOffset indexOffset, QueryContext queryContext) {
        Assert.hardAssert(query.getPath().isEmpty(), "Currently we only support collection group queries at the root.", new Object[0]);
        String collectionGroup = query.getCollectionGroup();
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapEmptyDocumentMap = DocumentCollections.emptyDocumentMap();
        Iterator<ResourcePath> it = this.indexManager.getCollectionParents(collectionGroup).iterator();
        while (it.hasNext()) {
            for (Map.Entry<DocumentKey, Document> entry : getDocumentsMatchingCollectionQuery(query.asCollectionQueryAtPath(it.next().append(collectionGroup)), indexOffset, queryContext)) {
                immutableSortedMapEmptyDocumentMap = immutableSortedMapEmptyDocumentMap.insert(entry.getKey(), entry.getValue());
            }
        }
        return immutableSortedMapEmptyDocumentMap;
    }

    LocalDocumentsResult getNextDocuments(String str, FieldIndex.IndexOffset indexOffset, int i) {
        Map<DocumentKey, Overlay> map;
        Map<DocumentKey, MutableDocument> all = this.remoteDocumentCache.getAll(str, indexOffset, i);
        if (i - all.size() > 0) {
            map = this.documentOverlayCache.getOverlays(str, indexOffset.getLargestBatchId(), i - all.size());
        } else {
            map = new HashMap<>();
        }
        int iMax = -1;
        for (Overlay overlay : map.values()) {
            if (!all.containsKey(overlay.getKey())) {
                all.put(overlay.getKey(), getBaseDocument(overlay.getKey(), overlay));
            }
            iMax = Math.max(iMax, overlay.getLargestBatchId());
        }
        populateOverlays(map, all.keySet());
        return LocalDocumentsResult.fromOverlayedDocuments(iMax, computeViews(all, map, Collections.emptySet()));
    }

    private void populateOverlays(Map<DocumentKey, Overlay> map, Set<DocumentKey> set) {
        TreeSet treeSet = new TreeSet();
        for (DocumentKey documentKey : set) {
            if (!map.containsKey(documentKey)) {
                treeSet.add(documentKey);
            }
        }
        map.putAll(this.documentOverlayCache.getOverlays(treeSet));
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionQuery(final Query query, FieldIndex.IndexOffset indexOffset, QueryContext queryContext) {
        Map<DocumentKey, Overlay> overlays = this.documentOverlayCache.getOverlays(query.getPath(), indexOffset.getLargestBatchId());
        Map<DocumentKey, MutableDocument> documentsMatchingQuery = this.remoteDocumentCache.getDocumentsMatchingQuery(new QueryOrPipeline.QueryWrapper(query), indexOffset, overlays.keySet(), queryContext);
        Objects.requireNonNull(query);
        return retrieveMatchingLocalDocuments(overlays, documentsMatchingQuery, new Function() { // from class: com.google.firebase.firestore.local.LocalDocumentsView$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(query.matches((Document) obj));
            }
        });
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingPipeline(QueryOrPipeline queryOrPipeline, FieldIndex.IndexOffset indexOffset, QueryContext queryContext) {
        Map<DocumentKey, MutableDocument> documentsMatchingQuery;
        final RealtimePipeline realtimePipelinePipeline$com_google_firebase_firebase_firestore = queryOrPipeline.pipeline$com_google_firebase_firebase_firestore();
        if (PipelineUtilKt.getPipelineSourceType(realtimePipelinePipeline$com_google_firebase_firebase_firestore) == PipelineSourceType.COLLECTION_GROUP) {
            String pipelineCollectionGroup = PipelineUtilKt.getPipelineCollectionGroup(realtimePipelinePipeline$com_google_firebase_firebase_firestore);
            Assert.hardAssert(pipelineCollectionGroup != null, "Pipeline source type is COLLECTION_GROUP but is missing", new Object[0]);
            ImmutableSortedMap<DocumentKey, Document> immutableSortedMapEmptyDocumentMap = DocumentCollections.emptyDocumentMap();
            Iterator<ResourcePath> it = this.indexManager.getCollectionParents(pipelineCollectionGroup).iterator();
            while (it.hasNext()) {
                for (Map.Entry<DocumentKey, Document> entry : getDocumentsMatchingPipeline(new QueryOrPipeline.PipelineWrapper(PipelineUtilKt.asCollectionPipelineAtPath(realtimePipelinePipeline$com_google_firebase_firebase_firestore, it.next().append(pipelineCollectionGroup))), indexOffset, queryContext)) {
                    immutableSortedMapEmptyDocumentMap = immutableSortedMapEmptyDocumentMap.insert(entry.getKey(), entry.getValue());
                }
            }
            return immutableSortedMapEmptyDocumentMap;
        }
        Map<DocumentKey, Overlay> overlaysForPipeline = getOverlaysForPipeline(realtimePipelinePipeline$com_google_firebase_firebase_firestore, indexOffset.getLargestBatchId());
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$PipelineSourceType[PipelineUtilKt.getPipelineSourceType(realtimePipelinePipeline$com_google_firebase_firebase_firestore).ordinal()];
        if (i == 1) {
            documentsMatchingQuery = this.remoteDocumentCache.getDocumentsMatchingQuery(queryOrPipeline, indexOffset, overlaysForPipeline.keySet(), queryContext);
        } else if (i == 2) {
            List listAsList = Arrays.asList(PipelineUtilKt.getPipelineDocuments(realtimePipelinePipeline$com_google_firebase_firebase_firestore));
            HashSet hashSet = new HashSet();
            Iterator it2 = listAsList.iterator();
            while (it2.hasNext()) {
                hashSet.add(DocumentKey.fromPathString((String) it2.next()));
            }
            documentsMatchingQuery = this.remoteDocumentCache.getAll(hashSet);
        } else {
            throw new IllegalArgumentException("Invalid pipeline source to execute offline: " + realtimePipelinePipeline$com_google_firebase_firebase_firestore);
        }
        Objects.requireNonNull(realtimePipelinePipeline$com_google_firebase_firebase_firestore);
        return retrieveMatchingLocalDocuments(overlaysForPipeline, documentsMatchingQuery, new Function() { // from class: com.google.firebase.firestore.local.LocalDocumentsView$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(realtimePipelinePipeline$com_google_firebase_firebase_firestore.matches$com_google_firebase_firebase_firestore((Document) obj));
            }
        });
    }

    /* JADX INFO: renamed from: com.google.firebase.firestore.local.LocalDocumentsView$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$PipelineSourceType;

        static {
            int[] iArr = new int[PipelineSourceType.values().length];
            $SwitchMap$com$google$firebase$firestore$core$PipelineSourceType = iArr;
            try {
                iArr[PipelineSourceType.COLLECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$PipelineSourceType[PipelineSourceType.DOCUMENTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private MutableDocument getBaseDocument(DocumentKey documentKey, Overlay overlay) {
        if (overlay == null || (overlay.getMutation() instanceof PatchMutation)) {
            return this.remoteDocumentCache.get(documentKey);
        }
        return MutableDocument.newInvalidDocument(documentKey);
    }

    private ImmutableSortedMap<DocumentKey, Document> retrieveMatchingLocalDocuments(Map<DocumentKey, Overlay> map, Map<DocumentKey, MutableDocument> map2, Function<Document, Boolean> function) {
        for (Map.Entry<DocumentKey, Overlay> entry : map.entrySet()) {
            if (!map2.containsKey(entry.getKey())) {
                map2.put(entry.getKey(), MutableDocument.newInvalidDocument(entry.getKey()));
            }
        }
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapEmptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (Map.Entry<DocumentKey, MutableDocument> entry2 : map2.entrySet()) {
            Overlay overlay = map.get(entry2.getKey());
            if (overlay != null) {
                overlay.getMutation().applyToLocalView(entry2.getValue(), FieldMask.EMPTY, Timestamp.now());
            }
            if (function.apply(entry2.getValue()).booleanValue()) {
                immutableSortedMapEmptyDocumentMap = immutableSortedMapEmptyDocumentMap.insert(entry2.getKey(), entry2.getValue());
            }
        }
        return immutableSortedMapEmptyDocumentMap;
    }

    private Map<DocumentKey, Overlay> getOverlaysForPipeline(RealtimePipeline realtimePipeline, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$PipelineSourceType[PipelineUtilKt.getPipelineSourceType(realtimePipeline).ordinal()];
        if (i2 == 1) {
            String pipelineCollection = PipelineUtilKt.getPipelineCollection(realtimePipeline);
            Assert.hardAssert(pipelineCollection != null, "Pipeline source type is COLLECTION but is missing", new Object[0]);
            return this.documentOverlayCache.getOverlays(ResourcePath.fromString(pipelineCollection), i);
        }
        if (i2 == 2) {
            List listAsList = Arrays.asList(PipelineUtilKt.getPipelineDocuments(realtimePipeline));
            Assert.hardAssert(listAsList != null, "Pipeline source type is DOCUMENTS but is missing", new Object[0]);
            TreeSet treeSet = new TreeSet();
            Iterator it = listAsList.iterator();
            while (it.hasNext()) {
                treeSet.add(DocumentKey.fromPathString((String) it.next()));
            }
            return this.documentOverlayCache.getOverlays(treeSet);
        }
        throw new IllegalArgumentException("GetOverlaysForPipeline: Unrecognized pipeline source type for pipeline " + realtimePipeline);
    }
}
