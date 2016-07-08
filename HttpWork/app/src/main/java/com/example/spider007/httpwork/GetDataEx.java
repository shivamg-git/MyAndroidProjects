package com.example.spider007.httpwork;// Created by spider007 on 1/1/16.

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class GetDataEx {
    public String lastResponce="";

    public void getInternetData(Context c ,final TextView tv) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(c);
        String url = "http://collnect1.herokuapp.com/events.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tv.setText(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SHIVAM:", error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
