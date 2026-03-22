package androidx.camera.core.impl;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.CameraInfo;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public interface SessionProcessor {
    public static final int TYPE_CAMERA2_EXTENSION = 1;
    public static final int TYPE_VENDOR_LIBRARY = 0;

    public interface CaptureCallback {
        default void onCaptureCompleted(long j, int i, CameraCaptureResult cameraCaptureResult) {
        }

        default void onCaptureFailed(int i) {
        }

        default void onCaptureProcessProgressed(int i) {
        }

        default void onCaptureProcessStarted(int i) {
        }

        default void onCaptureSequenceAborted(int i) {
        }

        default void onCaptureSequenceCompleted(int i) {
        }

        default void onCaptureStarted(int i, long j) {
        }
    }

    public interface CaptureSessionRequestProcessor {
        Pair<Long, Long> getRealtimeStillCaptureLatency();

        void setExtensionStrength(int i);
    }

    void abortCapture(int i);

    void deInitSession();

    default Pair<Long, Long> getRealtimeCaptureLatency() {
        return null;
    }

    SessionConfig initSession(CameraInfo cameraInfo, OutputSurfaceConfiguration outputSurfaceConfiguration);

    void onCaptureSessionEnd();

    void onCaptureSessionStart(RequestProcessor requestProcessor);

    default void setCaptureSessionRequestProcessor(CaptureSessionRequestProcessor captureSessionRequestProcessor) {
    }

    void setParameters(Config config);

    int startCapture(boolean z, TagBundle tagBundle, CaptureCallback captureCallback);

    int startRepeating(TagBundle tagBundle, CaptureCallback captureCallback);

    default int startTrigger(Config config, TagBundle tagBundle, CaptureCallback captureCallback) {
        return -1;
    }

    void stopRepeating();

    default Map<Integer, List<Size>> getSupportedPostviewSize(Size size) {
        return Collections.emptyMap();
    }

    default Set<Integer> getSupportedCameraOperations() {
        return Collections.emptySet();
    }

    default List<Pair<CameraCharacteristics.Key, Object>> getAvailableCharacteristicsKeyValues() {
        return Collections.emptyList();
    }

    default Range<Float> getExtensionZoomRange() {
        if (Build.VERSION.SDK_INT < 30) {
            return null;
        }
        for (Pair<CameraCharacteristics.Key, Object> pair : getAvailableCharacteristicsKeyValues()) {
            if (((CameraCharacteristics.Key) pair.first).equals(CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE)) {
                return (Range) pair.second;
            }
        }
        return null;
    }

    default int[] getExtensionAvailableStabilizationModes() {
        for (Pair<CameraCharacteristics.Key, Object> pair : getAvailableCharacteristicsKeyValues()) {
            if (((CameraCharacteristics.Key) pair.first).equals(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES)) {
                return (int[]) pair.second;
            }
        }
        return null;
    }

    default Pair<Integer, Integer> getImplementationType() {
        return Pair.create(0, 0);
    }
}
