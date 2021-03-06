package edu.brandeis.cs.eureynoguchi.expenselog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;
import java.util.Date;
import java.util.Vector;
import java.util.ArrayList;
import android.database.MatrixCursor;

class DBOpenHelper extends SQLiteOpenHelper {

    private static DBOpenHelper dbHelperInstance;

    private static final String DATABASE_NAME = "okozukai.db";
    private static final String DATABASE_TABLE_EXPENSES = "Expenses";
    private static final String DATABASE_TABLE_USER = "User";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_EXPENSES = "CREATE TABLE " + DATABASE_TABLE_EXPENSES +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT, notes TEXT, time TEXT);";
    private static final String DATABASE_CREATE_USER = "CREATE TABLE " + DATABASE_TABLE_USER +
            " (id INTEGER PRIMARY KEY, current INTEGER, thisWeek INTEGER, leftOver INTEGER, threshold INTEGER);";

    private DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DBOpenHelper getInstance(Context context) {
        if (dbHelperInstance == null) {
            dbHelperInstance = new DBOpenHelper(context.getApplicationContext());
        }
        return dbHelperInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w("DBOpenHelper onCreate", DATABASE_CREATE_EXPENSES);
        Log.w("DBOpenHelper onCreate", DATABASE_CREATE_USER);

        db.execSQL(DATABASE_CREATE_EXPENSES);
        db.execSQL(DATABASE_CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBOpenHelper onUpgrade", "Upgrading from " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_EXPENSES + ";");
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_USER + ";");

        onCreate(db);
    }

    public long addNewUser(int current, int threshold) {
        ContentValues newValues = new ContentValues();

        newValues.put("id", 1);
        newValues.put("current", current);
        newValues.put("thisWeek", 0);
        newValues.put("leftOver", current);
        newValues.put("threshold", threshold);

        Log.w("DBOpenHelper", "addNewUser: current: " + current + " threshold: " + threshold);

        return getWritableDatabase().insert(DATABASE_TABLE_USER, null, newValues);
    }

    public int updateUser(int threshold) {
        ContentValues newValues = new ContentValues();

        newValues.put("threshold", threshold);

        return getWritableDatabase().update(DATABASE_TABLE_USER, newValues, "id = ?", new String[]{"1"});
    }

    public User getUser() {
        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + DATABASE_TABLE_USER + ";", null);
        cursor.moveToFirst();
        return new User(cursor.getInt(cursor.getColumnIndex("current")),
                cursor.getInt(cursor.getColumnIndex("thisWeek")),
                cursor.getInt(cursor.getColumnIndex("leftOver")),
                cursor.getInt(cursor.getColumnIndex("threshold"))
                );
    }

    public long addNewExpense(String description, String notes) {
        ContentValues newValues = new ContentValues();

        newValues.put("description", description);
        newValues.put("notes", notes);
        newValues.put("time", new Date().toString());

        Log.w("DBOpenHelper", "addNewExpense: Description: " + description + " Notes: " + notes);

        return getWritableDatabase().insert(DATABASE_TABLE_EXPENSES, null, newValues);

    }

    public Vector<ExpenseLogEntryData> getExpenses() {
        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + DATABASE_TABLE_EXPENSES + ";", null);
        Vector<ExpenseLogEntryData> entries = new Vector<ExpenseLogEntryData>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                long id = cursor.getLong(cursor.getColumnIndex("id"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String notes = cursor.getString(cursor.getColumnIndex("notes"));
                String time = cursor.getString(cursor.getColumnIndex("time"));

                entries.add(new ExpenseLogEntryData(id, description, notes, time));
                cursor.moveToNext();
            }
        }

        return entries;
    }

    public ExpenseLogEntryData getExpense(long id) {
        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + DATABASE_TABLE_EXPENSES + " WHERE id = " + id +" ;", null);

        if (cursor.moveToFirst() && !cursor.isAfterLast()) {
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String notes = cursor.getString(cursor.getColumnIndex("notes"));
            String time = cursor.getString(cursor.getColumnIndex("time"));

            return new ExpenseLogEntryData(id, description, notes, time);
        }

        return null;
    }

    public void deleteExpense(long id) {
        Log.w("DBOpenHelper", "deleteExpense: id: " + id);
        getWritableDatabase().delete(DATABASE_TABLE_EXPENSES, "id = " + id, null);
    }

    public ArrayList<Cursor> getData(String Query) {
        SQLiteDatabase sqlDB = this.getWritableDatabase();

        String[] columns = new String[] { "message" };
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query ;
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch (Exception ex) {
            Log.d("printing exception", ex.getMessage());

            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }

}
