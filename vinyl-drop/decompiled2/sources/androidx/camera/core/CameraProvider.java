package androidx.camera.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CameraProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H'J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015H'R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R \u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00038gX¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\n8gX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u001a\u0010\u0017\u001a\u00020\u00188gX¦\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001dÀ\u0006\u0001"}, d2 = {"Landroidx/camera/core/CameraProvider;", "", "availableCameraInfos", "", "Landroidx/camera/core/CameraInfo;", "getAvailableCameraInfos", "()Ljava/util/List;", "availableConcurrentCameraInfos", "getAvailableConcurrentCameraInfos", "isConcurrentCameraModeOn", "", "()Z", "hasCamera", "cameraSelector", "Landroidx/camera/core/CameraSelector;", "getCameraInfo", "addCameraPresenceListener", "", "executor", "Ljava/util/concurrent/Executor;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroidx/camera/core/CameraPresenceListener;", "removeCameraPresenceListener", "configImplType", "", "getConfigImplType$annotations", "()V", "getConfigImplType", "()I", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public interface CameraProvider {
    static /* synthetic */ void getConfigImplType$annotations() {
    }

    void addCameraPresenceListener(Executor executor, CameraPresenceListener listener);

    List<CameraInfo> getAvailableCameraInfos();

    List<List<CameraInfo>> getAvailableConcurrentCameraInfos();

    int getConfigImplType();

    boolean hasCamera(CameraSelector cameraSelector) throws CameraInfoUnavailableException;

    boolean isConcurrentCameraModeOn();

    void removeCameraPresenceListener(CameraPresenceListener listener);

    default CameraInfo getCameraInfo(CameraSelector cameraSelector) {
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        throw new UnsupportedOperationException("The camera provider is not implemented properly.");
    }
}
