package com.dojo.review_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.review_service.entity.ReviewEntity;

//@RestController
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = "/reviews", method = RequestMethod.POST)
    public HttpEntity<ReviewEntity> create(@RequestBody ReviewEntity reviewEntity) {
		ReviewEntity resp = reviewService.saveReview(reviewEntity);
		return new ResponseEntity<ReviewEntity>(resp, HttpStatus.OK);
	}
}
