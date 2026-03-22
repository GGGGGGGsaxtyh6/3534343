package androidx.camera.core.internal.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class VideoUtil {
    private static final String TAG = "VideoUtil";

    private VideoUtil() {
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getAbsolutePathFromUri(ContentResolver contentResolver, Uri uri) throws Throwable {
        Throwable th;
        Uri uri2;
        RuntimeException runtimeException;
        Cursor cursor;
        Cursor cursorQuery = null;
        try {
            try {
                uri2 = uri;
            } catch (RuntimeException e) {
                e = e;
                uri2 = uri;
            }
            try {
                cursorQuery = contentResolver.query(uri2, new String[]{"_data"}, null, null, null);
                cursor = (Cursor) Preconditions.checkNotNull(cursorQuery);
            } catch (RuntimeException e2) {
                e = e2;
                runtimeException = e;
                Logger.e(TAG, String.format("Failed in getting absolute path for Uri %s with Exception %s", uri2.toString(), runtimeException.toString()));
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return "";
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow("_data");
            cursor.moveToFirst();
            String string = cursor.getString(columnIndexOrThrow);
            if (cursor != null) {
                cursor.close();
            }
            return string;
        } catch (RuntimeException e3) {
            runtimeException = e3;
            cursorQuery = cursor;
            Logger.e(TAG, String.format("Failed in getting absolute path for Uri %s with Exception %s", uri2.toString(), runtimeException.toString()));
            if (cursorQuery != null) {
            }
            return "";
        } catch (Throwable th3) {
            th = th3;
            cursorQuery = cursor;
            if (cursorQuery != null) {
                cursorQuery.close();
                throw th;
            }
            throw th;
        }
    }
}
