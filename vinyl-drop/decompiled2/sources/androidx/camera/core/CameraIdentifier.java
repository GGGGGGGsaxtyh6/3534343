package androidx.camera.core;

import androidx.camera.core.impl.AdapterCameraInfo;
import androidx.camera.core.impl.Identifier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Preconditions;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CameraIdentifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB!\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J\u0013\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0004H\u0016R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u00068G¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Landroidx/camera/core/CameraIdentifier;", "", "cameraIds", "", "", "compatibilityId", "Landroidx/camera/core/impl/Identifier;", "<init>", "(Ljava/util/List;Landroidx/camera/core/impl/Identifier;)V", "getCameraIds", "()Ljava/util/List;", "getCompatibilityId", "()Landroidx/camera/core/impl/Identifier;", "internalId", "getInternalId", "()Ljava/lang/String;", "isOf", "", "camera", "Landroidx/camera/core/Camera;", "cameraInfo", "Landroidx/camera/core/CameraInfo;", "equals", "other", "hashCode", "", "toString", "Companion", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class CameraIdentifier {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<String> cameraIds;
    private final Identifier compatibilityId;

    public /* synthetic */ CameraIdentifier(List list, Identifier identifier, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, identifier);
    }

    @JvmStatic
    public static final CameraIdentifier create(String str) {
        return INSTANCE.create(str);
    }

    @JvmStatic
    public static final CameraIdentifier create(String str, String str2) {
        return INSTANCE.create(str, str2);
    }

    @JvmStatic
    public static final CameraIdentifier create(String str, String str2, Identifier identifier) {
        return INSTANCE.create(str, str2, identifier);
    }

    @JvmStatic
    public static final CameraIdentifier create(List<String> list) {
        return INSTANCE.create(list);
    }

    @JvmStatic
    public static final CameraIdentifier create(List<String> list, Identifier identifier) {
        return INSTANCE.create(list, identifier);
    }

    @JvmStatic
    public static final CameraIdentifier fromAdapterInfos(AdapterCameraInfo adapterCameraInfo, AdapterCameraInfo adapterCameraInfo2) {
        return INSTANCE.fromAdapterInfos(adapterCameraInfo, adapterCameraInfo2);
    }

    private CameraIdentifier(List<String> list, Identifier identifier) {
        this.cameraIds = list;
        this.compatibilityId = identifier;
        Preconditions.checkArgument(!list.isEmpty(), "Camera ID set cannot be empty.");
    }

    public final List<String> getCameraIds() {
        return this.cameraIds;
    }

    public final Identifier getCompatibilityId() {
        return this.compatibilityId;
    }

    public final String getInternalId() {
        Preconditions.checkState(this.cameraIds.size() == 1, "getInternalId() is only available for single-camera identifiers.");
        return (String) CollectionsKt.first((List) this.cameraIds);
    }

    public final boolean isOf(Camera camera) {
        Intrinsics.checkNotNullParameter(camera, "camera");
        Preconditions.checkNotNull(camera);
        return Intrinsics.areEqual(this, camera.getCameraInfo().getCameraIdentifier());
    }

    public final boolean isOf(CameraInfo cameraInfo) {
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        Preconditions.checkNotNull(cameraInfo);
        return Intrinsics.areEqual(this, cameraInfo.getCameraIdentifier());
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CameraIdentifier)) {
            return false;
        }
        CameraIdentifier cameraIdentifier = (CameraIdentifier) other;
        return Intrinsics.areEqual(this.cameraIds, cameraIdentifier.cameraIds) && Intrinsics.areEqual(this.compatibilityId, cameraIdentifier.compatibilityId);
    }

    public int hashCode() {
        int iHashCode = this.cameraIds.hashCode() * 31;
        Identifier identifier = this.compatibilityId;
        return iHashCode + (identifier != null ? identifier.hashCode() : 0);
    }

    public String toString() {
        String str;
        StringBuilder sbAppend = new StringBuilder("CameraIdentifier{cameraIds=").append(CollectionsKt.joinToString$default(this.cameraIds, ",", null, null, 0, null, null, 62, null));
        Identifier identifier = this.compatibilityId;
        if (identifier == null || (str = ", compatId=" + identifier) == null) {
            str = "";
        }
        return sbAppend.append(str).append('}').toString();
    }

    /* JADX INFO: compiled from: CameraIdentifier.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\u001a\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007¨\u0006\u0011"}, d2 = {"Landroidx/camera/core/CameraIdentifier$Companion;", "", "<init>", "()V", "create", "Landroidx/camera/core/CameraIdentifier;", "cameraIds", "", "", "compatibilityId", "Landroidx/camera/core/impl/Identifier;", "primaryCameraId", "secondaryCameraId", "fromAdapterInfos", "primaryInfo", "Landroidx/camera/core/impl/AdapterCameraInfo;", "secondaryInfo", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final CameraIdentifier create(String primaryCameraId) {
            Intrinsics.checkNotNullParameter(primaryCameraId, "primaryCameraId");
            return create$default(this, primaryCameraId, null, null, 6, null);
        }

        @JvmStatic
        public final CameraIdentifier create(String primaryCameraId, String str) {
            Intrinsics.checkNotNullParameter(primaryCameraId, "primaryCameraId");
            return create$default(this, primaryCameraId, str, null, 4, null);
        }

        @JvmStatic
        public final CameraIdentifier create(List<String> cameraIds) {
            Intrinsics.checkNotNullParameter(cameraIds, "cameraIds");
            return create$default(this, cameraIds, null, 2, null);
        }

        private Companion() {
        }

        public static /* synthetic */ CameraIdentifier create$default(Companion companion, List list, Identifier identifier, int i, Object obj) {
            if ((i & 2) != 0) {
                identifier = null;
            }
            return companion.create((List<String>) list, identifier);
        }

        @JvmStatic
        public final CameraIdentifier create(List<String> cameraIds, Identifier compatibilityId) {
            Intrinsics.checkNotNullParameter(cameraIds, "cameraIds");
            return new CameraIdentifier(cameraIds, compatibilityId, null);
        }

        public static /* synthetic */ CameraIdentifier create$default(Companion companion, String str, String str2, Identifier identifier, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            if ((i & 4) != 0) {
                identifier = null;
            }
            return companion.create(str, str2, identifier);
        }

        @JvmStatic
        public final CameraIdentifier create(String primaryCameraId, String secondaryCameraId, Identifier compatibilityId) {
            Intrinsics.checkNotNullParameter(primaryCameraId, "primaryCameraId");
            List<String> listMutableListOf = CollectionsKt.mutableListOf(primaryCameraId);
            if (secondaryCameraId != null) {
                listMutableListOf.add(secondaryCameraId);
            }
            return create(listMutableListOf, compatibilityId);
        }

        @JvmStatic
        public final CameraIdentifier fromAdapterInfos(AdapterCameraInfo primaryInfo, AdapterCameraInfo secondaryInfo) {
            Intrinsics.checkNotNullParameter(primaryInfo, "primaryInfo");
            String cameraId = secondaryInfo != null ? secondaryInfo.getCameraId() : null;
            Identifier compatibilityId = primaryInfo.getCameraConfig().getCompatibilityId();
            Intrinsics.checkNotNullExpressionValue(compatibilityId, "getCompatibilityId(...)");
            String cameraId2 = primaryInfo.getCameraId();
            Intrinsics.checkNotNullExpressionValue(cameraId2, "getCameraId(...)");
            return create(cameraId2, cameraId, compatibilityId);
        }
    }
}
