package com.qa.api.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsCredentials;
import com.qa.api.pojo.CreateContact;
import com.qa.api.utils.StringUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateANewContactTest  extends BaseTest
{
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
	public void createAContact()
	{
		CreateContact Contactbody = CreateContact.builder()
		.firstname("Joe").lastname("Brandon").email(StringUtil.getRandomEmail()).birthdate("1970-01-01")
		.phone("8005555555").street1("1 Main St.").street2("Apartment A").city("anytown").stateProvince("KS")
		.postalCode("12345").country("USA").build();
		System.out.println(Contactbody);
	Response postResponse =	restClint.post(BASE_URL_CONTACTS, CONTACTS_ENDPOINT, Contactbody, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	Assert.assertEquals(postResponse.statusCode(),201);
	}

}
