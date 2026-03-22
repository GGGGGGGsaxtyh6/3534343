package androidx.camera.video.internal;

import android.net.Uri;
import androidx.camera.core.Logger;
import androidx.camera.video.FileDescriptorOutputOptions;
import androidx.camera.video.FileOutputOptions;
import androidx.camera.video.MediaStoreOutputOptions;
import androidx.camera.video.OutputOptions;
import androidx.camera.video.internal.utils.StorageUtil;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OutputStorageImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/camera/video/internal/OutputStorageImpl;", "Landroidx/camera/video/internal/OutputStorage;", "outputOptions", "Landroidx/camera/video/OutputOptions;", "<init>", "(Landroidx/camera/video/OutputOptions;)V", "getOutputOptions", "getAvailableBytes", "", "Companion", "camera-video_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class OutputStorageImpl implements OutputStorage {
    private static final Companion Companion = new Companion(null);
    private static final String TAG = "OutputStorageImpl";
    private final OutputOptions outputOptions;

    public OutputStorageImpl(OutputOptions outputOptions) {
        Intrinsics.checkNotNullParameter(outputOptions, "outputOptions");
        this.outputOptions = outputOptions;
    }

    @Override // androidx.camera.video.internal.OutputStorage
    public OutputOptions getOutputOptions() {
        return this.outputOptions;
    }

    @Override // androidx.camera.video.internal.OutputStorage
    public long getAvailableBytes() {
        try {
            OutputOptions outputOptions = this.outputOptions;
            if (outputOptions instanceof FileOutputOptions) {
                File parentFile = ((FileOutputOptions) outputOptions).getFile().getParentFile();
                Intrinsics.checkNotNull(parentFile);
                return StorageUtil.getAvailableBytes(parentFile);
            }
            if (outputOptions instanceof MediaStoreOutputOptions) {
                Uri collectionUri = ((MediaStoreOutputOptions) outputOptions).getCollectionUri();
                Intrinsics.checkNotNullExpressionValue(collectionUri, "getCollectionUri(...)");
                return StorageUtil.getAvailableBytesForMediaStoreUri(collectionUri);
            }
            if (outputOptions instanceof FileDescriptorOutputOptions) {
                return Long.MAX_VALUE;
            }
            throw new AssertionError("Unknown OutputOptions: " + this.outputOptions);
        } catch (RuntimeException e) {
            Logger.w(TAG, "Fail to access the available bytes.", e);
            return Long.MAX_VALUE;
        }
    }

    /* JADX INFO: compiled from: OutputStorageImpl.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/video/internal/OutputStorageImpl$Companion;", "", "<init>", "()V", "TAG", "", "camera-video_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
