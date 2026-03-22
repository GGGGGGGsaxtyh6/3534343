package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.SupportedSurfaceCombination;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.streamsharing.StreamSharingConfig;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: StreamUseCaseUtil.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J<\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00112\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\b0\u0016H\u0007J\u0014\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0007J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!H\u0007JJ\u0010\"\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0016\u0010&\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020'0\u00162\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020'0\u0016H\u0007J\u001e\u0010)\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020+0$H\u0007J&\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\f00H\u0002JD\u00101\u001a\u00020\u001c2\u0014\u00102\u001a\u0010\u0012\u0004\u0012\u000203\u0012\u0006\u0012\u0004\u0018\u00010%0\n2\u0016\u00104\u001a\u0012\u0012\u0004\u0012\u000203\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\n2\f\u00105\u001a\b\u0012\u0004\u0012\u00020+00H\u0007Jn\u00106\u001a\u00020\u000f2\u0016\u0010&\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020'0\u00162\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020'0\u00162\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020%0\n2\u0016\u00104\u001a\u0012\u0012\u0004\u0012\u000203\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\n2\f\u00105\u001a\b\u0012\u0004\u0012\u00020+00H\u0007J!\u00107\u001a\u0004\u0018\u0001082\u0006\u00109\u001a\u0002082\b\u0010.\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u0010:J(\u0010;\u001a\u00020\u001c2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%002\u0010\u0010<\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001400H\u0007J\u0018\u0010=\u001a\u00020\u001c2\u0006\u0010>\u001a\u0002082\u0006\u0010-\u001a\u00020\fH\u0002J$\u0010?\u001a\u00020\u001c2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0002J\b\u0010B\u001a\u00020\u000fH\u0002J6\u0010C\u001a\u00020\u001c2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%002\u0010\u0010<\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014002\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Landroidx/camera/camera2/internal/StreamUseCaseUtil;", "", "<init>", "()V", "TAG", "", "STREAM_USE_CASE_STREAM_SPEC_OPTION", "Landroidx/camera/core/impl/Config$Option;", "", "STREAM_USE_CASE_TO_ELIGIBLE_CAPTURE_TYPES_MAP", "", "", "Landroidx/camera/core/impl/UseCaseConfigFactory$CaptureType;", "STREAM_USE_CASE_TO_ELIGIBLE_STREAM_SHARING_CHILDREN_TYPES_MAP", "populateSurfaceToStreamUseCaseMapping", "", "sessionConfigs", "", "Landroidx/camera/core/impl/SessionConfig;", "useCaseConfigs", "Landroidx/camera/core/impl/UseCaseConfig;", "streamUseCaseMap", "", "Landroidx/camera/core/impl/DeferrableSurface;", "getStreamSpecImplementationOptions", "Landroidx/camera/camera2/impl/Camera2ImplConfig;", "useCaseConfig", "isStreamUseCaseSupported", "", "characteristicsCompat", "Landroidx/camera/camera2/internal/compat/CameraCharacteristicsCompat;", "shouldUseStreamUseCase", "featureSettings", "Landroidx/camera/camera2/internal/SupportedSurfaceCombination$FeatureSettings;", "populateStreamUseCaseStreamSpecOptionWithInteropOverride", "attachedSurfaces", "", "Landroidx/camera/core/impl/AttachedSurfaceInfo;", "suggestedStreamSpecMap", "Landroidx/camera/core/impl/StreamSpec;", "attachedSurfaceStreamSpecMap", "areStreamUseCasesAvailableForSurfaceConfigs", "surfaceConfigs", "Landroidx/camera/core/impl/SurfaceConfig;", "isEligibleCaptureType", "captureType", "streamUseCase", "streamSharingTypes", "", "areCaptureTypesEligible", "surfaceConfigIndexAttachedSurfaceInfoMap", "", "surfaceConfigIndexUseCaseConfigMap", "surfaceConfigsWithStreamUseCase", "populateStreamUseCaseStreamSpecOptionWithSupportedSurfaceConfigs", "getUpdatedImplementationOptionsWithUseCaseStreamSpecOption", "Landroidx/camera/core/impl/Config;", "oldImplementationOptions", "(Landroidx/camera/core/impl/Config;Ljava/lang/Long;)Landroidx/camera/core/impl/Config;", "containsZslUseCase", "newUseCaseConfigs", "isZslUseCase", "config", "areStreamUseCasesAvailable", "availableStreamUseCasesSet", "streamUseCases", "throwInvalidCamera2InteropOverrideException", "isValidCamera2InteropOverride", "availableStreamUseCases", "camera-camera2_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class StreamUseCaseUtil {
    public static final StreamUseCaseUtil INSTANCE = new StreamUseCaseUtil();
    public static final Config.Option<Long> STREAM_USE_CASE_STREAM_SPEC_OPTION;
    private static final Map<Long, Set<UseCaseConfigFactory.CaptureType>> STREAM_USE_CASE_TO_ELIGIBLE_CAPTURE_TYPES_MAP;
    private static final Map<Long, Set<UseCaseConfigFactory.CaptureType>> STREAM_USE_CASE_TO_ELIGIBLE_STREAM_SHARING_CHILDREN_TYPES_MAP;
    private static final String TAG = "StreamUseCaseUtil";

    private StreamUseCaseUtil() {
    }

    static {
        Class cls = Long.TYPE;
        Intrinsics.checkNotNull(cls);
        Config.Option<Long> optionCreate = Config.Option.create("camera2.streamSpec.streamUseCase", cls);
        Intrinsics.checkNotNullExpressionValue(optionCreate, "create(...)");
        STREAM_USE_CASE_STREAM_SPEC_OPTION = optionCreate;
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        if (Build.VERSION.SDK_INT >= 33) {
            mapCreateMapBuilder.put(4L, SetsKt.setOf((Object[]) new UseCaseConfigFactory.CaptureType[]{UseCaseConfigFactory.CaptureType.PREVIEW, UseCaseConfigFactory.CaptureType.METERING_REPEATING, UseCaseConfigFactory.CaptureType.IMAGE_ANALYSIS}));
            mapCreateMapBuilder.put(1L, SetsKt.setOf((Object[]) new UseCaseConfigFactory.CaptureType[]{UseCaseConfigFactory.CaptureType.PREVIEW, UseCaseConfigFactory.CaptureType.METERING_REPEATING, UseCaseConfigFactory.CaptureType.IMAGE_ANALYSIS}));
            mapCreateMapBuilder.put(2L, SetsKt.setOf(UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE));
            mapCreateMapBuilder.put(3L, SetsKt.setOf(UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE));
        }
        STREAM_USE_CASE_TO_ELIGIBLE_CAPTURE_TYPES_MAP = MapsKt.build(mapCreateMapBuilder);
        Map mapCreateMapBuilder2 = MapsKt.createMapBuilder();
        if (Build.VERSION.SDK_INT >= 33) {
            mapCreateMapBuilder2.put(4L, SetsKt.setOf((Object[]) new UseCaseConfigFactory.CaptureType[]{UseCaseConfigFactory.CaptureType.PREVIEW, UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE, UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE}));
            mapCreateMapBuilder2.put(3L, SetsKt.setOf((Object[]) new UseCaseConfigFactory.CaptureType[]{UseCaseConfigFactory.CaptureType.PREVIEW, UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE}));
        }
        STREAM_USE_CASE_TO_ELIGIBLE_STREAM_SHARING_CHILDREN_TYPES_MAP = MapsKt.build(mapCreateMapBuilder2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void populateSurfaceToStreamUseCaseMapping(Collection<SessionConfig> sessionConfigs, Collection<? extends UseCaseConfig<?>> useCaseConfigs, Map<DeferrableSurface, Long> streamUseCaseMap) {
        Intrinsics.checkNotNullParameter(sessionConfigs, "sessionConfigs");
        Intrinsics.checkNotNullParameter(useCaseConfigs, "useCaseConfigs");
        Intrinsics.checkNotNullParameter(streamUseCaseMap, "streamUseCaseMap");
        ArrayList arrayList = new ArrayList(useCaseConfigs);
        Iterator<SessionConfig> it = sessionConfigs.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SessionConfig next = it.next();
            Config implementationOptions = next.getImplementationOptions();
            Config.Option<Long> option = STREAM_USE_CASE_STREAM_SPEC_OPTION;
            if (implementationOptions.containsOption(option) && next.getSurfaces().size() != 1) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("SessionConfig has stream use case but also contains %d surfaces, abort populateSurfaceToStreamUseCaseMapping().", Arrays.copyOf(new Object[]{Integer.valueOf(next.getSurfaces().size())}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                Logger.e(TAG, str);
                return;
            }
            if (next.getImplementationOptions().containsOption(option)) {
                int i = 0;
                for (SessionConfig sessionConfig : sessionConfigs) {
                    if (((UseCaseConfig) arrayList.get(i)).getCaptureType() == UseCaseConfigFactory.CaptureType.METERING_REPEATING) {
                        Intrinsics.checkNotNullExpressionValue(sessionConfig.getSurfaces(), "getSurfaces(...)");
                        Preconditions.checkState(!r4.isEmpty(), "MeteringRepeating should contain a surface");
                        streamUseCaseMap.put(sessionConfig.getSurfaces().get(0), 1L);
                    } else {
                        Config implementationOptions2 = sessionConfig.getImplementationOptions();
                        Config.Option<Long> option2 = STREAM_USE_CASE_STREAM_SPEC_OPTION;
                        if (implementationOptions2.containsOption(option2)) {
                            List<DeferrableSurface> surfaces = sessionConfig.getSurfaces();
                            Intrinsics.checkNotNullExpressionValue(surfaces, "getSurfaces(...)");
                            if (!surfaces.isEmpty()) {
                                DeferrableSurface deferrableSurface = sessionConfig.getSurfaces().get(0);
                                Object objRetrieveOption = sessionConfig.getImplementationOptions().retrieveOption(option2);
                                Intrinsics.checkNotNull(objRetrieveOption);
                                streamUseCaseMap.put(deferrableSurface, objRetrieveOption);
                            }
                        }
                    }
                    i++;
                }
            }
        }
        Logger.d(TAG, "populateSurfaceToStreamUseCaseMapping() - streamUseCaseMap = " + streamUseCaseMap);
    }

    @JvmStatic
    public static final Camera2ImplConfig getStreamSpecImplementationOptions(UseCaseConfig<?> useCaseConfig) {
        Intrinsics.checkNotNullParameter(useCaseConfig, "useCaseConfig");
        MutableOptionsBundle mutableOptionsBundleCreate = MutableOptionsBundle.create();
        Intrinsics.checkNotNullExpressionValue(mutableOptionsBundleCreate, "create(...)");
        if (useCaseConfig.containsOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION)) {
            mutableOptionsBundleCreate.insertOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION, useCaseConfig.retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION));
        }
        if (useCaseConfig.containsOption(UseCaseConfig.OPTION_ZSL_DISABLED)) {
            mutableOptionsBundleCreate.insertOption(UseCaseConfig.OPTION_ZSL_DISABLED, useCaseConfig.retrieveOption(UseCaseConfig.OPTION_ZSL_DISABLED));
        }
        if (useCaseConfig.containsOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE)) {
            mutableOptionsBundleCreate.insertOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE, useCaseConfig.retrieveOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE));
        }
        if (useCaseConfig.containsOption(UseCaseConfig.OPTION_INPUT_FORMAT)) {
            mutableOptionsBundleCreate.insertOption(UseCaseConfig.OPTION_INPUT_FORMAT, useCaseConfig.retrieveOption(UseCaseConfig.OPTION_INPUT_FORMAT));
        }
        return new Camera2ImplConfig(mutableOptionsBundleCreate);
    }

    @JvmStatic
    public static final boolean isStreamUseCaseSupported(CameraCharacteristicsCompat characteristicsCompat) {
        long[] jArr;
        Intrinsics.checkNotNullParameter(characteristicsCompat, "characteristicsCompat");
        return (Build.VERSION.SDK_INT < 33 || (jArr = (long[]) characteristicsCompat.get(CameraCharacteristics.SCALER_AVAILABLE_STREAM_USE_CASES)) == null || jArr.length == 0) ? false : true;
    }

    @JvmStatic
    public static final boolean shouldUseStreamUseCase(SupportedSurfaceCombination.FeatureSettings featureSettings) {
        Intrinsics.checkNotNullParameter(featureSettings, "featureSettings");
        return featureSettings.getCameraMode() == 0 && featureSettings.getRequiredMaxBitDepth() == 8 && !featureSettings.isHighSpeedOn();
    }

    @JvmStatic
    public static final boolean populateStreamUseCaseStreamSpecOptionWithInteropOverride(CameraCharacteristicsCompat characteristicsCompat, List<AttachedSurfaceInfo> attachedSurfaces, Map<UseCaseConfig<?>, StreamSpec> suggestedStreamSpecMap, Map<AttachedSurfaceInfo, StreamSpec> attachedSurfaceStreamSpecMap) {
        Intrinsics.checkNotNullParameter(characteristicsCompat, "characteristicsCompat");
        Intrinsics.checkNotNullParameter(attachedSurfaces, "attachedSurfaces");
        Intrinsics.checkNotNullParameter(suggestedStreamSpecMap, "suggestedStreamSpecMap");
        Intrinsics.checkNotNullParameter(attachedSurfaceStreamSpecMap, "attachedSurfaceStreamSpecMap");
        if (Build.VERSION.SDK_INT < 33) {
            return false;
        }
        ArrayList<UseCaseConfig<?>> arrayList = new ArrayList(suggestedStreamSpecMap.keySet());
        Iterator<AttachedSurfaceInfo> it = attachedSurfaces.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next().getImplementationOptions());
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Preconditions.checkNotNull(((StreamSpec) Preconditions.checkNotNull(suggestedStreamSpecMap.get((UseCaseConfig) it2.next()))).getImplementationOptions());
        }
        long[] jArr = (long[]) characteristicsCompat.get(CameraCharacteristics.SCALER_AVAILABLE_STREAM_USE_CASES);
        if (jArr != null && jArr.length != 0) {
            HashSet hashSet = new HashSet();
            for (long j : jArr) {
                hashSet.add(Long.valueOf(j));
            }
            if (INSTANCE.isValidCamera2InteropOverride(attachedSurfaces, arrayList, hashSet)) {
                for (AttachedSurfaceInfo attachedSurfaceInfo : attachedSurfaces) {
                    Config implementationOptions = attachedSurfaceInfo.getImplementationOptions();
                    StreamUseCaseUtil streamUseCaseUtil = INSTANCE;
                    Intrinsics.checkNotNull(implementationOptions);
                    Config updatedImplementationOptionsWithUseCaseStreamSpecOption = streamUseCaseUtil.getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(implementationOptions, (Long) implementationOptions.retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION));
                    if (updatedImplementationOptionsWithUseCaseStreamSpecOption != null) {
                        attachedSurfaceStreamSpecMap.put(attachedSurfaceInfo, attachedSurfaceInfo.toStreamSpec(updatedImplementationOptionsWithUseCaseStreamSpecOption));
                    }
                }
                for (UseCaseConfig<?> useCaseConfig : arrayList) {
                    StreamSpec streamSpec = suggestedStreamSpecMap.get(useCaseConfig);
                    Intrinsics.checkNotNull(streamSpec);
                    Config implementationOptions2 = streamSpec.getImplementationOptions();
                    StreamUseCaseUtil streamUseCaseUtil2 = INSTANCE;
                    Intrinsics.checkNotNull(implementationOptions2);
                    Config updatedImplementationOptionsWithUseCaseStreamSpecOption2 = streamUseCaseUtil2.getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(implementationOptions2, (Long) implementationOptions2.retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION));
                    if (updatedImplementationOptionsWithUseCaseStreamSpecOption2 != null) {
                        suggestedStreamSpecMap.put(useCaseConfig, streamSpec.toBuilder().setImplementationOptions(updatedImplementationOptionsWithUseCaseStreamSpecOption2).build());
                    }
                }
                return true;
            }
        }
        return false;
    }

    @JvmStatic
    public static final boolean areStreamUseCasesAvailableForSurfaceConfigs(CameraCharacteristicsCompat characteristicsCompat, List<SurfaceConfig> surfaceConfigs) {
        long[] jArr;
        Intrinsics.checkNotNullParameter(characteristicsCompat, "characteristicsCompat");
        Intrinsics.checkNotNullParameter(surfaceConfigs, "surfaceConfigs");
        if (Build.VERSION.SDK_INT < 33 || (jArr = (long[]) characteristicsCompat.get(CameraCharacteristics.SCALER_AVAILABLE_STREAM_USE_CASES)) == null || jArr.length == 0) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (long j : jArr) {
            hashSet.add(Long.valueOf(j));
        }
        Iterator<SurfaceConfig> it = surfaceConfigs.iterator();
        while (it.hasNext()) {
            if (!hashSet.contains(Long.valueOf(it.next().getStreamUseCase().getValue()))) {
                return false;
            }
        }
        return true;
    }

    private final boolean isEligibleCaptureType(UseCaseConfigFactory.CaptureType captureType, long streamUseCase, List<? extends UseCaseConfigFactory.CaptureType> streamSharingTypes) {
        if (Build.VERSION.SDK_INT < 33) {
            return false;
        }
        if (captureType == UseCaseConfigFactory.CaptureType.STREAM_SHARING) {
            Map<Long, Set<UseCaseConfigFactory.CaptureType>> map = STREAM_USE_CASE_TO_ELIGIBLE_STREAM_SHARING_CHILDREN_TYPES_MAP;
            if (!map.containsKey(Long.valueOf(streamUseCase))) {
                return false;
            }
            Set<UseCaseConfigFactory.CaptureType> set = map.get(Long.valueOf(streamUseCase));
            Intrinsics.checkNotNull(set);
            Set<UseCaseConfigFactory.CaptureType> set2 = set;
            if (streamSharingTypes.size() != set2.size()) {
                return false;
            }
            Iterator<? extends UseCaseConfigFactory.CaptureType> it = streamSharingTypes.iterator();
            while (it.hasNext()) {
                if (!set2.contains(it.next())) {
                    return false;
                }
            }
            return true;
        }
        Map<Long, Set<UseCaseConfigFactory.CaptureType>> map2 = STREAM_USE_CASE_TO_ELIGIBLE_CAPTURE_TYPES_MAP;
        if (map2.containsKey(Long.valueOf(streamUseCase))) {
            Set<UseCaseConfigFactory.CaptureType> set3 = map2.get(Long.valueOf(streamUseCase));
            Intrinsics.checkNotNull(set3);
            if (set3.contains(captureType)) {
                return true;
            }
        }
        return false;
    }

    @JvmStatic
    public static final boolean areCaptureTypesEligible(Map<Integer, ? extends AttachedSurfaceInfo> surfaceConfigIndexAttachedSurfaceInfoMap, Map<Integer, ? extends UseCaseConfig<?>> surfaceConfigIndexUseCaseConfigMap, List<SurfaceConfig> surfaceConfigsWithStreamUseCase) {
        List<UseCaseConfigFactory.CaptureType> listEmptyList;
        UseCaseConfigFactory.CaptureType captureType;
        Intrinsics.checkNotNullParameter(surfaceConfigIndexAttachedSurfaceInfoMap, "surfaceConfigIndexAttachedSurfaceInfoMap");
        Intrinsics.checkNotNullParameter(surfaceConfigIndexUseCaseConfigMap, "surfaceConfigIndexUseCaseConfigMap");
        Intrinsics.checkNotNullParameter(surfaceConfigsWithStreamUseCase, "surfaceConfigsWithStreamUseCase");
        int size = surfaceConfigsWithStreamUseCase.size();
        for (int i = 0; i < size; i++) {
            long value = surfaceConfigsWithStreamUseCase.get(i).getStreamUseCase().getValue();
            if (surfaceConfigIndexAttachedSurfaceInfoMap.containsKey(Integer.valueOf(i))) {
                AttachedSurfaceInfo attachedSurfaceInfo = surfaceConfigIndexAttachedSurfaceInfoMap.get(Integer.valueOf(i));
                StreamUseCaseUtil streamUseCaseUtil = INSTANCE;
                Intrinsics.checkNotNull(attachedSurfaceInfo);
                if (attachedSurfaceInfo.getCaptureTypes().size() == 1) {
                    captureType = attachedSurfaceInfo.getCaptureTypes().get(0);
                } else {
                    captureType = UseCaseConfigFactory.CaptureType.STREAM_SHARING;
                }
                Intrinsics.checkNotNull(captureType);
                List<UseCaseConfigFactory.CaptureType> captureTypes = attachedSurfaceInfo.getCaptureTypes();
                Intrinsics.checkNotNullExpressionValue(captureTypes, "getCaptureTypes(...)");
                if (!streamUseCaseUtil.isEligibleCaptureType(captureType, value, captureTypes)) {
                    return false;
                }
            } else if (surfaceConfigIndexUseCaseConfigMap.containsKey(Integer.valueOf(i))) {
                UseCaseConfig<?> useCaseConfig = surfaceConfigIndexUseCaseConfigMap.get(Integer.valueOf(i));
                Intrinsics.checkNotNull(useCaseConfig);
                UseCaseConfig<?> useCaseConfig2 = useCaseConfig;
                StreamUseCaseUtil streamUseCaseUtil2 = INSTANCE;
                UseCaseConfigFactory.CaptureType captureType2 = useCaseConfig2.getCaptureType();
                Intrinsics.checkNotNullExpressionValue(captureType2, "getCaptureType(...)");
                if (useCaseConfig2.getCaptureType() == UseCaseConfigFactory.CaptureType.STREAM_SHARING) {
                    listEmptyList = ((StreamSharingConfig) useCaseConfig2).getCaptureTypes();
                    Intrinsics.checkNotNullExpressionValue(listEmptyList, "getCaptureTypes(...)");
                } else {
                    listEmptyList = CollectionsKt.emptyList();
                }
                if (!streamUseCaseUtil2.isEligibleCaptureType(captureType2, value, listEmptyList)) {
                    return false;
                }
            } else {
                throw new AssertionError("SurfaceConfig does not map to any use case");
            }
        }
        return true;
    }

    @JvmStatic
    public static final void populateStreamUseCaseStreamSpecOptionWithSupportedSurfaceConfigs(Map<UseCaseConfig<?>, StreamSpec> suggestedStreamSpecMap, Map<AttachedSurfaceInfo, StreamSpec> attachedSurfaceStreamSpecMap, Map<Integer, ? extends AttachedSurfaceInfo> surfaceConfigIndexAttachedSurfaceInfoMap, Map<Integer, ? extends UseCaseConfig<?>> surfaceConfigIndexUseCaseConfigMap, List<SurfaceConfig> surfaceConfigsWithStreamUseCase) {
        Intrinsics.checkNotNullParameter(suggestedStreamSpecMap, "suggestedStreamSpecMap");
        Intrinsics.checkNotNullParameter(attachedSurfaceStreamSpecMap, "attachedSurfaceStreamSpecMap");
        Intrinsics.checkNotNullParameter(surfaceConfigIndexAttachedSurfaceInfoMap, "surfaceConfigIndexAttachedSurfaceInfoMap");
        Intrinsics.checkNotNullParameter(surfaceConfigIndexUseCaseConfigMap, "surfaceConfigIndexUseCaseConfigMap");
        Intrinsics.checkNotNullParameter(surfaceConfigsWithStreamUseCase, "surfaceConfigsWithStreamUseCase");
        int size = surfaceConfigsWithStreamUseCase.size();
        for (int i = 0; i < size; i++) {
            long value = surfaceConfigsWithStreamUseCase.get(i).getStreamUseCase().getValue();
            if (surfaceConfigIndexAttachedSurfaceInfoMap.containsKey(Integer.valueOf(i))) {
                AttachedSurfaceInfo attachedSurfaceInfo = surfaceConfigIndexAttachedSurfaceInfoMap.get(Integer.valueOf(i));
                Intrinsics.checkNotNull(attachedSurfaceInfo);
                Config implementationOptions = attachedSurfaceInfo.getImplementationOptions();
                StreamUseCaseUtil streamUseCaseUtil = INSTANCE;
                Intrinsics.checkNotNull(implementationOptions);
                Config updatedImplementationOptionsWithUseCaseStreamSpecOption = streamUseCaseUtil.getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(implementationOptions, Long.valueOf(value));
                if (updatedImplementationOptionsWithUseCaseStreamSpecOption != null) {
                    attachedSurfaceStreamSpecMap.put(attachedSurfaceInfo, attachedSurfaceInfo.toStreamSpec(updatedImplementationOptionsWithUseCaseStreamSpecOption));
                }
            } else if (surfaceConfigIndexUseCaseConfigMap.containsKey(Integer.valueOf(i))) {
                UseCaseConfig<?> useCaseConfig = surfaceConfigIndexUseCaseConfigMap.get(Integer.valueOf(i));
                Intrinsics.checkNotNull(useCaseConfig);
                UseCaseConfig<?> useCaseConfig2 = useCaseConfig;
                StreamSpec streamSpec = suggestedStreamSpecMap.get(useCaseConfig2);
                Intrinsics.checkNotNull(streamSpec);
                Config implementationOptions2 = streamSpec.getImplementationOptions();
                StreamUseCaseUtil streamUseCaseUtil2 = INSTANCE;
                Intrinsics.checkNotNull(implementationOptions2);
                Config updatedImplementationOptionsWithUseCaseStreamSpecOption2 = streamUseCaseUtil2.getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(implementationOptions2, Long.valueOf(value));
                if (updatedImplementationOptionsWithUseCaseStreamSpecOption2 != null) {
                    StreamSpec streamSpecBuild = streamSpec.toBuilder().setImplementationOptions(updatedImplementationOptionsWithUseCaseStreamSpecOption2).build();
                    Intrinsics.checkNotNullExpressionValue(streamSpecBuild, "build(...)");
                    suggestedStreamSpecMap.put(useCaseConfig2, streamSpecBuild);
                }
            } else {
                throw new AssertionError("SurfaceConfig does not map to any use case");
            }
        }
    }

    private final Config getUpdatedImplementationOptionsWithUseCaseStreamSpecOption(Config oldImplementationOptions, Long streamUseCase) {
        Config.Option<Long> option = STREAM_USE_CASE_STREAM_SPEC_OPTION;
        if (oldImplementationOptions.containsOption(option) && Intrinsics.areEqual(oldImplementationOptions.retrieveOption(option), streamUseCase)) {
            return null;
        }
        MutableOptionsBundle mutableOptionsBundleFrom = MutableOptionsBundle.from(oldImplementationOptions);
        Intrinsics.checkNotNullExpressionValue(mutableOptionsBundleFrom, "from(...)");
        mutableOptionsBundleFrom.insertOption(option, streamUseCase);
        return new Camera2ImplConfig(mutableOptionsBundleFrom);
    }

    @JvmStatic
    public static final boolean containsZslUseCase(List<? extends AttachedSurfaceInfo> attachedSurfaces, List<? extends UseCaseConfig<?>> newUseCaseConfigs) {
        Intrinsics.checkNotNullParameter(attachedSurfaces, "attachedSurfaces");
        Intrinsics.checkNotNullParameter(newUseCaseConfigs, "newUseCaseConfigs");
        for (AttachedSurfaceInfo attachedSurfaceInfo : attachedSurfaces) {
            List<UseCaseConfigFactory.CaptureType> captureTypes = attachedSurfaceInfo.getCaptureTypes();
            Intrinsics.checkNotNullExpressionValue(captureTypes, "getCaptureTypes(...)");
            UseCaseConfigFactory.CaptureType captureType = captureTypes.get(0);
            StreamUseCaseUtil streamUseCaseUtil = INSTANCE;
            Config implementationOptions = attachedSurfaceInfo.getImplementationOptions();
            Intrinsics.checkNotNull(implementationOptions);
            Intrinsics.checkNotNull(captureType);
            if (streamUseCaseUtil.isZslUseCase(implementationOptions, captureType)) {
                return true;
            }
        }
        for (UseCaseConfig<?> useCaseConfig : newUseCaseConfigs) {
            StreamUseCaseUtil streamUseCaseUtil2 = INSTANCE;
            UseCaseConfig<?> useCaseConfig2 = useCaseConfig;
            UseCaseConfigFactory.CaptureType captureType2 = useCaseConfig.getCaptureType();
            Intrinsics.checkNotNullExpressionValue(captureType2, "getCaptureType(...)");
            if (streamUseCaseUtil2.isZslUseCase(useCaseConfig2, captureType2)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isZslUseCase(Config config, UseCaseConfigFactory.CaptureType captureType) {
        Object objRetrieveOption = config.retrieveOption(UseCaseConfig.OPTION_ZSL_DISABLED, false);
        Intrinsics.checkNotNull(objRetrieveOption);
        if (((Boolean) objRetrieveOption).booleanValue() || !config.containsOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE)) {
            return false;
        }
        Object objRetrieveOption2 = config.retrieveOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE);
        Intrinsics.checkNotNull(objRetrieveOption2);
        return TemplateTypeUtil.getSessionConfigTemplateType(captureType, ((Number) objRetrieveOption2).intValue()) == 5;
    }

    private final boolean areStreamUseCasesAvailable(Set<Long> availableStreamUseCasesSet, Set<Long> streamUseCases) {
        Iterator<Long> it = streamUseCases.iterator();
        while (it.hasNext()) {
            if (!availableStreamUseCasesSet.contains(Long.valueOf(it.next().longValue()))) {
                return false;
            }
        }
        return true;
    }

    private final void throwInvalidCamera2InteropOverrideException() {
        throw new IllegalArgumentException("Either all use cases must have non-default stream use case assigned or none should have it");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean isValidCamera2InteropOverride(List<? extends AttachedSurfaceInfo> attachedSurfaces, List<? extends UseCaseConfig<?>> newUseCaseConfigs, Set<Long> availableStreamUseCases) {
        boolean z;
        boolean z2;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<? extends AttachedSurfaceInfo> it = attachedSurfaces.iterator();
        if (it.hasNext()) {
            AttachedSurfaceInfo next = it.next();
            Config implementationOptions = next.getImplementationOptions();
            Intrinsics.checkNotNull(implementationOptions);
            if (implementationOptions.containsOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION)) {
                Config implementationOptions2 = next.getImplementationOptions();
                Intrinsics.checkNotNull(implementationOptions2);
                Object objRetrieveOption = implementationOptions2.retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION);
                Intrinsics.checkNotNull(objRetrieveOption);
                if (((Number) objRetrieveOption).longValue() != 0) {
                    z = true;
                    z2 = false;
                }
            } else {
                z2 = true;
                z = false;
            }
        } else {
            z = false;
            z2 = false;
        }
        for (UseCaseConfig<?> useCaseConfig : newUseCaseConfigs) {
            if (useCaseConfig.containsOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION)) {
                Object objRetrieveOption2 = useCaseConfig.retrieveOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION);
                Intrinsics.checkNotNull(objRetrieveOption2);
                long jLongValue = ((Number) objRetrieveOption2).longValue();
                if (jLongValue != 0) {
                    if (z2) {
                        throwInvalidCamera2InteropOverrideException();
                    }
                    linkedHashSet.add(Long.valueOf(jLongValue));
                    z = true;
                } else if (z) {
                    throwInvalidCamera2InteropOverrideException();
                }
            } else if (z) {
                throwInvalidCamera2InteropOverrideException();
            }
            z2 = true;
        }
        return !z2 && areStreamUseCasesAvailable(availableStreamUseCases, linkedHashSet);
    }
}
