package com.example.mysmartlist.Utils.WebCrawler;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.mysmartlist.Services.WebCrawlingService;

import java.util.Calendar;

/**
 * Created by E610 on 4/9/2018.
 */

public class WebCrawlingUtils {

    @TargetApi(Build.VERSION_CODES.M)
    public static void scheduleJob(Context context) {

        ComponentName serviceComponent = new ComponentName(context, WebCrawlingService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(7*24*60*60*1000); // wait at least
        builder.setOverrideDeadline((7*24*60*60*1000)+(60*1000)); // maximum delay
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not

        PersistableBundle  bundle=new PersistableBundle();
        builder.setExtras(bundle);
        //JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        JobScheduler jobScheduler =(JobScheduler)context.getSystemService(context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());

    }
}
