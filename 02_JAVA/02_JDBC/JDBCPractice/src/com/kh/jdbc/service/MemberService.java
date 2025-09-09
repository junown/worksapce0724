package com.kh.jdbc.service;


import static com.kh.jdbc.common.JDBCTemplate.close;
import static com.kh.jdbc.common.JDBCTemplate.commit;
import static com.kh.jdbc.common.JDBCTemplate.getConnection;
import static com.kh.jdbc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.modle.dao.MemberDao;

import kom.kh.jdbc.model.vo.Member;

public class MemberService {
	private MemberDao md = new MemberDao();
	
	public int insertmenu(Member m) {
		
		Connection conn = getConnection();
		
		int result = md.insertMember(m, conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public List<Member> selectMemberList() {
		Connection conn = getConnection();
		
		List<Member> list = md.selectMemberList(conn);
		close(conn);
		
		return list;
		
	}
	
	public int updateMember(Member m) {
		Connection conn = getConnection();
		
		int result = md.updateMember(m, conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
