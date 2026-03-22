package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.PipelineUtilKt;
import com.google.firebase.firestore.core.QueryOrPipeline;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.proto.MaybeDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
final class SQLiteRemoteDocumentCache implements RemoteDocumentCache {
    static final int BINDS_PER_STATEMENT = 9;
    private final SQLitePersistence db;
    private final DocumentTypeBackfiller documentTypeBackfiller = new DocumentTypeBackfiller();
    private IndexManager indexManager;
    private final LocalSerializer serializer;

    SQLiteRemoteDocumentCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void setIndexManager(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    private enum DocumentType {
        NO_DOCUMENT(1),
        FOUND_DOCUMENT(2),
        UNKNOWN_DOCUMENT(3),
        INVALID_DOCUMENT(4);

        final int dbValue;

        DocumentType(int i) {
            this.dbValue = i;
        }

        static DocumentType forMutableDocument(MutableDocument mutableDocument) {
            if (mutableDocument.isNoDocument()) {
                return NO_DOCUMENT;
            }
            if (mutableDocument.isFoundDocument()) {
                return FOUND_DOCUMENT;
            }
            if (mutableDocument.isUnknownDocument()) {
                return UNKNOWN_DOCUMENT;
            }
            Assert.hardAssert(!mutableDocument.isValidDocument(), "MutableDocument has an unknown type", new Object[0]);
            return INVALID_DOCUMENT;
        }
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void add(MutableDocument mutableDocument, SnapshotVersion snapshotVersion) {
        Assert.hardAssert(!snapshotVersion.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        DocumentKey key = mutableDocument.getKey();
        Timestamp timestamp = snapshotVersion.getTimestamp();
        this.db.execute("INSERT OR REPLACE INTO remote_documents (path, path_length, read_time_seconds, read_time_nanos, document_type, contents) VALUES (?, ?, ?, ?, ?, ?)", EncodedPath.encode(key.getPath()), Integer.valueOf(key.getPath().length()), Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanoseconds()), Integer.valueOf(DocumentType.forMutableDocument(mutableDocument).dbValue), this.serializer.encodeMaybeDocument(mutableDocument).toByteArray());
        this.indexManager.addToCollectionParentIndex(mutableDocument.getKey().getCollectionPath());
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void removeAll(Collection<DocumentKey> collection) {
        if (collection.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapEmptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (DocumentKey documentKey : collection) {
            arrayList.add(EncodedPath.encode(documentKey.getPath()));
            immutableSortedMapEmptyDocumentMap = immutableSortedMapEmptyDocumentMap.insert(documentKey, MutableDocument.newNoDocument(documentKey, SnapshotVersion.NONE));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "DELETE FROM remote_documents WHERE path IN (", arrayList, ")");
        while (longQuery.hasMoreSubqueries()) {
            longQuery.executeNextSubquery();
        }
        this.indexManager.updateIndexEntries(immutableSortedMapEmptyDocumentMap);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public MutableDocument get(DocumentKey documentKey) {
        return getAll(Collections.singletonList(documentKey)).get(documentKey);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> iterable) {
        final HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (DocumentKey documentKey : iterable) {
            arrayList.add(EncodedPath.encode(documentKey.getPath()));
            map.put(documentKey, MutableDocument.newInvalidDocument(documentKey));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT contents, read_time_seconds, read_time_nanos, document_type, path FROM remote_documents WHERE path IN (", arrayList, ") ORDER BY path");
        final BackgroundQueue backgroundQueue = new BackgroundQueue();
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda3
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.m693xca14d1c3(backgroundQueue, map, (Cursor) obj);
                }
            });
        }
        backgroundQueue.drain();
        this.documentTypeBackfiller.backfill(this.db);
        synchronized (map) {
        }
        return map;
    }

    /* JADX INFO: renamed from: lambda$getAll$0$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache, reason: not valid java name */
    /* synthetic */ void m693xca14d1c3(BackgroundQueue backgroundQueue, Map map, Cursor cursor) {
        m694xbda45604(backgroundQueue, map, cursor, null);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(String str, FieldIndex.IndexOffset indexOffset, int i) {
        List<ResourcePath> collectionParents = this.indexManager.getCollectionParents(str);
        ArrayList arrayList = new ArrayList(collectionParents.size());
        Iterator<ResourcePath> it = collectionParents.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().append(str));
        }
        if (arrayList.isEmpty()) {
            return Collections.emptyMap();
        }
        if (arrayList.size() * 9 < 900) {
            return getAll(arrayList, indexOffset, i, null);
        }
        HashMap map = new HashMap();
        int i2 = 0;
        while (i2 < arrayList.size()) {
            int i3 = i2 + 100;
            map.putAll(getAll(arrayList.subList(i2, Math.min(arrayList.size(), i3)), indexOffset, i, null));
            i2 = i3;
        }
        return Util.firstNEntries(map, i, FieldIndex.IndexOffset.DOCUMENT_COMPARATOR);
    }

    private Map<DocumentKey, MutableDocument> getAll(List<ResourcePath> list, FieldIndex.IndexOffset indexOffset, int i, @Nullable DocumentType documentType, @Nullable final Function<MutableDocument, Boolean> function, @Nullable QueryContext queryContext) {
        String str;
        Timestamp timestamp = indexOffset.getReadTime().getTimestamp();
        DocumentKey documentKey = indexOffset.getDocumentKey();
        StringBuilder sb = new StringBuilder("SELECT contents, read_time_seconds, read_time_nanos, document_type, path FROM remote_documents WHERE path >= ? AND path < ? AND path_length = ? ");
        if (documentType == null) {
            str = "";
        } else {
            str = " AND (document_type IS NULL OR document_type = ?) ";
        }
        StringBuilder sbRepeatSequence = Util.repeatSequence(sb.append(str).append("AND (read_time_seconds > ? OR ( read_time_seconds = ? AND read_time_nanos > ?) OR ( read_time_seconds = ? AND read_time_nanos = ? and path > ?)) ").toString(), list.size(), " UNION ");
        sbRepeatSequence.append("ORDER BY read_time_seconds, read_time_nanos, path LIMIT ?");
        int i2 = 0;
        Object[] objArr = new Object[(((documentType != null ? 1 : 0) + 9) * list.size()) + 1];
        for (ResourcePath resourcePath : list) {
            String strEncode = EncodedPath.encode(resourcePath);
            objArr[i2] = strEncode;
            objArr[i2 + 1] = EncodedPath.prefixSuccessor(strEncode);
            int i3 = i2 + 3;
            objArr[i2 + 2] = Integer.valueOf(resourcePath.length() + 1);
            if (documentType != null) {
                objArr[i3] = Integer.valueOf(documentType.dbValue);
                i3 = i2 + 4;
            }
            objArr[i3] = Long.valueOf(timestamp.getSeconds());
            objArr[i3 + 1] = Long.valueOf(timestamp.getSeconds());
            objArr[i3 + 2] = Integer.valueOf(timestamp.getNanoseconds());
            objArr[i3 + 3] = Long.valueOf(timestamp.getSeconds());
            objArr[i3 + 4] = Integer.valueOf(timestamp.getNanoseconds());
            objArr[i3 + 5] = EncodedPath.encode(documentKey.getPath());
            i2 = i3 + 6;
        }
        objArr[i2] = Integer.valueOf(i);
        final BackgroundQueue backgroundQueue = new BackgroundQueue();
        final HashMap map = new HashMap();
        int iForEach = this.db.query(sbRepeatSequence.toString()).binding(objArr).forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f$0.m694xbda45604(backgroundQueue, map, function, (Cursor) obj);
            }
        });
        if (queryContext != null) {
            queryContext.incrementDocumentReadCount(iForEach);
        }
        backgroundQueue.drain();
        this.documentTypeBackfiller.backfill(this.db);
        synchronized (map) {
        }
        return map;
    }

    private Map<DocumentKey, MutableDocument> getAll(List<ResourcePath> list, FieldIndex.IndexOffset indexOffset, int i, @Nullable Function<MutableDocument, Boolean> function) {
        return getAll(list, indexOffset, i, null, function, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: processRowInBackground, reason: merged with bridge method [inline-methods] */
    public void m694xbda45604(BackgroundQueue backgroundQueue, final Map<DocumentKey, MutableDocument> map, Cursor cursor, @Nullable final Function<MutableDocument, Boolean> function) {
        final byte[] blob = cursor.getBlob(0);
        final int i = cursor.getInt(1);
        final int i2 = cursor.getInt(2);
        final boolean zIsNull = cursor.isNull(3);
        final String string = cursor.getString(4);
        Runnable runnable = new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m695x16ef698(blob, i, i2, zIsNull, string, function, map);
            }
        };
        if (cursor.isFirst() && cursor.isLast()) {
            runnable.run();
        } else {
            backgroundQueue.submit(runnable);
        }
    }

    /* JADX INFO: renamed from: lambda$processRowInBackground$2$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache, reason: not valid java name */
    /* synthetic */ void m695x16ef698(byte[] bArr, int i, int i2, boolean z, String str, Function function, Map map) {
        MutableDocument mutableDocumentDecodeMaybeDocument = decodeMaybeDocument(bArr, i, i2);
        if (z) {
            this.documentTypeBackfiller.enqueue(str, i, i2, mutableDocumentDecodeMaybeDocument);
        }
        if (function == null || ((Boolean) function.apply(mutableDocumentDecodeMaybeDocument)).booleanValue()) {
            synchronized (map) {
                map.put(mutableDocumentDecodeMaybeDocument.getKey(), mutableDocumentDecodeMaybeDocument);
            }
        }
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(QueryOrPipeline queryOrPipeline, FieldIndex.IndexOffset indexOffset, @Nonnull Set<DocumentKey> set) {
        return getDocumentsMatchingQuery(queryOrPipeline, indexOffset, set, null);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(final QueryOrPipeline queryOrPipeline, FieldIndex.IndexOffset indexOffset, @Nonnull final Set<DocumentKey> set, @Nullable QueryContext queryContext) {
        ResourcePath resourcePathFromString;
        ResourcePath resourcePath = ResourcePath.EMPTY;
        if (queryOrPipeline.isQuery()) {
            resourcePathFromString = queryOrPipeline.query().getPath();
        } else {
            String pipelineCollection = PipelineUtilKt.getPipelineCollection(queryOrPipeline.pipeline$com_google_firebase_firebase_firestore());
            Assert.hardAssert(pipelineCollection != null, "SQLiteRemoteDocumentCache.getDocumentsMatchingQuery receives pipeline without collection source.", new Object[0]);
            resourcePathFromString = ResourcePath.fromString(pipelineCollection);
        }
        return getAll(Collections.singletonList(resourcePathFromString), indexOffset, Integer.MAX_VALUE, DocumentType.FOUND_DOCUMENT, new Function() { // from class: com.google.firebase.firestore.local.SQLiteRemoteDocumentCache$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                MutableDocument mutableDocument = (MutableDocument) obj;
                return Boolean.valueOf(queryOrPipeline.matches(mutableDocument) || set.contains(mutableDocument.getKey()));
            }
        }, queryContext);
    }

    private MutableDocument decodeMaybeDocument(byte[] bArr, int i, int i2) {
        try {
            return this.serializer.decodeMaybeDocument(MaybeDocument.parseFrom(bArr)).setReadTime(new SnapshotVersion(new Timestamp(i, i2)));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("MaybeDocument failed to parse: %s", e);
        }
    }

    private static class DocumentTypeBackfiller {
        private final ConcurrentHashMap<BackfillKey, DocumentType> documentTypeByBackfillKey;

        private DocumentTypeBackfiller() {
            this.documentTypeByBackfillKey = new ConcurrentHashMap<>();
        }

        void enqueue(String str, int i, int i2, MutableDocument mutableDocument) {
            this.documentTypeByBackfillKey.putIfAbsent(new BackfillKey(str, i, i2), DocumentType.forMutableDocument(mutableDocument));
        }

        void backfill(SQLitePersistence sQLitePersistence) {
            while (true) {
                BackfillSqlInfo backfillSqlInfoCalculateBackfillSql = calculateBackfillSql();
                if (backfillSqlInfoCalculateBackfillSql == null) {
                    return;
                } else {
                    sQLitePersistence.execute(backfillSqlInfoCalculateBackfillSql.sql, backfillSqlInfoCalculateBackfillSql.bindings);
                }
            }
        }

        private static class BackfillSqlInfo {
            final Object[] bindings;
            final int numDocumentsAffected;
            final String sql;

            BackfillSqlInfo(String str, Object[] objArr, int i) {
                this.sql = str;
                this.bindings = objArr;
                this.numDocumentsAffected = i;
            }
        }

        @Nullable
        BackfillSqlInfo calculateBackfillSql() {
            if (this.documentTypeByBackfillKey.isEmpty()) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            Iterator<BackfillKey> it = this.documentTypeByBackfillKey.keySet().iterator();
            int i = 0;
            while (it.hasNext() && arrayList.size() < 900) {
                BackfillKey next = it.next();
                DocumentType documentTypeRemove = this.documentTypeByBackfillKey.remove(next);
                if (documentTypeRemove != null) {
                    i++;
                    arrayList.add(next.path);
                    int size = arrayList.size();
                    arrayList.add(Integer.valueOf(next.readTimeSeconds));
                    int size2 = arrayList.size();
                    arrayList.add(Integer.valueOf(next.readTimeNanos));
                    int size3 = arrayList.size();
                    arrayList.add(Integer.valueOf(documentTypeRemove.dbValue));
                    sb.append(" WHEN path=?").append(size).append(" AND read_time_seconds=?").append(size2).append(" AND read_time_nanos=?").append(size3).append(" THEN ?").append(arrayList.size());
                    if (sb2.length() > 0) {
                        sb2.append(" OR");
                    }
                    sb2.append(" (path=?").append(size).append(" AND read_time_seconds=?").append(size2).append(" AND read_time_nanos=?").append(size3).append(')');
                }
            }
            if (i == 0) {
                return null;
            }
            return new BackfillSqlInfo("UPDATE remote_documents SET document_type = CASE" + ((Object) sb) + " ELSE NULL END WHERE" + ((Object) sb2), arrayList.toArray(), i);
        }

        private static class BackfillKey {
            final String path;
            final int readTimeNanos;
            final int readTimeSeconds;

            BackfillKey(String str, int i, int i2) {
                this.path = str;
                this.readTimeSeconds = i;
                this.readTimeNanos = i2;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof BackfillKey)) {
                    return false;
                }
                BackfillKey backfillKey = (BackfillKey) obj;
                return this.readTimeSeconds == backfillKey.readTimeSeconds && this.readTimeNanos == backfillKey.readTimeNanos && Objects.equals(this.path, backfillKey.path);
            }

            public int hashCode() {
                return Objects.hash(this.path, Integer.valueOf(this.readTimeSeconds), Integer.valueOf(this.readTimeNanos));
            }

            public String toString() {
                return "DocumentTypeBackfiller.BackfillKey(path=" + this.path + ", readTimeSeconds=" + this.readTimeSeconds + ", readTimeNanos=" + this.readTimeNanos + ")";
            }
        }
    }
}
