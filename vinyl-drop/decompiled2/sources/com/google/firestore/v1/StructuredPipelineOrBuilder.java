package com.google.firestore.v1;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface StructuredPipelineOrBuilder extends MessageLiteOrBuilder {
    boolean containsOptions(String str);

    @Deprecated
    Map<String, Value> getOptions();

    int getOptionsCount();

    Map<String, Value> getOptionsMap();

    Value getOptionsOrDefault(String str, Value value);

    Value getOptionsOrThrow(String str);

    Pipeline getPipeline();

    boolean hasPipeline();
}
