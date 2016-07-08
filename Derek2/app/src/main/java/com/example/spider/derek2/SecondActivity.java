package com.example.spider.derek2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by spider on 30/6/16.
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.second_activity);
        super.onCreate(savedInstanceState);
    }

    public void Submit(View view) {
        Intent intent = getIntent();
        EditText et = (EditText) findViewById(R.id.editText);
        String name = String.valueOf(et.getText());
//        intent.putExtra("Name",name);

//  Passing Object in Intent
        Human human = new Human(name,21);
        intent.putExtra("HUMAN",human);

        setResult(RESULT_OK,intent);
        finish();
    }
}
