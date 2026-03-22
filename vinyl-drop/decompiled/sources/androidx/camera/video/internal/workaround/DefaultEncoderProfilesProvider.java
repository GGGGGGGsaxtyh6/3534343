package androidx.camera.video.internal.workaround;

import android.util.Size;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.video.Quality;
import androidx.camera.video.internal.encoder.VideoEncoderInfo;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.Scopes;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultEncoderProfilesProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 :2\u00020\u0001:\u0001:B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\"\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u0016H\u0002J,\u0010$\u001a\u00020\u00172\b\b\u0002\u0010%\u001a\u00020\u00162\b\b\u0002\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)H\u0002Jf\u0010*\u001a\u00020\u001f2\b\b\u0002\u0010+\u001a\u00020\u00162\b\b\u0002\u0010,\u001a\u00020-2\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u00162\b\b\u0002\u0010.\u001a\u00020\u00162\b\b\u0002\u0010/\u001a\u00020\u00162\b\b\u0002\u00100\u001a\u00020\u00162\b\b\u0002\u00101\u001a\u00020\u00162\b\b\u0002\u00102\u001a\u00020\u0016H\u0002JD\u00103\u001a\u00020)2\b\b\u0002\u0010+\u001a\u00020\u00162\b\b\u0002\u0010,\u001a\u00020-2\b\b\u0002\u00104\u001a\u00020\u00162\b\b\u0002\u00105\u001a\u00020\u00162\b\b\u0002\u00106\u001a\u00020\u00162\b\b\u0002\u0010/\u001a\u00020\u0016H\u0002J\f\u00107\u001a\u00020\u0016*\u00020\u0006H\u0002J\u001c\u00108\u001a\u0004\u0018\u000109*\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001a\u001a\u00020\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000RG\u0010\u000b\u001a.\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r \u000e*\u0015\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\u0005¢\u0006\u0002\b\u000f0\f¢\u0006\u0002\b\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Landroidx/camera/video/internal/workaround/DefaultEncoderProfilesProvider;", "Landroidx/camera/core/impl/EncoderProfilesProvider;", "cameraInfo", "Landroidx/camera/core/impl/CameraInfoInternal;", "targetQualities", "", "Landroidx/camera/video/Quality;", "videoEncoderInfoFinder", "Landroidx/camera/video/internal/encoder/VideoEncoderInfo$Finder;", "<init>", "(Landroidx/camera/core/impl/CameraInfoInternal;Ljava/util/List;Landroidx/camera/video/internal/encoder/VideoEncoderInfo$Finder;)V", "supportedSizes", "", "Landroid/util/Size;", "kotlin.jvm.PlatformType", "Lorg/jspecify/annotations/NonNull;", "getSupportedSizes", "()Ljava/util/List;", "supportedSizes$delegate", "Lkotlin/Lazy;", "encoderProfilesMap", "", "", "Landroidx/camera/core/impl/EncoderProfilesProxy;", "hasProfile", "", "quality", "getAll", "getProfileInternal", "generateEncoderProfiles", "generateVideoProfiles", "Landroidx/camera/core/impl/EncoderProfilesProxy$VideoProfileProxy;", "resolveVideoProfile", "width", "height", "bitrate", "createDefaultEncoderProfiles", "defaultDurationSeconds", "recommendedFileFormat", "videoProfile", "audioProfile", "Landroidx/camera/core/impl/EncoderProfilesProxy$AudioProfileProxy;", "createDefaultVideoProfile", "codec", "mimeType", "", "frameRate", Scopes.PROFILE, "bitDepth", "chromaSubsampling", "hdrFormat", "createDefaultAudioProfile", "bitRate", "sampleRate", "channels", "getTypicalBitrate", "find", "Landroidx/camera/video/Quality$ConstantQuality;", "Companion", "camera-video_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class DefaultEncoderProfilesProvider implements EncoderProfilesProvider {
    public static final int DEFAULT_AUDIO_BITRATE = 96000;
    public static final int DEFAULT_AUDIO_CHANNELS = 1;
    public static final int DEFAULT_AUDIO_CODEC = 3;
    public static final String DEFAULT_AUDIO_MIME_TYPE = "audio/mp4a-latm";
    public static final int DEFAULT_AUDIO_PROFILE = 2;
    public static final int DEFAULT_AUDIO_SAMPLE_RATE = 44100;
    public static final int DEFAULT_DURATION_SECONDS = 60;
    public static final int DEFAULT_OUTPUT_FORMAT = 2;
    public static final int DEFAULT_VIDEO_BITRATE_FHD = 10000000;
    public static final int DEFAULT_VIDEO_BITRATE_HD = 4000000;
    public static final int DEFAULT_VIDEO_BITRATE_SD = 2000000;
    public static final int DEFAULT_VIDEO_BITRATE_UHD = 40000000;
    public static final int DEFAULT_VIDEO_BIT_DEPTH = 8;
    public static final int DEFAULT_VIDEO_CHROMA_SUBSAMPLING = 0;
    public static final int DEFAULT_VIDEO_CODEC = 2;
    public static final int DEFAULT_VIDEO_FRAME_RATE = 30;
    public static final int DEFAULT_VIDEO_HDR_FORMAT = 0;
    public static final String DEFAULT_VIDEO_MIME_TYPE = "video/avc";
    public static final int DEFAULT_VIDEO_PROFILE = -1;
    private final CameraInfoInternal cameraInfo;
    private final Map<Integer, EncoderProfilesProxy> encoderProfilesMap;

    /* JADX INFO: renamed from: supportedSizes$delegate, reason: from kotlin metadata */
    private final Lazy supportedSizes;
    private final List<Quality> targetQualities;
    private final VideoEncoderInfo.Finder videoEncoderInfoFinder;

    /* JADX WARN: Multi-variable type inference failed */
    public DefaultEncoderProfilesProvider(CameraInfoInternal cameraInfo, List<? extends Quality> targetQualities, VideoEncoderInfo.Finder videoEncoderInfoFinder) {
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        Intrinsics.checkNotNullParameter(targetQualities, "targetQualities");
        Intrinsics.checkNotNullParameter(videoEncoderInfoFinder, "videoEncoderInfoFinder");
        this.cameraInfo = cameraInfo;
        this.targetQualities = targetQualities;
        this.videoEncoderInfoFinder = videoEncoderInfoFinder;
        this.supportedSizes = LazyKt.lazy(new Function0() { // from class: androidx.camera.video.internal.workaround.DefaultEncoderProfilesProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.cameraInfo.getSupportedResolutions(34);
            }
        });
        this.encoderProfilesMap = new LinkedHashMap();
    }

    private final List<Size> getSupportedSizes() {
        return (List) this.supportedSizes.getValue();
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProvider
    public boolean hasProfile(int quality) {
        return getProfileInternal(quality) != null;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProvider
    public EncoderProfilesProxy getAll(int quality) {
        return getProfileInternal(quality);
    }

    private final EncoderProfilesProxy getProfileInternal(int quality) {
        if (this.encoderProfilesMap.containsKey(Integer.valueOf(quality))) {
            return this.encoderProfilesMap.get(Integer.valueOf(quality));
        }
        EncoderProfilesProxy encoderProfilesProxyGenerateEncoderProfiles = generateEncoderProfiles(quality);
        this.encoderProfilesMap.put(Integer.valueOf(quality), encoderProfilesProxyGenerateEncoderProfiles);
        return encoderProfilesProxyGenerateEncoderProfiles;
    }

    private final EncoderProfilesProxy generateEncoderProfiles(int quality) {
        EncoderProfilesProxy.VideoProfileProxy videoProfileProxyGenerateVideoProfiles = generateVideoProfiles(quality);
        if (videoProfileProxyGenerateVideoProfiles == null) {
            return null;
        }
        return createDefaultEncoderProfiles$default(this, 0, 0, videoProfileProxyGenerateVideoProfiles, createDefaultAudioProfile$default(this, 0, null, 0, 0, 0, 0, 63, null), 3, null);
    }

    private final EncoderProfilesProxy.VideoProfileProxy generateVideoProfiles(int quality) {
        EncoderProfilesProxy.VideoProfileProxy videoProfileProxyResolveVideoProfile;
        Quality.ConstantQuality constantQualityFind = find(this.targetQualities, quality);
        if (constantQualityFind == null) {
            return null;
        }
        for (Size size : constantQualityFind.getTypicalSizes()) {
            if (getSupportedSizes().contains(size) && (videoProfileProxyResolveVideoProfile = resolveVideoProfile(size.getWidth(), size.getHeight(), getTypicalBitrate(constantQualityFind))) != null) {
                return videoProfileProxyResolveVideoProfile;
            }
        }
        return null;
    }

    private final EncoderProfilesProxy.VideoProfileProxy resolveVideoProfile(int width, int height, int bitrate) {
        EncoderProfilesProxy.VideoProfileProxy videoProfileProxyCreateDefaultVideoProfile$default = createDefaultVideoProfile$default(this, 0, null, width, height, bitrate, 0, 0, 0, 0, 0, 995, null);
        VideoEncoderInfo videoEncoderInfoFind = this.videoEncoderInfoFinder.find(videoProfileProxyCreateDefaultVideoProfile$default.getMediaType());
        if (videoEncoderInfoFind == null || !videoEncoderInfoFind.isSizeSupportedAllowSwapping(width, height)) {
            return null;
        }
        Integer num = (Integer) videoEncoderInfoFind.getSupportedBitrateRange().clamp(Integer.valueOf(bitrate));
        if (num != null && num.intValue() == bitrate) {
            return videoProfileProxyCreateDefaultVideoProfile$default;
        }
        Intrinsics.checkNotNull(num);
        return createDefaultVideoProfile$default(this, 0, null, width, height, num.intValue(), 0, 0, 0, 0, 0, 995, null);
    }

    static /* synthetic */ EncoderProfilesProxy createDefaultEncoderProfiles$default(DefaultEncoderProfilesProvider defaultEncoderProfilesProvider, int i, int i2, EncoderProfilesProxy.VideoProfileProxy videoProfileProxy, EncoderProfilesProxy.AudioProfileProxy audioProfileProxy, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 60;
        }
        if ((i3 & 2) != 0) {
            i2 = 2;
        }
        return defaultEncoderProfilesProvider.createDefaultEncoderProfiles(i, i2, videoProfileProxy, audioProfileProxy);
    }

    private final EncoderProfilesProxy createDefaultEncoderProfiles(int defaultDurationSeconds, int recommendedFileFormat, EncoderProfilesProxy.VideoProfileProxy videoProfile, EncoderProfilesProxy.AudioProfileProxy audioProfile) {
        EncoderProfilesProxy.ImmutableEncoderProfilesProxy immutableEncoderProfilesProxyCreate = EncoderProfilesProxy.ImmutableEncoderProfilesProxy.create(defaultDurationSeconds, recommendedFileFormat, CollectionsKt.listOf(audioProfile), CollectionsKt.listOf(videoProfile));
        Intrinsics.checkNotNullExpressionValue(immutableEncoderProfilesProxyCreate, "create(...)");
        return immutableEncoderProfilesProxyCreate;
    }

    static /* synthetic */ EncoderProfilesProxy.VideoProfileProxy createDefaultVideoProfile$default(DefaultEncoderProfilesProvider defaultEncoderProfilesProvider, int i, String str, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, Object obj) {
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        String str2;
        DefaultEncoderProfilesProvider defaultEncoderProfilesProvider2;
        if ((i10 & 1) != 0) {
            i = 2;
        }
        if ((i10 & 2) != 0) {
            str = DEFAULT_VIDEO_MIME_TYPE;
        }
        if ((i10 & 32) != 0) {
            i5 = 30;
        }
        if ((i10 & 64) != 0) {
            i6 = -1;
        }
        if ((i10 & 128) != 0) {
            i7 = 8;
        }
        if ((i10 & 256) != 0) {
            i8 = 0;
        }
        if ((i10 & 512) != 0) {
            i11 = 0;
            i14 = i7;
            i12 = i8;
            i16 = i5;
            i13 = i6;
            i18 = i3;
            i15 = i4;
            str2 = str;
            i17 = i2;
            defaultEncoderProfilesProvider2 = defaultEncoderProfilesProvider;
            i19 = i;
        } else {
            i11 = i9;
            i12 = i8;
            i13 = i6;
            i14 = i7;
            i15 = i4;
            i16 = i5;
            i17 = i2;
            i18 = i3;
            i19 = i;
            str2 = str;
            defaultEncoderProfilesProvider2 = defaultEncoderProfilesProvider;
        }
        return defaultEncoderProfilesProvider2.createDefaultVideoProfile(i19, str2, i17, i18, i15, i16, i13, i14, i12, i11);
    }

    private final EncoderProfilesProxy.VideoProfileProxy createDefaultVideoProfile(int codec, String mimeType, int width, int height, int bitrate, int frameRate, int profile, int bitDepth, int chromaSubsampling, int hdrFormat) {
        EncoderProfilesProxy.VideoProfileProxy videoProfileProxyCreate = EncoderProfilesProxy.VideoProfileProxy.create(codec, mimeType, bitrate, frameRate, width, height, profile, bitDepth, chromaSubsampling, hdrFormat);
        Intrinsics.checkNotNullExpressionValue(videoProfileProxyCreate, "create(...)");
        return videoProfileProxyCreate;
    }

    static /* synthetic */ EncoderProfilesProxy.AudioProfileProxy createDefaultAudioProfile$default(DefaultEncoderProfilesProvider defaultEncoderProfilesProvider, int i, String str, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i = 3;
        }
        if ((i6 & 2) != 0) {
            str = DEFAULT_AUDIO_MIME_TYPE;
        }
        if ((i6 & 4) != 0) {
            i2 = DEFAULT_AUDIO_BITRATE;
        }
        if ((i6 & 8) != 0) {
            i3 = DEFAULT_AUDIO_SAMPLE_RATE;
        }
        if ((i6 & 16) != 0) {
            i4 = 1;
        }
        if ((i6 & 32) != 0) {
            i5 = 2;
        }
        int i7 = i4;
        int i8 = i5;
        return defaultEncoderProfilesProvider.createDefaultAudioProfile(i, str, i2, i3, i7, i8);
    }

    private final EncoderProfilesProxy.AudioProfileProxy createDefaultAudioProfile(int codec, String mimeType, int bitRate, int sampleRate, int channels, int profile) {
        EncoderProfilesProxy.AudioProfileProxy audioProfileProxyCreate = EncoderProfilesProxy.AudioProfileProxy.create(codec, mimeType, bitRate, sampleRate, channels, profile);
        Intrinsics.checkNotNullExpressionValue(audioProfileProxyCreate, "create(...)");
        return audioProfileProxyCreate;
    }

    private final int getTypicalBitrate(Quality quality) {
        if (Intrinsics.areEqual(quality, Quality.UHD)) {
            return DEFAULT_VIDEO_BITRATE_UHD;
        }
        if (Intrinsics.areEqual(quality, Quality.FHD)) {
            return DEFAULT_VIDEO_BITRATE_FHD;
        }
        if (Intrinsics.areEqual(quality, Quality.HD)) {
            return DEFAULT_VIDEO_BITRATE_HD;
        }
        if (Intrinsics.areEqual(quality, Quality.SD)) {
            return DEFAULT_VIDEO_BITRATE_SD;
        }
        throw new IllegalArgumentException("Undefined bitrate for quality: " + quality);
    }

    private final Quality.ConstantQuality find(List<? extends Quality> list, int i) {
        Object next;
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Quality quality = (Quality) next;
            Intrinsics.checkNotNull(quality, "null cannot be cast to non-null type androidx.camera.video.Quality.ConstantQuality");
            if (((Quality.ConstantQuality) quality).getQualityValue(1) == i) {
                break;
            }
        }
        if (next instanceof Quality.ConstantQuality) {
            return (Quality.ConstantQuality) next;
        }
        return null;
    }
}
