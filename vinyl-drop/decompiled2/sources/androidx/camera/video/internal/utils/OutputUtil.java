package androidx.camera.video.internal.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import androidx.camera.core.Logger;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class OutputUtil {
    private static final String TAG = "OutputUtil";

    private OutputUtil() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getAbsolutePathFromUri(ContentResolver contentResolver, Uri uri, String str) throws Throwable {
        Throwable th;
        Uri uri2;
        RuntimeException runtimeException;
        Cursor cursorQuery;
        ?? r1 = 0;
        try {
            try {
                try {
                    uri2 = uri;
                } catch (Throwable th2) {
                    th = th2;
                    r1 = contentResolver;
                    if (r1 == 0) {
                        r1.close();
                        throw th;
                    }
                    throw th;
                }
            } catch (RuntimeException e) {
                e = e;
                uri2 = uri;
            }
            try {
                cursorQuery = contentResolver.query(uri2, new String[]{str}, null, null, null);
                if (cursorQuery == null) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
                try {
                    int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow(str);
                    cursorQuery.moveToFirst();
                    String string = cursorQuery.getString(columnIndexOrThrow);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return string;
                } catch (RuntimeException e2) {
                    runtimeException = e2;
                    Logger.e(TAG, String.format("Failed in getting absolute path for Uri %s with Exception %s", uri2.toString(), runtimeException.toString()));
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
            } catch (RuntimeException e3) {
                e = e3;
                runtimeException = e;
                cursorQuery = null;
                Logger.e(TAG, String.format("Failed in getting absolute path for Uri %s with Exception %s", uri2.toString(), runtimeException.toString()));
                if (cursorQuery != null) {
                }
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            if (r1 == 0) {
            }
        }
    }

    public static boolean createParentFolder(File file) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return false;
        }
        return parentFile.exists() ? parentFile.isDirectory() : parentFile.mkdirs();
    }
}
