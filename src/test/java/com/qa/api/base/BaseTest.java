package com.qa.api.base;

import java.io.ObjectInputFilter.Config;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;


//@Listeners(ChainTestListener.class)
public class BaseTest 
{
	protected  static String BASE_URL_GOREST;
	protected static String BASE_URL_REQRES;
	
	protected RestClient restClint;
	
	//************API BaseURL*************//
	
	
	protected final static String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";
	
	protected final static String BASE_URL_PRODUCTS = "https://fakestoreapi.com";
	
	
	protected final static String BASE_ERGAST_CIRCUT = "https://ergast.com";


	//************API ENDPOINTS*************//
	protected final static String GOREST_USERS_ENDPOINT = "/public/v2/users";
	
	protected final static String CONTACTS_LOGIN_ENDPOINT = "/users/login";
	protected final static String CONTACTS_ENDPOINT = "/contacts"; 
	
	protected final static String PRODUCTS_ENDPOINT = "/products";
	
	protected final static String ERGAST_CIRCUT_ENDPOINT = "api/f1/2017/circuits.xml";
	
	
	
	
	@BeforeSuite
	public void setAllureReport()
	{
		RestAssured.filters(new AllureRestAssured());
		BASE_URL_GOREST = ConfigManager.get("baseurl.gorest").trim();
		BASE_URL_REQRES = ConfigManager.get("baseurl.reqres").trim();
	}
	
	@BeforeTest
	public void setup()
	{
		restClint = new RestClient();
	}
	
	

}
