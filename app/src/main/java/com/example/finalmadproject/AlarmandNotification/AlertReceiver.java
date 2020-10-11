package com.example.finalmadproject.AlarmandNotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.finalmadproject.TaskManagement.UpdateSubject;

public class AlertReceiver extends BroadcastReceiver {
    public static MediaPlayer mediaplayer;
    @Override
    public void onReceive(Context context, Intent intent) {
        //Notification
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());

        //Play the alarm -MediaPlayer
        mediaplayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaplayer.start();
        System.out.println("Method running ------------------------------------------");
        Toast.makeText(context, "Alarm Method is triggering", Toast.LENGTH_SHORT).show();



    }
}
