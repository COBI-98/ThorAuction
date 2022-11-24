package com.goodee.finalproject.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.member.MemberVO;
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
	public ModelAndView adminpage(MemberVO memberVO, KakaoVO kakaoVO) throws Exception
	{
		log.info("--- get adminpage ---");
		ModelAndView modelAndView = new ModelAndView();
		// log.info("adminpage auth: {}", authentication.getPrincipal());

		List<MemberVO> memberVOs = adminService.getMemberTotal(memberVO);
		List<KakaoVO> kakaoVOs = adminService.getKakaoTotal(kakaoVO);

		log.info("adminService memberVO: {}", memberVOs);
		log.info("adminService kakaoVO: {}", kakaoVOs);

		modelAndView.addObject("member", memberVOs);
		modelAndView.addObject("kakao", kakaoVOs);
		modelAndView.addObject("test", "test");
		modelAndView.setViewName("admin/adminpage");
		
		log.info("size: {}",memberVOs.size());

		return modelAndView;
	}
}
