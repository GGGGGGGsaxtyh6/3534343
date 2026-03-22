package com.google.firebase.appcheck.internal;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class HttpErrorResponse {
    static final String CODE_KEY = "code";
    static final String ERROR_KEY = "error";
    static final String MESSAGE_KEY = "message";
    private int errorCode;
    private String errorMessage;

    public static HttpErrorResponse fromJsonString(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(new JSONObject(str).optString(ERROR_KEY));
        return new HttpErrorResponse(jSONObject.optInt(CODE_KEY), jSONObject.optString(MESSAGE_KEY));
    }

    private HttpErrorResponse(int i, String str) {
        this.errorCode = i;
        this.errorMessage = str;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
