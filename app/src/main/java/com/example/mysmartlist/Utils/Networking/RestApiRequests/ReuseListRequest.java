package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.Client.Client;
import com.example.mysmartlist.Models.List.List;
import com.example.mysmartlist.Models.Reports.Reports;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class ReuseListRequest extends FetchData implements Callback<List>  {


    private int list_id;
    private int client_id;

    public ReuseListRequest(int client_id,int list_id){
        this.client_id=client_id;
        this.list_id=list_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<List>   listCall = apiInterface.reuseList(client_id,list_id);
        listCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<List> call, Response<List> response) {
        List  body =response.body();
        callbacks.OnSuccess( body);
    }

    @Override
    public void onFailure(Call<List> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
