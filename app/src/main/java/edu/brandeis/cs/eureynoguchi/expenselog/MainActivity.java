package edu.brandeis.cs.eureynoguchi.expenselog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                        getBaseContext());

                boolean isFirstStart = sharedPreferences.getBoolean("firstStart", true);
                if (isFirstStart) {
                    final Intent intro = new Intent(MainActivity.this, FirstVisitActivity.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intro);
                        }
                    });

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("firstStart", false);
                    editor.apply();
                }
            }
        });

        thread.start();

        startActivity(new Intent(this, InputPassActivity.class));
    }

}
