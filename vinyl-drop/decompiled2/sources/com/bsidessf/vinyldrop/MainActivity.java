package com.bsidessf.vinyldrop;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.EnvironmentCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\u0007H\u0002J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/bsidessf/vinyldrop/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "isValid", "", "email", "", "password", "signIn", "goToWelcome", "toast", "message", "app"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        this.auth = firebaseAuth;
        final TextInputEditText textInputEditText = (TextInputEditText) findViewById(R.id.emailInput);
        final TextInputEditText textInputEditText2 = (TextInputEditText) findViewById(R.id.passwordInput);
        MaterialButton materialButton = (MaterialButton) findViewById(R.id.loginButton);
        ((TextView) findViewById(R.id.registerLink)).setOnClickListener(new View.OnClickListener() { // from class: com.bsidessf.vinyldrop.MainActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity mainActivity = this.f$0;
                mainActivity.startActivity(new Intent(mainActivity, (Class<?>) RegisterActivity.class));
            }
        });
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.bsidessf.vinyldrop.MainActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.onCreate$lambda$1(textInputEditText, textInputEditText2, this, view);
            }
        });
    }

    static final void onCreate$lambda$1(TextInputEditText textInputEditText, TextInputEditText textInputEditText2, MainActivity mainActivity, View view) {
        String string;
        String string2;
        String string3;
        Editable text = textInputEditText.getText();
        String str = "";
        if (text == null || (string3 = text.toString()) == null || (string = StringsKt.trim((CharSequence) string3).toString()) == null) {
            string = "";
        }
        Editable text2 = textInputEditText2.getText();
        if (text2 != null && (string2 = text2.toString()) != null) {
            str = string2;
        }
        if (mainActivity.isValid(string, str)) {
            mainActivity.signIn(string, str);
        }
    }

    private final boolean isValid(String email, String password) {
        if (email.length() == 0) {
            toast("Email is required");
            return false;
        }
        if (password.length() != 0) {
            return true;
        }
        toast("Password is required");
        return false;
    }

    private final void signIn(String email, String password) {
        FirebaseAuth firebaseAuth = this.auth;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener() { // from class: com.bsidessf.vinyldrop.MainActivity$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                MainActivity.signIn$lambda$2(this.f$0, task);
            }
        });
    }

    static final void signIn$lambda$2(MainActivity mainActivity, Task task) {
        String localizedMessage;
        String uid;
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            FirebaseAuth firebaseAuth = mainActivity.auth;
            if (firebaseAuth == null) {
                Intrinsics.throwUninitializedPropertyAccessException("auth");
                firebaseAuth = null;
            }
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            if (currentUser == null || (uid = currentUser.getUid()) == null) {
                uid = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            Log.d("MainActivity", "Signed in userId=" + uid);
            mainActivity.toast("Login successful");
            mainActivity.goToWelcome();
            return;
        }
        Exception exception = task.getException();
        if (exception == null || (localizedMessage = exception.getLocalizedMessage()) == null) {
            localizedMessage = "Login failed";
        }
        mainActivity.toast(localizedMessage);
    }

    private final void goToWelcome() {
        startActivity(new Intent(this, (Class<?>) WelcomeActivity.class));
        finish();
    }

    private final void toast(String message) {
        Toast.makeText(this, message, 0).show();
    }
}
