package com.startingWithRestApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.text.DateFormatter;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.StringEndsWith;
import org.hamcrest.number.OrderingComparison;
import org.hamcrest.text.IsEmptyString;
import org.jsoup.select.Evaluator.IndexGreaterThan;
import org.testng.annotations.Test;
import org.hamcrest.Matcher.*;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;

public class MatchersDemo {
	public static final String BASE_URL="https://api.github.com/";
	Map<String,String> expectedHeaders=Map.of("content-encoding", "gzip", "X-RateLimit-Limit","60");
	
	
	@Test
	public void simpleMatchersDemo() {
		RestAssured.get(BASE_URL)
		.then()
		.statusCode(200)
		.statusCode(Matchers.lessThan(300))//matcher can be used as assertion
		.header("cache-control", Matchers.containsStringIgnoringCase("max-age=60"))
		.time(Matchers.lessThan(2L),TimeUnit.SECONDS)
		.header("etag", Matchers.notNullValue())
		.header("etag", Matchers.not(IsEmptyString.emptyString()));//matcher inside a matcher
	}
	
	@Test
	public void complexMatchersDemo() {
		RestAssured.get(BASE_URL)
		.then()
		
		/*
		 * We're converting the type of string to integer in the second parameter before providing it to the matchers using Function interface. Function interface here Map the header to another value type before exposing it to the Hamcrest matcher
		 */
		.header("X-RateLimit-Limit", Integer::parseInt, Matchers.equalTo(60))
		.header("Date", date->LocalDate.parse(date,DateTimeFormatter.RFC_1123_DATE_TIME), OrderingComparison.comparesEqualTo(LocalDate.now()))//Take the string from header and parse it and use the formatter mentioned in the second parameter;
		.header("X-RateLimit-Limit", response -> Matchers.greaterThan(response.header("X-RateLimit-Remaining")))// grab the first header and given the same response which we've received , compare the value with the response of other header which is located in a header in the response
		;
	}
	
	@Test
	public void usingMapToTestHeaders() {
		RestAssured.get(BASE_URL)
		.then()
		.headers("content-encoding", "gzip", "X-RateLimit-Limit","60") //Since map and headers contains of key and value pair, we can utilized the concept of map to validate the response
		.headers(expectedHeaders); 

	}
}
