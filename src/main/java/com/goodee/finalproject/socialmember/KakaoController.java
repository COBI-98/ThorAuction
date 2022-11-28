package com.goodee.finalproject.socialmember;

import java.security.Principal;
import java.util.List;

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

	@PostMapping("IdCheck")
	@ResponseBody
	public int IdCheck(KakaoVO kakaoVO) throws Exception
	{
		log.info("======= id check =======");
		int rs = memberSocialService.IdCheck(kakaoVO);

		log.info("idCheck rs: {}", rs);

		return rs;
	}

	@GetMapping("kakaoLogin")
	public ModelAndView kakaoLogin(KakaoVO kakaoVO, Authentication authentication) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		log.info("--- get kakaoLogin ---");
		log.info("===== authentication: {}", authentication.getName());
		log.info("kakao: {}", kakaoVO.getKaNickName());

		int rs = memberSocialService.IdCheck(kakaoVO);

		log.info("idCheck rs: {}", rs);

		// if (rs == 1)
		// {
		// modelAndView.addObject("rs", rs);
		// modelAndView.setViewName("redirect:/");
		//
		// return modelAndView;
		// }
		// else
		// {
		// }
		if (authentication.getPrincipal() != null)
		{
			memberSocialService.setKakao1((KakaoVO) authentication.getPrincipal());

			modelAndView.addObject("kakaoInfo", authentication.getPrincipal());
			modelAndView.setViewName("socialMember/kakaoLogin");

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
	public ModelAndView kakaoLogin(HttpSession session, KakaoDetailVO kakaoDetailVO) throws Exception
	{
		log.info("==== post kakaoLogin ====");

		ModelAndView modelAndView = new ModelAndView();

		log.info("kakao : {}", session.getAttribute("SPRING_SECURITY_CONTEXT"));

		int rs2 = memberSocialService.setKakaoDetail(kakaoDetailVO);

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
		modelAndView.setViewName("socialMember/kakaoLogin");
		// modelAndView.setViewName("index");

		return modelAndView;
	}
}
