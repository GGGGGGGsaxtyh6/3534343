package com.google.firebase.firestore;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class VectorValue {
    private final double[] values;

    VectorValue(double[] dArr) {
        this.values = dArr == null ? new double[0] : (double[]) dArr.clone();
    }

    public double[] toArray() {
        return (double[]) this.values.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.values, ((VectorValue) obj).values);
    }

    public int hashCode() {
        return Arrays.hashCode(this.values);
    }
}
