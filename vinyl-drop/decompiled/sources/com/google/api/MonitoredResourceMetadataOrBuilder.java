package com.google.api;

import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Struct;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface MonitoredResourceMetadataOrBuilder extends MessageLiteOrBuilder {
    boolean containsUserLabels(String str);

    Struct getSystemLabels();

    @Deprecated
    Map<String, String> getUserLabels();

    int getUserLabelsCount();

    Map<String, String> getUserLabelsMap();

    String getUserLabelsOrDefault(String str, String str2);

    String getUserLabelsOrThrow(String str);

    boolean hasSystemLabels();
}
