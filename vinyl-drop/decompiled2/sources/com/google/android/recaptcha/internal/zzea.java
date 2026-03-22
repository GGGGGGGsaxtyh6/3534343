package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletableDeferred;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzea extends SuspendLambda implements Function1 {
    Object zza;
    int zzb;
    final /* synthetic */ zzec zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ CompletableDeferred zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzea(zzec zzecVar, long j, CompletableDeferred completableDeferred, Continuation continuation) {
        super(1, continuation);
        this.zzc = zzecVar;
        this.zzd = j;
        this.zze = completableDeferred;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation continuation) {
        return new zzea(this.zzc, this.zzd, this.zze, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return ((zzea) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        zzen zzenVarZzf;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zzb;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                zzenVarZzf = this.zzc.zzb.zzf(41);
                this.zza = zzenVarZzf;
                this.zzb = 1;
                obj = this.zzc.zza.zzo(this.zzd, this);
                if (obj != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            zzenVarZzf = (zzen) this.zza;
            if (i != 1) {
                ResultKt.throwOnFailure(obj);
                zzenVarZzf.zza();
                this.zzc.zzf = zzcm.zzb;
                return Boxing.boxBoolean(this.zze.complete(Unit.INSTANCE));
            }
            ResultKt.throwOnFailure(obj);
            zzsc zzscVar = (zzsc) obj;
            this.zzc.zze = zzscVar;
            this.zza = zzenVarZzf;
            this.zzb = 2;
            if (this.zzc.zza.zzn(zzscVar, this.zzd, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            zzenVarZzf.zza();
            this.zzc.zzf = zzcm.zzb;
            return Boxing.boxBoolean(this.zze.complete(Unit.INSTANCE));
        } catch (zzbd e) {
            this.zzc.zzd = e;
            zzenVarZzf.zzb(e);
            throw e;
        }
    }
}
