package com.example.spider007.trsvisapp;// Created by spider007 on 31/12/15.

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class MySurfaceViewExample extends Activity implements View.OnTouchListener {

    SpiderSurfaceView Ssv;
    float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Ssv = new SpiderSurfaceView(MySurfaceViewExample.this);
        Ssv.setOnTouchListener(this);
        setContentView(Ssv);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Ssv.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Ssv.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                Toast.makeText(this,"Action UP",Toast.LENGTH_LONG).show();
                break;
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(this,"Action DOWN",Toast.LENGTH_LONG).show();
                break;
        }



        return true;
    }


    public class SpiderSurfaceView extends SurfaceView implements Runnable {

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        private boolean isRunning = true;

        public SpiderSurfaceView(Context context) {
            super(context);
            ourHolder = getHolder();
        }

        public void pause() {
            isRunning = false;
            while (true) {
                try {
                    ourThread.join();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ourThread = null;
        }

        public void resume() {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run() {
            while (isRunning) {
                if (!ourHolder.getSurface().isValid())
                    continue;

                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(50, 50, 50);
                Paint p = new Paint();
                p.setColor(Color.rgb(200, 200, 200));
                canvas.drawRect(30, 30, canvas.getWidth() - 30, canvas.getHeight() - 30, p);
                if (x != 0 && y != 0) {
                    p.setColor(Color.rgb(50, 50, 50));
                    p.setTextSize(30);
                    canvas.drawText("X : " + x + "\tY : " + y, 100, canvas.getHeight() - 60, p);
                    Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.button_blank_blue_01);
                    canvas.drawBitmap(bm,x-bm.getWidth()/2,y-bm.getHeight()/2,null);
                }
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
