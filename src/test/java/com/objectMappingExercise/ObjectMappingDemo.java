package com.objectMappingExercise;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Type;

import org.testng.annotations.Test;

import com.entities.User;

import io.restassured.RestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import junit.framework.Assert;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMappingDemo {
	public static final String BASE_URL = "https://api.github.com/users/rest-assured";

	@Test
	public void objectMappingTestOne() {
		User user = RestAssured.get(BASE_URL).as(User.class);// using as method we're unmarshalling the json response as
																// a class and saving it a variable
		/*
		 * This below validation will throw an exception because though we're calling
		 * this getLogin method from user class, Jackson found a field in json response
		 * payload which is not present in the class we're calling, we can either create
		 * every fields present in the user url or we need to add jsonignoreproperty at
		 * class level for the class we're invoking the fields from
		 */
		Assert.assertEquals(user.getLogin(), "rest-assured");
		assertEquals(user.getId(), 19369327);
		/*
		 * we'll get an error because we're created the variablename and methodname in
		 * camelCase however JSON uses underscore(_) for joining two words, solution for
		 * this issue is either create the variables and methodname in json recognizable
		 * format or we can add an annotation JSONProperty and give the name that
		 * matches the JSON and which is needs to be written on top of the variable
		 */
		assertEquals(user.getPublicRepos(), 2);
	}
	
	@Test
	public void objectMappingRelyingOnMapperType() {
		User user = RestAssured.get(BASE_URL)
		.as(User.class,ObjectMapperType.JACKSON_2);//By default it will be Jackson because that's the dependency we've added but we can specify a different object mapper 
		assertEquals(user.getLogin(), "rest-assured");
	}
	
	public Jackson2Mapper creatingOwnMapper() {
		return new Jackson2Mapper(new Jackson2ObjectMapperFactory() {
			@Override
			public ObjectMapper create(Type cls, String charset) {
				ObjectMapper om=new ObjectMapper();
				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return om;
			}
		});		
	}
	
	public Jackson2Mapper creatingOwnMapperUsingLambda() {
		return new Jackson2Mapper((type,s)->{
			ObjectMapper om=new ObjectMapper();
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return om;
		});
		
	}
	
	@Test
	public void objectMappingUsingSpecifiedMapper() {
		User user = RestAssured.get(BASE_URL)
		.as(User.class,creatingOwnMapper());
		assertEquals(user.getLogin(), "rest-assured");
	}
	
}
