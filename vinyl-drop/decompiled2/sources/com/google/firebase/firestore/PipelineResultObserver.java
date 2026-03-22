package com.google.firebase.firestore;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firestore.v1.Value;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: Pipeline.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000bH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&¨\u0006\u0012"}, d2 = {"Lcom/google/firebase/firestore/PipelineResultObserver;", "", "onDocument", "", "key", "Lcom/google/firebase/firestore/model/DocumentKey;", "data", "", "", "Lcom/google/firestore/v1/Value;", "createTime", "Lcom/google/firebase/Timestamp;", "updateTime", "onComplete", "executionTime", "onError", "exception", "Lcom/google/firebase/firestore/FirebaseFirestoreException;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public interface PipelineResultObserver {
    void onComplete(Timestamp executionTime);

    void onDocument(DocumentKey key, Map<String, Value> data, Timestamp createTime, Timestamp updateTime);

    void onError(FirebaseFirestoreException exception);
}
