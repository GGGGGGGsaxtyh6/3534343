package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;

/* JADX INFO: loaded from: classes3.dex */
public interface ClientStreamListener extends StreamListener {

    public enum RpcProgress {
        PROCESSED,
        REFUSED,
        DROPPED,
        MISCARRIED
    }

    void closed(Status status, RpcProgress rpcProgress, Metadata metadata);

    void headersRead(Metadata metadata);
}
