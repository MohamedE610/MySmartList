package com.example.mysmartlist.Utils.Networking;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.ClientLists.ClientLists;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class getOldClientListsRequest extends FetchData implements Callback<ClientLists>  {


    private int client_id;

    public getOldClientListsRequest(int client_id){
        this.client_id=client_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<ClientLists> clientListsCall = apiInterface.getOldClientLists(client_id);
        clientListsCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ClientLists> call, Response<ClientLists> response) {
        ClientLists  lists =response.body();
        callbacks.OnSuccess( lists);
    }

    @Override
    public void onFailure(Call<ClientLists> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
