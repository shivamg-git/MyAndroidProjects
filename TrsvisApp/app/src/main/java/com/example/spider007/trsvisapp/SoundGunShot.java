package com.example.spider007.trsvisapp;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class SoundGunShot extends Activity implements View.OnClickListener,View.OnLongClickListener {

    SoundPool sp;
    int gs = 0;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        gs = sp.load(this, R.raw.gun_shot, 1);
        mp = MediaPlayer.create(SoundGunShot.this, R.raw.bg1);

    }

    @Override
    public void onClick(View v) {

        if (gs != 0) {
            sp.play(gs, 1, 1, 0, 0, 1);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Log.d("Hello",mp.toString());
        mp.start();
        return false;
    }

}
