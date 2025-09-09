package com.kh.example.opp4;

public class SquareController {
	private Shape s = new Shape();

	
	public double calcPerimeter(double height, double width) {
		s = new Shape(4, height, width); //TODO 타입 알 수 없음 매개변수 있는 생성자로 초기화
		return s.getWidth() * 2 + s.getHeight() * 2;
	}
	
	public double cacalcArea(double height, double width) {
		s = new Shape(0, height, width);
		return s.getWidth() * s.getHeight();
	}
	
	public void paintColor(String color) {
		s.setColor(color);
	}
	
	public String print() {
		return "사각형" + s.information();
	}
}
