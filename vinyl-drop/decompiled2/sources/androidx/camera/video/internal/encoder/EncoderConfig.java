package androidx.camera.video.internal.encoder;

import android.media.MediaFormat;
import androidx.camera.core.impl.Timebase;

/* JADX INFO: loaded from: classes.dex */
public interface EncoderConfig {
    public static final int CODEC_PROFILE_NONE = -1;
    public static final String KEY_CSD_0 = "csd-0";
    public static final String KEY_CSD_1 = "csd-1";
    public static final String KEY_CSD_2 = "csd-2";

    Timebase getInputTimebase();

    String getMimeType();

    int getProfile();

    MediaFormat toMediaFormat() throws InvalidConfigException;
}
