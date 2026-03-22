package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.pipeline.BooleanExpression;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Filter {
    public abstract String getCanonicalId();

    public abstract List<Filter> getFilters();

    public abstract List<FieldFilter> getFlattenedFilters();

    public abstract boolean matches(Document document);

    abstract BooleanExpression toPipelineExpr();
}
