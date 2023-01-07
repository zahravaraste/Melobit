package com.example.melobit.manager;

import android.content.Context;

import com.example.melobit.ResponseListener;
import com.example.melobit.models.MusicResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api-beta.melobit.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    CallMusic callMusic = retrofit.create(CallMusic.class);
    public RequestManager (Context context) {
        this.context = context;
    }

    public void getMusic(ResponseListener listener) {
        Call<MusicResponse> call = callMusic.callMusic();
        try {
            call.enqueue(new Callback<MusicResponse>() {
                @Override
                public void onResponse(Call<MusicResponse> call, Response<MusicResponse> response) {
                    if (!response.isSuccessful()){
                        listener.didError(response.message());
                        return;
                    }
                    listener.didFetch(response.body().getResults(),response.message());
                }

                @Override
                public void onFailure(Call<MusicResponse> call, Throwable t) {
                    listener.didError(t.getMessage());
                }
            });
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }

    private interface CallMusic {
        @GET("song/new/0/11")
        Call<MusicResponse> callMusic();
    }
}
