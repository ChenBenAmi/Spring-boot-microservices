package com.micro.moviecatalogservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogItem {
	
	private String nanme;
	private String desc;
	private int rating;

}
