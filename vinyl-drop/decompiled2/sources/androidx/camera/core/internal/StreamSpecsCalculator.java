package androidx.camera.core.internal;

import android.util.Range;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.StreamSpec;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamSpecsCalculator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016Jn\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\t2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0016H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0001"}, d2 = {"Landroidx/camera/core/internal/StreamSpecsCalculator;", "", "setCameraDeviceSurfaceManager", "", "cameraDeviceSurfaceManager", "Landroidx/camera/core/impl/CameraDeviceSurfaceManager;", "calculateSuggestedStreamSpecs", "Landroidx/camera/core/internal/StreamSpecQueryResult;", "cameraMode", "", "cameraInfoInternal", "Landroidx/camera/core/impl/CameraInfoInternal;", "newUseCases", "", "Landroidx/camera/core/UseCase;", "attachedUseCases", "cameraConfig", "Landroidx/camera/core/impl/CameraConfig;", "sessionType", "targetFrameRate", "Landroid/util/Range;", "isFeatureComboInvocation", "", "findMaxSupportedFrameRate", "Companion", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public interface StreamSpecsCalculator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final StreamSpecsCalculator NO_OP_STREAM_SPECS_CALCULATOR = new StreamSpecsCalculator() { // from class: androidx.camera.core.internal.StreamSpecsCalculator$Companion$NO_OP_STREAM_SPECS_CALCULATOR$1
        @Override // androidx.camera.core.internal.StreamSpecsCalculator
        public StreamSpecQueryResult calculateSuggestedStreamSpecs(int cameraMode, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> newUseCases, List<? extends UseCase> attachedUseCases, CameraConfig cameraConfig, int sessionType, Range<Integer> targetFrameRate, boolean isFeatureComboInvocation, boolean findMaxSupportedFrameRate) {
            Intrinsics.checkNotNullParameter(cameraInfoInternal, "cameraInfoInternal");
            Intrinsics.checkNotNullParameter(newUseCases, "newUseCases");
            Intrinsics.checkNotNullParameter(attachedUseCases, "attachedUseCases");
            Intrinsics.checkNotNullParameter(cameraConfig, "cameraConfig");
            Intrinsics.checkNotNullParameter(targetFrameRate, "targetFrameRate");
            return new StreamSpecQueryResult(null, 0, 3, null);
        }
    };

    StreamSpecQueryResult calculateSuggestedStreamSpecs(int cameraMode, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> newUseCases, List<? extends UseCase> attachedUseCases, CameraConfig cameraConfig, int sessionType, Range<Integer> targetFrameRate, boolean isFeatureComboInvocation, boolean findMaxSupportedFrameRate);

    default void setCameraDeviceSurfaceManager(CameraDeviceSurfaceManager cameraDeviceSurfaceManager) {
        Intrinsics.checkNotNullParameter(cameraDeviceSurfaceManager, "cameraDeviceSurfaceManager");
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ StreamSpecQueryResult calculateSuggestedStreamSpecs$default(StreamSpecsCalculator streamSpecsCalculator, int i, CameraInfoInternal cameraInfoInternal, List list, List list2, CameraConfig cameraConfig, int i2, Range range, boolean z, boolean z2, int i3, Object obj) {
        boolean z3;
        StreamSpecsCalculator streamSpecsCalculator2;
        int i4;
        CameraInfoInternal cameraInfoInternal2;
        List list3;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: calculateSuggestedStreamSpecs");
        }
        List listEmptyList = (i3 & 8) != 0 ? CollectionsKt.emptyList() : list2;
        CameraConfig cameraConfigDefaultConfig = (i3 & 16) != 0 ? CameraConfigs.defaultConfig() : cameraConfig;
        int i5 = (i3 & 32) != 0 ? 0 : i2;
        Range range2 = (i3 & 64) != 0 ? StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED : range;
        boolean z4 = (i3 & 128) != 0 ? false : z;
        if ((i3 & 256) != 0) {
            z3 = false;
            i4 = i;
            cameraInfoInternal2 = cameraInfoInternal;
            list3 = list;
            streamSpecsCalculator2 = streamSpecsCalculator;
        } else {
            z3 = z2;
            streamSpecsCalculator2 = streamSpecsCalculator;
            i4 = i;
            cameraInfoInternal2 = cameraInfoInternal;
            list3 = list;
        }
        return streamSpecsCalculator2.calculateSuggestedStreamSpecs(i4, cameraInfoInternal2, list3, listEmptyList, cameraConfigDefaultConfig, i5, range2, z4, z3);
    }

    /* JADX INFO: compiled from: StreamSpecsCalculator.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jr\u0010\u0006\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u0014\u001a\u00020\t2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0012H\u0007R\u0013\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\u0018"}, d2 = {"Landroidx/camera/core/internal/StreamSpecsCalculator$Companion;", "", "<init>", "()V", "NO_OP_STREAM_SPECS_CALCULATOR", "Landroidx/camera/core/internal/StreamSpecsCalculator;", "calculateSuggestedStreamSpecsCompat", "Landroidx/camera/core/internal/StreamSpecQueryResult;", "cameraMode", "", "cameraInfoInternal", "Landroidx/camera/core/impl/CameraInfoInternal;", "newUseCases", "", "Landroidx/camera/core/UseCase;", "cameraConfig", "Landroidx/camera/core/impl/CameraConfig;", "isFeatureComboInvocation", "", "attachedUseCases", "sessionType", "targetFrameRate", "Landroid/util/Range;", "findMaxSupportedFrameRate", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final StreamSpecQueryResult calculateSuggestedStreamSpecsCompat(StreamSpecsCalculator streamSpecsCalculator, int i, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> newUseCases) {
            Intrinsics.checkNotNullParameter(streamSpecsCalculator, "<this>");
            Intrinsics.checkNotNullParameter(cameraInfoInternal, "cameraInfoInternal");
            Intrinsics.checkNotNullParameter(newUseCases, "newUseCases");
            return calculateSuggestedStreamSpecsCompat$default(this, streamSpecsCalculator, i, cameraInfoInternal, newUseCases, null, false, null, 0, null, false, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, null);
        }

        public final StreamSpecQueryResult calculateSuggestedStreamSpecsCompat(StreamSpecsCalculator streamSpecsCalculator, int i, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> newUseCases, CameraConfig cameraConfig) {
            Intrinsics.checkNotNullParameter(streamSpecsCalculator, "<this>");
            Intrinsics.checkNotNullParameter(cameraInfoInternal, "cameraInfoInternal");
            Intrinsics.checkNotNullParameter(newUseCases, "newUseCases");
            Intrinsics.checkNotNullParameter(cameraConfig, "cameraConfig");
            return calculateSuggestedStreamSpecsCompat$default(this, streamSpecsCalculator, i, cameraInfoInternal, newUseCases, cameraConfig, false, null, 0, null, false, 496, null);
        }

        public final StreamSpecQueryResult calculateSuggestedStreamSpecsCompat(StreamSpecsCalculator streamSpecsCalculator, int i, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> newUseCases, CameraConfig cameraConfig, boolean z) {
            Intrinsics.checkNotNullParameter(streamSpecsCalculator, "<this>");
            Intrinsics.checkNotNullParameter(cameraInfoInternal, "cameraInfoInternal");
            Intrinsics.checkNotNullParameter(newUseCases, "newUseCases");
            Intrinsics.checkNotNullParameter(cameraConfig, "cameraConfig");
            return calculateSuggestedStreamSpecsCompat$default(this, streamSpecsCalculator, i, cameraInfoInternal, newUseCases, cameraConfig, z, null, 0, null, false, 480, null);
        }

        public final StreamSpecQueryResult calculateSuggestedStreamSpecsCompat(StreamSpecsCalculator streamSpecsCalculator, int i, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> newUseCases, CameraConfig cameraConfig, boolean z, List<? extends UseCase> attachedUseCases) {
            Intrinsics.checkNotNullParameter(streamSpecsCalculator, "<this>");
            Intrinsics.checkNotNullParameter(cameraInfoInternal, "cameraInfoInternal");
            Intrinsics.checkNotNullParameter(newUseCases, "newUseCases");
            Intrinsics.checkNotNullParameter(cameraConfig, "cameraConfig");
            Intrinsics.checkNotNullParameter(attachedUseCases, "attachedUseCases");
            return calculateSuggestedStreamSpecsCompat$default(this, streamSpecsCalculator, i, cameraInfoInternal, newUseCases, cameraConfig, z, attachedUseCases, 0, null, false, 448, null);
        }

        public final StreamSpecQueryResult calculateSuggestedStreamSpecsCompat(StreamSpecsCalculator streamSpecsCalculator, int i, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> newUseCases, CameraConfig cameraConfig, boolean z, List<? extends UseCase> attachedUseCases, int i2) {
            Intrinsics.checkNotNullParameter(streamSpecsCalculator, "<this>");
            Intrinsics.checkNotNullParameter(cameraInfoInternal, "cameraInfoInternal");
            Intrinsics.checkNotNullParameter(newUseCases, "newUseCases");
            Intrinsics.checkNotNullParameter(cameraConfig, "cameraConfig");
            Intrinsics.checkNotNullParameter(attachedUseCases, "attachedUseCases");
            return calculateSuggestedStreamSpecsCompat$default(this, streamSpecsCalculator, i, cameraInfoInternal, newUseCases, cameraConfig, z, attachedUseCases, i2, null, false, 384, null);
        }

        public final StreamSpecQueryResult calculateSuggestedStreamSpecsCompat(StreamSpecsCalculator streamSpecsCalculator, int i, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> newUseCases, CameraConfig cameraConfig, boolean z, List<? extends UseCase> attachedUseCases, int i2, Range<Integer> targetFrameRate) {
            Intrinsics.checkNotNullParameter(streamSpecsCalculator, "<this>");
            Intrinsics.checkNotNullParameter(cameraInfoInternal, "cameraInfoInternal");
            Intrinsics.checkNotNullParameter(newUseCases, "newUseCases");
            Intrinsics.checkNotNullParameter(cameraConfig, "cameraConfig");
            Intrinsics.checkNotNullParameter(attachedUseCases, "attachedUseCases");
            Intrinsics.checkNotNullParameter(targetFrameRate, "targetFrameRate");
            return calculateSuggestedStreamSpecsCompat$default(this, streamSpecsCalculator, i, cameraInfoInternal, newUseCases, cameraConfig, z, attachedUseCases, i2, targetFrameRate, false, 256, null);
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StreamSpecQueryResult calculateSuggestedStreamSpecsCompat$default(Companion companion, StreamSpecsCalculator streamSpecsCalculator, int i, CameraInfoInternal cameraInfoInternal, List list, CameraConfig cameraConfig, boolean z, List list2, int i2, Range range, boolean z2, int i3, Object obj) {
            boolean z3;
            Companion companion2;
            StreamSpecsCalculator streamSpecsCalculator2;
            int i4;
            CameraInfoInternal cameraInfoInternal2;
            List list3;
            CameraConfig cameraConfigDefaultConfig = (i3 & 8) != 0 ? CameraConfigs.defaultConfig() : cameraConfig;
            boolean z4 = (i3 & 16) != 0 ? false : z;
            List listEmptyList = (i3 & 32) != 0 ? CollectionsKt.emptyList() : list2;
            int i5 = (i3 & 64) != 0 ? 0 : i2;
            Range range2 = (i3 & 128) != 0 ? StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED : range;
            if ((i3 & 256) != 0) {
                z3 = false;
                streamSpecsCalculator2 = streamSpecsCalculator;
                i4 = i;
                cameraInfoInternal2 = cameraInfoInternal;
                list3 = list;
                companion2 = companion;
            } else {
                z3 = z2;
                companion2 = companion;
                streamSpecsCalculator2 = streamSpecsCalculator;
                i4 = i;
                cameraInfoInternal2 = cameraInfoInternal;
                list3 = list;
            }
            return companion2.calculateSuggestedStreamSpecsCompat(streamSpecsCalculator2, i4, cameraInfoInternal2, list3, cameraConfigDefaultConfig, z4, listEmptyList, i5, range2, z3);
        }

        public final StreamSpecQueryResult calculateSuggestedStreamSpecsCompat(StreamSpecsCalculator streamSpecsCalculator, int i, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> newUseCases, CameraConfig cameraConfig, boolean z, List<? extends UseCase> attachedUseCases, int i2, Range<Integer> targetFrameRate, boolean z2) {
            Intrinsics.checkNotNullParameter(streamSpecsCalculator, "<this>");
            Intrinsics.checkNotNullParameter(cameraInfoInternal, "cameraInfoInternal");
            Intrinsics.checkNotNullParameter(newUseCases, "newUseCases");
            Intrinsics.checkNotNullParameter(cameraConfig, "cameraConfig");
            Intrinsics.checkNotNullParameter(attachedUseCases, "attachedUseCases");
            Intrinsics.checkNotNullParameter(targetFrameRate, "targetFrameRate");
            return streamSpecsCalculator.calculateSuggestedStreamSpecs(i, cameraInfoInternal, newUseCases, attachedUseCases, cameraConfig, i2, targetFrameRate, z, z2);
        }
    }
}
