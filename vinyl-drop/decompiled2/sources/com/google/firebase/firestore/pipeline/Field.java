package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResult;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResultUnset;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResultValue;
import com.google.firebase.firestore.pipeline.evaluation.EvaluationContext;
import com.google.firestore.v1.Value;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: expressions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0010¢\u0006\u0002\b\u0014J\r\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0014J!\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0010¢\u0006\u0002\b\u001bJ\u0018\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\r\u0010\u001e\u001a\u00020\tH\u0010¢\u0006\u0002\b\u001fJ\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0096\u0002J\b\u0010$\u001a\u00020%H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006'"}, d2 = {"Lcom/google/firebase/firestore/pipeline/Field;", "Lcom/google/firebase/firestore/pipeline/Selectable;", "fieldPath", "Lcom/google/firebase/firestore/model/FieldPath;", "<init>", "(Lcom/google/firebase/firestore/model/FieldPath;)V", "getFieldPath$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/model/FieldPath;", "alias", "", "getAlias$com_google_firebase_firebase_firestore", "()Ljava/lang/String;", "expr", "Lcom/google/firebase/firestore/pipeline/Expression;", "getExpr$com_google_firebase_firebase_firestore", "()Lcom/google/firebase/firestore/pipeline/Expression;", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "toProto$com_google_firebase_firebase_firestore", "evaluateFunction", "Lkotlin/Function1;", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "context", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "evaluateFunction$com_google_firebase_firebase_firestore", "getServerTimestamp", "fieldValue", "canonicalId", "canonicalId$com_google_firebase_firebase_firestore", "equals", "", "other", "", "hashCode", "", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class Field extends Selectable {
    public static final Field CREATE_TIME;
    public static final Field DOCUMENT_ID;
    public static final Field UPDATE_TIME;
    private final String alias;
    private final Expression expr;
    private final FieldPath fieldPath;

    /* JADX INFO: compiled from: expressions.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DocumentSnapshot.ServerTimestampBehavior.values().length];
            try {
                iArr[DocumentSnapshot.ServerTimestampBehavior.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentSnapshot.ServerTimestampBehavior.ESTIMATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentSnapshot.ServerTimestampBehavior.PREVIOUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public Field(FieldPath fieldPath) {
        Intrinsics.checkNotNullParameter(fieldPath, "fieldPath");
        this.fieldPath = fieldPath;
        String strCanonicalString = fieldPath.canonicalString();
        Intrinsics.checkNotNullExpressionValue(strCanonicalString, "canonicalString(...)");
        this.alias = strCanonicalString;
        this.expr = this;
    }

    /* JADX INFO: renamed from: getFieldPath$com_google_firebase_firebase_firestore, reason: from getter */
    public final FieldPath getFieldPath() {
        return this.fieldPath;
    }

    static {
        FieldPath KEY_PATH = FieldPath.KEY_PATH;
        Intrinsics.checkNotNullExpressionValue(KEY_PATH, "KEY_PATH");
        DOCUMENT_ID = new Field(KEY_PATH);
        FieldPath UPDATE_TIME_PATH = FieldPath.UPDATE_TIME_PATH;
        Intrinsics.checkNotNullExpressionValue(UPDATE_TIME_PATH, "UPDATE_TIME_PATH");
        UPDATE_TIME = new Field(UPDATE_TIME_PATH);
        FieldPath CREATE_TIME_PATH = FieldPath.CREATE_TIME_PATH;
        Intrinsics.checkNotNullExpressionValue(CREATE_TIME_PATH, "CREATE_TIME_PATH");
        CREATE_TIME = new Field(CREATE_TIME_PATH);
    }

    @Override // com.google.firebase.firestore.pipeline.Selectable
    /* JADX INFO: renamed from: getAlias$com_google_firebase_firebase_firestore, reason: from getter */
    public String getAlias() {
        return this.alias;
    }

    @Override // com.google.firebase.firestore.pipeline.Selectable
    /* JADX INFO: renamed from: getExpr$com_google_firebase_firebase_firestore, reason: from getter */
    public Expression getExpr() {
        return this.expr;
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public Value toProto$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
        Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
        return toProto$com_google_firebase_firebase_firestore();
    }

    public final Value toProto$com_google_firebase_firebase_firestore() {
        Value valueBuild = Value.newBuilder().setFieldReferenceValue(this.fieldPath.canonicalString()).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public Function1<MutableDocument, EvaluateResult> evaluateFunction$com_google_firebase_firebase_firestore(final EvaluationContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new Function1() { // from class: com.google.firebase.firestore.pipeline.Field$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Field.evaluateFunction$lambda$1(this.f$0, context, (MutableDocument) obj);
            }
        };
    }

    static final EvaluateResult evaluateFunction$lambda$1(Field field, EvaluationContext evaluationContext, MutableDocument input) {
        EvaluateResultValue evaluateResultValue;
        Intrinsics.checkNotNullParameter(input, "input");
        FieldPath fieldPath = field.fieldPath;
        if (Intrinsics.areEqual(fieldPath, FieldPath.KEY_PATH)) {
            FirebaseFirestore firestore$com_google_firebase_firebase_firestore = evaluationContext.getPipeline().getFirestore();
            DocumentReference documentReferenceDocument = firestore$com_google_firebase_firebase_firestore != null ? firestore$com_google_firebase_firebase_firestore.document(input.getKey().getPath().canonicalString()) : null;
            Intrinsics.checkNotNull(documentReferenceDocument);
            return new EvaluateResultValue(Values.encodeValue(documentReferenceDocument));
        }
        if (Intrinsics.areEqual(fieldPath, FieldPath.CREATE_TIME_PATH)) {
            Timestamp timestamp = input.getCreateTime().getTimestamp();
            Intrinsics.checkNotNullExpressionValue(timestamp, "getTimestamp(...)");
            return new EvaluateResultValue(Values.encodeValue(timestamp));
        }
        if (Intrinsics.areEqual(fieldPath, FieldPath.UPDATE_TIME_PATH)) {
            Timestamp timestamp2 = input.getVersion().getTimestamp();
            Intrinsics.checkNotNullExpressionValue(timestamp2, "getTimestamp(...)");
            return new EvaluateResultValue(Values.encodeValue(timestamp2));
        }
        Value field2 = input.getField(field.fieldPath);
        if (field2 != null) {
            if (ServerTimestamps.isServerTimestamp(field2)) {
                evaluateResultValue = field.getServerTimestamp(field2, evaluationContext);
            } else {
                evaluateResultValue = new EvaluateResultValue(field2);
            }
            if (evaluateResultValue != null) {
                return evaluateResultValue;
            }
        }
        return EvaluateResultUnset.INSTANCE;
    }

    private final EvaluateResult getServerTimestamp(Value fieldValue, EvaluationContext context) {
        DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior;
        EventManager.ListenOptions internalOptions$com_google_firebase_firebase_firestore = context.getPipeline().getInternalOptions();
        if (internalOptions$com_google_firebase_firebase_firestore == null || (serverTimestampBehavior = internalOptions$com_google_firebase_firebase_firestore.serverTimestampBehavior) == null) {
            serverTimestampBehavior = DocumentSnapshot.ServerTimestampBehavior.NONE;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[serverTimestampBehavior.ordinal()];
        if (i == 1) {
            return EvaluateResult.INSTANCE.getNULL();
        }
        if (i != 2) {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            Value previousValue = ServerTimestamps.getPreviousValue(fieldValue);
            return previousValue == null ? EvaluateResult.INSTANCE.getNULL() : new EvaluateResultValue(previousValue);
        }
        EvaluateResult.Companion companion = EvaluateResult.INSTANCE;
        com.google.protobuf.Timestamp localWriteTime = ServerTimestamps.getLocalWriteTime(fieldValue);
        Intrinsics.checkNotNullExpressionValue(localWriteTime, "getLocalWriteTime(...)");
        return companion.timestamp(localWriteTime);
    }

    @Override // com.google.firebase.firestore.pipeline.Expression
    public String canonicalId$com_google_firebase_firebase_firestore() {
        return "fld(" + this.fieldPath.canonicalString() + ')';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Field) {
            return Intrinsics.areEqual(this.fieldPath, ((Field) other).fieldPath);
        }
        return false;
    }

    public int hashCode() {
        return this.fieldPath.hashCode();
    }
}
