package androidx.camera.core.impl;

import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.InitializationException;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class CameraRepository implements InternalCameraPresenceListener {
    private static final String TAG = "CameraRepository";
    private CameraFactory mCameraFactory;
    private CallbackToFutureAdapter.Completer<Void> mDeinitCompleter;
    private ListenableFuture<Void> mDeinitFuture;
    private final Object mCamerasLock = new Object();
    private final Map<String, CameraInternal> mCameras = new LinkedHashMap();
    private final Set<CameraInternal> mReleasingCameras = new HashSet();

    public void init(CameraFactory cameraFactory) throws InitializationException {
        this.mCameraFactory = cameraFactory;
        synchronized (this.mCamerasLock) {
            try {
                try {
                    for (String str : cameraFactory.getAvailableCameraIds()) {
                        Logger.d(TAG, "Added camera: " + str);
                        CameraInternal cameraInternalPut = this.mCameras.put(str, cameraFactory.getCamera(str));
                        if (cameraInternalPut != null) {
                            cameraInternalPut.release();
                        }
                    }
                } catch (CameraUnavailableException e) {
                    throw new InitializationException(e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public ListenableFuture<Void> deinit() {
        synchronized (this.mCamerasLock) {
            boolean zIsEmpty = this.mCameras.isEmpty();
            ListenableFuture<Void> future = this.mDeinitFuture;
            if (zIsEmpty) {
                if (future == null) {
                    future = Futures.immediateFuture(null);
                }
                return future;
            }
            if (future == null) {
                future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.impl.CameraRepository$$ExternalSyntheticLambda0
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                        return this.f$0.m176lambda$deinit$0$androidxcameracoreimplCameraRepository(completer);
                    }
                });
                this.mDeinitFuture = future;
            }
            this.mReleasingCameras.addAll(this.mCameras.values());
            for (final CameraInternal cameraInternal : this.mCameras.values()) {
                cameraInternal.release().addListener(new Runnable() { // from class: androidx.camera.core.impl.CameraRepository$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m177lambda$deinit$1$androidxcameracoreimplCameraRepository(cameraInternal);
                    }
                }, CameraXExecutors.directExecutor());
            }
            this.mCameras.clear();
            return future;
        }
    }

    /* JADX INFO: renamed from: lambda$deinit$0$androidx-camera-core-impl-CameraRepository, reason: not valid java name */
    /* synthetic */ Object m176lambda$deinit$0$androidxcameracoreimplCameraRepository(CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.mCamerasLock) {
            this.mDeinitCompleter = completer;
        }
        return "CameraRepository-deinit";
    }

    /* JADX INFO: renamed from: lambda$deinit$1$androidx-camera-core-impl-CameraRepository, reason: not valid java name */
    /* synthetic */ void m177lambda$deinit$1$androidxcameracoreimplCameraRepository(CameraInternal cameraInternal) {
        synchronized (this.mCamerasLock) {
            this.mReleasingCameras.remove(cameraInternal);
            if (this.mReleasingCameras.isEmpty()) {
                Preconditions.checkNotNull(this.mDeinitCompleter);
                this.mDeinitCompleter.set(null);
                this.mDeinitCompleter = null;
                this.mDeinitFuture = null;
            }
        }
    }

    public CameraInternal getCamera(String str) {
        CameraInternal cameraInternal;
        synchronized (this.mCamerasLock) {
            cameraInternal = this.mCameras.get(str);
            if (cameraInternal == null) {
                throw new IllegalArgumentException("Invalid camera: " + str);
            }
        }
        return cameraInternal;
    }

    public LinkedHashSet<CameraInternal> getCameras() {
        LinkedHashSet<CameraInternal> linkedHashSet;
        synchronized (this.mCamerasLock) {
            linkedHashSet = new LinkedHashSet<>(this.mCameras.values());
        }
        return linkedHashSet;
    }

    Set<String> getCameraIds() {
        LinkedHashSet linkedHashSet;
        synchronized (this.mCamerasLock) {
            linkedHashSet = new LinkedHashSet(this.mCameras.keySet());
        }
        return linkedHashSet;
    }

    @Override // androidx.camera.core.impl.InternalCameraPresenceListener
    public void onCamerasUpdated(List<String> list) throws CameraUpdateException {
        HashSet<String> hashSet;
        HashMap map = new HashMap();
        synchronized (this.mCamerasLock) {
            hashSet = new HashSet(list);
            hashSet.removeAll(this.mCameras.keySet());
        }
        try {
            for (String str : hashSet) {
                map.put(str, this.mCameraFactory.getCamera(str));
            }
            synchronized (this.mCamerasLock) {
                HashSet hashSet2 = new HashSet(this.mCameras.keySet());
                hashSet2.removeAll(list);
                ArrayList<CameraInternal> arrayList = new ArrayList();
                Iterator it = hashSet2.iterator();
                while (it.hasNext()) {
                    arrayList.add(this.mCameras.get((String) it.next()));
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (String str2 : list) {
                    if (this.mCameras.containsKey(str2)) {
                        linkedHashMap.put(str2, this.mCameras.get(str2));
                    } else {
                        linkedHashMap.put(str2, (CameraInternal) map.get(str2));
                    }
                }
                this.mCameras.clear();
                this.mCameras.putAll(linkedHashMap);
                for (CameraInternal cameraInternal : arrayList) {
                    if (cameraInternal != null) {
                        cameraInternal.onRemoved();
                    }
                }
            }
        } catch (CameraUnavailableException e) {
            throw new CameraUpdateException("Failed to create CameraInternal", e);
        }
    }
}
