package com.methodChaining;

public class ExecutingMethodChainingEg {
	public static void main(String[] args) {
		Person person = new Person();
		person.id(1).name("Magesh").address("Seaside Apartment").setHobbies(new Hobbies()).isSinging("Halamathi Habibibo")
				.isPlaying("Cricket");
		System.out.println(person.getHobbies().getPlaying());
		String trekkedPlace=new Person().id(2).setHobbies(new Hobbies()).isTrekking("Himalayas").getTrekking();
		System.out.println(trekkedPlace);
	}
}
