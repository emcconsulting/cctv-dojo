package com.dojo.composite_service.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.composite_service.domain.Review;
import com.dojo.composite_service.domain.ReviewServiceResponse;
import com.dojo.composite_service.service.CompositeService;

@RestController
@RequestMapping("/composite")
public class CompositeController {

	@Autowired
	private CompositeService compositeService;

	

	@GetMapping(path = "reviews/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Review> getReviewById(@PathVariable String reviewId) {
		Review review =  compositeService.getReviewById(reviewId);
		review.add(linkTo(methodOn(CompositeController.class).getReviewById(reviewId)).withSelfRel());
		 return new ResponseEntity<Review>(review, HttpStatus.OK);

	}

	@PostMapping(path = "reviews", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Review createReviews(@RequestBody ReviewServiceResponse review) {
		return compositeService.createReview(review);
	}
}
