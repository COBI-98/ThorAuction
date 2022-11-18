package com.goodee.finalproject.socialMember;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class KakaoController
{
	@Autowired
	private MemberService memberService;

	@GetMapping("delete")
	public ModelAndView SocialDelete(HttpSession session) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();

		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication authentication = securityContextImpl.getAuthentication();

		KakaoVO kakaoVO = (KakaoVO) authentication.getPrincipal();

		int rs = memberService.SocialDelete(kakaoVO);

		if (rs > 0)
		{
			modelAndView.setViewName("redirect:/member/logout");
		}
		
		return modelAndView;
	}

	@GetMapping("join")
	public String join() throws Exception
	{
		log.info("--- get join ---");

		return "member/join";
	}

	@GetMapping("login")
	public String login() throws Exception
	{
		log.info("--- get login ---");

		return "member/login";
	}
}
