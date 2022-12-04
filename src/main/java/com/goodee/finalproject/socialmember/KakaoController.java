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
	@Autowired
	public AdminService adminService;

	@PostMapping("IdCheck")
	@ResponseBody
	public int IdCheck(KakaoVO kakaoVO, HttpServletResponse response) throws Exception
	{
		log.info("======= id check =======");
		int rs = memberSocialService.IdCheck(kakaoVO);

		log.info("idCheck rs: {}", rs);

		return rs;
	}

	@GetMapping("kakaoLogin")
	public ModelAndView kakaoLogin(HttpSession session, KakaoVO kakaoVO, Authentication authentication) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		log.info("--- get kakaoLogin ---");
		log.info("===== authentication: {}", authentication.getPrincipal());

		int rs = memberSocialService.setKakao1((KakaoVO) authentication.getPrincipal());

		modelAndView.addObject("kakaoInfo", authentication.getPrincipal());
		modelAndView.setViewName("socialMember/kakaoLogin");
		session.setAttribute("kakaoVO", rs); // 카카오 정보 DB insert 성공/실패 값
		session.setAttribute("kakaoInfo", authentication.getPrincipal());

		log.info("getNickName: {}", session.getAttribute("kakaoVO"));
		int se = (int) session.getAttribute("kakaoVO");
		if (se == 0)
		{
			modelAndView.setViewName("redirect:/");

			return modelAndView;
		}

		return modelAndView;
	}

	@PostMapping("kakaoLogin")
	public ModelAndView kakaoLogin(KakaoDetailVO kakaoDetailVO) throws Exception
	{
		log.info("==== post kakaoLogin ====");

		ModelAndView modelAndView = new ModelAndView();

		int rs2 = memberSocialService.setKakaoDetail(kakaoDetailVO);

		log.info("kakao login rs2: {}", rs2);

		modelAndView.addObject("rs2", rs2);
		modelAndView.setViewName("redirect:/");

		return modelAndView;
	}
}
