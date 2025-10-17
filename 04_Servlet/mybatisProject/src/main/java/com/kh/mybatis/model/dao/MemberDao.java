package com.kh.mybatis.model.dao;


import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.model.vo.Member;

public class MemberDao {
	public Member loginMember(SqlSession sqlSession, String userId,String userPwd) {
		HashMap<String, String> map = new HashMap<>();
		map.put("memberId", userId);
		map.put("memberPwd", userPwd);
		
		Member loginMember = sqlSession.selectOne("MemberMapper.loginMember", map);
		return loginMember;
	}
	
	public int idCheck(SqlSession sqlSession, String checkId) {
		return sqlSession.selectOne("MemberMapper.idCheck", checkId);
	}
	
	public int insertMember(SqlSession sqlSession, Member m) {
		return sqlSession.insert("MemberMapper.insertMember", m);
	}
	
	public int deleteMember(SqlSession sqlSession, String memberId) {
		return sqlSession.update("MemberMapper.deleteMember", memberId);
	}
	
	public int updateMember(SqlSession sqlSession, Member m) {
		return sqlSession.update("MemberMapper.updateMember", m);
	}
	
	public Member seleteUpdatedMember(SqlSession sqlSession, String userId) {
		return sqlSession.selectOne("MemberMapper.selectUpdatedMember", userId);
	}
	
	public int updateMemberPwd(SqlSession sqlSession, String userId, String updatePwd) {
		HashMap<String, String> map = new HashMap<>();
		map.put("memberId", userId);
		map.put("memberPwd", updatePwd);
		
		int updateMember = sqlSession.update("MemberMapper.updateMemberPwd", map);
		return updateMember;
		
	}
}
















