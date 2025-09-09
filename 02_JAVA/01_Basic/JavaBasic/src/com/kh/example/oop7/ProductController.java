package com.kh.example.oop7;

public class ProductController {
	Product[] pro = new Product[10];
	
	public ProductController() {
		pro[0] = new Product("갤럭시", 1200000, "삼성");
		pro[1] = new Product("갤럭시", 1300000, "애플");
		pro[2] = new Product("갤럭시", 800000, "애플");
	}
	
	public void insertProduct(String pName, int price, String brand) {
		for(int i=0; i<pro.length; i++) {
			if(pro[i] == null) {
				pro[i] = new Product(pName, price, brand);
				break;
			}
		}
	}
	public Product[] selectProduct() {
		return pro;
	}
	
	public boolean deleteProduct() {
		Product[] pro = selectProduct();
		for(int i=0; i<pro.length; i++) {
			if(i == 0 && pro[i] == null) {
				return false;
			}else if(pro[i] == null) {
				System.out.println(pro[i-1].inform());
				pro[i-1] = null;
				return true;
			}
		}
		return false;
	}
	//전달받은 keyword를 상품명을 통한 검색으로
	//검색된 상품 목록을 반환.
	public Product[] searchProduct(String keyword) {
		Product[] searchArr = new Product[pro.length];
		
		int count = 0;
		for(int i=0; i<pro.length; i++) {
			if(pro[i] == null) {
				break;
			}else if (pro[i].getpName().contains(keyword)) {
				searchArr[count++] = pro[i];
			}
		}
		return searchArr;
	}
	/*
	 * 상품명을 검색해서 상품가격을 수정
	 * 성공여부 반환
	 * */
	public boolean updateProductPrice(String item, int price) {
		Product[] pro = selectProduct();
		for(int i=0; i<pro.length; i++) {
			if(pro[i] == null) {
				return false;
			}else if (pro[i].getpName().contains(item)) {
				pro[i].setPrice(price);
				return true;
			}
		}
		return false;
	}
}
