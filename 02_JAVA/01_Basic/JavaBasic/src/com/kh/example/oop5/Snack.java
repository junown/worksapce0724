package com.kh.example.oop5;

public class Snack {
	private String kind;
	private String name;
	private String flavor;
	private int numof;
	private int price;
	
	public Snack() {
	}

	public Snack(String kind, String name, String flavor, int numof, int price) {
		super();
		this.kind = kind;
		this.name = name;
		this.flavor = flavor;
		this.numof = numof;
		this.price = price;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public int getNumof() {
		return numof;
	}

	public void setNumof(int numof) {
		this.numof = numof;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String information() {
		return kind + " " + name + " " + flavor + " " + numof + " " + price + " ";
	}
}
