package io.grpc.util;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;

/* JADX INFO: loaded from: classes3.dex */
public final class HealthProducerHelper extends ForwardingLoadBalancerHelper {
    private final LoadBalancer.Helper delegate;

    public HealthProducerHelper(LoadBalancer.Helper helper) {
        this.delegate = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
    }

    @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
    public LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
        LoadBalancer.SubchannelStateListener subchannelStateListener = (LoadBalancer.SubchannelStateListener) createSubchannelArgs.getOption(LoadBalancer.HEALTH_CONSUMER_LISTENER_ARG_KEY);
        LoadBalancer.Subchannel subchannelCreateSubchannel = super.createSubchannel(createSubchannelArgs);
        return (subchannelStateListener == null || subchannelCreateSubchannel.getAttributes().get(LoadBalancer.HAS_HEALTH_PRODUCER_LISTENER_KEY) != null) ? subchannelCreateSubchannel : new HealthProducerSubchannel(subchannelCreateSubchannel, subchannelStateListener);
    }

    @Override // io.grpc.util.ForwardingLoadBalancerHelper
    protected LoadBalancer.Helper delegate() {
        return this.delegate;
    }

    static final class HealthProducerSubchannel extends ForwardingSubchannel {
        private final LoadBalancer.Subchannel delegate;
        private final LoadBalancer.SubchannelStateListener healthListener;

        HealthProducerSubchannel(LoadBalancer.Subchannel subchannel, LoadBalancer.SubchannelStateListener subchannelStateListener) {
            this.delegate = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel, "delegate");
            this.healthListener = (LoadBalancer.SubchannelStateListener) Preconditions.checkNotNull(subchannelStateListener, "healthListener");
        }

        @Override // io.grpc.util.ForwardingSubchannel
        public LoadBalancer.Subchannel delegate() {
            return this.delegate;
        }

        @Override // io.grpc.util.ForwardingSubchannel, io.grpc.LoadBalancer.Subchannel
        public void start(final LoadBalancer.SubchannelStateListener subchannelStateListener) {
            this.delegate.start(new LoadBalancer.SubchannelStateListener() { // from class: io.grpc.util.HealthProducerHelper.HealthProducerSubchannel.1
                @Override // io.grpc.LoadBalancer.SubchannelStateListener
                public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
                    subchannelStateListener.onSubchannelState(connectivityStateInfo);
                    HealthProducerSubchannel.this.healthListener.onSubchannelState(connectivityStateInfo);
                }
            });
        }

        @Override // io.grpc.util.ForwardingSubchannel, io.grpc.LoadBalancer.Subchannel
        public Attributes getAttributes() {
            return super.getAttributes().toBuilder().set(LoadBalancer.HAS_HEALTH_PRODUCER_LISTENER_KEY, Boolean.TRUE).build();
        }
    }
}
