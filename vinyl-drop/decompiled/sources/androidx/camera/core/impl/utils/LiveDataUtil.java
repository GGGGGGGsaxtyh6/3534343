package androidx.camera.core.impl.utils;

import androidx.arch.core.util.Function;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveDataUtil.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J<\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\nH\u0007¨\u0006\u000b"}, d2 = {"Landroidx/camera/core/impl/utils/LiveDataUtil;", "", "<init>", "()V", "map", "Landroidx/lifecycle/LiveData;", "O", "I", "source", "mapFunction", "Landroidx/arch/core/util/Function;", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LiveDataUtil {
    public static final LiveDataUtil INSTANCE = new LiveDataUtil();

    private LiveDataUtil() {
    }

    @JvmStatic
    public static final <I, O> LiveData<O> map(LiveData<I> source, Function<I, O> mapFunction) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(mapFunction, "mapFunction");
        MappingRedirectableLiveData mappingRedirectableLiveData = new MappingRedirectableLiveData(mapFunction.apply(source.getValue()), mapFunction);
        mappingRedirectableLiveData.redirectTo(source);
        return mappingRedirectableLiveData;
    }
}
