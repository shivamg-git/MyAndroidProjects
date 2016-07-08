package com.example.spider007.trsvisapp;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.io.FileOutputStream;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.DIRECTORY_MUSIC;
import static android.os.Environment.DIRECTORY_PICTURES;

public class ExternalData extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView tvRead, tvWrite;
    boolean canR, canW;
    Spinner spinner;
    EditText etFileName;
    Button bConform, bSave;
    File path = null;
    File file_name = null;
    String state;
    String[] paths = {"Music", "Picture", "Download", "Spider"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_data);
        initialize();
        checkstatus();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_dropdown_item, paths);
        spinner.setAdapter(adapter);

    }

    private void checkstatus() {
        state = Environment.getExternalStorageState();
        tvWrite.setText("Write Permition : false");
        tvRead.setText("Read Permition  : false");

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            canR = canW = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            canR = true;
            canW = false;
        } else {
            canR = canW = false;
        }
        if (canR) tvRead.setText("Read Permition  : True");
        if (canW) tvWrite.setText("Write Permition : True");
    }

    private void initialize() {
        tvRead = (TextView) findViewById(R.id.tvRead);
        tvWrite = (TextView) findViewById(R.id.tvWrite);
        spinner = (Spinner) findViewById(R.id.spinner);
        etFileName = (EditText) findViewById(R.id.etFileName);
        bConform = (Button) findViewById(R.id.bConform);
        bSave = (Button) findViewById(R.id.bSave);
        bConform.setOnClickListener(this);
        bSave.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bConform:
                bSave.setVisibility(View.VISIBLE);
                break;

            case R.id.bSave:
                String filen = etFileName.getText().toString();
                file_name = new File(path + "/" + filen + ".png");

                checkstatus();
                if (canR && canW) {
                    path.mkdirs();

                    try {
                        InputStream is = getResources().openRawResource(R.drawable.logo);
                        FileOutputStream os = new FileOutputStream(file_name);
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();
                        Toast.makeText(this, "file has been created!", Toast.LENGTH_LONG).show();

                        MediaScannerConnection.scanFile(ExternalData.this,
                                new String[]{file_name.toString()},
                                null,
                                new MediaScannerConnection.OnScanCompletedListener() {

                                    @Override
                                    public void onScanCompleted(String path, Uri uri) {
                                        //TODO Why it's not working
                                        Toast t;
                                        t = Toast.makeText(ExternalData.this, "updated", Toast.LENGTH_LONG);
                                        t.show();
                                    }
                                });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (spinner.getSelectedItemPosition()) {
            case 0:
                path = Environment.getExternalStoragePublicDirectory(DIRECTORY_MUSIC);
                Log.d("SPIDER>> Abs path : ", path.getAbsolutePath());
                break;

            case 1:
                path = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES);
                break;

            case 2:
                path = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS);
                break;

            case 3:
                path = new File(Environment.getExternalStorageDirectory().getPath() + "/Spider");
                path.setWritable(true);
                Log.d("SPIDER>> Absolut : ", path.getAbsolutePath());
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
