package androidx.camera.camera2.interop;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Pair;
import androidx.camera.camera2.internal.Camera2CameraInfoImpl;
import androidx.camera.camera2.internal.Camera2PhysicalCameraInfoImpl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.impl.AdapterCameraInfo;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.SessionProcessor;
import androidx.core.util.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class Camera2CameraInfo {
    private static final String TAG = "Camera2CameraInfo";
    private Camera2CameraInfoImpl mCamera2CameraInfoImpl;
    private Camera2PhysicalCameraInfoImpl mCamera2PhysicalCameraInfo;
    private List<Pair<CameraCharacteristics.Key, Object>> mExtensionsSpecificChars;

    public Camera2CameraInfo(Camera2CameraInfoImpl camera2CameraInfoImpl) {
        this.mCamera2CameraInfoImpl = camera2CameraInfoImpl;
    }

    public Camera2CameraInfo(Camera2PhysicalCameraInfoImpl camera2PhysicalCameraInfoImpl) {
        this.mCamera2PhysicalCameraInfo = camera2PhysicalCameraInfoImpl;
    }

    private Camera2CameraInfo(Camera2CameraInfoImpl camera2CameraInfoImpl, List<Pair<CameraCharacteristics.Key, Object>> list) {
        this.mCamera2CameraInfoImpl = camera2CameraInfoImpl;
        this.mExtensionsSpecificChars = list;
    }

    public static Camera2CameraInfo from(CameraInfo cameraInfo) {
        SessionProcessor sessionProcessor;
        if (cameraInfo instanceof Camera2PhysicalCameraInfoImpl) {
            return ((Camera2PhysicalCameraInfoImpl) cameraInfo).getCamera2CameraInfo();
        }
        CameraInfoInternal implementation = ((CameraInfoInternal) cameraInfo).getImplementation();
        Preconditions.checkArgument(implementation instanceof Camera2CameraInfoImpl, "CameraInfo doesn't contain Camera2 implementation.");
        Camera2CameraInfo camera2CameraInfo = ((Camera2CameraInfoImpl) implementation).getCamera2CameraInfo();
        return (!(cameraInfo instanceof AdapterCameraInfo) || (sessionProcessor = ((AdapterCameraInfo) cameraInfo).getSessionProcessor()) == null) ? camera2CameraInfo : new Camera2CameraInfo(camera2CameraInfo.mCamera2CameraInfoImpl, sessionProcessor.getAvailableCharacteristicsKeyValues());
    }

    public String getCameraId() {
        Camera2PhysicalCameraInfoImpl camera2PhysicalCameraInfoImpl = this.mCamera2PhysicalCameraInfo;
        if (camera2PhysicalCameraInfoImpl != null) {
            return camera2PhysicalCameraInfoImpl.getCameraId();
        }
        return this.mCamera2CameraInfoImpl.getCameraId();
    }

    public <T> T getCameraCharacteristic(CameraCharacteristics.Key<T> key) {
        Camera2PhysicalCameraInfoImpl camera2PhysicalCameraInfoImpl = this.mCamera2PhysicalCameraInfo;
        if (camera2PhysicalCameraInfoImpl != null) {
            return (T) camera2PhysicalCameraInfoImpl.getCameraCharacteristicsCompat().get(key);
        }
        List<Pair<CameraCharacteristics.Key, Object>> list = this.mExtensionsSpecificChars;
        if (list != null) {
            for (Pair<CameraCharacteristics.Key, Object> pair : list) {
                if (((CameraCharacteristics.Key) pair.first).equals(key)) {
                    return (T) pair.second;
                }
            }
        }
        return (T) this.mCamera2CameraInfoImpl.getCameraCharacteristicsCompat().get(key);
    }

    public static CameraCharacteristics extractCameraCharacteristics(CameraInfo cameraInfo) {
        if (cameraInfo instanceof Camera2PhysicalCameraInfoImpl) {
            return ((Camera2PhysicalCameraInfoImpl) cameraInfo).getCameraCharacteristicsCompat().toCameraCharacteristics();
        }
        CameraInfoInternal implementation = ((CameraInfoInternal) cameraInfo).getImplementation();
        Preconditions.checkState(implementation instanceof Camera2CameraInfoImpl, "CameraInfo does not contain any Camera2 information.");
        return ((Camera2CameraInfoImpl) implementation).getCameraCharacteristicsCompat().toCameraCharacteristics();
    }

    public Map<String, CameraCharacteristics> getCameraCharacteristicsMap() {
        if (this.mCamera2PhysicalCameraInfo != null) {
            return Collections.emptyMap();
        }
        return this.mCamera2CameraInfoImpl.getCameraCharacteristicsMap();
    }
}
