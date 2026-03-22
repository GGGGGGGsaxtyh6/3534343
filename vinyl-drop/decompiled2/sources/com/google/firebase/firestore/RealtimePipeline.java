package com.google.firebase.firestore;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.RealtimePipeline;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.core.QueryListener;
import com.google.firebase.firestore.core.QueryOrPipeline;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.pipeline.BooleanExpression;
import com.google.firebase.firestore.pipeline.BooleanFunctionExpression;
import com.google.firebase.firestore.pipeline.Expression;
import com.google.firebase.firestore.pipeline.Field;
import com.google.firebase.firestore.pipeline.FunctionExpression;
import com.google.firebase.firestore.pipeline.InternalOptions;
import com.google.firebase.firestore.pipeline.LimitStage;
import com.google.firebase.firestore.pipeline.OffsetStage;
import com.google.firebase.firestore.pipeline.Ordering;
import com.google.firebase.firestore.pipeline.SortStage;
import com.google.firebase.firestore.pipeline.Stage;
import com.google.firebase.firestore.pipeline.WhereStage;
import com.google.firebase.firestore.pipeline.evaluation.EvaluationContext;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firestore.v1.Pipeline;
import com.google.firestore.v1.StructuredPipeline;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: RealtimePipeline.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0002XYBA\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0010\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eB-\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\n¢\u0006\u0004\b\r\u0010\u0010J\u001a\u0010\u001b\u001a\u00020\u00002\u0010\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u0002J\u0014\u0010\u001c\u001a\u00020\u00002\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\nH\u0002J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001eJ'\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0#\"\u00020!¢\u0006\u0002\u0010$J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'J\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)J\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010+\u001a\u00020,J\u0014\u0010-\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020*00J\u001c\u0010-\u001a\u00020.2\u0006\u0010+\u001a\u00020,2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020*00J\u001c\u0010-\u001a\u00020.2\u0006\u00101\u001a\u0002022\f\u0010/\u001a\b\u0012\u0004\u0012\u00020*00J$\u0010-\u001a\u00020.2\u0006\u00101\u001a\u0002022\u0006\u0010+\u001a\u00020,2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020*00J\u0015\u00103\u001a\u00020\u00002\u0006\u0010+\u001a\u00020\fH\u0000¢\u0006\u0002\b4J\r\u00109\u001a\u00020:H\u0000¢\u0006\u0002\b;J\b\u0010<\u001a\u00020:H\u0016J\u0013\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010@\u001a\u00020\u001eH\u0016J!\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\t2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020B0\tH\u0000¢\u0006\u0002\bDJ\r\u0010E\u001a\u00020>H\u0000¢\u0006\u0002\bFJ\r\u0010G\u001a\u00020>H\u0000¢\u0006\u0002\bHJ\u0015\u0010I\u001a\u00020>2\u0006\u0010J\u001a\u00020KH\u0000¢\u0006\u0002\bLJ\b\u0010M\u001a\u00020NH\u0002J\u001d\u0010O\u001a\u0012\u0012\u0004\u0012\u00020K0Pj\b\u0012\u0004\u0012\u00020K`QH\u0000¢\u0006\u0002\bRJ\r\u0010S\u001a\u00020TH\u0000¢\u0006\u0002\bUJ\b\u0010V\u001a\u00020WH\u0002R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR%\u00105\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b6\u0010\u0018¨\u0006Z"}, d2 = {"Lcom/google/firebase/firestore/RealtimePipeline;", "", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "serializer", "Lcom/google/firebase/firestore/remote/RemoteSerializer;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "stages", "", "Lcom/google/firebase/firestore/pipeline/Stage;", "internalOptions", "Lcom/google/firebase/firestore/core/EventManager$ListenOptions;", "<init>", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/firestore/remote/RemoteSerializer;Lcom/google/firebase/firestore/UserDataReader;Ljava/util/List;Lcom/google/firebase/firestore/core/EventManager$ListenOptions;)V", "stage", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/firestore/remote/RemoteSerializer;Lcom/google/firebase/firestore/UserDataReader;Lcom/google/firebase/firestore/pipeline/Stage;)V", "getFirestore$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/FirebaseFirestore;", "getSerializer$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/remote/RemoteSerializer;", "getUserDataReader$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/UserDataReader;", "getStages$com_google_firebase_firebase_firestore", "()Ljava/util/List;", "getInternalOptions$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/core/EventManager$ListenOptions;", "with", "append", "limit", "", "sort", "order", "Lcom/google/firebase/firestore/pipeline/Ordering;", "additionalOrders", "", "(Lcom/google/firebase/firestore/pipeline/Ordering;[Lcom/google/firebase/firestore/pipeline/Ordering;)Lcom/google/firebase/firestore/RealtimePipeline;", "where", "condition", "Lcom/google/firebase/firestore/pipeline/BooleanExpression;", "snapshots", "Lkotlinx/coroutines/flow/Flow;", "Lcom/google/firebase/firestore/RealtimePipeline$Snapshot;", "options", "Lcom/google/firebase/firestore/RealtimePipeline$ListenOptions;", "addSnapshotListener", "Lcom/google/firebase/firestore/ListenerRegistration;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/google/firebase/firestore/EventListener;", "executor", "Ljava/util/concurrent/Executor;", "withListenOptions", "withListenOptions$com_google_firebase_firebase_firestore", "rewrittenStages", "getRewrittenStages$com_google_firebase_firebase_firestore", "rewrittenStages$delegate", "Lkotlin/Lazy;", "canonicalId", "", "canonicalId$com_google_firebase_firebase_firestore", "toString", "equals", "", "other", "hashCode", "evaluate", "Lcom/google/firebase/firestore/model/MutableDocument;", "inputs", "evaluate$com_google_firebase_firebase_firestore", "matchesAllDocuments", "matchesAllDocuments$com_google_firebase_firebase_firestore", "hasLimit", "hasLimit$com_google_firebase_firebase_firestore", "matches", "doc", "Lcom/google/firebase/firestore/model/Document;", "matches$com_google_firebase_firebase_firestore", "evaluateContext", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator$com_google_firebase_firebase_firestore", "toStructurePipelineProto", "Lcom/google/firestore/v1/StructuredPipeline;", "toStructurePipelineProto$com_google_firebase_firebase_firestore", "getLastEffectiveSortStage", "Lcom/google/firebase/firestore/pipeline/SortStage;", "ListenOptions", "Snapshot", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class RealtimePipeline {
    private final FirebaseFirestore firestore;
    private final EventManager.ListenOptions internalOptions;

    /* JADX INFO: renamed from: rewrittenStages$delegate, reason: from kotlin metadata */
    private final Lazy rewrittenStages;
    private final RemoteSerializer serializer;
    private final List<Stage<?>> stages;
    private final UserDataReader userDataReader;

    /* JADX WARN: Multi-variable type inference failed */
    public RealtimePipeline(FirebaseFirestore firebaseFirestore, RemoteSerializer serializer, UserDataReader userDataReader, List<? extends Stage<?>> stages, EventManager.ListenOptions listenOptions) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        Intrinsics.checkNotNullParameter(stages, "stages");
        this.firestore = firebaseFirestore;
        this.serializer = serializer;
        this.userDataReader = userDataReader;
        this.stages = stages;
        this.internalOptions = listenOptions;
        this.rewrittenStages = LazyKt.lazy(new Function0() { // from class: com.google.firebase.firestore.RealtimePipeline$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return RealtimePipeline.rewrittenStages_delegate$lambda$5(this.f$0);
            }
        });
    }

    public /* synthetic */ RealtimePipeline(FirebaseFirestore firebaseFirestore, RemoteSerializer remoteSerializer, UserDataReader userDataReader, List list, EventManager.ListenOptions listenOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firebaseFirestore, remoteSerializer, userDataReader, list, (i & 16) != 0 ? null : listenOptions);
    }

    /* JADX INFO: renamed from: getFirestore$com_google_firebase_firebase_firestore, reason: from getter */
    public final FirebaseFirestore getFirestore() {
        return this.firestore;
    }

    /* JADX INFO: renamed from: getSerializer$com_google_firebase_firebase_firestore, reason: from getter */
    public final RemoteSerializer getSerializer() {
        return this.serializer;
    }

    /* JADX INFO: renamed from: getUserDataReader$com_google_firebase_firebase_firestore, reason: from getter */
    public final UserDataReader getUserDataReader() {
        return this.userDataReader;
    }

    public final List<Stage<?>> getStages$com_google_firebase_firebase_firestore() {
        return this.stages;
    }

    /* JADX INFO: renamed from: getInternalOptions$com_google_firebase_firebase_firestore, reason: from getter */
    public final EventManager.ListenOptions getInternalOptions() {
        return this.internalOptions;
    }

    /* JADX INFO: compiled from: RealtimePipeline.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B)\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bB\t\b\u0016¢\u0006\u0004\b\n\u0010\fJ\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\r\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Lcom/google/firebase/firestore/RealtimePipeline$ListenOptions;", "", "source", "Lcom/google/firebase/firestore/ListenSource;", "serverTimestampBehavior", "Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;", "metadataChanges", "Lcom/google/firebase/firestore/MetadataChanges;", "options", "Lcom/google/firebase/firestore/pipeline/InternalOptions;", "<init>", "(Lcom/google/firebase/firestore/ListenSource;Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;Lcom/google/firebase/firestore/MetadataChanges;Lcom/google/firebase/firestore/pipeline/InternalOptions;)V", "()V", "getSource$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/ListenSource;", "getServerTimestampBehavior$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;", "getMetadataChanges$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/MetadataChanges;", "withSource", "withServerTimestampBehavior", "withMetadataChanges", "toListenOptions", "Lcom/google/firebase/firestore/core/EventManager$ListenOptions;", "toListenOptions$com_google_firebase_firebase_firestore", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class ListenOptions {
        public static final ListenOptions DEFAULT = new ListenOptions(ListenSource.DEFAULT, DocumentSnapshot.ServerTimestampBehavior.NONE, MetadataChanges.EXCLUDE, InternalOptions.EMPTY);
        private final MetadataChanges metadataChanges;
        private final DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior;
        private final ListenSource source;

        private ListenOptions(ListenSource listenSource, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior, MetadataChanges metadataChanges, InternalOptions internalOptions) {
            this.source = listenSource;
            this.serverTimestampBehavior = serverTimestampBehavior;
            this.metadataChanges = metadataChanges;
        }

        /* JADX INFO: renamed from: getSource$com_google_firebase_firebase_firestore, reason: from getter */
        public final ListenSource getSource() {
            return this.source;
        }

        /* JADX INFO: renamed from: getServerTimestampBehavior$com_google_firebase_firebase_firestore, reason: from getter */
        public final DocumentSnapshot.ServerTimestampBehavior getServerTimestampBehavior() {
            return this.serverTimestampBehavior;
        }

        /* JADX INFO: renamed from: getMetadataChanges$com_google_firebase_firebase_firestore, reason: from getter */
        public final MetadataChanges getMetadataChanges() {
            return this.metadataChanges;
        }

        public ListenOptions() {
            this(ListenSource.DEFAULT, DocumentSnapshot.ServerTimestampBehavior.NONE, MetadataChanges.EXCLUDE, InternalOptions.EMPTY);
        }

        public final ListenOptions withSource(ListenSource source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new ListenOptions(source, this.serverTimestampBehavior, this.metadataChanges, InternalOptions.EMPTY);
        }

        public final ListenOptions withServerTimestampBehavior(DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
            Intrinsics.checkNotNullParameter(serverTimestampBehavior, "serverTimestampBehavior");
            return new ListenOptions(this.source, serverTimestampBehavior, this.metadataChanges, InternalOptions.EMPTY);
        }

        public final ListenOptions withMetadataChanges(MetadataChanges metadataChanges) {
            Intrinsics.checkNotNullParameter(metadataChanges, "metadataChanges");
            return new ListenOptions(this.source, this.serverTimestampBehavior, metadataChanges, InternalOptions.EMPTY);
        }

        public final EventManager.ListenOptions toListenOptions$com_google_firebase_firebase_firestore() {
            EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
            listenOptions.source = this.source;
            listenOptions.includeQueryMetadataChanges = this.metadataChanges == MetadataChanges.INCLUDE;
            listenOptions.includeDocumentMetadataChanges = this.metadataChanges == MetadataChanges.INCLUDE;
            listenOptions.waitForSyncWhenOnline = false;
            listenOptions.serverTimestampBehavior = this.serverTimestampBehavior;
            return listenOptions;
        }
    }

    /* JADX INFO: compiled from: RealtimePipeline.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000f2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0019"}, d2 = {"Lcom/google/firebase/firestore/RealtimePipeline$Snapshot;", "", "viewSnapshot", "Lcom/google/firebase/firestore/core/ViewSnapshot;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "options", "Lcom/google/firebase/firestore/RealtimePipeline$ListenOptions;", "<init>", "(Lcom/google/firebase/firestore/core/ViewSnapshot;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/firestore/RealtimePipeline$ListenOptions;)V", "metadata", "Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$SnapshotMetadata;", "getMetadata", "()Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$SnapshotMetadata;", "results", "", "Lcom/google/firebase/firestore/PipelineResult;", "getResults", "()Ljava/util/List;", "getChanges", "Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$ResultChange;", "metadataChanges", "Lcom/google/firebase/firestore/MetadataChanges;", "SnapshotMetadata", "ResultChange", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Snapshot {
        private final FirebaseFirestore firestore;
        private final ListenOptions options;
        private final ViewSnapshot viewSnapshot;

        public Snapshot(ViewSnapshot viewSnapshot, FirebaseFirestore firestore, ListenOptions options) {
            Intrinsics.checkNotNullParameter(viewSnapshot, "viewSnapshot");
            Intrinsics.checkNotNullParameter(firestore, "firestore");
            Intrinsics.checkNotNullParameter(options, "options");
            this.viewSnapshot = viewSnapshot;
            this.firestore = firestore;
            this.options = options;
        }

        /* JADX INFO: compiled from: RealtimePipeline.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$SnapshotMetadata;", "", "hasPendingWrites", "", "isConsistentBetweenListeners", "<init>", "(ZZ)V", "getHasPendingWrites", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final /* data */ class SnapshotMetadata {
            private final boolean hasPendingWrites;
            private final boolean isConsistentBetweenListeners;

            public static /* synthetic */ SnapshotMetadata copy$default(SnapshotMetadata snapshotMetadata, boolean z, boolean z2, int i, Object obj) {
                if ((i & 1) != 0) {
                    z = snapshotMetadata.hasPendingWrites;
                }
                if ((i & 2) != 0) {
                    z2 = snapshotMetadata.isConsistentBetweenListeners;
                }
                return snapshotMetadata.copy(z, z2);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final boolean getHasPendingWrites() {
                return this.hasPendingWrites;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final boolean getIsConsistentBetweenListeners() {
                return this.isConsistentBetweenListeners;
            }

            public final SnapshotMetadata copy(boolean hasPendingWrites, boolean isConsistentBetweenListeners) {
                return new SnapshotMetadata(hasPendingWrites, isConsistentBetweenListeners);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SnapshotMetadata)) {
                    return false;
                }
                SnapshotMetadata snapshotMetadata = (SnapshotMetadata) other;
                return this.hasPendingWrites == snapshotMetadata.hasPendingWrites && this.isConsistentBetweenListeners == snapshotMetadata.isConsistentBetweenListeners;
            }

            public int hashCode() {
                return (Boolean.hashCode(this.hasPendingWrites) * 31) + Boolean.hashCode(this.isConsistentBetweenListeners);
            }

            public String toString() {
                return "SnapshotMetadata(hasPendingWrites=" + this.hasPendingWrites + ", isConsistentBetweenListeners=" + this.isConsistentBetweenListeners + ')';
            }

            public SnapshotMetadata(boolean z, boolean z2) {
                this.hasPendingWrites = z;
                this.isConsistentBetweenListeners = z2;
            }

            public final boolean getHasPendingWrites() {
                return this.hasPendingWrites;
            }

            public final boolean isConsistentBetweenListeners() {
                return this.isConsistentBetweenListeners;
            }
        }

        /* JADX INFO: compiled from: RealtimePipeline.kt */
        @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u0000 (2\u00020\u0001:\u0002'(B-\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nB9\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0011\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\u0012J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J:\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0007HÖ\u0001J\t\u0010%\u001a\u00020&HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001a\u0010\u0018¨\u0006)"}, d2 = {"Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$ResultChange;", "", "result", "Lcom/google/firebase/firestore/PipelineResult;", "type", "Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$ResultChange$ChangeType;", "oldIndex", "", "newIndex", "<init>", "(Lcom/google/firebase/firestore/PipelineResult;Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$ResultChange$ChangeType;Ljava/lang/Integer;Ljava/lang/Integer;)V", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "doc", "Lcom/google/firebase/firestore/model/Document;", "serverTimestampBehavior", "Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;", "Lcom/google/firebase/firestore/DocumentChange$Type;", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/firestore/model/Document;Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;Lcom/google/firebase/firestore/DocumentChange$Type;II)V", "getResult", "()Lcom/google/firebase/firestore/PipelineResult;", "getType", "()Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$ResultChange$ChangeType;", "getOldIndex", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNewIndex", "component1", "component2", "component3", "component4", "copy", "(Lcom/google/firebase/firestore/PipelineResult;Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$ResultChange$ChangeType;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$ResultChange;", "equals", "", "other", "hashCode", "toString", "", "ChangeType", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final /* data */ class ResultChange {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private final Integer newIndex;
            private final Integer oldIndex;
            private final PipelineResult result;
            private final ChangeType type;

            public static /* synthetic */ ResultChange copy$default(ResultChange resultChange, PipelineResult pipelineResult, ChangeType changeType, Integer num, Integer num2, int i, Object obj) {
                if ((i & 1) != 0) {
                    pipelineResult = resultChange.result;
                }
                if ((i & 2) != 0) {
                    changeType = resultChange.type;
                }
                if ((i & 4) != 0) {
                    num = resultChange.oldIndex;
                }
                if ((i & 8) != 0) {
                    num2 = resultChange.newIndex;
                }
                return resultChange.copy(pipelineResult, changeType, num, num2);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final PipelineResult getResult() {
                return this.result;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final ChangeType getType() {
                return this.type;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final Integer getOldIndex() {
                return this.oldIndex;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final Integer getNewIndex() {
                return this.newIndex;
            }

            public final ResultChange copy(PipelineResult result, ChangeType type, Integer oldIndex, Integer newIndex) {
                Intrinsics.checkNotNullParameter(result, "result");
                Intrinsics.checkNotNullParameter(type, "type");
                return new ResultChange(result, type, oldIndex, newIndex);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ResultChange)) {
                    return false;
                }
                ResultChange resultChange = (ResultChange) other;
                return Intrinsics.areEqual(this.result, resultChange.result) && this.type == resultChange.type && Intrinsics.areEqual(this.oldIndex, resultChange.oldIndex) && Intrinsics.areEqual(this.newIndex, resultChange.newIndex);
            }

            public int hashCode() {
                int iHashCode = ((this.result.hashCode() * 31) + this.type.hashCode()) * 31;
                Integer num = this.oldIndex;
                int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
                Integer num2 = this.newIndex;
                return iHashCode2 + (num2 != null ? num2.hashCode() : 0);
            }

            public String toString() {
                return "ResultChange(result=" + this.result + ", type=" + this.type + ", oldIndex=" + this.oldIndex + ", newIndex=" + this.newIndex + ')';
            }

            public ResultChange(PipelineResult result, ChangeType type, Integer num, Integer num2) {
                Intrinsics.checkNotNullParameter(result, "result");
                Intrinsics.checkNotNullParameter(type, "type");
                this.result = result;
                this.type = type;
                this.oldIndex = num;
                this.newIndex = num2;
            }

            public final PipelineResult getResult() {
                return this.result;
            }

            public final ChangeType getType() {
                return this.type;
            }

            public final Integer getOldIndex() {
                return this.oldIndex;
            }

            public final Integer getNewIndex() {
                return this.newIndex;
            }

            /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
            /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
            /* JADX INFO: compiled from: RealtimePipeline.kt */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$ResultChange$ChangeType;", "", "<init>", "(Ljava/lang/String;I)V", "ADDED", "MODIFIED", "REMOVED", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            public static final class ChangeType {
                private static final /* synthetic */ EnumEntries $ENTRIES;
                private static final /* synthetic */ ChangeType[] $VALUES;
                public static final ChangeType ADDED = new ChangeType("ADDED", 0);
                public static final ChangeType MODIFIED = new ChangeType("MODIFIED", 1);
                public static final ChangeType REMOVED = new ChangeType("REMOVED", 2);

                private static final /* synthetic */ ChangeType[] $values() {
                    return new ChangeType[]{ADDED, MODIFIED, REMOVED};
                }

                public static EnumEntries<ChangeType> getEntries() {
                    return $ENTRIES;
                }

                private ChangeType(String str, int i) {
                }

                static {
                    ChangeType[] changeTypeArr$values = $values();
                    $VALUES = changeTypeArr$values;
                    $ENTRIES = EnumEntriesKt.enumEntries(changeTypeArr$values);
                }

                public static ChangeType valueOf(String str) {
                    return (ChangeType) Enum.valueOf(ChangeType.class, str);
                }

                public static ChangeType[] values() {
                    return (ChangeType[]) $VALUES.clone();
                }
            }

            /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
            public ResultChange(FirebaseFirestore firestore, Document doc, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior, DocumentChange.Type type, int i, int i2) {
                this(new PipelineResult(doc, serverTimestampBehavior, firestore), INSTANCE.getChangeType(type), Integer.valueOf(i), Integer.valueOf(i2));
                Intrinsics.checkNotNullParameter(firestore, "firestore");
                Intrinsics.checkNotNullParameter(doc, "doc");
                Intrinsics.checkNotNullParameter(serverTimestampBehavior, "serverTimestampBehavior");
                Intrinsics.checkNotNullParameter(type, "type");
            }

            /* JADX INFO: compiled from: RealtimePipeline.kt */
            @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\b"}, d2 = {"Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$ResultChange$Companion;", "", "<init>", "()V", "getChangeType", "Lcom/google/firebase/firestore/RealtimePipeline$Snapshot$ResultChange$ChangeType;", "type", "Lcom/google/firebase/firestore/DocumentChange$Type;", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            public static final class Companion {

                /* JADX INFO: compiled from: RealtimePipeline.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[DocumentChange.Type.values().length];
                        try {
                            iArr[DocumentChange.Type.ADDED.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[DocumentChange.Type.MODIFIED.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[DocumentChange.Type.REMOVED.ordinal()] = 3;
                        } catch (NoSuchFieldError unused3) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                /* JADX INFO: Access modifiers changed from: private */
                public final ChangeType getChangeType(DocumentChange.Type type) {
                    int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
                    if (i == 1) {
                        return ChangeType.ADDED;
                    }
                    if (i == 2) {
                        return ChangeType.MODIFIED;
                    }
                    if (i != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    return ChangeType.REMOVED;
                }
            }
        }

        public final SnapshotMetadata getMetadata() {
            return new SnapshotMetadata(this.viewSnapshot.hasPendingWrites(), !this.viewSnapshot.isFromCache());
        }

        public final List<PipelineResult> getResults() {
            DocumentSet documents = this.viewSnapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            DocumentSet documentSet = documents;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(documentSet, 10));
            for (Document document : documentSet) {
                Intrinsics.checkNotNull(document);
                arrayList.add(new PipelineResult(document, this.options.getServerTimestampBehavior(), this.firestore));
            }
            return arrayList;
        }

        public static /* synthetic */ List getChanges$default(Snapshot snapshot, MetadataChanges metadataChanges, int i, Object obj) {
            if ((i & 1) != 0) {
                metadataChanges = null;
            }
            return snapshot.getChanges(metadataChanges);
        }

        public final List<ResultChange> getChanges(MetadataChanges metadataChanges) {
            if (metadataChanges == null) {
                metadataChanges = MetadataChanges.EXCLUDE;
            }
            return RealtimePipelineKt.changesFromSnapshot(metadataChanges, this.viewSnapshot, new Function4() { // from class: com.google.firebase.firestore.RealtimePipeline$Snapshot$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    return RealtimePipeline.Snapshot.getChanges$lambda$1(this.f$0, (Document) obj, (DocumentChange.Type) obj2, ((Integer) obj3).intValue(), ((Integer) obj4).intValue());
                }
            });
        }

        static final ResultChange getChanges$lambda$1(Snapshot snapshot, Document doc, DocumentChange.Type type, int i, int i2) {
            Intrinsics.checkNotNullParameter(doc, "doc");
            Intrinsics.checkNotNullParameter(type, "type");
            return new ResultChange(snapshot.firestore, doc, snapshot.options.getServerTimestampBehavior(), type, i, i2);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RealtimePipeline(FirebaseFirestore firestore, RemoteSerializer serializer, UserDataReader userDataReader, Stage<?> stage) {
        this(firestore, serializer, userDataReader, CollectionsKt.listOf(stage), null, 16, null);
        Intrinsics.checkNotNullParameter(firestore, "firestore");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        Intrinsics.checkNotNullParameter(stage, "stage");
    }

    private final RealtimePipeline with(List<? extends Stage<?>> stages) {
        return new RealtimePipeline(this.firestore, this.serializer, this.userDataReader, stages, null, 16, null);
    }

    private final RealtimePipeline append(Stage<?> stage) {
        return with(CollectionsKt.plus((Collection<? extends Stage<?>>) this.stages, stage));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final RealtimePipeline limit(int limit) {
        return append(new LimitStage(limit, null, 2, 0 == true ? 1 : 0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final RealtimePipeline sort(Ordering order, Ordering... additionalOrders) {
        Intrinsics.checkNotNullParameter(order, "order");
        Intrinsics.checkNotNullParameter(additionalOrders, "additionalOrders");
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(order);
        spreadBuilder.addSpread(additionalOrders);
        return append(new SortStage((Ordering[]) spreadBuilder.toArray(new Ordering[spreadBuilder.size()]), null, 2, 0 == true ? 1 : 0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final RealtimePipeline where(BooleanExpression condition) {
        Intrinsics.checkNotNullParameter(condition, "condition");
        return append(new WhereStage(condition, null, 2, 0 == true ? 1 : 0));
    }

    public final Flow<Snapshot> snapshots() {
        return snapshots(ListenOptions.DEFAULT);
    }

    /* JADX INFO: renamed from: com.google.firebase.firestore.RealtimePipeline$snapshots$1, reason: invalid class name */
    /* JADX INFO: compiled from: RealtimePipeline.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lcom/google/firebase/firestore/RealtimePipeline$Snapshot;"}, k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.google.firebase.firestore.RealtimePipeline$snapshots$1", f = "RealtimePipeline.kt", i = {}, l = {428}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super Snapshot>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ListenOptions $options;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ListenOptions listenOptions, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$options = listenOptions;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = RealtimePipeline.this.new AnonymousClass1(this.$options, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super Snapshot> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = (ProducerScope) this.L$0;
                final ListenerRegistration listenerRegistrationAddSnapshotListener = RealtimePipeline.this.addSnapshotListener(this.$options, new EventListener() { // from class: com.google.firebase.firestore.RealtimePipeline$snapshots$1$$ExternalSyntheticLambda0
                    @Override // com.google.firebase.firestore.EventListener
                    public final void onEvent(Object obj2, FirebaseFirestoreException firebaseFirestoreException) {
                        RealtimePipeline.AnonymousClass1.invokeSuspend$lambda$0(producerScope, (RealtimePipeline.Snapshot) obj2, firebaseFirestoreException);
                    }
                });
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, new Function0() { // from class: com.google.firebase.firestore.RealtimePipeline$snapshots$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RealtimePipeline.AnonymousClass1.invokeSuspend$lambda$1(listenerRegistrationAddSnapshotListener);
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        static final void invokeSuspend$lambda$0(ProducerScope producerScope, Snapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
            if (snapshot != null) {
                ChannelResult.m2282boximpl(producerScope.mo2268trySendJP2dKIU(snapshot));
            } else {
                Boolean.valueOf(producerScope.close(firebaseFirestoreException));
            }
        }

        static final Unit invokeSuspend$lambda$1(ListenerRegistration listenerRegistration) {
            listenerRegistration.remove();
            return Unit.INSTANCE;
        }
    }

    public final Flow<Snapshot> snapshots(ListenOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return FlowKt.callbackFlow(new AnonymousClass1(options, null));
    }

    public final ListenerRegistration addSnapshotListener(EventListener<Snapshot> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return addSnapshotListener(ListenOptions.DEFAULT, listener);
    }

    public final ListenerRegistration addSnapshotListener(ListenOptions options, EventListener<Snapshot> listener) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Executor DEFAULT_CALLBACK_EXECUTOR = Executors.DEFAULT_CALLBACK_EXECUTOR;
        Intrinsics.checkNotNullExpressionValue(DEFAULT_CALLBACK_EXECUTOR, "DEFAULT_CALLBACK_EXECUTOR");
        return addSnapshotListener(DEFAULT_CALLBACK_EXECUTOR, options, listener);
    }

    public final ListenerRegistration addSnapshotListener(Executor executor, EventListener<Snapshot> listener) {
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(listener, "listener");
        return addSnapshotListener(executor, ListenOptions.DEFAULT, listener);
    }

    public final ListenerRegistration addSnapshotListener(Executor executor, final ListenOptions options, final EventListener<Snapshot> listener) {
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(listener, "listener");
        final AsyncEventListener asyncEventListener = new AsyncEventListener(executor, new EventListener() { // from class: com.google.firebase.firestore.RealtimePipeline$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                RealtimePipeline.addSnapshotListener$lambda$1(listener, this, options, (ViewSnapshot) obj, firebaseFirestoreException);
            }
        });
        FirebaseFirestore firebaseFirestore = this.firestore;
        Intrinsics.checkNotNull(firebaseFirestore);
        Object objCallClient = firebaseFirestore.callClient(new Function() { // from class: com.google.firebase.firestore.RealtimePipeline$$ExternalSyntheticLambda4
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return RealtimePipeline.addSnapshotListener$lambda$3(this.f$0, options, asyncEventListener, (FirestoreClient) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(objCallClient, "callClient(...)");
        return (ListenerRegistration) objCallClient;
    }

    static final void addSnapshotListener$lambda$1(EventListener eventListener, RealtimePipeline realtimePipeline, ListenOptions listenOptions, ViewSnapshot viewSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        Snapshot snapshot;
        if (viewSnapshot != null) {
            FirebaseFirestore firebaseFirestore = realtimePipeline.firestore;
            Intrinsics.checkNotNull(firebaseFirestore);
            snapshot = new Snapshot(viewSnapshot, firebaseFirestore, listenOptions);
        } else {
            snapshot = null;
        }
        eventListener.onEvent(snapshot, firebaseFirestoreException);
    }

    static final ListenerRegistration addSnapshotListener$lambda$3(RealtimePipeline realtimePipeline, ListenOptions listenOptions, final AsyncEventListener asyncEventListener, final FirestoreClient firestoreClient) {
        Intrinsics.checkNotNull(firestoreClient);
        final QueryListener queryListenerListen = firestoreClient.listen(new QueryOrPipeline.PipelineWrapper(realtimePipeline), listenOptions.toListenOptions$com_google_firebase_firebase_firestore(), asyncEventListener);
        Intrinsics.checkNotNullExpressionValue(queryListenerListen, "listen(...)");
        return new ListenerRegistration() { // from class: com.google.firebase.firestore.RealtimePipeline$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.ListenerRegistration
            public final void remove() {
                RealtimePipeline.addSnapshotListener$lambda$3$lambda$2(asyncEventListener, firestoreClient, queryListenerListen);
            }
        };
    }

    static final void addSnapshotListener$lambda$3$lambda$2(AsyncEventListener asyncEventListener, FirestoreClient firestoreClient, QueryListener queryListener) {
        asyncEventListener.mute();
        Intrinsics.checkNotNull(firestoreClient);
        firestoreClient.stopListening(queryListener);
    }

    public final RealtimePipeline withListenOptions$com_google_firebase_firebase_firestore(EventManager.ListenOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return new RealtimePipeline(this.firestore, this.serializer, this.userDataReader, this.stages, options);
    }

    public final List<Stage<?>> getRewrittenStages$com_google_firebase_firebase_firestore() {
        return (List) this.rewrittenStages.getValue();
    }

    static final List rewrittenStages_delegate$lambda$5(RealtimePipeline realtimePipeline) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        boolean z = false;
        for (Stage<?> stage : realtimePipeline.stages) {
            if ((stage instanceof LimitStage) || (stage instanceof OffsetStage)) {
                if (!z) {
                    listCreateListBuilder.add(SortStage.INSTANCE.getBY_DOCUMENT_ID$com_google_firebase_firebase_firestore());
                    z = true;
                }
                listCreateListBuilder.add(stage);
            } else if (stage instanceof SortStage) {
                listCreateListBuilder.add(((SortStage) stage).withStableOrdering$com_google_firebase_firebase_firestore());
                z = true;
            } else {
                listCreateListBuilder.add(stage);
            }
        }
        if (!z) {
            listCreateListBuilder.add(SortStage.INSTANCE.getBY_DOCUMENT_ID$com_google_firebase_firebase_firestore());
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    static final CharSequence canonicalId$lambda$6(Stage stage) {
        Intrinsics.checkNotNullParameter(stage, "stage");
        return stage.canonicalId$com_google_firebase_firebase_firestore();
    }

    public final String canonicalId$com_google_firebase_firebase_firestore() {
        return CollectionsKt.joinToString$default(getRewrittenStages$com_google_firebase_firebase_firestore(), "|", null, null, 0, null, new Function1() { // from class: com.google.firebase.firestore.RealtimePipeline$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RealtimePipeline.canonicalId$lambda$6((Stage) obj);
            }
        }, 30, null);
    }

    public String toString() {
        return canonicalId$com_google_firebase_firebase_firestore();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RealtimePipeline)) {
            return false;
        }
        RealtimePipeline realtimePipeline = (RealtimePipeline) other;
        if (Intrinsics.areEqual(this.serializer.databaseId(), realtimePipeline.serializer.databaseId())) {
            return Intrinsics.areEqual(getRewrittenStages$com_google_firebase_firebase_firestore(), realtimePipeline.getRewrittenStages$com_google_firebase_firebase_firestore());
        }
        return false;
    }

    public int hashCode() {
        return (this.serializer.databaseId().hashCode() * 31) + this.stages.hashCode();
    }

    public final List<MutableDocument> evaluate$com_google_firebase_firebase_firestore(List<MutableDocument> inputs) {
        Intrinsics.checkNotNullParameter(inputs, "inputs");
        EvaluationContext evaluationContext = new EvaluationContext(this);
        Iterator<T> it = getRewrittenStages$com_google_firebase_firebase_firestore().iterator();
        while (it.hasNext()) {
            inputs = ((Stage) it.next()).evaluate$com_google_firebase_firebase_firestore(evaluationContext, inputs);
        }
        return inputs;
    }

    public final boolean matchesAllDocuments$com_google_firebase_firebase_firestore() {
        com.google.firebase.firestore.model.FieldPath fieldPath;
        for (Stage<?> stage : getRewrittenStages$com_google_firebase_firebase_firestore()) {
            if (Intrinsics.areEqual(stage.getName(), "limit")) {
                return false;
            }
            if (stage instanceof WhereStage) {
                Expression condition$com_google_firebase_firebase_firestore = ((WhereStage) stage).getCondition();
                BooleanFunctionExpression booleanFunctionExpression = condition$com_google_firebase_firebase_firestore instanceof BooleanFunctionExpression ? (BooleanFunctionExpression) condition$com_google_firebase_firebase_firestore : null;
                Expression expr = booleanFunctionExpression != null ? booleanFunctionExpression.getExpr() : null;
                FunctionExpression functionExpression = expr instanceof FunctionExpression ? (FunctionExpression) expr : null;
                if (Intrinsics.areEqual(functionExpression != null ? functionExpression.getName() : null, "exists") && functionExpression.getParams().length == 1) {
                    Expression expression = functionExpression != null ? functionExpression.getParams()[0] : null;
                    Field field = expression instanceof Field ? (Field) expression : null;
                    if (field == null || (fieldPath = field.getFieldPath()) == null || !fieldPath.isKeyField()) {
                    }
                }
                return false;
            }
        }
        return true;
    }

    public final boolean hasLimit$com_google_firebase_firebase_firestore() {
        Iterator<Stage<?>> it = getRewrittenStages$com_google_firebase_firebase_firestore().iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next().getName(), "limit")) {
                return true;
            }
        }
        return false;
    }

    public final boolean matches$com_google_firebase_firebase_firestore(Document doc) {
        Intrinsics.checkNotNullParameter(doc, "doc");
        return !evaluate$com_google_firebase_firebase_firestore(CollectionsKt.listOf((MutableDocument) doc)).isEmpty();
    }

    private final EvaluationContext evaluateContext() {
        return new EvaluationContext(this);
    }

    public final Comparator<Document> comparator$com_google_firebase_firebase_firestore() {
        return getLastEffectiveSortStage().comparator$com_google_firebase_firebase_firestore(evaluateContext());
    }

    public final StructuredPipeline toStructurePipelineProto$com_google_firebase_firebase_firestore() {
        StructuredPipeline.Builder builderNewBuilder = StructuredPipeline.newBuilder();
        Pipeline.Builder builderNewBuilder2 = com.google.firestore.v1.Pipeline.newBuilder();
        List<Stage<?>> rewrittenStages$com_google_firebase_firebase_firestore = getRewrittenStages$com_google_firebase_firebase_firestore();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(rewrittenStages$com_google_firebase_firebase_firestore, 10));
        Iterator<T> it = rewrittenStages$com_google_firebase_firebase_firestore.iterator();
        while (it.hasNext()) {
            arrayList.add(((Stage) it.next()).toProtoStage$com_google_firebase_firebase_firestore(this.userDataReader));
        }
        builderNewBuilder.setPipeline(builderNewBuilder2.addAllStages(arrayList).build());
        StructuredPipeline structuredPipelineBuild = builderNewBuilder.build();
        Intrinsics.checkNotNullExpressionValue(structuredPipelineBuild, "build(...)");
        return structuredPipelineBuild;
    }

    private final SortStage getLastEffectiveSortStage() {
        for (Stage stage : CollectionsKt.asReversed(getRewrittenStages$com_google_firebase_firebase_firestore())) {
            if (stage instanceof SortStage) {
                return (SortStage) stage;
            }
        }
        throw Assert.fail("RealtimePipeline must contain at least one Sort stage (ensured by RewriteStages).", new Object[0]);
    }
}
