package com.goodee.finalproject.socialmember;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/kjk/admin/*")
@Slf4j
public class PracticeController
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
