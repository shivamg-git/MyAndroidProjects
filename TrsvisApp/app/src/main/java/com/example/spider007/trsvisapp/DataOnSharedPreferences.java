package com.example.spider007.trsvisapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DataOnSharedPreferences extends AppCompatActivity implements View.OnClickListener{

    Button load,save;
    EditText data;
    TextView tv;
    SharedPreferences sp;
    public static String file = "MySecretFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_on_shared_preferences);
        initialize();
        sp = getSharedPreferences(file, 0);

    }

    private void initialize() {

        data = (EditText) findViewById(R.id.editText);
        load = (Button) findViewById(R.id.bLoad);
        save = (Button) findViewById(R.id.bSave);
        tv = (TextView) findViewById(R.id.textView);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSave:
                String sharedData  = data.getText().toString();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Data",sharedData);
                editor.apply();
                break;

            case R.id.bLoad:
                sp = getSharedPreferences(file, 0);
                String d = sp.getString("Data","Coudn't Load data");
                tv.setText(d);
                break;
        }
    }
}
