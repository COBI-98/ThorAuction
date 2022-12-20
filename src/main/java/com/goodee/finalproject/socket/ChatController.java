package com.goodee.finalproject.socket;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.board.application.ApplicationService;
import com.goodee.finalproject.board.application.ApplicationVO;
import com.goodee.finalproject.member.MemberService;
import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.payhistory.PayHistoryService;
import com.goodee.finalproject.payhistory.PayHistoryVO;
import com.goodee.finalproject.product.ProductService;
import com.goodee.finalproject.product.ProductVO;
import com.goodee.finalproject.socialmember.KakaoDetailVO;
import com.goodee.finalproject.socialmember.KakaoVO;
import com.goodee.finalproject.socialmember.MemberSocialService;

@Controller
public class ChatController {
	
	static WebSocketChat webSocketChat = new WebSocketChat();
	static List<String> list = new ArrayList<String>();
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberSocialService memberSocialService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private PayHistoryService payHistoryService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/liveAuction")
	public ModelAndView chat(Authentication authentication) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<ApplicationVO> itemList = applicationService.getApprovalCheckList();
		System.out.println(authentication.getPrincipal());
			if(authentication.getPrincipal().toString().substring(0, 8).equals("MemberVO")) {
				
				MemberVO mem = (MemberVO) authentication.getPrincipal();
				if(ban(mem.getId())) {
					mv.setViewName("/index");
					return mv;
				}
				else{
					mv.addObject("member", mem.getId());
					mv.addObject("num", "1");
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
					mv.addObject("num", kakao.getKaNickName());
					mv.addObject("role",kakao.getKakaoRoleVOs());
					mv.addObject("point", detail.getKaPoint());
				}
			}

			mv.addObject("itemList", itemList);
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
	
	@RequestMapping(value="/liveAuction/loginNum", method=RequestMethod.POST)
	@ResponseBody
	public int pointMinus(String loginnum) throws Exception {
		int value = webSocketChat.getValue();
		String winuser = webSocketChat.getWinuser();
		//String item = webSocketChat.getItem();
		int itemNum = webSocketChat.getItemNum();
		int result = 0;
		
		PayHistoryVO payHistoryVO = new PayHistoryVO();

		//일반 로그인 했을 때
		if(loginnum.equals("1")) {
			MemberVO mem = new MemberVO();
			mem.setId(winuser);
			Long point = memberService.getOneMember(mem).getPoint();
			mem.setPoint(point - value);
			mem.setId(winuser);
			memberService.setPoint(mem);
			//낙찰 내역 DB 저장
			payHistoryVO.setId(winuser);

		//소셜 로그인 했을 때 
		}else {
			KakaoDetailVO kakaoDetailVO = new KakaoDetailVO();
			kakaoDetailVO.setKaNickName(loginnum);
			Long point = memberSocialService.getOneMember(kakaoDetailVO).getKaPoint();
			kakaoDetailVO.setKaNickName(loginnum);
			kakaoDetailVO.setKaPoint(point - value);
			memberSocialService.setPoint(kakaoDetailVO);
			
			//낙찰 내역 DB 저장 (유저 ID - winuser, 구매 가격 - value, 구매한 날 - sysdate, 상품 번호 - itemNum)
			payHistoryVO.setId(loginnum);

		}
		payHistoryVO.setCashe(value);
		payHistoryVO.setProductNum(itemNum);
		payHistoryService.setPayHistory(payHistoryVO);
		
		ProductVO productVO = new ProductVO();
		productVO.setProductNum(Long.valueOf(itemNum));
		result = productService.setStatus(productVO);
		
		
		//정보 초기화
		webSocketChat.setValue(0);
		webSocketChat.setWinuser("");
		webSocketChat.setItem("");
		webSocketChat.setItemNum(0);
		webSocketChat.setBroadName("");
		webSocketChat.setUnit("");
		return result;
	}
	

}