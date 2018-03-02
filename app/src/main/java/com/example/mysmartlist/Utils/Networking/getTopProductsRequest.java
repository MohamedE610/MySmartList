package com.example.mysmartlist.Utils.Networking;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.TopProducts.TopProduct;
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

public class getTopProductsRequest extends FetchData implements Callback<ArrayList<TopProduct>>  {


    public getTopProductsRequest(){
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<ArrayList<TopProduct>>  topProductsCall = apiInterface.getTopProducts();
        topProductsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<TopProduct>> call, Response<ArrayList<TopProduct>> response) {
        ArrayList<TopProduct>  topProducts =response.body();
        callbacks.OnSuccess( topProducts);
    }

    @Override
    public void onFailure(Call<ArrayList<TopProduct>> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
