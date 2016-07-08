package com.example.spider007.trsvisapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

public class GFX extends Activity {

    Shivam ourView;
    PowerManager.WakeLock wl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PowerManager pm = (PowerManager )getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK,"Whatever");

        super.onCreate(savedInstanceState);
        wl.acquire();
        ourView = new Shivam(this);
        setContentView(ourView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        wl.release();
    }
}
