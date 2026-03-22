package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.BooleanExpression;
import com.google.firebase.firestore.pipeline.Expression;
import com.google.firebase.firestore.pipeline.Field;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class FieldFilter extends Filter {
    private final FieldPath field;
    private final Operator operator;
    private final Value value;

    public enum Operator {
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL("<="),
        EQUAL("=="),
        NOT_EQUAL("!="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL(">="),
        ARRAY_CONTAINS("array_contains"),
        ARRAY_CONTAINS_ANY("array_contains_any"),
        IN("in"),
        NOT_IN("not_in");

        private final String text;

        Operator(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    protected FieldFilter(FieldPath fieldPath, Operator operator, Value value) {
        this.field = fieldPath;
        this.operator = operator;
        this.value = value;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public FieldPath getField() {
        return this.field;
    }

    public Value getValue() {
        return this.value;
    }

    public static FieldFilter create(FieldPath fieldPath, Operator operator, Value value) {
        if (fieldPath.isKeyField()) {
            if (operator == Operator.IN) {
                return new KeyFieldInFilter(fieldPath, value);
            }
            if (operator == Operator.NOT_IN) {
                return new KeyFieldNotInFilter(fieldPath, value);
            }
            Assert.hardAssert((operator == Operator.ARRAY_CONTAINS || operator == Operator.ARRAY_CONTAINS_ANY) ? false : true, operator.toString() + "queries don't make sense on document keys", new Object[0]);
            return new KeyFieldFilter(fieldPath, operator, value);
        }
        if (operator == Operator.ARRAY_CONTAINS) {
            return new ArrayContainsFilter(fieldPath, value);
        }
        if (operator == Operator.IN) {
            return new InFilter(fieldPath, value);
        }
        if (operator == Operator.ARRAY_CONTAINS_ANY) {
            return new ArrayContainsAnyFilter(fieldPath, value);
        }
        if (operator == Operator.NOT_IN) {
            return new NotInFilter(fieldPath, value);
        }
        return new FieldFilter(fieldPath, operator, value);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public boolean matches(Document document) {
        Value field = document.getField(this.field);
        return this.operator == Operator.NOT_EQUAL ? (field == null || field.hasNullValue() || !matchesComparison(Values.compare(field, this.value))) ? false : true : field != null && Values.typeOrder(field) == Values.typeOrder(this.value) && matchesComparison(Values.compare(field, this.value));
    }

    /* JADX INFO: renamed from: com.google.firebase.firestore.core.FieldFilter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator;

        static {
            int[] iArr = new int[Operator.values().length];
            $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator = iArr;
            try {
                iArr[Operator.LESS_THAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.LESS_THAN_OR_EQUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.EQUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.NOT_EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.GREATER_THAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.GREATER_THAN_OR_EQUAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.ARRAY_CONTAINS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.ARRAY_CONTAINS_ANY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.IN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[Operator.NOT_IN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    protected boolean matchesComparison(int i) {
        switch (AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[this.operator.ordinal()]) {
            case 1:
                return i < 0;
            case 2:
                return i <= 0;
            case 3:
                return i == 0;
            case 4:
                return i != 0;
            case 5:
                return i > 0;
            case 6:
                return i >= 0;
            default:
                throw Assert.fail("Unknown FieldFilter operator: %s", this.operator);
        }
    }

    public boolean isInequality() {
        return Arrays.asList(Operator.LESS_THAN, Operator.LESS_THAN_OR_EQUAL, Operator.GREATER_THAN, Operator.GREATER_THAN_OR_EQUAL, Operator.NOT_EQUAL, Operator.NOT_IN).contains(this.operator);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public String getCanonicalId() {
        return getField().canonicalString() + getOperator().toString() + Values.canonicalId(getValue());
    }

    @Override // com.google.firebase.firestore.core.Filter
    public List<FieldFilter> getFlattenedFilters() {
        return Collections.singletonList(this);
    }

    @Override // com.google.firebase.firestore.core.Filter
    public List<Filter> getFilters() {
        return Collections.singletonList(this);
    }

    @Override // com.google.firebase.firestore.core.Filter
    BooleanExpression toPipelineExpr() {
        Field field = new Field(this.field);
        BooleanExpression booleanExpressionExists = field.exists();
        switch (AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[this.operator.ordinal()]) {
            case 1:
                return Expression.and(booleanExpressionExists, field.lessThan(this.value));
            case 2:
                return Expression.and(booleanExpressionExists, field.lessThanOrEqual(this.value));
            case 3:
                return Expression.and(booleanExpressionExists, field.equal(this.value));
            case 4:
                return Expression.and(booleanExpressionExists, field.notEqual(this.value));
            case 5:
                return Expression.and(booleanExpressionExists, field.greaterThan(this.value));
            case 6:
                return Expression.and(booleanExpressionExists, field.greaterThanOrEqual(this.value));
            case 7:
                return Expression.and(booleanExpressionExists, field.arrayContains(this.value));
            case 8:
                return Expression.and(booleanExpressionExists, field.arrayContainsAny(this.value.getArrayValue().getValuesList()));
            case 9:
                return Expression.and(booleanExpressionExists, field.equalAny(this.value.getArrayValue().getValuesList()));
            case 10:
                return Expression.and(booleanExpressionExists, field.notEqualAny(this.value.getArrayValue().getValuesList()));
            default:
                throw new IllegalArgumentException("Unsupported operator: " + this.operator);
        }
    }

    private static boolean hasNaN(List<Value> list) {
        Iterator<Value> it = list.iterator();
        while (it.hasNext()) {
            if (Values.isNanValue(it.next())) {
                return true;
            }
        }
        return false;
    }

    private static List<Value> filterNaN(List<Value> list) {
        ArrayList arrayList = new ArrayList(list.size() - 1);
        for (Value value : list) {
            if (!Values.isNanValue(value)) {
                arrayList.add(value);
            }
        }
        return arrayList;
    }

    public String toString() {
        return getCanonicalId();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof FieldFilter)) {
            FieldFilter fieldFilter = (FieldFilter) obj;
            if (this.operator == fieldFilter.operator && this.field.equals(fieldFilter.field) && this.value.equals(fieldFilter.value)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((1147 + this.operator.hashCode()) * 31) + this.field.hashCode()) * 31) + this.value.hashCode();
    }
}
