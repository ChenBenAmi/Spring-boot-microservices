package com.micro.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.micro.moviecatalogservice.pojos.CatalogItem;
import com.micro.moviecatalogservice.pojos.Movie;
import com.micro.moviecatalogservice.pojos.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfoService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClient;
	
	private static final String BASE_MOVIE_URL = "http://movie-info-service/movies/";
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") }, threadPoolKey = "movieInfoPool", threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "20"),
					@HystrixProperty(name = "maxQueueSize", value = "10") })
	public CatalogItem getMovieInfo(Rating rating) {
		 Movie movie=restTemplate.getForObject(BASE_MOVIE_URL + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found","",rating.getRating());

	}
}
