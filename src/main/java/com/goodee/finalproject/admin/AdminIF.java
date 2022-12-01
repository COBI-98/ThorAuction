package com.goodee.finalproject.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.member.RoleVO;
import com.goodee.finalproject.socialmember.KakaoVO;

@Mapper
public interface AdminIF
{
	public List<MemberVO> getMemberTotal(MemberVO memberVO) throws Exception;

	public List<KakaoVO> getKakaoTotal(KakaoVO kakaoVO) throws Exception;

	public int roleSetManager(List<MemberVO> memberVOs, String id) throws Exception;

	public int roleSetUser(List<MemberVO> memberVOs, String id) throws Exception;

	public int roleSetBan(List<MemberVO> memberVOs, String id) throws Exception;

	public int kakaoRoleSetManager(List<KakaoVO> kakaoVOs, String kaNickName, String kakaoEmail, String kakaoName) throws Exception;

	public int kakaoRoleSetUser(List<KakaoVO> kakaoVOs, String kaNickName, String kakaoEmail, String kakaoName) throws Exception;

	public int kakaoRoleSetBan(List<KakaoVO> kakaoVOs, String kaNickName, String kakaoEmail, String kakaoName) throws Exception;
}
