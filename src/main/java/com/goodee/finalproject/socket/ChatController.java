package com.goodee.finalproject.socket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	
	WebSocketChat webSocketChat = new WebSocketChat();
		
	@RequestMapping("/chat")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("count",webSocketChat.getCount());
		mv.setViewName("chat");
		return mv;
	}
	
}