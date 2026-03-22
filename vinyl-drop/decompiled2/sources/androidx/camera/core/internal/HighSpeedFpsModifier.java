package androidx.camera.core.internal;

import android.media.MediaCodec;
import android.util.Range;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.SessionConfig;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HighSpeedFpsModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0012\u0010\u0010\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\f\u0010\u0010\u001a\u00020\f*\u00020\nH\u0002J\f\u0010\u0011\u001a\u00020\f*\u00020\u0012H\u0002¨\u0006\u0014"}, d2 = {"Landroidx/camera/core/internal/HighSpeedFpsModifier;", "", "<init>", "()V", "modifyFpsForPreviewOnlyRepeating", "", "outputConfigs", "", "Landroidx/camera/core/impl/SessionConfig$OutputConfig;", "repeatingConfigBuilder", "Landroidx/camera/core/impl/CaptureConfig$Builder;", "isHighSpeedFixedFps", "", "Landroid/util/Range;", "", "toPreviewOnlyRange", "hasVideoSurface", "isVideoSurface", "Landroidx/camera/core/impl/DeferrableSurface;", "Companion", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class HighSpeedFpsModifier {
    private static final Companion Companion = new Companion(null);
    private static final int PREVIEW_ONLY_FPS_LOWER = 30;
    private static final String TAG = "HighSpeedFpsModifier";

    /* JADX INFO: compiled from: HighSpeedFpsModifier.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/camera/core/internal/HighSpeedFpsModifier$Companion;", "", "<init>", "()V", "TAG", "", "PREVIEW_ONLY_FPS_LOWER", "", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void modifyFpsForPreviewOnlyRepeating(Collection<? extends SessionConfig.OutputConfig> outputConfigs, CaptureConfig.Builder repeatingConfigBuilder) {
        Range<Integer> expectedFrameRateRange;
        Intrinsics.checkNotNullParameter(outputConfigs, "outputConfigs");
        Intrinsics.checkNotNullParameter(repeatingConfigBuilder, "repeatingConfigBuilder");
        if (outputConfigs.size() != 2 || !hasVideoSurface(outputConfigs) || hasVideoSurface(repeatingConfigBuilder) || (expectedFrameRateRange = repeatingConfigBuilder.getExpectedFrameRateRange()) == null) {
            return;
        }
        if (!isHighSpeedFixedFps(expectedFrameRateRange)) {
            expectedFrameRateRange = null;
        }
        if (expectedFrameRateRange != null) {
            repeatingConfigBuilder.setExpectedFrameRateRange(toPreviewOnlyRange(expectedFrameRateRange));
        }
    }

    private final boolean isHighSpeedFixedFps(Range<Integer> range) {
        return ((Number) range.getUpper()).intValue() >= 120 && Intrinsics.areEqual(range.getLower(), range.getUpper());
    }

    private final Range<Integer> toPreviewOnlyRange(Range<Integer> range) {
        Range<Integer> range2 = new Range<>((Comparable) 30, range.getUpper());
        Logger.d(TAG, "Modified high-speed FPS range from " + range + " to " + range2);
        return range2;
    }

    private final boolean hasVideoSurface(Collection<? extends SessionConfig.OutputConfig> collection) {
        Collection<? extends SessionConfig.OutputConfig> collection2 = collection;
        if ((collection2 instanceof Collection) && collection2.isEmpty()) {
            return false;
        }
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            DeferrableSurface surface = ((SessionConfig.OutputConfig) it.next()).getSurface();
            Intrinsics.checkNotNullExpressionValue(surface, "getSurface(...)");
            if (isVideoSurface(surface)) {
                return true;
            }
        }
        return false;
    }

    private final boolean hasVideoSurface(CaptureConfig.Builder builder) {
        Set<DeferrableSurface> surfaces = builder.getSurfaces();
        Intrinsics.checkNotNullExpressionValue(surfaces, "getSurfaces(...)");
        Set<DeferrableSurface> set = surfaces;
        if ((set instanceof Collection) && set.isEmpty()) {
            return false;
        }
        for (DeferrableSurface deferrableSurface : set) {
            Intrinsics.checkNotNull(deferrableSurface);
            if (isVideoSurface(deferrableSurface)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isVideoSurface(DeferrableSurface deferrableSurface) {
        return Intrinsics.areEqual(deferrableSurface.getContainerClass(), MediaCodec.class);
    }
}
