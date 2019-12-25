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

import com.micro.moviecatalogservice.pojo.CatalogItem;
import com.micro.moviecatalogservice.pojo.Movie;
import com.micro.moviecatalogservice.pojo.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalogItems(@PathVariable("userId") String userId) {

		List<Rating> ratings = Arrays.asList(new Rating("12", 4), new Rating("13", 3));

		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "test", rating.getRating());
		}).collect(Collectors.toList());

	}
}
