package com.startingWithRestApi;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecificationDemo {
	public static final String BASE_URL="https://api.github.com/";
	
	
	@BeforeClass
	public void setup() {
		RestAssured.responseSpecification=new ResponseSpecBuilder()
											.expectStatusCode(404)
											.expectContentType(ContentType.JSON)
											.build();
	}
	
	ResponseSpecification commonResponseSpec=(ResponseSpecification) new ResponseSpecBuilder()
																	.expectStatusCode(404)
																	.expectContentType(ContentType.JSON)
																	.build();
	
	@Test
	public void testWithSpecOne() {
		RestAssured.get(BASE_URL+"non/existing/url");
	}
	
	@Test
	public void testWithSpecTwo() {
		RestAssured.get(BASE_URL+"non/exisiting/url")
					.then()
						.spec(commonResponseSpec);
	}
	
	@AfterClass
	public void cleanup()
	{
		RestAssured.responseSpecification=null;
	}
	
}
