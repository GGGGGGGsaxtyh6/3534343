package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.CameraControl;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class LowLightBoostControl {
    static final int DEFAULT_LLB_STATE = -1;
    private static final String TAG = "LowLightBoostControl";
    private final Camera2CameraControlImpl mCamera2CameraControlImpl;
    final Camera2CameraControlImpl.CaptureResultListener mCaptureResultListener;
    CallbackToFutureAdapter.Completer<Void> mEnableLlbCompleter;
    private final Executor mExecutor;
    private boolean mIsActive;
    private final boolean mIsLowLightBoostSupported;
    private final MutableLiveData<Integer> mLowLightBoostState;
    boolean mTargetLlbEnabled;
    private final AtomicInteger mLowLightBoostStateAtomic = new AtomicInteger(-1);
    private final Object mLock = new Object();
    private boolean mLowLightBoostDisabledByUseCaseSessionConfig = false;

    LowLightBoostControl(Camera2CameraControlImpl camera2CameraControlImpl, CameraCharacteristicsCompat cameraCharacteristicsCompat, Executor executor) {
        this.mCamera2CameraControlImpl = camera2CameraControlImpl;
        this.mExecutor = executor;
        boolean zCheckLowLightBoostAvailability = checkLowLightBoostAvailability(cameraCharacteristicsCompat);
        this.mIsLowLightBoostSupported = zCheckLowLightBoostAvailability;
        this.mLowLightBoostState = new MutableLiveData<>(-1);
        Camera2CameraControlImpl.CaptureResultListener captureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.LowLightBoostControl$$ExternalSyntheticLambda0
            @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
            public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
                return this.f$0.m77x7947e373(totalCaptureResult);
            }
        };
        this.mCaptureResultListener = captureResultListener;
        if (zCheckLowLightBoostAvailability) {
            camera2CameraControlImpl.addCaptureResultListener(captureResultListener);
        }
    }

    /* JADX INFO: renamed from: lambda$new$0$androidx-camera-camera2-internal-LowLightBoostControl, reason: not valid java name */
    /* synthetic */ boolean m77x7947e373(TotalCaptureResult totalCaptureResult) {
        Integer num;
        if (this.mEnableLlbCompleter != null) {
            Integer num2 = (Integer) totalCaptureResult.getRequest().get(CaptureRequest.CONTROL_AE_MODE);
            if (num2 == null) {
                return false;
            }
            if ((num2.intValue() == 6) != this.mTargetLlbEnabled) {
                return false;
            }
            this.mEnableLlbCompleter.set(null);
            this.mEnableLlbCompleter = null;
        }
        if (Build.VERSION.SDK_INT >= 35 && this.mTargetLlbEnabled && (num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_LOW_LIGHT_BOOST_STATE)) != null) {
            setLiveDataValue(this.mLowLightBoostState, num.intValue());
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
        if (this.mTargetLlbEnabled) {
            this.mTargetLlbEnabled = false;
            this.mCamera2CameraControlImpl.enableLowLightBoostInternal(false);
            setLiveDataValue(this.mLowLightBoostState, -1);
        }
        CallbackToFutureAdapter.Completer<Void> completer = this.mEnableLlbCompleter;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            this.mEnableLlbCompleter = null;
        }
    }

    void setLowLightBoostDisabledByUseCaseSessionConfig(boolean z) {
        synchronized (this.mLock) {
            this.mLowLightBoostDisabledByUseCaseSessionConfig = z;
            if (z) {
                if (this.mTargetLlbEnabled) {
                    this.mTargetLlbEnabled = false;
                    this.mCamera2CameraControlImpl.enableLowLightBoostInternal(false);
                    setLiveDataValue(this.mLowLightBoostState, -1);
                    CallbackToFutureAdapter.Completer<Void> completer = this.mEnableLlbCompleter;
                    if (completer != null) {
                        completer.setException(new IllegalStateException("Low-light boost is disabled when expected frame rate range exceeds 30 or HDR 10-bit is on."));
                        this.mEnableLlbCompleter = null;
                    }
                }
            }
        }
    }

    boolean isLowLightBoostDisabledByUseCaseSessionConfig() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mLowLightBoostDisabledByUseCaseSessionConfig;
        }
        return z;
    }

    ListenableFuture<Void> enableLowLightBoost(final boolean z) {
        if (!this.mIsLowLightBoostSupported) {
            Logger.d(TAG, "Unable to enable low-light boost due to it is not supported.");
            return Futures.immediateFailedFuture(new IllegalStateException("Low-light boost is not supported"));
        }
        setLiveDataValue(this.mLowLightBoostState, z ? 0 : -1);
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.LowLightBoostControl$$ExternalSyntheticLambda2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m76xca12e633(z, completer);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$enableLowLightBoost$2$androidx-camera-camera2-internal-LowLightBoostControl, reason: not valid java name */
    /* synthetic */ Object m76xca12e633(final boolean z, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.LowLightBoostControl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m75xa0be90f2(completer, z);
            }
        });
        return "enableLowLightBoost: " + z;
    }

    LiveData<Integer> getLowLightBoostState() {
        return this.mLowLightBoostState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: enableLowLightBoostInternal, reason: merged with bridge method [inline-methods] */
    public void m75xa0be90f2(CallbackToFutureAdapter.Completer<Void> completer, boolean z) {
        synchronized (this.mLock) {
            if (this.mLowLightBoostDisabledByUseCaseSessionConfig) {
                setLiveDataValue(this.mLowLightBoostState, -1);
                if (completer != null) {
                    completer.setException(new IllegalStateException("Low-light boost is disabled when expected frame rate range exceeds 30 or HDR 10-bit is on."));
                }
            } else {
                if (!this.mIsActive) {
                    setLiveDataValue(this.mLowLightBoostState, -1);
                    if (completer != null) {
                        completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
                        return;
                    }
                    return;
                }
                this.mTargetLlbEnabled = z;
                this.mCamera2CameraControlImpl.enableLowLightBoostInternal(z);
                setLiveDataValue(this.mLowLightBoostState, z ? 0 : -1);
                CallbackToFutureAdapter.Completer<Void> completer2 = this.mEnableLlbCompleter;
                if (completer2 != null) {
                    completer2.setException(new CameraControl.OperationCanceledException("There is a new enableLowLightBoost being set"));
                }
                this.mEnableLlbCompleter = completer;
            }
        }
    }

    boolean isLowLightBoostSupported() {
        return this.mIsLowLightBoostSupported;
    }

    static boolean checkLowLightBoostAvailability(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        int[] iArr;
        if (Build.VERSION.SDK_INT > 34 && (iArr = (int[]) cameraCharacteristicsCompat.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES)) != null) {
            for (int i : iArr) {
                if (i == 6) {
                    return true;
                }
            }
        }
        return false;
    }

    private void setLiveDataValue(MutableLiveData<Integer> mutableLiveData, int i) {
        if (this.mLowLightBoostStateAtomic.getAndSet(i) != i) {
            if (Threads.isMainThread()) {
                mutableLiveData.setValue(Integer.valueOf(i));
            } else {
                mutableLiveData.postValue(Integer.valueOf(i));
            }
        }
    }
}
