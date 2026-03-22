package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* JADX INFO: compiled from: com.google.mlkit:barcode-scanning@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
public enum zzdy {
    DOUBLE(0, 1, zzes.DOUBLE),
    FLOAT(1, 1, zzes.FLOAT),
    INT64(2, 1, zzes.LONG),
    UINT64(3, 1, zzes.LONG),
    INT32(4, 1, zzes.INT),
    FIXED64(5, 1, zzes.LONG),
    FIXED32(6, 1, zzes.INT),
    BOOL(7, 1, zzes.BOOLEAN),
    STRING(8, 1, zzes.STRING),
    MESSAGE(9, 1, zzes.MESSAGE),
    BYTES(10, 1, zzes.BYTE_STRING),
    UINT32(11, 1, zzes.INT),
    ENUM(12, 1, zzes.ENUM),
    SFIXED32(13, 1, zzes.INT),
    SFIXED64(14, 1, zzes.LONG),
    SINT32(15, 1, zzes.INT),
    SINT64(16, 1, zzes.LONG),
    GROUP(17, 1, zzes.MESSAGE),
    DOUBLE_LIST(18, 2, zzes.DOUBLE),
    FLOAT_LIST(19, 2, zzes.FLOAT),
    INT64_LIST(20, 2, zzes.LONG),
    UINT64_LIST(21, 2, zzes.LONG),
    INT32_LIST(22, 2, zzes.INT),
    FIXED64_LIST(23, 2, zzes.LONG),
    FIXED32_LIST(24, 2, zzes.INT),
    BOOL_LIST(25, 2, zzes.BOOLEAN),
    STRING_LIST(26, 2, zzes.STRING),
    MESSAGE_LIST(27, 2, zzes.MESSAGE),
    BYTES_LIST(28, 2, zzes.BYTE_STRING),
    UINT32_LIST(29, 2, zzes.INT),
    ENUM_LIST(30, 2, zzes.ENUM),
    SFIXED32_LIST(31, 2, zzes.INT),
    SFIXED64_LIST(32, 2, zzes.LONG),
    SINT32_LIST(33, 2, zzes.INT),
    SINT64_LIST(34, 2, zzes.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzes.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzes.FLOAT),
    INT64_LIST_PACKED(37, 3, zzes.LONG),
    UINT64_LIST_PACKED(38, 3, zzes.LONG),
    INT32_LIST_PACKED(39, 3, zzes.INT),
    FIXED64_LIST_PACKED(40, 3, zzes.LONG),
    FIXED32_LIST_PACKED(41, 3, zzes.INT),
    BOOL_LIST_PACKED(42, 3, zzes.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzes.INT),
    ENUM_LIST_PACKED(44, 3, zzes.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzes.INT),
    SFIXED64_LIST_PACKED(46, 3, zzes.LONG),
    SINT32_LIST_PACKED(47, 3, zzes.INT),
    SINT64_LIST_PACKED(48, 3, zzes.LONG),
    GROUP_LIST(49, 2, zzes.MESSAGE),
    MAP(50, 4, zzes.VOID);

    private static final zzdy[] zzZ;
    private final int zzab;

    static {
        zzdy[] zzdyVarArrValues = values();
        zzZ = new zzdy[zzdyVarArrValues.length];
        for (zzdy zzdyVar : zzdyVarArrValues) {
            zzZ[zzdyVar.zzab] = zzdyVar;
        }
    }

    zzdy(int i, int i2, zzes zzesVar) {
        this.zzab = i;
        int i3 = i2 - 1;
        if (i3 == 1 || i3 == 3) {
            zzesVar.zza();
        }
        if (i2 == 1) {
            zzes zzesVar2 = zzes.VOID;
            zzesVar.ordinal();
        }
    }

    public final int zza() {
        return this.zzab;
    }
}
