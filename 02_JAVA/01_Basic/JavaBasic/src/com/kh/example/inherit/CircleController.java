package com.kh.example.inherit;

public class CircleController {
	private Circle c = new Circle();
	
	public String calcArea(int x, int y, int radius) {
		c.setX(x); // 초기화
		c.setY(y); // 초기화
		c.setRadius(radius); // 초기화
		double men = Math.PI * c.getRadius() * c.getRadius();
		return c.toString() + " / " + men;
 	}
	
	public String calcCircum(int x, int y, int radius) {
		c.setX(x);
		c.setY(y);
		c.setRadius(radius);
		double dul = Math.PI * c.getRadius() * 2;
		return c.toString() + " / " + dul;
	}
}
