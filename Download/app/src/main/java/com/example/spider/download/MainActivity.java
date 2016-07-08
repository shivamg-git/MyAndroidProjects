package com.example.spider.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FileService.TRANSACTION_DONE);

        // Prepare the main thread to receive a broadcast and act on it
        registerReceiver(downloadReceiver, intentFilter);
    }

    public void startService(View view) {
        Intent file_service = new Intent(this, FileService.class);
        file_service.putExtra("URL", "https://www.newthinktank.com/wordpress/lotr.txt");
        startService(file_service);
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        // Called when the broadcast is received
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.e("FileService", "Service Received");

            showFileContents();
        }
    };

    private void showFileContents() {
        StringBuilder sb;

        try {
            // Opens a stream so we can read from our local file
            FileInputStream fis = openFileInput("myFile");

            // Gets an input stream for reading data
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

            // Used to read the data in small bytes to minimize system load
            BufferedReader bufferedReader = new BufferedReader(isr);

            // Read the data in bytes until nothing is left to read
            sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            // Put downloaded text into the EditText
            tv.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
