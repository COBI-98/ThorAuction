package com.goodee.finalproject;

import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController
{
	@GetMapping("/")
	public ModelAndView home(Authentication authentication, HttpSession session) throws Exception
	{
		log.info("===== home controller =====");

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("ka", session.getAttribute("kakaoInfo"));
		modelAndView.setViewName("index");

		return modelAndView;
	}
}