package com.startingWithRestApi;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class ValidatableNestedResponseBodyDemo 
{
	public static final String BASE_URL="https://api.github.com/";
	
	@Test
	public void nestedBodyValidation() {
		RestAssured.get(BASE_URL+"rate_limit")
		.then()
		/*
		 *Instead of writing duplication code for specifying path like resources.core.limit and resources.core.remaining, we can make use of rootpath method provided by rest assured 
		 */
		.rootPath("resources.core")
		.body("limit", 	Matchers.equalTo(60))
		.body("remaining", Matchers.lessThan(60))
		.body("reset", Matchers.notNullValue())
		//We can move to different path of the nested json by specifying the rootpath method again
		.rootPath("resources.search")
		.body("limit", Matchers.equalTo(10))
		.body("remaining", Matchers.lessThanOrEqualTo(10))
		//we can reset the rootpath using no rootpath method
		.noRootPath()
		.body("resources.graphql.resource", Matchers.containsString("graphql"));
		;
	}
}
