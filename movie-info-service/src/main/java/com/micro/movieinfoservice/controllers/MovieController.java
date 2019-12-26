package com.micro.movieinfoservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.micro.movieinfoservice.pojos.Movie;
import com.micro.movieinfoservice.pojos.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClient;

	private static final String BASE_MOVIE_URL="https://api.themoviedb.org/3/movie/";
	
//	@GetMapping("/{movieId}")
//	public Movie getMovie(@PathVariable("movieId") String movieId) {
//		return new Movie(movieId, "test name","test desc");
//
//	}

	@GetMapping("/{movieId}")
	public Movie getMovie(@PathVariable("movieId") String movieId) {
		System.out.println(movieId);
		MovieSummary movieSummary = restTemplate.getForObject(BASE_MOVIE_URL+movieId+"?api_key="+apiKey, MovieSummary.class);
		return new Movie(movieId, movieSummary.getTitle(),movieSummary.getOverview());

	}

}
