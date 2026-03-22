package com.google.mlkit.vision.barcode.bundled.internal;

import android.content.Context;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzba;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbn;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbp;

/* JADX INFO: compiled from: com.google.mlkit:barcode-scanning@@17.3.0 */
/* JADX INFO: loaded from: classes3.dex */
public class ThickBarcodeScannerCreator extends zzbp {
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbq
    public zzbn newBarcodeScanner(IObjectWrapper iObjectWrapper, zzba zzbaVar) {
        return new zza((Context) ObjectWrapper.unwrap(iObjectWrapper), zzbaVar);
    }
}
