package com.example.yeschef.jsonObjects;

import java.time.LocalDateTime;

public class Comment {

    private String id;
    private String content;
    private LocalDateTime commentPostTime;
    private Integer rating;
    private String byUserName;

    public Comment(String id, String content, LocalDateTime commentPostTime, Integer rating, String byUserName) {
        super();
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.commentPostTime = commentPostTime;
        this.byUserName = byUserName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCommentPostTime() {
        return commentPostTime;
    }

    public void setCommentPostTime(LocalDateTime commentPostTime) {
        this.commentPostTime = commentPostTime;
    }

    public String getByUser() {
        return byUserName;
    }

    public void setByUser(String byUser) {
        this.byUserName = byUser;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}