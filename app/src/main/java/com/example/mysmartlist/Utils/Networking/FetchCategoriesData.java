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
import com.example.mysmartlist.Models.Product_1;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class FetchCategoriesData extends FetchData implements Callback<Categories>  {

    public FetchCategoriesData(){
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Categories>  categoriesCall = apiInterface.getCategories();
        categoriesCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Categories> call, Response<Categories> response) {
        Categories  categories =response.body();
        callbacks.OnSuccess(categories);
    }

    @Override
    public void onFailure(Call<Categories> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
