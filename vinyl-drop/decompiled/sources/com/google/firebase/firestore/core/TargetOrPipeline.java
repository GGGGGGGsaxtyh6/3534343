package com.google.firebase.firestore.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.RealtimePipeline;
import com.google.firebase.firestore.model.ResourcePath;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PipelineUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0014\u0015B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tJ\r\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0012H\u0016R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0006R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0001\u0002\u0016\u0017¨\u0006\u0018"}, d2 = {"Lcom/google/firebase/firestore/core/TargetOrPipeline;", "", "<init>", "()V", "isTarget", "", "()Z", "isPipeline", TypedValues.AttributesType.S_TARGET, "Lcom/google/firebase/firestore/core/Target;", "pipeline", "Lcom/google/firebase/firestore/RealtimePipeline;", "pipeline$com_google_firebase_firebase_firestore", "singleDocPath", "Lcom/google/firebase/firestore/model/ResourcePath;", "getSingleDocPath", "()Lcom/google/firebase/firestore/model/ResourcePath;", "canonicalId", "", "toString", "TargetWrapper", "PipelineWrapper", "Lcom/google/firebase/firestore/core/TargetOrPipeline$PipelineWrapper;", "Lcom/google/firebase/firestore/core/TargetOrPipeline$TargetWrapper;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class TargetOrPipeline {
    public /* synthetic */ TargetOrPipeline(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: PipelineUtil.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/google/firebase/firestore/core/TargetOrPipeline$TargetWrapper;", "Lcom/google/firebase/firestore/core/TargetOrPipeline;", TypedValues.AttributesType.S_TARGET, "Lcom/google/firebase/firestore/core/Target;", "<init>", "(Lcom/google/firebase/firestore/core/Target;)V", "getTarget", "()Lcom/google/firebase/firestore/core/Target;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class TargetWrapper extends TargetOrPipeline {
        private final Target target;

        public static /* synthetic */ TargetWrapper copy$default(TargetWrapper targetWrapper, Target target, int i, Object obj) {
            if ((i & 1) != 0) {
                target = targetWrapper.target;
            }
            return targetWrapper.copy(target);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Target getTarget() {
            return this.target;
        }

        public final TargetWrapper copy(Target target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return new TargetWrapper(target);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TargetWrapper) && Intrinsics.areEqual(this.target, ((TargetWrapper) other).target);
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        @Override // com.google.firebase.firestore.core.TargetOrPipeline
        public String toString() {
            return "TargetWrapper(target=" + this.target + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TargetWrapper(Target target) {
            super(null);
            Intrinsics.checkNotNullParameter(target, "target");
            this.target = target;
        }

        public final Target getTarget() {
            return this.target;
        }
    }

    private TargetOrPipeline() {
    }

    /* JADX INFO: compiled from: PipelineUtil.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/google/firebase/firestore/core/TargetOrPipeline$PipelineWrapper;", "Lcom/google/firebase/firestore/core/TargetOrPipeline;", "pipeline", "Lcom/google/firebase/firestore/RealtimePipeline;", "<init>", "(Lcom/google/firebase/firestore/RealtimePipeline;)V", "getPipeline", "()Lcom/google/firebase/firestore/RealtimePipeline;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class PipelineWrapper extends TargetOrPipeline {
        private final RealtimePipeline pipeline;

        public static /* synthetic */ PipelineWrapper copy$default(PipelineWrapper pipelineWrapper, RealtimePipeline realtimePipeline, int i, Object obj) {
            if ((i & 1) != 0) {
                realtimePipeline = pipelineWrapper.pipeline;
            }
            return pipelineWrapper.copy(realtimePipeline);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
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

        @Override // com.google.firebase.firestore.core.TargetOrPipeline
        public String toString() {
            return "PipelineWrapper(pipeline=" + this.pipeline + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PipelineWrapper(RealtimePipeline pipeline) {
            super(null);
            Intrinsics.checkNotNullParameter(pipeline, "pipeline");
            this.pipeline = pipeline;
        }

        public final RealtimePipeline getPipeline() {
            return this.pipeline;
        }
    }

    public final boolean isTarget() {
        return this instanceof TargetWrapper;
    }

    public final boolean isPipeline() {
        return this instanceof PipelineWrapper;
    }

    public final Target target() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.google.firebase.firestore.core.TargetOrPipeline.TargetWrapper");
        return ((TargetWrapper) this).getTarget();
    }

    public final RealtimePipeline pipeline$com_google_firebase_firebase_firestore() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.google.firebase.firestore.core.TargetOrPipeline.PipelineWrapper");
        return ((PipelineWrapper) this).getPipeline();
    }

    public final ResourcePath getSingleDocPath() {
        String[] pipelineDocuments;
        if (this instanceof PipelineWrapper) {
            PipelineWrapper pipelineWrapper = (PipelineWrapper) this;
            if (PipelineUtilKt.getPipelineSourceType(pipelineWrapper.getPipeline()) == PipelineSourceType.DOCUMENTS && (pipelineDocuments = PipelineUtilKt.getPipelineDocuments(pipelineWrapper.getPipeline())) != null && pipelineDocuments.length == 1) {
                return ResourcePath.fromString(pipelineDocuments[0]);
            }
            return null;
        }
        if (!(this instanceof TargetWrapper)) {
            throw new NoWhenBranchMatchedException();
        }
        TargetWrapper targetWrapper = (TargetWrapper) this;
        if (targetWrapper.getTarget().isDocumentQuery()) {
            return targetWrapper.getTarget().getPath();
        }
        return null;
    }

    public final String canonicalId() {
        if (this instanceof PipelineWrapper) {
            return ((PipelineWrapper) this).getPipeline().canonicalId$com_google_firebase_firebase_firestore();
        }
        if (!(this instanceof TargetWrapper)) {
            throw new NoWhenBranchMatchedException();
        }
        String canonicalId = ((TargetWrapper) this).getTarget().getCanonicalId();
        Intrinsics.checkNotNullExpressionValue(canonicalId, "getCanonicalId(...)");
        return canonicalId;
    }

    public String toString() {
        if (this instanceof PipelineWrapper) {
            return ((PipelineWrapper) this).getPipeline().canonicalId$com_google_firebase_firebase_firestore();
        }
        if (!(this instanceof TargetWrapper)) {
            throw new NoWhenBranchMatchedException();
        }
        String string = ((TargetWrapper) this).getTarget().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
