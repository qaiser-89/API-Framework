package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest extends BaseTest{
	
	private String tokenId;
	
	@BeforeClass
	public void setUpToken()
	{
		tokenId = "ddcce3e9c2be795397f587100dfdf9d062bf4bc1300dfe144b9b9009f0af3407";
		ConfigManager.set("bearertoken", tokenId);
	}

	@Test
	public void updateAUser()
	{
		User user = User.builder()
				.name("Ravethy")
				.email(StringUtil.getRandomEmail())
				.status("active")
				.gender("female")
				.build();
// 1 First we have to create user then get it and after that update and then (if needed) get again chaining process as already available use can be deleted any time.
		Response postResponse =	restClint.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT,user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(postResponse.jsonPath().getString("name"), "Ravethy");
		
// fetch the User Id from Response body to use in following requests!!
		String userid =	postResponse.jsonPath().getString("id");
		System.out.println("new Created user ID is ======="+userid);
		
		
// 2 Now we have to run the Get Method.
		Response getResponse =	restClint.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userid, AuthType.BEARER_TOKEN, ContentType.JSON, null, null);
		Assert.assertTrue(getResponse.statusLine().contains("OK"));
		Assert.assertEquals(getResponse.jsonPath().getString("id"),userid);
		
// 3 Now we will update the created user.
	user.setName("Ravethy Sherma");
	user.setStatus("inactive");
	Response putResponse = restClint.put(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userid, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	Assert.assertTrue(putResponse.statusLine().contains("OK"));
	Assert.assertEquals(putResponse.jsonPath().getString("id"),userid);
	Assert.assertEquals(putResponse.jsonPath().getString("name"), "Ravethy Sherma");
	Assert.assertEquals(putResponse.jsonPath().getString("status"), "inactive");
// 4 Again GET call to check if everything is updated this step  is not mandatory but good to have and depends on company!!!
	
	getResponse =	restClint.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userid, AuthType.BEARER_TOKEN, ContentType.JSON, null, null);
	Assert.assertTrue(getResponse.statusLine().contains("OK"));
	Assert.assertEquals(getResponse.jsonPath().getString("id"),userid);
	Assert.assertEquals(getResponse.jsonPath().getString("name"), "Ravethy Sherma");
	Assert.assertEquals(getResponse.jsonPath().getString("status"), "inactive");
	
	}
}
