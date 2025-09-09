package com.kh.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.count.DeptCount;
import com.kh.jdbc.dept.Dept;
import com.kh.jdbc.employee.Employee;
import com.kh.jdbc.salary.Salary;
import com.kh.jdbc.service.EmployeeService;

public class EmployeeController {
	private EmployeeService es;
	
	public EmployeeController() {
		super();
		this.es = new EmployeeService();
	}
	public void insertEmployee(String empno, String deptcode, String empname, String address,
								String email, String phone, String salary) {
		
		Employee e = new Employee(empno, deptcode, empname, address, email,
								phone);
		Salary s = new Salary(empno, empname, deptcode, Integer.parseInt(salary));
		
		int result = es.insertEmployee(e);
		int results = es.insertEmployeeSalary(s);
		
		if (result > 0 && results > 0) {
			System.out.println("사원 추가 성공");
		}else {
			System.out.println("사원 추가 실패");
		}
		
		
		
	}
	
	public void deleteEmployee(String empno, String empname) {
		Employee e = new Employee();
		Salary s = new Salary();

		s.setEmpno(empno);
		s.setEmpname(empname);
		
		e.setEmpno(empno);
		e.setEmpname(empname);
		int results = es.deleteEmployeeSalary(s);
		int result = es.deleteEmployee(e);
		if (result > 0 && results > 0) {
			System.out.println("사원 삭제 성공");
		}else {
			System.out.println("사원 삭제 실패");
		}
	}
	
	public void updateEmployee(String empno, String deptcode, String address, String email, String phone, int salary) {
		Employee e = new Employee();
		Salary s = new Salary();
		
		e.setEmpno(empno);
		e.setDeptcode(deptcode);
		e.setAddress(address);
		e.setEmail(email);
		e.setPhone(phone);
		s.setSalary(salary);
		s.setEmpno(empno);
		
		int result = es.updateEmployee(e);
		int results = es.updateEmployeeSalary(s);
		
		if (result > 0 && results > 0) {
			System.out.println("사원 정보 수정 완료");
		}else {
			System.out.println("사원 정보 수정 실패");
		}

	}
	
	public void keywordEmployee(String keyword) {
		ArrayList<Object> list = es.keywordEmployee(keyword);
		
		if (!list.isEmpty()) {
			System.out.println("성공적으로 사원의 정보를 출력");
			 for (Object e : list) {
			        System.out.print(e);
			    }
		}else {
			System.out.println("사원정보 불러오기 실패");
		}
	}
	
	
	
	public void selectEmployeeAll() {
		List<Object> list = es.selectEmployeeList();
		
		if(list.isEmpty()) {
			System.out.println("사원정보 불러오기 실패");
		}else {
			for(Object o : list) {
				System.out.println(o);
			}
		}
	}
	
	public void countEmployee() {
		int total = es.countEmployee();
		
		if (total > 0) {
			System.out.println("전체사원 수 : " + total); 
		}else {
			System.out.println("전체 사원의 수 검색 실패");
		}
	}
	
	public void countDept() {
		ArrayList<DeptCount> list = es.countDept();
		
		if (list.isEmpty()) {
			System.out.println("부서 검색 실패"); 
		}else {
			for(Object o : list) {
				System.out.println(o);
			}
		}
	}
}
