package com.example.mysmartlist.Utils;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;

import com.example.mysmartlist.Services.ReportService;
import com.example.mysmartlist.Services.WebCrawlingService;

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

        if(alarmType==WEEKLY){
           scheduleJobWeekly();
        }else if(alarmType==MONTHLY){
           scheduleJobMonthly();
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void scheduleJobWeekly() {
        ComponentName serviceComponent = new ComponentName(context, ReportService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(7*24*60*60*1000); // wait at least
        builder.setOverrideDeadline((7*24*60*60*1000)+(60*1000)); // maximum delay
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not
        PersistableBundle bundle=new PersistableBundle();
        bundle.putString("action","weekly");
        builder.setExtras(bundle);

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());

    }


    @TargetApi(Build.VERSION_CODES.M)
    public void scheduleJobMonthly() {
        ComponentName serviceComponent = new ComponentName(context, ReportService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(30*24*60*60*1000); // wait at least
        builder.setOverrideDeadline((30*24*60*60*1000)+(60*1000)); // maximum delay
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not

        PersistableBundle bundle=new PersistableBundle();
        bundle.putString("action","monthly");
        builder.setExtras(bundle);
        JobScheduler jobScheduler = (JobScheduler)  context.getSystemService(context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());

    }

}
