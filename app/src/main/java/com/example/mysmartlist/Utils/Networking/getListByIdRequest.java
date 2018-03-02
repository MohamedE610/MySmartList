package com.example.mysmartlist.Utils.Networking;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.List.List;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class getListByIdRequest extends FetchData implements Callback<List>  {

    private int list_id;

    public getListByIdRequest(int list_id){
        this.list_id=list_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<List>  listCall = apiInterface.getListById(list_id);
        listCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<List> call, Response<List> response) {
        List list =response.body();
        callbacks.OnSuccess(list);
    }

    @Override
    public void onFailure(Call<List> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
