package androidx.camera.camera2.internal;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.Pair;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.camera2.internal.compat.StreamConfigurationMapCompat;
import androidx.camera.camera2.internal.compat.workaround.ExtraSupportedSurfaceCombinationsContainer;
import androidx.camera.camera2.internal.compat.workaround.ResolutionCorrector;
import androidx.camera.camera2.internal.compat.workaround.TargetAspectRatio;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.featuregroup.impl.FeatureCombinationQuery;
import androidx.camera.core.featuregroup.impl.feature.FpsRangeFeature;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraMode;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.StreamUseCase;
import androidx.camera.core.impl.SurfaceCombination;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.SurfaceSizeDefinition;
import androidx.camera.core.impl.SurfaceStreamSpecQueryResult;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.AspectRatioUtil;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.UnsafeLazyImpl;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes.dex */
public final class SupportedSurfaceCombination {
    private static final int FRAME_RATE_UNLIMITED = Integer.MAX_VALUE;
    private static final String TAG = "SupportedSurfaceCombination";
    private final CamcorderProfileHelper mCamcorderProfileHelper;
    private final String mCameraId;
    private final CameraCharacteristicsCompat mCharacteristics;
    private final DisplayInfoManager mDisplayInfoManager;
    private final DynamicRangeResolver mDynamicRangeResolver;
    private final ExtraSupportedSurfaceCombinationsContainer mExtraSupportedSurfaceCombinationsContainer;
    private final FeatureCombinationQuery mFeatureCombinationQuery;
    private final int mHardwareLevel;
    private final HighSpeedResolver mHighSpeedResolver;
    private boolean mIsBurstCaptureSupported;
    private final boolean mIsConcurrentCameraModeSupported;
    private boolean mIsManualSensorSupported;
    private final boolean mIsPreviewStabilizationSupported;
    private boolean mIsRawSupported;
    private final boolean mIsStreamUseCaseSupported;
    private boolean mIsUltraHighResolutionSensorSupported;
    SurfaceSizeDefinition mSurfaceSizeDefinition;
    private final List<SurfaceCombination> mSurfaceCombinations = new ArrayList();
    private final List<SurfaceCombination> mUltraHighSurfaceCombinations = new ArrayList();
    private final List<SurfaceCombination> mConcurrentSurfaceCombinations = new ArrayList();
    private final List<SurfaceCombination> mPreviewStabilizationSurfaceCombinations = new ArrayList();
    private final List<SurfaceCombination> mHighSpeedSurfaceCombinations = new ArrayList();
    private final List<SurfaceCombination> mFcqSurfaceCombinations = new ArrayList();
    private final Map<FeatureSettings, List<SurfaceCombination>> mFeatureSettingsToSupportedCombinationsMap = new HashMap();
    private final List<SurfaceCombination> mSurfaceCombinations10Bit = new ArrayList();
    private final List<SurfaceCombination> mSurfaceCombinationsUltraHdr = new ArrayList();
    private final List<SurfaceCombination> mSurfaceCombinationsStreamUseCase = new ArrayList();
    List<Integer> mSurfaceSizeDefinitionFormats = new ArrayList();
    private final TargetAspectRatio mTargetAspectRatio = new TargetAspectRatio();
    private final ResolutionCorrector mResolutionCorrector = new ResolutionCorrector();

    enum CheckingMethod {
        WITHOUT_FEATURE_COMBO,
        WITH_FEATURE_COMBO,
        WITHOUT_FEATURE_COMBO_FIRST_AND_THEN_WITH_IT
    }

    private void checkCustomization() {
    }

