package androidx.camera.featurecombinationquery;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.SessionConfiguration;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompat;

/* JADX INFO: loaded from: classes.dex */
class Camera2CameraDeviceSetupCompat implements CameraDeviceSetupCompat {
    private final CameraDevice.CameraDeviceSetup mCameraDeviceSetup;

    Camera2CameraDeviceSetupCompat(CameraManager cameraManager, String str) throws CameraAccessException {
        this.mCameraDeviceSetup = cameraManager.getCameraDeviceSetup(str);
    }

    @Override // androidx.camera.featurecombinationquery.CameraDeviceSetupCompat
    public CameraDeviceSetupCompat.SupportQueryResult isSessionConfigurationSupported(SessionConfiguration sessionConfiguration) throws CameraAccessException {
        return new CameraDeviceSetupCompat.SupportQueryResult(this.mCameraDeviceSetup.isSessionConfigurationSupported(sessionConfiguration) ? 1 : 2, 2, getBuildTimeEpochMillis());
    }

    @Override // androidx.camera.featurecombinationquery.CameraDeviceSetupCompat
    public CameraDeviceSetupCompat.SupportQueryResult isSessionConfigurationSupportedLegacy(SessionConfigurationLegacy sessionConfigurationLegacy) {
        throw new IllegalStateException("This device supports CameraDeviceSetup. Please use Camera2 SessionConfiguration for querying instead.");
    }

    public static long getBuildTimeEpochMillis() {
        String property = System.getProperty("ro.build.date.utc");
        if (property == null) {
            return 0L;
        }
        try {
            return Long.parseLong(property) * 1000;
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }
}
