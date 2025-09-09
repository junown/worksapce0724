package com.kh.practice.controll;

import java.util.Scanner;

public class Practice3 {
	/*
	 * 어린이, 청소년, 성인의 구분에 따라 입장료가 다르게 부과되는 **놀이공원 요금 계산기**를 만들어보세요.
	   또한, 주말에는 20% 할인이 적용됩니다.
	 * 
	 * 구분	나이 범위	기본 요금
	   어린이	0 ~ 12세	5,000원
	   청소년	13 ~ 18세	7,000원
	   성인	19세 이상	10,000원
	 * 
	 * [출력문]
	 * 나이를 입력하세요 : 14
	   요일을 입력하세요 : 토
	   
	   청소년 요금입니다. (주말 할인 적용)
	   최종 요금은 5600원입니다.
	 * */
	
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("나이를 입력하세요 : ");
	int age = sc.nextInt();
	System.out.println("요일를 입력하세요 : ");
	String day = sc.next();
	double pay = 0;
	double sale = 0;
	String say = null;
	String age2 = null;
	if (age <= 12) {
		pay = 5000;
		age2 = "어린이";
	}else if (age <= 18) {
		pay = 7000;
		age2 = "청소년";
	}else {
		pay = 10000;
		age2 = "성인";
	}
	switch (day) {
	case "월" :
	case "화" :
	case "수" :
	case "목" :
	case "금" :
		sale = 1;
		say = "";
		break;
	case "토" :
	case "일" :
		sale = 0.8;
		say = "(주말 할인 적용)";
			
	}
	pay = pay * sale;
	System.out.printf("%s 요금입니다. %s \n",age2, say);
	System.out.printf("최종 요금은%.0f원입니다",pay);
	}
}