    SupportedSurfaceCombination(Context context, String str, CameraManagerCompat cameraManagerCompat, CamcorderProfileHelper camcorderProfileHelper, FeatureCombinationQuery featureCombinationQuery) throws CameraUnavailableException {
        this.mIsRawSupported = false;
        this.mIsBurstCaptureSupported = false;
        this.mIsUltraHighResolutionSensorSupported = false;
        this.mIsManualSensorSupported = false;
        String str2 = (String) Preconditions.checkNotNull(str);
        this.mCameraId = str2;
        this.mCamcorderProfileHelper = (CamcorderProfileHelper) Preconditions.checkNotNull(camcorderProfileHelper);
        this.mExtraSupportedSurfaceCombinationsContainer = new ExtraSupportedSurfaceCombinationsContainer();
        this.mDisplayInfoManager = DisplayInfoManager.getInstance(context);
        try {
            CameraCharacteristicsCompat cameraCharacteristicsCompat = cameraManagerCompat.getCameraCharacteristicsCompat(str2);
            this.mCharacteristics = cameraCharacteristicsCompat;
            Integer num = (Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            this.mHardwareLevel = num != null ? num.intValue() : 2;
            int[] iArr = (int[]) cameraCharacteristicsCompat.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
            if (iArr != null) {
                for (int i : iArr) {
                    if (i == 3) {
                        this.mIsRawSupported = true;
                    } else if (i == 6) {
                        this.mIsBurstCaptureSupported = true;
                    } else if (Build.VERSION.SDK_INT >= 31 && i == 16) {
                        this.mIsUltraHighResolutionSensorSupported = true;
                    } else if (i == 1) {
                        this.mIsManualSensorSupported = true;
                    }
                }
            }
            DynamicRangeResolver dynamicRangeResolver = new DynamicRangeResolver(this.mCharacteristics);
            this.mDynamicRangeResolver = dynamicRangeResolver;
            this.mHighSpeedResolver = new HighSpeedResolver(this.mCharacteristics);
            generateSupportedCombinationList();
            if (this.mIsUltraHighResolutionSensorSupported) {
                generateUltraHighSupportedCombinationList();
            }
            boolean zHasSystemFeature = context.getPackageManager().hasSystemFeature("android.hardware.camera.concurrent");
            this.mIsConcurrentCameraModeSupported = zHasSystemFeature;
            if (zHasSystemFeature) {
                generateConcurrentSupportedCombinationList();
            }
            if (dynamicRangeResolver.is10BitDynamicRangeSupported()) {
                generate10BitSupportedCombinationList();
            }
            boolean zIsStreamUseCaseSupported = StreamUseCaseUtil.isStreamUseCaseSupported(this.mCharacteristics);
            this.mIsStreamUseCaseSupported = zIsStreamUseCaseSupported;
            if (zIsStreamUseCaseSupported) {
                generateStreamUseCaseSupportedCombinationList();
            }
            boolean zIsPreviewStabilizationSupported = VideoStabilizationUtil.isPreviewStabilizationSupported(this.mCharacteristics);
            this.mIsPreviewStabilizationSupported = zIsPreviewStabilizationSupported;
            if (zIsPreviewStabilizationSupported) {
                generatePreviewStabilizationSupportedCombinationList();
            }
            generateSurfaceSizeDefinition();
            checkCustomization();
            this.mFeatureCombinationQuery = featureCombinationQuery;
        } catch (CameraAccessExceptionCompat e) {
            throw CameraUnavailableExceptionHelper.createFrom(e);
        }
    }

    boolean checkSupported(FeatureSettings featureSettings, List<SurfaceConfig> list, Map<SurfaceConfig, DynamicRange> map, List<UseCaseConfig<?>> list2, List<Integer> list3) {
        Iterator<SurfaceCombination> it = getSurfaceCombinationsByFeatureSettings(featureSettings).iterator();
        boolean z = false;
        while (it.hasNext()) {
            z = it.next().getOrderedSupportedSurfaceConfigList(list) != null;
            if (z) {
                break;
            }
        }
        if (!z || !featureSettings.requiresFeatureComboQuery()) {
            return z;
        }
        SessionConfig sessionConfigCreateFeatureComboSessionConfig = createFeatureComboSessionConfig(featureSettings, list, map, list2, list3);
        boolean zIsSupported = this.mFeatureCombinationQuery.isSupported(sessionConfigCreateFeatureComboSessionConfig);
        Iterator<DeferrableSurface> it2 = sessionConfigCreateFeatureComboSessionConfig.getSurfaces().iterator();
        while (it2.hasNext()) {
            it2.next().close();
        }
        return zIsSupported;
    }

    private SessionConfig createFeatureComboSessionConfig(FeatureSettings featureSettings, List<SurfaceConfig> list, Map<SurfaceConfig, DynamicRange> map, List<UseCaseConfig<?>> list2, List<Integer> list3) {
        Range<Integer> targetFpsRange = featureSettings.getTargetFpsRange();
        SessionConfig.ValidatingBuilder validatingBuilder = new SessionConfig.ValidatingBuilder();
        for (int i = 0; i < list.size(); i++) {
            SurfaceConfig surfaceConfig = list.get(i);
            Size resolution = surfaceConfig.getResolution(getUpdatedSurfaceSizeDefinitionByFormat(surfaceConfig.getImageFormat()));
            UseCaseConfig<?> useCaseConfig = list2.get(list3.get(i).intValue());
            SessionConfig.Builder builderCreateSessionConfigBuilder = FeatureCombinationQuery.createSessionConfigBuilder(useCaseConfig, resolution, (DynamicRange) Objects.requireNonNull(map.get(surfaceConfig)));
            builderCreateSessionConfigBuilder.setExpectedFrameRateRange(StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(targetFpsRange) ? FpsRangeFeature.DEFAULT_FPS_RANGE : targetFpsRange);
            if (featureSettings.isPreviewStabilizationOn()) {
                builderCreateSessionConfigBuilder.setPreviewStabilization(2);
            }
            validatingBuilder.add(builderCreateSessionConfigBuilder.build());
            Preconditions.checkState(validatingBuilder.isValid(), "Cannot create a combined SessionConfig for feature combo after adding " + useCaseConfig + " with " + surfaceConfig + " due to [" + validatingBuilder.getInvalidReason() + "]; surfaceConfigList = " + list + ", featureSettings = " + featureSettings + ", newUseCaseConfigs = " + list2);
        }
        return validatingBuilder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    List<SurfaceConfig> getOrderedSupportedStreamUseCaseSurfaceConfigList(FeatureSettings featureSettings, List<SurfaceConfig> list, Map<Integer, AttachedSurfaceInfo> map, Map<Integer, UseCaseConfig<?>> map2) {
        if (!StreamUseCaseUtil.shouldUseStreamUseCase(featureSettings)) {
            return null;
        }
        Iterator<SurfaceCombination> it = this.mSurfaceCombinationsStreamUseCase.iterator();
        while (it.hasNext()) {
            final List<SurfaceConfig> orderedSupportedSurfaceConfigList = it.next().getOrderedSupportedSurfaceConfigList(list);
            if (orderedSupportedSurfaceConfigList != null) {
                boolean zAreCaptureTypesEligible = StreamUseCaseUtil.areCaptureTypesEligible(map, map2, orderedSupportedSurfaceConfigList);
                UnsafeLazyImpl unsafeLazyImpl = new UnsafeLazyImpl(new Function0() { // from class: androidx.camera.camera2.internal.SupportedSurfaceCombination$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return this.f$0.m84x2e20dfec(orderedSupportedSurfaceConfigList);
                    }
                });
                if (zAreCaptureTypesEligible && ((Boolean) unsafeLazyImpl.getValue()).booleanValue()) {
                    return orderedSupportedSurfaceConfigList;
                }
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: lambda$getOrderedSupportedStreamUseCaseSurfaceConfigList$0$androidx-camera-camera2-internal-SupportedSurfaceCombination, reason: not valid java name */
    /* synthetic */ Boolean m84x2e20dfec(List list) {
        return Boolean.valueOf(StreamUseCaseUtil.areStreamUseCasesAvailableForSurfaceConfigs(this.mCharacteristics, list));
    }

    private List<SurfaceCombination> getSurfaceCombinationsByFeatureSettings(FeatureSettings featureSettings) {
        if (this.mFeatureSettingsToSupportedCombinationsMap.containsKey(featureSettings)) {
            return this.mFeatureSettingsToSupportedCombinationsMap.get(featureSettings);
        }
        List<SurfaceCombination> arrayList = new ArrayList<>();
        if (featureSettings.requiresFeatureComboQuery()) {
            if (this.mFcqSurfaceCombinations.isEmpty()) {
                generateFcqSurfaceCombinations();
            }
            arrayList.addAll(this.mFcqSurfaceCombinations);
        } else if (featureSettings.isUltraHdrOn()) {
            if (this.mSurfaceCombinationsUltraHdr.isEmpty()) {
                generateUltraHdrSupportedCombinationList();
            }
            if (featureSettings.getCameraMode() == 0) {
                arrayList.addAll(this.mSurfaceCombinationsUltraHdr);
            }
        } else if (featureSettings.isHighSpeedOn()) {
            if (this.mHighSpeedSurfaceCombinations.isEmpty()) {
                generateHighSpeedSupportedCombinationList();
            }
            arrayList.addAll(this.mHighSpeedSurfaceCombinations);
        } else if (featureSettings.getRequiredMaxBitDepth() == 8) {
            int cameraMode = featureSettings.getCameraMode();
            if (cameraMode == 1) {
                arrayList = this.mConcurrentSurfaceCombinations;
            } else if (cameraMode == 2) {
                arrayList.addAll(this.mUltraHighSurfaceCombinations);
                arrayList.addAll(this.mSurfaceCombinations);
            } else {
                arrayList.addAll(featureSettings.isPreviewStabilizationOn() ? this.mPreviewStabilizationSurfaceCombinations : this.mSurfaceCombinations);
            }
        } else if (featureSettings.getRequiredMaxBitDepth() == 10 && featureSettings.getCameraMode() == 0) {
            arrayList.addAll(this.mSurfaceCombinations10Bit);
        }
        this.mFeatureSettingsToSupportedCombinationsMap.put(featureSettings, arrayList);
        return arrayList;
    }

    SurfaceConfig transformSurfaceConfig(int i, int i2, Size size, StreamUseCase streamUseCase) {
        return SurfaceConfig.transformSurfaceConfig(i2, size, getUpdatedSurfaceSizeDefinitionByFormat(i2), i, SurfaceConfig.ConfigSource.CAPTURE_SESSION_TABLES, streamUseCase);
    }

    private int getMaxFrameRate(int i, Size size, boolean z) {
        Preconditions.checkState(!z || i == 34);
        if (z) {
            return this.mHighSpeedResolver.getMaxFrameRate(size);
        }
        return getMaxFrameRate(this.mCharacteristics, i, size);
    }

    private int getMaxFrameRate(CameraCharacteristicsCompat cameraCharacteristicsCompat, int i, Size size) {
        long outputMinFrameDuration = ((StreamConfigurationMapCompat) Objects.requireNonNull(cameraCharacteristicsCompat.getStreamConfigurationMapCompat())).getOutputMinFrameDuration(i, size);
        if (outputMinFrameDuration > 0) {
            return (int) (1.0E9d / outputMinFrameDuration);
        }
        if (!this.mIsManualSensorSupported) {
            return Integer.MAX_VALUE;
        }
        Logger.w(TAG, "minFrameDuration: " + outputMinFrameDuration + " is invalid for imageFormat = " + i + ", size = " + size);
        return 0;
    }

    private static int getRangeLength(Range<Integer> range) {
        return (((Integer) range.getUpper()).intValue() - ((Integer) range.getLower()).intValue()) + 1;
    }

    private static int getRangeDistance(Range<Integer> range, Range<Integer> range2) {
        Preconditions.checkState((range.contains((Integer) range2.getUpper()) || range.contains((Integer) range2.getLower())) ? false : true, "Ranges must not intersect");
        if (((Integer) range.getLower()).intValue() > ((Integer) range2.getUpper()).intValue()) {
            return ((Integer) range.getLower()).intValue() - ((Integer) range2.getUpper()).intValue();
        }
        return ((Integer) range2.getLower()).intValue() - ((Integer) range.getUpper()).intValue();
    }

    private static Range<Integer> compareIntersectingRanges(Range<Integer> range, Range<Integer> range2, Range<Integer> range3) {
        double rangeLength = getRangeLength(range2.intersect(range));
        double rangeLength2 = getRangeLength(range3.intersect(range));
        double rangeLength3 = rangeLength2 / ((double) getRangeLength(range3));
        double rangeLength4 = rangeLength / ((double) getRangeLength(range2));
        return (rangeLength2 <= rangeLength ? rangeLength2 != rangeLength ? rangeLength4 >= 0.5d || rangeLength3 <= rangeLength4 : rangeLength3 <= rangeLength4 && (rangeLength3 != rangeLength4 || ((Integer) range3.getLower()).intValue() <= ((Integer) range2.getLower()).intValue()) : rangeLength3 < 0.5d && rangeLength3 < rangeLength4) ? range2 : range3;
    }

    private Range<Integer> getClosestSupportedDeviceFrameRate(Range<Integer> range, int i, Range<Integer>[] rangeArr) {
        if (StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(range)) {
            return StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        }
        if (rangeArr == null) {
            return StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        }
        Range<T> range2 = new Range<>(Integer.valueOf(Math.min(((Integer) range.getLower()).intValue(), i)), Integer.valueOf(Math.min(((Integer) range.getUpper()).intValue(), i)));
        Range<Integer> rangeCompareIntersectingRanges = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        int rangeLength = 0;
        for (Range<Integer> range3 : rangeArr) {
            if (i >= ((Integer) ((Range) Objects.requireNonNull(range3)).getLower()).intValue()) {
                if (rangeCompareIntersectingRanges.equals(StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED)) {
                    rangeCompareIntersectingRanges = range3;
                }
                if (range3.equals(range2)) {
                    return range3;
                }
                try {
                    int rangeLength2 = getRangeLength(range3.intersect(range2));
                    if (rangeLength == 0) {
                        rangeLength = rangeLength2;
                    } else {
                        if (rangeLength2 >= rangeLength) {
                            rangeCompareIntersectingRanges = compareIntersectingRanges(range2, rangeCompareIntersectingRanges, range3);
                            rangeLength = getRangeLength(range2.intersect(rangeCompareIntersectingRanges));
                        }
                        range3 = rangeCompareIntersectingRanges;
                    }
                } catch (IllegalArgumentException unused) {
                    if (rangeLength != 0 || (getRangeDistance(range3, range2) >= getRangeDistance(rangeCompareIntersectingRanges, range2) && (getRangeDistance(range3, range2) != getRangeDistance(rangeCompareIntersectingRanges, range2) || (((Integer) range3.getLower()).intValue() <= ((Integer) rangeCompareIntersectingRanges.getUpper()).intValue() && getRangeLength(range3) >= getRangeLength(rangeCompareIntersectingRanges))))) {
                    }
                }
                rangeCompareIntersectingRanges = range3;
            }
        }
        return rangeCompareIntersectingRanges;
    }

    private Range<Integer> getUpdatedTargetFrameRate(Range<Integer> range, Range<Integer> range2, boolean z) {
        if (StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(range2) && StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(range)) {
            return StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        }
        if (StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(range2)) {
            return range;
        }
        if (StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(range)) {
            return range2;
        }
        if (z) {
            Preconditions.checkState(range == range2, "All targetFrameRate should be the same if strict fps is required");
            return range;
        }
        try {
            return range2.intersect(range);
        } catch (IllegalArgumentException unused) {
            return range2;
        }
    }

    private boolean getAndValidateIsStrictFpsRequired(boolean z, Boolean bool) {
        if (bool == null || bool.booleanValue() == z) {
            return z;
        }
        throw new IllegalStateException("All isStrictFpsRequired should be the same");
    }

    private int getUpdatedMaximumFps(int i, int i2, Size size, boolean z) {
        return Math.min(i, getMaxFrameRate(i2, size, z));
    }

    SurfaceStreamSpecQueryResult getSuggestedStreamSpecifications(int i, List<AttachedSurfaceInfo> list, Map<UseCaseConfig<?>, List<Size>> map, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean zIsStrictFpsRequired;
        Range<Integer> targetFpsRange;
        refreshPreviewSize();
        boolean zIsHighSpeedOn = HighSpeedResolver.isHighSpeedOn(list, map.keySet());
        Map<UseCaseConfig<?>, List<Size>> mapFilterCommonSupportedSizes = zIsHighSpeedOn ? this.mHighSpeedResolver.filterCommonSupportedSizes(map) : map;
        ArrayList arrayList = new ArrayList(mapFilterCommonSupportedSizes.keySet());
        List<Integer> useCasesPriorityOrder = getUseCasesPriorityOrder(arrayList);
        Map<UseCaseConfig<?>, DynamicRange> mapResolveAndValidateDynamicRanges = this.mDynamicRangeResolver.resolveAndValidateDynamicRanges(list, arrayList, useCasesPriorityOrder);
        Logger.d(TAG, "resolvedDynamicRanges = " + mapResolveAndValidateDynamicRanges);
        boolean zIsUltraHdrOn = isUltraHdrOn(list, mapFilterCommonSupportedSizes);
        if (z4) {
            targetFpsRange = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
            zIsStrictFpsRequired = false;
        } else {
            zIsStrictFpsRequired = isStrictFpsRequired(list, arrayList);
            targetFpsRange = getTargetFpsRange(list, arrayList, useCasesPriorityOrder, zIsStrictFpsRequired);
        }
        Range<Integer> range = targetFpsRange;
        boolean z5 = zIsStrictFpsRequired;
        Logger.d(TAG, "getSuggestedStreamSpecifications: isPreviewStabilizationOn = " + z + ", mIsPreviewStabilizationSupported = " + this.mIsPreviewStabilizationSupported + ", isFeatureComboInvocation = " + z3);
        if (z && !this.mIsPreviewStabilizationSupported && z3) {
            throw new IllegalArgumentException("Preview stabilization is not supported by the camera.");
        }
        return resolveSpecsByCheckingMethod(getCheckingMethod(mapResolveAndValidateDynamicRanges.values(), range, z, zIsUltraHdrOn, z3), createFeatureSettings(i, z2, mapResolveAndValidateDynamicRanges, z, zIsUltraHdrOn, zIsHighSpeedOn, z3, false, range, z5), list, mapFilterCommonSupportedSizes, arrayList, useCasesPriorityOrder, mapResolveAndValidateDynamicRanges, z4);
    }

    private SurfaceStreamSpecQueryResult resolveSpecsByCheckingMethod(CheckingMethod checkingMethod, FeatureSettings featureSettings, List<AttachedSurfaceInfo> list, Map<UseCaseConfig<?>, List<Size>> map, List<UseCaseConfig<?>> list2, List<Integer> list3, Map<UseCaseConfig<?>, DynamicRange> map2, boolean z) {
        Logger.d(TAG, "resolveSpecsByCheckingMethod: checkingMethod = " + checkingMethod);
        int iOrdinal = checkingMethod.ordinal();
        if (iOrdinal == 1) {
            return resolveSpecsBySettings(createFeatureSettings(featureSettings.getCameraMode(), featureSettings.hasVideoCapture(), map2, featureSettings.isPreviewStabilizationOn(), featureSettings.isUltraHdrOn(), featureSettings.isHighSpeedOn(), featureSettings.isFeatureComboInvocation(), true, featureSettings.getTargetFpsRange(), featureSettings.isStrictFpsRequired()), list, map, list2, list3, map2, z);
        }
        if (iOrdinal == 2) {
            try {
                return resolveSpecsBySettings(featureSettings, list, map, list2, list3, map2, z);
            } catch (IllegalArgumentException e) {
                Logger.d(TAG, "Failed to find a supported combination without feature combo, trying again with feature combo", e);
                return resolveSpecsBySettings(createFeatureSettings(featureSettings.getCameraMode(), featureSettings.hasVideoCapture(), map2, featureSettings.isPreviewStabilizationOn(), featureSettings.isUltraHdrOn(), featureSettings.isHighSpeedOn(), featureSettings.isFeatureComboInvocation(), true, featureSettings.getTargetFpsRange(), featureSettings.isStrictFpsRequired()), list, map, list2, list3, map2, z);
            }
        }
        return resolveSpecsBySettings(featureSettings, list, map, list2, list3, map2, z);
    }

    private SurfaceStreamSpecQueryResult resolveSpecsBySettings(FeatureSettings featureSettings, List<AttachedSurfaceInfo> list, Map<UseCaseConfig<?>, List<Size>> map, List<UseCaseConfig<?>> list2, List<Integer> list3, Map<UseCaseConfig<?>, DynamicRange> map2, boolean z) {
        HashMap map3;
        HashMap map4;
        int i;
        int i2;
        Range<Integer>[] frameRateRangesFor;
        List<AttachedSurfaceInfo> list4 = list;
        List<UseCaseConfig<?>> list5 = list2;
        Logger.d(TAG, "resolveSpecsBySettings: featureSettings = " + featureSettings);
        if (!featureSettings.requiresFeatureComboQuery() && !isUseCasesCombinationSupported(featureSettings, list, map)) {
            throw new IllegalArgumentException("No supported surface combination is found for camera device - Id : " + this.mCameraId + ".  May be attempting to bind too many use cases. Existing surfaces: " + list4 + ". New configs: " + list5 + ". GroupableFeature settings: " + featureSettings);
        }
        Map<UseCaseConfig<?>, List<Size>> mapFilterSupportedSizes = filterSupportedSizes(map, featureSettings, z);
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list3.iterator();
        while (it.hasNext()) {
            UseCaseConfig<?> useCaseConfig = list5.get(it.next().intValue());
            List<Size> listEmptyList = mapFilterSupportedSizes.get(useCaseConfig);
            if (listEmptyList == null) {
                listEmptyList = Collections.emptyList();
            }
            arrayList.add(applyResolutionSelectionOrderRelatedWorkarounds(listEmptyList, useCaseConfig.getInputFormat()));
        }
        List<List<Size>> sizeArrangements = featureSettings.isHighSpeedOn() ? this.mHighSpeedResolver.getSizeArrangements(arrayList) : getAllPossibleSizeArrangements(arrayList);
        HashMap map5 = new HashMap();
        HashMap map6 = new HashMap();
        HashMap map7 = new HashMap();
        HashMap map8 = new HashMap();
        boolean zContainsZslUseCase = StreamUseCaseUtil.containsZslUseCase(list4, list5);
        int maxSupportedFpsFromAttachedSurfaces = getMaxSupportedFpsFromAttachedSurfaces(list4, featureSettings.isHighSpeedOn());
        List<SurfaceConfig> orderedSupportedStreamUseCaseSurfaceConfigList = null;
        if (!this.mIsStreamUseCaseSupported || zContainsZslUseCase) {
            map3 = map7;
            map4 = map8;
            i = maxSupportedFpsFromAttachedSurfaces;
        } else {
            Iterator<List<Size>> it2 = sizeArrangements.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    map3 = map7;
                    map4 = map8;
                    i = maxSupportedFpsFromAttachedSurfaces;
                    break;
                }
                Pair<List<SurfaceConfig>, Integer> surfaceConfigListAndFpsCeiling = getSurfaceConfigListAndFpsCeiling(featureSettings, list4, it2.next(), list5, list3, maxSupportedFpsFromAttachedSurfaces, map7, map8);
                map3 = map7;
                map4 = map8;
                i = maxSupportedFpsFromAttachedSurfaces;
                orderedSupportedStreamUseCaseSurfaceConfigList = getOrderedSupportedStreamUseCaseSurfaceConfigList(featureSettings, (List) surfaceConfigListAndFpsCeiling.first, map3, map4);
                if (orderedSupportedStreamUseCaseSurfaceConfigList != null) {
                    break;
                }
                map3.clear();
                map4.clear();
                list5 = list2;
                map7 = map3;
                maxSupportedFpsFromAttachedSurfaces = i;
                list4 = list;
                map8 = map4;
            }
            Logger.d(TAG, "orderedSurfaceConfigListForStreamUseCase = " + orderedSupportedStreamUseCaseSurfaceConfigList);
        }
        List<SurfaceConfig> list6 = orderedSupportedStreamUseCaseSurfaceConfigList;
        HashMap map9 = map3;
        HashMap map10 = map4;
        BestSizesAndMaxFpsForConfigs bestSizesAndMaxFpsForConfigsFindBestSizesAndFps = findBestSizesAndFps(featureSettings, list, list2, list3, sizeArrangements, list6, map2, i, z);
        Logger.d(TAG, "resolveSpecsBySettings: bestSizesAndFps = " + bestSizesAndMaxFpsForConfigsFindBestSizesAndFps);
        List<Size> bestSizes = bestSizesAndMaxFpsForConfigsFindBestSizesAndFps.getBestSizes();
        int maxFpsForBestSizes = bestSizesAndMaxFpsForConfigsFindBestSizesAndFps.getMaxFpsForBestSizes();
        List<Size> bestSizesForStreamUseCase = bestSizesAndMaxFpsForConfigsFindBestSizesAndFps.getBestSizesForStreamUseCase();
        int maxFpsForStreamUseCase = bestSizesAndMaxFpsForConfigsFindBestSizesAndFps.getMaxFpsForStreamUseCase();
        int maxFpsForAllSizes = bestSizesAndMaxFpsForConfigsFindBestSizesAndFps.getMaxFpsForAllSizes();
        if (bestSizes != null) {
            Range<Integer> closestSupportedDeviceFrameRate = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
            if (!StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(featureSettings.getTargetFpsRange())) {
                if (featureSettings.isHighSpeedOn()) {
                    frameRateRangesFor = this.mHighSpeedResolver.getFrameRateRangesFor(bestSizes);
                } else {
                    frameRateRangesFor = (Range[]) this.mCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                }
                Range<Integer> closestSupportedDeviceFrameRate2 = getClosestSupportedDeviceFrameRate(featureSettings.getTargetFpsRange(), maxFpsForBestSizes, frameRateRangesFor);
                if (featureSettings.isFeatureComboInvocation() || featureSettings.isStrictFpsRequired()) {
                    i2 = maxFpsForAllSizes;
                    Preconditions.checkArgument(closestSupportedDeviceFrameRate2.equals(featureSettings.getTargetFpsRange()), "Target FPS range " + featureSettings.getTargetFpsRange() + " is not supported. Max FPS supported by the calculated best combination: " + maxFpsForBestSizes + ". Calculated best FPS range for device: " + closestSupportedDeviceFrameRate2 + ". Device supported FPS ranges: " + Arrays.toString(frameRateRangesFor));
                } else {
                    i2 = maxFpsForAllSizes;
                }
                closestSupportedDeviceFrameRate = closestSupportedDeviceFrameRate2;
            } else {
                i2 = maxFpsForAllSizes;
                if (featureSettings.isHighSpeedOn()) {
                    closestSupportedDeviceFrameRate = getClosestSupportedDeviceFrameRate(HighSpeedResolver.DEFAULT_FPS, maxFpsForBestSizes, this.mHighSpeedResolver.getFrameRateRangesFor(bestSizes));
                }
            }
            Iterator<UseCaseConfig<?>> it3 = list2.iterator();
            while (it3.hasNext()) {
                UseCaseConfig<?> next = it3.next();
                Iterator<UseCaseConfig<?>> it4 = it3;
                StreamSpec.Builder zslDisabled = StreamSpec.builder(bestSizes.get(list3.indexOf(Integer.valueOf(list2.indexOf(next))))).setSessionType(featureSettings.isHighSpeedOn() ? 1 : 0).setDynamicRange((DynamicRange) Preconditions.checkNotNull(map2.get(next))).setImplementationOptions(StreamUseCaseUtil.getStreamSpecImplementationOptions(next)).setZslDisabled(featureSettings.hasVideoCapture());
                if (!StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(closestSupportedDeviceFrameRate)) {
                    zslDisabled.setExpectedFrameRateRange(closestSupportedDeviceFrameRate);
                }
                map6.put(next, zslDisabled.build());
                it3 = it4;
            }
            if (list6 != null && maxFpsForBestSizes == maxFpsForStreamUseCase && bestSizes.size() == bestSizesForStreamUseCase.size()) {
                int i3 = 0;
                while (true) {
                    if (i3 < bestSizes.size()) {
                        if (!bestSizes.get(i3).equals(bestSizesForStreamUseCase.get(i3))) {
                            break;
                        }
                        i3++;
                    } else if (!StreamUseCaseUtil.populateStreamUseCaseStreamSpecOptionWithInteropOverride(this.mCharacteristics, list, map6, map5)) {
                        StreamUseCaseUtil.populateStreamUseCaseStreamSpecOptionWithSupportedSurfaceConfigs(map6, map5, map9, map10, list6);
                    }
                }
            }
            return new SurfaceStreamSpecQueryResult(map6, map5, i2);
        }
        throw new IllegalArgumentException("No supported surface combination is found for camera device - Id : " + this.mCameraId + " and Hardware level: " + this.mHardwareLevel + ". May be the specified resolution is too large and not supported. Existing surfaces: " + list + " New configs: " + list2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [boolean, int] */
    private CheckingMethod getCheckingMethod(Collection<DynamicRange> collection, Range<Integer> range, boolean z, boolean z2, boolean z3) {
        if (!z3) {
            return CheckingMethod.WITHOUT_FEATURE_COMBO;
        }
        ?? Contains = collection.contains(DynamicRange.HLG_10_BIT);
        int i = Contains;
        if (range != null) {
            i = Contains;
            if (((Integer) range.getUpper()).intValue() == 60) {
                i = Contains + 1;
            }
        }
        if (z) {
            i++;
        }
        if (z2) {
            i++;
        }
        if (i > 1) {
            return CheckingMethod.WITH_FEATURE_COMBO;
        }
        if (i == 1) {
            return CheckingMethod.WITHOUT_FEATURE_COMBO_FIRST_AND_THEN_WITH_IT;
        }
        return CheckingMethod.WITHOUT_FEATURE_COMBO;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0116 A[PHI: r6 r7 r10
      0x0116: PHI (r6v2 boolean) = (r6v1 boolean), (r6v1 boolean), (r6v1 boolean), (r6v3 boolean) binds: [B:26:0x00eb, B:28:0x00f7, B:34:0x0104, B:38:0x0112] A[DONT_GENERATE, DONT_INLINE]
      0x0116: PHI (r7v3 int) = (r7v1 int), (r7v1 int), (r7v5 int), (r7v6 int) binds: [B:26:0x00eb, B:28:0x00f7, B:34:0x0104, B:38:0x0112] A[DONT_GENERATE, DONT_INLINE]
      0x0116: PHI (r10v3 java.util.List<android.util.Size>) = 
      (r10v1 java.util.List<android.util.Size>)
      (r10v1 java.util.List<android.util.Size>)
      (r10v5 java.util.List<android.util.Size>)
      (r10v6 java.util.List<android.util.Size>)
     binds: [B:26:0x00eb, B:28:0x00f7, B:34:0x0104, B:38:0x0112] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x013e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private BestSizesAndMaxFpsForConfigs findBestSizesAndFps(final FeatureSettings featureSettings, List<AttachedSurfaceInfo> list, final List<UseCaseConfig<?>> list2, final List<Integer> list3, List<List<Size>> list4, List<SurfaceConfig> list5, Map<UseCaseConfig<?>, DynamicRange> map, int i, boolean z) {
        FeatureSettings featureSettings2;
        Range<Integer> targetFpsRange = featureSettings.getTargetFpsRange();
        Iterator<List<Size>> it = list4.iterator();
        int i2 = Integer.MAX_VALUE;
        boolean z2 = false;
        int i3 = Integer.MAX_VALUE;
        boolean z3 = false;
        int i4 = Integer.MAX_VALUE;
        List<Size> list6 = null;
        List<Size> list7 = null;
        while (true) {
            if (!it.hasNext()) {
                featureSettings2 = featureSettings;
                break;
            }
            List<Size> next = it.next();
            HashMap map2 = new HashMap();
            HashMap map3 = new HashMap();
            Pair<List<SurfaceConfig>, Integer> surfaceConfigListAndFpsCeiling = getSurfaceConfigListAndFpsCeiling(featureSettings, list, next, list2, list3, i, map2, map3);
            final List<SurfaceConfig> list8 = (List) surfaceConfigListAndFpsCeiling.first;
            int iIntValue = ((Integer) surfaceConfigListAndFpsCeiling.second).intValue();
            boolean zIsConfigFrameRateAcceptable = isConfigFrameRateAcceptable(i, targetFpsRange, iIntValue);
            final HashMap map4 = new HashMap();
            Iterator<List<Size>> it2 = it;
            int i5 = 0;
            while (i5 < list8.size()) {
                SurfaceConfig surfaceConfig = list8.get(i5);
                DynamicRange dynamicRange = DynamicRange.UNSPECIFIED;
                int i6 = i5;
                if (map2.containsKey(Integer.valueOf(i6))) {
                    dynamicRange = ((AttachedSurfaceInfo) Objects.requireNonNull(map2.get(Integer.valueOf(i6)))).getDynamicRange();
                } else {
                    if (map3.containsKey(Integer.valueOf(i6))) {
                        dynamicRange = map.get(Objects.requireNonNull(map3.get(Integer.valueOf(i6))));
                    }
                    map4.put(surfaceConfig, dynamicRange);
                    i5 = i6 + 1;
                }
                map4.put(surfaceConfig, dynamicRange);
                i5 = i6 + 1;
            }
            UnsafeLazyImpl unsafeLazyImpl = new UnsafeLazyImpl(new Function0() { // from class: androidx.camera.camera2.internal.SupportedSurfaceCombination$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f$0.m83x855fb871(featureSettings, list8, map4, list2, list3);
                }
            });
            if (z && ((Boolean) unsafeLazyImpl.getValue()).booleanValue() && (i2 == Integer.MAX_VALUE || i2 < iIntValue)) {
                i2 = iIntValue;
            }
            if (!z2 && ((Boolean) unsafeLazyImpl.getValue()).booleanValue()) {
                if (i3 == Integer.MAX_VALUE || i3 < iIntValue) {
                    i3 = iIntValue;
                    list6 = next;
                }
                if (zIsConfigFrameRateAcceptable) {
                    if (z3 && !z) {
                        featureSettings2 = featureSettings;
                        i3 = iIntValue;
                        list6 = next;
                        break;
                    }
                    z2 = true;
                    i3 = iIntValue;
                    list6 = next;
                    if (list5 == null) {
                    }
                    it = it2;
                }
            } else {
                if (list5 == null && !z3) {
                    featureSettings2 = featureSettings;
                    if (getOrderedSupportedStreamUseCaseSurfaceConfigList(featureSettings2, list8, map2, map3) != null) {
                        if (i4 == Integer.MAX_VALUE || i4 < iIntValue) {
                            i4 = iIntValue;
                            list7 = next;
                        }
                        if (!zIsConfigFrameRateAcceptable) {
                            continue;
                        } else {
                            if (z2 && !z) {
                                i4 = iIntValue;
                                list7 = next;
                                break;
                            }
                            z3 = true;
                            i4 = iIntValue;
                            list7 = next;
                        }
                    } else {
                        continue;
                    }
                }
                it = it2;
            }
        }
        if (featureSettings2.isFeatureComboInvocation() && !StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(targetFpsRange) && (i3 == Integer.MAX_VALUE || i3 < ((Integer) targetFpsRange.getUpper()).intValue())) {
            return BestSizesAndMaxFpsForConfigs.of(null, null, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        return BestSizesAndMaxFpsForConfigs.of(list6, list7, i3, i4, i2);
    }

    /* JADX INFO: renamed from: lambda$findBestSizesAndFps$1$androidx-camera-camera2-internal-SupportedSurfaceCombination, reason: not valid java name */
    /* synthetic */ Boolean m83x855fb871(FeatureSettings featureSettings, List list, Map map, List list2, List list3) {
        return Boolean.valueOf(checkSupported(featureSettings, list, map, list2, list3));
    }

    private static boolean isConfigFrameRateAcceptable(int i, Range<Integer> range, int i2) {
        return StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(range) || i2 >= i || i2 >= ((Integer) range.getUpper()).intValue();
    }

    private static boolean isUltraHdrOn(List<AttachedSurfaceInfo> list, Map<UseCaseConfig<?>, List<Size>> map) {
        Iterator<AttachedSurfaceInfo> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getImageFormat() == 4101) {
                return true;
            }
        }
        Iterator<UseCaseConfig<?>> it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            if (it2.next().getInputFormat() == 4101) {
                return true;
            }
        }
        return false;
    }

    private FeatureSettings createFeatureSettings(int i, boolean z, Map<UseCaseConfig<?>, DynamicRange> map, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, Range<Integer> range, boolean z7) {
        int requiredMaxBitDepth = getRequiredMaxBitDepth(map);
        if (i != 0 && z3) {
            throw new IllegalArgumentException(String.format("Camera device id is %s. Ultra HDR is not currently supported in %s camera mode.", this.mCameraId, CameraMode.toLabelString(i)));
        }
        if (i != 0 && requiredMaxBitDepth == 10) {
            throw new IllegalArgumentException(String.format("Camera device id is %s. 10 bit dynamic range is not currently supported in %s camera mode.", this.mCameraId, CameraMode.toLabelString(i)));
        }
        if (i != 0 && z5) {
            throw new IllegalArgumentException(String.format("Camera device id is %s. Feature combination query is not currently supported in %s camera mode.", this.mCameraId, CameraMode.toLabelString(i)));
        }
        if (z4 && z5) {
            throw new IllegalArgumentException("High-speed session is not supported with feature combination");
        }
        if (z4 && !this.mHighSpeedResolver.isHighSpeedSupported()) {
            throw new IllegalArgumentException("High-speed session is not supported on this device.");
        }
        if (z5 && range == StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED && z6) {
            range = FpsRangeFeature.DEFAULT_FPS_RANGE;
        }
        return FeatureSettings.of(i, z, requiredMaxBitDepth, z2, z3, z4, z5, z6, range, z7);
    }

    private boolean isUseCasesCombinationSupported(FeatureSettings featureSettings, List<AttachedSurfaceInfo> list, Map<UseCaseConfig<?>, List<Size>> map) {
        ArrayList arrayList = new ArrayList();
        Iterator<AttachedSurfaceInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getSurfaceConfig());
        }
        CompareSizesByArea compareSizesByArea = new CompareSizesByArea();
        for (UseCaseConfig<?> useCaseConfig : map.keySet()) {
            List<Size> list2 = map.get(useCaseConfig);
            Preconditions.checkArgument((list2 == null || list2.isEmpty()) ? false : true, "No available output size is found for " + useCaseConfig + ".");
            Size size = (Size) Collections.min(list2, compareSizesByArea);
            int inputFormat = useCaseConfig.getInputFormat();
            arrayList.add(SurfaceConfig.transformSurfaceConfig(inputFormat, size, getUpdatedSurfaceSizeDefinitionByFormat(inputFormat), featureSettings.getCameraMode(), SurfaceConfig.ConfigSource.CAPTURE_SESSION_TABLES, useCaseConfig.getStreamUseCase()));
        }
        return checkSupported(featureSettings, arrayList, Collections.emptyMap(), Collections.emptyList(), Collections.emptyList());
    }

    private Range<Integer> getTargetFpsRange(List<AttachedSurfaceInfo> list, List<UseCaseConfig<?>> list2, List<Integer> list3, boolean z) {
        Range<Integer> updatedTargetFrameRate = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        Iterator<AttachedSurfaceInfo> it = list.iterator();
        while (it.hasNext()) {
            updatedTargetFrameRate = getUpdatedTargetFrameRate(it.next().getTargetFrameRate(), updatedTargetFrameRate, z);
        }
        Iterator<Integer> it2 = list3.iterator();
        while (it2.hasNext()) {
            updatedTargetFrameRate = getUpdatedTargetFrameRate((Range) Objects.requireNonNull(list2.get(it2.next().intValue()).getTargetFrameRate(StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED)), updatedTargetFrameRate, z);
        }
        return updatedTargetFrameRate;
    }

    private boolean isStrictFpsRequired(List<AttachedSurfaceInfo> list, List<UseCaseConfig<?>> list2) {
        Iterator<AttachedSurfaceInfo> it = list.iterator();
        Boolean boolValueOf = null;
        while (it.hasNext()) {
            boolValueOf = Boolean.valueOf(getAndValidateIsStrictFpsRequired(it.next().isStrictFrameRateRequired(), boolValueOf));
        }
        Iterator<UseCaseConfig<?>> it2 = list2.iterator();
        while (it2.hasNext()) {
            boolValueOf = Boolean.valueOf(getAndValidateIsStrictFpsRequired(it2.next().isStrictFrameRateRequired(), boolValueOf));
        }
        if (boolValueOf != null) {
            return boolValueOf.booleanValue();
        }
        return false;
    }

    private int getMaxSupportedFpsFromAttachedSurfaces(List<AttachedSurfaceInfo> list, boolean z) {
        int updatedMaximumFps = Integer.MAX_VALUE;
        for (AttachedSurfaceInfo attachedSurfaceInfo : list) {
            updatedMaximumFps = getUpdatedMaximumFps(updatedMaximumFps, attachedSurfaceInfo.getImageFormat(), attachedSurfaceInfo.getSize(), z);
        }
        return updatedMaximumFps;
    }

    Map<UseCaseConfig<?>, List<Size>> filterSupportedSizes(Map<UseCaseConfig<?>, List<Size>> map, FeatureSettings featureSettings, boolean z) {
        HashMap map2 = new HashMap();
        for (UseCaseConfig<?> useCaseConfig : map.keySet()) {
            ArrayList arrayList = new ArrayList();
            HashMap map3 = new HashMap();
            Iterator it = ((List) Objects.requireNonNull(map.get(useCaseConfig))).iterator();
            while (it.hasNext()) {
                populateReducedSizeListAndUniqueMaxFpsMap(featureSettings, featureSettings.getTargetFpsRange(), (Size) it.next(), useCaseConfig.getInputFormat(), useCaseConfig.getStreamUseCase(), z, map3, arrayList);
            }
            map2.put(useCaseConfig, arrayList);
        }
        return map2;
    }

    private void populateReducedSizeListAndUniqueMaxFpsMap(FeatureSettings featureSettings, Range<Integer> range, Size size, int i, StreamUseCase streamUseCase, boolean z, Map<SurfaceConfig.ConfigSize, Set<Integer>> map, List<Size> list) {
        SurfaceConfig.ConfigSize configSize = SurfaceConfig.transformSurfaceConfig(i, size, getUpdatedSurfaceSizeDefinitionByFormat(i), featureSettings.getCameraMode(), featureSettings.requiresFeatureComboQuery() ? SurfaceConfig.ConfigSource.FEATURE_COMBINATION_TABLE : SurfaceConfig.ConfigSource.CAPTURE_SESSION_TABLES, streamUseCase).getConfigSize();
        int maxFrameRate = (!StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(range) || z) ? getMaxFrameRate(i, size, featureSettings.isHighSpeedOn()) : Integer.MAX_VALUE;
        if (featureSettings.isFeatureComboInvocation()) {
            if (configSize == SurfaceConfig.ConfigSize.NOT_SUPPORT) {
                return;
            }
            if (!StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(range) && maxFrameRate < ((Integer) range.getUpper()).intValue()) {
                return;
            }
        }
        Set<Integer> hashSet = map.get(configSize);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            map.put(configSize, hashSet);
        }
        if (hashSet.contains(Integer.valueOf(maxFrameRate))) {
            return;
        }
        list.add(size);
        hashSet.add(Integer.valueOf(maxFrameRate));
    }

    private Pair<List<SurfaceConfig>, Integer> getSurfaceConfigListAndFpsCeiling(FeatureSettings featureSettings, List<AttachedSurfaceInfo> list, List<Size> list2, List<UseCaseConfig<?>> list3, List<Integer> list4, int i, Map<Integer, AttachedSurfaceInfo> map, Map<Integer, UseCaseConfig<?>> map2) {
        ArrayList arrayList = new ArrayList();
        for (AttachedSurfaceInfo attachedSurfaceInfo : list) {
            arrayList.add(attachedSurfaceInfo.getSurfaceConfig());
            if (map != null) {
                map.put(Integer.valueOf(arrayList.size() - 1), attachedSurfaceInfo);
            }
        }
        for (int i2 = 0; i2 < list2.size(); i2++) {
            Size size = list2.get(i2);
            UseCaseConfig<?> useCaseConfig = list3.get(list4.get(i2).intValue());
            int inputFormat = useCaseConfig.getInputFormat();
            arrayList.add(SurfaceConfig.transformSurfaceConfig(inputFormat, size, getUpdatedSurfaceSizeDefinitionByFormat(inputFormat), featureSettings.getCameraMode(), featureSettings.requiresFeatureComboQuery() ? SurfaceConfig.ConfigSource.FEATURE_COMBINATION_TABLE : SurfaceConfig.ConfigSource.CAPTURE_SESSION_TABLES, useCaseConfig.getStreamUseCase()));
            if (map2 != null) {
                map2.put(Integer.valueOf(arrayList.size() - 1), useCaseConfig);
            }
            i = getUpdatedMaximumFps(i, useCaseConfig.getInputFormat(), size, featureSettings.isHighSpeedOn());
        }
        return new Pair<>(arrayList, Integer.valueOf(i));
    }

    List<Size> applyResolutionSelectionOrderRelatedWorkarounds(List<Size> list, int i) {
        Rational rational;
        int i2 = this.mTargetAspectRatio.get(this.mCameraId, this.mCharacteristics);
        if (i2 == 0) {
            rational = AspectRatioUtil.ASPECT_RATIO_4_3;
        } else if (i2 != 1) {
            rational = null;
            if (i2 == 2) {
                Size maximumSize = getUpdatedSurfaceSizeDefinitionByFormat(256).getMaximumSize(256);
                if (maximumSize != null) {
                    rational = new Rational(maximumSize.getWidth(), maximumSize.getHeight());
                }
            } else if (i2 != 3) {
                throw new AssertionError("Undefined targetAspectRatio: " + i2);
            }
        } else {
            rational = AspectRatioUtil.ASPECT_RATIO_16_9;
        }
        if (rational != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Size size : list) {
                if (AspectRatioUtil.hasMatchingAspectRatio(size, rational)) {
                    arrayList.add(size);
                } else {
                    arrayList2.add(size);
                }
            }
            arrayList2.addAll(0, arrayList);
            list = arrayList2;
        }
        return this.mResolutionCorrector.insertOrPrioritize(SurfaceConfig.getConfigType(i), list);
    }

