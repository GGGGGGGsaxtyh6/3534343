package androidx.camera.core.impl.utils;

import androidx.arch.core.util.Function;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX INFO: compiled from: LiveDataUtil.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005R\u0010\u0010\u0003\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/camera/core/impl/utils/RedirectableLiveData;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/camera/core/impl/utils/MappingRedirectableLiveData;", "initialValue", "<init>", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class RedirectableLiveData<T> extends MappingRedirectableLiveData<T, T> {
    private final T initialValue;

    static final Object _init_$lambda$0(Object obj) {
        return obj;
    }

    public RedirectableLiveData(T t) {
        super(t, new Function() { // from class: androidx.camera.core.impl.utils.RedirectableLiveData$$ExternalSyntheticLambda0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return RedirectableLiveData._init_$lambda$0(obj);
            }
        });
        this.initialValue = t;
    }
}
