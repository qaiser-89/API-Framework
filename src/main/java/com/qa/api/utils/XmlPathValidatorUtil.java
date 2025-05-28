package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XmlPathValidatorUtil {

	private static XmlPath getXMLPath(Response response)
	{
		String responseBody = response.getBody().asString();
		return new XmlPath(responseBody);
	}
	
	public static <T> T read(Response response, String xmlPathExpression)
	{
		XmlPath xmlpath = getXMLPath(response);
		return xmlpath.get(xmlPathExpression);
	}

	public static <T> List<T> readList(Response response, String xmlPathExpression)
	{
		XmlPath xmlpath = getXMLPath(response);
		return xmlpath.getList(xmlPathExpression);
	}
	
	public static <T> List<Map<String, T>> readListOfMaps(Response response, String xmlPathExpression)
	{
		XmlPath xmlpath = getXMLPath(response);
		return xmlpath.getList(xmlPathExpression);
	}
	
}
