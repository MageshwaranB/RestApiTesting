package com.startingWithRestApi;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class ValidatableResponseRepeatingItemsDemo 
{
	public static final String BASE_URL="https://reqres.in/api/users?page=1";
	
	@Test
	public void repeatingItems() {
		RestAssured.get(BASE_URL)
		.then()
		//If we have more than one matches for the item we're searching for, we need to mention the index like arrays
		.body("data.id[0]",Matchers.equalTo(1))
		.body("data.id[2]", Matchers.equalTo(3))
		.body("data.first_name[3]", Matchers.containsString("Eve"))
		.body("data.first_name", Matchers.hasItem("Emma"))
		.body("data.last_name", Matchers.hasItem("Morris"))
		.body("data.first_name", Matchers.hasItem(Matchers.endsWith("cey")))
		;
	}

}
