package com.google.firebase.firestore.remote;

import io.grpc.Metadata;
import io.grpc.Status;

/* JADX INFO: loaded from: classes3.dex */
interface IncomingStreamObserver<RespT> {
    void onClose(Status status);

    void onHeaders(Metadata metadata);

    void onNext(RespT respt);

    void onOpen();
}
