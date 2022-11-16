package com.goodee.finalproject.social;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class KakaoController
{
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
