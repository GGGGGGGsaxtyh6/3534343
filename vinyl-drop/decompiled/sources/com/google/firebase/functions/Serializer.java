package com.google.firebase.functions;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.model.Values;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Serializer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/google/firebase/functions/Serializer;", "", "<init>", "()V", "dateFormat", "Ljava/text/DateFormat;", "encode", "obj", "decode", "Companion", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class Serializer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String LONG_TYPE = "type.googleapis.com/google.protobuf.Int64Value";
    public static final String UNSIGNED_LONG_TYPE = "type.googleapis.com/google.protobuf.UInt64Value";
    private final DateFormat dateFormat;

    public Serializer() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        this.dateFormat = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public final Object encode(Object obj) {
        boolean z;
        boolean z2;
        if (obj == null || obj == JSONObject.NULL) {
            Object NULL = JSONObject.NULL;
            Intrinsics.checkNotNullExpressionValue(NULL, "NULL");
            return NULL;
        }
        if (obj instanceof Long) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("@type", LONG_TYPE);
                jSONObject.put(Values.VECTOR_MAP_VECTORS_KEY, String.valueOf(((Number) obj).longValue()));
                return jSONObject;
            } catch (JSONException e) {
                throw new RuntimeException("Error encoding Long.", e);
            }
        }
        if ((obj instanceof Number) || (obj instanceof String) || (obj instanceof Boolean) || ((z = obj instanceof JSONObject)) || ((z2 = obj instanceof JSONArray))) {
            return obj;
        }
        if (obj instanceof Map) {
            JSONObject jSONObject2 = new JSONObject();
            Map map = (Map) obj;
            for (Object obj2 : map.keySet()) {
                if (!(obj2 instanceof String)) {
                    throw new IllegalArgumentException("Object keys must be strings.".toString());
                }
                try {
                    jSONObject2.put((String) obj2, encode(map.get(obj2)));
                } catch (JSONException e2) {
                    throw new RuntimeException(e2);
                }
            }
            return jSONObject2;
        }
        if (obj instanceof List) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                jSONArray.put(encode(it.next()));
            }
            return jSONArray;
        }
        if (!z) {
            if (z2) {
                JSONArray jSONArray2 = new JSONArray();
                JSONArray jSONArray3 = (JSONArray) obj;
                int length = jSONArray3.length();
                for (int i = 0; i < length; i++) {
                    jSONArray2.put(encode(jSONArray3.opt(i)));
                }
                return jSONArray2;
            }
            throw new IllegalArgumentException("Object cannot be encoded in JSON: " + obj);
        }
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = (JSONObject) obj;
        Iterator<String> itKeys = jSONObject4.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (next == null) {
                throw new IllegalArgumentException("Object keys cannot be null.");
            }
            try {
                jSONObject3.put(next, encode(jSONObject4.opt(next)));
            } catch (JSONException e3) {
                throw new RuntimeException(e3);
            }
        }
        return jSONObject3;
    }

    public final Object decode(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Number) || (obj instanceof String) || (obj instanceof Boolean)) {
            return obj;
        }
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject.has("@type")) {
                String strOptString = jSONObject.optString("@type");
                String strOptString2 = jSONObject.optString(Values.VECTOR_MAP_VECTORS_KEY);
                if (Intrinsics.areEqual(strOptString, LONG_TYPE)) {
                    try {
                        Intrinsics.checkNotNull(strOptString2);
                        return Long.valueOf(Long.parseLong(strOptString2));
                    } catch (NumberFormatException unused) {
                        throw new IllegalArgumentException("Invalid Long format:" + strOptString2);
                    }
                }
                if (Intrinsics.areEqual(strOptString, UNSIGNED_LONG_TYPE)) {
                    try {
                        Intrinsics.checkNotNull(strOptString2);
                        return Long.valueOf(Long.parseLong(strOptString2));
                    } catch (NumberFormatException unused2) {
                        throw new IllegalArgumentException("Invalid Long format:" + strOptString2);
                    }
                }
            }
            HashMap map = new HashMap();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, decode(jSONObject.opt(next)));
            }
            return map;
        }
        if (obj instanceof JSONArray) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                arrayList.add(decode(jSONArray.opt(i)));
            }
            return arrayList;
        }
        if (obj == JSONObject.NULL) {
            return null;
        }
        throw new IllegalArgumentException("Object cannot be decoded from JSON: " + obj);
    }

    /* JADX INFO: compiled from: Serializer.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u00020\u00058\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u0016\u0010\u0007\u001a\u00020\u00058\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003¨\u0006\t"}, d2 = {"Lcom/google/firebase/functions/Serializer$Companion;", "", "<init>", "()V", "LONG_TYPE", "", "getLONG_TYPE$com_google_firebase_firebase_functions$annotations", "UNSIGNED_LONG_TYPE", "getUNSIGNED_LONG_TYPE$com_google_firebase_firebase_functions$annotations", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getLONG_TYPE$com_google_firebase_firebase_functions$annotations() {
        }

        public static /* synthetic */ void getUNSIGNED_LONG_TYPE$com_google_firebase_firebase_functions$annotations() {
        }

        private Companion() {
        }
    }
}
