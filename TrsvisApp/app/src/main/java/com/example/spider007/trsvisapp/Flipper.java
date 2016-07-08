package com.example.spider007.trsvisapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class Flipper extends AppCompatActivity implements View.OnClickListener {

    ViewFlipper fl;
    Button back, auto, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);
        initialize();

    }

    private void initialize() {
        fl = (ViewFlipper) findViewById(R.id.viewFlipper);
        fl.setOnClickListener(this);
        back = (Button) findViewById(R.id.bBack);
        auto = (Button) findViewById(R.id.bAuto);
        next = (Button) findViewById(R.id.bNext);
        back.setOnClickListener(this);
        auto.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bBack:
                if(fl.isFlipping()) fl.stopFlipping();
                fl.showPrevious();
                break;

            case R.id.bAuto:
                fl.setFlipInterval(500);
                fl.startFlipping();
                break;

            case R.id.bNext:
            case R.id.viewFlipper:
                if(fl.isFlipping()) fl.stopFlipping();
                fl.showNext();
                break;
        }
    }
}
