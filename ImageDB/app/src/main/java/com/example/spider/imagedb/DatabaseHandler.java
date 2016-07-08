package com.example.spider.imagedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*** Created by spider on 6/7/16. */

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int version = 1;
    public static final String name = "MyImagesDB";

    public DatabaseHandler(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTableImage = "CREATE TABLE IF NOT EXISTS Images(ImageURI VARCHAR);";
        db.execSQL(CreateTableImage);
        Log.e("DB", "Table Created!!!");
    }

    public Boolean insertImages(ContentValues cv) {
        SQLiteDatabase db = getWritableDatabase();
        // Return True if Success
        return db.insert("Images", null, cv) != -1;
    }

    public Cursor getImages(){
        Log.e("IN DB:","enter getImage");
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM Images";
        return db.rawQuery(query,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
