package com.micro.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.micro.moviecatalogservice.pojos.Rating;
import com.micro.moviecatalogservice.pojos.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieRatingService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClient;

	private static final String BASE_MOVIE_RATING_URL = "http://movie-rating-data-service/rating/user/";

	@HystrixCommand(fallbackMethod = "getFallbackCatalogUserRating", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") })
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject(BASE_MOVIE_RATING_URL + userId, UserRating.class);
	}

	public UserRating getFallbackCatalogUserRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRating(Arrays.asList(new Rating("0", 0)));
		return userRating;
	}
}
