package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.Categories.Categories;
import com.example.mysmartlist.Models.ProductsByClientID.ProductsByClientID;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class getMarketCategoriesRequest extends FetchData implements Callback<Categories>  {


    private int market_id;

    public getMarketCategoriesRequest(int market_id){
        this.market_id=market_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Categories>  call = apiInterface.getCategoriesMarket(market_id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Categories> call, Response<Categories> response) {
        Categories body =response.body();
        callbacks.OnSuccess( body);
    }

    @Override
    public void onFailure(Call<Categories> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
