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

public class AddProductRequest extends FetchData implements Callback<HashMap>  {

    HashMap jsonObject;

    public AddProductRequest(HashMap jsonObject){
        this.jsonObject=jsonObject;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<HashMap> addProductToDatabaseCall = apiInterface.addProductToDatabase(jsonObject);
        addProductToDatabaseCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<HashMap> call, Response<HashMap> response) {
        HashMap  jsonObject =response.body();
        callbacks.OnSuccess(jsonObject);
    }

    @Override
    public void onFailure(Call<HashMap> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
