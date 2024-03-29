	package com.yeschef.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Document
public class Comment {

	@Id private String id;
	
	private String content;		
	private LocalDateTime commentPostTime;
	private String rating;
	
	@DBRef
	@JsonBackReference
	private User byUser;
	
	@DBRef
	@JsonBackReference
	private Recipe hostRecipe;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	public Comment(String content, String rating, User byUser, Recipe hostRecipe) {
		super();
		this.content = content;
		this.rating = rating;
		this.commentPostTime = LocalDateTime.now();
		this.byUser = byUser;
		this.hostRecipe = hostRecipe;
	}
	
	public Comment(String content, String rating, LocalDateTime commentPostTime, User byUser, Recipe hostRecipe) {
		super();
		this.content = content;
		this.rating = rating;
		this.commentPostTime = commentPostTime;
		this.byUser = byUser;
		this.hostRecipe = hostRecipe;
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

	public User getByUser() {
		return byUser;
	}

	public void setByUser(User byUser) {
		this.byUser = byUser;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Recipe getHostRecipe() {
		return hostRecipe;
	}

	public void setHostRecipe(Recipe hostRecipe) {
		this.hostRecipe = hostRecipe;
	}
}