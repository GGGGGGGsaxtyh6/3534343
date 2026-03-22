package io.grpc.internal;

import io.grpc.SynchronizationContext;
import io.grpc.internal.BackoffPolicy;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
final class BackoffPolicyRetryScheduler implements RetryScheduler {
    private static final Logger logger = Logger.getLogger(BackoffPolicyRetryScheduler.class.getName());
    private BackoffPolicy policy;
    private final BackoffPolicy.Provider policyProvider;
    private final ScheduledExecutorService scheduledExecutorService;
    private SynchronizationContext.ScheduledHandle scheduledHandle;
    private final SynchronizationContext syncContext;

    BackoffPolicyRetryScheduler(BackoffPolicy.Provider provider, ScheduledExecutorService scheduledExecutorService, SynchronizationContext synchronizationContext) {
        this.policyProvider = provider;
        this.scheduledExecutorService = scheduledExecutorService;
        this.syncContext = synchronizationContext;
    }

    @Override // io.grpc.internal.RetryScheduler
    public void schedule(Runnable runnable) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.policy == null) {
            this.policy = this.policyProvider.get();
        }
        SynchronizationContext.ScheduledHandle scheduledHandle = this.scheduledHandle;
        if (scheduledHandle == null || !scheduledHandle.isPending()) {
            long jNextBackoffNanos = this.policy.nextBackoffNanos();
            this.scheduledHandle = this.syncContext.schedule(runnable, jNextBackoffNanos, TimeUnit.NANOSECONDS, this.scheduledExecutorService);
            logger.log(Level.FINE, "Scheduling DNS resolution backoff for {0}ns", Long.valueOf(jNextBackoffNanos));
        }
    }

    @Override // io.grpc.internal.RetryScheduler
    public void reset() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.BackoffPolicyRetryScheduler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m761lambda$reset$0$iogrpcinternalBackoffPolicyRetryScheduler();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$reset$0$io-grpc-internal-BackoffPolicyRetryScheduler, reason: not valid java name */
    /* synthetic */ void m761lambda$reset$0$iogrpcinternalBackoffPolicyRetryScheduler() {
        SynchronizationContext.ScheduledHandle scheduledHandle = this.scheduledHandle;
        if (scheduledHandle != null && scheduledHandle.isPending()) {
            this.scheduledHandle.cancel();
        }
        this.policy = null;
    }
}
