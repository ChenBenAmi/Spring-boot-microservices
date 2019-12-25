package com.micro.movieratingdataservice.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.movieratingdataservice.pojos.Rating;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}

}
