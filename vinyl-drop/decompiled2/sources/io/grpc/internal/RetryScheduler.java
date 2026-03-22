package io.grpc.internal;

/* JADX INFO: loaded from: classes3.dex */
public interface RetryScheduler {
    void reset();

    void schedule(Runnable runnable);
}
