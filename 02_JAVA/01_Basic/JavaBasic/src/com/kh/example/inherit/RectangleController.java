package com.kh.example.inherit;

public class RectangleController {
	private Rectangle r = new Rectangle();
	
	public String calcArea(int x, int y, int height, int width) {
		r.setX(x); //초기화
		r.setY(y); //초기화
		r.setHeight(height); //초기화
		r.setWidth(width); //초기화
		int men = r.getHeight() * r.getWidth();
		return r.toString() + " / " + men;
	}
	
	public String calcPerimeter(int x, int y, int height, int width) {
		r.setX(x);
		r.setY(y);
		r.setHeight(height);
		r.setWidth(width);
		int dul = 2 * (r.getHeight() + r.getWidth());
		return r.toString() + " / " + dul;
	}
}
