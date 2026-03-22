package androidx.camera.core.impl;

import android.util.Log;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.impl.Observable;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractCameraPresenceSource implements Observable<List<CameraIdentifier>> {
    private static final String TAG = "CameraPresenceSrc";
    private List<CameraIdentifier> mCurrentData;
    private Throwable mCurrentError;
    private boolean mIsActive;
    private final Object mLock;
    private final List<ObserverWrapper> mObservers;

    @Override // androidx.camera.core.impl.Observable
    public abstract ListenableFuture<List<CameraIdentifier>> fetchData();

    protected abstract void startMonitoring();

    protected abstract void stopMonitoring();

    public AbstractCameraPresenceSource() {
        this(Collections.emptyList());
    }

    public AbstractCameraPresenceSource(List<String> list) {
        this.mLock = new Object();
        this.mObservers = new CopyOnWriteArrayList();
        this.mCurrentError = null;
        this.mIsActive = false;
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(CameraIdentifier.create(it.next()));
        }
        this.mCurrentData = arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class ObserverWrapper {
        final Executor mExecutor;
        final Observable.Observer<? super List<CameraIdentifier>> mObserver;

        ObserverWrapper(Executor executor, Observable.Observer<? super List<CameraIdentifier>> observer) {
            this.mExecutor = executor;
            this.mObserver = observer;
        }
    }

    protected void updateData(List<CameraIdentifier> list) {
        updateState(list, null);
    }

    protected void updateError(Throwable th) {
        updateState(null, th);
    }

    private void updateState(List<CameraIdentifier> list, Throwable th) {
        boolean z;
        List<CameraIdentifier> listUnmodifiableList;
        Throwable th2;
        synchronized (this.mLock) {
            if (th != null) {
                z = this.mCurrentError == null || !this.mCurrentData.isEmpty();
                this.mCurrentError = th;
                this.mCurrentData = Collections.emptyList();
            } else {
                Preconditions.checkNotNull(list);
                boolean z2 = (this.mCurrentError == null && this.mCurrentData.equals(list)) ? false : true;
                this.mCurrentError = null;
                this.mCurrentData = list;
                z = z2;
            }
            listUnmodifiableList = Collections.unmodifiableList(this.mCurrentData);
            th2 = this.mCurrentError;
        }
        if (z) {
            Log.d(TAG, "Data changed. Notifying " + this.mObservers.size() + " observers. Error: " + (th2 != null));
            Iterator<ObserverWrapper> it = this.mObservers.iterator();
            while (it.hasNext()) {
                notifyObserver(it.next(), listUnmodifiableList, th2);
            }
        }
    }

    private void notifyObserver(final ObserverWrapper observerWrapper, final List<CameraIdentifier> list, final Throwable th) {
        observerWrapper.mExecutor.execute(new Runnable() { // from class: androidx.camera.core.impl.AbstractCameraPresenceSource$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AbstractCameraPresenceSource.lambda$notifyObserver$0(th, observerWrapper, list);
            }
        });
    }

    static /* synthetic */ void lambda$notifyObserver$0(Throwable th, ObserverWrapper observerWrapper, List list) {
        if (th != null) {
            observerWrapper.mObserver.onError(th);
        } else {
            observerWrapper.mObserver.onNewData(list);
        }
    }

    @Override // androidx.camera.core.impl.Observable
    public void addObserver(Executor executor, Observable.Observer<? super List<CameraIdentifier>> observer) {
        List<CameraIdentifier> listUnmodifiableList;
        Throwable th;
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(observer);
        this.mObservers.add(new ObserverWrapper(executor, observer));
        synchronized (this.mLock) {
            if (!this.mIsActive && !this.mObservers.isEmpty()) {
                Log.i(TAG, "First observer added. Starting monitoring.");
                this.mIsActive = true;
                startMonitoring();
            }
            listUnmodifiableList = Collections.unmodifiableList(this.mCurrentData);
            th = this.mCurrentError;
        }
        notifyObserver(new ObserverWrapper(executor, observer), listUnmodifiableList, th);
    }

    @Override // androidx.camera.core.impl.Observable
    public void removeObserver(Observable.Observer<? super List<CameraIdentifier>> observer) {
        ObserverWrapper next;
        Preconditions.checkNotNull(observer);
        Iterator<ObserverWrapper> it = this.mObservers.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.mObserver.equals(observer)) {
                    break;
                }
            }
        }
        if (next != null) {
            this.mObservers.remove(next);
        }
        synchronized (this.mLock) {
            if (this.mIsActive && this.mObservers.isEmpty()) {
                Log.i(TAG, "Last observer removed. Stopping monitoring.");
                this.mIsActive = false;
                stopMonitoring();
            }
        }
    }
}
