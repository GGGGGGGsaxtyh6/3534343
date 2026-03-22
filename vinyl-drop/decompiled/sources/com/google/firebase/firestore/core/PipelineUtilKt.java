package com.google.firebase.firestore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.firestore.RealtimePipeline;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.pipeline.CollectionGroupSource;
import com.google.firebase.firestore.pipeline.CollectionSource;
import com.google.firebase.firestore.pipeline.DatabaseSource;
import com.google.firebase.firestore.pipeline.DocumentsSource;
import com.google.firebase.firestore.pipeline.InternalOptions;
import com.google.firebase.firestore.pipeline.LimitStage;
import com.google.firebase.firestore.pipeline.Ordering;
import com.google.firebase.firestore.pipeline.SortStage;
import com.google.firebase.firestore.pipeline.Stage;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PipelineUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0012\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u001f\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0002\u0010\u000b\u001a\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a\u0017\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0002\u0010\u0011\u001a\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\u0015"}, d2 = {"getPipelineFlavor", "Lcom/google/firebase/firestore/core/PipelineFlavor;", "pipeline", "Lcom/google/firebase/firestore/RealtimePipeline;", "getPipelineSourceType", "Lcom/google/firebase/firestore/core/PipelineSourceType;", "getPipelineCollectionGroup", "", "getPipelineCollection", "getPipelineDocuments", "", "(Lcom/google/firebase/firestore/RealtimePipeline;)[Ljava/lang/String;", "asCollectionPipelineAtPath", "path", "Lcom/google/firebase/firestore/model/ResourcePath;", "getLastEffectiveLimit", "", "(Lcom/google/firebase/firestore/RealtimePipeline;)Ljava/lang/Integer;", "getLastEffectiveSortOrderings", "", "Lcom/google/firebase/firestore/pipeline/Ordering;", "com.google.firebase-firebase-firestore"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PipelineUtilKt {
    public static final PipelineFlavor getPipelineFlavor(RealtimePipeline pipeline) {
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        return PipelineFlavor.EXACT;
    }

    public static final PipelineSourceType getPipelineSourceType(RealtimePipeline pipeline) {
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        Assert.hardAssert(!pipeline.getStages$com_google_firebase_firebase_firestore().isEmpty(), "Pipeline must have at least one stage to determine its source.", new Object[0]);
        Stage stage = (Stage) CollectionsKt.first((List) pipeline.getStages$com_google_firebase_firebase_firestore());
        return stage instanceof CollectionSource ? PipelineSourceType.COLLECTION : stage instanceof CollectionGroupSource ? PipelineSourceType.COLLECTION_GROUP : stage instanceof DatabaseSource ? PipelineSourceType.DATABASE : stage instanceof DocumentsSource ? PipelineSourceType.DOCUMENTS : PipelineSourceType.UNKNOWN;
    }

    public static final String getPipelineCollectionGroup(RealtimePipeline pipeline) {
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        if (getPipelineSourceType(pipeline) != PipelineSourceType.COLLECTION_GROUP) {
            return null;
        }
        Assert.hardAssert(!pipeline.getStages$com_google_firebase_firebase_firestore().isEmpty(), "Pipeline source is CollectionGroup but stages are empty.", new Object[0]);
        Stage stage = (Stage) CollectionsKt.first((List) pipeline.getStages$com_google_firebase_firebase_firestore());
        if (stage instanceof CollectionGroupSource) {
            return ((CollectionGroupSource) stage).getCollectionId();
        }
        return null;
    }

    public static final String getPipelineCollection(RealtimePipeline pipeline) {
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        if (getPipelineSourceType(pipeline) != PipelineSourceType.COLLECTION) {
            return null;
        }
        Assert.hardAssert(!pipeline.getStages$com_google_firebase_firebase_firestore().isEmpty(), "Pipeline source is Collection but stages are empty.", new Object[0]);
        Stage stage = (Stage) CollectionsKt.first((List) pipeline.getStages$com_google_firebase_firebase_firestore());
        if (stage instanceof CollectionSource) {
            return ((CollectionSource) stage).getPath().canonicalString();
        }
        return null;
    }

    public static final String[] getPipelineDocuments(RealtimePipeline pipeline) {
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        if (getPipelineSourceType(pipeline) != PipelineSourceType.DOCUMENTS) {
            return null;
        }
        Assert.hardAssert(!pipeline.getStages$com_google_firebase_firebase_firestore().isEmpty(), "Pipeline source is Documents but stages are empty.", new Object[0]);
        Stage stage = (Stage) CollectionsKt.first((List) pipeline.getStages$com_google_firebase_firebase_firestore());
        if (!(stage instanceof DocumentsSource)) {
            return null;
        }
        ResourcePath[] documents = ((DocumentsSource) stage).getDocuments();
        ArrayList arrayList = new ArrayList(documents.length);
        for (ResourcePath resourcePath : documents) {
            arrayList.add(resourcePath.canonicalString());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static final RealtimePipeline asCollectionPipelineAtPath(RealtimePipeline pipeline, ResourcePath path) {
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        Intrinsics.checkNotNullParameter(path, "path");
        List<Stage<?>> stages$com_google_firebase_firebase_firestore = pipeline.getStages$com_google_firebase_firebase_firestore();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(stages$com_google_firebase_firebase_firestore, 10));
        Iterator<T> it = stages$com_google_firebase_firebase_firestore.iterator();
        while (it.hasNext()) {
            CollectionSource collectionSource = (Stage) it.next();
            if (collectionSource instanceof CollectionGroupSource) {
                collectionSource = new CollectionSource(path, pipeline.getSerializer(), InternalOptions.EMPTY);
            }
            arrayList.add(collectionSource);
        }
        return new RealtimePipeline(pipeline.getFirestore(), pipeline.getSerializer(), pipeline.getUserDataReader(), arrayList, null, 16, null);
    }

    public static final Integer getLastEffectiveLimit(RealtimePipeline pipeline) {
        Intrinsics.checkNotNullParameter(pipeline, "pipeline");
        for (Stage stage : CollectionsKt.asReversed(pipeline.getRewrittenStages$com_google_firebase_firebase_firestore())) {
            if (stage instanceof LimitStage) {
                return Integer.valueOf(((LimitStage) stage).getLimit());
            }
        }
        return null;
    }

    private static final List<Ordering> getLastEffectiveSortOrderings(RealtimePipeline realtimePipeline) {
        for (Stage stage : CollectionsKt.asReversed(realtimePipeline.getRewrittenStages$com_google_firebase_firebase_firestore())) {
            if (stage instanceof SortStage) {
                return ArraysKt.toList(((SortStage) stage).getOrders());
            }
        }
        Assert.fail("RealtimePipeline must contain at least one Sort stage (ensured by RewriteStages).", new Object[0]);
        return CollectionsKt.emptyList();
    }
}
