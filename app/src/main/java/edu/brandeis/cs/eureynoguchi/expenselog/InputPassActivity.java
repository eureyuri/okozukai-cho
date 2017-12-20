package edu.brandeis.cs.eureynoguchi.expenselog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

/**
 * Created by eureyuri on 2017/12/20.
 */

public class InputPassActivity extends AppCompatActivity {

    PatternLockView mPatternLockView;
    String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_pass_activity);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                getBaseContext());
        password = sharedPreferences.getString("password", "0");

        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                if (password.equals(PatternLockUtils.patternToString(mPatternLockView, pattern))) {
                    startActivity(new Intent(InputPassActivity.this, AddExpenseActivity.class));
                } else {
                    Toast.makeText(InputPassActivity.this, "パスワードが違います", Toast.LENGTH_SHORT).show();
                    mPatternLockView.clearPattern();
                }
            }

            @Override
            public void onCleared() {

            }
        });
    }

}
