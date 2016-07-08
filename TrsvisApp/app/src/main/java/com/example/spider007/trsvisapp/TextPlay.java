package com.example.spider007.trsvisapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

public class TextPlay extends AppCompatActivity {

    EditText command;
    Button try_command;
    ToggleButton pass;
    TextView print;

    private void initialize() {
        command = (EditText) findViewById(R.id.etCommand);
        try_command = (Button) findViewById(R.id.bCommand);
        pass = (ToggleButton) findViewById(R.id.tbpass);
        print = (TextView) findViewById(R.id.tvout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_play);

        initialize();
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pass.isChecked()) {
                    command.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    command.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

        try_command.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String comm = command.getText().toString();
                        switch (comm) {
                            case "left":
                                print.setText("Left");
                                print.setGravity(Gravity.START);
                                print.setTextColor(Color.GREEN);
                                break;
                            case "right":
                                print.setText("Right");
                                print.setGravity(Gravity.END);
                                print.setTextColor(Color.GREEN);

                                break;
                            case "center":
                                print.setText("Center");
                                print.setGravity(Gravity.CENTER);
                                print.setTextColor(Color.GREEN);
                                break;
                            case "WTF":
                                Random seed = new Random();
                                print.setText("WTF!!!");
                                print.setTextSize(seed.nextInt(50));
                                print.setTextColor(Color.rgb(seed.nextInt(255), seed.nextInt(255), seed.nextInt(255)));
                                switch (seed.nextInt(3)) {
                                    case 0:
                                        print.setGravity(Gravity.END);
                                        break;
                                    case 1:
                                        print.setGravity(Gravity.START);
                                        break;
                                    case 2:
                                        print.setGravity(Gravity.CENTER);
                                        break;
                                }
                                break;

                            default:
                                print.setText("Invalid");
                                print.setGravity(Gravity.CENTER);
                                print.setTextColor(Color.RED);
                        }
                    }
                });
    }
}
