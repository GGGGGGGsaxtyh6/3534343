package androidx.camera.camera2.internal;

import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.AdapterCameraInfo;

/* JADX INFO: loaded from: classes.dex */
class ZoomStateImpl implements ZoomState {
    private float mLinearZoom;
    private final float mMaxZoomRatio;
    private final float mMinZoomRatio;
    private float mZoomRatio;

    ZoomStateImpl(float f, float f2) {
        this.mMaxZoomRatio = f;
        this.mMinZoomRatio = f2;
    }

    void setZoomRatio(float f) throws IllegalArgumentException {
        float f2 = this.mMaxZoomRatio;
        if (f <= f2) {
            float f3 = this.mMinZoomRatio;
            if (f >= f3) {
                this.mZoomRatio = f;
                this.mLinearZoom = AdapterCameraInfo.getPercentageByRatio(f, f3, f2);
                return;
            }
        }
        throw new IllegalArgumentException("Requested zoomRatio " + f + " is not within valid range [" + this.mMinZoomRatio + " , " + this.mMaxZoomRatio + "]");
    }

    void setLinearZoom(float f) throws IllegalArgumentException {
        if (f > 1.0f || f < 0.0f) {
            throw new IllegalArgumentException("Requested linearZoom " + f + " is not within valid range [0..1]");
        }
        this.mLinearZoom = f;
        this.mZoomRatio = AdapterCameraInfo.getZoomRatioByPercentage(f, this.mMinZoomRatio, this.mMaxZoomRatio);
    }

    @Override // androidx.camera.core.ZoomState
    public float getZoomRatio() {
        return this.mZoomRatio;
    }

    @Override // androidx.camera.core.ZoomState
    public float getMaxZoomRatio() {
        return this.mMaxZoomRatio;
    }

    @Override // androidx.camera.core.ZoomState
    public float getMinZoomRatio() {
        return this.mMinZoomRatio;
    }

    @Override // androidx.camera.core.ZoomState
    public float getLinearZoom() {
        return this.mLinearZoom;
    }
}
