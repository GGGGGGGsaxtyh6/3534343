package androidx.camera.core;

import androidx.camera.core.impl.CameraConfig;

/* JADX INFO: loaded from: classes.dex */
public interface Camera {
    CameraControl getCameraControl();

    CameraInfo getCameraInfo();

    CameraConfig getExtendedConfig();

    default boolean isUseCasesCombinationSupported(boolean z, UseCase... useCaseArr) {
        return true;
    }

    default boolean isUseCasesCombinationSupported(UseCase... useCaseArr) {
        return isUseCasesCombinationSupported(true, useCaseArr);
    }

    default boolean isUseCasesCombinationSupportedByFramework(UseCase... useCaseArr) {
        return isUseCasesCombinationSupported(false, useCaseArr);
    }
}
