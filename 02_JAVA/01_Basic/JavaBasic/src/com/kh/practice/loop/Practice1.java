package com.kh.practice.loop;

import java.util.Scanner;

public class Practice1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int sum = 0;
		
		for (int i=1; i<10; i++) {
			sum = i * num;
			System.out.printf("%d * %d = %d\n",num, i, sum);
		}
		
	}
}
