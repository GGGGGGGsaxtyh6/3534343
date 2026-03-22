package androidx.camera.core.impl;

import androidx.camera.core.InitializationException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface CameraPresenceMonitor {
    void onCameraIdsUpdated(List<String> list) throws InitializationException;
}
