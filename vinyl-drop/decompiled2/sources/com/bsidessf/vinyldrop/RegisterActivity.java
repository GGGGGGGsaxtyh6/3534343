package com.bsidessf.vinyldrop;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RegisterActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u0018\u0010\u0010\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/bsidessf/vinyldrop/RegisterActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "isValid", "", "email", "", "password", "confirmPassword", "createAccount", "toast", "message", "app"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        this.auth = firebaseAuth;
        final TextInputEditText textInputEditText = (TextInputEditText) findViewById(R.id.registerEmailInput);
        final TextInputEditText textInputEditText2 = (TextInputEditText) findViewById(R.id.registerPasswordInput);
        final TextInputEditText textInputEditText3 = (TextInputEditText) findViewById(R.id.registerConfirmPasswordInput);
        ((MaterialButton) findViewById(R.id.registerButton)).setOnClickListener(new View.OnClickListener() { // from class: com.bsidessf.vinyldrop.RegisterActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RegisterActivity.onCreate$lambda$0(textInputEditText, textInputEditText2, textInputEditText3, this, view);
            }
        });
    }

    static final void onCreate$lambda$0(TextInputEditText textInputEditText, TextInputEditText textInputEditText2, TextInputEditText textInputEditText3, RegisterActivity registerActivity, View view) {
        String string;
        String string2;
        String string3;
        String string4;
        Editable text = textInputEditText.getText();
        String str = "";
        if (text == null || (string4 = text.toString()) == null || (string = StringsKt.trim((CharSequence) string4).toString()) == null) {
            string = "";
        }
        Editable text2 = textInputEditText2.getText();
        if (text2 == null || (string2 = text2.toString()) == null) {
            string2 = "";
        }
        Editable text3 = textInputEditText3.getText();
        if (text3 != null && (string3 = text3.toString()) != null) {
            str = string3;
        }
        if (registerActivity.isValid(string, string2, str)) {
            registerActivity.createAccount(string, string2);
        }
    }

    private final boolean isValid(String email, String password, String confirmPassword) {
        if (email.length() == 0) {
            toast("Email is required");
            return false;
        }
        if (password.length() < 6) {
            toast("Password must be at least 6 characters");
            return false;
        }
        if (Intrinsics.areEqual(password, confirmPassword)) {
            return true;
        }
        toast("Passwords do not match");
        return false;
    }

    private final void createAccount(String email, String password) {
        FirebaseAuth firebaseAuth = this.auth;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener() { // from class: com.bsidessf.vinyldrop.RegisterActivity$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                RegisterActivity.createAccount$lambda$1(this.f$0, task);
            }
        });
    }

    static final void createAccount$lambda$1(RegisterActivity registerActivity, Task task) {
        String localizedMessage;
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            registerActivity.toast("Account created successfully");
            registerActivity.finish();
            return;
        }
        Exception exception = task.getException();
        if (exception == null || (localizedMessage = exception.getLocalizedMessage()) == null) {
            localizedMessage = "Registration failed";
        }
        registerActivity.toast(localizedMessage);
    }

    private final void toast(String message) {
        Toast.makeText(this, message, 0).show();
    }
}
