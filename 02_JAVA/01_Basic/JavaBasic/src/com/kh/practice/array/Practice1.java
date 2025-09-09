package com.kh.practice.array;

import java.util.Scanner;

public class Practice1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int x = sc.nextInt();
		
		int[] iArr = new int[n]; // 배열 선언
		
		for(int i = 0; i < n; i++) {
			iArr[i] = sc.nextInt(); // 배열 스캐너로 받기
		}
		
		for(int i = 0; i<iArr.length; i++) { // length = 길이
			if(iArr[i] < x) {
				System.out.print(iArr[i] + " ");
			}
		}
		sc.close();
		
	}
}
