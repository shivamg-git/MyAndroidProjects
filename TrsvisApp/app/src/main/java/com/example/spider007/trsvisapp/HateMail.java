package com.example.spider007.trsvisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HateMail extends AppCompatActivity {

    EditText name, hateFull, lastWord,email;
    String sName, sHateFull, sLastWord,sEmail;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hatemail);
        initialize();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToString();
                String emails[] = { sEmail };
                String message  = "Hello " + sName + "\n"
                        +"You are " + sHateFull + "\n" +
                        "finally,"+sLastWord ;
                Intent sendMail = new Intent(Intent.ACTION_SEND);
                sendMail.putExtra(Intent.EXTRA_EMAIL,emails);
                sendMail.putExtra(Intent.EXTRA_SUBJECT, "I hate You!");
                sendMail.setType("plain/text");
                sendMail.putExtra(Intent.EXTRA_TEXT,message);
                startActivity(sendMail);
            }
        });

    }

    private void initialize() {
        email = (EditText) findViewById(R.id.etEmail);
        name = (EditText) findViewById(R.id.etName);
        hateFull = (EditText) findViewById(R.id.etHateFull);
        lastWord = (EditText) findViewById(R.id.etLastWords);
        send = (Button) findViewById(R.id.bSend);
    }

    private void convertToString() {
        sEmail = email.getText().toString();
        sName = name.getText().toString();
        sHateFull = hateFull.getText().toString();
        sLastWord = lastWord.getText().toString();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
