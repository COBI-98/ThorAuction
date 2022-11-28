package com.goodee.finalproject.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.member.MemberVO;

@Controller
public class ChatController {
	
	WebSocketChat webSocketChat = new WebSocketChat();
	
	//Map<String, String> map = new HashMap<>();
	private static List<String> list = new ArrayList<>();
		
	@RequestMapping("/chat")
	public ModelAndView chat(HttpSession session,HttpServletRequest req) {
		MemberVO mem = (MemberVO) req.getSession().getAttribute("member");
		ModelAndView mv = new ModelAndView();
//		for(int i=0;i<list.size();i++) {
//			if(!(list.get(i).equals(mem.getName()))) {
				//list.add(mem.getName());
			//}
		//}
		mv.addObject("list",list);
		mv.addObject("member", mem.getName());
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