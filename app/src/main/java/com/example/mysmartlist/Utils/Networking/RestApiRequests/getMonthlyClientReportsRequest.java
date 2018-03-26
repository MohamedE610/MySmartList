package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.Reports.Reports;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class getMonthlyClientReportsRequest extends FetchData implements Callback<Reports>  {


    private int client_id;

    public getMonthlyClientReportsRequest(int client_id){
        this.client_id=client_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Reports>  monthlyClientReports = apiInterface.getMonthlyClientReports(client_id);
        monthlyClientReports.enqueue(this);
    }

    @Override
    public void onResponse(Call<Reports> call, Response<Reports> response) {
        Reports  body =response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<Reports> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
