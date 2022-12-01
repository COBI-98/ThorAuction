package com.goodee.finalproject.socket;

import java.util.ArrayList;
import java.util.List;

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
	static List<String> list = new ArrayList<String>();
	
	@RequestMapping("/liveAuction")
	public ModelAndView chat(HttpSession session,HttpServletRequest req) {
		list = webSocketChat.getBanList();
		MemberVO mem = (MemberVO) req.getSession().getAttribute("member");
		ModelAndView mv = new ModelAndView();
		if(list.size() > 0) {
			for(int i=0;i<list.size();i++) {
				if(mem.getName().equals(list.get(i))) {
					mv.setViewName("/index");
				}
			}
		}else {
			mv.addObject("member", mem.getName());
			mv.addObject("point", 3000L);
			mv.addObject("value",webSocketChat.getValue());
			mv.setViewName("/liveAuction/liveAuction");
		}
		
		return mv;
	}
	
}