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
import com.goodee.finalproject.mypage.MypageService;
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
	@Autowired
	private MypageService mypageService;

	@GetMapping("/")
	public ModelAndView home(Principal principal, MemberVO memberVO, HttpSession session, KakaoVO kakaoVO, KakaoDetailVO kakaoDetailVO,
			@RequestParam(value = "role", required = false) String role) throws Exception
	{
		log.info("===== home controller =====");

		// log.info("param Role: {}", role);
		// log.info("principal name: {}", principal);

		ModelAndView modelAndView = new ModelAndView();

		List<KakaoVO> kakaoVOs = adminService.getKakaoTotal(kakaoVO);
		List<MemberVO> memberVOs = adminService.getMemberTotal(memberVO);
		// List<NaverVO> naverVOs = adminService.getNaverTotal(naverVO);

		log.info("kakaoVOs: {}", kakaoVOs);

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
				
				// header 포인트 최신화
				memberVO.setId(principal.getName());
				log.info("indexId: {}", memberVO.getId());
				
				memberVO = mypageService.getList(memberVO);
				log.info("indexVO: {}", memberVO);
				
				modelAndView.addObject("memberDB", memberVO);
				modelAndView.setViewName("/**");
			}
			else
			{
				log.info("타입정보 : {}" + principal.getClass());
				log.info("ID정보 : {}" + principal.getName());
			}
		}
		
		modelAndView.setViewName("index");
		modelAndView.setViewName("redirect:/product/list");

		return modelAndView;
	}
}
