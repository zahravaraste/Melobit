package com.example.melobit.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Song {
    private String id;
    private Album album;
    private List<Artist> artists = new ArrayList<Artist>();
    private Audio audio;
    private Boolean copyrighted;
    private Boolean localized;
    private String downloadCount;
    private Integer duration;
    private Boolean hasVideo;
    private String title;
    private String lyrics;
    private Image image;
    private String releaseDate;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }
    public List<Artist> getArtists() {
        return artists;
    }
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    public Audio getAudio() {
        return audio;
    }
    public void setAudio(Audio audio) {
        this.audio = audio;
    }
    public Boolean getCopyrighted() {
        return copyrighted;
    }
    public void setCopyrighted(Boolean copyrighted) {
        this.copyrighted = copyrighted;
    }
    public Boolean getLocalized() {
        return localized;
    }
    public void setLocalized(Boolean localized) {
        this.localized = localized;
    }
    public String getDownloadCount() {
        return downloadCount;
    }
    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public Boolean getHasVideo() {
        return hasVideo;
    }
    public void setHasVideo(Boolean hasVideo) {
        this.hasVideo = hasVideo;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLyrics() {
        return lyrics;
    }
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
