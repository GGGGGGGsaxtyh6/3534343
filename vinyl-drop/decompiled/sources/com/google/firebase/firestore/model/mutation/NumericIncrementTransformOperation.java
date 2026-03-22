package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;

/* JADX INFO: loaded from: classes3.dex */
public class NumericIncrementTransformOperation implements TransformOperation {
    private Value operand;

    private long safeIncrement(long j, long j2) {
        long j3 = j + j2;
        return ((j ^ j3) & (j2 ^ j3)) >= 0 ? j3 : j3 >= 0 ? Long.MIN_VALUE : Long.MAX_VALUE;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToRemoteDocument(Value value, Value value2) {
        return value2;
    }

    public NumericIncrementTransformOperation(Value value) {
        Assert.hardAssert(Values.isNumber(value), "NumericIncrementTransformOperation expects a NumberValue operand", new Object[0]);
        this.operand = value;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToLocalView(Value value, Timestamp timestamp) {
        Value valueComputeBaseValue = computeBaseValue(value);
        if (Values.isInteger(valueComputeBaseValue) && Values.isInteger(this.operand)) {
            return Value.newBuilder().setIntegerValue(safeIncrement(valueComputeBaseValue.getIntegerValue(), operandAsLong())).build();
        }
        if (Values.isInteger(valueComputeBaseValue)) {
            return Value.newBuilder().setDoubleValue(valueComputeBaseValue.getIntegerValue() + operandAsDouble()).build();
        }
        Assert.hardAssert(Values.isDouble(valueComputeBaseValue), "Expected NumberValue to be of type DoubleValue, but was ", value.getClass().getCanonicalName());
        return Value.newBuilder().setDoubleValue(valueComputeBaseValue.getDoubleValue() + operandAsDouble()).build();
    }

    public Value getOperand() {
        return this.operand;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value computeBaseValue(Value value) {
        return Values.isNumber(value) ? value : Value.newBuilder().setIntegerValue(0L).build();
    }

    private double operandAsDouble() {
        boolean zIsDouble = Values.isDouble(this.operand);
        Value value = this.operand;
        if (zIsDouble) {
            return value.getDoubleValue();
        }
        boolean zIsInteger = Values.isInteger(value);
        Value value2 = this.operand;
        if (zIsInteger) {
            return value2.getIntegerValue();
        }
        throw Assert.fail("Expected 'operand' to be of Number type, but was " + value2.getClass().getCanonicalName(), new Object[0]);
    }

    private long operandAsLong() {
        boolean zIsDouble = Values.isDouble(this.operand);
        Value value = this.operand;
        if (zIsDouble) {
            return (long) value.getDoubleValue();
        }
        boolean zIsInteger = Values.isInteger(value);
        Value value2 = this.operand;
        if (zIsInteger) {
            return value2.getIntegerValue();
        }
        throw Assert.fail("Expected 'operand' to be of Number type, but was " + value2.getClass().getCanonicalName(), new Object[0]);
    }
}
