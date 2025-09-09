package com.kh.example.inherit3;

public class PersonController {
	private Student[] s = new Student[3];
	private Employee[] e = new Employee[10];
	
	public int[] personCount() {
		int a = 0, b = 0;
		
		for(Student st : s) {
			if(st == null) {
				break;
			}else {
				a++;
			}
		}
		
		for(Employee ep : e) {
			if(ep == null) {
				break;
			}else {
				b++;
			}
		}
		return new int[] {a, b};
	}
	
	public void insertStudent(String name, int age, double height, double weight, int grade, String major) {
		for(int i=0; i<s.length; i++) {
			if(s[i] == null) {
				s[i] = new Student(name, age, height, weight, grade, major);
				break;
			}
		}
	}
	
	public Student[] printStudent() {
		return s;
	}
	
	public void insertEmployee(String name, int age, double height, double weight, int salary, String dept) {
		for(int i=0; i<e.length; i++) {
			if(e[i] == null) {
				e[i] = new Employee(name, age, height, weight, salary, dept);
				break;
			}
		}
	}
	
	public Employee[] printEmployee() {
		return e;
	}
}
