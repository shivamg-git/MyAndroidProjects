package com.example.spider007.trsvisapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by spider007 on 22/12/15.
 */
public class Splash extends Activity {
    MediaPlayer startmusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        startmusic = MediaPlayer.create(Splash.this, R.raw.this_is_sparta);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Boolean music = pref.getBoolean("checkbox", true);

        if(music) startmusic.start();

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent menu= new Intent("com.example.spider007.MENU");
                    startActivity(menu);
                }
            }
        };
        timer.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
        startmusic.release();
    }
}
