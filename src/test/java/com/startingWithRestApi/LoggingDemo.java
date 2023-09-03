package com.startingWithRestApi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;

public class LoggingDemo 
{
	public static final String BASE_URL="https://jsonplaceholder.typicode.com/posts";
	
	@Test
	public void logTest() {
		RestAssured
			.given()
			//logging everything
				.log().all()
			//logging specifically
				.log().headers()
				.log().body()
			.when()
				.get(BASE_URL)
			.then()
			//no condition
				.log().headers()
				.log().status()
				//with condition
				.log().ifValidationFails(LogDetail.HEADERS)
				.statusCode(200);
	}
	
	@Test
	public void logTestWithConfigFactoryLog() {
		RestAssured
			.given()
			//logging everything
				.log().all()
			//logging specifically
				.log().headers()
				.log().body()
			.when()
				.get(BASE_URL)
			.then()
			
				.statusCode(200);
	}
}
