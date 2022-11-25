package com.goodee.finalproject.socket;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	
	WebSocketChat webSocketChat = new WebSocketChat();
		
	@RequestMapping("/chat")
	public ModelAndView chat(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("value",webSocketChat.getValue());
		System.out.println("value : "+webSocketChat.getValue());
		mv.addObject("count",webSocketChat.getCount());
		mv.setViewName("chat");
		return mv;
	}
	
//	@RequestMapping(value="/chat", method=RequestMethod.POST)
//	public ModelAndView chat(int rank) {
//		value = rank;
//		System.out.println(value);
//		return null;
//	}

}