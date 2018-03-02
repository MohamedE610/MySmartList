package com.example.mysmartlist.Utils.Networking.RetrofitUtils;


import com.example.mysmartlist.Utils.Callbacks;

import retrofit2.Retrofit;

/**
 * Created by abdallah on 1/11/2018.
 */

public class FetchData  {

    protected Retrofit retrofit;
    protected ApiInterface apiInterface;
    protected Callbacks callbacks;

    public FetchData(){}

    public void setCallbacks(Callbacks callbacks){
        this.callbacks=callbacks;
    }

}
