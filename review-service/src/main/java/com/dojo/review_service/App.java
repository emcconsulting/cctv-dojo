package com.dojo.review_service;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	final static String exchangeName = "dojo-exchange";
	final static String routing_key = "reviewkey";


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }
}
