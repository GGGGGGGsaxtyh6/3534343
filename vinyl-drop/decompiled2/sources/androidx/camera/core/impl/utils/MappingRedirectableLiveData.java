package androidx.camera.core.impl.utils;

import androidx.arch.core.util.Function;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveDataUtil.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B#\u0012\u0006\u0010\u0004\u001a\u00028\u0001\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\f\u001a\u00020\r2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bJ,\u0010\u000e\u001a\u00020\r\"\u0004\b\u0002\u0010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000b2\u000e\u0010\u0011\u001a\n\u0012\u0006\b\u0000\u0012\u0002H\u000f0\u0012H\u0016J\u000f\u0010\u0013\u001a\u0004\u0018\u00018\u0001H\u0016¢\u0006\u0002\u0010\u0014R\u0010\u0010\u0004\u001a\u00028\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/camera/core/impl/utils/MappingRedirectableLiveData;", "I", "O", "Landroidx/lifecycle/MediatorLiveData;", "initialValue", "mapFunction", "Landroidx/arch/core/util/Function;", "<init>", "(Ljava/lang/Object;Landroidx/arch/core/util/Function;)V", "Ljava/lang/Object;", "liveDataSource", "Landroidx/lifecycle/LiveData;", "redirectTo", "", "addSource", ExifInterface.LATITUDE_SOUTH, "source", "onChanged", "Landroidx/lifecycle/Observer;", "getValue", "()Ljava/lang/Object;", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public class MappingRedirectableLiveData<I, O> extends MediatorLiveData<O> {
    private final O initialValue;
    private LiveData<I> liveDataSource;
    private final Function<I, O> mapFunction;

    public MappingRedirectableLiveData(O o, Function<I, O> mapFunction) {
        Intrinsics.checkNotNullParameter(mapFunction, "mapFunction");
        this.initialValue = o;
        this.mapFunction = mapFunction;
    }

    public final void redirectTo(final LiveData<I> liveDataSource) {
        Intrinsics.checkNotNullParameter(liveDataSource, "liveDataSource");
        LiveData<I> liveData = this.liveDataSource;
        if (liveData != null) {
            Intrinsics.checkNotNull(liveData);
            super.removeSource(liveData);
        }
        this.liveDataSource = liveDataSource;
        Threads.runOnMain(new Runnable() { // from class: androidx.camera.core.impl.utils.MappingRedirectableLiveData$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                MappingRedirectableLiveData.redirectTo$lambda$2(this.f$0, liveDataSource);
            }
        });
    }

    static final void redirectTo$lambda$2(final MappingRedirectableLiveData mappingRedirectableLiveData, LiveData liveData) {
        final Function1 function1 = new Function1() { // from class: androidx.camera.core.impl.utils.MappingRedirectableLiveData$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MappingRedirectableLiveData.redirectTo$lambda$2$lambda$0(this.f$0, obj);
            }
        };
        super.addSource(liveData, new Observer() { // from class: androidx.camera.core.impl.utils.MappingRedirectableLiveData$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                function1.invoke(obj);
            }
        });
    }

    static final Unit redirectTo$lambda$2$lambda$0(MappingRedirectableLiveData mappingRedirectableLiveData, Object obj) {
        mappingRedirectableLiveData.setValue(mappingRedirectableLiveData.mapFunction.apply(obj));
        return Unit.INSTANCE;
    }

    @Override // androidx.lifecycle.MediatorLiveData
    public <S> void addSource(LiveData<S> source, Observer<? super S> onChanged) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(onChanged, "onChanged");
        throw new UnsupportedOperationException();
    }

    @Override // androidx.lifecycle.LiveData
    public O getValue() {
        LiveData<I> liveData = this.liveDataSource;
        if (liveData == null) {
            return this.initialValue;
        }
        return this.mapFunction.apply(liveData.getValue());
    }
}
