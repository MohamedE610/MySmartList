package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
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

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class createClientAccountRequest extends FetchData implements Callback<Client>  {

    HashMap clientDetails;

    public createClientAccountRequest(HashMap clientDetails){
        this.clientDetails=clientDetails;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Client>  clientAccountCall = apiInterface.createClientAccount(clientDetails);
        clientAccountCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Client> call, Response<Client> response) {
        if(response.isSuccessful()){

        }else {
            String s=response.errorBody().toString();
        }
        Client  client =response.body();
        callbacks.OnSuccess( client);
    }

    @Override
    public void onFailure(Call<Client> call, Throwable t) {

        callbacks.OnFailure(t.getMessage());
    }
}
