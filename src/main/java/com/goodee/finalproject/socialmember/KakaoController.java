package com.goodee.finalproject.socialmember;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

import com.goodee.finalproject.admin.AdminService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/socialMember/*")
public class KakaoController
{
	@Autowired
	private MemberSocialService memberSocialService;

	@GetMapping("kakaoLogin")
	public ModelAndView kakaoLogin(NaverVO naverVO, HttpSession session, KakaoVO kakaoVO, Authentication authentication) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		log.info("--- get kakaoLogin ---");
		log.info("===== authentication: {}", authentication.getPrincipal().getClass());
		log.info("equals: {}", authentication.getPrincipal().getClass().toString());

		if (authentication.getPrincipal().getClass().toString().equals("class com.goodee.finalproject.socialmember.KakaoVO"))
		{
			int rs = memberSocialService.setKakao1((KakaoVO) authentication.getPrincipal());

			modelAndView.addObject("kakaoInfo", authentication.getPrincipal());
			modelAndView.setViewName("socialMember/kakaoLogin");
			session.setAttribute("kakaoVO", rs); // 카카오 정보 DB insert 성공/실패 값
			session.setAttribute("kakaoInfo", authentication.getPrincipal());

			if (rs == 0)
			{
				modelAndView.setViewName("redirect:/");

				return modelAndView;
			}
		}
		else if (authentication.getPrincipal().getClass().toString().equals("class com.goodee.finalproject.socialmember.NaverVO"))
		{
			int rsNa = memberSocialService.setNaver((NaverVO) authentication.getPrincipal());

			log.info("naver insert: {}", rsNa);

			log.info("authentication.getPrincipal(): {}", authentication.getPrincipal());
			modelAndView.addObject("naverInfo", authentication.getPrincipal());
			modelAndView.setViewName("socialMember/kakaoLogin");
			session.setAttribute("naverInfo", authentication.getPrincipal());
			session.setAttribute("naverVO", rsNa); // 카카오 정보 DB insert 성공/실패 값

			if (rsNa == 0)
			{
				modelAndView.setViewName("redirect:/");

				return modelAndView;
			}
			else if (rsNa == 1)
			{
				modelAndView.setViewName("socialMember/kakaoLogin");

				return modelAndView;
			}
		}

		return modelAndView;
	}

	@PostMapping("kakaoLogin")
	public ModelAndView kakaoLogin(KakaoDetailVO kakaoDetailVO, Authentication authentication, NaverDetailVO naverDetailVO) throws Exception
	{
		log.info("==== post kakaoLogin ====");
		log.info("auth class: {}", authentication.getPrincipal().getClass().toString());

		ModelAndView modelAndView = new ModelAndView();

		if (authentication.getPrincipal().getClass().toString().equals("class com.goodee.finalproject.socialmember.KakaoVO"))
		{

			int rs2 = memberSocialService.setKakaoDetail(kakaoDetailVO);

			log.info("kakao login rs2: {}", rs2);

			modelAndView.addObject("rs2", rs2);
			modelAndView.setViewName("redirect:/");
		}
		else if (authentication.getPrincipal().getClass().toString().equals("class com.goodee.finalproject.socialmember.NaverVO"))
		{
			int rs2 = memberSocialService.setNaverDetail(naverDetailVO);

			modelAndView.addObject("rs2", rs2);
			modelAndView.setViewName("redirect:/");
		}

		return modelAndView;
	}
}
