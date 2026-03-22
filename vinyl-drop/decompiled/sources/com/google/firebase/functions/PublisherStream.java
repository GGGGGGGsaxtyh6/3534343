package com.google.firebase.functions;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.net.HttpHeaders;
import com.google.firebase.functions.FirebaseFunctionsException;
import com.google.firebase.functions.StreamResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX INFO: compiled from: PublisherStream.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BI\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u001f\u001a\u00020 2\u000e\u0010!\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0017H\u0016J\b\u0010\"\u001a\u00020 H\u0002J\b\u0010#\u001a\u00020 H\u0002J\u0010\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020 H\u0002J\u0010\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020 H\u0002J\u0010\u0010/\u001a\u00020 2\u0006\u00100\u001a\u000201H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0014\u001a\u001c\u0012\u0018\u0012\u0016\u0012\f\u0012\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/google/firebase/functions/PublisherStream;", "Lorg/reactivestreams/Publisher;", "Lcom/google/firebase/functions/StreamResponse;", ImagesContract.URL, "Ljava/net/URL;", "data", "", "options", "Lcom/google/firebase/functions/HttpsCallOptions;", "client", "Lokhttp3/OkHttpClient;", "serializer", "Lcom/google/firebase/functions/Serializer;", "contextTask", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/firebase/functions/HttpsCallableContext;", "executor", "Ljava/util/concurrent/Executor;", "<init>", "(Ljava/net/URL;Ljava/lang/Object;Lcom/google/firebase/functions/HttpsCallOptions;Lokhttp3/OkHttpClient;Lcom/google/firebase/functions/Serializer;Lcom/google/android/gms/tasks/Task;Ljava/util/concurrent/Executor;)V", "subscribers", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lkotlin/Pair;", "Lorg/reactivestreams/Subscriber;", "Ljava/util/concurrent/atomic/AtomicLong;", "activeCall", "Lokhttp3/Call;", "isStreamingStarted", "", "isCompleted", "messageQueue", "subscribe", "", "subscriber", "startStreaming", "cancelStream", "processSSEStream", "inputStream", "Ljava/io/InputStream;", "processEvent", "dataChunk", "", "dispatchMessages", "notifyError", "e", "", "notifyComplete", "validateResponse", "response", "Lokhttp3/Response;", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PublisherStream implements Publisher<StreamResponse> {
    private Call activeCall;
    private final OkHttpClient client;
    private final Task<HttpsCallableContext> contextTask;
    private final Object data;
    private final Executor executor;
    private volatile boolean isCompleted;
    private volatile boolean isStreamingStarted;
    private final ConcurrentLinkedQueue<StreamResponse> messageQueue;
    private final HttpsCallOptions options;
    private final Serializer serializer;
    private final ConcurrentLinkedQueue<Pair<Subscriber<? super StreamResponse>, AtomicLong>> subscribers;
    private final URL url;

    public PublisherStream(URL url, Object obj, HttpsCallOptions options, OkHttpClient client, Serializer serializer, Task<HttpsCallableContext> contextTask, Executor executor) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(contextTask, "contextTask");
        Intrinsics.checkNotNullParameter(executor, "executor");
        this.url = url;
        this.data = obj;
        this.options = options;
        this.client = client;
        this.serializer = serializer;
        this.contextTask = contextTask;
        this.executor = executor;
        this.subscribers = new ConcurrentLinkedQueue<>();
        this.messageQueue = new ConcurrentLinkedQueue<>();
    }

    @Override // org.reactivestreams.Publisher
    public void subscribe(final Subscriber<? super StreamResponse> subscriber) {
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        synchronized (this) {
            if (this.isCompleted) {
                subscriber.onError(new FirebaseFunctionsException("Cannot subscribe: Streaming has already completed.", FirebaseFunctionsException.Code.CANCELLED, null));
            } else {
                this.subscribers.add(TuplesKt.to(subscriber, new AtomicLong(0L)));
                subscriber.onSubscribe(new Subscription() { // from class: com.google.firebase.functions.PublisherStream.subscribe.2
                    @Override // org.reactivestreams.Subscription
                    public void request(long n) {
                        Object next;
                        AtomicLong atomicLong;
                        if (n <= 0) {
                            subscriber.onError(new IllegalArgumentException("Requested messages must be positive."));
                            return;
                        }
                        PublisherStream publisherStream = this;
                        Subscriber<? super StreamResponse> subscriber2 = subscriber;
                        synchronized (publisherStream) {
                            if (publisherStream.isCompleted) {
                                return;
                            }
                            Iterator it = publisherStream.subscribers.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    next = null;
                                    break;
                                } else {
                                    next = it.next();
                                    if (Intrinsics.areEqual(((Pair) next).getFirst(), subscriber2)) {
                                        break;
                                    }
                                }
                            }
                            Pair pair = (Pair) next;
                            if (pair != null && (atomicLong = (AtomicLong) pair.getSecond()) != null) {
                                atomicLong.addAndGet(n);
                            }
                            publisherStream.dispatchMessages();
                            if (!publisherStream.isStreamingStarted) {
                                publisherStream.isStreamingStarted = true;
                                publisherStream.startStreaming();
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    }

                    @Override // org.reactivestreams.Subscription
                    public void cancel() {
                        PublisherStream publisherStream = this;
                        Subscriber<? super StreamResponse> subscriber2 = subscriber;
                        synchronized (publisherStream) {
                            publisherStream.notifyError(new FirebaseFunctionsException("Stream was canceled", FirebaseFunctionsException.Code.CANCELLED, null));
                            Iterator it = publisherStream.subscribers.iterator();
                            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                            while (it.hasNext()) {
                                if (Intrinsics.areEqual(((Pair) it.next()).getFirst(), subscriber2)) {
                                    it.remove();
                                }
                            }
                            if (publisherStream.subscribers.isEmpty()) {
                                publisherStream.cancelStream();
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startStreaming() {
        this.contextTask.addOnCompleteListener(this.executor, new OnCompleteListener() { // from class: com.google.firebase.functions.PublisherStream$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                PublisherStream.startStreaming$lambda$6(this.f$0, task);
            }
        });
    }

    static final void startStreaming$lambda$6(final PublisherStream publisherStream, Task contextTask) {
        Intrinsics.checkNotNullParameter(contextTask, "contextTask");
        if (!contextTask.isSuccessful()) {
            publisherStream.notifyError(new FirebaseFunctionsException("Error retrieving context", FirebaseFunctionsException.Code.INTERNAL, null, contextTask.getException()));
            return;
        }
        HttpsCallableContext httpsCallableContext = (HttpsCallableContext) contextTask.getResult();
        OkHttpClient okHttpClientApply$com_google_firebase_firebase_functions = publisherStream.options.apply$com_google_firebase_firebase_functions(publisherStream.client);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = new JSONObject(MapsKt.mapOf(TuplesKt.to("data", publisherStream.serializer.encode(publisherStream.data)))).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        Request.Builder builderPost = new Request.Builder().url(publisherStream.url).post(companion.create(mediaType, string));
        builderPost.header(HttpHeaders.ACCEPT, "text/event-stream");
        builderPost.header(HttpHeaders.CONTENT_TYPE, "application/json");
        if (httpsCallableContext != null) {
            String authToken = httpsCallableContext.getAuthToken();
            if (authToken != null) {
                builderPost.header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken);
            }
            String instanceIdToken = httpsCallableContext.getInstanceIdToken();
            if (instanceIdToken != null) {
                builderPost.header("Firebase-Instance-ID-Token", instanceIdToken);
            }
            String appCheckToken = httpsCallableContext.getAppCheckToken();
            if (appCheckToken != null) {
                builderPost.header("X-Firebase-AppCheck", appCheckToken);
            }
        }
        Call callNewCall = okHttpClientApply$com_google_firebase_firebase_functions.newCall(builderPost.build());
        publisherStream.activeCall = callNewCall;
        callNewCall.enqueue(new Callback() { // from class: com.google.firebase.functions.PublisherStream$startStreaming$1$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                FirebaseFunctionsException.Code code;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                if (e instanceof InterruptedIOException) {
                    code = FirebaseFunctionsException.Code.DEADLINE_EXCEEDED;
                } else {
                    code = FirebaseFunctionsException.Code.INTERNAL;
                }
                this.this$0.notifyError(new FirebaseFunctionsException(code.name(), code, null, e));
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                this.this$0.validateResponse(response);
                ResponseBody responseBodyBody = response.body();
                InputStream inputStreamByteStream = responseBodyBody != null ? responseBodyBody.byteStream() : null;
                PublisherStream publisherStream2 = this.this$0;
                if (inputStreamByteStream != null) {
                    publisherStream2.processSSEStream(inputStreamByteStream);
                } else {
                    publisherStream2.notifyError(new FirebaseFunctionsException("Response body is null", FirebaseFunctionsException.Code.INTERNAL, null));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelStream() {
        Call call = this.activeCall;
        if (call != null) {
            call.cancel();
        }
        notifyError(new FirebaseFunctionsException("Stream was canceled", FirebaseFunctionsException.Code.CANCELLED, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processSSEStream(InputStream inputStream) {
        String strRemovePrefix;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            BufferedReader bufferedReader2 = bufferedReader;
            try {
                StringBuilder sb = new StringBuilder();
                for (String str : TextStreamsKt.lineSequence(bufferedReader2)) {
                    if (StringsKt.isBlank(str)) {
                        String string = sb.toString();
                        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                        processEvent(string);
                        StringsKt.clear(sb);
                    } else {
                        if (StringsKt.startsWith$default(str, "data:", false, 2, (Object) null)) {
                            strRemovePrefix = StringsKt.removePrefix(str, (CharSequence) "data:");
                        } else if (StringsKt.startsWith$default(str, "result:", false, 2, (Object) null)) {
                            strRemovePrefix = StringsKt.removePrefix(str, (CharSequence) "result:");
                        }
                        sb.append(StringsKt.trim((CharSequence) strRemovePrefix).toString()).append("\n");
                    }
                }
                if (sb.length() > 0) {
                    String string2 = sb.toString();
                    Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                    processEvent(string2);
                }
            } catch (Exception e) {
                String message = e.getMessage();
                if (message == null) {
                    message = "Error reading stream";
                }
                notifyError(new FirebaseFunctionsException(message, FirebaseFunctionsException.Code.INTERNAL, e));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(bufferedReader, null);
        } finally {
        }
    }

    private final void processEvent(String dataChunk) {
        Object objDecode;
        try {
            JSONObject jSONObject = new JSONObject(dataChunk);
            if (jSONObject.has("message")) {
                Object objDecode2 = this.serializer.decode(jSONObject.opt("message"));
                if (objDecode2 != null) {
                    this.messageQueue.add(new StreamResponse.Message(new HttpsCallableResult(objDecode2)));
                }
                dispatchMessages();
                return;
            }
            if (jSONObject.has("error")) {
                Object objDecode3 = this.serializer.decode(jSONObject.opt("error"));
                if (objDecode3 != null) {
                    notifyError(new FirebaseFunctionsException(objDecode3.toString(), FirebaseFunctionsException.Code.INTERNAL, objDecode3));
                    return;
                }
                return;
            }
            if (!jSONObject.has("result") || (objDecode = this.serializer.decode(jSONObject.opt("result"))) == null) {
                return;
            }
            this.messageQueue.add(new StreamResponse.Result(new HttpsCallableResult(objDecode)));
            dispatchMessages();
            notifyComplete();
        } catch (Throwable th) {
            notifyError(new FirebaseFunctionsException("Invalid JSON: " + dataChunk, FirebaseFunctionsException.Code.INTERNAL, th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dispatchMessages() {
        synchronized (this) {
            Iterator<Pair<Subscriber<? super StreamResponse>, AtomicLong>> it = this.subscribers.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                Pair<Subscriber<? super StreamResponse>, AtomicLong> next = it.next();
                Subscriber<? super StreamResponse> subscriberComponent1 = next.component1();
                AtomicLong atomicLongComponent2 = next.component2();
                while (atomicLongComponent2.get() > 0 && !this.messageQueue.isEmpty()) {
                    subscriberComponent1.onNext(this.messageQueue.poll());
                    atomicLongComponent2.decrementAndGet();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyError(Throwable e) {
        if (this.isCompleted) {
            return;
        }
        this.isCompleted = true;
        Iterator<T> it = this.subscribers.iterator();
        while (it.hasNext()) {
            try {
                ((Subscriber) ((Pair) it.next()).component1()).onError(e);
            } catch (Exception unused) {
            }
        }
        this.subscribers.clear();
        this.messageQueue.clear();
    }

    private final void notifyComplete() {
        if (this.isCompleted) {
            return;
        }
        this.isCompleted = true;
        Iterator<T> it = this.subscribers.iterator();
        while (it.hasNext()) {
            ((Subscriber) ((Pair) it.next()).component1()).onComplete();
        }
        this.subscribers.clear();
        this.messageQueue.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void validateResponse(Response response) {
        String strString;
        if (response.isSuccessful()) {
            return;
        }
        String str = "";
        if (response.code() == 404) {
            MediaType.Companion companion = MediaType.INSTANCE;
            String strHeader$default = Response.header$default(response, HttpHeaders.CONTENT_TYPE, null, 2, null);
            if (strHeader$default == null) {
                strHeader$default = "";
            }
            MediaType mediaType = companion.parse(strHeader$default);
            if (Intrinsics.areEqual(mediaType != null ? mediaType.subtype() : null, "html")) {
                StringBuilder sb = new StringBuilder("URL not found. Raw response: ");
                ResponseBody responseBodyBody = response.body();
                notifyError(new FirebaseFunctionsException(StringsKt.trimMargin$default(sb.append(responseBodyBody != null ? responseBodyBody.string() : null).toString(), null, 1, null), FirebaseFunctionsException.Code.INSTANCE.fromHttpStatus(response.code()), null));
                return;
            }
        }
        ResponseBody responseBodyBody2 = response.body();
        if (responseBodyBody2 != null && (strString = responseBodyBody2.string()) != null) {
            str = strString;
        }
        try {
            Object objDecode = this.serializer.decode(new JSONObject(str).opt("error"));
            notifyError(new FirebaseFunctionsException(String.valueOf(objDecode), FirebaseFunctionsException.Code.INTERNAL, objDecode));
        } catch (Throwable th) {
            notifyError(new FirebaseFunctionsException(th.getMessage() + " Unexpected Response:\n" + str + ' ', FirebaseFunctionsException.Code.INTERNAL, th));
        }
    }
}
