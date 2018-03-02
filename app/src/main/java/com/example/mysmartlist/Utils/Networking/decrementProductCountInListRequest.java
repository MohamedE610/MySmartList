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

public class decrementProductCountInListRequest extends FetchData implements Callback<JSONObject>  {

    private int list_id;
    private int product_id;
    JSONObject  jsonObject;

    public decrementProductCountInListRequest(int list_id, int product_id , JSONObject jsonObject){
        this.list_id=list_id;
        this.jsonObject=jsonObject;
        this.product_id=product_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<JSONObject>  decrementProductCountInListCall = apiInterface
                .decrementProductCountInList(list_id+"",product_id+"",jsonObject);
        decrementProductCountInListCall.enqueue(this);
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
