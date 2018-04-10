package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

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

public class getMarketProductsByClientIDRequest extends FetchData implements Callback<ArrayList<ProductsByClientID>>  {


    private int client_id,market_id;

    public getMarketProductsByClientIDRequest(int market_id,int client_id){
        this.client_id=client_id;
        this.market_id=market_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<ArrayList<ProductsByClientID>>  clientCall = apiInterface.getProductsMarket(market_id,client_id);
        clientCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<ProductsByClientID>> call, Response<ArrayList<ProductsByClientID>> response) {
        ArrayList<ProductsByClientID> body =response.body();
        callbacks.OnSuccess( body);
    }

    @Override
    public void onFailure(Call<ArrayList<ProductsByClientID>> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
