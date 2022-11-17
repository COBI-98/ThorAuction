package com.goodee.finalproject.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView setUpdate() throws Exception {
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO = new MemberVO();
		
		mv.addObject("member", memberVO);
		
		mv.setViewName("mypage/update");
		
		return mv;
	}
	
	// 회원정보 수정 POST
	@PostMapping("update")
	public String setUpdate(MemberVO memberVO) throws Exception {
		
		
		
		int result = mypageService.setUpdate(memberVO);
		
		return "../mypage/update";
	}
}
