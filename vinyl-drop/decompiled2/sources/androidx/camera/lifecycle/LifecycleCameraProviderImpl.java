package androidx.camera.lifecycle;

import android.content.Context;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraPresenceListener;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraX;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.CompositionSettings;
import androidx.camera.core.ConcurrentCamera;
import androidx.camera.core.LegacySessionConfig;
import androidx.camera.core.Preview;
import androidx.camera.core.SessionConfig;
import androidx.camera.core.UseCase;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.ViewPort;
import androidx.camera.core.impl.AdapterCameraInfo;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigProvider;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.ExtendedCameraConfigProviderStore;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.ContextUtil;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.lifecycle.LifecycleCameraRepository;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LifecycleOwner;
import androidx.tracing.Trace;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: LifecycleCameraProviderImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.H\u0000¢\u0006\u0002\b/J\u0015\u00100\u001a\u0002012\u0006\u0010-\u001a\u00020.H\u0000¢\u0006\u0002\b2J\u001d\u00103\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u00104\u001a\u000205H\u0000¢\u0006\u0002\b6J\u0010\u00107\u001a\u0002052\u0006\u00108\u001a\u000209H\u0016J\u0010\u00107\u001a\u0002052\u0006\u0010:\u001a\u00020;H\u0016J%\u0010<\u001a\u0002012\u0016\u0010=\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001090>\"\u0004\u0018\u000109H\u0017¢\u0006\u0002\u0010?J\u0010\u0010<\u001a\u0002012\u0006\u0010:\u001a\u00020;H\u0017J\b\u0010@\u001a\u000201H\u0017J\u0010\u0010A\u001a\u0002052\u0006\u0010B\u001a\u00020CH\u0016J5\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010B\u001a\u00020C2\u0016\u0010=\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001090>\"\u0004\u0018\u000109H\u0017¢\u0006\u0002\u0010HJ \u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010B\u001a\u00020C2\u0006\u0010I\u001a\u00020JH\u0017J \u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010B\u001a\u00020C2\u0006\u0010:\u001a\u00020;H\u0017J\u0018\u0010D\u001a\u00020K2\u000e\u0010L\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010N0MH\u0017J@\u0010W\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010X\u001a\u00020C2\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010C2\b\b\u0002\u0010Z\u001a\u00020[2\b\b\u0002\u0010\\\u001a\u00020[2\u0006\u0010:\u001a\u00020;H\u0002J\u0010\u0010]\u001a\u00020P2\u0006\u0010B\u001a\u00020CH\u0016J\u0018\u0010^\u001a\u0002012\u0006\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020bH\u0017J\u0010\u0010c\u001a\u0002012\u0006\u0010a\u001a\u00020bH\u0017J\u0010\u0010d\u001a\u0002052\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010e\u001a\u0002052\u0006\u00108\u001a\u000209H\u0002J\u0018\u0010f\u001a\u00020g2\u0006\u0010B\u001a\u00020C2\u0006\u0010h\u001a\u00020PH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R%\u0010\u0010\u001a\u0015\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u000f0\u000f0\u000e¢\u0006\u0002\b\u00128\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0013\u001a\u00070\u0014¢\u0006\u0002\b\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0017\u001a\u0004\u0018\u00010\u00188\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0019\u0010\u0003\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020$0#j\b\u0012\u0004\u0012\u00020$`%X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u00020'X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010O\u001a\b\u0012\u0004\u0012\u00020P0M8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010RR \u0010S\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020P0M0M8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bT\u0010RR\u0014\u0010U\u001a\u0002058WX\u0096\u0004¢\u0006\u0006\u001a\u0004\bU\u0010VR$\u0010i\u001a\u00020'2\u0006\u0010i\u001a\u00020'8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bj\u0010)\"\u0004\bk\u0010+R0\u0010m\u001a\b\u0012\u0004\u0012\u00020P0M2\f\u0010l\u001a\b\u0012\u0004\u0012\u00020P0M8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bn\u0010R\"\u0004\bo\u0010p¨\u0006q"}, d2 = {"Landroidx/camera/lifecycle/LifecycleCameraProviderImpl;", "Landroidx/camera/lifecycle/LifecycleCameraProvider;", "<init>", "()V", "lock", "", "cameraXConfigProvider", "Landroidx/camera/core/CameraXConfig$Provider;", "getCameraXConfigProvider$camera_lifecycle_release$annotations", "getCameraXConfigProvider$camera_lifecycle_release", "()Landroidx/camera/core/CameraXConfig$Provider;", "setCameraXConfigProvider$camera_lifecycle_release", "(Landroidx/camera/core/CameraXConfig$Provider;)V", "cameraXInitializeFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "Ljava/lang/Void;", "cameraXShutdownFuture", "kotlin.jvm.PlatformType", "Lorg/jspecify/annotations/NonNull;", "lifecycleCameraRepository", "Landroidx/camera/lifecycle/LifecycleCameraRepository;", "cameraX", "Landroidx/camera/core/CameraX;", "context", "Landroid/content/Context;", "getContext$camera_lifecycle_release$annotations", "getContext$camera_lifecycle_release", "()Landroid/content/Context;", "setContext$camera_lifecycle_release", "(Landroid/content/Context;)V", "cameraInfoMap", "", "Landroidx/camera/core/CameraIdentifier;", "Landroidx/camera/core/impl/AdapterCameraInfo;", "lifecycleCameraKeys", "Ljava/util/HashSet;", "Landroidx/camera/lifecycle/LifecycleCameraRepository$Key;", "Lkotlin/collections/HashSet;", "configImplType", "", "getConfigImplType", "()I", "setConfigImplType", "(I)V", "initAsync", "cameraXConfig", "Landroidx/camera/core/CameraXConfig;", "initAsync$camera_lifecycle_release", "configure", "", "configure$camera_lifecycle_release", "shutdownAsync", "clearConfigProvider", "", "shutdownAsync$camera_lifecycle_release", "isBound", "useCase", "Landroidx/camera/core/UseCase;", "sessionConfig", "Landroidx/camera/core/SessionConfig;", "unbind", "useCases", "", "([Landroidx/camera/core/UseCase;)V", "unbindAll", "hasCamera", "cameraSelector", "Landroidx/camera/core/CameraSelector;", "bindToLifecycle", "Landroidx/camera/core/Camera;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/camera/core/CameraSelector;[Landroidx/camera/core/UseCase;)Landroidx/camera/core/Camera;", "useCaseGroup", "Landroidx/camera/core/UseCaseGroup;", "Landroidx/camera/core/ConcurrentCamera;", "singleCameraConfigs", "", "Landroidx/camera/core/ConcurrentCamera$SingleCameraConfig;", "availableCameraInfos", "Landroidx/camera/core/CameraInfo;", "getAvailableCameraInfos", "()Ljava/util/List;", "availableConcurrentCameraInfos", "getAvailableConcurrentCameraInfos", "isConcurrentCameraModeOn", "()Z", "bindToLifecycleInternal", "primaryCameraSelector", "secondaryCameraSelector", "primaryCompositionSettings", "Landroidx/camera/core/CompositionSettings;", "secondaryCompositionSettings", "getCameraInfo", "addCameraPresenceListener", "executor", "Ljava/util/concurrent/Executor;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroidx/camera/core/CameraPresenceListener;", "removeCameraPresenceListener", "isVideoCapture", "isPreview", "getCameraConfig", "Landroidx/camera/core/impl/CameraConfig;", "cameraInfo", "cameraOperatingMode", "getCameraOperatingMode", "setCameraOperatingMode", "cameraInfos", "activeConcurrentCameraInfos", "getActiveConcurrentCameraInfos", "setActiveConcurrentCameraInfos", "(Ljava/util/List;)V", "camera-lifecycle_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LifecycleCameraProviderImpl implements LifecycleCameraProvider {
    private final Map<CameraIdentifier, AdapterCameraInfo> cameraInfoMap;
    private CameraX cameraX;
    private CameraXConfig.Provider cameraXConfigProvider;
    private ListenableFuture<Void> cameraXInitializeFuture;
    private ListenableFuture<Void> cameraXShutdownFuture;
    private int configImplType;
    private Context context;
    private final HashSet<LifecycleCameraRepository.Key> lifecycleCameraKeys;
    private final LifecycleCameraRepository lifecycleCameraRepository;
    private final Object lock = new Object();

    public static /* synthetic */ void getCameraXConfigProvider$camera_lifecycle_release$annotations() {
    }

    public static /* synthetic */ void getContext$camera_lifecycle_release$annotations() {
    }

    public LifecycleCameraProviderImpl() {
        ListenableFuture<Void> listenableFutureImmediateFuture = Futures.immediateFuture(null);
        Intrinsics.checkNotNullExpressionValue(listenableFutureImmediateFuture, "immediateFuture(...)");
        this.cameraXShutdownFuture = listenableFutureImmediateFuture;
        LifecycleCameraRepository lifecycleCameraRepository = LifecycleCameraRepository.getInstance();
        Intrinsics.checkNotNullExpressionValue(lifecycleCameraRepository, "getInstance(...)");
        this.lifecycleCameraRepository = lifecycleCameraRepository;
        this.cameraInfoMap = new HashMap();
        this.lifecycleCameraKeys = new HashSet<>();
        this.configImplType = -1;
    }

    /* JADX INFO: renamed from: getCameraXConfigProvider$camera_lifecycle_release, reason: from getter */
    public final CameraXConfig.Provider getCameraXConfigProvider() {
        return this.cameraXConfigProvider;
    }

    public final void setCameraXConfigProvider$camera_lifecycle_release(CameraXConfig.Provider provider) {
        this.cameraXConfigProvider = provider;
    }

    /* JADX INFO: renamed from: getContext$camera_lifecycle_release, reason: from getter */
    public final Context getContext() {
        return this.context;
    }

    public final void setContext$camera_lifecycle_release(Context context) {
        this.context = context;
    }

    @Override // androidx.camera.core.CameraProvider
    public int getConfigImplType() {
        return this.configImplType;
    }

    public void setConfigImplType(int i) {
        this.configImplType = i;
    }

    public static /* synthetic */ ListenableFuture initAsync$camera_lifecycle_release$default(LifecycleCameraProviderImpl lifecycleCameraProviderImpl, Context context, CameraXConfig cameraXConfig, int i, Object obj) {
        if ((i & 2) != 0) {
            cameraXConfig = null;
        }
        return lifecycleCameraProviderImpl.initAsync$camera_lifecycle_release(context, cameraXConfig);
    }

    public final ListenableFuture<Void> initAsync$camera_lifecycle_release(final Context context, CameraXConfig cameraXConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        synchronized (this.lock) {
            ListenableFuture<Void> listenableFuture = this.cameraXInitializeFuture;
            if (listenableFuture != null) {
                Intrinsics.checkNotNull(listenableFuture, "null cannot be cast to non-null type com.google.common.util.concurrent.ListenableFuture<java.lang.Void>");
                return listenableFuture;
            }
            if (cameraXConfig != null) {
                configure$camera_lifecycle_release(cameraXConfig);
            }
            final CameraX cameraX = new CameraX(context, this.cameraXConfigProvider);
            setConfigImplType(cameraX.getConfigImplType());
            FutureChain futureChainFrom = FutureChain.from(this.cameraXShutdownFuture);
            final Function1 function1 = new Function1() { // from class: androidx.camera.lifecycle.LifecycleCameraProviderImpl$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return cameraX.getInitializeFuture();
                }
            };
            FutureChain futureChainTransformAsync = futureChainFrom.transformAsync(new AsyncFunction() { // from class: androidx.camera.lifecycle.LifecycleCameraProviderImpl$$ExternalSyntheticLambda2
                @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    return LifecycleCameraProviderImpl.initAsync$lambda$3$lambda$2(function1, obj);
                }
            }, CameraXExecutors.directExecutor());
            Intrinsics.checkNotNullExpressionValue(futureChainTransformAsync, "transformAsync(...)");
            this.cameraXInitializeFuture = futureChainTransformAsync;
            Futures.addCallback(futureChainTransformAsync, new FutureCallback<Void>() { // from class: androidx.camera.lifecycle.LifecycleCameraProviderImpl$initAsync$1$2
                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onSuccess(Void r2) {
                    this.this$0.cameraX = cameraX;
                    this.this$0.setContext$camera_lifecycle_release(ContextUtil.getApplicationContext(context));
                }

                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onFailure(Throwable t) {
                    Intrinsics.checkNotNullParameter(t, "t");
                    this.this$0.shutdownAsync$camera_lifecycle_release(false);
                }
            }, CameraXExecutors.directExecutor());
            ListenableFuture<Void> listenableFutureNonCancellationPropagating = Futures.nonCancellationPropagating(futureChainTransformAsync);
            Intrinsics.checkNotNullExpressionValue(listenableFutureNonCancellationPropagating, "nonCancellationPropagating(...)");
            return listenableFutureNonCancellationPropagating;
        }
    }

    static final ListenableFuture initAsync$lambda$3$lambda$2(Function1 function1, Object obj) {
        return (ListenableFuture) function1.invoke(obj);
    }

    public final void configure$camera_lifecycle_release(final CameraXConfig cameraXConfig) {
        Intrinsics.checkNotNullParameter(cameraXConfig, "cameraXConfig");
        Trace.beginSection("CX:configureInstanceInternal");
        try {
            synchronized (this.lock) {
                Preconditions.checkNotNull(cameraXConfig);
                Preconditions.checkState(getCameraXConfigProvider() == null, "CameraX has already been configured. To use a different configuration, shutdown() must be called.");
                setCameraXConfigProvider$camera_lifecycle_release(new CameraXConfig.Provider() { // from class: androidx.camera.lifecycle.LifecycleCameraProviderImpl$configure$1$1$1
                    @Override // androidx.camera.core.CameraXConfig.Provider
                    public final CameraXConfig getCameraXConfig() {
                        return cameraXConfig;
                    }
                });
                Unit unit = Unit.INSTANCE;
            }
            Unit unit2 = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }

    public static /* synthetic */ ListenableFuture shutdownAsync$camera_lifecycle_release$default(LifecycleCameraProviderImpl lifecycleCameraProviderImpl, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return lifecycleCameraProviderImpl.shutdownAsync$camera_lifecycle_release(z);
    }

    public final ListenableFuture<Void> shutdownAsync$camera_lifecycle_release(boolean clearConfigProvider) {
        ListenableFuture<Void> listenableFutureImmediateFuture;
        Threads.runOnMainSync(new Runnable() { // from class: androidx.camera.lifecycle.LifecycleCameraProviderImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LifecycleCameraProviderImpl.shutdownAsync$lambda$6(this.f$0);
            }
        });
        CameraX cameraX = this.cameraX;
        if (cameraX != null) {
            Intrinsics.checkNotNull(cameraX);
            listenableFutureImmediateFuture = cameraX.shutdown();
        } else {
            listenableFutureImmediateFuture = Futures.immediateFuture(null);
        }
        Intrinsics.checkNotNull(listenableFutureImmediateFuture);
        synchronized (this.lock) {
            if (clearConfigProvider) {
                this.cameraXConfigProvider = null;
                this.cameraXInitializeFuture = null;
                this.cameraXShutdownFuture = listenableFutureImmediateFuture;
                this.cameraInfoMap.clear();
                this.lifecycleCameraKeys.clear();
                Unit unit = Unit.INSTANCE;
            } else {
                this.cameraXInitializeFuture = null;
                this.cameraXShutdownFuture = listenableFutureImmediateFuture;
                this.cameraInfoMap.clear();
                this.lifecycleCameraKeys.clear();
                Unit unit2 = Unit.INSTANCE;
            }
        }
        this.cameraX = null;
        this.context = null;
        return listenableFutureImmediateFuture;
    }

    static final void shutdownAsync$lambda$6(LifecycleCameraProviderImpl lifecycleCameraProviderImpl) {
        lifecycleCameraProviderImpl.unbindAll();
        lifecycleCameraProviderImpl.lifecycleCameraRepository.removeLifecycleCameras(lifecycleCameraProviderImpl.lifecycleCameraKeys);
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public boolean isBound(UseCase useCase) {
        Intrinsics.checkNotNullParameter(useCase, "useCase");
        for (LifecycleCamera lifecycleCamera : this.lifecycleCameraRepository.getLifecycleCameras()) {
            Intrinsics.checkNotNullExpressionValue(lifecycleCamera, "next(...)");
            if (lifecycleCamera.isBound(useCase)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public boolean isBound(SessionConfig sessionConfig) {
        Intrinsics.checkNotNullParameter(sessionConfig, "sessionConfig");
        for (LifecycleCamera lifecycleCamera : this.lifecycleCameraRepository.getLifecycleCameras()) {
            Intrinsics.checkNotNullExpressionValue(lifecycleCamera, "next(...)");
            if (lifecycleCamera.isBound(sessionConfig)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public void unbind(UseCase... useCases) {
        Intrinsics.checkNotNullParameter(useCases, "useCases");
        Trace.beginSection("CX:unbind");
        try {
            Threads.checkMainThread();
            if (getCameraOperatingMode() == 2) {
                throw new UnsupportedOperationException("Unbind UseCase is not supported in concurrent camera mode, call unbindAll() first.");
            }
            this.lifecycleCameraRepository.unbind(new LegacySessionConfig(ArraysKt.filterNotNull(useCases), null, null, 6, null), this.lifecycleCameraKeys);
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public void unbind(SessionConfig sessionConfig) {
        Intrinsics.checkNotNullParameter(sessionConfig, "sessionConfig");
        Trace.beginSection("CX:unbind-sessionConfig");
        try {
            Threads.checkMainThread();
            if (getCameraOperatingMode() != 2) {
                this.lifecycleCameraRepository.unbind(sessionConfig, this.lifecycleCameraKeys);
                Unit unit = Unit.INSTANCE;
                return;
            }
            throw new UnsupportedOperationException("Unbind SessionConfig is not supported in concurrent camera mode call unbindAll() first.");
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public void unbindAll() {
        Trace.beginSection("CX:unbindAll");
        try {
            Threads.checkMainThread();
            setCameraOperatingMode(0);
            this.lifecycleCameraRepository.unbindAll(this.lifecycleCameraKeys);
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.camera.core.CameraProvider
    public boolean hasCamera(CameraSelector cameraSelector) throws CameraInfoUnavailableException {
        boolean z;
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        Trace.beginSection("CX:hasCamera");
        try {
            CameraX cameraX = this.cameraX;
            Intrinsics.checkNotNull(cameraX);
            cameraSelector.select(cameraX.getCameraRepository().getCameras());
            z = true;
        } catch (IllegalArgumentException unused) {
            z = false;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
        Trace.endSection();
        return z;
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCase... useCases) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        Intrinsics.checkNotNullParameter(useCases, "useCases");
        Trace.beginSection("CX:bindToLifecycle");
        try {
            if (getCameraOperatingMode() != 2) {
                setCameraOperatingMode(1);
                return bindToLifecycleInternal$default(this, lifecycleOwner, cameraSelector, null, null, null, new LegacySessionConfig(ArraysKt.filterNotNull(useCases), null, null, 6, null), 28, null);
            }
            throw new UnsupportedOperationException("bindToLifecycle for single camera is not supported in concurrent camera mode, call unbindAll() first");
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCaseGroup useCaseGroup) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        Intrinsics.checkNotNullParameter(useCaseGroup, "useCaseGroup");
        Trace.beginSection("CX:bindToLifecycle-UseCaseGroup");
        try {
            if (getCameraOperatingMode() != 2) {
                setCameraOperatingMode(1);
                return bindToLifecycleInternal$default(this, lifecycleOwner, cameraSelector, null, null, null, new LegacySessionConfig(useCaseGroup), 28, null);
            }
            throw new UnsupportedOperationException("bindToLifecycle for single camera is not supported in concurrent camera mode, call unbindAll() first.");
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, SessionConfig sessionConfig) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        Intrinsics.checkNotNullParameter(sessionConfig, "sessionConfig");
        Trace.beginSection("CX:bindToLifecycle-SessionConfig");
        try {
            if (getCameraOperatingMode() != 2) {
                setCameraOperatingMode(1);
                return bindToLifecycleInternal$default(this, lifecycleOwner, cameraSelector, null, null, null, sessionConfig, 28, null);
            }
            throw new UnsupportedOperationException("bindToLifecycle for single camera is not supported in concurrent camera mode, call unbindAll() first.");
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x01ea A[Catch: all -> 0x02a5, TryCatch #1 {all -> 0x02a5, blocks: (B:3:0x000e, B:5:0x0015, B:7:0x001b, B:10:0x0053, B:12:0x0059, B:14:0x0067, B:16:0x007d, B:18:0x0093, B:19:0x00c1, B:21:0x00c7, B:22:0x00dc, B:24:0x00e2, B:26:0x00f7, B:27:0x00fb, B:28:0x010e, B:65:0x0276, B:29:0x0129, B:30:0x0130, B:31:0x0131, B:32:0x0136, B:33:0x0137, B:35:0x014a, B:37:0x0150, B:38:0x0158, B:39:0x016e, B:41:0x0180, B:44:0x018b, B:45:0x0192, B:46:0x0193, B:49:0x01ae, B:51:0x01bc, B:53:0x01e1, B:59:0x01f9, B:64:0x0273, B:55:0x01ea, B:57:0x01f0, B:60:0x0234, B:61:0x0238, B:63:0x023e, B:68:0x027f, B:69:0x0286, B:70:0x0287, B:71:0x028c, B:72:0x028d, B:73:0x0294, B:74:0x0295, B:75:0x029c, B:76:0x029d, B:77:0x02a4), top: B:83:0x000e, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x023e A[Catch: all -> 0x02a5, LOOP:2: B:61:0x0238->B:63:0x023e, LOOP_END, TryCatch #1 {all -> 0x02a5, blocks: (B:3:0x000e, B:5:0x0015, B:7:0x001b, B:10:0x0053, B:12:0x0059, B:14:0x0067, B:16:0x007d, B:18:0x0093, B:19:0x00c1, B:21:0x00c7, B:22:0x00dc, B:24:0x00e2, B:26:0x00f7, B:27:0x00fb, B:28:0x010e, B:65:0x0276, B:29:0x0129, B:30:0x0130, B:31:0x0131, B:32:0x0136, B:33:0x0137, B:35:0x014a, B:37:0x0150, B:38:0x0158, B:39:0x016e, B:41:0x0180, B:44:0x018b, B:45:0x0192, B:46:0x0193, B:49:0x01ae, B:51:0x01bc, B:53:0x01e1, B:59:0x01f9, B:64:0x0273, B:55:0x01ea, B:57:0x01f0, B:60:0x0234, B:61:0x0238, B:63:0x023e, B:68:0x027f, B:69:0x0286, B:70:0x0287, B:71:0x028c, B:72:0x028d, B:73:0x0294, B:74:0x0295, B:75:0x029c, B:76:0x029d, B:77:0x02a4), top: B:83:0x000e, inners: #0 }] */
    @Override // androidx.camera.lifecycle.LifecycleCameraProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ConcurrentCamera bindToLifecycle(List<ConcurrentCamera.SingleCameraConfig> singleCameraConfigs) {
        Intrinsics.checkNotNullParameter(singleCameraConfigs, "singleCameraConfigs");
        Trace.beginSection("CX:bindToLifecycle-Concurrent");
        try {
            if (singleCameraConfigs.size() < 2) {
                throw new IllegalArgumentException("Concurrent camera needs two camera configs.");
            }
            if (singleCameraConfigs.size() > 2) {
                throw new IllegalArgumentException("Concurrent camera is only supporting two cameras at maximum.");
            }
            ConcurrentCamera.SingleCameraConfig singleCameraConfig = singleCameraConfigs.get(0);
            Intrinsics.checkNotNull(singleCameraConfig);
            ConcurrentCamera.SingleCameraConfig singleCameraConfig2 = singleCameraConfig;
            ConcurrentCamera.SingleCameraConfig singleCameraConfig3 = singleCameraConfigs.get(1);
            Intrinsics.checkNotNull(singleCameraConfig3);
            ConcurrentCamera.SingleCameraConfig singleCameraConfig4 = singleCameraConfig3;
            ArrayList arrayList = new ArrayList();
            if (Intrinsics.areEqual(singleCameraConfig2.getCameraSelector().getLensFacing(), singleCameraConfig4.getCameraSelector().getLensFacing())) {
                if (getCameraOperatingMode() == 2) {
                    throw new UnsupportedOperationException("Camera is already running, call unbindAll() before binding more cameras.");
                }
                if (!Intrinsics.areEqual(singleCameraConfig2.getLifecycleOwner(), singleCameraConfig4.getLifecycleOwner()) || !Intrinsics.areEqual(singleCameraConfig2.getUseCaseGroup().getViewPort(), singleCameraConfig4.getUseCaseGroup().getViewPort()) || !Intrinsics.areEqual(singleCameraConfig2.getUseCaseGroup().getEffects(), singleCameraConfig4.getUseCaseGroup().getEffects())) {
                    throw new IllegalArgumentException("Two camera configs need to have the same lifecycle owner, view port and effects.");
                }
                LifecycleOwner lifecycleOwner = singleCameraConfig2.getLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(lifecycleOwner, "getLifecycleOwner(...)");
                CameraSelector cameraSelector = singleCameraConfig2.getCameraSelector();
                Intrinsics.checkNotNullExpressionValue(cameraSelector, "getCameraSelector(...)");
                ViewPort viewPort = singleCameraConfig2.getUseCaseGroup().getViewPort();
                List<CameraEffect> effects = singleCameraConfig2.getUseCaseGroup().getEffects();
                Intrinsics.checkNotNullExpressionValue(effects, "getEffects(...)");
                ArrayList arrayList2 = new ArrayList();
                for (ConcurrentCamera.SingleCameraConfig singleCameraConfig5 : singleCameraConfigs) {
                    Intrinsics.checkNotNull(singleCameraConfig5);
                    for (UseCase useCase : singleCameraConfig5.getUseCaseGroup().getUseCases()) {
                        Intrinsics.checkNotNullExpressionValue(useCase, "next(...)");
                        UseCase useCase2 = useCase;
                        String physicalCameraId = singleCameraConfig5.getCameraSelector().getPhysicalCameraId();
                        if (physicalCameraId != null) {
                            useCase2.setPhysicalCameraId(physicalCameraId);
                        }
                    }
                    List<UseCase> useCases = singleCameraConfig5.getUseCaseGroup().getUseCases();
                    Intrinsics.checkNotNullExpressionValue(useCases, "getUseCases(...)");
                    arrayList2.addAll(useCases);
                }
                setCameraOperatingMode(1);
                arrayList.add(bindToLifecycleInternal$default(this, lifecycleOwner, cameraSelector, null, null, null, new LegacySessionConfig(arrayList2, viewPort, effects), 28, null));
            } else {
                Context context = getContext();
                Intrinsics.checkNotNull(context);
                if (context.getPackageManager().hasSystemFeature("android.hardware.camera.concurrent")) {
                    if (getCameraOperatingMode() == 1) {
                        throw new UnsupportedOperationException("Camera is already running, call unbindAll() before binding more cameras.");
                    }
                    ArrayList arrayList3 = new ArrayList();
                    try {
                        CameraSelector cameraSelector2 = singleCameraConfig2.getCameraSelector();
                        Intrinsics.checkNotNullExpressionValue(cameraSelector2, "getCameraSelector(...)");
                        CameraInfo cameraInfo = getCameraInfo(cameraSelector2);
                        CameraSelector cameraSelector3 = singleCameraConfig4.getCameraSelector();
                        Intrinsics.checkNotNullExpressionValue(cameraSelector3, "getCameraSelector(...)");
                        CameraInfo cameraInfo2 = getCameraInfo(cameraSelector3);
                        arrayList3.add(cameraInfo);
                        arrayList3.add(cameraInfo2);
                        if (!getActiveConcurrentCameraInfos().isEmpty() && !Intrinsics.areEqual(arrayList3, getActiveConcurrentCameraInfos())) {
                            throw new UnsupportedOperationException("Cameras are already running, call unbindAll() before binding more cameras.");
                        }
                        setCameraOperatingMode(2);
                        if (Objects.equals(singleCameraConfig2.getUseCaseGroup().getUseCases(), singleCameraConfig4.getUseCaseGroup().getUseCases()) && singleCameraConfig2.getUseCaseGroup().getUseCases().size() == 2) {
                            UseCase useCase3 = singleCameraConfig2.getUseCaseGroup().getUseCases().get(0);
                            UseCase useCase4 = singleCameraConfig2.getUseCaseGroup().getUseCases().get(1);
                            Intrinsics.checkNotNull(useCase3);
                            if (isVideoCapture(useCase3)) {
                                Intrinsics.checkNotNull(useCase4);
                                if (!isPreview(useCase4)) {
                                    if (isPreview(useCase3)) {
                                        Intrinsics.checkNotNull(useCase4);
                                        if (isVideoCapture(useCase4)) {
                                        }
                                        setActiveConcurrentCameraInfos(arrayList3);
                                    }
                                    while (r0.hasNext()) {
                                    }
                                    setActiveConcurrentCameraInfos(arrayList3);
                                }
                                LifecycleOwner lifecycleOwner2 = singleCameraConfig2.getLifecycleOwner();
                                Intrinsics.checkNotNullExpressionValue(lifecycleOwner2, "getLifecycleOwner(...)");
                                CameraSelector cameraSelector4 = singleCameraConfig2.getCameraSelector();
                                Intrinsics.checkNotNullExpressionValue(cameraSelector4, "getCameraSelector(...)");
                                CameraSelector cameraSelector5 = singleCameraConfig4.getCameraSelector();
                                CompositionSettings compositionSettings = singleCameraConfig2.getCompositionSettings();
                                Intrinsics.checkNotNullExpressionValue(compositionSettings, "getCompositionSettings(...)");
                                CompositionSettings compositionSettings2 = singleCameraConfig4.getCompositionSettings();
                                Intrinsics.checkNotNullExpressionValue(compositionSettings2, "getCompositionSettings(...)");
                                UseCaseGroup useCaseGroup = singleCameraConfig2.getUseCaseGroup();
                                Intrinsics.checkNotNullExpressionValue(useCaseGroup, "getUseCaseGroup(...)");
                                arrayList.add(bindToLifecycleInternal(lifecycleOwner2, cameraSelector4, cameraSelector5, compositionSettings, compositionSettings2, new LegacySessionConfig(useCaseGroup)));
                                setActiveConcurrentCameraInfos(arrayList3);
                            }
                        } else {
                            for (ConcurrentCamera.SingleCameraConfig singleCameraConfig6 : singleCameraConfigs) {
                                Intrinsics.checkNotNull(singleCameraConfig6);
                                LifecycleOwner lifecycleOwner3 = singleCameraConfig6.getLifecycleOwner();
                                Intrinsics.checkNotNullExpressionValue(lifecycleOwner3, "getLifecycleOwner(...)");
                                CameraSelector cameraSelector6 = singleCameraConfig6.getCameraSelector();
                                Intrinsics.checkNotNullExpressionValue(cameraSelector6, "getCameraSelector(...)");
                                UseCaseGroup useCaseGroup2 = singleCameraConfig6.getUseCaseGroup();
                                Intrinsics.checkNotNullExpressionValue(useCaseGroup2, "getUseCaseGroup(...)");
                                arrayList.add(bindToLifecycleInternal$default(this, lifecycleOwner3, cameraSelector6, null, null, null, new LegacySessionConfig(useCaseGroup2), 28, null));
                            }
                            setActiveConcurrentCameraInfos(arrayList3);
                        }
                    } catch (IllegalArgumentException unused) {
                        throw new IllegalArgumentException("Invalid camera selectors in camera configs.");
                    }
                } else {
                    throw new UnsupportedOperationException("Concurrent camera is not supported on the device.");
                }
            }
            return new ConcurrentCamera(arrayList);
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.camera.core.CameraProvider
    public List<CameraInfo> getAvailableCameraInfos() {
        Trace.beginSection("CX:getAvailableCameraInfos");
        try {
            ArrayList arrayList = new ArrayList();
            CameraX cameraX = this.cameraX;
            Intrinsics.checkNotNull(cameraX);
            LinkedHashSet<CameraInternal> cameras = cameraX.getCameraRepository().getCameras();
            Intrinsics.checkNotNullExpressionValue(cameras, "getCameras(...)");
            Iterator<CameraInternal> it = cameras.iterator();
            while (it.hasNext()) {
                CameraInfo cameraInfo = it.next().getCameraInfo();
                Intrinsics.checkNotNullExpressionValue(cameraInfo, "getCameraInfo(...)");
                arrayList.add(cameraInfo);
            }
            return arrayList;
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.camera.core.CameraProvider
    public List<List<CameraInfo>> getAvailableConcurrentCameraInfos() {
        Trace.beginSection("CX:getAvailableConcurrentCameraInfos");
        try {
            Objects.requireNonNull(this.cameraX);
            CameraX cameraX = this.cameraX;
            Intrinsics.checkNotNull(cameraX);
            Objects.requireNonNull(cameraX.getCameraFactory().getCameraCoordinator());
            CameraX cameraX2 = this.cameraX;
            Intrinsics.checkNotNull(cameraX2);
            List<List<CameraSelector>> concurrentCameraSelectors = cameraX2.getCameraFactory().getCameraCoordinator().getConcurrentCameraSelectors();
            Intrinsics.checkNotNullExpressionValue(concurrentCameraSelectors, "getConcurrentCameraSelectors(...)");
            ArrayList arrayList = new ArrayList();
            for (List<CameraSelector> list : concurrentCameraSelectors) {
                ArrayList arrayList2 = new ArrayList();
                for (CameraSelector cameraSelector : list) {
                    try {
                        Intrinsics.checkNotNull(cameraSelector);
                        arrayList2.add(getCameraInfo(cameraSelector));
                    } catch (IllegalArgumentException unused) {
                    }
                }
                arrayList.add(arrayList2);
            }
            return arrayList;
        } finally {
            Trace.endSection();
        }
    }

    @Override // androidx.camera.core.CameraProvider
    public boolean isConcurrentCameraModeOn() {
        return getCameraOperatingMode() == 2;
    }

    static /* synthetic */ Camera bindToLifecycleInternal$default(LifecycleCameraProviderImpl lifecycleCameraProviderImpl, LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, CameraSelector cameraSelector2, CompositionSettings compositionSettings, CompositionSettings compositionSettings2, SessionConfig sessionConfig, int i, Object obj) {
        if ((i & 4) != 0) {
            cameraSelector2 = null;
        }
        CameraSelector cameraSelector3 = cameraSelector2;
        if ((i & 8) != 0) {
            compositionSettings = CompositionSettings.DEFAULT;
        }
        CompositionSettings compositionSettings3 = compositionSettings;
        if ((i & 16) != 0) {
            compositionSettings2 = CompositionSettings.DEFAULT;
        }
        return lifecycleCameraProviderImpl.bindToLifecycleInternal(lifecycleOwner, cameraSelector, cameraSelector3, compositionSettings3, compositionSettings2, sessionConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Camera bindToLifecycleInternal(LifecycleOwner lifecycleOwner, CameraSelector primaryCameraSelector, CameraSelector secondaryCameraSelector, CompositionSettings primaryCompositionSettings, CompositionSettings secondaryCompositionSettings, SessionConfig sessionConfig) {
        CameraInternal cameraInternal;
        AdapterCameraInfo adapterCameraInfo;
        Trace.beginSection("CX:bindToLifecycle-internal");
        try {
            Threads.checkMainThread();
            CameraX cameraX = this.cameraX;
            Intrinsics.checkNotNull(cameraX);
            CameraInternal cameraInternalSelect = primaryCameraSelector.select(cameraX.getCameraRepository().getCameras());
            Intrinsics.checkNotNullExpressionValue(cameraInternalSelect, "select(...)");
            cameraInternalSelect.setPrimary(true);
            CameraInfo cameraInfo = getCameraInfo(primaryCameraSelector);
            Intrinsics.checkNotNull(cameraInfo, "null cannot be cast to non-null type androidx.camera.core.impl.AdapterCameraInfo");
            AdapterCameraInfo adapterCameraInfo2 = (AdapterCameraInfo) cameraInfo;
            if (secondaryCameraSelector != null) {
                CameraX cameraX2 = this.cameraX;
                Intrinsics.checkNotNull(cameraX2);
                CameraInternal cameraInternalSelect2 = secondaryCameraSelector.select(cameraX2.getCameraRepository().getCameras());
                cameraInternalSelect2.setPrimary(false);
                CameraInfo cameraInfo2 = getCameraInfo(secondaryCameraSelector);
                Intrinsics.checkNotNull(cameraInfo2, "null cannot be cast to non-null type androidx.camera.core.impl.AdapterCameraInfo");
                cameraInternal = cameraInternalSelect2;
                adapterCameraInfo = (AdapterCameraInfo) cameraInfo2;
            } else {
                cameraInternal = null;
                adapterCameraInfo = null;
            }
            CameraIdentifier cameraIdentifierFromAdapterInfos = CameraIdentifier.INSTANCE.fromAdapterInfos(adapterCameraInfo2, adapterCameraInfo);
            LifecycleCamera lifecycleCamera = this.lifecycleCameraRepository.getLifecycleCamera(lifecycleOwner, cameraIdentifierFromAdapterInfos);
            Collection<LifecycleCamera> lifecycleCameras = this.lifecycleCameraRepository.getLifecycleCameras();
            for (UseCase useCase : sessionConfig.getUseCases()) {
                for (LifecycleCamera lifecycleCamera2 : lifecycleCameras) {
                    Intrinsics.checkNotNullExpressionValue(lifecycleCamera2, "next(...)");
                    LifecycleCamera lifecycleCamera3 = lifecycleCamera2;
                    if (lifecycleCamera3.isBound(useCase) && !Intrinsics.areEqual(lifecycleCamera3.getLifecycleOwner(), lifecycleOwner)) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String str = String.format("Use case %s already bound to a different lifecycle.", Arrays.copyOf(new Object[]{useCase}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                        throw new IllegalStateException(str);
                    }
                }
            }
            if (lifecycleCamera == null) {
                LifecycleCameraRepository lifecycleCameraRepository = this.lifecycleCameraRepository;
                CameraX cameraX3 = this.cameraX;
                Intrinsics.checkNotNull(cameraX3);
                lifecycleCamera = lifecycleCameraRepository.createLifecycleCamera(lifecycleOwner, cameraX3.getCameraUseCaseAdapterProvider().provide(cameraInternalSelect, cameraInternal, adapterCameraInfo2, adapterCameraInfo, primaryCompositionSettings, secondaryCompositionSettings));
            }
            if (!sessionConfig.getUseCases().isEmpty()) {
                LifecycleCameraRepository lifecycleCameraRepository2 = this.lifecycleCameraRepository;
                Intrinsics.checkNotNull(lifecycleCamera);
                CameraX cameraX4 = this.cameraX;
                Intrinsics.checkNotNull(cameraX4);
                lifecycleCameraRepository2.bindToLifecycleCamera(lifecycleCamera, sessionConfig, cameraX4.getCameraFactory().getCameraCoordinator());
                this.lifecycleCameraKeys.add(LifecycleCameraRepository.Key.create(lifecycleOwner, cameraIdentifierFromAdapterInfos));
            } else {
                Intrinsics.checkNotNull(lifecycleCamera);
            }
            Trace.endSection();
            return lifecycleCamera;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // androidx.camera.core.CameraProvider
    public CameraInfo getCameraInfo(CameraSelector cameraSelector) {
        Object adapterCameraInfo;
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        Trace.beginSection("CX:getCameraInfo");
        try {
            CameraX cameraX = this.cameraX;
            Intrinsics.checkNotNull(cameraX);
            CameraInfoInternal cameraInfoInternal = cameraSelector.select(cameraX.getCameraRepository().getCameras()).getCameraInfoInternal();
            Intrinsics.checkNotNullExpressionValue(cameraInfoInternal, "getCameraInfoInternal(...)");
            CameraConfig cameraConfig = getCameraConfig(cameraSelector, cameraInfoInternal);
            CameraIdentifier.Companion companion = CameraIdentifier.INSTANCE;
            String cameraId = cameraInfoInternal.getCameraId();
            Intrinsics.checkNotNullExpressionValue(cameraId, "getCameraId(...)");
            CameraIdentifier cameraIdentifierCreate = companion.create(cameraId, null, cameraConfig.getCompatibilityId());
            synchronized (this.lock) {
                adapterCameraInfo = this.cameraInfoMap.get(cameraIdentifierCreate);
                if (adapterCameraInfo == null) {
                    adapterCameraInfo = new AdapterCameraInfo(cameraInfoInternal, cameraConfig);
                    this.cameraInfoMap.put(cameraIdentifierCreate, adapterCameraInfo);
                }
                Unit unit = Unit.INSTANCE;
            }
            AdapterCameraInfo adapterCameraInfo2 = (AdapterCameraInfo) adapterCameraInfo;
            Trace.endSection();
            return adapterCameraInfo2;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // androidx.camera.core.CameraProvider
    public void addCameraPresenceListener(Executor executor, CameraPresenceListener listener) {
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(listener, "listener");
        CameraX cameraX = this.cameraX;
        Intrinsics.checkNotNull(cameraX);
        cameraX.getCameraAvailabilityProvider().addCameraPresenceListener(listener, executor);
    }

    @Override // androidx.camera.core.CameraProvider
    public void removeCameraPresenceListener(CameraPresenceListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        CameraX cameraX = this.cameraX;
        Intrinsics.checkNotNull(cameraX);
        cameraX.getCameraAvailabilityProvider().removeCameraPresenceListener(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isVideoCapture(UseCase useCase) {
        return useCase.getCurrentConfig().containsOption(UseCaseConfig.OPTION_CAPTURE_TYPE) && useCase.getCurrentConfig().getCaptureType() == UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPreview(UseCase useCase) {
        return useCase instanceof Preview;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CameraConfig getCameraConfig(CameraSelector cameraSelector, CameraInfo cameraInfo) {
        Iterator<CameraFilter> it = cameraSelector.getCameraFilterSet().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        CameraConfig cameraConfig = null;
        while (it.hasNext()) {
            CameraFilter next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            CameraFilter cameraFilter = next;
            if (!Intrinsics.areEqual(cameraFilter.getIdentifier(), CameraFilter.DEFAULT_ID)) {
                CameraConfigProvider configProvider = ExtendedCameraConfigProviderStore.getConfigProvider(cameraFilter.getIdentifier());
                Context context = this.context;
                Intrinsics.checkNotNull(context);
                CameraConfig config = configProvider.getConfig(cameraInfo, context);
                if (config == null) {
                    continue;
                } else {
                    if (cameraConfig != null) {
                        throw new IllegalArgumentException("Cannot apply multiple extended camera configs at the same time.");
                    }
                    cameraConfig = config;
                }
            }
        }
        return cameraConfig == null ? CameraConfigs.defaultConfig() : cameraConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCameraOperatingMode() {
        CameraX cameraX = this.cameraX;
        if (cameraX == null) {
            return 0;
        }
        Intrinsics.checkNotNull(cameraX);
        return cameraX.getCameraFactory().getCameraCoordinator().getCameraOperatingMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCameraOperatingMode(int i) {
        CameraX cameraX = this.cameraX;
        if (cameraX == null) {
            return;
        }
        Intrinsics.checkNotNull(cameraX);
        cameraX.getCameraFactory().getCameraCoordinator().setCameraOperatingMode(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<CameraInfo> getActiveConcurrentCameraInfos() {
        CameraX cameraX = this.cameraX;
        if (cameraX == null) {
            return new ArrayList();
        }
        Intrinsics.checkNotNull(cameraX);
        List<CameraInfo> activeConcurrentCameraInfos = cameraX.getCameraFactory().getCameraCoordinator().getActiveConcurrentCameraInfos();
        Intrinsics.checkNotNullExpressionValue(activeConcurrentCameraInfos, "getActiveConcurrentCameraInfos(...)");
        return activeConcurrentCameraInfos;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setActiveConcurrentCameraInfos(List<? extends CameraInfo> list) {
        CameraX cameraX = this.cameraX;
        if (cameraX == null) {
            return;
        }
        Intrinsics.checkNotNull(cameraX);
        cameraX.getCameraFactory().getCameraCoordinator().setActiveConcurrentCameraInfos(list);
    }
}
