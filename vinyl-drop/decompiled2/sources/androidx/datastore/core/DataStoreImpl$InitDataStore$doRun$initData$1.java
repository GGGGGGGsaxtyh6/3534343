package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.core.DataStoreImpl;
import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: DataStoreImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/core/Data;", ExifInterface.GPS_DIRECTION_TRUE}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1", f = "DataStoreImpl.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, l = {437, 458, 546, 468}, m = "invokeSuspend", n = {"updateLock", "initializationComplete", "currentData", "updateLock", "initializationComplete", "currentData", "api", "initializationComplete", "currentData", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2"})
final class DataStoreImpl$InitDataStore$doRun$initData$1<T> extends SuspendLambda implements Function1<Continuation<? super Data<T>>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;
    final /* synthetic */ DataStoreImpl<T>.InitDataStore this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataStoreImpl$InitDataStore$doRun$initData$1(DataStoreImpl<T> dataStoreImpl, DataStoreImpl<T>.InitDataStore initDataStore, Continuation<? super DataStoreImpl$InitDataStore$doRun$initData$1> continuation) {
        super(1, continuation);
        this.this$0 = dataStoreImpl;
        this.this$1 = initDataStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new DataStoreImpl$InitDataStore$doRun$initData$1(this.this$0, this.this$1, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Data<T>> continuation) {
        return ((DataStoreImpl$InitDataStore$doRun$initData$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x011a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) throws Throwable {
        Mutex mutexMutex$default;
        Ref.BooleanRef booleanRef;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        Ref.BooleanRef booleanRef2;
        Mutex mutex;
        Iterator<T> it;
        Mutex mutex2;
        Ref.BooleanRef booleanRef3;
        Ref.ObjectRef objectRef3;
        DataStoreImpl$InitDataStore$doRun$initData$1$api$1 dataStoreImpl$InitDataStore$doRun$initData$1$api$1;
        Ref.ObjectRef objectRef4;
        Object obj2;
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            mutexMutex$default = MutexKt.Mutex$default(false, 1, null);
            booleanRef = new Ref.BooleanRef();
            objectRef = new Ref.ObjectRef();
            this.L$0 = mutexMutex$default;
            this.L$1 = booleanRef;
            this.L$2 = objectRef;
            this.L$3 = objectRef;
            this.label = 1;
            obj = this.this$0.readDataOrHandleCorruption(true, this);
            if (obj != coroutine_suspended) {
                objectRef2 = objectRef;
            }
            return coroutine_suspended;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    i = this.I$0;
                    obj2 = this.L$0;
                    ResultKt.throwOnFailure(obj);
                    return new Data(obj2, i, ((Number) obj).intValue());
                }
                mutex = (Mutex) this.L$2;
                objectRef4 = (Ref.ObjectRef) this.L$1;
                booleanRef2 = (Ref.BooleanRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                try {
                    booleanRef2.element = true;
                    Unit unit = Unit.INSTANCE;
                    mutex.unlock(null);
                    obj2 = objectRef4.element;
                    T t = objectRef4.element;
                    int iHashCode = t != null ? t.hashCode() : 0;
                    this.L$0 = obj2;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.I$0 = iHashCode;
                    this.label = 4;
                    obj = this.this$0.getCoordinator().getVersion(this);
                    if (obj != coroutine_suspended) {
                        i = iHashCode;
                        return new Data(obj2, i, ((Number) obj).intValue());
                    }
                    return coroutine_suspended;
                } catch (Throwable th) {
                    mutex.unlock(null);
                    throw th;
                }
            }
            it = (Iterator) this.L$4;
            dataStoreImpl$InitDataStore$doRun$initData$1$api$1 = (DataStoreImpl$InitDataStore$doRun$initData$1$api$1) this.L$3;
            objectRef3 = (Ref.ObjectRef) this.L$2;
            booleanRef3 = (Ref.BooleanRef) this.L$1;
            mutex2 = (Mutex) this.L$0;
            ResultKt.throwOnFailure(obj);
            while (it.hasNext()) {
                Function2 function2 = (Function2) it.next();
                this.L$0 = mutex2;
                this.L$1 = booleanRef3;
                this.L$2 = objectRef3;
                this.L$3 = dataStoreImpl$InitDataStore$doRun$initData$1$api$1;
                this.L$4 = it;
                this.label = 2;
                if (function2.invoke(dataStoreImpl$InitDataStore$doRun$initData$1$api$1, this) == coroutine_suspended) {
                    break;
                }
            }
            objectRef2 = objectRef3;
            booleanRef2 = booleanRef3;
            mutex = mutex2;
            ((DataStoreImpl.InitDataStore) this.this$1).initTasks = null;
            this.L$0 = booleanRef2;
            this.L$1 = objectRef2;
            this.L$2 = mutex;
            this.L$3 = null;
            this.L$4 = null;
            this.label = 3;
            if (mutex.lock(null, this) != coroutine_suspended) {
                objectRef4 = objectRef2;
                booleanRef2.element = true;
                Unit unit2 = Unit.INSTANCE;
                mutex.unlock(null);
                obj2 = objectRef4.element;
                T t2 = objectRef4.element;
                if (t2 != null) {
                }
                this.L$0 = obj2;
                this.L$1 = null;
                this.L$2 = null;
                this.I$0 = iHashCode;
                this.label = 4;
                obj = this.this$0.getCoordinator().getVersion(this);
                if (obj != coroutine_suspended) {
                }
            }
            return coroutine_suspended;
        }
        objectRef = (Ref.ObjectRef) this.L$3;
        objectRef2 = (Ref.ObjectRef) this.L$2;
        booleanRef = (Ref.BooleanRef) this.L$1;
        mutexMutex$default = (Mutex) this.L$0;
        ResultKt.throwOnFailure(obj);
        objectRef.element = (T) ((Data) obj).getValue();
        DataStoreImpl$InitDataStore$doRun$initData$1$api$1 dataStoreImpl$InitDataStore$doRun$initData$1$api$12 = new DataStoreImpl$InitDataStore$doRun$initData$1$api$1(mutexMutex$default, booleanRef, objectRef2, this.this$0);
        List list = ((DataStoreImpl.InitDataStore) this.this$1).initTasks;
        if (list == null) {
            booleanRef2 = booleanRef;
            mutex = mutexMutex$default;
            ((DataStoreImpl.InitDataStore) this.this$1).initTasks = null;
            this.L$0 = booleanRef2;
            this.L$1 = objectRef2;
            this.L$2 = mutex;
            this.L$3 = null;
            this.L$4 = null;
            this.label = 3;
            if (mutex.lock(null, this) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        it = list.iterator();
        mutex2 = mutexMutex$default;
        booleanRef3 = booleanRef;
        objectRef3 = objectRef2;
        dataStoreImpl$InitDataStore$doRun$initData$1$api$1 = dataStoreImpl$InitDataStore$doRun$initData$1$api$12;
        while (it.hasNext()) {
        }
        objectRef2 = objectRef3;
        booleanRef2 = booleanRef3;
        mutex = mutex2;
        ((DataStoreImpl.InitDataStore) this.this$1).initTasks = null;
        this.L$0 = booleanRef2;
        this.L$1 = objectRef2;
        this.L$2 = mutex;
        this.L$3 = null;
        this.L$4 = null;
        this.label = 3;
        if (mutex.lock(null, this) != coroutine_suspended) {
        }
        return coroutine_suspended;
    }
}
