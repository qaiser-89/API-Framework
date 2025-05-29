package com.qa.api.gorest.tests;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreateUserTest extends BaseTest {
	
	
	private String tokenId;
	
	@BeforeClass
	public void setUpToken()
	{
		tokenId = "ddcce3e9c2be795397f587100dfdf9d062bf4bc1300dfe144b9b9009f0af3407";
		ConfigManager.set("bearertoken", tokenId);
	}
	
//it's always better to create Dataprovider with 2d object array than using a excel file its faster
	@DataProvider
	public Object[][] setupData()
	{
		
		return new Object[][] {
			{"Priyanka", "female", "active"},
			{"Rajesh", "male", "active"},
			{"Priyanka", "female", "inactive"}
		};
	}
	
	@Test(dataProvider = "setupData")
	public void createANewUser(String Name, String Gender, String Status)
	{
		User user=new User(null, Name, StringUtil.getRandomEmail(), Gender, Status);
		Response response = restClint.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.body().jsonPath().getString("name"), Name);
		Assert.assertEquals(response.body().jsonPath().getString("gender"), Gender);
		Assert.assertEquals(response.body().jsonPath().getString("status"), Status);
		Assert.assertNotNull(response.body().jsonPath().getString("id"));
	}
	
	@Test(enabled = false) //as we are using hardcoded values in email its failing coz of email already exist...
	public void createANewUserWithJsonString()
	{
		String userjson = "{\n"
				+ "\"name\": \"Lai Guha\",\n"
				+ "\"email\": \"guha_lai873@bins.example\",\n"
				+ "\"gender\": \"female\",\n"
				+ "\"status\": \"active\"\n"
				+ "}"; 
		Response response = restClint.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userjson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.body().jsonPath().getString("name"), "Lai Guha");
		Assert.assertNotNull(response.body().jsonPath().getString("id"));
	}
	
	@Test(enabled = false) //hardcoded email in json file and test failing!!!!
	public void createANewUserWithJsonFile()
	{
		File userfile=new File("./src/test/recources/jsons/user.json");
		Response response = restClint.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userfile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.body().jsonPath().getString("name"), "Qaiser");
		Assert.assertNotNull(response.body().jsonPath().getString("id"));
	}

}
