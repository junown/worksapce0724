package com.kh.example.inherit;

public class Circle extends Point{ // x, y좌표가 없어서 Point 상속
	private int radius;
	
	public Circle() {
	}

	
	
	public Circle(int x, int y, int radius) {
		super(x, y);
		this.radius = radius;
	}



	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public String toString() {
		return super.toString() + ", " +  radius;
	}

	
	
	
	
}
