package com.startingWithRestApi;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class BasicResponseBodyDemo {
	public static final String BASE_URL="https://api.github.com/rate_limit";
	
	@Test
	public void jsonPathReturnsMap() {
		Response response = RestAssured.get(BASE_URL);
		/*
		 * To get the nested values inside a body, see the below step
		 */
		ResponseBody responseBody = response.body();
		JsonPath jsonPath1 = responseBody.jsonPath();//Getting the entire json path for the given url from the response body
		
		//We can also use method chaining to get the jsonpath as well
		JsonPath jsonPath2 = response.body().jsonPath();
		Map<String,String> fullJsonPath = jsonPath2.get();//getting full json path and storing it in a map
		Map<String,String>  resourcesMap = jsonPath2.get("resources");//getting only the resources key and value pair
		Map<String,String> 	coreMap=jsonPath2.get("resources.core");///getting only core key and value
		int limitValue = jsonPath2.get("resources.core.limit");//getting only core's limit value
		int graphQLRemainingValue=jsonPath2.get("resources.graphql.remaining");//getting only the graphql's remaining value
		System.out.println(fullJsonPath);
		System.out.println(resourcesMap);
		System.out.println(coreMap);
		System.out.println(limitValue);
		System.out.println(graphQLRemainingValue);
		assertEquals(limitValue, 60);
		assertEquals(graphQLRemainingValue, 0);
	}
	
	@Test
	public void castingFailures() {
		Response response = RestAssured.get(BASE_URL);
		JsonPath jsonPath2 = response.body().jsonPath();
		Map<String,String> incorrectPath = jsonPath2.get("incorrectPath");//Throws null pointer exception because of invalid path
		int failureValue=jsonPath2.get("resources.core");//throws class cast exception. Because it will return a map but we're storing it in an integer
		String failureValue2=jsonPath2.get("resources.core.limit");// Similarily throws a class cast exception
	}
}
