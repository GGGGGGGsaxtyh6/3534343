package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbq {
    public static final zzbq zza = new zzbq();

    private zzbq() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ae, code lost:
    
        if (kotlinx.coroutines.DelayKt.delay(r7, r1) != r3) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0083 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00ae -> B:13:0x003f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zza(Function1 function1, long j, long j2, double d, Function1 function12, Continuation continuation) throws Exception {
        zzbp zzbpVar;
        long jCoerceAtMost;
        long j3;
        double d2;
        Function1 function13;
        zzbp zzbpVar2;
        Function1 function14;
        Function1 function15;
        long j4;
        double d3;
        Exception e;
        if (continuation instanceof zzbp) {
            zzbpVar = (zzbp) continuation;
            int i = zzbpVar.zzh;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzbpVar.zzh = i - Integer.MIN_VALUE;
            } else {
                zzbpVar = new zzbp(this, continuation);
            }
        }
        Object obj = zzbpVar.zzf;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzbpVar.zzh;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            jCoerceAtMost = j;
            j3 = j2;
            d2 = d;
            function13 = function12;
            zzbpVar2 = zzbpVar;
            function14 = function1;
            zzbpVar2.zza = function14;
            zzbpVar2.zzb = function13;
            zzbpVar2.zzc = j3;
            zzbpVar2.zze = d2;
            zzbpVar2.zzd = jCoerceAtMost;
            zzbpVar2.zzh = 1;
            Object objInvoke = function13.invoke(zzbpVar2);
            if (objInvoke != coroutine_suspended) {
            }
        } else {
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                jCoerceAtMost = zzbpVar.zzd;
                d3 = zzbpVar.zze;
                j4 = zzbpVar.zzc;
                function13 = (Function1) zzbpVar.zzb;
                function15 = (Function1) zzbpVar.zza;
                ResultKt.throwOnFailure(obj);
                Function1 function16 = function15;
                zzbpVar2 = zzbpVar;
                function14 = function16;
                long j5 = j4;
                d2 = d3;
                j3 = j5;
                try {
                } catch (Exception e2) {
                    e = e2;
                    zzbp zzbpVar3 = zzbpVar2;
                    function15 = function14;
                    zzbpVar = zzbpVar3;
                    double d4 = d2;
                    j4 = j3;
                    d3 = d4;
                    if (((Boolean) function15.invoke(e)).booleanValue()) {
                    }
                }
                zzbpVar2.zza = function14;
                zzbpVar2.zzb = function13;
                zzbpVar2.zzc = j3;
                zzbpVar2.zze = d2;
                zzbpVar2.zzd = jCoerceAtMost;
                zzbpVar2.zzh = 1;
                Object objInvoke2 = function13.invoke(zzbpVar2);
                return objInvoke2 != coroutine_suspended ? coroutine_suspended : objInvoke2;
            }
            jCoerceAtMost = zzbpVar.zzd;
            d3 = zzbpVar.zze;
            j4 = zzbpVar.zzc;
            function13 = (Function1) zzbpVar.zzb;
            function15 = (Function1) zzbpVar.zza;
            try {
                ResultKt.throwOnFailure(obj);
                return obj;
            } catch (Exception e3) {
                e = e3;
                if (((Boolean) function15.invoke(e)).booleanValue()) {
                    throw e;
                }
                jCoerceAtMost = RangesKt.coerceAtMost((long) (jCoerceAtMost * d3), j4);
                zzbpVar.zza = function15;
                zzbpVar.zzb = function13;
                zzbpVar.zzc = j4;
                zzbpVar.zze = d3;
                zzbpVar.zzd = jCoerceAtMost;
                zzbpVar.zzh = 2;
            }
        }
    }
}
