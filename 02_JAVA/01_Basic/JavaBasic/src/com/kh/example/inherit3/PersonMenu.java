package com.kh.example.inherit3;

import java.util.Scanner;

public class PersonMenu {
	private Scanner sc = new Scanner(System.in);
	private PersonController pc = new PersonController();
	
	public void mainMenu() {
		while(true) {
		System.out.println("1. 학생 메뉴");
		System.out.println("2. 사원 메뉴");
		System.out.println("9. 끝내기");
		System.out.println("메뉴 번호 : ");
		int menu = sc.nextInt();
		sc.nextLine();
		
		switch(menu) {
		case 1: studentMenu(); break;
		case 2: employeeMenu(); break;
		case 9: System.out.println("종료합니다"); return;
			default :
				System.out.println("잘못 입력하셨습니다");
		}
		}
	}
	public void studentMenu() {
		while(true) {
			System.out.println("1. 학생 추가");
			System.out.println("2. 학생 보기");
			System.out.println("9. 메인으로");
			System.out.println("메뉴 번호 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: 
				insertEmployee(); break;
			case 2:
				printEmployee(); break;
			case 9: System.out.println("종료합니다"); return;
				default :
					System.out.println("잘못 입력하셨습니다");
			}
			}
	}
	
	public void employeeMenu() {
		
	}
	
	public void insertEmployee() {
		if(pc.personCount()[0] >= 3) {
			System.out.println("학생을 담을 수 있는 공간이 꽉 찼기 때문에 학생 추가를 종료하고 학생 메뉴로 돌아갑니다.");
			return;
		}
		while(true) {
		System.out.println("학생 이름 : ");
		String name = sc.nextLine();
		System.out.println("학생 나이 : ");
		int age = sc.nextInt();
		System.out.println("학생 키 : ");
		double height = sc.nextDouble();
		System.out.println("학생 몸무게 : ");
		double weight = sc.nextDouble();
		System.out.println("학생 학년 : ");
		int grade = sc.nextInt();
		System.out.println("학생 전공 : ");
		String major = sc.nextLine();
		

		pc.insertStudent(name, age, height, weight, grade, major);
		
		if(pc.personCount()[0] >= 3) {
			System.out.println("학생을 담을 수 있는 공간이 꽉 찼기 때문에 학생 추가를 종료하고 학생 메뉴로 돌아갑니다.");
			return;
		}
		System.out.println("그만하시려면 N(또는 n), 이어하시려면 아무 키나 누르세요 : ");
		char stop = sc.nextLine().charAt(0);
		if(stop == 'n' && stop == 'N') {
			break;
		}
		}
	}
	
	public void printEmployee() {
		Student[] s = pc.printStudent();
		for(int i=0; i>s.length; i++) {
			System.out.println(s[i]);
		}
	}
}
