package com.qa.api.client;
import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.util.Map;
import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIException;
import com.qa.api.manager.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestClient {
	// response specifications
	private ResponseSpecification responseSpec200=	expect().statusCode(200);
	private ResponseSpecification responseSpec201=	expect().statusCode(201);
	private ResponseSpecification responseSpec204=	expect().statusCode(204);
	private ResponseSpecification responseSpec400=	expect().statusCode(400);
	private ResponseSpecification responseSpec401=	expect().statusCode(401);
	private ResponseSpecification responseSpec422=	expect().statusCode(422);
	private ResponseSpecification responseSpec200or400=	expect().statusCode(anyOf(equalTo(200), equalTo(400)));

	private ResponseSpecification responseSpec200or201=	expect().statusCode(anyOf(equalTo(200), equalTo(201)));
	private ResponseSpecification responseSpec200or404=	expect().statusCode(anyOf(equalTo(200), equalTo(404)));
	
	
	private RequestSpecification setupRequest(String baseurl, AuthType authType, ContentType contentType) {
		
	
	RequestSpecification request =  RestAssured
			.given().log().all()
				.baseUri(baseurl)
				.contentType(contentType)
				.accept(contentType);
	
	switch (authType){
	case BEARER_TOKEN:
		request.header("Authorization", "Bearer "+ ConfigManager.get("bearertoken"));
	break;
	case OAUTH2:
		request.header("Authorization", "Bearer "+ ConfigManager.get("oauth2 token"));
		break;
	case BASIC_AUTH:
		request.header("Authorization", "Basic "+ ConfigManager.get("bacis auth"));
		break;
	
	case API_KEY:
		request.header("x-api-key", "api key");
		break;	
	case NO_AUTH:
		System.out.println("No authorization needed.......");
		break;	
		
	default:
		throw new  APIException("=======Invalid AUTH please check with Dev!!!!");
	}
		return request;
	
	}
	
	private void applyParams(RequestSpecification request, Map<String, String> queryParams, Map<String, String> pathParams) 
	{
		if (queryParams != null) {
			request.params(queryParams);
		}
		if (pathParams != null) {
			request.pathParams(pathParams);
		}
	}
	
	
	// Time to do the CURD operations!!!!!
	//Get
	
	/**
	 * This method is used to call GET API's
	 * @param baseuri
	 * @param endPoint
	 * @param authType
	 * @param contentType
	 * @param queryParams
	 * @param pathParams
	 * @return This method returns GET API Response
	 */
	public Response get(String baseurl, String endPoint,
			AuthType authType, ContentType contentType,
			Map<String, String> queryParams, Map<String, String> pathParams)
	{
		RequestSpecification request = setupRequest(baseurl, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.get(endPoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;
		}
	
	
	/**
	 * This is a Post API Call/Method
	 * T is already used keyword in JAVA which means you can give me any "TYPE" if we are using T type
	 * we have to us <T> before return type in method that's why <t> response in method......
	 * @param <T>
	 * @param baseurl
	 * @param endpoint
	 * @param body
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public <T>Response post(String baseurl, String endpoint, T body,
			Map<String, String> queryParams, Map<String, String> pathParams,
			AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseurl, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.body(body).post(endpoint).then().extract().response();
		response.prettyPrint();
		return response;
	}
	
	/**
	 * we are overloading this method as T any tpye constructor does not take file type!!!!!
	 * @param baseurl
	 * @param endpoint
	 * @param file
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public Response post(String baseurl, String endpoint, File file,
			Map<String, String> queryParams, Map<String, String> pathParams,
			AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseurl, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.body(file).post(endpoint).then().spec(responseSpec200or400).extract().response();
		response.prettyPrint();
		return response;
	}
	/**
	 * 
	 * @param <T>
	 * @param baseurl
	 * @param endpoint
	 * @param body
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public <T>Response put(String baseurl, String endpoint, T body,
			Map<String, String> queryParams, Map<String, String> pathParams,
			AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseurl, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.body(body).put(endpoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
	}
	/**
	 * 
	 * @param <T>
	 * @param baseurl
	 * @param endpoint
	 * @param body
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public <T>Response patch(String baseurl, String endpoint, T body,
			Map<String, String> queryParams, Map<String, String> pathParams,
			AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseurl, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.body(body).patch(endpoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
	}
	/**
	 * 
	 * @param baseurl
	 * @param endpoint
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public Response delete(String baseurl, String endpoint, 
			Map<String, String> queryParams, Map<String, String> pathParams,
			AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseurl, authType, contentType);
		applyParams(request, queryParams, pathParams);
		Response response = request.delete(endpoint).then().spec(responseSpec204).extract().response();
		response.prettyPrint();
		return response;
	}
	
}
