package androidx.camera.core.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.Quirk;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PreviewGreenTintQuirk.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\u0005H\u0007J\u001e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007J\u001e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u000f"}, d2 = {"Landroidx/camera/core/internal/compat/quirk/PreviewGreenTintQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "isMotoE20", "", "()Z", "load", "shouldForceEnableStreamSharing", "cameraId", "", "appUseCases", "", "Landroidx/camera/core/UseCase;", "shouldForceEnableStreamSharingForMotoE20", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PreviewGreenTintQuirk implements Quirk {
    public static final PreviewGreenTintQuirk INSTANCE = new PreviewGreenTintQuirk();

    private PreviewGreenTintQuirk() {
    }

    private final boolean isMotoE20() {
        return StringsKt.equals("motorola", Build.BRAND, true) && StringsKt.equals("moto e20", Build.MODEL, true);
    }

    @JvmStatic
    public static final boolean load() {
        return INSTANCE.isMotoE20();
    }

    @JvmStatic
    public static final boolean shouldForceEnableStreamSharing(String cameraId, Collection<? extends UseCase> appUseCases) {
        Intrinsics.checkNotNullParameter(cameraId, "cameraId");
        Intrinsics.checkNotNullParameter(appUseCases, "appUseCases");
        PreviewGreenTintQuirk previewGreenTintQuirk = INSTANCE;
        if (previewGreenTintQuirk.isMotoE20()) {
            return previewGreenTintQuirk.shouldForceEnableStreamSharingForMotoE20(cameraId, appUseCases);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0070 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean shouldForceEnableStreamSharingForMotoE20(java.lang.String r6, java.util.Collection<? extends androidx.camera.core.UseCase> r7) {
        /*
            r5 = this;
            java.lang.String r0 = "0"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r0)
            r0 = 0
            if (r6 == 0) goto L73
            int r6 = r7.size()
            r1 = 2
            if (r6 == r1) goto L11
            goto L73
        L11:
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            boolean r6 = r7 instanceof java.util.Collection
            r1 = 1
            if (r6 == 0) goto L23
            r2 = r7
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L23
        L21:
            r2 = r0
            goto L38
        L23:
            java.util.Iterator r2 = r7.iterator()
        L27:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L21
            java.lang.Object r3 = r2.next()
            androidx.camera.core.UseCase r3 = (androidx.camera.core.UseCase) r3
            boolean r3 = r3 instanceof androidx.camera.core.Preview
            if (r3 == 0) goto L27
            r2 = r1
        L38:
            if (r6 == 0) goto L45
            r6 = r7
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L45
        L43:
            r6 = r0
            goto L6e
        L45:
            java.util.Iterator r6 = r7.iterator()
        L49:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L43
            java.lang.Object r7 = r6.next()
            androidx.camera.core.UseCase r7 = (androidx.camera.core.UseCase) r7
            androidx.camera.core.impl.UseCaseConfig r3 = r7.getCurrentConfig()
            androidx.camera.core.impl.Config$Option<androidx.camera.core.impl.UseCaseConfigFactory$CaptureType> r4 = androidx.camera.core.impl.UseCaseConfig.OPTION_CAPTURE_TYPE
            boolean r3 = r3.containsOption(r4)
            if (r3 == 0) goto L49
            androidx.camera.core.impl.UseCaseConfig r7 = r7.getCurrentConfig()
            androidx.camera.core.impl.UseCaseConfigFactory$CaptureType r7 = r7.getCaptureType()
            androidx.camera.core.impl.UseCaseConfigFactory$CaptureType r3 = androidx.camera.core.impl.UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE
            if (r7 != r3) goto L49
            r6 = r1
        L6e:
            if (r2 == 0) goto L73
            if (r6 == 0) goto L73
            return r1
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.compat.quirk.PreviewGreenTintQuirk.shouldForceEnableStreamSharingForMotoE20(java.lang.String, java.util.Collection):boolean");
    }
}
