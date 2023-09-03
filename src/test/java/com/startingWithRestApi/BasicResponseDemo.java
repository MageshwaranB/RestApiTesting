package com.startingWithRestApi;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class BasicResponseDemo 
{	
	public static final String BASE_URL = "https://api.github.com/";
	
	@Test
	public void convenienceMethods() {
		Response response = RestAssured.get(BASE_URL);
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
	}
	
	@Test
	public void genericHeader() {
		Response response = RestAssured.get(BASE_URL);
		Assert.assertEquals(response.getHeader("server"),"GitHub.com");
		Assert.assertEquals(response.getHeader("x-ratelimit-limit"), "60");//This is a custom header indicating how many free calls one can make within a certain timeframe without any authentication
	}
	
	@Test
	public void getHeaders() {
		Response response = RestAssured.get(BASE_URL);
		Headers headers = response.getHeaders();
		String val = headers.getValue("header1");
		System.out.println(val);
		int size=headers.size();
		System.out.println(size);
		List<Header> headerLists = headers.asList();
		for(Header currentHeader:headerLists) {
			System.out.println(currentHeader);
		}
		boolean isHeaderPresent = headers.hasHeaderWithName("header2");
		assertTrue(isHeaderPresent);
	}
	
	@Test
	public void timeExample() {
		Response response = RestAssured.get(BASE_URL);
		System.out.println(response.getTime());
		System.out.println(response.timeIn(TimeUnit.MINUTES));
	}
}
