package com.kh.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Run2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		Statement stm = null;
		int result = 0;
		
		System.out.println("번호 : ");
		int tno = sc.nextInt();
		
		System.out.println("이름 : ");
		String tName = sc.next();
		sc.nextLine();
		
		String sql = "INSERT INTO TEST VALUES ("+tno+", '"+tName+"',SYSDATE)";
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("등록 성공");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","C##JDBC", "JDBC");
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				conn.commit();
			}	else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			stmt.close();
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		sc.close();
		
		if(result > 0) {
			System.out.println("데이터 추가 서공");
		} else {
			System.out.println("실패");
		}
		
		
	}
}
