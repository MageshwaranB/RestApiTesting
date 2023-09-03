package com.startingWithRestApi;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class ValidatableResponseBodyDemo 
{
	public static final String BASE_URL="https://api.github.com/";
	
	@Test
	public void matcherExample() {
		RestAssured.get(BASE_URL)
		.then()
		.body("current_user_url", Matchers.equalTo(BASE_URL+"user"))
		.body(Matchers.containsString("feeds_url"))
		;
	}
	
	@Test
	public void complexMatcherExample() {
		RestAssured.get(BASE_URL+"users/MageshwaranB")
		.then()
		/*
		 *  Verifying the url always contains the login using ResponseAwareMatcher with the help of lambda expression
			and we're providing the value dynamically inside containsString method instead of directly hardcoding it inside
		 */
		.body("url", response->Matchers.containsString(response.body().jsonPath().get("login")));
	}
	
	
	@DataProvider
	public Object[][] names(){
		return new Object[][] {
			{"MageshwaranB"},
			{"mageshb"}
		};
	}
	
	@Test(dataProvider = "names")
	public void complexBodyExampleWithDp(String names) {
		RestAssured.get(BASE_URL+"users/"+names)
		.then()
		.body("url", response->Matchers.containsString(response.body().jsonPath().get("login")));
	}
}
