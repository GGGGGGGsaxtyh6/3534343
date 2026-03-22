package com.google.android.recaptcha.internal;

import android.webkit.WebView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.6.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzil extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzja zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzil(zzja zzjaVar, Continuation continuation) {
        super(2, continuation);
        this.zzc = zzjaVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new zzil(this.zzc, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzil) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0077, code lost:
    
        if (r7 == r0) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0060 A[PHI: r7
      0x0060: PHI (r7v15 java.lang.Object) = (r7v11 java.lang.Object), (r7v0 java.lang.Object) binds: [B:16:0x005e, B:7:0x0014] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zzb;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.zzb = 1;
            obj = this.zzc.zzw(this);
            if (obj != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                ResultKt.throwOnFailure(obj);
                if (i == 3) {
                    ((WebView) obj).addJavascriptInterface(this.zzc.zzq(), "RN");
                    this.zzb = 4;
                    obj = this.zzc.zzw(this);
                }
                ((WebView) obj).setWebViewClient(new zzik(this.zzc));
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            ((WebView) obj).getSettings().setJavaScriptEnabled(true);
            this.zza = null;
            this.zzb = 3;
            obj = this.zzc.zzw(this);
            if (obj != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        ResultKt.throwOnFailure(obj);
        ((WebView) obj).removeJavascriptInterface("RN");
        zzja zzjaVar = this.zzc;
        this.zza = zzjaVar;
        this.zzb = 2;
        obj = zzjaVar.zzw(this);
        if (obj != coroutine_suspended) {
            ((WebView) obj).getSettings().setJavaScriptEnabled(true);
            this.zza = null;
            this.zzb = 3;
            obj = this.zzc.zzw(this);
            if (obj != coroutine_suspended) {
            }
        }
        return coroutine_suspended;
    }
}
