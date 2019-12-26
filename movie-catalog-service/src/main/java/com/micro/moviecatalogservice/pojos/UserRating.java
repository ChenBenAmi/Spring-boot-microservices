package com.micro.moviecatalogservice.pojos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {
	
	private String userId;
	private List<Rating> userRating;

}
