package com.google.firebase.firestore.pipeline.evaluation;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.evaluation.UtilsKt;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0018\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\b\u001a\u001a\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0080\bø\u0001\u0000\u001a\u0085\u0001\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0005H\u0080\bø\u0001\u0000\u001a\u008a\u0001\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0005H\u0081\bø\u0001\u0000¢\u0006\u0002\b\u0010\u001a\u008a\u0001\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0005H\u0081\bø\u0001\u0000¢\u0006\u0002\b\u0012\u001a\u008a\u0001\u0010\u0013\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0005H\u0081\bø\u0001\u0000¢\u0006\u0002\b\u0015\u001a\u008a\u0001\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0005H\u0081\bø\u0001\u0000¢\u0006\u0002\b\u0016\u001a\u008a\u0001\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u0005H\u0081\bø\u0001\u0000¢\u0006\u0002\b\u0018\u001a\u008a\u0001\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0005H\u0081\bø\u0001\u0000¢\u0006\u0002\b\u001a\u001a\u0090\u0001\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001a\b\u0004\u0010\u000e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0081\bø\u0001\u0000¢\u0006\u0002\b\u001b\u001a\u009b\u0001\u0010\u0004\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00010\u00052\u0014\b\u0004\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0005H\u0080\bø\u0001\u0000\u001a©\u0001\u0010\u001f\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r\"\u0004\b\u0000\u0010 2\u0006\u0010!\u001a\u00020\"2\u0014\b\u0004\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H 0\u00052\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u00020\u00010\u0005H\u0080\bø\u0001\u0000\u001aã\u0001\u0010\u001f\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r\"\u0004\b\u0000\u0010$\"\u0004\b\u0001\u0010%2\u0006\u0010&\u001a\u00020\"2\u0014\b\u0004\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H$0\u00052\u0014\b\u0004\u0010(\u001a\u000e\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010)\u001a\u00020\"2\u0014\b\u0004\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H%0\u00052\u0014\b\u0004\u0010+\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\u00010\u0005H\u0080\bø\u0001\u0000\u001a\u0094\u0001\u0010,\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001e\b\u0004\u0010\u000e\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0004\u0012\u00020\u00010-H\u0081\bø\u0001\u0000¢\u0006\u0002\b.\u001a\u0098\u0001\u0010,\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\"\b\u0004\u0010\u000e\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u0006\u0012\u0004\u0012\u00020\u00010-H\u0081\bø\u0001\u0000¢\u0006\u0002\b/\u001a\u0098\u0001\u0010,\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\"\b\u0004\u0010\u000e\u001a\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0004\u0012\u00020\u00010-H\u0081\bø\u0001\u0000¢\u0006\u0002\b0\u001a\u0090\u0001\u0010,\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001a\b\u0004\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u00010-H\u0081\bø\u0001\u0000¢\u0006\u0002\b2\u001a\u0090\u0001\u0010,\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001a\b\u0004\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010-H\u0081\bø\u0001\u0000¢\u0006\u0002\b3\u001a6\u00104\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0006\u0012\u0004\u0018\u0001H 0\u0005\"\u0004\b\u0000\u0010 2\u0014\b\u0004\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u0002H 0\u0005H\u0080\bø\u0001\u0000\u001a\u009c\u0001\u0010,\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2&\b\u0004\u0010\u000e\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u0006\u0012\u0004\u0012\u00020\u00010-H\u0081\bø\u0001\u0000¢\u0006\u0002\b6\u001aÓ\u0001\u00107\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r\"\u0004\b\u0000\u0010$\"\u0004\b\u0001\u0010%2\u0006\u0010&\u001a\u00020\"2\u0014\b\u0004\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H$0\u00052\u0006\u0010)\u001a\u00020\"2\u0014\b\u0004\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H%0\u00052\u001a\b\u0004\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\u00010-H\u0080\bø\u0001\u0000\u001aÙ\u0001\u00108\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r\"\u0004\b\u0000\u0010$\"\u0004\b\u0001\u0010%2\u0006\u0010&\u001a\u00020\"2\u0014\b\u0004\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H$0\u00052\u0006\u0010)\u001a\u00020\"2\u0014\b\u0004\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H%0\u00052 \b\u0004\u00109\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\u00010-0\u0003H\u0080\bø\u0001\u0000\u001a£\u0001\u0010:\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r22\b\u0004\u0010\u000e\u001a,\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\u0004\u0012\u00020\u00010;H\u0080\bø\u0001\u0000\u001a\u0091\u0001\u0010<\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2 \b\u0004\u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010;H\u0080\bø\u0001\u0000\u001a\u0097\u0001\u0010=\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2&\b\u0004\u0010\u000e\u001a \u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0004\u0012\u00020\u00010;H\u0080\bø\u0001\u0000\u001a\u008b\u0001\u0010>\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001a\b\u0004\u0010\u000e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0080\bø\u0001\u0000\u001a\u008b\u0001\u0010?\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001a\b\u0004\u0010\u000e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0081\bø\u0001\u0000\u001a\u0090\u0001\u0010@\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001a\b\u0004\u0010\u000e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0081\bø\u0001\u0000¢\u0006\u0002\bA\u001a¯\u0001\u0010B\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r\"\u0004\b\u0000\u0010 2\u0006\u0010!\u001a\u00020\"2\u0014\b\u0004\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H 0\u00052\u001a\b\u0004\u0010\u000e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H 0\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0080\bø\u0001\u0000\u001a\u008a\u0001\u0010@\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020\u00010\u0005H\u0081\bø\u0001\u0000¢\u0006\u0002\bD\u001a\u008f\u0001\u0010E\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001e\b\u0004\u0010\u0002\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0004\u0012\u00020\u00110-H\u0080\bø\u0001\u0000\u001a\u009b\u0001\u0010F\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u00052\u0014\b\u0004\u0010H\u001a\u000e\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I0\u0005H\u0080\bø\u0001\u0000\u001a§\u0001\u0010F\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001a\b\u0004\u0010G\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170-2\u001a\b\u0004\u0010H\u001a\u0014\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I0-H\u0080\bø\u0001\u0000\u001a\u0085\u0001\u0010J\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020\u00010\u0005H\u0080\bø\u0001\u0000\u001a\u009b\u0001\u0010J\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u0014\b\u0004\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u00052\u0014\b\u0004\u0010H\u001a\u000e\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020\u00010\u0005H\u0080\bø\u0001\u0000\u001a¬\u0001\u0010J\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001a\b\u0004\u0010G\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010-2\u001a\b\u0004\u0010H\u001a\u0014\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010-H\u0081\bø\u0001\u0000¢\u0006\u0002\bK\u001a§\u0001\u0010J\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001a\b\u0004\u0010G\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010-2\u001a\b\u0004\u0010H\u001a\u0014\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020\u00010-H\u0080\bø\u0001\u0000\u001a\u008b\u0001\u0010J\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\r2\u001a\b\u0004\u0010H\u001a\u0014\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020\u00010-H\u0080\bø\u0001\u0000\"w\u0010N\u001ae\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005j\u0002`\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bO\u0010P*B\b\u0000\u0010L\"\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u00052\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005*\u008c\u0001\b\u0000\u0010M\"#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002`\u000b0\u00052a\u00128\u00126\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012#\u0012!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0005j\u0002`\u000b0\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006Q"}, d2 = {"catch", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "f", "Lkotlin/Function0;", "unaryFunction", "Lkotlin/Function1;", "", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lkotlin/ParameterName;", "name", "input", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateDocument;", "params", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateFunction;", "function", "Lcom/google/firestore/v1/Value;", "unaryValueFunction", "", "unaryBooleanFunction", "unaryFunctionPrimitive", "", "unaryStringFunctionPrimitive", "unaryStringFunction", "", "unaryLongFunction", "Lcom/google/protobuf/Timestamp;", "unaryTimestampFunction", "unaryArrayFunction", "byteOp", "Lcom/google/protobuf/ByteString;", "stringOp", "unaryFunctionType", ExifInterface.GPS_DIRECTION_TRUE, "valueTypeCase", "Lcom/google/firestore/v1/Value$ValueTypeCase;", "valueExtractor", "T1", "T2", "valueTypeCase1", "valueExtractor1", "function1", "valueTypeCase2", "valueExtractor2", "function2", "binaryFunction", "Lkotlin/Function2;", "binaryValueValueFunction", "binaryValueArrayFunction", "binaryArrayValueFunction", "", "binaryVectorVectorFunction", "binaryStringStringFunction", "cache", "ifAbsent", "binaryArrayArrayFunction", "binaryFunctionType", "binaryFunctionConstructorType", "functionConstructor", "ternaryLazyFunction", "Lkotlin/Function3;", "ternaryTimestampFunction", "ternaryNullableValueFunction", "variadicResultFunction", "variadicNullableValueFunction", "variadicFunction", "variadicStringFunction", "variadicFunctionType", "", "variadicBooleanFunction", "comparison", "arithmeticPrimitive", "intOp", "doubleOp", "", "arithmetic", "arithmeticNumberLong", "EvaluateDocument", "EvaluateFunction", "notImplemented", "getNotImplemented", "()Lkotlin/jvm/functions/Function1;", "com.google.firebase-firebase-firestore"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class UtilsKt {
    private static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> notImplemented = new Function1() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$notImplemented$1
        @Override // kotlin.jvm.functions.Function1
        public final Void invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> list) {
            Intrinsics.checkNotNullParameter(list, "<unused var>");
            throw new NotImplementedError(null, 1, null);
        }
    };

    /* JADX INFO: compiled from: Utils.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Value.ValueTypeCase.values().length];
            try {
                iArr[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Value.ValueTypeCase.ARRAY_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Value.ValueTypeCase.TIMESTAMP_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Value.ValueTypeCase.STRING_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Value.ValueTypeCase.INTEGER_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Value.ValueTypeCase.DOUBLE_VALUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: catch, reason: not valid java name */
    public static final EvaluateResult m719catch(Function0<? extends EvaluateResult> f) {
        Intrinsics.checkNotNullParameter(f, "f");
        try {
            return f.invoke();
        } catch (Exception unused) {
            return EvaluateResultError.INSTANCE;
        }
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> unaryFunction(final Function1<? super EvaluateResult, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.unaryFunction.1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<EvaluateResult, EvaluateResult> function12 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.unaryFunction.1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            return function12.invoke(function1.invoke(input));
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> unaryBooleanFunction(final Function1<? super Boolean, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.BOOLEAN_VALUE;
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunctionType$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase2 = valueTypeCase;
                final Function1 function12 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunctionType$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase3 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase3 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase3 == valueTypeCase2) {
                                    try {
                                        return (EvaluateResult) function12.invoke(Boolean.valueOf(value.getBooleanValue()));
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> unaryStringFunction(final Function1<? super String, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.STRING_VALUE;
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunctionType$3
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase2 = valueTypeCase;
                final Function1 function12 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunctionType$3.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase3 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase3 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase3 == valueTypeCase2) {
                                    try {
                                        return (EvaluateResult) function12.invoke(value.getStringValue());
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> unaryLongFunction(final Function1<? super Long, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.INTEGER_VALUE;
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunctionType$5
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase2 = valueTypeCase;
                final Function1 function12 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunctionType$5.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase3 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase3 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase3 == valueTypeCase2) {
                                    try {
                                        return (EvaluateResult) function12.invoke(Long.valueOf(value.getIntegerValue()));
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> unaryTimestampFunction(final Function1<? super Timestamp, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.TIMESTAMP_VALUE;
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunctionType$4
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase2 = valueTypeCase;
                final Function1 function12 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunctionType$4.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase3 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase3 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase3 == valueTypeCase2) {
                                    try {
                                        return (EvaluateResult) function12.invoke(value.getTimestampValue());
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> unaryFunction(final Function1<? super ByteString, ? extends EvaluateResult> byteOp, final Function1<? super String, ? extends EvaluateResult> stringOp) {
        Intrinsics.checkNotNullParameter(byteOp, "byteOp");
        Intrinsics.checkNotNullParameter(stringOp, "stringOp");
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.BYTES_VALUE;
        final Value.ValueTypeCase valueTypeCase2 = Value.ValueTypeCase.STRING_VALUE;
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunctionType$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase3 = valueTypeCase;
                final Value.ValueTypeCase valueTypeCase4 = valueTypeCase2;
                final Function1 function12 = byteOp;
                final Function1 function13 = stringOp;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunctionType$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase5 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase5 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase5 == valueTypeCase3) {
                            try {
                                return (EvaluateResult) function12.invoke(value.getBytesValue());
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        if (valueTypeCase5 == valueTypeCase4) {
                            try {
                                return (EvaluateResult) function13.invoke(value.getStringValue());
                            } catch (Exception unused2) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        return EvaluateResultError.INSTANCE;
                    }
                };
            }
        };
    }

    public static final <T1, T2> Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> unaryFunctionType(final Value.ValueTypeCase valueTypeCase1, final Function1<? super Value, ? extends T1> valueExtractor1, final Function1<? super T1, ? extends EvaluateResult> function1, final Value.ValueTypeCase valueTypeCase2, final Function1<? super Value, ? extends T2> valueExtractor2, final Function1<? super T2, ? extends EvaluateResult> function2) {
        Intrinsics.checkNotNullParameter(valueTypeCase1, "valueTypeCase1");
        Intrinsics.checkNotNullParameter(valueExtractor1, "valueExtractor1");
        Intrinsics.checkNotNullParameter(function1, "function1");
        Intrinsics.checkNotNullParameter(valueTypeCase2, "valueTypeCase2");
        Intrinsics.checkNotNullParameter(valueExtractor2, "valueExtractor2");
        Intrinsics.checkNotNullParameter(function2, "function2");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.unaryFunctionType.2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(0);
                final Value.ValueTypeCase valueTypeCase = valueTypeCase1;
                final Value.ValueTypeCase valueTypeCase3 = valueTypeCase2;
                final Function1<T1, EvaluateResult> function13 = function1;
                final Function1<Value, T1> function14 = valueExtractor1;
                final Function1<T2, EvaluateResult> function15 = function2;
                final Function1<Value, T2> function16 = valueExtractor2;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.unaryFunctionType.2.1

                    /* JADX INFO: renamed from: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunctionType$2$1$WhenMappings */
                    /* JADX INFO: compiled from: Utils.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[Value.ValueTypeCase.values().length];
                            try {
                                iArr[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
                            } catch (NoSuchFieldError unused) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResultInvoke = function12.invoke(input);
                        if (evaluateResultInvoke.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResultInvoke.getValue();
                        Value.ValueTypeCase valueTypeCase4 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase4 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase4.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase4 != valueTypeCase) {
                            if (valueTypeCase4 != valueTypeCase3) {
                                return EvaluateResultError.INSTANCE;
                            }
                            try {
                                return function15.invoke((T2) function16.invoke(value));
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            return function13.invoke((T1) function14.invoke(value));
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> binaryValueValueFunction(final Function2<? super Value, ? super Value, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function2<Value, Value, EvaluateResult> function2 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResultInvoke = function1.invoke(input);
                        if (evaluateResultInvoke.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResultInvoke2 = function12.invoke(input);
                        if (evaluateResultInvoke2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            return function2.invoke(evaluateResultInvoke.getValue(), evaluateResultInvoke2.getValue());
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> binaryStringStringFunction(final Function2<? super String, ? super String, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.STRING_VALUE;
        final Value.ValueTypeCase valueTypeCase2 = Value.ValueTypeCase.STRING_VALUE;
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$$inlined$binaryFunctionType$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(final List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Value.ValueTypeCase valueTypeCase3 = valueTypeCase;
                final Value.ValueTypeCase valueTypeCase4 = valueTypeCase2;
                final Function2 function2 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$$inlined$binaryFunctionType$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) ((Function1) params.get(0)).invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        EvaluateResult evaluateResult2 = (EvaluateResult) ((Function1) params.get(1)).invoke(input);
                        if (evaluateResult2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value2 = evaluateResult2.getValue();
                        Value.ValueTypeCase valueTypeCase5 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase5 == null ? -1 : UtilsKt.C00901.C00351.WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase5 != valueTypeCase3) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value.ValueTypeCase valueTypeCase6 = value2 != null ? value2.getValueTypeCase() : null;
                        int i2 = valueTypeCase6 == null ? -1 : UtilsKt.C00901.C00351.WhenMappings.$EnumSwitchMapping$0[valueTypeCase6.ordinal()];
                        if (i2 == -1 || i2 == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase6 != valueTypeCase4) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            return (EvaluateResult) function2.invoke(value.getStringValue(), value2.getStringValue());
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final <T> Function1<String, T> cache(final Function1<? super String, ? extends T> ifAbsent) {
        Intrinsics.checkNotNullParameter(ifAbsent, "ifAbsent");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) new Pair(null, null);
        return new Function1<String, T>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.cache.1
            @Override // kotlin.jvm.functions.Function1
            public final T invoke(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                Pair<String, T> pair = objectRef.element;
                String strComponent1 = pair.component1();
                T tComponent2 = pair.component2();
                if (Intrinsics.areEqual(strComponent1, s)) {
                    return tComponent2;
                }
                T tInvoke = ifAbsent.invoke(s);
                objectRef.element = (T) new Pair(s, tInvoke);
                return tInvoke;
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> binaryArrayArrayFunction(final Function2<? super List<Value>, ? super List<Value>, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$7
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(final List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function2<List<Value>, List<Value>, EvaluateResult> function2 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$7.1

                    /* JADX INFO: renamed from: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$7$1$WhenMappings */
                    /* JADX INFO: compiled from: Utils.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[Value.ValueTypeCase.values().length];
                            try {
                                iArr[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
                            } catch (NoSuchFieldError unused) {
                            }
                            try {
                                iArr[Value.ValueTypeCase.ARRAY_VALUE.ordinal()] = 2;
                            } catch (NoSuchFieldError unused2) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        List<Value> valuesList;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResultInvoke = params.get(0).invoke(input);
                        if (evaluateResultInvoke.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResultInvoke.getValue();
                        EvaluateResult evaluateResultInvoke2 = params.get(1).invoke(input);
                        if (evaluateResultInvoke2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value2 = evaluateResultInvoke2.getValue();
                        List<Value> valuesList2 = null;
                        Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                        if (i == -1 || i == 1) {
                            valuesList = null;
                        } else if (i == 2) {
                            valuesList = value.getArrayValue().getValuesList();
                        } else {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value.ValueTypeCase valueTypeCase2 = value2 != null ? value2.getValueTypeCase() : null;
                        int i2 = valueTypeCase2 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase2.ordinal()];
                        if (i2 != -1 && i2 != 1) {
                            if (i2 == 2) {
                                valuesList2 = value2.getArrayValue().getValuesList();
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        return (valuesList == null || valuesList2 == null) ? EvaluateResult.INSTANCE.getNULL() : function2.invoke(valuesList, valuesList2);
                    }
                };
            }
        };
    }

    public static final <T1, T2> Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> binaryFunctionType(final Value.ValueTypeCase valueTypeCase1, final Function1<? super Value, ? extends T1> valueExtractor1, final Value.ValueTypeCase valueTypeCase2, final Function1<? super Value, ? extends T2> valueExtractor2, final Function2<? super T1, ? super T2, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(valueTypeCase1, "valueTypeCase1");
        Intrinsics.checkNotNullParameter(valueExtractor1, "valueExtractor1");
        Intrinsics.checkNotNullParameter(valueTypeCase2, "valueTypeCase2");
        Intrinsics.checkNotNullParameter(valueExtractor2, "valueExtractor2");
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.binaryFunctionType.1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(final List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Value.ValueTypeCase valueTypeCase = valueTypeCase1;
                final Value.ValueTypeCase valueTypeCase3 = valueTypeCase2;
                final Function2<T1, T2, EvaluateResult> function2 = function;
                final Function1<Value, T1> function1 = valueExtractor1;
                final Function1<Value, T2> function12 = valueExtractor2;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.binaryFunctionType.1.1

                    /* JADX INFO: renamed from: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunctionType$1$1$WhenMappings */
                    /* JADX INFO: compiled from: Utils.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[Value.ValueTypeCase.values().length];
                            try {
                                iArr[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
                            } catch (NoSuchFieldError unused) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResultInvoke = params.get(0).invoke(input);
                        if (evaluateResultInvoke.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResultInvoke.getValue();
                        EvaluateResult evaluateResultInvoke2 = params.get(1).invoke(input);
                        if (evaluateResultInvoke2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value2 = evaluateResultInvoke2.getValue();
                        Value.ValueTypeCase valueTypeCase4 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase4 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase4.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase4 != valueTypeCase) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value.ValueTypeCase valueTypeCase5 = value2 != null ? value2.getValueTypeCase() : null;
                        int i2 = valueTypeCase5 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                        if (i2 == -1 || i2 == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase5 != valueTypeCase3) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            return function2.invoke((T1) function1.invoke(value), (T2) function12.invoke(value2));
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final <T1, T2> Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> binaryFunctionConstructorType(final Value.ValueTypeCase valueTypeCase1, final Function1<? super Value, ? extends T1> valueExtractor1, final Value.ValueTypeCase valueTypeCase2, final Function1<? super Value, ? extends T2> valueExtractor2, final Function0<? extends Function2<? super T1, ? super T2, ? extends EvaluateResult>> functionConstructor) {
        Intrinsics.checkNotNullParameter(valueTypeCase1, "valueTypeCase1");
        Intrinsics.checkNotNullParameter(valueExtractor1, "valueExtractor1");
        Intrinsics.checkNotNullParameter(valueTypeCase2, "valueTypeCase2");
        Intrinsics.checkNotNullParameter(valueExtractor2, "valueExtractor2");
        Intrinsics.checkNotNullParameter(functionConstructor, "functionConstructor");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.binaryFunctionConstructorType.1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function2 function2 = (Function2) functionConstructor.invoke();
                final Value.ValueTypeCase valueTypeCase = valueTypeCase1;
                final Value.ValueTypeCase valueTypeCase3 = valueTypeCase2;
                final Function1<Value, T1> function13 = valueExtractor1;
                final Function1<Value, T2> function14 = valueExtractor2;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.binaryFunctionConstructorType.1.1

                    /* JADX INFO: renamed from: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunctionConstructorType$1$1$WhenMappings */
                    /* JADX INFO: compiled from: Utils.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[Value.ValueTypeCase.values().length];
                            try {
                                iArr[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
                            } catch (NoSuchFieldError unused) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Value value;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResultInvoke = function1.invoke(input);
                        if (evaluateResultInvoke.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResultInvoke2 = function12.invoke(input);
                        if (evaluateResultInvoke2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value2 = evaluateResultInvoke.getValue();
                        Value value3 = null;
                        Value.ValueTypeCase valueTypeCase4 = value2 != null ? value2.getValueTypeCase() : null;
                        int i = valueTypeCase4 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase4.ordinal()];
                        if (i == -1 || i == 1) {
                            value = null;
                        } else {
                            if (valueTypeCase4 != valueTypeCase) {
                                return EvaluateResultError.INSTANCE;
                            }
                            value = evaluateResultInvoke.getValue();
                        }
                        Value value4 = evaluateResultInvoke2.getValue();
                        Value.ValueTypeCase valueTypeCase5 = value4 != null ? value4.getValueTypeCase() : null;
                        int i2 = valueTypeCase5 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                        if (i2 != -1 && i2 != 1) {
                            if (valueTypeCase5 != valueTypeCase3) {
                                return EvaluateResultError.INSTANCE;
                            }
                            value3 = evaluateResultInvoke2.getValue();
                        }
                        if (value == null || value3 == null) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        return function2.invoke((T1) function13.invoke(value), (T2) function14.invoke(value3));
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> ternaryLazyFunction(final Function3<? super Function0<? extends EvaluateResult>, ? super Function0<? extends EvaluateResult>, ? super Function0<? extends EvaluateResult>, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.ternaryLazyFunction.1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 3) {
                    throw Assert.fail("Function should have exactly 3 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function13 = params.get(2);
                final Function3<Function0<? extends EvaluateResult>, Function0<? extends EvaluateResult>, Function0<? extends EvaluateResult>, EvaluateResult> function3 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.ternaryLazyFunction.1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(final MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        Function3<Function0<? extends EvaluateResult>, Function0<? extends EvaluateResult>, Function0<? extends EvaluateResult>, EvaluateResult> function32 = function3;
                        final Function1<MutableDocument, EvaluateResult> function14 = function1;
                        final Function1<MutableDocument, EvaluateResult> function15 = function12;
                        final Function1<MutableDocument, EvaluateResult> function16 = function13;
                        try {
                            return function32.invoke(new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryLazyFunction$1$1$1$1
                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final EvaluateResult invoke() {
                                    return function14.invoke(input);
                                }
                            }, new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryLazyFunction$1$1$1$2
                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final EvaluateResult invoke() {
                                    return function15.invoke(input);
                                }
                            }, new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryLazyFunction$1$1$1$3
                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final EvaluateResult invoke() {
                                    return function16.invoke(input);
                                }
                            });
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> variadicResultFunction(final Function1<? super List<? extends EvaluateResult>, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.variadicResultFunction.1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(final List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                final Function1<List<? extends EvaluateResult>, EvaluateResult> function1 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.variadicResultFunction.1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        List<Function1<MutableDocument, EvaluateResult>> list = params;
                        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                        Iterator<T> it = list.iterator();
                        while (it.hasNext()) {
                            arrayList.add((EvaluateResult) ((Function1) it.next()).invoke(input));
                        }
                        try {
                            return function1.invoke(arrayList);
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> variadicStringFunction(final Function1<? super List<String>, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.STRING_VALUE;
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$variadicFunction$$inlined$variadicFunctionType$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(final List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                final Value.ValueTypeCase valueTypeCase2 = valueTypeCase;
                final Function1 function1 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$variadicFunction$$inlined$variadicFunctionType$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        ArrayList arrayList = new ArrayList(params.size());
                        Iterator it = params.iterator();
                        boolean z = false;
                        while (it.hasNext()) {
                            EvaluateResult evaluateResult = (EvaluateResult) ((Function1) it.next()).invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase3 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase3 == null ? -1 : UtilsKt.C00941.C00401.WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()];
                            if (i == -1 || i == 1) {
                                z = true;
                            } else if (valueTypeCase3 == valueTypeCase2) {
                                Value value2 = evaluateResult.getValue();
                                Intrinsics.checkNotNull(value2);
                                arrayList.add(value2.getStringValue());
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        if (z) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        try {
                            return (EvaluateResult) function1.invoke(arrayList);
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final <T> Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> variadicFunctionType(final Value.ValueTypeCase valueTypeCase, final Function1<? super Value, ? extends T> valueExtractor, final Function1<? super List<? extends T>, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(valueTypeCase, "valueTypeCase");
        Intrinsics.checkNotNullParameter(valueExtractor, "valueExtractor");
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.variadicFunctionType.1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(final List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                final Value.ValueTypeCase valueTypeCase2 = valueTypeCase;
                final Function1<Value, T> function1 = valueExtractor;
                final Function1<List<? extends T>, EvaluateResult> function12 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt.variadicFunctionType.1.1

                    /* JADX INFO: renamed from: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$variadicFunctionType$1$1$WhenMappings */
                    /* JADX INFO: compiled from: Utils.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[Value.ValueTypeCase.values().length];
                            try {
                                iArr[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
                            } catch (NoSuchFieldError unused) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        ArrayList arrayList = new ArrayList(params.size());
                        Iterator<Function1<MutableDocument, EvaluateResult>> it = params.iterator();
                        boolean z = false;
                        while (it.hasNext()) {
                            EvaluateResult evaluateResultInvoke = it.next().invoke(input);
                            if (evaluateResultInvoke.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResultInvoke.getValue();
                            Value.ValueTypeCase valueTypeCase3 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase3 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()];
                            if (i == -1 || i == 1) {
                                z = true;
                            } else {
                                if (valueTypeCase3 != valueTypeCase2) {
                                    return EvaluateResultError.INSTANCE;
                                }
                                Function1<Value, T> function13 = function1;
                                Value value2 = evaluateResultInvoke.getValue();
                                Intrinsics.checkNotNull(value2);
                                arrayList.add(function13.invoke(value2));
                            }
                        }
                        if (z) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        try {
                            return function12.invoke(arrayList);
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> variadicBooleanFunction(final Function1<? super boolean[], ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$variadicFunction$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(final List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                final Function1<boolean[], EvaluateResult> function1 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$variadicFunction$2.1

                    /* JADX INFO: renamed from: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$variadicFunction$2$1$WhenMappings */
                    /* JADX INFO: compiled from: Utils.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[Value.ValueTypeCase.values().length];
                            try {
                                iArr[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
                            } catch (NoSuchFieldError unused) {
                            }
                            try {
                                iArr[Value.ValueTypeCase.BOOLEAN_VALUE.ordinal()] = 2;
                            } catch (NoSuchFieldError unused2) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        boolean[] zArr = new boolean[params.size()];
                        boolean z = false;
                        int i = 0;
                        for (Object obj : params) {
                            int i2 = i + 1;
                            if (i < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            EvaluateResult evaluateResult = (EvaluateResult) ((Function1) obj).invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                            int i3 = valueTypeCase == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i3 == -1 || i3 == 1) {
                                z = true;
                            } else if (i3 == 2) {
                                zArr[i] = value.getBooleanValue();
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                            i = i2;
                        }
                        if (z) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        try {
                            return function1.invoke(zArr);
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> arithmetic(final Function1<? super Long, ? extends EvaluateResult> intOp, final Function1<? super Double, ? extends EvaluateResult> doubleOp) {
        Intrinsics.checkNotNullParameter(intOp, "intOp");
        Intrinsics.checkNotNullParameter(doubleOp, "doubleOp");
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase2 = Value.ValueTypeCase.DOUBLE_VALUE;
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmetic$$inlined$unaryFunctionType$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase3 = valueTypeCase;
                final Value.ValueTypeCase valueTypeCase4 = valueTypeCase2;
                final Function1 function12 = intOp;
                final Function1 function13 = doubleOp;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmetic$$inlined$unaryFunctionType$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase5 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase5 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase5 == valueTypeCase3) {
                            try {
                                return (EvaluateResult) function12.invoke(Long.valueOf(value.getIntegerValue()));
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        if (valueTypeCase5 == valueTypeCase4) {
                            try {
                                return (EvaluateResult) function13.invoke(Double.valueOf(value.getDoubleValue()));
                            } catch (Exception unused2) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        return EvaluateResultError.INSTANCE;
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> getNotImplemented() {
        return notImplemented;
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> unaryValueFunction(final Function1<? super Value, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunction$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1 function12 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunction$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i == -1 || i == 1) {
                                return EvaluateResult.INSTANCE.getNULL();
                            }
                            Function1 function13 = function12;
                            Value value2 = evaluateResult.getValue();
                            Intrinsics.checkNotNull(value2);
                            return (EvaluateResult) function13.invoke(value2);
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> unaryStringFunctionPrimitive(final Function1<? super String, String> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.STRING_VALUE;
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunctionPrimitive$$inlined$unaryStringFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase2 = valueTypeCase;
                final Function1 function12 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunctionPrimitive$$inlined$unaryStringFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase3 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase3 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase3 == valueTypeCase2) {
                                    try {
                                        return EvaluateResult.INSTANCE.string((String) function12.invoke(value.getStringValue()));
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> unaryArrayFunction(final Function1<? super List<Value>, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1 function12 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunction$$inlined$unaryFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            Value value = ((EvaluateResult) function1.invoke(input)).getValue();
                            if (value == null) {
                                return EvaluateResult.INSTANCE.getNULL();
                            }
                            Value.ValueTypeCase valueTypeCase = value.getValueTypeCase();
                            int i = valueTypeCase == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i == 1) {
                                return EvaluateResult.INSTANCE.getNULL();
                            }
                            if (i == 2) {
                                Function1 function13 = function12;
                                List<Value> valuesList = value.getArrayValue().getValuesList();
                                Intrinsics.checkNotNullExpressionValue(valuesList, "getValuesList(...)");
                                return (EvaluateResult) function13.invoke(valuesList);
                            }
                            return EvaluateResultError.INSTANCE;
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final <T> Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> unaryFunctionType(final Value.ValueTypeCase valueTypeCase, final Function1<? super Value, ? extends T> valueExtractor, final Function1<? super T, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(valueTypeCase, "valueTypeCase");
        Intrinsics.checkNotNullParameter(valueExtractor, "valueExtractor");
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunctionType$$inlined$unaryFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase2 = valueTypeCase;
                final Function1 function12 = function;
                final Function1 function13 = valueExtractor;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$unaryFunctionType$$inlined$unaryFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        try {
                            EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                            if (evaluateResult.getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = evaluateResult.getValue();
                            Value.ValueTypeCase valueTypeCase3 = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase3 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()];
                            if (i != -1 && i != 1) {
                                if (valueTypeCase3 == valueTypeCase2) {
                                    try {
                                        return (EvaluateResult) function12.invoke(function13.invoke(value));
                                    } catch (Exception unused) {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> binaryValueArrayFunction(final Function2<? super Value, ? super List<Value>, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$$inlined$binaryValueValueFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function2 function2 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$$inlined$binaryValueValueFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            Value.ValueTypeCase valueTypeCase = value2 != null ? value2.getValueTypeCase() : null;
                            int i = valueTypeCase == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i == -1 || i == 1) {
                                return EvaluateResult.INSTANCE.getNULL();
                            }
                            if (i == 2) {
                                Function2 function22 = function2;
                                List<Value> valuesList = value2.getArrayValue().getValuesList();
                                Intrinsics.checkNotNullExpressionValue(valuesList, "getValuesList(...)");
                                return (EvaluateResult) function22.invoke(value, valuesList);
                            }
                            return EvaluateResultError.INSTANCE;
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> binaryArrayValueFunction(final Function2<? super List<Value>, ? super Value, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$$inlined$binaryValueValueFunction$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function2 function2 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$$inlined$binaryValueValueFunction$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i == -1 || i == 1) {
                                return EvaluateResult.INSTANCE.getNULL();
                            }
                            if (i == 2) {
                                Function2 function22 = function2;
                                List<Value> valuesList = value.getArrayValue().getValuesList();
                                Intrinsics.checkNotNullExpressionValue(valuesList, "getValuesList(...)");
                                return (EvaluateResult) function22.invoke(valuesList, value2);
                            }
                            return EvaluateResultError.INSTANCE;
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> binaryVectorVectorFunction(final Function2<? super double[], ? super double[], ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$$inlined$binaryValueValueFunction$3
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function2 function2 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$binaryFunction$$inlined$binaryValueValueFunction$3.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        double[] vectorValue;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            double[] vectorValue2 = null;
                            if (value == null || Values.isNullValue(value)) {
                                vectorValue = null;
                            } else {
                                if (!Values.isVectorValue(value)) {
                                    return EvaluateResultError.INSTANCE;
                                }
                                vectorValue = Values.getVectorValue(value);
                            }
                            if (value2 != null && !Values.isNullValue(value2)) {
                                if (!Values.isVectorValue(value2)) {
                                    return EvaluateResultError.INSTANCE;
                                }
                                vectorValue2 = Values.getVectorValue(value2);
                            }
                            if (vectorValue == null || vectorValue2 == null) {
                                return EvaluateResult.INSTANCE.getNULL();
                            }
                            try {
                                return (EvaluateResult) function2.invoke(vectorValue, vectorValue2);
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> ternaryTimestampFunction(final Function3<? super Timestamp, ? super String, ? super Long, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryTimestampFunction$$inlined$ternaryNullableValueFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 3) {
                    throw Assert.fail("Function should have exactly 3 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function13 = params.get(2);
                final Function3 function3 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryTimestampFunction$$inlined$ternaryNullableValueFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(final MutableDocument input) {
                        Timestamp timestampValue;
                        Intrinsics.checkNotNullParameter(input, "input");
                        final Function1 function14 = function1;
                        final Function1 function15 = function12;
                        final Function1 function16 = function13;
                        try {
                            Function0<EvaluateResult> function0 = new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryTimestampFunction$.inlined.ternaryNullableValueFunction.1.1.1
                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final EvaluateResult invoke() {
                                    return (EvaluateResult) function14.invoke(input);
                                }
                            };
                            Function0<EvaluateResult> function02 = new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryTimestampFunction$.inlined.ternaryNullableValueFunction.1.1.2
                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final EvaluateResult invoke() {
                                    return (EvaluateResult) function15.invoke(input);
                                }
                            };
                            Function0<EvaluateResult> function03 = new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryTimestampFunction$.inlined.ternaryNullableValueFunction.1.1.3
                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final EvaluateResult invoke() {
                                    return (EvaluateResult) function16.invoke(input);
                                }
                            };
                            Function0<EvaluateResult> function04 = function02;
                            Function0<EvaluateResult> function05 = function0;
                            if (function05.invoke().getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = function05.invoke().getValue();
                            if (function04.invoke().getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value2 = function04.invoke().getValue();
                            if (function03.invoke().getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value3 = function03.invoke().getValue();
                            Long lValueOf = null;
                            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i == -1 || i == 1) {
                                timestampValue = null;
                            } else if (i == 3) {
                                timestampValue = value.getTimestampValue();
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value.ValueTypeCase valueTypeCase2 = value2 != null ? value2.getValueTypeCase() : null;
                            if ((valueTypeCase2 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase2.ordinal()]) == 4) {
                                String stringValue = value2.getStringValue();
                                Intrinsics.checkNotNullExpressionValue(stringValue, "getStringValue(...)");
                                Value.ValueTypeCase valueTypeCase3 = value3 != null ? value3.getValueTypeCase() : null;
                                int i2 = valueTypeCase3 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()];
                                if (i2 != -1 && i2 != 1) {
                                    if (i2 == 5) {
                                        lValueOf = Long.valueOf(value3.getIntegerValue());
                                    } else {
                                        return EvaluateResultError.INSTANCE;
                                    }
                                }
                                if (timestampValue != null && lValueOf != null) {
                                    return (EvaluateResult) function3.invoke(timestampValue, stringValue, lValueOf);
                                }
                                return EvaluateResult.INSTANCE.getNULL();
                            }
                            return EvaluateResultError.INSTANCE;
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> ternaryNullableValueFunction(final Function3<? super Value, ? super Value, ? super Value, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryNullableValueFunction$$inlined$ternaryLazyFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 3) {
                    throw Assert.fail("Function should have exactly 3 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function13 = params.get(2);
                final Function3 function3 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryNullableValueFunction$$inlined$ternaryLazyFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(final MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        final Function1 function14 = function1;
                        final Function1 function15 = function12;
                        final Function1 function16 = function13;
                        try {
                            Function0<EvaluateResult> function0 = new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryNullableValueFunction$.inlined.ternaryLazyFunction.1.1.1
                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final EvaluateResult invoke() {
                                    return (EvaluateResult) function14.invoke(input);
                                }
                            };
                            Function0<EvaluateResult> function02 = new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryNullableValueFunction$.inlined.ternaryLazyFunction.1.1.2
                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final EvaluateResult invoke() {
                                    return (EvaluateResult) function15.invoke(input);
                                }
                            };
                            Function0<EvaluateResult> function03 = new Function0<EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$ternaryNullableValueFunction$.inlined.ternaryLazyFunction.1.1.3
                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final EvaluateResult invoke() {
                                    return (EvaluateResult) function16.invoke(input);
                                }
                            };
                            Function0<EvaluateResult> function04 = function02;
                            Function0<EvaluateResult> function05 = function0;
                            if (function05.invoke().getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value = function05.invoke().getValue();
                            if (function04.invoke().getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value value2 = function04.invoke().getValue();
                            if (function03.invoke().getIsError()) {
                                return EvaluateResultError.INSTANCE;
                            }
                            return (EvaluateResult) function3.invoke(value, value2, function03.invoke().getValue());
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> variadicNullableValueFunction(final Function1<? super List<Value>, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$variadicNullableValueFunction$$inlined$variadicResultFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(final List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                final Function1 function1 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$variadicNullableValueFunction$$inlined$variadicResultFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Object objInvoke;
                        Intrinsics.checkNotNullParameter(input, "input");
                        List list = params;
                        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            arrayList.add((EvaluateResult) ((Function1) it.next()).invoke(input));
                        }
                        ArrayList arrayList2 = arrayList;
                        try {
                            Function1 function12 = function1;
                            ArrayList arrayList3 = arrayList2;
                            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
                            Iterator it2 = arrayList3.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    Value value = ((EvaluateResult) it2.next()).getValue();
                                    if (value == null) {
                                        objInvoke = EvaluateResultError.INSTANCE;
                                        break;
                                    }
                                    arrayList4.add(value);
                                } else {
                                    objInvoke = function12.invoke(arrayList4);
                                    break;
                                }
                            }
                            return (EvaluateResult) objInvoke;
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> comparison(final Function2<? super Value, ? super Value, Boolean> f) {
        Intrinsics.checkNotNullParameter(f, "f");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$comparison$$inlined$binaryValueValueFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function2 function2 = f;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$comparison$$inlined$binaryValueValueFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            return EvaluateResult.INSTANCE.m715boolean(((Boolean) function2.invoke(evaluateResult.getValue(), evaluateResult2.getValue())).booleanValue());
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> arithmeticPrimitive(final Function1<? super Long, Long> intOp, final Function1<? super Double, Double> doubleOp) {
        Intrinsics.checkNotNullParameter(intOp, "intOp");
        Intrinsics.checkNotNullParameter(doubleOp, "doubleOp");
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase2 = Value.ValueTypeCase.DOUBLE_VALUE;
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmeticPrimitive$$inlined$arithmetic$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase3 = valueTypeCase;
                final Value.ValueTypeCase valueTypeCase4 = valueTypeCase2;
                final Function1 function12 = intOp;
                final Function1 function13 = doubleOp;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmeticPrimitive$$inlined$arithmetic$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase5 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase5 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase5 != valueTypeCase3) {
                            if (valueTypeCase5 != valueTypeCase4) {
                                return EvaluateResultError.INSTANCE;
                            }
                            try {
                                return EvaluateResult.INSTANCE.m716double(((Number) function13.invoke(Double.valueOf(value.getDoubleValue()))).doubleValue());
                            } catch (Exception unused) {
                                return EvaluateResultError.INSTANCE;
                            }
                        }
                        try {
                            return EvaluateResult.INSTANCE.m718long(((Number) function12.invoke(Long.valueOf(value.getIntegerValue()))).longValue());
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> arithmeticPrimitive(final Function2<? super Long, ? super Long, Long> intOp, final Function2<? super Double, ? super Double, Double> doubleOp) {
        Intrinsics.checkNotNullParameter(intOp, "intOp");
        Intrinsics.checkNotNullParameter(doubleOp, "doubleOp");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmeticPrimitive$$inlined$arithmetic$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function2 function2 = intOp;
                final Function2 function22 = doubleOp;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmeticPrimitive$$inlined$arithmetic$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        LongValue longValue;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            LongValue longValue2 = null;
                            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i == -1 || i == 1) {
                                longValue = null;
                            } else if (i == 5) {
                                longValue = new LongValue(value.getIntegerValue());
                            } else if (i == 6) {
                                longValue = new DoubleValue(value.getDoubleValue());
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value.ValueTypeCase valueTypeCase2 = value2 != null ? value2.getValueTypeCase() : null;
                            int i2 = valueTypeCase2 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase2.ordinal()];
                            if (i2 != -1 && i2 != 1) {
                                if (i2 == 5) {
                                    longValue2 = new LongValue(value2.getIntegerValue());
                                } else if (i2 == 6) {
                                    longValue2 = new DoubleValue(value2.getDoubleValue());
                                } else {
                                    return EvaluateResultError.INSTANCE;
                                }
                            }
                            if (longValue != null && longValue2 != null) {
                                if (longValue instanceof LongValue) {
                                    if (longValue2 instanceof LongValue) {
                                        return EvaluateResult.INSTANCE.m718long(((Number) function2.invoke(Long.valueOf(((LongValue) longValue).getValue()), Long.valueOf(((LongValue) longValue2).getValue()))).longValue());
                                    }
                                    if (!(longValue2 instanceof DoubleValue)) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    return EvaluateResult.INSTANCE.m716double(((Number) function22.invoke(Double.valueOf(((LongValue) longValue).getValue()), Double.valueOf(((DoubleValue) longValue2).getValue()))).doubleValue());
                                }
                                if (!(longValue instanceof DoubleValue)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                if (longValue2 instanceof DoubleValue) {
                                    return EvaluateResult.INSTANCE.m716double(((Number) function22.invoke(Double.valueOf(((DoubleValue) longValue).getValue()), Double.valueOf(((DoubleValue) longValue2).getValue()))).doubleValue());
                                }
                                if (longValue2 instanceof LongValue) {
                                    return EvaluateResult.INSTANCE.m716double(((Number) function22.invoke(Double.valueOf(((DoubleValue) longValue).getValue()), Double.valueOf(((LongValue) longValue2).getValue()))).doubleValue());
                                }
                                return EvaluateResultError.INSTANCE;
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> arithmetic(final Function1<? super Double, ? extends EvaluateResult> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        final Value.ValueTypeCase valueTypeCase = Value.ValueTypeCase.INTEGER_VALUE;
        final Value.ValueTypeCase valueTypeCase2 = Value.ValueTypeCase.DOUBLE_VALUE;
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmetic$$inlined$arithmetic$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 1) {
                    throw Assert.fail("Function should have exactly 1 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Value.ValueTypeCase valueTypeCase3 = valueTypeCase;
                final Value.ValueTypeCase valueTypeCase4 = valueTypeCase2;
                final Function1 function12 = function;
                final Function1 function13 = function;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmetic$$inlined$arithmetic$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        Value value = evaluateResult.getValue();
                        Value.ValueTypeCase valueTypeCase5 = value != null ? value.getValueTypeCase() : null;
                        int i = valueTypeCase5 == null ? -1 : UtilsKt.AnonymousClass2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0[valueTypeCase5.ordinal()];
                        if (i == -1 || i == 1) {
                            return EvaluateResult.INSTANCE.getNULL();
                        }
                        if (valueTypeCase5 != valueTypeCase3) {
                            if (valueTypeCase5 == valueTypeCase4) {
                                try {
                                    return (EvaluateResult) function12.invoke(Double.valueOf(value.getDoubleValue()));
                                } catch (Exception unused) {
                                    return EvaluateResultError.INSTANCE;
                                }
                            }
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            return (EvaluateResult) function13.invoke(Double.valueOf(value.getIntegerValue()));
                        } catch (Exception unused2) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> arithmeticNumberLong(final Function2<? super Long, ? super Long, ? extends EvaluateResult> intOp, final Function2<? super Double, ? super Long, ? extends EvaluateResult> doubleOp) {
        Intrinsics.checkNotNullParameter(intOp, "intOp");
        Intrinsics.checkNotNullParameter(doubleOp, "doubleOp");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmetic$$inlined$binaryValueValueFunction$2
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function2 function2 = intOp;
                final Function2 function22 = doubleOp;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmetic$$inlined$binaryValueValueFunction$2.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        LongValue longValue;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            Long lValueOf = null;
                            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i == -1 || i == 1) {
                                longValue = null;
                            } else if (i == 5) {
                                longValue = new LongValue(value.getIntegerValue());
                            } else if (i == 6) {
                                longValue = new DoubleValue(value.getDoubleValue());
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value.ValueTypeCase valueTypeCase2 = value2 != null ? value2.getValueTypeCase() : null;
                            int i2 = valueTypeCase2 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase2.ordinal()];
                            if (i2 != -1 && i2 != 1) {
                                if (i2 == 5) {
                                    lValueOf = Long.valueOf(value2.getIntegerValue());
                                } else if (i2 == 6) {
                                    lValueOf = Long.valueOf((long) value2.getDoubleValue());
                                } else {
                                    return EvaluateResultError.INSTANCE;
                                }
                            }
                            if (longValue != null && lValueOf != null) {
                                if (longValue instanceof LongValue) {
                                    return (EvaluateResult) function2.invoke(Long.valueOf(((LongValue) longValue).getValue()), lValueOf);
                                }
                                if (longValue instanceof DoubleValue) {
                                    return (EvaluateResult) function22.invoke(Double.valueOf(((DoubleValue) longValue).getValue()), lValueOf);
                                }
                                throw new NoWhenBranchMatchedException();
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> arithmetic(final Function2<? super Long, ? super Long, ? extends EvaluateResult> intOp, final Function2<? super Double, ? super Double, ? extends EvaluateResult> doubleOp) {
        Intrinsics.checkNotNullParameter(intOp, "intOp");
        Intrinsics.checkNotNullParameter(doubleOp, "doubleOp");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmetic$$inlined$binaryValueValueFunction$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function2 function2 = intOp;
                final Function2 function22 = doubleOp;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmetic$$inlined$binaryValueValueFunction$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        LongValue longValue;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            LongValue longValue2 = null;
                            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i == -1 || i == 1) {
                                longValue = null;
                            } else if (i == 5) {
                                longValue = new LongValue(value.getIntegerValue());
                            } else if (i == 6) {
                                longValue = new DoubleValue(value.getDoubleValue());
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value.ValueTypeCase valueTypeCase2 = value2 != null ? value2.getValueTypeCase() : null;
                            int i2 = valueTypeCase2 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase2.ordinal()];
                            if (i2 != -1 && i2 != 1) {
                                if (i2 == 5) {
                                    longValue2 = new LongValue(value2.getIntegerValue());
                                } else if (i2 == 6) {
                                    longValue2 = new DoubleValue(value2.getDoubleValue());
                                } else {
                                    return EvaluateResultError.INSTANCE;
                                }
                            }
                            if (longValue != null && longValue2 != null) {
                                if (longValue instanceof LongValue) {
                                    if (longValue2 instanceof LongValue) {
                                        return (EvaluateResult) function2.invoke(Long.valueOf(((LongValue) longValue).getValue()), Long.valueOf(((LongValue) longValue2).getValue()));
                                    }
                                    if (longValue2 instanceof DoubleValue) {
                                        return (EvaluateResult) function22.invoke(Double.valueOf(((LongValue) longValue).getValue()), Double.valueOf(((DoubleValue) longValue2).getValue()));
                                    }
                                    throw new NoWhenBranchMatchedException();
                                }
                                if (longValue instanceof DoubleValue) {
                                    return longValue2 instanceof DoubleValue ? (EvaluateResult) function22.invoke(Double.valueOf(((DoubleValue) longValue).getValue()), Double.valueOf(((DoubleValue) longValue2).getValue())) : longValue2 instanceof LongValue ? (EvaluateResult) function22.invoke(Double.valueOf(((DoubleValue) longValue).getValue()), Double.valueOf(((LongValue) longValue2).getValue())) : EvaluateResultError.INSTANCE;
                                }
                                throw new NoWhenBranchMatchedException();
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }

    public static final Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> arithmetic(final Function2<? super Double, ? super Double, ? extends EvaluateResult> doubleOp) {
        Intrinsics.checkNotNullParameter(doubleOp, "doubleOp");
        return (Function1) new Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<? super MutableDocument, ? extends EvaluateResult>>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmetic$$inlined$arithmetic$1
            @Override // kotlin.jvm.functions.Function1
            public final Function1<MutableDocument, EvaluateResult> invoke(List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>> params) {
                Intrinsics.checkNotNullParameter(params, "params");
                if (params.size() != 2) {
                    throw Assert.fail("Function should have exactly 2 params, but %d were given.", Integer.valueOf(params.size()));
                }
                final Function1<? super MutableDocument, ? extends EvaluateResult> function1 = params.get(0);
                final Function1<? super MutableDocument, ? extends EvaluateResult> function12 = params.get(1);
                final Function2 function2 = doubleOp;
                final Function2 function22 = doubleOp;
                return new Function1<MutableDocument, EvaluateResult>() { // from class: com.google.firebase.firestore.pipeline.evaluation.UtilsKt$arithmetic$$inlined$arithmetic$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final EvaluateResult invoke(MutableDocument input) {
                        LongValue longValue;
                        Intrinsics.checkNotNullParameter(input, "input");
                        EvaluateResult evaluateResult = (EvaluateResult) function1.invoke(input);
                        if (evaluateResult.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        EvaluateResult evaluateResult2 = (EvaluateResult) function12.invoke(input);
                        if (evaluateResult2.getIsError()) {
                            return EvaluateResultError.INSTANCE;
                        }
                        try {
                            Value value = evaluateResult.getValue();
                            Value value2 = evaluateResult2.getValue();
                            LongValue longValue2 = null;
                            Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
                            int i = valueTypeCase == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
                            if (i == -1 || i == 1) {
                                longValue = null;
                            } else if (i == 5) {
                                longValue = new LongValue(value.getIntegerValue());
                            } else if (i == 6) {
                                longValue = new DoubleValue(value.getDoubleValue());
                            } else {
                                return EvaluateResultError.INSTANCE;
                            }
                            Value.ValueTypeCase valueTypeCase2 = value2 != null ? value2.getValueTypeCase() : null;
                            int i2 = valueTypeCase2 == null ? -1 : UtilsKt.WhenMappings.$EnumSwitchMapping$0[valueTypeCase2.ordinal()];
                            if (i2 != -1 && i2 != 1) {
                                if (i2 == 5) {
                                    longValue2 = new LongValue(value2.getIntegerValue());
                                } else if (i2 == 6) {
                                    longValue2 = new DoubleValue(value2.getDoubleValue());
                                } else {
                                    return EvaluateResultError.INSTANCE;
                                }
                            }
                            if (longValue != null && longValue2 != null) {
                                if (longValue instanceof LongValue) {
                                    if (longValue2 instanceof LongValue) {
                                        return (EvaluateResult) function22.invoke(Double.valueOf(((LongValue) longValue).getValue()), Double.valueOf(((LongValue) longValue2).getValue()));
                                    }
                                    if (longValue2 instanceof DoubleValue) {
                                        return (EvaluateResult) function2.invoke(Double.valueOf(((LongValue) longValue).getValue()), Double.valueOf(((DoubleValue) longValue2).getValue()));
                                    }
                                    throw new NoWhenBranchMatchedException();
                                }
                                if (longValue instanceof DoubleValue) {
                                    return longValue2 instanceof DoubleValue ? (EvaluateResult) function2.invoke(Double.valueOf(((DoubleValue) longValue).getValue()), Double.valueOf(((DoubleValue) longValue2).getValue())) : longValue2 instanceof LongValue ? (EvaluateResult) function2.invoke(Double.valueOf(((DoubleValue) longValue).getValue()), Double.valueOf(((LongValue) longValue2).getValue())) : EvaluateResultError.INSTANCE;
                                }
                                throw new NoWhenBranchMatchedException();
                            }
                            return EvaluateResult.INSTANCE.getNULL();
                        } catch (Exception unused) {
                            return EvaluateResultError.INSTANCE;
                        }
                    }
                };
            }
        };
    }
}
