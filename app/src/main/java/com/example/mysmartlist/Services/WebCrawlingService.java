package com.example.mysmartlist.Services;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.PersistableBundle;

import com.example.mysmartlist.Activities.ReportsActivity;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.AlarmManagerUtils;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.CalculateClientMonthlyReportRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.CalculateClientWeeklyReportRequest;
import com.example.mysmartlist.Utils.WebCrawler.Banda.BandaCategoriesWebCrawling;
import com.example.mysmartlist.Utils.WebCrawler.Banda.BandaProductsWebCrawling;
import com.example.mysmartlist.Utils.WebCrawler.Danob.DanobCategoriesWebCrawling;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class WebCrawlingService extends JobService {

    public WebCrawlingService() {

    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        BandaCategoriesWebCrawling bandaCategoriesWebCrawling=new BandaCategoriesWebCrawling(getApplicationContext());
        bandaCategoriesWebCrawling.execute();

        DanobCategoriesWebCrawling danobCategoriesWebCrawling=new DanobCategoriesWebCrawling(getApplicationContext());
        danobCategoriesWebCrawling.execute();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        return false;
    }


}
