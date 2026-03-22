package androidx.camera.core.impl;

import android.util.Range;
import android.util.Rational;
import androidx.arch.core.util.Function;
import androidx.camera.core.ExposureState;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.UseCase;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.utils.LiveDataUtil;
import androidx.camera.core.impl.utils.SessionProcessorUtil;
import androidx.camera.core.internal.ImmutableZoomState;
import androidx.core.math.MathUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AdapterCameraInfo extends ForwardingCameraInfo {
    public static final int CAMERA_OPERATION_AE_REGION = 3;
    public static final int CAMERA_OPERATION_AF_REGION = 2;
    public static final int CAMERA_OPERATION_AUTO_FOCUS = 1;
    public static final int CAMERA_OPERATION_AWB_REGION = 4;
    public static final int CAMERA_OPERATION_EXPOSURE_COMPENSATION = 7;
    public static final int CAMERA_OPERATION_EXTENSION_STRENGTH = 8;
    public static final int CAMERA_OPERATION_FLASH = 5;
    public static final int CAMERA_OPERATION_TORCH = 6;
    public static final int CAMERA_OPERATION_ZOOM = 0;
    private final CameraConfig mCameraConfig;
    private final CameraInfoInternal mCameraInfo;
    private LiveData<ZoomState> mExtensionZoomStateLiveData;
    private boolean mIsCaptureProcessProgressSupported;
    private boolean mIsPostviewSupported;
    private final SessionProcessor mSessionProcessor;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CameraOperation {
    }

    public static float getPercentageByRatio(float f, float f2, float f3) {
        if (f3 == f2) {
            return 0.0f;
        }
        if (f == f3) {
            return 1.0f;
        }
        if (f == f2) {
            return 0.0f;
        }
        float f4 = 1.0f / f2;
        return ((1.0f / f) - f4) / ((1.0f / f3) - f4);
    }

    public AdapterCameraInfo(CameraInfoInternal cameraInfoInternal, CameraConfig cameraConfig) {
        super(cameraInfoInternal);
        this.mIsPostviewSupported = false;
        this.mIsCaptureProcessProgressSupported = false;
        this.mExtensionZoomStateLiveData = null;
        this.mCameraInfo = cameraInfoInternal;
        this.mCameraConfig = cameraConfig;
        this.mSessionProcessor = cameraConfig.getSessionProcessor(null);
        setPostviewSupported(cameraConfig.isPostviewSupported());
        setCaptureProcessProgressSupported(cameraConfig.isCaptureProcessProgressSupported());
    }

    public CameraConfig getCameraConfig() {
        return this.mCameraConfig;
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.impl.CameraInfoInternal
    public CameraInfoInternal getImplementation() {
        return this.mCameraInfo;
    }

    public SessionProcessor getSessionProcessor() {
        return this.mSessionProcessor;
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.CameraInfo
    public boolean hasFlashUnit() {
        if (SessionProcessorUtil.isOperationSupported(this.mSessionProcessor, 5)) {
            return this.mCameraInfo.hasFlashUnit();
        }
        return false;
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.CameraInfo
    public LiveData<Integer> getTorchState() {
        if (!SessionProcessorUtil.isOperationSupported(this.mSessionProcessor, 6)) {
            return new MutableLiveData(0);
        }
        return this.mCameraInfo.getTorchState();
    }

    public static float getZoomRatioByPercentage(float f, float f2, float f3) {
        if (f == 1.0f) {
            return f3;
        }
        if (f == 0.0f) {
            return f2;
        }
        double d = 1.0f / f2;
        return (float) MathUtils.clamp(1.0d / (d + ((((double) (1.0f / f3)) - d) * ((double) f))), f2, f3);
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.CameraInfo
    public LiveData<ZoomState> getZoomState() {
        if (!SessionProcessorUtil.isOperationSupported(this.mSessionProcessor, 0)) {
            return new MutableLiveData(ImmutableZoomState.create(1.0f, 1.0f, 1.0f, 0.0f));
        }
        if (this.mSessionProcessor != null) {
            ZoomState value = this.mCameraInfo.getZoomState().getValue();
            final Range<Float> extensionZoomRange = this.mSessionProcessor.getExtensionZoomRange();
            if (extensionZoomRange != null && (((Float) extensionZoomRange.getLower()).floatValue() != value.getMinZoomRatio() || ((Float) extensionZoomRange.getUpper()).floatValue() != value.getMaxZoomRatio())) {
                if (this.mExtensionZoomStateLiveData == null) {
                    this.mExtensionZoomStateLiveData = LiveDataUtil.map(this.mCameraInfo.getZoomState(), new Function() { // from class: androidx.camera.core.impl.AdapterCameraInfo$$ExternalSyntheticLambda0
                        @Override // androidx.arch.core.util.Function
                        public final Object apply(Object obj) {
                            Range range = extensionZoomRange;
                            ZoomState zoomState = (ZoomState) obj;
                            return ImmutableZoomState.create(zoomState.getZoomRatio(), ((Float) range.getUpper()).floatValue(), ((Float) range.getLower()).floatValue(), AdapterCameraInfo.getPercentageByRatio(zoomState.getZoomRatio(), ((Float) range.getLower()).floatValue(), ((Float) range.getUpper()).floatValue()));
                        }
                    });
                }
                return this.mExtensionZoomStateLiveData;
            }
        }
        return this.mCameraInfo.getZoomState();
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.CameraInfo
    public ExposureState getExposureState() {
        if (!SessionProcessorUtil.isOperationSupported(this.mSessionProcessor, 7)) {
            return new ExposureState() { // from class: androidx.camera.core.impl.AdapterCameraInfo.1
                @Override // androidx.camera.core.ExposureState
                public int getExposureCompensationIndex() {
                    return 0;
                }

                @Override // androidx.camera.core.ExposureState
                public boolean isExposureCompensationSupported() {
                    return false;
                }

                @Override // androidx.camera.core.ExposureState
                public Range<Integer> getExposureCompensationRange() {
                    return new Range<>(0, 0);
                }

                @Override // androidx.camera.core.ExposureState
                public Rational getExposureCompensationStep() {
                    return Rational.ZERO;
                }
            };
        }
        return this.mCameraInfo.getExposureState();
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.CameraInfo
    public boolean isFocusMeteringSupported(FocusMeteringAction focusMeteringAction) {
        FocusMeteringAction modifiedFocusMeteringAction = SessionProcessorUtil.getModifiedFocusMeteringAction(this.mSessionProcessor, focusMeteringAction);
        if (modifiedFocusMeteringAction == null) {
            return false;
        }
        return this.mCameraInfo.isFocusMeteringSupported(modifiedFocusMeteringAction);
    }

    public void setPostviewSupported(boolean z) {
        this.mIsPostviewSupported = z;
    }

    public void setCaptureProcessProgressSupported(boolean z) {
        this.mIsCaptureProcessProgressSupported = z;
    }

    @Override // androidx.camera.core.impl.CameraInfoInternal
    public boolean isPostviewSupported() {
        return this.mIsPostviewSupported;
    }

    @Override // androidx.camera.core.impl.CameraInfoInternal
    public boolean isCaptureProcessProgressSupported() {
        return this.mIsCaptureProcessProgressSupported;
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.impl.CameraInfoInternal
    public boolean isVideoStabilizationSupported() {
        int[] extensionAvailableStabilizationModes;
        SessionProcessor sessionProcessor = this.mSessionProcessor;
        if (sessionProcessor != null && (extensionAvailableStabilizationModes = sessionProcessor.getExtensionAvailableStabilizationModes()) != null) {
            for (int i : extensionAvailableStabilizationModes) {
                if (i == 1) {
                    return true;
                }
            }
            return false;
        }
        return super.isVideoStabilizationSupported();
    }

    @Override // androidx.camera.core.impl.ForwardingCameraInfo, androidx.camera.core.impl.CameraInfoInternal
    public boolean isPreviewStabilizationSupported() {
        int[] extensionAvailableStabilizationModes;
        SessionProcessor sessionProcessor = this.mSessionProcessor;
        if (sessionProcessor != null && (extensionAvailableStabilizationModes = sessionProcessor.getExtensionAvailableStabilizationModes()) != null) {
            for (int i : extensionAvailableStabilizationModes) {
                if (i == 2) {
                    return true;
                }
            }
            return false;
        }
        return super.isPreviewStabilizationSupported();
    }

    @Override // androidx.camera.core.impl.CameraInfoInternal
    public boolean isUseCaseCombinationSupported(List<UseCase> list, int i, boolean z) {
        return this.mCameraInfo.isUseCaseCombinationSupported(list, i, z, this.mCameraConfig);
    }
}
