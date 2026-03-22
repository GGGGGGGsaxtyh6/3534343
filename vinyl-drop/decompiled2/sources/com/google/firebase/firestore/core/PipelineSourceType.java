package com.google.firebase.firestore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: PipelineUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/google/firebase/firestore/core/PipelineSourceType;", "", "<init>", "(Ljava/lang/String;I)V", "COLLECTION", "COLLECTION_GROUP", "DATABASE", "DOCUMENTS", "UNKNOWN", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PipelineSourceType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ PipelineSourceType[] $VALUES;
    public static final PipelineSourceType COLLECTION = new PipelineSourceType("COLLECTION", 0);
    public static final PipelineSourceType COLLECTION_GROUP = new PipelineSourceType("COLLECTION_GROUP", 1);
    public static final PipelineSourceType DATABASE = new PipelineSourceType("DATABASE", 2);
    public static final PipelineSourceType DOCUMENTS = new PipelineSourceType("DOCUMENTS", 3);
    public static final PipelineSourceType UNKNOWN = new PipelineSourceType("UNKNOWN", 4);

    private static final /* synthetic */ PipelineSourceType[] $values() {
        return new PipelineSourceType[]{COLLECTION, COLLECTION_GROUP, DATABASE, DOCUMENTS, UNKNOWN};
    }

    public static EnumEntries<PipelineSourceType> getEntries() {
        return $ENTRIES;
    }

    private PipelineSourceType(String str, int i) {
    }

    static {
        PipelineSourceType[] pipelineSourceTypeArr$values = $values();
        $VALUES = pipelineSourceTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(pipelineSourceTypeArr$values);
    }

    public static PipelineSourceType valueOf(String str) {
        return (PipelineSourceType) Enum.valueOf(PipelineSourceType.class, str);
    }

    public static PipelineSourceType[] values() {
        return (PipelineSourceType[]) $VALUES.clone();
    }
}
