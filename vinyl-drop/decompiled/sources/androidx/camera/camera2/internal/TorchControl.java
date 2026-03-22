package androidx.camera.camera2.internal;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.workaround.FlashAvailabilityChecker;
import androidx.camera.core.CameraControl;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
final class TorchControl {
    static final int DEFAULT_TORCH_STATE = 0;
    static final int OFF = 0;
    static final int ON = 1;
    private static final String TAG = "TorchControl";
    static final int USED_AS_FLASH = 2;
    private final Camera2CameraControlImpl mCamera2CameraControlImpl;
    private int mDefaultTorchStrength;
    CallbackToFutureAdapter.Completer<Void> mEnableTorchCompleter;
    private final Executor mExecutor;
    private final boolean mHasFlashUnit;
    private boolean mIsActive;
    private boolean mIsTorchStrengthSupported;
    boolean mTargetTorchEnabled;
    private int mTargetTorchStrength;
    private final MutableLiveData<Integer> mTorchState;
    private final MutableLiveData<Integer> mTorchStrength;
    private CallbackToFutureAdapter.Completer<Void> mTorchStrengthCompleter;

    TorchControl(Camera2CameraControlImpl camera2CameraControlImpl, CameraCharacteristicsCompat cameraCharacteristicsCompat, Executor executor) {
        this.mCamera2CameraControlImpl = camera2CameraControlImpl;
        this.mExecutor = executor;
        Objects.requireNonNull(cameraCharacteristicsCompat);
        boolean zIsFlashAvailable = FlashAvailabilityChecker.isFlashAvailable(new Camera2CameraInfoImpl$$ExternalSyntheticLambda0(cameraCharacteristicsCompat));
        this.mHasFlashUnit = zIsFlashAvailable;
        boolean zIsTorchStrengthLevelSupported = cameraCharacteristicsCompat.isTorchStrengthLevelSupported();
        this.mIsTorchStrengthSupported = zIsTorchStrengthLevelSupported;
        int defaultTorchStrengthLevel = (zIsFlashAvailable && zIsTorchStrengthLevelSupported) ? cameraCharacteristicsCompat.getDefaultTorchStrengthLevel() : 0;
        this.mDefaultTorchStrength = defaultTorchStrengthLevel;
        this.mTargetTorchStrength = defaultTorchStrengthLevel;
        this.mTorchState = new MutableLiveData<>(0);
        this.mTorchStrength = new MutableLiveData<>(Integer.valueOf(this.mDefaultTorchStrength));
        camera2CameraControlImpl.addCaptureResultListener(new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.TorchControl$$ExternalSyntheticLambda4
            @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
            public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
                return this.f$0.m95lambda$new$0$androidxcameracamera2internalTorchControl(totalCaptureResult);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$androidx-camera-camera2-internal-TorchControl, reason: not valid java name */
    /* synthetic */ boolean m95lambda$new$0$androidxcameracamera2internalTorchControl(TotalCaptureResult totalCaptureResult) {
        Integer num;
        if (this.mEnableTorchCompleter != null) {
            Integer num2 = (Integer) totalCaptureResult.getRequest().get(CaptureRequest.FLASH_MODE);
            if ((num2 != null && num2.intValue() == 2) == this.mTargetTorchEnabled) {
                this.mEnableTorchCompleter.set(null);
                this.mEnableTorchCompleter = null;
            }
        }
        if (this.mIsTorchStrengthSupported && Build.VERSION.SDK_INT >= 35 && this.mTorchStrengthCompleter != null && (num = (Integer) totalCaptureResult.get(CaptureResult.FLASH_STRENGTH_LEVEL)) != null && num.intValue() == this.mTargetTorchStrength) {
            this.mTorchStrengthCompleter.set(null);
            this.mTorchStrengthCompleter = null;
        }
        return false;
    }

    void setActive(boolean z) {
        if (this.mIsActive == z) {
            return;
        }
        this.mIsActive = z;
        if (z) {
            return;
        }
        if (this.mTargetTorchEnabled) {
            this.mTargetTorchEnabled = false;
            this.mTargetTorchStrength = this.mDefaultTorchStrength;
            this.mCamera2CameraControlImpl.enableTorchInternal(0);
            setTorchState(0);
            setLiveDataValue(this.mTorchStrength, Integer.valueOf(this.mDefaultTorchStrength));
        }
        CallbackToFutureAdapter.Completer<Void> completer = this.mEnableTorchCompleter;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            this.mEnableTorchCompleter = null;
        }
        CallbackToFutureAdapter.Completer<Void> completer2 = this.mTorchStrengthCompleter;
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            this.mTorchStrengthCompleter = null;
        }
    }

