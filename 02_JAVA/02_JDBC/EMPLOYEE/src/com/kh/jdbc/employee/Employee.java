package com.kh.jdbc.employee;

import java.time.LocalDateTime;

import com.kh.jdbc.dept.Dept;
import com.kh.jdbc.salary.Salary;

public class Employee {
	private String empno;
	private String deptcode;
	private String empname;
	private String address;
	private String email;
	private String phone;
	private LocalDateTime hiredate;

	public Employee() {
		super();
	}
	public Employee(String empno, String deptcode, String empname, String address, String email, String phone,
			LocalDateTime hiredate, Dept dvo, Salary svo) {
		super();
		this.empno = empno;
		this.deptcode = deptcode;
		this.empname = empname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.hiredate = hiredate;
	}
	
	

	public Employee(String empno, String deptcode, String empname, String address, String email, String phone) {
		super();
		this.empno = empno;
		this.deptcode = deptcode;
		this.empname = empname;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}



	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getHiredate() {
		return hiredate;
	}

	public void setHiredate(LocalDateTime hiredate) {
		this.hiredate = hiredate;
	}

	@Override
	public String toString() {
		return "사원번호 : " + empno + " 부서코드 : " + deptcode + " 사원명 : " + empname + " 이메일 주소 : " + email + " 전화번호 : " + phone + " 입사일 : " + hiredate; 
	}
	
	

	
	
	
	
	
}
