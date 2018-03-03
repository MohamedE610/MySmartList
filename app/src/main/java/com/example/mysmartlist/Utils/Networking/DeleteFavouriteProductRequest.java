package com.example.mysmartlist.Utils.Networking;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class DeleteFavouriteProductRequest extends FetchData implements Callback<HashMap>  {

    private int client_id;
    private int product_id;

    public DeleteFavouriteProductRequest(int client_id , int product_id){
        this.client_id=client_id;
        this.product_id=product_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<HashMap>   deleteFavouriteProductCall = apiInterface.deleteFavouriteProduct(client_id,product_id);
        deleteFavouriteProductCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<HashMap> call, Response<HashMap> response) {
        HashMap   jsonObject =response.body();
        callbacks.OnSuccess(jsonObject);
    }

    @Override
    public void onFailure(Call<HashMap> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
