package com.kh.practice.darray;

import java.util.Scanner;

public class Practice1 {
	/*
	 * 크기가 N × M인 두 정수 행렬 A, B가 주어질 때, 두 행렬의 원소별 합을 구해 출력하세요.
	 * 첫째 줄: 정수 `N`, `M` (1 ≤ N, M ≤ 100)
 	   다음 `N`줄: 행렬 **A**의 원소 `M`개
 	   다음 `N`줄: 행렬 **B**의 원소 `M`개
	 * 
	 * [입력]
	 * 	3 3
		1 1 1
		2 2 2
		0 1 0
		3 3 3
		4 4 4
		5 5 100
	 *
	 *[출력]
		4 4 4
		6 6 6
		5 6 100
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] arr = new int [N][M];
		int[][] arr2 = new int [N][M];
		
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		for(int i=0; i<arr2.length; i++) {
			for(int j=0; j<arr2[i].length; j++) {
				arr2[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print((arr[i][j] + arr2[i][j])+ " ");
			}
			System.out.println();
		}
	}
}
