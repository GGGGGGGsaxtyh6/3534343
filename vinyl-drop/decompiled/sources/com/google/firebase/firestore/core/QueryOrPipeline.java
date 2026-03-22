package com.google.firebase.firestore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.firestore.RealtimePipeline;
import com.google.firebase.firestore.core.TargetOrPipeline;
import com.google.firebase.firestore.model.Document;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PipelineUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u001a\u001bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tJ\r\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0005J\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0018j\b\u0012\u0004\u0012\u00020\u0016`\u0019R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0006\u0082\u0001\u0002\u001c\u001d¨\u0006\u001e"}, d2 = {"Lcom/google/firebase/firestore/core/QueryOrPipeline;", "", "<init>", "()V", "isQuery", "", "()Z", "isPipeline", SearchIntents.EXTRA_QUERY, "Lcom/google/firebase/firestore/core/Query;", "pipeline", "Lcom/google/firebase/firestore/RealtimePipeline;", "pipeline$com_google_firebase_firebase_firestore", "canonicalId", "", "toString", "toTargetOrPipeline", "Lcom/google/firebase/firestore/core/TargetOrPipeline;", "matchesAllDocuments", "hasLimit", "matches", "doc", "Lcom/google/firebase/firestore/model/Document;", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "QueryWrapper", "PipelineWrapper", "Lcom/google/firebase/firestore/core/QueryOrPipeline$PipelineWrapper;", "Lcom/google/firebase/firestore/core/QueryOrPipeline$QueryWrapper;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class QueryOrPipeline {
    public /* synthetic */ QueryOrPipeline(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: PipelineUtil.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\tJ\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/google/firebase/firestore/core/QueryOrPipeline$QueryWrapper;", "Lcom/google/firebase/firestore/core/QueryOrPipeline;", SearchIntents.EXTRA_QUERY, "Lcom/google/firebase/firestore/core/Query;", "<init>", "(Lcom/google/firebase/firestore/core/Query;)V", "getQuery$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/core/Query;", "component1", "component1$com_google_firebase_firebase_firestore", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class QueryWrapper extends QueryOrPipeline {
        private final Query query;

        public static /* synthetic */ QueryWrapper copy$default(QueryWrapper queryWrapper, Query query, int i, Object obj) {
            if ((i & 1) != 0) {
                query = queryWrapper.query;
            }
            return queryWrapper.copy(query);
        }

        /* JADX INFO: renamed from: component1$com_google_firebase_firebase_firestore, reason: from getter */
        public final Query getQuery() {
            return this.query;
        }

        public final QueryWrapper copy(Query query) {
            Intrinsics.checkNotNullParameter(query, "query");
            return new QueryWrapper(query);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof QueryWrapper) && Intrinsics.areEqual(this.query, ((QueryWrapper) other).query);
        }

        public int hashCode() {
            return this.query.hashCode();
        }

        @Override // com.google.firebase.firestore.core.QueryOrPipeline
        public String toString() {
            return "QueryWrapper(query=" + this.query + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public QueryWrapper(Query query) {
            super(null);
            Intrinsics.checkNotNullParameter(query, "query");
            this.query = query;
        }

        public final Query getQuery$com_google_firebase_firebase_firestore() {
            return this.query;
        }
    }

    private QueryOrPipeline() {
    }

    /* JADX INFO: compiled from: PipelineUtil.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\tJ\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/google/firebase/firestore/core/QueryOrPipeline$PipelineWrapper;", "Lcom/google/firebase/firestore/core/QueryOrPipeline;", "pipeline", "Lcom/google/firebase/firestore/RealtimePipeline;", "<init>", "(Lcom/google/firebase/firestore/RealtimePipeline;)V", "getPipeline$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/RealtimePipeline;", "component1", "component1$com_google_firebase_firebase_firestore", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class PipelineWrapper extends QueryOrPipeline {
        private final RealtimePipeline pipeline;

        public static /* synthetic */ PipelineWrapper copy$default(PipelineWrapper pipelineWrapper, RealtimePipeline realtimePipeline, int i, Object obj) {
            if ((i & 1) != 0) {
                realtimePipeline = pipelineWrapper.pipeline;
            }
            return pipelineWrapper.copy(realtimePipeline);
        }

        /* JADX INFO: renamed from: component1$com_google_firebase_firebase_firestore, reason: from getter */
        public final RealtimePipeline getPipeline() {
            return this.pipeline;
        }

        public final PipelineWrapper copy(RealtimePipeline pipeline) {
            Intrinsics.checkNotNullParameter(pipeline, "pipeline");
            return new PipelineWrapper(pipeline);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof PipelineWrapper) && Intrinsics.areEqual(this.pipeline, ((PipelineWrapper) other).pipeline);
        }

        public int hashCode() {
            return this.pipeline.hashCode();
        }

        @Override // com.google.firebase.firestore.core.QueryOrPipeline
        public String toString() {
            return "PipelineWrapper(pipeline=" + this.pipeline + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PipelineWrapper(RealtimePipeline pipeline) {
            super(null);
            Intrinsics.checkNotNullParameter(pipeline, "pipeline");
            this.pipeline = pipeline;
        }

        public final RealtimePipeline getPipeline$com_google_firebase_firebase_firestore() {
            return this.pipeline;
        }
    }

    public final boolean isQuery() {
        return this instanceof QueryWrapper;
    }

    public final boolean isPipeline() {
        return this instanceof PipelineWrapper;
    }

    public final Query query() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.google.firebase.firestore.core.QueryOrPipeline.QueryWrapper");
        return ((QueryWrapper) this).getQuery$com_google_firebase_firebase_firestore();
    }

    public final RealtimePipeline pipeline$com_google_firebase_firebase_firestore() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.google.firebase.firestore.core.QueryOrPipeline.PipelineWrapper");
        return ((PipelineWrapper) this).getPipeline$com_google_firebase_firebase_firestore();
    }

    public final String canonicalId() {
        if (this instanceof PipelineWrapper) {
            return ((PipelineWrapper) this).getPipeline$com_google_firebase_firebase_firestore().canonicalId$com_google_firebase_firebase_firestore();
        }
        if (!(this instanceof QueryWrapper)) {
            throw new NoWhenBranchMatchedException();
        }
        String canonicalId = ((QueryWrapper) this).getQuery$com_google_firebase_firebase_firestore().getCanonicalId();
        Intrinsics.checkNotNullExpressionValue(canonicalId, "getCanonicalId(...)");
        return canonicalId;
    }

    public String toString() {
        if (this instanceof PipelineWrapper) {
            return ((PipelineWrapper) this).getPipeline$com_google_firebase_firebase_firestore().canonicalId$com_google_firebase_firebase_firestore();
        }
        if (!(this instanceof QueryWrapper)) {
            throw new NoWhenBranchMatchedException();
        }
        String string = ((QueryWrapper) this).getQuery$com_google_firebase_firebase_firestore().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final TargetOrPipeline toTargetOrPipeline() {
        if (this instanceof PipelineWrapper) {
            return new TargetOrPipeline.PipelineWrapper(((PipelineWrapper) this).getPipeline$com_google_firebase_firebase_firestore());
        }
        if (!(this instanceof QueryWrapper)) {
            throw new NoWhenBranchMatchedException();
        }
        Target target = ((QueryWrapper) this).getQuery$com_google_firebase_firebase_firestore().toTarget();
        Intrinsics.checkNotNullExpressionValue(target, "toTarget(...)");
        return new TargetOrPipeline.TargetWrapper(target);
    }

    public final boolean matchesAllDocuments() {
        if (this instanceof PipelineWrapper) {
            return ((PipelineWrapper) this).getPipeline$com_google_firebase_firebase_firestore().matchesAllDocuments$com_google_firebase_firebase_firestore();
        }
        if (this instanceof QueryWrapper) {
            return ((QueryWrapper) this).getQuery$com_google_firebase_firebase_firestore().matchesAllDocuments();
        }
        throw new NoWhenBranchMatchedException();
    }

    public final boolean hasLimit() {
        if (this instanceof PipelineWrapper) {
            return ((PipelineWrapper) this).getPipeline$com_google_firebase_firebase_firestore().hasLimit$com_google_firebase_firebase_firestore();
        }
        if (this instanceof QueryWrapper) {
            return ((QueryWrapper) this).getQuery$com_google_firebase_firebase_firestore().hasLimit();
        }
        throw new NoWhenBranchMatchedException();
    }

    public final boolean matches(Document doc) {
        Intrinsics.checkNotNullParameter(doc, "doc");
        if (this instanceof PipelineWrapper) {
            return ((PipelineWrapper) this).getPipeline$com_google_firebase_firebase_firestore().matches$com_google_firebase_firebase_firestore(doc);
        }
        if (this instanceof QueryWrapper) {
            return ((QueryWrapper) this).getQuery$com_google_firebase_firebase_firestore().matches(doc);
        }
        throw new NoWhenBranchMatchedException();
    }

    public final Comparator<Document> comparator() {
        if (this instanceof PipelineWrapper) {
            return ((PipelineWrapper) this).getPipeline$com_google_firebase_firebase_firestore().comparator$com_google_firebase_firebase_firestore();
        }
        if (!(this instanceof QueryWrapper)) {
            throw new NoWhenBranchMatchedException();
        }
        Comparator<Document> comparator = ((QueryWrapper) this).getQuery$com_google_firebase_firebase_firestore().comparator();
        Intrinsics.checkNotNullExpressionValue(comparator, "comparator(...)");
        return comparator;
    }
}
