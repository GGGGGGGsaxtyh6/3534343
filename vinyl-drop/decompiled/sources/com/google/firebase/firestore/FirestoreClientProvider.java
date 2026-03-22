package com.google.firebase.firestore;

import androidx.core.util.Consumer;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
final class FirestoreClientProvider {
    private AsyncQueue asyncQueue = new AsyncQueue();
    private FirestoreClient client;
    private final Function<AsyncQueue, FirestoreClient> clientFactory;

    FirestoreClientProvider(Function<AsyncQueue, FirestoreClient> function) {
        this.clientFactory = function;
    }

    boolean isConfigured() {
        return this.client != null;
    }

    synchronized void ensureConfigured() {
        if (!isConfigured()) {
            this.client = this.clientFactory.apply(this.asyncQueue);
        }
    }

    synchronized <T> T call(Function<FirestoreClient, T> function) {
        ensureConfigured();
        return function.apply(this.client);
    }

    synchronized void procedure(Consumer<FirestoreClient> consumer) {
        ensureConfigured();
        consumer.accept(this.client);
    }

    synchronized <T> T executeIfShutdown(Function<Executor, T> function, Function<Executor, T> function2) {
        Executor executor = new Executor() { // from class: com.google.firebase.firestore.FirestoreClientProvider$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                this.f$0.m609x1755525a(runnable);
            }
        };
        FirestoreClient firestoreClient = this.client;
        if (firestoreClient != null && !firestoreClient.isTerminated()) {
            return function2.apply(executor);
        }
        return function.apply(executor);
    }

    /* JADX INFO: renamed from: lambda$executeIfShutdown$0$com-google-firebase-firestore-FirestoreClientProvider, reason: not valid java name */
    /* synthetic */ void m609x1755525a(Runnable runnable) {
        this.asyncQueue.enqueueAndForgetEvenAfterShutdown(runnable);
    }

    synchronized Task<Void> terminate() {
        Task<Void> taskTerminate;
        ensureConfigured();
        taskTerminate = this.client.terminate();
        this.asyncQueue.shutdown();
        return taskTerminate;
    }

    AsyncQueue getAsyncQueue() {
        return this.asyncQueue;
    }
}
