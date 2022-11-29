package com.goodee.finalproject.mypage;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String mypageHome() throws Exception {
		
		return "mypage/index";
	}
	
	// 포인트 충전 GET
	@GetMapping("charge")
	public void charge(HttpSession session) throws Exception {
		
		// 결제를 위한 member 세션 유지
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		if(memberVO != null) {
			log.info("세션님 있으시면 모습을 드러내주세요~~ {}", memberVO);
		}
	}
	
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
		
		
		
		log.info("나와주세요~ : {}", payVO.getPayUid());
		log.info("나와주세요~ : {}", payVO.getPayTotal());
		log.info("나와주세요~ : {}", payVO.getPayResult());
		log.info("나와주세요~ : {}", payVO.getPayDate());
		
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
		String voPw = memberVO.getPw();
		
		if(!(sessionPw.equals(voPw))) {
			attributes.addFlashAttribute("msg", false);
			return "redirect:/mypage/delete";
		}
		
		mypageService.setDelete(memberVO);
		session.invalidate(); // 세션 업데이트?
		
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
		
		return "redirect:../member/login";
	}
}
