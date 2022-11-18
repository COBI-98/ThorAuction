package com.goodee.finalproject.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	// 아이디 중복확인 POST
	@PostMapping("/idCheck")
	@ResponseBody
	public int getIdCheck(HttpServletRequest httpServletRequest) throws Exception {
		
		String equalsId = httpServletRequest.getParameter("id");
		MemberVO idCheck = memberService.getIdCheck(equalsId);
		
		int result = 0;
		
		if(idCheck != null) {
			result = 1;
		}
		
		return result;
	}
	
	// 로그인 GET
	@GetMapping("login")
	public void getLogin() throws Exception {}
	
	// 로그인 POST
	@PostMapping("login")
	public String getLogin(MemberVO memberVO, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		memberVO = memberService.getLogin(memberVO);
		
		session.setAttribute("member", memberVO);
		
		log.info("login 확인 : {}", memberVO.getId());
		
		//mv.addObject("member", memberVO);
		
		return "redirect:../";
	}
	
	// 로그아웃
	@GetMapping("logout")
	public String getLogout(HttpSession session) throws Exception {
		session.invalidate();
		
		return "redirect:../";
		
	}
	
	// 회원가입 GET
	@GetMapping("join")
	public void setJoin() throws Exception {}
	
	// 회원가입 + 회원등급 POST
	@PostMapping("join")
	public String setJoin(MemberVO memberVO) throws Exception {
		
		int result = memberService.setJoin(memberVO);
		
		return "redirect:../";
	}
}
