package com.google.firebase.firestore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: PipelineUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/firebase/firestore/core/PipelineFlavor;", "", "<init>", "(Ljava/lang/String;I)V", "EXACT", "AUGMENTED", "KEYLESS", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PipelineFlavor {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ PipelineFlavor[] $VALUES;
    public static final PipelineFlavor EXACT = new PipelineFlavor("EXACT", 0);
    public static final PipelineFlavor AUGMENTED = new PipelineFlavor("AUGMENTED", 1);
    public static final PipelineFlavor KEYLESS = new PipelineFlavor("KEYLESS", 2);

    private static final /* synthetic */ PipelineFlavor[] $values() {
        return new PipelineFlavor[]{EXACT, AUGMENTED, KEYLESS};
    }

    public static EnumEntries<PipelineFlavor> getEntries() {
        return $ENTRIES;
    }

    private PipelineFlavor(String str, int i) {
    }

    static {
        PipelineFlavor[] pipelineFlavorArr$values = $values();
        $VALUES = pipelineFlavorArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(pipelineFlavorArr$values);
    }

    public static PipelineFlavor valueOf(String str) {
        return (PipelineFlavor) Enum.valueOf(PipelineFlavor.class, str);
    }

    public static PipelineFlavor[] values() {
        return (PipelineFlavor[]) $VALUES.clone();
    }
}
