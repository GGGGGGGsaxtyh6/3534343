package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.mlkit:barcode-scanning@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzfq implements zzge {
    private final zzfm zza;
    private final zzgs zzb;
    private final boolean zzc;
    private final zzdt zzd;

    private zzfq(zzgs zzgsVar, zzdt zzdtVar, zzfm zzfmVar) {
        this.zzb = zzgsVar;
        this.zzc = zzfmVar instanceof zzed;
        this.zzd = zzdtVar;
        this.zza = zzfmVar;
    }

    static zzfq zzc(zzgs zzgsVar, zzdt zzdtVar, zzfm zzfmVar) {
        return new zzfq(zzgsVar, zzdtVar, zzfmVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final int zza(Object obj) {
        int iZzb = ((zzeh) obj).zzc.zzb();
        return this.zzc ? iZzb + ((zzed) obj).zzb.zzb() : iZzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final int zzb(Object obj) {
        int iHashCode = ((zzeh) obj).zzc.hashCode();
        return this.zzc ? (iHashCode * 53) + ((zzed) obj).zzb.zza.hashCode() : iHashCode;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final Object zze() {
        zzfm zzfmVar = this.zza;
        return zzfmVar instanceof zzeh ? ((zzeh) zzfmVar).zzK() : zzfmVar.zzZ().zzk();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final void zzf(Object obj) {
        this.zzb.zza(obj);
        this.zzd.zza(obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final void zzg(Object obj, Object obj2) {
        zzgg.zzp(this.zzb, obj, obj2);
        if (this.zzc) {
            zzgg.zzo(this.zzd, obj, obj2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00be A[EDGE_INSN: B:61:0x00be->B:33:0x00be BREAK  A[LOOP:1: B:17:0x0067->B:64:0x0067], SYNTHETIC] */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzcu zzcuVar) throws IOException {
        int iZzj;
        zzeh zzehVar = (zzeh) obj;
        zzgt zzgtVarZzf = zzehVar.zzc;
        if (zzgtVarZzf == zzgt.zzc()) {
            zzgtVarZzf = zzgt.zzf();
            zzehVar.zzc = zzgtVarZzf;
        }
        zzgt zzgtVar = zzgtVarZzf;
        zzdx zzdxVarZzc = ((zzed) obj).zzc();
        zzef zzefVarZzb = null;
        while (i < i2) {
            int iZzj2 = zzcv.zzj(bArr, i, zzcuVar);
            int i3 = zzcuVar.zza;
            if (i3 == 11) {
                int i4 = i2;
                zzcu zzcuVar2 = zzcuVar;
                int i5 = 0;
                zzdf zzdfVar = null;
                while (true) {
                    if (iZzj2 >= i4) {
                        iZzj = iZzj2;
                        break;
                    }
                    iZzj = zzcv.zzj(bArr, iZzj2, zzcuVar2);
                    int i6 = zzcuVar2.zza;
                    int i7 = i6 >>> 3;
                    int i8 = i6 & 7;
                    if (i7 != 2) {
                        if (i7 == 3) {
                            if (zzefVarZzb != null) {
                                iZzj2 = zzcv.zze(zzfu.zza().zzb(zzefVarZzb.zza.getClass()), bArr, iZzj, i4, zzcuVar2);
                                zzdxVarZzc.zzi(zzefVarZzb.zzb, zzcuVar2.zzc);
                            } else if (i8 == 2) {
                                iZzj2 = zzcv.zza(bArr, iZzj, zzcuVar2);
                                zzdfVar = (zzdf) zzcuVar2.zzc;
                            }
                        }
                        if (i6 != 12) {
                            break;
                        } else {
                            iZzj2 = zzcv.zzp(i6, bArr, iZzj, i4, zzcuVar2);
                        }
                    } else if (i8 == 0) {
                        iZzj2 = zzcv.zzj(bArr, iZzj, zzcuVar2);
                        i5 = zzcuVar2.zza;
                        zzefVarZzb = zzcuVar2.zzd.zzb(this.zza, i5);
                    } else if (i6 != 12) {
                    }
                }
                if (zzdfVar != null) {
                    zzgtVar.zzj((i5 << 3) | 2, zzdfVar);
                }
                i = iZzj;
                i2 = i4;
                zzcuVar = zzcuVar2;
            } else if ((i3 & 7) == 2) {
                zzefVarZzb = zzcuVar.zzd.zzb(this.zza, i3 >>> 3);
                if (zzefVarZzb != null) {
                    i = zzcv.zze(zzfu.zza().zzb(zzefVarZzb.zza.getClass()), bArr, iZzj2, i2, zzcuVar);
                    zzdxVarZzc.zzi(zzefVarZzb.zzb, zzcuVar.zzc);
                } else {
                    i = zzcv.zzi(i3, bArr, iZzj2, i2, zzgtVar, zzcuVar);
                }
            } else {
                i = zzcv.zzp(i3, bArr, iZzj2, i2, zzcuVar);
            }
        }
        if (i != i2) {
            throw new zzer("Failed to parse the message.");
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final void zzi(Object obj, zzhh zzhhVar) throws IOException {
        Iterator itZzf = ((zzed) obj).zzb.zzf();
        while (itZzf.hasNext()) {
            Map.Entry entry = (Map.Entry) itZzf.next();
            zzdw zzdwVar = (zzdw) entry.getKey();
            if (zzdwVar.zze() != zzhg.MESSAGE) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            zzdwVar.zzg();
            zzdwVar.zzf();
            if (entry instanceof zzeu) {
                zzhhVar.zzw(zzdwVar.zza(), ((zzeu) entry).zza().zzb());
            } else {
                zzhhVar.zzw(zzdwVar.zza(), entry.getValue());
            }
        }
        ((zzeh) obj).zzc.zzk(zzhhVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final boolean zzj(Object obj, Object obj2) {
        if (!((zzeh) obj).zzc.equals(((zzeh) obj2).zzc)) {
            return false;
        }
        if (this.zzc) {
            return ((zzed) obj).zzb.equals(((zzed) obj2).zzb);
        }
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzge
    public final boolean zzk(Object obj) {
        return ((zzed) obj).zzb.zzk();
    }
}
