package com.example.mysmartlist.Utils.Networking;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.Product;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class FetchProductSearchData extends FetchData implements Callback<Product>  {

    public FetchProductSearchData(){
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Product> clientsCall = apiInterface.getProductSearch();
        clientsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Product> call, Response<Product> response) {
        Product clients=response.body();
        callbacks.OnSuccess(clients);
    }

    @Override
    public void onFailure(Call<Product> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
