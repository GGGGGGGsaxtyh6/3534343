package com.google.android.recaptcha.internal;

import android.content.Context;
import java.nio.charset.StandardCharsets;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzae implements zzy {
    private final zzek zza;
    private final Context zzb;
    private final zzan zzc;
    private boolean zzd = true;
    private String zze = "";
    private final zzbs zzf;

    public zzae(zzek zzekVar, Context context, CoroutineScope coroutineScope, zzan zzanVar, zzbs zzbsVar) {
        this.zza = zzekVar;
        this.zzb = context;
        this.zzc = zzanVar;
        this.zzf = zzbsVar;
    }

    private static final String zzi(zzle zzleVar) {
        zzkh zzkhVarZzg = zzkh.zzg();
        byte[] bArrZzl = zzleVar.zzl();
        byte[] bArrZzd = zzjz.zza().zza(zzkhVarZzg.zzi(bArrZzl, 0, bArrZzl.length), StandardCharsets.UTF_8).zzd();
        zzle zzleVarZzk = zzle.zzk(bArrZzd, 0, bArrZzd.length);
        zzkh zzkhVarZzh = zzkh.zzh();
        byte[] bArrZzl2 = zzleVarZzk.zzl();
        return zzkhVarZzh.zzi(bArrZzl2, 0, bArrZzl2.length);
    }

    @Override // com.google.android.recaptcha.internal.zzy
    public final int zza() {
        return 2;
    }

    @Override // com.google.android.recaptcha.internal.zzy
    public final zzek zzb() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzy
    public final Object zzc(String str, Continuation continuation) {
        return CoroutineScopeKt.coroutineScope(new zzac(this, str, null), continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0090, code lost:
    
        if (r12.zze(r0) == r1) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.google.android.recaptcha.internal.zzy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzd(zzse zzseVar, Continuation continuation) throws Throwable {
        zzad zzadVar;
        zzae zzaeVar;
        zzen zzenVar;
        if (continuation instanceof zzad) {
            zzadVar = (zzad) continuation;
            int i = zzadVar.zzd;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzadVar.zzd = i - Integer.MIN_VALUE;
            } else {
                zzadVar = new zzad(this, continuation);
            }
        }
        Object obj = zzadVar.zzb;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzadVar.zzd;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            zzen zzenVarZzc = zzz.zzc(this);
            if (this.zzf.zza(this.zzb) != 3 || zzseVar.zzf() == 0) {
                this.zzd = false;
                zzenVarZzc.zzb(new zzbd(zzbb.zzb, zzba.zzab, null));
                return Unit.INSTANCE;
            }
            this.zze = zzi(zzseVar.zzg());
            zzan zzanVar = this.zzc;
            long jZzf = zzseVar.zzf();
            zzadVar.zza = this;
            zzadVar.zze = zzenVarZzc;
            zzadVar.zzd = 1;
            if (zzanVar.zzd(jZzf, zzadVar) != coroutine_suspended) {
                zzaeVar = this;
                zzenVar = zzenVarZzc;
            }
            return coroutine_suspended;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            zzenVar = (zzen) zzadVar.zza;
            ResultKt.throwOnFailure(obj);
            zzenVar.zza();
            return Unit.INSTANCE;
        }
        zzenVar = zzadVar.zze;
        zzaeVar = (zzae) zzadVar.zza;
        ResultKt.throwOnFailure(obj);
        zzan zzanVar2 = zzaeVar.zzc;
        zzadVar.zza = zzenVar;
        zzadVar.zze = null;
        zzadVar.zzd = 2;
    }

    @Override // com.google.android.recaptcha.internal.zzy
    public final void zze(zzsr zzsrVar) {
        this.zze = zzi(zzsrVar.zzf());
    }

    @Override // com.google.android.recaptcha.internal.zzy
    public final boolean zzf() {
        return this.zzd;
    }
}
