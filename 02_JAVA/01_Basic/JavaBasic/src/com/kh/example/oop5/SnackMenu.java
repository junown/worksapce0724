package com.kh.example.oop5;

import java.util.Scanner;

public class SnackMenu {
	private Scanner sc = new Scanner(System.in);
	private SnackController scr = new SnackController();
	
	public void menu() {
		System.out.println("스낵류를 입력하세요.");
		System.out.print("종류 : ");
		String kind = sc.next();
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("맛 : ");
		String flavor = sc.next();
		System.out.print("개수 : ");
		int numof = sc.nextInt();
		System.out.print("가격 : ");
		int price = sc.nextInt();
		scr.saveData(kind, name, flavor, numof, price);
		System.out.print("저장 완료되었습니다");
		sc.nextLine(); //개행문자 비우기
		
		System.out.println(scr.saveData(kind, name, flavor, numof, price));
		System.out.println("저장한 정보를 확인하시겠습니까?(y/n) : ");
		String check = sc.nextLine();
		if (check.equals("y")){
			System.out.println(scr.confirmData());
		}
	}
}
