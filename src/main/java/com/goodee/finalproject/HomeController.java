package com.goodee.finalproject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.admin.AdminService;
import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.socialmember.KakaoVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController
{
	@Autowired
	private AdminService adminService;
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String client_id;
	@Value("${kakao.logout}")
	private String logout_uri;

	@GetMapping("/")
	public ModelAndView home(MemberVO memberVO, HttpSession session, KakaoVO kakaoVO,
			@RequestParam(value = "role", required = false) String role, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		log.info("===== home controller =====");
		log.info("param Role: {}", role);

		ModelAndView modelAndView = new ModelAndView();

		List<KakaoVO> kakaoVOs = adminService.getKakaoTotal(kakaoVO);

//		List<MemberVO> memberVOs = adminService.getMemberTotal(memberVO);
//		modelAndView.addObject("member", memberVOs);

		modelAndView.addObject("kakaoRole", kakaoVOs);
		modelAndView.setViewName("index");

		modelAndView.addObject("ka", session.getAttribute("kakaoInfo"));
		modelAndView.setViewName("index");

		return modelAndView;
	}
}
