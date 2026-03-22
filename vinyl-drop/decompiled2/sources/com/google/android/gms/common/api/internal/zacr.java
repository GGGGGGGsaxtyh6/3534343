package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zacr extends com.google.android.gms.internal.base.zao {
    final /* synthetic */ zacs zaa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zacr(zacs zacsVar, Looper looper) {
        super(looper);
        Objects.requireNonNull(zacsVar);
        this.zaa = zacsVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            PendingResult pendingResult = (PendingResult) message.obj;
            zacs zacsVar = this.zaa;
            synchronized (zacsVar.zaf()) {
                zacs zacsVar2 = (zacs) Preconditions.checkNotNull(zacsVar.zae());
                if (pendingResult == null) {
                    zacsVar2.zac(new Status(13, "Transform returned null"));
                } else if (pendingResult instanceof zaci) {
                    zacsVar2.zac(((zaci) pendingResult).zaa());
                } else {
                    zacsVar2.zaa(pendingResult);
                }
            }
            return;
        }
        if (i == 1) {
            RuntimeException runtimeException = (RuntimeException) message.obj;
            String message2 = runtimeException.getMessage();
            String.valueOf(message2);
            Log.e("TransformedResultImpl", "Runtime exception on the transformation worker thread: ".concat(String.valueOf(message2)));
            throw runtimeException;
        }
        int i2 = message.what;
        StringBuilder sb = new StringBuilder(String.valueOf(i2).length() + 59);
        sb.append("TransformationResultHandler received unknown message type: ");
        sb.append(i2);
        Log.e("TransformedResultImpl", sb.toString());
    }
}
