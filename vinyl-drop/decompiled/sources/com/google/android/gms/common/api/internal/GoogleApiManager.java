package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
public class GoogleApiManager implements Handler.Callback {
    private static GoogleApiManager zah;
    private TelemetryData zaf;
    private TelemetryLoggingClient zag;
    private final Context zaj;
    private final GoogleApiAvailability zak;
    private final com.google.android.gms.common.internal.zao zal;
    private final Handler zas;
    private volatile boolean zat;
    public static final Status zaa = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zab = new Status(4, "The user must be signed in to make this API call.");
    private static final Object zae = new Object();
    private static volatile boolean zai = false;
    private long zac = 10000;
    private boolean zad = false;
    private final AtomicInteger zam = new AtomicInteger(1);
    private final AtomicInteger zan = new AtomicInteger(0);
    private final Map zao = new ConcurrentHashMap(5, 0.75f, 1);
    private zaab zap = null;
    private final Set zaq = new ArraySet();
    private final Set zar = new ArraySet();

    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zat = true;
        this.zaj = context;
        com.google.android.gms.internal.base.zao zaoVar = new com.google.android.gms.internal.base.zao(looper, this);
        this.zas = zaoVar;
        this.zak = googleApiAvailability;
        this.zal = new com.google.android.gms.common.internal.zao(googleApiAvailability);
        if (DeviceProperties.isAuto(context)) {
            this.zat = false;
        }
        zaoVar.sendMessage(zaoVar.obtainMessage(6));
    }

    public static void reportSignOut() {
        synchronized (zae) {
            GoogleApiManager googleApiManager = zah;
            if (googleApiManager != null) {
                googleApiManager.zan.incrementAndGet();
                Handler handler = googleApiManager.zas;
                handler.sendMessageAtFrontOfQueue(handler.obtainMessage(10));
            }
        }
    }

    private final zabk zaH(GoogleApi googleApi) {
        Map map = this.zao;
        ApiKey apiKey = googleApi.getApiKey();
        zabk zabkVar = (zabk) map.get(apiKey);
        if (zabkVar == null) {
            zabkVar = new zabk(this, googleApi);
            map.put(apiKey, zabkVar);
        }
        if (zabkVar.zap()) {
            this.zar.add(apiKey);
        }
        zabkVar.zam();
        return zabkVar;
    }

    private final void zaI(TaskCompletionSource taskCompletionSource, int i, GoogleApi googleApi) {
        zaby zabyVarZaa;
        if (i == 0 || (zabyVarZaa = zaby.zaa(this, i, googleApi.getApiKey())) == null) {
            return;
        }
        Task task = taskCompletionSource.getTask();
        final Handler handler = this.zas;
        Objects.requireNonNull(handler);
        task.addOnCompleteListener(new Executor() { // from class: com.google.android.gms.common.api.internal.zabp
            @Override // java.util.concurrent.Executor
            public final /* synthetic */ void execute(Runnable runnable) {
                handler.post(runnable);
            }
        }, zabyVarZaa);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status zaJ(ApiKey apiKey, ConnectionResult connectionResult) {
        String strZaa = apiKey.zaa();
        String strValueOf = String.valueOf(connectionResult);
        StringBuilder sb = new StringBuilder(String.valueOf(strZaa).length() + 63 + String.valueOf(strValueOf).length());
        sb.append("API: ");
        sb.append(strZaa);
        sb.append(" is not available on this device. Connection failed with: ");
        sb.append(strValueOf);
        return new Status(connectionResult, sb.toString());
    }

    private final void zaK() {
        TelemetryData telemetryData = this.zaf;
        if (telemetryData != null) {
            if (telemetryData.zaa() > 0 || zam()) {
                zaL().log(telemetryData);
            }
            this.zaf = null;
        }
    }

    private final TelemetryLoggingClient zaL() {
        if (this.zag == null) {
            this.zag = TelemetryLogging.getClient(this.zaj);
        }
        return this.zag;
    }

    public static GoogleApiManager zaa(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (zae) {
            if (zah == null) {
                zah = new GoogleApiManager(context.getApplicationContext(), GmsClientSupervisor.getOrStartHandlerThread().getLooper(), GoogleApiAvailability.getInstance());
                if (zai) {
                    final Handler handler = zah.zas;
                    Objects.requireNonNull(handler);
                    GmsClient.zag(new Executor() { // from class: com.google.android.gms.common.api.internal.zabo
                        @Override // java.util.concurrent.Executor
                        public final /* synthetic */ void execute(Runnable runnable) {
                            handler.post(runnable);
                        }
                    });
                }
            }
            googleApiManager = zah;
        }
        return googleApiManager;
    }

    public static GoogleApiManager zab() {
        GoogleApiManager googleApiManager;
        synchronized (zae) {
            Preconditions.checkNotNull(zah, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = zah;
        }
        return googleApiManager;
    }

    public static boolean zas() {
        synchronized (zae) {
            if (zah != null) {
                return false;
            }
            zai = true;
            return true;
        }
    }

    public static boolean zat() {
        return zai;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        zabk zabkVar = null;
        switch (message.what) {
            case 1:
                this.zac = true == ((Boolean) message.obj).booleanValue() ? 10000L : 300000L;
                Handler handler = this.zas;
                handler.removeMessages(12);
                Iterator it = this.zao.keySet().iterator();
                while (it.hasNext()) {
                    handler.sendMessageDelayed(handler.obtainMessage(12, (ApiKey) it.next()), this.zac);
                }
                return true;
            case 2:
                zal zalVar = (zal) message.obj;
                Iterator it2 = zalVar.zaa().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        ApiKey apiKey = (ApiKey) it2.next();
                        zabk zabkVar2 = (zabk) this.zao.get(apiKey);
                        if (zabkVar2 == null) {
                            zalVar.zac(apiKey, new ConnectionResult(13), null);
                        } else if (zabkVar2.zao()) {
                            zalVar.zac(apiKey, ConnectionResult.RESULT_SUCCESS, zabkVar2.zaf().getEndpointPackageName());
                        } else {
                            ConnectionResult connectionResultZai = zabkVar2.zai();
                            if (connectionResultZai != null) {
                                zalVar.zac(apiKey, connectionResultZai, null);
                            } else {
                                zabkVar2.zan(zalVar);
                                zabkVar2.zam();
                            }
                        }
                    }
                }
                return true;
            case 3:
                for (zabk zabkVar3 : this.zao.values()) {
                    zabkVar3.zah();
                    zabkVar3.zam();
                }
                return true;
            case 4:
            case 8:
            case 13:
                zacc zaccVar = (zacc) message.obj;
                Map map = this.zao;
                GoogleApi googleApi = zaccVar.zac;
                zabk zabkVarZaH = (zabk) map.get(googleApi.getApiKey());
                if (zabkVarZaH == null) {
                    zabkVarZaH = zaH(googleApi);
                }
                if (!zabkVarZaH.zap() || this.zan.get() == zaccVar.zab) {
                    zabkVarZaH.zad(zaccVar.zaa);
                } else {
                    zaccVar.zaa.zad(zaa);
                    zabkVarZaH.zae();
                }
                return true;
            case 5:
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator it3 = this.zao.values().iterator();
                while (true) {
                    if (it3.hasNext()) {
                        zabk zabkVar4 = (zabk) it3.next();
                        if (zabkVar4.zaq() == i) {
                            zabkVar = zabkVar4;
                        }
                    }
                }
                if (zabkVar == null) {
                    StringBuilder sb = new StringBuilder(String.valueOf(i).length() + 65);
                    sb.append("Could not find API instance ");
                    sb.append(i);
                    sb.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb.toString(), new Exception());
                } else if (connectionResult.getErrorCode() == 13) {
                    String errorString = this.zak.getErrorString(connectionResult.getErrorCode());
                    String errorMessage = connectionResult.getErrorMessage();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(errorString).length() + 69 + String.valueOf(errorMessage).length());
                    sb2.append("Error resolution was canceled by the user, original error message: ");
                    sb2.append(errorString);
                    sb2.append(": ");
                    sb2.append(errorMessage);
                    zabkVar.zav(new Status(17, sb2.toString()));
                } else {
                    zabkVar.zav(zaJ(zabkVar.zaA(), connectionResult));
                }
                return true;
            case 6:
                Context context = this.zaj;
                if (context.getApplicationContext() instanceof Application) {
                    BackgroundDetector.initialize((Application) context.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new zabf(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zac = 300000L;
                    }
                }
                return true;
            case 7:
                zaH((GoogleApi) message.obj);
                return true;
            case 9:
                Map map2 = this.zao;
                if (map2.containsKey(message.obj)) {
                    ((zabk) map2.get(message.obj)).zaj();
                }
                return true;
            case 10:
                Set set = this.zar;
                Iterator it4 = set.iterator();
                while (it4.hasNext()) {
                    zabk zabkVar5 = (zabk) this.zao.remove((ApiKey) it4.next());
                    if (zabkVar5 != null) {
                        zabkVar5.zae();
                    }
                }
                set.clear();
                return true;
            case 11:
                Map map3 = this.zao;
                if (map3.containsKey(message.obj)) {
                    ((zabk) map3.get(message.obj)).zak();
                }
                return true;
            case 12:
                Map map4 = this.zao;
                if (map4.containsKey(message.obj)) {
                    ((zabk) map4.get(message.obj)).zal();
                }
                return true;
            case 14:
                zaac zaacVar = (zaac) message.obj;
                ApiKey apiKeyZaa = zaacVar.zaa();
                Map map5 = this.zao;
                if (map5.containsKey(apiKeyZaa)) {
                    zaacVar.zab().setResult(Boolean.valueOf(((zabk) map5.get(apiKeyZaa)).zaw(false)));
                } else {
                    zaacVar.zab().setResult(false);
                }
                return true;
            case 15:
                zabl zablVar = (zabl) message.obj;
                Map map6 = this.zao;
                if (map6.containsKey(zablVar.zaa())) {
                    ((zabk) map6.get(zablVar.zaa())).zax(zablVar);
                }
                return true;
            case 16:
                zabl zablVar2 = (zabl) message.obj;
                Map map7 = this.zao;
                if (map7.containsKey(zablVar2.zaa())) {
                    ((zabk) map7.get(zablVar2.zaa())).zay(zablVar2);
                }
                return true;
            case 17:
                zaK();
                return true;
            case 18:
                zabz zabzVar = (zabz) message.obj;
                long j = zabzVar.zac;
                if (j == 0) {
                    zaL().log(new TelemetryData(zabzVar.zab, Arrays.asList(zabzVar.zaa)));
                } else {
                    TelemetryData telemetryData = this.zaf;
                    if (telemetryData != null) {
                        List listZab = telemetryData.zab();
                        if (telemetryData.zaa() != zabzVar.zab || (listZab != null && listZab.size() >= zabzVar.zad)) {
                            this.zas.removeMessages(17);
                            zaK();
                        } else {
                            this.zaf.zac(zabzVar.zaa);
                        }
                    }
                    if (this.zaf == null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(zabzVar.zaa);
                        this.zaf = new TelemetryData(zabzVar.zab, arrayList);
                        Handler handler2 = this.zas;
                        handler2.sendMessageDelayed(handler2.obtainMessage(17), j);
                    }
                }
                return true;
            case 19:
                this.zad = false;
                return true;
            default:
                int i2 = message.what;
                StringBuilder sb3 = new StringBuilder(String.valueOf(i2).length() + 20);
                sb3.append("Unknown message id: ");
                sb3.append(i2);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
    }

    final /* synthetic */ GoogleApiAvailability zaA() {
        return this.zak;
    }

    final /* synthetic */ com.google.android.gms.common.internal.zao zaB() {
        return this.zal;
    }

    final /* synthetic */ Map zaC() {
        return this.zao;
    }

    final /* synthetic */ zaab zaD() {
        return this.zap;
    }

    final /* synthetic */ Set zaE() {
        return this.zaq;
    }

    final /* synthetic */ Handler zaF() {
        return this.zas;
    }

    final /* synthetic */ boolean zaG() {
        return this.zat;
    }

    public final int zac() {
        return this.zam.getAndIncrement();
    }

    public final void zad(GoogleApi googleApi) {
        Handler handler = this.zas;
        handler.sendMessage(handler.obtainMessage(7, googleApi));
    }

    public final void zae(zaab zaabVar) {
        synchronized (zae) {
            if (this.zap != zaabVar) {
                this.zap = zaabVar;
                this.zaq.clear();
            }
            this.zaq.addAll(zaabVar.zab());
        }
    }

    final void zaf(zaab zaabVar) {
        synchronized (zae) {
            if (this.zap == zaabVar) {
                this.zap = null;
                this.zaq.clear();
            }
        }
    }

    final zabk zag(ApiKey apiKey) {
        return (zabk) this.zao.get(apiKey);
    }

    public final Task zah(Iterable iterable) {
        zal zalVar = new zal(iterable);
        Handler handler = this.zas;
        handler.sendMessage(handler.obtainMessage(2, zalVar));
        return zalVar.zab();
    }

    public final void zai() {
        Handler handler = this.zas;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public final Task zaj(GoogleApi googleApi) {
        zaac zaacVar = new zaac(googleApi.getApiKey());
        Handler handler = this.zas;
        handler.sendMessage(handler.obtainMessage(14, zaacVar));
        return zaacVar.zab().getTask();
    }

    public final void zak(GoogleApi googleApi, int i, BaseImplementation.ApiMethodImpl apiMethodImpl) {
        zacc zaccVar = new zacc(new zae(i, apiMethodImpl), this.zan.get(), googleApi);
        Handler handler = this.zas;
        handler.sendMessage(handler.obtainMessage(4, zaccVar));
    }

    public final void zal(GoogleApi googleApi, int i, TaskApiCall taskApiCall, TaskCompletionSource taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        zaI(taskCompletionSource, taskApiCall.zab(), googleApi);
        zacc zaccVar = new zacc(new zag(i, taskApiCall, taskCompletionSource, statusExceptionMapper), this.zan.get(), googleApi);
        Handler handler = this.zas;
        handler.sendMessage(handler.obtainMessage(4, zaccVar));
    }

    final boolean zam() {
        if (this.zad) {
            return false;
        }
        RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
        if (config != null && !config.getMethodInvocationTelemetryEnabled()) {
            return false;
        }
        int iZab = this.zal.zab(this.zaj, 203400000);
        return iZab == -1 || iZab == 0;
    }

    public final Task zan(GoogleApi googleApi, RegisterListenerMethod registerListenerMethod, UnregisterListenerMethod unregisterListenerMethod, Runnable runnable) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zaI(taskCompletionSource, registerListenerMethod.zab(), googleApi);
        zacc zaccVar = new zacc(new zaf(new zacd(registerListenerMethod, unregisterListenerMethod, runnable), taskCompletionSource), this.zan.get(), googleApi);
        Handler handler = this.zas;
        handler.sendMessage(handler.obtainMessage(8, zaccVar));
        return taskCompletionSource.getTask();
    }

    public final Task zao(GoogleApi googleApi, ListenerHolder.ListenerKey listenerKey, int i) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zaI(taskCompletionSource, i, googleApi);
        zacc zaccVar = new zacc(new zah(listenerKey, taskCompletionSource), this.zan.get(), googleApi);
        Handler handler = this.zas;
        handler.sendMessage(handler.obtainMessage(13, zaccVar));
        return taskCompletionSource.getTask();
    }

    final boolean zap(ConnectionResult connectionResult, int i) {
        return this.zak.zad(this.zaj, connectionResult, i);
    }

    public final void zaq(ConnectionResult connectionResult, int i) {
        if (zap(connectionResult, i)) {
            return;
        }
        Handler handler = this.zas;
        handler.sendMessage(handler.obtainMessage(5, i, 0, connectionResult));
    }

    final void zar(MethodInvocation methodInvocation, int i, long j, int i2) {
        zabz zabzVar = new zabz(methodInvocation, i, j, i2);
        Handler handler = this.zas;
        handler.sendMessage(handler.obtainMessage(18, zabzVar));
    }

    final /* synthetic */ long zaw() {
        return this.zac;
    }

    final /* synthetic */ void zax(boolean z) {
        this.zad = true;
    }

    final /* synthetic */ Context zaz() {
        return this.zaj;
    }
}
