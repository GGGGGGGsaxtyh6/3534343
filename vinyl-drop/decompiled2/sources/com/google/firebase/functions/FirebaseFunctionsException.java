package com.google.firebase.functions;

import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.firebase.FirebaseException;
import com.google.firebase.firestore.model.Values;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: FirebaseFunctionsException.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0002\u0011\u0012B#\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB-\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/google/firebase/functions/FirebaseFunctionsException;", "Lcom/google/firebase/FirebaseException;", "message", "", "code", "Lcom/google/firebase/functions/FirebaseFunctionsException$Code;", "details", "", "<init>", "(Ljava/lang/String;Lcom/google/firebase/functions/FirebaseFunctionsException$Code;Ljava/lang/Object;)V", "cause", "", "(Ljava/lang/String;Lcom/google/firebase/functions/FirebaseFunctionsException$Code;Ljava/lang/Object;Ljava/lang/Throwable;)V", "getCode", "()Lcom/google/firebase/functions/FirebaseFunctionsException$Code;", "getDetails", "()Ljava/lang/Object;", "Code", "Companion", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FirebaseFunctionsException extends FirebaseException {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Code code;
    private final Object details;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: FirebaseFunctionsException.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\b\u0086\u0081\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0018"}, d2 = {"Lcom/google/firebase/functions/FirebaseFunctionsException$Code;", "", Values.VECTOR_MAP_VECTORS_KEY, "", "<init>", "(Ljava/lang/String;II)V", "OK", "CANCELLED", "UNKNOWN", "INVALID_ARGUMENT", "DEADLINE_EXCEEDED", "NOT_FOUND", "ALREADY_EXISTS", "PERMISSION_DENIED", "RESOURCE_EXHAUSTED", "FAILED_PRECONDITION", "ABORTED", "OUT_OF_RANGE", "UNIMPLEMENTED", "INTERNAL", "UNAVAILABLE", "DATA_LOSS", "UNAUTHENTICATED", "Companion", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Code {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Code[] $VALUES;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        private static final SparseArray<Code> STATUS_LIST;
        private final int value;
        public static final Code OK = new Code("OK", 0, 0);
        public static final Code CANCELLED = new Code("CANCELLED", 1, 1);
        public static final Code UNKNOWN = new Code("UNKNOWN", 2, 2);
        public static final Code INVALID_ARGUMENT = new Code("INVALID_ARGUMENT", 3, 3);
        public static final Code DEADLINE_EXCEEDED = new Code("DEADLINE_EXCEEDED", 4, 4);
        public static final Code NOT_FOUND = new Code("NOT_FOUND", 5, 5);
        public static final Code ALREADY_EXISTS = new Code("ALREADY_EXISTS", 6, 6);
        public static final Code PERMISSION_DENIED = new Code("PERMISSION_DENIED", 7, 7);
        public static final Code RESOURCE_EXHAUSTED = new Code("RESOURCE_EXHAUSTED", 8, 8);
        public static final Code FAILED_PRECONDITION = new Code("FAILED_PRECONDITION", 9, 9);
        public static final Code ABORTED = new Code("ABORTED", 10, 10);
        public static final Code OUT_OF_RANGE = new Code("OUT_OF_RANGE", 11, 11);
        public static final Code UNIMPLEMENTED = new Code("UNIMPLEMENTED", 12, 12);
        public static final Code INTERNAL = new Code("INTERNAL", 13, 13);
        public static final Code UNAVAILABLE = new Code("UNAVAILABLE", 14, 14);
        public static final Code DATA_LOSS = new Code("DATA_LOSS", 15, 15);
        public static final Code UNAUTHENTICATED = new Code("UNAUTHENTICATED", 16, 16);

        private static final /* synthetic */ Code[] $values() {
            return new Code[]{OK, CANCELLED, UNKNOWN, INVALID_ARGUMENT, DEADLINE_EXCEEDED, NOT_FOUND, ALREADY_EXISTS, PERMISSION_DENIED, RESOURCE_EXHAUSTED, FAILED_PRECONDITION, ABORTED, OUT_OF_RANGE, UNIMPLEMENTED, INTERNAL, UNAVAILABLE, DATA_LOSS, UNAUTHENTICATED};
        }

        @JvmStatic
        public static final Code fromHttpStatus(int i) {
            return INSTANCE.fromHttpStatus(i);
        }

        @JvmStatic
        public static final Code fromValue(int i) {
            return INSTANCE.fromValue(i);
        }

        public static EnumEntries<Code> getEntries() {
            return $ENTRIES;
        }

        private Code(String str, int i, int i2) {
            this.value = i2;
        }

        static {
            Code[] codeArr$values = $values();
            $VALUES = codeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(codeArr$values);
            Companion companion = new Companion(null);
            INSTANCE = companion;
            STATUS_LIST = companion.buildStatusList();
        }

        /* JADX INFO: compiled from: FirebaseFunctionsException.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\nH\u0007R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/google/firebase/functions/FirebaseFunctionsException$Code$Companion;", "", "<init>", "()V", "STATUS_LIST", "Landroid/util/SparseArray;", "Lcom/google/firebase/functions/FirebaseFunctionsException$Code;", "buildStatusList", "fromValue", Values.VECTOR_MAP_VECTORS_KEY, "", "fromHttpStatus", NotificationCompat.CATEGORY_STATUS, "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final SparseArray<Code> buildStatusList() {
                SparseArray<Code> sparseArray = new SparseArray<>();
                for (Code code : Code.values()) {
                    Code code2 = sparseArray.get(code.ordinal());
                    if (code2 != null) {
                        throw new IllegalStateException(("Code value duplication between " + code2 + Typography.amp + code.name()).toString());
                    }
                    sparseArray.put(code.ordinal(), code);
                }
                return sparseArray;
            }

            @JvmStatic
            public final Code fromValue(int value) {
                Object obj = Code.STATUS_LIST.get(value, Code.UNKNOWN);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                return (Code) obj;
            }

            @JvmStatic
            public final Code fromHttpStatus(int status) {
                if (status == 200) {
                    return Code.OK;
                }
                if (status == 409) {
                    return Code.ABORTED;
                }
                if (status == 429) {
                    return Code.RESOURCE_EXHAUSTED;
                }
                if (status == 400) {
                    return Code.INVALID_ARGUMENT;
                }
                if (status == 401) {
                    return Code.UNAUTHENTICATED;
                }
                if (status == 403) {
                    return Code.PERMISSION_DENIED;
                }
                if (status == 404) {
                    return Code.NOT_FOUND;
                }
                if (status == 503) {
                    return Code.UNAVAILABLE;
                }
                if (status != 504) {
                    switch (status) {
                        case 499:
                            return Code.CANCELLED;
                        case 500:
                            return Code.INTERNAL;
                        case TypedValues.PositionType.TYPE_TRANSITION_EASING /* 501 */:
                            return Code.UNIMPLEMENTED;
                        default:
                            return Code.UNKNOWN;
                    }
                }
                return Code.DEADLINE_EXCEEDED;
            }
        }

        public static Code valueOf(String str) {
            return (Code) Enum.valueOf(Code.class, str);
        }

        public static Code[] values() {
            return (Code[]) $VALUES.clone();
        }
    }

    public final Code getCode() {
        return this.code;
    }

    public final Object getDetails() {
        return this.details;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirebaseFunctionsException(String message, Code code, Object obj) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        this.code = code;
        this.details = obj;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirebaseFunctionsException(String message, Code code, Object obj, Throwable th) {
        super(message, th);
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNull(th);
        this.code = code;
        this.details = obj;
    }

    /* JADX INFO: compiled from: FirebaseFunctionsException.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0001¢\u0006\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/google/firebase/functions/FirebaseFunctionsException$Companion;", "", "<init>", "()V", "fromResponse", "Lcom/google/firebase/functions/FirebaseFunctionsException;", "code", "Lcom/google/firebase/functions/FirebaseFunctionsException$Code;", "body", "", "serializer", "Lcom/google/firebase/functions/Serializer;", "fromResponse$com_google_firebase_firebase_functions", "com.google.firebase-firebase-functions"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FirebaseFunctionsException fromResponse$com_google_firebase_firebase_functions(Code code, String body, Serializer serializer) {
            Object objOpt;
            Intrinsics.checkNotNullParameter(code, "code");
            Intrinsics.checkNotNullParameter(serializer, "serializer");
            String strName = code.name();
            try {
                if (body == null) {
                    body = "";
                }
                JSONObject jSONObject = new JSONObject(body).getJSONObject("error");
                if (jSONObject.opt(NotificationCompat.CATEGORY_STATUS) instanceof String) {
                    String string = jSONObject.getString(NotificationCompat.CATEGORY_STATUS);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    code = Code.valueOf(string);
                    strName = code.name();
                }
                if (jSONObject.opt("message") instanceof String) {
                    String string2 = jSONObject.getString("message");
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    if (string2.length() > 0) {
                        strName = jSONObject.getString("message");
                    }
                }
                objOpt = jSONObject.opt("details");
                if (objOpt != null) {
                    try {
                        objOpt = serializer.decode(objOpt);
                    } catch (IllegalArgumentException unused) {
                        code = Code.INTERNAL;
                        strName = code.name();
                    } catch (JSONException unused2) {
                    }
                }
            } catch (IllegalArgumentException unused3) {
                objOpt = null;
            } catch (JSONException unused4) {
                objOpt = null;
            }
            if (code == Code.OK) {
                return null;
            }
            return new FirebaseFunctionsException(strName, code, objOpt);
        }
    }
}
