package androidx.camera.core.imagecapture;

import android.util.Size;

/* JADX INFO: loaded from: classes.dex */
public abstract class PostviewSettings {
    public abstract int getInputFormat();

    public abstract Size getResolution();

    public static PostviewSettings create(Size size, int i) {
        return new AutoValue_PostviewSettings(size, i);
    }
}
