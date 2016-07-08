package com.example.spider007.trsvisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Get extends AppCompatActivity implements View.OnClickListener {

    EditText send;
    Button startActivity;
    Button SAFR;
    TextView tvAns;

    void initialize() {
        send = (EditText) findViewById(R.id.etSend);
        startActivity = (Button) findViewById(R.id.bStartActivity);
        SAFR = (Button) findViewById(R.id.bStartActivityForResult);
        tvAns = (TextView) findViewById(R.id.tvAns);
        startActivity.setOnClickListener(this);
        SAFR.setOnClickListener(this);
      }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        initialize();
    }

    @Override
    public void onClick(View v) {
        String text = send.getText().toString();
        Bundle basket = new Bundle();
        Intent i;
        switch (v.getId()) {
            case R.id.bStartActivity:

                basket.putString("name", text);
                i = new Intent(Get.this, Send.class);
                i.putExtras(basket);
                startActivity(i);
                break;

            case R.id.bStartActivityForResult:
                i = new Intent(Get.this, Send.class);
                startActivityForResult(i, 0);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tvAns.setText("h");
        if (resultCode == RESULT_OK) {
            Bundle b = data.getExtras();
            tvAns.setText(b.getString("ans"));
        }
    }
}