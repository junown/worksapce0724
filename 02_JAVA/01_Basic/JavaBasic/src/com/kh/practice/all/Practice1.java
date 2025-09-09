package com.kh.practice.all;

import java.util.Scanner;

public class Practice1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] black =new int[] {1, 1, 2, 2, 2, 8};
		int [] white = new int [6];
		for(int i=0;i<white.length;i++) {
			white[i] = sc.nextInt();
			System.out.print((black[i] - white[i]) + " ");
		}
		
	}

}