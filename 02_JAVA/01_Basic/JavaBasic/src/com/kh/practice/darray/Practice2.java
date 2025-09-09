package com.kh.practice.darray;

import java.util.Scanner;

public class Practice2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("줄 :");
		int N = sc.nextInt();
		System.out.println("열 :");
		int M = sc.nextInt();
		int [][] sum = new int [N][M];
		int max = 0;
		int row = 1;
		int col = 1;
		
		for(int i = 0; i<sum.length; i++) {
			for(int j = 0; j<sum[i].length; j++) {
				sum[i][j] = sc.nextInt();
				if(max < sum[i][j]) {
					max = sum[i][j];
					row = i + 1;
					col = j + 1;
				}
			}
		}
		System.out.println(max);
		System.out.println(row + " " + col);
		sc.close();
	}
}