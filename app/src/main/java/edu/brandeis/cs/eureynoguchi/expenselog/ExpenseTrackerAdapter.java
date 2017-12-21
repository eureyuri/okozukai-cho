package edu.brandeis.cs.eureynoguchi.expenselog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpenseTrackerAdapter extends BaseAdapter {
    private ArrayList<ExpenseLogEntryData> logEntryDatas;
    private Context appContext;

    public ExpenseTrackerAdapter(DBOpenHelper dbOpenHelper, Context context) {
        logEntryDatas = new ArrayList<ExpenseLogEntryData>();
        appContext = context;

        for (ExpenseLogEntryData data : dbOpenHelper.getExpenses()) {
            logEntryDatas.add(data);
        }
    }

    @Override
    public int getCount() {
        return logEntryDatas.size();
    }

    @Override
    public Object getItem(int index) {
        return logEntryDatas.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    public void add(ExpenseLogEntryData expense) {
        logEntryDatas.add(expense);
    }

    public void delete(ExpenseLogEntryData expense) {
        logEntryDatas.remove(expense);
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {
        final ExpenseLogEntryData expenseData = logEntryDatas.get(index);

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_entry, parent, false);
        }

        TextView description = view.findViewById(R.id.descriptionEntry);
        description.setText(expenseData.getDescription());

        TextView notes = view.findViewById(R.id.notesEntry);
        notes.setText(expenseData.getNotes());

        TextView time = view.findViewById(R.id.timeEntry);
        time.setText(expenseData.getTimeDate());

        Button button = (Button)view.findViewById(R.id.deleteButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBOpenHelper.getInstance(appContext).deleteExpense(expenseData.getId());

                delete(expenseData);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
