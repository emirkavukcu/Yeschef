package com.example.yeschef.jsonObjects;

import java.time.LocalDateTime;
import java.util.List;

public class Recipe {

    private String id;
    private String title;
    private String description;
    private LocalDateTime postTime;
    private String type;
    private float ratingOverall;
    private String creatorUserName;

    public Recipe(String id, String title, String description, String type, LocalDateTime postTime, float ratingOverall, String creatorUserName) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.postTime = postTime;
        this.ratingOverall = ratingOverall;
        this.creatorUserName = creatorUserName;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getRatingOverall() {
        return ratingOverall;
    }

    public void setRatingOverall(float ratingOverall) {
        this.ratingOverall = ratingOverall;
    }

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

}
