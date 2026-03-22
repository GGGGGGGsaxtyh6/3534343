package com.google.zxing;

/* JADX INFO: loaded from: classes3.dex */
public class GrayscaleLuminanceSource extends LuminanceSource {
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final byte[] luminances;
    private final int top;

    @Override // com.google.zxing.LuminanceSource
    public boolean isCropSupported() {
        return true;
    }

    @Override // com.google.zxing.LuminanceSource
    public boolean isRotateSupported() {
        return true;
    }

    public GrayscaleLuminanceSource(int i, int i2, byte[] bArr) {
        super(i, i2);
        this.luminances = bArr;
        this.dataWidth = i;
        this.dataHeight = i2;
        this.left = 0;
        this.top = 0;
    }

    private GrayscaleLuminanceSource(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i5, i6);
        if (i5 + i3 > i || i6 + i4 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.luminances = bArr;
        this.dataWidth = i;
        this.dataHeight = i2;
        this.left = i3;
        this.top = i4;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getRow(int i, byte[] bArr) {
        if (i < 0 || i >= getHeight()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i);
        }
        int width = getWidth();
        if (bArr == null || bArr.length < width) {
            bArr = new byte[width];
        }
        System.arraycopy(this.luminances, ((i + this.top) * this.dataWidth) + this.left, bArr, 0, width);
        return bArr;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        int i = this.dataWidth;
        if (width == i && height == this.dataHeight) {
            return this.luminances;
        }
        int i2 = width * height;
        byte[] bArr = new byte[i2];
        int i3 = (this.top * i) + this.left;
        if (width == i) {
            System.arraycopy(this.luminances, i3, bArr, 0, i2);
            return bArr;
        }
        for (int i4 = 0; i4 < height; i4++) {
            System.arraycopy(this.luminances, i3, bArr, i4 * width, width);
            i3 += this.dataWidth;
        }
        return bArr;
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource crop(int i, int i2, int i3, int i4) {
        return new GrayscaleLuminanceSource(this.luminances, this.dataWidth, this.dataHeight, this.left + i, this.top + i2, i3, i4);
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource rotateCounterClockwise() {
        byte[] bArr = new byte[this.luminances.length];
        for (int i = 0; i < this.dataHeight; i++) {
            int i2 = 0;
            while (true) {
                int i3 = this.dataWidth;
                if (i2 < i3) {
                    bArr[(((i3 - 1) - i2) * this.dataHeight) + i] = this.luminances[(i * i3) + i2];
                    i2++;
                }
            }
        }
        return new GrayscaleLuminanceSource(bArr, this.dataHeight, this.dataWidth, this.top, this.dataWidth - (this.left + getWidth()), getHeight(), getWidth());
    }
}
