package com.google.android.recaptcha.internal;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfj {
    private final Lazy zza;

    public zzfj() {
        int i = zzav.zza;
        this.zza = LazyKt.lazy(zzfi.zza);
    }

    public static final /* synthetic */ zzex zza(zzfj zzfjVar) {
        return (zzex) zzfjVar.zza.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ Object zzc(zzfj zzfjVar, zzbr zzbrVar, zzsp zzspVar, Continuation continuation) throws Throwable {
        zzfg zzfgVar;
        if (continuation instanceof zzfg) {
            zzfgVar = (zzfg) continuation;
            int i = zzfgVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzfgVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzfgVar = new zzfg(zzfjVar, continuation);
            }
        }
        Object obj = zzfgVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzfgVar.zzc;
        if (i2 != 0) {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        zzfh zzfhVar = new zzfh(zzfjVar, zzbrVar, zzspVar, null);
        zzfgVar.zzc = 1;
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(zzfhVar, zzfgVar);
        return objCoroutineScope == coroutine_suspended ? coroutine_suspended : objCoroutineScope;
    }

    public final Object zzb(zzbr zzbrVar, zzsp zzspVar, Continuation continuation) {
        return zzc(this, zzbrVar, zzspVar, continuation);
    }
}
