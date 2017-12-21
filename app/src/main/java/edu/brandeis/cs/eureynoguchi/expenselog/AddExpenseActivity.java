package edu.brandeis.cs.eureynoguchi.expenselog;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense_activity);

        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText description = (EditText)findViewById(R.id.description);
                String descriptionString = description.getText().toString();

                EditText notes = (EditText)findViewById(R.id.notes);
                String notesString = notes.getText().toString();

                DBOpenHelper dbHelper = DBOpenHelper.getInstance(getApplicationContext());
                long id = dbHelper.addNewExpense(descriptionString, notesString);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("id", id);
                setResult(Activity.RESULT_OK, returnIntent);

                finish();
            }
        });

        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
            return true;
        } else if (item.getItemId() == 2) {
            startActivity(new Intent(this, SettingActivity.class));
            return true;
        } else if (item.getItemId() == 3) {
            startActivity(new Intent(this, AndroidDatabaseManager.class));
            return true;
        }
        return false;
    }

}
