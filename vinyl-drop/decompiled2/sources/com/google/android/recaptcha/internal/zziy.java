package com.google.android.recaptcha.internal;

import android.webkit.WebView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zziy extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    Object zzd;
    int zze;
    final /* synthetic */ zzja zzf;
    final /* synthetic */ zzen zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zziy(zzja zzjaVar, zzen zzenVar, Continuation continuation) {
        super(2, continuation);
        this.zzf = zzjaVar;
        this.zzg = zzenVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zziy(this.zzf, this.zzg, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zziy) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00da, code lost:
    
        if (r9.zzf.zzm().zzc(com.google.android.recaptcha.internal.zzje.zzc, r9) == r0) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00aa  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) throws Throwable {
        zzja zzjaVar;
        zzsc zzscVar;
        zzcg zzcgVar;
        zzja zzjaVar2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zze;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            zzjaVar = this.zzf;
            zzsc zzscVar2 = zzjaVar.zzf;
            if (zzscVar2 == null) {
                zzscVar2 = null;
            }
            zzsc zzscVar3 = this.zzf.zzf;
            if (zzscVar3 == null) {
                zzscVar3 = null;
            }
            zzcg zzcgVar2 = new zzcg(zzscVar3.zzf());
            this.zza = zzjaVar;
            this.zzb = zzjaVar;
            this.zzc = zzscVar2;
            this.zzd = zzcgVar2;
            this.zze = 1;
            Object objZzw = this.zzf.zzw(this);
            if (objZzw != coroutine_suspended) {
                zzscVar = zzscVar2;
                obj = objZzw;
                zzcgVar = zzcgVar2;
                zzjaVar2 = zzjaVar;
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            ResultKt.throwOnFailure(obj);
            if (i != 2) {
                if (i == 3) {
                }
                this.zzg.zza();
                return Unit.INSTANCE;
            }
            Boxing.boxInt(this.zzf.zzA().hashCode());
            this.zze = 3;
            if (this.zzf.zzA().await(this) != coroutine_suspended) {
            }
            return coroutine_suspended;
            this.zze = 4;
        } else {
            zzcgVar = (zzcg) this.zzd;
            zzscVar = (zzsc) this.zzc;
            zzjaVar = (zzja) this.zzb;
            zzjaVar2 = (zzja) this.zza;
            ResultKt.throwOnFailure(obj);
        }
        zzjaVar2.zzb = zzjaVar.zzC(zzscVar, zzcgVar, (WebView) obj);
        Boxing.boxInt(this.zzf.zzA().hashCode());
        this.zzf.zzh.zzd();
        this.zzf.zzh.zze();
        zzja zzjaVar3 = this.zzf;
        zzsc zzscVar4 = zzjaVar3.zzf;
        if (zzscVar4 == null) {
            zzscVar4 = null;
        }
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = 2;
        if (zzjaVar3.zzE(zzscVar4, this) != coroutine_suspended) {
        }
        return coroutine_suspended;
    }
}
