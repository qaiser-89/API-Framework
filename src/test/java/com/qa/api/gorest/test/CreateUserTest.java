package com.qa.api.gorest.test;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonTokenId;
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
	
	@Test
	public void createANewUser()
	{
		User user=new User(null, "Qaiser", StringUtil.getRandomEmail(), "male", "active");
		Response response = restClint.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.body().jsonPath().getString("name"), "Qaiser");
		Assert.assertNotNull(response.body().jsonPath().getString("id"));
	}
	
	@Test
	public void createANewUserWithJsonString()
	{
		String userjson = "{\n"
				+ "\"name\": \"Lai Guha\",\n"
				+ "\"email\": \"guha_lai87@bins.example\",\n"
				+ "\"gender\": \"female\",\n"
				+ "\"status\": \"active\"\n"
				+ "}"; 
		Response response = restClint.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userjson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.body().jsonPath().getString("name"), "Lai Guha");
		Assert.assertNotNull(response.body().jsonPath().getString("id"));
	}
	
	@Test
	public void createANewUserWithJsonFile()
	{
		File userfile=new File("./src/test/recources/jsons/user.json");
		Response response = restClint.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userfile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.body().jsonPath().getString("name"), "Qaiser");
		Assert.assertNotNull(response.body().jsonPath().getString("id"));
	}

}
