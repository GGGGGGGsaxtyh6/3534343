package com.google.firestore.v1;

import com.google.firestore.v1.ExecutePipelineRequest;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

/* JADX INFO: loaded from: classes3.dex */
public interface ExecutePipelineRequestOrBuilder extends MessageLiteOrBuilder {
    ExecutePipelineRequest.ConsistencySelectorCase getConsistencySelectorCase();

    String getDatabase();

    ByteString getDatabaseBytes();

    TransactionOptions getNewTransaction();

    ExecutePipelineRequest.PipelineTypeCase getPipelineTypeCase();

    Timestamp getReadTime();

    StructuredPipeline getStructuredPipeline();

    ByteString getTransaction();

    boolean hasNewTransaction();

    boolean hasReadTime();

    boolean hasStructuredPipeline();

    boolean hasTransaction();
}
