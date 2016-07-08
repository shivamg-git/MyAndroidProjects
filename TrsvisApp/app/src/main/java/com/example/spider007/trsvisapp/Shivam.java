package com.example.spider007.trsvisapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class Shivam extends View {

    Bitmap gball;
    int y;
    int side = 0;
    Typeface font;

    public Shivam(Context context) {
        super(context);
        gball = BitmapFactory.decodeResource(getResources(), R.drawable.button_blank_gray_01);
        font = Typeface.createFromAsset(context.getAssets(), "GoodDog.otf");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.rgb(77, 77, 77));
        Paint brush = new Paint();
        Paint textbrush = new Paint();
        textbrush.setColor(Color.WHITE);
        textbrush.setTextSize(50);
        textbrush.setTextAlign(Paint.Align.CENTER);
        textbrush.setTypeface(font);
        canvas.drawText("Spider", canvas.getWidth() / 2, canvas.getHeight() / 2, textbrush);


        brush.setColor(Color.GREEN);
        int i = 0;
        for (; i < canvas.getHeight(); i += 400) {
            canvas.drawRect(0, i, canvas.getWidth(), 100 + i, brush);
        }

        canvas.drawBitmap(gball, (canvas.getWidth() / 2) - (gball.getWidth() / 2), y, null);
        if (side == 0) {
            if (y + gball.getHeight() < canvas.getHeight())
                y += 4;
            else
                side = 1;
        } else {
            if (y > 0)
                y -= 4;
            else
                side = 0;
        }

        brush.setColor(Color.RED);
        for (i = 200; i < canvas.getHeight(); i += 400) {
            canvas.drawRect(0, i, canvas.getWidth(), 100 + i, brush);
        }
        invalidate();
    }
}
