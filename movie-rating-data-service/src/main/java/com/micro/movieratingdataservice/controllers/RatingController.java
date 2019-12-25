package com.micro.movieratingdataservice.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.movieratingdataservice.pojos.Rating;
import com.micro.movieratingdataservice.pojos.UserRating;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	@RequestMapping("/user/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		List<Rating> userRating= Arrays.asList(new Rating("12", 4), new Rating("13", 3));
		UserRating userRatingObject=new UserRating();
		userRatingObject.setUserRating(userRating);
		return userRatingObject;
	}

}
