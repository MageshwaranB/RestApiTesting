package com.methodChaining;

public class Person 
{
	private int id;
	private String name;
	private String address;
	
	private Hobbies hobbies;
	
	public int getId() {
		return id;
	}
	public Person id(int id) {
		this.id = id;
		return this;
	}
	public Hobbies getHobbies() {
		return hobbies;
	}
	public Hobbies setHobbies(Hobbies hobbies) {
		this.hobbies = hobbies;
		return hobbies;
	}
	public String getName() {
		return name;
	}
	public Person name(String name) {
		this.name = name;
		return this;
	}
	public String getAddress() {
		return address;
	}
	public Person address(String address) {
		this.address = address;
		return this;
	}
	
	public Person andThen() {
		return this;
	}
		
}
