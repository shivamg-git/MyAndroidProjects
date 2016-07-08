package com.example.spider007.loveandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    boolean loveSwitch = false;
    Button button;
    TextView lyrics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getReferences();
    }


    public void OnLoveClicked(View v) {
        if (loveSwitch) {
            lyrics.setVisibility(View.VISIBLE);
            button.setText(R.string.hide);
            loveSwitch=false;
        } else {
            lyrics.setVisibility(View.INVISIBLE);
            button.setText(R.string.show);
            loveSwitch=true;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getReferences() {
        lyrics = (TextView) findViewById(R.id.Lyrics);
        button = (Button) findViewById(R.id.Switch);
    }
}
