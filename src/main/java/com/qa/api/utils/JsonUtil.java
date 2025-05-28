package com.qa.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;



public class JsonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static <t> t jsonDeserialize(Response response, Class<t> targetClass)
	{
		try {
		return	objectMapper.readValue(response.getBody().asString(), targetClass);
		}
		catch (Exception e) {
			throw new RuntimeException("deserialization failed"+targetClass.getName());
		}
	}

}
