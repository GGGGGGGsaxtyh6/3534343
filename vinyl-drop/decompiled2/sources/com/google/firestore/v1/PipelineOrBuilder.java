package com.google.firestore.v1;

import com.google.firestore.v1.Pipeline;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface PipelineOrBuilder extends MessageLiteOrBuilder {
    Pipeline.Stage getStages(int i);

    int getStagesCount();

    List<Pipeline.Stage> getStagesList();
}
