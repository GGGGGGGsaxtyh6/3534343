package androidx.camera.video.internal.utils;

import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import androidx.camera.core.Logger;
import androidx.camera.video.AudioStats;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: StorageUtil.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0005H\u0007J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0007H\u0007J\u0014\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0013j\u0002`\u0014H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/camera/video/internal/utils/StorageUtil;", "", "<init>", "()V", "TAG", "", "getAvailableBytes", "", "file", "Ljava/io/File;", "path", "getAvailableBytesForMediaStoreUri", "uri", "Landroid/net/Uri;", "formatSize", "bytes", "isStorageFullException", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "camera-video_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class StorageUtil {
    public static final StorageUtil INSTANCE = new StorageUtil();
    private static final String TAG = "StorageUtil";

    private StorageUtil() {
    }

    @JvmStatic
    public static final long getAvailableBytes(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        return getAvailableBytes(path);
    }

    @JvmStatic
    public static final long getAvailableBytes(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        return new StatFs(path).getAvailableBytes();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004f, code lost:
    
        if (r0.equals("external") == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0058, code lost:
    
        if (r0.equals("external_primary") != false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005a, code lost:
    
        r3 = android.os.Environment.getExternalStorageDirectory();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, "getExternalStorageDirectory(...)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0067, code lost:
    
        return getAvailableBytes(r3);
     */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final long getAvailableBytesForMediaStoreUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (!Intrinsics.areEqual(uri.getScheme(), "content")) {
            throw new IllegalStateException(("Not a content uri: " + uri).toString());
        }
        String str = uri.getPathSegments().get(0);
        if (str != null) {
            int iHashCode = str.hashCode();
            if (iHashCode != -1921573490) {
                if (iHashCode != -1820761141) {
                    if (iHashCode == 570410685 && str.equals("internal")) {
                        File dataDirectory = Environment.getDataDirectory();
                        Intrinsics.checkNotNullExpressionValue(dataDirectory, "getDataDirectory(...)");
                        return getAvailableBytes(dataDirectory);
                    }
                }
            }
        }
        Logger.w(TAG, "Unknown MediaStore URI: " + uri);
        return Long.MAX_VALUE;
    }

    @JvmStatic
    public static final String formatSize(long bytes) {
        if (bytes < 0) {
            throw new IllegalArgumentException("Bytes cannot be negative".toString());
        }
        int i = 0;
        String[] strArr = {"B", "KB", "MB", "GB", "TB"};
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double d = bytes;
        double d2 = d;
        while (d2 >= 1024.0d && i < 4) {
            d2 /= 1024.0d;
            i++;
        }
        if (i == 0) {
            return decimalFormat.format(d2) + ' ' + strArr[i];
        }
        StringBuilder sb = new StringBuilder();
        while (-1 < i) {
            double dPow = Math.pow(1024.0d, i);
            double dFloor = Math.floor(d / dPow);
            if (dFloor > AudioStats.AUDIO_AMPLITUDE_NONE) {
                sb.append(decimalFormat.format(dFloor)).append(" ").append(strArr[i]).append(" ");
                d -= dFloor * dPow;
            }
            i--;
        }
        return StringsKt.trim(sb).toString();
    }

    @JvmStatic
    public static final boolean isStorageFullException(Exception e) {
        String message;
        Intrinsics.checkNotNullParameter(e, "e");
        return (e instanceof FileNotFoundException) && (message = e.getMessage()) != null && StringsKt.contains$default((CharSequence) message, (CharSequence) "No space left on device", false, 2, (Object) null);
    }
}
