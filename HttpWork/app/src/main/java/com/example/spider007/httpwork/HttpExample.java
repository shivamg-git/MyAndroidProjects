package com.example.spider007.httpwork;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpExample extends AppCompatActivity {
    TextView tv;
//    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_example);
        tv = (TextView) findViewById(R.id.data);
//        b= (Button)findViewById(R.id.button);

        RequestQueue queue = Volley.newRequestQueue(HttpExample.this);
        String url = "http://collnect1.herokuapp.com/events.json";
        Log.d("S:","I am Here!");
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        JSONObject event = null;
                        try {
                            Log.d("Shivam", response.substring(0, 100));
                            JSONArray events = new JSONArray(response);
                            Log.d("Shivam:", events.toString());
                            event = events.getJSONObject(0);
                            Log.d("Shivam:", event.toString());

                            Log.d("Shivam:", event.getString("name"));
//                            tv.setText(event.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            tv.setText(event.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SHIVAM:", error.toString());
                    }
                });
        queue.add(stringRequest);
        }
}

