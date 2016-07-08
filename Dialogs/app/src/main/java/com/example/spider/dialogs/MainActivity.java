package com.example.spider.dialogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public Boolean isPlaying;
    public Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Music Play...
        i = new Intent(this, MyMusicService.class);
        isPlaying = true;
        startService(i);
    }

    public void onTimePick(View v) {
        Calendar calender = Calendar.getInstance();
        int minute = calender.get(Calendar.MINUTE);
        int hour = calender.get(Calendar.HOUR_OF_DAY);
        TimePickerDialog.OnTimeSetListener tListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String formattedDate = hourOfDay + " : " + minute;
                Toast.makeText(MainActivity.this, formattedDate, Toast.LENGTH_SHORT).show();
            }
        };
        new TimePickerDialog(MainActivity.this, tListener, hour, minute, false).show();
    }

    public void onDatePick(View view) {
        Calendar calender = Calendar.getInstance();
        int dd = calender.get(Calendar.DATE);
        int mm = calender.get(Calendar.MONTH);
        int yy = calender.get(Calendar.YEAR);

        DatePickerDialog.OnDateSetListener dpListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String formattedDate = dayOfMonth + "/" + monthOfYear + "/" + year;
                Toast.makeText(MainActivity.this, formattedDate, Toast.LENGTH_SHORT).show();
            }
        };
        new DatePickerDialog(MainActivity.this, dpListener, dd, mm, yy).show();

    }

    public void onProcessDialog(View view) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Download");
        pd.setMessage("Your Mind is Downloading");
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    pd.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void onAlert(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Notice");
        alert.setMessage("Are You Alive");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "OK you are Alive", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Really You dead!", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }

    public void onDialogCustom(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        Button b = (Button) dialog.findViewById(R.id.buttonOK);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void onMusicButton(View view) {
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.music);
        if (isPlaying) {
            assert fab != null;
            fab.setImageResource(android.R.drawable.ic_media_play);
            isPlaying = false;
            stopService(i);
        } else {
            assert fab != null;
            fab.setImageResource(android.R.drawable.ic_media_pause);
            isPlaying = true;
            startService(i);
        }
    }
}
