package androidx.camera.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SessionConfig.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\t\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/camera/core/LegacySessionConfig;", "Landroidx/camera/core/SessionConfig;", "useCases", "", "Landroidx/camera/core/UseCase;", "viewPort", "Landroidx/camera/core/ViewPort;", "effects", "Landroidx/camera/core/CameraEffect;", "<init>", "(Ljava/util/List;Landroidx/camera/core/ViewPort;Ljava/util/List;)V", "useCaseGroup", "Landroidx/camera/core/UseCaseGroup;", "(Landroidx/camera/core/UseCaseGroup;)V", "isLegacy", "", "()Z", "camera-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LegacySessionConfig extends SessionConfig {
    private final boolean isLegacy;

    public /* synthetic */ LegacySessionConfig(List list, ViewPort viewPort, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : viewPort, (i & 4) != 0 ? CollectionsKt.emptyList() : list2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacySessionConfig(List<? extends UseCase> useCases, ViewPort viewPort, List<? extends CameraEffect> effects) {
        super(useCases, viewPort, effects, null, null, null, 56, null);
        Intrinsics.checkNotNullParameter(useCases, "useCases");
        Intrinsics.checkNotNullParameter(effects, "effects");
        this.isLegacy = true;
    }

    @Override // androidx.camera.core.SessionConfig
    /* JADX INFO: renamed from: isLegacy, reason: from getter */
    public boolean getIsLegacy() {
        return this.isLegacy;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LegacySessionConfig(UseCaseGroup useCaseGroup) {
        Intrinsics.checkNotNullParameter(useCaseGroup, "useCaseGroup");
        List<UseCase> useCases = useCaseGroup.getUseCases();
        Intrinsics.checkNotNullExpressionValue(useCases, "getUseCases(...)");
        ViewPort viewPort = useCaseGroup.getViewPort();
        List<CameraEffect> effects = useCaseGroup.getEffects();
        Intrinsics.checkNotNullExpressionValue(effects, "getEffects(...)");
        this(useCases, viewPort, effects);
    }
}
