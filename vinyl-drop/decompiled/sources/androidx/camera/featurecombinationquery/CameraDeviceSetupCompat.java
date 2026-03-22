package androidx.camera.featurecombinationquery;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.params.SessionConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public interface CameraDeviceSetupCompat {
    SupportQueryResult isSessionConfigurationSupported(SessionConfiguration sessionConfiguration) throws CameraAccessException;

    SupportQueryResult isSessionConfigurationSupportedLegacy(SessionConfigurationLegacy sessionConfigurationLegacy);

    public static final class SupportQueryResult {
        public static final int RESULT_SUPPORTED = 1;
        public static final int RESULT_UNDEFINED = 0;
        public static final int RESULT_UNSUPPORTED = 2;
        public static final int SOURCE_ANDROID_FRAMEWORK = 2;
        public static final int SOURCE_PLAY_SERVICES = 1;
        public static final int SOURCE_UNDEFINED = 0;
        private final int mSource;
        private final int mSupported;
        private final long mTimestampMillis;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Supported {
        }

        public SupportQueryResult(int i, int i2, long j) {
            this.mSupported = i;
            this.mSource = i2;
            this.mTimestampMillis = j;
        }

        public int getSupported() {
            return this.mSupported;
        }

        public int getSource() {
            return this.mSource;
        }

        public long getTimestampMillis() {
            return this.mTimestampMillis;
        }
    }
}
