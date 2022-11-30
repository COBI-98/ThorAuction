package com.goodee.finalproject.socket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.member.MemberVO;

@Controller
public class ChatController {
	
	WebSocketChat webSocketChat = new WebSocketChat();
	
	@RequestMapping("/liveAuction")
	public ModelAndView chat(HttpSession session,HttpServletRequest req) {
		MemberVO mem = (MemberVO) req.getSession().getAttribute("member");
		ModelAndView mv = new ModelAndView();

//		mv.addObject("member", mem.getName());
//		mv.addObject("point", mem.getPoint());
		
		mv.addObject("member", "ball");
		mv.addObject("point","40000");
		mv.addObject("value",webSocketChat.getValue());
		mv.setViewName("liveAuction/liveAuction");
		return mv;
	}
	
	

}