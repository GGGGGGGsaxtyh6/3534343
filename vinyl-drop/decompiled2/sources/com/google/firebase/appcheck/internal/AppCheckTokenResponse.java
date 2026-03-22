package com.google.firebase.appcheck.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.FirebaseException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class AppCheckTokenResponse {
    static final String TIME_TO_LIVE_KEY = "ttl";
    static final String TOKEN_KEY = "token";
    private String timeToLive;
    private String token;

    public static AppCheckTokenResponse fromJsonString(String str) throws FirebaseException, JSONException {
        JSONObject jSONObject = new JSONObject(str);
        String strEmptyToNull = Strings.emptyToNull(jSONObject.optString(TOKEN_KEY));
        String strEmptyToNull2 = Strings.emptyToNull(jSONObject.optString(TIME_TO_LIVE_KEY));
        if (strEmptyToNull == null || strEmptyToNull2 == null) {
            throw new FirebaseException("Unexpected server response.");
        }
        return new AppCheckTokenResponse(strEmptyToNull, strEmptyToNull2);
    }

    private AppCheckTokenResponse(String str, String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        this.token = str;
        this.timeToLive = str2;
    }

    public String getToken() {
        return this.token;
    }

    public String getTimeToLive() {
        return this.timeToLive;
    }
}
