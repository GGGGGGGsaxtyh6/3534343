package com.google.zxing;

import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: loaded from: classes3.dex */
public final class RGBLuminanceSource extends GrayscaleLuminanceSource {
    public RGBLuminanceSource(int i, int i2, int[] iArr) {
        super(i, i2, toGrayscale(i, i2, iArr));
    }

    private static byte[] toGrayscale(int i, int i2, int[] iArr) {
        int i3 = i * i2;
        if (iArr == null || iArr.length < i3) {
            throw new IllegalArgumentException("Pixel array length is less than width * height");
        }
        byte[] bArr = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = iArr[i4];
            bArr[i4] = (byte) (((((i5 >> 16) & 255) + ((i5 >> 7) & TypedValues.PositionType.TYPE_POSITION_TYPE)) + (i5 & 255)) / 4);
        }
        return bArr;
    }
}
