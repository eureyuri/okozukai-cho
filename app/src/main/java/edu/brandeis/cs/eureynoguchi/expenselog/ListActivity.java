package edu.brandeis.cs.eureynoguchi.expenselog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by eureyuri on 2017/12/21.
 */

public class ListActivity extends AppCompatActivity {

    final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ExpenseTrackerAdapter(DBOpenHelper.getInstance(getApplicationContext()), getApplicationContext()));

        if (listView.getAdapter().getCount() == 0) {
            Toast.makeText(ListActivity.this, "メニューから追加してください", Toast.LENGTH_SHORT).show();
        }
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
            return true;
        } else if (item.getItemId() == 1) {
            startActivityForResult(new Intent(this, AddExpenseActivity.class), REQUEST_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                long id = data.getLongExtra("id", -1);
                ExpenseLogEntryData expenseEntry = DBOpenHelper.getInstance(getApplicationContext()).getExpense(id);
                ListView listView = (ListView)findViewById(R.id.listView);
                ExpenseTrackerAdapter expenseAdapter = (ExpenseTrackerAdapter)listView.getAdapter();
                expenseAdapter.add(expenseEntry);
                expenseAdapter.notifyDataSetChanged();
            }
        }
    }
}
