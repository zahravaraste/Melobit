package com.example.melobit;

import com.example.melobit.models.ArtistResult;

import java.util.List;

public interface ArtistResponseListener <ArtistResponse>{
    void didFetch(List<ArtistResult> list, String status);
    void didError(String status);
}
