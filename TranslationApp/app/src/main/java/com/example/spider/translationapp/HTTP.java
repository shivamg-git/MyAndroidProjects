package com.example.spider.translationapp;

import android.util.Log;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/***
 * Created by spider on 2/7/16.
 ***/
public class HTTP {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Log.d("URL: ",url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.d("Executed: ","OK");
            return response.body().string();
        }catch (Exception e){
            Log.d("Executed: ","NOT OK");
            e.printStackTrace();
            return null;
        }
    }
}
