package com.example.mysmartlist.Services;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.example.mysmartlist.Activities.ReportsActivity;
import com.example.mysmartlist.Models.Reports.Reports;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getAllClientReportsRequest;

public class ReportService extends Service {

    getAllClientReportsRequest allClientReportsRequest;
    private Integer uid;

    public ReportService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String action="";

        if (intent!=null&& intent.getAction()!=null){
            action=intent.getAction();
        }

        MySharedPreferences.setUpMySharedPreferences(getApplicationContext());
        uid=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));

        if(action.equals("weekly")){
            getWeeklyReports();
        }else if(action.equals("monthly")){
            getMonthlyReports();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void getMonthlyReports() {

    }

    private void getWeeklyReports() {

        allClientReportsRequest=new getAllClientReportsRequest(uid);
        allClientReportsRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Reports reports=(Reports)obj;


                createNotification();
            }

            @Override
            public void OnFailure(Object obj) {

            }
        });

        allClientReportsRequest.start();
    }



    private PendingIntent createPendingIntent() {
        Intent intent = new Intent(this, ReportsActivity.class);
        return PendingIntent.getActivity(this, 678, intent, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification createNotification() {

        PendingIntent pendingIntent=createPendingIntent();

        Notification notification=new Notification.Builder(this)
                .setContentTitle("قائمتى الزكية")
                .setContentText("لديك تقارير جديده")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        return notification;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
