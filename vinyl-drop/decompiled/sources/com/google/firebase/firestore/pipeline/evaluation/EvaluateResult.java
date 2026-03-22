package com.google.firebase.firestore.pipeline.evaluation;

import androidx.camera.video.AudioStats;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.Value;
import com.google.protobuf.Timestamp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EvaluateResult.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \r2\u00020\u0001:\u0001\rB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0012\u0010\u000b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u0012\u0010\f\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\n\u0082\u0001\u0003\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "", "<init>", "()V", Values.VECTOR_MAP_VECTORS_KEY, "Lcom/google/firestore/v1/Value;", "getValue", "()Lcom/google/firestore/v1/Value;", "isError", "", "()Z", "isSuccess", "isUnset", "Companion", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResultError;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResultUnset;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResultValue;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class EvaluateResult {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final EvaluateResultValue DOUBLE_ZERO;
    private static final EvaluateResultValue FALSE;
    private static final EvaluateResultValue LONG_ZERO;
    private static final EvaluateResultValue NULL;
    private static final EvaluateResultValue TRUE;

    public /* synthetic */ EvaluateResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Value getValue();

    public abstract boolean isError();

    public abstract boolean isSuccess();

    public abstract boolean isUnset();

    private EvaluateResult() {
    }

    /* JADX INFO: compiled from: EvaluateResult.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0010\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001aJ\u0014\u0010\u001b\u001a\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020 J\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u0018J\u000e\u0010#\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007¨\u0006$"}, d2 = {"Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult$Companion;", "", "<init>", "()V", "TRUE", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResultValue;", "getTRUE", "()Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResultValue;", "FALSE", "getFALSE", "NULL", "getNULL", "DOUBLE_ZERO", "getDOUBLE_ZERO", "LONG_ZERO", "getLONG_ZERO", TypedValues.Custom.S_BOOLEAN, "", "(Ljava/lang/Boolean;)Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResultValue;", "double", "", "long", "", "int", "", TypedValues.Custom.S_STRING, "", "list", "", "Lcom/google/firestore/v1/Value;", "timestamp", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "Lcom/google/protobuf/Timestamp;", "seconds", "nanos", Values.VECTOR_MAP_VECTORS_KEY, "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EvaluateResultValue getTRUE() {
            return EvaluateResult.TRUE;
        }

        public final EvaluateResultValue getFALSE() {
            return EvaluateResult.FALSE;
        }

        public final EvaluateResultValue getNULL() {
            return EvaluateResult.NULL;
        }

        public final EvaluateResultValue getDOUBLE_ZERO() {
            return EvaluateResult.DOUBLE_ZERO;
        }

        public final EvaluateResultValue getLONG_ZERO() {
            return EvaluateResult.LONG_ZERO;
        }

        /* JADX INFO: renamed from: boolean, reason: not valid java name */
        public final EvaluateResultValue m714boolean(Boolean bool) {
            return bool == null ? getNULL() : m715boolean(bool.booleanValue());
        }

        /* JADX INFO: renamed from: boolean, reason: not valid java name */
        public final EvaluateResultValue m715boolean(boolean z) {
            return z ? getTRUE() : getFALSE();
        }

        /* JADX INFO: renamed from: double, reason: not valid java name */
        public final EvaluateResultValue m716double(double d) {
            return new EvaluateResultValue(Values.encodeValue(d));
        }

        /* JADX INFO: renamed from: long, reason: not valid java name */
        public final EvaluateResultValue m718long(long j) {
            return new EvaluateResultValue(Values.encodeValue(j));
        }

        /* JADX INFO: renamed from: long, reason: not valid java name */
        public final EvaluateResultValue m717long(int i) {
            return new EvaluateResultValue(Values.encodeValue(i));
        }

        public final EvaluateResultValue string(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            return new EvaluateResultValue(Values.encodeValue(string));
        }

        public final EvaluateResultValue list(List<Value> list) {
            Intrinsics.checkNotNullParameter(list, "list");
            return new EvaluateResultValue(Values.encodeValue(list));
        }

        public final EvaluateResult timestamp(Timestamp timestamp) {
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            return new EvaluateResultValue(Values.encodeValue(timestamp));
        }

        public final EvaluateResult timestamp(long seconds, int nanos) {
            try {
                return timestamp(Values.timestamp(seconds, nanos));
            } catch (IllegalArgumentException unused) {
                return EvaluateResultError.INSTANCE;
            }
        }

        public final EvaluateResultValue value(Value value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new EvaluateResultValue(value);
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        TRUE = new EvaluateResultValue(Values.TRUE_VALUE);
        FALSE = new EvaluateResultValue(Values.FALSE_VALUE);
        NULL = new EvaluateResultValue(Values.NULL_VALUE);
        DOUBLE_ZERO = companion.m716double(AudioStats.AUDIO_AMPLITUDE_NONE);
        LONG_ZERO = companion.m717long(0);
    }
}
