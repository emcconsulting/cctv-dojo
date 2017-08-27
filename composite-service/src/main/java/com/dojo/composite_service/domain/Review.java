package com.dojo.composite_service.domain;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Review extends ResourceSupport  {
	
	@JsonCreator
	public Review(@JsonProperty("userName") String userName, @JsonProperty("reviewComment") String reviewComment) {
		this.reviewComment = reviewComment;
		this.userName = userName;
	}
	
	public Review() {
		
	}

	private Long userId;

	private Long reviewId;

	private String userName;

	private String reviewComment;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}
}
