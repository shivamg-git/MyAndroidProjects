package com.example.spider007.trsvisapp;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button addOne, subOne, reset;
    TextView counterView;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addOne = (Button) findViewById(R.id.add_button);
        subOne = (Button) findViewById(R.id.sub_button);
        reset = (Button) findViewById(R.id.reset_button);
        counterView = (TextView) findViewById(R.id.counter_text_view);
        counterView.setText("Your Count is " + counter);

        addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter += 1;
                counterView.setText("Your Count is " + counter);
            }
        });

        subOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter -= 1;
                counterView.setText("Your Count is " + counter);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                counterView.setText("Your Count is " + counter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_au) {
            return true;
        }else if(id == R.id.action_pref){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);setContentView(R.layout.activity_main);
        counterView.setText("Your Count  " + counter);

        addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter += 1;
                counterView.setText("Your Count is " + counter);
            }
        });

        subOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter -= 1;
                counterView.setText("Your Count is " + counter);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                counterView.setText("Your Count is " + counter);
            }
        });
    }
}
