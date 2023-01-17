package com.example.melobit.manager;

import android.content.Context;

import com.example.melobit.ResponseListener;
import com.example.melobit.SongResponseListener;
import com.example.melobit.models.MusicData;
import com.example.melobit.models.MusicResponse;
import com.example.melobit.models.Song;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api-beta.melobit.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    CallLatestSongs callMusic = retrofit.create(CallLatestSongs.class);
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


    CallTopDaySongs callDayMusic = retrofit.create(CallTopDaySongs.class);
    public void getTopDayMusic(ResponseListener listener) {

        Call<MusicResponse> call2 = callDayMusic.callTopDayMusic();
        try {
            call2.enqueue(new Callback<MusicResponse>() {
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


    CallTopWeekSongs callWeekMusic = retrofit.create(CallTopWeekSongs.class);
    public void getTopWeekMusic(ResponseListener listener) {

        Call<MusicResponse> call2 = callWeekMusic.callTopWeekMusic();
        try {
            call2.enqueue(new Callback<MusicResponse>() {
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



    GetSongById getSongById = retrofit.create(GetSongById.class);
    public void getSong(SongResponseListener listener, String id) {

        Call<Song> call3 = getSongById.getSongById(id);
        try {
            call3.enqueue(new Callback<Song>() {
                @Override
                public void onResponse(Call<Song> call, Response<Song> response) {
                    if (!response.isSuccessful()){
                        listener.didError(response.message());
                        return;
                    }
                    listener.didFetch(response.body(),response.message());
                }

                @Override
                public void onFailure(Call<Song> call, Throwable t) {
                    listener.didError(t.getMessage());
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private interface CallLatestSongs {
        @GET("song/new/0/11")
        Call<MusicResponse> callMusic();
    }

    private interface CallTopDaySongs {
        @GET("song/top/day/0/100")
        Call<MusicResponse> callTopDayMusic();
    }

    private interface CallTopWeekSongs {
        @GET("song/top/week/0/100")
        Call<MusicResponse> callTopWeekMusic();
    }

    private interface GetSongById {
        @GET("song/{id}")
        Call<Song> getSongById(@Path("id") String songId);
    }
}
