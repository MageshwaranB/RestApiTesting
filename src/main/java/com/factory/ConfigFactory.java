package com.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.config.FailureConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;

public class ConfigFactory 
{
	public static RestAssuredConfig getDefaultConfig() {
		ResponseValidationFailureListener failureListener = (reqSpec, resSpec, response)
				-> System.out.printf("We have a failure, "+
		"response status was %s and the body contained : %s",
		response.getStatusCode(),response.body().asPrettyString());
		
		return RestAssured.config()
				.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL))
				.failureConfig(FailureConfig.failureConfig().failureListeners(failureListener))
				.redirect(RedirectConfig.redirectConfig().maxRedirects(1))
				.objectMapperConfig(ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory(getDefaultMapper()))
				;
		
	}

	private static Jackson2ObjectMapperFactory getDefaultMapper() {
		// TODO Auto-generated method stub
		return (type,s)->{
			ObjectMapper om=new ObjectMapper();
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return om;
		};
	}
}
