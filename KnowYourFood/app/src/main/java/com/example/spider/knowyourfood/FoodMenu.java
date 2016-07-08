package com.example.spider.knowyourfood;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FoodMenu extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener{

    ListView listView;
    String[] foodNames = {"Oats Meal","Watermelon","Banana","Cucumber","Peanuts"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,foodNames);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,foodNames[position],Toast.LENGTH_SHORT).show();
        view.animate();
        Intent intent = new Intent(this,Details.class);
        intent.putExtra("FoodName",foodNames[position]);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,Add_Entry.class));

    }
}
