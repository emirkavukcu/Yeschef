package com.yeschef.model;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Document
public class Recipe {
	
	@Id private String id;
	
	private String title;
	private String description;	
	private LocalDateTime postTime;
	private String type;

	private float ratingOverall;
	
	private List<Integer> ratingList;
	
	@JsonManagedReference
	private List<Comment> comments;
	
	@DBRef
	private User creator;
	
	public Recipe() {
		
	}
	
	public Recipe(String title, String description, String type, User creator) {
		super();
		this.title = title;
		this.description = description;
		this.type = type;
		this.postTime = LocalDateTime.now();
		this.ratingOverall = -1;
		this.creator = creator;
		
		List<Comment> comments = new ArrayList<>();
		this.comments = comments;
		
		List<Integer> ratings = new ArrayList<>();
		this.ratingList = ratings;	
	}
	
	public Recipe(String title, String description, String type, LocalDateTime postTime, float ratingOverall, User creator,
			List<Comment> comments) {
		super();
		this.title = title;
		this.description = description;
		this.type = type;
		this.postTime = postTime;
		this.ratingOverall = ratingOverall;
		this.creator = creator;
		this.comments = comments;
	}
	
	public Recipe(String id, String title, String description, String type, LocalDateTime postTime, float ratingOverall, User creator,
			List<Comment> comments, List<Integer> ratingList) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.postTime = postTime;
		this.ratingOverall = ratingOverall;
		this.creator = creator;
		this.comments = comments;
		this.ratingList = ratingList;
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
		addRating(Integer.valueOf(comment.getRating()));
	}
	
	public float calculateRating() {
		
		float newRating = 0;
		
		for(int i = 0; i < ratingList.size(); i++) {
			
			newRating = newRating + ratingList.get(i);
		}
		
		newRating = newRating/ratingList.size();
		newRating = (float) Math.round(newRating * 100) / 100; //Formatting up to 2 decimal places
		
		return newRating;
	}
	
	public void addRating(Integer rating) {
		ratingList.add(rating);
		ratingOverall = calculateRating();
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

	public List<Integer> getRatingList() {
		return ratingList;
	}

	public void setRatingList(List<Integer> ratingList) {
		this.ratingList = ratingList;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", description=" + description + ", postTime=" + postTime
				+ ", type=" + type + ", ratingOverall=" + ratingOverall + ", ratingList=" + ratingList + ", comments="
				+ comments + ", creator=" + creator + "]";
	}
	
}