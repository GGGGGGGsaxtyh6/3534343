package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zziz extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzja zzc;
    final /* synthetic */ zzen zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zziz(zzja zzjaVar, zzen zzenVar, Continuation continuation) {
        super(2, continuation);
        this.zzc = zzjaVar;
        this.zzd = zzenVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zziz(this.zzc, this.zzd, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zziz) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
    
        if (kotlinx.coroutines.TimeoutKt.withTimeout(20000, new com.google.android.recaptcha.internal.zziy(r5.zzc, r5.zzd, null), r5) == r0) goto L20;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) throws Throwable {
        zzbd zzbdVar;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zzb;
        try {
        } catch (Exception e) {
            e.getMessage();
            zzbd zzbdVarZza = zzf.zza(e, new zzbd(zzbb.zzb, zzba.zzV, e.getMessage()));
            this.zza = zzbdVarZza;
            this.zzb = 3;
            if (this.zzc.zzm().zzc(zzje.zza, this) != coroutine_suspended) {
                zzbdVar = zzbdVarZza;
            }
            return coroutine_suspended;
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.zzb = 1;
            if (this.zzc.zzx(this) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i == 2) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            zzbdVar = (zzbd) this.zza;
            ResultKt.throwOnFailure(obj);
            this.zzd.zzb(zzbdVar);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        this.zzb = 2;
    }
}
