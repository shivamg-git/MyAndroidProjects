package com.example.spider.imagedb;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

/*** Created by spider on 6/7/16. */
public class ImageAdapter extends ArrayAdapter<String> {
    public ImageAdapter(Context context, String... resource) {
        super(context,R.layout.image_tile ,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.image_tile,parent,false);
        ImageView im = (ImageView) view.findViewById(R.id.image);
        String uriInString = getItem(position);
        Uri uri = Uri.parse(uriInString);
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
            im.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.findViewById(R.id.gradient).setAlpha(0.3f);
        return view;
    }
}
