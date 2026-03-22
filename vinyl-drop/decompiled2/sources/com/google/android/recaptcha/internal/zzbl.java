package com.google.android.recaptcha.internal;

import androidx.core.view.PointerIconCompat;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbl {
    public static final zzbl zza = new zzbl(9999);
    public static final zzbl zzb = new zzbl(PointerIconCompat.TYPE_WAIT);
    public static final zzbl zzc = new zzbl(WebSocketProtocol.CLOSE_NO_STATUS_CODE);
    public static final zzbl zzd = new zzbl(PointerIconCompat.TYPE_CELL);
    public static final zzbl zze = new zzbl(PointerIconCompat.TYPE_CROSSHAIR);
    public static final zzbl zzf = new zzbl(PointerIconCompat.TYPE_TEXT);
    public static final zzbl zzg = new zzbl(PointerIconCompat.TYPE_VERTICAL_TEXT);
    public static final zzbl zzh = new zzbl(PointerIconCompat.TYPE_ALIAS);
    private final int zzi;

    private zzbl(int i) {
        this.zzi = i;
    }

    public final int zza() {
        return this.zzi;
    }
}
