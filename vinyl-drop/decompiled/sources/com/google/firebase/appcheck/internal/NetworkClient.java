package com.google.firebase.appcheck.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.inject.Provider;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class NetworkClient {
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";
    public static final int DEBUG = 2;
    private static final String DEBUG_EXCHANGE_URL_TEMPLATE = "https://firebaseappcheck.googleapis.com/v1/projects/%s/apps/%s:exchangeDebugToken?key=%s";
    public static final int PLAY_INTEGRITY = 3;
    private static final String PLAY_INTEGRITY_CHALLENGE_URL_TEMPLATE = "https://firebaseappcheck.googleapis.com/v1/projects/%s/apps/%s:generatePlayIntegrityChallenge?key=%s";
    private static final String PLAY_INTEGRITY_EXCHANGE_URL_TEMPLATE = "https://firebaseappcheck.googleapis.com/v1/projects/%s/apps/%s:exchangePlayIntegrityToken?key=%s";
    public static final int RECAPTCHA_ENTERPRISE = 4;
    private static final String RECAPTCHA_ENTERPRISE_URL_TEMPLATE = "https://firebaseappcheck.googleapis.com/v1/projects/%s/apps/%s:exchangeRecaptchaEnterpriseToken?key=%s";
    private static final String TAG = "com.google.firebase.appcheck.internal.NetworkClient";
    public static final int UNKNOWN = 0;
    static final String X_ANDROID_CERT = "X-Android-Cert";
    static final String X_ANDROID_PACKAGE = "X-Android-Package";
    static final String X_FIREBASE_CLIENT = "X-Firebase-Client";
    private final String apiKey;
    private final String appId;
    private final Context context;
    private final Provider<HeartBeatController> heartBeatControllerProvider;
    private final String projectId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AttestationTokenType {
    }

    private static boolean isResponseSuccess(int i) {
        return i >= 200 && i < 300;
    }

    public NetworkClient(FirebaseApp firebaseApp) {
        this(firebaseApp.getApplicationContext(), firebaseApp.getOptions(), ((DefaultFirebaseAppCheck) FirebaseAppCheck.getInstance(firebaseApp)).getHeartbeatControllerProvider());
    }

    NetworkClient(Context context, FirebaseOptions firebaseOptions, Provider<HeartBeatController> provider) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(firebaseOptions);
        Preconditions.checkNotNull(provider);
        this.context = context;
        this.apiKey = firebaseOptions.getApiKey();
        this.appId = firebaseOptions.getApplicationId();
        String projectId = firebaseOptions.getProjectId();
        this.projectId = projectId;
        if (projectId == null) {
            throw new IllegalArgumentException("FirebaseOptions#getProjectId cannot be null.");
        }
        this.heartBeatControllerProvider = provider;
    }

    public AppCheckTokenResponse exchangeAttestationForAppCheckToken(byte[] bArr, int i, RetryManager retryManager) throws FirebaseException, JSONException, IOException {
        if (!retryManager.canRetry()) {
            throw new FirebaseException("Too many attempts.");
        }
        return AppCheckTokenResponse.fromJsonString(makeNetworkRequest(new URL(String.format(getUrlTemplate(i), this.projectId, this.appId, this.apiKey)), bArr, retryManager, true));
    }

    public String generatePlayIntegrityChallenge(byte[] bArr, RetryManager retryManager) throws FirebaseException, JSONException, IOException {
        if (!retryManager.canRetry()) {
            throw new FirebaseException("Too many attempts.");
        }
        return makeNetworkRequest(new URL(String.format(PLAY_INTEGRITY_CHALLENGE_URL_TEMPLATE, this.projectId, this.appId, this.apiKey)), bArr, retryManager, false);
    }

    private String makeNetworkRequest(URL url, byte[] bArr, RetryManager retryManager, boolean z) throws FirebaseException, JSONException, IOException {
        InputStream errorStream;
        HttpURLConnection httpURLConnectionCreateHttpUrlConnection = createHttpUrlConnection(url);
        try {
            httpURLConnectionCreateHttpUrlConnection.setDoOutput(true);
            httpURLConnectionCreateHttpUrlConnection.setFixedLengthStreamingMode(bArr.length);
            httpURLConnectionCreateHttpUrlConnection.setRequestProperty("Content-Type", APPLICATION_JSON);
            String heartBeat = getHeartBeat();
            if (heartBeat != null) {
                httpURLConnectionCreateHttpUrlConnection.setRequestProperty(X_FIREBASE_CLIENT, heartBeat);
            }
            httpURLConnectionCreateHttpUrlConnection.setRequestProperty(X_ANDROID_PACKAGE, this.context.getPackageName());
            httpURLConnectionCreateHttpUrlConnection.setRequestProperty(X_ANDROID_CERT, getFingerprintHashForPackage());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnectionCreateHttpUrlConnection.getOutputStream(), bArr.length);
            try {
                bufferedOutputStream.write(bArr, 0, bArr.length);
                bufferedOutputStream.close();
                int responseCode = httpURLConnectionCreateHttpUrlConnection.getResponseCode();
                if (isResponseSuccess(responseCode)) {
                    errorStream = httpURLConnectionCreateHttpUrlConnection.getInputStream();
                } else {
                    errorStream = httpURLConnectionCreateHttpUrlConnection.getErrorStream();
                }
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, StandardCharsets.UTF_8));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    } finally {
                    }
                }
                bufferedReader.close();
                String string = sb.toString();
                if (isResponseSuccess(responseCode)) {
                    if (z) {
                        retryManager.resetBackoffOnSuccess();
                    }
                    return string;
                }
                retryManager.updateBackoffOnFailure(responseCode);
                HttpErrorResponse httpErrorResponseFromJsonString = HttpErrorResponse.fromJsonString(string);
                throw new FirebaseException("Error returned from API. code: " + httpErrorResponseFromJsonString.getErrorCode() + " body: " + httpErrorResponseFromJsonString.getErrorMessage());
            } finally {
            }
        } finally {
            httpURLConnectionCreateHttpUrlConnection.disconnect();
        }
    }

    String getHeartBeat() {
        HeartBeatController heartBeatController = this.heartBeatControllerProvider.get();
        if (heartBeatController != null) {
            try {
                return (String) Tasks.await(heartBeatController.getHeartBeatsHeader());
            } catch (Exception unused) {
                Log.w(TAG, "Unable to get heartbeats!");
            }
        }
        return null;
    }

    private String getFingerprintHashForPackage() {
        try {
            Context context = this.context;
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context, context.getPackageName());
            if (packageCertificateHashBytes == null) {
                Log.e(TAG, "Could not get fingerprint hash for package: " + this.context.getPackageName());
                return null;
            }
            return Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "No such package: " + this.context.getPackageName(), e);
            return null;
        }
    }

    private static String getUrlTemplate(int i) {
        if (i == 2) {
            return DEBUG_EXCHANGE_URL_TEMPLATE;
        }
        if (i == 3) {
            return PLAY_INTEGRITY_EXCHANGE_URL_TEMPLATE;
        }
        if (i == 4) {
            return RECAPTCHA_ENTERPRISE_URL_TEMPLATE;
        }
        throw new IllegalArgumentException("Unknown token type.");
    }

    HttpURLConnection createHttpUrlConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}
