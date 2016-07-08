package com.example.spider007.trsvisapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {
    String classes[] = {"MainActivity", "TextPlay", "HateMail", "BackgroundSet", "Get", "GFX",
            "SoundGunShot", "Slider", "TabHostActivity", "SimpleWebBrowser", "Flipper",
            "DataOnSharedPreferences","FileOutputStream","ExternalData","SQLiteExample",
            "MySurfaceViewExample","Accelerate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Making Activity FullScreen
        // WORKING
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // End

        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
        registerForContextMenu(getListView());

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String sel = classes[position];
        try {
            Class selected = Class.forName("com.example.spider007.trsvisapp." + sel);
            Intent activity = new Intent(Menu.this, selected);
            startActivity(activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
            Intent i = new Intent("com.example.spider007.AboutUs");
            startActivity(i);
            return true;
        } else if (id == R.id.action_pref) {
            Intent i = new Intent("com.example.spider007.Pref");
            startActivity(i);
            return true;
        } else if (id == R.id.exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
