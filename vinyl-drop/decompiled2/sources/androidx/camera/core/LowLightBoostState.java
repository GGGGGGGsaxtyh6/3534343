package androidx.camera.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class LowLightBoostState {
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;
    public static final int OFF = -1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    private LowLightBoostState() {
    }
}
