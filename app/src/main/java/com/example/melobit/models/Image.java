package com.example.melobit.models;

public class Image {
    private ThumbnailSmall thumbnailSmall;
    private Thumbnail thumbnail;
    private CoverSmall coverSmall;
    private Cover cover;
    public ThumbnailSmall getThumbnailSmall() {
        return thumbnailSmall;
    }
    public void setThumbnailSmall(ThumbnailSmall thumbnailSmall) {
        this.thumbnailSmall = thumbnailSmall;
    }
    public Thumbnail getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
    public CoverSmall getCoverSmall() {
        return coverSmall;
    }
    public void setCoverSmall(CoverSmall coverSmall) {
        this.coverSmall = coverSmall;
    }
    public Cover getCover() {
        return cover;
    }
    public void setCover(Cover cover) {
        this.cover = cover;
    }
}
