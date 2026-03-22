package com.google.firebase.appcheck;

import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: classes3.dex */
public interface AppCheckProvider {
    Task<AppCheckToken> getToken();
}
