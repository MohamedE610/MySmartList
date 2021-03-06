package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.Products.Products;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class getNewProductRequest extends FetchData implements Callback<Products>  {


    public getNewProductRequest(){
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Products>  newProductsCall = apiInterface.getNewProducts();
        newProductsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Products> call, Response<Products> response) {
        Products   products =response.body();
        callbacks.OnSuccess(products);
    }

    @Override
    public void onFailure(Call<Products> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
