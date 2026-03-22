package com.google.firebase.firestore.pipeline.evaluation;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResult;
import com.google.firebase.firestore.pipeline.evaluation.LogicalKt;
import com.google.firebase.firestore.pipeline.evaluation.UtilsKt;
import com.google.firebase.firestore.pipeline.evaluation.UtilsKt$variadicFunction$2;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Logical.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\"w\u0010\u0000\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\"w\u0010\r\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\"w\u0010\u000f\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f\"w\u0010\u0011\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\"w\u0010\u0013\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\f\"w\u0010\u0015\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\f\"w\u0010\u0017\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\f\"w\u0010\u0019\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\f\"w\u0010\u001b\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\f\"w\u0010\u001d\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\f¨\u0006\u001f"}, d2 = {"evaluateAnd", "Lkotlin/Function1;", "", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lkotlin/ParameterName;", "name", "input", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateDocument;", "params", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateFunction;", "getEvaluateAnd", "()Lkotlin/jvm/functions/Function1;", "evaluateOr", "getEvaluateOr", "evaluateXor", "getEvaluateXor", "evaluateCond", "getEvaluateCond", "evaluateLogicalMaximum", "getEvaluateLogicalMaximum", "evaluateLogicalMinimum", "getEvaluateLogicalMinimum", "evaluateIsNaN", "getEvaluateIsNaN", "evaluateIsNotNaN", "getEvaluateIsNotNaN", "evaluateIsNull", "getEvaluateIsNull", "evaluateIsNotNull", "getEvaluateIsNotNull", "com.google.firebase-firebase-firestore"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LogicalKt {
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateIsNaN;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateIsNotNaN;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateIsNotNull;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateIsNull;
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateAnd = new Function1() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return LogicalKt.evaluateAnd$lambda$1((List) obj);
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateOr = new Function1() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return LogicalKt.evaluateOr$lambda$3((List) obj);
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateXor = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$variadicBooleanFunction$1
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(final List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$variadicBooleanFunction$1.1
                @Override // kotlin.jvm.functions.Function1
                public final EvaluateResult invoke(MutableDocument input) {
                    Intrinsics.checkNotNullParameter(input, "input");
                    int size = params.size();
                    boolean[] zArr = new boolean[size];
                    boolean z = false;
                    int i = 0;
                    for (Object obj : params) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        EvaluateResult evaluateResult = (EvaluateResult) ((Function1) obj).invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                        int i3 = valueTypeCase == null ? -1 : UtilsKt$variadicFunction$2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                        if (i3 == -1 || i3 == 1) {
                            z = true;
                        } else if (i3 == 2) {
                            zArr[i] = value.getBooleanValue();
                        } else {
                            return EvaluateResultError.INSTANCE;
                        }
                        i = i2;
                    }
                    if (z) {
                        return EvaluateResult.INSTANCE.getNULL();
                    }
                    try {
                        EvaluateResult.Companion companion = EvaluateResult.INSTANCE;
                        Boolean boolValueOf = false;
                        for (int i4 = 0; i4 < size; i4++) {
                            boolValueOf = Boolean.valueOf(boolValueOf.booleanValue() ^ zArr[i4]);
                        }
                        return companion.m715boolean(boolValueOf.booleanValue());
                    } catch (Exception unused) {
                        return EvaluateResultError.INSTANCE;
                    }
                }
            };
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateCond = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$ternaryLazyFunction$1
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            if (params.size() != 3) {
                throw Assert.fail("Function should have exactly 3 params, but %d were given.", Integer.valueOf(params.size()));
            }
            final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
            final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
            final Function1<? super MutableDocument, ? extends EvaluateResult> function13 = params.get(2);
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$ternaryLazyFunction$1.1
                @Override // kotlin.jvm.functions.Function1
                public final EvaluateResult invoke(final MutableDocument input) {
                    Intrinsics.checkNotNullParameter(input, "input");
                    final Function1 function14 = function1;
                    final Function1 function15 = function12;
                    final Function1 function16 = function13;
                    try {
                        Function0<EvaluateResult> function0 = new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$.inlined.ternaryLazyFunction.1.1.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final EvaluateResult invoke() {
                                return (EvaluateResult) function14.invoke(input);
                            }
                        };
                        Function0<EvaluateResult> function02 = new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$.inlined.ternaryLazyFunction.1.1.2
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final EvaluateResult invoke() {
                                return (EvaluateResult) function15.invoke(input);
                            }
                        };
                        Function0<EvaluateResult> function03 = new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$.inlined.ternaryLazyFunction.1.1.3
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final EvaluateResult invoke() {
                                return (EvaluateResult) function16.invoke(input);
                            }
                        };
                        Function0<EvaluateResult> function04 = function02;
                        EvaluateResult evaluateResultInvoke = function0.invoke();
                        if (evaluateResultInvoke.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResultInvoke.getValue();
                        Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase == null ? -1 : LogicalKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                        if (i == -1 || i == 1) {
                            return function03.invoke();
                        }
                        if (i == 2) {
                            return value.getBooleanValue() ? function04.invoke() : function03.invoke();
                        }
                        return EvaluateResultError.INSTANCE;
                    } catch (Exception unused) {
                        return EvaluateResultError.INSTANCE;
                    }
                }
            };
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateLogicalMaximum = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$variadicResultFunction$1
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(final List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$variadicResultFunction$1.1
                @Override // kotlin.jvm.functions.Function1
                public final EvaluateResult invoke(MutableDocument input) {
                    Intrinsics.checkNotNullParameter(input, "input");
                    List list = params;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add((EvaluateResult) ((Function1) it.next()).invoke(input));
                    }
                    ArrayList<EvaluateResult> arrayList2 = arrayList;
                    try {
                        if (arrayList2.size() < 2) {
                            return EvaluateResultError.INSTANCE;
                        }
                        LogicalKt$evaluateLogicalMaximum$1$maximum$1 logicalKt$evaluateLogicalMaximum$1$maximum$1 = new Function2<Value, Value, Value>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$evaluateLogicalMaximum$1$maximum$1
                            @Override // kotlin.jvm.functions.Function2
                            public final Value invoke(Value value, Value b) {
                                int iIntValue;
                                Intrinsics.checkNotNullParameter(b, "b");
                                return (value != null && ((iIntValue = ((Number) ((Function2) Values.Enterprise.INSTANCE.getCompare$com_google_firebase_firebase_firestore()).invoke(value, b)).intValue()) == 0 || iIntValue > 0)) ? value : b;
                            }
                        };
                        Value valueInvoke = null;
                        for (EvaluateResult evaluateResult : arrayList2) {
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase == null ? -1 : LogicalKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i != -1 && i != 1) {
                                valueInvoke = logicalKt$evaluateLogicalMaximum$1$maximum$1.invoke(valueInvoke, value);
                            }
                        }
                        return valueInvoke == null ? EvaluateResult.INSTANCE.getNULL() : EvaluateResult.INSTANCE.value(valueInvoke);
                    } catch (Exception unused) {
                        return EvaluateResultError.INSTANCE;
                    }
                }
            };
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateLogicalMinimum = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$variadicResultFunction$2
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(final List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$variadicResultFunction$2.1
                @Override // kotlin.jvm.functions.Function1
                public final EvaluateResult invoke(MutableDocument input) {
                    Intrinsics.checkNotNullParameter(input, "input");
                    List list = params;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add((EvaluateResult) ((Function1) it.next()).invoke(input));
                    }
                    ArrayList<EvaluateResult> arrayList2 = arrayList;
                    try {
                        if (arrayList2.size() < 2) {
                            return EvaluateResultError.INSTANCE;
                        }
                        LogicalKt$evaluateLogicalMinimum$1$minimum$1 logicalKt$evaluateLogicalMinimum$1$minimum$1 = new Function2<Value, Value, Value>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$evaluateLogicalMinimum$1$minimum$1
                            @Override // kotlin.jvm.functions.Function2
                            public final Value invoke(Value value, Value b) {
                                int iIntValue;
                                Intrinsics.checkNotNullParameter(b, "b");
                                return (value != null && ((iIntValue = ((Number) ((Function2) Values.Enterprise.INSTANCE.getCompare$com_google_firebase_firebase_firestore()).invoke(value, b)).intValue()) == 0 || iIntValue <= 0)) ? value : b;
                            }
                        };
                        Value valueInvoke = null;
                        for (EvaluateResult evaluateResult : arrayList2) {
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase == null ? -1 : LogicalKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i != -1 && i != 1) {
                                valueInvoke = logicalKt$evaluateLogicalMinimum$1$minimum$1.invoke(valueInvoke, value);
                            }
                        }
                        return valueInvoke == null ? EvaluateResult.INSTANCE.getNULL() : EvaluateResult.INSTANCE.value(valueInvoke);
                    } catch (Exception unused) {
                        return EvaluateResultError.INSTANCE;
                    }
                }
            };
        }
    };

    /* JADX INFO: compiled from: Logical.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Value.ValueTypeCase.values().length];
            try {
                iArr[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Value.ValueTypeCase.BOOLEAN_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase2 = Value.ValueTypeCase.DOUBLE_VALUE;
        evaluateIsNaN = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$arithmetic$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase3 = valueTypeCase;
                final Value.ValueTypeCase valueTypeCase4 = valueTypeCase2;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$arithmetic$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
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
                                return EvaluateResult.INSTANCE.m715boolean(Double.isNaN(value.getDoubleValue()));
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            value.getIntegerValue();
                            return EvaluateResult.INSTANCE.getFALSE();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        final Value.ValueTypeCase valueTypeCase3 = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase4 = Value.ValueTypeCase.DOUBLE_VALUE;
        evaluateIsNotNaN = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$arithmetic$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase5 = valueTypeCase3;
                final Value.ValueTypeCase valueTypeCase6 = valueTypeCase4;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$special$$inlined$arithmetic$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
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
                                return EvaluateResult.INSTANCE.m715boolean(!Double.isNaN(value.getDoubleValue()));
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            value.getIntegerValue();
                            return EvaluateResult.INSTANCE.getTRUE();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
        evaluateIsNull = new Function1() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LogicalKt.evaluateIsNull$lambda$13((List) obj);
            }
        };
        evaluateIsNotNull = new Function1() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LogicalKt.evaluateIsNotNull$lambda$15((List) obj);
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateAnd() {
        return evaluateAnd;
    }

    static final Function1 evaluateAnd$lambda$1(final List params) {
        Intrinsics.checkNotNullParameter(params, "params");
        return new Function1() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LogicalKt.evaluateAnd$lambda$1$lambda$0(params, (MutableDocument) obj);
            }
        };
    }

    static final EvaluateResult evaluateAnd$lambda$1$lambda$0(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> list, MutableDocument mutableDocument) {
        Iterator<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            EvaluateResult evaluateResultInvoke = it.next().invoke(mutableDocument);
            if (evaluateResultInvoke.getIsError()) {
                return EvaluateResultError.INSTANCE;
            }
            Value value = evaluateResultInvoke.getValue();
            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
            int i = valueTypeCase == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
            if (i == -1 || i == 1) {
                z = true;
            } else if (i == 2) {
                if (!value.getBooleanValue()) {
                    return EvaluateResult.INSTANCE.getFALSE();
                }
            } else {
                return EvaluateResultError.INSTANCE;
            }
        }
        EvaluateResult.Companion companion = EvaluateResult.INSTANCE;
        return z ? companion.getNULL() : companion.getTRUE();
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateOr() {
        return evaluateOr;
    }

    static final Function1 evaluateOr$lambda$3(final List params) {
        Intrinsics.checkNotNullParameter(params, "params");
        return new Function1() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LogicalKt.evaluateOr$lambda$3$lambda$2(params, (MutableDocument) obj);
            }
        };
    }

    static final EvaluateResult evaluateOr$lambda$3$lambda$2(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> list, MutableDocument mutableDocument) {
        Iterator<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            EvaluateResult evaluateResultInvoke = it.next().invoke(mutableDocument);
            if (evaluateResultInvoke.getIsError()) {
                return EvaluateResultError.INSTANCE;
            }
            Value value = evaluateResultInvoke.getValue();
            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
            int i = valueTypeCase == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
            if (i == -1 || i == 1) {
                z = true;
            } else if (i == 2) {
                if (value.getBooleanValue()) {
                    return EvaluateResult.INSTANCE.getTRUE();
                }
            } else {
                return EvaluateResultError.INSTANCE;
            }
        }
        EvaluateResult.Companion companion = EvaluateResult.INSTANCE;
        return z ? companion.getNULL() : companion.getFALSE();
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateXor() {
        return evaluateXor;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateCond() {
        return evaluateCond;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateLogicalMaximum() {
        return evaluateLogicalMaximum;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateLogicalMinimum() {
        return evaluateLogicalMinimum;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateIsNaN() {
        return evaluateIsNaN;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateIsNotNaN() {
        return evaluateIsNotNaN;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateIsNull() {
        return evaluateIsNull;
    }

    static final Function1 evaluateIsNull$lambda$13(List params) {
        Intrinsics.checkNotNullParameter(params, "params");
        if (params.size() != 1) {
            throw Assert.fail("IsNull function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
        }
        final Function1 function1 = (Function1) params.get(0);
        return new Function1() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LogicalKt.evaluateIsNull$lambda$13$lambda$12(function1, (MutableDocument) obj);
            }
        };
    }

    static final EvaluateResult evaluateIsNull$lambda$13$lambda$12(Function1<? super MutableDocument, ? extends EvaluateResult> function1, MutableDocument mutableDocument) {
        Value value = function1.invoke(mutableDocument).getValue();
        return value == null ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.m715boolean(value.hasNullValue());
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateIsNotNull() {
        return evaluateIsNotNull;
    }

    static final Function1 evaluateIsNotNull$lambda$15(List params) {
        Intrinsics.checkNotNullParameter(params, "params");
        if (params.size() != 1) {
            throw Assert.fail("IsNotNull function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
        }
        final Function1 function1 = (Function1) params.get(0);
        return new Function1() { // from class: com.google.firebase.firestore.pipeline.evaluation.LogicalKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LogicalKt.evaluateIsNotNull$lambda$15$lambda$14(function1, (MutableDocument) obj);
            }
        };
    }

    static final EvaluateResult evaluateIsNotNull$lambda$15$lambda$14(Function1<? super MutableDocument, ? extends EvaluateResult> function1, MutableDocument mutableDocument) {
        return function1.invoke(mutableDocument).getValue() == null ? EvaluateResultError.INSTANCE : EvaluateResult.INSTANCE.m715boolean(!r0.hasNullValue());
    }
}
