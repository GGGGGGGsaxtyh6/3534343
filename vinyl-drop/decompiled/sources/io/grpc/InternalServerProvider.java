package io.grpc;

import io.grpc.ServerProvider;

/* JADX INFO: loaded from: classes3.dex */
public final class InternalServerProvider {
    private InternalServerProvider() {
    }

    public static ServerBuilder<?> builderForPort(ServerProvider serverProvider, int i) {
        return serverProvider.builderForPort(i);
    }

    public static ServerProvider.NewServerBuilderResult newServerBuilderForPort(ServerProvider serverProvider, int i, ServerCredentials serverCredentials) {
        return serverProvider.newServerBuilderForPort(i, serverCredentials);
    }
}
