package com.startingWithRestApi;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.FailureConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.listener.ResponseValidationFailureListener;

public class ConfigDemo {
	public static final String BASE_URL = "https://api.github.com/";

	@Test
	public void maxRedirectsTest() {
		RestAssured.config.redirect(RedirectConfig.redirectConfig().followRedirects(false));// We're forcing not to
																							// redirect to a different
																							// links and underlying
																							// httpclient will throw a
																							// 304 message

		RestAssured.get(BASE_URL + "repos/twitter/bootstrap").then().statusCode(Matchers.equalTo(200));// Though the
																										// site has been
																										// moved to
																										// different
																										// location, it
																										// constantly
																										// redirects to
																										// a link where
																										// it can the
																										// desired
																										// endpoint and
																										// returns 200
	}

	@Test
	public void failureConfig() {
		ResponseValidationFailureListener failureListener = (reqSpec, resSpec, response)
				-> System.out.printf("We have a failure, "+
		"response status was %s and the body contained : %s",
		response.getStatusCode(),response.body().asPrettyString());
		/*
		 * Failure config is a listener 
		 */
				
		RestAssured.config=RestAssuredConfig.config()
				.failureConfig(FailureConfig.failureConfig().failureListeners(failureListener));
		RestAssured.get(BASE_URL + "users/MageshwaranB").then().body("some.path", Matchers.equalTo("a thing"));//Though the test fails it returns us the status code and the response body
	}

}
