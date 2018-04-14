package com.example.mysmartlist.Utils.Networking.RestApiRequests;/*
package com.example.abdallah.chatdemo.Utils.RetrofitLib;

import com.example.abdallah.chatdemo.Models.Clients.Clients;
import com.example.abdallah.chatdemo.Utils.Callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

*/

import com.example.mysmartlist.Models.Note.Note;
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

public class getProductNoteByIdRequest extends FetchData implements Callback<Note>  {


    private int note_id;

    public getProductNoteByIdRequest(int note_id){
        this.note_id=note_id;
    }

    public void start() {
        retrofit= ApiClient.getClient();
        apiInterface=retrofit.create(ApiInterface.class);
        Call<Note>  clientCall = apiInterface.getProductNoteById(note_id);
        clientCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Note> call, Response<Note> response) {
        Note body =response.body();
        callbacks.OnSuccess( body);
    }

    @Override
    public void onFailure(Call<Note> call, Throwable t) {
        callbacks.OnFailure(t.getMessage());
    }
}
