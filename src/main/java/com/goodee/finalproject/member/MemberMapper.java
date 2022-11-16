package com.goodee.finalproject.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	// 로그인
	public MemberVO getLogin(MemberVO memberVO) throws Exception;
	
	// 회원등급
	public int setRole(MemberVO memberVO) throws Exception;
	
	// 회원가입
	public int setJoin(MemberVO memberVO) throws Exception;

}
