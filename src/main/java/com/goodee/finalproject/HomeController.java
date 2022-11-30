package com.goodee.finalproject;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.admin.AdminService;
import com.goodee.finalproject.socialmember.KakaoDetailVO;
import com.goodee.finalproject.socialmember.KakaoVO;
import com.goodee.finalproject.socialmember.MemberSocialService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController
{
	@GetMapping("/")
	public ModelAndView home(Authentication authentication, HttpSession session) throws Exception
	{
		log.info("===== home controller =====");
		log.info("getNickName: {}", session.getAttribute("kakaoInfo"));

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("ka", session.getAttribute("kakaoInfo"));
		modelAndView.setViewName("index");

		return modelAndView;
	}
}
