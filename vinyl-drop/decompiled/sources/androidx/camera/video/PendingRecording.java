package androidx.camera.video;

import android.content.Context;
import androidx.camera.core.impl.utils.ContextUtil;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.PermissionChecker;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

/* JADX INFO: compiled from: PendingRecording.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0014\u001a\u00020\u0003H\u0001J\b\u0010\u0015\u001a\u00020\u0005H\u0001J\b\u0010\u0016\u001a\u00020\u0007H\u0001J\n\u0010\u0017\u001a\u0004\u0018\u00010\u000fH\u0001J\u0010\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0001J\b\u0010\u0010\u001a\u00020\u0011H\u0001J\b\u0010\u0012\u001a\u00020\u0011H\u0001J\b\u0010\u0013\u001a\u00020\u0011H\u0001J\u0012\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u001a\u001a\u00020\u0011H\u0007J\b\u0010\u001b\u001a\u00020\u0000H\u0007J\u001e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007J\u001c\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0087@¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Landroidx/camera/video/PendingRecording;", "", "context", "Landroid/content/Context;", "recorder", "Landroidx/camera/video/Recorder;", "outputOptions", "Landroidx/camera/video/OutputOptions;", "<init>", "(Landroid/content/Context;Landroidx/camera/video/Recorder;Landroidx/camera/video/OutputOptions;)V", "applicationContext", "eventListener", "Landroidx/core/util/Consumer;", "Landroidx/camera/video/VideoRecordEvent;", "listenerExecutor", "Ljava/util/concurrent/Executor;", "isAudioEnabled", "", "isAudioInitialMuted", "isPersistent", "getApplicationContext", "getRecorder", "getOutputOptions", "getListenerExecutor", "getEventListener", "withAudioEnabled", "initialMuted", "asPersistentRecording", "start", "Landroidx/camera/video/Recording;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "(Landroidx/core/util/Consumer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "camera-video_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PendingRecording {
    private final Context applicationContext;
    private Consumer<VideoRecordEvent> eventListener;
    private boolean isAudioEnabled;
    private boolean isAudioInitialMuted;
    private boolean isPersistent;
    private Executor listenerExecutor;
    private final OutputOptions outputOptions;
    private final Recorder recorder;

    public final PendingRecording withAudioEnabled() {
        return withAudioEnabled$default(this, false, 1, null);
    }

    public PendingRecording(Context context, Recorder recorder, OutputOptions outputOptions) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(recorder, "recorder");
        Intrinsics.checkNotNullParameter(outputOptions, "outputOptions");
        this.recorder = recorder;
        this.outputOptions = outputOptions;
        Context applicationContext = ContextUtil.getApplicationContext(context);
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.applicationContext = applicationContext;
    }

    public final Context getApplicationContext() {
        return this.applicationContext;
    }

    public final Recorder getRecorder() {
        return this.recorder;
    }

    public final OutputOptions getOutputOptions() {
        return this.outputOptions;
    }

    public final Executor getListenerExecutor() {
        return this.listenerExecutor;
    }

    public final Consumer<VideoRecordEvent> getEventListener() {
        return this.eventListener;
    }

    /* JADX INFO: renamed from: isAudioEnabled, reason: from getter */
    public final boolean getIsAudioEnabled() {
        return this.isAudioEnabled;
    }

    /* JADX INFO: renamed from: isAudioInitialMuted, reason: from getter */
    public final boolean getIsAudioInitialMuted() {
        return this.isAudioInitialMuted;
    }

    /* JADX INFO: renamed from: isPersistent, reason: from getter */
    public final boolean getIsPersistent() {
        return this.isPersistent;
    }

    public static /* synthetic */ PendingRecording withAudioEnabled$default(PendingRecording pendingRecording, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return pendingRecording.withAudioEnabled(z);
    }

    public final PendingRecording withAudioEnabled(boolean initialMuted) {
        if (PermissionChecker.checkSelfPermission(this.applicationContext, "android.permission.RECORD_AUDIO") == -1) {
            throw new SecurityException("Attempted to enable audio for recording but application does not have RECORD_AUDIO permission granted.");
        }
        Preconditions.checkState(this.recorder.isAudioSupported(), "The Recorder this recording is associated to doesn't support audio.");
        this.isAudioEnabled = true;
        this.isAudioInitialMuted = initialMuted;
        return this;
    }

    public final PendingRecording asPersistentRecording() {
        this.isPersistent = true;
        return this;
    }

    public final Recording start(Executor listenerExecutor, Consumer<VideoRecordEvent> listener) {
        Intrinsics.checkNotNullParameter(listenerExecutor, "listenerExecutor");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Preconditions.checkNotNull(listenerExecutor, "Listener Executor can't be null.");
        Preconditions.checkNotNull(listener, "Event listener can't be null");
        this.listenerExecutor = listenerExecutor;
        this.eventListener = listener;
        Recording recordingStart = this.recorder.start(this);
        Intrinsics.checkNotNullExpressionValue(recordingStart, "start(...)");
        return recordingStart;
    }

    public final Object start(Consumer<VideoRecordEvent> consumer, Continuation<? super Recording> continuation) {
        Executor executorDirectExecutor;
        CoroutineContext.Element element = continuation.getContext().get(ContinuationInterceptor.INSTANCE);
        CoroutineDispatcher coroutineDispatcher = element instanceof CoroutineDispatcher ? (CoroutineDispatcher) element : null;
        if (coroutineDispatcher == null || (executorDirectExecutor = ExecutorsKt.asExecutor(coroutineDispatcher)) == null) {
            executorDirectExecutor = CameraXExecutors.directExecutor();
            Intrinsics.checkNotNullExpressionValue(executorDirectExecutor, "directExecutor(...)");
        }
        return start(executorDirectExecutor, consumer);
    }
}
