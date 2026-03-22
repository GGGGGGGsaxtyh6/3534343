package androidx.camera.camera2.internal.compat.quirk;

import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.Quirks;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public interface CaptureIntentPreviewQuirk extends Quirk {
    default boolean workaroundByCaptureIntentPreview() {
        return true;
    }

    static boolean workaroundByCaptureIntentPreview(Quirks quirks) {
        Iterator it = quirks.getAll(CaptureIntentPreviewQuirk.class).iterator();
        while (it.hasNext()) {
            if (((CaptureIntentPreviewQuirk) it.next()).workaroundByCaptureIntentPreview()) {
                return true;
            }
        }
        return false;
    }
}
