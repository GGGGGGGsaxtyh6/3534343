package com.google.firebase.firestore.pipeline.evaluation;

import androidx.camera.video.AudioStats;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.common.math.DoubleMath;
import com.google.common.math.LongMath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResult;
import com.google.firebase.firestore.pipeline.evaluation.UtilsKt;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: Arithmetic.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\"w\u0010\u0000\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\"w\u0010\r\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\"w\u0010\u000f\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f\"w\u0010\u0011\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\"w\u0010\u0013\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\f\"w\u0010\u0015\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\f\"w\u0010\u0017\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\f\"w\u0010\u0019\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\f\"w\u0010\u001b\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\f\"w\u0010\u001d\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\f\"w\u0010\u001f\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\f\"w\u0010!\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\f\"w\u0010#\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\f\"w\u0010%\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\f\"w\u0010'\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\f\"w\u0010)\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\f¨\u0006+"}, d2 = {"evaluateAdd", "Lkotlin/Function1;", "", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lkotlin/ParameterName;", "name", "input", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateDocument;", "params", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateFunction;", "getEvaluateAdd", "()Lkotlin/jvm/functions/Function1;", "evaluateCeil", "getEvaluateCeil", "evaluateDivide", "getEvaluateDivide", "evaluateFloor", "getEvaluateFloor", "evaluateMod", "getEvaluateMod", "evaluateMultiply", "getEvaluateMultiply", "evaluatePow", "getEvaluatePow", "evaluateRound", "getEvaluateRound", "evaluateRoundToPrecision", "getEvaluateRoundToPrecision", "evaluateAbs", "getEvaluateAbs", "evaluateExp", "getEvaluateExp", "evaluateLn", "getEvaluateLn", "evaluateLog", "getEvaluateLog", "evaluateLog10", "getEvaluateLog10", "evaluateSqrt", "getEvaluateSqrt", "evaluateSubtract", "getEvaluateSubtract", "com.google.firebase-firebase-firestore"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ArithmeticKt {
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateAbs;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateAdd = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$1
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            if (params.size() != 2) {
                throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
            }
            final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
            final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$1.1
                @Override // kotlin.jvm.functions.Function1
                public final EvaluateResult invoke(MutableDocument input) {
                    LongValue longValue;
                    Intrinsics.checkNotNullParameter(input, "input");
                    EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                    if (evaluateResult.isError()) {
                        return EvaluateResultError.INSTANCE;
                    }
                    EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                    if (evaluateResult2.isError()) {
                        return EvaluateResultError.INSTANCE;
                    }
                    try {
                        Value value = evaluateResult.getValue();
                        Value value2 = evaluateResult2.getValue();
                        LongValue longValue2 = null;
                        Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                        if (i == -1 || i == 1) {
                            longValue = null;
                        } else if (i == 5) {
                            longValue = new LongValue(value.getIntegerValue());
                        } else if (i == 6) {
                            longValue = new DoubleValue(value.getDoubleValue());
                        } else {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value.ValueTypeCase valueTypeCase2 = value2 != null ? value2.getValueTypeCase() : null;
                        int i2 = valueTypeCase2 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase2.ordinal()];
                        if (i2 != -1 && i2 != 1) {
                            if (i2 == 5) {
                                longValue2 = new LongValue(value2.getIntegerValue());
                            } else if (i2 == 6) {
                                longValue2 = new DoubleValue(value2.getDoubleValue());
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        if (longValue != null && longValue2 != null) {
                            if (longValue instanceof LongValue) {
                                if (longValue2 instanceof LongValue) {
                                    return EvaluateResult.INSTANCE.m718long(LongMath.checkedAdd(((LongValue) longValue).getValue(), ((LongValue) longValue2).getValue()));
                                }
                                if (!(longValue2 instanceof DoubleValue)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                return EvaluateResult.INSTANCE.m716double(((LongValue) longValue).getValue() + ((DoubleValue) longValue2).getValue());
                            }
                            if (!(longValue instanceof DoubleValue)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            if (longValue2 instanceof DoubleValue) {
                                return EvaluateResult.INSTANCE.m716double(((DoubleValue) longValue).getValue() + ((DoubleValue) longValue2).getValue());
                            }
                            if (longValue2 instanceof LongValue) {
                                return EvaluateResult.INSTANCE.m716double(((DoubleValue) longValue).getValue() + ((LongValue) longValue2).getValue());
                            }
                            return EvaluateResultError.INSTANCE;
                        }
                        return EvaluateResult.INSTANCE.getNULL();
                    } catch (Exception unused) {
                        return EvaluateResultError.INSTANCE;
                    }
                }
            };
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateCeil;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateDivide;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateExp;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateFloor;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateLn;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateLog;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateLog10;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateMod;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateMultiply;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluatePow;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateRound;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateRoundToPrecision;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateSqrt;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateSubtract;

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateAdd() {
        return evaluateAdd;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateCeil() {
        return evaluateCeil;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateDivide() {
        return evaluateDivide;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateFloor() {
        return evaluateFloor;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateMod() {
        return evaluateMod;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateMultiply() {
        return evaluateMultiply;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluatePow() {
        return evaluatePow;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateRound() {
        return evaluateRound;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateRoundToPrecision() {
        return evaluateRoundToPrecision;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateAbs() {
        return evaluateAbs;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateExp() {
        return evaluateExp;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateLn() {
        return evaluateLn;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateLog() {
        return evaluateLog;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateLog10() {
        return evaluateLog10;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateSqrt() {
        return evaluateSqrt;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateSubtract() {
        return evaluateSubtract;
    }

    static {
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase2 = Value.ValueTypeCase.DOUBLE_VALUE;
        evaluateCeil = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase3 = valueTypeCase;
                final Value.ValueTypeCase valueTypeCase4 = valueTypeCase2;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase5 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase5 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase5 != valueTypeCase3) {
                            if (valueTypeCase5 != valueTypeCase4) {
                                return EvaluateResultError.INSTANCE;
                            }
                            try {
                                return EvaluateResult.INSTANCE.m716double(Math.ceil(value.getDoubleValue()));
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            return EvaluateResult.INSTANCE.m718long(value.getIntegerValue());
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        evaluateDivide = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$3
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$3.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        LongValue longValue;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            LongValue longValue2 = null;
                            Value.ValueTypeCase valueTypeCase3 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase3 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()];
                            if (i == -1 || i == 1) {
                                longValue = null;
                            } else if (i == 5) {
                                longValue = new LongValue(value.getIntegerValue());
                            } else if (i == 6) {
                                longValue = new DoubleValue(value.getDoubleValue());
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value.ValueTypeCase valueTypeCase4 = value2 != null ? value2.getValueTypeCase() : null;
                            int i2 = valueTypeCase4 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase4.ordinal()];
                            if (i2 != -1 && i2 != 1) {
                                if (i2 == 5) {
                                    longValue2 = new LongValue(value2.getIntegerValue());
                                } else if (i2 == 6) {
                                    longValue2 = new DoubleValue(value2.getDoubleValue());
                                } else {
                                    return EvaluateResultError.INSTANCE;
                                }
                            }
                            if (longValue != null && longValue2 != null) {
                                if (longValue instanceof LongValue) {
                                    if (longValue2 instanceof LongValue) {
                                        return EvaluateResult.INSTANCE.m718long(((LongValue) longValue).getValue() / ((LongValue) longValue2).getValue());
                                    }
                                    if (!(longValue2 instanceof DoubleValue)) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    return EvaluateResult.INSTANCE.m716double(((LongValue) longValue).getValue() / ((DoubleValue) longValue2).getValue());
                                }
                                if (!(longValue instanceof DoubleValue)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                if (longValue2 instanceof DoubleValue) {
                                    return EvaluateResult.INSTANCE.m716double(((DoubleValue) longValue).getValue() / ((DoubleValue) longValue2).getValue());
                                }
                                if (longValue2 instanceof LongValue) {
                                    return EvaluateResult.INSTANCE.m716double(((DoubleValue) longValue).getValue() / ((LongValue) longValue2).getValue());
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase3 = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase4 = Value.ValueTypeCase.DOUBLE_VALUE;
        evaluateFloor = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$4
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase5 = valueTypeCase3;
                final Value.ValueTypeCase valueTypeCase6 = valueTypeCase4;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$4.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase7 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase7 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase7.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase7 != valueTypeCase5) {
                            if (valueTypeCase7 != valueTypeCase6) {
                                return EvaluateResultError.INSTANCE;
                            }
                            try {
                                return EvaluateResult.INSTANCE.m716double(Math.floor(value.getDoubleValue()));
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            return EvaluateResult.INSTANCE.m718long(value.getIntegerValue());
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        evaluateMod = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$5
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$5.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        LongValue longValue;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            LongValue longValue2 = null;
                            Value.ValueTypeCase valueTypeCase5 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase5 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                            if (i == -1 || i == 1) {
                                longValue = null;
                            } else if (i == 5) {
                                longValue = new LongValue(value.getIntegerValue());
                            } else if (i == 6) {
                                longValue = new DoubleValue(value.getDoubleValue());
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value.ValueTypeCase valueTypeCase6 = value2 != null ? value2.getValueTypeCase() : null;
                            int i2 = valueTypeCase6 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase6.ordinal()];
                            if (i2 != -1 && i2 != 1) {
                                if (i2 == 5) {
                                    longValue2 = new LongValue(value2.getIntegerValue());
                                } else if (i2 == 6) {
                                    longValue2 = new DoubleValue(value2.getDoubleValue());
                                } else {
                                    return EvaluateResultError.INSTANCE;
                                }
                            }
                            if (longValue != null && longValue2 != null) {
                                if (longValue instanceof LongValue) {
                                    if (longValue2 instanceof LongValue) {
                                        return EvaluateResult.INSTANCE.m718long(((LongValue) longValue).getValue() % ((LongValue) longValue2).getValue());
                                    }
                                    if (!(longValue2 instanceof DoubleValue)) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    return EvaluateResult.INSTANCE.m716double(((LongValue) longValue).getValue() % ((DoubleValue) longValue2).getValue());
                                }
                                if (!(longValue instanceof DoubleValue)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                if (longValue2 instanceof DoubleValue) {
                                    return EvaluateResult.INSTANCE.m716double(((DoubleValue) longValue).getValue() % ((DoubleValue) longValue2).getValue());
                                }
                                if (longValue2 instanceof LongValue) {
                                    return EvaluateResult.INSTANCE.m716double(((DoubleValue) longValue).getValue() % ((LongValue) longValue2).getValue());
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        evaluateMultiply = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$6
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$6.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        LongValue longValue;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            LongValue longValue2 = null;
                            Value.ValueTypeCase valueTypeCase5 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase5 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                            if (i == -1 || i == 1) {
                                longValue = null;
                            } else if (i == 5) {
                                longValue = new LongValue(value.getIntegerValue());
                            } else if (i == 6) {
                                longValue = new DoubleValue(value.getDoubleValue());
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value.ValueTypeCase valueTypeCase6 = value2 != null ? value2.getValueTypeCase() : null;
                            int i2 = valueTypeCase6 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase6.ordinal()];
                            if (i2 != -1 && i2 != 1) {
                                if (i2 == 5) {
                                    longValue2 = new LongValue(value2.getIntegerValue());
                                } else if (i2 == 6) {
                                    longValue2 = new DoubleValue(value2.getDoubleValue());
                                } else {
                                    return EvaluateResultError.INSTANCE;
                                }
                            }
                            if (longValue != null && longValue2 != null) {
                                if (longValue instanceof LongValue) {
                                    if (longValue2 instanceof LongValue) {
                                        return EvaluateResult.INSTANCE.m718long(LongMath.checkedMultiply(((LongValue) longValue).getValue(), ((LongValue) longValue2).getValue()));
                                    }
                                    if (!(longValue2 instanceof DoubleValue)) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    return EvaluateResult.INSTANCE.m716double(((LongValue) longValue).getValue() * ((DoubleValue) longValue2).getValue());
                                }
                                if (!(longValue instanceof DoubleValue)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                if (longValue2 instanceof DoubleValue) {
                                    return EvaluateResult.INSTANCE.m716double(((DoubleValue) longValue).getValue() * ((DoubleValue) longValue2).getValue());
                                }
                                if (longValue2 instanceof LongValue) {
                                    return EvaluateResult.INSTANCE.m716double(((DoubleValue) longValue).getValue() * ((LongValue) longValue2).getValue());
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        evaluatePow = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$1.1
                    /* JADX WARN: Code restructure failed: missing block: B:83:0x012a, code lost:
                    
                        if (r3 < androidx.camera.video.AudioStats.AUDIO_AMPLITUDE_NONE) goto L84;
                     */
                    @Override // kotlin.jvm.functions.Function1
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final EvaluateResult invoke(MutableDocument input) {
                        LongValue longValue;
                        EvaluateResult.Companion companion;
                        EvaluateResult.Companion companion2;
                        double dPow;
                        EvaluateResult.Companion companion3;
                        EvaluateResult.Companion companion4;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            LongValue longValue2 = null;
                            Value.ValueTypeCase valueTypeCase5 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase5 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                            if (i == -1 || i == 1) {
                                longValue = null;
                            } else if (i == 5) {
                                longValue = new LongValue(value.getIntegerValue());
                            } else {
                                if (i != 6) {
                                    return EvaluateResultError.INSTANCE;
                                }
                                longValue = new DoubleValue(value.getDoubleValue());
                            }
                            Value.ValueTypeCase valueTypeCase6 = value2 != null ? value2.getValueTypeCase() : null;
                            int i2 = valueTypeCase6 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase6.ordinal()];
                            if (i2 != -1 && i2 != 1) {
                                if (i2 == 5) {
                                    longValue2 = new LongValue(value2.getIntegerValue());
                                } else {
                                    if (i2 != 6) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                    longValue2 = new DoubleValue(value2.getDoubleValue());
                                }
                            }
                            if (longValue != null && longValue2 != null) {
                                if (longValue instanceof LongValue) {
                                    if (longValue2 instanceof LongValue) {
                                        double value3 = ((LongValue) longValue).getValue();
                                        double value4 = ((LongValue) longValue2).getValue();
                                        if (value4 != AudioStats.AUDIO_AMPLITUDE_NONE && value3 != 1.0d) {
                                            if (value3 == -1.0d && Double.isInfinite(value4)) {
                                                companion3 = EvaluateResult.INSTANCE;
                                                return companion3.m716double(1.0d);
                                            }
                                            if (!Double.isNaN(value4) && !Double.isNaN(value3)) {
                                                if (value3 >= AudioStats.AUDIO_AMPLITUDE_NONE || Double.isInfinite(value3) || Double.isNaN(value3) || DoubleMath.isMathematicalInteger(value4)) {
                                                    if (value3 == AudioStats.AUDIO_AMPLITUDE_NONE || value3 == -0.0d) {
                                                    }
                                                    companion2 = EvaluateResult.INSTANCE;
                                                    dPow = Math.pow(value3, value4);
                                                    return companion2.m716double(dPow);
                                                }
                                                return EvaluateResultError.INSTANCE;
                                            }
                                            companion = EvaluateResult.INSTANCE;
                                            return companion.m716double(Double.NaN);
                                        }
                                        companion4 = EvaluateResult.INSTANCE;
                                        return companion4.m716double(1.0d);
                                    }
                                    if (!(longValue2 instanceof DoubleValue)) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    double value5 = ((LongValue) longValue).getValue();
                                    double value6 = ((DoubleValue) longValue2).getValue();
                                    if (value6 != AudioStats.AUDIO_AMPLITUDE_NONE && value5 != 1.0d) {
                                        if (value5 == -1.0d && Double.isInfinite(value6)) {
                                            companion3 = EvaluateResult.INSTANCE;
                                            return companion3.m716double(1.0d);
                                        }
                                        if (!Double.isNaN(value6) && !Double.isNaN(value5)) {
                                            if (value5 < AudioStats.AUDIO_AMPLITUDE_NONE && !Double.isInfinite(value5) && !Double.isNaN(value5) && !DoubleMath.isMathematicalInteger(value6)) {
                                                return EvaluateResultError.INSTANCE;
                                            }
                                            if (value5 == AudioStats.AUDIO_AMPLITUDE_NONE || value5 == -0.0d) {
                                                if (value6 < AudioStats.AUDIO_AMPLITUDE_NONE) {
                                                    return EvaluateResultError.INSTANCE;
                                                }
                                            }
                                            companion2 = EvaluateResult.INSTANCE;
                                            dPow = Math.pow(value5, value6);
                                            return companion2.m716double(dPow);
                                        }
                                        companion = EvaluateResult.INSTANCE;
                                        return companion.m716double(Double.NaN);
                                    }
                                    companion4 = EvaluateResult.INSTANCE;
                                    return companion4.m716double(1.0d);
                                }
                                if (!(longValue instanceof DoubleValue)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                if (longValue2 instanceof DoubleValue) {
                                    double value7 = ((DoubleValue) longValue).getValue();
                                    double value8 = ((DoubleValue) longValue2).getValue();
                                    if (value8 != AudioStats.AUDIO_AMPLITUDE_NONE && value7 != 1.0d) {
                                        if (value7 == -1.0d && Double.isInfinite(value8)) {
                                            companion3 = EvaluateResult.INSTANCE;
                                            return companion3.m716double(1.0d);
                                        }
                                        if (!Double.isNaN(value8) && !Double.isNaN(value7)) {
                                            if (value7 < AudioStats.AUDIO_AMPLITUDE_NONE && !Double.isInfinite(value7) && !Double.isNaN(value7) && !DoubleMath.isMathematicalInteger(value8)) {
                                                return EvaluateResultError.INSTANCE;
                                            }
                                            if (value7 == AudioStats.AUDIO_AMPLITUDE_NONE || value7 == -0.0d) {
                                                if (value8 < AudioStats.AUDIO_AMPLITUDE_NONE) {
                                                    return EvaluateResultError.INSTANCE;
                                                }
                                            }
                                            companion2 = EvaluateResult.INSTANCE;
                                            dPow = Math.pow(value7, value8);
                                            return companion2.m716double(dPow);
                                        }
                                        companion = EvaluateResult.INSTANCE;
                                        return companion.m716double(Double.NaN);
                                    }
                                    companion4 = EvaluateResult.INSTANCE;
                                    return companion4.m716double(1.0d);
                                }
                                if (!(longValue2 instanceof LongValue)) {
                                    return EvaluateResultError.INSTANCE;
                                }
                                double value9 = ((DoubleValue) longValue).getValue();
                                double value10 = ((LongValue) longValue2).getValue();
                                if (value10 != AudioStats.AUDIO_AMPLITUDE_NONE && value9 != 1.0d) {
                                    if (value9 == -1.0d && Double.isInfinite(value10)) {
                                        companion3 = EvaluateResult.INSTANCE;
                                        return companion3.m716double(1.0d);
                                    }
                                    if (!Double.isNaN(value10) && !Double.isNaN(value9)) {
                                        if (value9 < AudioStats.AUDIO_AMPLITUDE_NONE && !Double.isInfinite(value9) && !Double.isNaN(value9) && !DoubleMath.isMathematicalInteger(value10)) {
                                            return EvaluateResultError.INSTANCE;
                                        }
                                        if (value9 == AudioStats.AUDIO_AMPLITUDE_NONE || value9 == -0.0d) {
                                            if (value10 < AudioStats.AUDIO_AMPLITUDE_NONE) {
                                                return EvaluateResultError.INSTANCE;
                                            }
                                        }
                                        companion2 = EvaluateResult.INSTANCE;
                                        dPow = Math.pow(value9, value10);
                                        return companion2.m716double(dPow);
                                    }
                                    companion = EvaluateResult.INSTANCE;
                                    return companion.m716double(Double.NaN);
                                }
                                companion4 = EvaluateResult.INSTANCE;
                                return companion4.m716double(1.0d);
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase5 = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase6 = Value.ValueTypeCase.DOUBLE_VALUE;
        evaluateRound = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$7
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase7 = valueTypeCase5;
                final Value.ValueTypeCase valueTypeCase8 = valueTypeCase6;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$7.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase9 = value != null ? value.getValueTypeCase() : null;
                        int i = -1;
                        int i2 = valueTypeCase9 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase9.ordinal()];
                        if (i2 == -1 || i2 == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase9 != valueTypeCase7) {
                            if (valueTypeCase9 != valueTypeCase8) {
                                return EvaluateResultError.INSTANCE;
                            }
                            try {
                                double doubleValue = value.getDoubleValue();
                                EvaluateResult.Companion companion = EvaluateResult.INSTANCE;
                                if (!Double.isInfinite(doubleValue) && !Double.isNaN(doubleValue)) {
                                    double d = doubleValue % ((double) 1);
                                    double d2 = doubleValue - d;
                                    if (Math.abs(d) >= 0.5d) {
                                        if (doubleValue >= AudioStats.AUDIO_AMPLITUDE_NONE) {
                                            i = 1;
                                        }
                                        doubleValue = d2 + ((double) i);
                                    } else {
                                        doubleValue = d2;
                                    }
                                }
                                return companion.m716double(doubleValue);
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            return EvaluateResult.INSTANCE.m718long(value.getIntegerValue());
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        evaluateRoundToPrecision = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticNumberLong$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticNumberLong$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        LongValue longValue;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            Long lValueOf = null;
                            Value.ValueTypeCase valueTypeCase7 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase7 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase7.ordinal()];
                            if (i == -1 || i == 1) {
                                longValue = null;
                            } else if (i == 5) {
                                longValue = new LongValue(value.getIntegerValue());
                            } else if (i == 6) {
                                longValue = new DoubleValue(value.getDoubleValue());
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value.ValueTypeCase valueTypeCase8 = value2 != null ? value2.getValueTypeCase() : null;
                            int i2 = valueTypeCase8 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase8.ordinal()];
                            if (i2 != -1 && i2 != 1) {
                                if (i2 == 5) {
                                    lValueOf = Long.valueOf(value2.getIntegerValue());
                                } else if (i2 == 6) {
                                    lValueOf = Long.valueOf((long) value2.getDoubleValue());
                                } else {
                                    return EvaluateResultError.INSTANCE;
                                }
                            }
                            if (longValue != null && lValueOf != null) {
                                if (!(longValue instanceof LongValue)) {
                                    if (!(longValue instanceof DoubleValue)) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    double value3 = ((DoubleValue) longValue).getValue();
                                    long jLongValue = lValueOf.longValue();
                                    if (jLongValue >= 16 || Double.isInfinite(value3) || Double.isNaN(value3)) {
                                        return EvaluateResult.INSTANCE.m716double(value3);
                                    }
                                    if ((-jLongValue) >= ((long) Math.floor(Math.log10(Math.abs(value3)))) + 1) {
                                        return EvaluateResult.INSTANCE.getDOUBLE_ZERO();
                                    }
                                    BigDecimal scale = BigDecimal.valueOf(value3).setScale((int) jLongValue, RoundingMode.HALF_UP);
                                    Intrinsics.checkNotNullExpressionValue(scale, "setScale(...)");
                                    double dDoubleValue = scale.doubleValue();
                                    return (Double.isInfinite(dDoubleValue) || Double.isNaN(dDoubleValue)) ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.m716double(dDoubleValue);
                                }
                                long value4 = ((LongValue) longValue).getValue();
                                long jLongValue2 = lValueOf.longValue();
                                if (jLongValue2 >= 0) {
                                    return EvaluateResult.INSTANCE.m718long(value4);
                                }
                                if ((-jLongValue2) >= ((long) Math.floor(Math.log10(Math.abs(value4)))) + 1) {
                                    return EvaluateResult.INSTANCE.getLONG_ZERO();
                                }
                                long jPow = (long) Math.pow(10.0d, -jLongValue2);
                                long j = value4 - (value4 % jPow);
                                if (Math.abs(j) < Math.abs(jPow / ((long) 2))) {
                                    return EvaluateResult.INSTANCE.m718long(j);
                                }
                                if (value4 < 0) {
                                    if (value4 < (-9223372036854775807L) + jPow) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                    return EvaluateResult.INSTANCE.m718long(j - jPow);
                                }
                                if (value4 > Long.MAX_VALUE - jPow) {
                                    return EvaluateResultError.INSTANCE;
                                }
                                return EvaluateResult.INSTANCE.m718long(j + jPow);
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase7 = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase8 = Value.ValueTypeCase.DOUBLE_VALUE;
        evaluateAbs = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$8
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase9 = valueTypeCase7;
                final Value.ValueTypeCase valueTypeCase10 = valueTypeCase8;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$8.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase11 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase11 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase11.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase11 != valueTypeCase9) {
                            if (valueTypeCase11 != valueTypeCase10) {
                                return EvaluateResultError.INSTANCE;
                            }
                            try {
                                return EvaluateResult.INSTANCE.m716double(Math.abs(value.getDoubleValue()));
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            long integerValue = value.getIntegerValue();
                            EvaluateResult.Companion companion = EvaluateResult.INSTANCE;
                            if (integerValue != Long.MIN_VALUE) {
                                return companion.m718long(Math.abs(integerValue));
                            }
                            throw new ArithmeticException("long overflow");
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase9 = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase10 = Value.ValueTypeCase.DOUBLE_VALUE;
        evaluateExp = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase11 = valueTypeCase9;
                final Value.ValueTypeCase valueTypeCase12 = valueTypeCase10;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase13 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase13 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase13.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase13 != valueTypeCase11) {
                            if (valueTypeCase13 != valueTypeCase12) {
                                return EvaluateResultError.INSTANCE;
                            }
                            try {
                                double doubleValue = value.getDoubleValue();
                                if (Math.exp(doubleValue) == Double.POSITIVE_INFINITY && doubleValue != Double.POSITIVE_INFINITY) {
                                    throw new Exception("exp(...) exponent overflow");
                                }
                                return EvaluateResult.INSTANCE.m716double(Math.exp(doubleValue));
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            double integerValue = value.getIntegerValue();
                            if (Math.exp(integerValue) == Double.POSITIVE_INFINITY && integerValue != Double.POSITIVE_INFINITY) {
                                throw new Exception("exp(...) exponent overflow");
                            }
                            return EvaluateResult.INSTANCE.m716double(Math.exp(integerValue));
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase11 = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase12 = Value.ValueTypeCase.DOUBLE_VALUE;
        evaluateLn = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$3
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase13 = valueTypeCase11;
                final Value.ValueTypeCase valueTypeCase14 = valueTypeCase12;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$3.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase15 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase15 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase15.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase15 != valueTypeCase13) {
                            if (valueTypeCase15 != valueTypeCase14) {
                                return EvaluateResultError.INSTANCE;
                            }
                            try {
                                double doubleValue = value.getDoubleValue();
                                return doubleValue <= AudioStats.AUDIO_AMPLITUDE_NONE ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.m716double(Math.log(doubleValue));
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            double integerValue = value.getIntegerValue();
                            return integerValue <= AudioStats.AUDIO_AMPLITUDE_NONE ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.m716double(Math.log(integerValue));
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        evaluateLog = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$4
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$4.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        LongValue longValue;
                        EvaluateResult.Companion companion;
                        double dLog;
                        EvaluateResult.Companion companion2;
                        EvaluateResult.Companion companion3;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            LongValue longValue2 = null;
                            Value.ValueTypeCase valueTypeCase13 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase13 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase13.ordinal()];
                            if (i == -1 || i == 1) {
                                longValue = null;
                            } else if (i == 5) {
                                longValue = new LongValue(value.getIntegerValue());
                            } else {
                                if (i != 6) {
                                    return EvaluateResultError.INSTANCE;
                                }
                                longValue = new DoubleValue(value.getDoubleValue());
                            }
                            Value.ValueTypeCase valueTypeCase14 = value2 != null ? value2.getValueTypeCase() : null;
                            int i2 = valueTypeCase14 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase14.ordinal()];
                            if (i2 != -1 && i2 != 1) {
                                if (i2 == 5) {
                                    longValue2 = new LongValue(value2.getIntegerValue());
                                } else {
                                    if (i2 != 6) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                    longValue2 = new DoubleValue(value2.getDoubleValue());
                                }
                            }
                            if (longValue != null && longValue2 != null) {
                                if (longValue instanceof LongValue) {
                                    if (longValue2 instanceof LongValue) {
                                        double value3 = ((LongValue) longValue).getValue();
                                        double value4 = ((LongValue) longValue2).getValue();
                                        if (value3 == Double.NEGATIVE_INFINITY) {
                                            companion3 = EvaluateResult.INSTANCE;
                                            return companion3.m716double(Double.NaN);
                                        }
                                        if (value4 == Double.POSITIVE_INFINITY) {
                                            companion2 = EvaluateResult.INSTANCE;
                                            return companion2.m716double(Double.NaN);
                                        }
                                        if (value4 > AudioStats.AUDIO_AMPLITUDE_NONE && value3 > AudioStats.AUDIO_AMPLITUDE_NONE && value4 != 1.0d) {
                                            companion = EvaluateResult.INSTANCE;
                                            dLog = MathKt.log(value3, value4);
                                            return companion.m716double(dLog);
                                        }
                                        return EvaluateResultError.INSTANCE;
                                    }
                                    if (!(longValue2 instanceof DoubleValue)) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    double value5 = ((LongValue) longValue).getValue();
                                    double value6 = ((DoubleValue) longValue2).getValue();
                                    if (value5 == Double.NEGATIVE_INFINITY) {
                                        companion3 = EvaluateResult.INSTANCE;
                                        return companion3.m716double(Double.NaN);
                                    }
                                    if (value6 == Double.POSITIVE_INFINITY) {
                                        companion2 = EvaluateResult.INSTANCE;
                                        return companion2.m716double(Double.NaN);
                                    }
                                    if (value6 > AudioStats.AUDIO_AMPLITUDE_NONE && value5 > AudioStats.AUDIO_AMPLITUDE_NONE && value6 != 1.0d) {
                                        companion = EvaluateResult.INSTANCE;
                                        dLog = MathKt.log(value5, value6);
                                        return companion.m716double(dLog);
                                    }
                                    return EvaluateResultError.INSTANCE;
                                }
                                if (!(longValue instanceof DoubleValue)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                if (longValue2 instanceof DoubleValue) {
                                    double value7 = ((DoubleValue) longValue).getValue();
                                    double value8 = ((DoubleValue) longValue2).getValue();
                                    if (value7 == Double.NEGATIVE_INFINITY) {
                                        companion3 = EvaluateResult.INSTANCE;
                                        return companion3.m716double(Double.NaN);
                                    }
                                    if (value8 == Double.POSITIVE_INFINITY) {
                                        companion2 = EvaluateResult.INSTANCE;
                                        return companion2.m716double(Double.NaN);
                                    }
                                    if (value8 > AudioStats.AUDIO_AMPLITUDE_NONE && value7 > AudioStats.AUDIO_AMPLITUDE_NONE && value8 != 1.0d) {
                                        companion = EvaluateResult.INSTANCE;
                                        dLog = MathKt.log(value7, value8);
                                        return companion.m716double(dLog);
                                    }
                                    return EvaluateResultError.INSTANCE;
                                }
                                if (!(longValue2 instanceof LongValue)) {
                                    return EvaluateResultError.INSTANCE;
                                }
                                double value9 = ((DoubleValue) longValue).getValue();
                                double value10 = ((LongValue) longValue2).getValue();
                                if (value9 == Double.NEGATIVE_INFINITY) {
                                    companion3 = EvaluateResult.INSTANCE;
                                    return companion3.m716double(Double.NaN);
                                }
                                if (value10 == Double.POSITIVE_INFINITY) {
                                    companion2 = EvaluateResult.INSTANCE;
                                    return companion2.m716double(Double.NaN);
                                }
                                if (value10 > AudioStats.AUDIO_AMPLITUDE_NONE && value9 > AudioStats.AUDIO_AMPLITUDE_NONE && value10 != 1.0d) {
                                    companion = EvaluateResult.INSTANCE;
                                    dLog = MathKt.log(value9, value10);
                                    return companion.m716double(dLog);
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase13 = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase14 = Value.ValueTypeCase.DOUBLE_VALUE;
        evaluateLog10 = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$5
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase15 = valueTypeCase13;
                final Value.ValueTypeCase valueTypeCase16 = valueTypeCase14;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$5.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase17 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase17 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase17.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase17 != valueTypeCase15) {
                            if (valueTypeCase17 != valueTypeCase16) {
                                return EvaluateResultError.INSTANCE;
                            }
                            try {
                                double doubleValue = value.getDoubleValue();
                                return doubleValue <= AudioStats.AUDIO_AMPLITUDE_NONE ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.m716double(Math.log10(doubleValue));
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            double integerValue = value.getIntegerValue();
                            return integerValue <= AudioStats.AUDIO_AMPLITUDE_NONE ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.m716double(Math.log10(integerValue));
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase15 = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase16 = Value.ValueTypeCase.DOUBLE_VALUE;
        evaluateSqrt = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$6
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase17 = valueTypeCase15;
                final Value.ValueTypeCase valueTypeCase18 = valueTypeCase16;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmetic$6.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase19 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase19 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase19.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase19 != valueTypeCase17) {
                            if (valueTypeCase19 != valueTypeCase18) {
                                return EvaluateResultError.INSTANCE;
                            }
                            try {
                                double doubleValue = value.getDoubleValue();
                                return doubleValue < AudioStats.AUDIO_AMPLITUDE_NONE ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.m716double(Math.sqrt(doubleValue));
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            double integerValue = value.getIntegerValue();
                            return integerValue < AudioStats.AUDIO_AMPLITUDE_NONE ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.m716double(Math.sqrt(integerValue));
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        evaluateSubtract = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$9
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt$special$$inlined$arithmeticPrimitive$9.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        LongValue longValue;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.isError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            LongValue longValue2 = null;
                            Value.ValueTypeCase valueTypeCase17 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase17 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase17.ordinal()];
                            if (i == -1 || i == 1) {
                                longValue = null;
                            } else if (i == 5) {
                                longValue = new LongValue(value.getIntegerValue());
                            } else if (i == 6) {
                                longValue = new DoubleValue(value.getDoubleValue());
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value.ValueTypeCase valueTypeCase18 = value2 != null ? value2.getValueTypeCase() : null;
                            int i2 = valueTypeCase18 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase18.ordinal()];
                            if (i2 != -1 && i2 != 1) {
                                if (i2 == 5) {
                                    longValue2 = new LongValue(value2.getIntegerValue());
                                } else if (i2 == 6) {
                                    longValue2 = new DoubleValue(value2.getDoubleValue());
                                } else {
                                    return EvaluateResultError.INSTANCE;
                                }
                            }
                            if (longValue != null && longValue2 != null) {
                                if (longValue instanceof LongValue) {
                                    if (longValue2 instanceof LongValue) {
                                        return EvaluateResult.INSTANCE.m718long(LongMath.checkedSubtract(((LongValue) longValue).getValue(), ((LongValue) longValue2).getValue()));
                                    }
                                    if (!(longValue2 instanceof DoubleValue)) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    return EvaluateResult.INSTANCE.m716double(((LongValue) longValue).getValue() - ((DoubleValue) longValue2).getValue());
                                }
                                if (!(longValue instanceof DoubleValue)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                if (longValue2 instanceof DoubleValue) {
                                    return EvaluateResult.INSTANCE.m716double(((DoubleValue) longValue).getValue() - ((DoubleValue) longValue2).getValue());
                                }
                                if (longValue2 instanceof LongValue) {
                                    return EvaluateResult.INSTANCE.m716double(((DoubleValue) longValue).getValue() - ((LongValue) longValue2).getValue());
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }
}
