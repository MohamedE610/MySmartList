package com.example.mysmartlist.Services;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import com.example.mysmartlist.Utils.WebCrawler.Panda.PandaCategoriesWebCrawling;
import com.example.mysmartlist.Utils.WebCrawler.Danob.DanobCategoriesWebCrawling;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class WebCrawlingService extends JobService {

    public WebCrawlingService() {

    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        PandaCategoriesWebCrawling pandaCategoriesWebCrawling =new PandaCategoriesWebCrawling(getApplicationContext());
        pandaCategoriesWebCrawling.execute();

        DanobCategoriesWebCrawling danobCategoriesWebCrawling=new DanobCategoriesWebCrawling(getApplicationContext());
        danobCategoriesWebCrawling.execute();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        return false;
    }


}
