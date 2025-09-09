package com.kh.example.poly;

public class Dog extends Animal{
	private String breed;
	
	public Dog() {
	}

	public Dog(String name, int age, String breed) {
		super(name, age);
		this.breed = breed;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	@Override
	public void speak() {
		System.out.println("멍멍!");
	}

}
