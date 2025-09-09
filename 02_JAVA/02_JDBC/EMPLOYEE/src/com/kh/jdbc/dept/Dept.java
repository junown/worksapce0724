package com.kh.jdbc.dept;

public class Dept {
	private String deptid;
	private String depttitle;
	
	public Dept() {
		super();
	}
	
	public Dept(int empno, String deptid, String depttitle) {
		super();
		this.deptid = deptid;
		this.depttitle = depttitle;
	}
	
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getDepttitle() {
		return depttitle;
	}
	public void setDepttitle(String depttitle) {
		this.depttitle = depttitle;
	}
	
	@Override
	public String toString() {
		return"부서명 : " + depttitle;
	}
	
	
}
