package com.qa.api.base;

import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;

public class BaseTest 
{
	
	protected RestClient restClint;
	
	//************API BaseURL*************//
	protected final static String BASE_URL_GOREST = "https://gorest.co.in";
	
	protected final static String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";
	
	protected final static String BASE_URL_PRODUCTS = "https://fakestoreapi.com";
	
	
	protected final static String BASE_ERGAST_CIRCUT = "https://ergast.com";


	//************API ENDPOINTS*************//
	protected final static String GOREST_USERS_ENDPOINT = "/public/v2/users";
	
	protected final static String CONTACTS_LOGIN_ENDPOINT = "/users/login";
	protected final static String CONTACTS_ENDPOINT = "/contacts"; 
	
	protected final static String PRODUCTS_ENDPOINT = "/products";
	
	protected final static String ERGAST_CIRCUT_ENDPOINT = "api/f1/2017/circuits.xml";
	
	
	
	@BeforeTest
	public void setup()
	{
		restClint = new RestClient();
	}
	
	

}
