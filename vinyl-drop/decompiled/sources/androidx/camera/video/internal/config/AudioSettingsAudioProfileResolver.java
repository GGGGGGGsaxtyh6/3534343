package androidx.camera.video.internal.config;

import android.util.Range;
import android.util.Rational;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.video.AudioSpec;
import androidx.camera.video.internal.audio.AudioSettings;
import androidx.core.util.Supplier;

/* JADX INFO: loaded from: classes.dex */
public final class AudioSettingsAudioProfileResolver implements Supplier<AudioSettings> {
    private static final String TAG = "AudioSrcAdPrflRslvr";
    private final EncoderProfilesProxy.AudioProfileProxy mAudioProfile;
    private final AudioSpec mAudioSpec;
    private final Rational mCaptureToEncodeRatio;

    public AudioSettingsAudioProfileResolver(AudioSpec audioSpec, EncoderProfilesProxy.AudioProfileProxy audioProfileProxy, Rational rational) {
        this.mAudioSpec = audioSpec;
        this.mAudioProfile = audioProfileProxy;
        this.mCaptureToEncodeRatio = rational;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.core.util.Supplier
    public AudioSettings get() {
        int iResolveAudioSource = AudioConfigUtil.resolveAudioSource(this.mAudioSpec);
        int iResolveAudioSourceFormat = AudioConfigUtil.resolveAudioSourceFormat(this.mAudioSpec);
        int channelCount = this.mAudioSpec.getChannelCount();
        int channels = this.mAudioProfile.getChannels();
        if (channelCount == -1) {
            Logger.d(TAG, "Resolved AUDIO channel count from AudioProfile: " + channels);
            channelCount = channels;
        } else {
            Logger.d(TAG, "Media spec AUDIO channel count overrides AudioProfile [AudioProfile channel count: " + channels + ", Resolved Channel Count: " + channelCount + "]");
        }
        Range<Integer> sampleRate = this.mAudioSpec.getSampleRate();
        int sampleRate2 = this.mAudioProfile.getSampleRate();
        CaptureEncodeRates captureEncodeRatesResolveSampleRates = AudioConfigUtil.resolveSampleRates(sampleRate, sampleRate2, channelCount, iResolveAudioSourceFormat, this.mCaptureToEncodeRatio);
        Logger.d(TAG, "Using resolved AUDIO sample rate or nearest supported from AudioProfile: Capture sample rate: " + captureEncodeRatesResolveSampleRates.getCaptureRate() + "Hz. Encode sample rate: " + captureEncodeRatesResolveSampleRates.getEncodeRate() + "Hz. [AudioProfile sample rate: " + sampleRate2 + "Hz]");
        return AudioSettings.builder().setAudioSource(iResolveAudioSource).setAudioFormat(iResolveAudioSourceFormat).setChannelCount(channelCount).setCaptureSampleRate(captureEncodeRatesResolveSampleRates.getCaptureRate()).setEncodeSampleRate(captureEncodeRatesResolveSampleRates.getEncodeRate()).build();
    }
}
