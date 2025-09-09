package com.kh.example.poly2;

import java.util.Scanner;

public class LibraryMenu {
	private LibraryController lc = new LibraryController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		System.out.println("이름 : ");
		String name = sc.next();
		System.out.println("나이 : ");
		int age = sc.nextInt();
		System.out.println("성별 : ");
		char gender = sc.next().charAt(0);
		lc.insertMember(new Member(name, age, gender));
	while(true) {
		System.out.println();
		System.out.println("==== 메뉴 ==== ");
		System.out.println("1. 마이페이지 ");
		System.out.println("2. 도서 전체 조회 ");
		System.out.println("3. 도서 검색 ");
		System.out.println("4. 도서 대여하기 ");
		System.out.println("5. 도서 추가하기");
		System.out.println("9. 프로그램 종료하기 ");
		System.out.println("메뉴 번호 : ");
		
		int menu = sc.nextInt();
		sc.nextLine();
		
		switch(menu) {
		case 1:
			Member m = lc.myInfo();
			if(m != null)
				System.out.println(m);
			break;
		case 2:
			selectAll();
			break;
		case 3:
			searchBook();
			break;
		case 4:
			rentBook();
			break;
		case 5:
			insertBook();
			break;
		case 6:
			deleteBook();
			break;
		case 9:
			System.out.println("시스템을 종료합니다");
			return;
			}
		}
	}
	//도서정보를 전체 출력한 후 index를 입력받아
	//도서목록에서 해당 index에 값을 제거한 후
	//도서목록에 생성된 객체에 딱맞게 배열을 다시 생성해서 교체
	public void deleteBook() {
		selectAll();
		System.out.println("삭제할 책 번호 : ");
		int index = sc.nextInt();
		lc.deleteBook(index);
	}
	//도서정보를 모두 입력받아 컨트롤러의 도서목록에 추가
	//단, 도서목록이 가득찼을경우 길이가+1 배열을 새로 생성하여 추가
	public void insertBook() {
		//정보입력받아 생성
		Book b = null;
		
		System.out.print("책의 종류(1.AniBook, 2.CookBook) : ");
		int type = sc.nextInt();
		sc.nextLine();
		
		System.out.print("제목 : ");
		String title = sc.nextLine();
		
		System.out.print("작가 : ");
		String author = sc.nextLine();
		
		System.out.print("출판사 : ");
		String publisher = sc.nextLine();
		
		switch(type) {
		case 1:
			System.out.println("제한 나이 : ");
			int accessAge = sc.nextInt();
			b = new AniBook(title, author, publisher, accessAge);
			break;
		case 2:
			System.out.println("쿠폰 여부(1.발급 2.미발급) : ");
			boolean isCoupon = sc.nextInt() == 1;
			b = new CookBook(title, author, publisher, isCoupon);
			break;
		}
		
		lc.insertBook(b);
		/*
		int result = lc.insertBook(type);
		if(result == 0) {
			System.out.println("잘못 입력하셨습니다");
		}else if(result == 1) {
			System.out.println("제목 : "); //String title, String author, String publisher, int accessAge
			String title = sc.nextLine();
			System.out.println("작가 : ");
			String author = sc.nextLine();
			System.out.println("출판사 : ");
			String publisher = sc.nextLine();
			System.out.println("제한 나이 : ");
			int accessAge = sc.nextInt();
			
		}else if(result == 2) {
			System.out.println("제목 : "); //String title, String author, String publisher, int accessAge
			String title = sc.nextLine();
			System.out.println("작가 : ");
			String author = sc.nextLine();
			System.out.println("출판사 : ");
			String publisher = sc.nextLine();
			System.out.println("쿠폰 여부 : ");
			boolean coupon = sc.nextBoolean();
			
		}*/
	}
	
	
	public void selectAll() {
		Book[] bookarr = lc.selectAll();
		for(int i=0; i<bookarr.length; i++) {
			if(bookarr[i] == null) {
				break;
			}
			System.out.println(i + "번 도서 : " + bookarr[i]); 
		}
	}
	
	public void searchBook() {
		System.out.println("검색할 제목 키워드 : ");
		String keyword = sc.nextLine();
		lc.searchBook(keyword);
		Book[] searchBook = lc.searchBook(keyword);
		if(searchBook[0] == null) {
			System.out.println("검색결과가 없습니다");
		}else {
			for(Book bk : searchBook) {
				if(bk == null) {
					break;}
				System.out.println(bk);
			}
		}
	}
	
	public void rentBook() {
		selectAll();
		System.out.println("대여할 도서 번호 선택");
		int num = sc.nextInt();
		lc.rentBook(num);
		int result = lc.rentBook(num);
		switch(result) {
		case 0: System.out.println("성공적으로 대여하였습니다");
		case 1: System.out.println("나이 제한으로 대여 불가능입니다");
		case 2: System.out.println("성공적으로 대여되었습니다. 요리학원 쿠폰이 발급되었으니 마이페이지에서 확인하세요");
		}
	}
}
