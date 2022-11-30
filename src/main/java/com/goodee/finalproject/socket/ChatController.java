package com.goodee.finalproject.socket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	
	@RequestMapping("/chat")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat");
		return mv;
	}
	
	
	@GetMapping("/liveAuction")
	public ModelAndView liveAuction() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/liveAuction/liveAuction");
		return mv;
		
	}
}