package com.goodee.finalproject;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.admin.AdminService;
import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.socialmember.KakaoDetailVO;
import com.goodee.finalproject.socialmember.KakaoVO;
import com.goodee.finalproject.socialmember.MemberSocialService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController
{
	@Autowired
	private AdminService adminService;
	@Autowired
	private MemberSocialService memberSocialService;

	@GetMapping("/")
	public ModelAndView home(Principal principal, MemberVO memberVO, HttpSession session, KakaoVO kakaoVO, KakaoDetailVO kakaoDetailVO,
			@RequestParam(value = "role", required = false) String role, @RequestParam(value = "check", required = false) String check)
			throws Exception
	{
		log.info("===== home controller =====");

		// log.info("param Role: {}", role);
		// log.info("principal name: {}", principal);

		ModelAndView modelAndView = new ModelAndView();

		List<KakaoVO> kakaoVOs = adminService.getKakaoTotal(kakaoVO);
		List<MemberVO> memberVOs = adminService.getMemberTotal(memberVO);
		// List<NaverVO> naverVOs = adminService.getNaverTotal(naverVO);

		log.info("kakaoVOs: {}", kakaoVOs);
		log.info(check);
		session.setAttribute("c", check);

//		if (check == null)
//		{
//			log.info("야야야ㅑ야야야야ㅑㅑ");
//			modelAndView.setViewName("redirect:./socialMember/kakaoLogin");
//
//			return modelAndView;
//		}

		modelAndView.addObject("kakaoRole", kakaoVOs);
		modelAndView.addObject("member", memberVOs);
		// modelAndView.addObject("naver", naverVOs);
		modelAndView.addObject("ka", session.getAttribute("kakaoInfo"));

		if (principal != null)
		{
			log.info("prin: {}", principal.toString().indexOf("MemberVO"));
			if (principal.toString().indexOf("MemberVO") == 47)
			{
				log.info("타입정보 : {}" + principal.getClass());
				log.info("ID정보 : {}" + principal.getName());
				modelAndView.addObject("memID", principal.getName());
				modelAndView.setViewName("header");
			}
			else
			{
				log.info("타입정보 : {}" + principal.getClass());
				log.info("ID정보 : {}" + principal.getName());
			}
		}

		modelAndView.setViewName("index");

		return modelAndView;
	}
}
