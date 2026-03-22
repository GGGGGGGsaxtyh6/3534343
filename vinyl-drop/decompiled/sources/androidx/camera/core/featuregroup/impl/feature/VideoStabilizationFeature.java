package androidx.camera.core.featuregroup.impl.feature;

import androidx.camera.core.featuregroup.GroupableFeature;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStabilizationFeature.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000e\u000fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Landroidx/camera/core/featuregroup/impl/feature/VideoStabilizationFeature;", "Landroidx/camera/core/featuregroup/GroupableFeature;", "mode", "Landroidx/camera/core/featuregroup/impl/feature/VideoStabilizationFeature$StabilizationMode;", "<init>", "(Landroidx/camera/core/featuregroup/impl/feature/VideoStabilizationFeature$StabilizationMode;)V", "getMode", "()Landroidx/camera/core/featuregroup/impl/feature/VideoStabilizationFeature$StabilizationMode;", "featureTypeInternal", "Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", "getFeatureTypeInternal$camera_core_release", "()Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", "toString", "", "StabilizationMode", "Companion", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class VideoStabilizationFeature extends GroupableFeature {
    public static final StabilizationMode DEFAULT_STABILIZATION_MODE = StabilizationMode.OFF;
    private final FeatureTypeInternal featureTypeInternal;
    private final StabilizationMode mode;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: VideoStabilizationFeature.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/camera/core/featuregroup/impl/feature/VideoStabilizationFeature$StabilizationMode;", "", "<init>", "(Ljava/lang/String;I)V", "OFF", "ON", "PREVIEW", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class StabilizationMode {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ StabilizationMode[] $VALUES;
        public static final StabilizationMode OFF = new StabilizationMode("OFF", 0);
        public static final StabilizationMode ON = new StabilizationMode("ON", 1);
        public static final StabilizationMode PREVIEW = new StabilizationMode("PREVIEW", 2);

        private static final /* synthetic */ StabilizationMode[] $values() {
            return new StabilizationMode[]{OFF, ON, PREVIEW};
        }

        public static EnumEntries<StabilizationMode> getEntries() {
            return $ENTRIES;
        }

        private StabilizationMode(String str, int i) {
        }

        static {
            StabilizationMode[] stabilizationModeArr$values = $values();
            $VALUES = stabilizationModeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(stabilizationModeArr$values);
        }

        public static StabilizationMode valueOf(String str) {
            return (StabilizationMode) Enum.valueOf(StabilizationMode.class, str);
        }

        public static StabilizationMode[] values() {
            return (StabilizationMode[]) $VALUES.clone();
        }
    }

    public VideoStabilizationFeature(StabilizationMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.mode = mode;
        this.featureTypeInternal = FeatureTypeInternal.VIDEO_STABILIZATION;
    }

    public final StabilizationMode getMode() {
        return this.mode;
    }

    @Override // androidx.camera.core.featuregroup.GroupableFeature
    /* JADX INFO: renamed from: getFeatureTypeInternal$camera_core_release, reason: from getter */
    public FeatureTypeInternal getFeatureTypeInternal() {
        return this.featureTypeInternal;
    }

    public String toString() {
        return "VideoStabilizationFeature(mode=" + this.mode.name() + ')';
    }
}
