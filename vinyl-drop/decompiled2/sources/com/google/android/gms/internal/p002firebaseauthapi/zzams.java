package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzalf;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@24.0.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzams<T> implements zzanb<T> {
    private final zzamm zza;
    private final zzanu<?, ?> zzb;
    private final boolean zzc;
    private final zzakw<?> zzd;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanb
    public final int zza(T t) {
        zzanu<?, ?> zzanuVar = this.zzb;
        int iZzb = zzanuVar.zzb(zzanuVar.zzd(t));
        return this.zzc ? iZzb + this.zzd.zza(t).zza() : iZzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanb
    public final int zzb(T t) {
        int iHashCode = this.zzb.zzd(t).hashCode();
        return this.zzc ? (iHashCode * 53) + this.zzd.zza(t).hashCode() : iHashCode;
    }

    static <T> zzams<T> zza(zzanu<?, ?> zzanuVar, zzakw<?> zzakwVar, zzamm zzammVar) {
        return new zzams<>(zzanuVar, zzakwVar, zzammVar);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanb
    public final T zza() {
        zzamm zzammVar = this.zza;
        if (zzammVar instanceof zzalf) {
            return (T) ((zzalf) zzammVar).zzo();
        }
        return (T) zzammVar.zzq().zzf();
    }

    private zzams(zzanu<?, ?> zzanuVar, zzakw<?> zzakwVar, zzamm zzammVar) {
        this.zzb = zzanuVar;
        this.zzc = zzakwVar.zza(zzammVar);
        this.zzd = zzakwVar;
        this.zza = zzammVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanb
    public final void zzd(T t) {
        this.zzb.zzf(t);
        this.zzd.zzc(t);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanb
    public final void zza(T t, T t2) {
        zzand.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzand.zza(this.zzd, t, t2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0087 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[LOOP:0: B:46:0x000c->B:54:?, LOOP_END, SYNTHETIC] */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t, zzanc zzancVar, zzaku zzakuVar) throws IOException {
        boolean zZzt;
        zzanu<?, ?> zzanuVar = this.zzb;
        zzakw<?> zzakwVar = this.zzd;
        Object objZzc = zzanuVar.zzc(t);
        zzakx<T> zzakxVarZzb = zzakwVar.zzb(t);
        while (zzancVar.zzc() != Integer.MAX_VALUE) {
            try {
                int iZzd = zzancVar.zzd();
                int iZzj = 0;
                if (iZzd != 11) {
                    if ((iZzd & 7) == 2) {
                        Object objZza = zzakwVar.zza(zzakuVar, this.zza, iZzd >>> 3);
                        if (objZza != null) {
                            zzakwVar.zza(zzancVar, objZza, zzakuVar, zzakxVarZzb);
                        } else {
                            zZzt = zzanuVar.zza(objZzc, zzancVar, 0);
                        }
                    } else {
                        zZzt = zzancVar.zzt();
                    }
                    if (zZzt) {
                        return;
                    }
                } else {
                    Object objZza2 = null;
                    zzajv zzajvVarZzp = null;
                    while (zzancVar.zzc() != Integer.MAX_VALUE) {
                        int iZzd2 = zzancVar.zzd();
                        if (iZzd2 == 16) {
                            iZzj = zzancVar.zzj();
                            objZza2 = zzakwVar.zza(zzakuVar, this.zza, iZzj);
                        } else if (iZzd2 == 26) {
                            if (objZza2 != null) {
                                zzakwVar.zza(zzancVar, objZza2, zzakuVar, zzakxVarZzb);
                            } else {
                                zzajvVarZzp = zzancVar.zzp();
                            }
                        } else if (iZzd2 == 12 || !zzancVar.zzt()) {
                            break;
                        }
                    }
                    if (zzancVar.zzd() != 12) {
                        throw zzall.zzb();
                    }
                    if (zzajvVarZzp != null) {
                        if (objZza2 != null) {
                            zzakwVar.zza(zzajvVarZzp, objZza2, zzakuVar, zzakxVarZzb);
                        } else {
                            zzanuVar.zza(objZzc, iZzj, zzajvVarZzp);
                        }
                    }
                }
                zZzt = true;
                if (zZzt) {
                }
            } finally {
                zzanuVar.zzb(t, objZzc);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a1, code lost:
    
        if (r12 == null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a3, code lost:
    
        r6.zza((r11 << 3) | 2, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a9, code lost:
    
        r12 = r13;
        r11 = r3;
        r13 = r5;
        r14 = r7;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a1 A[EDGE_INSN: B:58:0x00a1->B:35:0x00a1 BREAK  A[LOOP:1: B:18:0x0059->B:63:0x0059], SYNTHETIC] */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t, byte[] bArr, int i, int i2, zzaju zzajuVar) throws IOException {
        int iZzc;
        zzalf zzalfVar = (zzalf) t;
        zzanx zzanxVarZzd = zzalfVar.zzb;
        if (zzanxVarZzd == zzanx.zzc()) {
            zzanxVarZzd = zzanx.zzd();
            zzalfVar.zzb = zzanxVarZzd;
        }
        zzanx zzanxVar = zzanxVarZzd;
        ((zzalf.zzd) t).zza();
        zzalf.zzf zzfVar = null;
        while (i < i2) {
            int iZzc2 = zzajr.zzc(bArr, i, zzajuVar);
            int i3 = zzajuVar.zza;
            if (i3 == 11) {
                byte[] bArr2 = bArr;
                int i4 = i2;
                zzaju zzajuVar2 = zzajuVar;
                int i5 = 0;
                zzajv zzajvVar = null;
                while (true) {
                    if (iZzc2 >= i4) {
                        iZzc = iZzc2;
                        break;
                    }
                    iZzc = zzajr.zzc(bArr2, iZzc2, zzajuVar2);
                    int i6 = zzajuVar2.zza;
                    int i7 = i6 >>> 3;
                    int i8 = i6 & 7;
                    if (i7 != 2) {
                        if (i7 == 3) {
                            if (zzfVar != null) {
                                zzamx.zza();
                                throw new NoSuchMethodError();
                            }
                            if (i8 == 2) {
                                iZzc2 = zzajr.zza(bArr2, iZzc, zzajuVar2);
                                zzajvVar = (zzajv) zzajuVar2.zzc;
                            }
                        }
                        if (i6 != 12) {
                            break;
                        } else {
                            iZzc2 = zzajr.zza(i6, bArr2, iZzc, i4, zzajuVar2);
                        }
                    } else if (i8 == 0) {
                        iZzc2 = zzajr.zzc(bArr2, iZzc, zzajuVar2);
                        i5 = zzajuVar2.zza;
                        zzfVar = (zzalf.zzf) this.zzd.zza(zzajuVar2.zzd, this.zza, i5);
                    } else if (i6 != 12) {
                    }
                }
            } else if ((i3 & 7) == 2) {
                zzfVar = (zzalf.zzf) this.zzd.zza(zzajuVar.zzd, this.zza, i3 >>> 3);
                if (zzfVar != null) {
                    zzamx.zza();
                    throw new NoSuchMethodError();
                }
                i = zzajr.zza(i3, bArr, iZzc2, i2, zzanxVar, zzajuVar);
            } else {
                i = zzajr.zza(i3, bArr, iZzc2, i2, zzajuVar);
            }
        }
        if (i != i2) {
            throw zzall.zzg();
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanb
    public final void zza(T t, zzaol zzaolVar) throws IOException {
        Iterator itZzd = this.zzd.zza(t).zzd();
        while (itZzd.hasNext()) {
            Map.Entry entry = (Map.Entry) itZzd.next();
            zzakz zzakzVar = (zzakz) entry.getKey();
            if (zzakzVar.zzc() != zzaoj.MESSAGE || zzakzVar.zze() || zzakzVar.zzd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof zzalp) {
                zzaolVar.zza(zzakzVar.zza(), (Object) ((zzalp) entry).zza().zzb());
            } else {
                zzaolVar.zza(zzakzVar.zza(), entry.getValue());
            }
        }
        zzanu<?, ?> zzanuVar = this.zzb;
        zzanuVar.zza(zzanuVar.zzd(t), zzaolVar);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanb
    public final boolean zzb(T t, T t2) {
        if (!this.zzb.zzd(t).equals(this.zzb.zzd(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza(t).equals(this.zzd.zza(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzanb
    public final boolean zze(T t) {
        return this.zzd.zza(t).zzg();
    }
}
