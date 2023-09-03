package com.startingWithRestApi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class PeekAndPrintDemo {
	public static final String BASE_URL = "https://api.github.com/";

	@Test
	public void peek() {
		RestAssured.get(BASE_URL).peek();// peek prints both headers and body to the standard output
	}

	@Test
	public void prettyPeek() {
		RestAssured.get(BASE_URL).prettyPeek();// peek prints both headers and body to the standard output and it also
												// formats
												// the body nicely to make it more readable
	}

	@Test
	public void print() {
		RestAssured.get(BASE_URL).print();// It prints only the body of the response and it return it as a string
	}
	
	@Test
	public void prettyPrint() {
		RestAssured.get(BASE_URL).prettyPrint();// It prints only the body of the response and it return it as a string
	}
}
