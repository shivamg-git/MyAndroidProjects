package com.example.spider007.trsvisapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileOutputStream extends AppCompatActivity implements View.OnClickListener {

    public static String file = "OutPutFile";
    Button load, save;
    EditText data;
    TextView tv;
    java.io.FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_on_shared_preferences);

        initialize();

        // Open Closing of file...to store some data!
        /*
        try {
            fos = openFileOutput(file, MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */


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
        switch (v.getId()) {
            case R.id.bSave:
                String data1 = this.data.getText().toString();

                // Store data of file Using normal Files!

                /*
                File fl = new File(file);
                try {

                    fos = new java.io.FileOutputStream(fl);
                        // Write Somthing here!
                    fos.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                */

                try {
                    fos = openFileOutput(file, Context.MODE_PRIVATE);
                    fos.write(data1.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(FileOutputStream.this,"Data Saved",500).show();
                break;

            case R.id.bLoad:
                load.setVisibility(View.GONE);
                new loadSomeStuff().execute(file);
                break;

        }
    }

    //Asyn <what is passed in , for progress , what is got returned >

    public class loadSomeStuff extends AsyncTask<String, Integer, String> {

        ProgressDialog pg;
        String collected = null;
        FileInputStream fis = null;

        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i < 20; i++) {
                publishProgress(5);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pg.dismiss();

            try {
                fis = openFileInput(file);
                byte[] dataArray = new byte[fis.available()];
                while (fis.read(dataArray) != -1) {
                    collected = new String(dataArray);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg = new ProgressDialog(FileOutputStream.this);
            pg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pg.setMax(100);
            pg.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            load.setVisibility(View.VISIBLE);
            tv.setText(collected);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pg.incrementProgressBy(values[0]);
        }
    }
}
