package com.goodee.finalproject.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.member.RoleVO;
import com.goodee.finalproject.socialmember.KakaoRoleVO;
import com.goodee.finalproject.socialmember.KakaoVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService
{
	@Autowired
	public AdminIF adminIF;

	public List<MemberVO> getMemberTotal(MemberVO memberVO) throws Exception
	{
		return adminIF.getMemberTotal(memberVO);
	}

	public List<KakaoVO> getKakaoTotal(KakaoVO kakaoVO) throws Exception
	{
		List<KakaoVO> ar = adminIF.getKakaoTotal(kakaoVO);

		return ar;
	}

	public int roleSetManager(MemberVO memberVO) throws Exception
	{
		return adminIF.roleSetManager(memberVO);
	}

	public int roleSetUser(MemberVO memberVO) throws Exception
	{
		return adminIF.roleSetUser(memberVO);
	}

	public int roleSetBan(MemberVO memberVO) throws Exception
	{
		return adminIF.roleSetBan(memberVO);
	}
}
