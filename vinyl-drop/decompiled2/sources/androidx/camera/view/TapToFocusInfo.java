package androidx.camera.view;

import android.graphics.PointF;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: TapToFocusInfo.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/camera/view/TapToFocusInfo;", "", "focusState", "", "tapPoint", "Landroid/graphics/PointF;", "<init>", "(ILandroid/graphics/PointF;)V", "getFocusState", "()I", "getTapPoint", "()Landroid/graphics/PointF;", "camera-view_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class TapToFocusInfo {
    private final int focusState;
    private final PointF tapPoint;

    public TapToFocusInfo(int i, PointF pointF) {
        this.focusState = i;
        this.tapPoint = pointF;
    }

    public final int getFocusState() {
        return this.focusState;
    }

    public final PointF getTapPoint() {
        return this.tapPoint;
    }
}
