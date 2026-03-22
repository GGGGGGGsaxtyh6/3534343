package androidx.camera.featurecombinationquery;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.params.SessionConfiguration;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompat;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class AggregatedCameraDeviceSetupCompat implements CameraDeviceSetupCompat {
    private final List<CameraDeviceSetupCompat> mCameraDeviceSetupImpls;

    AggregatedCameraDeviceSetupCompat(List<CameraDeviceSetupCompat> list) {
        this.mCameraDeviceSetupImpls = list;
    }

    @Override // androidx.camera.featurecombinationquery.CameraDeviceSetupCompat
    public CameraDeviceSetupCompat.SupportQueryResult isSessionConfigurationSupported(SessionConfiguration sessionConfiguration) throws CameraAccessException {
        Iterator<CameraDeviceSetupCompat> it = this.mCameraDeviceSetupImpls.iterator();
        while (it.hasNext()) {
            CameraDeviceSetupCompat.SupportQueryResult supportQueryResultIsSessionConfigurationSupported = it.next().isSessionConfigurationSupported(sessionConfiguration);
            if (supportQueryResultIsSessionConfigurationSupported.getSupported() != 0) {
                return supportQueryResultIsSessionConfigurationSupported;
            }
        }
        return new CameraDeviceSetupCompat.SupportQueryResult(0, 0, 0L);
    }

    @Override // androidx.camera.featurecombinationquery.CameraDeviceSetupCompat
    public CameraDeviceSetupCompat.SupportQueryResult isSessionConfigurationSupportedLegacy(SessionConfigurationLegacy sessionConfigurationLegacy) {
        Iterator<CameraDeviceSetupCompat> it = this.mCameraDeviceSetupImpls.iterator();
        while (it.hasNext()) {
            if (it.next() instanceof Camera2CameraDeviceSetupCompat) {
                throw new IllegalStateException("This device supports CameraDeviceSetup. Please use Camera2 SessionConfiguration for querying instead.");
            }
        }
        Iterator<CameraDeviceSetupCompat> it2 = this.mCameraDeviceSetupImpls.iterator();
        while (it2.hasNext()) {
            CameraDeviceSetupCompat.SupportQueryResult supportQueryResultIsSessionConfigurationSupportedLegacy = it2.next().isSessionConfigurationSupportedLegacy(sessionConfigurationLegacy);
            if (supportQueryResultIsSessionConfigurationSupportedLegacy.getSupported() != 0) {
                return supportQueryResultIsSessionConfigurationSupportedLegacy;
            }
        }
        return new CameraDeviceSetupCompat.SupportQueryResult(0, 0, 0L);
    }
}
