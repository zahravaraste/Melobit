package com.example.melobit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SongResponse {

    private Integer total;
    private List<Song> results;
    public Integer getTotal(){
        return total;
    }
    public List<Song> getResults(){
        return results;
    }
}
