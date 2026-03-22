package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;

/* JADX INFO: loaded from: classes3.dex */
public class BundledQuery implements BundleElement {
    private final Query.LimitType limitType;
    private final Target target;

    public BundledQuery(Target target, Query.LimitType limitType) {
        this.target = target;
        this.limitType = limitType;
    }

    public Target getTarget() {
        return this.target;
    }

    public Query.LimitType getLimitType() {
        return this.limitType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            BundledQuery bundledQuery = (BundledQuery) obj;
            if (this.target.equals(bundledQuery.target) && this.limitType == bundledQuery.limitType) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.target.hashCode() * 31) + this.limitType.hashCode();
    }
}
