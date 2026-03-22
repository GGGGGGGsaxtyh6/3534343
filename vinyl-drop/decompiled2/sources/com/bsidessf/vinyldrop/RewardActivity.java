package com.bsidessf.vinyldrop;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;

/* JADX INFO: compiled from: RewardActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\t"}, d2 = {"Lcom/bsidessf/vinyldrop/RewardActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class RewardActivity extends AppCompatActivity {
    public static final String EXTRA_IS_ERROR = "extra_is_error";
    public static final String EXTRA_REWARD_VALUE = "extra_reward_value";

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        TextView textView = (TextView) findViewById(R.id.rewardText);
        TextView textView2 = (TextView) findViewById(R.id.rewardTitle);
        boolean booleanExtra = getIntent().getBooleanExtra(EXTRA_IS_ERROR, false);
        String stringExtra = getIntent().getStringExtra(EXTRA_REWARD_VALUE);
        if (stringExtra == null) {
            stringExtra = "";
        }
        if (booleanExtra) {
            textView2.setText(getString(R.string.reward_error_title));
            textView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        } else {
            textView2.setText(getString(R.string.reward_congrats));
        }
        textView.setText(stringExtra);
    }
}
