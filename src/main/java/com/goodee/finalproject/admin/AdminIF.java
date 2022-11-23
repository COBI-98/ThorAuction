package com.goodee.finalproject.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.socialmember.KakaoVO;

@Mapper
public interface AdminIF
{
	public List<MemberVO> getMemberTotal(MemberVO memberVO) throws Exception;
	
	public List<KakaoVO> getKakaoTotal(KakaoVO kakaoVO) throws Exception;
}
