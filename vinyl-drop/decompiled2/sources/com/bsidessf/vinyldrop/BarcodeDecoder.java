package com.bsidessf.vinyldrop;

import android.graphics.Bitmap;
import androidx.camera.core.ImageProxy;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BarcodeDecoder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/bsidessf/vinyldrop/BarcodeDecoder;", "", "<init>", "()V", "formats", "", "Lcom/google/zxing/BarcodeFormat;", "hints", "", "Lcom/google/zxing/DecodeHintType;", "decodeFromImageProxy", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "decodeFromBitmap", "bitmap", "Landroid/graphics/Bitmap;", "decodeWithRotation", "source", "Lcom/google/zxing/LuminanceSource;", "rotationDegrees", "", "app"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class BarcodeDecoder {
    public static final BarcodeDecoder INSTANCE = new BarcodeDecoder();
    private static final List<BarcodeFormat> formats;
    private static final Map<DecodeHintType, List<BarcodeFormat>> hints;

    private BarcodeDecoder() {
    }

    static {
        List<BarcodeFormat> listListOf = CollectionsKt.listOf(BarcodeFormat.QR_CODE);
        formats = listListOf;
        hints = MapsKt.mapOf(TuplesKt.to(DecodeHintType.POSSIBLE_FORMATS, listListOf));
    }

    public final String decodeFromImageProxy(ImageProxy imageProxy) {
        Intrinsics.checkNotNullParameter(imageProxy, "imageProxy");
        ImageProxy.PlaneProxy planeProxy = imageProxy.getPlanes()[0];
        ByteBuffer buffer = planeProxy.getBuffer();
        Intrinsics.checkNotNullExpressionValue(buffer, "getBuffer(...)");
        int width = imageProxy.getWidth();
        int height = imageProxy.getHeight();
        int rowStride = planeProxy.getRowStride();
        int pixelStride = planeProxy.getPixelStride();
        byte[] bArr = new byte[width * height];
        byte[] bArr2 = new byte[rowStride];
        int i = 0;
        for (int i2 = 0; i2 < height; i2++) {
            buffer.position(i2 * rowStride);
            buffer.get(bArr2, 0, rowStride);
            if (pixelStride == 1) {
                System.arraycopy(bArr2, 0, bArr, i, width);
                i += width;
            } else {
                int i3 = 0;
                while (i3 < width) {
                    bArr[i] = bArr2[i3 * pixelStride];
                    i3++;
                    i++;
                }
            }
        }
        return decodeWithRotation(new PlanarYUVLuminanceSource(bArr, width, height, 0, 0, width, height, false), imageProxy.getImageInfo().getRotationDegrees());
    }

    public final String decodeFromBitmap(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        return decodeWithRotation(new RGBLuminanceSource(width, height, iArr), 0);
    }

    private final String decodeWithRotation(LuminanceSource source, int rotationDegrees) {
        int i = (4 - ((((rotationDegrees % 360) + 360) % 360) / 90)) % 4;
        for (int i2 = 0; i2 < i; i2++) {
            if (source.isRotateSupported()) {
                source = source.rotateCounterClockwise();
                Intrinsics.checkNotNullExpressionValue(source, "rotateCounterClockwise(...)");
            }
        }
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        multiFormatReader.setHints(hints);
        try {
            String text = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(source))).getText();
            multiFormatReader.reset();
            return text;
        } catch (Exception unused) {
            multiFormatReader.reset();
            return null;
        } catch (Throwable th) {
            multiFormatReader.reset();
            throw th;
        }
    }
}
