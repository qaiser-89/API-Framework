package com.qa.api.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPIWithJsonPath extends BaseTest 
{
	
	@Test
	public void getProductTest()
	{
		Response response =	restClint.get(BASE_URL_CONTACTS, CONTACTS_ENDPOINT,AuthType.BEARER_TOKEN, ContentType.JSON, null, null);

		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	

}
