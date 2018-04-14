package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.Notes.Notes;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class getProductNotesRequest extends FetchData implements Callback<Notes>  {


    private int client_id,product_id;

    public getProductNotesRequest(int client_id, int product_id){
        this.client_id=client_id;
        this.product_id=product_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Notes>  clientCall = apiInterface.getProductNotes(client_id,product_id);
        clientCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Notes> call, Response<Notes> response) {
        Notes body =response.body();
        callbacks.OnSuccess( body);
    }

    @Override
    public void onFailure(Call<Notes> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
