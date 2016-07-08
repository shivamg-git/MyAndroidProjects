package com.example.spider.imagedb;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowCase extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_case);
        gridView = (GridView) findViewById(R.id.gridView);
        String[] uri = getUriArray();
        ImageAdapter imageAddapter = new ImageAdapter(this,uri);
        gridView.setAdapter(imageAddapter);
    }


    public String[] getUriArray() {
        List<String> list = new ArrayList<>();
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        Cursor c = databaseHandler.getImages();
        Log.e("IN GETURI ", "Image Getting success!");

        while (c.moveToNext()) {
            list.add(c.getString(0));
        }
        String[] stringUri = new String[list.size()];
        list.toArray(stringUri);
        return stringUri;
    }
}
