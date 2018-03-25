package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.PopularProducts.PopularProduct;
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

public class getPopularProductsRequest extends FetchData implements Callback<ArrayList<PopularProduct>>  {


    public getPopularProductsRequest(){
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<ArrayList<PopularProduct>> popularProductsCall = apiInterface.getPopularProducts();
        popularProductsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<PopularProduct>> call, Response<ArrayList<PopularProduct>> response) {
        ArrayList<PopularProduct>   popularProducts =response.body();
        callbacks.OnSuccess( popularProducts);
    }

    @Override
    public void onFailure(Call<ArrayList<PopularProduct>> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
