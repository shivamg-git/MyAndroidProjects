package com.example.spider.wheather;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new PlaceHolderFragment())
                    .commit();
        }
    }

    public static class PlaceHolderFragment extends android.support.v4.app.Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_main, container);
            String[] data = {"hello", "Hello", "hello", "Hello", "hello", "Hello", "hello", "Hello", "hello", "Hello", "hello", "Hello", "hello", "Hello"};
            List<String> list = new ArrayList<String>(Arrays.asList(data));
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.text_view, R.id.text, list);
            ListView lv = (ListView) v.findViewById(R.id.list_view);
            lv.setAdapter(arrayAdapter);
            return v;
        }
    }
}