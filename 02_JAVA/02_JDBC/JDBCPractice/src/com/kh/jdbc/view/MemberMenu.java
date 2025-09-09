package com.kh.jdbc.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.controller.MemberController;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	public void mainmenu() {
		while(true) {
			System.out.println("메인메뉴");
			System.out.println("1. 회원추가");
			System.out.println("2. 회원보기");
			System.out.println("3. 회원정보수정");
			System.out.println("9. 종료");
			System.out.print("메뉴 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				insertmenu(); break;
			case 2:
				mc.selelctMember(); break;
			case 3:
				updateMember(); break;
			case 9:
				System.out.println("종료");
				return;
				default:
					System.out.println("잘못입력 하셨습니다");
			}
		}
	}
	public void updateMember() {
		System.out.println("========== 회원 정보 수정 ==========");
		System.out.print("정보를 수정할 id : ");
		String userid = sc.nextLine();
		
		System.out.print("수정후 이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("수정후 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("수정후 주소 : ");
		String address = sc.nextLine();
		
		System.out.print("수정후 취미 : ");
		String hobby = sc.nextLine();
		
		mc.updateMember(userid, email, phone, address, hobby);
	}
	
	public void insertmenu() {
		System.out.println("========== 회원 추가 ==========");
		System.out.println("아이디 : ");
		String userid = sc.nextLine();
		System.out.println("비밀번호 : ");
		String userpwd = sc.nextLine();
		System.out.println("이름 : ");
		String username = sc.nextLine();
		System.out.println("성 : ");
		String gender = sc.nextLine();
		System.out.println("나이 : ");
		String age = sc.nextLine();
		System.out.println("이메일 : ");
		String email = sc.nextLine();
		System.out.println("전화번호 : ");
		String phone = sc.nextLine();
		System.out.println("주소 : ");
		String address = sc.nextLine();
		System.out.println("취미 : ");
		String hobby = sc.nextLine();
		
		mc.insertmenu(userid, userpwd, username, gender, age, email, phone, address, hobby);
		}
	
	public void displaySuccess(String msg) {
		System.out.println("\n서비스 요청 성공 : " + msg);
	}
	
	public void displayFail(String msg) {
		System.out.println("\n서비스 요청 실패 : " + msg);
	}
	
	public void displayNoData(String msg) {
		System.out.println("\n" + msg);
	}
	
	public void displayList(List list, String title) {
		System.out.println("==========" + title + "==========");
		for(Object o : list) {
			System.out.println(o);
		}
	}

}

