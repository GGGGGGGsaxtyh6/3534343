package com.google.android.recaptcha.internal;

import android.app.Application;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.api.ApiException;
import com.google.android.recaptcha.RecaptchaException;
import java.util.UUID;
import kotlin.LazyKt;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcv {
    private final Application zza;
    private zzdc zzc;
    private final zzl zze;
    private final Mutex zzb = MutexKt.Mutex$default(false, 1, null);
    private final String zzd = UUID.randomUUID().toString();
    private zzbi zzf = new zzbi();

    /* JADX WARN: Multi-variable type inference failed */
    public zzcv(Application application) {
        this.zza = application;
        int i = 1;
        this.zze = new zzl(null, i, 0 == true ? 1 : 0);
        int i2 = zzav.zza;
        Application application2 = application;
        zzaw[] zzawVarArr = {new zzaw("com.google.android.recaptcha.internal.zzaz".hashCode(), new zzaz(null, 1, null)), new zzaw("com.google.android.recaptcha.internal.zzfu".hashCode(), new zzfu()), new zzaw("com.google.android.recaptcha.internal.zzbe".hashCode(), new zzbe()), new zzaw("com.google.android.recaptcha.internal.zzjd".hashCode(), new zzjd()), new zzaw("com.google.android.recaptcha.internal.zzbr".hashCode(), new zzbr("https://www.recaptcha.net/recaptcha/api3")), new zzaw("com.google.android.recaptcha.internal.zzex".hashCode(), new zzex(0 == true ? 1 : 0, i, 0 == true ? 1 : 0)), new zzaw("com.google.android.recaptcha.internal.zzfk".hashCode(), new zzfk(true)), new zzaw(Application.class.getName().hashCode(), application), new zzaw("com.google.android.recaptcha.internal.zzbf".hashCode(), new zzbf(application2)), new zzaw("com.google.android.recaptcha.internal.zzfj".hashCode(), new zzfj()), new zzaw("com.google.android.recaptcha.internal.zzaq".hashCode(), new zzbm(application2)), new zzaw("com.google.android.recaptcha.internal.zzey".hashCode(), new zzfa()), new zzaw("com.google.android.recaptcha.internal.zzff".hashCode(), new zzff())};
        for (int i3 = 0; i3 < 13; i3++) {
            zzaw zzawVar = zzawVarArr[i3];
            if (!zzav.zzc.containsKey(Integer.valueOf(zzawVar.zza()))) {
                zzav.zzc.put(Integer.valueOf(zzawVar.zza()), zzawVar);
            }
        }
    }

    public static final /* synthetic */ zzdc zza(zzcv zzcvVar, String str) throws zzbd {
        zzdc zzdcVar = zzcvVar.zzc;
        if (zzdcVar == null) {
            return null;
        }
        if (Intrinsics.areEqual(zzdcVar.zzd(), str)) {
            return zzdcVar;
        }
        throw new zzbd(zzbb.zzd, zzba.zzam, null);
    }

    public static final /* synthetic */ void zzc(zzcv zzcvVar, long j) throws zzbd {
        if (j < 5000) {
            throw new zzbd(zzbb.zzj, zzba.zzI, null);
        }
        if (ContextCompat.checkSelfPermission(zzcvVar.zza, "android.permission.INTERNET") != 0) {
            throw new zzbd(zzbb.zzc, zzba.zzao, null);
        }
    }

    public static final /* synthetic */ zzcn zze(zzcv zzcvVar, String str, zzbi zzbiVar, zzch zzchVar, zzek zzekVar) {
        zzdt zzdtVar = new zzdt(str, zzbiVar, zzekVar, zzcvVar.zze);
        return Intrinsics.areEqual(zzchVar, zzch.zza) ? new zzef(zzdtVar) : new zzec(zzdtVar, zzbiVar, zzekVar, new zzbo());
    }

    public static /* synthetic */ Object zzh(zzcv zzcvVar, String str, long j, zzcn zzcnVar, zzbi zzbiVar, zzch zzchVar, Continuation continuation, int i, Object obj) throws RecaptchaException, TimeoutCancellationException, ApiException {
        if ((i & 8) != 0) {
            zzbiVar = zzcvVar.zzf;
        }
        zzbi zzbiVar2 = zzbiVar;
        if ((i & 16) != 0) {
            zzchVar = zzch.zza;
        }
        zzch zzchVar2 = zzchVar;
        if ((i & 2) != 0) {
            j = 10000;
        }
        return zzcvVar.zzg(str, j, null, zzbiVar2, zzchVar2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzj(String str, int i, Function2 function2, Continuation continuation) throws Throwable {
        zzcu zzcuVar;
        Exception e;
        zzen zzenVar;
        zzbd e2;
        if (continuation instanceof zzcu) {
            zzcuVar = (zzcu) continuation;
            int i2 = zzcuVar.zzc;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                zzcuVar.zzc = i2 - Integer.MIN_VALUE;
            } else {
                zzcuVar = new zzcu(this, continuation);
            }
        }
        Object objInvoke = zzcuVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = zzcuVar.zzc;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objInvoke);
            zzek zzekVarZzk = zzk(str, this.zzf, i);
            zzen zzenVarZzf = zzekVarZzk.zzf(6);
            try {
                zzcuVar.zzd = zzenVarZzf;
                zzcuVar.zzc = 1;
                objInvoke = function2.invoke(zzekVarZzk, zzcuVar);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                zzenVar = zzenVarZzf;
            } catch (zzbd e3) {
                e2 = e3;
                zzenVar = zzenVarZzf;
                zzenVar.zzb(e2);
                throw e2.zzc();
            } catch (Exception e4) {
                e = e4;
                zzenVar = zzenVarZzf;
                zzbd zzbdVar = new zzbd(zzbb.zzb, zzba.zza, e.getMessage());
                zzenVar.zzb(zzbdVar);
                throw zzbdVar.zzc();
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            zzenVar = zzcuVar.zzd;
            try {
                ResultKt.throwOnFailure(objInvoke);
            } catch (zzbd e5) {
                e2 = e5;
                zzenVar.zzb(e2);
                throw e2.zzc();
            } catch (Exception e6) {
                e = e6;
                zzbd zzbdVar2 = new zzbd(zzbb.zzb, zzba.zza, e.getMessage());
                zzenVar.zzb(zzbdVar2);
                throw zzbdVar2.zzc();
            }
        }
        zzenVar.zza();
        return objInvoke;
    }

    private final zzek zzk(String str, zzbi zzbiVar, int i) {
        String string = UUID.randomUUID().toString();
        int i2 = zzav.zza;
        zzes zzesVar = new zzes(this.zza, new zzeu(((zzbr) LazyKt.lazy(zzcr.zza).getValue()).zzc()), zzbiVar.zza());
        zzek zzekVar = new zzek(str, this.zzd, string, i, this.zza, zzesVar, null);
        zzekVar.zzc(string);
        return zzekVar;
    }

    public final zzbi zzd() {
        return this.zzf;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzg(String str, long j, zzcn zzcnVar, zzbi zzbiVar, zzch zzchVar, Continuation continuation) throws Throwable {
        zzcs zzcsVar;
        zzbi zzbiVar2;
        zzch zzchVar2;
        long j2;
        zzcv zzcvVar;
        String str2;
        Mutex mutex;
        Mutex mutex2;
        if (continuation instanceof zzcs) {
            zzcsVar = (zzcs) continuation;
            int i = zzcsVar.zzg;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcsVar.zzg = i - Integer.MIN_VALUE;
            } else {
                zzcsVar = new zzcs(this, continuation);
            }
        }
        Object objZzj = zzcsVar.zze;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzcsVar.zzg;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(objZzj);
                Mutex mutex3 = this.zzb;
                zzcsVar.zza = this;
                zzcsVar.zzh = str;
                zzcsVar.zzb = null;
                zzcsVar.zzj = zzbiVar;
                zzcsVar.zzi = zzchVar;
                zzcsVar.zzc = mutex3;
                zzcsVar.zzd = j;
                zzcsVar.zzg = 1;
                if (mutex3.lock(null, zzcsVar) != coroutine_suspended) {
                    zzbiVar2 = zzbiVar;
                    zzchVar2 = zzchVar;
                    j2 = j;
                    zzcvVar = this;
                    str2 = str;
                    mutex = mutex3;
                }
                return coroutine_suspended;
            }
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutex2 = (Mutex) zzcsVar.zza;
                try {
                    ResultKt.throwOnFailure(objZzj);
                    zzdc zzdcVar = (zzdc) objZzj;
                    mutex2.unlock(null);
                    return zzdcVar;
                } catch (Throwable th) {
                    th = th;
                    mutex2.unlock(null);
                    throw th;
                }
            }
            long j3 = zzcsVar.zzd;
            Mutex mutex4 = (Mutex) zzcsVar.zzc;
            zzch zzchVar3 = zzcsVar.zzi;
            zzbi zzbiVar3 = zzcsVar.zzj;
            Object obj = zzcsVar.zzb;
            String str3 = zzcsVar.zzh;
            zzcv zzcvVar2 = (zzcv) zzcsVar.zza;
            ResultKt.throwOnFailure(objZzj);
            zzchVar2 = zzchVar3;
            zzbiVar2 = zzbiVar3;
            str2 = str3;
            zzcvVar = zzcvVar2;
            j2 = j3;
            mutex = mutex4;
            int i3 = Intrinsics.areEqual(zzchVar2, zzch.zza) ? 3 : Intrinsics.areEqual(zzchVar2, zzch.zzb) ? 4 : 2;
            zzct zzctVar = new zzct(zzcvVar, str2, j2, null, zzbiVar2, zzchVar2, null);
            zzcsVar.zza = mutex;
            zzcsVar.zzh = null;
            zzcsVar.zzb = null;
            zzcsVar.zzj = null;
            zzcsVar.zzi = null;
            zzcsVar.zzc = null;
            zzcsVar.zzg = 2;
            objZzj = zzcvVar.zzj(str2, i3, zzctVar, zzcsVar);
            if (objZzj != coroutine_suspended) {
                mutex2 = mutex;
                zzdc zzdcVar2 = (zzdc) objZzj;
                mutex2.unlock(null);
                return zzdcVar2;
            }
            return coroutine_suspended;
        } catch (Throwable th2) {
            th = th2;
            mutex2 = mutex;
            mutex2.unlock(null);
            throw th;
        }
    }
}
