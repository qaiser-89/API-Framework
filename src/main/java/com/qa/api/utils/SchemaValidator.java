package com.qa.api.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidator 
{
	
	public static boolean schemaValidation(Response response, String schemaFileName) 
	{
		try {
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFileName));
			System.out.println("Schema Valitation Passed "+schemaFileName);
			return true;
		} catch (Exception e) {
			System.out.println("Schema Validation is failed "+e.getMessage());
			return false;
		}
	}
	

}
