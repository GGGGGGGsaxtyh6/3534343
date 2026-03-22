package androidx.camera.core.internal;

import androidx.camera.core.UseCase;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.camera.core.streamsharing.StreamSharing;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CalculatedUseCaseInfo.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0015\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000eHÆ\u0003J\t\u0010-\u001a\u00020\u0011HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u009d\u0001\u0010/\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u000204HÖ\u0001J\t\u00105\u001a\u000206HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#¨\u00067"}, d2 = {"Landroidx/camera/core/internal/CalculatedUseCaseInfo;", "", "appUseCases", "", "Landroidx/camera/core/UseCase;", "cameraUseCases", "cameraUseCasesToAttach", "", "cameraUseCasesToKeep", "cameraUseCasesToDetach", "streamSharing", "Landroidx/camera/core/streamsharing/StreamSharing;", "placeholderForExtensions", "useCaseConfigs", "", "Landroidx/camera/core/internal/CameraUseCaseAdapter$ConfigPair;", "primaryStreamSpecResult", "Landroidx/camera/core/internal/StreamSpecQueryResult;", "secondaryStreamSpecResult", "<init>", "(Ljava/util/Collection;Ljava/util/Collection;Ljava/util/List;Ljava/util/List;Ljava/util/List;Landroidx/camera/core/streamsharing/StreamSharing;Landroidx/camera/core/UseCase;Ljava/util/Map;Landroidx/camera/core/internal/StreamSpecQueryResult;Landroidx/camera/core/internal/StreamSpecQueryResult;)V", "getAppUseCases", "()Ljava/util/Collection;", "getCameraUseCases", "getCameraUseCasesToAttach", "()Ljava/util/List;", "getCameraUseCasesToKeep", "getCameraUseCasesToDetach", "getStreamSharing", "()Landroidx/camera/core/streamsharing/StreamSharing;", "getPlaceholderForExtensions", "()Landroidx/camera/core/UseCase;", "getUseCaseConfigs", "()Ljava/util/Map;", "getPrimaryStreamSpecResult", "()Landroidx/camera/core/internal/StreamSpecQueryResult;", "getSecondaryStreamSpecResult", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "", "toString", "", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class CalculatedUseCaseInfo {
    private final Collection<UseCase> appUseCases;
    private final Collection<UseCase> cameraUseCases;
    private final List<UseCase> cameraUseCasesToAttach;
    private final List<UseCase> cameraUseCasesToDetach;
    private final List<UseCase> cameraUseCasesToKeep;
    private final UseCase placeholderForExtensions;
    private final StreamSpecQueryResult primaryStreamSpecResult;
    private final StreamSpecQueryResult secondaryStreamSpecResult;
    private final StreamSharing streamSharing;
    private final Map<UseCase, CameraUseCaseAdapter.ConfigPair> useCaseConfigs;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CalculatedUseCaseInfo copy$default(CalculatedUseCaseInfo calculatedUseCaseInfo, Collection collection, Collection collection2, List list, List list2, List list3, StreamSharing streamSharing, UseCase useCase, Map map, StreamSpecQueryResult streamSpecQueryResult, StreamSpecQueryResult streamSpecQueryResult2, int i, Object obj) {
        if ((i & 1) != 0) {
            collection = calculatedUseCaseInfo.appUseCases;
        }
        if ((i & 2) != 0) {
            collection2 = calculatedUseCaseInfo.cameraUseCases;
        }
        if ((i & 4) != 0) {
            list = calculatedUseCaseInfo.cameraUseCasesToAttach;
        }
        if ((i & 8) != 0) {
            list2 = calculatedUseCaseInfo.cameraUseCasesToKeep;
        }
        if ((i & 16) != 0) {
            list3 = calculatedUseCaseInfo.cameraUseCasesToDetach;
        }
        if ((i & 32) != 0) {
            streamSharing = calculatedUseCaseInfo.streamSharing;
        }
        if ((i & 64) != 0) {
            useCase = calculatedUseCaseInfo.placeholderForExtensions;
        }
        if ((i & 128) != 0) {
            map = calculatedUseCaseInfo.useCaseConfigs;
        }
        if ((i & 256) != 0) {
            streamSpecQueryResult = calculatedUseCaseInfo.primaryStreamSpecResult;
        }
        if ((i & 512) != 0) {
            streamSpecQueryResult2 = calculatedUseCaseInfo.secondaryStreamSpecResult;
        }
        StreamSpecQueryResult streamSpecQueryResult3 = streamSpecQueryResult;
        StreamSpecQueryResult streamSpecQueryResult4 = streamSpecQueryResult2;
        UseCase useCase2 = useCase;
        Map map2 = map;
        List list4 = list3;
        StreamSharing streamSharing2 = streamSharing;
        return calculatedUseCaseInfo.copy(collection, collection2, list, list2, list4, streamSharing2, useCase2, map2, streamSpecQueryResult3, streamSpecQueryResult4);
    }

    public final Collection<UseCase> component1() {
        return this.appUseCases;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final StreamSpecQueryResult getSecondaryStreamSpecResult() {
        return this.secondaryStreamSpecResult;
    }

    public final Collection<UseCase> component2() {
        return this.cameraUseCases;
    }

    public final List<UseCase> component3() {
        return this.cameraUseCasesToAttach;
    }

    public final List<UseCase> component4() {
        return this.cameraUseCasesToKeep;
    }

    public final List<UseCase> component5() {
        return this.cameraUseCasesToDetach;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final StreamSharing getStreamSharing() {
        return this.streamSharing;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final UseCase getPlaceholderForExtensions() {
        return this.placeholderForExtensions;
    }

    public final Map<UseCase, CameraUseCaseAdapter.ConfigPair> component8() {
        return this.useCaseConfigs;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final StreamSpecQueryResult getPrimaryStreamSpecResult() {
        return this.primaryStreamSpecResult;
    }

    public final CalculatedUseCaseInfo copy(Collection<? extends UseCase> appUseCases, Collection<? extends UseCase> cameraUseCases, List<? extends UseCase> cameraUseCasesToAttach, List<? extends UseCase> cameraUseCasesToKeep, List<? extends UseCase> cameraUseCasesToDetach, StreamSharing streamSharing, UseCase placeholderForExtensions, Map<UseCase, ? extends CameraUseCaseAdapter.ConfigPair> useCaseConfigs, StreamSpecQueryResult primaryStreamSpecResult, StreamSpecQueryResult secondaryStreamSpecResult) {
        Intrinsics.checkNotNullParameter(appUseCases, "appUseCases");
        Intrinsics.checkNotNullParameter(cameraUseCases, "cameraUseCases");
        Intrinsics.checkNotNullParameter(cameraUseCasesToAttach, "cameraUseCasesToAttach");
        Intrinsics.checkNotNullParameter(cameraUseCasesToKeep, "cameraUseCasesToKeep");
        Intrinsics.checkNotNullParameter(cameraUseCasesToDetach, "cameraUseCasesToDetach");
        Intrinsics.checkNotNullParameter(useCaseConfigs, "useCaseConfigs");
        Intrinsics.checkNotNullParameter(primaryStreamSpecResult, "primaryStreamSpecResult");
        return new CalculatedUseCaseInfo(appUseCases, cameraUseCases, cameraUseCasesToAttach, cameraUseCasesToKeep, cameraUseCasesToDetach, streamSharing, placeholderForExtensions, useCaseConfigs, primaryStreamSpecResult, secondaryStreamSpecResult);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CalculatedUseCaseInfo)) {
            return false;
        }
        CalculatedUseCaseInfo calculatedUseCaseInfo = (CalculatedUseCaseInfo) other;
        return Intrinsics.areEqual(this.appUseCases, calculatedUseCaseInfo.appUseCases) && Intrinsics.areEqual(this.cameraUseCases, calculatedUseCaseInfo.cameraUseCases) && Intrinsics.areEqual(this.cameraUseCasesToAttach, calculatedUseCaseInfo.cameraUseCasesToAttach) && Intrinsics.areEqual(this.cameraUseCasesToKeep, calculatedUseCaseInfo.cameraUseCasesToKeep) && Intrinsics.areEqual(this.cameraUseCasesToDetach, calculatedUseCaseInfo.cameraUseCasesToDetach) && Intrinsics.areEqual(this.streamSharing, calculatedUseCaseInfo.streamSharing) && Intrinsics.areEqual(this.placeholderForExtensions, calculatedUseCaseInfo.placeholderForExtensions) && Intrinsics.areEqual(this.useCaseConfigs, calculatedUseCaseInfo.useCaseConfigs) && Intrinsics.areEqual(this.primaryStreamSpecResult, calculatedUseCaseInfo.primaryStreamSpecResult) && Intrinsics.areEqual(this.secondaryStreamSpecResult, calculatedUseCaseInfo.secondaryStreamSpecResult);
    }

    public int hashCode() {
        int iHashCode = ((((((((this.appUseCases.hashCode() * 31) + this.cameraUseCases.hashCode()) * 31) + this.cameraUseCasesToAttach.hashCode()) * 31) + this.cameraUseCasesToKeep.hashCode()) * 31) + this.cameraUseCasesToDetach.hashCode()) * 31;
        StreamSharing streamSharing = this.streamSharing;
        int iHashCode2 = (iHashCode + (streamSharing == null ? 0 : streamSharing.hashCode())) * 31;
        UseCase useCase = this.placeholderForExtensions;
        int iHashCode3 = (((((iHashCode2 + (useCase == null ? 0 : useCase.hashCode())) * 31) + this.useCaseConfigs.hashCode()) * 31) + this.primaryStreamSpecResult.hashCode()) * 31;
        StreamSpecQueryResult streamSpecQueryResult = this.secondaryStreamSpecResult;
        return iHashCode3 + (streamSpecQueryResult != null ? streamSpecQueryResult.hashCode() : 0);
    }

    public String toString() {
        return "CalculatedUseCaseInfo(appUseCases=" + this.appUseCases + ", cameraUseCases=" + this.cameraUseCases + ", cameraUseCasesToAttach=" + this.cameraUseCasesToAttach + ", cameraUseCasesToKeep=" + this.cameraUseCasesToKeep + ", cameraUseCasesToDetach=" + this.cameraUseCasesToDetach + ", streamSharing=" + this.streamSharing + ", placeholderForExtensions=" + this.placeholderForExtensions + ", useCaseConfigs=" + this.useCaseConfigs + ", primaryStreamSpecResult=" + this.primaryStreamSpecResult + ", secondaryStreamSpecResult=" + this.secondaryStreamSpecResult + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CalculatedUseCaseInfo(Collection<? extends UseCase> appUseCases, Collection<? extends UseCase> cameraUseCases, List<? extends UseCase> cameraUseCasesToAttach, List<? extends UseCase> cameraUseCasesToKeep, List<? extends UseCase> cameraUseCasesToDetach, StreamSharing streamSharing, UseCase useCase, Map<UseCase, ? extends CameraUseCaseAdapter.ConfigPair> useCaseConfigs, StreamSpecQueryResult primaryStreamSpecResult, StreamSpecQueryResult streamSpecQueryResult) {
        Intrinsics.checkNotNullParameter(appUseCases, "appUseCases");
        Intrinsics.checkNotNullParameter(cameraUseCases, "cameraUseCases");
        Intrinsics.checkNotNullParameter(cameraUseCasesToAttach, "cameraUseCasesToAttach");
        Intrinsics.checkNotNullParameter(cameraUseCasesToKeep, "cameraUseCasesToKeep");
        Intrinsics.checkNotNullParameter(cameraUseCasesToDetach, "cameraUseCasesToDetach");
        Intrinsics.checkNotNullParameter(useCaseConfigs, "useCaseConfigs");
        Intrinsics.checkNotNullParameter(primaryStreamSpecResult, "primaryStreamSpecResult");
        this.appUseCases = appUseCases;
        this.cameraUseCases = cameraUseCases;
        this.cameraUseCasesToAttach = cameraUseCasesToAttach;
        this.cameraUseCasesToKeep = cameraUseCasesToKeep;
        this.cameraUseCasesToDetach = cameraUseCasesToDetach;
        this.streamSharing = streamSharing;
        this.placeholderForExtensions = useCase;
        this.useCaseConfigs = useCaseConfigs;
        this.primaryStreamSpecResult = primaryStreamSpecResult;
        this.secondaryStreamSpecResult = streamSpecQueryResult;
    }

    public final Collection<UseCase> getAppUseCases() {
        return this.appUseCases;
    }

    public final Collection<UseCase> getCameraUseCases() {
        return this.cameraUseCases;
    }

    public final List<UseCase> getCameraUseCasesToAttach() {
        return this.cameraUseCasesToAttach;
    }

    public final List<UseCase> getCameraUseCasesToKeep() {
        return this.cameraUseCasesToKeep;
    }

    public final List<UseCase> getCameraUseCasesToDetach() {
        return this.cameraUseCasesToDetach;
    }

    public final StreamSharing getStreamSharing() {
        return this.streamSharing;
    }

    public final UseCase getPlaceholderForExtensions() {
        return this.placeholderForExtensions;
    }

    public final Map<UseCase, CameraUseCaseAdapter.ConfigPair> getUseCaseConfigs() {
        return this.useCaseConfigs;
    }

    public final StreamSpecQueryResult getPrimaryStreamSpecResult() {
        return this.primaryStreamSpecResult;
    }

    public final StreamSpecQueryResult getSecondaryStreamSpecResult() {
        return this.secondaryStreamSpecResult;
    }
}
