package io.grpc.internal;

import com.google.common.base.Strings;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.PickFirstLoadBalancer;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class PickFirstLoadBalancerProvider extends LoadBalancerProvider {
    private static final String SHUFFLE_ADDRESS_LIST_KEY = "shuffleAddressList";
    static boolean enableNewPickFirst;

    @Override // io.grpc.LoadBalancerProvider
    public int getPriority() {
        return 5;
    }

    @Override // io.grpc.LoadBalancerProvider
    public boolean isAvailable() {
        return true;
    }

    static {
        enableNewPickFirst = !Strings.isNullOrEmpty(System.getenv("GRPC_EXPERIMENTAL_ENABLE_NEW_PICK_FIRST")) && Boolean.parseBoolean(System.getenv("GRPC_EXPERIMENTAL_ENABLE_NEW_PICK_FIRST"));
    }

    @Override // io.grpc.LoadBalancerProvider
    public String getPolicyName() {
        return GrpcUtil.DEFAULT_LB_POLICY;
    }

    @Override // io.grpc.LoadBalancer.Factory
    public LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        if (enableNewPickFirst) {
            return new PickFirstLeafLoadBalancer(helper);
        }
        return new PickFirstLoadBalancer(helper);
    }

    @Override // io.grpc.LoadBalancerProvider
    public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        try {
            return NameResolver.ConfigOrError.fromConfig(new PickFirstLoadBalancer.PickFirstLoadBalancerConfig(JsonUtil.getBoolean(map, SHUFFLE_ADDRESS_LIST_KEY)));
        } catch (RuntimeException e) {
            return NameResolver.ConfigOrError.fromError(Status.UNAVAILABLE.withCause(e).withDescription("Failed parsing configuration for " + getPolicyName()));
        }
    }
}
