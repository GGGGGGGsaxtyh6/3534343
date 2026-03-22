package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface CustomHttpPatternOrBuilder extends MessageLiteOrBuilder {
    String getKind();

    ByteString getKindBytes();

    String getPath();

    ByteString getPathBytes();
}
