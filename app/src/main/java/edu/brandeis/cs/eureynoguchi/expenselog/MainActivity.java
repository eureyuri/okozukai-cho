package edu.brandeis.cs.eureynoguchi.expenselog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ExpenseTrackerAdapter(DBOpenHelper.getInstance(getApplicationContext()), getApplicationContext()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        createMenu(menu);
        return true;
    }

    private void createMenu(Menu menu) {
        menu.setQwertyMode(true);
        MenuItem item1 = menu.add(0, 0, 0, "Add");
        MenuItem checkDatabase = menu.add(1, 1, 1, "Database");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        return menuChoice(item);
    }

    private boolean menuChoice(MenuItem item) {
        if (item.getItemId() == 0) {
            startActivityForResult(new Intent(this, AddExpenseActivity.class), REQUEST_CODE);
            return true;
        } else if (item.getItemId() == 1) {
            startActivity(new Intent(this, AndroidDatabaseManager.class));
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
