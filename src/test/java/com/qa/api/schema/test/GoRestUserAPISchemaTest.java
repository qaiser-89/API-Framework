package com.qa.api.schema.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestUserAPISchemaTest extends BaseTest{
	
	@Test
	public void getUserAPISchemaTest()
	{
		ConfigManager.set("bearertoken", "ddcce3e9c2be795397f587100dfdf9d062bf4bc1300dfe144b9b9009f0af3407");
		
		Response response =
		restClint.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, AuthType.BEARER_TOKEN, ContentType.ANY, null, null);
		Assert.assertTrue(SchemaValidator.schemaValidation(response, "schemas/getUsersSchems.json"));
	}
	
	@Test
	public void createAUserSchemaTest()
	{
		ConfigManager.set("bearertoken", "ddcce3e9c2be795397f587100dfdf9d062bf4bc1300dfe144b9b9009f0af3407");

		User user =	User.builder().name("Rajesh")
		.email(StringUtil.getRandomEmail()).gender("male")
		.status("active").build();
		
		Response response = 
				restClint.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(SchemaValidator.schemaValidation(response, "schemas/createuserschema.json"));

	}

}
