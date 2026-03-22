package io.grpc.internal;

/* JADX INFO: loaded from: classes3.dex */
public interface ServerListener {
    void serverShutdown();

    ServerTransportListener transportCreated(ServerTransport serverTransport);
}
