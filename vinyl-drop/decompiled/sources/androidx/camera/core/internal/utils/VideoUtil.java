package androidx.camera.core.internal.utils;

/* JADX INFO: loaded from: classes.dex */
public final class VideoUtil {
    private static final String TAG = "VideoUtil";

    private VideoUtil() {
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getAbsolutePathFromUri(android.content.ContentResolver r9, android.net.Uri r10) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "_data"
            r1 = 1
            r2 = 0
            java.lang.String[] r5 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36
            r1 = 0
            r5[r1] = r0     // Catch: java.lang.Throwable -> L33 java.lang.RuntimeException -> L36
            r7 = 0
            r8 = 0
            r6 = 0
            r3 = r9
            r4 = r10
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.RuntimeException -> L31 java.lang.Throwable -> L33
            java.lang.Object r9 = androidx.core.util.Preconditions.checkNotNull(r2)     // Catch: java.lang.RuntimeException -> L31 java.lang.Throwable -> L33
            android.database.Cursor r9 = (android.database.Cursor) r9     // Catch: java.lang.RuntimeException -> L31 java.lang.Throwable -> L33
            int r10 = r9.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> L29 java.lang.RuntimeException -> L2d
            r9.moveToFirst()     // Catch: java.lang.Throwable -> L29 java.lang.RuntimeException -> L2d
            java.lang.String r10 = r9.getString(r10)     // Catch: java.lang.Throwable -> L29 java.lang.RuntimeException -> L2d
            if (r9 == 0) goto L28
            r9.close()
        L28:
            return r10
        L29:
            r0 = move-exception
            r10 = r0
            r2 = r9
            goto L58
        L2d:
            r0 = move-exception
            r10 = r0
            r2 = r9
            goto L39
        L31:
            r0 = move-exception
            goto L38
        L33:
            r0 = move-exception
            r10 = r0
            goto L58
        L36:
            r0 = move-exception
            r4 = r10
        L38:
            r10 = r0
        L39:
            java.lang.String r9 = "VideoUtil"
            java.lang.String r0 = "Failed in getting absolute path for Uri %s with Exception %s"
            java.lang.String r1 = r4.toString()     // Catch: java.lang.Throwable -> L33
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> L33
            java.lang.Object[] r10 = new java.lang.Object[]{r1, r10}     // Catch: java.lang.Throwable -> L33
            java.lang.String r10 = java.lang.String.format(r0, r10)     // Catch: java.lang.Throwable -> L33
            androidx.camera.core.Logger.e(r9, r10)     // Catch: java.lang.Throwable -> L33
            java.lang.String r9 = ""
            if (r2 == 0) goto L57
            r2.close()
        L57:
            return r9
        L58:
            if (r2 == 0) goto L5d
            r2.close()
        L5d:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.utils.VideoUtil.getAbsolutePathFromUri(android.content.ContentResolver, android.net.Uri):java.lang.String");
    }
}
