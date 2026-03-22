package androidx.camera.video.internal.audio;

import androidx.camera.video.internal.audio.AutoValue_AudioSettings;
import androidx.camera.video.internal.workaround.DefaultEncoderProfilesProvider;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AudioSettings {
    public static final List<Integer> COMMON_SAMPLE_RATES = Collections.unmodifiableList(Arrays.asList(192000, 48000, Integer.valueOf(DefaultEncoderProfilesProvider.DEFAULT_AUDIO_SAMPLE_RATE), 24000, 22050, 16000, 12000, 11025, 8000, 4800));

    public abstract int getAudioFormat();

    public abstract int getAudioSource();

    public abstract int getCaptureSampleRate();

    public abstract int getChannelCount();

    public abstract int getEncodeSampleRate();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_AudioSettings.Builder().setAudioSource(-1).setCaptureSampleRate(-1).setEncodeSampleRate(-1).setChannelCount(-1).setAudioFormat(-1);
    }

    AudioSettings() {
    }

    public int getBytesPerFrame() {
        return AudioUtils.getBytesPerFrame(getAudioFormat(), getChannelCount());
    }

    public static abstract class Builder {
        abstract AudioSettings autoBuild();

        public abstract Builder setAudioFormat(int i);

        public abstract Builder setAudioSource(int i);

        public abstract Builder setCaptureSampleRate(int i);

        public abstract Builder setChannelCount(int i);

        public abstract Builder setEncodeSampleRate(int i);

        public final AudioSettings build() {
            String str;
            AudioSettings audioSettingsAutoBuild = autoBuild();
            if (audioSettingsAutoBuild.getAudioSource() != -1) {
                str = "";
            } else {
                str = " audioSource";
            }
            if (audioSettingsAutoBuild.getCaptureSampleRate() <= 0) {
                str = str + " captureSampleRate";
            }
            if (audioSettingsAutoBuild.getEncodeSampleRate() <= 0) {
                str = str + " encodeSampleRate";
            }
            if (audioSettingsAutoBuild.getChannelCount() <= 0) {
                str = str + " channelCount";
            }
            if (audioSettingsAutoBuild.getAudioFormat() == -1) {
                str = str + " audioFormat";
            }
            if (str.isEmpty()) {
                return audioSettingsAutoBuild;
            }
            throw new IllegalArgumentException("Required settings missing or non-positive:" + str);
        }

        Builder() {
        }
    }
}
