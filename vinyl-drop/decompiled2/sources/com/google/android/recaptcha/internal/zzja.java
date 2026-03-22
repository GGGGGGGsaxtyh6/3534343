package com.google.android.recaptcha.internal;

import android.app.Application;
import android.webkit.WebView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.TimeoutCancellationException;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzja extends zze {
    public CompletableDeferred zza;
    public zzfo zzb;
    private final zzek zzc;
    private zzsc zzf;
    private final zzek zzj;
    private final Lazy zzk;
    private final Lazy zzl;
    private final Lazy zzm;
    private final Lazy zzn;
    private final Lazy zzo;
    private zzen zzp;
    private final zzbi zzq;
    private final Map zzd = zzjb.zza();
    private final Map zze = new LinkedHashMap();
    private final zzcb zzg = new zzcb(zzje.zza);
    private final zzjh zzh = zzjh.zzc();
    private final zzij zzi = new zzij(this);

    public zzja(zzek zzekVar, zzbi zzbiVar) {
        this.zzc = zzekVar;
        this.zzq = zzbiVar;
        zzek zzekVarZza = zzekVar.zza();
        zzekVarZza.zzc(zzekVar.zzd());
        this.zzj = zzekVarZza;
        int i = zzav.zza;
        this.zzk = LazyKt.lazy(zzis.zza);
        this.zzl = LazyKt.lazy(zzit.zza);
        this.zzm = LazyKt.lazy(zziu.zza);
        this.zzn = LazyKt.lazy(zziv.zza);
        this.zzo = LazyKt.lazy(zziw.zza);
    }

    private final Application zzD() {
        return (Application) this.zzo.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzE(zzsc zzscVar, Continuation continuation) throws Throwable {
        zzim zzimVar;
        zzbd zzbdVar;
        zzja zzjaVar;
        if (continuation instanceof zzim) {
            zzimVar = (zzim) continuation;
            int i = zzimVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzimVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzimVar = new zzim(this, continuation);
            }
        }
        Object objZzd = zzimVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzimVar.zzc;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objZzd);
            try {
                zzff zzffVar = (zzff) this.zzn.getValue();
                zzek zzekVar = this.zzj;
                zzimVar.zzd = this;
                zzimVar.zzc = 1;
                objZzd = zzffVar.zzd(zzscVar, zzekVar, zzimVar);
                if (objZzd == coroutine_suspended) {
                    return coroutine_suspended;
                }
                zzjaVar = this;
            } catch (zzbd e) {
                zzbdVar = e;
                zzjaVar = this;
                zzjaVar.zzA().completeExceptionally(zzbdVar);
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            zzjaVar = zzimVar.zzd;
            try {
                ResultKt.throwOnFailure(objZzd);
            } catch (zzbd e2) {
                zzbdVar = e2;
                zzjaVar.zzA().completeExceptionally(zzbdVar);
            }
        }
        BuildersKt__Builders_commonKt.launch$default(zzjaVar.zzq.zzb(), null, null, new zzin(zzjaVar, (String) objZzd, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzF(String str, Continuation continuation) throws Throwable {
        zzio zzioVar;
        Exception exc;
        zzja zzjaVar;
        String str2;
        String str3;
        zzja zzjaVar2;
        zzen zzenVar;
        if (continuation instanceof zzio) {
            zzioVar = (zzio) continuation;
            int i = zzioVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzioVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzioVar = new zzio(this, continuation);
            }
        }
        Object obj = zzioVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzioVar.zzc;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            this.zzp = this.zzj.zzf(26);
            try {
                String strZza = ((zzbr) this.zzl.getValue()).zza();
                zzioVar.zzd = this;
                zzioVar.zze = str;
                zzioVar.zzf = strZza;
                zzioVar.zzc = 1;
                Object objZzw = zzw(zzioVar);
                if (objZzw == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str2 = str;
                str3 = strZza;
                obj = objZzw;
                zzjaVar2 = this;
                ((WebView) obj).loadDataWithBaseURL(str3, str2, "text/html", "utf-8", null);
            } catch (Exception e) {
                exc = e;
                zzjaVar = this;
                zzbd zzbdVar = new zzbd(zzbb.zzb, zzba.zzU, exc.getMessage());
                zzenVar = zzjaVar.zzp;
                if (zzenVar != null) {
                }
                zzjaVar.zzp = null;
                zzjaVar.zzA().completeExceptionally(zzbdVar);
                return Unit.INSTANCE;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            String str4 = zzioVar.zzf;
            String str5 = zzioVar.zze;
            zzjaVar = zzioVar.zzd;
            try {
                ResultKt.throwOnFailure(obj);
                str3 = str4;
                str2 = str5;
                zzjaVar2 = zzjaVar;
                try {
                    ((WebView) obj).loadDataWithBaseURL(str3, str2, "text/html", "utf-8", null);
                } catch (Exception e2) {
                    zzjaVar = zzjaVar2;
                    exc = e2;
                    zzbd zzbdVar2 = new zzbd(zzbb.zzb, zzba.zzU, exc.getMessage());
                    zzenVar = zzjaVar.zzp;
                    if (zzenVar != null) {
                        zzenVar.zzb(zzbdVar2);
                    }
                    zzjaVar.zzp = null;
                    zzjaVar.zzA().completeExceptionally(zzbdVar2);
                }
            } catch (Exception e3) {
                exc = e3;
                zzbd zzbdVar22 = new zzbd(zzbb.zzb, zzba.zzU, exc.getMessage());
                zzenVar = zzjaVar.zzp;
                if (zzenVar != null) {
                }
                zzjaVar.zzp = null;
                zzjaVar.zzA().completeExceptionally(zzbdVar22);
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzG(String str, Continuation continuation) throws Throwable {
        zzix zzixVar;
        zzja zzjaVar;
        zzja zzjaVar2;
        if (continuation instanceof zzix) {
            zzixVar = (zzix) continuation;
            int i = zzixVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzixVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzixVar = new zzix(this, continuation);
            }
        }
        Object objZzb = zzixVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzixVar.zzc;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objZzb);
            zzcb zzcbVar = this.zzg;
            zzje[] zzjeVarArr = {zzje.zzd, zzje.zzc, zzje.zzb};
            zzixVar.zzd = this;
            zzixVar.zze = str;
            zzixVar.zzc = 1;
            objZzb = zzcbVar.zzb(zzjeVarArr, zzixVar);
            if (objZzb != coroutine_suspended) {
                zzjaVar = this;
            }
            return coroutine_suspended;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str = zzixVar.zze;
            zzjaVar2 = zzixVar.zzd;
            ResultKt.throwOnFailure(objZzb);
            zzjaVar2.zza = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
            zzek zzekVar = zzjaVar2.zzj;
            zzekVar.zzc(str);
            BuildersKt__Builders_commonKt.launch$default(zzjaVar2.zzq.zza(), null, null, new zziz(zzjaVar2, zzekVar.zzf(42), null), 3, null);
            return Unit.INSTANCE;
        }
        str = zzixVar.zze;
        zzjaVar = zzixVar.zzd;
        ResultKt.throwOnFailure(objZzb);
        if (((Boolean) objZzb).booleanValue()) {
            return Unit.INSTANCE;
        }
        zzcb zzcbVar2 = zzjaVar.zzg;
        zzje zzjeVar = zzje.zzb;
        zzixVar.zzd = zzjaVar;
        zzixVar.zze = str;
        zzixVar.zzc = 2;
        if (zzcbVar2.zzc(zzjeVar, zzixVar) != coroutine_suspended) {
            zzjaVar2 = zzjaVar;
            zzjaVar2.zza = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
            zzek zzekVar2 = zzjaVar2.zzj;
            zzekVar2.zzc(str);
            BuildersKt__Builders_commonKt.launch$default(zzjaVar2.zzq.zza(), null, null, new zziz(zzjaVar2, zzekVar2.zzf(42), null), 3, null);
            return Unit.INSTANCE;
        }
        return coroutine_suspended;
    }

    public static final /* synthetic */ zzfk zzp(zzja zzjaVar) {
        return (zzfk) zzjaVar.zzm.getValue();
    }

    public final CompletableDeferred zzA() {
        CompletableDeferred completableDeferred = this.zza;
        if (completableDeferred != null) {
            return completableDeferred;
        }
        return null;
    }

    public final zzft zzC(zzsc zzscVar, zzcg zzcgVar, WebView webView) {
        zzfw zzfwVar = new zzfw(webView, this.zzq.zzb());
        zzhy zzhyVar = new zzhy();
        zzhyVar.zzb(CollectionsKt.toLongArray(zzscVar.zzP()));
        zzgf zzgfVar = new zzgf(zzfwVar, zzcgVar, new zzbo());
        zzhz zzhzVar = new zzhz(zzhyVar, new zzhw());
        zzgfVar.zze(3, zzD());
        zzgfVar.zze(5, zzig.zza());
        zzgfVar.zze(6, new zzia(zzD()));
        zzgfVar.zze(7, new zzic());
        zzgfVar.zze(8, new zzii(zzD()));
        zzgfVar.zze(9, new zzid(zzD()));
        zzgfVar.zze(10, new zzib(zzD()));
        return new zzft(this.zzq.zzd(), zzgfVar, zzhzVar, zzfn.zza());
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final zzen zza(String str) {
        zzek zzekVar = this.zzc;
        zzekVar.zzc(str);
        return zzekVar.zzf(33);
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final zzen zzb() {
        zzek zzekVar = this.zzc;
        zzekVar.zzc(zzekVar.zzd());
        return zzekVar.zzf(32);
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzd(String str, Continuation continuation) {
        zzsh zzshVarZzf = zzsi.zzf();
        zzshVarZzf.zze(str);
        return zzshVarZzf.zzk();
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e7, code lost:
    
        if (r8.zzG(r4, r2) != r3) goto L44;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // com.google.android.recaptcha.internal.zze
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final Object zzf(String str, Continuation continuation) throws Throwable {
        zzip zzipVar;
        String str2;
        zzja zzjaVar;
        zzja zzjaVar2;
        zzja zzjaVar3;
        String str3;
        zzja zzjaVar4;
        CompletableDeferred completableDeferredZzA;
        zzja zzjaVar5;
        CompletableDeferred completableDeferred;
        if (continuation instanceof zzip) {
            zzipVar = (zzip) continuation;
            int i = zzipVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzipVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzipVar = new zzip(this, continuation);
            }
        }
        Object objZza = zzipVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzipVar.zzc;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objZza);
            zzcb zzcbVar = this.zzg;
            zzje zzjeVar = zzje.zzd;
            zzipVar.zzd = this;
            zzipVar.zze = str;
            zzipVar.zzc = 1;
            objZza = zzcbVar.zza(zzjeVar, zzipVar);
            if (objZza != coroutine_suspended) {
                str2 = str;
                zzjaVar = this;
            }
            return coroutine_suspended;
        }
        if (i2 != 1) {
            if (i2 == 2) {
                str2 = zzipVar.zze;
                zzjaVar2 = zzipVar.zzd;
                ResultKt.throwOnFailure(objZza);
                if (!((Boolean) objZza).booleanValue()) {
                    zzipVar.zzd = zzjaVar2;
                    zzipVar.zze = str2;
                    zzipVar.zzc = 3;
                }
                zzjaVar3 = zzjaVar2;
                completableDeferredZzA = zzjaVar3.zzA();
                zzipVar.zzd = zzjaVar3;
                zzipVar.zze = str2;
                zzipVar.zzc = 4;
                if (completableDeferredZzA.await(zzipVar) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i2 == 3) {
                str2 = zzipVar.zze;
                zzjaVar3 = zzipVar.zzd;
                ResultKt.throwOnFailure(objZza);
                try {
                    completableDeferredZzA = zzjaVar3.zzA();
                    zzipVar.zzd = zzjaVar3;
                    zzipVar.zze = str2;
                    zzipVar.zzc = 4;
                    if (completableDeferredZzA.await(zzipVar) != coroutine_suspended) {
                        zzjaVar5 = zzjaVar3;
                        CompletableDeferred completableDeferredCompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                        zzjaVar5.zze.put(str2, completableDeferredCompletableDeferred$default);
                        zztp zztpVarZzf = zztq.zzf();
                        zztpVarZzf.zze(str2);
                        byte[] bArrZzd = ((zztq) zztpVarZzf.zzk()).zzd();
                        BuildersKt__Builders_commonKt.launch$default(zzjaVar5.zzq.zzb(), null, null, new zziq(zzjaVar5, zzkh.zzh().zzi(bArrZzd, 0, bArrZzd.length), null), 3, null);
                        zzipVar.zzd = zzjaVar5;
                        zzipVar.zze = str2;
                        zzipVar.zzc = 5;
                        objZza = completableDeferredCompletableDeferred$default.await(zzipVar);
                        if (objZza != coroutine_suspended) {
                        }
                    }
                    return coroutine_suspended;
                } catch (Exception e) {
                    e = e;
                    str3 = str2;
                    zzjaVar4 = zzjaVar3;
                    zzbd zzbdVarZza = zzf.zza(e, new zzbd(zzbb.zzb, zzba.zzW, e.getMessage()));
                    completableDeferred = (CompletableDeferred) zzjaVar4.zze.remove(str3);
                    if (completableDeferred != null) {
                    }
                    Result.Companion companion = Result.INSTANCE;
                    return Result.m773constructorimpl(ResultKt.createFailure(zzbdVarZza));
                }
            }
            if (i2 != 4) {
                if (i2 != 5) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                str3 = zzipVar.zze;
                zzjaVar4 = zzipVar.zzd;
                try {
                    ResultKt.throwOnFailure(objZza);
                    zzsi zzsiVar = (zzsi) objZza;
                    zzsh zzshVarZzf = zzsi.zzf();
                    zzshVarZzf.zze(str3);
                    zzsl zzslVarZzf = zzsm.zzf();
                    zzslVarZzf.zze(zzsiVar.zzl());
                    zzshVarZzf.zzq(zzslVarZzf);
                    zzsj zzsjVarZzf = zzsk.zzf();
                    zzsjVarZzf.zze(zzsiVar.zzj());
                    zzsjVarZzf.zzf(zzsiVar.zzM());
                    zzshVarZzf.zzr(zzsjVarZzf);
                    Result.Companion companion2 = Result.INSTANCE;
                    return Result.m773constructorimpl(zzshVarZzf.zzk());
                } catch (Exception e2) {
                    e = e2;
                    zzbd zzbdVarZza2 = zzf.zza(e, new zzbd(zzbb.zzb, zzba.zzW, e.getMessage()));
                    completableDeferred = (CompletableDeferred) zzjaVar4.zze.remove(str3);
                    if (completableDeferred != null) {
                    }
                    Result.Companion companion3 = Result.INSTANCE;
                    return Result.m773constructorimpl(ResultKt.createFailure(zzbdVarZza2));
                }
            }
            str2 = zzipVar.zze;
            zzjaVar5 = zzipVar.zzd;
            try {
                ResultKt.throwOnFailure(objZza);
                CompletableDeferred completableDeferredCompletableDeferred$default2 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
                zzjaVar5.zze.put(str2, completableDeferredCompletableDeferred$default2);
                zztp zztpVarZzf2 = zztq.zzf();
                zztpVarZzf2.zze(str2);
                byte[] bArrZzd2 = ((zztq) zztpVarZzf2.zzk()).zzd();
                BuildersKt__Builders_commonKt.launch$default(zzjaVar5.zzq.zzb(), null, null, new zziq(zzjaVar5, zzkh.zzh().zzi(bArrZzd2, 0, bArrZzd2.length), null), 3, null);
                zzipVar.zzd = zzjaVar5;
                zzipVar.zze = str2;
                zzipVar.zzc = 5;
                objZza = completableDeferredCompletableDeferred$default2.await(zzipVar);
                if (objZza != coroutine_suspended) {
                    str3 = str2;
                    zzjaVar4 = zzjaVar5;
                    zzsi zzsiVar2 = (zzsi) objZza;
                    zzsh zzshVarZzf2 = zzsi.zzf();
                    zzshVarZzf2.zze(str3);
                    zzsl zzslVarZzf2 = zzsm.zzf();
                    zzslVarZzf2.zze(zzsiVar2.zzl());
                    zzshVarZzf2.zzq(zzslVarZzf2);
                    zzsj zzsjVarZzf2 = zzsk.zzf();
                    zzsjVarZzf2.zze(zzsiVar2.zzj());
                    zzsjVarZzf2.zzf(zzsiVar2.zzM());
                    zzshVarZzf2.zzr(zzsjVarZzf2);
                    Result.Companion companion22 = Result.INSTANCE;
                    return Result.m773constructorimpl(zzshVarZzf2.zzk());
                }
                return coroutine_suspended;
            } catch (Exception e3) {
                e = e3;
                str3 = str2;
                zzjaVar4 = zzjaVar5;
                zzbd zzbdVarZza22 = zzf.zza(e, new zzbd(zzbb.zzb, zzba.zzW, e.getMessage()));
                completableDeferred = (CompletableDeferred) zzjaVar4.zze.remove(str3);
                if (completableDeferred != null) {
                    Boxing.boxBoolean(completableDeferred.completeExceptionally(zzbdVarZza22));
                }
                Result.Companion companion32 = Result.INSTANCE;
                return Result.m773constructorimpl(ResultKt.createFailure(zzbdVarZza22));
            }
        }
        str2 = zzipVar.zze;
        zzjaVar = zzipVar.zzd;
        ResultKt.throwOnFailure(objZza);
        if (((Boolean) objZza).booleanValue()) {
            zzbd zzbdVar = new zzbd(zzbb.zzb, zzba.zzav, null);
            Result.Companion companion4 = Result.INSTANCE;
            return Result.m773constructorimpl(ResultKt.createFailure(zzbdVar));
        }
        zzcb zzcbVar2 = zzjaVar.zzg;
        zzje zzjeVar2 = zzje.zzc;
        zzipVar.zzd = zzjaVar;
        zzipVar.zze = str2;
        zzipVar.zzc = 2;
        objZza = zzcbVar2.zza(zzjeVar2, zzipVar);
        if (objZza != coroutine_suspended) {
            zzjaVar2 = zzjaVar;
            if (!((Boolean) objZza).booleanValue()) {
            }
            zzjaVar3 = zzjaVar2;
            completableDeferredZzA = zzjaVar3.zzA();
            zzipVar.zzd = zzjaVar3;
            zzipVar.zze = str2;
            zzipVar.zzc = 4;
            if (completableDeferredZzA.await(zzipVar) != coroutine_suspended) {
            }
        }
        return coroutine_suspended;
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzg(zzbd zzbdVar, Continuation continuation) {
        if (Intrinsics.areEqual(zzbdVar.zza(), zzba.zzb)) {
            zzen zzenVar = this.zzp;
            if (zzenVar != null) {
                zzenVar.zzb(zzbdVar);
            }
            this.zzp = null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x005c, code lost:
    
        if (zzG(r6, r0) != r1) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0071, code lost:
    
        if (r6.zzc(r7, r0) == r1) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.google.android.recaptcha.internal.zze
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final Object zzh(zzsc zzscVar, Continuation continuation) throws Throwable {
        zzir zzirVar;
        if (continuation instanceof zzir) {
            zzirVar = (zzir) continuation;
            int i = zzirVar.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzirVar.zzc = i - Integer.MIN_VALUE;
            } else {
                zzirVar = new zzir(this, continuation);
            }
        }
        Object obj = zzirVar.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzirVar.zzc;
        if (i2 != 0) {
            if (i2 == 1) {
                ResultKt.throwOnFailure(obj);
                Result.Companion companion = Result.INSTANCE;
                return Result.m773constructorimpl(ResultKt.createFailure(new zzbd(zzbb.zzb, zzba.zzav, null)));
            }
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m773constructorimpl(Unit.INSTANCE);
        }
        ResultKt.throwOnFailure(obj);
        if (zzscVar.zzT() && zzscVar.zzR() && zzscVar.zzQ()) {
            this.zzf = zzscVar;
            String strZzd = this.zzc.zzd();
            zzirVar.zzc = 2;
        } else {
            zzcb zzcbVar = this.zzg;
            zzje zzjeVar = zzje.zzd;
            zzirVar.zzc = 1;
        }
        return coroutine_suspended;
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzi(String str, long j, Exception exc, Continuation continuation) {
        exc.getMessage();
        CompletableDeferred completableDeferred = (CompletableDeferred) this.zze.remove(str);
        if (completableDeferred != null) {
            Boxing.boxBoolean(completableDeferred.completeExceptionally(exc));
        }
        return Unit.INSTANCE;
    }

    @Override // com.google.android.recaptcha.internal.zze
    protected final Object zzj(Exception exc, Continuation continuation) {
        return ((exc instanceof TimeoutCancellationException) && this.zzi.zza() == null) ? new zzbd(zzbb.zzc, zzba.zzH, null) : zzf.zza(exc, new zzbd(zzbb.zzb, zzba.zzV, exc.getMessage()));
    }

    public final zzcb zzm() {
        return this.zzg;
    }

    public final zzij zzq() {
        return this.zzi;
    }

    public final Object zzw(Continuation continuation) {
        return BuildersKt.withContext(this.zzq.zzb().getCoroutineContext(), new zzjc((zzjd) this.zzk.getValue(), zzD(), null), continuation);
    }

    public final Object zzx(Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(this.zzq.zzb().getCoroutineContext(), new zzil(this, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }
}
