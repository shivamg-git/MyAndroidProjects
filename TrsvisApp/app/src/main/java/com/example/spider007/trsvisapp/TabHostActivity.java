package com.example.spider007.trsvisapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by spider007 on 28/12/15.
 */
public class TabHostActivity extends Activity implements View.OnClickListener {

    TabHost th;
    Button start, stop, addtab;
    long startTime, stopTime;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        initialize();
        setTabs();
        addtab.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    private void setTabs() {
        th.setup();
        TabHost.TabSpec ts = th.newTabSpec("tag1");
        ts.setContent(R.id.tab1);
        ts.setIndicator("Stop Watch");
        th.addTab(ts);

        ts = th.newTabSpec("tag2");
        ts.setContent(R.id.tab2);
        ts.setIndicator("Tab");
        th.addTab(ts);

        ts = th.newTabSpec("tag3");
        ts.setContent(R.id.tab3);
        ts.setIndicator("Add Tab ");
        th.addTab(ts);
    }

    private void initialize() {
        th = (TabHost) findViewById(R.id.tabHost);
        start = (Button) findViewById(R.id.bStart);
        stop = (Button) findViewById(R.id.bStop);
        addtab = (Button) findViewById(R.id.bAddTab);
        result = (TextView) findViewById(R.id.result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bAddTab:
                TabHost.TabSpec tsNew = th.newTabSpec("Tag1");
                tsNew.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        TextView tv = new TextView(TabHostActivity.this);
                        tv.setText("You have created new tab");
                        return tv;
                    }
                });
                tsNew.setIndicator("New Tab");
                th.addTab(tsNew);
                break;

            case R.id.bStart:
                startTime = System.currentTimeMillis();

                break;

            case R.id.bStop:
                stopTime = System.currentTimeMillis();
                if (startTime != 0) {
                    long rs = stopTime - startTime;
                    int min, sec, mil, c1;
                    c1 = (int) rs % 60000;
                    min = (int) rs / 60000;
                    mil = c1 % 1000;
                    sec = c1 / 1000;
                    result.setText(String.format("%d:%d:%d",min,sec,mil));
                }
                break;
        }

    }
}
