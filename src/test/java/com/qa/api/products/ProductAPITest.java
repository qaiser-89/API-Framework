package com.qa.api.products;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITest extends BaseTest {
	@Test
	public void getAllProducts()
	{
	Response response=restClint.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINT, AuthType.NO_AUTH, ContentType.ANY, null, null);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	Product[] product = JsonUtil.jsonDeserialize(response, Product[].class);
	
	for(Product p : product)
	{
		System.out.println("id: "+p.getId());
		System.out.println("title: "+p.getTitle());
		System.out.println("price: "+p.getPrice());
		System.out.println("discription: "+p.getDiscription());
		System.out.println("image: "+p.getImage());
		System.out.println("catagory: "+p.getCatagory());
		
		
		System.out.println("id: "+p.getRating().getRate());
		System.out.println("id: "+p.getRating().getCount());
		
	}
		
	}
	
	
	
	
	

}
