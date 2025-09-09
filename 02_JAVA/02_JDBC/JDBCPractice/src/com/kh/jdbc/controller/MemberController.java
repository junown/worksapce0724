package com.kh.jdbc.controller;

import kom.kh.jdbc.model.vo.Member;

import java.util.List;

import com.kh.jdbc.service.MemberService;
import com.kh.jdbc.view.MemberMenu;

public class MemberController {
	MemberService ms = new MemberService();
	
	
	public void insertmenu(String userid, String userpwd, String username, String gender,
			              String age, String email, String phone, String address, String hobby) {
		Member m = new Member(userid, userpwd, username, gender,
	              Integer.parseInt(age), email, phone, address, hobby);
		
		int result = ms.insertmenu(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원추가");
		}else {
			new MemberMenu().displayFail("회원 추가 실패");
		}
	}
	
	public void selelctMember() {
		List<Member> list = ms.selectMemberList();
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("검색 결과가 없습니다");
		}else {
			new MemberMenu().displayList(list, "회원 목록");
		}
	}
	
	public void updateMember(String userId, String email, String phone, String address, String hobby) {
		Member m = new Member();
		m.setUserId(userId);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setHobby(hobby);
		
		int result = ms.updateMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 수정 완료");
		}else {
			new MemberMenu().displayFail("수정 실패");
		}
	}
}