    ListenableFuture<Void> enableTorch(final boolean z) {
        if (!this.mHasFlashUnit) {
            Logger.d(TAG, "Unable to enableTorch due to there is no flash unit.");
            return Futures.immediateFailedFuture(new IllegalStateException("No flash unit"));
        }
        setTorchState(z ? 1 : 0);
        final int i = z ? 1 : 0;
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.TorchControl$$ExternalSyntheticLambda1
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m94x328afed3(i, z, completer);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$enableTorch$2$androidx-camera-camera2-internal-TorchControl, reason: not valid java name */
    /* synthetic */ Object m94x328afed3(final int i, boolean z, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.TorchControl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m93xbd10d892(completer, i);
            }
        });
        return "enableTorch: " + z;
    }

    LiveData<Integer> getTorchState() {
        return this.mTorchState;
    }

    LiveData<Integer> getTorchStrengthLevel() {
        return this.mTorchStrength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: enableTorchInternal, reason: merged with bridge method [inline-methods] */
    public void m93xbd10d892(CallbackToFutureAdapter.Completer<Void> completer, int i) {
        if (!this.mHasFlashUnit) {
            if (completer != null) {
                completer.setException(new IllegalStateException("No flash unit"));
                return;
            }
            return;
        }
        if (!this.mIsActive) {
            setTorchState(0);
            if (completer != null) {
                completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
                return;
            }
            return;
        }
        if (this.mCamera2CameraControlImpl.isLowLightBoostOn()) {
            if (completer != null) {
                completer.setException(new IllegalStateException("Torch can not be enabled when low-light boost is on!"));
                return;
            }
            return;
        }
        this.mTargetTorchEnabled = i != 0;
        this.mCamera2CameraControlImpl.enableTorchInternal(i);
        setTorchState(i);
        CallbackToFutureAdapter.Completer<Void> completer2 = this.mEnableTorchCompleter;
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("There is a new enableTorch being set"));
        }
        this.mEnableTorchCompleter = completer;
    }

    ListenableFuture<Void> setTorchStrengthLevel(final int i) {
        if (!this.mIsTorchStrengthSupported) {
            return Futures.immediateFailedFuture(new UnsupportedOperationException("Setting torch strength is not supported on the device."));
        }
        setLiveDataValue(this.mTorchStrength, Integer.valueOf(i));
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.TorchControl$$ExternalSyntheticLambda0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m97x92e8b885(i, completer);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setTorchStrengthLevel$4$androidx-camera-camera2-internal-TorchControl, reason: not valid java name */
    /* synthetic */ Object m97x92e8b885(final int i, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.TorchControl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m96x1d6e9244(completer, i);
            }
        });
        return "setTorchStrength: " + i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: setTorchStrengthLevelInternal, reason: merged with bridge method [inline-methods] */
    public void m96x1d6e9244(CallbackToFutureAdapter.Completer<Void> completer, int i) {
        if (!this.mIsTorchStrengthSupported) {
            if (completer != null) {
                completer.setException(new UnsupportedOperationException("Setting torch strength is not supported on the device."));
                return;
            }
            return;
        }
        if (!this.mIsActive) {
            if (completer != null) {
                completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
                return;
            }
            return;
        }
        this.mTargetTorchStrength = i;
        this.mCamera2CameraControlImpl.setTorchStrengthLevelInternal(i);
        if (!this.mCamera2CameraControlImpl.isTorchOn() && completer != null) {
            completer.set(null);
            return;
        }
        CallbackToFutureAdapter.Completer<Void> completer2 = this.mTorchStrengthCompleter;
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("There is a new torch strength being set."));
        }
        this.mTorchStrengthCompleter = completer;
    }

    void forceUpdateTorchStateToOff() {
        if (this.mTargetTorchEnabled) {
            this.mTargetTorchEnabled = false;
            setTorchState(0);
        }
    }

    private void setTorchState(int i) {
        setLiveDataValue(this.mTorchState, Integer.valueOf(i != 1 ? 0 : 1));
    }

    private <T> void setLiveDataValue(MutableLiveData<T> mutableLiveData, T t) {
        if (Threads.isMainThread()) {
            mutableLiveData.setValue(t);
        } else {
            mutableLiveData.postValue(t);
        }
    }
}
