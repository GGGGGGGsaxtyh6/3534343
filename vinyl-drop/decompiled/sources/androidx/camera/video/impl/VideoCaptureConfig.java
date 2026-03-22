package androidx.camera.video.impl;

import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.internal.ThreadConfig;
import androidx.camera.video.VideoCapture;
import androidx.camera.video.VideoOutput;
import androidx.camera.video.internal.encoder.VideoEncoderInfo;
import androidx.core.util.Preconditions;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class VideoCaptureConfig<T extends VideoOutput> implements UseCaseConfig<VideoCapture<T>>, ImageOutputConfig, ThreadConfig {
    private final OptionsBundle mConfig;
    public static final Config.Option<VideoOutput> OPTION_VIDEO_OUTPUT = Config.Option.create("camerax.video.VideoCapture.videoOutput", VideoOutput.class);
    public static final Config.Option<VideoEncoderInfo.Finder> OPTION_VIDEO_ENCODER_INFO_FINDER = Config.Option.create("camerax.video.VideoCapture.videoEncoderInfoFinder", VideoEncoderInfo.Finder.class);
    public static final Config.Option<Boolean> OPTION_FORCE_ENABLE_SURFACE_PROCESSING = Config.Option.create("camerax.video.VideoCapture.forceEnableSurfaceProcessing", Boolean.class);

    @Override // androidx.camera.core.impl.ImageInputConfig
    public int getInputFormat() {
        return 34;
    }

    public VideoCaptureConfig(OptionsBundle optionsBundle) {
        Preconditions.checkArgument(optionsBundle.containsOption(OPTION_VIDEO_OUTPUT));
        this.mConfig = optionsBundle;
    }

    public T getVideoOutput() {
        return (T) Objects.requireNonNull((VideoOutput) retrieveOption(OPTION_VIDEO_OUTPUT));
    }

    public VideoEncoderInfo.Finder getVideoEncoderInfoFinder() {
        return (VideoEncoderInfo.Finder) Objects.requireNonNull((VideoEncoderInfo.Finder) retrieveOption(OPTION_VIDEO_ENCODER_INFO_FINDER));
    }

    public boolean isSurfaceProcessingForceEnabled() {
        return ((Boolean) Objects.requireNonNull((Boolean) retrieveOption(OPTION_FORCE_ENABLE_SURFACE_PROCESSING, false))).booleanValue();
    }

    @Override // androidx.camera.core.impl.ReadableConfig
    public Config getConfig() {
        return this.mConfig;
    }
}
