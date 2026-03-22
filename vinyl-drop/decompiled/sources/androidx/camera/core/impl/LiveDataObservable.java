package androidx.camera.core.impl;

import android.os.SystemClock;
import androidx.camera.core.impl.LiveDataObservable;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class LiveDataObservable<T> implements Observable<T> {
    private Observer<Result<T>> mLiveDataObserver;
    final MutableLiveData<Result<T>> mLiveData = new MutableLiveData<>();
    private final Map<Observable.Observer<? super T>, Executor> mObservers = new HashMap();

    public void postValue(T t) {
        this.mLiveData.postValue(Result.fromValue(t));
    }

    public void postError(Throwable th) {
        this.mLiveData.postValue(Result.fromError(th));
    }

    public LiveData<Result<T>> getLiveData() {
        return this.mLiveData;
    }

    @Override // androidx.camera.core.impl.Observable
    public ListenableFuture<T> fetchData() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.impl.LiveDataObservable$$ExternalSyntheticLambda5
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m187lambda$fetchData$1$androidxcameracoreimplLiveDataObservable(completer);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$fetchData$1$androidx-camera-core-impl-LiveDataObservable, reason: not valid java name */
    /* synthetic */ Object m187lambda$fetchData$1$androidxcameracoreimplLiveDataObservable(final CallbackToFutureAdapter.Completer completer) throws Exception {
        CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.impl.LiveDataObservable$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m186lambda$fetchData$0$androidxcameracoreimplLiveDataObservable(completer);
            }
        });
        return this + " [fetch@" + SystemClock.uptimeMillis() + "]";
    }

    /* JADX INFO: renamed from: lambda$fetchData$0$androidx-camera-core-impl-LiveDataObservable, reason: not valid java name */
    /* synthetic */ void m186lambda$fetchData$0$androidxcameracoreimplLiveDataObservable(CallbackToFutureAdapter.Completer completer) {
        Result<T> value = this.mLiveData.getValue();
        if (value == null) {
            completer.setException(new IllegalStateException("Observable has not yet been initialized with a value."));
        } else if (value.completedSuccessfully()) {
            completer.set(value.getValue());
        } else {
            Preconditions.checkNotNull(value.getError());
            completer.setException(value.getError());
        }
    }

    @Override // androidx.camera.core.impl.Observable
    public void addObserver(Executor executor, final Observable.Observer<? super T> observer) {
        synchronized (this.mObservers) {
            boolean zIsEmpty = this.mObservers.isEmpty();
            this.mObservers.put(observer, executor);
            if (zIsEmpty) {
                enableInternalObserver();
            } else {
                executor.execute(new Runnable() { // from class: androidx.camera.core.impl.LiveDataObservable$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m182xb97b3ef8(observer);
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: lambda$addObserver$2$androidx-camera-core-impl-LiveDataObservable, reason: not valid java name */
    /* synthetic */ void m182xb97b3ef8(Observable.Observer observer) {
        Result<T> value = this.mLiveData.getValue();
        if (value == null) {
            return;
        }
        if (value.completedSuccessfully()) {
            observer.onNewData(value.getValue());
        } else {
            Preconditions.checkNotNull(value.getError());
            observer.onError(value.getError());
        }
    }

    @Override // androidx.camera.core.impl.Observable
    public void removeObserver(Observable.Observer<? super T> observer) {
        synchronized (this.mObservers) {
            this.mObservers.remove(observer);
            if (this.mObservers.isEmpty()) {
                disableInternalObserver();
            }
        }
    }

    private void enableInternalObserver() {
        CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.impl.LiveDataObservable$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m185x649dea1c();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$enableInternalObserver$5$androidx-camera-core-impl-LiveDataObservable, reason: not valid java name */
    /* synthetic */ void m185x649dea1c() {
        if (this.mLiveDataObserver == null) {
            this.mLiveDataObserver = new Observer() { // from class: androidx.camera.core.impl.LiveDataObservable$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.m184xab265c7d((LiveDataObservable.Result) obj);
                }
            };
        }
        this.mLiveData.observeForever(this.mLiveDataObserver);
    }

    /* JADX INFO: renamed from: lambda$enableInternalObserver$4$androidx-camera-core-impl-LiveDataObservable, reason: not valid java name */
    /* synthetic */ void m184xab265c7d(final Result result) {
        HashMap map;
        synchronized (this.mObservers) {
            map = new HashMap(this.mObservers);
        }
        for (final Map.Entry entry : map.entrySet()) {
            ((Executor) entry.getValue()).execute(new Runnable() { // from class: androidx.camera.core.impl.LiveDataObservable$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    LiveDataObservable.lambda$enableInternalObserver$3(entry, result);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void lambda$enableInternalObserver$3(Map.Entry entry, Result result) {
        Observable.Observer observer = (Observable.Observer) entry.getKey();
        if (result.completedSuccessfully()) {
            observer.onNewData(result.getValue());
        } else {
            Preconditions.checkNotNull(result.getError());
            observer.onError(result.getError());
        }
    }

    private void disableInternalObserver() {
        CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.impl.LiveDataObservable$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m183xd8d57c50();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$disableInternalObserver$6$androidx-camera-core-impl-LiveDataObservable, reason: not valid java name */
    /* synthetic */ void m183xd8d57c50() {
        Observer<Result<T>> observer = this.mLiveDataObserver;
        if (observer != null) {
            this.mLiveData.removeObserver(observer);
        }
    }

    public static final class Result<T> {
        private final Throwable mError;
        private final T mValue;

        private Result(T t, Throwable th) {
            this.mValue = t;
            this.mError = th;
        }

        static <T> Result<T> fromValue(T t) {
            return new Result<>(t, null);
        }

        static <T> Result<T> fromError(Throwable th) {
            return new Result<>(null, (Throwable) Preconditions.checkNotNull(th));
        }

        public boolean completedSuccessfully() {
            return this.mError == null;
        }

        public T getValue() {
            if (!completedSuccessfully()) {
                throw new IllegalStateException("Result contains an error. Does not contain a value.");
            }
            return this.mValue;
        }

        public Throwable getError() {
            return this.mError;
        }

        public String toString() {
            return "[Result: <" + (completedSuccessfully() ? "Value: " + this.mValue : "Error: " + this.mError) + ">]";
        }
    }
}
