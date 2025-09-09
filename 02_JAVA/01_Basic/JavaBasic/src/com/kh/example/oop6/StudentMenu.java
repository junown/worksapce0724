package com.kh.example.oop6;

public class StudentMenu {
	private StudentController ssm = new StudentController();
	
	public StudentMenu() {
		System.out.println("========= 학생 정보 출력 =========");
		Student[] stdAr = ssm.printStudent();
		for (int i=0; i<stdAr.length; i++) {
			System.out.println("ssm.printStudent()");
		}
		System.out.println("========= 학생 성적 출력 =========");
		double[] dArr = ssm.avgScore();
		System.out.println("학생 점수 합계 : " + dArr[0]);
		System.out.println("학생 점수 평균 : " + dArr[1]);
		System.out.println("========= 성적 결과 출력 =========");
		for (int i=0; i<stdAr.length; i++) {
			if (stdAr[i].getScore() < StudentController.CUT_LINE) {
				System.out.println(stdAr[i].getName() + "학생은 재시험 대상입니다.");
		}	else {
			System.out.println(stdAr[i].getName() + "학생은 통과입니다.");
		}
		}
	}
	
}
