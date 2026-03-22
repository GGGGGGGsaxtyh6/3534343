package com.bsidessf.vinyldrop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: WelcomeAcitivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/bsidessf/vinyldrop/WelcomeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "functions", "Lcom/google/firebase/functions/FirebaseFunctions;", "getFunctions", "()Lcom/google/firebase/functions/FirebaseFunctions;", "functions$delegate", "Lkotlin/Lazy;", "scanLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "startBarcodeScan", "sendScanToFunction", Values.VECTOR_MAP_VECTORS_KEY, "", "app"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class WelcomeActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: functions$delegate, reason: from kotlin metadata */
    private final Lazy functions = LazyKt.lazy(new Function0() { // from class: com.bsidessf.vinyldrop.WelcomeActivity$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return FirebaseFunctions.INSTANCE.getInstance();
        }
    });
    private final ActivityResultLauncher<Intent> scanLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.bsidessf.vinyldrop.WelcomeActivity$$ExternalSyntheticLambda2
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            WelcomeActivity.scanLauncher$lambda$1(this.f$0, (ActivityResult) obj);
        }
    });

    private final FirebaseFunctions getFunctions() {
        return (FirebaseFunctions) this.functions.getValue();
    }

    static final void scanLauncher$lambda$1(WelcomeActivity welcomeActivity, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            Intent data = result.getData();
            String stringExtra = data != null ? data.getStringExtra(BarcodeScanActivity.EXTRA_BARCODE_VALUE) : null;
            String str = stringExtra;
            if (str != null && !StringsKt.isBlank(str)) {
                welcomeActivity.sendScanToFunction(stringExtra);
                return;
            } else {
                Toast.makeText(welcomeActivity, "No QR content found", 0).show();
                return;
            }
        }
        Toast.makeText(welcomeActivity, "Scan cancelled", 0).show();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ((MaterialButton) findViewById(R.id.scanBarcodeButton)).setOnClickListener(new View.OnClickListener() { // from class: com.bsidessf.vinyldrop.WelcomeActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.startBarcodeScan();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startBarcodeScan() {
        this.scanLauncher.launch(new Intent(this, (Class<?>) BarcodeScanActivity.class));
    }

    private final void sendScanToFunction(String value) {
        Task<HttpsCallableResult> taskCall = getFunctions().getHttpsCallable("validateScanPayload").call(MapsKt.hashMapOf(TuplesKt.to(Values.VECTOR_MAP_VECTORS_KEY, value)));
        final Function1 function1 = new Function1() { // from class: com.bsidessf.vinyldrop.WelcomeActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WelcomeActivity.sendScanToFunction$lambda$3(this.f$0, (HttpsCallableResult) obj);
            }
        };
        taskCall.addOnSuccessListener(new OnSuccessListener() { // from class: com.bsidessf.vinyldrop.WelcomeActivity$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                function1.invoke(obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.bsidessf.vinyldrop.WelcomeActivity$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                WelcomeActivity.sendScanToFunction$lambda$5(this.f$0, exc);
            }
        });
    }

    static final Unit sendScanToFunction$lambda$3(WelcomeActivity welcomeActivity, HttpsCallableResult httpsCallableResult) {
        String str;
        Object obj = httpsCallableResult.data;
        Map map = obj instanceof Map ? (Map) obj : null;
        Object obj2 = map != null ? map.get("success") : null;
        Boolean bool = obj2 instanceof Boolean ? (Boolean) obj2 : null;
        boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
        Object obj3 = map != null ? map.get("reward") : null;
        String str2 = obj3 instanceof String ? (String) obj3 : null;
        Object obj4 = map != null ? map.get("error") : null;
        Boolean bool2 = obj4 instanceof Boolean ? (Boolean) obj4 : null;
        boolean zBooleanValue2 = bool2 != null ? bool2.booleanValue() : false;
        Object obj5 = map != null ? map.get("reason") : null;
        String str3 = obj5 instanceof String ? (String) obj5 : null;
        if (zBooleanValue && (str = str2) != null && !StringsKt.isBlank(str)) {
            Intent intentPutExtra = new Intent(welcomeActivity, (Class<?>) RewardActivity.class).putExtra(RewardActivity.EXTRA_REWARD_VALUE, str2);
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
            welcomeActivity.startActivity(intentPutExtra);
        } else if (zBooleanValue2) {
            Log.e("WelcomeActivity", "Scan rejected: " + str3);
            Intent intent = new Intent(welcomeActivity, (Class<?>) RewardActivity.class);
            if (str3 == null) {
                str3 = "Scan rejected";
            }
            Intent intentPutExtra2 = intent.putExtra(RewardActivity.EXTRA_REWARD_VALUE, str3).putExtra(RewardActivity.EXTRA_IS_ERROR, true);
            Intrinsics.checkNotNullExpressionValue(intentPutExtra2, "putExtra(...)");
            welcomeActivity.startActivity(intentPutExtra2);
        } else {
            Toast.makeText(welcomeActivity, "Scan accepted", 0).show();
        }
        return Unit.INSTANCE;
    }

    static final void sendScanToFunction$lambda$5(WelcomeActivity welcomeActivity, Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        Log.e("WelcomeActivity", "Scan rejected", e);
        Intent intent = new Intent(welcomeActivity, (Class<?>) RewardActivity.class);
        String localizedMessage = e.getLocalizedMessage();
        Intent intentPutExtra = intent.putExtra(RewardActivity.EXTRA_REWARD_VALUE, localizedMessage != null ? localizedMessage : "Scan rejected").putExtra(RewardActivity.EXTRA_IS_ERROR, true);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        welcomeActivity.startActivity(intentPutExtra);
    }
}
