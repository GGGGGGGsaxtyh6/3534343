package com.google.firebase.firestore.pipeline;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.UserDataReader;
import com.google.firebase.firestore.VectorValue;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.pipeline.Expression;
import com.google.firebase.firestore.pipeline.evaluation.ArithmeticKt;
import com.google.firebase.firestore.pipeline.evaluation.ArrayKt;
import com.google.firebase.firestore.pipeline.evaluation.ComparisonKt;
import com.google.firebase.firestore.pipeline.evaluation.DebugKt;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResult;
import com.google.firebase.firestore.pipeline.evaluation.EvaluateResultValue;
import com.google.firebase.firestore.pipeline.evaluation.EvaluationContext;
import com.google.firebase.firestore.pipeline.evaluation.GenericsKt;
import com.google.firebase.firestore.pipeline.evaluation.LogicalKt;
import com.google.firebase.firestore.pipeline.evaluation.MapsKt;
import com.google.firebase.firestore.pipeline.evaluation.Strings;
import com.google.firebase.firestore.pipeline.evaluation.TimestampKt;
import com.google.firebase.firestore.pipeline.evaluation.UtilsKt;
import com.google.firebase.firestore.pipeline.evaluation.VectorKt;
import com.google.firebase.firestore.util.CustomClassMapper;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

