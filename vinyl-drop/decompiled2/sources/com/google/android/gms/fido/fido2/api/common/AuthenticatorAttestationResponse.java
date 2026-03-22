package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.u2f.api.common.SignResponseData;
import com.google.android.gms.internal.fido.zzch;
import com.google.android.gms.internal.fido.zzcl;
import com.google.android.gms.internal.fido.zzcz;
import com.google.android.gms.internal.fido.zzdl;
import com.google.android.gms.internal.fido.zzdo;
import com.google.android.gms.internal.fido.zzdq;
import com.google.android.gms.internal.fido.zzdr;
import com.google.common.primitives.SignedBytes;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public class AuthenticatorAttestationResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator<AuthenticatorAttestationResponse> CREATOR = new zzk();
    private final byte[] zza;
    private final byte[] zzb;
    private final byte[] zzc;
    private final String[] zzd;

    AuthenticatorAttestationResponse(byte[] bArr, byte[] bArr2, byte[] bArr3, String[] strArr) {
        this.zza = (byte[]) Preconditions.checkNotNull(bArr);
        this.zzb = (byte[]) Preconditions.checkNotNull(bArr2);
        this.zzc = (byte[]) Preconditions.checkNotNull(bArr3);
        this.zzd = (String[]) Preconditions.checkNotNull(strArr);
    }

    public static AuthenticatorAttestationResponse deserializeFromBytes(byte[] bArr) {
        return (AuthenticatorAttestationResponse) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthenticatorAttestationResponse)) {
            return false;
        }
        AuthenticatorAttestationResponse authenticatorAttestationResponse = (AuthenticatorAttestationResponse) obj;
        return Arrays.equals(this.zza, authenticatorAttestationResponse.zza) && Arrays.equals(this.zzb, authenticatorAttestationResponse.zzb) && Arrays.equals(this.zzc, authenticatorAttestationResponse.zzc);
    }

    public byte[] getAttestationObject() {
        return this.zzc;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse
    public byte[] getClientDataJSON() {
        return this.zzb;
    }

    @Deprecated
    public byte[] getKeyHandle() {
        return this.zza;
    }

    public String[] getTransports() {
        return this.zzd;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.zza)), Integer.valueOf(Arrays.hashCode(this.zzb)), Integer.valueOf(Arrays.hashCode(this.zzc)));
    }

    @Override // com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    public String toString() {
        com.google.android.gms.internal.fido.zzam zzamVarZza = com.google.android.gms.internal.fido.zzan.zza(this);
        zzch zzchVarZzf = zzch.zzf();
        byte[] bArr = this.zza;
        zzamVarZza.zzb(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE, zzchVarZzf.zzg(bArr, 0, bArr.length));
        zzch zzchVarZzf2 = zzch.zzf();
        byte[] bArr2 = this.zzb;
        zzamVarZza.zzb("clientDataJSON", zzchVarZzf2.zzg(bArr2, 0, bArr2.length));
        zzch zzchVarZzf3 = zzch.zzf();
        byte[] bArr3 = this.zzc;
        zzamVarZza.zzb("attestationObject", zzchVarZzf3.zzg(bArr3, 0, bArr3.length));
        zzamVarZza.zzb("transports", Arrays.toString(this.zzd));
        return zzamVarZza.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getKeyHandle(), false);
        SafeParcelWriter.writeByteArray(parcel, 3, getClientDataJSON(), false);
        SafeParcelWriter.writeByteArray(parcel, 4, getAttestationObject(), false);
        SafeParcelWriter.writeStringArray(parcel, 5, getTransports(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0117 A[Catch: zzdq -> 0x01f7, JSONException -> 0x023e, TRY_LEAVE, TryCatch #0 {zzdq -> 0x01f7, blocks: (B:31:0x00e6, B:37:0x0105, B:39:0x0117, B:44:0x012b, B:47:0x014d, B:49:0x0163, B:51:0x0169, B:52:0x0180, B:53:0x0185, B:54:0x0186, B:55:0x018b, B:60:0x0196, B:62:0x01a6, B:64:0x01b4, B:65:0x01c7, B:66:0x01cc, B:67:0x01cd, B:68:0x01d2, B:73:0x01f1, B:74:0x01f6), top: B:103:0x00e6, outer: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01f1 A[Catch: zzdq -> 0x01f7, JSONException -> 0x023e, TRY_ENTER, TryCatch #0 {zzdq -> 0x01f7, blocks: (B:31:0x00e6, B:37:0x0105, B:39:0x0117, B:44:0x012b, B:47:0x014d, B:49:0x0163, B:51:0x0169, B:52:0x0180, B:53:0x0185, B:54:0x0186, B:55:0x018b, B:60:0x0196, B:62:0x01a6, B:64:0x01b4, B:65:0x01c7, B:66:0x01cc, B:67:0x01cd, B:68:0x01d2, B:73:0x01f1, B:74:0x01f6), top: B:103:0x00e6, outer: #6 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final JSONObject zza() {
        zzdr zzdrVar;
        byte[] bArrZza;
        try {
            JSONObject jSONObject = new JSONObject();
            byte[] bArr = this.zzb;
            if (bArr != null) {
                jSONObject.put("clientDataJSON", Base64Utils.encodeUrlSafeNoPadding(bArr));
            }
            byte[] bArr2 = this.zzc;
            if (bArr2 != null) {
                jSONObject.put("attestationObject", Base64Utils.encodeUrlSafeNoPadding(bArr2));
            }
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (true) {
                String[] strArr = this.zzd;
                if (i >= strArr.length) {
                    break;
                }
                if (strArr[i].equals(Transport.HYBRID.toString())) {
                    jSONArray.put(i, "hybrid");
                } else {
                    jSONArray.put(i, this.zzd[i]);
                }
                i++;
            }
            jSONObject.put("transports", jSONArray);
            try {
                try {
                    zzdr zzdrVar2 = (zzdr) zzdr.zzj(this.zzc).zzh().zzc().get(zzdr.zzi("authData"));
                    if (zzdrVar2 == null) {
                        throw new IllegalArgumentException("attestation object missing authData");
                    }
                    zzcz zzczVarZzc = zzdrVar2.zze().zzc();
                    ByteBuffer byteBufferZzi = zzczVarZzc.zzi();
                    try {
                        byteBufferZzi.position(byteBufferZzi.position() + 32);
                        if ((byteBufferZzi.get() & SignedBytes.MAX_POWER_OF_TWO) == 0) {
                            throw new IllegalArgumentException("authData does not include credential data");
                        }
                        byteBufferZzi.position(byteBufferZzi.position() + 4);
                        byteBufferZzi.position(byteBufferZzi.position() + 16);
                        byteBufferZzi.position(byteBufferZzi.position() + byteBufferZzi.getShort());
                        try {
                            zzdo zzdoVarZzh = zzdr.zzk(zzczVarZzc.zzg(byteBufferZzi.position(), zzczVarZzc.zzd()).zzh()).zzh();
                            zzdr zzdrVar3 = (zzdr) zzdoVarZzh.zzc().get(zzdr.zzg(3L));
                            zzdr zzdrVar4 = (zzdr) zzdoVarZzh.zzc().get(zzdr.zzg(1L));
                            if (zzdrVar3 == null || zzdrVar4 == null) {
                                throw new IllegalArgumentException("COSE key missing required fields");
                            }
                            try {
                                long jZzc = zzdrVar3.zzf().zzc();
                                long jZzc2 = zzdrVar4.zzf().zzc();
                                if (jZzc2 == 1) {
                                    zzdrVar = (zzdr) zzdoVarZzh.zzc().get(zzdr.zzg(-1L));
                                    if (zzdrVar != null) {
                                        throw new IllegalArgumentException("COSE key missing required fields");
                                    }
                                    long jZzc3 = zzdrVar.zzf().zzc();
                                    if (jZzc2 == 2 && jZzc3 == 1) {
                                        zzdr zzdrVar5 = (zzdr) zzdoVarZzh.zzc().get(zzdr.zzg(-2L));
                                        zzdr zzdrVar6 = (zzdr) zzdoVarZzh.zzc().get(zzdr.zzg(-3L));
                                        if (zzdrVar5 == null || zzdrVar6 == null) {
                                            throw new IllegalArgumentException("COSE key missing required fields");
                                        }
                                        zzcz zzczVarZzc2 = zzdrVar5.zze().zzc();
                                        zzcz zzczVarZzc3 = zzdrVar6.zze().zzc();
                                        if (zzczVarZzc2.zzd() != 32 || zzczVarZzc3.zzd() != 32) {
                                            throw new IllegalArgumentException("COSE coordinates are the wrong size");
                                        }
                                        bArrZza = zzcl.zza(Base64.decode("MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE", 0), zzczVarZzc2.zzm(), zzczVarZzc3.zzm());
                                    } else if (jZzc2 == 1 && jZzc3 == 6) {
                                        zzdr zzdrVar7 = (zzdr) zzdoVarZzh.zzc().get(zzdr.zzg(-2L));
                                        if (zzdrVar7 == null) {
                                            throw new IllegalArgumentException("COSE key missing required fields");
                                        }
                                        zzcz zzczVarZzc4 = zzdrVar7.zze().zzc();
                                        if (zzczVarZzc4.zzd() != 32) {
                                            throw new IllegalArgumentException("COSE coordinates are the wrong size");
                                        }
                                        bArrZza = zzcl.zza(Base64.decode("MCowBQYDK2VwAyEA", 0), zzczVarZzc4.zzm());
                                    } else {
                                        bArrZza = null;
                                    }
                                } else {
                                    if (jZzc2 == 2) {
                                        jZzc2 = 2;
                                        zzdrVar = (zzdr) zzdoVarZzh.zzc().get(zzdr.zzg(-1L));
                                        if (zzdrVar != null) {
                                        }
                                    }
                                    bArrZza = null;
                                }
                                jSONObject.put("authenticatorData", Base64Utils.encodeUrlSafeNoPadding(zzczVarZzc.zzm()));
                                jSONObject.put("publicKeyAlgorithm", jZzc);
                                if (bArrZza != null) {
                                    jSONObject.put("publicKey", Base64Utils.encodeUrlSafeNoPadding(bArrZza));
                                }
                                return jSONObject;
                            } catch (zzdq e) {
                                throw new IllegalArgumentException("COSE key ill-formed", e);
                            }
                        } catch (zzdl e2) {
                            e = e2;
                            throw new IllegalArgumentException("failed to parse COSE key", e);
                        } catch (zzdq e3) {
                            e = e3;
                            throw new IllegalArgumentException("failed to parse COSE key", e);
                        }
                    } catch (IllegalArgumentException e4) {
                        throw new IllegalArgumentException("ill-formed authenticator data", e4);
                    }
                } catch (zzdq e5) {
                    throw new IllegalArgumentException("authData value has wrong type", e5);
                }
            } catch (zzdl e6) {
                e = e6;
                throw new IllegalArgumentException("failed to parse attestation object", e);
            } catch (zzdq e7) {
                e = e7;
                throw new IllegalArgumentException("failed to parse attestation object", e);
            }
        } catch (JSONException e8) {
            throw new RuntimeException("Error encoding AuthenticatorAttestationResponse to JSON object", e8);
        }
    }
}
