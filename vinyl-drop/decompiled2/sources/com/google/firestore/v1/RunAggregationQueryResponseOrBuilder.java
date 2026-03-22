package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* JADX INFO: loaded from: classes3.dex */
public interface RunAggregationQueryResponseOrBuilder extends MessageLiteOrBuilder {
    Timestamp getReadTime();

    AggregationResult getResult();

    ByteString getTransaction();

    boolean hasReadTime();

    boolean hasResult();
}
