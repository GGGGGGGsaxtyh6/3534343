package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageCaptureException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface TakePictureManager {

    public interface Provider {
        TakePictureManager newInstance(ImageCaptureControl imageCaptureControl);
    }

    void abortRequests();

    RequestWithCallback getCapturingRequest();

    ImagePipeline getImagePipeline();

    List<RequestWithCallback> getIncompleteRequests();

    boolean hasCapturingRequest();

    void offerRequest(TakePictureRequest takePictureRequest);

    void pause();

    void resume();

    void setImagePipeline(ImagePipeline imagePipeline);

    public static abstract class CaptureError {
        abstract ImageCaptureException getImageCaptureException();

        abstract int getRequestId();

        static CaptureError of(int i, ImageCaptureException imageCaptureException) {
            return new AutoValue_TakePictureManager_CaptureError(i, imageCaptureException);
        }
    }
}
