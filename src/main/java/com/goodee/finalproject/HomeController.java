package com.goodee.finalproject;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.socialmember.KakaoDetailVO;
import com.goodee.finalproject.socialmember.KakaoVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController
{
	@GetMapping("/")
	public String home(Authentication authentication) throws Exception
	{
		log.info("===== home controller =====");
		// log.info("kakao name? : {}", session.getAttribute("SPRING_SECURITY_CONTEXT"));
		
		return "index";
	}
}
