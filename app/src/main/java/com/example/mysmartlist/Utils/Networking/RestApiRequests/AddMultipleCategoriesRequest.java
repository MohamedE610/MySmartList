package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.CategoriesRespone.CategoriesResponse;
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

public class AddMultipleCategoriesRequest extends FetchData implements Callback<CategoriesResponse>  {

    HashMap hashMap;

    public AddMultipleCategoriesRequest(HashMap hashMap){
        this.hashMap=hashMap;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<CategoriesResponse>  addMultipleCategories = apiInterface.AddMultipleCategories(hashMap);
        addMultipleCategories.enqueue(this);
    }

    @Override
    public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
        CategoriesResponse  body =response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<CategoriesResponse> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
