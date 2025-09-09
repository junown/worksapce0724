package com.kh.example.oop7;

import java.util.Scanner;

public class ProductMenu {
	private ProductController pc = new ProductController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		while(true) {
			System.out.println("===== 상품 관리 메뉴 =====");
			System.out.println("1. 상품 추가");
			System.out.println("2. 상품 전체 조회");
			System.out.println("3. 상품 삭제(최근저장)");
			System.out.println("4. 상품명 키워드 검색");
			System.out.println("5. 상품가격 수정");
			System.out.println("9. 프로그램 종료");
			System.out.println("메뉴번호 : ");
			
			int select = sc.nextInt();
			sc.nextLine();
			
			System.out.println();
			switch(select) {
			case 1:
				System.out.println("추가할 상품명 : ");
				String pName = sc.nextLine();
				System.out.println("추가할 가격 : ");
				int pirce = sc.nextInt();
				System.out.println("추가할 브랜드명 : ");
				String brand = sc.next();
				pc.insertProduct(pName, pirce, brand);
				break;
			case 2:
				Product[] pro = pc.selectProduct();
				for(Product p : pro) {
					if(p != null)
					System.out.println(p.inform());
				}
 				break;
			case 3: deleteProduct(); break;
			case 4: searchProduct(); break;
			case 5: updateProductPrice(); break;
			case 9:
				System.out.println("프로그램을 종료합니다");
				return;
				default:
					System.out.println("잘못된 번호입니다 다시 입력하세요");
			}
		}
	}
	
	/*
	 * 상품명과 수정할 가격을 입력받아 해당상품의 가격을 수정한다
	 * */
	
	public void updateProductPrice() {
		System.out.println("수정할 상품명 : ");
		String item = sc.nextLine();
		System.out.println("수정할 가격 : ");
		int price = sc.nextInt();
		boolean isok = pc.updateProductPrice(item, price);
		if(isok) {
			System.out.println("수정이 완료되었습니다");
		}else {
			System.out.println("수정 실패");
		}
	}
	
	/*
	 * 가장장 최근에 추가된 상품을 제거하고 성공여부를 출력
	 * */
	public void deleteProduct() {
		boolean isDelete = pc.deleteProduct();
		if(isDelete) {
			System.out.println("정상적으로 삭제하였습니다");
		}else {
			System.out.println("삭제에 실패");
		}
	}
	
	/*
	 * 키워드를 입력받아 제품명을  통한 키워드 검색을 하여
	 * 제품목록을 출력 
	 * */
	public void searchProduct() {
		System.out.println("키워드 : ");
		String keyword = sc.nextLine();
		Product[] arr = pc.searchProduct(keyword);
	}
}
