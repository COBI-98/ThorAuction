package com.goodee.finalproject.socialmember;

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
@RequestMapping("/admin/*")
public class KakaoController
{
	@Autowired
	private MemberSocialService memberService;

	@GetMapping("adminpage")
	public void adminpage(HttpSession session, Authentication authentication, KakaoDetailVO kakaoDetailVO) throws Exception
	{
		log.info("--- get adminpage ---");
		ModelAndView modelAndView = new ModelAndView();
		log.info("adminpage auth: {}", authentication);
		log.info("adminpage detail: {}", kakaoDetailVO);
		log.info("session: {}", session.getAttribute("Detail"));

		// modelAndView.addObject("auth", authentication);
	}

	@PostMapping("IdCheck")
	@ResponseBody
	public int IdCheck(KakaoVO kakaoVO, Authentication authentication) throws Exception
	{
		log.info("kakao id : {}", kakaoVO);
		// log.info("authenti id : {}", authentication.getPrincipal());

		int rs = memberService.IdCheck(kakaoVO);

		log.info("idCheck rs: {}", rs);

		return rs;
	}

	@GetMapping("kakaoLogin")
	public ModelAndView kakaoLogin(KakaoVO kakaoVO, Authentication authentication) throws Exception
	{
		log.info("--- get kakaoLogin ---");
		log.info("===== authentication: {}", authentication.getPrincipal());
		int rs = memberService.IdCheck(kakaoVO);
		
		log.info("idCheck rs: {}", rs);
		
		ModelAndView modelAndView = new ModelAndView();

		if (authentication.getPrincipal() != null)
		{
			memberService.setKakao1((KakaoVO) authentication.getPrincipal());
			
			modelAndView.addObject("kakaoInfo", authentication.getPrincipal());
			modelAndView.setViewName("admin/kakaoLogin");
			return modelAndView;
		}
		else
		{
			modelAndView.addObject("kakaoInfo", authentication.getPrincipal());
			modelAndView.setViewName("redirect:/");

			return modelAndView;
		}

	}

	@PostMapping("kakaoLogin")
	public ModelAndView kakaoLogin(HttpSession session, KakaoVO kakaoVO, KakaoDetailVO kakaoDetailVO) throws Exception
	{
		log.info("==== post kakaoLogin ====");

		ModelAndView modelAndView = new ModelAndView();

		log.info("kakao : {}", session.getAttribute("SPRING_SECURITY_CONTEXT"));

		int rs2 = memberService.setKakaoDetail(kakaoDetailVO);

		log.info("kakao login rs2: {}", rs2);

		modelAndView.addObject("rs2", rs2);
		modelAndView.setViewName("redirect:/");
		session.setAttribute("Detail", kakaoDetailVO);

		return modelAndView;
	}

	@GetMapping("joinchoose")
	public void joinchoose(Authentication authentication) throws Exception
	{
		log.info("--- get joinchoose ---");
	}

	@PostMapping("joinchoose")
	public ModelAndView joinchoose(HttpSession session, Authentication authentication, KakaoDetailVO kakaoDetailVO) throws Exception
	{
		log.info("authenti id : {}", authentication.getPrincipal());

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("detailData", authentication.getPrincipal());
		session.setAttribute("kakaoVO", authentication.getPrincipal());
		modelAndView.setViewName("admin/kakaoLogin");
		// modelAndView.setViewName("index");

		return modelAndView;
	}
}
