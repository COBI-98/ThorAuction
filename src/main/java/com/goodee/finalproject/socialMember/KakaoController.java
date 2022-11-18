package com.goodee.finalproject.socialMember;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
	@Autowired
	private MemberSocialService memberSocialService;

	// join 2번 페이지로 카카오 정보 넘기기

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

	@GetMapping("kakaoLogin")
	public void kakaoLogin(KakaoVO kakaoVO) throws Exception
	{
		log.info("--- get kakaoLogin ---");

		int rs = memberService.setKakaoMember(kakaoVO);

		log.info("kakao login rs: {}", rs);

		return;
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
