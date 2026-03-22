package io.grpc.okhttp;

import io.grpc.ServerCredentials;
import io.grpc.ServerProvider;
import io.grpc.okhttp.OkHttpServerBuilder;
import java.net.InetSocketAddress;

/* JADX INFO: loaded from: classes3.dex */
public final class OkHttpServerProvider extends ServerProvider {
    @Override // io.grpc.ServerProvider
    protected boolean isAvailable() {
        return true;
    }

    @Override // io.grpc.ServerProvider
    protected int priority() {
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.ServerProvider
    public OkHttpServerBuilder builderForPort(int i) {
        throw new UnsupportedOperationException("Use Grpc.newServerBuilderForPort() instead");
    }

    @Override // io.grpc.ServerProvider
    protected ServerProvider.NewServerBuilderResult newServerBuilderForPort(int i, ServerCredentials serverCredentials) {
        OkHttpServerBuilder.HandshakerSocketFactoryResult handshakerSocketFactoryResultHandshakerSocketFactoryFrom = OkHttpServerBuilder.handshakerSocketFactoryFrom(serverCredentials);
        if (handshakerSocketFactoryResultHandshakerSocketFactoryFrom.error != null) {
            return ServerProvider.NewServerBuilderResult.error(handshakerSocketFactoryResultHandshakerSocketFactoryFrom.error);
        }
        return ServerProvider.NewServerBuilderResult.serverBuilder(new OkHttpServerBuilder(new InetSocketAddress(i), handshakerSocketFactoryResultHandshakerSocketFactoryFrom.factory));
    }
}
