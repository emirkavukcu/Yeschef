package com.yeschef.model;

import java.time.LocalDateTime;
import java.util.List;

public class UserPayload {
	
    private String id;	
	private String username;	
	private LocalDateTime accountCreateTime;
	private List<Comment> postedComments;
	
	public UserPayload() {
	}
	

	public UserPayload(String id, String username, LocalDateTime accountCreateTime, List<Comment> postedComments) {
		super();
		this.id = id;
		this.username = username;
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


	public List<Comment> getPostedComments() {
		return postedComments;
	}


	public void setPostedComments(List<Comment> postedComments) {
		this.postedComments = postedComments;
	}
	
	

}
