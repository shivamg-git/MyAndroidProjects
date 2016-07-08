package com.example.spider007.trsvisapp;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;

public class BackgroundSet extends AppCompatActivity implements View.OnClickListener {

    ImageView iv;
    ImageButton ib;
    Button b;
    int cameraData;
    Intent takeImage;
    Bitmap bm;

    private void initialize() {
        iv = (ImageView) findViewById(R.id.ivShowImage);
        ib = (ImageButton) findViewById(R.id.ibTakeImage);
        b = (Button) findViewById(R.id.bSetBackground);
        ib.setOnClickListener(this);
        b.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_set);
        initialize();
        InputStream is = getResources().openRawResource(R.drawable.logo);
        bm = BitmapFactory.decodeStream(is);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bm = (Bitmap) extras.get("data");
            iv.setImageBitmap(bm);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibTakeImage:
                takeImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takeImage, cameraData);
                break;
            case R.id.bSetBackground:

                WallpaperManager image = WallpaperManager.getInstance(getApplicationContext());
                try {
                    image.setBitmap(bm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
