package com.example.spider.food;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] foodNames = {"Oats Meal", "Milk", "Watermelon", "Banana", "Wheat Bread", "Cheese"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Spider
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.activity_list_item, foodNames);
        listView.setAdapter(arrayAdapter);

    }
}
