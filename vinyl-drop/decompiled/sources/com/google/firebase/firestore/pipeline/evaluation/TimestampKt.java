package com.google.firebase.firestore.pipeline.evaluation;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.common.math.LongMath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResult;
import com.google.firebase.firestore.pipeline.evaluation.UtilsKt;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import com.google.protobuf.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;

/* JADX INFO: compiled from: Timestamp.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\u001a \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0001H\u0002\u001a \u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0007\u001a\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0003\u001a\u000e\u00101\u001a\u00020\u00192\u0006\u00102\u001a\u00020\u0001\u001a\u000e\u00103\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u0001\u001a\u000e\u00105\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"w\u0010\u001a\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001c¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001bj\u0002`$X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&\"w\u0010'\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001c¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001bj\u0002`$X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&\"w\u0010)\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001c¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001bj\u0002`$X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010&\"w\u0010+\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001c¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001bj\u0002`$X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010&\"w\u0010-\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001c¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001bj\u0002`$X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010&\"w\u0010/\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001c¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001bj\u0002`$X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010&\"w\u00106\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001c¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001bj\u0002`$X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u0010&\"w\u00108\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001c¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001bj\u0002`$X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010&\"w\u0010:\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001c¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(#\u0012#\u0012!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020!0\u001bj\u0002`\"0\u001bj\u0002`$X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010&¨\u0006<"}, d2 = {"L_NANOS_PER_SECOND", "", "I_NANOS_PER_SECOND", "", "L_MICROS_PER_SECOND", "I_MICROS_PER_SECOND", "L_MILLIS_PER_SECOND", "I_MILLIS_PER_SECOND", "TIMESTAMP_MIN_SECONDS", "TIMESTAMP_MAX_SECONDS", "TIMESTAMP_MIN_MILLISECONDS", "TIMESTAMP_MAX_MILLISECONDS", "TIMESTAMP_MIN_MICROSECONDS", "TIMESTAMP_MAX_MICROSECONDS", "plus", "Lcom/google/protobuf/Timestamp;", "t", "seconds", "nanos", "minus", "convertUnit", "Ljava/time/temporal/ChronoUnit;", "unit", "", "isTimestampInBounds", "", "evaluateTimestampAdd", "Lkotlin/Function1;", "", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lkotlin/ParameterName;", "name", "input", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateDocument;", "params", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateFunction;", "getEvaluateTimestampAdd", "()Lkotlin/jvm/functions/Function1;", "evaluateTimestampSub", "getEvaluateTimestampSub", "evaluateTimestampTrunc", "getEvaluateTimestampTrunc", "evaluateTimestampToUnixMicros", "getEvaluateTimestampToUnixMicros", "evaluateTimestampToUnixMillis", "getEvaluateTimestampToUnixMillis", "evaluateTimestampToUnixSeconds", "getEvaluateTimestampToUnixSeconds", "isMicrosecondsInTimestampBounds", "microseconds", "isMillisecondsInTimestampBounds", "milliseconds", "isSecondsInTimestampBounds", "evaluateUnixMicrosToTimestamp", "getEvaluateUnixMicrosToTimestamp", "evaluateUnixMillisToTimestamp", "getEvaluateUnixMillisToTimestamp", "evaluateUnixSecondsToTimestamp", "getEvaluateUnixSecondsToTimestamp", "com.google.firebase-firebase-firestore"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class TimestampKt {
    private static final int I_MICROS_PER_SECOND = 1000000;
    private static final int I_MILLIS_PER_SECOND = 1000;
    private static final int I_NANOS_PER_SECOND = 1000000000;
    private static final long L_MICROS_PER_SECOND = 1000000;
    private static final long L_MILLIS_PER_SECOND = 1000;
    private static final long L_NANOS_PER_SECOND = 1000000000;
    private static final long TIMESTAMP_MAX_MICROSECONDS = 253402300799999999L;
    private static final long TIMESTAMP_MAX_MILLISECONDS = 253402300799999L;
    private static final long TIMESTAMP_MAX_SECONDS = 253402300799L;
    private static final long TIMESTAMP_MIN_MICROSECONDS = -62135596800000000L;
    private static final long TIMESTAMP_MIN_MILLISECONDS = -62135596800000L;
    private static final long TIMESTAMP_MIN_SECONDS = -62135596800L;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateTimestampToUnixMicros;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateTimestampToUnixMillis;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateTimestampToUnixSeconds;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateUnixMicrosToTimestamp;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateUnixMillisToTimestamp;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateUnixSecondsToTimestamp;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateTimestampAdd = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$ternaryTimestampFunction$1
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            if (params.size() != 3) {
                throw Assert.fail("Function should have exactly 3 params, but %d were given.", Integer.valueOf(params.size()));
            }
            final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
            final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
            final Function1<? super MutableDocument, ? extends EvaluateResult> function13 = params.get(2);
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$ternaryTimestampFunction$1.1
                /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
                /* JADX WARN: Removed duplicated region for block: B:94:0x01c0 A[Catch: Exception -> 0x01df, TryCatch #0 {Exception -> 0x01df, blocks: (B:3:0x000b, B:5:0x002c, B:7:0x0031, B:9:0x0047, B:11:0x004c, B:13:0x0062, B:15:0x0067, B:17:0x0074, B:28:0x008f, B:30:0x0094, B:33:0x009c, B:40:0x00b1, B:42:0x00bc, B:51:0x00d5, B:53:0x00da, B:57:0x00e8, B:59:0x00f4, B:61:0x0119, B:63:0x011e, B:65:0x012d, B:66:0x0131, B:98:0x01cc, B:68:0x0136, B:71:0x0140, B:92:0x01b2, B:94:0x01c0, B:96:0x01c5, B:72:0x014f, B:75:0x0159, B:76:0x0164, B:79:0x016d, B:80:0x0179, B:83:0x0182, B:84:0x0191, B:87:0x019a, B:88:0x019f, B:91:0x01a8, B:100:0x01d1, B:46:0x00c6, B:102:0x01da, B:37:0x00a6, B:22:0x007f), top: B:106:0x000b }] */
                /* JADX WARN: Removed duplicated region for block: B:96:0x01c5 A[Catch: Exception -> 0x01df, TryCatch #0 {Exception -> 0x01df, blocks: (B:3:0x000b, B:5:0x002c, B:7:0x0031, B:9:0x0047, B:11:0x004c, B:13:0x0062, B:15:0x0067, B:17:0x0074, B:28:0x008f, B:30:0x0094, B:33:0x009c, B:40:0x00b1, B:42:0x00bc, B:51:0x00d5, B:53:0x00da, B:57:0x00e8, B:59:0x00f4, B:61:0x0119, B:63:0x011e, B:65:0x012d, B:66:0x0131, B:98:0x01cc, B:68:0x0136, B:71:0x0140, B:92:0x01b2, B:94:0x01c0, B:96:0x01c5, B:72:0x014f, B:75:0x0159, B:76:0x0164, B:79:0x016d, B:80:0x0179, B:83:0x0182, B:84:0x0191, B:87:0x019a, B:88:0x019f, B:91:0x01a8, B:100:0x01d1, B:46:0x00c6, B:102:0x01da, B:37:0x00a6, B:22:0x007f), top: B:106:0x000b }] */
                @Override // kotlin.jvm.functions.Function1
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final com.google.firebase.firestore.pipeline.evaluation.EvaluateResult invoke(final com.google.firebase.firestore.model.MutableDocument r8) {
                    /*
                        Method dump skipped, instruction units count: 510
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$ternaryTimestampFunction$1.AnonymousClass1.invoke(com.google.firebase.firestore.model.MutableDocument):com.google.firebase.firestore.pipeline.evaluation.EvaluateResult");
                }
            };
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateTimestampSub = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$ternaryTimestampFunction$2
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            if (params.size() != 3) {
                throw Assert.fail("Function should have exactly 3 params, but %d were given.", Integer.valueOf(params.size()));
            }
            final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
            final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
            final Function1<? super MutableDocument, ? extends EvaluateResult> function13 = params.get(2);
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$ternaryTimestampFunction$2.1
                /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
                /* JADX WARN: Removed duplicated region for block: B:94:0x01c0 A[Catch: Exception -> 0x01df, TryCatch #0 {Exception -> 0x01df, blocks: (B:3:0x000b, B:5:0x002c, B:7:0x0031, B:9:0x0047, B:11:0x004c, B:13:0x0062, B:15:0x0067, B:17:0x0074, B:28:0x008f, B:30:0x0094, B:33:0x009c, B:40:0x00b1, B:42:0x00bc, B:51:0x00d5, B:53:0x00da, B:57:0x00e8, B:59:0x00f4, B:61:0x0119, B:63:0x011e, B:65:0x012d, B:66:0x0131, B:98:0x01cc, B:68:0x0136, B:71:0x0140, B:92:0x01b2, B:94:0x01c0, B:96:0x01c5, B:72:0x014f, B:75:0x0159, B:76:0x0164, B:79:0x016d, B:80:0x0179, B:83:0x0182, B:84:0x0191, B:87:0x019a, B:88:0x019f, B:91:0x01a8, B:100:0x01d1, B:46:0x00c6, B:102:0x01da, B:37:0x00a6, B:22:0x007f), top: B:106:0x000b }] */
                /* JADX WARN: Removed duplicated region for block: B:96:0x01c5 A[Catch: Exception -> 0x01df, TryCatch #0 {Exception -> 0x01df, blocks: (B:3:0x000b, B:5:0x002c, B:7:0x0031, B:9:0x0047, B:11:0x004c, B:13:0x0062, B:15:0x0067, B:17:0x0074, B:28:0x008f, B:30:0x0094, B:33:0x009c, B:40:0x00b1, B:42:0x00bc, B:51:0x00d5, B:53:0x00da, B:57:0x00e8, B:59:0x00f4, B:61:0x0119, B:63:0x011e, B:65:0x012d, B:66:0x0131, B:98:0x01cc, B:68:0x0136, B:71:0x0140, B:92:0x01b2, B:94:0x01c0, B:96:0x01c5, B:72:0x014f, B:75:0x0159, B:76:0x0164, B:79:0x016d, B:80:0x0179, B:83:0x0182, B:84:0x0191, B:87:0x019a, B:88:0x019f, B:91:0x01a8, B:100:0x01d1, B:46:0x00c6, B:102:0x01da, B:37:0x00a6, B:22:0x007f), top: B:106:0x000b }] */
                @Override // kotlin.jvm.functions.Function1
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final com.google.firebase.firestore.pipeline.evaluation.EvaluateResult invoke(final com.google.firebase.firestore.model.MutableDocument r8) {
                    /*
                        Method dump skipped, instruction units count: 510
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$ternaryTimestampFunction$2.AnonymousClass1.invoke(com.google.firebase.firestore.model.MutableDocument):com.google.firebase.firestore.pipeline.evaluation.EvaluateResult");
                }
            };
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateTimestampTrunc = UtilsKt.getNotImplemented();

    public static final boolean isMicrosecondsInTimestampBounds(long j) {
        return j >= TIMESTAMP_MIN_MICROSECONDS && j <= TIMESTAMP_MAX_MICROSECONDS;
    }

    public static final boolean isMillisecondsInTimestampBounds(long j) {
        return j >= TIMESTAMP_MIN_MILLISECONDS && j <= 253402300799999L;
    }

    public static final boolean isSecondsInTimestampBounds(long j) {
        return j >= TIMESTAMP_MIN_SECONDS && j <= TIMESTAMP_MAX_SECONDS;
    }

    public static final boolean isTimestampInBounds(long j, int i) {
        return j >= TIMESTAMP_MIN_SECONDS && j <= TIMESTAMP_MAX_SECONDS && i >= 0 && ((long) i) < L_NANOS_PER_SECOND;
    }

    public static final Timestamp plus(Timestamp t, long j, long j2) {
        Intrinsics.checkNotNullParameter(t, "t");
        if (j2 == 0) {
            return plus(t, j);
        }
        long nanos = ((long) t.getNanos()) + j2;
        return Values.timestamp(LongMath.checkedAdd(LongMath.checkedAdd(t.getSeconds(), j), nanos / L_NANOS_PER_SECOND), (int) (nanos % ((long) 1000000000)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Timestamp plus(Timestamp timestamp, long j) {
        return j == 0 ? timestamp : Values.timestamp(LongMath.checkedAdd(timestamp.getSeconds(), j), timestamp.getNanos());
    }

    public static final Timestamp minus(Timestamp t, long j, long j2) {
        Intrinsics.checkNotNullParameter(t, "t");
        if (j2 == 0) {
            return minus(t, j);
        }
        long nanos = ((long) t.getNanos()) - j2;
        return Values.timestamp(LongMath.checkedSubtract(t.getSeconds(), LongMath.checkedSubtract(j, nanos / L_NANOS_PER_SECOND)), (int) (nanos % ((long) 1000000000)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Timestamp minus(Timestamp timestamp, long j) {
        return j == 0 ? timestamp : Values.timestamp(LongMath.checkedSubtract(timestamp.getSeconds(), j), timestamp.getNanos());
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final ChronoUnit convertUnit(String unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        switch (unit.hashCode()) {
            case -1074026988:
                if (unit.equals("minute")) {
                    return ChronoUnit.MINUTES;
                }
                break;
            case -906279820:
                if (unit.equals("second")) {
                    return ChronoUnit.SECONDS;
                }
                break;
            case -368353224:
                if (unit.equals("microsecond")) {
                    return ChronoUnit.MICROS;
                }
                break;
            case 99228:
                if (unit.equals("day")) {
                    return ChronoUnit.DAYS;
                }
                break;
            case 3208676:
                if (unit.equals("hour")) {
                    return ChronoUnit.HOURS;
                }
                break;
            case 1942410881:
                if (unit.equals("millisecond")) {
                    return ChronoUnit.MILLIS;
                }
                break;
        }
        throw new IllegalArgumentException("Unexpected timestamp unit: " + unit);
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateTimestampAdd() {
        return evaluateTimestampAdd;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateTimestampSub() {
        return evaluateTimestampSub;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateTimestampTrunc() {
        return evaluateTimestampTrunc;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateTimestampToUnixMicros() {
        return evaluateTimestampToUnixMicros;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateTimestampToUnixMillis() {
        return evaluateTimestampToUnixMillis;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateTimestampToUnixSeconds() {
        return evaluateTimestampToUnixSeconds;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateUnixMicrosToTimestamp() {
        return evaluateUnixMicrosToTimestamp;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateUnixMillisToTimestamp() {
        return evaluateUnixMillisToTimestamp;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateUnixSecondsToTimestamp() {
        return evaluateUnixSecondsToTimestamp;
    }

    static {
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.TIMESTAMP_VALUE;
        evaluateTimestampToUnixMicros = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryTimestampFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase2 = valueTypeCase;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryTimestampFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        long jCheckedAdd;
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase3 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase3 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase3 == valueTypeCase2) {
                                    try {
                                        Timestamp timestampValue = value.getTimestampValue();
                                        if (!TimestampKt.isTimestampInBounds(timestampValue.getSeconds(), timestampValue.getNanos())) {
                                            return EvaluateResultError.INSTANCE;
                                        }
                                        EvaluateResult.Companion companion = EvaluateResult.INSTANCE;
                                        if (timestampValue.getSeconds() < -9223372036854L) {
                                            jCheckedAdd = LongMath.checkedAdd(LongMath.checkedMultiply(timestampValue.getSeconds() + 1, 1000000L), (((long) timestampValue.getNanos()) / 1000) - 1000000);
                                        } else {
                                            jCheckedAdd = LongMath.checkedAdd(LongMath.checkedMultiply(timestampValue.getSeconds(), 1000000L), ((long) timestampValue.getNanos()) / 1000);
                                        }
                                        return companion.m718long(jCheckedAdd);
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase2 = Value.ValueTypeCase.TIMESTAMP_VALUE;
        evaluateTimestampToUnixMillis = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryTimestampFunction$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase3 = valueTypeCase2;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryTimestampFunction$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        long jCheckedAdd;
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase4 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase4 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase4.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase4 == valueTypeCase3) {
                                    try {
                                        Timestamp timestampValue = value.getTimestampValue();
                                        if (!TimestampKt.isTimestampInBounds(timestampValue.getSeconds(), timestampValue.getNanos())) {
                                            return EvaluateResultError.INSTANCE;
                                        }
                                        EvaluateResult.Companion companion = EvaluateResult.INSTANCE;
                                        if (timestampValue.getSeconds() < 0 && timestampValue.getNanos() > 0) {
                                            jCheckedAdd = LongMath.checkedAdd(LongMath.checkedMultiply(timestampValue.getSeconds() + 1, 1000L), (((long) timestampValue.getNanos()) / 1000000) - 1000);
                                        } else {
                                            jCheckedAdd = LongMath.checkedAdd(LongMath.checkedMultiply(timestampValue.getSeconds(), 1000L), ((long) timestampValue.getNanos()) / 1000000);
                                        }
                                        return companion.m718long(jCheckedAdd);
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase3 = Value.ValueTypeCase.TIMESTAMP_VALUE;
        evaluateTimestampToUnixSeconds = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryTimestampFunction$3
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase4 = valueTypeCase3;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryTimestampFunction$3.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        EvaluateResultError evaluateResultErrorM718long;
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase5 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase5 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase5 == valueTypeCase4) {
                                    try {
                                        Timestamp timestampValue = value.getTimestampValue();
                                        if (TimestampKt.isTimestampInBounds(timestampValue.getSeconds(), timestampValue.getNanos())) {
                                            long nanos = timestampValue.getNanos();
                                            if (0 > nanos || nanos >= 1000000000) {
                                                evaluateResultErrorM718long = EvaluateResultError.INSTANCE;
                                            } else {
                                                evaluateResultErrorM718long = EvaluateResult.INSTANCE.m718long(timestampValue.getSeconds());
                                            }
                                        } else {
                                            evaluateResultErrorM718long = EvaluateResultError.INSTANCE;
                                        }
                                        return evaluateResultErrorM718long;
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase4 = Value.ValueTypeCase.INTEGER_VALUE;
        evaluateUnixMicrosToTimestamp = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryLongFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase5 = valueTypeCase4;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryLongFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase6 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase6 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase6.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase6 == valueTypeCase5) {
                                    try {
                                        long integerValue = value.getIntegerValue();
                                        return !TimestampKt.isMicrosecondsInTimestampBounds(integerValue) ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.timestamp(Math.floorDiv(integerValue, 1000000L), UByte$$ExternalSyntheticBackport0.m(integerValue, DurationKt.NANOS_IN_MILLIS) * 1000);
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase5 = Value.ValueTypeCase.INTEGER_VALUE;
        evaluateUnixMillisToTimestamp = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryLongFunction$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase6 = valueTypeCase5;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryLongFunction$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase7 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase7 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase7.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase7 == valueTypeCase6) {
                                    try {
                                        long integerValue = value.getIntegerValue();
                                        return !TimestampKt.isMillisecondsInTimestampBounds(integerValue) ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.timestamp(Math.floorDiv(integerValue, 1000L), UByte$$ExternalSyntheticBackport0.m(integerValue, 1000) * DurationKt.NANOS_IN_MILLIS);
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase6 = Value.ValueTypeCase.INTEGER_VALUE;
        evaluateUnixSecondsToTimestamp = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryLongFunction$3
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase7 = valueTypeCase6;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.TimestampKt$special$$inlined$unaryLongFunction$3.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase8 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase8 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase8.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase8 == valueTypeCase7) {
                                    try {
                                        long integerValue = value.getIntegerValue();
                                        return !TimestampKt.isSecondsInTimestampBounds(integerValue) ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.timestamp(integerValue, 0);
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }
}
