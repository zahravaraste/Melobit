package com.example.melobit;

import com.example.melobit.models.MusicData;

import java.util.List;

public interface ResponseListener<MusicResponse>{
    void didFetch(List<MusicData> list, String status);
    void didError(String status);
}

