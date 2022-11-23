package com.goodee.finalproject.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	// 아이디 중복확인
	public MemberVO getIdCheck(String id) throws Exception {
		
		return memberMapper.getIdCheck(id);
	}
	
	// 로그인
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		
		return memberMapper.getLogin(memberVO);
	}
	
	// 회원가입 + 회원등급
	public int setJoin(MemberVO memberVO) throws Exception {
		
		int result = memberMapper.setJoin(memberVO);
		
		// 회원등급
		memberMapper.setRole(memberVO);
		
		return result;
	}

}
