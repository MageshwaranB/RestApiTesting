package com.methodChaining;

public class SyntaticSugarEg 	
{
	public static void main(String[] args) {
		new Person().id(3)
		.name("Suresh")
		.andThen()//Syntactic Sugar
		.address("Titan Towers")
		.andThen()//Syntactic Sugar
		.getName();
		
		
		new Hobbies()
		.isPlaying("Console Games")
		.andThen()//Syntactic sugar
		.isSinging("Stand By Me")
		.andThen()//Syntactic sugar
		.getPlaying();
	}
}
