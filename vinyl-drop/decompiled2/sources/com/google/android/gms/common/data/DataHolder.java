package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.sqlite.CursorWrapper;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Parcelable.Creator<DataHolder> CREATOR = new zad();
    private static final Builder zak = new zab(new String[0], null);
    final int zaa;
    Bundle zab;
    int[] zac;
    int zad;
    boolean zae;
    private final String[] zaf;
    private final CursorWindow[] zag;
    private final int zah;
    private final Bundle zai;
    private boolean zaj;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.9.0 */
    public static class Builder {
        private final String[] zaa;
        private final ArrayList zab = new ArrayList();
        private final HashMap zac = new HashMap();

        /* synthetic */ Builder(String[] strArr, String str, byte[] bArr) {
            this.zaa = (String[]) Preconditions.checkNotNull(strArr);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DataHolder build(int i) {
            return new DataHolder(this, i, (Bundle) null, (byte[]) (0 == true ? 1 : 0));
        }

        public Builder withRow(ContentValues contentValues) {
            Asserts.checkNotNull(contentValues);
            HashMap map = new HashMap(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            return zaa(map);
        }

        public Builder zaa(HashMap map) {
            Asserts.checkNotNull(map);
            this.zab.add(map);
            return this;
        }

        final /* synthetic */ String[] zab() {
            return this.zaa;
        }

        final /* synthetic */ ArrayList zac() {
            return this.zab;
        }

        public DataHolder build(int i, Bundle bundle) {
            return new DataHolder(this, i, bundle, -1, (byte[]) null);
        }
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.zae = false;
        this.zaj = true;
        this.zaa = i;
        this.zaf = strArr;
        this.zag = cursorWindowArr;
        this.zah = i2;
        this.zai = bundle;
    }

    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, byte[] bArr) {
        this(builder, i, (Bundle) null);
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.zae = false;
        this.zaj = true;
        this.zaa = 1;
        this.zaf = (String[]) Preconditions.checkNotNull(strArr);
        this.zag = (CursorWindow[]) Preconditions.checkNotNull(cursorWindowArr);
        this.zah = i;
        this.zai = bundle;
        zaa();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Builder builder(String[] strArr) {
        return new Builder(strArr, null, 0 == true ? 1 : 0);
    }

    public static DataHolder empty(int i) {
        return new DataHolder(zak, i, (Bundle) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0179, code lost:
    
        if (r12 != false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x017b, code lost:
    
        r5 = new java.lang.StringBuilder(java.lang.String.valueOf(r11).length() + 63);
        r5.append("Couldn't populate window data for row ");
        r5.append(r11);
        r5.append(" - allocating new window.");
        android.util.Log.d("DataHolder", r5.toString());
        r9.freeLastRow();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x019f, code lost:
    
        r5 = false;
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01a0, code lost:
    
        r0 = new android.database.CursorWindow(false);
        r0.setStartPosition(r11);
        r0.setNumColumns(r21.zab().length);
        r10.add(r0);
        r11 = r11 - 1;
        r9 = r0;
        r12 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01b6, code lost:
    
        r11 = r11 + 1;
        r7 = r5;
        r0 = r18;
        r5 = r19;
        r6 = r20;
        r9 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01c1, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01c9, code lost:
    
        throw new com.google.android.gms.common.data.zac("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01ca, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [int] */
    /* JADX WARN: Type inference failed for: r7v5, types: [int] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r9v1, types: [android.database.CursorWindow] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v2, types: [android.database.CursorWindow] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static CursorWindow[] zae(Builder builder, int i) {
        boolean z;
        ?? r9;
        Map map;
        boolean zPutString;
        ?? r7;
        ArrayList arrayList;
        String str;
        String str2;
        String str3;
        String str4;
        boolean zPutDouble;
        String str5 = ")";
        String str6 = "Allocating additional cursor window for large data set (row ";
        boolean z2 = false;
        if (builder.zab().length == 0) {
            return new CursorWindow[0];
        }
        ArrayList arrayListZac = builder.zac();
        int size = arrayListZac.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ?? arrayList2 = new ArrayList();
        arrayList2.add(cursorWindow);
        cursorWindow.setNumColumns(builder.zab().length);
        int i2 = 0;
        boolean z3 = false;
        ?? r92 = cursorWindow;
        while (i2 < size) {
            try {
                boolean zAllocRow = r92.allocRow();
                r9 = r92;
                if (!zAllocRow) {
                    StringBuilder sb = new StringBuilder(String.valueOf(i2).length() + 61);
                    sb.append(str6);
                    sb.append(i2);
                    sb.append(str5);
                    Log.d("DataHolder", sb.toString());
                    CursorWindow cursorWindow2 = new CursorWindow(z2);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(builder.zab().length);
                    arrayList2.add(cursorWindow2);
                    boolean zAllocRow2 = cursorWindow2.allocRow();
                    r9 = cursorWindow2;
                    if (!zAllocRow2) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList2.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList2.toArray(new CursorWindow[arrayList2.size()]);
                    }
                }
                map = (Map) arrayListZac.get(i2);
                zPutString = true;
                r7 = z2;
            } catch (RuntimeException e) {
                e = e;
                z = z2;
            }
            while (true) {
                try {
                } catch (RuntimeException e2) {
                    e = e2;
                    z = false;
                }
                if (r7 < builder.zab().length) {
                    if (!zPutString) {
                        arrayList = arrayListZac;
                        str = str5;
                        str2 = str6;
                        break;
                    }
                    String str7 = builder.zab()[r7];
                    ArrayList arrayList3 = arrayListZac;
                    Object obj = map.get(str7);
                    if (obj == null) {
                        zPutString = r9.putNull(i2, r7);
                        str3 = str5;
                    } else {
                        str3 = str5;
                        if (obj instanceof String) {
                            zPutString = r9.putString((String) obj, i2, r7);
                        } else {
                            if (obj instanceof Long) {
                                str4 = str6;
                                zPutDouble = r9.putLong(((Long) obj).longValue(), i2, r7);
                            } else {
                                str4 = str6;
                                if (obj instanceof Integer) {
                                    zPutDouble = r9.putLong(((Integer) obj).intValue(), i2, r7);
                                } else if (obj instanceof Boolean) {
                                    zPutDouble = r9.putLong(true != ((Boolean) obj).booleanValue() ? 0L : 1L, i2, r7);
                                } else if (obj instanceof byte[]) {
                                    zPutDouble = r9.putBlob((byte[]) obj, i2, r7);
                                } else if (obj instanceof Double) {
                                    zPutDouble = r9.putDouble(((Double) obj).doubleValue(), i2, r7);
                                } else {
                                    if (!(obj instanceof Float)) {
                                        String string = obj.toString();
                                        StringBuilder sb2 = new StringBuilder(String.valueOf(str7).length() + 32 + string.length());
                                        sb2.append("Unsupported object for column ");
                                        sb2.append(str7);
                                        sb2.append(": ");
                                        sb2.append(string);
                                        throw new IllegalArgumentException(sb2.toString());
                                    }
                                    zPutDouble = r9.putDouble(((Float) obj).floatValue(), i2, r7);
                                }
                            }
                            zPutString = zPutDouble;
                            arrayListZac = arrayList3;
                            str5 = str3;
                            str6 = str4;
                            r7++;
                        }
                    }
                    str4 = str6;
                    arrayListZac = arrayList3;
                    str5 = str3;
                    str6 = str4;
                    r7++;
                    int size2 = arrayList2.size();
                    for (?? r72 = z; r72 < size2; r72++) {
                        ((CursorWindow) arrayList2.get(r72)).close();
                    }
                    throw e;
                }
                arrayList = arrayListZac;
                str = str5;
                str2 = str6;
                if (zPutString) {
                    boolean z4 = false;
                    z3 = false;
                    ?? r93 = r9;
                }
            }
        }
        return (CursorWindow[]) arrayList2.toArray(new CursorWindow[arrayList2.size()]);
    }

    private final void zaf(String str, int i) {
        Bundle bundle = this.zab;
        if (bundle == null || !bundle.containsKey(str)) {
            String.valueOf(str);
            throw new IllegalArgumentException("No such column: ".concat(String.valueOf(str)));
        }
        if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (i < 0 || i >= this.zad) {
            throw new CursorIndexOutOfBoundsException(i, this.zad);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (!this.zae) {
                this.zae = true;
                int i = 0;
                while (true) {
                    CursorWindow[] cursorWindowArr = this.zag;
                    if (i >= cursorWindowArr.length) {
                        break;
                    }
                    cursorWindowArr[i].close();
                    i++;
                }
            }
        }
    }

    protected final void finalize() throws Throwable {
        try {
            if (this.zaj && this.zag.length > 0 && !isClosed()) {
                close();
                String string = toString();
                StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 178);
                sb.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
                sb.append(string);
                sb.append(")");
                Log.e("DataBuffer", sb.toString());
            }
        } finally {
            super.finalize();
        }
    }

    public boolean getBoolean(String str, int i, int i2) {
        zaf(str, i);
        return Long.valueOf(this.zag[i2].getLong(i, this.zab.getInt(str))).longValue() == 1;
    }

    public byte[] getByteArray(String str, int i, int i2) {
        zaf(str, i);
        return this.zag[i2].getBlob(i, this.zab.getInt(str));
    }

    public int getCount() {
        return this.zad;
    }

    public int getInteger(String str, int i, int i2) {
        zaf(str, i);
        return this.zag[i2].getInt(i, this.zab.getInt(str));
    }

    public long getLong(String str, int i, int i2) {
        zaf(str, i);
        return this.zag[i2].getLong(i, this.zab.getInt(str));
    }

    public Bundle getMetadata() {
        return this.zai;
    }

    public int getStatusCode() {
        return this.zah;
    }

    public String getString(String str, int i, int i2) {
        zaf(str, i);
        return this.zag[i2].getString(i, this.zab.getInt(str));
    }

    public int getWindowIndex(int i) {
        int length;
        int i2 = 0;
        Preconditions.checkState(i >= 0 && i < this.zad);
        while (true) {
            int[] iArr = this.zac;
            length = iArr.length;
            if (i2 >= length) {
                break;
            }
            if (i < iArr[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == length ? i2 - 1 : i2;
    }

    public boolean hasColumn(String str) {
        return this.zab.containsKey(str);
    }

    public boolean hasNull(String str, int i, int i2) {
        zaf(str, i);
        return this.zag[i2].isNull(i, this.zab.getInt(str));
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.zae;
        }
        return z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String[] strArr = this.zaf;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, strArr, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zag, i, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zaa);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        if ((i & 1) != 0) {
            close();
        }
    }

    public final void zaa() {
        this.zab = new Bundle();
        int i = 0;
        while (true) {
            String[] strArr = this.zaf;
            if (i >= strArr.length) {
                break;
            }
            this.zab.putInt(strArr[i], i);
            i++;
        }
        CursorWindow[] cursorWindowArr = this.zag;
        this.zac = new int[cursorWindowArr.length];
        int numRows = 0;
        for (int i2 = 0; i2 < cursorWindowArr.length; i2++) {
            this.zac[i2] = numRows;
            numRows += cursorWindowArr[i2].getNumRows() - (numRows - cursorWindowArr[i2].getStartPosition());
        }
        this.zad = numRows;
    }

    public final float zab(String str, int i, int i2) {
        zaf(str, i);
        return this.zag[i2].getFloat(i, this.zab.getInt(str));
    }

    public final double zac(String str, int i, int i2) {
        zaf(str, i);
        return this.zag[i2].getDouble(i, this.zab.getInt(str));
    }

    public final void zad(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zaf(str, i);
        this.zag[i2].copyStringToBuffer(i, this.zab.getInt(str), charArrayBuffer);
    }

    public DataHolder(Cursor cursor, int i, Bundle bundle) {
        int startPosition;
        CursorWrapper cursorWrapper = new CursorWrapper(cursor);
        String[] columnNames = cursorWrapper.getColumnNames();
        ArrayList arrayList = new ArrayList();
        try {
            int count = cursorWrapper.getCount();
            CursorWindow window = cursorWrapper.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                startPosition = 0;
            } else {
                window.acquireReference();
                cursorWrapper.setWindow(null);
                arrayList.add(window);
                startPosition = window.getNumRows();
            }
            while (startPosition < count) {
                if (!cursorWrapper.moveToPosition(startPosition)) {
                    break;
                }
                CursorWindow window2 = cursorWrapper.getWindow();
                if (window2 != null) {
                    window2.acquireReference();
                    cursorWrapper.setWindow(null);
                } else {
                    window2 = new CursorWindow(false);
                    window2.setStartPosition(startPosition);
                    cursorWrapper.fillWindow(startPosition, window2);
                }
                if (window2.getNumRows() == 0) {
                    break;
                }
                arrayList.add(window2);
                startPosition = window2.getStartPosition() + window2.getNumRows();
            }
            cursorWrapper.close();
            this(columnNames, (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]), i, bundle);
        } catch (Throwable th) {
            cursorWrapper.close();
            throw th;
        }
    }

    private DataHolder(Builder builder, int i, Bundle bundle) {
        this(builder.zab(), zae(builder, -1), i, (Bundle) null);
    }

    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, int i2, byte[] bArr) {
        this(builder.zab(), zae(builder, -1), i, bundle);
    }
}
