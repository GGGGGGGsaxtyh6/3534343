package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.mlkit:barcode-scanning@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdu extends zzdt {
    zzdu() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdt
    final void zza(Object obj) {
        ((zzed) obj).zzb.zzg();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdt
    final void zzb(zzhh zzhhVar, Map.Entry entry) throws IOException {
        zzee zzeeVar = (zzee) entry.getKey();
        zzhf zzhfVar = zzhf.DOUBLE;
        switch (zzeeVar.zzb) {
            case DOUBLE:
                zzhhVar.zzf(zzeeVar.zza, ((Double) entry.getValue()).doubleValue());
                break;
            case FLOAT:
                zzhhVar.zzo(zzeeVar.zza, ((Float) entry.getValue()).floatValue());
                break;
            case INT64:
                zzhhVar.zzt(zzeeVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case UINT64:
                zzhhVar.zzK(zzeeVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case INT32:
                zzhhVar.zzr(zzeeVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case FIXED64:
                zzhhVar.zzm(zzeeVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case FIXED32:
                zzhhVar.zzk(zzeeVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case BOOL:
                zzhhVar.zzb(zzeeVar.zza, ((Boolean) entry.getValue()).booleanValue());
                break;
            case STRING:
                zzhhVar.zzG(zzeeVar.zza, (String) entry.getValue());
                break;
            case GROUP:
                zzhhVar.zzq(zzeeVar.zza, entry.getValue(), zzfu.zza().zzb(entry.getValue().getClass()));
                break;
            case MESSAGE:
                zzhhVar.zzv(zzeeVar.zza, entry.getValue(), zzfu.zza().zzb(entry.getValue().getClass()));
                break;
            case BYTES:
                zzhhVar.zzd(zzeeVar.zza, (zzdf) entry.getValue());
                break;
            case UINT32:
                zzhhVar.zzI(zzeeVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case ENUM:
                zzhhVar.zzr(zzeeVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SFIXED32:
                zzhhVar.zzx(zzeeVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SFIXED64:
                zzhhVar.zzz(zzeeVar.zza, ((Long) entry.getValue()).longValue());
                break;
            case SINT32:
                zzhhVar.zzB(zzeeVar.zza, ((Integer) entry.getValue()).intValue());
                break;
            case SINT64:
                zzhhVar.zzD(zzeeVar.zza, ((Long) entry.getValue()).longValue());
                break;
        }
    }
}
