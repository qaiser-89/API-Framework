package com.qa.api.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsCredentials;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPIWithJsonPath extends BaseTest 
{
	private String token;
	//we have generate new token before every method to avoid token expiry in the middle of our testrun.
	
	@BeforeMethod
	public String getToken()
	{
		ContactsCredentials creditials = ContactsCredentials.builder()
				.email("hunnyqaiser@gmail.com")
				.password("Lahore!23")
				.build();
		
		Response tokenResponse =restClint.post(BASE_URL_CONTACTS, CONTACTS_LOGIN_ENDPOINT, creditials, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(tokenResponse.statusCode(), 200);
		String 	token =	tokenResponse.jsonPath().getString("token");
		System.out.println("Contacts Login Token is -------->"+token);
		ConfigManager.set("bearertoken", token);
//we have set/update the token value in properties file otherwise it will take the value already written in there and test will fail.
		return token;
	}
		
	
	@Test
	public void getProductTest()
	{
		Response response =	restClint.get(BASE_URL_CONTACTS, CONTACTS_ENDPOINT,AuthType.BEARER_TOKEN, ContentType.JSON, null, null);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	

}
