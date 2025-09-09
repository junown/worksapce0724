package com.kh.practice.all;

import java.util.Scanner;

public class Practice4 {
	
	/*
	 * 문자열.replace("찾을문자열", "바꿀문자열");
	 * 원래문자열에서 찾을문자열을 검색하여 바꿀문자열로 치환한 새로운 문자열을 반환.
	 * */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		
		int i=0, count=0;
		int len = s.length();
		
		while(i<s.length()) {
			//3글자 일때
			if( (i+2  < len) &&
			   s.charAt(i) == 'd' &&
			   s.charAt(i + 1) == 'z' &&
		       s.charAt(i + 2) == '=') {
				count++;
				i += 3;
				continue;
			}
			//2글자 일때
			String str = s.charAt(i) + "" + s.charAt(i+1);		
			if(i+1 < len) {
			if(str.equals("c=") || 
					str.equals("c-") || 
					str.equals("d-") || 
					str.equals("lj") || 
					str.equals("nj") || 
					str.equals("s=") || 
					str.equals("z=")) {
					count++;
					i += 2;
					continue;
			}
			}
			
			//1글자 일때
			count++;
			i++;
		}
		System.out.println(count);
	}

}
