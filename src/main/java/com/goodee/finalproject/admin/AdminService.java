package com.goodee.finalproject.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.member.RoleVO;
import com.goodee.finalproject.socialmember.KakaoRoleVO;
import com.goodee.finalproject.socialmember.KakaoVO;
import com.goodee.finalproject.socialmember.NaverVO;

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

	public List<NaverVO> getNaverTotal(NaverVO naverVO) throws Exception
	{
		return adminIF.getNaverTotal(naverVO);
	}

	public int roleSetManager(List<MemberVO> memberVOs, List<KakaoVO> kakaoVOs, String id, String kakaoEmail, String kakaoName,
			String kaNickName) throws Exception
	{
		int rs = adminIF.roleSetManager(memberVOs, id);

		if (id == "")
		{
			log.info("nickname class: {}", kaNickName.getClass());
			try
			{
				log.info("parseLong: {}", (Long.parseLong(kaNickName)));
				rs = adminIF.kakaoRoleSetManager(kakaoVOs, kaNickName, kakaoEmail, kakaoName);
			}
			catch (NumberFormatException e)
			{
				rs = adminIF.naverRoleSetManager(kaNickName);
			}
		}

		return rs;
	}

	public int roleSetUser(List<MemberVO> memberVOs, String id, List<KakaoVO> kakaoVOs, String kakaoEmail, String kakaoName,
			String kaNickName) throws Exception
	{
		int rs = adminIF.roleSetUser(memberVOs, id);

		if (id == "")
		{
			try
			{
				log.info("parseLong: {}", (Long.parseLong(kaNickName)));
				rs = adminIF.kakaoRoleSetUser(kakaoVOs, kaNickName, kakaoEmail, kakaoName);
			}
			catch (NumberFormatException e)
			{
				rs = adminIF.naverRoleSetUser(kaNickName);
			}
		}

		return rs;
	}

	public int roleSetBan(List<MemberVO> memberVOs, String id, List<KakaoVO> kakaoVOs, String kakaoEmail, String kakaoName,
			String kaNickName) throws Exception
	{
		int rs = adminIF.roleSetBan(memberVOs, id);

		if (id == "")
		{
			try
			{
				log.info("parseLong: {}", (Long.parseLong(kaNickName)));
				rs = adminIF.kakaoRoleSetBan(kakaoVOs, kaNickName, kakaoEmail, kakaoName);
			}
			catch (NumberFormatException e)
			{
				rs = adminIF.naverRoleSetBan(kaNickName);
			}
		}

		return rs;
	}
}
