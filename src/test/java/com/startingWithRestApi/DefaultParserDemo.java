package com.startingWithRestApi;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

public class DefaultParserDemo {
	public static final String BASE_URL="https://api.github.com/";
	
	@Test
	public void parserAndSyntacticSugar() {
		/*
		 * By default, Rest Assured parser is set to JSON, since Rest Assured supports multiple different formats like XML, Text; we can change it by defaultParser method and specifying the format we want 
		 */
		RestAssured.get(BASE_URL)
		.then()
		.using()//syntactic sugar to give it more readability
		.defaultParser(Parser.XML)
		.body("current_user_url", Matchers.equalTo(BASE_URL+"user"))//Test is failing we set the parser as XML but we're receiving JSON validation
		;
	}
}
