package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.QueryOrPipeline;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.IndexManager;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public class QueryEngine {
    private static final int DEFAULT_INDEX_AUTO_CREATION_MIN_COLLECTION_SIZE = 100;
    private static final double DEFAULT_RELATIVE_INDEX_READ_COST_PER_DOCUMENT = 2.0d;
    private static final String LOG_TAG = "QueryEngine";
    private IndexManager indexManager;
    private boolean initialized;
    private LocalDocumentsView localDocumentsView;
    private boolean indexAutoCreationEnabled = false;
    private int indexAutoCreationMinCollectionSize = 100;
    private double relativeIndexReadCostPerDocument = DEFAULT_RELATIVE_INDEX_READ_COST_PER_DOCUMENT;

    public void initialize(LocalDocumentsView localDocumentsView, IndexManager indexManager) {
        this.localDocumentsView = localDocumentsView;
        this.indexManager = indexManager;
        this.initialized = true;
    }

    public void setIndexAutoCreationEnabled(boolean z) {
        this.indexAutoCreationEnabled = z;
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(QueryOrPipeline queryOrPipeline, SnapshotVersion snapshotVersion, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        Assert.hardAssert(this.initialized, "initialize() not called", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapPerformQueryUsingIndex = performQueryUsingIndex(queryOrPipeline);
        if (immutableSortedMapPerformQueryUsingIndex != null) {
            return immutableSortedMapPerformQueryUsingIndex;
        }
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapPerformQueryUsingRemoteKeys = performQueryUsingRemoteKeys(queryOrPipeline, immutableSortedSet, snapshotVersion);
        if (immutableSortedMapPerformQueryUsingRemoteKeys != null) {
            return immutableSortedMapPerformQueryUsingRemoteKeys;
        }
        QueryContext queryContext = new QueryContext();
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapExecuteFullCollectionScan = executeFullCollectionScan(queryOrPipeline, queryContext);
        if (immutableSortedMapExecuteFullCollectionScan != null && this.indexAutoCreationEnabled) {
            createCacheIndexes(queryOrPipeline, queryContext, immutableSortedMapExecuteFullCollectionScan.size());
        }
        return immutableSortedMapExecuteFullCollectionScan;
    }

    private void createCacheIndexes(QueryOrPipeline queryOrPipeline, QueryContext queryContext, int i) {
        if (queryOrPipeline.isPipeline()) {
            Logger.debug(LOG_TAG, "SDK will skip creating cache indexes for pipelines.", new Object[0]);
            return;
        }
        if (queryContext.getDocumentReadCount() < this.indexAutoCreationMinCollectionSize) {
            Logger.debug(LOG_TAG, "SDK will not create cache indexes for query: %s, since it only creates cache indexes for collection contains more than or equal to %s documents.", queryOrPipeline.toString(), Integer.valueOf(this.indexAutoCreationMinCollectionSize));
            return;
        }
        Logger.debug(LOG_TAG, "Query: %s, scans %s local documents and returns %s documents as results.", queryOrPipeline.toString(), Integer.valueOf(queryContext.getDocumentReadCount()), Integer.valueOf(i));
        if (queryContext.getDocumentReadCount() > this.relativeIndexReadCostPerDocument * ((double) i)) {
            this.indexManager.createTargetIndexes(queryOrPipeline.query().toTarget());
            Logger.debug(LOG_TAG, "The SDK decides to create cache indexes for query: %s, as using cache indexes may help improve performance.", queryOrPipeline.toString());
        }
    }

    @Nullable
    private ImmutableSortedMap<DocumentKey, Document> performQueryUsingIndex(QueryOrPipeline queryOrPipeline) {
        if (queryOrPipeline.isPipeline()) {
            Logger.debug(LOG_TAG, "Skipping using indexes for pipelines.", new Object[0]);
            return null;
        }
        if (queryOrPipeline.query().matchesAllDocuments()) {
            return null;
        }
        Target target = queryOrPipeline.query().toTarget();
        IndexManager.IndexType indexType = this.indexManager.getIndexType(target);
        if (indexType.equals(IndexManager.IndexType.NONE)) {
            return null;
        }
        if (queryOrPipeline.query().hasLimit() && indexType.equals(IndexManager.IndexType.PARTIAL)) {
            return performQueryUsingIndex(new QueryOrPipeline.QueryWrapper(queryOrPipeline.query().limitToFirst(-1L)));
        }
        List<DocumentKey> documentsMatchingTarget = this.indexManager.getDocumentsMatchingTarget(target);
        Assert.hardAssert(documentsMatchingTarget != null, "index manager must return results for partial and full indexes.", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> documents = this.localDocumentsView.getDocuments(documentsMatchingTarget);
        FieldIndex.IndexOffset minOffset = this.indexManager.getMinOffset(target);
        ImmutableSortedSet<Document> immutableSortedSetApplyQuery = applyQuery(queryOrPipeline, documents);
        if (needsRefill(queryOrPipeline, documentsMatchingTarget.size(), immutableSortedSetApplyQuery, minOffset.getReadTime())) {
            return performQueryUsingIndex(new QueryOrPipeline.QueryWrapper(queryOrPipeline.query().limitToFirst(-1L)));
        }
        return appendRemainingResults(immutableSortedSetApplyQuery, queryOrPipeline, minOffset);
    }

    @Nullable
    private ImmutableSortedMap<DocumentKey, Document> performQueryUsingRemoteKeys(QueryOrPipeline queryOrPipeline, ImmutableSortedSet<DocumentKey> immutableSortedSet, SnapshotVersion snapshotVersion) {
        if (queryOrPipeline.matchesAllDocuments() || snapshotVersion.equals(SnapshotVersion.NONE)) {
            return null;
        }
        ImmutableSortedSet<Document> immutableSortedSetApplyQuery = applyQuery(queryOrPipeline, this.localDocumentsView.getDocuments(immutableSortedSet));
        if (needsRefill(queryOrPipeline, immutableSortedSet.size(), immutableSortedSetApplyQuery, snapshotVersion)) {
            return null;
        }
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Re-using previous result from %s to execute query: %s", snapshotVersion.toString(), queryOrPipeline.toString());
        }
        return appendRemainingResults(immutableSortedSetApplyQuery, queryOrPipeline, FieldIndex.IndexOffset.createSuccessor(snapshotVersion, -1));
    }

    private ImmutableSortedSet<Document> applyQuery(QueryOrPipeline queryOrPipeline, ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        ImmutableSortedSet<Document> immutableSortedSet = new ImmutableSortedSet<>(Collections.emptyList(), queryOrPipeline.comparator());
        Iterator<Map.Entry<DocumentKey, Document>> it = immutableSortedMap.iterator();
        while (it.hasNext()) {
            Document value = it.next().getValue();
            if (queryOrPipeline.matches(value)) {
                immutableSortedSet = immutableSortedSet.insert(value);
            }
        }
        return immutableSortedSet;
    }

    private boolean needsRefill(QueryOrPipeline queryOrPipeline, int i, ImmutableSortedSet<Document> immutableSortedSet, SnapshotVersion snapshotVersion) {
        Document minEntry;
        if (queryOrPipeline.isPipeline()) {
            return queryOrPipeline.hasLimit();
        }
        if (!queryOrPipeline.query().hasLimit()) {
            return false;
        }
        if (i != immutableSortedSet.size()) {
            return true;
        }
        if (queryOrPipeline.query().getLimitType() == Query.LimitType.LIMIT_TO_FIRST) {
            minEntry = immutableSortedSet.getMaxEntry();
        } else {
            minEntry = immutableSortedSet.getMinEntry();
        }
        if (minEntry == null) {
            return false;
        }
        return minEntry.hasPendingWrites() || minEntry.getVersion().compareTo(snapshotVersion) > 0;
    }

    private ImmutableSortedMap<DocumentKey, Document> executeFullCollectionScan(QueryOrPipeline queryOrPipeline, QueryContext queryContext) {
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Using full collection scan to execute query: %s", queryOrPipeline.toString());
        }
        return this.localDocumentsView.getDocumentsMatchingQuery(queryOrPipeline, FieldIndex.IndexOffset.NONE, queryContext);
    }

    private ImmutableSortedMap<DocumentKey, Document> appendRemainingResults(Iterable<Document> iterable, QueryOrPipeline queryOrPipeline, FieldIndex.IndexOffset indexOffset) {
        ImmutableSortedMap<DocumentKey, Document> documentsMatchingQuery = this.localDocumentsView.getDocumentsMatchingQuery(queryOrPipeline, indexOffset);
        for (Document document : iterable) {
            documentsMatchingQuery = documentsMatchingQuery.insert(document.getKey(), document);
        }
        return documentsMatchingQuery;
    }

    void setIndexAutoCreationMinCollectionSize(int i) {
        this.indexAutoCreationMinCollectionSize = i;
    }

    void setRelativeIndexReadCostPerDocument(double d) {
        this.relativeIndexReadCostPerDocument = d;
    }
}
