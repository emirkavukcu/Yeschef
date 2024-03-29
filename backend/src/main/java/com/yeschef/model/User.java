package com.yeschef.model;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	
	@Id private String id;
	
	private String username;	
	private String password;
	private LocalDateTime accountCreateTime;
	
	private List<Comment> postedComments;
	
	public User() {
		
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.accountCreateTime = LocalDateTime.now();
		
		List<Comment> comments = new ArrayList<>();
		this.postedComments = comments;
	}

	public User(String username, String password, LocalDateTime accountCreateTime) {
		super();
		this.username = username;
		this.password = password;
		this.accountCreateTime = accountCreateTime;
	}

	public User(String id, String username, String password, LocalDateTime accountCreateTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.accountCreateTime = accountCreateTime;
	}
	
	public void addComment(Comment comment) {
		postedComments.add(comment);
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