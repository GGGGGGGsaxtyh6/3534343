package com.google.firebase.firestore.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.google.cloud.datastore.core.number.NumberComparisonHelper;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.VectorValue;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.ArrayValueOrBuilder;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.NullValue;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KFunction;
import kotlin.text.Regex;

/* JADX INFO: compiled from: Values.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u001c\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0088\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001f\u001a\u00020\u00102\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0007J\u001c\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u00072\b\u0010$\u001a\u0004\u0018\u00010\u0007H\u0007J\u0018\u0010%\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007H\u0002J\u0018\u0010&\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007H\u0002J\u0018\u0010'\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007H\u0002J\u001a\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0007H\u0007J\u0018\u0010,\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007H\u0007J \u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007H\u0002J(\u0010/\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00072\u0006\u00100\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00072\u0006\u00101\u001a\u00020\"H\u0007J(\u00102\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00072\u0006\u00100\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00072\u0006\u00101\u001a\u00020\"H\u0007J\u0018\u00103\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007H\u0002J\u0018\u00104\u001a\u00020\u00102\u0006\u0010#\u001a\u0002052\u0006\u0010$\u001a\u000205H\u0002J\u0018\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u0005H\u0002J\u0018\u00109\u001a\u00020\u00102\u0006\u0010#\u001a\u00020:2\u0006\u0010$\u001a\u00020:H\u0002J\u0018\u0010;\u001a\u00020\u00102\u0006\u0010#\u001a\u00020<2\u0006\u0010$\u001a\u00020<H\u0002J\u0018\u0010=\u001a\u00020\u00102\u0006\u0010#\u001a\u00020>2\u0006\u0010$\u001a\u00020>H\u0002J\u0018\u0010?\u001a\u00020\u00102\u0006\u0010#\u001a\u00020>2\u0006\u0010$\u001a\u00020>H\u0002J\u0010\u0010@\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0007H\u0007J\u001c\u0010A\u001a\u00020B2\n\u0010C\u001a\u00060Dj\u0002`E2\u0006\u0010 \u001a\u00020\u0007H\u0002J\u001c\u0010F\u001a\u00020B2\n\u0010C\u001a\u00060Dj\u0002`E2\u0006\u0010G\u001a\u000205H\u0002J\u001c\u0010H\u001a\u00020B2\n\u0010C\u001a\u00060Dj\u0002`E2\u0006\u0010I\u001a\u00020:H\u0002J\u001c\u0010J\u001a\u00020B2\n\u0010C\u001a\u00060Dj\u0002`E2\u0006\u0010 \u001a\u00020\u0007H\u0002J\u001c\u0010K\u001a\u00020B2\n\u0010C\u001a\u00060Dj\u0002`E2\u0006\u0010L\u001a\u00020>H\u0002J\u001c\u0010M\u001a\u00020B2\n\u0010C\u001a\u00060Dj\u0002`E2\u0006\u0010N\u001a\u00020<H\u0002J\u0012\u0010O\u001a\u00020\"2\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0007J\u0012\u0010P\u001a\u00020\"2\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0007J\u0012\u0010Q\u001a\u00020\"2\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0007J\u0012\u0010R\u001a\u00020\"2\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0007J\u0012\u0010S\u001a\u00020\"2\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0007J\u0012\u0010T\u001a\u00020\"2\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0007J\u0012\u0010U\u001a\u00020\"2\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0007J\u0012\u0010V\u001a\u00020\"2\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0007J\u0018\u0010W\u001a\u00020\u00072\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020[H\u0007J\u0010\u0010e\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0007J\u0010\u0010f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0007J\u0010\u0010g\u001a\u00020\"2\u0006\u0010 \u001a\u00020\u0007H\u0007J\u0010\u0010h\u001a\u00020\"2\u0006\u0010 \u001a\u00020\u0007H\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010 \u001a\u00020jH\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0010H\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010 \u001a\u00020kH\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010 \u001a\u00020lH\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010 \u001a\u00020mH\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0005H\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010 \u001a\u00020nH\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010o\u001a\u00020pH\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010G\u001a\u00020qH\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010 \u001a\u000205H\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\"H\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010t\u001a\u00020uH\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010 \u001a\u00020vH\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010 \u001a\u00020wH\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010x\u001a\u00020yH\u0007J\u0010\u0010i\u001a\u00020\u00072\u0006\u0010z\u001a\u00020{H\u0007J\u0010\u0010|\u001a\u00020\u00072\u0006\u0010z\u001a\u00020}H\u0007J\u001c\u0010i\u001a\u00020\u00072\u0012\u0010~\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\u007fH\u0007J\u0018\u0010i\u001a\u00020\u00072\u000e\u0010\u0080\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070\u0081\u0001H\u0007J\u0013\u0010\u0082\u0001\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0007J\u001a\u0010G\u001a\u0002052\u0007\u0010\u0083\u0001\u001a\u00020j2\u0007\u0010\u0084\u0001\u001a\u00020\u0010H\u0007J\u0015\u0010\u0085\u0001\u001a\u0004\u0018\u00010}2\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0007J\u001b\u0010\u0086\u0001\u001a\u00020B2\u0007\u0010\u0083\u0001\u001a\u00020j2\u0007\u0010\u0087\u0001\u001a\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010r\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010s\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0089\u0001"}, d2 = {"Lcom/google/firebase/firestore/model/Values;", "", "<init>", "()V", "TYPE_KEY", "", "NAN_VALUE", "Lcom/google/firestore/v1/Value;", "NULL_VALUE", "MIN_VALUE", "MAX_VALUE_TYPE", "MAX_VALUE", "VECTOR_VALUE_TYPE", "VECTOR_MAP_VECTORS_KEY", "MIN_VECTOR_VALUE", "TYPE_ORDER_NULL", "", "TYPE_ORDER_UNSET", "TYPE_ORDER_BOOLEAN", "TYPE_ORDER_NUMBER_NAN", "TYPE_ORDER_NUMBER", "TYPE_ORDER_TIMESTAMP", "TYPE_ORDER_SERVER_TIMESTAMP", "TYPE_ORDER_STRING", "TYPE_ORDER_BLOB", "TYPE_ORDER_REFERENCE", "TYPE_ORDER_GEOPOINT", "TYPE_ORDER_ARRAY", "TYPE_ORDER_VECTOR", "TYPE_ORDER_MAP", "TYPE_ORDER_MAX_VALUE", "typeOrder", Values.VECTOR_MAP_VECTORS_KEY, "equals", "", "left", "right", "numberEquals", "arrayEquals", "objectEquals", "contains", "haystack", "Lcom/google/firestore/v1/ArrayValueOrBuilder;", "needle", "compare", "compareInternal", "leftType", "lowerBoundCompare", "leftInclusive", "rightInclusive", "upperBoundCompare", "compareNumbers", "compareTimestamps", "Lcom/google/protobuf/Timestamp;", "compareReferences", "leftPath", "rightPath", "compareGeoPoints", "Lcom/google/type/LatLng;", "compareArrays", "Lcom/google/firestore/v1/ArrayValue;", "compareMaps", "Lcom/google/firestore/v1/MapValue;", "compareVectors", "canonicalId", "canonifyValue", "", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "canonifyTimestamp", "timestamp", "canonifyGeoPoint", "latLng", "canonifyReference", "canonifyObject", "mapValue", "canonifyArray", "arrayValue", "isInteger", "isDouble", "isNumber", "isArray", "isReferenceValue", "isNullValue", "isNanValue", "isMapValue", "refValue", "databaseId", "Lcom/google/firebase/firestore/model/DatabaseId;", "key", "Lcom/google/firebase/firestore/model/DocumentKey;", "MIN_BOOLEAN", "MIN_NUMBER", "MIN_TIMESTAMP", "MIN_STRING", "MIN_BYTES", "MIN_REFERENCE", "MIN_GEO_POINT", "MIN_ARRAY", "MIN_MAP", "getLowerBound", "getUpperBound", "isMaxValue", "isVectorValue", "encodeValue", "", "", "", "", "Lcom/google/firebase/firestore/model/ResourcePath;", "date", "Ljava/util/Date;", "Lcom/google/firebase/Timestamp;", "TRUE_VALUE", "FALSE_VALUE", "geoPoint", "Lcom/google/firebase/firestore/GeoPoint;", "", "Lcom/google/firebase/firestore/Blob;", "docRef", "Lcom/google/firebase/firestore/DocumentReference;", "vector", "Lcom/google/firebase/firestore/VectorValue;", "encodeVectorValue", "", "map", "", "values", "", "encodeAnyValue", "seconds", "nanos", "getVectorValue", "validateRange", "nanoseconds", "Enterprise", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class Values {
    public static final Value FALSE_VALUE;
    public static final Values INSTANCE = new Values();
    public static final Value MAX_VALUE;
    public static final Value MAX_VALUE_TYPE;
    private static final Value MIN_ARRAY;
    private static final Value MIN_BOOLEAN;
    private static final Value MIN_BYTES;
    private static final Value MIN_GEO_POINT;
    private static final Value MIN_MAP;
    private static final Value MIN_NUMBER;
    private static final Value MIN_REFERENCE;
    private static final Value MIN_STRING;
    private static final Value MIN_TIMESTAMP;
    public static final Value MIN_VALUE;
    private static final Value MIN_VECTOR_VALUE;
    public static final Value NAN_VALUE;
    public static final Value NULL_VALUE;
    public static final Value TRUE_VALUE;
    public static final String TYPE_KEY = "__type__";
    public static final int TYPE_ORDER_ARRAY = 15;
    public static final int TYPE_ORDER_BLOB = 8;
    public static final int TYPE_ORDER_BOOLEAN = 2;
    public static final int TYPE_ORDER_GEOPOINT = 13;
    public static final int TYPE_ORDER_MAP = 17;
    public static final int TYPE_ORDER_MAX_VALUE = Integer.MAX_VALUE;
    public static final int TYPE_ORDER_NULL = 0;
    public static final int TYPE_ORDER_NUMBER = 4;
    public static final int TYPE_ORDER_NUMBER_NAN = 3;
    public static final int TYPE_ORDER_REFERENCE = 10;
    public static final int TYPE_ORDER_SERVER_TIMESTAMP = 6;
    public static final int TYPE_ORDER_STRING = 7;
    public static final int TYPE_ORDER_TIMESTAMP = 5;
    public static final int TYPE_ORDER_UNSET = 0;
    public static final int TYPE_ORDER_VECTOR = 16;
    public static final String VECTOR_MAP_VECTORS_KEY = "value";
    public static final Value VECTOR_VALUE_TYPE;

    /* JADX INFO: compiled from: Values.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
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
            try {
                iArr[Value.ValueTypeCase.INTEGER_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Value.ValueTypeCase.DOUBLE_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Value.ValueTypeCase.TIMESTAMP_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Value.ValueTypeCase.STRING_VALUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Value.ValueTypeCase.BYTES_VALUE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[Value.ValueTypeCase.REFERENCE_VALUE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[Value.ValueTypeCase.GEO_POINT_VALUE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[Value.ValueTypeCase.ARRAY_VALUE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[Value.ValueTypeCase.MAP_VALUE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private Values() {
    }

    static {
        Value valueBuild = Value.newBuilder().setDoubleValue(Double.NaN).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        NAN_VALUE = valueBuild;
        Value valueBuild2 = Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild2, "build(...)");
        Value value = valueBuild2;
        NULL_VALUE = value;
        MIN_VALUE = value;
        Value valueBuild3 = Value.newBuilder().setStringValue("__max__").build();
        Intrinsics.checkNotNullExpressionValue(valueBuild3, "build(...)");
        Value value2 = valueBuild3;
        MAX_VALUE_TYPE = value2;
        Value valueBuild4 = Value.newBuilder().setMapValue(MapValue.newBuilder().putFields(TYPE_KEY, value2)).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild4, "build(...)");
        MAX_VALUE = valueBuild4;
        Value valueBuild5 = Value.newBuilder().setStringValue("__vector__").build();
        Intrinsics.checkNotNullExpressionValue(valueBuild5, "build(...)");
        Value value3 = valueBuild5;
        VECTOR_VALUE_TYPE = value3;
        Value valueBuild6 = Value.newBuilder().setMapValue(MapValue.newBuilder().putFields(TYPE_KEY, value3).putFields(VECTOR_MAP_VECTORS_KEY, Value.newBuilder().setArrayValue(ArrayValue.newBuilder()).build())).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild6, "build(...)");
        MIN_VECTOR_VALUE = valueBuild6;
        Value valueBuild7 = Value.newBuilder().setBooleanValue(false).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild7, "build(...)");
        MIN_BOOLEAN = valueBuild7;
        Value valueBuild8 = Value.newBuilder().setDoubleValue(Double.NaN).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild8, "build(...)");
        MIN_NUMBER = valueBuild8;
        Value valueBuild9 = Value.newBuilder().setTimestampValue(Timestamp.newBuilder().setSeconds(Long.MIN_VALUE)).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild9, "build(...)");
        MIN_TIMESTAMP = valueBuild9;
        Value valueBuild10 = Value.newBuilder().setStringValue("").build();
        Intrinsics.checkNotNullExpressionValue(valueBuild10, "build(...)");
        MIN_STRING = valueBuild10;
        Value valueBuild11 = Value.newBuilder().setBytesValue(ByteString.EMPTY).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild11, "build(...)");
        MIN_BYTES = valueBuild11;
        DatabaseId EMPTY = DatabaseId.EMPTY;
        Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        DocumentKey documentKeyEmpty = DocumentKey.empty();
        Intrinsics.checkNotNullExpressionValue(documentKeyEmpty, "empty(...)");
        MIN_REFERENCE = refValue(EMPTY, documentKeyEmpty);
        Value valueBuild12 = Value.newBuilder().setGeoPointValue(LatLng.newBuilder().setLatitude(-90.0d).setLongitude(-180.0d)).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild12, "build(...)");
        MIN_GEO_POINT = valueBuild12;
        Value valueBuild13 = Value.newBuilder().setArrayValue(ArrayValue.getDefaultInstance()).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild13, "build(...)");
        MIN_ARRAY = valueBuild13;
        Value valueBuild14 = Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild14, "build(...)");
        MIN_MAP = valueBuild14;
        Value valueBuild15 = Value.newBuilder().setBooleanValue(true).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild15, "build(...)");
        TRUE_VALUE = valueBuild15;
        Value valueBuild16 = Value.newBuilder().setBooleanValue(false).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild16, "build(...)");
        FALSE_VALUE = valueBuild16;
    }

    @JvmStatic
    public static final int typeOrder(Value value) {
        Value.ValueTypeCase valueTypeCase = value != null ? value.getValueTypeCase() : null;
        switch (valueTypeCase == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()]) {
            case -1:
            case 1:
                return 0;
            case 0:
            default:
                throw Assert.fail("Invalid value type: " + value.getValueTypeCase(), new Object[0]);
            case 2:
                return 2;
            case 4:
                if (Double.isNaN(value.getDoubleValue())) {
                    return 3;
                }
            case 3:
                return 4;
            case 5:
                return 5;
            case 6:
                return 7;
            case 7:
                return 8;
            case 8:
                return 10;
            case 9:
                return 13;
            case 10:
                return 15;
            case 11:
                if (ServerTimestamps.isServerTimestamp(value)) {
                    return 6;
                }
                if (isMaxValue(value)) {
                    return Integer.MAX_VALUE;
                }
                return isVectorValue(value) ? 16 : 17;
        }
    }

    @JvmStatic
    public static final boolean equals(Value left, Value right) {
        if (left == right) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        Values values = INSTANCE;
        int iTypeOrder = typeOrder(left);
        if (iTypeOrder != typeOrder(right)) {
            return false;
        }
        if (iTypeOrder == 3 || iTypeOrder == 4) {
            return values.numberEquals(left, right);
        }
        if (iTypeOrder == 6) {
            return Intrinsics.areEqual(ServerTimestamps.getLocalWriteTime(left), ServerTimestamps.getLocalWriteTime(right));
        }
        if (iTypeOrder == Integer.MAX_VALUE) {
            return true;
        }
        switch (iTypeOrder) {
            case 15:
                return values.arrayEquals(left, right);
            case 16:
            case 17:
                return values.objectEquals(left, right);
            default:
                return Intrinsics.areEqual(left, right);
        }
    }

    private final boolean numberEquals(Value left, Value right) {
        int i;
        Value.ValueTypeCase valueTypeCase = left.getValueTypeCase();
        int i2 = valueTypeCase == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()];
        if (i2 == 3) {
            Value.ValueTypeCase valueTypeCase2 = right.getValueTypeCase();
            i = valueTypeCase2 != null ? WhenMappings.$EnumSwitchMapping$0[valueTypeCase2.ordinal()] : -1;
            return i != 3 ? i == 4 && NumberComparisonHelper.firestoreCompareDoubleWithLong(right.getDoubleValue(), left.getIntegerValue()) == 0 : left.getIntegerValue() == right.getIntegerValue();
        }
        if (i2 != 4) {
            return false;
        }
        Value.ValueTypeCase valueTypeCase3 = right.getValueTypeCase();
        i = valueTypeCase3 != null ? WhenMappings.$EnumSwitchMapping$0[valueTypeCase3.ordinal()] : -1;
        return i != 3 ? i == 4 && NumberComparisonHelper.firestoreCompareDoubles(left.getDoubleValue(), right.getDoubleValue()) == 0 : NumberComparisonHelper.firestoreCompareDoubleWithLong(left.getDoubleValue(), right.getIntegerValue()) == 0;
    }

    private final boolean arrayEquals(Value left, Value right) {
        ArrayValue arrayValue = left.getArrayValue();
        ArrayValue arrayValue2 = right.getArrayValue();
        if (arrayValue.getValuesCount() != arrayValue2.getValuesCount()) {
            return false;
        }
        int valuesCount = arrayValue.getValuesCount();
        for (int i = 0; i < valuesCount; i++) {
            if (!equals(arrayValue.getValues(i), arrayValue2.getValues(i))) {
                return false;
            }
        }
        return true;
    }

    private final boolean objectEquals(Value left, Value right) {
        MapValue mapValue = left.getMapValue();
        MapValue mapValue2 = right.getMapValue();
        if (mapValue.getFieldsCount() != mapValue2.getFieldsCount()) {
            return false;
        }
        Map<String, Value> fieldsMap = mapValue.getFieldsMap();
        Intrinsics.checkNotNullExpressionValue(fieldsMap, "getFieldsMap(...)");
        for (Map.Entry<String, Value> entry : fieldsMap.entrySet()) {
            String key = entry.getKey();
            Value value = entry.getValue();
            Value value2 = mapValue2.getFieldsMap().get(key);
            if (value2 == null || !equals(value, value2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: compiled from: Values.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0000¢\u0006\u0002\b\tJ!\u0010\u000f\u001a\u00020\u00102\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0000¢\u0006\u0002\b\u0011R&\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\f0\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/google/firebase/firestore/model/Values$Enterprise;", "", "<init>", "()V", "equals", "", "left", "Lcom/google/firestore/v1/Value;", "right", "equals$com_google_firebase_firebase_firestore", "compare", "Lkotlin/reflect/KFunction2;", "", "getCompare$com_google_firebase_firebase_firestore", "()Lkotlin/reflect/KFunction;", "strictCompare", "Lcom/google/firebase/firestore/model/Values$Enterprise$CompareResult;", "strictCompare$com_google_firebase_firebase_firestore", "CompareResult", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Enterprise {
        public static final Enterprise INSTANCE = new Enterprise();
        private static final KFunction<Integer> compare = new Values$Enterprise$compare$1(Values.INSTANCE);

        private Enterprise() {
        }

        public final boolean equals$com_google_firebase_firebase_firestore(Value left, Value right) {
            return Values.equals(left, right);
        }

        public final KFunction<Integer> getCompare$com_google_firebase_firebase_firestore() {
            return compare;
        }

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* JADX INFO: compiled from: Values.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/google/firebase/firestore/model/Values$Enterprise$CompareResult;", "", "<init>", "(Ljava/lang/String;I)V", "LESS_THAN", "EQUAL", "GREATER_THAN", "TYPE_MISMATCH", "com.google.firebase-firebase-firestore"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class CompareResult {
            private static final /* synthetic */ EnumEntries $ENTRIES;
            private static final /* synthetic */ CompareResult[] $VALUES;
            public static final CompareResult LESS_THAN = new CompareResult("LESS_THAN", 0);
            public static final CompareResult EQUAL = new CompareResult("EQUAL", 1);
            public static final CompareResult GREATER_THAN = new CompareResult("GREATER_THAN", 2);
            public static final CompareResult TYPE_MISMATCH = new CompareResult("TYPE_MISMATCH", 3);

            private static final /* synthetic */ CompareResult[] $values() {
                return new CompareResult[]{LESS_THAN, EQUAL, GREATER_THAN, TYPE_MISMATCH};
            }

            public static EnumEntries<CompareResult> getEntries() {
                return $ENTRIES;
            }

            private CompareResult(String str, int i) {
            }

            static {
                CompareResult[] compareResultArr$values = $values();
                $VALUES = compareResultArr$values;
                $ENTRIES = EnumEntriesKt.enumEntries(compareResultArr$values);
            }

            public static CompareResult valueOf(String str) {
                return (CompareResult) Enum.valueOf(CompareResult.class, str);
            }

            public static CompareResult[] values() {
                return (CompareResult[]) $VALUES.clone();
            }
        }

        public final CompareResult strictCompare$com_google_firebase_firebase_firestore(Value left, Value right) {
            if (left == null && right == null) {
                return CompareResult.EQUAL;
            }
            if (left == null || right == null) {
                return CompareResult.TYPE_MISMATCH;
            }
            int iTypeOrder = Values.typeOrder(left);
            if (iTypeOrder == Values.typeOrder(right)) {
                int iCompareInternal = Values.INSTANCE.compareInternal(iTypeOrder, left, right);
                if (iCompareInternal < 0) {
                    return CompareResult.LESS_THAN;
                }
                if (iCompareInternal > 0) {
                    return CompareResult.GREATER_THAN;
                }
                return CompareResult.EQUAL;
            }
            return CompareResult.TYPE_MISMATCH;
        }
    }

    @JvmStatic
    public static final boolean contains(ArrayValueOrBuilder haystack, Value needle) {
        Intrinsics.checkNotNullParameter(haystack, "haystack");
        Iterator<Value> it = haystack.getValuesList().iterator();
        while (it.hasNext()) {
            if (equals(it.next(), needle)) {
                return true;
            }
        }
        return false;
    }

    @JvmStatic
    public static final int compare(Value left, Value right) {
        Intrinsics.checkNotNullParameter(left, "left");
        Intrinsics.checkNotNullParameter(right, "right");
        Values values = INSTANCE;
        int iTypeOrder = typeOrder(left);
        int iTypeOrder2 = typeOrder(right);
        if (iTypeOrder != iTypeOrder2) {
            return Intrinsics.compare(iTypeOrder, iTypeOrder2);
        }
        return values.compareInternal(iTypeOrder, left, right);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int compareInternal(int leftType, Value left, Value right) {
        if (leftType != 0) {
            if (leftType == 10) {
                String referenceValue = left.getReferenceValue();
                Intrinsics.checkNotNullExpressionValue(referenceValue, "getReferenceValue(...)");
                String referenceValue2 = right.getReferenceValue();
                Intrinsics.checkNotNullExpressionValue(referenceValue2, "getReferenceValue(...)");
                return compareReferences(referenceValue, referenceValue2);
            }
            if (leftType == 13) {
                LatLng geoPointValue = left.getGeoPointValue();
                Intrinsics.checkNotNullExpressionValue(geoPointValue, "getGeoPointValue(...)");
                LatLng geoPointValue2 = right.getGeoPointValue();
                Intrinsics.checkNotNullExpressionValue(geoPointValue2, "getGeoPointValue(...)");
                return compareGeoPoints(geoPointValue, geoPointValue2);
            }
            if (leftType != Integer.MAX_VALUE) {
                switch (leftType) {
                    case 2:
                        return Boolean.compare(left.getBooleanValue(), right.getBooleanValue());
                    case 3:
                        break;
                    case 4:
                        return compareNumbers(left, right);
                    case 5:
                        Timestamp timestampValue = left.getTimestampValue();
                        Intrinsics.checkNotNullExpressionValue(timestampValue, "getTimestampValue(...)");
                        Timestamp timestampValue2 = right.getTimestampValue();
                        Intrinsics.checkNotNullExpressionValue(timestampValue2, "getTimestampValue(...)");
                        return compareTimestamps(timestampValue, timestampValue2);
                    case 6:
                        Timestamp localWriteTime = ServerTimestamps.getLocalWriteTime(left);
                        Intrinsics.checkNotNullExpressionValue(localWriteTime, "getLocalWriteTime(...)");
                        Timestamp localWriteTime2 = ServerTimestamps.getLocalWriteTime(right);
                        Intrinsics.checkNotNullExpressionValue(localWriteTime2, "getLocalWriteTime(...)");
                        return compareTimestamps(localWriteTime, localWriteTime2);
                    case 7:
                        return Util.compareUtf8Strings(left.getStringValue(), right.getStringValue());
                    case 8:
                        return Util.compareByteStrings(left.getBytesValue(), right.getBytesValue());
                    default:
                        switch (leftType) {
                            case 15:
                                ArrayValue arrayValue = left.getArrayValue();
                                Intrinsics.checkNotNullExpressionValue(arrayValue, "getArrayValue(...)");
                                ArrayValue arrayValue2 = right.getArrayValue();
                                Intrinsics.checkNotNullExpressionValue(arrayValue2, "getArrayValue(...)");
                                return compareArrays(arrayValue, arrayValue2);
                            case 16:
                                MapValue mapValue = left.getMapValue();
                                Intrinsics.checkNotNullExpressionValue(mapValue, "getMapValue(...)");
                                MapValue mapValue2 = right.getMapValue();
                                Intrinsics.checkNotNullExpressionValue(mapValue2, "getMapValue(...)");
                                return compareVectors(mapValue, mapValue2);
                            case 17:
                                MapValue mapValue3 = left.getMapValue();
                                Intrinsics.checkNotNullExpressionValue(mapValue3, "getMapValue(...)");
                                MapValue mapValue4 = right.getMapValue();
                                Intrinsics.checkNotNullExpressionValue(mapValue4, "getMapValue(...)");
                                return compareMaps(mapValue3, mapValue4);
                            default:
                                throw Assert.fail("Invalid value type: " + leftType, new Object[0]);
                        }
                }
            }
        }
        return 0;
    }

    @JvmStatic
    public static final int lowerBoundCompare(Value left, boolean leftInclusive, Value right, boolean rightInclusive) {
        Intrinsics.checkNotNullParameter(left, "left");
        Intrinsics.checkNotNullParameter(right, "right");
        int iCompare = compare(left, right);
        if (iCompare != 0) {
            return iCompare;
        }
        if (!leftInclusive || rightInclusive) {
            return (leftInclusive || !rightInclusive) ? 0 : 1;
        }
        return -1;
    }

    @JvmStatic
    public static final int upperBoundCompare(Value left, boolean leftInclusive, Value right, boolean rightInclusive) {
        Intrinsics.checkNotNullParameter(left, "left");
        Intrinsics.checkNotNullParameter(right, "right");
        int iCompare = compare(left, right);
        if (iCompare != 0) {
            return iCompare;
        }
        if (!leftInclusive || rightInclusive) {
            return (leftInclusive || !rightInclusive) ? 0 : -1;
        }
        return 1;
    }

    private final int compareNumbers(Value left, Value right) {
        if (left.hasDoubleValue()) {
            if (right.hasDoubleValue()) {
                return NumberComparisonHelper.firestoreCompareDoubles(left.getDoubleValue(), right.getDoubleValue());
            }
            if (right.hasIntegerValue()) {
                return NumberComparisonHelper.firestoreCompareDoubleWithLong(left.getDoubleValue(), right.getIntegerValue());
            }
        } else if (left.hasIntegerValue()) {
            if (right.hasIntegerValue()) {
                return Long.compare(left.getIntegerValue(), right.getIntegerValue());
            }
            if (right.hasDoubleValue()) {
                return NumberComparisonHelper.firestoreCompareDoubleWithLong(right.getDoubleValue(), left.getIntegerValue()) * (-1);
            }
        }
        throw Assert.fail("Unexpected values: %s vs %s", left, right);
    }

    private final int compareTimestamps(Timestamp left, Timestamp right) {
        int iCompare = Intrinsics.compare(left.getSeconds(), right.getSeconds());
        return iCompare != 0 ? iCompare : Intrinsics.compare(left.getNanos(), right.getNanos());
    }

    private final int compareReferences(String leftPath, String rightPath) {
        String[] strArr = (String[]) new Regex(DomExceptionUtils.SEPARATOR).split(leftPath, 0).toArray(new String[0]);
        String[] strArr2 = (String[]) new Regex(DomExceptionUtils.SEPARATOR).split(rightPath, 0).toArray(new String[0]);
        int iMin = (int) Math.min(strArr.length, strArr2.length);
        for (int i = 0; i < iMin; i++) {
            int iCompareTo = strArr[i].compareTo(strArr2[i]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Intrinsics.compare(strArr.length, strArr2.length);
    }

    private final int compareGeoPoints(LatLng left, LatLng right) {
        int iFirestoreCompareDoubles = NumberComparisonHelper.firestoreCompareDoubles(left.getLatitude(), right.getLatitude());
        return iFirestoreCompareDoubles == 0 ? NumberComparisonHelper.firestoreCompareDoubles(left.getLongitude(), right.getLongitude()) : iFirestoreCompareDoubles;
    }

    private final int compareArrays(ArrayValue left, ArrayValue right) {
        int iMin = (int) Math.min(left.getValuesCount(), right.getValuesCount());
        for (int i = 0; i < iMin; i++) {
            Value values = left.getValues(i);
            Intrinsics.checkNotNullExpressionValue(values, "getValues(...)");
            Value values2 = right.getValues(i);
            Intrinsics.checkNotNullExpressionValue(values2, "getValues(...)");
            int iCompare = compare(values, values2);
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Intrinsics.compare(left.getValuesCount(), right.getValuesCount());
    }

    private final int compareMaps(MapValue left, MapValue right) {
        Iterator it = new TreeMap(left.getFieldsMap()).entrySet().iterator();
        Iterator it2 = new TreeMap(right.getFieldsMap()).entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Map.Entry entry2 = (Map.Entry) it2.next();
            int iCompareUtf8Strings = Util.compareUtf8Strings((String) entry.getKey(), (String) entry2.getKey());
            if (iCompareUtf8Strings != 0) {
                return iCompareUtf8Strings;
            }
            int iCompare = compare((Value) entry.getValue(), (Value) entry2.getValue());
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Boolean.compare(it.hasNext(), it2.hasNext());
    }

    private final int compareVectors(MapValue left, MapValue right) {
        Map<String, Value> fieldsMap = left.getFieldsMap();
        Map<String, Value> fieldsMap2 = right.getFieldsMap();
        Value value = fieldsMap.get(VECTOR_MAP_VECTORS_KEY);
        Intrinsics.checkNotNull(value);
        ArrayValue arrayValue = value.getArrayValue();
        Value value2 = fieldsMap2.get(VECTOR_MAP_VECTORS_KEY);
        Intrinsics.checkNotNull(value2);
        ArrayValue arrayValue2 = value2.getArrayValue();
        int iCompare = Intrinsics.compare(arrayValue.getValuesCount(), arrayValue2.getValuesCount());
        if (iCompare != 0) {
            return iCompare;
        }
        Intrinsics.checkNotNull(arrayValue);
        Intrinsics.checkNotNull(arrayValue2);
        return compareArrays(arrayValue, arrayValue2);
    }

    @JvmStatic
    public static final String canonicalId(Value value) {
        Intrinsics.checkNotNullParameter(value, "value");
        StringBuilder sb = new StringBuilder();
        INSTANCE.canonifyValue(sb, value);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final void canonifyValue(StringBuilder builder, Value value) {
        Value.ValueTypeCase valueTypeCase = value.getValueTypeCase();
        switch (valueTypeCase == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()]) {
            case 1:
                builder.append("null");
                return;
            case 2:
                builder.append(value.getBooleanValue());
                return;
            case 3:
                builder.append(value.getIntegerValue());
                return;
            case 4:
                builder.append(value.getDoubleValue());
                return;
            case 5:
                Timestamp timestampValue = value.getTimestampValue();
                Intrinsics.checkNotNullExpressionValue(timestampValue, "getTimestampValue(...)");
                canonifyTimestamp(builder, timestampValue);
                Unit unit = Unit.INSTANCE;
                return;
            case 6:
                builder.append(value.getStringValue());
                return;
            case 7:
                builder.append(Util.toDebugString(value.getBytesValue()));
                return;
            case 8:
                canonifyReference(builder, value);
                Unit unit2 = Unit.INSTANCE;
                return;
            case 9:
                LatLng geoPointValue = value.getGeoPointValue();
                Intrinsics.checkNotNullExpressionValue(geoPointValue, "getGeoPointValue(...)");
                canonifyGeoPoint(builder, geoPointValue);
                Unit unit3 = Unit.INSTANCE;
                return;
            case 10:
                ArrayValue arrayValue = value.getArrayValue();
                Intrinsics.checkNotNullExpressionValue(arrayValue, "getArrayValue(...)");
                canonifyArray(builder, arrayValue);
                Unit unit4 = Unit.INSTANCE;
                return;
            case 11:
                MapValue mapValue = value.getMapValue();
                Intrinsics.checkNotNullExpressionValue(mapValue, "getMapValue(...)");
                canonifyObject(builder, mapValue);
                Unit unit5 = Unit.INSTANCE;
                return;
            default:
                throw Assert.fail("Invalid value type: " + value.getValueTypeCase(), new Object[0]);
        }
    }

    private final void canonifyTimestamp(StringBuilder builder, Timestamp timestamp) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("time(%s,%s)", Arrays.copyOf(new Object[]{Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanos())}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        builder.append(str);
    }

    private final void canonifyGeoPoint(StringBuilder builder, LatLng latLng) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("geo(%s,%s)", Arrays.copyOf(new Object[]{Double.valueOf(latLng.getLatitude()), Double.valueOf(latLng.getLongitude())}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        builder.append(str);
    }

    private final void canonifyReference(StringBuilder builder, Value value) {
        Assert.hardAssert(isReferenceValue(value), "Value should be a ReferenceValue", new Object[0]);
        builder.append(DocumentKey.fromName(value.getReferenceValue()));
    }

    private final void canonifyObject(StringBuilder builder, MapValue mapValue) {
        ArrayList arrayList = new ArrayList(mapValue.getFieldsMap().keySet());
        CollectionsKt.sort(arrayList);
        builder.append("{");
        Iterator it = arrayList.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            String str = (String) it.next();
            builder.append(str).append(":");
            Value fieldsOrThrow = mapValue.getFieldsOrThrow(str);
            Intrinsics.checkNotNullExpressionValue(fieldsOrThrow, "getFieldsOrThrow(...)");
            canonifyValue(builder, fieldsOrThrow);
            if (it.hasNext()) {
                builder.append(",");
            }
        }
        builder.append("}");
    }

    private final void canonifyArray(StringBuilder builder, ArrayValue arrayValue) {
        builder.append("[");
        if (arrayValue.getValuesCount() > 0) {
            Value values = arrayValue.getValues(0);
            Intrinsics.checkNotNullExpressionValue(values, "getValues(...)");
            canonifyValue(builder, values);
            int valuesCount = arrayValue.getValuesCount();
            for (int i = 1; i < valuesCount; i++) {
                builder.append(",");
                Value values2 = arrayValue.getValues(i);
                Intrinsics.checkNotNullExpressionValue(values2, "getValues(...)");
                canonifyValue(builder, values2);
            }
        }
        builder.append("]");
    }

    @JvmStatic
    public static final boolean isInteger(Value value) {
        return value != null && value.hasIntegerValue();
    }

    @JvmStatic
    public static final boolean isDouble(Value value) {
        return value != null && value.hasDoubleValue();
    }

    @JvmStatic
    public static final boolean isNumber(Value value) {
        return isInteger(value) || isDouble(value);
    }

    @JvmStatic
    public static final boolean isArray(Value value) {
        return value != null && value.hasArrayValue();
    }

    @JvmStatic
    public static final boolean isReferenceValue(Value value) {
        return value != null && value.hasReferenceValue();
    }

    @JvmStatic
    public static final boolean isNullValue(Value value) {
        return value != null && value.hasNullValue();
    }

    @JvmStatic
    public static final boolean isNanValue(Value value) {
        return value != null && Double.isNaN(value.getDoubleValue());
    }

    @JvmStatic
    public static final boolean isMapValue(Value value) {
        return value != null && value.hasMapValue();
    }

    @JvmStatic
    public static final Value refValue(DatabaseId databaseId, DocumentKey key) {
        Intrinsics.checkNotNullParameter(databaseId, "databaseId");
        Intrinsics.checkNotNullParameter(key, "key");
        Value.Builder builderNewBuilder = Value.newBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("projects/%s/databases/%s/documents/%s", Arrays.copyOf(new Object[]{databaseId.getProjectId(), databaseId.getDatabaseId(), key.toString()}, 3));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        Value valueBuild = builderNewBuilder.setReferenceValue(str).build();
        Intrinsics.checkNotNull(valueBuild);
        return valueBuild;
    }

    @JvmStatic
    public static final Value getLowerBound(Value value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Value.ValueTypeCase valueTypeCase = value.getValueTypeCase();
        switch (valueTypeCase == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()]) {
            case 1:
                return NULL_VALUE;
            case 2:
                return MIN_BOOLEAN;
            case 3:
            case 4:
                return MIN_NUMBER;
            case 5:
                return MIN_TIMESTAMP;
            case 6:
                return MIN_STRING;
            case 7:
                return MIN_BYTES;
            case 8:
                return MIN_REFERENCE;
            case 9:
                return MIN_GEO_POINT;
            case 10:
                return MIN_ARRAY;
            case 11:
                return isVectorValue(value) ? MIN_VECTOR_VALUE : MIN_MAP;
            default:
                throw new IllegalArgumentException("Unknown value type: " + value.getValueTypeCase());
        }
    }

    @JvmStatic
    public static final Value getUpperBound(Value value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Value.ValueTypeCase valueTypeCase = value.getValueTypeCase();
        switch (valueTypeCase == null ? -1 : WhenMappings.$EnumSwitchMapping$0[valueTypeCase.ordinal()]) {
            case 1:
                return MIN_BOOLEAN;
            case 2:
                return MIN_NUMBER;
            case 3:
            case 4:
                return MIN_TIMESTAMP;
            case 5:
                return MIN_STRING;
            case 6:
                return MIN_BYTES;
            case 7:
                return MIN_REFERENCE;
            case 8:
                return MIN_GEO_POINT;
            case 9:
                return MIN_ARRAY;
            case 10:
                return MIN_VECTOR_VALUE;
            case 11:
                return isVectorValue(value) ? MIN_MAP : MAX_VALUE;
            default:
                throw new IllegalArgumentException("Unknown value type: " + value.getValueTypeCase());
        }
    }

    @JvmStatic
    public static final boolean isMaxValue(Value value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return Intrinsics.areEqual(MAX_VALUE_TYPE, value.getMapValue().getFieldsMap().get(TYPE_KEY));
    }

    @JvmStatic
    public static final boolean isVectorValue(Value value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return Intrinsics.areEqual(VECTOR_VALUE_TYPE, value.getMapValue().getFieldsMap().get(TYPE_KEY));
    }

    @JvmStatic
    public static final Value encodeValue(long value) {
        Value valueBuild = Value.newBuilder().setIntegerValue(value).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(int value) {
        Value valueBuild = Value.newBuilder().setIntegerValue(value).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(double value) {
        Value valueBuild = Value.newBuilder().setDoubleValue(value).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(float value) {
        Value valueBuild = Value.newBuilder().setDoubleValue(value).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(Number value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof Long) {
            return encodeValue(value.longValue());
        }
        if (value instanceof Integer) {
            return encodeValue(value.intValue());
        }
        if (value instanceof Double) {
            return encodeValue(value.doubleValue());
        }
        if (value instanceof Float) {
            return encodeValue(value.floatValue());
        }
        throw new IllegalArgumentException("Unexpected number type: " + value);
    }

    @JvmStatic
    public static final Value encodeValue(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Value valueBuild = Value.newBuilder().setStringValue(value).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(ResourcePath value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Value valueBuild = Value.newBuilder().setReferenceValue(DomExceptionUtils.SEPARATOR + value.canonicalString()).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(Date date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return encodeValue(new com.google.firebase.Timestamp(date));
    }

    @JvmStatic
    public static final Value encodeValue(com.google.firebase.Timestamp timestamp) {
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        return encodeValue(timestamp(timestamp.getSeconds(), timestamp.getNanoseconds()));
    }

    @JvmStatic
    public static final Value encodeValue(Timestamp value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Value valueBuild = Value.newBuilder().setTimestampValue(value).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(boolean value) {
        return value ? TRUE_VALUE : FALSE_VALUE;
    }

    @JvmStatic
    public static final Value encodeValue(GeoPoint geoPoint) {
        Intrinsics.checkNotNullParameter(geoPoint, "geoPoint");
        Value valueBuild = Value.newBuilder().setGeoPointValue(LatLng.newBuilder().setLatitude(geoPoint.getLatitude()).setLongitude(geoPoint.getLongitude())).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(byte[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Value valueBuild = Value.newBuilder().setBytesValue(ByteString.copyFrom(value)).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(Blob value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Value valueBuild = Value.newBuilder().setBytesValue(value.toByteString()).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(DocumentReference docRef) {
        Intrinsics.checkNotNullParameter(docRef, "docRef");
        Value valueBuild = Value.newBuilder().setReferenceValue(docRef.getFullPath()).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(VectorValue vector) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        double[] array = vector.toArray();
        Intrinsics.checkNotNullExpressionValue(array, "toArray(...)");
        return encodeVectorValue(array);
    }

    @JvmStatic
    public static final Value encodeVectorValue(double[] vector) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        ArrayValue.Builder builderNewBuilder = ArrayValue.newBuilder();
        for (double d : vector) {
            builderNewBuilder.addValues(encodeValue(d));
        }
        Value valueBuild = Value.newBuilder().setMapValue(MapValue.newBuilder().putFields(TYPE_KEY, VECTOR_VALUE_TYPE).putFields(VECTOR_MAP_VECTORS_KEY, Value.newBuilder().setArrayValue(builderNewBuilder).build())).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(Map<String, Value> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        Value valueBuild = Value.newBuilder().setMapValue(MapValue.newBuilder().putAllFields(map)).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeValue(Iterable<Value> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        Value valueBuild = Value.newBuilder().setArrayValue(ArrayValue.newBuilder().addAllValues(values)).build();
        Intrinsics.checkNotNullExpressionValue(valueBuild, "build(...)");
        return valueBuild;
    }

    @JvmStatic
    public static final Value encodeAnyValue(Object value) {
        if (value == null) {
            return NULL_VALUE;
        }
        if (value instanceof String) {
            return encodeValue((String) value);
        }
        if (value instanceof Number) {
            return encodeValue((Number) value);
        }
        if (value instanceof Date) {
            return encodeValue((Date) value);
        }
        if (value instanceof com.google.firebase.Timestamp) {
            return encodeValue((com.google.firebase.Timestamp) value);
        }
        if (value instanceof Boolean) {
            return encodeValue(((Boolean) value).booleanValue());
        }
        if (value instanceof GeoPoint) {
            return encodeValue((GeoPoint) value);
        }
        if (value instanceof Blob) {
            return encodeValue((Blob) value);
        }
        if (value instanceof VectorValue) {
            return encodeValue((VectorValue) value);
        }
        throw new IllegalArgumentException("Unexpected type: " + value);
    }

    @JvmStatic
    public static final Timestamp timestamp(long seconds, int nanos) {
        INSTANCE.validateRange(seconds, nanos);
        Timestamp timestampBuild = Timestamp.newBuilder().setSeconds(seconds).setNanos((nanos / 1000) * 1000).build();
        Intrinsics.checkNotNullExpressionValue(timestampBuild, "build(...)");
        return timestampBuild;
    }

    @JvmStatic
    public static final double[] getVectorValue(Value value) {
        Value value2;
        ArrayValue arrayValue;
        List<Value> valuesList;
        if ((value != null ? value.getValueTypeCase() : null) != Value.ValueTypeCase.MAP_VALUE || !isVectorValue(value) || (value2 = value.getMapValue().getFieldsMap().get(VECTOR_MAP_VECTORS_KEY)) == null || (arrayValue = value2.getArrayValue()) == null || (valuesList = arrayValue.getValuesList()) == null) {
            return null;
        }
        List<Value> list = valuesList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Double.valueOf(((Value) it.next()).getDoubleValue()));
        }
        return CollectionsKt.toDoubleArray(arrayList);
    }

    private final void validateRange(long seconds, int nanoseconds) {
        if (nanoseconds < 0 || nanoseconds >= 1000000000) {
            throw new IllegalArgumentException(("Timestamp nanoseconds out of range: " + nanoseconds).toString());
        }
        if (-62135596800L > seconds || seconds >= 253402300800L) {
            throw new IllegalArgumentException(("Timestamp seconds out of range: " + seconds).toString());
        }
    }
}
