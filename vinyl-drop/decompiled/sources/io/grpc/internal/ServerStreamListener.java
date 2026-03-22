package io.grpc.internal;

import io.grpc.Status;

/* JADX INFO: loaded from: classes3.dex */
public interface ServerStreamListener extends StreamListener {
    void closed(Status status);

    void halfClosed();
}
