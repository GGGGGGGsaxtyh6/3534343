package com.google.firebase.functions;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamResponse.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Lcom/google/firebase/functions/StreamResponse;", "", "<init>", "()V", "Message", "Result", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class StreamResponse {
    public /* synthetic */ StreamResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private StreamResponse() {
    }

    /* JADX INFO: compiled from: StreamResponse.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/google/firebase/functions/StreamResponse$Message;", "Lcom/google/firebase/functions/StreamResponse;", "message", "Lcom/google/firebase/functions/HttpsCallableResult;", "<init>", "(Lcom/google/firebase/functions/HttpsCallableResult;)V", "getMessage", "()Lcom/google/firebase/functions/HttpsCallableResult;", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Message extends StreamResponse {
        private final HttpsCallableResult message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Message(HttpsCallableResult message) {
            super(null);
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
        }

        public final HttpsCallableResult getMessage() {
            return this.message;
        }
    }

    /* JADX INFO: compiled from: StreamResponse.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/google/firebase/functions/StreamResponse$Result;", "Lcom/google/firebase/functions/StreamResponse;", "result", "Lcom/google/firebase/functions/HttpsCallableResult;", "<init>", "(Lcom/google/firebase/functions/HttpsCallableResult;)V", "getResult", "()Lcom/google/firebase/functions/HttpsCallableResult;", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Result extends StreamResponse {
        private final HttpsCallableResult result;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Result(HttpsCallableResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        public final HttpsCallableResult getResult() {
            return this.result;
        }
    }
}
