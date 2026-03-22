package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcb {
    private Object zza;
    private final Mutex zzb = MutexKt.Mutex$default(false, 1, null);

    public zzcb(Object obj) {
        this.zza = obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zza(Object obj, Continuation continuation) throws Throwable {
        zzby zzbyVar;
        Mutex mutex;
        zzcb zzcbVar;
        if (continuation instanceof zzby) {
            zzbyVar = (zzby) continuation;
            int i = zzbyVar.zzd;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzbyVar.zzd = i - Integer.MIN_VALUE;
            } else {
                zzbyVar = new zzby(this, continuation);
            }
        }
        Object obj2 = zzbyVar.zzb;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzbyVar.zzd;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            mutex = this.zzb;
            zzbyVar.zze = this;
            zzbyVar.zzf = (zzje) obj;
            zzbyVar.zza = mutex;
            zzbyVar.zzd = 1;
            if (mutex.lock(null, zzbyVar) == coroutine_suspended) {
                return coroutine_suspended;
            }
            zzcbVar = this;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Mutex mutex2 = (Mutex) zzbyVar.zza;
            zzje zzjeVar = zzbyVar.zzf;
            zzcbVar = zzbyVar.zze;
            ResultKt.throwOnFailure(obj2);
            mutex = mutex2;
            obj = zzjeVar;
        }
        try {
            return Boxing.boxBoolean(Intrinsics.areEqual(zzcbVar.zza, obj));
        } finally {
            mutex.unlock(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzb(Object[] objArr, Continuation continuation) throws Throwable {
        zzbz zzbzVar;
        Mutex mutex;
        zzcb zzcbVar;
        if (continuation instanceof zzbz) {
            zzbzVar = (zzbz) continuation;
            int i = zzbzVar.zzd;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzbzVar.zzd = i - Integer.MIN_VALUE;
            } else {
                zzbzVar = new zzbz(this, continuation);
            }
        }
        Object obj = zzbzVar.zzb;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzbzVar.zzd;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            mutex = this.zzb;
            zzbzVar.zze = this;
            zzbzVar.zzf = (zzje[]) objArr;
            zzbzVar.zza = mutex;
            zzbzVar.zzd = 1;
            if (mutex.lock(null, zzbzVar) == coroutine_suspended) {
                return coroutine_suspended;
            }
            zzcbVar = this;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Mutex mutex2 = (Mutex) zzbzVar.zza;
            zzje[] zzjeVarArr = zzbzVar.zzf;
            zzcbVar = zzbzVar.zze;
            ResultKt.throwOnFailure(obj);
            mutex = mutex2;
            objArr = zzjeVarArr;
        }
        try {
            return Boxing.boxBoolean(ArraysKt.contains(objArr, zzcbVar.zza));
        } finally {
            mutex.unlock(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object zzc(Object obj, Continuation continuation) throws Throwable {
        zzca zzcaVar;
        Mutex mutex;
        zzcb zzcbVar;
        if (continuation instanceof zzca) {
            zzcaVar = (zzca) continuation;
            int i = zzcaVar.zzd;
            if ((i & Integer.MIN_VALUE) != 0) {
                zzcaVar.zzd = i - Integer.MIN_VALUE;
            } else {
                zzcaVar = new zzca(this, continuation);
            }
        }
        Object obj2 = zzcaVar.zzb;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = zzcaVar.zzd;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            mutex = this.zzb;
            zzcaVar.zze = this;
            zzcaVar.zzf = (zzje) obj;
            zzcaVar.zza = mutex;
            zzcaVar.zzd = 1;
            if (mutex.lock(null, zzcaVar) == coroutine_suspended) {
                return coroutine_suspended;
            }
            zzcbVar = this;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Mutex mutex2 = (Mutex) zzcaVar.zza;
            zzje zzjeVar = zzcaVar.zzf;
            zzcbVar = zzcaVar.zze;
            ResultKt.throwOnFailure(obj2);
            mutex = mutex2;
            obj = zzjeVar;
        }
        try {
            zzcbVar.zza = obj;
            Unit unit = Unit.INSTANCE;
            mutex.unlock(null);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            mutex.unlock(null);
            throw th;
        }
    }
}
