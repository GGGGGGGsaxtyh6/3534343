package com.google.android.gms.common.util;

import android.os.SystemClock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
public interface Clock {
    default long currentThreadTimeMillis() {
        return SystemClock.currentThreadTimeMillis();
    }

    long currentTimeMillis();

    long elapsedRealtime();

    long nanoTime();
}
