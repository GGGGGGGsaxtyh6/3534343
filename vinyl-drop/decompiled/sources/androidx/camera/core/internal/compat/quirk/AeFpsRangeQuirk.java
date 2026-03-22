package androidx.camera.core.internal.compat.quirk;

import android.util.Range;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.StreamSpec;

/* JADX INFO: loaded from: classes.dex */
public interface AeFpsRangeQuirk extends Quirk {
    default Range<Integer> getTargetAeFpsRange() {
        return StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
    }
}
