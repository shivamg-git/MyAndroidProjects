package com.example.spider.download;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by spider on 5/7/16.
 */
public class FileService extends IntentService {

    public static final String TRANSACTION_DONE = "com.example.spider.download.TRANSACTION_DONE";

    public FileService() {
        super(FileService.class.getName());
    }

    public FileService(String s) {
        super(s);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("FileService", "Service Started!");
        String passedUrl = intent.getExtras().getString("URL");
        downloadFile(passedUrl);
        Log.e("FileService", "Service Ended!");
        Intent i = new Intent(TRANSACTION_DONE);
        FileService.this.sendBroadcast(i);
        Log.e("S","broadcasted!");
    }

    private void downloadFile(String passedUrl) {
        String fileName = "myFile";
        try {
            FileOutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            URL fileURL = new URL(passedUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) fileURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();

            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0) {

                // Write the data received to our file
                outputStream.write(buffer, 0, bufferLength);
            }
            Log.e("S","download");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
