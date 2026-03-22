package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* JADX INFO: compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzwz extends LazyInstanceMap {
    private zzwz() {
        throw null;
    }

    /* synthetic */ zzwz(zzwy zzwyVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzwh zzwhVar = (zzwh) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzwp(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzwi(MlKitContext.getInstance().getApplicationContext(), zzwhVar), zzwhVar.zzb());
    }
}
