package com.google.firebase.firestore.pipeline.evaluation;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.evaluation.MapsKt;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Maps.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"w\u0010\u0000\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\"w\u0010\r\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012#\u0012!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0001j\u0002`\b0\u0001j\u0002`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"evaluateMapGet", "Lkotlin/Function1;", "", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lkotlin/ParameterName;", "name", "input", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateDocument;", "params", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateFunction;", "getEvaluateMapGet", "()Lkotlin/jvm/functions/Function1;", "evaluateMap", "getEvaluateMap", "com.google.firebase-firebase-firestore"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MapsKt {
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateMapGet = (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.MapsKt$special$$inlined$binaryValueValueFunction$1
        @Override // kotlin.jvm.functions.Function1
        public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
            Intrinsics.checkNotNullParameter(params, "params");
            if (params.size() != 2) {
                throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
            }
            final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
            final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
            return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.MapsKt$special$$inlined$binaryValueValueFunction$1.1
                @Override // kotlin.jvm.functions.Function1
                public final EvaluateResult invoke(MutableDocument input) {
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
                        Map<String, Value> fieldsMap = (value == null || !Values.isMapValue(value) || Values.isVectorValue(value)) ? null : value.getMapValue().getFieldsMap();
                        Value.ValueTypeCase valueTypeCase = value2 != null ? value2.getValueTypeCase() : null;
                        if ((valueTypeCase == null ? -1 : MapsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()]) == 1) {
                            Value value3 = fieldsMap != null ? fieldsMap.get(value2.getStringValue()) : null;
                            return value3 == null ? EvaluateResultUnset.INSTANCE : new EvaluateResultValue(value3);
                        }
                        return EvaluateResultError.INSTANCE;
                    } catch (Exception unused) {
                        return EvaluateResultError.INSTANCE;
                    }
                }
            };
        }
    };
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateMap = new Function1() { // from class: com.google.firebase.firestore.pipeline.evaluation.MapsKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return MapsKt.evaluateMap$lambda$2((List) obj);
        }
    };

    /* JADX INFO: compiled from: Maps.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Value.ValueTypeCase.values().length];
            try {
                iArr[Value.ValueTypeCase.STRING_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateMapGet() {
        return evaluateMapGet;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getEvaluateMap() {
        return evaluateMap;
    }

    static final Function1 evaluateMap$lambda$2(final List params) {
        Intrinsics.checkNotNullParameter(params, "params");
        if (params.size() % 2 != 0) {
            throw Assert.fail("Function should have even number of params, but %d were given.", Integer.valueOf(params.size()));
        }
        return new Function1() { // from class: com.google.firebase.firestore.pipeline.evaluation.MapsKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MapsKt.evaluateMap$lambda$2$lambda$1(params, (MutableDocument) obj);
            }
        };
    }

    static final EvaluateResult evaluateMap$lambda$2$lambda$1(List list, MutableDocument input) {
        Value value;
        Intrinsics.checkNotNullParameter(input, "input");
        HashMap map = new HashMap(list.size() / 2);
        int i = 0;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, list.size() - 1, 2);
        if (progressionLastElement >= 0) {
            while (true) {
                Value value2 = ((EvaluateResult) ((Function1) list.get(i)).invoke(input)).getValue();
                if (value2 != null && value2.hasStringValue() && (value = ((EvaluateResult) ((Function1) list.get(i + 1)).invoke(input)).getValue()) != null) {
                    String stringValue = value2.getStringValue();
                    Intrinsics.checkNotNullExpressionValue(stringValue, "getStringValue(...)");
                    if (map.put(stringValue, value) == null) {
                        if (i == progressionLastElement) {
                            break;
                        }
                        i += 2;
                    } else {
                        return EvaluateResultError.INSTANCE;
                    }
                }
                return EvaluateResultError.INSTANCE;
            }
        }
        return new EvaluateResultValue(Values.encodeValue(map));
    }
}
