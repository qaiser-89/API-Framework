package com.qa.api.gorest.test;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest{
	
	@Test
	public void getAllUsersTest()
	{
		Response response = restClint.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT,AuthType.BEARER_TOKEN, ContentType.JSON,null, null);
		Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
	
	@Test
	public void getAllUsersWithQuaryParamTest()
	{
		HashMap<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("name", "naveen");
		queryParam.put("status","active");
		Response response = restClint.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, AuthType.BEARER_TOKEN, ContentType.JSON, queryParam, null);
		Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
	@Test
	public void getASingleUser()
	{
		String userID = "7908861";
		Response response = restClint.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userID,AuthType.BEARER_TOKEN, ContentType.JSON,null, null);
		Assert.assertTrue(response.statusLine().contains("OK"));
	}

}
