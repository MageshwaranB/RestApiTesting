package com.startingWithRestApi;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.entities.User;
import com.factory.ConfigFactory;

import io.restassured.RestAssured;

public class ConfigDemoWithCleanCode 
{
	public static final String BASE_URL="https://api.github.com/";
	
	@BeforeSuite
	public void setup() {
		RestAssured.config=ConfigFactory.getDefaultConfig();
	}
	
	@Test
	public void cleanTestWithHiddenConfig() {
		User user = RestAssured.get(BASE_URL+"users/MageshwaranB")
		.as(User.class);
		assertEquals(user.login, "MageshwaranB");
		assertEquals(user.id, 88375878);
	}
}
