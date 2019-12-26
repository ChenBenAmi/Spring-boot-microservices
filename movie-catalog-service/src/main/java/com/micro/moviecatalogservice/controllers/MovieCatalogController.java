package com.micro.moviecatalogservice.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.micro.moviecatalogservice.pojos.CatalogItem;
import com.micro.moviecatalogservice.pojos.Movie;
import com.micro.moviecatalogservice.pojos.Rating;
import com.micro.moviecatalogservice.pojos.UserRating;
import com.micro.moviecatalogservice.services.MovieInfoService;
import com.micro.moviecatalogservice.services.MovieRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private MovieInfoService movieInfoService;
	
	@Autowired
	private MovieRatingService movieRatingService;





	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalogItems(@PathVariable("userId") String userId) {
//		UserRating userRating = webClient.build().get().uri(BASE_MOVIE_RATING_URL +"user/"+ userId).retrieve()
//				.bodyToMono(UserRating.class).block();
		System.out.println("Hey");
		UserRating userRating = movieRatingService.getUserRating(userId);
		System.out.println("Hey1");
		return userRating.getUserRating().stream().map(rating -> {
			return movieInfoService.getMovieInfo(rating);
		}).collect(Collectors.toList());
	}



	

	

}
