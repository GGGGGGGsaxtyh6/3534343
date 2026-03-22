package com.google.firebase.appcheck.internal.util;

import android.text.TextUtils;
import android.util.Base64;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class TokenParser {
    public static Map<String, Object> parseTokenClaims(String str) {
        Preconditions.checkNotEmpty(str);
        String[] strArrSplit = str.split("\\.", -1);
        if (strArrSplit.length < 2) {
            Logger.getLogger().e("Invalid token (too few subsections):\n" + str);
            return Collections.emptyMap();
        }
        try {
            Map<String, Object> jsonIntoMap = parseJsonIntoMap(new String(Base64.decode(strArrSplit[1], 11), "UTF-8"));
            return jsonIntoMap == null ? Collections.emptyMap() : jsonIntoMap;
        } catch (UnsupportedEncodingException e) {
            Logger.getLogger().e("Unable to decode token (charset unknown):\n" + e);
            return Collections.emptyMap();
        }
    }

    private static Map<String, Object> parseJsonIntoMap(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != JSONObject.NULL) {
                return toMap(jSONObject);
            }
            return null;
        } catch (Exception e) {
            Logger.getLogger().d("Failed to parse JSONObject into Map:\n" + e);
            return Collections.emptyMap();
        }
    }

    private static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        ArrayMap arrayMap = new ArrayMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object map = jSONObject.get(next);
            if (map instanceof JSONArray) {
                map = toList((JSONArray) map);
            } else if (map instanceof JSONObject) {
                map = toMap((JSONObject) map);
            } else if (map.equals(JSONObject.NULL)) {
                map = null;
            }
            arrayMap.put(next, map);
        }
        return arrayMap;
    }

    private static List<Object> toList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object map = jSONArray.get(i);
            if (map instanceof JSONArray) {
                map = toList((JSONArray) map);
            } else if (map instanceof JSONObject) {
                map = toMap((JSONObject) map);
            }
            arrayList.add(map);
        }
        return arrayList;
    }
}
