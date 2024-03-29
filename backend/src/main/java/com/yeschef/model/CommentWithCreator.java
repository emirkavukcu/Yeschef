package com.yeschef.model;

import java.time.LocalDateTime;

public class CommentWithCreator {
	
	private String id;
	private String content;		
	private LocalDateTime commentPostTime;
	private String rating;
	private String creatorUserName;
	
	public CommentWithCreator(String id, String content, LocalDateTime commentPostTime, String rating,
			String creatorUserName) {
		super();
		this.id = id;
		this.content = content;
		this.commentPostTime = commentPostTime;
		this.rating = rating;
		this.creatorUserName = creatorUserName;
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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getCreatorUserName() {
		return creatorUserName;
	}

	public void setCreatorUserName(String creatorUserName) {
		this.creatorUserName = creatorUserName;
	}
	
}
