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

public class MoveListFromCurrentListToOldListRequest extends FetchData implements Callback<JSONObject>  {

    private int list_id;

    public MoveListFromCurrentListToOldListRequest(int list_id , int product_id){
        this.list_id=list_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<JSONObject>    moveListFromCurrentListToOldList = apiInterface.MoveListFromCurrentListToOldList(list_id);
        moveListFromCurrentListToOldList.enqueue(this);
    }

    @Override
    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
        JSONObject  body =response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<JSONObject> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
