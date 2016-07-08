package com.example.spider007.trsvisapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;


public class HotOrNot {

    public static String KEY_ID = "_id";
    public static String KEY_NAME = "name";
    public static String KEY_HOTNESS = "hotness";

    private static String DATABASE_NAME = "HotOrNot";
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_TABLE = "Peoples";

    // Instance Variable
    private final Context ourcontex;
    private DBHelper ourHelper;
    private SQLiteDatabase ourDatabase;

    // Constructor
    public HotOrNot(Context ourcontex) {
        this.ourcontex = ourcontex;
    }

    public HotOrNot open() throws SQLException{
        ourHelper = new DBHelper(ourcontex);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public long createEntry(String name, int hotness) {

        ContentValues cv = new ContentValues();
        if (name != null && hotness <= 10 && hotness >= 0) {
            cv.put(KEY_NAME, name);
            cv.put(KEY_HOTNESS, hotness);
            return ourDatabase.insert(DATABASE_TABLE, null, cv);
        }
        return -1L;
    }

    public void updateEntry(long l1, String n1, String h1) throws SQLException {
        ContentValues cv = new ContentValues();
        if (n1 != null && l1 <= 10 && l1 >= 0) {
            cv.put(KEY_NAME, n1);
            cv.put(KEY_HOTNESS, h1);
            ourDatabase.update(DATABASE_TABLE, cv, KEY_ID + "=" + l1, null);
            Toast.makeText(ourcontex, "Updated", Toast.LENGTH_LONG).show();
        }

    }


    // getter methods
    public void getData(Context c, TableLayout tl)  {
        String[] column = new String[]{KEY_ID, KEY_NAME, KEY_HOTNESS};
        Cursor cursor = ourDatabase.query(DATABASE_TABLE, column, null, null, null, null, null);
        String result = "";

        int iID = cursor.getColumnIndex(KEY_ID);
        int iName = cursor.getColumnIndex(KEY_NAME);
        int iHotness = cursor.getColumnIndex(KEY_HOTNESS);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            TableRow tr = new TableRow(c);
            TextView tvId = new TextView(c);
            TextView tvName = new TextView(c);
            TextView tvHotness = new TextView(c);

            tvId.setGravity(Gravity.START);
            tvName.setGravity(Gravity.CENTER);
            tvHotness.setGravity(Gravity.END);

            tvId.setText(cursor.getString(iID));
            tvName.setText(cursor.getString(iName));
            tvHotness.setText(cursor.getString(iHotness));

            tr.addView(tvId);
            tr.addView(tvName);
            tr.addView(tvHotness);
            tl.addView(tr);
        }
    }

    public String getName(long l) throws SQLException{
        String[] column = new String[]{KEY_ID, KEY_NAME, KEY_HOTNESS};
        Cursor cursor = ourDatabase.query(DATABASE_TABLE, column, KEY_ID + "=" + l, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getString(1);
        }
        return null;
    }

    public int getHotness(long l) throws SQLException {
        String[] column = new String[]{KEY_ID, KEY_NAME, KEY_HOTNESS};
        Cursor cursor = ourDatabase.query(DATABASE_TABLE, column, KEY_ID + "=" + l, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getInt(2);
        }
        return 0;
    }

    public void deleteEntry(long l2) throws SQLException{
        ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + l2, null);
    }


    //Helper Class for SQLite
    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + DATABASE_TABLE + " ( " +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_HOTNESS + " INTEGER NOT NULL " + ");";

            db.execSQL(query);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }
}
