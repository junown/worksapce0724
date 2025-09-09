package com.kh.compara;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Run {

	/*
	 * - Comparable : 객체 스스로 기본 정렬 기준 제공
	 * - Comparator : 필요할 때 외부에서 정렬 기준 주입
	 * 
	 * */
	public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		list.add(new Student("철수", 80, 18));
		list.add(new Student("민수", 90, 15));
		list.add(new Student("지수", 75, 17));
		list.add(new Student("지수", 75, 16));
		list.add(new Student("영수", 100, 19));
		
		System.out.println("최초 저장 값 : ");
		System.out.println(list);
		
		//Comparable : 학생의 정의된 기준으로 정렬(Student클래스의 compareTo 기준)
		// 점수 오름차순 -> 이름 오름차순 -> 나이 오름차순
		Collections.sort(list);
		System.out.println("Comparable 정렬 후 : ");
		System.out.println(list);
		
		//Comparator : 이름 오름차순
		Collections.sort(list, new NameAscComparator());
		System.out.println("Comparator 이름 오름차순 정렬 후 : ");
		System.out.println(list);
		
		//Comparator : 점수 내름차순
		Collections.sort(list, new ScoreDescComparator());
		System.out.println("Comparator 점수 내름차순 정렬 후 : ");
		System.out.println(list);
		
		//Comparator : 점수 내름차순
		Collections.sort(list, new ScoreDescAndNameAscComparator());
		System.out.println("Comparator 점수 내름차순 & 이름 오름차순 정렬 후 : ");
		System.out.println(list);
	}

}
