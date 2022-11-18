package com.goodee.finalproject.mypage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.finalproject.member.MemberController;
import com.goodee.finalproject.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mypage/*")
@Slf4j
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	// 회원탈퇴 GET
	@GetMapping("delete")
	public void setDelete() throws Exception {}
	
	// 회원탈퇴 POST
	@PostMapping("delete")
	public String setDelete(MemberVO memberVO, HttpSession session, RedirectAttributes attributes) throws Exception {
		
		// 세션에 있는 member정보를 가져와 member변수에 넣어준다
		MemberVO member =  (MemberVO)session.getAttribute("member");
		
		// 세션에 있는 비밀번호
		String sessionPw = member.getPw();
		
		// VO에 들어오는 비밀번호 
		String voPw = memberVO.getPw();
		
		if(!(sessionPw.equals(voPw))) {
			attributes.addFlashAttribute("msg", false);
			return "redirect:/mypage/delete";
		}
		
		mypageService.setDelete(memberVO);
//		session.invalidate();
		
		return "redirect:../";
		
		
		
	}
	
	// 회원정보 수정 GET
	@GetMapping("update")
	public void setUpdate() throws Exception {}
	
	// 회원정보 수정 POST
	@PostMapping("update")
	public String setUpdate(MemberVO memberVO, HttpSession session) throws Exception {
		
		mypageService.setUpdate(memberVO);
		
		session.invalidate(); // 세션 끊어버리는 방법말고 뭐가 있을까...
		
//		int result = mypageService.setUpdate(memberVO, session.getServletContext()); // 새로 로그인 해야 수정된 게 보이는데 
		
		return "../member/login";
	}
}
