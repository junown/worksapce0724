package com.kh.practice.array;

import java.util.Scanner;

public class Practice {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean[] submitStd = new boolean[30];
		
		for (int i=0; i<30; i++) {
			submitStd[sc.nextInt() - 1] = true;
		}
		
		for (int i=0; i<submitStd.length; i++) {
			if(!submitStd[i]) {
				System.out.println(i + 1);
			}
		}
		sc.close();
	}

}
