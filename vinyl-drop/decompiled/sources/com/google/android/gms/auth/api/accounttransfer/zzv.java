package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* JADX INFO: compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzv implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i;
        int header;
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        zzw zzwVar = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        loop0: while (true) {
            i = i2;
            while (parcel.dataPosition() < iValidateObjectHeader) {
                header = SafeParcelReader.readHeader(parcel);
                int fieldId = SafeParcelReader.getFieldId(header);
                if (fieldId != 1) {
                    if (fieldId == 2) {
                        zzw zzwVar2 = (zzw) SafeParcelReader.createParcelable(parcel, header, zzw.CREATOR);
                        hashSet.add(2);
                        zzwVar = zzwVar2;
                    } else if (fieldId == 3) {
                        String strCreateString = SafeParcelReader.createString(parcel, header);
                        hashSet.add(3);
                        str = strCreateString;
                    } else if (fieldId == 4) {
                        String strCreateString2 = SafeParcelReader.createString(parcel, header);
                        hashSet.add(4);
                        str2 = strCreateString2;
                    } else if (fieldId != 5) {
                        SafeParcelReader.skipUnknownField(parcel, header);
                    } else {
                        String strCreateString3 = SafeParcelReader.createString(parcel, header);
                        hashSet.add(5);
                        str3 = strCreateString3;
                    }
                }
            }
            i2 = SafeParcelReader.readInt(parcel, header);
            hashSet.add(1);
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzu(hashSet, i, zzwVar, str, str2, str3);
        }
        throw new SafeParcelReader.ParseException("Overread allowed size end=" + iValidateObjectHeader, parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzu[i];
    }
}
