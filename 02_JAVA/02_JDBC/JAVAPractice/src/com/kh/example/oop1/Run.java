package com.kh.example.oop1;

public class Run {

	public static void main(String[] args) {
		Product p1 = new Product();
		p1.setBrand("삼성");
		p1.setpName("갤럭시");
		p1.setPrice(10000000);
		
		Product p2 = new Product();
		p2.setBrand("lg");
		p2.setpName("그램");
		p2.setPrice(19000000);
		
		p1.information();
		p2.information();
		
	}

}
