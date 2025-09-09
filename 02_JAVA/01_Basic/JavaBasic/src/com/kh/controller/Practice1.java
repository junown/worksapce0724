package com.kh.controller;

import java.util.Scanner;

public class Practice1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*
		 * 나이를 입력받아
		 * 13세이하면 : 어린이
		 * 13세초과 19세이하 : 청소년
		 * 19세 초과 : 성인
		 * 
		 * [출력]
		 * 나이를 입력 : xx
		 * xx은 xxx에 속합니다
		 * */
		
		System.out.println("나이를 입력 : ");
		int age = sc.nextInt();
		String age2 = null;
		if (age <= 13) {
			age2 = "어린이";
		}else if (age <= 19) {
			age2 = "청소년";
		}else if (age > 19) {
			age2 = "성인";
		}else {
			System.out.println("잘못 입력하셨습니다");
		}
		System.out.println(age + "은 " + age2 + "에 속합니다.");
	}
}
