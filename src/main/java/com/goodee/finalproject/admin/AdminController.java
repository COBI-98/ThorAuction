package com.goodee.finalproject.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.member.RoleVO;
import com.goodee.finalproject.socialmember.KakaoRoleVO;
import com.goodee.finalproject.socialmember.KakaoVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/*")
public class AdminController
{
	@Autowired
	public AdminService adminService;

	@GetMapping("adminpage")
	public ModelAndView adminpage(MemberVO memberVO, KakaoVO kakaoVO, KakaoRoleVO kakaoRoleVO) throws Exception
	{
		log.info("--- get adminpage ---");
		ModelAndView modelAndView = new ModelAndView();

		List<MemberVO> memberVOs = adminService.getMemberTotal(memberVO);
		List<KakaoVO> kakaoVOs = adminService.getKakaoTotal(kakaoVO);

		// log.info("adminService memberVO: {}", memberVOs);
		// log.info("adminService kakaoVO: {}", kakaoVOs);

		modelAndView.addObject("member", memberVOs);
		modelAndView.addObject("kakao", kakaoVOs);
		modelAndView.setViewName("admin/adminpage");
		modelAndView.setViewName("header");

		return modelAndView;
	}

	@PostMapping("adminpage")
	@ResponseBody
	public void adminpage(KakaoVO kakaoVO, @RequestParam("kakaoID") String kaNickName, @RequestParam("kakaoEmail") String kakaoEmail,
			@RequestParam("kakaoName") String kakaoName, @RequestParam("ID") String id, MemberVO memberVO,
			@RequestParam("roleName") String role) throws Exception
	{
		log.info("====== post adminPage =====");
		List<MemberVO> memberVOs = adminService.getMemberTotal(memberVO);
		List<KakaoVO> kakaoVOs = adminService.getKakaoTotal(kakaoVO);
		// kakaoVOs kakaoEmail kakaoName
		log.info(kakaoName);
		log.info(kakaoEmail);
		log.info(kaNickName);
		log.info(role);
		log.info("list ID: {}", id);

		if (role.equals("ROLE_MANGER"))
		{
			log.info("manager update");
			log.info("update maneger: {}", adminService.roleSetManager(memberVOs, id, kakaoVOs, kakaoEmail, kakaoName, kaNickName));

			return;
		}
		else if (role.equals("ROLE_USER"))
		{
			log.info("user update");
			log.info("update USER: {}", adminService.roleSetUser(memberVOs, id, kakaoVOs, kakaoEmail, kakaoName, kaNickName));

			return;
		}
		else if (role.equals("ROLE_BAN"))
		{
			log.info("ban update");
			log.info("update BAN: {}", adminService.roleSetBan(memberVOs, id, kakaoVOs, kakaoEmail, kakaoName, kaNickName));

			return;
		}
		else
		{
			log.info("error");
			return;
		}
	}
}
