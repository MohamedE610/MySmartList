package com.example.mysmartlist.Utils.Networking;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.Product_1;
import com.example.mysmartlist.Models.Products.Products;
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

public class FetchProductSearchData extends FetchData implements Callback<Products>  {

    HashMap searchDetails;
    int client_id;
    public FetchProductSearchData(HashMap searchDetails, int client_id){
        this.searchDetails=searchDetails;
        this.client_id=client_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Products>  productSearchResultCall = apiInterface.getProductSearchResult(client_id,searchDetails);
        productSearchResultCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Products> call, Response<Products> response) {
        Products  jsonObject=response.body();
        callbacks.OnSuccess(jsonObject);
    }

    @Override
    public void onFailure(Call<Products> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
