package com.micro.movieratingdataservice.pojos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {
	
	private List<Rating> userRating;

}
