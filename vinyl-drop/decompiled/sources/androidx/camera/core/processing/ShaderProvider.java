package androidx.camera.core.processing;

/* JADX INFO: loaded from: classes.dex */
public interface ShaderProvider {
    default String createFragmentShader(String str, String str2) {
        return null;
    }
}
