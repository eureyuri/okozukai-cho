package edu.brandeis.cs.eureynoguchi.expenselog;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddExpenseActivity extends Activity {

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

}
