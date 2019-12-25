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

import com.micro.moviecatalogservice.pojo.CatalogItem;
import com.micro.moviecatalogservice.pojo.Movie;
import com.micro.moviecatalogservice.pojo.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClient;

	private static final String BASE_MOVIE_URL = "http://movie-info-service/movies/";

	private static final String BASE_MOVIE_RATING_URL = "http://movie-rating-data-service/rating/";

	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalogItems(@PathVariable("userId") String userId) {

		UserRating ratings = webClient.build().get().uri(BASE_MOVIE_RATING_URL +"user/"+ userId).retrieve()
				.bodyToMono(UserRating.class).block();

		return ratings.getUserRating().stream().map(rating -> {

//			Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);

			Movie movie = webClient.build().get().uri(BASE_MOVIE_URL + rating.getMovieId()).retrieve()
					.bodyToMono(Movie.class).block();

			return new CatalogItem(movie.getName(), "test", rating.getRating());
		}).collect(Collectors.toList());

	}
}
