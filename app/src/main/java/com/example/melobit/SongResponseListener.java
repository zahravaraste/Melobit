package com.example.melobit;

import com.example.melobit.models.MusicData;
import com.example.melobit.models.Song;

import java.util.List;

public interface SongResponseListener {
    void didFetch(Song response, String status);
    void didError(String status);
}
