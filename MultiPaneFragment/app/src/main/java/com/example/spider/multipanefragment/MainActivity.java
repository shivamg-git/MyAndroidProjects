package com.example.spider.multipanefragment;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int CurrentOrientation = getResources().getConfiguration().orientation;
        if (CurrentOrientation == Configuration.ORIENTATION_PORTRAIT){
            hideSideBar();
        }
    }

    private void hideSideBar() {
        View sidebar = findViewById(R.id.side_panel);
        if(sidebar.getVisibility() == View.VISIBLE){
            sidebar.setVisibility(View.INVISIBLE);
        }
    }
}
