package com.dojo.composite_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dojo.composite_service.domain.ReviewServiceResponse;
import com.dojo.composite_service.domain.UserEntityResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CompositeUserService {

	@Autowired
	private RestTemplate restTemplate;

	protected String userServiceUrl = "http://USER-SERVICE";

	//@HystrixCommand(fallbackMethod = "userDetailFallback")
	public UserEntityResponse getUserDetail(ReviewServiceResponse reviewResponse) {
		UserEntityResponse userResponse = restTemplate
				.getForObject(userServiceUrl + "/user/" + reviewResponse.getUserId(), UserEntityResponse.class);

		/*
		 * UserEntityResponse userResponse = restTemplate
		 * .getForObject("http://localhost:9090" + "/user/" +
		 * reviewResponse.getUserId(), UserEntityResponse.class);
		 */
		return userResponse;
	}

	public UserEntityResponse userDetailFallback(ReviewServiceResponse reviewResponse,Throwable th) {
		th.printStackTrace();
		UserEntityResponse resp = new UserEntityResponse();
		resp.setFirstName("FIRST-NAME-NOT-AVAILABLE");
		resp.setLastName("LAST-NAME-NOT-AVAILABLE");
		return resp;
	}

}
