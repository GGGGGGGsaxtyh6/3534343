package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zze {
    private boolean zza;

    protected zzen zza(String str) {
        throw null;
    }

    protected zzen zzb() {
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e4, code lost:
    
        if (r0 != r5) goto L45;
     */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzc(String str, long j, Continuation continuation) throws Throwable {
        zza zzaVar;
        zzen zzenVarZza;
        Exception exc;
        long j2;
        zzen zzenVar;
        zze zzeVar;
        zze zzeVar2;
        String str2;
        zze zzeVar3;
        String str3;
        long j3;
        Exception e;
        String str4 = str;
        long j4 = j;
        if (continuation instanceof zza) {
            zzaVar = (zza) continuation;
            int i = zzaVar.zze;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzaVar.zze = i - Integer.MIN_VALUE;
            } else {
                zzaVar = new zza(this, continuation);
            }
        }
        zza zzaVar2 = zzaVar;
        Object objWithTimeout = zzaVar2.zzc;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzaVar2.zze;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objWithTimeout);
            zzenVarZza = zza(str);
            try {
                zzb zzbVar = new zzb(this, str4, null);
                zzaVar2.zza = this;
                zzaVar2.zzf = str4;
                zzaVar2.zzg = zzenVarZza;
                zzaVar2.zzb = j4;
                zzaVar2.zze = 1;
                objWithTimeout = TimeoutKt.withTimeout(j4, zzbVar, zzaVar2);
                if (objWithTimeout != coroutine_suspended) {
                    zzeVar2 = this;
                }
            } catch (Exception e2) {
                exc = e2;
                j2 = j4;
                zzenVar = zzenVarZza;
                zzeVar = this;
                zzbd zzbdVarZza = zzf.zza(exc, new zzbd(zzbb.zzb, zzba.zzaa, exc.getMessage()));
                if (zzenVar != null) {
                    zzenVar.zzb(zzbdVarZza);
                }
                zzaVar2.zza = zzeVar;
                zzaVar2.zzf = str4;
                zzaVar2.zzg = null;
                zzaVar2.zze = 2;
                str2 = str4;
                if (zzeVar.zzi(str2, j2, exc, zzaVar2) != coroutine_suspended) {
                    zzeVar3 = zzeVar;
                    str3 = str2;
                    Result.Companion companion = Result.INSTANCE;
                    zzaVar2.zza = null;
                    zzaVar2.zzf = null;
                    zzaVar2.zze = 3;
                    objWithTimeout = zzeVar3.zzd(str3, zzaVar2);
                }
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            long j5 = zzaVar2.zzb;
            zzenVar = zzaVar2.zzg;
            String str5 = zzaVar2.zzf;
            zzeVar2 = (zze) zzaVar2.zza;
            try {
                ResultKt.throwOnFailure(objWithTimeout);
                zzenVarZza = zzenVar;
                j4 = j5;
                str4 = str5;
            } catch (Exception e3) {
                e = e3;
                j3 = j5;
                str4 = str5;
                zzeVar = zzeVar2;
                j2 = j3;
                exc = e;
                zzbd zzbdVarZza2 = zzf.zza(exc, new zzbd(zzbb.zzb, zzba.zzaa, exc.getMessage()));
                if (zzenVar != null) {
                }
                zzaVar2.zza = zzeVar;
                zzaVar2.zzf = str4;
                zzaVar2.zzg = null;
                zzaVar2.zze = 2;
                str2 = str4;
                if (zzeVar.zzi(str2, j2, exc, zzaVar2) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
        } else {
            if (i2 != 2) {
                if (i2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objWithTimeout);
                return Result.m773constructorimpl(objWithTimeout);
            }
            str3 = zzaVar2.zzf;
            zzeVar3 = (zze) zzaVar2.zza;
            ResultKt.throwOnFailure(objWithTimeout);
            Result.Companion companion2 = Result.INSTANCE;
            zzaVar2.zza = null;
            zzaVar2.zzf = null;
            zzaVar2.zze = 3;
            objWithTimeout = zzeVar3.zzd(str3, zzaVar2);
        }
        try {
            Object value = ((Result) objWithTimeout).getValue();
            ResultKt.throwOnFailure(value);
            zzsi zzsiVar = (zzsi) value;
            if (zzenVarZza != null) {
                zzenVarZza.zza();
            }
            return Result.m773constructorimpl(zzsiVar);
        } catch (Exception e4) {
            e = e4;
            j3 = j4;
            zzenVar = zzenVarZza;
            zzeVar = zzeVar2;
            j2 = j3;
            exc = e;
            zzbd zzbdVarZza22 = zzf.zza(exc, new zzbd(zzbb.zzb, zzba.zzaa, exc.getMessage()));
            if (zzenVar != null) {
            }
            zzaVar2.zza = zzeVar;
            zzaVar2.zzf = str4;
            zzaVar2.zzg = null;
            zzaVar2.zze = 2;
            str2 = str4;
            if (zzeVar.zzi(str2, j2, exc, zzaVar2) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
    }

    protected abstract Object zzd(String str, Continuation continuation);

    /* JADX WARN: Removed duplicated region for block: B:38:0x00b2 A[PHI: r8 r9 r11
      0x00b2: PHI (r8v12 com.google.android.recaptcha.internal.zzen) = (r8v8 com.google.android.recaptcha.internal.zzen), (r8v20 com.google.android.recaptcha.internal.zzen) binds: [B:37:0x00b0, B:16:0x003d] A[DONT_GENERATE, DONT_INLINE]
      0x00b2: PHI (r9v6 com.google.android.recaptcha.internal.zze) = (r9v3 com.google.android.recaptcha.internal.zze), (r9v15 com.google.android.recaptcha.internal.zze) binds: [B:37:0x00b0, B:16:0x003d] A[DONT_GENERATE, DONT_INLINE]
      0x00b2: PHI (r11v8 java.lang.Object) = (r11v5 java.lang.Object), (r11v1 java.lang.Object) binds: [B:37:0x00b0, B:16:0x003d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zze(long j, zzsc zzscVar, Continuation continuation) throws Throwable {
        zzc zzcVar;
        Exception e;
        zze zzeVar;
        zzen zzenVar;
        zzbd zzbdVar;
        zzbd zzbdVar2;
        if (continuation instanceof zzc) {
            zzcVar = (zzc) continuation;
            int i = zzcVar.zzd;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcVar.zzd = i - Integer.MIN_VALUE;
            } else {
                zzcVar = new zzc(this, continuation);
            }
        }
        Object objZzj = zzcVar.zzb;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzcVar.zzd;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objZzj);
            zzen zzenVarZzb = zzb();
            if (this.zza) {
                zzenVarZzb.zza();
                Result.Companion companion = Result.INSTANCE;
                return Result.m773constructorimpl(Unit.INSTANCE);
            }
            try {
                zzd zzdVar = new zzd(this, zzscVar, null);
                zzcVar.zza = this;
                zzcVar.zze = zzenVarZzb;
                zzcVar.zzd = 1;
                Object objWithTimeout = TimeoutKt.withTimeout(j, zzdVar, zzcVar);
                if (objWithTimeout != coroutine_suspended) {
                    objZzj = objWithTimeout;
                    zzenVar = zzenVarZzb;
                    zzeVar = this;
                }
            } catch (Exception e2) {
                e = e2;
                zzeVar = this;
                zzenVar = zzenVarZzb;
                zzeVar.zza = false;
                zzcVar.zza = zzeVar;
                zzcVar.zze = zzenVar;
                zzcVar.zzd = 2;
                objZzj = zzeVar.zzj(e, zzcVar);
                if (objZzj != coroutine_suspended) {
                    zzbdVar = (zzbd) objZzj;
                    if (zzenVar != null) {
                    }
                    zzcVar.zza = zzbdVar;
                    zzcVar.zze = null;
                    zzcVar.zzd = 3;
                    if (zzeVar.zzg(zzbdVar, zzcVar) != coroutine_suspended) {
                    }
                }
            }
            return coroutine_suspended;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                zzbdVar2 = (zzbd) zzcVar.zza;
                ResultKt.throwOnFailure(objZzj);
                Result.Companion companion2 = Result.INSTANCE;
                return Result.m773constructorimpl(ResultKt.createFailure(zzbdVar2));
            }
            zzenVar = zzcVar.zze;
            zzeVar = (zze) zzcVar.zza;
            ResultKt.throwOnFailure(objZzj);
            zzbdVar = (zzbd) objZzj;
            if (zzenVar != null) {
                zzenVar.zzb(zzbdVar);
            }
            zzcVar.zza = zzbdVar;
            zzcVar.zze = null;
            zzcVar.zzd = 3;
            if (zzeVar.zzg(zzbdVar, zzcVar) != coroutine_suspended) {
                zzbdVar2 = zzbdVar;
                Result.Companion companion22 = Result.INSTANCE;
                return Result.m773constructorimpl(ResultKt.createFailure(zzbdVar2));
            }
            return coroutine_suspended;
        }
        zzenVar = zzcVar.zze;
        zzeVar = (zze) zzcVar.zza;
        try {
            ResultKt.throwOnFailure(objZzj);
        } catch (Exception e3) {
            e = e3;
            zzeVar.zza = false;
            zzcVar.zza = zzeVar;
            zzcVar.zze = zzenVar;
            zzcVar.zzd = 2;
            objZzj = zzeVar.zzj(e, zzcVar);
            if (objZzj != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        ResultKt.throwOnFailure(((Result) objZzj).getValue());
        Unit unit = Unit.INSTANCE;
        zzeVar.zza = true;
        if (zzenVar != null) {
            zzenVar.zza();
        }
        return Result.m773constructorimpl(unit);
    }

    protected abstract Object zzf(String str, Continuation continuation) throws zzbd;

    protected Object zzg(zzbd zzbdVar, Continuation continuation) {
        return Unit.INSTANCE;
    }

    protected abstract Object zzh(zzsc zzscVar, Continuation continuation) throws zzbd;

    protected Object zzi(String str, long j, Exception exc, Continuation continuation) {
        return Unit.INSTANCE;
    }

    protected Object zzj(Exception exc, Continuation continuation) {
        return zzf.zza(exc, new zzbd(zzbb.zzb, zzba.zzap, exc.getMessage()));
    }

    protected void zzk(zzsr zzsrVar) {
    }

    public final boolean zzl() {
        return this.zza;
    }
}
