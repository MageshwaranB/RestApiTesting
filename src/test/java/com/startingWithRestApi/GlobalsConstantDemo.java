package com.startingWithRestApi;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsAnything;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GlobalsConstantDemo {
	@BeforeSuite
	public void setup() {
		RestAssured.baseURI="https://reqres.in/";
		RestAssured.basePath="api/users";
		RestAssured.rootPath="data";
	}
	
	@Test
	public void testOneUsingGlobalConstants() {
		RestAssured.get()
		.then()
		.body("id[0]",Matchers.is(1));
	}
	
	@Test
	public void testTwoUsingGlobalConstants() {
		RestAssured.get()
		.then()
		.body("first_name[0]",Matchers.is(Matchers.startsWith("Geo")));
	}
	
	
}
