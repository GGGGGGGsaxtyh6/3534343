package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;
import java.util.Objects;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zaa implements PendingResult.StatusListener {
    final /* synthetic */ Batch zaa;

    zaa(Batch batch) {
        Objects.requireNonNull(batch);
        this.zaa = batch;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        Batch batch = this.zaa;
        synchronized (batch.zai()) {
            if (batch.isCanceled()) {
                return;
            }
            if (status.isCanceled()) {
                batch.zag(true);
            } else if (!status.isSuccess()) {
                batch.zae(true);
            }
            batch.zac(batch.zab() - 1);
            if (batch.zab() == 0) {
                if (batch.zaf()) {
                    super/*com.google.android.gms.common.api.internal.BasePendingResult*/.cancel();
                } else {
                    batch.setResult(new BatchResult(batch.zad() ? new Status(13) : Status.RESULT_SUCCESS, batch.zah()));
                }
            }
        }
    }
}
