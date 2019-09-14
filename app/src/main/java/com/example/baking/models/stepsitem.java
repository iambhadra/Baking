package com.example.baking.models;

import java.io.Serializable;

public class stepsitem implements Serializable {

    int id;
    String shortDescription;
    String description;
    String videoURL;
    String thumbnailURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURl() {
        return videoURL;
    }

    public void setVideoURl(String videoURl) {
        this.videoURL = videoURl;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

}
