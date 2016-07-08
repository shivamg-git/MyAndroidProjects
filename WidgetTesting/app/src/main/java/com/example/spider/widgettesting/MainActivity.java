package com.example.spider.widgettesting;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Random;
import java.util.logging.Handler;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    TextView tv;
    Button button, rb;
    RadioGroup rg;
    RadioButton apple, banana, orange, melon;
    RadioButton[] choise;
    CheckBox c1, c2, c3;
    Switch sw;
    ToggleButton tb;
    ImageView image;
    SeekBar sb;
    Button bp1, bp2;
    ProgressDialog pd1;
    Handler handler;
    int progress = 0;
    long filesize = 0;
    long totalfilesize = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUIReferences();
        tv.setOnClickListener(this);
        button.setOnClickListener(this);
        button.setOnLongClickListener(this);
        rb.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);
        c1.setOnCheckedChangeListener(this);
        c2.setOnCheckedChangeListener(this);
        c3.setOnCheckedChangeListener(this);
        sw.setOnCheckedChangeListener(this);
        tb.setOnCheckedChangeListener(this);
        sb.setOnSeekBarChangeListener(this);
        bp1.setOnClickListener(this);
        bp2.setOnClickListener(this);

    }


    // finding all the UI components.
    private void setUIReferences() {
        tv = (TextView) findViewById(R.id.tv);
        button = (Button) findViewById(R.id.button);
        rg = (RadioGroup) findViewById(R.id.rg);
        apple = (RadioButton) findViewById(R.id.apple);
        banana = (RadioButton) findViewById(R.id.banana);
        orange = (RadioButton) findViewById(R.id.orange);
        melon = (RadioButton) findViewById(R.id.melon);
        rb = (Button) findViewById(R.id.radioButtonOk);
        choise = new RadioButton[4];
        choise[0] = apple;
        choise[1] = banana;
        choise[2] = orange;
        choise[3] = melon;
        c1 = (CheckBox) findViewById(R.id.checkBox);
        c2 = (CheckBox) findViewById(R.id.checkBox2);
        c3 = (CheckBox) findViewById(R.id.checkBox3);
        sw = (Switch) findViewById(R.id.switch1);
        tb = (ToggleButton) findViewById(R.id.toggleButton);
        image = (ImageView) findViewById(R.id.image);
        sb = (SeekBar) findViewById(R.id.seekBar);
        bp1 = (Button) findViewById(R.id.button1);
        bp2 = (Button) findViewById(R.id.button2);
    }

    @Override
    public void onClick(View v) {

        if (v == tv) {
            Random random = new Random();
            int color = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            tv.setTextColor(color);
        }

        if (v == button) {
            Toast.makeText(this, "Button Clicked!!! ", Toast.LENGTH_SHORT).show();
        }

        if (v == rb) {
            for (RadioButton ch : choise) {
                if (ch.isChecked())
                    Toast.makeText(this, ch.getText() + " is Accepted!!!", Toast.LENGTH_SHORT).show();
            }
        }

        if (v == bp1) {
            pd1 = new ProgressDialog(this);
            pd1.setTitle("Progress Dialog");
            pd1.setMessage("Loading...");
            pd1.show();

            Log.d("MAIN", "before Thread");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pd1.dismiss();
                }
            }).start();


        }

        if (v == bp2) {
            pd1 = new ProgressDialog(this);
            pd1.setTitle("Downloading");
            pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd1.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (progress < 100) {
                        progress = checkfilesize();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        pd1.setProgress(progress);
                    }
                    pd1.dismiss();
                }
            }).start();

        }

    }

    private int checkfilesize() {
        filesize += 100;
        return (int) ((filesize * 1.0 / totalfilesize) * 100);
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == button) {
            Toast.makeText(this, "Button Long Pressed! ", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Toast.makeText(this, Integer.toString(checkedId), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == sw) {
            if (sw.isChecked()) {
                tb.setChecked(true);
                setimage(true);
            } else {
                tb.setChecked(false);
                setimage(false);
            }
        }
        if (buttonView == tb) {
            if (tb.isChecked()) {
                sw.setChecked(true);
                setimage(true);
            } else {
                sw.setChecked(false);
                setimage(false);
            }
        }

        if (buttonView == c1) {
            if (c1.isChecked()) {
                Toast.makeText(this, c1.getText() + " is Checked!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, c1.getText() + " is UNChecked!!", Toast.LENGTH_SHORT).show();
            }
        }
        if (buttonView == c2) {
            if (c2.isChecked()) {
                Toast.makeText(this, c2.getText() + " is Checked!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, c2.getText() + " is UNChecked!!", Toast.LENGTH_SHORT).show();
            }
        }
        if (buttonView == c3) {
            if (c3.isChecked()) {
                Toast.makeText(this, c3.getText() + " is Checked!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, c3.getText() + " is UNChecked!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setimage(boolean on) {
        if (on) {
            image.setImageResource(R.drawable.bon);
        } else
            image.setImageResource(R.drawable.boff);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tv.setText(Integer.toString(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Toast.makeText(this, "you touched seekbar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Toast.makeText(this, "you left seekbar", Toast.LENGTH_SHORT).show();
    }
}
