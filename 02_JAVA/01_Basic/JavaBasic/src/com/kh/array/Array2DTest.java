package com.kh.array;

import java.util.Scanner;

public class Array2DTest {
	
	public static void main(String[] args) {
		//사용자에게 행(m)과 열(n)을 입력받아
		//해당 행과열의 빙고판을 만들어라.
		//다음 행과 열의 들어갈 문자를 각각 모두 입력받아 저장한 뒤
		//출력해라
		
		/*
		 * 행 : 2
		 * 열 : 3
		 * 
		 * 1행1열 : 바나나
		 * 1행2열 : 배
		 * ...
		 * 2행3열 : 귤
		 * */
		Scanner sc = new Scanner(System.in);
		/*
		//행과열을 입력
		System.out.print("행 : ");
		int m = sc.nextInt();
		
		System.out.print("열 : ");
		int n = sc.nextInt();
		
		String[][] arr = new String[m][n];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.printf("%d행 %d열 : ", i+1, j+1);
				arr[i][j] = sc.next();
			}
		}
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		*/
		
		// 사용자에게 좌석의 행과 열을 입력받아 2차원배열을 생성
		// 각 좌석에 들어갈 관객의 이름을 입력받아 저장한뒤
		// 모두 입력받았으면 좌석표를 출력
		
		// ex)
		// 행(줄)의 수 :
		// 열(좌석)의 수 :
		// 1행 1열 : 철수~
		// 1행 2열 : 민수~
		// 1행 3열 : 상수~
		// ...
		// "========좌석표========"
		// 철수 민수 상수...
		System.out.println("행 :");
		int x = sc.nextInt();
		System.out.println("열 :");
		int y = sc.nextInt();
		String [][] arr2 = new String[x][y];
		for(int i=0; i<arr2.length; i++) {
			for(int j=0; j<arr2[i].length; j++) {
				System.out.printf("%d행 %d열 : ", i+1, j+1);
				arr2[i][j] = sc.next();
			}
		}

		System.out.println("========좌석표========");
		
		for(int i=0; i<arr2.length; i++) {
			for(int j=0; j<arr2[i].length; j++) {
				System.out.print(arr2[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}
}
