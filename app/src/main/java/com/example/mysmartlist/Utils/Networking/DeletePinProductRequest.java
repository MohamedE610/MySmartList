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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class DeletePinProductRequest extends FetchData implements Callback<JSONObject>  {

    private int client_id;
    private int product_id;

    public DeletePinProductRequest(int client_id , int product_id){
        this.client_id=client_id;
        this.product_id=product_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<JSONObject>   deletePinProductCall = apiInterface.deletePinProduct(client_id,product_id);
        deletePinProductCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
        JSONObject   jsonObject =response.body();
        callbacks.OnSuccess(jsonObject);
    }

    @Override
    public void onFailure(Call<JSONObject> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
