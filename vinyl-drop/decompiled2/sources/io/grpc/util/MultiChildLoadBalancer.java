package io.grpc.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.Status;
import io.grpc.internal.PickFirstLoadBalancerProvider;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public abstract class MultiChildLoadBalancer extends LoadBalancer {
    private static final Logger logger = Logger.getLogger(MultiChildLoadBalancer.class.getName());
    protected ConnectivityState currentConnectivityState;
    private final LoadBalancer.Helper helper;
    protected boolean resolvingAddresses;
    private final Map<Object, ChildLbState> childLbStates = new LinkedHashMap();
    protected final LoadBalancerProvider pickFirstLbProvider = new PickFirstLoadBalancerProvider();

    protected abstract LoadBalancer.SubchannelPicker getSubchannelPicker(Map<Object, LoadBalancer.SubchannelPicker> map);

    protected boolean reactivateChildOnReuse() {
        return true;
    }

    protected boolean reconnectOnIdle() {
        return true;
    }

    protected MultiChildLoadBalancer(LoadBalancer.Helper helper) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
        logger.log(Level.FINE, "Created");
    }

    protected LoadBalancer.SubchannelPicker getInitialPicker() {
        return new LoadBalancer.FixedResultPicker(LoadBalancer.PickResult.withNoResult());
    }

    protected LoadBalancer.SubchannelPicker getErrorPicker(Status status) {
        return new LoadBalancer.FixedResultPicker(LoadBalancer.PickResult.withError(status));
    }

    protected ImmutableMap<Object, ChildLbState> getImmutableChildMap() {
        return ImmutableMap.copyOf((Map) this.childLbStates);
    }

    protected Collection<ChildLbState> getChildLbStates() {
        return this.childLbStates.values();
    }

    protected ChildLbState getChildLbState(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof EquivalentAddressGroup) {
            obj = new Endpoint((EquivalentAddressGroup) obj);
        }
        return this.childLbStates.get(obj);
    }

    protected ChildLbState getChildLbStateEag(EquivalentAddressGroup equivalentAddressGroup) {
        return getChildLbState(new Endpoint(equivalentAddressGroup));
    }

    protected Map<Object, ChildLbState> createChildLbMap(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        HashMap map = new HashMap();
        Iterator<EquivalentAddressGroup> it = resolvedAddresses.getAddresses().iterator();
        while (it.hasNext()) {
            Endpoint endpoint = new Endpoint(it.next());
            ChildLbState childLbState = this.childLbStates.get(endpoint);
            if (childLbState != null) {
                map.put(endpoint, childLbState);
            } else {
                map.put(endpoint, createChildLbState(endpoint, null, getInitialPicker(), resolvedAddresses));
            }
        }
        return map;
    }

    protected ChildLbState createChildLbState(Object obj, Object obj2, LoadBalancer.SubchannelPicker subchannelPicker, LoadBalancer.ResolvedAddresses resolvedAddresses) {
        return new ChildLbState(this, obj, this.pickFirstLbProvider, obj2, subchannelPicker);
    }

    @Override // io.grpc.LoadBalancer
    public Status acceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        try {
            this.resolvingAddresses = true;
            AcceptResolvedAddressRetVal acceptResolvedAddressRetValAcceptResolvedAddressesInternal = acceptResolvedAddressesInternal(resolvedAddresses);
            if (!acceptResolvedAddressRetValAcceptResolvedAddressesInternal.status.isOk()) {
                return acceptResolvedAddressRetValAcceptResolvedAddressesInternal.status;
            }
            updateOverallBalancingState();
            shutdownRemoved(acceptResolvedAddressRetValAcceptResolvedAddressesInternal.removedChildren);
            return acceptResolvedAddressRetValAcceptResolvedAddressesInternal.status;
        } finally {
            this.resolvingAddresses = false;
        }
    }

    protected LoadBalancer.ResolvedAddresses getChildAddresses(Object obj, LoadBalancer.ResolvedAddresses resolvedAddresses, Object obj2) {
        Endpoint endpoint;
        EquivalentAddressGroup next;
        if (obj instanceof EquivalentAddressGroup) {
            endpoint = new Endpoint((EquivalentAddressGroup) obj);
        } else {
            Preconditions.checkArgument(obj instanceof Endpoint, "key is wrong type");
            endpoint = (Endpoint) obj;
        }
        Iterator<EquivalentAddressGroup> it = resolvedAddresses.getAddresses().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (endpoint.equals(new Endpoint(next))) {
                break;
            }
        }
        Preconditions.checkNotNull(next, obj + " no longer present in load balancer children");
        return resolvedAddresses.toBuilder().setAddresses(Collections.singletonList(next)).setAttributes(Attributes.newBuilder().set(IS_PETIOLE_POLICY, true).build()).setLoadBalancingPolicyConfig(obj2).build();
    }

    protected AcceptResolvedAddressRetVal acceptResolvedAddressesInternal(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        logger.log(Level.FINE, "Received resolution result: {0}", resolvedAddresses);
        Map<Object, ChildLbState> mapCreateChildLbMap = createChildLbMap(resolvedAddresses);
        if (mapCreateChildLbMap.isEmpty()) {
            Status statusWithDescription = Status.UNAVAILABLE.withDescription("NameResolver returned no usable address. " + resolvedAddresses);
            handleNameResolutionError(statusWithDescription);
            return new AcceptResolvedAddressRetVal(statusWithDescription, null);
        }
        for (Map.Entry<Object, ChildLbState> entry : mapCreateChildLbMap.entrySet()) {
            Object key = entry.getKey();
            LoadBalancerProvider policyProvider = entry.getValue().getPolicyProvider();
            Object config = entry.getValue().getConfig();
            boolean zContainsKey = this.childLbStates.containsKey(key);
            Map<Object, ChildLbState> map = this.childLbStates;
            if (!zContainsKey) {
                map.put(key, entry.getValue());
            } else {
                ChildLbState childLbState = map.get(key);
                if (childLbState.isDeactivated() && reactivateChildOnReuse()) {
                    childLbState.reactivate(policyProvider);
                }
            }
            ChildLbState childLbState2 = this.childLbStates.get(key);
            LoadBalancer.ResolvedAddresses childAddresses = getChildAddresses(key, resolvedAddresses, config);
            this.childLbStates.get(key).setResolvedAddresses(childAddresses);
            if (!childLbState2.deactivated) {
                childLbState2.lb.handleResolvedAddresses(childAddresses);
            }
        }
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator it = ImmutableList.copyOf((Collection) this.childLbStates.keySet()).iterator();
        while (it.hasNext()) {
            E next = it.next();
            if (!mapCreateChildLbMap.containsKey(next)) {
                ChildLbState childLbState3 = this.childLbStates.get(next);
                childLbState3.deactivate();
                arrayList.add(childLbState3);
            }
        }
        return new AcceptResolvedAddressRetVal(Status.OK, arrayList);
    }

    protected void shutdownRemoved(List<ChildLbState> list) {
        Iterator<ChildLbState> it = list.iterator();
        while (it.hasNext()) {
            it.next().shutdown();
        }
    }

    @Override // io.grpc.LoadBalancer
    public void handleNameResolutionError(Status status) {
        if (this.currentConnectivityState != ConnectivityState.READY) {
            this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, getErrorPicker(status));
        }
    }

    protected void handleNameResolutionError(ChildLbState childLbState, Status status) {
        childLbState.lb.handleNameResolutionError(status);
    }

    @Override // io.grpc.LoadBalancer
    public void shutdown() {
        logger.log(Level.FINE, "Shutdown");
        Iterator<ChildLbState> it = this.childLbStates.values().iterator();
        while (it.hasNext()) {
            it.next().shutdown();
        }
        this.childLbStates.clear();
    }

    protected void updateOverallBalancingState() {
        HashMap map = new HashMap();
        ConnectivityState connectivityStateAggregateState = null;
        for (ChildLbState childLbState : getChildLbStates()) {
            if (!childLbState.deactivated) {
                map.put(childLbState.key, childLbState.currentPicker);
                connectivityStateAggregateState = aggregateState(connectivityStateAggregateState, childLbState.currentState);
            }
        }
        if (connectivityStateAggregateState != null) {
            this.helper.updateBalancingState(connectivityStateAggregateState, getSubchannelPicker(map));
            this.currentConnectivityState = connectivityStateAggregateState;
        }
    }

    @Nullable
    protected static ConnectivityState aggregateState(@Nullable ConnectivityState connectivityState, ConnectivityState connectivityState2) {
        if (connectivityState == null) {
            return connectivityState2;
        }
        if (connectivityState == ConnectivityState.READY || connectivityState2 == ConnectivityState.READY) {
            return ConnectivityState.READY;
        }
        if (connectivityState == ConnectivityState.CONNECTING || connectivityState2 == ConnectivityState.CONNECTING) {
            return ConnectivityState.CONNECTING;
        }
        return (connectivityState == ConnectivityState.IDLE || connectivityState2 == ConnectivityState.IDLE) ? ConnectivityState.IDLE : connectivityState;
    }

    protected LoadBalancer.Helper getHelper() {
        return this.helper;
    }

    protected void removeChild(Object obj) {
        this.childLbStates.remove(obj);
    }

    protected List<ChildLbState> getReadyChildren() {
        ArrayList arrayList = new ArrayList();
        for (ChildLbState childLbState : getChildLbStates()) {
            if (!childLbState.isDeactivated() && childLbState.getCurrentState() == ConnectivityState.READY) {
                arrayList.add(childLbState);
            }
        }
        return arrayList;
    }

    public class ChildLbState {
        private final Object config;
        private LoadBalancer.SubchannelPicker currentPicker;
        private ConnectivityState currentState;
        private boolean deactivated;
        private final Object key;
        private final GracefulSwitchLoadBalancer lb;
        private final LoadBalancerProvider policyProvider;
        private LoadBalancer.ResolvedAddresses resolvedAddresses;

        public ChildLbState(MultiChildLoadBalancer multiChildLoadBalancer, Object obj, LoadBalancerProvider loadBalancerProvider, Object obj2, LoadBalancer.SubchannelPicker subchannelPicker) {
            this(obj, loadBalancerProvider, obj2, subchannelPicker, null, false);
        }

        public ChildLbState(Object obj, LoadBalancerProvider loadBalancerProvider, Object obj2, LoadBalancer.SubchannelPicker subchannelPicker, LoadBalancer.ResolvedAddresses resolvedAddresses, boolean z) {
            this.key = obj;
            this.policyProvider = loadBalancerProvider;
            this.deactivated = z;
            this.currentPicker = subchannelPicker;
            this.config = obj2;
            GracefulSwitchLoadBalancer gracefulSwitchLoadBalancer = new GracefulSwitchLoadBalancer(new ChildLbStateHelper());
            this.lb = gracefulSwitchLoadBalancer;
            this.currentState = z ? ConnectivityState.IDLE : ConnectivityState.CONNECTING;
            this.resolvedAddresses = resolvedAddresses;
            if (z) {
                return;
            }
            gracefulSwitchLoadBalancer.switchTo(loadBalancerProvider);
        }

        public String toString() {
            return "Address = " + this.key + ", state = " + this.currentState + ", picker type: " + this.currentPicker.getClass() + ", lb: " + this.lb.delegate().getClass() + (this.deactivated ? ", deactivated" : "");
        }

        public Object getKey() {
            return this.key;
        }

        Object getConfig() {
            return this.config;
        }

        protected GracefulSwitchLoadBalancer getLb() {
            return this.lb;
        }

        public LoadBalancerProvider getPolicyProvider() {
            return this.policyProvider;
        }

        protected LoadBalancer.Subchannel getSubchannels(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            if (getCurrentPicker() == null) {
                return null;
            }
            return getCurrentPicker().pickSubchannel(pickSubchannelArgs).getSubchannel();
        }

        public ConnectivityState getCurrentState() {
            return this.currentState;
        }

        public LoadBalancer.SubchannelPicker getCurrentPicker() {
            return this.currentPicker;
        }

        public EquivalentAddressGroup getEag() {
            LoadBalancer.ResolvedAddresses resolvedAddresses = this.resolvedAddresses;
            if (resolvedAddresses == null || resolvedAddresses.getAddresses().isEmpty()) {
                return null;
            }
            return this.resolvedAddresses.getAddresses().get(0);
        }

        public boolean isDeactivated() {
            return this.deactivated;
        }

        protected void setDeactivated() {
            this.deactivated = true;
        }

        protected void markReactivated() {
            this.deactivated = false;
        }

        protected void setResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
            Preconditions.checkNotNull(resolvedAddresses, "Missing address list for child");
            this.resolvedAddresses = resolvedAddresses;
        }

        protected void deactivate() {
            if (this.deactivated) {
                return;
            }
            MultiChildLoadBalancer.this.childLbStates.remove(this.key);
            this.deactivated = true;
            MultiChildLoadBalancer.logger.log(Level.FINE, "Child balancer {0} deactivated", this.key);
        }

        protected void reactivate(LoadBalancerProvider loadBalancerProvider) {
            this.deactivated = false;
        }

        protected void shutdown() {
            this.lb.shutdown();
            this.currentState = ConnectivityState.SHUTDOWN;
            MultiChildLoadBalancer.logger.log(Level.FINE, "Child balancer {0} deleted", this.key);
        }

        public LoadBalancer.ResolvedAddresses getResolvedAddresses() {
            return this.resolvedAddresses;
        }

        private final class ChildLbStateHelper extends ForwardingLoadBalancerHelper {
            private ChildLbStateHelper() {
            }

            @Override // io.grpc.util.ForwardingLoadBalancerHelper, io.grpc.LoadBalancer.Helper
            public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
                if (MultiChildLoadBalancer.this.childLbStates.containsKey(ChildLbState.this.key)) {
                    ChildLbState.this.currentState = connectivityState;
                    ChildLbState.this.currentPicker = subchannelPicker;
                    if (ChildLbState.this.deactivated || MultiChildLoadBalancer.this.resolvingAddresses) {
                        return;
                    }
                    if (connectivityState == ConnectivityState.IDLE && MultiChildLoadBalancer.this.reconnectOnIdle()) {
                        ChildLbState.this.lb.requestConnection();
                    }
                    MultiChildLoadBalancer.this.updateOverallBalancingState();
                }
            }

            @Override // io.grpc.util.ForwardingLoadBalancerHelper
            protected LoadBalancer.Helper delegate() {
                return MultiChildLoadBalancer.this.helper;
            }
        }
    }

    protected static class Endpoint {
        final String[] addrs;
        final int hashCode;

        public Endpoint(EquivalentAddressGroup equivalentAddressGroup) {
            Preconditions.checkNotNull(equivalentAddressGroup, "eag");
            this.addrs = new String[equivalentAddressGroup.getAddresses().size()];
            Iterator<SocketAddress> it = equivalentAddressGroup.getAddresses().iterator();
            int i = 0;
            while (it.hasNext()) {
                this.addrs[i] = it.next().toString();
                i++;
            }
            Arrays.sort(this.addrs);
            this.hashCode = Arrays.hashCode(this.addrs);
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Endpoint)) {
                return false;
            }
            Endpoint endpoint = (Endpoint) obj;
            if (endpoint.hashCode == this.hashCode) {
                String[] strArr = endpoint.addrs;
                int length = strArr.length;
                String[] strArr2 = this.addrs;
                if (length == strArr2.length) {
                    return Arrays.equals(strArr, strArr2);
                }
            }
            return false;
        }

        public String toString() {
            return Arrays.toString(this.addrs);
        }
    }

    protected static class AcceptResolvedAddressRetVal {
        public final List<ChildLbState> removedChildren;
        public final Status status;

        public AcceptResolvedAddressRetVal(Status status, List<ChildLbState> list) {
            this.status = status;
            this.removedChildren = list;
        }
    }
}
