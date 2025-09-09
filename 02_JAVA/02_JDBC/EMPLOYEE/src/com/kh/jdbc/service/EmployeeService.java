package com.kh.jdbc.service;

import static com.kh.jdbc.common.JDBCTemplate.close;
import static com.kh.jdbc.common.JDBCTemplate.commit;
import static com.kh.jdbc.common.JDBCTemplate.getConnection;
import static com.kh.jdbc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.count.DeptCount;
import com.kh.jdbc.dao.EmployeeDao;
import com.kh.jdbc.dept.Dept;
import com.kh.jdbc.employee.Employee;
import com.kh.jdbc.salary.Salary;

public class EmployeeService {
	private EmployeeDao ed;
	
	
	public EmployeeService() {
		super();
		this.ed = new EmployeeDao();
	}
	
	public int insertEmployee(Employee e) {
		Connection conn = getConnection();
		
		int result = ed.insertEmployee(e, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int insertEmployeeSalary(Salary s) {
		Connection conn = getConnection();
		
		int results = ed.insertEmployeeSalary(s, conn);
		
		if(results > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return results;
	}
	
	public int deleteEmployeeSalary(Salary s) {
		Connection conn = getConnection();
		
		int result = ed.deleteEmployeeSalary(s, conn);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int deleteEmployee(Employee e) {
		Connection conn = getConnection();
		
		int result = ed.deleteEmployee(e, conn);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int updateEmployee(Employee e) {
		Connection conn = getConnection();
		
		int result = ed.updateEmployee(e, conn);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int updateEmployeeSalary(Salary s) {
		Connection conn = getConnection();
		
		int result = ed.updateEmployeeSalary(s, conn);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public ArrayList<Object> keywordEmployee(String keyword) {
		Connection conn = getConnection();
		ArrayList<Object> list = ed.keywordEmployee(keyword, conn);
		close(conn);
		return list;
	}
	
	
	public List<Object> selectEmployeeList() {
		Connection conn = getConnection();
		
		List<Object> list = ed.selectEmployeeList(conn);
		close(conn);
		
		return list;
	}
	
	public int countEmployee() {
		Connection conn = getConnection();
		int total = ed.countEmployee(conn);
		if(total > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return total;
	}
	
	public ArrayList<DeptCount> countDept() {
		Connection conn = getConnection();
		ArrayList<DeptCount> list = ed.countDept(conn);
		if(list.isEmpty()) {
			rollback(conn);
		} else {
			commit(conn);
		}
		close(conn);
		return list;
	}
}
