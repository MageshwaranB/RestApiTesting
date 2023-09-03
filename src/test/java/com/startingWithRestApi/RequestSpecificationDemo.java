package com.startingWithRestApi;

import org.testng.annotations.Test;

import com.factory.ConfigFactory;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationDemo 
{
	public static final String BASE_URL="https://api.github.com/";
	
	@Test
	public void testUsingReqSpec() {
		RestAssured
		.given()
			.spec(getDefaultRequestSpec())
		.when()
			.get()
		.then()
			.statusCode(200)
		;
	}
	
	public static RequestSpecification getDefaultRequestSpec() {
		return new RequestSpecBuilder()
				.setBaseUri(BASE_URL)
				.setConfig(ConfigFactory.getDefaultConfig())
				.build();
	}
}
