package com.qa.api.gorest.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest {
@Test
	public void deleteAUser()
	{
		User user = User.builder()
				.name("Rahul")
				.email(StringUtil.getRandomEmail())
				.status("active")
				.gender("male")
				.build();
// 1 First we have to create user then get it and after that update and then (if needed) get again chaining process as already available use can be deleted any time.
		Response postResponse =	restClint.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT,user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(postResponse.jsonPath().getString("name"), "Rahul");
		
// fetch the User Id from Response body to use in following requests!!
		String userid =	postResponse.jsonPath().getString("id");
		System.out.println("<new Created user ID is ======="+userid+"======================>");
		
		
// 2 Now we have to run the Get Method.
		Response getResponse =	restClint.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userid, AuthType.BEARER_TOKEN, ContentType.JSON, null, null);
		Assert.assertTrue(getResponse.statusLine().contains("OK"));
		Assert.assertEquals(getResponse.jsonPath().getString("id"),userid);
		
// 3 Now we will update the created user.
	Response deleteResponse = restClint.delete(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	Assert.assertTrue(deleteResponse.statusLine().contains("No Content"));
// 4 Fetch the Deleted user now to chcek if its been deleted!!
	getResponse =	restClint.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userid, AuthType.BEARER_TOKEN, ContentType.JSON, null, null);
	Assert.assertTrue(getResponse.statusLine().contains("Not Found"));
	Assert.assertEquals(getResponse.statusCode(), 404);
	Assert.assertEquals(getResponse.jsonPath().getString("message"), "Resource not found");
	}
	
}
