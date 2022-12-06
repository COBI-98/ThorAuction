package com.goodee.finalproject.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.NA;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.member.RoleVO;
import com.goodee.finalproject.socialmember.KakaoVO;
import com.goodee.finalproject.socialmember.NaverVO;

@Mapper
public interface AdminIF
{
	// 정보 전체 리스트 출력
	public List<MemberVO> getMemberTotal(MemberVO memberVO) throws Exception;

	public List<KakaoVO> getKakaoTotal(KakaoVO kakaoVO) throws Exception;

	public List<NaverVO> getNaverTotal(NaverVO naverVO) throws Exception;

	// 일반 멤버 등급 수정
	public int roleSetManager(List<MemberVO> memberVOs, String id) throws Exception;

	public int roleSetUser(List<MemberVO> memberVOs, String id) throws Exception;

	public int roleSetBan(List<MemberVO> memberVOs, String id) throws Exception;
	
	// 카카오 등급 수정
	public int kakaoRoleSetManager(List<KakaoVO> kakaoVOs, String kaNickName, String kakaoEmail, String kakaoName) throws Exception;

	public int kakaoRoleSetUser(List<KakaoVO> kakaoVOs, String kaNickName, String kakaoEmail, String kakaoName) throws Exception;

	public int kakaoRoleSetBan(List<KakaoVO> kakaoVOs, String kaNickName, String kakaoEmail, String kakaoName) throws Exception;

	// 네이버 등급 수정
	public int naverRoleSetManager(String NaNickname) throws Exception;

	public int naverRoleSetUser(String NaNickname) throws Exception;

	public int naverRoleSetBan(String NaNickname) throws Exception;
}
