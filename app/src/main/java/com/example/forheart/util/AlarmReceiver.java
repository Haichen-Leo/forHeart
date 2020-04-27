package com.example.forheart.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.forheart.MainActivity;
import com.example.forheart.R;

public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManager notificationManager = null;
    public final static String TIMER_ACTION = "com.example.forheart.TIMER_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (intent.getAction().equals(TIMER_ACTION)) {
            Intent intent1 = new Intent(context,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intent1,0);
            Notification notify = new Notification.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("forHeart")
                    .setContentText("Time to take exercise!")
                    .setContentIntent(pendingIntent)
                    .build();
            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(1, notify);
        }
    }
}
