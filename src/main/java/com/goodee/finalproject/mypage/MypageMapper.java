package com.goodee.finalproject.mypage;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.finalproject.member.MemberVO;

@Mapper
public interface MypageMapper {
	
	// 회원정보 수정
	public int setUpdate(MemberVO memberVO) throws Exception;

}
