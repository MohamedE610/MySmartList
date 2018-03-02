package com.example.mysmartlist.Utils.Networking;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.Client.Client;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class getClientByIDRequest extends FetchData implements Callback<Client>  {


    private int client_id;

    public getClientByIDRequest(int client_id){
        this.client_id=client_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Client>  clientCall = apiInterface.getClientByID(client_id);
        clientCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Client> call, Response<Client> response) {
        Client   client =response.body();
        callbacks.OnSuccess( client);
    }

    @Override
    public void onFailure(Call<Client> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
