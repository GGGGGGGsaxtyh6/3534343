package com.google.firebase.firestore;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class UserDataWriter {
    private final FirebaseFirestore firestore;
    private final DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior;

    public UserDataWriter(FirebaseFirestore firebaseFirestore, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        this.firestore = firebaseFirestore;
        this.serverTimestampBehavior = serverTimestampBehavior;
    }

    public Object convertValue(Value value) {
        switch (Values.typeOrder(value)) {
            case 0:
                return null;
            case 1:
            case 9:
            case 11:
            case 12:
            case 14:
            default:
                throw Assert.fail("Unknown value type: " + value.getValueTypeCase(), new Object[0]);
            case 2:
                return Boolean.valueOf(value.getBooleanValue());
            case 3:
            case 4:
                if (value.hasIntegerValue()) {
                    return Long.valueOf(value.getIntegerValue());
                }
                return Double.valueOf(value.getDoubleValue());
            case 5:
                return convertTimestamp(value.getTimestampValue());
            case 6:
                return convertServerTimestamp(value);
            case 7:
                return value.getStringValue();
            case 8:
                return Blob.fromByteString(value.getBytesValue());
            case 10:
                return convertReference(value);
            case 13:
                return new GeoPoint(value.getGeoPointValue().getLatitude(), value.getGeoPointValue().getLongitude());
            case 15:
                return convertArray(value.getArrayValue());
            case 16:
                return convertVectorValue(value.getMapValue().getFieldsMap());
            case 17:
                return convertObject(value.getMapValue().getFieldsMap());
        }
    }

    Map<String, Object> convertObject(Map<String, Value> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Value> entry : map.entrySet()) {
            map2.put(entry.getKey(), convertValue(entry.getValue()));
        }
        return map2;
    }

    VectorValue convertVectorValue(Map<String, Value> map) {
        List<Value> valuesList = map.get(Values.VECTOR_MAP_VECTORS_KEY).getArrayValue().getValuesList();
        double[] dArr = new double[valuesList.size()];
        for (int i = 0; i < valuesList.size(); i++) {
            dArr[i] = valuesList.get(i).getDoubleValue();
        }
        return new VectorValue(dArr);
    }

    /* JADX INFO: renamed from: com.google.firebase.firestore.UserDataWriter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$DocumentSnapshot$ServerTimestampBehavior;

        static {
            int[] iArr = new int[DocumentSnapshot.ServerTimestampBehavior.values().length];
            $SwitchMap$com$google$firebase$firestore$DocumentSnapshot$ServerTimestampBehavior = iArr;
            try {
                iArr[DocumentSnapshot.ServerTimestampBehavior.PREVIOUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$DocumentSnapshot$ServerTimestampBehavior[DocumentSnapshot.ServerTimestampBehavior.ESTIMATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private Object convertServerTimestamp(Value value) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$DocumentSnapshot$ServerTimestampBehavior[this.serverTimestampBehavior.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return null;
            }
            return convertTimestamp(ServerTimestamps.getLocalWriteTime(value));
        }
        Value previousValue = ServerTimestamps.getPreviousValue(value);
        if (previousValue == null) {
            return null;
        }
        return convertValue(previousValue);
    }

    private Object convertTimestamp(Timestamp timestamp) {
        return new com.google.firebase.Timestamp(timestamp.getSeconds(), timestamp.getNanos());
    }

    private List<Object> convertArray(ArrayValue arrayValue) {
        ArrayList arrayList = new ArrayList(arrayValue.getValuesCount());
        Iterator<Value> it = arrayValue.getValuesList().iterator();
        while (it.hasNext()) {
            arrayList.add(convertValue(it.next()));
        }
        return arrayList;
    }

    private Object convertReference(Value value) {
        DatabaseId databaseIdFromName = DatabaseId.fromName(value.getReferenceValue());
        DocumentKey documentKeyFromName = DocumentKey.fromName(value.getReferenceValue());
        DatabaseId databaseId = this.firestore.getDatabaseId();
        if (!databaseIdFromName.equals(databaseId)) {
            Logger.warn("DocumentSnapshot", "Document %s contains a document reference within a different database (%s/%s) which is not supported. It will be treated as a reference in the current database (%s/%s) instead.", documentKeyFromName.getPath(), databaseIdFromName.getProjectId(), databaseIdFromName.getDatabaseId(), databaseId.getProjectId(), databaseId.getDatabaseId());
        }
        return new DocumentReference(documentKeyFromName, this.firestore);
    }
}
