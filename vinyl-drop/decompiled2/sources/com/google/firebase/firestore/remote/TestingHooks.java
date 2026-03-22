package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.remote.WatchChangeAggregator;
import com.google.firebase.firestore.util.Preconditions;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
final class TestingHooks {
    private static final TestingHooks instance = new TestingHooks();
    private final CopyOnWriteArrayList<AtomicReference<ExistenceFilterMismatchListener>> existenceFilterMismatchListeners = new CopyOnWriteArrayList<>();

    interface ExistenceFilterMismatchListener {
        void onExistenceFilterMismatch(ExistenceFilterMismatchInfo existenceFilterMismatchInfo);
    }

    private TestingHooks() {
    }

    static TestingHooks getInstance() {
        return instance;
    }

    void notifyOnExistenceFilterMismatch(ExistenceFilterMismatchInfo existenceFilterMismatchInfo) {
        Iterator<AtomicReference<ExistenceFilterMismatchListener>> it = this.existenceFilterMismatchListeners.iterator();
        while (it.hasNext()) {
            ExistenceFilterMismatchListener existenceFilterMismatchListener = it.next().get();
            if (existenceFilterMismatchListener != null) {
                existenceFilterMismatchListener.onExistenceFilterMismatch(existenceFilterMismatchInfo);
            }
        }
    }

    ListenerRegistration addExistenceFilterMismatchListener(ExistenceFilterMismatchListener existenceFilterMismatchListener) {
        Preconditions.checkNotNull(existenceFilterMismatchListener, "a null listener is not allowed");
        final AtomicReference<ExistenceFilterMismatchListener> atomicReference = new AtomicReference<>(existenceFilterMismatchListener);
        this.existenceFilterMismatchListeners.add(atomicReference);
        return new ListenerRegistration() { // from class: com.google.firebase.firestore.remote.TestingHooks$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.ListenerRegistration
            public final void remove() {
                this.f$0.m743xc9d8f707(atomicReference);
            }
        };
    }

    /* JADX INFO: renamed from: lambda$addExistenceFilterMismatchListener$0$com-google-firebase-firestore-remote-TestingHooks, reason: not valid java name */
    /* synthetic */ void m743xc9d8f707(AtomicReference atomicReference) {
        atomicReference.set(null);
        this.existenceFilterMismatchListeners.remove(atomicReference);
    }

    static abstract class ExistenceFilterMismatchInfo {
        abstract ExistenceFilterBloomFilterInfo bloomFilter();

        abstract String databaseId();

        abstract int existenceFilterCount();

        abstract int localCacheCount();

        abstract String projectId();

        ExistenceFilterMismatchInfo() {
        }

        static ExistenceFilterMismatchInfo create(int i, int i2, String str, String str2, ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo) {
            return new AutoValue_TestingHooks_ExistenceFilterMismatchInfo(i, i2, str, str2, existenceFilterBloomFilterInfo);
        }

        static ExistenceFilterMismatchInfo from(int i, ExistenceFilter existenceFilter, DatabaseId databaseId, BloomFilter bloomFilter, WatchChangeAggregator.BloomFilterApplicationStatus bloomFilterApplicationStatus) {
            return create(i, existenceFilter.getCount(), databaseId.getProjectId(), databaseId.getDatabaseId(), ExistenceFilterBloomFilterInfo.from(bloomFilter, bloomFilterApplicationStatus, existenceFilter));
        }
    }

    static abstract class ExistenceFilterBloomFilterInfo {
        abstract boolean applied();

        abstract int bitmapLength();

        abstract BloomFilter bloomFilter();

        abstract int hashCount();

        abstract int padding();

        ExistenceFilterBloomFilterInfo() {
        }

        static ExistenceFilterBloomFilterInfo create(BloomFilter bloomFilter, boolean z, int i, int i2, int i3) {
            return new AutoValue_TestingHooks_ExistenceFilterBloomFilterInfo(bloomFilter, z, i, i2, i3);
        }

        static ExistenceFilterBloomFilterInfo from(BloomFilter bloomFilter, WatchChangeAggregator.BloomFilterApplicationStatus bloomFilterApplicationStatus, ExistenceFilter existenceFilter) {
            com.google.firestore.v1.BloomFilter unchangedNames = existenceFilter.getUnchangedNames();
            if (unchangedNames == null) {
                return null;
            }
            return create(bloomFilter, bloomFilterApplicationStatus == WatchChangeAggregator.BloomFilterApplicationStatus.SUCCESS, unchangedNames.getHashCount(), unchangedNames.getBits().getBitmap().size(), unchangedNames.getBits().getPadding());
        }
    }
}
