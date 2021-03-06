package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.Products.ProductData;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiClient;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.ApiInterface;
import com.example.mysmartlist.Utils.Networking.RetrofitUtils.FetchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abdallah on 12/18/2017.
 */

public class FetchProductByIdData extends FetchData implements Callback<ProductData>  {

    private int product_id;

    public FetchProductByIdData(int product_id){
        this.product_id=product_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<ProductData> productByID = apiInterface.getProductByID(product_id);
        productByID.enqueue(this);
    }

    @Override
    public void onResponse(Call<ProductData> call, Response<ProductData> response) {
        ProductData productData=response.body();
        callbacks.OnSuccess(productData);
    }

    @Override
    public void onFailure(Call<ProductData> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
