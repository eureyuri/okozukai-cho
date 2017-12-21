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

/**
 * Created by eureyuri on 2017/12/21.
 */

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        EditText current = (EditText)findViewById(R.id.input_current);
        current.setText(DBOpenHelper.getInstance(getApplicationContext()).getUser().getCurrent() + "");
        current.setKeyListener(null);

        Button confirm = (Button)findViewById(R.id.confirm_setting);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText threshold = (EditText)findViewById(R.id.input_threshold);

                String thresholdString = threshold.getText().toString();

                DBOpenHelper db = DBOpenHelper.getInstance(getApplicationContext());
                db.updateUser(Integer.parseInt(thresholdString));
                startActivity(new Intent(SettingActivity.this, DashboardActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        createMenu(menu);
        return true;
    }

    private void createMenu(Menu menu) {
        menu.setQwertyMode(true);
        MenuItem list = menu.add(0, 0, 0, "お小遣い帳");
        MenuItem add = menu.add(1, 1, 1, "追加");
        MenuItem setting = menu.add(2, 2, 2, "設定");
        MenuItem checkDatabase = menu.add(3, 3, 3, "Database");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        return menuChoice(item);
    }

    private boolean menuChoice(MenuItem item) {
        if (item.getItemId() == 0) {
            startActivity(new Intent(this, ListActivity.class));
            return true;
        } else if (item.getItemId() == 1) {
            startActivity(new Intent(this, AddExpenseActivity.class));
            return true;
        } else if (item.getItemId() == 2) {
            return true;
        } else if (item.getItemId() == 3) {
            startActivity(new Intent(this, AndroidDatabaseManager.class));
            return true;
        }
        return false;
    }
}
