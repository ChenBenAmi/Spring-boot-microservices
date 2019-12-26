package com.micro.moviecatalogservice.controllers;

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
import com.micro.moviecatalogservice.pojos.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClient;

	private static final String BASE_MOVIE_URL = "http://movie-info-service/movies/";

	private static final String BASE_MOVIE_RATING_URL = "http://movie-rating-data-service/rating/user/";

	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalogItems(@PathVariable("userId") String userId) {

//		UserRating userRating = webClient.build().get().uri(BASE_MOVIE_RATING_URL +"user/"+ userId).retrieve()
//				.bodyToMono(UserRating.class).block();
		System.out.println("Hey");
		UserRating userRating=restTemplate.getForObject(BASE_MOVIE_RATING_URL+userId,UserRating.class);
		System.out.println("Hey1");
		
		return userRating.getUserRating().stream().map(rating -> {

//			Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);

			Movie movie = restTemplate.getForObject(BASE_MOVIE_URL+rating.getMovieId(), Movie.class);

			return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
		}).collect(Collectors.toList());

	}
}
