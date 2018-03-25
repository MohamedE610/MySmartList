package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.ProductsByClientID.ProductsByClientID;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class FetchProductSearchData extends FetchData implements Callback<ArrayList<ProductsByClientID>>  {

    HashMap searchDetails;
    int client_id;
    public FetchProductSearchData(HashMap searchDetails, int client_id){
        this.searchDetails=searchDetails;
        this.client_id=client_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call< ArrayList<ProductsByClientID>>  productSearchResultCall = apiInterface.getProductSearchResult(client_id,searchDetails);
        productSearchResultCall.enqueue(this);
    }

    @Override
    public void onResponse(Call< ArrayList<ProductsByClientID>> call, Response< ArrayList<ProductsByClientID>> response) {
        ArrayList<ProductsByClientID>  jsonObject=response.body();
        callbacks.OnSuccess(jsonObject);
    }

    @Override
    public void onFailure(Call< ArrayList<ProductsByClientID>> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
