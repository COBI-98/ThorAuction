package com.goodee.finalproject.mypage;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.finalproject.board.application.ApplicationVO;
import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.product.BidAmountVO;
import com.goodee.finalproject.product.LikeSaleProductVO;
import com.goodee.finalproject.product.ProductService;
import com.goodee.finalproject.product.ProductVO;
import com.goodee.finalproject.product.SaleProductVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mypage/*")
@Slf4j
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	@Autowired
	private ProductService productService;
	
	
	// 마이페이지 홈
	@GetMapping("info")
	public ModelAndView info(MemberVO memberVO, ModelAndView mv) throws Exception {
		
		// DB에서 조회한 VO를 다시 VO에 담아줌
		memberVO = mypageService.getList(memberVO);
		
//		memberVO = (MemberVO)session.getAttribute("member");
		
		log.info("나와주세요 : {}", memberVO);
		
		mv.addObject("memberDB", memberVO);
		mv.setViewName("mypage/info");
		
		return mv;
	}
	
	// 신청한 상품
	@GetMapping("appProduct")
	public void appProduct(ApplicationVO applicationVO, Model model, @AuthenticationPrincipal MemberVO memberVO) throws Exception {
		
		applicationVO.setWriter(memberVO.getId());
		
		List<ApplicationVO> appProduct = mypageService.appProduct(applicationVO);
		
		log.info("신청 상품 : {}", appProduct);
		
		model.addAttribute("memberDB", memberVO);
		model.addAttribute("appProduct", appProduct);

	}
	
	// 관심 상품 삭제
	@PostMapping("deleteLikeProduct")
	@ResponseBody
	public int deleteLikeProduct(LikeSaleProductVO likeSaleProductVO) throws Exception {
		
		int result = mypageService.deleteLikeProduct(likeSaleProductVO);
		
		if (result > 0) {
			log.info("삭제 성공");
		} else {
			log.info("삭제 실패");
		}
		
		return result;
	}
	
	// 관심내역
	@GetMapping("likeProduct")
	public void likeProduct(LikeSaleProductVO likeSaleProductVO, Model model, @AuthenticationPrincipal MemberVO memberVO) throws Exception {
		
		List<LikeSaleProductVO> likelike = mypageService.likeProduct(likeSaleProductVO);
		
		log.info("관심내역 : {}", likelike);
		
		model.addAttribute("likeProduct", likelike);
		model.addAttribute("memberDB", memberVO);

	}
	
	// 입찰내역
	@GetMapping("bidhistory")
	public Model bidProductInformation(BidAmountVO bidAmountVO, @AuthenticationPrincipal MemberVO memberVO, Model model) throws Exception {
		SaleProductVO saleProductVO = new SaleProductVO();
		
		
		// 상품정보
		List<SaleProductVO> saleProductVOs = mypageService.bidProductInformation(bidAmountVO);
		// 내가 입찰한 상품의 입찰가, 입찰시간
		List<BidAmountVO> bidAmountVOs = mypageService.bidHistory(bidAmountVO);		
		// 입찰한 상품 수
		int count = mypageService.productCount(bidAmountVO);
		
		List<SaleProductVO> saleVO = new ArrayList<>();
		
		saleVO = mypageService.bidProductInformation(bidAmountVO);
		
		List<Long> orderTime = new ArrayList<>();
		List<Timestamp> time = new ArrayList<>();
		List<Long> orderBidAmount = new ArrayList<>();
		
		// 경매 종료시간 계산 
		for(int productNum = 0; productNum < saleVO.size(); productNum++) {
			ProductVO productVO = new ProductVO();
			
			productVO.setProductNum(saleProductVOs.get(productNum).getProductNum());
			productVO = productService.getProductApproval(productVO);
			
			// 등록일을 초로 변환
			Long productAddDate = saleVO.get(productNum).getProductDate().getTime();
			// 기간을 초로 변환
			Long auctionPeriod = productVO.getAuctionPeriod()*24*3600*1000;
			
			log.info("등록일->초 : {}", productAddDate);
			log.info("기간->초 : {}", auctionPeriod);
			
			// Long을 tiestamp으로 변환(초에서 날짜로 변환)
			Timestamp timestamp = new Timestamp(productAddDate + auctionPeriod);
			orderTime.add(productAddDate + auctionPeriod);
			 time.add(timestamp);
			 
			log.info("Long : {}", orderTime);
			log.info("timestamp : {}", timestamp);
;
			log.info("=============================================");
			
			// 내가 입찰한 상품의 최고가
			bidAmountVO.setProductId(saleVO.get(productNum).getProductId());
			Long check = mypageService.bidMaxHistory(bidAmountVO);
			
			orderBidAmount.add(check);
			model.addAttribute("bidMax"+productNum, check);
			
			//model.addAttribute("orderTime", timestamp);
		} // for End
		
		log.info("입찰내역 : {}", saleProductVOs);
		log.info("입찰내역 : {}", bidAmountVOs);
		log.info("입찰내역 : {}", count);
//		log.info("bidMaxAmountVO : {}", bidMaxAmounts);
		log.info("입찰내역 : {}", memberVO);
		
		model.addAttribute("saleProducts", saleProductVOs);
		model.addAttribute("bidAmounts", bidAmountVOs);
//		model.addAttribute("bidMaxAmounts", bidMaxAmounts);
		model.addAttribute("memberDB", memberVO);
		model.addAttribute("count", count);
		model.addAttribute("orderTime", time);
		model.addAttribute("time", time);
		model.addAttribute("orderBidAmount", orderBidAmount);
		
		return model;
	}
	
	// 결제내역
	@GetMapping("payhistory")
	public void payhistory(PayVO payVO, Model model) throws Exception {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setId(payVO.getId());
		
		List<PayVO> payVOs = mypageService.payHistory(payVO);
		
		log.info("결제내역 : {}", payVOs);
		
		model.addAttribute("pays", payVOs);
		model.addAttribute("memberDB", memberVO);
	}
	
	// 포인트 충전 GET
	@GetMapping("charge")
	public void chargePoint(@AuthenticationPrincipal MemberVO memberVO, Model model) throws Exception {
		
		log.info("회원 정보 : {}", memberVO);
		
		model.addAttribute("memberDB", memberVO);
	}
	
	// 포인트 충전 POST
	@PostMapping("charge/point")
	@ResponseBody
	public int chargePoint(PayVO payVO, @AuthenticationPrincipal MemberVO memberVO, HttpServletRequest request, HttpSession session) throws Exception {
		
		// 세션으로 id를 꺼냄
//		memberVO = (MemberVO)session.getAttribute("member");
		log.info("회원 정보 : {}", memberVO);
		
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
	
	// 비밀번호 체크 GET
	@GetMapping("checkpw")
	public void checkPw(@AuthenticationPrincipal MemberVO memberVO, Model model) throws Exception {
		log.info("회원 정보 : {}", memberVO);
		
		model.addAttribute("memberDB", memberVO);
		
	}
	
	// 비밀번호 체크 POST
	@PostMapping("checkpw")
	@ResponseBody
	public boolean checkPw(@AuthenticationPrincipal MemberVO memberVO, @RequestParam String checkPassword, Model model) throws Exception {
		
//		model.addAttribute("memberDB", memberVO);
		
		String id = memberVO.getId();
		log.info("컨트롤러 비빌 : {}", memberVO);
		log.info("컨트롤러 아이디 : {}", id);
		log.info("컨트롤러 비빌 : {}", checkPassword);
		
//		return "mypage/checkpw";
		
		return mypageService.checkPw(memberVO, checkPassword);
		
	}
	
	// 회원탈퇴 GET
	@GetMapping("delete")
	public void setDelete(@AuthenticationPrincipal MemberVO memberVO2, Model model) throws Exception {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setId(memberVO2.getId());
		
		log.info("회원 정보 : {}", memberVO);
		
		model.addAttribute("memberDB", memberVO);
	}
	
	// 회원탈퇴 POST
	@PostMapping("delete")
	public String setDelete(@AuthenticationPrincipal MemberVO memberVO, HttpSession session, RedirectAttributes attributes) throws Exception {
		
		log.info("탈퇴 PST: {}", memberVO);
		
		int result = mypageService.setDelete(memberVO);
		
		if(result > 0) {
			log.info("탈퇴 성공");
		} else {
			log.info("탈퇴 실패");
		}
		
		// 시큐리티 로그아웃 처리
		SecurityContextHolder.clearContext();
		
		return "redirect:/";
		
//		MemberVO memberVO = new MemberVO();
		
//		// 세션에 있는 member정보를 가져와 member변수에 넣어준다
////		MemberVO member =  (MemberVO)session.getAttribute("member");
//		MemberVO member = memberVO2;
//		
//		// 세션에 있는 비밀번호
//		String sessionPw = member.getPw();
//		
//		// VO에 들어오는 비밀번호 
//		String VOPw = memberVO.getPw();
//		
//		log.info("Delete : {}",VOPw);
//		
//		if(!(sessionPw.equals(VOPw))) {
//			attributes.addFlashAttribute("msg", false);
//			return "redirect:/mypage/delete";
//		}
	}
	
	// 비밀번호 수정 GET
	@GetMapping("updatepw")
	public void setUpdatePw(@AuthenticationPrincipal MemberVO memberVO2, Model model) throws Exception {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setId(memberVO2.getId());
		
		memberVO = mypageService.getList(memberVO);
		
		log.info("컴트롤러 비번수정 GET : {}", memberVO);
		
		model.addAttribute("memberDB", memberVO);
	}	
	
	// 비밀번호 수정 POST
	@PostMapping("updatepw")
	public String setUpdatePw(@RequestBody MemberVO memberVO, ModelAndView mv) throws Exception {
		
		int result = mypageService.setUpdatePw(memberVO);
		
		if(result > 0) {
			log.info("수정 성공");
		} else {
			log.info("수정 실패");
		}
		
		log.info("컨트롤러 비번 수정 Update : {}", memberVO);
		
		// 시큐리티 로그아웃 처리
		SecurityContextHolder.clearContext();
		
		return "redirect:../";
		
	}	
	
	// 회원정보 수정 GET
	@GetMapping("update")
	public void setUpdate(@AuthenticationPrincipal MemberVO memberVO2, Model model) throws Exception {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setId(memberVO2.getId());
		
		memberVO = mypageService.getList(memberVO);
		
		log.info("컴트롤러 수정 GET : {}", memberVO);
		
		model.addAttribute("memberDB", memberVO);
		
		
	}
	
	// 회원정보 수정 POST
	@PostMapping("update")
	//@PutMapping("update")
	public String setUpdate(@RequestBody MemberVO memberVO, ModelAndView mv) throws Exception {
//		MemberVO memberVO = new MemberVO();
//		
//		memberVO = memberVO2;
		
//		memberVO.setId(memberVO2.getId());
//		memberVO.setPw(memberVO2.getPw());
		
		int result = mypageService.setUpdate(memberVO);
		
		if(result > 0) {
			log.info("수정 성공");
		} else {
			log.info("수정 실패");
		}
		
		log.info("컨트롤러 Update : {}", memberVO);
		
		return "redirect:../";
		
//		mv.addObject("memberDB", memberVO);
//		mv.setViewName("mypage/info");
		
		
//		// 세션 최신화
//		// 1. 새로운 UsernamePasswordAuthenticationToken 생성하여 AuthenticationManager을 이용해 등록
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(memberVO.getId(), memberVO.getPw()));
//
//		
//		// 2. SecurityContextHolder 안에 있는 Context를 호출해 변경된 Authentication으로 설정
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		
			
//		session.invalidate();
	}
	
}
