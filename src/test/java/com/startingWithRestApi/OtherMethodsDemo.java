package com.startingWithRestApi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class OtherMethodsDemo 
{
	public static final String BASE_URL="https://api.github.com/user/repos";
	public static final String ACCESS_TOKEN="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";
	
	@Test(description = "Create a repo")
	public void postTest() {
		RestAssured
			.given()
				.header("Authorization","token "+ACCESS_TOKEN)//Providing the access token so that we can do the post operation
				.body("{\"name\": \"deleteMe\"}")//request Body contains simple key and value 
			.when()
				.post(BASE_URL)//mentioning the url to which performing the post operation
			.then()
				.statusCode(201)//status code should be 201 which means created
				;
	}
	
	@Test(description = "Updating a repo")
	public void patchTest() {
		RestAssured
			.given()
				.header("Authorization","token "+ACCESS_TOKEN)//Providing the access token so that we can do the post operation
				.body("{\"name\": \"deleteMe-Patched\"}")//request Body contains simple key and value 
			.when()
				.patch("https://api.github.com/repos/MageshwaranB/deleteMe")//mentioning the url to which performing the patch operation
			.then()
				.statusCode(200)//status code should be 200 which means OK
				;
	}
	
	@Test(description = "Deleting a repo")
	public void deleteTest() {
		RestAssured
			.given()
				.header("Authorization","token "+ACCESS_TOKEN)//Providing the access token so that we can do the post operation
				//.body("{\"name\": \"deleteMe-Patched\"}")Since we're deleting we don't need the body
			.when()
				.delete("https://api.github.com/repos/MageshwaranB/deleteMe-Patched")//mentioning the url to which performing the delete operation. Note that content has been to be present for this delete operation to work successfully
			.then()
				.statusCode(204)//status code should be 204 which means no content
				;
	}
}
