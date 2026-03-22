package androidx.camera.core;

import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: loaded from: classes.dex */
public interface CameraControl {
    ListenableFuture<Void> cancelFocusAndMetering();

    ListenableFuture<Void> enableTorch(boolean z);

    ListenableFuture<Integer> setExposureCompensationIndex(int i);

    ListenableFuture<Void> setLinearZoom(float f);

    ListenableFuture<Void> setZoomRatio(float f);

    ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction focusMeteringAction);

    default ListenableFuture<Void> enableLowLightBoostAsync(boolean z) {
        return Futures.immediateFailedFuture(new OperationCanceledException("Not supported!"));
    }

    default ListenableFuture<Void> setTorchStrengthLevel(int i) {
        return Futures.immediateFailedFuture(new UnsupportedOperationException("Setting torch strength is not supported on the device."));
    }

    public static final class OperationCanceledException extends Exception {
        public OperationCanceledException(String str) {
            super(str);
        }

        public OperationCanceledException(String str, Throwable th) {
            super(str, th);
        }
    }
}