/* JADX INFO: compiled from: expressions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0004\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u001f\n\u0002\u0010\u0013\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u0000 ¶\u00012\u00020\u0001:\u0004µ\u0001¶\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H ¢\u0006\u0002\b\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\f\u001a\u00020\u0000J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\u0006\u0010\u0014\u001a\u00020\u0000J\u0006\u0010\u0015\u001a\u00020\u0000J\u0006\u0010\u0016\u001a\u00020\u0000J\u0006\u0010\u0017\u001a\u00020\u0000J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0000J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001aJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0000J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u001aJ\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0000J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u001aJ\u0006\u0010!\u001a\u00020\u0000J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0010J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0000J\u0006\u0010$\u001a\u00020\u0000J\u0006\u0010%\u001a\u00020\u0000J\u000e\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u001aJ\u000e\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u0000J\u0006\u0010(\u001a\u00020\u0000J\u0006\u0010)\u001a\u00020\u0000J\u0006\u0010*\u001a\u00020\u0000J\u0014\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00010.J\u000e\u0010+\u001a\u00020,2\u0006\u0010/\u001a\u00020\u0000J\u0014\u00100\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00010.J\u000e\u00100\u001a\u00020,2\u0006\u0010/\u001a\u00020\u0000J\u0006\u00101\u001a\u00020,J\r\u00102\u001a\u00020,H\u0000¢\u0006\u0002\b3J\r\u00104\u001a\u00020,H\u0000¢\u0006\u0002\b5J\r\u00106\u001a\u00020,H\u0000¢\u0006\u0002\b7J\r\u00108\u001a\u00020,H\u0000¢\u0006\u0002\b9J\u0006\u0010:\u001a\u00020\u0000J\u0006\u0010;\u001a\u00020\u0000J\u0006\u0010<\u001a\u00020\u0000J\u000e\u0010=\u001a\u00020,2\u0006\u0010>\u001a\u00020\u0000J\u0006\u0010?\u001a\u00020\u0000J\u000e\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\u0000J\u000e\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\u0005J\u000e\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020BJ\u000e\u0010C\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\u0005J\u000e\u0010C\u001a\u00020\u00002\u0006\u0010D\u001a\u00020\u0000J\u000e\u0010=\u001a\u00020,2\u0006\u0010>\u001a\u00020\u0005J\u000e\u0010E\u001a\u00020,2\u0006\u0010>\u001a\u00020\u0000J\u000e\u0010E\u001a\u00020,2\u0006\u0010>\u001a\u00020\u0005J\u000e\u0010F\u001a\u00020,2\u0006\u0010>\u001a\u00020\u0000J\u000e\u0010F\u001a\u00020,2\u0006\u0010>\u001a\u00020\u0005J\u001f\u0010G\u001a\u00020\u00002\u0012\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000I\"\u00020\u0000¢\u0006\u0002\u0010JJ\u001f\u0010G\u001a\u00020\u00002\u0012\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010I\"\u00020\u0001¢\u0006\u0002\u0010KJ\u001f\u0010L\u001a\u00020\u00002\u0012\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000I\"\u00020\u0000¢\u0006\u0002\u0010JJ\u001f\u0010L\u001a\u00020\u00002\u0012\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010I\"\u00020\u0001¢\u0006\u0002\u0010KJ\u0006\u0010M\u001a\u00020\u0000J\u000e\u0010N\u001a\u00020,2\u0006\u0010O\u001a\u00020\u0000J\u000e\u0010N\u001a\u00020,2\u0006\u0010O\u001a\u00020\u0005J\u000e\u0010P\u001a\u00020,2\u0006\u0010Q\u001a\u00020\u0000J\u000e\u0010P\u001a\u00020,2\u0006\u0010Q\u001a\u00020\u0005J\u000e\u0010R\u001a\u00020,2\u0006\u0010S\u001a\u00020\u0000J\u000e\u0010R\u001a\u00020,2\u0006\u0010S\u001a\u00020\u0005J\u0006\u0010T\u001a\u00020\u0000J\u0016\u0010O\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u0000J\u0016\u0010O\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u00102\u0006\u0010:\u001a\u00020\u0010J\u0006\u0010V\u001a\u00020\u0000J\u0006\u0010W\u001a\u00020\u0000J\u0006\u0010X\u001a\u00020\u0000J\u000e\u0010Y\u001a\u00020\u00002\u0006\u0010Z\u001a\u00020\u0005J\u000e\u0010Y\u001a\u00020\u00002\u0006\u0010Z\u001a\u00020\u0000J\u001f\u0010[\u001a\u00020\u00002\u0012\u0010\\\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000I\"\u00020\u0000¢\u0006\u0002\u0010JJ\u001f\u0010[\u001a\u00020\u00002\u0012\u0010]\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050I\"\u00020\u0005¢\u0006\u0002\u0010^J\u001f\u0010[\u001a\u00020\u00002\u0012\u0010]\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010I\"\u00020\u0001¢\u0006\u0002\u0010KJ\u000e\u0010_\u001a\u00020\u00002\u0006\u0010`\u001a\u00020\u0000J\u000e\u0010_\u001a\u00020\u00002\u0006\u0010a\u001a\u00020\u0005J'\u0010b\u001a\u00020\u00002\u0006\u0010c\u001a\u00020\u00002\u0012\u0010d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000I\"\u00020\u0000¢\u0006\u0002\u0010eJ\u000e\u0010f\u001a\u00020\u00002\u0006\u0010`\u001a\u00020\u0000J\u000e\u0010f\u001a\u00020\u00002\u0006\u0010a\u001a\u00020\u0005J\u000e\u0010g\u001a\u00020\u00002\u0006\u0010h\u001a\u00020\u0000J\u000e\u0010g\u001a\u00020\u00002\u0006\u0010h\u001a\u00020iJ\u000e\u0010g\u001a\u00020\u00002\u0006\u0010h\u001a\u00020jJ\u000e\u0010k\u001a\u00020\u00002\u0006\u0010h\u001a\u00020\u0000J\u000e\u0010k\u001a\u00020\u00002\u0006\u0010h\u001a\u00020iJ\u000e\u0010k\u001a\u00020\u00002\u0006\u0010h\u001a\u00020jJ\u000e\u0010l\u001a\u00020\u00002\u0006\u0010h\u001a\u00020\u0000J\u000e\u0010l\u001a\u00020\u00002\u0006\u0010h\u001a\u00020iJ\u000e\u0010l\u001a\u00020\u00002\u0006\u0010h\u001a\u00020jJ\u0006\u0010m\u001a\u00020\u0000J\u0006\u0010n\u001a\u00020\u0000J\u0006\u0010o\u001a\u00020\u0000J\u0006\u0010p\u001a\u00020\u0000J\u0006\u0010q\u001a\u00020\u0000J\u0006\u0010r\u001a\u00020\u0000J\u0006\u0010s\u001a\u00020\u0000J\u0016\u0010t\u001a\u00020\u00002\u0006\u0010u\u001a\u00020\u00002\u0006\u0010v\u001a\u00020\u0000J\u0016\u0010t\u001a\u00020\u00002\u0006\u0010u\u001a\u00020\u00052\u0006\u0010v\u001a\u00020wJ\u000e\u0010x\u001a\u00020\u00002\u0006\u0010y\u001a\u00020\u0005J\u000e\u0010x\u001a\u00020\u00002\u0006\u0010y\u001a\u00020\u0000J\u0016\u0010z\u001a\u00020\u00002\u0006\u0010u\u001a\u00020\u00002\u0006\u0010v\u001a\u00020\u0000J\u0016\u0010z\u001a\u00020\u00002\u0006\u0010u\u001a\u00020\u00052\u0006\u0010v\u001a\u00020wJ'\u0010{\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00002\u0012\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010I\"\u00020\u0001¢\u0006\u0002\u0010|J'\u0010{\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u00012\u0012\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010I\"\u00020\u0001¢\u0006\u0002\u0010}J(\u0010~\u001a\u00020\u00002\u0006\u0010\u007f\u001a\u00020\u00002\u0013\u0010\u0080\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010I\"\u00020\u0001¢\u0006\u0002\u0010|J(\u0010~\u001a\u00020\u00002\u0006\u0010\u007f\u001a\u00020\u00012\u0013\u0010\u0080\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010I\"\u00020\u0001¢\u0006\u0002\u0010}J\u0007\u0010\u0081\u0001\u001a\u00020\u0000J\u0007\u0010\u0082\u0001\u001a\u00020\u0000J\u0010\u0010\u0083\u0001\u001a\u00020,2\u0007\u0010\u0084\u0001\u001a\u00020\u0000J\u0010\u0010\u0083\u0001\u001a\u00020,2\u0007\u0010\u0084\u0001\u001a\u00020\u0001J\u0015\u0010\u0085\u0001\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00010.J\u000f\u0010\u0085\u0001\u001a\u00020,2\u0006\u0010/\u001a\u00020\u0000J\u0015\u0010\u0086\u0001\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00010.J\u000f\u0010\u0086\u0001\u001a\u00020,2\u0006\u0010/\u001a\u00020\u0000J\u0007\u0010\u0087\u0001\u001a\u00020\u0000J\u0010\u0010\u0088\u0001\u001a\u00020\u00002\u0007\u0010\u0089\u0001\u001a\u00020\u0000J\u0010\u0010\u0088\u0001\u001a\u00020\u00002\u0007\u0010\u0089\u0001\u001a\u00020\u0010J\b\u0010\u008a\u0001\u001a\u00030\u008b\u0001J\b\u0010\u008c\u0001\u001a\u00030\u008b\u0001J\b\u0010\u008d\u0001\u001a\u00030\u008b\u0001J\b\u0010\u008e\u0001\u001a\u00030\u008b\u0001J\b\u0010\u008f\u0001\u001a\u00030\u008b\u0001J\b\u0010\u0090\u0001\u001a\u00030\u008b\u0001J\b\u0010\u0091\u0001\u001a\u00030\u0092\u0001J\b\u0010\u0093\u0001\u001a\u00030\u0092\u0001J\u0010\u0010\u0094\u0001\u001a\u00020,2\u0007\u0010\u0095\u0001\u001a\u00020\u0000J\u0010\u0010\u0094\u0001\u001a\u00020,2\u0007\u0010\u0096\u0001\u001a\u00020\u0001J\u0010\u0010\u0097\u0001\u001a\u00020,2\u0007\u0010\u0095\u0001\u001a\u00020\u0000J\u0010\u0010\u0097\u0001\u001a\u00020,2\u0007\u0010\u0096\u0001\u001a\u00020\u0001J\u0010\u0010\u0098\u0001\u001a\u00020,2\u0007\u0010\u0095\u0001\u001a\u00020\u0000J\u0010\u0010\u0098\u0001\u001a\u00020,2\u0007\u0010\u0096\u0001\u001a\u00020\u0001J\u0010\u0010\u0099\u0001\u001a\u00020,2\u0007\u0010\u0095\u0001\u001a\u00020\u0000J\u0010\u0010\u0099\u0001\u001a\u00020,2\u0007\u0010\u0096\u0001\u001a\u00020\u0001J\u0010\u0010\u009a\u0001\u001a\u00020,2\u0007\u0010\u0095\u0001\u001a\u00020\u0000J\u0010\u0010\u009a\u0001\u001a\u00020,2\u0007\u0010\u0096\u0001\u001a\u00020\u0001J\u0010\u0010\u009b\u0001\u001a\u00020,2\u0007\u0010\u0095\u0001\u001a\u00020\u0000J\u0010\u0010\u009b\u0001\u001a\u00020,2\u0007\u0010\u0096\u0001\u001a\u00020\u0001J\u0007\u0010\u009c\u0001\u001a\u00020,J\u0010\u0010\u009d\u0001\u001a\u00020\u00002\u0007\u0010\u009e\u0001\u001a\u00020\u0000J\u0010\u0010\u009d\u0001\u001a\u00020\u00002\u0007\u0010\u009f\u0001\u001a\u00020\u0001J\u0010\u0010 \u0001\u001a\u00020\u00002\u0007\u0010¡\u0001\u001a\u00020\u0000J\u0010\u0010 \u0001\u001a\u00020\u00002\u0007\u0010¢\u0001\u001a\u00020\u0001J\u0007\u0010£\u0001\u001a\u00020,J\u0007\u0010¤\u0001\u001a\u00020,J\u001a\u0010¥\u0001\u001a\u00030¦\u00012\b\u0010§\u0001\u001a\u00030¨\u0001H ¢\u0006\u0003\b©\u0001J?\u0010ª\u0001\u001a(\u0012\u0017\u0012\u00150¬\u0001¢\u0006\u000f\b\u00ad\u0001\u0012\n\b®\u0001\u0012\u0005\b\b(¯\u0001\u0012\u0005\u0012\u00030°\u00010«\u0001j\u0003`±\u00012\b\u0010²\u0001\u001a\u00030³\u0001H ¢\u0006\u0003\b´\u0001¨\u0006·\u0001"}, d2 = {"Lcom/google/firebase/firestore/pipeline/Expression;", "", "<init>", "()V", "canonicalId", "", "canonicalId$com_google_firebase_firebase_firestore", "bitAnd", "bitsOther", "", "bitOr", "bitXor", "bitNot", "bitLeftShift", "numberExpr", "number", "", "bitRightShift", "alias", "Lcom/google/firebase/firestore/pipeline/Selectable;", "documentId", "collectionId", "abs", "exp", "add", "second", "", "subtract", "subtrahend", "multiply", "divide", "divisor", "mod", "round", "roundToPrecision", "decimalPlace", "ceil", "floor", "pow", "exponent", "sqrt", "ln", "log10", "equalAny", "Lcom/google/firebase/firestore/pipeline/BooleanExpression;", "values", "", "arrayExpression", "notEqualAny", "isAbsent", "isNan", "isNan$com_google_firebase_firebase_firestore", "isNotNan", "isNotNan$com_google_firebase_firebase_firestore", "isNull", "isNull$com_google_firebase_firebase_firestore", "isNotNull", "isNotNull$com_google_firebase_firebase_firestore", "length", "charLength", "byteLength", "like", "pattern", "type", "split", "delimiter", "Lcom/google/firebase/firestore/Blob;", "join", "delimiterExpression", "regexContains", "regexMatch", "logicalMaximum", "others", "", "([Lcom/google/firebase/firestore/pipeline/Expression;)Lcom/google/firebase/firestore/pipeline/Expression;", "([Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/Expression;", "logicalMinimum", "reverse", "stringContains", "substring", "startsWith", "prefix", "endsWith", "suffix", "stringReverse", "start", "toLower", "toUpper", "trim", "trimValue", "valueToTrim", "stringConcat", "stringExpressions", "strings", "([Ljava/lang/String;)Lcom/google/firebase/firestore/pipeline/Expression;", "mapGet", "keyExpression", "key", "mapMerge", "mapExpr", "otherMaps", "(Lcom/google/firebase/firestore/pipeline/Expression;[Lcom/google/firebase/firestore/pipeline/Expression;)Lcom/google/firebase/firestore/pipeline/Expression;", "mapRemove", "cosineDistance", "vector", "", "Lcom/google/firebase/firestore/VectorValue;", "dotProduct", "euclideanDistance", "vectorLength", "unixMicrosToTimestamp", "timestampToUnixMicros", "unixMillisToTimestamp", "timestampToUnixMillis", "unixSecondsToTimestamp", "timestampToUnixSeconds", "timestampAdd", "unit", "amount", "", "timestampTruncate", "granularity", "timestampSubtract", "concat", "(Lcom/google/firebase/firestore/pipeline/Expression;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/Expression;", "(Ljava/lang/Object;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/Expression;", "arrayConcat", "secondArray", "otherArrays", "arrayReverse", "arraySum", "arrayContains", "element", "arrayContainsAll", "arrayContainsAny", "arrayLength", "arrayGet", TypedValues.CycleType.S_WAVE_OFFSET, "count", "Lcom/google/firebase/firestore/pipeline/AggregateFunction;", "countDistinct", "sum", "average", "minimum", "maximum", "ascending", "Lcom/google/firebase/firestore/pipeline/Ordering;", "descending", "equal", "other", Values.VECTOR_MAP_VECTORS_KEY, "notEqual", "greaterThan", "greaterThanOrEqual", "lessThan", "lessThanOrEqual", "exists", "ifError", "catchExpr", "catchValue", "ifAbsent", "elseExpr", "elseValue", "isError", "asBoolean", "toProto", "Lcom/google/firestore/v1/Value;", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "toProto$com_google_firebase_firebase_firestore", "evaluateFunction", "Lkotlin/Function1;", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lkotlin/ParameterName;", "name", "input", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResult;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateDocument;", "context", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "evaluateFunction$com_google_firebase_firebase_firestore", "Constant", "Companion", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class Expression {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Expression NULL = new Constant(Values.NULL_VALUE);

    @JvmStatic
    public static final Expression abs(Expression expression) {
        return INSTANCE.abs(expression);
    }

    @JvmStatic
    public static final Expression abs(String str) {
        return INSTANCE.abs(str);
    }

    @JvmStatic
    public static final Expression add(Expression expression, Expression expression2) {
        return INSTANCE.add(expression, expression2);
    }

    @JvmStatic
    public static final Expression add(Expression expression, Number number) {
        return INSTANCE.add(expression, number);
    }

    @JvmStatic
    public static final Expression add(String str, Expression expression) {
        return INSTANCE.add(str, expression);
    }

    @JvmStatic
    public static final Expression add(String str, Number number) {
        return INSTANCE.add(str, number);
    }

    @JvmStatic
    public static final BooleanExpression and(BooleanExpression booleanExpression, BooleanExpression... booleanExpressionArr) {
        return INSTANCE.and(booleanExpression, booleanExpressionArr);
    }

    @JvmStatic
    public static final Expression array(List<? extends Object> list) {
        return INSTANCE.array(list);
    }

    @JvmStatic
    public static final Expression array(Object... objArr) {
        return INSTANCE.array(objArr);
    }

    @JvmStatic
    public static final Expression arrayConcat(Expression expression, Expression expression2, Object... objArr) {
        return INSTANCE.arrayConcat(expression, expression2, objArr);
    }

    @JvmStatic
    public static final Expression arrayConcat(Expression expression, Object obj, Object... objArr) {
        return INSTANCE.arrayConcat(expression, obj, objArr);
    }

    @JvmStatic
    public static final Expression arrayConcat(String str, Expression expression, Object... objArr) {
        return INSTANCE.arrayConcat(str, expression, objArr);
    }

    @JvmStatic
    public static final Expression arrayConcat(String str, Object obj, Object... objArr) {
        return INSTANCE.arrayConcat(str, obj, objArr);
    }

    @JvmStatic
    public static final BooleanExpression arrayContains(Expression expression, Expression expression2) {
        return INSTANCE.arrayContains(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression arrayContains(Expression expression, Object obj) {
        return INSTANCE.arrayContains(expression, obj);
    }

    @JvmStatic
    public static final BooleanExpression arrayContains(String str, Expression expression) {
        return INSTANCE.arrayContains(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression arrayContains(String str, Object obj) {
        return INSTANCE.arrayContains(str, obj);
    }

    @JvmStatic
    public static final BooleanExpression arrayContainsAll(Expression expression, Expression expression2) {
        return INSTANCE.arrayContainsAll(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression arrayContainsAll(Expression expression, List<? extends Object> list) {
        return INSTANCE.arrayContainsAll(expression, list);
    }

    @JvmStatic
    public static final BooleanExpression arrayContainsAll(String str, Expression expression) {
        return INSTANCE.arrayContainsAll(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression arrayContainsAll(String str, List<? extends Object> list) {
        return INSTANCE.arrayContainsAll(str, list);
    }

    @JvmStatic
    public static final BooleanExpression arrayContainsAny(Expression expression, Expression expression2) {
        return INSTANCE.arrayContainsAny(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression arrayContainsAny(Expression expression, List<? extends Object> list) {
        return INSTANCE.arrayContainsAny(expression, list);
    }

    @JvmStatic
    public static final BooleanExpression arrayContainsAny(String str, Expression expression) {
        return INSTANCE.arrayContainsAny(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression arrayContainsAny(String str, List<? extends Object> list) {
        return INSTANCE.arrayContainsAny(str, list);
    }

    @JvmStatic
    public static final Expression arrayGet(Expression expression, int i) {
        return INSTANCE.arrayGet(expression, i);
    }

    @JvmStatic
    public static final Expression arrayGet(Expression expression, Expression expression2) {
        return INSTANCE.arrayGet(expression, expression2);
    }

    @JvmStatic
    public static final Expression arrayGet(String str, int i) {
        return INSTANCE.arrayGet(str, i);
    }

    @JvmStatic
    public static final Expression arrayGet(String str, Expression expression) {
        return INSTANCE.arrayGet(str, expression);
    }

    @JvmStatic
    public static final Expression arrayLength(Expression expression) {
        return INSTANCE.arrayLength(expression);
    }

    @JvmStatic
    public static final Expression arrayLength(String str) {
        return INSTANCE.arrayLength(str);
    }

    @JvmStatic
    public static final Expression arrayReverse(Expression expression) {
        return INSTANCE.arrayReverse(expression);
    }

    @JvmStatic
    public static final Expression arrayReverse(String str) {
        return INSTANCE.arrayReverse(str);
    }

    @JvmStatic
    public static final Expression arraySum(Expression expression) {
        return INSTANCE.arraySum(expression);
    }

    @JvmStatic
    public static final Expression arraySum(String str) {
        return INSTANCE.arraySum(str);
    }

    @JvmStatic
    public static final Expression bitAnd(Expression expression, Expression expression2) {
        return INSTANCE.bitAnd(expression, expression2);
    }

    @JvmStatic
    public static final Expression bitAnd(Expression expression, byte[] bArr) {
        return INSTANCE.bitAnd(expression, bArr);
    }

    @JvmStatic
    public static final Expression bitAnd(String str, Expression expression) {
        return INSTANCE.bitAnd(str, expression);
    }

    @JvmStatic
    public static final Expression bitAnd(String str, byte[] bArr) {
        return INSTANCE.bitAnd(str, bArr);
    }

    @JvmStatic
    public static final Expression bitLeftShift(Expression expression, int i) {
        return INSTANCE.bitLeftShift(expression, i);
    }

    @JvmStatic
    public static final Expression bitLeftShift(Expression expression, Expression expression2) {
        return INSTANCE.bitLeftShift(expression, expression2);
    }

    @JvmStatic
    public static final Expression bitLeftShift(String str, int i) {
        return INSTANCE.bitLeftShift(str, i);
    }

    @JvmStatic
    public static final Expression bitLeftShift(String str, Expression expression) {
        return INSTANCE.bitLeftShift(str, expression);
    }

    @JvmStatic
    public static final Expression bitNot(Expression expression) {
        return INSTANCE.bitNot(expression);
    }

    @JvmStatic
    public static final Expression bitNot(String str) {
        return INSTANCE.bitNot(str);
    }

    @JvmStatic
    public static final Expression bitOr(Expression expression, Expression expression2) {
        return INSTANCE.bitOr(expression, expression2);
    }

    @JvmStatic
    public static final Expression bitOr(Expression expression, byte[] bArr) {
        return INSTANCE.bitOr(expression, bArr);
    }

    @JvmStatic
    public static final Expression bitOr(String str, Expression expression) {
        return INSTANCE.bitOr(str, expression);
    }

    @JvmStatic
    public static final Expression bitOr(String str, byte[] bArr) {
        return INSTANCE.bitOr(str, bArr);
    }

    @JvmStatic
    public static final Expression bitRightShift(Expression expression, int i) {
        return INSTANCE.bitRightShift(expression, i);
    }

    @JvmStatic
    public static final Expression bitRightShift(Expression expression, Expression expression2) {
        return INSTANCE.bitRightShift(expression, expression2);
    }

    @JvmStatic
    public static final Expression bitRightShift(String str, int i) {
        return INSTANCE.bitRightShift(str, i);
    }

    @JvmStatic
    public static final Expression bitRightShift(String str, Expression expression) {
        return INSTANCE.bitRightShift(str, expression);
    }

    @JvmStatic
    public static final Expression bitXor(Expression expression, Expression expression2) {
        return INSTANCE.bitXor(expression, expression2);
    }

    @JvmStatic
    public static final Expression bitXor(Expression expression, byte[] bArr) {
        return INSTANCE.bitXor(expression, bArr);
    }

    @JvmStatic
    public static final Expression bitXor(String str, Expression expression) {
        return INSTANCE.bitXor(str, expression);
    }

    @JvmStatic
    public static final Expression bitXor(String str, byte[] bArr) {
        return INSTANCE.bitXor(str, bArr);
    }

    @JvmStatic
    public static final Expression byteLength(Expression expression) {
        return INSTANCE.byteLength(expression);
    }

    @JvmStatic
    public static final Expression byteLength(String str) {
        return INSTANCE.byteLength(str);
    }

    @JvmStatic
    public static final Expression ceil(Expression expression) {
        return INSTANCE.ceil(expression);
    }

    @JvmStatic
    public static final Expression ceil(String str) {
        return INSTANCE.ceil(str);
    }

    @JvmStatic
    public static final Expression charLength(Expression expression) {
        return INSTANCE.charLength(expression);
    }

    @JvmStatic
    public static final Expression charLength(String str) {
        return INSTANCE.charLength(str);
    }

    @JvmStatic
    public static final Expression collectionId(Expression expression) {
        return INSTANCE.collectionId(expression);
    }

    @JvmStatic
    public static final Expression collectionId(String str) {
        return INSTANCE.collectionId(str);
    }

    @JvmStatic
    public static final Expression concat(Expression expression, Expression expression2, Object... objArr) {
        return INSTANCE.concat(expression, expression2, objArr);
    }

    @JvmStatic
    public static final Expression concat(Expression expression, Object obj, Object... objArr) {
        return INSTANCE.concat(expression, obj, objArr);
    }

    @JvmStatic
    public static final Expression concat(String str, Expression expression, Object... objArr) {
        return INSTANCE.concat(str, expression, objArr);
    }

    @JvmStatic
    public static final Expression concat(String str, Object obj, Object... objArr) {
        return INSTANCE.concat(str, obj, objArr);
    }

    @JvmStatic
    public static final Expression conditional(BooleanExpression booleanExpression, Expression expression, Expression expression2) {
        return INSTANCE.conditional(booleanExpression, expression, expression2);
    }

    @JvmStatic
    public static final Expression conditional(BooleanExpression booleanExpression, Object obj, Object obj2) {
        return INSTANCE.conditional(booleanExpression, obj, obj2);
    }

    @JvmStatic
    public static final BooleanExpression constant(boolean z) {
        return INSTANCE.constant(z);
    }

    @JvmStatic
    public static final Expression constant(Timestamp timestamp) {
        return INSTANCE.constant(timestamp);
    }

    @JvmStatic
    public static final Expression constant(Blob blob) {
        return INSTANCE.constant(blob);
    }

    @JvmStatic
    public static final Expression constant(DocumentReference documentReference) {
        return INSTANCE.constant(documentReference);
    }

    @JvmStatic
    public static final Expression constant(GeoPoint geoPoint) {
        return INSTANCE.constant(geoPoint);
    }

    @JvmStatic
    public static final Expression constant(VectorValue vectorValue) {
        return INSTANCE.constant(vectorValue);
    }

    @JvmStatic
    public static final Expression constant(Number number) {
        return INSTANCE.constant(number);
    }

    @JvmStatic
    public static final Expression constant(String str) {
        return INSTANCE.constant(str);
    }

    @JvmStatic
    public static final Expression constant(Date date) {
        return INSTANCE.constant(date);
    }

    @JvmStatic
    public static final Expression constant(byte[] bArr) {
        return INSTANCE.constant(bArr);
    }

    @JvmStatic
    public static final Expression cosineDistance(Expression expression, VectorValue vectorValue) {
        return INSTANCE.cosineDistance(expression, vectorValue);
    }

    @JvmStatic
    public static final Expression cosineDistance(Expression expression, Expression expression2) {
        return INSTANCE.cosineDistance(expression, expression2);
    }

    @JvmStatic
    public static final Expression cosineDistance(Expression expression, double[] dArr) {
        return INSTANCE.cosineDistance(expression, dArr);
    }

    @JvmStatic
    public static final Expression cosineDistance(String str, VectorValue vectorValue) {
        return INSTANCE.cosineDistance(str, vectorValue);
    }

    @JvmStatic
    public static final Expression cosineDistance(String str, Expression expression) {
        return INSTANCE.cosineDistance(str, expression);
    }

    @JvmStatic
    public static final Expression cosineDistance(String str, double[] dArr) {
        return INSTANCE.cosineDistance(str, dArr);
    }

    @JvmStatic
    public static final Expression currentTimestamp() {
        return INSTANCE.currentTimestamp();
    }

    @JvmStatic
    public static final Expression divide(Expression expression, Expression expression2) {
        return INSTANCE.divide(expression, expression2);
    }

    @JvmStatic
    public static final Expression divide(Expression expression, Number number) {
        return INSTANCE.divide(expression, number);
    }

    @JvmStatic
    public static final Expression divide(String str, Expression expression) {
        return INSTANCE.divide(str, expression);
    }

    @JvmStatic
    public static final Expression divide(String str, Number number) {
        return INSTANCE.divide(str, number);
    }

    @JvmStatic
    public static final Expression documentId(DocumentReference documentReference) {
        return INSTANCE.documentId(documentReference);
    }

    @JvmStatic
    public static final Expression documentId(Expression expression) {
        return INSTANCE.documentId(expression);
    }

    @JvmStatic
    public static final Expression documentId(String str) {
        return INSTANCE.documentId(str);
    }

    @JvmStatic
    public static final Expression dotProduct(Expression expression, VectorValue vectorValue) {
        return INSTANCE.dotProduct(expression, vectorValue);
    }

    @JvmStatic
    public static final Expression dotProduct(Expression expression, Expression expression2) {
        return INSTANCE.dotProduct(expression, expression2);
    }

    @JvmStatic
    public static final Expression dotProduct(Expression expression, double[] dArr) {
        return INSTANCE.dotProduct(expression, dArr);
    }

    @JvmStatic
    public static final Expression dotProduct(String str, VectorValue vectorValue) {
        return INSTANCE.dotProduct(str, vectorValue);
    }

    @JvmStatic
    public static final Expression dotProduct(String str, Expression expression) {
        return INSTANCE.dotProduct(str, expression);
    }

    @JvmStatic
    public static final Expression dotProduct(String str, double[] dArr) {
        return INSTANCE.dotProduct(str, dArr);
    }

    @JvmStatic
    public static final BooleanExpression endsWith(Expression expression, Expression expression2) {
        return INSTANCE.endsWith(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression endsWith(Expression expression, String str) {
        return INSTANCE.endsWith(expression, str);
    }

    @JvmStatic
    public static final BooleanExpression endsWith(String str, Expression expression) {
        return INSTANCE.endsWith(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression endsWith(String str, String str2) {
        return INSTANCE.endsWith(str, str2);
    }

    @JvmStatic
    public static final BooleanExpression equal(Expression expression, Expression expression2) {
        return INSTANCE.equal(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression equal(Expression expression, Object obj) {
        return INSTANCE.equal(expression, obj);
    }

    @JvmStatic
    public static final BooleanExpression equal(String str, Expression expression) {
        return INSTANCE.equal(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression equal(String str, Object obj) {
        return INSTANCE.equal(str, obj);
    }

    @JvmStatic
    public static final BooleanExpression equalAny(Expression expression, Expression expression2) {
        return INSTANCE.equalAny(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression equalAny(Expression expression, List<? extends Object> list) {
        return INSTANCE.equalAny(expression, list);
    }

    @JvmStatic
    public static final BooleanExpression equalAny(String str, Expression expression) {
        return INSTANCE.equalAny(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression equalAny(String str, List<? extends Object> list) {
        return INSTANCE.equalAny(str, list);
    }

    @JvmStatic
    public static final Expression euclideanDistance(Expression expression, VectorValue vectorValue) {
        return INSTANCE.euclideanDistance(expression, vectorValue);
    }

    @JvmStatic
    public static final Expression euclideanDistance(Expression expression, Expression expression2) {
        return INSTANCE.euclideanDistance(expression, expression2);
    }

    @JvmStatic
    public static final Expression euclideanDistance(Expression expression, double[] dArr) {
        return INSTANCE.euclideanDistance(expression, dArr);
    }

    @JvmStatic
    public static final Expression euclideanDistance(String str, VectorValue vectorValue) {
        return INSTANCE.euclideanDistance(str, vectorValue);
    }

    @JvmStatic
    public static final Expression euclideanDistance(String str, Expression expression) {
        return INSTANCE.euclideanDistance(str, expression);
    }

    @JvmStatic
    public static final Expression euclideanDistance(String str, double[] dArr) {
        return INSTANCE.euclideanDistance(str, dArr);
    }

    @JvmStatic
    public static final BooleanExpression exists(Expression expression) {
        return INSTANCE.exists(expression);
    }

    @JvmStatic
    public static final BooleanExpression exists(String str) {
        return INSTANCE.exists(str);
    }

    @JvmStatic
    public static final Expression exp(Expression expression) {
        return INSTANCE.exp(expression);
    }

    @JvmStatic
    public static final Expression exp(String str) {
        return INSTANCE.exp(str);
    }

    @JvmStatic
    public static final Field field(FieldPath fieldPath) {
        return INSTANCE.field(fieldPath);
    }

    @JvmStatic
    public static final Field field(String str) {
        return INSTANCE.field(str);
    }

    @JvmStatic
    public static final Expression floor(Expression expression) {
        return INSTANCE.floor(expression);
    }

    @JvmStatic
    public static final Expression floor(String str) {
        return INSTANCE.floor(str);
    }

    @JvmStatic
    public static final BooleanExpression greaterThan(Expression expression, Expression expression2) {
        return INSTANCE.greaterThan(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression greaterThan(Expression expression, Object obj) {
        return INSTANCE.greaterThan(expression, obj);
    }

    @JvmStatic
    public static final BooleanExpression greaterThan(String str, Expression expression) {
        return INSTANCE.greaterThan(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression greaterThan(String str, Object obj) {
        return INSTANCE.greaterThan(str, obj);
    }

    @JvmStatic
    public static final BooleanExpression greaterThanOrEqual(Expression expression, Expression expression2) {
        return INSTANCE.greaterThanOrEqual(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression greaterThanOrEqual(Expression expression, Object obj) {
        return INSTANCE.greaterThanOrEqual(expression, obj);
    }

    @JvmStatic
    public static final BooleanExpression greaterThanOrEqual(String str, Expression expression) {
        return INSTANCE.greaterThanOrEqual(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression greaterThanOrEqual(String str, Object obj) {
        return INSTANCE.greaterThanOrEqual(str, obj);
    }

    @JvmStatic
    public static final Expression ifAbsent(Expression expression, Expression expression2) {
        return INSTANCE.ifAbsent(expression, expression2);
    }

    @JvmStatic
    public static final Expression ifAbsent(Expression expression, Object obj) {
        return INSTANCE.ifAbsent(expression, obj);
    }

    @JvmStatic
    public static final Expression ifAbsent(String str, Expression expression) {
        return INSTANCE.ifAbsent(str, expression);
    }

    @JvmStatic
    public static final Expression ifAbsent(String str, Object obj) {
        return INSTANCE.ifAbsent(str, obj);
    }

    @JvmStatic
    public static final BooleanExpression ifError(BooleanExpression booleanExpression, BooleanExpression booleanExpression2) {
        return INSTANCE.ifError(booleanExpression, booleanExpression2);
    }

    @JvmStatic
    public static final Expression ifError(Expression expression, Expression expression2) {
        return INSTANCE.ifError(expression, expression2);
    }

    @JvmStatic
    public static final Expression ifError(Expression expression, Object obj) {
        return INSTANCE.ifError(expression, obj);
    }

    @JvmStatic
    public static final BooleanExpression isAbsent(Expression expression) {
        return INSTANCE.isAbsent(expression);
    }

    @JvmStatic
    public static final BooleanExpression isAbsent(String str) {
        return INSTANCE.isAbsent(str);
    }

    @JvmStatic
    public static final BooleanExpression isError(Expression expression) {
        return INSTANCE.isError(expression);
    }

    @JvmStatic
    public static final Expression join(Expression expression, Expression expression2) {
        return INSTANCE.join(expression, expression2);
    }

    @JvmStatic
    public static final Expression join(Expression expression, String str) {
        return INSTANCE.join(expression, str);
    }

    @JvmStatic
    public static final Expression join(String str, Expression expression) {
        return INSTANCE.join(str, expression);
    }

    @JvmStatic
    public static final Expression join(String str, String str2) {
        return INSTANCE.join(str, str2);
    }

    @JvmStatic
    public static final Expression length(Expression expression) {
        return INSTANCE.length(expression);
    }

    @JvmStatic
    public static final Expression length(String str) {
        return INSTANCE.length(str);
    }

    @JvmStatic
    public static final BooleanExpression lessThan(Expression expression, Expression expression2) {
        return INSTANCE.lessThan(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression lessThan(Expression expression, Object obj) {
        return INSTANCE.lessThan(expression, obj);
    }

    @JvmStatic
    public static final BooleanExpression lessThan(String str, Expression expression) {
        return INSTANCE.lessThan(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression lessThan(String str, Object obj) {
        return INSTANCE.lessThan(str, obj);
    }

    @JvmStatic
    public static final BooleanExpression lessThanOrEqual(Expression expression, Expression expression2) {
        return INSTANCE.lessThanOrEqual(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression lessThanOrEqual(Expression expression, Object obj) {
        return INSTANCE.lessThanOrEqual(expression, obj);
    }

    @JvmStatic
    public static final BooleanExpression lessThanOrEqual(String str, Expression expression) {
        return INSTANCE.lessThanOrEqual(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression lessThanOrEqual(String str, Object obj) {
        return INSTANCE.lessThanOrEqual(str, obj);
    }

    @JvmStatic
    public static final BooleanExpression like(Expression expression, Expression expression2) {
        return INSTANCE.like(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression like(Expression expression, String str) {
        return INSTANCE.like(expression, str);
    }

    @JvmStatic
    public static final BooleanExpression like(String str, Expression expression) {
        return INSTANCE.like(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression like(String str, String str2) {
        return INSTANCE.like(str, str2);
    }

    @JvmStatic
    public static final Expression ln(Expression expression) {
        return INSTANCE.ln(expression);
    }

    @JvmStatic
    public static final Expression ln(String str) {
        return INSTANCE.ln(str);
    }

    @JvmStatic
    public static final Expression log(Expression expression, Expression expression2) {
        return INSTANCE.log(expression, expression2);
    }

    @JvmStatic
    public static final Expression log(Expression expression, Number number) {
        return INSTANCE.log(expression, number);
    }

    @JvmStatic
    public static final Expression log(String str, Expression expression) {
        return INSTANCE.log(str, expression);
    }

    @JvmStatic
    public static final Expression log(String str, Number number) {
        return INSTANCE.log(str, number);
    }

    @JvmStatic
    public static final Expression log10(Expression expression) {
        return INSTANCE.log10(expression);
    }

    @JvmStatic
    public static final Expression log10(String str) {
        return INSTANCE.log10(str);
    }

    @JvmStatic
    public static final Expression logicalMaximum(Expression expression, Object... objArr) {
        return INSTANCE.logicalMaximum(expression, objArr);
    }

    @JvmStatic
    public static final Expression logicalMaximum(String str, Object... objArr) {
        return INSTANCE.logicalMaximum(str, objArr);
    }

    @JvmStatic
    public static final Expression logicalMinimum(Expression expression, Object... objArr) {
        return INSTANCE.logicalMinimum(expression, objArr);
    }

    @JvmStatic
    public static final Expression logicalMinimum(String str, Object... objArr) {
        return INSTANCE.logicalMinimum(str, objArr);
    }

    @JvmStatic
    public static final Expression map(Map<String, ? extends Object> map) {
        return INSTANCE.map(map);
    }

    @JvmStatic
    public static final Expression mapGet(Expression expression, Expression expression2) {
        return INSTANCE.mapGet(expression, expression2);
    }

    @JvmStatic
    public static final Expression mapGet(Expression expression, String str) {
        return INSTANCE.mapGet(expression, str);
    }

    @JvmStatic
    public static final Expression mapGet(String str, Expression expression) {
        return INSTANCE.mapGet(str, expression);
    }

    @JvmStatic
    public static final Expression mapGet(String str, String str2) {
        return INSTANCE.mapGet(str, str2);
    }

    @JvmStatic
    public static final Expression mapMerge(Expression expression, Expression expression2, Expression... expressionArr) {
        return INSTANCE.mapMerge(expression, expression2, expressionArr);
    }

    @JvmStatic
    public static final Expression mapMerge(String str, Expression expression, Expression... expressionArr) {
        return INSTANCE.mapMerge(str, expression, expressionArr);
    }

    @JvmStatic
    public static final Expression mapRemove(Expression expression, Expression expression2) {
        return INSTANCE.mapRemove(expression, expression2);
    }

    @JvmStatic
    public static final Expression mapRemove(Expression expression, String str) {
        return INSTANCE.mapRemove(expression, str);
    }

    @JvmStatic
    public static final Expression mapRemove(String str, Expression expression) {
        return INSTANCE.mapRemove(str, expression);
    }

    @JvmStatic
    public static final Expression mapRemove(String str, String str2) {
        return INSTANCE.mapRemove(str, str2);
    }

    @JvmStatic
    public static final Expression mod(Expression expression, Expression expression2) {
        return INSTANCE.mod(expression, expression2);
    }

    @JvmStatic
    public static final Expression mod(Expression expression, Number number) {
        return INSTANCE.mod(expression, number);
    }

    @JvmStatic
    public static final Expression mod(String str, Expression expression) {
        return INSTANCE.mod(str, expression);
    }

    @JvmStatic
    public static final Expression mod(String str, Number number) {
        return INSTANCE.mod(str, number);
    }

    @JvmStatic
    public static final Expression multiply(Expression expression, Expression expression2) {
        return INSTANCE.multiply(expression, expression2);
    }

    @JvmStatic
    public static final Expression multiply(Expression expression, Number number) {
        return INSTANCE.multiply(expression, number);
    }

    @JvmStatic
    public static final Expression multiply(String str, Expression expression) {
        return INSTANCE.multiply(str, expression);
    }

    @JvmStatic
    public static final Expression multiply(String str, Number number) {
        return INSTANCE.multiply(str, number);
    }

    @JvmStatic
    public static final BooleanExpression not(BooleanExpression booleanExpression) {
        return INSTANCE.not(booleanExpression);
    }

    @JvmStatic
    public static final BooleanExpression notEqual(Expression expression, Expression expression2) {
        return INSTANCE.notEqual(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression notEqual(Expression expression, Object obj) {
        return INSTANCE.notEqual(expression, obj);
    }

    @JvmStatic
    public static final BooleanExpression notEqual(String str, Expression expression) {
        return INSTANCE.notEqual(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression notEqual(String str, Object obj) {
        return INSTANCE.notEqual(str, obj);
    }

    @JvmStatic
    public static final BooleanExpression notEqualAny(Expression expression, Expression expression2) {
        return INSTANCE.notEqualAny(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression notEqualAny(Expression expression, List<? extends Object> list) {
        return INSTANCE.notEqualAny(expression, list);
    }

    @JvmStatic
    public static final BooleanExpression notEqualAny(String str, Expression expression) {
        return INSTANCE.notEqualAny(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression notEqualAny(String str, List<? extends Object> list) {
        return INSTANCE.notEqualAny(str, list);
    }

    @JvmStatic
    public static final Expression nullValue() {
        return INSTANCE.nullValue();
    }

    @JvmStatic
    public static final BooleanExpression or(BooleanExpression booleanExpression, BooleanExpression... booleanExpressionArr) {
        return INSTANCE.or(booleanExpression, booleanExpressionArr);
    }

    @JvmStatic
    public static final Expression pow(Expression expression, Expression expression2) {
        return INSTANCE.pow(expression, expression2);
    }

    @JvmStatic
    public static final Expression pow(Expression expression, Number number) {
        return INSTANCE.pow(expression, number);
    }

    @JvmStatic
    public static final Expression pow(String str, Expression expression) {
        return INSTANCE.pow(str, expression);
    }

    @JvmStatic
    public static final Expression pow(String str, Number number) {
        return INSTANCE.pow(str, number);
    }

    @JvmStatic
    public static final Expression rawFunction(String str, Expression... expressionArr) {
        return INSTANCE.rawFunction(str, expressionArr);
    }

    @JvmStatic
    public static final BooleanExpression regexContains(Expression expression, Expression expression2) {
        return INSTANCE.regexContains(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression regexContains(Expression expression, String str) {
        return INSTANCE.regexContains(expression, str);
    }

    @JvmStatic
    public static final BooleanExpression regexContains(String str, Expression expression) {
        return INSTANCE.regexContains(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression regexContains(String str, String str2) {
        return INSTANCE.regexContains(str, str2);
    }

    @JvmStatic
    public static final BooleanExpression regexMatch(Expression expression, Expression expression2) {
        return INSTANCE.regexMatch(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression regexMatch(Expression expression, String str) {
        return INSTANCE.regexMatch(expression, str);
    }

    @JvmStatic
    public static final BooleanExpression regexMatch(String str, Expression expression) {
        return INSTANCE.regexMatch(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression regexMatch(String str, String str2) {
        return INSTANCE.regexMatch(str, str2);
    }

    @JvmStatic
    public static final Expression reverse(Expression expression) {
        return INSTANCE.reverse(expression);
    }

    @JvmStatic
    public static final Expression reverse(String str) {
        return INSTANCE.reverse(str);
    }

    @JvmStatic
    public static final Expression round(Expression expression) {
        return INSTANCE.round(expression);
    }

    @JvmStatic
    public static final Expression round(String str) {
        return INSTANCE.round(str);
    }

    @JvmStatic
    public static final Expression roundToPrecision(Expression expression, int i) {
        return INSTANCE.roundToPrecision(expression, i);
    }

    @JvmStatic
    public static final Expression roundToPrecision(Expression expression, Expression expression2) {
        return INSTANCE.roundToPrecision(expression, expression2);
    }

    @JvmStatic
    public static final Expression roundToPrecision(String str, int i) {
        return INSTANCE.roundToPrecision(str, i);
    }

    @JvmStatic
    public static final Expression roundToPrecision(String str, Expression expression) {
        return INSTANCE.roundToPrecision(str, expression);
    }

    @JvmStatic
    public static final Expression split(Expression expression, Blob blob) {
        return INSTANCE.split(expression, blob);
    }

    @JvmStatic
    public static final Expression split(Expression expression, Expression expression2) {
        return INSTANCE.split(expression, expression2);
    }

    @JvmStatic
    public static final Expression split(Expression expression, String str) {
        return INSTANCE.split(expression, str);
    }

    @JvmStatic
    public static final Expression split(String str, Blob blob) {
        return INSTANCE.split(str, blob);
    }

    @JvmStatic
    public static final Expression split(String str, Expression expression) {
        return INSTANCE.split(str, expression);
    }

    @JvmStatic
    public static final Expression split(String str, String str2) {
        return INSTANCE.split(str, str2);
    }

    @JvmStatic
    public static final Expression sqrt(Expression expression) {
        return INSTANCE.sqrt(expression);
    }

    @JvmStatic
    public static final Expression sqrt(String str) {
        return INSTANCE.sqrt(str);
    }

    @JvmStatic
    public static final BooleanExpression startsWith(Expression expression, Expression expression2) {
        return INSTANCE.startsWith(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression startsWith(Expression expression, String str) {
        return INSTANCE.startsWith(expression, str);
    }

    @JvmStatic
    public static final BooleanExpression startsWith(String str, Expression expression) {
        return INSTANCE.startsWith(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression startsWith(String str, String str2) {
        return INSTANCE.startsWith(str, str2);
    }

    @JvmStatic
    public static final Expression stringConcat(Expression expression, Expression... expressionArr) {
        return INSTANCE.stringConcat(expression, expressionArr);
    }

    @JvmStatic
    public static final Expression stringConcat(Expression expression, Object... objArr) {
        return INSTANCE.stringConcat(expression, objArr);
    }

    @JvmStatic
    public static final Expression stringConcat(String str, Expression... expressionArr) {
        return INSTANCE.stringConcat(str, expressionArr);
    }

    @JvmStatic
    public static final Expression stringConcat(String str, Object... objArr) {
        return INSTANCE.stringConcat(str, objArr);
    }

    @JvmStatic
    public static final BooleanExpression stringContains(Expression expression, Expression expression2) {
        return INSTANCE.stringContains(expression, expression2);
    }

    @JvmStatic
    public static final BooleanExpression stringContains(Expression expression, String str) {
        return INSTANCE.stringContains(expression, str);
    }

    @JvmStatic
    public static final BooleanExpression stringContains(String str, Expression expression) {
        return INSTANCE.stringContains(str, expression);
    }

    @JvmStatic
    public static final BooleanExpression stringContains(String str, String str2) {
        return INSTANCE.stringContains(str, str2);
    }

    @JvmStatic
    public static final Expression stringReverse(Expression expression) {
        return INSTANCE.stringReverse(expression);
    }

    @JvmStatic
    public static final Expression stringReverse(String str) {
        return INSTANCE.stringReverse(str);
    }

    @JvmStatic
    public static final Expression substring(Expression expression, Expression expression2, Expression expression3) {
        return INSTANCE.substring(expression, expression2, expression3);
    }

    @JvmStatic
    public static final Expression substring(String str, int i, int i2) {
        return INSTANCE.substring(str, i, i2);
    }

    @JvmStatic
    public static final Expression subtract(Expression expression, Expression expression2) {
        return INSTANCE.subtract(expression, expression2);
    }

    @JvmStatic
    public static final Expression subtract(Expression expression, Number number) {
        return INSTANCE.subtract(expression, number);
    }

    @JvmStatic
    public static final Expression subtract(String str, Expression expression) {
        return INSTANCE.subtract(str, expression);
    }

    @JvmStatic
    public static final Expression subtract(String str, Number number) {
        return INSTANCE.subtract(str, number);
    }

    @JvmStatic
    public static final Expression timestampAdd(Expression expression, Expression expression2, Expression expression3) {
        return INSTANCE.timestampAdd(expression, expression2, expression3);
    }

    @JvmStatic
    public static final Expression timestampAdd(Expression expression, String str, long j) {
        return INSTANCE.timestampAdd(expression, str, j);
    }

    @JvmStatic
    public static final Expression timestampAdd(String str, Expression expression, Expression expression2) {
        return INSTANCE.timestampAdd(str, expression, expression2);
    }

    @JvmStatic
    public static final Expression timestampAdd(String str, String str2, long j) {
        return INSTANCE.timestampAdd(str, str2, j);
    }

    @JvmStatic
    public static final Expression timestampSubtract(Expression expression, Expression expression2, Expression expression3) {
        return INSTANCE.timestampSubtract(expression, expression2, expression3);
    }

    @JvmStatic
    public static final Expression timestampSubtract(Expression expression, String str, long j) {
        return INSTANCE.timestampSubtract(expression, str, j);
    }

    @JvmStatic
    public static final Expression timestampSubtract(String str, Expression expression, Expression expression2) {
        return INSTANCE.timestampSubtract(str, expression, expression2);
    }

    @JvmStatic
    public static final Expression timestampSubtract(String str, String str2, long j) {
        return INSTANCE.timestampSubtract(str, str2, j);
    }

    @JvmStatic
    public static final Expression timestampToUnixMicros(Expression expression) {
        return INSTANCE.timestampToUnixMicros(expression);
    }

    @JvmStatic
    public static final Expression timestampToUnixMicros(String str) {
        return INSTANCE.timestampToUnixMicros(str);
    }

    @JvmStatic
    public static final Expression timestampToUnixMillis(Expression expression) {
        return INSTANCE.timestampToUnixMillis(expression);
    }

    @JvmStatic
    public static final Expression timestampToUnixMillis(String str) {
        return INSTANCE.timestampToUnixMillis(str);
    }

    @JvmStatic
    public static final Expression timestampToUnixSeconds(Expression expression) {
        return INSTANCE.timestampToUnixSeconds(expression);
    }

    @JvmStatic
    public static final Expression timestampToUnixSeconds(String str) {
        return INSTANCE.timestampToUnixSeconds(str);
    }

    @JvmStatic
    public static final Expression timestampTruncate(Expression expression, Expression expression2) {
        return INSTANCE.timestampTruncate(expression, expression2);
    }

    @JvmStatic
    public static final Expression timestampTruncate(Expression expression, Expression expression2, String str) {
        return INSTANCE.timestampTruncate(expression, expression2, str);
    }

    @JvmStatic
    public static final Expression timestampTruncate(Expression expression, String str) {
        return INSTANCE.timestampTruncate(expression, str);
    }

    @JvmStatic
    public static final Expression timestampTruncate(Expression expression, String str, String str2) {
        return INSTANCE.timestampTruncate(expression, str, str2);
    }

    @JvmStatic
    public static final Expression timestampTruncate(String str, Expression expression) {
        return INSTANCE.timestampTruncate(str, expression);
    }

    @JvmStatic
    public static final Expression timestampTruncate(String str, Expression expression, String str2) {
        return INSTANCE.timestampTruncate(str, expression, str2);
    }

    @JvmStatic
    public static final Expression timestampTruncate(String str, String str2) {
        return INSTANCE.timestampTruncate(str, str2);
    }

    @JvmStatic
    public static final Expression timestampTruncate(String str, String str2, String str3) {
        return INSTANCE.timestampTruncate(str, str2, str3);
    }

    @JvmStatic
    public static final Expression toLower(Expression expression) {
        return INSTANCE.toLower(expression);
    }

    @JvmStatic
    public static final Expression toLower(String str) {
        return INSTANCE.toLower(str);
    }

    @JvmStatic
    public static final Expression toUpper(Expression expression) {
        return INSTANCE.toUpper(expression);
    }

    @JvmStatic
    public static final Expression toUpper(String str) {
        return INSTANCE.toUpper(str);
    }

    @JvmStatic
    public static final Expression trim(Expression expression) {
        return INSTANCE.trim(expression);
    }

    @JvmStatic
    public static final Expression trim(String str) {
        return INSTANCE.trim(str);
    }

    @JvmStatic
    public static final Expression trimValue(Expression expression, Expression expression2) {
        return INSTANCE.trimValue(expression, expression2);
    }

    @JvmStatic
    public static final Expression trimValue(String str, String str2) {
        return INSTANCE.trimValue(str, str2);
    }

    @JvmStatic
    public static final Expression type(Expression expression) {
        return INSTANCE.type(expression);
    }

    @JvmStatic
    public static final Expression type(String str) {
        return INSTANCE.type(str);
    }

    @JvmStatic
    public static final Expression unixMicrosToTimestamp(Expression expression) {
        return INSTANCE.unixMicrosToTimestamp(expression);
    }

    @JvmStatic
    public static final Expression unixMicrosToTimestamp(String str) {
        return INSTANCE.unixMicrosToTimestamp(str);
    }

    @JvmStatic
    public static final Expression unixMillisToTimestamp(Expression expression) {
        return INSTANCE.unixMillisToTimestamp(expression);
    }

    @JvmStatic
    public static final Expression unixMillisToTimestamp(String str) {
        return INSTANCE.unixMillisToTimestamp(str);
    }

    @JvmStatic
    public static final Expression unixSecondsToTimestamp(Expression expression) {
        return INSTANCE.unixSecondsToTimestamp(expression);
    }

    @JvmStatic
    public static final Expression unixSecondsToTimestamp(String str) {
        return INSTANCE.unixSecondsToTimestamp(str);
    }

    @JvmStatic
    public static final Expression vector(VectorValue vectorValue) {
        return INSTANCE.vector(vectorValue);
    }

    @JvmStatic
    public static final Expression vector(double[] dArr) {
        return INSTANCE.vector(dArr);
    }

    @JvmStatic
    public static final Expression vectorLength(Expression expression) {
        return INSTANCE.vectorLength(expression);
    }

    @JvmStatic
    public static final Expression vectorLength(String str) {
        return INSTANCE.vectorLength(str);
    }

    @JvmStatic
    public static final BooleanExpression xor(BooleanExpression booleanExpression, BooleanExpression... booleanExpressionArr) {
        return INSTANCE.xor(booleanExpression, booleanExpressionArr);
    }

    public abstract String canonicalId$com_google_firebase_firebase_firestore();

    public abstract Function1<MutableDocument, EvaluateResult> evaluateFunction$com_google_firebase_firebase_firestore(EvaluationContext context);

    public abstract Value toProto$com_google_firebase_firebase_firestore(UserDataReader userDataReader);

    /* JADX INFO: compiled from: expressions.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0010¢\u0006\u0002\b\u000bJ!\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0012J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\r\u0010\u0015\u001a\u00020\u0014H\u0010¢\u0006\u0002\b\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001d"}, d2 = {"Lcom/google/firebase/firestore/pipeline/Expression$Constant;", "Lcom/google/firebase/firestore/pipeline/Expression;", Values.VECTOR_MAP_VECTORS_KEY, "Lcom/google/firestore/v1/Value;", "<init>", "(Lcom/google/firestore/v1/Value;)V", "getValue", "()Lcom/google/firestore/v1/Value;", "toProto", "userDataReader", "Lcom/google/firebase/firestore/UserDataReader;", "toProto$com_google_firebase_firebase_firestore", "evaluateFunction", "Lkotlin/Function1;", "Lcom/google/firebase/firestore/model/MutableDocument;", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluateResultValue;", "context", "Lcom/google/firebase/firestore/pipeline/evaluation/EvaluationContext;", "evaluateFunction$com_google_firebase_firebase_firestore", "toString", "", "canonicalId", "canonicalId$com_google_firebase_firebase_firestore", "equals", "", "other", "", "hashCode", "", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Constant extends Expression {
        private final Value value;

        public Constant(Value value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this.value = value;
        }

        public final Value getValue() {
            return this.value;
        }

        @Override // com.google.firebase.firestore.pipeline.Expression
        public Value toProto$com_google_firebase_firebase_firestore(UserDataReader userDataReader) {
            Intrinsics.checkNotNullParameter(userDataReader, "userDataReader");
            return this.value;
        }

        @Override // com.google.firebase.firestore.pipeline.Expression
        public Function1<MutableDocument, EvaluateResultValue> evaluateFunction$com_google_firebase_firebase_firestore(EvaluationContext context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new Function1() { // from class: com.google.firebase.firestore.pipeline.Expression$Constant$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Expression.Constant.evaluateFunction$lambda$0(this.f$0, (MutableDocument) obj);
                }
            };
        }

        static final EvaluateResultValue evaluateFunction$lambda$0(Constant constant, MutableDocument mutableDocument) {
            Intrinsics.checkNotNullParameter(mutableDocument, "<unused var>");
            return new EvaluateResultValue(constant.value);
        }

        public String toString() {
            return canonicalId$com_google_firebase_firebase_firestore();
        }

        @Override // com.google.firebase.firestore.pipeline.Expression
        public String canonicalId$com_google_firebase_firebase_firestore() {
            return "cst(" + Values.canonicalId(this.value) + ')';
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Constant) {
                return Intrinsics.areEqual(this.value, ((Constant) other).value);
            }
            return false;
        }

        public int hashCode() {
            return this.value.hashCode();
        }
    }

    /* JADX INFO: compiled from: expressions.kt */
    @Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\u0010\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b!\n\u0002\u0010 \n\u0002\b8\n\u0002\u0010$\n\u0002\b!\n\u0002\u0010\t\n\u0002\b9\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0002\b\u0007J\u0012\u0010\b\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0002J+\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00012\u0014\u0010\t\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u00050\nH\u0082\bJ#\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0002¢\u0006\u0002\u0010\u000fJ'\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\f2\u000e\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\fH\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0014H\u0007J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0015H\u0007J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0016H\u0007J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0017H\u0007J\u0010\u0010\u0013\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u0019H\u0007J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u001aH\u0007J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u001bH\u0007J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u001cH\u0007J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u001fH\u0007J\b\u0010 \u001a\u00020\u0005H\u0007J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"H\u0007J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u001fH\u0007J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0014H\u0007J\u0010\u0010#\u001a\u00020$2\u0006\u0010&\u001a\u00020'H\u0007J)\u0010(\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00142\u0012\u0010)\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\f\"\u00020\u0005H\u0007¢\u0006\u0002\u0010*J)\u0010+\u001a\u00020\u00182\u0006\u0010,\u001a\u00020\u00182\u0012\u0010-\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\f\"\u00020\u0018H\u0007¢\u0006\u0002\u0010.J)\u0010/\u001a\u00020\u00182\u0006\u0010,\u001a\u00020\u00182\u0012\u0010-\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\f\"\u00020\u0018H\u0007¢\u0006\u0002\u0010.J)\u00100\u001a\u00020\u00182\u0006\u0010,\u001a\u00020\u00182\u0012\u0010-\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\f\"\u00020\u0018H\u0007¢\u0006\u0002\u0010.J\u0010\u00101\u001a\u00020\u00182\u0006\u0010,\u001a\u00020\u0018H\u0007J\u0018\u00102\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u0005H\u0007J\u0018\u00102\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u001bH\u0007J\u0018\u00102\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u0005H\u0007J\u0018\u00102\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u001bH\u0007J\u0018\u00106\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u0005H\u0007J\u0018\u00106\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u001bH\u0007J\u0018\u00106\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u0005H\u0007J\u0018\u00106\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u001bH\u0007J\u0018\u00107\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u0005H\u0007J\u0018\u00107\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u001bH\u0007J\u0018\u00107\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u0005H\u0007J\u0018\u00107\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u001bH\u0007J\u0010\u00108\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u0005H\u0007J\u0010\u00108\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u0014H\u0007J\u0018\u00109\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u0005H\u0007J\u0018\u00109\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00052\u0006\u0010;\u001a\u00020<H\u0007J\u0018\u00109\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00142\u0006\u0010:\u001a\u00020\u0005H\u0007J\u0018\u00109\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00142\u0006\u0010;\u001a\u00020<H\u0007J\u0018\u0010=\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u0005H\u0007J\u0018\u0010=\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00052\u0006\u0010;\u001a\u00020<H\u0007J\u0018\u0010=\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00142\u0006\u0010:\u001a\u00020\u0005H\u0007J\u0018\u0010=\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00142\u0006\u0010;\u001a\u00020<H\u0007J\u0010\u0010>\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u0005H\u0007J\u0010\u0010>\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0014H\u0007J\u0018\u0010A\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u00052\u0006\u0010B\u001a\u00020<H\u0007J\u0018\u0010A\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u00142\u0006\u0010B\u001a\u00020<H\u0007J\u0018\u0010A\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u00052\u0006\u0010B\u001a\u00020\u0005H\u0007J\u0018\u0010A\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u00142\u0006\u0010B\u001a\u00020\u0005H\u0007J\u0010\u0010C\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u0005H\u0007J\u0010\u0010C\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0014H\u0007J\u0010\u0010D\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u0005H\u0007J\u0010\u0010D\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0014H\u0007J\u0018\u0010E\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u0015H\u0007J\u0018\u0010E\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u00142\u0006\u0010F\u001a\u00020\u0015H\u0007J\u0018\u0010E\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u0005H\u0007J\u0018\u0010E\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u00142\u0006\u0010F\u001a\u00020\u0005H\u0007J\u0010\u0010G\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u0005H\u0007J\u0010\u0010G\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0014H\u0007J\u0010\u0010H\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u0005H\u0007J\u0010\u0010H\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0014H\u0007J\u0010\u0010I\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u0005H\u0007J\u0010\u0010I\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0014H\u0007J\u0018\u0010J\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u00052\u0006\u0010K\u001a\u00020\u0015H\u0007J\u0018\u0010J\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u00142\u0006\u0010K\u001a\u00020\u0015H\u0007J\u0018\u0010J\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u00052\u0006\u0010K\u001a\u00020\u0005H\u0007J\u0018\u0010J\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u00142\u0006\u0010K\u001a\u00020\u0005H\u0007J\u0010\u0010L\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u0005H\u0007J\u0010\u0010L\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0014H\u0007J\u0010\u0010M\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u0005H\u0007J\u0010\u0010M\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0014H\u0007J\u0018\u0010N\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u0005H\u0007J\u0018\u0010N\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u0015H\u0007J\u0018\u0010N\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\u00142\u0006\u0010P\u001a\u00020\u0005H\u0007J\u0018\u0010N\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\u00142\u0006\u0010P\u001a\u00020\u0015H\u0007J\u0018\u0010R\u001a\u00020\u00052\u0006\u0010S\u001a\u00020\u00052\u0006\u0010T\u001a\u00020\u0005H\u0007J\u0018\u0010R\u001a\u00020\u00052\u0006\u0010S\u001a\u00020\u00052\u0006\u0010T\u001a\u00020\u0015H\u0007J\u0018\u0010R\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\u00142\u0006\u0010T\u001a\u00020\u0005H\u0007J\u0018\u0010R\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\u00142\u0006\u0010T\u001a\u00020\u0015H\u0007J\u0018\u0010U\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u0005H\u0007J\u0018\u0010U\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u0015H\u0007J\u0018\u0010U\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\u00142\u0006\u0010P\u001a\u00020\u0005H\u0007J\u0018\u0010U\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\u00142\u0006\u0010P\u001a\u00020\u0015H\u0007J\u0018\u0010V\u001a\u00020\u00052\u0006\u0010W\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\u0005H\u0007J\u0018\u0010V\u001a\u00020\u00052\u0006\u0010W\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\u0015H\u0007J\u0018\u0010V\u001a\u00020\u00052\u0006\u0010Y\u001a\u00020\u00142\u0006\u0010X\u001a\u00020\u0005H\u0007J\u0018\u0010V\u001a\u00020\u00052\u0006\u0010Y\u001a\u00020\u00142\u0006\u0010X\u001a\u00020\u0015H\u0007J\u0018\u0010Z\u001a\u00020\u00052\u0006\u0010W\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\u0005H\u0007J\u0018\u0010Z\u001a\u00020\u00052\u0006\u0010W\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\u0015H\u0007J\u0018\u0010Z\u001a\u00020\u00052\u0006\u0010Y\u001a\u00020\u00142\u0006\u0010X\u001a\u00020\u0005H\u0007J\u0018\u0010Z\u001a\u00020\u00052\u0006\u0010Y\u001a\u00020\u00142\u0006\u0010X\u001a\u00020\u0015H\u0007J\u001e\u0010[\u001a\u00020\u00182\u0006\u0010\\\u001a\u00020\u00052\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00010^H\u0007J\u0018\u0010[\u001a\u00020\u00182\u0006\u0010\\\u001a\u00020\u00052\u0006\u0010_\u001a\u00020\u0005H\u0007J\u001e\u0010[\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00010^H\u0007J\u0018\u0010[\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010_\u001a\u00020\u0005H\u0007J\u001e\u0010a\u001a\u00020\u00182\u0006\u0010\\\u001a\u00020\u00052\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00010^H\u0007J\u0018\u0010a\u001a\u00020\u00182\u0006\u0010\\\u001a\u00020\u00052\u0006\u0010_\u001a\u00020\u0005H\u0007J\u001e\u0010a\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00010^H\u0007J\u0018\u0010a\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010_\u001a\u00020\u0005H\u0007J\u0010\u0010b\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u0005H\u0007J\u0010\u0010b\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0015\u0010c\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0005H\u0001¢\u0006\u0002\bdJ\u0015\u0010c\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u0014H\u0001¢\u0006\u0002\bdJ\u0015\u0010e\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0005H\u0001¢\u0006\u0002\bfJ\u0015\u0010e\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u0014H\u0001¢\u0006\u0002\bfJ\u0015\u0010g\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0005H\u0001¢\u0006\u0002\bhJ\u0015\u0010g\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u0014H\u0001¢\u0006\u0002\bhJ\u0015\u0010i\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0005H\u0001¢\u0006\u0002\bjJ\u0015\u0010i\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u0014H\u0001¢\u0006\u0002\bjJ\u0010\u0010k\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0007J\u0010\u0010k\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0010\u0010l\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0007J\u0010\u0010l\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0010\u0010m\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0007J\u0010\u0010m\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0010\u0010n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0007J\u0010\u0010n\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0018\u0010o\u001a\u00020\u00182\u0006\u0010p\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0005H\u0007J\u0018\u0010r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010s\u001a\u00020\u0005H\u0007J\u0018\u0010r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010s\u001a\u00020\u0014H\u0007J\u0018\u0010r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010s\u001a\u00020\u001cH\u0007J\u0018\u0010r\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0006\u0010s\u001a\u00020\u0005H\u0007J\u0018\u0010r\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0006\u0010s\u001a\u00020\u0014H\u0007J\u0018\u0010r\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0006\u0010s\u001a\u00020\u001cH\u0007J\u0018\u0010t\u001a\u00020\u00052\u0006\u0010_\u001a\u00020\u00052\u0006\u0010s\u001a\u00020\u0014H\u0007J\u0018\u0010t\u001a\u00020\u00052\u0006\u0010_\u001a\u00020\u00052\u0006\u0010u\u001a\u00020\u0005H\u0007J\u0018\u0010t\u001a\u00020\u00052\u0006\u0010v\u001a\u00020\u00142\u0006\u0010s\u001a\u00020\u0014H\u0007J\u0018\u0010t\u001a\u00020\u00052\u0006\u0010v\u001a\u00020\u00142\u0006\u0010u\u001a\u00020\u0005H\u0007J\u0018\u0010o\u001a\u00020\u00182\u0006\u0010p\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0014H\u0007J\u0018\u0010o\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010q\u001a\u00020\u0005H\u0007J\u0018\u0010o\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010q\u001a\u00020\u0014H\u0007J\r\u0010w\u001a\u00020\u0005H\u0001¢\u0006\u0002\bxJ\u0018\u0010y\u001a\u00020\u00182\u0006\u0010p\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0005H\u0007J\u0018\u0010y\u001a\u00020\u00182\u0006\u0010p\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0014H\u0007J\u0018\u0010y\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010q\u001a\u00020\u0005H\u0007J\u0018\u0010y\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010q\u001a\u00020\u0014H\u0007J\u0018\u0010z\u001a\u00020\u00182\u0006\u0010p\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0005H\u0007J\u0018\u0010z\u001a\u00020\u00182\u0006\u0010p\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u0014H\u0007J\u0018\u0010z\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010q\u001a\u00020\u0005H\u0007J\u0018\u0010z\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010q\u001a\u00020\u0014H\u0007J)\u0010{\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u00052\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0002\u0010|J)\u0010{\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0002\u0010}J)\u0010~\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u00052\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0002\u0010|J)\u0010~\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0002\u0010}J\u0010\u0010\u007f\u001a\u00020\u00052\u0006\u0010p\u001a\u00020\u0005H\u0007J\u0010\u0010\u007f\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u001a\u0010\u0080\u0001\u001a\u00020\u00182\u0006\u0010p\u001a\u00020\u00052\u0007\u0010\u0081\u0001\u001a\u00020\u0005H\u0007J\u001a\u0010\u0080\u0001\u001a\u00020\u00182\u0006\u0010p\u001a\u00020\u00052\u0007\u0010\u0081\u0001\u001a\u00020\u0014H\u0007J\u001a\u0010\u0080\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0007\u0010\u0081\u0001\u001a\u00020\u0005H\u0007J\u001a\u0010\u0080\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0007\u0010\u0081\u0001\u001a\u00020\u0014H\u0007J\u001b\u0010\u0082\u0001\u001a\u00020\u00182\u0007\u0010\u0083\u0001\u001a\u00020\u00052\u0007\u0010\u0084\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010\u0082\u0001\u001a\u00020\u00182\u0007\u0010\u0083\u0001\u001a\u00020\u00052\u0007\u0010\u0084\u0001\u001a\u00020\u0014H\u0007J\u001a\u0010\u0082\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0007\u0010\u0084\u0001\u001a\u00020\u0005H\u0007J\u001a\u0010\u0082\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0007\u0010\u0084\u0001\u001a\u00020\u0014H\u0007J\u001b\u0010\u0085\u0001\u001a\u00020\u00182\u0007\u0010\u0083\u0001\u001a\u00020\u00052\u0007\u0010\u0086\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010\u0085\u0001\u001a\u00020\u00182\u0007\u0010\u0083\u0001\u001a\u00020\u00052\u0007\u0010\u0086\u0001\u001a\u00020\u0014H\u0007J\u001a\u0010\u0085\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0007\u0010\u0086\u0001\u001a\u00020\u0005H\u0007J\u001a\u0010\u0085\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0007\u0010\u0086\u0001\u001a\u00020\u0014H\u0007J\u0012\u0010\u0087\u0001\u001a\u00020\u00052\u0007\u0010\u0088\u0001\u001a\u00020\u0005H\u0007J\u0011\u0010\u0087\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\"\u0010\u0081\u0001\u001a\u00020\u00052\u0006\u0010p\u001a\u00020\u00052\u0007\u0010\u0089\u0001\u001a\u00020\u00052\u0006\u0010l\u001a\u00020\u0005H\u0007J\"\u0010\u0081\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010\u0089\u0001\u001a\u00020<2\u0006\u0010l\u001a\u00020<H\u0007J\u0011\u0010\u008a\u0001\u001a\u00020\u00052\u0006\u0010p\u001a\u00020\u0005H\u0007J\u0011\u0010\u008a\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0011\u0010\u008b\u0001\u001a\u00020\u00052\u0006\u0010p\u001a\u00020\u0005H\u0007J\u0011\u0010\u008b\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0011\u0010\u008c\u0001\u001a\u00020\u00052\u0006\u0010p\u001a\u00020\u0005H\u0007J\u0011\u0010\u008c\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u001a\u0010\u008d\u0001\u001a\u00020\u00052\u0006\u0010p\u001a\u00020\u00052\u0007\u0010\u008e\u0001\u001a\u00020\u0005H\u0007J\u001a\u0010\u008d\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010\u008e\u0001\u001a\u00020\u0014H\u0007J-\u0010\u008f\u0001\u001a\u00020\u00052\u0007\u0010\u0090\u0001\u001a\u00020\u00052\u0013\u0010\u0091\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\f\"\u00020\u0005H\u0007¢\u0006\u0003\u0010\u0092\u0001J,\u0010\u008f\u0001\u001a\u00020\u00052\u0007\u0010\u0090\u0001\u001a\u00020\u00052\u0013\u0010\u0091\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0002\u0010|J+\u0010\u008f\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0013\u0010\u0091\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\f\"\u00020\u0005H\u0007¢\u0006\u0002\u0010*J+\u0010\u008f\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0013\u0010\u0091\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0002\u0010}J#\u0010\u0093\u0001\u001a\u00020\u00052\u000f\u0010\u0094\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\fH\u0000¢\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001J\u001f\u0010\u0093\u0001\u001a\u00020\u00052\u0014\u0010\u0094\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0097\u0001H\u0007J\u001b\u0010\u0098\u0001\u001a\u00020\u00052\u0007\u0010\u0099\u0001\u001a\u00020\u00052\u0007\u0010\u009a\u0001\u001a\u00020\u0014H\u0007J\u001a\u0010\u0098\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010\u009a\u0001\u001a\u00020\u0014H\u0007J\u001b\u0010\u0098\u0001\u001a\u00020\u00052\u0007\u0010\u0099\u0001\u001a\u00020\u00052\u0007\u0010\u009b\u0001\u001a\u00020\u0005H\u0007J\u001a\u0010\u0098\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010\u009b\u0001\u001a\u00020\u0005H\u0007J6\u0010\u009c\u0001\u001a\u00020\u00052\u0007\u0010\u009d\u0001\u001a\u00020\u00052\u0007\u0010\u009e\u0001\u001a\u00020\u00052\u0013\u0010\u009f\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\f\"\u00020\u0005H\u0007¢\u0006\u0003\u0010 \u0001J6\u0010\u009c\u0001\u001a\u00020\u00052\u0007\u0010¡\u0001\u001a\u00020\u00142\u0007\u0010\u009e\u0001\u001a\u00020\u00052\u0013\u0010\u009f\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\f\"\u00020\u0005H\u0007¢\u0006\u0003\u0010¢\u0001J\u001b\u0010£\u0001\u001a\u00020\u00052\u0007\u0010¤\u0001\u001a\u00020\u00052\u0007\u0010\u009a\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010£\u0001\u001a\u00020\u00052\u0007\u0010¥\u0001\u001a\u00020\u00142\u0007\u0010\u009a\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010£\u0001\u001a\u00020\u00052\u0007\u0010¤\u0001\u001a\u00020\u00052\u0007\u0010\u009a\u0001\u001a\u00020\u0014H\u0007J\u001b\u0010£\u0001\u001a\u00020\u00052\u0007\u0010¥\u0001\u001a\u00020\u00142\u0007\u0010\u009a\u0001\u001a\u00020\u0014H\u0007J\u001b\u0010¦\u0001\u001a\u00020\u00052\u0007\u0010§\u0001\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010¦\u0001\u001a\u00020\u00052\u0007\u0010§\u0001\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\"H\u0007J\u001b\u0010¦\u0001\u001a\u00020\u00052\u0007\u0010§\u0001\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\u001fH\u0007J\u001a\u0010¦\u0001\u001a\u00020\u00052\u0007\u0010©\u0001\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0005H\u0007J\u001a\u0010¦\u0001\u001a\u00020\u00052\u0007\u0010©\u0001\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"H\u0007J\u001a\u0010¦\u0001\u001a\u00020\u00052\u0007\u0010©\u0001\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u001fH\u0007J\u001b\u0010ª\u0001\u001a\u00020\u00052\u0007\u0010§\u0001\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010ª\u0001\u001a\u00020\u00052\u0007\u0010§\u0001\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\"H\u0007J\u001b\u0010ª\u0001\u001a\u00020\u00052\u0007\u0010§\u0001\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\u001fH\u0007J\u001a\u0010ª\u0001\u001a\u00020\u00052\u0007\u0010©\u0001\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0005H\u0007J\u001a\u0010ª\u0001\u001a\u00020\u00052\u0007\u0010©\u0001\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"H\u0007J\u001a\u0010ª\u0001\u001a\u00020\u00052\u0007\u0010©\u0001\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u001fH\u0007J\u001b\u0010«\u0001\u001a\u00020\u00052\u0007\u0010§\u0001\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010«\u0001\u001a\u00020\u00052\u0007\u0010§\u0001\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\"H\u0007J\u001b\u0010«\u0001\u001a\u00020\u00052\u0007\u0010§\u0001\u001a\u00020\u00052\u0007\u0010¨\u0001\u001a\u00020\u001fH\u0007J\u001a\u0010«\u0001\u001a\u00020\u00052\u0007\u0010©\u0001\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0005H\u0007J\u001a\u0010«\u0001\u001a\u00020\u00052\u0007\u0010©\u0001\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"H\u0007J\u001a\u0010«\u0001\u001a\u00020\u00052\u0007\u0010©\u0001\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u001fH\u0007J\u0012\u0010¬\u0001\u001a\u00020\u00052\u0007\u0010\u00ad\u0001\u001a\u00020\u0005H\u0007J\u0011\u0010¬\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\t\u0010®\u0001\u001a\u00020\u0005H\u0007J\u0011\u0010¯\u0001\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0007J\u0011\u0010¯\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0011\u0010°\u0001\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0007J\u0011\u0010°\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0011\u0010±\u0001\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0007J\u0011\u0010±\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0011\u0010²\u0001\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0007J\u0011\u0010²\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0011\u0010³\u0001\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0007J\u0011\u0010³\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0011\u0010´\u0001\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0007J\u0011\u0010´\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u0014H\u0007J$\u0010µ\u0001\u001a\u00020\u00052\u0007\u0010¶\u0001\u001a\u00020\u00052\u0007\u0010·\u0001\u001a\u00020\u00052\u0007\u0010¸\u0001\u001a\u00020\u0005H\u0007J%\u0010µ\u0001\u001a\u00020\u00052\u0007\u0010¶\u0001\u001a\u00020\u00052\u0007\u0010·\u0001\u001a\u00020\u00142\b\u0010¸\u0001\u001a\u00030¹\u0001H\u0007J#\u0010µ\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010·\u0001\u001a\u00020\u00052\u0007\u0010¸\u0001\u001a\u00020\u0005H\u0007J$\u0010µ\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010·\u0001\u001a\u00020\u00142\b\u0010¸\u0001\u001a\u00030¹\u0001H\u0007J$\u0010º\u0001\u001a\u00020\u00052\u0007\u0010¶\u0001\u001a\u00020\u00052\u0007\u0010·\u0001\u001a\u00020\u00052\u0007\u0010¸\u0001\u001a\u00020\u0005H\u0007J%\u0010º\u0001\u001a\u00020\u00052\u0007\u0010¶\u0001\u001a\u00020\u00052\u0007\u0010·\u0001\u001a\u00020\u00142\b\u0010¸\u0001\u001a\u00030¹\u0001H\u0007J#\u0010º\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010·\u0001\u001a\u00020\u00052\u0007\u0010¸\u0001\u001a\u00020\u0005H\u0007J$\u0010º\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010·\u0001\u001a\u00020\u00142\b\u0010¸\u0001\u001a\u00030¹\u0001H\u0007J\u001b\u0010»\u0001\u001a\u00020\u00052\u0007\u0010¶\u0001\u001a\u00020\u00052\u0007\u0010¼\u0001\u001a\u00020\u0014H\u0007J\u001b\u0010»\u0001\u001a\u00020\u00052\u0007\u0010¶\u0001\u001a\u00020\u00052\u0007\u0010¼\u0001\u001a\u00020\u0005H\u0007J\u001a\u0010»\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010¼\u0001\u001a\u00020\u0014H\u0007J\u001a\u0010»\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010¼\u0001\u001a\u00020\u0005H\u0007J$\u0010»\u0001\u001a\u00020\u00052\u0007\u0010¶\u0001\u001a\u00020\u00052\u0007\u0010¼\u0001\u001a\u00020\u00142\u0007\u0010½\u0001\u001a\u00020\u0014H\u0007J$\u0010»\u0001\u001a\u00020\u00052\u0007\u0010¶\u0001\u001a\u00020\u00052\u0007\u0010¼\u0001\u001a\u00020\u00052\u0007\u0010½\u0001\u001a\u00020\u0014H\u0007J#\u0010»\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010¼\u0001\u001a\u00020\u00142\u0007\u0010½\u0001\u001a\u00020\u0014H\u0007J#\u0010»\u0001\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00142\u0007\u0010¼\u0001\u001a\u00020\u00052\u0007\u0010½\u0001\u001a\u00020\u0014H\u0007J\u001b\u0010¾\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010¾\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0001H\u0007J\u0019\u0010¾\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\u0005H\u0007J\u0019\u0010¾\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u0001H\u0007J\u001b\u0010Á\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010Á\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0001H\u0007J\u0019\u0010Á\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\u0005H\u0007J\u0019\u0010Á\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u0001H\u0007J\u001b\u0010Â\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010Â\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0001H\u0007J\u0019\u0010Â\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\u0005H\u0007J\u0019\u0010Â\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u0001H\u0007J\u001b\u0010Ã\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010Ã\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0001H\u0007J\u0019\u0010Ã\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\u0005H\u0007J\u0019\u0010Ã\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u0001H\u0007J\u001b\u0010Ä\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010Ä\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0001H\u0007J\u0019\u0010Ä\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\u0005H\u0007J\u0019\u0010Ä\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u0001H\u0007J\u001b\u0010Å\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010Å\u0001\u001a\u00020\u00182\u0007\u0010¿\u0001\u001a\u00020\u00052\u0007\u0010À\u0001\u001a\u00020\u0001H\u0007J\u0019\u0010Å\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\u0005H\u0007J\u0019\u0010Å\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u0001H\u0007J3\u0010Æ\u0001\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00052\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0003\u0010Ç\u0001J3\u0010Æ\u0001\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00012\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0003\u0010È\u0001J3\u0010Æ\u0001\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00142\u0006\u0010P\u001a\u00020\u00052\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0003\u0010É\u0001J3\u0010Æ\u0001\u001a\u00020\u00052\u0006\u0010O\u001a\u00020\u00142\u0006\u0010P\u001a\u00020\u00012\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0003\u0010Ê\u0001J(\u0010Ë\u0001\u001a\u00020\u00052\u0017\u0010\u0094\u0001\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0003\u0010Ì\u0001J\u001a\u0010Ë\u0001\u001a\u00020\u00052\u000f\u0010\u0094\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010^H\u0007J6\u0010Í\u0001\u001a\u00020\u00052\u0007\u0010Î\u0001\u001a\u00020\u00052\u0007\u0010Ï\u0001\u001a\u00020\u00052\u0013\u0010Ð\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0003\u0010Ç\u0001J6\u0010Í\u0001\u001a\u00020\u00052\u0007\u0010Î\u0001\u001a\u00020\u00052\u0007\u0010Ï\u0001\u001a\u00020\u00012\u0013\u0010Ð\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0003\u0010È\u0001J6\u0010Í\u0001\u001a\u00020\u00052\u0007\u0010Ñ\u0001\u001a\u00020\u00142\u0007\u0010Ï\u0001\u001a\u00020\u00052\u0013\u0010Ð\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0003\u0010É\u0001J6\u0010Í\u0001\u001a\u00020\u00052\u0007\u0010Ñ\u0001\u001a\u00020\u00142\u0007\u0010Ï\u0001\u001a\u00020\u00012\u0013\u0010Ð\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0003\u0010Ê\u0001J\u0012\u0010Ò\u0001\u001a\u00020\u00052\u0007\u0010Ë\u0001\u001a\u00020\u0005H\u0007J\u0011\u0010Ò\u0001\u001a\u00020\u00052\u0006\u0010v\u001a\u00020\u0014H\u0007J\u0012\u0010Ó\u0001\u001a\u00020\u00052\u0007\u0010Ë\u0001\u001a\u00020\u0005H\u0007J\u0011\u0010Ó\u0001\u001a\u00020\u00052\u0006\u0010v\u001a\u00020\u0014H\u0007J\u001b\u0010Ô\u0001\u001a\u00020\u00182\u0007\u0010Ë\u0001\u001a\u00020\u00052\u0007\u0010Õ\u0001\u001a\u00020\u0005H\u0007J\u001a\u0010Ô\u0001\u001a\u00020\u00182\u0006\u0010v\u001a\u00020\u00142\u0007\u0010Õ\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010Ô\u0001\u001a\u00020\u00182\u0007\u0010Ë\u0001\u001a\u00020\u00052\u0007\u0010Õ\u0001\u001a\u00020\u0001H\u0007J\u001a\u0010Ô\u0001\u001a\u00020\u00182\u0006\u0010v\u001a\u00020\u00142\u0007\u0010Õ\u0001\u001a\u00020\u0001H\u0007J \u0010Ö\u0001\u001a\u00020\u00182\u0007\u0010Ë\u0001\u001a\u00020\u00052\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00010^H\u0007J\u001a\u0010Ö\u0001\u001a\u00020\u00182\u0007\u0010Ë\u0001\u001a\u00020\u00052\u0006\u0010_\u001a\u00020\u0005H\u0007J\u001f\u0010Ö\u0001\u001a\u00020\u00182\u0006\u0010v\u001a\u00020\u00142\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00010^H\u0007J\u0019\u0010Ö\u0001\u001a\u00020\u00182\u0006\u0010v\u001a\u00020\u00142\u0006\u0010_\u001a\u00020\u0005H\u0007J \u0010×\u0001\u001a\u00020\u00182\u0007\u0010Ë\u0001\u001a\u00020\u00052\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00010^H\u0007J\u001a\u0010×\u0001\u001a\u00020\u00182\u0007\u0010Ë\u0001\u001a\u00020\u00052\u0006\u0010_\u001a\u00020\u0005H\u0007J\u001f\u0010×\u0001\u001a\u00020\u00182\u0006\u0010v\u001a\u00020\u00142\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00010^H\u0007J\u0019\u0010×\u0001\u001a\u00020\u00182\u0006\u0010v\u001a\u00020\u00142\u0006\u0010_\u001a\u00020\u0005H\u0007J\u0012\u0010Ø\u0001\u001a\u00020\u00052\u0007\u0010Ë\u0001\u001a\u00020\u0005H\u0007J\u0011\u0010Ø\u0001\u001a\u00020\u00052\u0006\u0010v\u001a\u00020\u0014H\u0007J\u001b\u0010Ù\u0001\u001a\u00020\u00052\u0007\u0010Ë\u0001\u001a\u00020\u00052\u0007\u0010Ú\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010Ù\u0001\u001a\u00020\u00052\u0007\u0010Ë\u0001\u001a\u00020\u00052\u0007\u0010Ú\u0001\u001a\u00020<H\u0007J\u001a\u0010Ù\u0001\u001a\u00020\u00052\u0006\u0010v\u001a\u00020\u00142\u0007\u0010Ú\u0001\u001a\u00020\u0005H\u0007J\u001a\u0010Ù\u0001\u001a\u00020\u00052\u0006\u0010v\u001a\u00020\u00142\u0007\u0010Ú\u0001\u001a\u00020<H\u0007J#\u0010Û\u0001\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u00182\u0007\u0010Ü\u0001\u001a\u00020\u00052\u0007\u0010Ý\u0001\u001a\u00020\u0005H\u0007J#\u0010Û\u0001\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u00182\u0007\u0010Þ\u0001\u001a\u00020\u00012\u0007\u0010ß\u0001\u001a\u00020\u0001H\u0007J\u0011\u0010à\u0001\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u0005H\u0007J\u0011\u0010à\u0001\u001a\u00020\u00182\u0006\u0010`\u001a\u00020\u0014H\u0007J\u0018\u0010á\u0001\u001a\u00020\u00052\u0007\u0010â\u0001\u001a\u00020\u0014H\u0001¢\u0006\u0003\bã\u0001J\u001b\u0010ä\u0001\u001a\u00020\u00052\u0007\u0010å\u0001\u001a\u00020\u00052\u0007\u0010æ\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010ä\u0001\u001a\u00020\u00182\u0007\u0010å\u0001\u001a\u00020\u00182\u0007\u0010æ\u0001\u001a\u00020\u0018H\u0007J\u0011\u0010ç\u0001\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0005H\u0007J\u001b\u0010ä\u0001\u001a\u00020\u00052\u0007\u0010å\u0001\u001a\u00020\u00052\u0007\u0010è\u0001\u001a\u00020\u0001H\u0007J\u001b\u0010é\u0001\u001a\u00020\u00052\u0007\u0010ê\u0001\u001a\u00020\u00052\u0007\u0010Ý\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010é\u0001\u001a\u00020\u00052\u0007\u0010ê\u0001\u001a\u00020\u00052\u0007\u0010ß\u0001\u001a\u00020\u0001H\u0007J\u001b\u0010é\u0001\u001a\u00020\u00052\u0007\u0010ë\u0001\u001a\u00020\u00142\u0007\u0010Ý\u0001\u001a\u00020\u0005H\u0007J\u001b\u0010é\u0001\u001a\u00020\u00052\u0007\u0010ë\u0001\u001a\u00020\u00142\u0007\u0010ß\u0001\u001a\u00020\u0001H\u0007J\u0012\u0010ì\u0001\u001a\u00020\u00052\u0007\u0010í\u0001\u001a\u00020\u0005H\u0007J\u0012\u0010ì\u0001\u001a\u00020\u00052\u0007\u0010î\u0001\u001a\u00020\u0014H\u0007J\u0012\u0010ï\u0001\u001a\u00020\u00052\u0007\u0010ð\u0001\u001a\u00020\u0005H\u0007J\u0012\u0010ï\u0001\u001a\u00020\u00052\u0007\u0010ð\u0001\u001a\u00020\u0014H\u0007J\u0012\u0010ï\u0001\u001a\u00020\u00052\u0007\u0010ñ\u0001\u001a\u00020\u001eH\u0007R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006ò\u0001"}, d2 = {"Lcom/google/firebase/firestore/pipeline/Expression$Companion;", "", "<init>", "()V", "toExprOrConstant", "Lcom/google/firebase/firestore/pipeline/Expression;", Values.VECTOR_MAP_VECTORS_KEY, "toExprOrConstant$com_google_firebase_firebase_firestore", "pojoToExprOrConstant", "toExpr", "Lkotlin/Function1;", "toArrayOfExprOrConstant", "", "others", "", "(Ljava/lang/Iterable;)[Lcom/google/firebase/firestore/pipeline/Expression;", "toArrayOfExprOrConstant$com_google_firebase_firebase_firestore", "([Ljava/lang/Object;)[Lcom/google/firebase/firestore/pipeline/Expression;", "NULL", "constant", "", "", "Ljava/util/Date;", "Lcom/google/firebase/Timestamp;", "Lcom/google/firebase/firestore/pipeline/BooleanExpression;", "", "Lcom/google/firebase/firestore/GeoPoint;", "", "Lcom/google/firebase/firestore/Blob;", "ref", "Lcom/google/firebase/firestore/DocumentReference;", "Lcom/google/firebase/firestore/VectorValue;", "nullValue", "vector", "", "field", "Lcom/google/firebase/firestore/pipeline/Field;", "name", "fieldPath", "Lcom/google/firebase/firestore/FieldPath;", "rawFunction", "expr", "(Ljava/lang/String;[Lcom/google/firebase/firestore/pipeline/Expression;)Lcom/google/firebase/firestore/pipeline/Expression;", "and", "condition", "conditions", "(Lcom/google/firebase/firestore/pipeline/BooleanExpression;[Lcom/google/firebase/firestore/pipeline/BooleanExpression;)Lcom/google/firebase/firestore/pipeline/BooleanExpression;", "or", "xor", "not", "bitAnd", "bits", "bitsOther", "bitsFieldName", "bitOr", "bitXor", "bitNot", "bitLeftShift", "numberExpr", "number", "", "bitRightShift", "round", "numericExpr", "numericField", "roundToPrecision", "decimalPlace", "ceil", "floor", "pow", "exponent", "abs", "exp", "ln", "log", "base", "log10", "sqrt", "add", "first", "second", "numericFieldName", "subtract", "minuend", "subtrahend", "multiply", "divide", "dividend", "divisor", "dividendFieldName", "mod", "equalAny", "expression", "values", "", "arrayExpression", "fieldName", "notEqualAny", "isAbsent", "isNan", "isNan$com_google_firebase_firebase_firestore", "isNotNan", "isNotNan$com_google_firebase_firebase_firestore", "isNull", "isNull$com_google_firebase_firebase_firestore", "isNotNull", "isNotNull$com_google_firebase_firebase_firestore", "type", "length", "charLength", "byteLength", "like", "stringExpression", "pattern", "split", "delimiter", "join", "delimiterExpression", "arrayFieldName", "rand", "rand$com_google_firebase_firebase_firestore", "regexContains", "regexMatch", "logicalMaximum", "(Lcom/google/firebase/firestore/pipeline/Expression;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/Expression;", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/Expression;", "logicalMinimum", "reverse", "stringContains", "substring", "startsWith", "stringExpr", "prefix", "endsWith", "suffix", "stringReverse", "str", "index", "toLower", "toUpper", "trim", "trimValue", "valueToTrim", "stringConcat", "firstString", "otherStrings", "(Lcom/google/firebase/firestore/pipeline/Expression;[Lcom/google/firebase/firestore/pipeline/Expression;)Lcom/google/firebase/firestore/pipeline/Expression;", "map", "elements", "map$com_google_firebase_firebase_firestore", "([Lcom/google/firebase/firestore/pipeline/Expression;)Lcom/google/firebase/firestore/pipeline/Expression;", "", "mapGet", "mapExpression", "key", "keyExpression", "mapMerge", "firstMap", "secondMap", "otherMaps", "(Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/Expression;[Lcom/google/firebase/firestore/pipeline/Expression;)Lcom/google/firebase/firestore/pipeline/Expression;", "firstMapFieldName", "(Ljava/lang/String;Lcom/google/firebase/firestore/pipeline/Expression;[Lcom/google/firebase/firestore/pipeline/Expression;)Lcom/google/firebase/firestore/pipeline/Expression;", "mapRemove", "mapExpr", "mapField", "cosineDistance", "vector1", "vector2", "vectorFieldName", "dotProduct", "euclideanDistance", "vectorLength", "vectorExpression", "currentTimestamp", "unixMicrosToTimestamp", "timestampToUnixMicros", "unixMillisToTimestamp", "timestampToUnixMillis", "unixSecondsToTimestamp", "timestampToUnixSeconds", "timestampAdd", "timestamp", "unit", "amount", "", "timestampSubtract", "timestampTruncate", "granularity", "timezone", "equal", "left", "right", "notEqual", "greaterThan", "greaterThanOrEqual", "lessThan", "lessThanOrEqual", "concat", "(Lcom/google/firebase/firestore/pipeline/Expression;Lcom/google/firebase/firestore/pipeline/Expression;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/Expression;", "(Lcom/google/firebase/firestore/pipeline/Expression;Ljava/lang/Object;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/Expression;", "(Ljava/lang/String;Lcom/google/firebase/firestore/pipeline/Expression;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/Expression;", "(Ljava/lang/String;Ljava/lang/Object;[Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/Expression;", "array", "([Ljava/lang/Object;)Lcom/google/firebase/firestore/pipeline/Expression;", "arrayConcat", "firstArray", "secondArray", "otherArrays", "firstArrayField", "arrayReverse", "arraySum", "arrayContains", "element", "arrayContainsAll", "arrayContainsAny", "arrayLength", "arrayGet", TypedValues.CycleType.S_WAVE_OFFSET, "conditional", "thenExpr", "elseExpr", "thenValue", "elseValue", "exists", "error", "message", "error$com_google_firebase_firebase_firestore", "ifError", "tryExpr", "catchExpr", "isError", "catchValue", "ifAbsent", "ifExpr", "ifFieldName", "collectionId", "path", "pathField", "documentId", "documentPath", "docRef", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final Expression toExpr(Object value, Function1<Object, ? extends Expression> toExpr) {
            if (value == null) {
                return Expression.NULL;
            }
            if (value instanceof Expression) {
                return (Expression) value;
            }
            if (value instanceof String) {
                return constant((String) value);
            }
            if (value instanceof Number) {
                return constant((Number) value);
            }
            if (value instanceof Date) {
                return constant((Date) value);
            }
            if (value instanceof Timestamp) {
                return constant((Timestamp) value);
            }
            if (value instanceof Boolean) {
                return constant(((Boolean) value).booleanValue());
            }
            if (value instanceof GeoPoint) {
                return constant((GeoPoint) value);
            }
            if (value instanceof Blob) {
                return constant((Blob) value);
            }
            if (value instanceof DocumentReference) {
                return constant((DocumentReference) value);
            }
            if (value instanceof byte[]) {
                return constant((byte[]) value);
            }
            if (value instanceof VectorValue) {
                return constant((VectorValue) value);
            }
            if (value instanceof Value) {
                return new Constant((Value) value);
            }
            if (!(value instanceof Map)) {
                if (value instanceof List) {
                    return array((List<? extends Object>) value);
                }
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : ((Map) value).entrySet()) {
                Object key = entry.getKey();
                if (!(key instanceof String)) {
                    throw new IllegalArgumentException("Maps with non-string keys are not supported");
                }
                CollectionsKt.addAll(arrayList, CollectionsKt.listOf((Object[]) new Expression[]{Expression.INSTANCE.constant((String) key), toExpr.invoke(entry.getValue())}));
            }
            return map$com_google_firebase_firebase_firestore((Expression[]) arrayList.toArray(new Expression[0]));
        }

        @JvmStatic
        public final Expression constant(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Constant(Values.encodeValue(value));
        }

        @JvmStatic
        public final Expression constant(Number value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Constant(Values.encodeValue(value));
        }

        @JvmStatic
        public final Expression constant(Date value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Constant(Values.encodeValue(value));
        }

        @JvmStatic
        public final Expression constant(Timestamp value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Constant(Values.encodeValue(value));
        }

        @JvmStatic
        public final BooleanExpression constant(boolean value) {
            return new BooleanConstant(new Constant(Values.encodeValue(value)));
        }

        @JvmStatic
        public final Expression constant(GeoPoint value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Constant(Values.encodeValue(value));
        }

        @JvmStatic
        public final Expression constant(byte[] value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Constant(Values.encodeValue(value));
        }

        @JvmStatic
        public final Expression constant(Blob value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Constant(Values.encodeValue(value));
        }

        @JvmStatic
        public final Expression constant(DocumentReference ref) {
            Intrinsics.checkNotNullParameter(ref, "ref");
            return new Constant(Values.encodeValue(ref));
        }

        @JvmStatic
        public final Expression constant(VectorValue value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Constant(Values.encodeValue(value));
        }

        @JvmStatic
        public final Expression nullValue() {
            return Expression.NULL;
        }

        @JvmStatic
        public final Expression vector(double[] vector) {
            Intrinsics.checkNotNullParameter(vector, "vector");
            return new Constant(Values.encodeVectorValue(vector));
        }

        @JvmStatic
        public final Expression vector(VectorValue vector) {
            Intrinsics.checkNotNullParameter(vector, "vector");
            return new Constant(Values.encodeValue(vector));
        }

        @JvmStatic
        public final Field field(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            int iHashCode = name.hashCode();
            if (iHashCode != -1178228688) {
                if (iHashCode != -625729597) {
                    if (iHashCode == -281103477 && name.equals(DocumentKey.KEY_FIELD_NAME)) {
                        com.google.firebase.firestore.model.FieldPath KEY_PATH = com.google.firebase.firestore.model.FieldPath.KEY_PATH;
                        Intrinsics.checkNotNullExpressionValue(KEY_PATH, "KEY_PATH");
                        return new Field(KEY_PATH);
                    }
                } else if (name.equals(com.google.firebase.firestore.model.FieldPath.UPDATE_TIME_NAME)) {
                    com.google.firebase.firestore.model.FieldPath UPDATE_TIME_PATH = com.google.firebase.firestore.model.FieldPath.UPDATE_TIME_PATH;
                    Intrinsics.checkNotNullExpressionValue(UPDATE_TIME_PATH, "UPDATE_TIME_PATH");
                    return new Field(UPDATE_TIME_PATH);
                }
            } else if (name.equals(com.google.firebase.firestore.model.FieldPath.CREATE_TIME_NAME)) {
                com.google.firebase.firestore.model.FieldPath CREATE_TIME_PATH = com.google.firebase.firestore.model.FieldPath.CREATE_TIME_PATH;
                Intrinsics.checkNotNullExpressionValue(CREATE_TIME_PATH, "CREATE_TIME_PATH");
                return new Field(CREATE_TIME_PATH);
            }
            com.google.firebase.firestore.model.FieldPath internalPath = FieldPath.fromDotSeparatedPath(name).getInternalPath();
            Intrinsics.checkNotNullExpressionValue(internalPath, "getInternalPath(...)");
            return new Field(internalPath);
        }

        @JvmStatic
        public final Field field(FieldPath fieldPath) {
            Intrinsics.checkNotNullParameter(fieldPath, "fieldPath");
            com.google.firebase.firestore.model.FieldPath internalPath = fieldPath.getInternalPath();
            Intrinsics.checkNotNullExpressionValue(internalPath, "getInternalPath(...)");
            return new Field(internalPath);
        }

        @JvmStatic
        public final Expression rawFunction(String name, Expression... expr) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new FunctionExpression(name, UtilsKt.getNotImplemented(), expr, null, 8, null);
        }

        @JvmStatic
        public final BooleanExpression and(BooleanExpression condition, BooleanExpression... conditions) {
            Intrinsics.checkNotNullParameter(condition, "condition");
            Intrinsics.checkNotNullParameter(conditions, "conditions");
            return new BooleanFunctionExpression("and", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) LogicalKt.getEvaluateAnd(), (Expression) condition, Arrays.copyOf(conditions, conditions.length));
        }

        @JvmStatic
        public final BooleanExpression or(BooleanExpression condition, BooleanExpression... conditions) {
            Intrinsics.checkNotNullParameter(condition, "condition");
            Intrinsics.checkNotNullParameter(conditions, "conditions");
            return new BooleanFunctionExpression("or", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) LogicalKt.getEvaluateOr(), (Expression) condition, Arrays.copyOf(conditions, conditions.length));
        }

        @JvmStatic
        public final BooleanExpression xor(BooleanExpression condition, BooleanExpression... conditions) {
            Intrinsics.checkNotNullParameter(condition, "condition");
            Intrinsics.checkNotNullParameter(conditions, "conditions");
            return new BooleanFunctionExpression("xor", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) LogicalKt.getEvaluateXor(), (Expression) condition, Arrays.copyOf(conditions, conditions.length));
        }

        @JvmStatic
        public final BooleanExpression not(BooleanExpression condition) {
            Intrinsics.checkNotNullParameter(condition, "condition");
            return new BooleanFunctionExpression("not", ComparisonKt.getEvaluateNot(), condition);
        }

        @JvmStatic
        public final Expression bitAnd(Expression bits, Expression bitsOther) {
            Intrinsics.checkNotNullParameter(bits, "bits");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_and", UtilsKt.getNotImplemented(), bits, bitsOther);
        }

        @JvmStatic
        public final Expression bitAnd(Expression bits, byte[] bitsOther) {
            Intrinsics.checkNotNullParameter(bits, "bits");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_and", UtilsKt.getNotImplemented(), bits, constant(bitsOther));
        }

        @JvmStatic
        public final Expression bitAnd(String bitsFieldName, Expression bitsOther) {
            Intrinsics.checkNotNullParameter(bitsFieldName, "bitsFieldName");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_and", UtilsKt.getNotImplemented(), bitsFieldName, bitsOther);
        }

        @JvmStatic
        public final Expression bitAnd(String bitsFieldName, byte[] bitsOther) {
            Intrinsics.checkNotNullParameter(bitsFieldName, "bitsFieldName");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_and", UtilsKt.getNotImplemented(), bitsFieldName, constant(bitsOther));
        }

        @JvmStatic
        public final Expression bitOr(Expression bits, Expression bitsOther) {
            Intrinsics.checkNotNullParameter(bits, "bits");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_or", UtilsKt.getNotImplemented(), bits, bitsOther);
        }

        @JvmStatic
        public final Expression bitOr(Expression bits, byte[] bitsOther) {
            Intrinsics.checkNotNullParameter(bits, "bits");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_or", UtilsKt.getNotImplemented(), bits, constant(bitsOther));
        }

        @JvmStatic
        public final Expression bitOr(String bitsFieldName, Expression bitsOther) {
            Intrinsics.checkNotNullParameter(bitsFieldName, "bitsFieldName");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_or", UtilsKt.getNotImplemented(), bitsFieldName, bitsOther);
        }

        @JvmStatic
        public final Expression bitOr(String bitsFieldName, byte[] bitsOther) {
            Intrinsics.checkNotNullParameter(bitsFieldName, "bitsFieldName");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_or", UtilsKt.getNotImplemented(), bitsFieldName, constant(bitsOther));
        }

        @JvmStatic
        public final Expression bitXor(Expression bits, Expression bitsOther) {
            Intrinsics.checkNotNullParameter(bits, "bits");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_xor", UtilsKt.getNotImplemented(), bits, bitsOther);
        }

        @JvmStatic
        public final Expression bitXor(Expression bits, byte[] bitsOther) {
            Intrinsics.checkNotNullParameter(bits, "bits");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_xor", UtilsKt.getNotImplemented(), bits, constant(bitsOther));
        }

        @JvmStatic
        public final Expression bitXor(String bitsFieldName, Expression bitsOther) {
            Intrinsics.checkNotNullParameter(bitsFieldName, "bitsFieldName");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_xor", UtilsKt.getNotImplemented(), bitsFieldName, bitsOther);
        }

        @JvmStatic
        public final Expression bitXor(String bitsFieldName, byte[] bitsOther) {
            Intrinsics.checkNotNullParameter(bitsFieldName, "bitsFieldName");
            Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
            return new FunctionExpression("bit_xor", UtilsKt.getNotImplemented(), bitsFieldName, constant(bitsOther));
        }

        @JvmStatic
        public final Expression bitNot(Expression bits) {
            Intrinsics.checkNotNullParameter(bits, "bits");
            return new FunctionExpression("bit_not", UtilsKt.getNotImplemented(), bits);
        }

        @JvmStatic
        public final Expression bitNot(String bitsFieldName) {
            Intrinsics.checkNotNullParameter(bitsFieldName, "bitsFieldName");
            return new FunctionExpression("bit_not", UtilsKt.getNotImplemented(), bitsFieldName);
        }

        @JvmStatic
        public final Expression bitLeftShift(Expression bits, Expression numberExpr) {
            Intrinsics.checkNotNullParameter(bits, "bits");
            Intrinsics.checkNotNullParameter(numberExpr, "numberExpr");
            return new FunctionExpression("bit_left_shift", UtilsKt.getNotImplemented(), bits, numberExpr);
        }

        @JvmStatic
        public final Expression bitLeftShift(Expression bits, int number) {
            Intrinsics.checkNotNullParameter(bits, "bits");
            return new FunctionExpression("bit_left_shift", UtilsKt.getNotImplemented(), bits, Integer.valueOf(number));
        }

        @JvmStatic
        public final Expression bitLeftShift(String bitsFieldName, Expression numberExpr) {
            Intrinsics.checkNotNullParameter(bitsFieldName, "bitsFieldName");
            Intrinsics.checkNotNullParameter(numberExpr, "numberExpr");
            return new FunctionExpression("bit_left_shift", UtilsKt.getNotImplemented(), bitsFieldName, numberExpr);
        }

        @JvmStatic
        public final Expression bitLeftShift(String bitsFieldName, int number) {
            Intrinsics.checkNotNullParameter(bitsFieldName, "bitsFieldName");
            return new FunctionExpression("bit_left_shift", UtilsKt.getNotImplemented(), bitsFieldName, Integer.valueOf(number));
        }

        @JvmStatic
        public final Expression bitRightShift(Expression bits, Expression numberExpr) {
            Intrinsics.checkNotNullParameter(bits, "bits");
            Intrinsics.checkNotNullParameter(numberExpr, "numberExpr");
            return new FunctionExpression("bit_right_shift", UtilsKt.getNotImplemented(), bits, numberExpr);
        }

        @JvmStatic
        public final Expression bitRightShift(Expression bits, int number) {
            Intrinsics.checkNotNullParameter(bits, "bits");
            return new FunctionExpression("bit_right_shift", UtilsKt.getNotImplemented(), bits, Integer.valueOf(number));
        }

        @JvmStatic
        public final Expression bitRightShift(String bitsFieldName, Expression numberExpr) {
            Intrinsics.checkNotNullParameter(bitsFieldName, "bitsFieldName");
            Intrinsics.checkNotNullParameter(numberExpr, "numberExpr");
            return new FunctionExpression("bit_right_shift", UtilsKt.getNotImplemented(), bitsFieldName, numberExpr);
        }

        @JvmStatic
        public final Expression bitRightShift(String bitsFieldName, int number) {
            Intrinsics.checkNotNullParameter(bitsFieldName, "bitsFieldName");
            return new FunctionExpression("bit_right_shift", UtilsKt.getNotImplemented(), bitsFieldName, Integer.valueOf(number));
        }

        @JvmStatic
        public final Expression round(Expression numericExpr) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            return new FunctionExpression("round", ArithmeticKt.getEvaluateRound(), numericExpr);
        }

        @JvmStatic
        public final Expression round(String numericField) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            return new FunctionExpression("round", ArithmeticKt.getEvaluateRound(), numericField);
        }

        @JvmStatic
        public final Expression roundToPrecision(Expression numericExpr, int decimalPlace) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            return new FunctionExpression("round", ArithmeticKt.getEvaluateRoundToPrecision(), numericExpr, constant(Integer.valueOf(decimalPlace)));
        }

        @JvmStatic
        public final Expression roundToPrecision(String numericField, int decimalPlace) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            return new FunctionExpression("round", ArithmeticKt.getEvaluateRoundToPrecision(), numericField, constant(Integer.valueOf(decimalPlace)));
        }

        @JvmStatic
        public final Expression roundToPrecision(Expression numericExpr, Expression decimalPlace) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            Intrinsics.checkNotNullParameter(decimalPlace, "decimalPlace");
            return new FunctionExpression("round", ArithmeticKt.getEvaluateRoundToPrecision(), numericExpr, decimalPlace);
        }

        @JvmStatic
        public final Expression roundToPrecision(String numericField, Expression decimalPlace) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            Intrinsics.checkNotNullParameter(decimalPlace, "decimalPlace");
            return new FunctionExpression("round", ArithmeticKt.getEvaluateRoundToPrecision(), numericField, decimalPlace);
        }

        @JvmStatic
        public final Expression ceil(Expression numericExpr) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            return new FunctionExpression("ceil", ArithmeticKt.getEvaluateCeil(), numericExpr);
        }

        @JvmStatic
        public final Expression ceil(String numericField) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            return new FunctionExpression("ceil", ArithmeticKt.getEvaluateCeil(), numericField);
        }

        @JvmStatic
        public final Expression floor(Expression numericExpr) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            return new FunctionExpression("floor", ArithmeticKt.getEvaluateFloor(), numericExpr);
        }

        @JvmStatic
        public final Expression floor(String numericField) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            return new FunctionExpression("floor", ArithmeticKt.getEvaluateFloor(), numericField);
        }

        @JvmStatic
        public final Expression pow(Expression numericExpr, Number exponent) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            Intrinsics.checkNotNullParameter(exponent, "exponent");
            return new FunctionExpression("pow", ArithmeticKt.getEvaluatePow(), numericExpr, constant(exponent));
        }

        @JvmStatic
        public final Expression pow(String numericField, Number exponent) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            Intrinsics.checkNotNullParameter(exponent, "exponent");
            return new FunctionExpression("pow", ArithmeticKt.getEvaluatePow(), numericField, constant(exponent));
        }

        @JvmStatic
        public final Expression pow(Expression numericExpr, Expression exponent) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            Intrinsics.checkNotNullParameter(exponent, "exponent");
            return new FunctionExpression("pow", ArithmeticKt.getEvaluatePow(), numericExpr, exponent);
        }

        @JvmStatic
        public final Expression pow(String numericField, Expression exponent) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            Intrinsics.checkNotNullParameter(exponent, "exponent");
            return new FunctionExpression("pow", ArithmeticKt.getEvaluatePow(), numericField, exponent);
        }

        @JvmStatic
        public final Expression abs(Expression numericExpr) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            return new FunctionExpression("abs", ArithmeticKt.getEvaluateAbs(), numericExpr);
        }

        @JvmStatic
        public final Expression abs(String numericField) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            return new FunctionExpression("abs", ArithmeticKt.getEvaluateAbs(), numericField);
        }

        @JvmStatic
        public final Expression exp(Expression numericExpr) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            return new FunctionExpression("exp", ArithmeticKt.getEvaluateExp(), numericExpr);
        }

        @JvmStatic
        public final Expression exp(String numericField) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            return new FunctionExpression("exp", ArithmeticKt.getEvaluateExp(), numericField);
        }

        @JvmStatic
        public final Expression ln(Expression numericExpr) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            return new FunctionExpression("ln", ArithmeticKt.getEvaluateLn(), numericExpr);
        }

        @JvmStatic
        public final Expression ln(String numericField) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            return new FunctionExpression("ln", ArithmeticKt.getEvaluateLn(), numericField);
        }

        @JvmStatic
        public final Expression log(Expression numericExpr, Number base) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            Intrinsics.checkNotNullParameter(base, "base");
            return new FunctionExpression("log", ArithmeticKt.getEvaluateLog(), numericExpr, constant(base));
        }

        @JvmStatic
        public final Expression log(String numericField, Number base) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            Intrinsics.checkNotNullParameter(base, "base");
            return new FunctionExpression("log", ArithmeticKt.getEvaluateLog(), numericField, constant(base));
        }

        @JvmStatic
        public final Expression log(Expression numericExpr, Expression base) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            Intrinsics.checkNotNullParameter(base, "base");
            return new FunctionExpression("log", ArithmeticKt.getEvaluateLog(), numericExpr, base);
        }

        @JvmStatic
        public final Expression log(String numericField, Expression base) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            Intrinsics.checkNotNullParameter(base, "base");
            return new FunctionExpression("log", ArithmeticKt.getEvaluateLog(), numericField, base);
        }

        @JvmStatic
        public final Expression log10(Expression numericExpr) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            return new FunctionExpression("log10", ArithmeticKt.getEvaluateLog10(), numericExpr);
        }

        @JvmStatic
        public final Expression log10(String numericField) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            return new FunctionExpression("log10", ArithmeticKt.getEvaluateLog10(), numericField);
        }

        @JvmStatic
        public final Expression sqrt(Expression numericExpr) {
            Intrinsics.checkNotNullParameter(numericExpr, "numericExpr");
            return new FunctionExpression("sqrt", ArithmeticKt.getEvaluateSqrt(), numericExpr);
        }

        @JvmStatic
        public final Expression sqrt(String numericField) {
            Intrinsics.checkNotNullParameter(numericField, "numericField");
            return new FunctionExpression("sqrt", ArithmeticKt.getEvaluateSqrt(), numericField);
        }

        @JvmStatic
        public final Expression add(Expression first, Expression second) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            return new FunctionExpression("add", ArithmeticKt.getEvaluateAdd(), first, second);
        }

        @JvmStatic
        public final Expression add(Expression first, Number second) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            return new FunctionExpression("add", ArithmeticKt.getEvaluateAdd(), first, second);
        }

        @JvmStatic
        public final Expression add(String numericFieldName, Expression second) {
            Intrinsics.checkNotNullParameter(numericFieldName, "numericFieldName");
            Intrinsics.checkNotNullParameter(second, "second");
            return new FunctionExpression("add", ArithmeticKt.getEvaluateAdd(), numericFieldName, second);
        }

        @JvmStatic
        public final Expression add(String numericFieldName, Number second) {
            Intrinsics.checkNotNullParameter(numericFieldName, "numericFieldName");
            Intrinsics.checkNotNullParameter(second, "second");
            return new FunctionExpression("add", ArithmeticKt.getEvaluateAdd(), numericFieldName, second);
        }

        @JvmStatic
        public final Expression subtract(Expression minuend, Expression subtrahend) {
            Intrinsics.checkNotNullParameter(minuend, "minuend");
            Intrinsics.checkNotNullParameter(subtrahend, "subtrahend");
            return new FunctionExpression("subtract", ArithmeticKt.getEvaluateSubtract(), minuend, subtrahend);
        }

        @JvmStatic
        public final Expression subtract(Expression minuend, Number subtrahend) {
            Intrinsics.checkNotNullParameter(minuend, "minuend");
            Intrinsics.checkNotNullParameter(subtrahend, "subtrahend");
            return new FunctionExpression("subtract", ArithmeticKt.getEvaluateSubtract(), minuend, subtrahend);
        }

        @JvmStatic
        public final Expression subtract(String numericFieldName, Expression subtrahend) {
            Intrinsics.checkNotNullParameter(numericFieldName, "numericFieldName");
            Intrinsics.checkNotNullParameter(subtrahend, "subtrahend");
            return new FunctionExpression("subtract", ArithmeticKt.getEvaluateSubtract(), numericFieldName, subtrahend);
        }

        @JvmStatic
        public final Expression subtract(String numericFieldName, Number subtrahend) {
            Intrinsics.checkNotNullParameter(numericFieldName, "numericFieldName");
            Intrinsics.checkNotNullParameter(subtrahend, "subtrahend");
            return new FunctionExpression("subtract", ArithmeticKt.getEvaluateSubtract(), numericFieldName, subtrahend);
        }

        @JvmStatic
        public final Expression multiply(Expression first, Expression second) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            return new FunctionExpression("multiply", ArithmeticKt.getEvaluateMultiply(), first, second);
        }

        @JvmStatic
        public final Expression multiply(Expression first, Number second) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            return new FunctionExpression("multiply", ArithmeticKt.getEvaluateMultiply(), first, second);
        }

        @JvmStatic
        public final Expression multiply(String numericFieldName, Expression second) {
            Intrinsics.checkNotNullParameter(numericFieldName, "numericFieldName");
            Intrinsics.checkNotNullParameter(second, "second");
            return new FunctionExpression("multiply", ArithmeticKt.getEvaluateMultiply(), numericFieldName, second);
        }

        @JvmStatic
        public final Expression multiply(String numericFieldName, Number second) {
            Intrinsics.checkNotNullParameter(numericFieldName, "numericFieldName");
            Intrinsics.checkNotNullParameter(second, "second");
            return new FunctionExpression("multiply", ArithmeticKt.getEvaluateMultiply(), numericFieldName, second);
        }

        @JvmStatic
        public final Expression divide(Expression dividend, Expression divisor) {
            Intrinsics.checkNotNullParameter(dividend, "dividend");
            Intrinsics.checkNotNullParameter(divisor, "divisor");
            return new FunctionExpression("divide", ArithmeticKt.getEvaluateDivide(), dividend, divisor);
        }

        @JvmStatic
        public final Expression divide(Expression dividend, Number divisor) {
            Intrinsics.checkNotNullParameter(dividend, "dividend");
            Intrinsics.checkNotNullParameter(divisor, "divisor");
            return new FunctionExpression("divide", ArithmeticKt.getEvaluateDivide(), dividend, divisor);
        }

        @JvmStatic
        public final Expression divide(String dividendFieldName, Expression divisor) {
            Intrinsics.checkNotNullParameter(dividendFieldName, "dividendFieldName");
            Intrinsics.checkNotNullParameter(divisor, "divisor");
            return new FunctionExpression("divide", ArithmeticKt.getEvaluateDivide(), dividendFieldName, divisor);
        }

        @JvmStatic
        public final Expression divide(String dividendFieldName, Number divisor) {
            Intrinsics.checkNotNullParameter(dividendFieldName, "dividendFieldName");
            Intrinsics.checkNotNullParameter(divisor, "divisor");
            return new FunctionExpression("divide", ArithmeticKt.getEvaluateDivide(), dividendFieldName, divisor);
        }

        @JvmStatic
        public final Expression mod(Expression dividend, Expression divisor) {
            Intrinsics.checkNotNullParameter(dividend, "dividend");
            Intrinsics.checkNotNullParameter(divisor, "divisor");
            return new FunctionExpression("mod", ArithmeticKt.getEvaluateMod(), dividend, divisor);
        }

        @JvmStatic
        public final Expression mod(Expression dividend, Number divisor) {
            Intrinsics.checkNotNullParameter(dividend, "dividend");
            Intrinsics.checkNotNullParameter(divisor, "divisor");
            return new FunctionExpression("mod", ArithmeticKt.getEvaluateMod(), dividend, divisor);
        }

        @JvmStatic
        public final Expression mod(String dividendFieldName, Expression divisor) {
            Intrinsics.checkNotNullParameter(dividendFieldName, "dividendFieldName");
            Intrinsics.checkNotNullParameter(divisor, "divisor");
            return new FunctionExpression("mod", ArithmeticKt.getEvaluateMod(), dividendFieldName, divisor);
        }

        @JvmStatic
        public final Expression mod(String dividendFieldName, Number divisor) {
            Intrinsics.checkNotNullParameter(dividendFieldName, "dividendFieldName");
            Intrinsics.checkNotNullParameter(divisor, "divisor");
            return new FunctionExpression("mod", ArithmeticKt.getEvaluateMod(), dividendFieldName, divisor);
        }

        @JvmStatic
        public final BooleanExpression equalAny(Expression expression, List<? extends Object> values) {
            Intrinsics.checkNotNullParameter(expression, "expression");
            Intrinsics.checkNotNullParameter(values, "values");
            return equalAny(expression, array(values));
        }

        @JvmStatic
        public final BooleanExpression equalAny(Expression expression, Expression arrayExpression) {
            Intrinsics.checkNotNullParameter(expression, "expression");
            Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
            return new BooleanFunctionExpression("equal_any", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ArrayKt.getEvaluateEqAny(), expression, arrayExpression);
        }

        @JvmStatic
        public final BooleanExpression equalAny(String fieldName, List<? extends Object> values) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(values, "values");
            return equalAny(fieldName, array(values));
        }

        @JvmStatic
        public final BooleanExpression equalAny(String fieldName, Expression arrayExpression) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
            return new BooleanFunctionExpression("equal_any", ArrayKt.getEvaluateEqAny(), fieldName, arrayExpression);
        }

        @JvmStatic
        public final BooleanExpression notEqualAny(Expression expression, List<? extends Object> values) {
            Intrinsics.checkNotNullParameter(expression, "expression");
            Intrinsics.checkNotNullParameter(values, "values");
            return notEqualAny(expression, array(values));
        }

        @JvmStatic
        public final BooleanExpression notEqualAny(Expression expression, Expression arrayExpression) {
            Intrinsics.checkNotNullParameter(expression, "expression");
            Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
            return new BooleanFunctionExpression("not_equal_any", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ArrayKt.getEvaluateNotEqAny(), expression, arrayExpression);
        }

        @JvmStatic
        public final BooleanExpression notEqualAny(String fieldName, List<? extends Object> values) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(values, "values");
            return notEqualAny(fieldName, array(values));
        }

        @JvmStatic
        public final BooleanExpression notEqualAny(String fieldName, Expression arrayExpression) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
            return new BooleanFunctionExpression("not_equal_any", ArrayKt.getEvaluateNotEqAny(), fieldName, arrayExpression);
        }

        @JvmStatic
        public final BooleanExpression isAbsent(Expression value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new BooleanFunctionExpression("is_absent", DebugKt.getEvaluateIsAbsent(), value);
        }

        @JvmStatic
        public final BooleanExpression isAbsent(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new BooleanFunctionExpression("is_absent", DebugKt.getEvaluateIsAbsent(), fieldName);
        }

        @JvmStatic
        public final BooleanExpression isNan$com_google_firebase_firebase_firestore(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new BooleanFunctionExpression("is_nan", LogicalKt.getEvaluateIsNaN(), expr);
        }

        @JvmStatic
        public final BooleanExpression isNan$com_google_firebase_firebase_firestore(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new BooleanFunctionExpression("is_nan", LogicalKt.getEvaluateIsNaN(), fieldName);
        }

        @JvmStatic
        public final BooleanExpression isNotNan$com_google_firebase_firebase_firestore(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new BooleanFunctionExpression("is_not_nan", LogicalKt.getEvaluateIsNotNaN(), expr);
        }

        @JvmStatic
        public final BooleanExpression isNotNan$com_google_firebase_firebase_firestore(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new BooleanFunctionExpression("is_not_nan", LogicalKt.getEvaluateIsNotNaN(), fieldName);
        }

        @JvmStatic
        public final BooleanExpression isNull$com_google_firebase_firebase_firestore(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new BooleanFunctionExpression("is_null", LogicalKt.getEvaluateIsNull(), expr);
        }

        @JvmStatic
        public final BooleanExpression isNull$com_google_firebase_firebase_firestore(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new BooleanFunctionExpression("is_null", LogicalKt.getEvaluateIsNull(), fieldName);
        }

        @JvmStatic
        public final BooleanExpression isNotNull$com_google_firebase_firebase_firestore(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new BooleanFunctionExpression("is_not_null", LogicalKt.getEvaluateIsNotNull(), expr);
        }

        @JvmStatic
        public final BooleanExpression isNotNull$com_google_firebase_firebase_firestore(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new BooleanFunctionExpression("is_not_null", LogicalKt.getEvaluateIsNotNull(), fieldName);
        }

        @JvmStatic
        public final Expression type(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new FunctionExpression("type", UtilsKt.getNotImplemented(), expr);
        }

        @JvmStatic
        public final Expression type(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("type", UtilsKt.getNotImplemented(), fieldName);
        }

        @JvmStatic
        public final Expression length(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new FunctionExpression("length", GenericsKt.getEvaluateLength(), expr);
        }

        @JvmStatic
        public final Expression length(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("length", GenericsKt.getEvaluateLength(), fieldName);
        }

        @JvmStatic
        public final Expression charLength(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new FunctionExpression("char_length", Strings.getEvaluateCharLength(), expr);
        }

        @JvmStatic
        public final Expression charLength(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("char_length", Strings.getEvaluateCharLength(), fieldName);
        }

        @JvmStatic
        public final Expression byteLength(Expression value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new FunctionExpression("byte_length", Strings.getEvaluateByteLength(), value);
        }

        @JvmStatic
        public final Expression byteLength(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("byte_length", Strings.getEvaluateByteLength(), fieldName);
        }

        @JvmStatic
        public final BooleanExpression like(Expression stringExpression, Expression pattern) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("like", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) Strings.getEvaluateLike(), stringExpression, pattern);
        }

        @JvmStatic
        public final Expression split(Expression value, Expression delimiter) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(delimiter, "delimiter");
            return new FunctionExpression("split", UtilsKt.getNotImplemented(), value, delimiter);
        }

        @JvmStatic
        public final Expression split(Expression value, String delimiter) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(delimiter, "delimiter");
            return new FunctionExpression("split", UtilsKt.getNotImplemented(), value, constant(delimiter));
        }

        @JvmStatic
        public final Expression split(Expression value, Blob delimiter) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(delimiter, "delimiter");
            return new FunctionExpression("split", UtilsKt.getNotImplemented(), value, constant(delimiter));
        }

        @JvmStatic
        public final Expression split(String fieldName, Expression delimiter) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(delimiter, "delimiter");
            return new FunctionExpression("split", UtilsKt.getNotImplemented(), fieldName, delimiter);
        }

        @JvmStatic
        public final Expression split(String fieldName, String delimiter) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(delimiter, "delimiter");
            return new FunctionExpression("split", UtilsKt.getNotImplemented(), fieldName, constant(delimiter));
        }

        @JvmStatic
        public final Expression split(String fieldName, Blob delimiter) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(delimiter, "delimiter");
            return new FunctionExpression("split", UtilsKt.getNotImplemented(), fieldName, constant(delimiter));
        }

        @JvmStatic
        public final Expression join(Expression arrayExpression, String delimiter) {
            Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
            Intrinsics.checkNotNullParameter(delimiter, "delimiter");
            return new FunctionExpression("join", ArrayKt.getEvaluateJoin(), arrayExpression, constant(delimiter));
        }

        @JvmStatic
        public final Expression join(Expression arrayExpression, Expression delimiterExpression) {
            Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
            Intrinsics.checkNotNullParameter(delimiterExpression, "delimiterExpression");
            return new FunctionExpression("join", ArrayKt.getEvaluateJoin(), arrayExpression, delimiterExpression);
        }

        @JvmStatic
        public final Expression join(String arrayFieldName, String delimiter) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            Intrinsics.checkNotNullParameter(delimiter, "delimiter");
            return new FunctionExpression("join", ArrayKt.getEvaluateJoin(), arrayFieldName, constant(delimiter));
        }

        @JvmStatic
        public final Expression join(String arrayFieldName, Expression delimiterExpression) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            Intrinsics.checkNotNullParameter(delimiterExpression, "delimiterExpression");
            return new FunctionExpression("join", ArrayKt.getEvaluateJoin(), arrayFieldName, delimiterExpression);
        }

        @JvmStatic
        public final BooleanExpression like(Expression stringExpression, String pattern) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("like", Strings.getEvaluateLike(), stringExpression, pattern);
        }

        @JvmStatic
        public final BooleanExpression like(String fieldName, Expression pattern) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("like", Strings.getEvaluateLike(), fieldName, pattern);
        }

        @JvmStatic
        public final BooleanExpression like(String fieldName, String pattern) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("like", Strings.getEvaluateLike(), fieldName, pattern);
        }

        @JvmStatic
        public final Expression rand$com_google_firebase_firebase_firestore() {
            return new FunctionExpression("rand", UtilsKt.getNotImplemented());
        }

        @JvmStatic
        public final BooleanExpression regexContains(Expression stringExpression, Expression pattern) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("regex_contains", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) Strings.getEvaluateRegexContains(), stringExpression, pattern);
        }

        @JvmStatic
        public final BooleanExpression regexContains(Expression stringExpression, String pattern) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("regex_contains", Strings.getEvaluateRegexContains(), stringExpression, pattern);
        }

        @JvmStatic
        public final BooleanExpression regexContains(String fieldName, Expression pattern) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("regex_contains", Strings.getEvaluateRegexContains(), fieldName, pattern);
        }

        @JvmStatic
        public final BooleanExpression regexContains(String fieldName, String pattern) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("regex_contains", Strings.getEvaluateRegexContains(), fieldName, pattern);
        }

        @JvmStatic
        public final BooleanExpression regexMatch(Expression stringExpression, Expression pattern) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("regex_match", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) Strings.getEvaluateRegexMatch(), stringExpression, pattern);
        }

        @JvmStatic
        public final BooleanExpression regexMatch(Expression stringExpression, String pattern) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("regex_match", Strings.getEvaluateRegexMatch(), stringExpression, pattern);
        }

        @JvmStatic
        public final BooleanExpression regexMatch(String fieldName, Expression pattern) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("regex_match", Strings.getEvaluateRegexMatch(), fieldName, pattern);
        }

        @JvmStatic
        public final BooleanExpression regexMatch(String fieldName, String pattern) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            return new BooleanFunctionExpression("regex_match", Strings.getEvaluateRegexMatch(), fieldName, pattern);
        }

        @JvmStatic
        public final Expression logicalMaximum(Expression expr, Object... others) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            Intrinsics.checkNotNullParameter(others, "others");
            return new FunctionExpression("maximum", LogicalKt.getEvaluateLogicalMaximum(), expr, Arrays.copyOf(others, others.length));
        }

        @JvmStatic
        public final Expression logicalMaximum(String fieldName, Object... others) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(others, "others");
            return new FunctionExpression("maximum", LogicalKt.getEvaluateLogicalMaximum(), fieldName, Arrays.copyOf(others, others.length));
        }

        @JvmStatic
        public final Expression logicalMinimum(Expression expr, Object... others) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            Intrinsics.checkNotNullParameter(others, "others");
            return new FunctionExpression("minimum", LogicalKt.getEvaluateLogicalMinimum(), expr, Arrays.copyOf(others, others.length));
        }

        @JvmStatic
        public final Expression logicalMinimum(String fieldName, Object... others) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(others, "others");
            return new FunctionExpression("minimum", LogicalKt.getEvaluateLogicalMinimum(), fieldName, Arrays.copyOf(others, others.length));
        }

        @JvmStatic
        public final Expression reverse(Expression stringExpression) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            return new FunctionExpression("reverse", Strings.getEvaluateReverse(), stringExpression);
        }

        @JvmStatic
        public final Expression reverse(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("reverse", Strings.getEvaluateReverse(), fieldName);
        }

        @JvmStatic
        public final BooleanExpression stringContains(Expression stringExpression, Expression substring) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            Intrinsics.checkNotNullParameter(substring, "substring");
            return new BooleanFunctionExpression("string_contains", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) Strings.getEvaluateStrContains(), stringExpression, substring);
        }

        @JvmStatic
        public final BooleanExpression stringContains(Expression stringExpression, String substring) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            Intrinsics.checkNotNullParameter(substring, "substring");
            return new BooleanFunctionExpression("string_contains", Strings.getEvaluateStrContains(), stringExpression, substring);
        }

        @JvmStatic
        public final BooleanExpression stringContains(String fieldName, Expression substring) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(substring, "substring");
            return new BooleanFunctionExpression("string_contains", Strings.getEvaluateStrContains(), fieldName, substring);
        }

        @JvmStatic
        public final BooleanExpression stringContains(String fieldName, String substring) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(substring, "substring");
            return new BooleanFunctionExpression("string_contains", Strings.getEvaluateStrContains(), fieldName, substring);
        }

        @JvmStatic
        public final BooleanExpression startsWith(Expression stringExpr, Expression prefix) {
            Intrinsics.checkNotNullParameter(stringExpr, "stringExpr");
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            return new BooleanFunctionExpression("starts_with", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) Strings.getEvaluateStartsWith(), stringExpr, prefix);
        }

        @JvmStatic
        public final BooleanExpression startsWith(Expression stringExpr, String prefix) {
            Intrinsics.checkNotNullParameter(stringExpr, "stringExpr");
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            return new BooleanFunctionExpression("starts_with", Strings.getEvaluateStartsWith(), stringExpr, prefix);
        }

        @JvmStatic
        public final BooleanExpression startsWith(String fieldName, Expression prefix) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            return new BooleanFunctionExpression("starts_with", Strings.getEvaluateStartsWith(), fieldName, prefix);
        }

        @JvmStatic
        public final BooleanExpression startsWith(String fieldName, String prefix) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            return new BooleanFunctionExpression("starts_with", Strings.getEvaluateStartsWith(), fieldName, prefix);
        }

        @JvmStatic
        public final BooleanExpression endsWith(Expression stringExpr, Expression suffix) {
            Intrinsics.checkNotNullParameter(stringExpr, "stringExpr");
            Intrinsics.checkNotNullParameter(suffix, "suffix");
            return new BooleanFunctionExpression("ends_with", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) Strings.getEvaluateEndsWith(), stringExpr, suffix);
        }

        @JvmStatic
        public final BooleanExpression endsWith(Expression stringExpr, String suffix) {
            Intrinsics.checkNotNullParameter(stringExpr, "stringExpr");
            Intrinsics.checkNotNullParameter(suffix, "suffix");
            return new BooleanFunctionExpression("ends_with", Strings.getEvaluateEndsWith(), stringExpr, suffix);
        }

        @JvmStatic
        public final BooleanExpression endsWith(String fieldName, Expression suffix) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(suffix, "suffix");
            return new BooleanFunctionExpression("ends_with", Strings.getEvaluateEndsWith(), fieldName, suffix);
        }

        @JvmStatic
        public final BooleanExpression endsWith(String fieldName, String suffix) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(suffix, "suffix");
            return new BooleanFunctionExpression("ends_with", Strings.getEvaluateEndsWith(), fieldName, suffix);
        }

        @JvmStatic
        public final Expression stringReverse(Expression str) {
            Intrinsics.checkNotNullParameter(str, "str");
            return new FunctionExpression("string_reverse", Strings.getEvaluateStringReverse(), str);
        }

        @JvmStatic
        public final Expression stringReverse(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("string_reverse", Strings.getEvaluateStringReverse(), fieldName);
        }

        @JvmStatic
        public final Expression substring(Expression stringExpression, Expression index, Expression length) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            Intrinsics.checkNotNullParameter(index, "index");
            Intrinsics.checkNotNullParameter(length, "length");
            return new FunctionExpression("substring", Strings.getEvaluateSubstring(), stringExpression, index, length);
        }

        @JvmStatic
        public final Expression substring(String fieldName, int index, int length) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("substring", Strings.getEvaluateSubstring(), fieldName, Integer.valueOf(index), Integer.valueOf(length));
        }

        @JvmStatic
        public final Expression toLower(Expression stringExpression) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            return new FunctionExpression("to_lower", Strings.getEvaluateToLowercase(), stringExpression);
        }

        @JvmStatic
        public final Expression toLower(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("to_lower", Strings.getEvaluateToLowercase(), fieldName);
        }

        @JvmStatic
        public final Expression toUpper(Expression stringExpression) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            return new FunctionExpression("to_upper", Strings.getEvaluateToUppercase(), stringExpression);
        }

        @JvmStatic
        public final Expression toUpper(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("to_upper", Strings.getEvaluateToUppercase(), fieldName);
        }

        @JvmStatic
        public final Expression trim(Expression stringExpression) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            return new FunctionExpression("trim", Strings.getEvaluateTrim(), stringExpression);
        }

        @JvmStatic
        public final Expression trim(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("trim", Strings.getEvaluateTrim(), fieldName);
        }

        @JvmStatic
        public final Expression trimValue(Expression stringExpression, Expression valueToTrim) {
            Intrinsics.checkNotNullParameter(stringExpression, "stringExpression");
            Intrinsics.checkNotNullParameter(valueToTrim, "valueToTrim");
            return new FunctionExpression("trim", UtilsKt.getNotImplemented(), stringExpression, valueToTrim);
        }

        @JvmStatic
        public final Expression trimValue(String fieldName, String valueToTrim) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(valueToTrim, "valueToTrim");
            return new FunctionExpression("trim", UtilsKt.getNotImplemented(), fieldName, constant(valueToTrim));
        }

        @JvmStatic
        public final Expression stringConcat(Expression firstString, Expression... otherStrings) {
            Intrinsics.checkNotNullParameter(firstString, "firstString");
            Intrinsics.checkNotNullParameter(otherStrings, "otherStrings");
            return new FunctionExpression("string_concat", Strings.getEvaluateStrConcat(), firstString, Arrays.copyOf(otherStrings, otherStrings.length));
        }

        @JvmStatic
        public final Expression stringConcat(Expression firstString, Object... otherStrings) {
            Intrinsics.checkNotNullParameter(firstString, "firstString");
            Intrinsics.checkNotNullParameter(otherStrings, "otherStrings");
            return new FunctionExpression("string_concat", Strings.getEvaluateStrConcat(), firstString, Arrays.copyOf(otherStrings, otherStrings.length));
        }

        @JvmStatic
        public final Expression stringConcat(String fieldName, Expression... otherStrings) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(otherStrings, "otherStrings");
            return new FunctionExpression("string_concat", Strings.getEvaluateStrConcat(), fieldName, Arrays.copyOf(otherStrings, otherStrings.length));
        }

        @JvmStatic
        public final Expression stringConcat(String fieldName, Object... otherStrings) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(otherStrings, "otherStrings");
            return new FunctionExpression("string_concat", Strings.getEvaluateStrConcat(), fieldName, Arrays.copyOf(otherStrings, otherStrings.length));
        }

        public final Expression map$com_google_firebase_firebase_firestore(Expression[] elements) {
            Intrinsics.checkNotNullParameter(elements, "elements");
            return new FunctionExpression("map", MapsKt.getEvaluateMap(), elements, null, 8, null);
        }

        @JvmStatic
        public final Expression mapGet(Expression mapExpression, String key) {
            Intrinsics.checkNotNullParameter(mapExpression, "mapExpression");
            Intrinsics.checkNotNullParameter(key, "key");
            return new FunctionExpression("map_get", MapsKt.getEvaluateMapGet(), mapExpression, key);
        }

        @JvmStatic
        public final Expression mapGet(String fieldName, String key) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(key, "key");
            return new FunctionExpression("map_get", MapsKt.getEvaluateMapGet(), fieldName, key);
        }

        @JvmStatic
        public final Expression mapGet(Expression mapExpression, Expression keyExpression) {
            Intrinsics.checkNotNullParameter(mapExpression, "mapExpression");
            Intrinsics.checkNotNullParameter(keyExpression, "keyExpression");
            return new FunctionExpression("map_get", MapsKt.getEvaluateMapGet(), mapExpression, keyExpression);
        }

        @JvmStatic
        public final Expression mapGet(String fieldName, Expression keyExpression) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(keyExpression, "keyExpression");
            return new FunctionExpression("map_get", MapsKt.getEvaluateMapGet(), fieldName, keyExpression);
        }

        @JvmStatic
        public final Expression mapMerge(Expression firstMap, Expression secondMap, Expression... otherMaps) {
            Intrinsics.checkNotNullParameter(firstMap, "firstMap");
            Intrinsics.checkNotNullParameter(secondMap, "secondMap");
            Intrinsics.checkNotNullParameter(otherMaps, "otherMaps");
            return new FunctionExpression("map_merge", UtilsKt.getNotImplemented(), firstMap, secondMap, Arrays.copyOf(otherMaps, otherMaps.length));
        }

        @JvmStatic
        public final Expression mapMerge(String firstMapFieldName, Expression secondMap, Expression... otherMaps) {
            Intrinsics.checkNotNullParameter(firstMapFieldName, "firstMapFieldName");
            Intrinsics.checkNotNullParameter(secondMap, "secondMap");
            Intrinsics.checkNotNullParameter(otherMaps, "otherMaps");
            Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> notImplemented = UtilsKt.getNotImplemented();
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(secondMap);
            spreadBuilder.addSpread(otherMaps);
            return new FunctionExpression("map_merge", notImplemented, firstMapFieldName, spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        }

        @JvmStatic
        public final Expression mapRemove(Expression mapExpr, Expression key) {
            Intrinsics.checkNotNullParameter(mapExpr, "mapExpr");
            Intrinsics.checkNotNullParameter(key, "key");
            return new FunctionExpression("map_remove", UtilsKt.getNotImplemented(), mapExpr, key);
        }

        @JvmStatic
        public final Expression mapRemove(String mapField, Expression key) {
            Intrinsics.checkNotNullParameter(mapField, "mapField");
            Intrinsics.checkNotNullParameter(key, "key");
            return new FunctionExpression("map_remove", UtilsKt.getNotImplemented(), mapField, key);
        }

        @JvmStatic
        public final Expression mapRemove(Expression mapExpr, String key) {
            Intrinsics.checkNotNullParameter(mapExpr, "mapExpr");
            Intrinsics.checkNotNullParameter(key, "key");
            return new FunctionExpression("map_remove", UtilsKt.getNotImplemented(), mapExpr, key);
        }

        @JvmStatic
        public final Expression mapRemove(String mapField, String key) {
            Intrinsics.checkNotNullParameter(mapField, "mapField");
            Intrinsics.checkNotNullParameter(key, "key");
            return new FunctionExpression("map_remove", UtilsKt.getNotImplemented(), mapField, key);
        }

        @JvmStatic
        public final Expression cosineDistance(Expression vector1, Expression vector2) {
            Intrinsics.checkNotNullParameter(vector1, "vector1");
            Intrinsics.checkNotNullParameter(vector2, "vector2");
            return new FunctionExpression("cosine_distance", VectorKt.getEvaluateCosineDistance(), vector1, vector2);
        }

        @JvmStatic
        public final Expression cosineDistance(Expression vector1, double[] vector2) {
            Intrinsics.checkNotNullParameter(vector1, "vector1");
            Intrinsics.checkNotNullParameter(vector2, "vector2");
            return new FunctionExpression("cosine_distance", VectorKt.getEvaluateCosineDistance(), vector1, vector(vector2));
        }

        @JvmStatic
        public final Expression cosineDistance(Expression vector1, VectorValue vector2) {
            Intrinsics.checkNotNullParameter(vector1, "vector1");
            Intrinsics.checkNotNullParameter(vector2, "vector2");
            return new FunctionExpression("cosine_distance", VectorKt.getEvaluateCosineDistance(), vector1, vector2);
        }

        @JvmStatic
        public final Expression cosineDistance(String vectorFieldName, Expression vector) {
            Intrinsics.checkNotNullParameter(vectorFieldName, "vectorFieldName");
            Intrinsics.checkNotNullParameter(vector, "vector");
            return new FunctionExpression("cosine_distance", VectorKt.getEvaluateCosineDistance(), vectorFieldName, vector);
        }

        @JvmStatic
        public final Expression cosineDistance(String vectorFieldName, double[] vector) {
            Intrinsics.checkNotNullParameter(vectorFieldName, "vectorFieldName");
            Intrinsics.checkNotNullParameter(vector, "vector");
            return new FunctionExpression("cosine_distance", VectorKt.getEvaluateCosineDistance(), vectorFieldName, vector(vector));
        }

        @JvmStatic
        public final Expression cosineDistance(String vectorFieldName, VectorValue vector) {
            Intrinsics.checkNotNullParameter(vectorFieldName, "vectorFieldName");
            Intrinsics.checkNotNullParameter(vector, "vector");
            return new FunctionExpression("cosine_distance", VectorKt.getEvaluateCosineDistance(), vectorFieldName, vector);
        }

        @JvmStatic
        public final Expression dotProduct(Expression vector1, Expression vector2) {
            Intrinsics.checkNotNullParameter(vector1, "vector1");
            Intrinsics.checkNotNullParameter(vector2, "vector2");
            return new FunctionExpression("dot_product", VectorKt.getEvaluateDotProductDistance(), vector1, vector2);
        }

        @JvmStatic
        public final Expression dotProduct(Expression vector1, double[] vector2) {
            Intrinsics.checkNotNullParameter(vector1, "vector1");
            Intrinsics.checkNotNullParameter(vector2, "vector2");
            return new FunctionExpression("dot_product", VectorKt.getEvaluateDotProductDistance(), vector1, vector(vector2));
        }

        @JvmStatic
        public final Expression dotProduct(Expression vector1, VectorValue vector2) {
            Intrinsics.checkNotNullParameter(vector1, "vector1");
            Intrinsics.checkNotNullParameter(vector2, "vector2");
            return new FunctionExpression("dot_product", VectorKt.getEvaluateDotProductDistance(), vector1, vector2);
        }

        @JvmStatic
        public final Expression dotProduct(String vectorFieldName, Expression vector) {
            Intrinsics.checkNotNullParameter(vectorFieldName, "vectorFieldName");
            Intrinsics.checkNotNullParameter(vector, "vector");
            return new FunctionExpression("dot_product", VectorKt.getEvaluateDotProductDistance(), vectorFieldName, vector);
        }

        @JvmStatic
        public final Expression dotProduct(String vectorFieldName, double[] vector) {
            Intrinsics.checkNotNullParameter(vectorFieldName, "vectorFieldName");
            Intrinsics.checkNotNullParameter(vector, "vector");
            return new FunctionExpression("dot_product", VectorKt.getEvaluateDotProductDistance(), vectorFieldName, vector(vector));
        }

        @JvmStatic
        public final Expression dotProduct(String vectorFieldName, VectorValue vector) {
            Intrinsics.checkNotNullParameter(vectorFieldName, "vectorFieldName");
            Intrinsics.checkNotNullParameter(vector, "vector");
            return new FunctionExpression("dot_product", VectorKt.getEvaluateDotProductDistance(), vectorFieldName, vector);
        }

        @JvmStatic
        public final Expression euclideanDistance(Expression vector1, Expression vector2) {
            Intrinsics.checkNotNullParameter(vector1, "vector1");
            Intrinsics.checkNotNullParameter(vector2, "vector2");
            return new FunctionExpression("euclidean_distance", VectorKt.getEvaluateEuclideanDistance(), vector1, vector2);
        }

        @JvmStatic
        public final Expression euclideanDistance(Expression vector1, double[] vector2) {
            Intrinsics.checkNotNullParameter(vector1, "vector1");
            Intrinsics.checkNotNullParameter(vector2, "vector2");
            return new FunctionExpression("euclidean_distance", VectorKt.getEvaluateEuclideanDistance(), vector1, vector(vector2));
        }

        @JvmStatic
        public final Expression euclideanDistance(Expression vector1, VectorValue vector2) {
            Intrinsics.checkNotNullParameter(vector1, "vector1");
            Intrinsics.checkNotNullParameter(vector2, "vector2");
            return new FunctionExpression("euclidean_distance", VectorKt.getEvaluateEuclideanDistance(), vector1, vector2);
        }

        @JvmStatic
        public final Expression euclideanDistance(String vectorFieldName, Expression vector) {
            Intrinsics.checkNotNullParameter(vectorFieldName, "vectorFieldName");
            Intrinsics.checkNotNullParameter(vector, "vector");
            return new FunctionExpression("euclidean_distance", VectorKt.getEvaluateEuclideanDistance(), vectorFieldName, vector);
        }

        @JvmStatic
        public final Expression euclideanDistance(String vectorFieldName, double[] vector) {
            Intrinsics.checkNotNullParameter(vectorFieldName, "vectorFieldName");
            Intrinsics.checkNotNullParameter(vector, "vector");
            return new FunctionExpression("euclidean_distance", VectorKt.getEvaluateEuclideanDistance(), vectorFieldName, vector(vector));
        }

        @JvmStatic
        public final Expression euclideanDistance(String vectorFieldName, VectorValue vector) {
            Intrinsics.checkNotNullParameter(vectorFieldName, "vectorFieldName");
            Intrinsics.checkNotNullParameter(vector, "vector");
            return new FunctionExpression("euclidean_distance", VectorKt.getEvaluateEuclideanDistance(), vectorFieldName, vector);
        }

        @JvmStatic
        public final Expression vectorLength(Expression vectorExpression) {
            Intrinsics.checkNotNullParameter(vectorExpression, "vectorExpression");
            return new FunctionExpression("vector_length", VectorKt.getEvaluateVectorLength(), vectorExpression);
        }

        @JvmStatic
        public final Expression vectorLength(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("vector_length", VectorKt.getEvaluateVectorLength(), fieldName);
        }

        @JvmStatic
        public final Expression currentTimestamp() {
            return new FunctionExpression("current_timestamp", UtilsKt.getNotImplemented());
        }

        @JvmStatic
        public final Expression unixMicrosToTimestamp(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new FunctionExpression("unix_micros_to_timestamp", TimestampKt.getEvaluateUnixMicrosToTimestamp(), expr);
        }

        @JvmStatic
        public final Expression unixMicrosToTimestamp(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("unix_micros_to_timestamp", TimestampKt.getEvaluateUnixMicrosToTimestamp(), fieldName);
        }

        @JvmStatic
        public final Expression timestampToUnixMicros(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new FunctionExpression("timestamp_to_unix_micros", TimestampKt.getEvaluateTimestampToUnixMicros(), expr);
        }

        @JvmStatic
        public final Expression timestampToUnixMicros(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("timestamp_to_unix_micros", TimestampKt.getEvaluateTimestampToUnixMicros(), fieldName);
        }

        @JvmStatic
        public final Expression unixMillisToTimestamp(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new FunctionExpression("unix_millis_to_timestamp", TimestampKt.getEvaluateUnixMillisToTimestamp(), expr);
        }

        @JvmStatic
        public final Expression unixMillisToTimestamp(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("unix_millis_to_timestamp", TimestampKt.getEvaluateUnixMillisToTimestamp(), fieldName);
        }

        @JvmStatic
        public final Expression timestampToUnixMillis(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new FunctionExpression("timestamp_to_unix_millis", TimestampKt.getEvaluateTimestampToUnixMillis(), expr);
        }

        @JvmStatic
        public final Expression timestampToUnixMillis(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("timestamp_to_unix_millis", TimestampKt.getEvaluateTimestampToUnixMillis(), fieldName);
        }

        @JvmStatic
        public final Expression unixSecondsToTimestamp(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new FunctionExpression("unix_seconds_to_timestamp", TimestampKt.getEvaluateUnixSecondsToTimestamp(), expr);
        }

        @JvmStatic
        public final Expression unixSecondsToTimestamp(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("unix_seconds_to_timestamp", TimestampKt.getEvaluateUnixSecondsToTimestamp(), fieldName);
        }

        @JvmStatic
        public final Expression timestampToUnixSeconds(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new FunctionExpression("timestamp_to_unix_seconds", TimestampKt.getEvaluateTimestampToUnixSeconds(), expr);
        }

        @JvmStatic
        public final Expression timestampToUnixSeconds(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new FunctionExpression("timestamp_to_unix_seconds", TimestampKt.getEvaluateTimestampToUnixSeconds(), fieldName);
        }

        @JvmStatic
        public final Expression timestampAdd(Expression timestamp, Expression unit, Expression amount) {
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(unit, "unit");
            Intrinsics.checkNotNullParameter(amount, "amount");
            return new FunctionExpression("timestamp_add", TimestampKt.getEvaluateTimestampAdd(), timestamp, unit, amount);
        }

        @JvmStatic
        public final Expression timestampAdd(Expression timestamp, String unit, long amount) {
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(unit, "unit");
            return new FunctionExpression("timestamp_add", TimestampKt.getEvaluateTimestampAdd(), timestamp, unit, Long.valueOf(amount));
        }

        @JvmStatic
        public final Expression timestampAdd(String fieldName, Expression unit, Expression amount) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(unit, "unit");
            Intrinsics.checkNotNullParameter(amount, "amount");
            return new FunctionExpression("timestamp_add", TimestampKt.getEvaluateTimestampAdd(), fieldName, unit, amount);
        }

        @JvmStatic
        public final Expression timestampAdd(String fieldName, String unit, long amount) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(unit, "unit");
            return new FunctionExpression("timestamp_add", TimestampKt.getEvaluateTimestampAdd(), fieldName, unit, Long.valueOf(amount));
        }

        @JvmStatic
        public final Expression timestampSubtract(Expression timestamp, Expression unit, Expression amount) {
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(unit, "unit");
            Intrinsics.checkNotNullParameter(amount, "amount");
            return new FunctionExpression("timestamp_subtract", TimestampKt.getEvaluateTimestampSub(), timestamp, unit, amount);
        }

        @JvmStatic
        public final Expression timestampSubtract(Expression timestamp, String unit, long amount) {
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(unit, "unit");
            return new FunctionExpression("timestamp_subtract", TimestampKt.getEvaluateTimestampSub(), timestamp, unit, Long.valueOf(amount));
        }

        @JvmStatic
        public final Expression timestampSubtract(String fieldName, Expression unit, Expression amount) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(unit, "unit");
            Intrinsics.checkNotNullParameter(amount, "amount");
            return new FunctionExpression("timestamp_subtract", TimestampKt.getEvaluateTimestampSub(), fieldName, unit, amount);
        }

        @JvmStatic
        public final Expression timestampSubtract(String fieldName, String unit, long amount) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(unit, "unit");
            return new FunctionExpression("timestamp_subtract", TimestampKt.getEvaluateTimestampSub(), fieldName, unit, Long.valueOf(amount));
        }

        @JvmStatic
        public final Expression timestampTruncate(Expression timestamp, String granularity) {
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(granularity, "granularity");
            return new FunctionExpression("timestamp_trunc", UtilsKt.getNotImplemented(), timestamp, constant(granularity));
        }

        @JvmStatic
        public final Expression timestampTruncate(Expression timestamp, Expression granularity) {
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(granularity, "granularity");
            return new FunctionExpression("timestamp_trunc", UtilsKt.getNotImplemented(), timestamp, granularity);
        }

        @JvmStatic
        public final Expression timestampTruncate(String fieldName, String granularity) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(granularity, "granularity");
            return new FunctionExpression("timestamp_trunc", UtilsKt.getNotImplemented(), field(fieldName), constant(granularity));
        }

        @JvmStatic
        public final Expression timestampTruncate(String fieldName, Expression granularity) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(granularity, "granularity");
            return new FunctionExpression("timestamp_trunc", UtilsKt.getNotImplemented(), field(fieldName), granularity);
        }

        @JvmStatic
        public final Expression timestampTruncate(Expression timestamp, String granularity, String timezone) {
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(granularity, "granularity");
            Intrinsics.checkNotNullParameter(timezone, "timezone");
            return new FunctionExpression("timestamp_trunc", UtilsKt.getNotImplemented(), timestamp, constant(granularity), constant(timezone));
        }

        @JvmStatic
        public final Expression timestampTruncate(Expression timestamp, Expression granularity, String timezone) {
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(granularity, "granularity");
            Intrinsics.checkNotNullParameter(timezone, "timezone");
            return new FunctionExpression("timestamp_trunc", UtilsKt.getNotImplemented(), timestamp, granularity, constant(timezone));
        }

        @JvmStatic
        public final Expression timestampTruncate(String fieldName, String granularity, String timezone) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(granularity, "granularity");
            Intrinsics.checkNotNullParameter(timezone, "timezone");
            return new FunctionExpression("timestamp_trunc", UtilsKt.getNotImplemented(), field(fieldName), constant(granularity), constant(timezone));
        }

        @JvmStatic
        public final Expression timestampTruncate(String fieldName, Expression granularity, String timezone) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(granularity, "granularity");
            Intrinsics.checkNotNullParameter(timezone, "timezone");
            return new FunctionExpression("timestamp_trunc", UtilsKt.getNotImplemented(), field(fieldName), granularity, constant(timezone));
        }

        @JvmStatic
        public final BooleanExpression equal(Expression left, Expression right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("equal", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ComparisonKt.getEvaluateEq(), left, right);
        }

        @JvmStatic
        public final BooleanExpression equal(Expression left, Object right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("equal", ComparisonKt.getEvaluateEq(), left, right);
        }

        @JvmStatic
        public final BooleanExpression equal(String fieldName, Expression expression) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new BooleanFunctionExpression("equal", ComparisonKt.getEvaluateEq(), fieldName, expression);
        }

        @JvmStatic
        public final BooleanExpression equal(String fieldName, Object value) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(value, "value");
            return new BooleanFunctionExpression("equal", ComparisonKt.getEvaluateEq(), fieldName, value);
        }

        @JvmStatic
        public final BooleanExpression notEqual(Expression left, Expression right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("not_equal", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ComparisonKt.getEvaluateNeq(), left, right);
        }

        @JvmStatic
        public final BooleanExpression notEqual(Expression left, Object right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("not_equal", ComparisonKt.getEvaluateNeq(), left, right);
        }

        @JvmStatic
        public final BooleanExpression notEqual(String fieldName, Expression expression) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new BooleanFunctionExpression("not_equal", ComparisonKt.getEvaluateNeq(), fieldName, expression);
        }

        @JvmStatic
        public final BooleanExpression notEqual(String fieldName, Object value) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(value, "value");
            return new BooleanFunctionExpression("not_equal", ComparisonKt.getEvaluateNeq(), fieldName, value);
        }

        @JvmStatic
        public final BooleanExpression greaterThan(Expression left, Expression right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("greater_than", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ComparisonKt.getEvaluateGt(), left, right);
        }

        @JvmStatic
        public final BooleanExpression greaterThan(Expression left, Object right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("greater_than", ComparisonKt.getEvaluateGt(), left, right);
        }

        @JvmStatic
        public final BooleanExpression greaterThan(String fieldName, Expression expression) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new BooleanFunctionExpression("greater_than", ComparisonKt.getEvaluateGt(), fieldName, expression);
        }

        @JvmStatic
        public final BooleanExpression greaterThan(String fieldName, Object value) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(value, "value");
            return new BooleanFunctionExpression("greater_than", ComparisonKt.getEvaluateGt(), fieldName, value);
        }

        @JvmStatic
        public final BooleanExpression greaterThanOrEqual(Expression left, Expression right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("greater_than_or_equal", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ComparisonKt.getEvaluateGte(), left, right);
        }

        @JvmStatic
        public final BooleanExpression greaterThanOrEqual(Expression left, Object right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("greater_than_or_equal", ComparisonKt.getEvaluateGte(), left, right);
        }

        @JvmStatic
        public final BooleanExpression greaterThanOrEqual(String fieldName, Expression expression) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new BooleanFunctionExpression("greater_than_or_equal", ComparisonKt.getEvaluateGte(), fieldName, expression);
        }

        @JvmStatic
        public final BooleanExpression greaterThanOrEqual(String fieldName, Object value) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(value, "value");
            return new BooleanFunctionExpression("greater_than_or_equal", ComparisonKt.getEvaluateGte(), fieldName, value);
        }

        @JvmStatic
        public final BooleanExpression lessThan(Expression left, Expression right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("less_than", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ComparisonKt.getEvaluateLt(), left, right);
        }

        @JvmStatic
        public final BooleanExpression lessThan(Expression left, Object right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("less_than", ComparisonKt.getEvaluateLt(), left, right);
        }

        @JvmStatic
        public final BooleanExpression lessThan(String fieldName, Expression expression) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new BooleanFunctionExpression("less_than", ComparisonKt.getEvaluateLt(), fieldName, expression);
        }

        @JvmStatic
        public final BooleanExpression lessThan(String fieldName, Object value) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(value, "value");
            return new BooleanFunctionExpression("less_than", ComparisonKt.getEvaluateLt(), fieldName, value);
        }

        @JvmStatic
        public final BooleanExpression lessThanOrEqual(Expression left, Expression right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("less_than_or_equal", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ComparisonKt.getEvaluateLte(), left, right);
        }

        @JvmStatic
        public final BooleanExpression lessThanOrEqual(Expression left, Object right) {
            Intrinsics.checkNotNullParameter(left, "left");
            Intrinsics.checkNotNullParameter(right, "right");
            return new BooleanFunctionExpression("less_than_or_equal", ComparisonKt.getEvaluateLte(), left, right);
        }

        @JvmStatic
        public final BooleanExpression lessThanOrEqual(String fieldName, Expression expression) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(expression, "expression");
            return new BooleanFunctionExpression("less_than_or_equal", ComparisonKt.getEvaluateLte(), fieldName, expression);
        }

        @JvmStatic
        public final BooleanExpression lessThanOrEqual(String fieldName, Object value) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            Intrinsics.checkNotNullParameter(value, "value");
            return new BooleanFunctionExpression("less_than_or_equal", ComparisonKt.getEvaluateLte(), fieldName, value);
        }

        @JvmStatic
        public final Expression concat(Expression first, Expression second, Object... others) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            Intrinsics.checkNotNullParameter(others, "others");
            return new FunctionExpression("concat", GenericsKt.getEvaluateConcat(), first, second, Arrays.copyOf(others, others.length));
        }

        @JvmStatic
        public final Expression concat(Expression first, Object second, Object... others) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            Intrinsics.checkNotNullParameter(others, "others");
            Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateConcat = GenericsKt.getEvaluateConcat();
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(second);
            spreadBuilder.addSpread(others);
            return new FunctionExpression("concat", evaluateConcat, first, spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        }

        @JvmStatic
        public final Expression concat(String first, Expression second, Object... others) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            Intrinsics.checkNotNullParameter(others, "others");
            Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateConcat = GenericsKt.getEvaluateConcat();
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(second);
            spreadBuilder.addSpread(others);
            return new FunctionExpression("concat", evaluateConcat, first, spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        }

        @JvmStatic
        public final Expression concat(String first, Object second, Object... others) {
            Intrinsics.checkNotNullParameter(first, "first");
            Intrinsics.checkNotNullParameter(second, "second");
            Intrinsics.checkNotNullParameter(others, "others");
            Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateConcat = GenericsKt.getEvaluateConcat();
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(second);
            spreadBuilder.addSpread(others);
            return new FunctionExpression("concat", evaluateConcat, first, spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        }

        @JvmStatic
        public final Expression array(Object... elements) {
            Intrinsics.checkNotNullParameter(elements, "elements");
            Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateArray = ArrayKt.getEvaluateArray();
            ArrayList arrayList = new ArrayList(elements.length);
            for (Object obj : elements) {
                arrayList.add(toExprOrConstant$com_google_firebase_firebase_firestore(obj));
            }
            return new FunctionExpression("array", evaluateArray, (Expression[]) arrayList.toArray(new Expression[0]), null, 8, null);
        }

        @JvmStatic
        public final Expression array(List<? extends Object> elements) {
            Intrinsics.checkNotNullParameter(elements, "elements");
            Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateArray = ArrayKt.getEvaluateArray();
            List<? extends Object> list = elements;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(toExprOrConstant$com_google_firebase_firebase_firestore(it.next()));
            }
            return new FunctionExpression("array", evaluateArray, (Expression[]) arrayList.toArray(new Expression[0]), null, 8, null);
        }

        @JvmStatic
        public final Expression arrayConcat(Expression firstArray, Expression secondArray, Object... otherArrays) {
            Intrinsics.checkNotNullParameter(firstArray, "firstArray");
            Intrinsics.checkNotNullParameter(secondArray, "secondArray");
            Intrinsics.checkNotNullParameter(otherArrays, "otherArrays");
            return new FunctionExpression("array_concat", ArrayKt.getEvaluateArrayConcat(), firstArray, secondArray, Arrays.copyOf(otherArrays, otherArrays.length));
        }

        @JvmStatic
        public final Expression arrayConcat(Expression firstArray, Object secondArray, Object... otherArrays) {
            Intrinsics.checkNotNullParameter(firstArray, "firstArray");
            Intrinsics.checkNotNullParameter(secondArray, "secondArray");
            Intrinsics.checkNotNullParameter(otherArrays, "otherArrays");
            Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateArrayConcat = ArrayKt.getEvaluateArrayConcat();
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(secondArray);
            spreadBuilder.addSpread(otherArrays);
            return new FunctionExpression("array_concat", evaluateArrayConcat, firstArray, spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        }

        @JvmStatic
        public final Expression arrayConcat(String firstArrayField, Expression secondArray, Object... otherArrays) {
            Intrinsics.checkNotNullParameter(firstArrayField, "firstArrayField");
            Intrinsics.checkNotNullParameter(secondArray, "secondArray");
            Intrinsics.checkNotNullParameter(otherArrays, "otherArrays");
            Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateArrayConcat = ArrayKt.getEvaluateArrayConcat();
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(secondArray);
            spreadBuilder.addSpread(otherArrays);
            return new FunctionExpression("array_concat", evaluateArrayConcat, firstArrayField, spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        }

        @JvmStatic
        public final Expression arrayConcat(String firstArrayField, Object secondArray, Object... otherArrays) {
            Intrinsics.checkNotNullParameter(firstArrayField, "firstArrayField");
            Intrinsics.checkNotNullParameter(secondArray, "secondArray");
            Intrinsics.checkNotNullParameter(otherArrays, "otherArrays");
            Function1<List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, Function1<MutableDocument, EvaluateResult>> evaluateArrayConcat = ArrayKt.getEvaluateArrayConcat();
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(secondArray);
            spreadBuilder.addSpread(otherArrays);
            return new FunctionExpression("array_concat", evaluateArrayConcat, firstArrayField, spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        }

        @JvmStatic
        public final Expression arrayReverse(Expression array) {
            Intrinsics.checkNotNullParameter(array, "array");
            return new FunctionExpression("array_reverse", ArrayKt.getEvaluateArrayReverse(), array);
        }

        @JvmStatic
        public final Expression arrayReverse(String arrayFieldName) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            return new FunctionExpression("array_reverse", ArrayKt.getEvaluateArrayReverse(), arrayFieldName);
        }

        @JvmStatic
        public final Expression arraySum(Expression array) {
            Intrinsics.checkNotNullParameter(array, "array");
            return new FunctionExpression("sum", UtilsKt.getNotImplemented(), array);
        }

        @JvmStatic
        public final Expression arraySum(String arrayFieldName) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            return new FunctionExpression("sum", UtilsKt.getNotImplemented(), arrayFieldName);
        }

        @JvmStatic
        public final BooleanExpression arrayContains(Expression array, Expression element) {
            Intrinsics.checkNotNullParameter(array, "array");
            Intrinsics.checkNotNullParameter(element, "element");
            return new BooleanFunctionExpression("array_contains", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ArrayKt.getEvaluateArrayContains(), array, element);
        }

        @JvmStatic
        public final BooleanExpression arrayContains(String arrayFieldName, Expression element) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            Intrinsics.checkNotNullParameter(element, "element");
            return new BooleanFunctionExpression("array_contains", ArrayKt.getEvaluateArrayContains(), arrayFieldName, element);
        }

        @JvmStatic
        public final BooleanExpression arrayContains(Expression array, Object element) {
            Intrinsics.checkNotNullParameter(array, "array");
            Intrinsics.checkNotNullParameter(element, "element");
            return new BooleanFunctionExpression("array_contains", ArrayKt.getEvaluateArrayContains(), array, element);
        }

        @JvmStatic
        public final BooleanExpression arrayContains(String arrayFieldName, Object element) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            Intrinsics.checkNotNullParameter(element, "element");
            return new BooleanFunctionExpression("array_contains", ArrayKt.getEvaluateArrayContains(), arrayFieldName, element);
        }

        @JvmStatic
        public final BooleanExpression arrayContainsAll(Expression array, List<? extends Object> values) {
            Intrinsics.checkNotNullParameter(array, "array");
            Intrinsics.checkNotNullParameter(values, "values");
            return arrayContainsAll(array, array(values));
        }

        @JvmStatic
        public final BooleanExpression arrayContainsAll(Expression array, Expression arrayExpression) {
            Intrinsics.checkNotNullParameter(array, "array");
            Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
            return new BooleanFunctionExpression("array_contains_all", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ArrayKt.getEvaluateArrayContainsAll(), array, arrayExpression);
        }

        @JvmStatic
        public final BooleanExpression arrayContainsAll(String arrayFieldName, List<? extends Object> values) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            Intrinsics.checkNotNullParameter(values, "values");
            return new BooleanFunctionExpression("array_contains_all", ArrayKt.getEvaluateArrayContainsAll(), arrayFieldName, array(values));
        }

        @JvmStatic
        public final BooleanExpression arrayContainsAll(String arrayFieldName, Expression arrayExpression) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
            return new BooleanFunctionExpression("array_contains_all", ArrayKt.getEvaluateArrayContainsAll(), arrayFieldName, arrayExpression);
        }

        @JvmStatic
        public final BooleanExpression arrayContainsAny(Expression array, List<? extends Object> values) {
            Intrinsics.checkNotNullParameter(array, "array");
            Intrinsics.checkNotNullParameter(values, "values");
            return new BooleanFunctionExpression("array_contains_any", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ArrayKt.getEvaluateArrayContainsAny(), array, array(values));
        }

        @JvmStatic
        public final BooleanExpression arrayContainsAny(Expression array, Expression arrayExpression) {
            Intrinsics.checkNotNullParameter(array, "array");
            Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
            return new BooleanFunctionExpression("array_contains_any", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) ArrayKt.getEvaluateArrayContainsAny(), array, arrayExpression);
        }

        @JvmStatic
        public final BooleanExpression arrayContainsAny(String arrayFieldName, List<? extends Object> values) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            Intrinsics.checkNotNullParameter(values, "values");
            return new BooleanFunctionExpression("array_contains_any", ArrayKt.getEvaluateArrayContainsAny(), arrayFieldName, array(values));
        }

        @JvmStatic
        public final BooleanExpression arrayContainsAny(String arrayFieldName, Expression arrayExpression) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
            return new BooleanFunctionExpression("array_contains_any", ArrayKt.getEvaluateArrayContainsAny(), arrayFieldName, arrayExpression);
        }

        @JvmStatic
        public final Expression arrayLength(Expression array) {
            Intrinsics.checkNotNullParameter(array, "array");
            return new FunctionExpression("array_length", ArrayKt.getEvaluateArrayLength(), array);
        }

        @JvmStatic
        public final Expression arrayLength(String arrayFieldName) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            return new FunctionExpression("array_length", ArrayKt.getEvaluateArrayLength(), arrayFieldName);
        }

        @JvmStatic
        public final Expression arrayGet(Expression array, Expression offset) {
            Intrinsics.checkNotNullParameter(array, "array");
            Intrinsics.checkNotNullParameter(offset, "offset");
            return new FunctionExpression("array_get", ArrayKt.getEvaluateArrayGet(), array, offset);
        }

        @JvmStatic
        public final Expression arrayGet(Expression array, int offset) {
            Intrinsics.checkNotNullParameter(array, "array");
            return new FunctionExpression("array_get", ArrayKt.getEvaluateArrayGet(), array, constant(Integer.valueOf(offset)));
        }

        @JvmStatic
        public final Expression arrayGet(String arrayFieldName, Expression offset) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            Intrinsics.checkNotNullParameter(offset, "offset");
            return new FunctionExpression("array_get", ArrayKt.getEvaluateArrayGet(), arrayFieldName, offset);
        }

        @JvmStatic
        public final Expression arrayGet(String arrayFieldName, int offset) {
            Intrinsics.checkNotNullParameter(arrayFieldName, "arrayFieldName");
            return new FunctionExpression("array_get", ArrayKt.getEvaluateArrayGet(), arrayFieldName, constant(Integer.valueOf(offset)));
        }

        @JvmStatic
        public final Expression conditional(BooleanExpression condition, Expression thenExpr, Expression elseExpr) {
            Intrinsics.checkNotNullParameter(condition, "condition");
            Intrinsics.checkNotNullParameter(thenExpr, "thenExpr");
            Intrinsics.checkNotNullParameter(elseExpr, "elseExpr");
            return new FunctionExpression("conditional", LogicalKt.getEvaluateCond(), condition, thenExpr, elseExpr);
        }

        @JvmStatic
        public final Expression conditional(BooleanExpression condition, Object thenValue, Object elseValue) {
            Intrinsics.checkNotNullParameter(condition, "condition");
            Intrinsics.checkNotNullParameter(thenValue, "thenValue");
            Intrinsics.checkNotNullParameter(elseValue, "elseValue");
            return new FunctionExpression("conditional", LogicalKt.getEvaluateCond(), condition, thenValue, elseValue);
        }

        @JvmStatic
        public final BooleanExpression exists(Expression value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new BooleanFunctionExpression("exists", DebugKt.getEvaluateExists(), value);
        }

        @JvmStatic
        public final BooleanExpression exists(String fieldName) {
            Intrinsics.checkNotNullParameter(fieldName, "fieldName");
            return new BooleanFunctionExpression("exists", DebugKt.getEvaluateExists(), fieldName);
        }

        @JvmStatic
        public final Expression error$com_google_firebase_firebase_firestore(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new FunctionExpression("error", DebugKt.getEvaluateError(), constant(message));
        }

        @JvmStatic
        public final Expression ifError(Expression tryExpr, Expression catchExpr) {
            Intrinsics.checkNotNullParameter(tryExpr, "tryExpr");
            Intrinsics.checkNotNullParameter(catchExpr, "catchExpr");
            return new FunctionExpression("if_error", UtilsKt.getNotImplemented(), tryExpr, catchExpr);
        }

        @JvmStatic
        public final BooleanExpression ifError(BooleanExpression tryExpr, BooleanExpression catchExpr) {
            Intrinsics.checkNotNullParameter(tryExpr, "tryExpr");
            Intrinsics.checkNotNullParameter(catchExpr, "catchExpr");
            return new BooleanFunctionExpression("if_error", (Function1<? super List<? extends Function1<? super MutableDocument, ? extends EvaluateResult>>, ? extends Function1<? super MutableDocument, ? extends EvaluateResult>>) UtilsKt.getNotImplemented(), (Expression) tryExpr, (Expression) catchExpr);
        }

        @JvmStatic
        public final BooleanExpression isError(Expression expr) {
            Intrinsics.checkNotNullParameter(expr, "expr");
            return new BooleanFunctionExpression("is_error", DebugKt.getEvaluateIsError(), expr);
        }

        @JvmStatic
        public final Expression ifError(Expression tryExpr, Object catchValue) {
            Intrinsics.checkNotNullParameter(tryExpr, "tryExpr");
            Intrinsics.checkNotNullParameter(catchValue, "catchValue");
            return new FunctionExpression("if_error", UtilsKt.getNotImplemented(), tryExpr, catchValue);
        }

        @JvmStatic
        public final Expression ifAbsent(Expression ifExpr, Expression elseExpr) {
            Intrinsics.checkNotNullParameter(ifExpr, "ifExpr");
            Intrinsics.checkNotNullParameter(elseExpr, "elseExpr");
            return new FunctionExpression("if_absent", UtilsKt.getNotImplemented(), ifExpr, elseExpr);
        }

        @JvmStatic
        public final Expression ifAbsent(Expression ifExpr, Object elseValue) {
            Intrinsics.checkNotNullParameter(ifExpr, "ifExpr");
            Intrinsics.checkNotNullParameter(elseValue, "elseValue");
            return new FunctionExpression("if_absent", UtilsKt.getNotImplemented(), ifExpr, elseValue);
        }

        @JvmStatic
        public final Expression ifAbsent(String ifFieldName, Expression elseExpr) {
            Intrinsics.checkNotNullParameter(ifFieldName, "ifFieldName");
            Intrinsics.checkNotNullParameter(elseExpr, "elseExpr");
            return new FunctionExpression("if_absent", UtilsKt.getNotImplemented(), ifFieldName, elseExpr);
        }

        @JvmStatic
        public final Expression ifAbsent(String ifFieldName, Object elseValue) {
            Intrinsics.checkNotNullParameter(ifFieldName, "ifFieldName");
            Intrinsics.checkNotNullParameter(elseValue, "elseValue");
            return new FunctionExpression("if_absent", UtilsKt.getNotImplemented(), ifFieldName, elseValue);
        }

        @JvmStatic
        public final Expression collectionId(Expression path) {
            Intrinsics.checkNotNullParameter(path, "path");
            return new FunctionExpression("collection_id", UtilsKt.getNotImplemented(), path);
        }

        @JvmStatic
        public final Expression collectionId(String pathField) {
            Intrinsics.checkNotNullParameter(pathField, "pathField");
            return collectionId(field(pathField));
        }

        @JvmStatic
        public final Expression documentId(Expression documentPath) {
            Intrinsics.checkNotNullParameter(documentPath, "documentPath");
            return new FunctionExpression("document_id", UtilsKt.getNotImplemented(), documentPath);
        }

        @JvmStatic
        public final Expression documentId(String documentPath) {
            Intrinsics.checkNotNullParameter(documentPath, "documentPath");
            return documentId(constant(documentPath));
        }

        @JvmStatic
        public final Expression documentId(DocumentReference docRef) {
            Intrinsics.checkNotNullParameter(docRef, "docRef");
            return documentId(constant(docRef));
        }

        public final Expression toExprOrConstant$com_google_firebase_firebase_firestore(Object value) {
            Constant constantArray;
            if (value == null) {
                constantArray = Expression.NULL;
            } else if (value instanceof Expression) {
                constantArray = (Expression) value;
            } else if (value instanceof String) {
                constantArray = constant((String) value);
            } else if (value instanceof Number) {
                constantArray = constant((Number) value);
            } else if (value instanceof Date) {
                constantArray = constant((Date) value);
            } else if (value instanceof Timestamp) {
                constantArray = constant((Timestamp) value);
            } else if (value instanceof Boolean) {
                constantArray = constant(((Boolean) value).booleanValue());
            } else if (value instanceof GeoPoint) {
                constantArray = constant((GeoPoint) value);
            } else if (value instanceof Blob) {
                constantArray = constant((Blob) value);
            } else if (value instanceof DocumentReference) {
                constantArray = constant((DocumentReference) value);
            } else if (value instanceof byte[]) {
                constantArray = constant((byte[]) value);
            } else if (value instanceof VectorValue) {
                constantArray = constant((VectorValue) value);
            } else if (value instanceof Value) {
                constantArray = new Constant((Value) value);
            } else if (value instanceof Map) {
                ArrayList arrayList = new ArrayList();
                for (Map.Entry entry : ((Map) value).entrySet()) {
                    Object key = entry.getKey();
                    if (!(key instanceof String)) {
                        throw new IllegalArgumentException("Maps with non-string keys are not supported");
                    }
                    CollectionsKt.addAll(arrayList, CollectionsKt.listOf((Object[]) new Expression[]{Expression.INSTANCE.constant((String) key), toExprOrConstant$com_google_firebase_firebase_firestore(entry.getValue())}));
                }
                constantArray = map$com_google_firebase_firebase_firestore((Expression[]) arrayList.toArray(new Expression[0]));
            } else {
                constantArray = value instanceof List ? array((List<? extends Object>) value) : null;
            }
            return constantArray == null ? pojoToExprOrConstant(CustomClassMapper.convertToPlainJavaTypes(value)) : constantArray;
        }

        private final Expression pojoToExprOrConstant(Object value) {
            Constant constantArray;
            if (value == null) {
                constantArray = Expression.NULL;
            } else if (value instanceof Expression) {
                constantArray = (Expression) value;
            } else if (value instanceof String) {
                constantArray = constant((String) value);
            } else if (value instanceof Number) {
                constantArray = constant((Number) value);
            } else if (value instanceof Date) {
                constantArray = constant((Date) value);
            } else if (value instanceof Timestamp) {
                constantArray = constant((Timestamp) value);
            } else if (value instanceof Boolean) {
                constantArray = constant(((Boolean) value).booleanValue());
            } else if (value instanceof GeoPoint) {
                constantArray = constant((GeoPoint) value);
            } else if (value instanceof Blob) {
                constantArray = constant((Blob) value);
            } else if (value instanceof DocumentReference) {
                constantArray = constant((DocumentReference) value);
            } else if (value instanceof byte[]) {
                constantArray = constant((byte[]) value);
            } else if (value instanceof VectorValue) {
                constantArray = constant((VectorValue) value);
            } else if (value instanceof Value) {
                constantArray = new Constant((Value) value);
            } else if (value instanceof Map) {
                ArrayList arrayList = new ArrayList();
                for (Map.Entry entry : ((Map) value).entrySet()) {
                    Object key = entry.getKey();
                    if (!(key instanceof String)) {
                        throw new IllegalArgumentException("Maps with non-string keys are not supported");
                    }
                    CollectionsKt.addAll(arrayList, CollectionsKt.listOf((Object[]) new Expression[]{Expression.INSTANCE.constant((String) key), pojoToExprOrConstant(entry.getValue())}));
                }
                constantArray = map$com_google_firebase_firebase_firestore((Expression[]) arrayList.toArray(new Expression[0]));
            } else {
                constantArray = value instanceof List ? array((List<? extends Object>) value) : null;
            }
            if (constantArray != null) {
                return constantArray;
            }
            throw new IllegalArgumentException("Unknown type: " + value);
        }

        private final Expression[] toArrayOfExprOrConstant(Iterable<? extends Object> others) {
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(others, 10));
            Iterator<? extends Object> it = others.iterator();
            while (it.hasNext()) {
                arrayList.add(toExprOrConstant$com_google_firebase_firebase_firestore(it.next()));
            }
            return (Expression[]) arrayList.toArray(new Expression[0]);
        }

        public final Expression[] toArrayOfExprOrConstant$com_google_firebase_firebase_firestore(Object[] others) {
            Intrinsics.checkNotNullParameter(others, "others");
            ArrayList arrayList = new ArrayList(others.length);
            for (Object obj : others) {
                arrayList.add(toExprOrConstant$com_google_firebase_firebase_firestore(obj));
            }
            return (Expression[]) arrayList.toArray(new Expression[0]);
        }

        @JvmStatic
        public final Expression map(Map<String, ? extends Object> elements) {
            Intrinsics.checkNotNullParameter(elements, "elements");
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<String, ? extends Object> entry : elements.entrySet()) {
                CollectionsKt.addAll(arrayList, CollectionsKt.listOf((Object[]) new Expression[]{Expression.INSTANCE.constant(entry.getKey()), Expression.INSTANCE.toExprOrConstant$com_google_firebase_firebase_firestore(entry.getValue())}));
            }
            return map$com_google_firebase_firebase_firestore((Expression[]) arrayList.toArray(new Expression[0]));
        }
    }

    public final Expression bitAnd(Expression bitsOther) {
        Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
        return INSTANCE.bitAnd(this, bitsOther);
    }

    public final Expression bitAnd(byte[] bitsOther) {
        Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
        return INSTANCE.bitAnd(this, bitsOther);
    }

    public final Expression bitOr(Expression bitsOther) {
        Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
        return INSTANCE.bitOr(this, bitsOther);
    }

    public final Expression bitOr(byte[] bitsOther) {
        Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
        return INSTANCE.bitOr(this, bitsOther);
    }

    public final Expression bitXor(Expression bitsOther) {
        Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
        return INSTANCE.bitXor(this, bitsOther);
    }

    public final Expression bitXor(byte[] bitsOther) {
        Intrinsics.checkNotNullParameter(bitsOther, "bitsOther");
        return INSTANCE.bitXor(this, bitsOther);
    }

    public final Expression bitNot() {
        return INSTANCE.bitNot(this);
    }

    public final Expression bitLeftShift(Expression numberExpr) {
        Intrinsics.checkNotNullParameter(numberExpr, "numberExpr");
        return INSTANCE.bitLeftShift(this, numberExpr);
    }

    public final Expression bitLeftShift(int number) {
        return INSTANCE.bitLeftShift(this, number);
    }

    public final Expression bitRightShift(Expression numberExpr) {
        Intrinsics.checkNotNullParameter(numberExpr, "numberExpr");
        return INSTANCE.bitRightShift(this, numberExpr);
    }

    public final Expression bitRightShift(int number) {
        return INSTANCE.bitRightShift(this, number);
    }

    public Selectable alias(String alias) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        return new AliasedExpression(alias, this);
    }

    public final Expression documentId() {
        return INSTANCE.documentId(this);
    }

    public final Expression collectionId() {
        return INSTANCE.collectionId(this);
    }

    public final Expression abs() {
        return INSTANCE.abs(this);
    }

    public final Expression exp() {
        return INSTANCE.exp(this);
    }

    public final Expression add(Expression second) {
        Intrinsics.checkNotNullParameter(second, "second");
        return INSTANCE.add(this, second);
    }

    public final Expression add(Number second) {
        Intrinsics.checkNotNullParameter(second, "second");
        return INSTANCE.add(this, second);
    }

    public final Expression subtract(Expression subtrahend) {
        Intrinsics.checkNotNullParameter(subtrahend, "subtrahend");
        return INSTANCE.subtract(this, subtrahend);
    }

    public final Expression subtract(Number subtrahend) {
        Intrinsics.checkNotNullParameter(subtrahend, "subtrahend");
        return INSTANCE.subtract(this, subtrahend);
    }

    public final Expression multiply(Expression second) {
        Intrinsics.checkNotNullParameter(second, "second");
        return INSTANCE.multiply(this, second);
    }

    public final Expression multiply(Number second) {
        Intrinsics.checkNotNullParameter(second, "second");
        return INSTANCE.multiply(this, second);
    }

    public final Expression divide(Expression divisor) {
        Intrinsics.checkNotNullParameter(divisor, "divisor");
        return INSTANCE.divide(this, divisor);
    }

    public final Expression divide(Number divisor) {
        Intrinsics.checkNotNullParameter(divisor, "divisor");
        return INSTANCE.divide(this, divisor);
    }

    public final Expression mod(Expression divisor) {
        Intrinsics.checkNotNullParameter(divisor, "divisor");
        return INSTANCE.mod(this, divisor);
    }

    public final Expression mod(Number divisor) {
        Intrinsics.checkNotNullParameter(divisor, "divisor");
        return INSTANCE.mod(this, divisor);
    }

    public final Expression round() {
        return INSTANCE.round(this);
    }

    public final Expression roundToPrecision(int decimalPlace) {
        return INSTANCE.roundToPrecision(this, decimalPlace);
    }

    public final Expression roundToPrecision(Expression decimalPlace) {
        Intrinsics.checkNotNullParameter(decimalPlace, "decimalPlace");
        return INSTANCE.roundToPrecision(this, decimalPlace);
    }

    public final Expression ceil() {
        return INSTANCE.ceil(this);
    }

    public final Expression floor() {
        return INSTANCE.floor(this);
    }

    public final Expression pow(Number exponent) {
        Intrinsics.checkNotNullParameter(exponent, "exponent");
        return INSTANCE.pow(this, exponent);
    }

    public final Expression pow(Expression exponent) {
        Intrinsics.checkNotNullParameter(exponent, "exponent");
        return INSTANCE.pow(this, exponent);
    }

    public final Expression sqrt() {
        return INSTANCE.sqrt(this);
    }

    public final Expression ln() {
        return INSTANCE.ln(this);
    }

    public final Expression log10() {
        return INSTANCE.log10(this);
    }

    public final BooleanExpression equalAny(List<? extends Object> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        return INSTANCE.equalAny(this, values);
    }

    public final BooleanExpression equalAny(Expression arrayExpression) {
        Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
        return INSTANCE.equalAny(this, arrayExpression);
    }

    public final BooleanExpression notEqualAny(List<? extends Object> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        return INSTANCE.notEqualAny(this, values);
    }

    public final BooleanExpression notEqualAny(Expression arrayExpression) {
        Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
        return INSTANCE.notEqualAny(this, arrayExpression);
    }

    public final BooleanExpression isAbsent() {
        return INSTANCE.isAbsent(this);
    }

    public final BooleanExpression isNan$com_google_firebase_firebase_firestore() {
        return INSTANCE.isNan$com_google_firebase_firebase_firestore(this);
    }

    public final BooleanExpression isNotNan$com_google_firebase_firebase_firestore() {
        return INSTANCE.isNotNan$com_google_firebase_firebase_firestore(this);
    }

    public final BooleanExpression isNull$com_google_firebase_firebase_firestore() {
        return INSTANCE.isNull$com_google_firebase_firebase_firestore(this);
    }

    public final BooleanExpression isNotNull$com_google_firebase_firebase_firestore() {
        return INSTANCE.isNotNull$com_google_firebase_firebase_firestore(this);
    }

    public final Expression length() {
        return INSTANCE.length(this);
    }

    public final Expression charLength() {
        return INSTANCE.charLength(this);
    }

    public final Expression byteLength() {
        return INSTANCE.byteLength(this);
    }

    public final BooleanExpression like(Expression pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        return INSTANCE.like(this, pattern);
    }

    public final Expression type() {
        return INSTANCE.type(this);
    }

    public final Expression split(Expression delimiter) {
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        return INSTANCE.split(this, delimiter);
    }

    public final Expression split(String delimiter) {
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        return INSTANCE.split(this, delimiter);
    }

    public final Expression split(Blob delimiter) {
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        return INSTANCE.split(this, delimiter);
    }

    public final Expression join(String delimiter) {
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        return INSTANCE.join(this, delimiter);
    }

    public final Expression join(Expression delimiterExpression) {
        Intrinsics.checkNotNullParameter(delimiterExpression, "delimiterExpression");
        return INSTANCE.join(this, delimiterExpression);
    }

    public final BooleanExpression like(String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        return INSTANCE.like(this, pattern);
    }

    public final BooleanExpression regexContains(Expression pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        return INSTANCE.regexContains(this, pattern);
    }

    public final BooleanExpression regexContains(String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        return INSTANCE.regexContains(this, pattern);
    }

    public final BooleanExpression regexMatch(Expression pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        return INSTANCE.regexMatch(this, pattern);
    }

    public final BooleanExpression regexMatch(String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        return INSTANCE.regexMatch(this, pattern);
    }

    public final Expression logicalMaximum(Expression... others) {
        Intrinsics.checkNotNullParameter(others, "others");
        return INSTANCE.logicalMaximum(this, Arrays.copyOf(others, others.length));
    }

    public final Expression logicalMaximum(Object... others) {
        Intrinsics.checkNotNullParameter(others, "others");
        return INSTANCE.logicalMaximum(this, Arrays.copyOf(others, others.length));
    }

    public final Expression logicalMinimum(Expression... others) {
        Intrinsics.checkNotNullParameter(others, "others");
        return INSTANCE.logicalMinimum(this, Arrays.copyOf(others, others.length));
    }

    public final Expression logicalMinimum(Object... others) {
        Intrinsics.checkNotNullParameter(others, "others");
        return INSTANCE.logicalMinimum(this, Arrays.copyOf(others, others.length));
    }

    public final Expression reverse() {
        return INSTANCE.reverse(this);
    }

    public final BooleanExpression stringContains(Expression substring) {
        Intrinsics.checkNotNullParameter(substring, "substring");
        return INSTANCE.stringContains(this, substring);
    }

    public final BooleanExpression stringContains(String substring) {
        Intrinsics.checkNotNullParameter(substring, "substring");
        return INSTANCE.stringContains(this, substring);
    }

    public final BooleanExpression startsWith(Expression prefix) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return INSTANCE.startsWith(this, prefix);
    }

    public final BooleanExpression startsWith(String prefix) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return INSTANCE.startsWith(this, prefix);
    }

    public final BooleanExpression endsWith(Expression suffix) {
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return INSTANCE.endsWith(this, suffix);
    }

    public final BooleanExpression endsWith(String suffix) {
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return INSTANCE.endsWith(this, suffix);
    }

    public final Expression stringReverse() {
        return INSTANCE.stringReverse(this);
    }

    public final Expression substring(Expression start, Expression length) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(length, "length");
        return INSTANCE.substring(this, start, length);
    }

    public final Expression substring(int start, int length) {
        Companion companion = INSTANCE;
        return companion.substring(this, companion.constant(Integer.valueOf(start)), companion.constant(Integer.valueOf(length)));
    }

    public final Expression toLower() {
        return INSTANCE.toLower(this);
    }

    public final Expression toUpper() {
        return INSTANCE.toUpper(this);
    }

    public final Expression trim() {
        return INSTANCE.trim(this);
    }

    public final Expression trimValue(String valueToTrim) {
        Intrinsics.checkNotNullParameter(valueToTrim, "valueToTrim");
        Companion companion = INSTANCE;
        return companion.trimValue(this, companion.constant(valueToTrim));
    }

    public final Expression trimValue(Expression valueToTrim) {
        Intrinsics.checkNotNullParameter(valueToTrim, "valueToTrim");
        return INSTANCE.trimValue(this, valueToTrim);
    }

    public final Expression stringConcat(Expression... stringExpressions) {
        Intrinsics.checkNotNullParameter(stringExpressions, "stringExpressions");
        return INSTANCE.stringConcat(this, (Expression[]) Arrays.copyOf(stringExpressions, stringExpressions.length));
    }

    public final Expression stringConcat(String... strings) {
        Intrinsics.checkNotNullParameter(strings, "strings");
        return INSTANCE.stringConcat(this, Arrays.copyOf(strings, strings.length));
    }

    public final Expression stringConcat(Object... strings) {
        Intrinsics.checkNotNullParameter(strings, "strings");
        return INSTANCE.stringConcat(this, Arrays.copyOf(strings, strings.length));
    }

    public final Expression mapGet(Expression keyExpression) {
        Intrinsics.checkNotNullParameter(keyExpression, "keyExpression");
        return INSTANCE.mapGet(this, keyExpression);
    }

    public final Expression mapGet(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return INSTANCE.mapGet(this, key);
    }

    public final Expression mapMerge(Expression mapExpr, Expression... otherMaps) {
        Intrinsics.checkNotNullParameter(mapExpr, "mapExpr");
        Intrinsics.checkNotNullParameter(otherMaps, "otherMaps");
        return INSTANCE.mapMerge(this, mapExpr, (Expression[]) Arrays.copyOf(otherMaps, otherMaps.length));
    }

    public final Expression mapRemove(Expression keyExpression) {
        Intrinsics.checkNotNullParameter(keyExpression, "keyExpression");
        return INSTANCE.mapRemove(this, keyExpression);
    }

    public final Expression mapRemove(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return INSTANCE.mapRemove(this, key);
    }

    public final Expression cosineDistance(Expression vector) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        return INSTANCE.cosineDistance(this, vector);
    }

    public final Expression cosineDistance(double[] vector) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        return INSTANCE.cosineDistance(this, vector);
    }

    public final Expression cosineDistance(VectorValue vector) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        return INSTANCE.cosineDistance(this, vector);
    }

    public final Expression dotProduct(Expression vector) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        return INSTANCE.dotProduct(this, vector);
    }

    public final Expression dotProduct(double[] vector) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        return INSTANCE.dotProduct(this, vector);
    }

    public final Expression dotProduct(VectorValue vector) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        return INSTANCE.dotProduct(this, vector);
    }

    public final Expression euclideanDistance(Expression vector) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        return INSTANCE.euclideanDistance(this, vector);
    }

    public final Expression euclideanDistance(double[] vector) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        return INSTANCE.euclideanDistance(this, vector);
    }

    public final Expression euclideanDistance(VectorValue vector) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        return INSTANCE.euclideanDistance(this, vector);
    }

    public final Expression vectorLength() {
        return INSTANCE.vectorLength(this);
    }

    public final Expression unixMicrosToTimestamp() {
        return INSTANCE.unixMicrosToTimestamp(this);
    }

    public final Expression timestampToUnixMicros() {
        return INSTANCE.timestampToUnixMicros(this);
    }

    public final Expression unixMillisToTimestamp() {
        return INSTANCE.unixMillisToTimestamp(this);
    }

    public final Expression timestampToUnixMillis() {
        return INSTANCE.timestampToUnixMillis(this);
    }

    public final Expression unixSecondsToTimestamp() {
        return INSTANCE.unixSecondsToTimestamp(this);
    }

    public final Expression timestampToUnixSeconds() {
        return INSTANCE.timestampToUnixSeconds(this);
    }

    public final Expression timestampAdd(Expression unit, Expression amount) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(amount, "amount");
        return INSTANCE.timestampAdd(this, unit, amount);
    }

    public final Expression timestampAdd(String unit, long amount) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return INSTANCE.timestampAdd(this, unit, amount);
    }

    public final Expression timestampTruncate(String granularity) {
        Intrinsics.checkNotNullParameter(granularity, "granularity");
        return INSTANCE.timestampTruncate(this, granularity);
    }

    public final Expression timestampTruncate(Expression granularity) {
        Intrinsics.checkNotNullParameter(granularity, "granularity");
        return INSTANCE.timestampTruncate(this, granularity);
    }

    public final Expression timestampSubtract(Expression unit, Expression amount) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(amount, "amount");
        return INSTANCE.timestampSubtract(this, unit, amount);
    }

    public final Expression timestampSubtract(String unit, long amount) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return INSTANCE.timestampSubtract(this, unit, amount);
    }

    public final Expression concat(Expression second, Object... others) {
        Intrinsics.checkNotNullParameter(second, "second");
        Intrinsics.checkNotNullParameter(others, "others");
        return INSTANCE.concat(this, second, Arrays.copyOf(others, others.length));
    }

    public final Expression concat(Object second, Object... others) {
        Intrinsics.checkNotNullParameter(second, "second");
        Intrinsics.checkNotNullParameter(others, "others");
        return INSTANCE.concat(this, second, Arrays.copyOf(others, others.length));
    }

    public final Expression arrayConcat(Expression secondArray, Object... otherArrays) {
        Intrinsics.checkNotNullParameter(secondArray, "secondArray");
        Intrinsics.checkNotNullParameter(otherArrays, "otherArrays");
        return INSTANCE.arrayConcat(this, secondArray, Arrays.copyOf(otherArrays, otherArrays.length));
    }

    public final Expression arrayConcat(Object secondArray, Object... otherArrays) {
        Intrinsics.checkNotNullParameter(secondArray, "secondArray");
        Intrinsics.checkNotNullParameter(otherArrays, "otherArrays");
        return INSTANCE.arrayConcat(this, secondArray, Arrays.copyOf(otherArrays, otherArrays.length));
    }

    public final Expression arrayReverse() {
        return INSTANCE.arrayReverse(this);
    }

    public final Expression arraySum() {
        return INSTANCE.arraySum(this);
    }

    public final BooleanExpression arrayContains(Expression element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return INSTANCE.arrayContains(this, element);
    }

    public final BooleanExpression arrayContains(Object element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return INSTANCE.arrayContains(this, element);
    }

    public final BooleanExpression arrayContainsAll(List<? extends Object> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        return INSTANCE.arrayContainsAll(this, values);
    }

    public final BooleanExpression arrayContainsAll(Expression arrayExpression) {
        Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
        return INSTANCE.arrayContainsAll(this, arrayExpression);
    }

    public final BooleanExpression arrayContainsAny(List<? extends Object> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        return INSTANCE.arrayContainsAny(this, values);
    }

    public final BooleanExpression arrayContainsAny(Expression arrayExpression) {
        Intrinsics.checkNotNullParameter(arrayExpression, "arrayExpression");
        return INSTANCE.arrayContainsAny(this, arrayExpression);
    }

    public final Expression arrayLength() {
        return INSTANCE.arrayLength(this);
    }

    public final Expression arrayGet(Expression offset) {
        Intrinsics.checkNotNullParameter(offset, "offset");
        return INSTANCE.arrayGet(this, offset);
    }

    public final Expression arrayGet(int offset) {
        return INSTANCE.arrayGet(this, offset);
    }

    public final AggregateFunction count() {
        return AggregateFunction.INSTANCE.count(this);
    }

    public final AggregateFunction countDistinct() {
        return AggregateFunction.INSTANCE.countDistinct(this);
    }

    public final AggregateFunction sum() {
        return AggregateFunction.INSTANCE.sum(this);
    }

    public final AggregateFunction average() {
        return AggregateFunction.INSTANCE.average(this);
    }

    public final AggregateFunction minimum() {
        return AggregateFunction.INSTANCE.minimum(this);
    }

    public final AggregateFunction maximum() {
        return AggregateFunction.INSTANCE.maximum(this);
    }

    public final Ordering ascending() {
        return Ordering.INSTANCE.ascending(this);
    }

    public final Ordering descending() {
        return Ordering.INSTANCE.descending(this);
    }

    public final BooleanExpression equal(Expression other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return INSTANCE.equal(this, other);
    }

    public final BooleanExpression equal(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return INSTANCE.equal(this, value);
    }

    public final BooleanExpression notEqual(Expression other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return INSTANCE.notEqual(this, other);
    }

    public final BooleanExpression notEqual(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return INSTANCE.notEqual(this, value);
    }

    public final BooleanExpression greaterThan(Expression other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return INSTANCE.greaterThan(this, other);
    }

    public final BooleanExpression greaterThan(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return INSTANCE.greaterThan(this, value);
    }

    public final BooleanExpression greaterThanOrEqual(Expression other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return INSTANCE.greaterThanOrEqual(this, other);
    }

    public final BooleanExpression greaterThanOrEqual(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return INSTANCE.greaterThanOrEqual(this, value);
    }

    public final BooleanExpression lessThan(Expression other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return INSTANCE.lessThan(this, other);
    }

    public final BooleanExpression lessThan(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return INSTANCE.lessThan(this, value);
    }

    public final BooleanExpression lessThanOrEqual(Expression other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return INSTANCE.lessThanOrEqual(this, other);
    }

    public final BooleanExpression lessThanOrEqual(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return INSTANCE.lessThanOrEqual(this, value);
    }

    public final BooleanExpression exists() {
        return INSTANCE.exists(this);
    }

    public final Expression ifError(Expression catchExpr) {
        Intrinsics.checkNotNullParameter(catchExpr, "catchExpr");
        return INSTANCE.ifError(this, catchExpr);
    }

    public final Expression ifError(Object catchValue) {
        Intrinsics.checkNotNullParameter(catchValue, "catchValue");
        return INSTANCE.ifError(this, catchValue);
    }

    public final Expression ifAbsent(Expression elseExpr) {
        Intrinsics.checkNotNullParameter(elseExpr, "elseExpr");
        return INSTANCE.ifAbsent(this, elseExpr);
    }

    public final Expression ifAbsent(Object elseValue) {
        Intrinsics.checkNotNullParameter(elseValue, "elseValue");
        return INSTANCE.ifAbsent(this, elseValue);
    }

    public final BooleanExpression isError() {
        return INSTANCE.isError(this);
    }

    public final BooleanExpression asBoolean() {
        if (this instanceof BooleanExpression) {
            return (BooleanExpression) this;
        }
        if (this instanceof Constant) {
            return new BooleanConstant((Constant) this);
        }
        if (this instanceof Field) {
            return new BooleanField((Field) this);
        }
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.google.firebase.firestore.pipeline.FunctionExpression");
        return new BooleanFunctionExpression((FunctionExpression) this);
    }
}
