package com.example.spider007.trsvisapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;

public class Slider extends Activity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener,SlidingDrawer.OnDrawerOpenListener {

    Button b1,b2,b3,b4,b5;
    CheckBox lock;
    SlidingDrawer slider;
    private void initialize(){
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        lock = (CheckBox) findViewById(R.id.lock);
        slider = (SlidingDrawer) findViewById(R.id.slidingDrawer);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        lock.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider);
        initialize();
        slider.setOnDrawerOpenListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b1:
                slider.open();
                break;
            case R.id.b2:
                break;
            case R.id.b3:
                break;
            case R.id.b4:
                slider.toggle();
                break;
            case R.id.b5:
                slider.close();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.isChecked()){
            slider.lock();
        }else{
            slider.unlock();
        }
    }

    @Override
    public void onDrawerOpened() {
        MediaPlayer mp =MediaPlayer.create(this,R.raw.gun_shot);
        mp.start();
    }
}
