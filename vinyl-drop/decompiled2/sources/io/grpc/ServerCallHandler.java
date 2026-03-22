package io.grpc;

import io.grpc.ServerCall;

/* JADX INFO: loaded from: classes3.dex */
public interface ServerCallHandler<RequestT, ResponseT> {
    ServerCall.Listener<RequestT> startCall(ServerCall<RequestT, ResponseT> serverCall, Metadata metadata);
}
