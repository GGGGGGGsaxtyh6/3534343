package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes3.dex */
public interface ExprOrBuilder extends MessageLiteOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getExpression();

    ByteString getExpressionBytes();

    String getLocation();

    ByteString getLocationBytes();

    String getTitle();

    ByteString getTitleBytes();
}
