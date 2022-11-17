package com.goodee.finalproject.mypage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.member.MemberController;
import com.goodee.finalproject.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mypage/*")
@Slf4j
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	// 회원정보 수정 GET
	@GetMapping("update")
	public void setUpdate() throws Exception {}
	
	// 회원정보 수정 POST
	@PostMapping("update")
	public String setUpdate(MemberVO memberVO, HttpSession session) throws Exception {
		
		mypageService.setUpdate(memberVO);
		
		session.invalidate();
		
//		int result = mypageService.setUpdate(memberVO, session.getServletContext());
		
		return "../member/login";
	}
}
