/*
package com.example.mysmartlist.Utils.Networking;*/
/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*//*


import com.example.mysmartlist.Models.Product_1;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

*/
/**
 * Created by abdallah on 12/18/2017.
 *//*


public class FetchCategoryByIdData extends FetchData implements Callback<Product_1>  {

    public FetchCategoryByIdData(){
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Product_1> clientsCall = apiInterface.getCategoryById();
        clientsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Product_1> call, Response<Product_1> response) {
        Product_1 clients=response.body();
        callbacks.OnSuccess(clients);
    }

    @Override
    public void onFailure(Call<Product_1> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
*/
