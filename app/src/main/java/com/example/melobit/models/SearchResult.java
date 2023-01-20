package com.example.melobit.models;

public class SearchResult {
    private String type;
    private Artist artist;
    private Integer position;
    private Album album;
    private Song song;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }
    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }
    public Song getSong() {
        return song;
    }
    public void setSong(Song song) {
        this.song = song;
    }
}
