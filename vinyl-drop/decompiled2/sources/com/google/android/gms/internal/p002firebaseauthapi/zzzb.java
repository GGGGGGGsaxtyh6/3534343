package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.EllipticCurve;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@24.0.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzzb implements zzbd {
    private final ECPrivateKey zza;
    private final zzzd zzb;
    private final String zzc;
    private final byte[] zzd;
    private final zzzh zze;
    private final zzlr zzf;
    private final byte[] zzg;

    public static zzbd zza(zzke zzkeVar) throws GeneralSecurityException {
        ECPrivateKey eCPrivateKeyZza = zzzf.zza((zzzi) zzze.zza.zza(((zzkb) ((zzlg) zzkeVar.zza())).zzd()), zzne.zza(zzkeVar.zzf().zza(zzbf.zza())));
        byte[] bArrZzb = new byte[0];
        if (((zzkb) ((zzlg) zzkeVar.zza())).zzh() != null) {
            bArrZzb = ((zzkb) ((zzlg) zzkeVar.zza())).zzh().zzb();
        }
        return new zzzb(eCPrivateKeyZza, bArrZzb, zzze.zza(((zzkb) ((zzlg) zzkeVar.zza())).zze()), (zzzh) zzze.zzb.zza(((zzkb) ((zzlg) zzkeVar.zza())).zzf()), zzln.zza((zzkb) ((zzlg) zzkeVar.zza())), zzkeVar.zzh().zzb());
    }

    private zzzb(ECPrivateKey eCPrivateKey, byte[] bArr, String str, zzzh zzzhVar, zzlr zzlrVar, byte[] bArr2) {
        this.zza = eCPrivateKey;
        this.zzb = new zzzd(eCPrivateKey);
        this.zzd = bArr;
        this.zzc = str;
        this.zze = zzzhVar;
        this.zzf = zzlrVar;
        this.zzg = bArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0055  */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbd
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int i;
        int i2;
        if (!zzqn.zza(this.zzg, bArr)) {
            throw new GeneralSecurityException("Invalid ciphertext (output prefix mismatch)");
        }
        int length = this.zzg.length;
        EllipticCurve curve = this.zza.getParams().getCurve();
        zzzh zzzhVar = this.zze;
        int iZza = zzzf.zza(curve);
        int iOrdinal = zzzhVar.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                if (iOrdinal != 2) {
                    throw new GeneralSecurityException("unknown EC point format");
                }
                i = iZza * 2;
            }
            i2 = i + length;
            if (bArr.length >= i2) {
                throw new GeneralSecurityException("ciphertext too short");
            }
            return this.zzf.zza(this.zzb.zza(Arrays.copyOfRange(bArr, length, i2), this.zzc, this.zzd, bArr2, this.zzf.zza(), this.zze), bArr, i2);
        }
        iZza *= 2;
        i = iZza + 1;
        i2 = i + length;
        if (bArr.length >= i2) {
        }
    }
}
