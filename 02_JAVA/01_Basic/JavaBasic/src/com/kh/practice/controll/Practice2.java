package com.kh.practice.controll;

import java.util.Scanner;

public class Practice2 {
	
	/*
	 * 1부터 6까지 눈이 있는 세 개의 주사위를 던졌을 때, 아래 규칙에 따라 상금을 계산하는 프로그램을 작성하세요.
	 * 
	 * 경우	구체 조건	상금 계산식
	 * 1	같은 눈이 세 개 모두 같을 때	10,000원 + (같은 눈) × 1,000원
	   2	같은 눈이 두 개만 같을 때	1,000원 + (같은 눈) × 100원
	   3	모두 다른 눈일 때	(최댓값) × 100원
	   
	   [출력문]
	   # 입력:
	   3 3 6
	   # 출력:
	   1300
	 * */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.printf("입력 : \n");
		int dice1 = sc.nextInt();
		int dice2 = sc.nextInt();
		int dice3 = sc.nextInt();
		int pay = 0;
		int same = 0;
        if (dice1 == dice2 && dice1 == dice3) {
        	pay = 10000 + (dice1) * 1000;
        }else if (dice1 == dice2 || dice1 == dice3 || dice2 == dice3) {
        	if (dice1 == dice2 || dice1 == dice3) {
        		same = dice1;
        	}else {same = dice2;
        	}
        	pay = 1000 + same * 100;
        }else {
        	
        	/*
        	 * int man = n1 > n2 ? n1 : n2;
        	 * max = max > n3 ? max : n3;
        	 * 
        	 * java에서 제공해주는 수학함수
        	 * java.lang.Math => 코드를 작성할 때 기본적으로 유용한 것들을 담아서 제공해줌.
        	 * Math.max(n1, n2) -> 둘중 큰값을 반환함
        	 * 
        	 * */
        	/*if (dice1 == 6|| dice2 == 6|| dice3 == 6) {
        		pay = 100 * 6;
        	}else if (dice1 == 5|| dice2 == 5|| dice3 == 5) {
        		pay = 100 * 5;
        	}else if (dice1 == 4|| dice2 == 4|| dice3 == 4) {
        		pay = 100 * 4;
        	}else if (dice1 == 3|| dice2 == 3|| dice3 == 3) {
        		pay = 100 * 3;
        	}else if (dice1 == 2|| dice2 == 2|| dice3 == 2) {
        		pay = 100 * 2;
        	}else if (dice1 == 1|| dice2 == 1|| dice3 == 1) {
        		pay = 100 * 1;
        	}
        	*/
        	int max = Math.max(dice1,  dice2);
        	max = Math.max(max,  dice3);
        	pay = max * 100;
        }
        System.out.printf("출력 : %d \n", pay);
	}

}
