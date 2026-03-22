package com.google.android.recaptcha.internal;

import com.google.android.play.core.integrity.StandardIntegrityException;
import com.google.android.play.core.integrity.model.StandardIntegrityErrorCode;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzac extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzae zzc;
    final /* synthetic */ String zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzac(zzae zzaeVar, String str, Continuation continuation) {
        super(2, continuation);
        this.zzc = zzaeVar;
        this.zzd = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzac(this.zzc, this.zzd, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzac) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        zzae zzaeVar;
        zzen zzenVarZzb;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zzb;
        try {
        } catch (Exception e) {
            zzenVarZzb.zzb(new zzbd(zzbb.zzb, zzba.zzaa, e.getMessage()));
            zzaeVar = this.zzc;
            this.zza = zzaeVar;
            int i2 = 2;
            this.zzb = 2;
            if (e instanceof StandardIntegrityException) {
                int errorCode = ((StandardIntegrityException) e).getErrorCode();
                if (errorCode == -100) {
                    i2 = 44;
                } else if (errorCode == -12) {
                    i2 = 39;
                } else if (errorCode == -3) {
                    i2 = 30;
                } else if (errorCode == -2) {
                    i2 = 29;
                } else if (errorCode != -1) {
                    switch (errorCode) {
                        case StandardIntegrityErrorCode.INTEGRITY_TOKEN_PROVIDER_INVALID /* -19 */:
                            i2 = 54;
                            break;
                        case StandardIntegrityErrorCode.CLIENT_TRANSIENT_ERROR /* -18 */:
                            i2 = 53;
                            break;
                        case -17:
                            i2 = 52;
                            break;
                        case -16:
                            i2 = 43;
                            break;
                        case -15:
                            i2 = 42;
                            break;
                        case -14:
                            i2 = 41;
                            break;
                        default:
                            switch (errorCode) {
                                case -9:
                                    i2 = 36;
                                    break;
                                case -8:
                                    i2 = 35;
                                    break;
                                case -7:
                                    i2 = 34;
                                    break;
                                case -6:
                                    i2 = 33;
                                    break;
                                case -5:
                                    i2 = 32;
                                    break;
                            }
                            break;
                    }
                } else {
                    i2 = 28;
                }
            } else {
                i2 = 45;
            }
            zztd zztdVarZzf = zzte.zzf();
            zztdVarZzf.zzq(i2);
            zztdVarZzf.zzr(15);
            obj = zztdVarZzf.zzk();
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        if (i != 0) {
            Object obj2 = this.zza;
            if (i != 1) {
                zzaeVar = (zzy) obj2;
                ResultKt.throwOnFailure(obj);
                int iZza = zzaeVar.zza();
                zztd zztdVar = (zztd) ((zzte) obj).zzr();
                zztdVar.zzf(zzaeVar.zza());
                return new zzw(iZza, (zzte) zztdVar.zzk());
            }
            zzenVarZzb = (zzen) obj2;
            ResultKt.throwOnFailure(obj);
        } else {
            ResultKt.throwOnFailure(obj);
            zzenVarZzb = zzz.zzb(this.zzc, this.zzd);
            zzae zzaeVar2 = this.zzc;
            this.zza = zzenVarZzb;
            this.zzb = 1;
            obj = zzaeVar2.zzc.zzc(zzaeVar2.zze, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        zzenVarZzb.zza();
        zzae zzaeVar3 = this.zzc;
        zztf zztfVarZzf = zzti.zzf();
        zztg zztgVarZzf = zzth.zzf();
        zztgVarZzf.zzw((String) obj);
        zztfVarZzf.zzf((zzth) zztgVarZzf.zzk());
        return zzz.zza(zzaeVar3, (zzti) zztfVarZzf.zzk());
    }
}
