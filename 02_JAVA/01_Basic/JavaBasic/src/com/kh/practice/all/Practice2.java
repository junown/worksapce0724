package com.kh.practice.all;

import java.util.Scanner;

public class Practice2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String N = sc.next();
		boolean check = true;
		
		for(int i = 0; i < N.length() / 2; i++) {
			if(N.charAt(i) != N.charAt(N.length() - i -1)) {
				check = false;
				break;
			}
		}
		if (check = true) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}

}
