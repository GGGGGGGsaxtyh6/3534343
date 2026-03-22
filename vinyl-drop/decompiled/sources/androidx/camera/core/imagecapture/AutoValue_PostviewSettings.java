package androidx.camera.core.imagecapture;

import android.util.Size;

/* JADX INFO: loaded from: classes.dex */
final class AutoValue_PostviewSettings extends PostviewSettings {
    private final int inputFormat;
    private final Size resolution;

    AutoValue_PostviewSettings(Size size, int i) {
        if (size == null) {
            throw new NullPointerException("Null resolution");
        }
        this.resolution = size;
        this.inputFormat = i;
    }

    @Override // androidx.camera.core.imagecapture.PostviewSettings
    public Size getResolution() {
        return this.resolution;
    }

    @Override // androidx.camera.core.imagecapture.PostviewSettings
    public int getInputFormat() {
        return this.inputFormat;
    }

    public String toString() {
        return "PostviewSettings{resolution=" + this.resolution + ", inputFormat=" + this.inputFormat + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PostviewSettings) {
            PostviewSettings postviewSettings = (PostviewSettings) obj;
            if (this.resolution.equals(postviewSettings.getResolution()) && this.inputFormat == postviewSettings.getInputFormat()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((this.resolution.hashCode() ^ 1000003) * 1000003) ^ this.inputFormat;
    }
}
