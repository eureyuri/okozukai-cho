package edu.brandeis.cs.eureynoguchi.expenselog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

/**
 * Created by eureyuri on 2017/12/20.
 * https://github.com/aritraroy/PatternLockView
 * https://www.youtube.com/watch?v=BYYFelIdZsQ
 */


public class SetPassActivity extends AppCompatActivity {

    PatternLockView mPatternLockView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_pass_activity);

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
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                        getBaseContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("password", PatternLockUtils.patternToString(mPatternLockView, pattern));
                editor.apply();

                startActivity(new Intent(SetPassActivity.this, SettingActivity.class));
            }

            @Override
            public void onCleared() {

            }
        });
    }
}
