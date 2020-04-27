package com.example.forheart.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

/**
 *  Util class to handle alarm event
 */
public class AlarmManagerUtils {

    private Context context;
    public static AlarmManager am;
    public static PendingIntent pendingIntent;
    private Calendar calendar;

    public final static String TIMER_ACTION = "com.example.forheart.TIMER_ACTION";

    public AlarmManagerUtils(Context aContext) {
        this.context = aContext;
    }

    // singleton design pattern
    private static AlarmManagerUtils INSTANCE;

    public static synchronized AlarmManagerUtils getINSTANCE(Context aContext) {
        if (INSTANCE == null) {
            INSTANCE = new AlarmManagerUtils(aContext);
        }
        return INSTANCE;
    }

    public void setAlarm(int id, long dateTime) {
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(TIMER_ACTION);
        pendingIntent = PendingIntent.getBroadcast(context, id, myIntent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, dateTime, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setExact(AlarmManager.RTC_WAKEUP, dateTime, pendingIntent);
        }
    }

    public void cancelAlarm(int id) {
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(TIMER_ACTION);
        pendingIntent = PendingIntent.getBroadcast(context, id, myIntent, 0);
        am.cancel(pendingIntent);
    }
}
