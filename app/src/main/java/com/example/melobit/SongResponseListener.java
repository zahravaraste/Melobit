package com.example.melobit;

import com.example.melobit.models.Song;

public interface SongResponseListener {
    void didFetch(Song response, String status);
    void didError(String status);
}
