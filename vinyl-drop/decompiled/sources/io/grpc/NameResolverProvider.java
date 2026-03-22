package io.grpc;

import io.grpc.NameResolver;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: classes3.dex */
public abstract class NameResolverProvider extends NameResolver.Factory {
    protected abstract boolean isAvailable();

    protected abstract int priority();

    protected String getScheme() {
        return getDefaultScheme();
    }

    public Collection<Class<? extends SocketAddress>> getProducedSocketAddressTypes() {
        return Collections.singleton(InetSocketAddress.class);
    }
}
