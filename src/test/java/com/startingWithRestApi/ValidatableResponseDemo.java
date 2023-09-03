package com.startingWithRestApi;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ValidatableResponseDemo 
{
	public static final String BASE_URL="https://api.github.com/";
	
	@Test
	public void validatingTheRestAssuredWay() {
		RestAssured.get(BASE_URL).then()//then() is a validatable response method which in turns many methods
		.assertThat() //syntactic code
		.statusCode(200)
		.and().assertThat() //syntactic code
		.contentType(ContentType.JSON)
		.header("x-ratelimit-limit", "60");
	}
	
	@Test
	public void basicValidatableExample() {
		RestAssured.get(BASE_URL)
					.then()
					.statusCode(HttpStatus.SC_OK);
	}
}
