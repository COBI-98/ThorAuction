package com.goodee.finalproject.mypage;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.finalproject.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mypage/*")
@Slf4j
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	// 마이페이지 홈
	@GetMapping("")
	public ModelAndView mypageHome(MemberVO memberVO, HttpSession session, ModelAndView mv) throws Exception {
		
		mypageService.getList(memberVO);
		
		memberVO = (MemberVO)session.getAttribute("member");
		
		log.info("나와주세요 : {}", memberVO);
		
		mv.addObject("memberDB", memberVO);
		mv.setViewName("mypage/index");
		
		return mv;
	}
	
	// 포인트 충전 GET
	@GetMapping("charge")
	public void chargePoint(HttpSession session) throws Exception { }
	
	// 포인트 충전 POST
	@PostMapping("charge/point")
	@ResponseBody
	public int chargePoint(PayVO payVO, HttpServletRequest request, HttpSession session) throws Exception {
		
		// 세션으로 id를 꺼냄
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		log.info("charge : {}", memberVO);
		
		// 세션으로 memberVO를 꺼내서, PayVo에 넣어줌
		payVO.setId(memberVO.getId());
		
		int result = mypageService.ChargePoint(payVO);
		
		Enumeration<String> enumeration = request.getParameterNames();
		
		while (enumeration.hasMoreElements()) {
			String string = enumeration.nextElement();
			log.info("key : {}", string);
			String value = request.getParameter(string);
			log.info("value : {}", value);
			
		}
		
		log.info("payUid : {}", payVO.getPayUid());
		log.info("payTotal : {}", payVO.getPayTotal());
		log.info("payResult : {}", payVO.getPayResult());
		log.info("payDate : {}", payVO.getPayDate());
		
		return result;
	}
	
	// 비밀번호 체크	
	@GetMapping("checkpw")
	@ResponseBody
	public int checkPw(MemberVO memberVO) throws Exception {
		
		int result = mypageService.checkPw(memberVO);
		
		return result;
		
	}
	
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
		String VOPw = memberVO.getPw();
		
		if(!(sessionPw.equals(VOPw))) {
			attributes.addFlashAttribute("msg", false);
			return "redirect:/mypage/delete";
		}
		
		mypageService.setDelete(memberVO);
		session.invalidate();
		
		return "redirect:../";
		
		
		
	}
	
	// 회원정보 수정 GET
	@GetMapping("update")
	public void setUpdate() throws Exception {}
	
	// 회원정보 수정 POST + 비밀번호 체크
	@PostMapping("update")
	public String setUpdate(MemberVO memberVO, HttpSession session, Model model) throws Exception {
		
		mypageService.setUpdate(memberVO);
			
		session.invalidate();
			
		return "redirect:../member/login";
	}
	
}
