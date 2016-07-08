package com.example.spider.activity_life;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener{

    String tag = "Lifecycle";
    TextView tv;

    CharSequence[] items = { "Google", "Apple","Facebook"};
    boolean[] itemChecked = new boolean[items.length];
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        String temp = tv.getText() + "OnCreate\n";
        tv.setText(temp);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ratingno = (TextView) findViewById(R.id.textView);
        rb = (RatingBar) findViewById(R.id.ratingBar);
        rb.setOnRatingBarChangeListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "onStart");
        String temp = tv.getText() + "OnStart\n";
        tv.setText(temp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "onResume");
        String temp = tv.getText() + "OnResume\n";
        tv.setText(temp);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(tag, "onRestart");
        String temp = tv.getText() + "OnRestart\n";
        tv.setText(temp);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "onPause");
        String temp = tv.getText() + "OnPause\n";
        tv.setText(temp);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "onStop");
        String temp = tv.getText() + "OnStop\n";
        tv.setText(temp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy");
        String temp = tv.getText() + "OnDestroy\n";
        tv.setText(temp);
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


    //    Dialog Box
    public void onClick(View v) {

        showDialog(0);
    }

    public void onClick2(View v) {

        final ProgressDialog dialog = ProgressDialog.show(this, "Doing Something","Loading...", true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                    dialog.dismiss();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });


    }


    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case 0:
                return new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("This is a dialog with Simple Text ...")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "OK Clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "Cancel Clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setMultiChoiceItems(items, itemChecked, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        Toast.makeText(getBaseContext(), items[which] + (isChecked ? "Checked!!!" : "Unchecked!!!" ), Toast.LENGTH_SHORT).show();
                                    }
                                }
                        ).create();
        }
        return null;
    }
    TextView ratingno;


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        String rat = Float.toString(rating);
        ratingno.setText(rat);
    }
}
