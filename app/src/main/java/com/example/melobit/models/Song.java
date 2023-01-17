package com.example.melobit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Song {

    private String id;
    private Album album;
    private List<Artist> artists;
    private Integer duration;
    private Boolean hasVideo;
    private Boolean copyrighted;
    private Image image;
    private String releaseDate;
    private Boolean localized;
    public String getId(){
        return id;
    }
    public Album getAlbum(){
        return album;
    }
    public List<Artist> getArtists(){
        return artists;
    }
    private Audio audio;
    private String title;
    private String downloadCount;
    public Integer getDuration(){
        return duration;
    }
    public Boolean isHasVideo(){
        return hasVideo;
    }
    public Boolean isCopyrighted(){
        return copyrighted;
    }
    public Image getImage(){
        return image;
    }
    public String getReleaseDate(){
        return releaseDate;
    }
    public Boolean isLocalized(){
        return localized;
    }
    public Audio getAudio(){
        return audio;
    }
    public String getTitle(){
        return title;
    }
    public String getDownloadCount(){
        return downloadCount;
    }
}
