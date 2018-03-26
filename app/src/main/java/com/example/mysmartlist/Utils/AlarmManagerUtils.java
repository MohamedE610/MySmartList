package com.example.mysmartlist.Utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.mysmartlist.Services.ReportService;

import java.util.Calendar;

/**
 * Created by abdallah on 3/26/2018.
 */

public class AlarmManagerUtils {

    static final int WEEKLY=101;
    static final int MONTHLY=102;

    final int WEEKLY_ID=501;
    final int MONTHLY_ID=502;

    final long weekInterval=7*24*60*60;
    final long monthInterval=30*24*60*60;

    Context context;
    private AlarmManager alarmManager;

    public AlarmManagerUtils(Context context){
        this.context=context;
    }

    public void setWeeklyAlarm(){
        setAlarmManager(WEEKLY);
    }

    public void setMonthlyAlarm(){
        setAlarmManager(MONTHLY);
    }

    private void setAlarmManager(int alarmType) {
        int hour,minute,id = 0;
        long interval = 0;
        hour=10;
        minute=10;
        Intent intent = new Intent(context, ReportService.class);

        if(alarmType==WEEKLY){
            id=WEEKLY_ID;
            interval=weekInterval;
            intent.setAction("weekly");
        }else if(alarmType==MONTHLY){
            id=MONTHLY_ID;
            interval=monthInterval;
            intent.setAction("monthly");
        }

        PendingIntent pendingIntent = PendingIntent.getService(context, id, intent, 0);
        long _alarm = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        _alarm=calendar.getTimeInMillis();

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, _alarm, interval , pendingIntent);

    }

    private void stopService() {
        context.stopService(new Intent(context, ReportService.class));
        cancelAlarms();
    }

    private void cancelAlarms() {
        Intent intentWeeklyReports = new Intent(context, ReportService.class);
        PendingIntent pendingIntentWeeklyReports = PendingIntent.getService(context, WEEKLY_ID, intentWeeklyReports, 0);

        Intent intentMonthlyReports = new Intent(context, ReportService.class);
        PendingIntent pendingIntentMonthlyReports = PendingIntent.getService(context, MONTHLY_ID, intentMonthlyReports, 0);

        alarmManager.cancel(pendingIntentWeeklyReports);
        alarmManager.cancel(pendingIntentMonthlyReports);
    }

}
