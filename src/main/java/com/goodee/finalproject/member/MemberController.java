package com.goodee.finalproject.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@PostMapping("idCheck")
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
	public void getLogin(@RequestParam(defaultValue = "false", required = false) Boolean error, String message, Model model) throws Exception {
		if(error) {
			model.addAttribute("msg", "ID가 존재하지 않거나 비밀번호가 일치하지 않습니다.");
		}
	}
	
	// 로그인 POST
//	@PostMapping("login")
//	public String getLogin(MemberVO memberVO, HttpSession session) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		
//		//@Valid MemberVO memberVO, BindingResult bindingResult, HttpSession session
////		
//////		memberVO = memberService.getLogin(memberVO);
////		
//		session.setAttribute("member", memberVO);
////		
//		log.info("login 확인 : {}", memberVO.getId());
////		
//		mv.addObject("member", memberVO);
////		
////		return "redirect:../";
//		
//		log.info("===== LOGIN POST =====");
//		
//		return "member/login";
//	}
	
	// 로그아웃
//	@GetMapping("logout")
//	public String getLogout(HttpSession session) throws Exception {
//		session.invalidate();
//		
//		return "redirect:../";
//		
//	}
	
	// 페이지 합쳐서 주석
//	// 회원가입 signup(ID, PW) GET
//	@GetMapping("signup")
//	public void setSignup() throws Exception {}
//	
//	// 회원가입 signup(ID, PW) POST	
//	@PostMapping("signup")
//	public String setSignup(@Valid MemberVO memberVO, BindingResult bindingResult, Model model) throws Exception {
//		System.out.println(memberVO);
//		
//		if(bindingResult.hasErrors()) {
//			log.info("SIGNUP ERROR");
//			
//			List<FieldError> errors = bindingResult.getFieldErrors();
//			Map<String, String> errorMsg = new HashMap<>();
//			
//			for(int i = 0; i < errors.size(); i++) {
//				String field = errors.get(i).getField();
//				String message = errors.get(i).getDefaultMessage();
//				
//				log.info("field : {}", field);
//				log.info("message : {}", message);
//				
//				errorMsg.put(field, message);
//			}
//			model.addAttribute("errorMsg", errorMsg);
//			
//			return "member/signup";
//			
//		}
//		
//		return "member/join";
//	}
	
	// 회원가입 join(name, birth, ...) GET
	@GetMapping("join")
	public void setJoin(@ModelAttribute MemberVO memberVO) throws Exception {}

	
	// 회원가입 join(name, birth, ...) + 회원등급 POST
	@PostMapping("join")
	public String setJoin(@Valid MemberVO memberVO, BindingResult bindingResult, Model model) throws Exception {
		int result = memberService.setJoin(memberVO);
		
		System.out.println(memberVO);
		
		if(bindingResult.hasErrors()) {
			log.info("JOIN ERROR");
			
			List<FieldError> errors = bindingResult.getFieldErrors();
			Map<String, String> errorMsg = new HashMap<>();
			
			for(int i = 0; i < errors.size(); i++) {
				String field = errors.get(i).getField();
				String message = errors.get(i).getDefaultMessage();
				
				log.info("field : {}", field);
				log.info("message : {}", message);
				
				errorMsg.put(field, message);
			}
			model.addAttribute("errorMsg", errorMsg);
			
			return "member/join";
			
		}
		
		return "redirect:../";
	}
}
