package com.example.spider.fragmentsimple;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Configuration config = getResources().getConfiguration();

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            FragmentLandscape fragmentLandscape = new FragmentLandscape();
            fragmentTransaction.replace(android.R.id.content,fragmentLandscape);
        }
        else{
            FragmentPortrait fragmentPortrait = new FragmentPortrait();
            fragmentTransaction.replace(android.R.id.content,fragmentPortrait);
        }
        fragmentTransaction.commit();

    }
}
