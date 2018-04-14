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
import com.example.mysmartlist.Models.Reports.Reports;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.AlarmManagerUtils;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.CalculateClientMonthlyReportRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.CalculateClientWeeklyReportRequest;
import com.example.mysmartlist.Utils.WebCrawler.WebCrawlingUtils;


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ReportService extends JobService {

    private Integer uid;

    public ReportService() {
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        try {
            PersistableBundle persistableBundle = jobParameters.getExtras();
            String action = persistableBundle.getString("action");


            MySharedPreferences.setUpMySharedPreferences(getApplicationContext());
            uid = Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
            String clientBudget = MySharedPreferences.getUserSetting("clientBudget");
            AlarmManagerUtils alarmManagerUtils = new AlarmManagerUtils(getApplicationContext());
            if (!clientBudget.equals(action)) {

                if (clientBudget.equals("weekly")) {
                    alarmManagerUtils.setWeeklyAlarm();
                } else if (clientBudget.equals("monthly")) {
                    alarmManagerUtils.setMonthlyAlarm();
                }

                stopSelf();
            }


            if (action.equals("weekly")) {
                calculateWeeklyReports();
                alarmManagerUtils.scheduleJobWeekly();
            } else if (action.equals("monthly")) {
                calculateMonthlyReports();
                alarmManagerUtils.scheduleJobMonthly();
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


    private void calculateMonthlyReports() {
        CalculateClientMonthlyReportRequest monthlyReportRequest = new CalculateClientMonthlyReportRequest(uid);
        monthlyReportRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Notification notification = createNotification();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(6578, notification);
            }

            @Override
            public void OnFailure(Object obj) {

            }
        });

        monthlyReportRequest.start();
    }

    private void calculateWeeklyReports() {
        CalculateClientWeeklyReportRequest weeklyReportRequest = new CalculateClientWeeklyReportRequest(uid);
        weeklyReportRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Notification notification = createNotification();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(6578, notification);
            }

            @Override
            public void OnFailure(Object obj) {

            }
        });
        weeklyReportRequest.start();
    }

    private void getMonthlyReports() {

    }


    private PendingIntent createPendingIntent() {
        Intent intent = new Intent(this, ReportsActivity.class);
        return PendingIntent.getActivity(this, 678, intent, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification createNotification() {

        PendingIntent pendingIntent = createPendingIntent();

        Notification notification = new Notification.Builder(this)
                .setContentTitle("قائمتى الزكية")
                .setContentText("لديك تقارير جديده")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();

        return notification;
    }

}
