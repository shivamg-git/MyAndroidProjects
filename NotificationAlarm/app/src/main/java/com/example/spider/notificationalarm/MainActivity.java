package com.example.spider.notificationalarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    NotificationManager notificationManager;
    int notifID = 33;
    boolean isNotificActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartNotificationButtonClick(View view) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        notification.setColor(Color.GRAY)
                .setContentTitle("Message")
                .setContentText("New Message")
                .setTicker("Alert New Message")
                .setSmallIcon(R.mipmap.ic_launcher);

        Intent moreInfoIntent = new Intent(this, MoreInfoNotification.class);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MoreInfoNotification.class);
        taskStackBuilder.addNextIntent(moreInfoIntent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifID, notification.build());
        isNotificActive = true;
    }

    public void onStopNotificationButtonClick(View view) {
        if(isNotificActive) {
            notificationManager.cancel(notifID);
        }
    }

    public void onAlarmButtonClick(View view) {

        Long alertTime = new GregorianCalendar().getTimeInMillis()+5000;
        Intent alertIntent = new Intent(this, AlertReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime,
                PendingIntent.getBroadcast(this, 1, alertIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT));
    }
}
