package com.goodee.finalproject.social;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/kjk/admin/*")
@Slf4j
public class KakaoController
{
	@GetMapping("admin")
	public String admin() throws Exception
	{
		log.info("-- get admin --");

		return "/kjk/admin/admin";
	}

	@GetMapping("loginPhoto")
	public String photo() throws Exception
	{
		log.info("-- photo --");

		return "/kjk/admin/loginPhoto";
	}
}