    private static int getRequiredMaxBitDepth(Map<UseCaseConfig<?>, DynamicRange> map) {
        Iterator<DynamicRange> it = map.values().iterator();
        while (it.hasNext()) {
            if (it.next().getBitDepth() == 10) {
                return 10;
            }
        }
        return 8;
    }

    private static List<Integer> getUseCasesPriorityOrder(List<UseCaseConfig<?>> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<UseCaseConfig<?>> it = list.iterator();
        while (it.hasNext()) {
            int surfaceOccupancyPriority = it.next().getSurfaceOccupancyPriority(0);
            if (!arrayList2.contains(Integer.valueOf(surfaceOccupancyPriority))) {
                arrayList2.add(Integer.valueOf(surfaceOccupancyPriority));
            }
        }
        Collections.sort(arrayList2);
        Collections.reverse(arrayList2);
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            int iIntValue = ((Integer) it2.next()).intValue();
            for (UseCaseConfig<?> useCaseConfig : list) {
                if (iIntValue == useCaseConfig.getSurfaceOccupancyPriority(0)) {
                    arrayList.add(Integer.valueOf(list.indexOf(useCaseConfig)));
                }
            }
        }
        return arrayList;
    }

    private List<List<Size>> getAllPossibleSizeArrangements(List<List<Size>> list) {
        Iterator<List<Size>> it = list.iterator();
        int size = 1;
        while (it.hasNext()) {
            size *= it.next().size();
        }
        if (size == 0) {
            throw new IllegalArgumentException("Failed to find supported resolutions.");
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            arrayList.add(new ArrayList());
        }
        int size2 = size / list.get(0).size();
        int i2 = size;
        for (int i3 = 0; i3 < list.size(); i3++) {
            List<Size> list2 = list.get(i3);
            for (int i4 = 0; i4 < size; i4++) {
                ((List) arrayList.get(i4)).add(list2.get((i4 % i2) / size2));
            }
            if (i3 < list.size() - 1) {
                i2 = size2;
                size2 /= list.get(i3 + 1).size();
            }
        }
        return arrayList;
    }

    private Size getMaxOutputSizeByFormat(StreamConfigurationMap streamConfigurationMap, int i, boolean z, Rational rational) {
        Size[] highResolutionOutputSizes;
        Size[] outputSizes = getOutputSizes(streamConfigurationMap, i, rational);
        if (outputSizes == null || outputSizes.length == 0) {
            return null;
        }
        CompareSizesByArea compareSizesByArea = new CompareSizesByArea();
        Size size = (Size) Collections.max(Arrays.asList(outputSizes), compareSizesByArea);
        Size size2 = SizeUtil.RESOLUTION_ZERO;
        if (z && (highResolutionOutputSizes = Api23Impl.getHighResolutionOutputSizes(streamConfigurationMap, i)) != null && highResolutionOutputSizes.length > 0) {
            size2 = (Size) Collections.max(Arrays.asList(highResolutionOutputSizes), compareSizesByArea);
        }
        return (Size) Collections.max(Arrays.asList(size, size2), compareSizesByArea);
    }

    private static Size[] getOutputSizes(StreamConfigurationMap streamConfigurationMap, int i, Rational rational) {
        Size[] outputSizes;
        try {
            if (i == 34) {
                outputSizes = streamConfigurationMap.getOutputSizes(SurfaceTexture.class);
            } else {
                outputSizes = streamConfigurationMap.getOutputSizes(i);
            }
        } catch (Throwable unused) {
            outputSizes = null;
        }
        if (outputSizes == null || outputSizes.length == 0) {
            return null;
        }
        if (rational == null) {
            return outputSizes;
        }
        ArrayList arrayList = new ArrayList();
        for (Size size : outputSizes) {
            if (AspectRatioUtil.hasMatchingAspectRatio(size, rational)) {
                arrayList.add(size);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (Size[]) arrayList.toArray(new Size[0]);
    }

    private void generateSupportedCombinationList() {
        this.mSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.generateSupportedCombinationList(this.mHardwareLevel, this.mIsRawSupported, this.mIsBurstCaptureSupported));
        this.mSurfaceCombinations.addAll(this.mExtraSupportedSurfaceCombinationsContainer.get(this.mCameraId));
    }

    private void generateUltraHighSupportedCombinationList() {
        this.mUltraHighSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.getUltraHighResolutionSupportedCombinationList());
    }

    private void generateConcurrentSupportedCombinationList() {
        this.mConcurrentSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.getConcurrentSupportedCombinationList());
    }

    private void generate10BitSupportedCombinationList() {
        this.mSurfaceCombinations10Bit.addAll(GuaranteedConfigurationsUtil.get10BitSupportedCombinationList());
    }

    private void generateUltraHdrSupportedCombinationList() {
        this.mSurfaceCombinationsUltraHdr.addAll(GuaranteedConfigurationsUtil.getUltraHdrSupportedCombinationList());
    }

    private void generateStreamUseCaseSupportedCombinationList() {
        if (Build.VERSION.SDK_INT >= 33) {
            this.mSurfaceCombinationsStreamUseCase.addAll(GuaranteedConfigurationsUtil.getStreamUseCaseSupportedCombinationList());
        }
    }

    private void generatePreviewStabilizationSupportedCombinationList() {
        if (Build.VERSION.SDK_INT >= 33) {
            this.mPreviewStabilizationSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.getPreviewStabilizationSupportedCombinationList());
        }
    }

    private void generateHighSpeedSupportedCombinationList() {
        if (this.mHighSpeedResolver.isHighSpeedSupported()) {
            this.mHighSpeedSurfaceCombinations.clear();
            Size maxSize = this.mHighSpeedResolver.getMaxSize();
            if (maxSize != null) {
                this.mHighSpeedSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.generateHighSpeedSupportedCombinationList(maxSize, getUpdatedSurfaceSizeDefinitionByFormat(34)));
            }
        }
    }

    private void generateFcqSurfaceCombinations() {
        this.mFcqSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.generateQueryableFcqCombinations());
    }

    private void generateSurfaceSizeDefinition() {
        this.mSurfaceSizeDefinition = SurfaceSizeDefinition.create(SizeUtil.RESOLUTION_VGA, new HashMap(), this.mDisplayInfoManager.getPreviewSize(), new HashMap(), getRecordSize(), new HashMap(), new HashMap(), new HashMap(), new HashMap());
    }

    SurfaceSizeDefinition getUpdatedSurfaceSizeDefinitionByFormat(int i) {
        if (!this.mSurfaceSizeDefinitionFormats.contains(Integer.valueOf(i))) {
            updateS720pOrS1440pSizeByFormat(this.mSurfaceSizeDefinition.getS720pSizeMap(), SizeUtil.RESOLUTION_720P, i);
            updateS720pOrS1440pSizeByFormat(this.mSurfaceSizeDefinition.getS1440pSizeMap(), SizeUtil.RESOLUTION_1440P, i);
            updateMaximumSizeByFormat(this.mSurfaceSizeDefinition.getMaximumSizeMap(), i, null);
            updateMaximumSizeByFormat(this.mSurfaceSizeDefinition.getMaximum4x3SizeMap(), i, AspectRatioUtil.ASPECT_RATIO_4_3);
            updateMaximumSizeByFormat(this.mSurfaceSizeDefinition.getMaximum16x9SizeMap(), i, AspectRatioUtil.ASPECT_RATIO_16_9);
            updateUltraMaximumSizeByFormat(this.mSurfaceSizeDefinition.getUltraMaximumSizeMap(), i);
            this.mSurfaceSizeDefinitionFormats.add(Integer.valueOf(i));
        }
        return this.mSurfaceSizeDefinition;
    }

    private void updateS720pOrS1440pSizeByFormat(Map<Integer, Size> map, Size size, int i) {
        if (this.mIsConcurrentCameraModeSupported) {
            Size maxOutputSizeByFormat = getMaxOutputSizeByFormat(this.mCharacteristics.getStreamConfigurationMapCompat().toStreamConfigurationMap(), i, false, null);
            Integer numValueOf = Integer.valueOf(i);
            if (maxOutputSizeByFormat != null) {
                size = (Size) Collections.min(Arrays.asList(size, maxOutputSizeByFormat), new CompareSizesByArea());
            }
            map.put(numValueOf, size);
        }
    }

    private void updateMaximumSizeByFormat(Map<Integer, Size> map, int i, Rational rational) {
        Size maxOutputSizeByFormat = getMaxOutputSizeByFormat(this.mCharacteristics.getStreamConfigurationMapCompat().toStreamConfigurationMap(), i, true, rational);
        if (maxOutputSizeByFormat != null) {
            map.put(Integer.valueOf(i), maxOutputSizeByFormat);
        }
    }

    private void updateUltraMaximumSizeByFormat(Map<Integer, Size> map, int i) {
        StreamConfigurationMap streamConfigurationMap;
        if (Build.VERSION.SDK_INT < 31 || !this.mIsUltraHighResolutionSensorSupported || (streamConfigurationMap = (StreamConfigurationMap) this.mCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP_MAXIMUM_RESOLUTION)) == null) {
            return;
        }
        map.put(Integer.valueOf(i), getMaxOutputSizeByFormat(streamConfigurationMap, i, true, null));
    }

    private void refreshPreviewSize() {
        this.mDisplayInfoManager.refresh();
        if (this.mSurfaceSizeDefinition == null) {
            generateSurfaceSizeDefinition();
        } else {
            this.mSurfaceSizeDefinition = SurfaceSizeDefinition.create(this.mSurfaceSizeDefinition.getAnalysisSize(), this.mSurfaceSizeDefinition.getS720pSizeMap(), this.mDisplayInfoManager.getPreviewSize(), this.mSurfaceSizeDefinition.getS1440pSizeMap(), this.mSurfaceSizeDefinition.getRecordSize(), this.mSurfaceSizeDefinition.getMaximumSizeMap(), this.mSurfaceSizeDefinition.getMaximum4x3SizeMap(), this.mSurfaceSizeDefinition.getMaximum16x9SizeMap(), this.mSurfaceSizeDefinition.getUltraMaximumSizeMap());
        }
    }

    private Size getRecordSize() {
        try {
            Size recordSizeFromCamcorderProfile = getRecordSizeFromCamcorderProfile(Integer.parseInt(this.mCameraId));
            if (recordSizeFromCamcorderProfile != null) {
                return recordSizeFromCamcorderProfile;
            }
        } catch (NumberFormatException unused) {
        }
        Size recordSizeFromStreamConfigurationMap = getRecordSizeFromStreamConfigurationMap();
        return recordSizeFromStreamConfigurationMap != null ? recordSizeFromStreamConfigurationMap : SizeUtil.RESOLUTION_480P;
    }

    private Size getRecordSizeFromStreamConfigurationMap() {
        Size[] outputSizes;
        try {
            outputSizes = this.mCharacteristics.getStreamConfigurationMapCompat().toStreamConfigurationMap().getOutputSizes(MediaRecorder.class);
        } catch (Throwable unused) {
            outputSizes = null;
        }
        if (outputSizes == null) {
            return null;
        }
        Arrays.sort(outputSizes, new CompareSizesByArea(true));
        for (Size size : outputSizes) {
            if (size.getWidth() <= SizeUtil.RESOLUTION_1080P.getWidth() && size.getHeight() <= SizeUtil.RESOLUTION_1080P.getHeight()) {
                return size;
            }
        }
        return null;
    }

    private Size getRecordSizeFromCamcorderProfile(int i) {
        CamcorderProfile camcorderProfile;
        int[] iArr = {1, 13, 10, 8, 12, 6, 5, 4};
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = iArr[i2];
            if (this.mCamcorderProfileHelper.hasProfile(i, i3) && (camcorderProfile = this.mCamcorderProfileHelper.get(i, i3)) != null) {
                return new Size(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
            }
        }
        return null;
    }

    static class Api23Impl {
        private Api23Impl() {
        }

        static Size[] getHighResolutionOutputSizes(StreamConfigurationMap streamConfigurationMap, int i) {
            return streamConfigurationMap.getHighResolutionOutputSizes(i);
        }
    }

    static abstract class BestSizesAndMaxFpsForConfigs {
        abstract List<Size> getBestSizes();

        abstract List<Size> getBestSizesForStreamUseCase();

        abstract int getMaxFpsForAllSizes();

        abstract int getMaxFpsForBestSizes();

        abstract int getMaxFpsForStreamUseCase();

        BestSizesAndMaxFpsForConfigs() {
        }

        static BestSizesAndMaxFpsForConfigs of(List<Size> list, List<Size> list2, int i, int i2, int i3) {
            return new AutoValue_SupportedSurfaceCombination_BestSizesAndMaxFpsForConfigs(list, list2, i, i2, i3);
        }
    }

    public static abstract class FeatureSettings {
        abstract int getCameraMode();

        abstract int getRequiredMaxBitDepth();

        abstract Range<Integer> getTargetFpsRange();

        abstract boolean hasVideoCapture();

        abstract boolean isFeatureComboInvocation();

        abstract boolean isHighSpeedOn();

        abstract boolean isPreviewStabilizationOn();

        abstract boolean isStrictFpsRequired();

        abstract boolean isUltraHdrOn();

        abstract boolean requiresFeatureComboQuery();

        static FeatureSettings of(int i, boolean z, int i2, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, Range<Integer> range, boolean z7) {
            return new AutoValue_SupportedSurfaceCombination_FeatureSettings(i, z, i2, z2, z3, z4, z5, z6, range, z7);
        }
    }
}
