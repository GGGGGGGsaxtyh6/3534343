package com.bsidessf.vinyldrop;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.firestore.model.Values;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BarcodeScanActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0014J\b\u0010\u0012\u001a\u00020\tH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0003J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\fH\u0002J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/bsidessf/vinyldrop/BarcodeScanActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "previewView", "Landroidx/camera/view/PreviewView;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "handledResult", "", "requestCameraPermission", "Landroidx/activity/result/ActivityResultLauncher;", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "hasCameraPermission", "startCamera", "processImageProxy", "scanner", "Lcom/google/mlkit/vision/barcode/BarcodeScanner;", "imageProxy", "Landroidx/camera/core/ImageProxy;", "pickBestBarcodeValue", "barcodes", "", "Lcom/google/mlkit/vision/barcode/common/Barcode;", "returnResult", Values.VECTOR_MAP_VECTORS_KEY, "toast", "message", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class BarcodeScanActivity extends AppCompatActivity {
    public static final String EXTRA_BARCODE_VALUE = "extra_barcode_value";
    private static final String TAG = "BarcodeScanActivity";
    private ExecutorService cameraExecutor;
    private volatile boolean handledResult;
    private PreviewView previewView;
    private final ActivityResultLauncher<String> requestCameraPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.bsidessf.vinyldrop.BarcodeScanActivity$$ExternalSyntheticLambda0
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            BarcodeScanActivity.requestCameraPermission$lambda$0(this.f$0, ((Boolean) obj).booleanValue());
        }
    });

    static final void requestCameraPermission$lambda$0(BarcodeScanActivity barcodeScanActivity, boolean z) {
        if (z) {
            barcodeScanActivity.startCamera();
        } else {
            barcodeScanActivity.toast("Camera permission is required to scan barcodes");
            barcodeScanActivity.finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        View viewFindViewById = findViewById(R.id.previewView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.previewView = (PreviewView) viewFindViewById;
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor(...)");
        this.cameraExecutor = executorServiceNewSingleThreadExecutor;
        if (hasCameraPermission()) {
            startCamera();
        } else {
            this.requestCameraPermission.launch("android.permission.CAMERA");
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ExecutorService executorService = this.cameraExecutor;
        if (executorService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraExecutor");
            executorService = null;
        }
        executorService.shutdown();
    }

    private final boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0;
    }

    private final void startCamera() {
        BarcodeScanActivity barcodeScanActivity = this;
        final ListenableFuture<ProcessCameraProvider> companion = ProcessCameraProvider.INSTANCE.getInstance(barcodeScanActivity);
        companion.addListener(new Runnable() { // from class: com.bsidessf.vinyldrop.BarcodeScanActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                BarcodeScanActivity.startCamera$lambda$5(companion, this);
            }
        }, ContextCompat.getMainExecutor(barcodeScanActivity));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final void startCamera$lambda$5(ListenableFuture listenableFuture, final BarcodeScanActivity barcodeScanActivity) {
        final ProcessCameraProvider processCameraProvider = (ProcessCameraProvider) listenableFuture.get();
        final Preview previewBuild = new Preview.Builder().build();
        PreviewView previewView = barcodeScanActivity.previewView;
        if (previewView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewView");
            previewView = null;
        }
        previewBuild.setSurfaceProvider(previewView.getSurfaceProvider());
        Intrinsics.checkNotNullExpressionValue(previewBuild, "also(...)");
        BarcodeScannerOptions barcodeScannerOptionsBuild = new BarcodeScannerOptions.Builder().setBarcodeFormats(256, new int[0]).build();
        Intrinsics.checkNotNullExpressionValue(barcodeScannerOptionsBuild, "build(...)");
        final BarcodeScanner client = BarcodeScanning.getClient(barcodeScannerOptionsBuild);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        final ImageAnalysis imageAnalysisBuild = new ImageAnalysis.Builder().setOutputImageFormat(1).setBackpressureStrategy(0).build();
        ExecutorService executorService = barcodeScanActivity.cameraExecutor;
        if (executorService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraExecutor");
            executorService = null;
        }
        imageAnalysisBuild.setAnalyzer(executorService, new ImageAnalysis.Analyzer() { // from class: com.bsidessf.vinyldrop.BarcodeScanActivity$$ExternalSyntheticLambda7
            @Override // androidx.camera.core.ImageAnalysis.Analyzer
            public final void analyze(ImageProxy imageProxy) {
                BarcodeScanActivity.startCamera$lambda$5$lambda$3$lambda$2(this.f$0, client, imageProxy);
            }
        });
        Intrinsics.checkNotNullExpressionValue(imageAnalysisBuild, "also(...)");
        final CameraSelector DEFAULT_BACK_CAMERA = CameraSelector.DEFAULT_BACK_CAMERA;
        Intrinsics.checkNotNullExpressionValue(DEFAULT_BACK_CAMERA, "DEFAULT_BACK_CAMERA");
        PreviewView previewView2 = barcodeScanActivity.previewView;
        if (previewView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewView");
            previewView2 = null;
        }
        previewView2.post(new Runnable() { // from class: com.bsidessf.vinyldrop.BarcodeScanActivity$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                BarcodeScanActivity.startCamera$lambda$5$lambda$4(this.f$0, previewBuild, imageAnalysisBuild, processCameraProvider, DEFAULT_BACK_CAMERA);
            }
        });
    }

    static final void startCamera$lambda$5$lambda$3$lambda$2(BarcodeScanActivity barcodeScanActivity, BarcodeScanner barcodeScanner, ImageProxy imageProxy) {
        Intrinsics.checkNotNullParameter(imageProxy, "imageProxy");
        barcodeScanActivity.processImageProxy(barcodeScanner, imageProxy);
    }

    static final void startCamera$lambda$5$lambda$4(BarcodeScanActivity barcodeScanActivity, Preview preview, ImageAnalysis imageAnalysis, ProcessCameraProvider processCameraProvider, CameraSelector cameraSelector) {
        PreviewView previewView = barcodeScanActivity.previewView;
        if (previewView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewView");
            previewView = null;
        }
        Display display = previewView.getDisplay();
        int rotation = display != null ? display.getRotation() : 0;
        preview.setTargetRotation(rotation);
        imageAnalysis.setTargetRotation(rotation);
        try {
            processCameraProvider.unbindAll();
            processCameraProvider.bindToLifecycle(barcodeScanActivity, cameraSelector, preview, imageAnalysis);
        } catch (Exception e) {
            Log.e(TAG, "Failed to bind camera use cases", e);
            barcodeScanActivity.toast("Failed to start camera");
            barcodeScanActivity.finish();
        }
    }

    private final void processImageProxy(BarcodeScanner scanner, final ImageProxy imageProxy) {
        Image image = imageProxy.getImage();
        if (image == null) {
            imageProxy.close();
            return;
        }
        InputImage inputImageFromMediaImage = InputImage.fromMediaImage(image, imageProxy.getImageInfo().getRotationDegrees());
        Intrinsics.checkNotNullExpressionValue(inputImageFromMediaImage, "fromMediaImage(...)");
        Task<List<Barcode>> taskProcess = scanner.process(inputImageFromMediaImage);
        final Function1 function1 = new Function1() { // from class: com.bsidessf.vinyldrop.BarcodeScanActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BarcodeScanActivity.processImageProxy$lambda$6(this.f$0, imageProxy, (List) obj);
            }
        };
        taskProcess.addOnSuccessListener(new OnSuccessListener() { // from class: com.bsidessf.vinyldrop.BarcodeScanActivity$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                function1.invoke(obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.bsidessf.vinyldrop.BarcodeScanActivity$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                BarcodeScanActivity.processImageProxy$lambda$8(exc);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.bsidessf.vinyldrop.BarcodeScanActivity$$ExternalSyntheticLambda6
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                BarcodeScanActivity.processImageProxy$lambda$9(imageProxy, task);
            }
        });
    }

    static final Unit processImageProxy$lambda$6(BarcodeScanActivity barcodeScanActivity, ImageProxy imageProxy, List list) {
        if (barcodeScanActivity.handledResult) {
            return Unit.INSTANCE;
        }
        Intrinsics.checkNotNull(list);
        String strPickBestBarcodeValue = barcodeScanActivity.pickBestBarcodeValue(list);
        String str = strPickBestBarcodeValue;
        if (str == null || StringsKt.isBlank(str)) {
            strPickBestBarcodeValue = BarcodeDecoder.INSTANCE.decodeFromImageProxy(imageProxy);
        }
        String str2 = strPickBestBarcodeValue;
        if (str2 != null && !StringsKt.isBlank(str2)) {
            barcodeScanActivity.handledResult = true;
            barcodeScanActivity.returnResult(strPickBestBarcodeValue);
        }
        return Unit.INSTANCE;
    }

    static final void processImageProxy$lambda$8(Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        Log.e(TAG, "Barcode scan failed", e);
    }

    static final void processImageProxy$lambda$9(ImageProxy imageProxy, Task it) {
        Intrinsics.checkNotNullParameter(it, "it");
        imageProxy.close();
    }

    private final String pickBestBarcodeValue(List<? extends Barcode> barcodes) {
        String rawValue;
        Barcode barcode = (Barcode) CollectionsKt.firstOrNull((List) barcodes);
        if (barcode != null && (rawValue = barcode.getRawValue()) != null) {
            return rawValue;
        }
        Barcode barcode2 = (Barcode) CollectionsKt.firstOrNull((List) barcodes);
        if (barcode2 != null) {
            return barcode2.getDisplayValue();
        }
        return null;
    }

    private final void returnResult(String value) {
        Intent intentPutExtra = new Intent().putExtra(EXTRA_BARCODE_VALUE, value);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        setResult(-1, intentPutExtra);
        finish();
    }

    private final void toast(final String message) {
        runOnUiThread(new Runnable() { // from class: com.bsidessf.vinyldrop.BarcodeScanActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Toast.makeText(this.f$0, message, 0).show();
            }
        });
    }
}
