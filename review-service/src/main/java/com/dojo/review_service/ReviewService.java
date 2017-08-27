package com.dojo.review_service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dojo.review_service.entity.ReviewEntity;
import com.dojo.review_service.repository.ReviewRepository;
import com.google.gson.Gson;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;
	
	@Autowired
    private  RabbitTemplate rabbitTemplate;
	
	@Value("${exchange.name}")
	private String exchangeName;
	
	@Value("${queue.name}")
	private String queueName;
	
	@Value("${routing.key}")
	private String routingKey;

	public ReviewEntity saveReview(ReviewEntity reviewEntity) {
		ReviewEntity res =  reviewRepo.save(reviewEntity);
		send(res);
		return res;
	}
	
	private void send(ReviewEntity reviewEntity) {
		 Gson gson = new Gson();
		 String json = gson.toJson(reviewEntity);
		 System.out.println("Json Representation >>" + json);	
		// rabbitTemplate.convertAndSend(exchangeName, routingKey, json);
		 rabbitTemplate.convertAndSend(queueName, json);
	}
}
