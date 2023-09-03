package com.startingWithRestApi;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class CustomRequestDemo 
{
	public static final String BASE_URL="https://api.github.com/";
	
	@Test
	public void customRequest() {
		//Below line is equivalent to RestAssured.get()
		RestAssured.request(Method.GET,BASE_URL) //Here we are issuing a GET Request using two alternative ways, one with enum and other as a string
		.then()
		.statusCode(200)
		.body(Matchers.containsString("current_user_url"))
		;
		
		RestAssured.request("GET",BASE_URL)
		.then()
		.statusCode(200)
		.body(Matchers.containsString("current_user_url"));
	}
	
	@Test
	public void traceExample() {
		RestAssured.request(Method.TRACE,BASE_URL)
		.then()
		.statusCode(405)//Method not allowed status code
		;
	}
}
