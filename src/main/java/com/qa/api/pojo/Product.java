package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	private Integer id;
	private String title;
	private Double price;
	private String discription;
	private String catagory;
	private String image;
	private Rating rating;
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Rating{
		private Double rate;
		private Integer count;
		
		
	}
	

}
