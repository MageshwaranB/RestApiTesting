package com.startingWithRestApi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class BasicTest {
	@Test
	public void simpleBasicTest() {
		RestAssured.get("https://api.github.com/").then().statusCode(200);
		/*
		 * We issued an HTTP GET Request to a particular endpoint to a particular API
		 * and then we got the response back, we chained the method then() and asserted
		 * the status code of the response is 200
		 */
	}
}
