package com.example.mysmartlist.Utils.Networking.RetrofitUtils;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

*/

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abdallah on 12/18/2017.
 */

public class ApiClient {

    public static final String BASE_URL = "http://gms-sms.com:89/gmsgroup/api/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
