package androidx.camera.core.internal;

import android.graphics.Rect;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.SurfaceStreamSpecQueryResult;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.camera.core.streamsharing.StreamSharing;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamSpecsCalculator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016Jb\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\r2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016JJ\u0010\u001c\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u001f0\u001e\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00120\u001e0\u001d2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J^\u0010!\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00120\u001e2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020$0\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Landroidx/camera/core/internal/StreamSpecsCalculatorImpl;", "Landroidx/camera/core/internal/StreamSpecsCalculator;", "useCaseConfigFactory", "Landroidx/camera/core/impl/UseCaseConfigFactory;", "cameraDeviceSurfaceManager", "Landroidx/camera/core/impl/CameraDeviceSurfaceManager;", "<init>", "(Landroidx/camera/core/impl/UseCaseConfigFactory;Landroidx/camera/core/impl/CameraDeviceSurfaceManager;)V", "setCameraDeviceSurfaceManager", "", "calculateSuggestedStreamSpecs", "Landroidx/camera/core/internal/StreamSpecQueryResult;", "cameraMode", "", "cameraInfoInternal", "Landroidx/camera/core/impl/CameraInfoInternal;", "newUseCases", "", "Landroidx/camera/core/UseCase;", "attachedUseCases", "cameraConfig", "Landroidx/camera/core/impl/CameraConfig;", "sessionType", "targetFrameRate", "Landroid/util/Range;", "isFeatureComboInvocation", "", "findMaxSupportedFrameRate", "calculateSuggestedStreamSpecsForAttachedUseCases", "Landroid/util/Pair;", "", "Landroidx/camera/core/impl/StreamSpec;", "Landroidx/camera/core/impl/AttachedSurfaceInfo;", "calculateSuggestedStreamSpecsForNewUseCases", "attachedSurfaceInfoToUseCaseMap", "configPairMap", "Landroidx/camera/core/internal/CameraUseCaseAdapter$ConfigPair;", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class StreamSpecsCalculatorImpl implements StreamSpecsCalculator {
    private CameraDeviceSurfaceManager cameraDeviceSurfaceManager;
    private final UseCaseConfigFactory useCaseConfigFactory;

    public StreamSpecsCalculatorImpl(UseCaseConfigFactory useCaseConfigFactory, CameraDeviceSurfaceManager cameraDeviceSurfaceManager) {
        Intrinsics.checkNotNullParameter(useCaseConfigFactory, "useCaseConfigFactory");
        this.useCaseConfigFactory = useCaseConfigFactory;
        this.cameraDeviceSurfaceManager = cameraDeviceSurfaceManager;
    }

    public /* synthetic */ StreamSpecsCalculatorImpl(UseCaseConfigFactory useCaseConfigFactory, CameraDeviceSurfaceManager cameraDeviceSurfaceManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(useCaseConfigFactory, (i & 2) != 0 ? null : cameraDeviceSurfaceManager);
    }

    @Override // androidx.camera.core.internal.StreamSpecsCalculator
    public void setCameraDeviceSurfaceManager(CameraDeviceSurfaceManager cameraDeviceSurfaceManager) {
        Intrinsics.checkNotNullParameter(cameraDeviceSurfaceManager, "cameraDeviceSurfaceManager");
        this.cameraDeviceSurfaceManager = cameraDeviceSurfaceManager;
    }

    @Override // androidx.camera.core.internal.StreamSpecsCalculator
    public StreamSpecQueryResult calculateSuggestedStreamSpecs(int cameraMode, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> newUseCases, List<? extends UseCase> attachedUseCases, CameraConfig cameraConfig, int sessionType, Range<Integer> targetFrameRate, boolean isFeatureComboInvocation, boolean findMaxSupportedFrameRate) {
        Intrinsics.checkNotNullParameter(cameraInfoInternal, "cameraInfoInternal");
        Intrinsics.checkNotNullParameter(newUseCases, "newUseCases");
        Intrinsics.checkNotNullParameter(attachedUseCases, "attachedUseCases");
        Intrinsics.checkNotNullParameter(cameraConfig, "cameraConfig");
        Intrinsics.checkNotNullParameter(targetFrameRate, "targetFrameRate");
        Pair<Map<UseCase, StreamSpec>, Map<AttachedSurfaceInfo, UseCase>> pairCalculateSuggestedStreamSpecsForAttachedUseCases = calculateSuggestedStreamSpecsForAttachedUseCases(cameraMode, cameraInfoInternal, attachedUseCases);
        Object second = pairCalculateSuggestedStreamSpecsForAttachedUseCases.second;
        Intrinsics.checkNotNullExpressionValue(second, "second");
        Map<UseCase, CameraUseCaseAdapter.ConfigPair> configs = CameraUseCaseAdapter.getConfigs(newUseCases, cameraConfig.getUseCaseConfigFactory(), this.useCaseConfigFactory, sessionType, targetFrameRate);
        Intrinsics.checkNotNullExpressionValue(configs, "getConfigs(...)");
        StreamSpecQueryResult streamSpecQueryResultCalculateSuggestedStreamSpecsForNewUseCases = calculateSuggestedStreamSpecsForNewUseCases(cameraMode, cameraInfoInternal, newUseCases, (Map) second, configs, isFeatureComboInvocation, findMaxSupportedFrameRate);
        Object first = pairCalculateSuggestedStreamSpecsForAttachedUseCases.first;
        Intrinsics.checkNotNullExpressionValue(first, "first");
        return new StreamSpecQueryResult(MapsKt.plus((Map) first, streamSpecQueryResultCalculateSuggestedStreamSpecsForNewUseCases.getStreamSpecs()), streamSpecQueryResultCalculateSuggestedStreamSpecsForNewUseCases.getMaxSupportedFrameRate());
    }

    private final Pair<Map<UseCase, StreamSpec>, Map<AttachedSurfaceInfo, UseCase>> calculateSuggestedStreamSpecsForAttachedUseCases(int cameraMode, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> attachedUseCases) {
        ArrayList arrayList = new ArrayList();
        String cameraId = cameraInfoInternal.getCameraId();
        Intrinsics.checkNotNullExpressionValue(cameraId, "getCameraId(...)");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (UseCase useCase : attachedUseCases) {
            StreamSpec attachedStreamSpec = useCase.getAttachedStreamSpec();
            if (attachedStreamSpec == null) {
                throw new IllegalArgumentException("Attached stream spec cannot be null for already attached use cases.".toString());
            }
            CameraDeviceSurfaceManager cameraDeviceSurfaceManager = this.cameraDeviceSurfaceManager;
            if (cameraDeviceSurfaceManager == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            int imageFormat = useCase.getImageFormat();
            Size attachedSurfaceResolution = useCase.getAttachedSurfaceResolution();
            if (attachedSurfaceResolution != null) {
                SurfaceConfig surfaceConfigTransformSurfaceConfig = cameraDeviceSurfaceManager.transformSurfaceConfig(cameraMode, cameraId, imageFormat, attachedSurfaceResolution, useCase.getCurrentConfig().getStreamUseCase());
                Intrinsics.checkNotNullExpressionValue(surfaceConfigTransformSurfaceConfig, "transformSurfaceConfig(...)");
                int imageFormat2 = useCase.getImageFormat();
                Size attachedSurfaceResolution2 = useCase.getAttachedSurfaceResolution();
                Intrinsics.checkNotNull(attachedSurfaceResolution2);
                DynamicRange dynamicRange = attachedStreamSpec.getDynamicRange();
                List<UseCaseConfigFactory.CaptureType> captureTypes = StreamSharing.getCaptureTypes(useCase);
                Config implementationOptions = attachedStreamSpec.getImplementationOptions();
                int sessionType = useCase.getCurrentConfig().getSessionType(0);
                Range<Integer> targetFrameRate = useCase.getCurrentConfig().getTargetFrameRate(StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED);
                if (targetFrameRate != null) {
                    AttachedSurfaceInfo attachedSurfaceInfoCreate = AttachedSurfaceInfo.create(surfaceConfigTransformSurfaceConfig, imageFormat2, attachedSurfaceResolution2, dynamicRange, captureTypes, implementationOptions, sessionType, targetFrameRate, useCase.getCurrentConfig().isStrictFrameRateRequired());
                    Intrinsics.checkNotNullExpressionValue(attachedSurfaceInfoCreate, "create(...)");
                    arrayList.add(attachedSurfaceInfoCreate);
                    linkedHashMap2.put(attachedSurfaceInfoCreate, useCase);
                    linkedHashMap.put(useCase, attachedStreamSpec);
                } else {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
            } else {
                throw new IllegalArgumentException("Attached surface resolution cannot be null for already attached use cases.".toString());
            }
        }
        return new Pair<>(linkedHashMap, linkedHashMap2);
    }

    private final StreamSpecQueryResult calculateSuggestedStreamSpecsForNewUseCases(int cameraMode, CameraInfoInternal cameraInfoInternal, List<? extends UseCase> newUseCases, Map<AttachedSurfaceInfo, ? extends UseCase> attachedSurfaceInfoToUseCaseMap, Map<UseCase, ? extends CameraUseCaseAdapter.ConfigPair> configPairMap, boolean isFeatureComboInvocation, boolean findMaxSupportedFrameRate) {
        int maxSupportedFrameRate;
        Rect sensorRect;
        String cameraId = cameraInfoInternal.getCameraId();
        Intrinsics.checkNotNullExpressionValue(cameraId, "getCameraId(...)");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (newUseCases.isEmpty()) {
            maxSupportedFrameRate = Integer.MAX_VALUE;
        } else {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            LinkedHashMap linkedHashMap3 = new LinkedHashMap();
            try {
                sensorRect = cameraInfoInternal.getSensorRect();
            } catch (NullPointerException unused) {
                sensorRect = null;
            }
            SupportedOutputSizesSorter supportedOutputSizesSorter = new SupportedOutputSizesSorter(cameraInfoInternal, sensorRect != null ? TransformUtils.rectToSize(sensorRect) : null);
            boolean z = false;
            for (UseCase useCase : newUseCases) {
                CameraUseCaseAdapter.ConfigPair configPair = configPairMap.get(useCase);
                if (configPair == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                CameraUseCaseAdapter.ConfigPair configPair2 = configPair;
                UseCaseConfig<?> useCaseConfigMergeConfigs = useCase.mergeConfigs(cameraInfoInternal, configPair2.mExtendedConfig, configPair2.mCameraConfig);
                Intrinsics.checkNotNullExpressionValue(useCaseConfigMergeConfigs, "mergeConfigs(...)");
                linkedHashMap2.put(useCaseConfigMergeConfigs, useCase);
                List<Size> sortedSupportedOutputSizes = supportedOutputSizesSorter.getSortedSupportedOutputSizes(useCaseConfigMergeConfigs);
                Intrinsics.checkNotNullExpressionValue(sortedSupportedOutputSizes, "getSortedSupportedOutputSizes(...)");
                linkedHashMap3.put(useCaseConfigMergeConfigs, sortedSupportedOutputSizes);
                if (useCaseConfigMergeConfigs.getPreviewStabilizationMode() == 2) {
                    z = true;
                }
            }
            CameraDeviceSurfaceManager cameraDeviceSurfaceManager = this.cameraDeviceSurfaceManager;
            if (cameraDeviceSurfaceManager == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            SurfaceStreamSpecQueryResult suggestedStreamSpecs = cameraDeviceSurfaceManager.getSuggestedStreamSpecs(cameraMode, cameraId, new ArrayList(attachedSurfaceInfoToUseCaseMap.keySet()), linkedHashMap3, z, CameraUseCaseAdapter.hasVideoCapture(newUseCases), isFeatureComboInvocation, findMaxSupportedFrameRate);
            Intrinsics.checkNotNullExpressionValue(suggestedStreamSpecs, "getSuggestedStreamSpecs(...)");
            Map<UseCaseConfig<?>, StreamSpec> mapComponent1 = suggestedStreamSpecs.component1();
            Map<AttachedSurfaceInfo, StreamSpec> mapComponent2 = suggestedStreamSpecs.component2();
            maxSupportedFrameRate = suggestedStreamSpecs.getMaxSupportedFrameRate();
            for (Map.Entry entry : linkedHashMap2.entrySet()) {
                Object value = entry.getValue();
                StreamSpec streamSpec = mapComponent1.get(entry.getKey());
                if (streamSpec != null) {
                    linkedHashMap.put(value, streamSpec);
                } else {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
            }
            for (Map.Entry<AttachedSurfaceInfo, StreamSpec> entry2 : mapComponent2.entrySet()) {
                if (attachedSurfaceInfoToUseCaseMap.containsKey(entry2.getKey())) {
                    UseCase useCase2 = attachedSurfaceInfoToUseCaseMap.get(entry2.getKey());
                    if (useCase2 != null) {
                        linkedHashMap.put(useCase2, entry2.getValue());
                    } else {
                        throw new IllegalArgumentException("Required value was null.".toString());
                    }
                }
            }
        }
        return new StreamSpecQueryResult(linkedHashMap, maxSupportedFrameRate);
    }
}
