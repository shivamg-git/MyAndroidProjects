package com.example.spider007.layoutsamples;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    String list_items[] = {"Horizontal_Scroll_view", "GridView", "ex3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list_items));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String select = list_items[position];
        try {
            Class selected_class = Class.forName("com.example.spider007.layoutsamples." + select);
            Intent selected_activity = new Intent(MainActivity.this, selected_class);
            startActivity(selected_activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
