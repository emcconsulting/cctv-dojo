package com.dojo.composite_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dojo.composite_service.domain.Review;
import com.dojo.composite_service.domain.ReviewServiceResponse;
import com.dojo.composite_service.domain.UserEntityResponse;

@Service
public class CompositeService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CompositeUserService userService;

	protected String reviewServiceUrl = "http://REVIEW-SERVICE";
	protected String userServiceUrl = "http://USER-SERVICE";

	public Review getReviewById(String reviewId) {

		// Get from review service
		ReviewServiceResponse reviewResponse = restTemplate.getForObject(reviewServiceUrl + "/reviews/" + reviewId,
				ReviewServiceResponse.class, reviewId);

		// Get from user service
		UserEntityResponse userResponse = getUserDetail(reviewResponse);

		Review resp = new Review();
		resp.setReviewComment(reviewResponse.getReviewComment());
		resp.setReviewId(reviewResponse.getId());
		resp.setUserId(userResponse.getId());
		resp.setUserName(userResponse.getFirstName() + " " + userResponse.getLastName());
		return resp;
	}

	public UserEntityResponse getUserDetail(ReviewServiceResponse reviewResponse) {
		return userService.getUserDetail(reviewResponse);
	}

	public Review createReview(ReviewServiceResponse review) {
		ReviewServiceResponse reviewResponse = restTemplate.postForObject(reviewServiceUrl + "/reviews/", review,
				ReviewServiceResponse.class);
		Review resp = new Review();
		resp.setReviewComment(reviewResponse.getReviewComment());
		resp.setReviewId(reviewResponse.getId());
		resp.setUserId(review.getUserId());
		return resp;

	}

}
