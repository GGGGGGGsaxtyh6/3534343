package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zabx extends zap {
    private TaskCompletionSource zad;

    private zabx(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment, GoogleApiAvailability.getInstance());
        this.zad = new TaskCompletionSource();
        this.mLifecycleFragment.addCallback("GmsAvailabilityHelper", this);
    }

    public static zabx zaa(Activity activity) {
        LifecycleFragment fragment = getFragment(activity);
        zabx zabxVar = (zabx) fragment.getCallbackOrNull("GmsAvailabilityHelper", zabx.class);
        if (zabxVar == null) {
            return new zabx(fragment);
        }
        if (zabxVar.zad.getTask().isComplete()) {
            zabxVar.zad = new TaskCompletionSource();
        }
        return zabxVar;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onDestroy() {
        super.onDestroy();
        this.zad.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    public final Task zab() {
        return this.zad.getTask();
    }

    @Override // com.google.android.gms.common.api.internal.zap
    protected final void zad(ConnectionResult connectionResult, int i) {
        String errorMessage = connectionResult.getErrorMessage();
        if (errorMessage == null) {
            errorMessage = "Error connecting to Google Play services";
        }
        this.zad.setException(new ApiException(new Status(connectionResult, errorMessage, connectionResult.getErrorCode())));
    }

    @Override // com.google.android.gms.common.api.internal.zap
    protected final void zae() {
        Activity lifecycleActivity = this.mLifecycleFragment.getLifecycleActivity();
        if (lifecycleActivity == null) {
            this.zad.trySetException(new ApiException(new Status(8)));
            return;
        }
        int iIsGooglePlayServicesAvailable = this.zac.isGooglePlayServicesAvailable(lifecycleActivity);
        TaskCompletionSource taskCompletionSource = this.zad;
        if (iIsGooglePlayServicesAvailable == 0) {
            taskCompletionSource.trySetResult(null);
        } else {
            if (taskCompletionSource.getTask().isComplete()) {
                return;
            }
            zaf(new ConnectionResult(iIsGooglePlayServicesAvailable, null), 0);
        }
    }
}
