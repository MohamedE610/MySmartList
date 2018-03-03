package com.example.mysmartlist.Utils.Networking;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.Categories.Categories;
import com.example.mysmartlist.Models.Client.Client;
import com.example.mysmartlist.Models.ClientLists.ClientLists;
import com.example.mysmartlist.Models.List.List;
import com.example.mysmartlist.Models.PopularProducts.PopularProduct;
import com.example.mysmartlist.Models.Products.Products;
import com.example.mysmartlist.Models.TopProducts.TopProduct;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class deleteListPermanentlyRequest extends FetchData implements Callback<HashMap>  {

    private int list_id;

    public deleteListPermanentlyRequest(int list_id , int product_id){
        this.list_id=list_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<HashMap> deleteListPermanentlyCall = apiInterface.deleteListPermanently(list_id);
        deleteListPermanentlyCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<HashMap> call, Response<HashMap> response) {
        HashMap  body =response.body();
        callbacks.OnSuccess(body);
    }

    @Override
    public void onFailure(Call<HashMap> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
