package com.kh.jdbc.salary;

public class Salary {
	private String empno;
	private String empname;
	private String deptcode;
	private int salary;
	
	
	public Salary() {
		super();
	}
	public Salary(String empno, String empname, String deptcode, int salary) {
		super();
		this.empno = empno;
		this.empname = empname;
		this.deptcode = deptcode;
		this.salary = salary;
	}
	
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getDeptid() {
		return deptcode;
	}
	public void setDeptid(String deptid) {
		this.deptcode = deptid;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "월급 : " + salary;
	}
	
	
}
