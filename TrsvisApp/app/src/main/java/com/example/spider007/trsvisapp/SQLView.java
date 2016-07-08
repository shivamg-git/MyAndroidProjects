package com.example.spider007.trsvisapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import java.sql.SQLException;

public class SQLView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlview);
        TableLayout table = (TableLayout)findViewById(R.id.dataTable);
        try {
            HotOrNot info = new HotOrNot(this);
            info.open();
            info.getData(this, table);
            info.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}
