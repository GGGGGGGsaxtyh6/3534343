package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface FunctionOrBuilder extends MessageLiteOrBuilder {
    boolean containsOptions(String str);

    Value getArgs(int i);

    int getArgsCount();

    List<Value> getArgsList();

    String getName();

    ByteString getNameBytes();

    @Deprecated
    Map<String, Value> getOptions();

    int getOptionsCount();

    Map<String, Value> getOptionsMap();

    Value getOptionsOrDefault(String str, Value value);

    Value getOptionsOrThrow(String str);
}
