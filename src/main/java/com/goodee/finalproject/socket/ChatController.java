package com.goodee.finalproject.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.member.MemberVO;

@Controller
public class ChatController {
	
	WebSocketChat webSocketChat = new WebSocketChat();
	
	//Map<String, String> map = new HashMap<>();
	private static Set<String> set = new HashSet<String>();
	
		
	@RequestMapping("/chat")
	public ModelAndView chat(HttpSession session,HttpServletRequest req) {
		MemberVO mem = (MemberVO) req.getSession().getAttribute("member");
		ModelAndView mv = new ModelAndView();

//		for(int i=0;i<list.size();i++) {
//			if(!(list.get(i).equals(mem.getName()))) {
//				list.add(mem.getName());
//			}
//		}
		mv.addObject("member", mem.getName());
		mv.addObject("point", mem.getPoint());
		mv.addObject("value",webSocketChat.getValue());
		System.out.println("value : "+webSocketChat.getValue());
		mv.addObject("count",webSocketChat.getCount());
		mv.setViewName("chat");
		return mv;
	}
	
	@RequestMapping(value="/chatid", method=RequestMethod.POST)
	@ResponseBody
	public Set<String> chat(String user) {
		ModelAndView mv = new ModelAndView();
		System.out.println(user);
		System.out.println(set.size());
		set.add(user);

		return set;
	}
	
	@RequestMapping(value="/out",method =RequestMethod.POST)
	public void out(String user){
		set.remove(user);
		System.out.println("sizeee"+set.size());
	}
	
	public void getuserlist() {
		this.set = set;
	}

}