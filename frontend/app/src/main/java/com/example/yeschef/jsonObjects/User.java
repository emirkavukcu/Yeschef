package com.example.yeschef.jsonObjects;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private String id;
    private String username;
    private String password;
    private LocalDateTime accountCreateTime;
    private List<Comment> postedComments;

    public User(String id, String username, String password, LocalDateTime accountCreateTime, List<Comment> postedComments) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountCreateTime = accountCreateTime;
        this.postedComments = postedComments;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getAccountCreateTime() {
        return accountCreateTime;
    }

    public void setAccountCreateTime(LocalDateTime accountCreateTime) {
        this.accountCreateTime = accountCreateTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Comment> getPostedComments() {
        return postedComments;
    }

    public void setPostedComments(List<Comment> postedComments) {
        this.postedComments = postedComments;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", accountCreateTime="
                + accountCreateTime + "]";
    }

}
