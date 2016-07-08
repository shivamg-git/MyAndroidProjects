package com.example.spider007.trsvisapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Send extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    String day;
    TextView que, selected, tvname;
    RadioGroup rg;
    Button sendResult;

    private void initialize() {
        tvname = (TextView) findViewById(R.id.tvName);
        que = (TextView) findViewById(R.id.tvQue);
        rg = (RadioGroup) findViewById(R.id.rgQue);
        sendResult = (Button) findViewById(R.id.bSendResult);
        selected = (TextView) findViewById(R.id.tvSelection);
        rg.setOnCheckedChangeListener(this);
        sendResult.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = sp.getString("name", "Shivam");


        Bundle data = getIntent().getExtras();
        try {
            tvname.setText("Hello, " + data.get("name"));
        } catch (java.lang.NullPointerException e) {
            e.printStackTrace();
        }

        String c = sp.getString("list", "4");
        if (c.contentEquals("1")) tvname.setText(et);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbMonday:
                day = "monday";
                break;
            case R.id.rbTuesday:
                day = "tuesday";
                break;
            case R.id.rbWednesday:
                day = "wednesday";
                break;
            case R.id.rbNone:
                day = "none";
                break;
        }
        selected.setText("Your Ans :  " + day);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSendResult:
                Bundle basket = new Bundle();
                basket.putString("ans", day);
                Intent i = new Intent();
                i.putExtras(basket);
                setResult(RESULT_OK, i);
                finish();
                break;
        }

    }
}
