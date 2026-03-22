package com.google.firebase.firestore;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.Value;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Pipeline.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001BC\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eB!\b\u0010\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\r\u0010\u0015J\b\u0010\u001b\u001a\u0004\u0018\u00010\bJ\u0014\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u0004\u0018\u00010\u00012\u0006\u0010!\u001a\u00020\bJ\u0010\u0010 \u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010\"\u001a\u00020\bH\u0016J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010&\u001a\u00020'H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lcom/google/firebase/firestore/PipelineResult;", "", "userDataWriter", "Lcom/google/firebase/firestore/UserDataWriter;", "ref", "Lcom/google/firebase/firestore/DocumentReference;", "fields", "", "", "Lcom/google/firestore/v1/Value;", "createTime", "Lcom/google/firebase/Timestamp;", "updateTime", "<init>", "(Lcom/google/firebase/firestore/UserDataWriter;Lcom/google/firebase/firestore/DocumentReference;Ljava/util/Map;Lcom/google/firebase/Timestamp;Lcom/google/firebase/Timestamp;)V", "document", "Lcom/google/firebase/firestore/model/Document;", "serverTimestampBehavior", "Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/google/firebase/firestore/model/Document;Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "getCreateTime", "()Lcom/google/firebase/Timestamp;", "getUpdateTime", "getRef", "()Lcom/google/firebase/firestore/DocumentReference;", "getId", "getData", "extractNestedValue", "fieldPath", "Lcom/google/firebase/firestore/FieldPath;", "get", "field", "toString", "equals", "", "other", "hashCode", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PipelineResult {
    private final Timestamp createTime;
    private final Map<String, Value> fields;
    private final DocumentReference ref;
    private final Timestamp updateTime;
    private final UserDataWriter userDataWriter;

    public PipelineResult(UserDataWriter userDataWriter, DocumentReference documentReference, Map<String, Value> fields, Timestamp timestamp, Timestamp timestamp2) {
        Intrinsics.checkNotNullParameter(userDataWriter, "userDataWriter");
        Intrinsics.checkNotNullParameter(fields, "fields");
        this.userDataWriter = userDataWriter;
        this.fields = fields;
        this.createTime = timestamp;
        this.updateTime = timestamp2;
        this.ref = documentReference;
    }

    public PipelineResult(Document document, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior, FirebaseFirestore firestore) {
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(serverTimestampBehavior, "serverTimestampBehavior");
        Intrinsics.checkNotNullParameter(firestore, "firestore");
        UserDataWriter userDataWriter = new UserDataWriter(firestore, serverTimestampBehavior);
        DocumentReference documentReference = new DocumentReference(document.getKey(), firestore);
        Map<String, Value> fieldsMap = document.getData().getFieldsMap();
        Intrinsics.checkNotNullExpressionValue(fieldsMap, "getFieldsMap(...)");
        SnapshotVersion createTime = document.getCreateTime();
        this(userDataWriter, documentReference, fieldsMap, createTime != null ? createTime.getTimestamp() : null, document.getVersion().getTimestamp());
    }

    public final Timestamp getCreateTime() {
        return this.createTime;
    }

    public final Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public final DocumentReference getRef() {
        return this.ref;
    }

    public final String getId() {
        DocumentReference documentReference = this.ref;
        if (documentReference != null) {
            return documentReference.getId();
        }
        return null;
    }

    public final Map<String, Object> getData() {
        Map<String, Object> mapConvertObject = this.userDataWriter.convertObject(this.fields);
        Intrinsics.checkNotNullExpressionValue(mapConvertObject, "convertObject(...)");
        return mapConvertObject;
    }

    private final Value extractNestedValue(FieldPath fieldPath) {
        Iterator<String> it = fieldPath.getInternalPath().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        if (!it.hasNext()) {
            return Values.encodeValue(this.fields);
        }
        String next = it.next();
        if (!this.fields.containsKey(next)) {
            return null;
        }
        Value fieldsOrDefault = this.fields.get(next);
        while (it.hasNext()) {
            String next2 = it.next();
            if (fieldsOrDefault == null || !fieldsOrDefault.hasMapValue()) {
                return null;
            }
            fieldsOrDefault = fieldsOrDefault.getMapValue().getFieldsOrDefault(next2, null);
        }
        return fieldsOrDefault;
    }

    public final Object get(String field) {
        Intrinsics.checkNotNullParameter(field, "field");
        FieldPath fieldPathFromDotSeparatedPath = FieldPath.fromDotSeparatedPath(field);
        Intrinsics.checkNotNullExpressionValue(fieldPathFromDotSeparatedPath, "fromDotSeparatedPath(...)");
        return get(fieldPathFromDotSeparatedPath);
    }

    public final Object get(FieldPath fieldPath) {
        Intrinsics.checkNotNullParameter(fieldPath, "fieldPath");
        return this.userDataWriter.convertValue(extractNestedValue(fieldPath));
    }

    public String toString() {
        return "PipelineResult{ref=" + this.ref + ", updateTime=" + this.updateTime + "}, data=" + getData();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.google.firebase.firestore.PipelineResult");
        PipelineResult pipelineResult = (PipelineResult) other;
        return Intrinsics.areEqual(this.ref, pipelineResult.ref) && Intrinsics.areEqual(this.fields, pipelineResult.fields);
    }

    public int hashCode() {
        DocumentReference documentReference = this.ref;
        return ((documentReference != null ? documentReference.hashCode() : 0) * 31) + this.fields.hashCode();
    }
}
