package com.qa.api.XMLPathValidator;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.XmlPathValidatorUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CircuitAPIWithXMLTest extends BaseTest{
	
	
	@Test
	public void getCircuitInfoTest()
	{
		Response response =
		restClint.get(BASE_ERGAST_CIRCUT, ERGAST_CIRCUT_ENDPOINT, AuthType.NO_AUTH, ContentType.ANY, null, null);
	
		List<String> circuitNames= XmlPathValidatorUtil.readList(response, "MRData.CircuitTable.Circuit.CircuitName");
		System.out.println(circuitNames);
		for(String e : circuitNames)
		{
			Assert.assertNotNull(e);
		}
		
		String ameriaLocation = XmlPathValidatorUtil.read(response, "**.find{ it.circuitId == 'america' }.Location.Locality");
		
		System.out.println("America Location is=========> "+ameriaLocation);
		Assert.assertEquals(ameriaLocation, "Astin");
	
	
	}
	

}
