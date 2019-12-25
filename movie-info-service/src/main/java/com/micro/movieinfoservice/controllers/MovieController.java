package com.micro.movieinfoservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.movieinfoservice.pojos.Movie;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@GetMapping("/{movieId}")
	public Movie getMovie(@PathVariable("movieId") String movieId) {
		return new Movie(movieId,"test name");
		
	}

}
