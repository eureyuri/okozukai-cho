package edu.brandeis.cs.eureynoguchi.expenselog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by eureyuri on 2017/12/21.
 */

public class InitialSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        TextView password = (TextView)findViewById(R.id.change_pass);
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InitialSettingActivity.this, SetPassActivity.class));
            }
        });

        Button confirm = (Button)findViewById(R.id.confirm_setting);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText current = (EditText)findViewById(R.id.input_current);
                EditText threshold = (EditText)findViewById(R.id.input_threshold);

                String currentString = current.getText().toString();
                String thresholdString = threshold.getText().toString();

                DBOpenHelper db = DBOpenHelper.getInstance(getApplicationContext());
                db.addNewUser(Integer.parseInt(currentString), Integer.parseInt(thresholdString));
                startActivity(new Intent(InitialSettingActivity.this, DashboardActivity.class));
            }
        });
    }

}
