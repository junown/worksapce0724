package com.kh.example.inherit;

import java.util.Scanner;

public class PointMenu {
	private Scanner sc = new Scanner(System.in);
	private CircleController cc = new CircleController();
	private RectangleController rc = new RectangleController();
	
	public void mainMenu() {
		while(true) {
		System.out.println("==== 메뉴 ====");
		System.out.println("1. 원");
		System.out.println("2. 사각형");
		System.out.println("9. 끝내기");
		System.out.print("메뉴 번호 : ");
		
		int select = sc.nextInt();
		sc.nextLine();
		
		switch(select) {
		case 1 : 
			circleMenu();
			break;
		case 2 :
			rectangleMenu();
			break;
		case 9 :
			System.out.println("종료합니다.");
			return;
		default:
			System.out.println("잘못된 번호입니다 다시 입력하세요");
		}
	  }
		
	}
	public void circleMenu() {
		while(true) {
		System.out.println("1. 원 둘레");
		System.out.println("2. 원 넓이");
		System.out.println("9. 메인으로");
		System.out.print("메뉴 번호 : ");
		
		int select = sc.nextInt();
		sc.nextLine();
		
		switch(select) {
		case 1 :
			calcCircum();
			break;
		case 2 :
			calcCircleArea();
			break;
		case 9 :
			System.out.println("종료합니다.");
			return;
		default:
			System.out.println("잘못된 번호입니다 다시 입력하세요");
		}
	  }
	}

		public void rectangleMenu() {
			while(true) {
			System.out.println("1. 사각형 둘레");
			System.out.println("2. 사각형 넓이");
			System.out.println("9. 메인으로");
			System.out.print("메뉴 번호 : ");
			
			int select = sc.nextInt();
			sc.nextLine();
			
			switch(select) {
			case 1 :
				calcPerimeter();
				break;
			case 2 :
				calcRectArea();
				break;
			case 9 :
				System.out.println("종료합니다.");
				return;
			default:
				System.out.println("잘못된 번호입니다 다시 입력하세요");
			}
		  }
		}
		public void calcCircum() {
				System.out.println("x 좌표 : ");
				int x = sc.nextInt();
				System.out.println("y 좌표 : ");
				int y = sc.nextInt();
				System.out.println("반지름 : ");
				int radius = sc.nextInt();
				sc.nextLine();
				
				String info = cc.calcCircum(x, y, radius);
				System.out.println("면적" + info);
				}
		public void calcCircleArea() {
				System.out.println("x 좌표 : ");
				int x = sc.nextInt();
				System.out.println("y 좌표 : ");
				int y = sc.nextInt();
				System.out.println("반지름 : ");
				int radius = sc.nextInt();
				sc.nextLine();
				
				String info = cc.calcCircum(x, y, radius);
				System.out.println("둘레" + info);
				}
		public void calcPerimeter() {
			System.out.println("x 좌표 : ");
			int x = sc.nextInt();
			System.out.println("y 좌표 : ");
			int y = sc.nextInt();
			System.out.println("높이 : ");
			int height = sc.nextInt();
			System.out.println("너비 : ");
			int width = sc.nextInt();
			sc.nextLine();
			
			String info = rc.calcPerimeter(x, y, height, width);
			System.out.println("면적" + info);
			}
		public void calcRectArea() {
			System.out.println("x 좌표 : ");
			int x = sc.nextInt();
			System.out.println("y 좌표 : ");
			int y = sc.nextInt();
			System.out.println("높이 : ");
			int height = sc.nextInt();
			System.out.println("너비 : ");
			int width = sc.nextInt();
			sc.nextLine();
			
			
			String info = rc.calcArea(x, y, height, width);
			System.out.println("둘레" + info);
			}
}
