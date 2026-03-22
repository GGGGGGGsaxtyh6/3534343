package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzec implements zzcn {
    private final zzdt zza;
    private final zzek zzb;
    private zzbd zzd;
    private zzsc zze;
    private final zzbi zzg;
    private CompletableDeferred zzc = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
    private zzcm zzf = zzcm.zza;

    public zzec(zzdt zzdtVar, zzbi zzbiVar, zzek zzekVar, zzbo zzboVar) {
        this.zza = zzdtVar;
        this.zzg = zzbiVar;
        this.zzb = zzekVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzl(Function1 function1, Continuation continuation) throws Throwable {
        zzdv zzdvVar;
        zzbn zzbnVar;
        if (continuation instanceof zzdv) {
            zzdvVar = (zzdv) continuation;
            int i = zzdvVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzdvVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzdvVar = new zzdv(this, continuation);
            }
        }
        Object obj = zzdvVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzdvVar.zzc;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            zzbn zzbnVar2 = new zzbn();
            zzdvVar.zzd = zzbnVar2;
            zzdvVar.zzc = 1;
            if (function1.invoke(zzdvVar) == coroutine_suspended) {
                return coroutine_suspended;
            }
            zzbnVar = zzbnVar2;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            zzbnVar = zzdvVar.zzd;
            ResultKt.throwOnFailure(obj);
        }
        zzbnVar.zzc();
        return Boxing.boxLong(zzbnVar.zza(TimeUnit.MILLISECONDS));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007f A[Catch: Exception -> 0x0034, TRY_ENTER, TryCatch #1 {Exception -> 0x0034, blocks: (B:13:0x0030, B:26:0x006d, B:31:0x007f, B:32:0x0088), top: B:54:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzm(long j, Continuation continuation) throws Throwable {
        zzdw zzdwVar;
        zzec zzecVar;
        zzec zzecVar2;
        zzbd zzbdVar;
        zzbd zzbdVar2;
        long jLongValue;
        if (continuation instanceof zzdw) {
            zzdwVar = (zzdw) continuation;
            int i = zzdwVar.zzd;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzdwVar.zzd = i - Integer.MIN_VALUE;
            } else {
                zzdwVar = new zzdw(this, continuation);
            }
        }
        Object objZzl = zzdwVar.zzb;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzdwVar.zzd;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(objZzl);
                zzdwVar.zze = this;
                zzdwVar.zza = j;
                zzdwVar.zzd = 1;
                if (zzn(j, zzdwVar) != coroutine_suspended) {
                    zzecVar = this;
                }
                return coroutine_suspended;
            }
            if (i2 == 1) {
                j = zzdwVar.zza;
                zzecVar = zzdwVar.zze;
                ResultKt.throwOnFailure(objZzl);
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                j = zzdwVar.zza;
                zzecVar2 = zzdwVar.zze;
                try {
                    ResultKt.throwOnFailure(objZzl);
                    jLongValue = j - ((Number) objZzl).longValue();
                    if (jLongValue < 500) {
                        return Boxing.boxLong(jLongValue);
                    }
                    throw new zzbd(zzbb.zzc, zzba.zzar, null);
                } catch (Exception e) {
                    e = e;
                    if (e instanceof zzbd) {
                    }
                    if (zzbdVar == null) {
                    }
                    if (Intrinsics.areEqual(zzecVar2.zzf, zzcm.zzd)) {
                    }
                    zzbdVar2 = zzecVar2.zzd;
                    if (zzbdVar2 == null) {
                    }
                }
            }
            zzdy zzdyVar = new zzdy(j, zzecVar, null);
            zzdwVar.zze = zzecVar;
            zzdwVar.zza = j;
            zzdwVar.zzd = 2;
            objZzl = zzecVar.zzl(zzdyVar, zzdwVar);
            if (objZzl != coroutine_suspended) {
                zzecVar2 = zzecVar;
                jLongValue = j - ((Number) objZzl).longValue();
                if (jLongValue < 500) {
                }
            }
            return coroutine_suspended;
        } catch (Exception e2) {
            e = e2;
            zzecVar2 = zzecVar;
            zzbdVar = e instanceof zzbd ? (zzbd) e : null;
            if (zzbdVar == null) {
                zzbdVar = new zzbd(zzbb.zzc, zzba.zzar, e.getMessage());
            }
            if (Intrinsics.areEqual(zzecVar2.zzf, zzcm.zzd) && !Intrinsics.areEqual(zzecVar2.zzf, zzcm.zzc)) {
                throw zzbdVar;
            }
            zzbdVar2 = zzecVar2.zzd;
            if (zzbdVar2 == null) {
                throw zzbdVar2;
            }
            throw zzbdVar;
        }
    }

    private final Object zzn(long j, Continuation continuation) {
        if (Intrinsics.areEqual(this.zzf, zzcm.zzb) || Intrinsics.areEqual(this.zzf, zzcm.zzc)) {
            return Unit.INSTANCE;
        }
        if (Intrinsics.areEqual(this.zzf, zzcm.zzd) && !zzo(this.zzd)) {
            return Unit.INSTANCE;
        }
        this.zzf = zzcm.zzc;
        CompletableDeferred completableDeferredCompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.zzc = completableDeferredCompletableDeferred$default;
        BuildersKt__Builders_commonKt.launch$default(this.zzg.zza(), null, null, new zzeb(this, completableDeferredCompletableDeferred$default, j, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean zzo(Exception exc) {
        if (!(exc instanceof zzbd)) {
            return true;
        }
        zzbd zzbdVar = (zzbd) exc;
        return (Intrinsics.areEqual(zzbdVar.zzb(), zzbb.zzd) || Intrinsics.areEqual(zzbdVar.zzb(), zzbb.zze) || Intrinsics.areEqual(zzbdVar.zzb(), zzbb.zzf)) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // com.google.android.recaptcha.internal.zzcn
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zza(String str, RecaptchaAction recaptchaAction, long j, Continuation continuation) throws Throwable {
        zzdu zzduVar;
        String str2;
        RecaptchaAction recaptchaAction2;
        Object objZzm;
        zzec zzecVar;
        String str3;
        double d;
        zzec zzecVar2;
        zzsc zzscVar;
        String str4;
        zzec zzecVar3;
        if (continuation instanceof zzdu) {
            zzduVar = (zzdu) continuation;
            int i = zzduVar.zzd;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzduVar.zzd = i - Integer.MIN_VALUE;
            } else {
                zzduVar = new zzdu(this, continuation);
            }
        }
        zzdu zzduVar2 = zzduVar;
        Object objZzm2 = zzduVar2.zzb;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzduVar2.zzd;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(objZzm2);
                zzduVar2.zze = this;
                str2 = str;
                zzduVar2.zzf = str2;
                recaptchaAction2 = recaptchaAction;
                zzduVar2.zzg = recaptchaAction2;
                zzduVar2.zzd = 1;
                objZzm = zzm(j, zzduVar2);
                if (objZzm == coroutine_suspended) {
                    return coroutine_suspended;
                }
                zzecVar = this;
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        str4 = zzduVar2.zzf;
                        zzecVar3 = zzduVar2.zze;
                        ResultKt.throwOnFailure(objZzm2);
                        zzsr zzsrVar = (zzsr) objZzm2;
                        zzecVar3.zza.zzq(str4, zzsrVar);
                        return zzsrVar.zzj();
                    }
                    d = zzduVar2.zza;
                    recaptchaAction2 = zzduVar2.zzg;
                    str3 = zzduVar2.zzf;
                    zzecVar2 = zzduVar2.zze;
                    ResultKt.throwOnFailure(objZzm2);
                    zzsi zzsiVar = (zzsi) objZzm2;
                    zzdt zzdtVar = zzecVar2.zza;
                    zzscVar = zzecVar2.zze;
                    if (zzscVar == null) {
                        zzscVar = null;
                    }
                    zzsp zzspVarZzi = zzdtVar.zzi(recaptchaAction2, zzsiVar, zzscVar);
                    zzdt zzdtVar2 = zzecVar2.zza;
                    long j2 = (long) d;
                    zzduVar2.zze = zzecVar2;
                    zzduVar2.zzf = str3;
                    zzduVar2.zzg = null;
                    zzduVar2.zzd = 3;
                    String str5 = str3;
                    objZzm2 = zzdtVar2.zzm(zzspVarZzi, str5, j2, zzduVar2);
                    if (objZzm2 != coroutine_suspended) {
                        str4 = str5;
                        zzecVar3 = zzecVar2;
                        zzsr zzsrVar2 = (zzsr) objZzm2;
                        zzecVar3.zza.zzq(str4, zzsrVar2);
                        return zzsrVar2.zzj();
                    }
                    return coroutine_suspended;
                }
                recaptchaAction2 = zzduVar2.zzg;
                String str6 = zzduVar2.zzf;
                zzecVar = zzduVar2.zze;
                ResultKt.throwOnFailure(objZzm2);
                objZzm = objZzm2;
                str2 = str6;
            }
            double dLongValue = ((Number) objZzm).longValue();
            zzdt zzdtVar3 = zzecVar.zza;
            double d2 = 0.45d * dLongValue;
            zzduVar2.zze = zzecVar;
            zzduVar2.zzf = str2;
            zzduVar2.zzg = recaptchaAction2;
            double d3 = dLongValue * 0.55d;
            zzduVar2.zza = d3;
            zzduVar2.zzd = 2;
            Object objZzl = zzdtVar3.zzl(str2, (long) d2, zzduVar2);
            if (objZzl != coroutine_suspended) {
                zzec zzecVar4 = zzecVar;
                str3 = str2;
                objZzm2 = objZzl;
                d = d3;
                zzecVar2 = zzecVar4;
                zzsi zzsiVar2 = (zzsi) objZzm2;
                zzdt zzdtVar4 = zzecVar2.zza;
                zzscVar = zzecVar2.zze;
                if (zzscVar == null) {
                }
                zzsp zzspVarZzi2 = zzdtVar4.zzi(recaptchaAction2, zzsiVar2, zzscVar);
                zzdt zzdtVar22 = zzecVar2.zza;
                long j22 = (long) d;
                zzduVar2.zze = zzecVar2;
                zzduVar2.zzf = str3;
                zzduVar2.zzg = null;
                zzduVar2.zzd = 3;
                String str52 = str3;
                objZzm2 = zzdtVar22.zzm(zzspVarZzi2, str52, j22, zzduVar2);
                if (objZzm2 != coroutine_suspended) {
                }
            }
            return coroutine_suspended;
        } catch (zzbd e) {
            throw e;
        } catch (Exception e2) {
            throw new zzbd(zzbb.zzb, zzba.zzay, e2.getMessage());
        }
    }

    @Override // com.google.android.recaptcha.internal.zzcn
    public final Object zzb(long j, Continuation continuation) {
        Object objZzn = zzn(j, continuation);
        return objZzn == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objZzn : Unit.INSTANCE;
    }
}
