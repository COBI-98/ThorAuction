package com.goodee.finalproject.socialMember;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@GetMapping("IdCheck")
	@ResponseBody
	public void IdCheck(KakaoVO kakaoVO, Authentication authentication)
	{
		log.info("kakao id : {}", kakaoVO.getKaNickName());
		log.info("authenti id : {}", authentication.getPrincipal());
	}

	@GetMapping("kakaoLogin")
	public ModelAndView kakaoLogin(HttpSession session, KakaoVO kakaoVO, Authentication authentication) throws Exception
	{
		log.info("--- get kakaoLogin ---");
		log.info("kakao : {}", session.getAttribute("SPRING_SECURITY_CONTEXT"));
		log.info("===== authentication: {}", authentication.getPrincipal());
		memberService.setKakao1((KakaoVO) authentication.getPrincipal());
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("kakaoInfo", authentication.getPrincipal());
		modelAndView.setViewName("/member/kakaoLogin");

		return modelAndView;
	}

	@PostMapping("kakaoLogin")
	public ModelAndView kakaoLogin(HttpSession session, KakaoVO kakaoVO, KakaoDetailVO kakaoDetailVO) throws Exception
	{
		log.info("==== post kakaoLogin ====");

		ModelAndView modelAndView = new ModelAndView();

		log.info("kakao : {}", session.getAttribute("SPRING_SECURITY_CONTEXT"));
		// Object rs = session.getAttribute("SPRING_SECURITY_CONTEXT");

		// rs = memberService.setKakao(kakaoVO);

		int rs2 = memberService.setKakaoDetail(kakaoDetailVO);

		// log.info("kakao login rs: {}", rs);
		log.info("kakao login rs2: {}", rs2);

		// modelAndView.addObject("rs", rs);
		modelAndView.addObject("rs2", rs2);
		modelAndView.setViewName("redirect:/member/login");

		return modelAndView;
	}

	@GetMapping("joinchoose")
	public void joinchoose() throws Exception
	{
		log.info("--- get joinchoose ---");
	}

	@GetMapping("join")
	public String join() throws Exception
	{
		log.info("--- get join ---");

		return "member/join";
	}

	@GetMapping("join2")
	public String join2() throws Exception
	{
		log.info("--- get join2 ---");

		return "member/join2";
	}

	@GetMapping("login")
	public String login() throws Exception
	{
		log.info("--- get login ---");

		return "member/login";
	}
}
