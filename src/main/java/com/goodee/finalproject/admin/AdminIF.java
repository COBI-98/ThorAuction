package com.goodee.finalproject.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.member.RoleVO;
import com.goodee.finalproject.socialmember.KakaoVO;

@Mapper
public interface AdminIF
{
	public List<MemberVO> getMemberTotal(MemberVO memberVO) throws Exception;

	public List<KakaoVO> getKakaoTotal(KakaoVO kakaoVO) throws Exception;

	public int roleSetManager(MemberVO memberVO) throws Exception;

	public int roleSetUser(MemberVO memberVO) throws Exception;

	public int roleSetBan(MemberVO memberVO) throws Exception;
}
