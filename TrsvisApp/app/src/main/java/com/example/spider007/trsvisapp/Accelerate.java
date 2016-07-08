package com.example.spider007.trsvisapp;// Created by spider007 on 1/1/16.

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener {

    SpiderSurfaceView sv;
    float x, y, sensorX, sensorY;
    SensorManager sm;
    Bitmap bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for the sensor !
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(Accelerate.this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
        bm = BitmapFactory.decodeResource(getResources(),R.drawable.button_blank_blue_01);
        x = y = sensorX = sensorY = 0;

        sv = new SpiderSurfaceView(Accelerate.this);
        sv.resume();
        setContentView(sv);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(Accelerate.this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];
        //ramp-speed - play with this value until satisfied

        float kFilteringFactor = 0.1f;

//last result storage - keep definition outside of this function, eg. in wrapping object
        float accel[]=new float[2];

//acceleration.x,.y,.z is the input from the sensor

//result.x,.y,.z is the filtered result

//high-pass filter to eliminate gravity
        accel[0] = sensorX * kFilteringFactor + accel[0] * (1.0f - kFilteringFactor);
        accel[1] = sensorY * kFilteringFactor + accel[1] * (1.0f - kFilteringFactor);
        x = sensorX - accel[0];
        y = sensorY - accel[1];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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
                float centerX = canvas.getWidth()/2;
                float centerY = canvas.getHeight()/2;

                canvas.drawBitmap(bm,centerX-(x*80)-(bm.getWidth()/2),centerY+(y*80)-(bm.getHeight()/2),null);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

}
