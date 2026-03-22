package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ExecutePipelineResponseOrBuilder extends MessageLiteOrBuilder {
    Timestamp getExecutionTime();

    Document getResults(int i);

    int getResultsCount();

    List<Document> getResultsList();

    ByteString getTransaction();

    boolean hasExecutionTime();
}
