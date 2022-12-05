package com.goodee.finalproject.socket;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.socialmember.KakaoDetailVO;
import com.goodee.finalproject.socialmember.KakaoVO;
import com.goodee.finalproject.socialmember.MemberSocialService;

@Controller
public class ChatController {
	
	static WebSocketChat webSocketChat = new WebSocketChat();
	static List<String> list = new ArrayList<String>();
	
	@Autowired
	private MemberSocialService memberSocialService;
	
	@RequestMapping("/liveAuction")
	public ModelAndView chat(Authentication authentication) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		
			if(authentication.getPrincipal().toString().substring(0, 8).equals("MemberVO")) {
				
				MemberVO mem = (MemberVO) authentication.getPrincipal();
				if(ban(mem.getId())) {
					mv.setViewName("/index");
					return mv;
				}
				else{
					mv.addObject("member", mem.getId());
					mv.addObject("role", mem.getRoleVOs());
					mv.addObject("point", mem.getPoint());
				}

			}else {
				KakaoVO kakao = (KakaoVO) authentication.getPrincipal();
				KakaoDetailVO detail = memberSocialService.getKakaoDetail(kakao);
				if(ban(kakao.getKaName())) {
					mv.setViewName("/index");
					return mv;
				}else {
					mv.addObject("member", kakao.getKaName());
					mv.addObject("role",kakao.getKakaoRoleVOs());
					mv.addObject("point", detail.getKaPoint());
				}
			}

			mv.addObject("value",webSocketChat.getValue());
			mv.setViewName("/liveAuction/liveAuction");
			return mv;
		
		
	}
	
	public static boolean ban(String str) {
		list = webSocketChat.getBanList();
		if(list.size() > 0) {
			for(int i=0;i<list.size();i++) {
				if(str.equals(list.get(i))) {
					return true;
				}
			}
	}
		return false;
		
	}
	
}