package com.google.firebase.firestore.pipeline.evaluation;

import android.os.Build;
import androidx.camera.video.AudioStats;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.evaluation.UtilsKt;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Vector.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0013\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\u001a\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0000\u001a\u0018\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0000\u001a\u0018\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0000\u001a \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001aH\u0002\u001a \u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001aH\u0003\"w\u0010\u0000\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\"w\u0010\r\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\"w\u0010\u000f\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f\"w\u0010\u0011\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"evaluateVectorLength", "Lkotlin/Function1;", "", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lkotlin/ParameterName;", "name", "input", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateDocument;", "params", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateFunction;", "getEvaluateVectorLength", "()Lkotlin/jvm/functions/Function1;", "evaluateCosineDistance", "getEvaluateCosineDistance", "evaluateDotProductDistance", "getEvaluateDotProductDistance", "evaluateEuclideanDistance", "getEvaluateEuclideanDistance", "cosineDistance", "vector1", "", "vector2", "euclideanDistance", "dotProductDistance", "fma", "", "a", "b", "c", "nativeFma", "com.google.firebase-firebase-firestore"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class VectorKt {
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateVectorLength = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.VectorKt$special$$inlined$unaryValueFunction$1
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            if (params.size() != 1) {
                throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
            }
            final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.VectorKt$special$$inlined$unaryValueFunction$1.1
                @Override // kotlin.jvm.functions.Function1
                public final EvaluateResult invoke(MutableDocument input) {
                    Intrinsics.checkNotNullParameter(input, "input");
                    try {
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        Value value2 = evaluateResult.getValue();
                        Intrinsics.checkNotNull(value2);
                        if (value2.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE && Values.isVectorValue(value2)) {
                            return EvaluateResult.INSTANCE.m718long(GenericsKt.vectorLengthImpl(value2));
                        }
                        return EvaluateResultError.INSTANCE;
                    } catch (Exception unused) {
                        return EvaluateResultError.INSTANCE;
                    }
                }
            };
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateCosineDistance = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.VectorKt$special$$inlined$binaryVectorVectorFunction$1
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            if (params.size() != 2) {
                throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
            }
            final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
            final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.VectorKt$special$$inlined$binaryVectorVectorFunction$1.1
                @Override // kotlin.jvm.functions.Function1
                public final EvaluateResult invoke(MutableDocument input) {
                    double[] vectorValue;
                    Intrinsics.checkNotNullParameter(input, "input");
                    EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                    if (evaluateResult.getIsError()) {
                        return EvaluateResultError.INSTANCE;
                    }
                    EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                    if (evaluateResult2.getIsError()) {
                        return EvaluateResultError.INSTANCE;
                    }
                    try {
                        Value value = evaluateResult.getValue();
                        Value value2 = evaluateResult2.getValue();
                        double[] vectorValue2 = null;
                        if (value == null || Values.isNullValue(value)) {
                            vectorValue = null;
                        } else {
                            if (!Values.isVectorValue(value)) {
                                return EvaluateResultError.INSTANCE;
                            }
                            vectorValue = Values.getVectorValue(value);
                        }
                        if (value2 != null && !Values.isNullValue(value2)) {
                            if (!Values.isVectorValue(value2)) {
                                return EvaluateResultError.INSTANCE;
                            }
                            vectorValue2 = Values.getVectorValue(value2);
                        }
                        if (vectorValue == null || vectorValue2 == null) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        try {
                            return VectorKt.cosineDistance(vectorValue, vectorValue2);
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    } catch (Exception unused2) {
                        return EvaluateResultError.INSTANCE;
                    }
                }
            };
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateDotProductDistance = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.VectorKt$special$$inlined$binaryVectorVectorFunction$2
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            if (params.size() != 2) {
                throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
            }
            final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
            final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.VectorKt$special$$inlined$binaryVectorVectorFunction$2.1
                @Override // kotlin.jvm.functions.Function1
                public final EvaluateResult invoke(MutableDocument input) {
                    double[] vectorValue;
                    Intrinsics.checkNotNullParameter(input, "input");
                    EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                    if (evaluateResult.getIsError()) {
                        return EvaluateResultError.INSTANCE;
                    }
                    EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                    if (evaluateResult2.getIsError()) {
                        return EvaluateResultError.INSTANCE;
                    }
                    try {
                        Value value = evaluateResult.getValue();
                        Value value2 = evaluateResult2.getValue();
                        double[] vectorValue2 = null;
                        if (value == null || Values.isNullValue(value)) {
                            vectorValue = null;
                        } else {
                            if (!Values.isVectorValue(value)) {
                                return EvaluateResultError.INSTANCE;
                            }
                            vectorValue = Values.getVectorValue(value);
                        }
                        if (value2 != null && !Values.isNullValue(value2)) {
                            if (!Values.isVectorValue(value2)) {
                                return EvaluateResultError.INSTANCE;
                            }
                            vectorValue2 = Values.getVectorValue(value2);
                        }
                        if (vectorValue == null || vectorValue2 == null) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        try {
                            return VectorKt.dotProductDistance(vectorValue, vectorValue2);
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    } catch (Exception unused2) {
                        return EvaluateResultError.INSTANCE;
                    }
                }
            };
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateEuclideanDistance = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.VectorKt$special$$inlined$binaryVectorVectorFunction$3
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            if (params.size() != 2) {
                throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
            }
            final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
            final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.VectorKt$special$$inlined$binaryVectorVectorFunction$3.1
                @Override // kotlin.jvm.functions.Function1
                public final EvaluateResult invoke(MutableDocument input) {
                    double[] vectorValue;
                    Intrinsics.checkNotNullParameter(input, "input");
                    EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                    if (evaluateResult.getIsError()) {
                        return EvaluateResultError.INSTANCE;
                    }
                    EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                    if (evaluateResult2.getIsError()) {
                        return EvaluateResultError.INSTANCE;
                    }
                    try {
                        Value value = evaluateResult.getValue();
                        Value value2 = evaluateResult2.getValue();
                        double[] vectorValue2 = null;
                        if (value == null || Values.isNullValue(value)) {
                            vectorValue = null;
                        } else {
                            if (!Values.isVectorValue(value)) {
                                return EvaluateResultError.INSTANCE;
                            }
                            vectorValue = Values.getVectorValue(value);
                        }
                        if (value2 != null && !Values.isNullValue(value2)) {
                            if (!Values.isVectorValue(value2)) {
                                return EvaluateResultError.INSTANCE;
                            }
                            vectorValue2 = Values.getVectorValue(value2);
                        }
                        if (vectorValue == null || vectorValue2 == null) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        try {
                            return VectorKt.euclideanDistance(vectorValue, vectorValue2);
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    } catch (Exception unused2) {
                        return EvaluateResultError.INSTANCE;
                    }
                }
            };
        }
    };

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateVectorLength() {
        return evaluateVectorLength;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateCosineDistance() {
        return evaluateCosineDistance;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateDotProductDistance() {
        return evaluateDotProductDistance;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateEuclideanDistance() {
        return evaluateEuclideanDistance;
    }

    public static final EvaluateResult cosineDistance(double[] vector1, double[] vector2) {
        Intrinsics.checkNotNullParameter(vector1, "vector1");
        Intrinsics.checkNotNullParameter(vector2, "vector2");
        if (vector1.length != vector2.length) {
            return EvaluateResultError.INSTANCE;
        }
        int length = vector1.length & (-4);
        double dFma = AudioStats.AUDIO_AMPLITUDE_NONE;
        double dFma2 = 0.0d;
        double dFma3 = 0.0d;
        double dFma4 = 0.0d;
        double dFma5 = 0.0d;
        double dFma6 = 0.0d;
        double dFma7 = 0.0d;
        double dFma8 = 0.0d;
        double dFma9 = 0.0d;
        double dFma10 = 0.0d;
        double dFma11 = 0.0d;
        double dFma12 = 0.0d;
        int i = 0;
        while (i < length) {
            dFma2 = fma(vector1[i], vector2[i], dFma2);
            int i2 = i + 1;
            dFma = fma(vector1[i2], vector2[i2], dFma);
            int i3 = i + 2;
            int i4 = i;
            dFma3 = fma(vector1[i3], vector2[i3], dFma3);
            int i5 = i4 + 3;
            dFma4 = fma(vector1[i5], vector2[i5], dFma4);
            double d = vector1[i4];
            dFma5 = fma(d, d, dFma5);
            double d2 = vector1[i2];
            dFma6 = fma(d2, d2, dFma6);
            double d3 = vector1[i3];
            dFma7 = fma(d3, d3, dFma7);
            double d4 = vector1[i5];
            dFma8 = fma(d4, d4, dFma8);
            double d5 = vector2[i4];
            dFma9 = fma(d5, d5, dFma9);
            double d6 = vector2[i2];
            dFma10 = fma(d6, d6, dFma10);
            double d7 = vector2[i3];
            dFma11 = fma(d7, d7, dFma11);
            double d8 = vector2[i5];
            dFma12 = fma(d8, d8, dFma12);
            i = i4 + 4;
        }
        double d9 = dFma2 + dFma + dFma3 + dFma4;
        double d10 = dFma5 + dFma6 + dFma7 + dFma8;
        double d11 = dFma9 + dFma10 + dFma11 + dFma12;
        int length2 = vector1.length;
        while (length < length2) {
            double d12 = vector1[length];
            double d13 = vector2[length];
            d9 += d12 * d13;
            d10 += d12 * d12;
            d11 += d13 * d13;
            length++;
        }
        double dSqrt = 1.0d - (d9 / Math.sqrt(d10 * d11));
        return Double.isNaN(dSqrt) ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.m716double(dSqrt);
    }

    public static final EvaluateResult euclideanDistance(double[] vector1, double[] vector2) {
        Intrinsics.checkNotNullParameter(vector1, "vector1");
        Intrinsics.checkNotNullParameter(vector2, "vector2");
        if (vector1.length != vector2.length) {
            return EvaluateResultError.INSTANCE;
        }
        int length = vector1.length & (-4);
        double dFma = AudioStats.AUDIO_AMPLITUDE_NONE;
        double dFma2 = 0.0d;
        double dFma3 = 0.0d;
        double dFma4 = 0.0d;
        for (int i = 0; i < length; i += 4) {
            double d = vector1[i] - vector2[i];
            int i2 = i + 1;
            double d2 = vector1[i2] - vector2[i2];
            int i3 = i + 2;
            double d3 = vector1[i3] - vector2[i3];
            int i4 = i + 3;
            double d4 = vector1[i4] - vector2[i4];
            dFma2 = fma(d, d, dFma2);
            dFma = fma(d2, d2, dFma);
            dFma3 = fma(d3, d3, dFma3);
            dFma4 = fma(d4, d4, dFma4);
        }
        double d5 = dFma2 + dFma + dFma3 + dFma4;
        int length2 = vector1.length;
        double dFma5 = d5;
        while (length < length2) {
            double d6 = vector1[length] - vector2[length];
            dFma5 = fma(d6, d6, dFma5);
            length++;
        }
        return EvaluateResult.INSTANCE.m716double(Math.sqrt(dFma5));
    }

    public static final EvaluateResult dotProductDistance(double[] vector1, double[] vector2) {
        Intrinsics.checkNotNullParameter(vector1, "vector1");
        Intrinsics.checkNotNullParameter(vector2, "vector2");
        if (vector1.length != vector2.length) {
            return EvaluateResultError.INSTANCE;
        }
        int length = vector1.length & (-4);
        double dFma = AudioStats.AUDIO_AMPLITUDE_NONE;
        double dFma2 = 0.0d;
        double dFma3 = 0.0d;
        double dFma4 = 0.0d;
        int i = 0;
        while (i < length) {
            dFma2 = fma(vector1[i], vector2[i], dFma2);
            int i2 = i + 1;
            dFma = fma(vector1[i2], vector2[i2], dFma);
            int i3 = i + 2;
            double d = vector1[i3];
            double d2 = vector2[i3];
            int i4 = i;
            dFma3 = fma(d, d2, dFma3);
            int i5 = i4 + 3;
            dFma4 = fma(vector1[i5], vector2[i5], dFma4);
            i = i4 + 4;
        }
        double d3 = dFma2 + dFma + dFma3 + dFma4;
        int length2 = vector1.length;
        while (length < length2) {
            d3 += vector1[length] * vector2[length];
            length++;
        }
        return EvaluateResult.INSTANCE.m716double(d3);
    }

    private static final double fma(double d, double d2, double d3) {
        return Build.VERSION.SDK_INT >= 33 ? nativeFma(d, d2, d3) : (d * d2) + d3;
    }

    private static final double nativeFma(double d, double d2, double d3) {
        return Math.fma(d, d2, d3);
    }
}
