package com.google.protobuf;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface FieldMaskOrBuilder extends MessageLiteOrBuilder {
    String getPaths(int index);

    ByteString getPathsBytes(int index);

    int getPathsCount();

    List<String> getPathsList();
}
