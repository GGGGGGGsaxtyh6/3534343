package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.SynchronizationContext;

/* JADX INFO: loaded from: classes3.dex */
final class RetryingNameResolver extends ForwardingNameResolver {
    static final Attributes.Key<ResolutionResultListener> RESOLUTION_RESULT_LISTENER_KEY = Attributes.Key.create("io.grpc.internal.RetryingNameResolver.RESOLUTION_RESULT_LISTENER_KEY");
    private final NameResolver retriedNameResolver;
    private final RetryScheduler retryScheduler;
    private final SynchronizationContext syncContext;

    RetryingNameResolver(NameResolver nameResolver, RetryScheduler retryScheduler, SynchronizationContext synchronizationContext) {
        super(nameResolver);
        this.retriedNameResolver = nameResolver;
        this.retryScheduler = retryScheduler;
        this.syncContext = synchronizationContext;
    }

    @Override // io.grpc.internal.ForwardingNameResolver, io.grpc.NameResolver
    public void start(NameResolver.Listener2 listener2) {
        super.start((NameResolver.Listener2) new RetryingListener(listener2));
    }

    @Override // io.grpc.internal.ForwardingNameResolver, io.grpc.NameResolver
    public void shutdown() {
        super.shutdown();
        this.retryScheduler.reset();
    }

    NameResolver getRetriedNameResolver() {
        return this.retriedNameResolver;
    }

    class DelayedNameResolverRefresh implements Runnable {
        DelayedNameResolverRefresh() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RetryingNameResolver.this.refresh();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class RetryingListener extends NameResolver.Listener2 {
        private NameResolver.Listener2 delegateListener;

        RetryingListener(NameResolver.Listener2 listener2) {
            this.delegateListener = listener2;
        }

        @Override // io.grpc.NameResolver.Listener2
        public void onResult(NameResolver.ResolutionResult resolutionResult) {
            if (resolutionResult.getAttributes().get(RetryingNameResolver.RESOLUTION_RESULT_LISTENER_KEY) != null) {
                throw new IllegalStateException("RetryingNameResolver can only be used once to wrap a NameResolver");
            }
            this.delegateListener.onResult(resolutionResult.toBuilder().setAttributes(resolutionResult.getAttributes().toBuilder().set(RetryingNameResolver.RESOLUTION_RESULT_LISTENER_KEY, RetryingNameResolver.this.new ResolutionResultListener()).build()).build());
        }

        @Override // io.grpc.NameResolver.Listener2, io.grpc.NameResolver.Listener
        public void onError(Status status) {
            this.delegateListener.onError(status);
            RetryingNameResolver.this.syncContext.execute(new Runnable() { // from class: io.grpc.internal.RetryingNameResolver$RetryingListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m763xf904c9eb();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onError$0$io-grpc-internal-RetryingNameResolver$RetryingListener, reason: not valid java name */
        /* synthetic */ void m763xf904c9eb() {
            RetryingNameResolver.this.retryScheduler.schedule(RetryingNameResolver.this.new DelayedNameResolverRefresh());
        }
    }

    class ResolutionResultListener {
        ResolutionResultListener() {
        }

        public void resolutionAttempted(Status status) {
            boolean zIsOk = status.isOk();
            RetryingNameResolver retryingNameResolver = RetryingNameResolver.this;
            if (zIsOk) {
                retryingNameResolver.retryScheduler.reset();
            } else {
                retryingNameResolver.retryScheduler.schedule(RetryingNameResolver.this.new DelayedNameResolverRefresh());
            }
        }
    }
}
