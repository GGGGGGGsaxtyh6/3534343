package androidx.camera.video.internal.config;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: CaptureEncodeRates.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Landroidx/camera/video/internal/config/CaptureEncodeRates;", "", "captureRate", "", "encodeRate", "<init>", "(II)V", "getCaptureRate", "()I", "getEncodeRate", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "camera-video_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class CaptureEncodeRates {
    private final int captureRate;
    private final int encodeRate;

    public static /* synthetic */ CaptureEncodeRates copy$default(CaptureEncodeRates captureEncodeRates, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = captureEncodeRates.captureRate;
        }
        if ((i3 & 2) != 0) {
            i2 = captureEncodeRates.encodeRate;
        }
        return captureEncodeRates.copy(i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCaptureRate() {
        return this.captureRate;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getEncodeRate() {
        return this.encodeRate;
    }

    public final CaptureEncodeRates copy(int captureRate, int encodeRate) {
        return new CaptureEncodeRates(captureRate, encodeRate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptureEncodeRates)) {
            return false;
        }
        CaptureEncodeRates captureEncodeRates = (CaptureEncodeRates) other;
        return this.captureRate == captureEncodeRates.captureRate && this.encodeRate == captureEncodeRates.encodeRate;
    }

    public int hashCode() {
        return (Integer.hashCode(this.captureRate) * 31) + Integer.hashCode(this.encodeRate);
    }

    public String toString() {
        return "CaptureEncodeRates(captureRate=" + this.captureRate + ", encodeRate=" + this.encodeRate + ')';
    }

    public CaptureEncodeRates(int i, int i2) {
        this.captureRate = i;
        this.encodeRate = i2;
    }

    public final int getCaptureRate() {
        return this.captureRate;
    }

    public final int getEncodeRate() {
        return this.encodeRate;
    }
}
