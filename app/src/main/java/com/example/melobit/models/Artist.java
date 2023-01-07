package com.example.melobit.models;

public class Artist {
    private String id;
    private Integer followersCount;
    private String fullName;
    private String type;
    private String sumSongsDownloadsCount;
    private Image image;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getFollowersCount() {
        return followersCount;
    }
    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSumSongsDownloadsCount() {
        return sumSongsDownloadsCount;
    }
    public void setSumSongsDownloadsCount(String sumSongsDownloadsCount) {
        this.sumSongsDownloadsCount = sumSongsDownloadsCount;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
}
