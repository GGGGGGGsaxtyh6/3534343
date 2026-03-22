package androidx.camera.camera2.impl;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.DynamicRangeProfiles;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.media.ImageReader;
import android.media.MediaCodec;
import android.util.Size;
import android.view.SurfaceHolder;
import androidx.camera.camera2.internal.CameraUnavailableExceptionHelper;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.camera2.internal.compat.params.DynamicRangeConversions;
import androidx.camera.camera2.internal.compat.params.DynamicRangesCompat;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.Logger;
import androidx.camera.core.featuregroup.impl.FeatureCombinationQuery;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompat;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompatFactory;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.firestore.model.Values;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeatureCombinationQueryImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 B2\u00020\u0001:\u0002ABB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020'H\u0016J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010&\u001a\u00020'H\u0002J\f\u0010+\u001a\u00020**\u00020,H\u0003J\f\u0010-\u001a\u00020**\u00020,H\u0003J\u0014\u0010.\u001a\u00020/*\u0002002\u0006\u00101\u001a\u00020,H\u0002J \u00102\u001a\u0004\u0018\u0001032\f\u00104\u001a\b\u0012\u0004\u0012\u0002000)2\u0006\u00105\u001a\u00020'H\u0002J\f\u00106\u001a\u00020\u0005*\u00020'H\u0002J\u000f\u00107\u001a\u0004\u0018\u00010\"H\u0002¢\u0006\u0002\u00108JF\u00109\u001a\u0002H:\"\f\b\u0000\u0010;*\u00060<j\u0002`=\"\u0004\b\u0001\u0010:*\b\u0012\u0004\u0012\u0002H;0)2\u0018\u0010>\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H;0)\u0012\u0004\u0012\u0002H:0?H\u0082\b¢\u0006\u0002\u0010@R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\u00070\u000b¢\u0006\u0002\b\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0011\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u001b\u001a\t\u0018\u00010\u001c¢\u0006\u0002\b\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u0010\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u0010\u001a\u0004\b!\u0010#¨\u0006C"}, d2 = {"Landroidx/camera/camera2/impl/FeatureCombinationQueryImpl;", "Landroidx/camera/core/featuregroup/impl/FeatureCombinationQuery;", "context", "Landroid/content/Context;", "cameraId", "", "cameraManagerCompat", "Landroidx/camera/camera2/internal/compat/CameraManagerCompat;", "<init>", "(Landroid/content/Context;Ljava/lang/String;Landroidx/camera/camera2/internal/compat/CameraManagerCompat;)V", "cameraDeviceSetupCompat", "Landroidx/camera/featurecombinationquery/CameraDeviceSetupCompat;", "Lorg/jspecify/annotations/NonNull;", "getCameraDeviceSetupCompat", "()Landroidx/camera/featurecombinationquery/CameraDeviceSetupCompat;", "cameraDeviceSetupCompat$delegate", "Lkotlin/Lazy;", "cameraDeviceSetup", "Landroid/hardware/camera2/CameraDevice$CameraDeviceSetup;", "getCameraDeviceSetup", "()Landroid/hardware/camera2/CameraDevice$CameraDeviceSetup;", "cameraDeviceSetup$delegate", "cameraCharacteristics", "Landroidx/camera/camera2/internal/compat/CameraCharacteristicsCompat;", "getCameraCharacteristics", "()Landroidx/camera/camera2/internal/compat/CameraCharacteristicsCompat;", "cameraCharacteristics$delegate", "dynamicRangeProfiles", "Landroid/hardware/camera2/params/DynamicRangeProfiles;", "Lorg/jspecify/annotations/Nullable;", "getDynamicRangeProfiles", "()Landroid/hardware/camera2/params/DynamicRangeProfiles;", "dynamicRangeProfiles$delegate", "isDeferredSurfaceSupported", "", "()Z", "isDeferredSurfaceSupported$delegate", "isSupported", "sessionConfig", "Landroidx/camera/core/impl/SessionConfig;", "createOutputConfigurations", "", "Landroidx/camera/camera2/impl/FeatureCombinationQueryImpl$CloseableOutputConfiguration;", "toConcreteOutputConfiguration", "Landroidx/camera/core/impl/SessionConfig$OutputConfig;", "toDeferredOutputConfiguration", "applyDynamicRange", "", "Landroid/hardware/camera2/params/OutputConfiguration;", "outputConfig", "getCamera2SessionConfiguration", "Landroid/hardware/camera2/params/SessionConfiguration;", "outputConfigs", "cameraXSessionConfig", "toLogString", "hasPlayServicesDependency", "()Ljava/lang/Boolean;", "use", "R", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "block", "Lkotlin/Function1;", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "CloseableOutputConfiguration", "Companion", "camera-camera2_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FeatureCombinationQueryImpl implements FeatureCombinationQuery {
    private static final String FCQ_PLAY_SERVICES_IMPL_KEY = "androidx.camera.featurecombinationquery.PLAY_SERVICES_IMPL_PROVIDER_KEY";
    private static final String TAG = "FeatureCombinationQueryImpl";

    /* JADX INFO: renamed from: cameraCharacteristics$delegate, reason: from kotlin metadata */
    private final Lazy cameraCharacteristics;

    /* JADX INFO: renamed from: cameraDeviceSetup$delegate, reason: from kotlin metadata */
    private final Lazy cameraDeviceSetup;

    /* JADX INFO: renamed from: cameraDeviceSetupCompat$delegate, reason: from kotlin metadata */
    private final Lazy cameraDeviceSetupCompat;
    private final String cameraId;
    private final CameraManagerCompat cameraManagerCompat;
    private final Context context;

    /* JADX INFO: renamed from: dynamicRangeProfiles$delegate, reason: from kotlin metadata */
    private final Lazy dynamicRangeProfiles;

    /* JADX INFO: renamed from: isDeferredSurfaceSupported$delegate, reason: from kotlin metadata */
    private final Lazy isDeferredSurfaceSupported;
    private static final FeatureCombinationQueryImpl$Companion$NO_OP_CALLBACK$1 NO_OP_CALLBACK = new CameraCaptureSession.StateCallback() { // from class: androidx.camera.camera2.impl.FeatureCombinationQueryImpl$Companion$NO_OP_CALLBACK$1
        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
        }
    };

    public FeatureCombinationQueryImpl(Context context, String cameraId, CameraManagerCompat cameraManagerCompat) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cameraId, "cameraId");
        Intrinsics.checkNotNullParameter(cameraManagerCompat, "cameraManagerCompat");
        this.context = context;
        this.cameraId = cameraId;
        this.cameraManagerCompat = cameraManagerCompat;
        this.cameraDeviceSetupCompat = LazyKt.lazy(new Function0() { // from class: androidx.camera.camera2.impl.FeatureCombinationQueryImpl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                FeatureCombinationQueryImpl featureCombinationQueryImpl = this.f$0;
                return new CameraDeviceSetupCompatFactory(featureCombinationQueryImpl.context).getCameraDeviceSetupCompat(featureCombinationQueryImpl.cameraId);
            }
        });
        this.cameraDeviceSetup = LazyKt.lazy(new Function0() { // from class: androidx.camera.camera2.impl.FeatureCombinationQueryImpl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FeatureCombinationQueryImpl.cameraDeviceSetup_delegate$lambda$1(this.f$0);
            }
        });
        this.cameraCharacteristics = LazyKt.lazy(new Function0() { // from class: androidx.camera.camera2.impl.FeatureCombinationQueryImpl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FeatureCombinationQueryImpl.cameraCharacteristics_delegate$lambda$2(this.f$0);
            }
        });
        this.dynamicRangeProfiles = LazyKt.lazy(new Function0() { // from class: androidx.camera.camera2.impl.FeatureCombinationQueryImpl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DynamicRangesCompat.fromCameraCharacteristics(this.f$0.getCameraCharacteristics()).toDynamicRangeProfiles();
            }
        });
        this.isDeferredSurfaceSupported = LazyKt.lazy(new Function0() { // from class: androidx.camera.camera2.impl.FeatureCombinationQueryImpl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(Intrinsics.areEqual((Object) this.f$0.hasPlayServicesDependency(), (Object) false));
            }
        });
    }

    private final CameraDeviceSetupCompat getCameraDeviceSetupCompat() {
        return (CameraDeviceSetupCompat) this.cameraDeviceSetupCompat.getValue();
    }

    private final CameraDevice.CameraDeviceSetup getCameraDeviceSetup() {
        return (CameraDevice.CameraDeviceSetup) this.cameraDeviceSetup.getValue();
    }

    static final CameraDevice.CameraDeviceSetup cameraDeviceSetup_delegate$lambda$1(FeatureCombinationQueryImpl featureCombinationQueryImpl) {
        if (featureCombinationQueryImpl.cameraManagerCompat.unwrap().isCameraDeviceSetupSupported(featureCombinationQueryImpl.cameraId)) {
            return featureCombinationQueryImpl.cameraManagerCompat.unwrap().getCameraDeviceSetup(featureCombinationQueryImpl.cameraId);
        }
        return null;
    }

    private final CameraCharacteristicsCompat getCameraCharacteristics() {
        return (CameraCharacteristicsCompat) this.cameraCharacteristics.getValue();
    }

    static final CameraCharacteristicsCompat cameraCharacteristics_delegate$lambda$2(FeatureCombinationQueryImpl featureCombinationQueryImpl) throws CameraUnavailableException {
        try {
            CameraCharacteristicsCompat cameraCharacteristicsCompat = featureCombinationQueryImpl.cameraManagerCompat.getCameraCharacteristicsCompat(featureCombinationQueryImpl.cameraId);
            Intrinsics.checkNotNull(cameraCharacteristicsCompat);
            return cameraCharacteristicsCompat;
        } catch (CameraAccessExceptionCompat e) {
            throw CameraUnavailableExceptionHelper.createFrom(e);
        }
    }

    private final DynamicRangeProfiles getDynamicRangeProfiles() {
        return (DynamicRangeProfiles) this.dynamicRangeProfiles.getValue();
    }

    private final boolean isDeferredSurfaceSupported() {
        return ((Boolean) this.isDeferredSurfaceSupported.getValue()).booleanValue();
    }

    @Override // androidx.camera.core.featuregroup.impl.FeatureCombinationQuery
    public boolean isSupported(SessionConfig sessionConfig) throws Exception {
        Intrinsics.checkNotNullParameter(sessionConfig, "sessionConfig");
        List<CloseableOutputConfiguration> listCreateOutputConfigurations = createOutputConfigurations(sessionConfig);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listCreateOutputConfigurations, 10));
        Iterator<T> it = listCreateOutputConfigurations.iterator();
        while (it.hasNext()) {
            arrayList.add(((CloseableOutputConfiguration) it.next()).getValue());
        }
        SessionConfiguration camera2SessionConfiguration = getCamera2SessionConfiguration(arrayList, sessionConfig);
        if (camera2SessionConfiguration == null) {
            return false;
        }
        int supported = getCameraDeviceSetupCompat().isSessionConfigurationSupported(camera2SessionConfiguration).getSupported();
        Logger.d(TAG, "isSupported: supported = " + supported + " for session config with " + toLogString(sessionConfig));
        boolean z = supported == 1;
        Iterator<T> it2 = listCreateOutputConfigurations.iterator();
        while (it2.hasNext()) {
            UByte$$ExternalSyntheticBackport0.m846m(it2.next());
        }
        return z;
    }

    private final List<CloseableOutputConfiguration> createOutputConfigurations(SessionConfig sessionConfig) {
        CloseableOutputConfiguration concreteOutputConfiguration;
        List<SessionConfig.OutputConfig> outputConfigs = sessionConfig.getOutputConfigs();
        Intrinsics.checkNotNullExpressionValue(outputConfigs, "getOutputConfigs(...)");
        List<SessionConfig.OutputConfig> list = outputConfigs;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (SessionConfig.OutputConfig outputConfig : list) {
            if (isDeferredSurfaceSupported()) {
                Intrinsics.checkNotNull(outputConfig);
                concreteOutputConfiguration = toDeferredOutputConfiguration(outputConfig);
            } else {
                Intrinsics.checkNotNull(outputConfig);
                concreteOutputConfiguration = toConcreteOutputConfiguration(outputConfig);
            }
            if (outputConfig.getSurface().getContainerClass() != null) {
                applyDynamicRange(concreteOutputConfiguration.getValue(), outputConfig);
            }
            arrayList.add(concreteOutputConfiguration);
        }
        return arrayList;
    }

    private final CloseableOutputConfiguration toConcreteOutputConfiguration(SessionConfig.OutputConfig outputConfig) {
        long j;
        Class<?> containerClass = outputConfig.getSurface().getContainerClass();
        if (Intrinsics.areEqual(containerClass, MediaCodec.class)) {
            j = 65536;
        } else if (Intrinsics.areEqual(containerClass, SurfaceHolder.class)) {
            j = 2048;
        } else {
            j = Intrinsics.areEqual(containerClass, SurfaceTexture.class) ? 256L : 0L;
        }
        long j2 = j;
        Logger.d(TAG, "toConcreteOutputConfiguration: surface containerClass = " + outputConfig.getSurface().getContainerClass() + ", usageFlag = " + j2);
        ImageReader imageReaderNewInstance = ImageReader.newInstance(outputConfig.getSurface().getPrescribedSize().getWidth(), outputConfig.getSurface().getPrescribedSize().getHeight(), outputConfig.getSurface().getPrescribedStreamFormat(), 1, j2);
        Intrinsics.checkNotNullExpressionValue(imageReaderNewInstance, "newInstance(...)");
        return new CloseableOutputConfiguration(new OutputConfiguration(imageReaderNewInstance.getSurface()), imageReaderNewInstance);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final CloseableOutputConfiguration toDeferredOutputConfiguration(SessionConfig.OutputConfig outputConfig) {
        OutputConfiguration outputConfiguration;
        Class<?> containerClass = outputConfig.getSurface().getContainerClass();
        Logger.d(TAG, "toDeferredOutputConfiguration: surface containerClass = " + outputConfig.getSurface().getContainerClass());
        if (containerClass != null) {
            Size prescribedSize = outputConfig.getSurface().getPrescribedSize();
            if (prescribedSize == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            outputConfiguration = new OutputConfiguration(prescribedSize, containerClass);
        } else {
            outputConfiguration = new OutputConfiguration(outputConfig.getSurface().getPrescribedStreamFormat(), outputConfig.getSurface().getPrescribedSize());
        }
        return new CloseableOutputConfiguration(outputConfiguration, null, 2, 0 == true ? 1 : 0);
    }

    private final void applyDynamicRange(OutputConfiguration outputConfiguration, SessionConfig.OutputConfig outputConfig) {
        DynamicRangeProfiles dynamicRangeProfiles = getDynamicRangeProfiles();
        if (dynamicRangeProfiles == null) {
            return;
        }
        Long lDynamicRangeToFirstSupportedProfile = DynamicRangeConversions.dynamicRangeToFirstSupportedProfile(outputConfig.getDynamicRange(), dynamicRangeProfiles);
        if (lDynamicRangeToFirstSupportedProfile != null) {
            outputConfiguration.setDynamicRangeProfile(lDynamicRangeToFirstSupportedProfile.longValue());
            return;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    private final SessionConfiguration getCamera2SessionConfiguration(List<OutputConfiguration> outputConfigs, SessionConfig cameraXSessionConfig) {
        SessionConfiguration sessionConfiguration = new SessionConfiguration(0, outputConfigs, CameraXExecutors.directExecutor(), NO_OP_CALLBACK);
        CameraDevice.CameraDeviceSetup cameraDeviceSetup = getCameraDeviceSetup();
        if (cameraDeviceSetup == null) {
            return null;
        }
        CaptureRequest.Builder builderCreateCaptureRequest = cameraDeviceSetup.createCaptureRequest(cameraXSessionConfig.getTemplateType());
        builderCreateCaptureRequest.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, cameraXSessionConfig.getExpectedFrameRateRange());
        if (cameraXSessionConfig.getRepeatingCaptureConfig().getPreviewStabilizationMode() == 2) {
            builderCreateCaptureRequest.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 2);
        }
        sessionConfiguration.setSessionParameters(builderCreateCaptureRequest.build());
        return sessionConfiguration;
    }

    private final String toLogString(SessionConfig sessionConfig) {
        StringBuilder sb = new StringBuilder("sessionParameters=[");
        sb.append("fpsRange=" + sessionConfig.getExpectedFrameRateRange());
        sb.append(", previewStabilizationMode=" + sessionConfig.getRepeatingCaptureConfig().getPreviewStabilizationMode());
        sb.append("], outputConfigurations=[");
        List<SessionConfig.OutputConfig> outputConfigs = sessionConfig.getOutputConfigs();
        Intrinsics.checkNotNullExpressionValue(outputConfigs, "getOutputConfigs(...)");
        int i = 0;
        for (Object obj : outputConfigs) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            SessionConfig.OutputConfig outputConfig = (SessionConfig.OutputConfig) obj;
            if (i != 0) {
                sb.append(",");
            }
            sb.append("{format=" + outputConfig.getSurface().getPrescribedStreamFormat() + ", size=" + outputConfig.getSurface().getPrescribedSize() + ", dynamicRange=" + outputConfig.getDynamicRange() + ", class=" + outputConfig.getSurface().getContainerClass() + '}');
            i = i2;
        }
        sb.append("]");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final Boolean hasPlayServicesDependency() {
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 132);
            if (packageInfo.services == null) {
                return false;
            }
            Iterator it = ArrayIteratorKt.iterator(packageInfo.services);
            while (it.hasNext()) {
                ServiceInfo serviceInfo = (ServiceInfo) it.next();
                if (serviceInfo.metaData != null && serviceInfo.metaData.getString(FCQ_PLAY_SERVICES_IMPL_KEY) != null) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: compiled from: FeatureCombinationQueryImpl.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0006HÂ\u0003J\u001f\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/impl/FeatureCombinationQueryImpl$CloseableOutputConfiguration;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", Values.VECTOR_MAP_VECTORS_KEY, "Landroid/hardware/camera2/params/OutputConfiguration;", "backingImageReader", "Landroid/media/ImageReader;", "<init>", "(Landroid/hardware/camera2/params/OutputConfiguration;Landroid/media/ImageReader;)V", "getValue", "()Landroid/hardware/camera2/params/OutputConfiguration;", "close", "", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "camera-camera2_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final /* data */ class CloseableOutputConfiguration implements AutoCloseable {
        private final ImageReader backingImageReader;
        private final OutputConfiguration value;

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final ImageReader getBackingImageReader() {
            return this.backingImageReader;
        }

        public static /* synthetic */ CloseableOutputConfiguration copy$default(CloseableOutputConfiguration closeableOutputConfiguration, OutputConfiguration outputConfiguration, ImageReader imageReader, int i, Object obj) {
            if ((i & 1) != 0) {
                outputConfiguration = closeableOutputConfiguration.value;
            }
            if ((i & 2) != 0) {
                imageReader = closeableOutputConfiguration.backingImageReader;
            }
            return closeableOutputConfiguration.copy(outputConfiguration, imageReader);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final OutputConfiguration getValue() {
            return this.value;
        }

        public final CloseableOutputConfiguration copy(OutputConfiguration value, ImageReader backingImageReader) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new CloseableOutputConfiguration(value, backingImageReader);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CloseableOutputConfiguration)) {
                return false;
            }
            CloseableOutputConfiguration closeableOutputConfiguration = (CloseableOutputConfiguration) other;
            return Intrinsics.areEqual(this.value, closeableOutputConfiguration.value) && Intrinsics.areEqual(this.backingImageReader, closeableOutputConfiguration.backingImageReader);
        }

        public int hashCode() {
            int iHashCode = this.value.hashCode() * 31;
            ImageReader imageReader = this.backingImageReader;
            return iHashCode + (imageReader == null ? 0 : imageReader.hashCode());
        }

        public String toString() {
            return "CloseableOutputConfiguration(value=" + this.value + ", backingImageReader=" + this.backingImageReader + ')';
        }

        public CloseableOutputConfiguration(OutputConfiguration value, ImageReader imageReader) {
            Intrinsics.checkNotNullParameter(value, "value");
            this.value = value;
            this.backingImageReader = imageReader;
        }

        public /* synthetic */ CloseableOutputConfiguration(OutputConfiguration outputConfiguration, ImageReader imageReader, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(outputConfiguration, (i & 2) != 0 ? null : imageReader);
        }

        public final OutputConfiguration getValue() {
            return this.value;
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            ImageReader imageReader = this.backingImageReader;
            if (imageReader != null) {
                imageReader.close();
            }
        }
    }

    private final <T extends AutoCloseable, R> R use(List<? extends T> list, Function1<? super List<? extends T>, ? extends R> function1) throws Exception {
        R rInvoke = function1.invoke(list);
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            UByte$$ExternalSyntheticBackport0.m846m(it.next());
        }
        return rInvoke;
    }
}
