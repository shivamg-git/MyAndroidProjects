package com.example.spider007.basiccalci1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText in1, in2;
    String operation = "+";
    TextView tv;
    Button add, sub,div;
    Float answer;
    Float input1, input2;

    private Float applyOperation(Float in1 , Float in2){

        Float result=0.0f;
        switch (this.operation){
            case "+":
                result =  in1 + in2 ;
                break;
            case "-":
                result = in1 - in2 ;
                break;
            case "/":
                result = in1 / in2 ;
                break;

        }
        return  result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.ans);
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        div = (Button) findViewById(R.id.div);
        in1 = (EditText) findViewById(R.id.i1);
        in2 = (EditText) findViewById(R.id.i2);

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = "/";
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = "+";
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = "-";
            }
        });

        in1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                apply();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                tv.setText("before in 1");
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                tv.setText("on in 1");
            }
        });

        in2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                apply();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                tv.setText("before in 2");
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                tv.setText("on in 2");
            }
        });

    }

    private void apply(){
        if(in2.getText().toString().matches(""))
        {
            input2 = 0.0f;
        }else{
            input2 = Float.parseFloat(in2.getText().toString());
        }
        if(in1.getText().toString().matches(""))
        {
            input1 = 0.0f;
        }else{
            input1 = Float.parseFloat(in1.getText().toString());
        }

        answer = applyOperation(input1,input2);
        tv.setText(answer.toString());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
