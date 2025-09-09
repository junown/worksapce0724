package com.kh.jdbc.dao;

import static com.kh.jdbc.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.jdbc.count.DeptCount;
import com.kh.jdbc.dept.Dept;
import com.kh.jdbc.employee.Employee;
import com.kh.jdbc.salary.Salary;

public class EmployeeDao {
	private Properties prop = new Properties();
	
	public EmployeeDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/query2.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertEmployee(Employee e, Connection conn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertEmployee");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, e.getEmpno());
			pstmt.setString(2, e.getDeptcode());
			pstmt.setString(3, e.getEmpname());
			pstmt.setString(4, e.getAddress());
			pstmt.setString(5, e.getEmail());
			pstmt.setString(6, e.getPhone());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertEmployeeSalary(Salary s, Connection conn) {
		
		int results = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertEmployeeSalary");
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, s.getEmpno());
			pstmt.setString(2, s.getEmpname());
			pstmt.setString(3, s.getDeptid());
			pstmt.setInt(4, s.getSalary());
			
			results = pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(pstmt);
		}
		return results;
	}
	public int deleteEmployeeSalary(Salary s, Connection conn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		
		String sql = prop.getProperty("deleteEmployeeSalary");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,s.getEmpno());
			pstmt.setString(2,s.getEmpname());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteEmployee(Employee e, Connection conn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		
		String sql = prop.getProperty("deleteEmployee");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,e.getEmpno());
			pstmt.setString(2,e.getEmpname());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateEmployee(Employee e, Connection conn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateEmployee");
		System.out.println(e);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getAddress());
			pstmt.setString(2, e.getEmail());
			pstmt.setString(3, e.getPhone());
			pstmt.setString(4, e.getDeptcode());
			pstmt.setString(5, e.getEmpno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
	public int updateEmployeeSalary(Salary s, Connection conn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateEmployeeSalary");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s.getSalary());
			pstmt.setString(2, s.getEmpno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Object> keywordEmployee(String keyword, Connection conn) {
		
		
		ArrayList<Object> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("keywordEmployee");
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Dept d = new Dept();
					Salary s = new Salary();
					Employee e = new Employee();
					e.setEmpno(rset.getString("EMP_NO"));
					e.setDeptcode(rset.getString("DEPT_CODE"));
					e.setEmpname(rset.getString("EMP_NAME"));
					e.setAddress(rset.getString("ADDRESS"));
					e.setEmail(rset.getString("EMAIL"));
					e.setPhone(rset.getString("PHONE"));
					e.setHiredate(rset.getTimestamp("HIRE_DATE").toLocalDateTime());
					d.setDepttitle(rset.getString("DEPT_TITLE"));
					s.setSalary(rset.getInt("SALARY"));
					
					list.add(e);
					list.add(s);
					list.add(d);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return list;
		}
	
	public ArrayList<Object> selectEmployeeList(Connection conn) {
		
		ResultSet rset = null;
		ArrayList<Object> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectEmployeeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Employee e = new Employee();
				Salary s = new Salary();
				Dept d = new Dept();
				e.setEmpno(rset.getString("EMP_NO"));
				e.setDeptcode(rset.getString("DEPT_CODE"));
				e.setEmpname(rset.getString("EMP_NAME"));
				e.setAddress(rset.getString("ADDRESS"));
				e.setEmail(rset.getString("EMAIL"));
				e.setPhone(rset.getString("PHONE"));
				e.setHiredate(rset.getTimestamp("HIRE_DATE").toLocalDateTime());
				s.setSalary(rset.getInt("SALARY"));
				d.setDepttitle(rset.getString("DEPT_TITLE"));
				
				list.add(e);
				list.add(s);
				list.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int countEmployee(Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		int total = 0;
		String sql = prop.getProperty("countEmployee");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				total = rset.getInt("사원수");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(conn);
		}return total;
	}
	
	public ArrayList<DeptCount> countDept(Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<DeptCount> list = new ArrayList<>();
		String sql = prop.getProperty("countDept");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DeptCount c = new DeptCount();
				c.setDeptTitle(rset.getString("부서명"));
				c.setCount(rset.getInt("인원수"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} return list;
	}
}