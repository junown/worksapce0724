package com.kh.practice.controll;

import java.util.Scanner;

public class Practice1 {
	
	/*
	 * 0점부터 100점 사이의 정수를 입력받아 아래 조건에 따라 등급(학점)을 출력하는 프로그램을 작성하세요.
	 * 90 ~ 100	A
	   80 ~ 89	B
	   70 ~ 79	C
	   60 ~ 69	D
	   0 ~ 59	F
	   
	   [출력문]
	   점수를 입력하세요 : 85
	   당신의 성적은 B입니다.
	 * */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("점수를 입력하세요 : ");
		int num = sc.nextInt();
		char grade = 0;
		
		if (num >= 90) {
			grade = 'A';
		}else if (num >= 80) {
			grade = 'B';
		}else if (num >= 70) {
			grade = 'C';
		}else if (num >= 60) {
			grade = 'D';
		}else if (num <= 59) {
			grade = 'F';
		}
		
		sc.close();
		System.out.println("당신의 성적은 " + grade + "입니다");
	}

}
